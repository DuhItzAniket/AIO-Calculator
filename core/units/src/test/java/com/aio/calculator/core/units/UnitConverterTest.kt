package com.aio.calculator.core.units

import org.junit.Assert.assertEquals
import org.junit.Test

class UnitConverterTest {
    @Test
    fun convertsRepresentativeUnits() {
        assertEquals(1000.0, UnitConverter.length(1.0, "km", "m"), 0.0001)
        assertEquals(2.20462, UnitConverter.mass(1.0, "kg", "lb"), 0.0001)
        assertEquals(32.0, UnitConverter.temperature(0.0, "c", "f"), 0.0001)
        assertEquals(273.15, UnitConverter.temperature(0.0, "c", "k"), 0.0001)
    }

    @Test(expected = IllegalStateException::class)
    fun unsupportedUnitIsRejected() {
        UnitConverter.length(1.0, "yd", "m")
    }
}
