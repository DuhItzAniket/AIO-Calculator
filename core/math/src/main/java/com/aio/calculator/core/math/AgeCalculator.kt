package com.aio.calculator.core.math

import java.time.LocalDate
import java.time.Period

data class Age(val years: Int, val months: Int, val days: Int)

object AgeCalculator {
    fun between(birthDate: LocalDate, asOf: LocalDate): Age {
        require(!birthDate.isAfter(asOf)) { "Birth date cannot be after the reference date" }
        val period = Period.between(birthDate, asOf)
        return Age(period.years, period.months, period.days)
    }
}
