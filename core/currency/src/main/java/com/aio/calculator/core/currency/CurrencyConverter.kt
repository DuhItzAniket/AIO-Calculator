package com.aio.calculator.core.currency

/** Offline reference rates used until a network refresh is explicitly requested. */
object CurrencyConverter {
    private val usdRates = mapOf("USD" to 1.0, "EUR" to 0.92, "GBP" to 0.79, "INR" to 83.0, "JPY" to 150.0)

    fun convert(value: Double, from: String, to: String): Double {
        val fromRate = usdRates[from.uppercase()] ?: error("Unsupported currency: $from")
        val toRate = usdRates[to.uppercase()] ?: error("Unsupported currency: $to")
        return value / fromRate * toRate
    }
}
