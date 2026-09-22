package com.aio.calculator.core.currency

import java.util.Date
import com.aio.calculator.core.database.AioDatabase
import com.aio.calculator.core.database.entity.CachedCurrencyRateEntity

/** Provider boundary for exchange-rate data. Implementations may use network or cache storage. */
interface CurrencyRateProvider {
    suspend fun getRate(baseCurrency: String, quoteCurrency: String): Double?
    suspend fun getAllCurrencies(): List<String>
    suspend fun getRateDate(): Date?
}

/**
 * Offline-safe starter provider with a small reference table.
 * Network refresh and durable caching are deliberately isolated for checkpoint 9.
 */
class FrankfurterProvider(private val database: AioDatabase? = null) : CurrencyRateProvider {
    private val rates = mapOf(
        "USD" to mapOf("EUR" to 0.92, "GBP" to 0.79, "INR" to 83.0, "JPY" to 150.0),
        "EUR" to mapOf("USD" to 1.09, "GBP" to 0.86, "INR" to 90.0),
        "GBP" to mapOf("USD" to 1.27, "EUR" to 1.16, "INR" to 105.0),
    )

    override suspend fun getRate(baseCurrency: String, quoteCurrency: String): Double? {
        val base = baseCurrency.uppercase()
        val quote = quoteCurrency.uppercase()
        if (base == quote) return 1.0
        val cached = database?.currencyRateDao()?.get(base, quote)
        if (cached != null && System.currentTimeMillis() - cached.fetchedTimestamp < CACHE_TTL_MS) return cached.rate
        val rate = rates[base]?.get(quote)
        if (rate != null) {
            database?.currencyRateDao()?.upsert(CachedCurrencyRateEntity(base, quote, rate))
        }
        return rate
    }

    override suspend fun getAllCurrencies(): List<String> =
        (rates.keys + rates.values.flatMap { it.keys }).distinct().sorted()

    override suspend fun getRateDate(): Date? = database?.currencyRateDao()?.latestTimestamp()?.let(::Date) ?: Date()

    suspend fun refreshRates() {
        rates.forEach { (base, quotes) ->
            quotes.forEach { (quote, rate) ->
                database?.currencyRateDao()?.upsert(CachedCurrencyRateEntity(base, quote, rate))
            }
        }
    }

    private companion object {
        const val CACHE_TTL_MS = 24L * 60L * 60L * 1000L
    }
}

/** Scheduling boundary reserved for the persistent refresh implementation. */
class CurrencyRateUpdater(private val provider: FrankfurterProvider) {
    suspend fun refreshNow() = provider.refreshRates()
}

data class CurrencyRateEntity(
    val baseCurrency: String,
    val quoteCurrency: String,
    val rate: Double,
    val fetchedTimestamp: Long = System.currentTimeMillis(),
    val provider: String = "offline-reference",
    val rateDate: Date? = null,
)
