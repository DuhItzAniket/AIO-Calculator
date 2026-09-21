package com.aio.calculator.core.common

import kotlinx.serialization.Serializable

/**
 * Represents a category of calculator tools
 */
@Serializable
enum class ToolCategory(val displayName: String, val iconName: String, val order: Int) {
    ALGEBRA("Algebra", "ic_category_algebra", 1),
    STATISTICS("Statistics", "ic_category_statistics", 2),
    GEOMETRY("Geometry", "ic_category_geometry", 3),
    TRIGONOMETRY("Trigonometry", "ic_category_trigonometry", 4),
    CALCULUS("Calculus", "ic_category_calculus", 5),
    PHYSICS("Physics", "ic_category_physics", 6),
    CHEMISTRY("Chemistry", "ic_category_chemistry", 7),
    ELECTRONICS("Electronics", "ic_category_electronics", 8),
    COMPUTER_SCIENCE("Computer Science", "ic_category_computer_science", 9),
    CONVERTERS("Unit Converters", "ic_category_converters", 10),
    FINANCE("Finance", "ic_category_finance", 11),
    HEALTH("Health", "ic_category_health", 12),
    DATETIME("Date & Time", "ic_category_datetime", 13),
    EVERYDAY("Everyday", "ic_category_everyday", 14),
    SHOPPING("Shopping", "ic_category_shopping", 15);

    companion object {
        fun allCategories(): List<ToolCategory> = values().sortedBy { it.order }
        fun fromDisplayName(name: String): ToolCategory? = values().firstOrNull { it.displayName == name }
    }
}

/**
 * Represents the type of calculator UI
 */
@Serializable
enum class CalculatorType {
    BASIC,           // Simple expression input
    SCIENTIFIC,      // Scientific functions
    FORM_BASED,      // Input form with multiple fields
    CONVERTER,       // Unit conversion
    INTERACTIVE,     // Interactive tool (e.g., shopping list)
    GRAPHING         // Function graphing
}

/**
 * Represents an angle mode
 */
@Serializable
enum class AngleMode(val displayName: String) {
    DEGREES("Degrees"),
    RADIANS("Radians")
}

/**
 * Theme options
 */
@Serializable
enum class ThemeMode(val displayName: String, val order: Int) {
    SYSTEM_DEFAULT("System Default", 0),
    LIGHT("Light", 1),
    DARK("Dark", 2),
    OLED_BLACK("OLED Black", 3),
    DYNAMIC_COLOR("Dynamic Color", 4);
}

/**
 * Precision options for decimal display
 */
@Serializable
enum class DecimalPrecision(val places: Int, val displayName: String) {
    AUTO(-1, "Auto"),
    ZERO(0, "0 decimals"),
    ONE(1, "1 decimal"),
    TWO(2, "2 decimals"),
    THREE(3, "3 decimals"),
    FOUR(4, "4 decimals"),
    FIVE(5, "5 decimals"),
    SIX(6, "6 decimals"),
    SCIENTIFIC(-2, "Scientific"),
    ENGINEERING(-3, "Engineering");
}