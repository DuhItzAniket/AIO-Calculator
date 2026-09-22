package com.aio.calculator.core.common

/** Configuration and documentation for startup/performance work. */
object PerformanceConfig {
    const val enableR8 = true
    const val shrinkResources = true
    const val proguardRules = "proguard-rules.pro"

    data class BaselineProfile(
        val name: String,
        val description: String,
        val startupSequences: List<String>,
        val lazyLoadedComponents: List<String>,
        val avoidOnStartup: List<String>
    )

    val defaultBaselineProfile = BaselineProfile(
        "AIO Calculator Default",
        "Critical user journeys for AIO Calculator",
        listOf("app_launch", "home_calculator_visible", "first_calculation"),
        listOf("specialist_tools", "formula_library", "constants_library"),
        listOf("full_database_sync", "all_currency_rates")
    )
}

object StartupTiming {
    @Volatile var appLaunchStart: Long = 0
    @Volatile var homeCalculatorVisible: Long = 0
    @Volatile var firstCalculationStart: Long = 0
    fun recordAppLaunchStart() { appLaunchStart = System.currentTimeMillis() }
    fun recordHomeCalculatorVisible() { homeCalculatorVisible = System.currentTimeMillis() }
    fun recordFirstCalculation() { firstCalculationStart = System.currentTimeMillis() }
}
