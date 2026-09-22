package com.aio.calculator.core.currency

import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyConverterTest {
    @Test
    fun convertsUsingOfflineReferenceRates() {
        assertEquals(92.0, CurrencyConverter.convert(100.0, "USD", "EUR"), 0.0001)
        assertEquals(1.0, CurrencyConverter.convert(83.0, "INR", "USD"), 0.0001)
    }
}
