package com.aio.calculator.core.currency

import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.aio.calculator.core.common.CalculationError
import com.aio.calculator.core.common.DataUnavailableError
import com.aio.calculator.core.database.AioDatabase
import com.aio.calculator.core.database.dao.CalculationHistoryDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isCancelled
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

/**
 * Currency rate provider abstraction.
 * All currency providers should implement this interface.
 */
interface CurrencyRateProvider {
    /**
     * Get the rate from base currency to quote currency.
     * Returns null if rate is unavailable.
     */
    suspend fun getRate(baseCurrency: String, quoteCurrency: String): Double?

    /**
     * Get all available currencies.
     */
    suspend fun getAllCurrencies(): List<String>

    /**
     * Get rate date for caching/stale display purposes.
     */
    suspend fun getRateDate(): Date?
}

/**
 * Frankfurter API provider (public, no-key required).
 * Reference: https://api.frankfurter.app
 */
class FrankfurterProvider @Inject constructor(
    private val database: AioDatabase
) : CurrencyRateProvider {

    private val BASE_URL = "https://api.frankfurter.app"
    private val WORKER_TAG = "currency_rate_refresh"

    override suspend fun getRate(baseCurrency: String, quoteCurrency: String): Double? {
        return try {
            // Check cached rate first
            val cached = database.currencyRateDao().getRate(baseCurrency, quoteCurrency)
            if (cached != null && isRateFresh(cached.fetchedTimestamp)) {
                return cached.rate
            }

            // Fetch from Frankfurter API
            val url = "$BASE_URL/latest?from=$baseCurrency&to=$quoteCurrency"
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 15000
            connection.readTimeout = 15000

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                val scanner = java.util.Scanner(inputStream.useDelimiter("Z"))
                val json = scanner.useDelimiter("\\A").next()
                scanner.close()

                // Parse JSON response
                val rate = parseFrankfurterResponse(json)
                if (rate != null) {
                    // Cache the rate
                    database.currencyRateDao().upsert(
                        baseCurrency,
                        quoteCurrency,
                        rate,
                        System.currentTimeMillis(),
                        "Frankfurter"
                    )
                }
                rate
            } else {
                // Use cached rate if network fails
                cached?.rate
            }
        } catch (e: Exception) {
            // Use cached rate on error
            database.currencyRateDao().getRate(baseCurrency, quoteCurrency)?.rate
        }
    }

    override suspend fun getAllCurrencies(): List<String> {
        try {
            val url = "$BASE_URL/latest"
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 15000

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                val scanner = java.util.Scanner(inputStream.useDelimiter("Z"))
                val json = scanner.useDelimiter("\\A").next()
                scanner.close()

                // Parse currencies from JSON
                // Frankfurter returns: {"rates":{"USD":1.0,"EUR":0.91,...},"date":"2024-01-15"}
                val rateMap = extractFrankfurterRates(json)
                return rateMap.keys.toList() + listOf("base")
            }
            emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getRateDate(): Date? {
        return try {
            val cached = database.currencyRateDao().getLatestRate()
            cached?.rateDate
        } catch (e: Exception) {
            null
        }
    }

    private fun isRateFresh(fetchedTimestamp: Long): Boolean {
        val maxStaleMillis = 24 * 60 * 60 * 1000 // 24 hours
        val now = System.currentTimeMillis()
        return (now - fetchedTimestamp) < maxStaleMillis
    }

    private fun parseFrankfurterResponse(json: String): Double? {
        // Frankfurter returns: {"rates":{"USD":1.0},"base":"USD","date":"2024-01-15"}
        // We need to extract the rate for the specific pair
        // Simplified parsing - in production use JSON parser
        return when {
            json.contains("\"rates\"") -> {
                // Try to extract rate value
                val ratePattern = "\"rate\":([0-9.]+)"
                val pattern = java.util.regex.Pattern(ratePattern)
                val matcher = pattern.matcher(json)
                if (matcher.find()) {
                    matcher.group(1).toDoubleOrNull()
                } else null
            }
            else -> null
        }
    }

    private fun extractFrankfurterRates(json: String): Map<String, Double> {
        // Simplified extraction - return empty map for now
        // In production, parse the "rates" object from JSON
        return emptyMap()
    }
}

/**
 * WorkManager to periodically refresh currency rates.
 */
class CurrencyRateUpdater @Inject constructor(
    private val context: Context,
    private val provider: CurrencyRateProvider,
    private val database: AioDatabase
) {
    companion object {
        const val WORKER_TAG = "currency_rate_refresh"
        const val FREQUENCY_HOURS = 24 // Refresh every 24 hours
    }

    fun startPeriodicUpdates() {
        val uploadWork = OneTimeWorkRequest.Builder(CurrencyWorker::class.java)
            .setInitialDelay(FREQUENCY_HOURS, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(context).enqueueUniqueWork(
            WORKER_TAG,
            WorkManager.WorkerMode.REPLACE,
            uploadWork
        )
    }
}

/**
 * Worker that fetches currency rates and updates the database.
 */
class CurrencyWorker @Inject constructor(
    context: Context,
    workerParams: WorkerParameters,
    private val provider: CurrencyRateProvider,
    private val database: AioDatabase
) : androidx.work.Worker(context, workerParams) {

    override fun doWork(): Result {
        try {
            // Fetch rates for major currencies
            val majorCurrencies = listOf("USD", "EUR", "GBP", "JPY", "CAD", "AUD", "CHF", "CNY")

            for (baseCurrency in majorCurrencies) {
                for (quoteCurrency in majorCurrencies) {
                    if (baseCurrency != quoteCurrency) {
                        val rate = provider.getRate(baseCurrency, quoteCurrency)
                        // Rate is already cached inside getRate()
                    }
                }
            }

            // Schedule next update
            val updater = CurrencyRateUpdater(
                context,
                provider,
                database
            )
            updater.startPeriodicUpdates()

            return Result.success()
        } catch (e: Exception) {
            // Log error but don't crash
            androidx.work.WorkerLogging.getInstance()
                .log("CurrencyWorker", "Error updating rates: ${e.message}")
            return Result.failure()
        }
    }
}

/**
 * Data entity for cached currency rates
 */
data class CurrencyRateEntity(
    val baseCurrency: String,
    val quoteCurrency: String,
    val rate: Double,
    val fetchedTimestamp: Long,
    val provider: String,
    val rateDate: Date? = null
)