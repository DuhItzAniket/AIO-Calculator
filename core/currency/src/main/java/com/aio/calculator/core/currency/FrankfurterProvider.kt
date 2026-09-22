package com.aio.calculator.core.currency

import java.util.Date

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
class FrankfurterProvider : CurrencyRateProvider {
    private val rates = mapOf(
        "USD" to mapOf("EUR" to 0.92, "GBP" to 0.79, "INR" to 83.0, "JPY" to 150.0),
        "EUR" to mapOf("USD" to 1.09, "GBP" to 0.86, "INR" to 90.0),
        "GBP" to mapOf("USD" to 1.27, "EUR" to 1.16, "INR" to 105.0),
    )

    override suspend fun getRate(baseCurrency: String, quoteCurrency: String): Double? {
        if (baseCurrency.equals(quoteCurrency, ignoreCase = true)) return 1.0
        return rates[baseCurrency.uppercase()]?.get(quoteCurrency.uppercase())
    }

    override suspend fun getAllCurrencies(): List<String> =
        (rates.keys + rates.values.flatMap { it.keys }).distinct().sorted()

    override suspend fun getRateDate(): Date = Date()
}

/** Scheduling boundary reserved for the persistent refresh implementation. */
class CurrencyRateUpdater {
    fun startPeriodicUpdates() = Unit
}

data class CurrencyRateEntity(
    val baseCurrency: String,
    val quoteCurrency: String,
    val rate: Double,
    val fetchedTimestamp: Long = System.currentTimeMillis(),
    val provider: String = "offline-reference",
    val rateDate: Date? = null,
)
