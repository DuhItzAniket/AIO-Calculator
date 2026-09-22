package com.aio.calculator.core.common

/** Central metadata registry used by category and search experiences. */
object ToolRegistry {
    private val definitions = listOf(
        ToolDefinition("basic_calculator", "Basic Calculator", ToolCategory.ALGEBRA, "Everyday arithmetic", calculatorType = CalculatorType.BASIC, keywords = listOf("calculate", "arithmetic")),
        ToolDefinition("scientific_calculator", "Scientific Calculator", ToolCategory.ALGEBRA, "Scientific functions and constants", calculatorType = CalculatorType.SCIENTIFIC, keywords = listOf("sin", "cos", "log", "sqrt")),
        ToolDefinition("percentage", "Percentage", ToolCategory.ALGEBRA, "Calculate percentages", keywords = listOf("percent", "discount")),
        ToolDefinition("statistics_mean", "Mean", ToolCategory.STATISTICS, "Arithmetic mean of a data set", keywords = listOf("average")),
        ToolDefinition("statistics_median", "Median", ToolCategory.STATISTICS, "Middle value of a data set"),
        ToolDefinition("geometry_triangle", "Triangle", ToolCategory.GEOMETRY, "Triangle area and perimeter", keywords = listOf("area", "perimeter")),
        ToolDefinition("trigonometry", "Trigonometry", ToolCategory.TRIGONOMETRY, "Trigonometric functions", keywords = listOf("sin", "cos", "tan")),
        ToolDefinition("physics_speed", "Speed", ToolCategory.PHYSICS, "Distance, speed, and time", keywords = listOf("velocity")),
        ToolDefinition("electronics_ohms_law", "Ohm's Law", ToolCategory.ELECTRONICS, "Voltage, current, and resistance", keywords = listOf("voltage", "current", "resistance", "ohm")),
        ToolDefinition("binary_converter", "Numeric Base Converter", ToolCategory.COMPUTER_SCIENCE, "Convert binary, decimal, hexadecimal, and octal", calculatorType = CalculatorType.CONVERTER, keywords = listOf("binary", "hex", "octal")),
        ToolDefinition("length_converter", "Length Converter", ToolCategory.CONVERTERS, "Convert common length units", calculatorType = CalculatorType.CONVERTER, keywords = listOf("distance", "km", "miles")),
        ToolDefinition("finance_emi", "EMI Calculator", ToolCategory.FINANCE, "Estimate loan monthly payments", keywords = listOf("loan", "interest")),
        ToolDefinition("health_bmi", "BMI Calculator", ToolCategory.HEALTH, "Estimate body mass index", keywords = listOf("body", "weight")),
        ToolDefinition("datetime_age", "Age Calculator", ToolCategory.DATETIME, "Calculate age between dates", keywords = listOf("birthday", "date")),
        ToolDefinition("shopping_list", "Shopping List", ToolCategory.SHOPPING, "Track items, tax, discounts, and budget", calculatorType = CalculatorType.INTERACTIVE, keywords = listOf("cart", "bill", "budget"))
    ).sortedWith(compareBy<ToolDefinition> { it.category.order }.thenBy { it.order })

    val allTools: List<ToolDefinition> get() = definitions
    val tools: List<ToolDefinition> get() = definitions

    fun getTool(id: String): ToolDefinition? = definitions.firstOrNull { it.id == id }
    fun getTools(category: ToolCategory): List<ToolDefinition> = definitions.filter { it.category == category }
    fun search(query: String): List<ToolDefinition> = if (query.isBlank()) definitions else definitions.filter { it.matches(query) }
    fun searchTools(query: String): List<ToolDefinition> = search(query)
    fun getFavorites(ids: Set<String>): List<ToolDefinition> = ids.mapNotNull(::getTool)
    fun getRecent(ids: List<String>): List<ToolDefinition> = ids.mapNotNull(::getTool).distinctBy { it.id }
}
