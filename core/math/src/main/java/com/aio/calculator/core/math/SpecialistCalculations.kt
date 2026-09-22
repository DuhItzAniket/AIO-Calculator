package com.aio.calculator.core.math

import com.aio.calculator.core.common.AngleMode

/** Pure formulas used by specialist tools and unit tests. */
object SpecialistCalculations {
    fun sine(value: Double, angleMode: AngleMode): Double =
        Math.sin(if (angleMode == AngleMode.DEGREES) Math.toRadians(value) else value)

    fun speed(distance: Double, time: Double): Double {
        require(time > 0.0) { "Time must be greater than zero" }
        return distance / time
    }

    fun ohmsVoltage(current: Double, resistance: Double): Double = current * resistance

    fun molarity(moles: Double, liters: Double): Double {
        require(moles >= 0.0 && liters > 0.0) { "Moles must be non-negative and volume must be positive" }
        return moles / liters
    }

    fun emi(principal: Double, annualRatePercent: Double, months: Int): Double {
        require(principal >= 0.0 && annualRatePercent >= 0.0 && months > 0) { "Invalid loan inputs" }
        val monthlyRate = annualRatePercent / 1200.0
        return if (monthlyRate == 0.0) principal / months
        else principal * monthlyRate * Math.pow(1 + monthlyRate, months.toDouble()) /
            (Math.pow(1 + monthlyRate, months.toDouble()) - 1)
    }

    fun bmi(weightKg: Double, heightMeters: Double): Double {
        require(weightKg > 0.0 && heightMeters > 0.0) { "Weight and height must be greater than zero" }
        return weightKg / (heightMeters * heightMeters)
    }
}
