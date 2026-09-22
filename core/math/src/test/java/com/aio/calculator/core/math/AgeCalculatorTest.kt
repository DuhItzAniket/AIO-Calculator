package com.aio.calculator.core.math

import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Test

class AgeCalculatorTest {
    @Test
    fun calculatesCalendarAge() {
        val age = AgeCalculator.between(LocalDate.of(2000, 1, 15), LocalDate.of(2025, 3, 20))
        assertEquals(Age(25, 2, 5), age)
    }
}
