package com.aio.calculator.core.math

import com.aio.calculator.core.common.AngleMode
import org.junit.Assert.assertEquals
import org.junit.Test

class SpecialistCalculationsTest {
    @Test
    fun formulasProduceExpectedValues() {
        assertEquals(1.0, SpecialistCalculations.sine(90.0, AngleMode.DEGREES), 0.0001)
        assertEquals(60.0, SpecialistCalculations.speed(120.0, 2.0), 0.0001)
        assertEquals(12.0, SpecialistCalculations.ohmsVoltage(2.0, 6.0), 0.0001)
        assertEquals(0.5, SpecialistCalculations.molarity(1.0, 2.0), 0.0001)
        assertEquals(100.0, SpecialistCalculations.emi(1200.0, 0.0, 12), 0.0001)
        assertEquals(22.8571, SpecialistCalculations.bmi(70.0, 1.75), 0.001)
    }

    @Test(expected = IllegalArgumentException::class)
    fun zeroTimeIsRejected() {
        SpecialistCalculations.speed(10.0, 0.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidBmiInputsAreRejected() {
        SpecialistCalculations.bmi(70.0, 0.0)
    }
}
