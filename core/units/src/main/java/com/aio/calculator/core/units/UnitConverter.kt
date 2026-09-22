package com.aio.calculator.core.units

/** Offline conversion utilities with explicit base-unit factors. */
object UnitConverter {
    private val lengthFactors = mapOf("m" to 1.0, "km" to 1000.0, "cm" to 0.01, "ft" to 0.3048, "mi" to 1609.344)
    private val massFactors = mapOf("kg" to 1.0, "g" to 0.001, "lb" to 0.45359237, "oz" to 0.0283495231)

    fun length(value: Double, from: String, to: String): Double =
        value * factor(lengthFactors, from) / factor(lengthFactors, to)

    fun mass(value: Double, from: String, to: String): Double =
        value * factor(massFactors, from) / factor(massFactors, to)

    fun temperature(value: Double, from: String, to: String): Double {
        val celsius = when (from.lowercase()) {
            "c" -> value
            "f" -> (value - 32.0) * 5.0 / 9.0
            "k" -> value - 273.15
            else -> error("Unsupported temperature unit: $from")
        }
        return when (to.lowercase()) {
            "c" -> celsius
            "f" -> celsius * 9.0 / 5.0 + 32.0
            "k" -> celsius + 273.15
            else -> error("Unsupported temperature unit: $to")
        }
    }

    private fun factor(factors: Map<String, Double>, unit: String): Double =
        factors[unit.lowercase()] ?: error("Unsupported unit: $unit")
}
