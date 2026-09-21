package com.aio.calculator.core.common

import kotlinx.serialization.Serializable

/**
 * Calculator type enumeration for tool routing
 */
@Serializable
enum class CalculatorType {
    BASIC,
    SCIENTIFIC,
    ALGEBRA,
    STATISTICS,
    GEOMETRY,
    TRIGONOMETRY,
    CALCULUS,
    PHYSICS,
    CHEMISTRY,
    ELECTRONICS,
    COMPUTER_SCIENCE,
    CONVERTER,
    FINANCE,
    HEALTH,
    DATETIME,
    EVERYDAY,
    SHOPPING
}

/**
 * Tool registry contains ALL calculator/tool metadata in one place.
 * This is the SINGLE SOURCE OF TRUTH for:
 * - Category listings
 * - Search functionality
 * - Favorites management
 * - Recent tools tracking
 * - Navigation routing
 * 
 * Adding a new calculator requires adding ONE entry here.
 */
object ToolRegistry {

    // ==========================================================================
    // ALGEBRA TOOLS
    // ==========================================================================
    val percentage = ToolDefinition(
        id = "percentage",
        title = "Percentage",
        category = ToolCategory.ALGEBRA,
        description = "Calculate percentage of a value",
        iconName = "ic_category_algebra",
        keywords = listOf("percentage", "of", "calc"),
        aliases = listOf("percent", "pct"),
        calculatorType = CalculatorType.BASIC,
        supportsFavorite = true,
        supportsHistory = true
    )

    val percentageChange = ToolDefinition(
        id = "percentage_change",
        title = "Percentage Change",
        category = ToolCategory.ALGEBRA,
        description = "Calculate percentage increase or decrease",
        iconName = "ic_category_algebra",
        keywords = listOf("percentage", "change", "increase", "decrease"),
        aliases = listOf("percent change"),
        calculatorType = CalculatorType.BASIC,
        supportsFavorite = true,
        supportsHistory = true
    )

    val percentageDifference = ToolDefinition(
        id = "percentage_difference",
        title = "Percentage Difference",
        category = ToolCategory.ALGEBRA,
        description = "Calculate percentage difference between two values",
        iconName = "ic_category_algebra",
        keywords = listOf("percentage", "difference", "between"),
        aliases = listOf("percent diff"),
        calculatorType = CalculatorType.BASIC,
        supportsFavorite = true,
        supportsHistory = true
    )

    val average = ToolDefinition(
        id = "average",
        title = "Average",
        category = ToolCategory.ALGEBRA,
        description = "Calculate the arithmetic mean",
        iconName = "ic_category_algebra",
        keywords = listOf("average", "mean", "arithmetic"),
        aliases = listOf("mean", "arithmetic mean"),
        calculatorType = CalculatorType.BASIC,
        supportsFavorite = true,
        supportsHistory = true
    )

    val weightedAverage = ToolDefinition(
        id = "weighted_average",
        title = "Weighted Average",
        category = ToolCategory.ALGEBRA,
        description = "Calculate weighted average with weights",
        iconName = "ic_category_algebra",
        keywords = listOf("weighted", "average"),
        aliases = listOf("weighted avg"),
        calculatorType = CalculatorType.BASIC,
        supportsFavorite = true,
        supportsHistory = true
    )

    val ratio = ToolDefinition(
        id = "ratio",
        title = "Ratio",
        category = ToolCategory.ALGEBRA,
        description = "Calculate ratio between two values",
        iconName = "ic_category_algebra",
        keywords = listOf("ratio", "proportion"),
        aliases = listOf("proportion"),
        calculatorType = CalculatorType.BASIC,
        supportsFavorite = true,
        supportsHistory = true
    )

    val proportion = ToolDefinition(
        id = "proportion",
        title = "Proportion",
        category = ToolCategory.ALGEBRA,
        description = "Check if two ratios are proportional",
        iconName = "ic_category_algebra",
        keywords = listOf("proportion"),
        calculatorType = CalculatorType.BASIC,
        supportsFavorite = true,
        supportsHistory = true
    )

    // ==========================================================================
    // STATISTICS & PROBABILITY TOOLS
    // ==========================================================================
    val statisticsMean = ToolDefinition(
        id = "statistics_mean",
        title = "Mean",
        category = ToolCategory.STATISTICS,
        description = "Calculate the arithmetic mean",
        iconName = "ic_category_statistics",
        keywords = listOf("mean", "average"),
        aliases = listOf("arithmetic mean"),
        calculatorType = CalculatorType.BASIC,
        supportsFavorite = true,
        supportsHistory = true
    )

    val statisticsMedian = ToolDefinition(
        id = "statistics_median",
        title = "Median",
        category = ToolCategory.STATISTICS,
        description = "Calculate the median value",
        iconName = "ic_category_statistics",
        keywords = listOf("median"),
        calculatorType = CalculatorType.BASIC,
        supportsFavorite = true,
        supportsHistory = true
    )

    val statisticsMode = ToolDefinition(
        id = "statistics_mode",
        title = "Mode",
        category = ToolCategory.STATISTICS,
        description = "Calculate the most frequent value",
        iconName = "ic_category_statistics",
        keywords = listOf("mode", "most frequent"),
        calculatorType = CalculatorType.BASIC,
        supportsFavorite = true,
        supportsHistory = true
    )

    val statisticsRange = ToolDefinition(
        id = "statistics_range",
        title = "Range",
        category = ToolCategory.STATISTICS,
        description = "Calculate range (max - min)",
        iconName = "ic_category_statistics",
        keywords = listOf("range"),
        calculatorType = CalculatorType.BASIC,
        supportsFavorite = true,
        supportsHistory = true
    )

    val statisticsVariance = ToolDefinition(
        id = "statistics_variance",
        title = "Variance",
        category = ToolCategory.STATISTICS,
        description = "Calculate variance",
        iconName = "ic_category_statistics",
        keywords = listOf("variance"),
        calculatorType = CalculatorType.BASIC,
        supportsFavorite = true,
        supportsHistory = true
    )

    val statisticsStdDeviation = ToolDefinition(
        id = "statistics_std_dev",
        title = "Standard Deviation",
        category = ToolCategory.STATISTICS,
        description = "Calculate standard deviation",
        iconName = "ic_category_statistics",
        keywords = listOf("standard deviation"),
        calculatorType = CalculatorType.BASIC,
        supportsFavorite = true,
        supportsHistory = true
    )

    // ==========================================================================
    // GEOMETRY TOOLS (2D)
    // ==========================================================================
    val geometrySquare = ToolDefinition(
        id = "geometry_square",
        title = "Square",
        category = ToolCategory.GEOMETRY,
        description = "Calculate area, perimeter of a square",
        iconName = "ic_category_geometry",
        keywords = listOf("square", "area", "perimeter"),
        calculatorType = CalculatorType.CONVERTER,
        supportsFavorite = true,
        supportsHistory = true
    )

    val geometryRectangle = ToolDefinition(
        id = "geometry_rectangle",
        title = "Rectangle",
        category = ToolCategory.GEOMETRY,
        description = "Calculate area, perimeter of a rectangle",
        iconName = "ic_category_geometry",
        keywords = listOf("rectangle", "area", "perimeter"),
        calculatorType = CalculatorType.CONVERTER,
        supportsFavorite = true,
        supportsHistory = true
    )

    val geometryTriangle = ToolDefinition(
        id = "geometry_triangle",
        title = "Triangle",
        category = ToolCategory.GEOMETRY,
        description = "Calculate area, perimeter of a triangle",
        iconName = "ic_category_geometry",
        keywords = listOf("triangle", "area", "perimeter"),
        calculatorType = CalculatorType.CONVERTER,
        supportsFavorite = true,
        supportsHistory = true
    )

    // ==========================================================================
    // TRIGONOMETRY TOOLS
    // ==========================================================================
    val trigonometrySin = ToolDefinition(
        id = "trigonometry_sin",
        title = "Sine",
        category = ToolCategory.TRIGONOMETRY,
        description = "Calculate sine of an angle",
        iconName = "ic_category_trigonometry",
        keywords = listOf("sin", "trig"),
        calculatorType = CalculatorType.SCIENTIFIC,
        supportsFavorite = true,
        supportsHistory = true
    )

    val trigonometryCos = ToolDefinition(
        id = "trigonometry_cos",
        title = "Cosine",
        category = ToolCategory.TRIGONOMETRY,
        description = "Calculate cosine of an angle",
        iconName = "ic_category_trigonometry",
        keywords = listOf("cos", "trig"),
        calculatorType = CalculatorType.SCIENTIFIC,
        supportsFavorite = true,
        supportsHistory = true
    )

    val trigonometryTan = ToolDefinition(
        id = "trigonometry_tan",
        title = "Tangent",
        category = ToolCategory.TRIGONOMETRY,
        description = "Calculate tangent of an angle",
        iconName = "ic_category_trigonometry",
        keywords = listOf("tan", "trig"),
        calculatorType = CalculatorType.SCIENTIFIC,
        supportsFavorite = true,
        supportsHistory = true
    )

    // ==========================================================================
    // CALCULUS TOOLS
    // ==========================================================================
    // Basic calculus placeholders
    val calculusDerivative = ToolDefinition(
        id = "calculus_derivative",
        title = "Derivative",
        category = ToolCategory.CALCULUS,
        description = "Calculate derivative of a function",
        iconName = "ic_category_calculus",
        keywords = listOf("derivative"),
        calculatorType = CalculatorType.SCIENTIFIC,
        supportsFavorite = true,
        supportsHistory = true
    )

    // ==========================================================================
    // PHYSICS TOOLS
    // ==========================================================================
    val physicsSpeed = ToolDefinition(
        id = "physics_speed",
        title = "Speed",
        category = ToolCategory.PHYSICS,
        description = "Calculate speed, distance, time",
        iconName = "ic_category_physics",
        keywords = listOf("speed", "distance", "time"),
        calculatorType = CalculatorType.CONVERTER,
        supportsFavorite = true,
        supportsHistory = true
    )

    val physicsForce = ToolDefinition(
        id = "physics_force",
        title = "Force",
        category = ToolCategory.PHYSICS,
        description = "Calculate force, mass, acceleration",
        iconName = "ic_category_physics",
        keywords = listOf("force", "mass", "acceleration"),
        calculatorType = CalculatorType.CONVERTER,
        supportsFavorite = true,
        supportsHistory = true
    )

    // ==========================================================================
    // CHEMISTRY TOOLS
    // ==========================================================================
    val chemistryMolarMass = ToolDefinition(
        id = "chemistry_molar_mass",
        title = "Molar Mass",
        category = ToolCategory.CHEMISTRY,
        description = "Calculate molar mass",
        iconName = "ic_category_chemistry",
        keywords = listOf("molar mass"),
        calculatorType = CalculatorType.CONVERTER,
        supportsFavorite = true,
        supportsHistory = true
    )

    // ==========================================================================
    // ELECTRONICS TOOLS
    // ==========================================================================
    val electronicsOhmsLaw = ToolDefinition(
        id = "electronics_ohms_law",
        title = "Ohm's Law",
        category = ToolCategory.ELECTRONICS,
        description = "Calculate voltage, current, resistance",
        iconName = "ic_category_electronics",
        keywords = listOf("ohms law", "voltage", "current", "resistance"),
        calculatorType = CalculatorType.CONVERTER,
        supportsFavorite = true,
        supportsHistory = true
    )

    // ==========================================================================
    // COMPUTER SCIENCE TOOLS
    // ==========================================================================
    val binaryConverter = ToolDefinition(
        id = "binary_converter",
        title = "Binary Converter",
        category = ToolCategory.COMPUTER_SCIENCE,
        description = "Convert between binary, decimal, hex",
        iconName = "ic_category_computer_science",
        keywords = listOf("binary", "convert", "decimal", "hex"),
        calculatorType = CalculatorType.CONVERTER,
        supportsFavorite = true,
        supportsHistory = true
    )

    // ==========================================================================
    // UNIT CONVERTERS
    // ==========================================================================
    val converterLength = ToolDefinition(
        id = "converter_length",
        title = "Length Converter",
        category = ToolCategory.CONVERTERS,
        description = "Convert between length units",
        iconName = "ic_category_converters",
        keywords = listOf("length", "convert", "cm", "inch", "m", "ft"),
        calculatorType = CalculatorType.CONVERTER,
        supportsFavorite = true,
        supportsHistory = true
    )

    val converterTemperature = ToolDefinition(
        id = "converter_temperature",
        title = "Temperature Converter",
        category = ToolCategory.CONVERTERS,
        description = "Convert between temperature units",
        iconName = "ic_category_converters",
        keywords = listOf("temperature", "convert", "celsius", "fahrenheit", "kelvin"),
        calculatorType = CalculatorType.CONVERTER,
        supportsFavorite = true,
        supportsHistory = true
    )

    // ==========================================================================
    // FINANCE TOOLS
    // ==========================================================================
    val financeCurrencyConverter = ToolDefinition(
        id = "finance_currency_converter",
        title = "Currency Converter",
        category = ToolCategory.FINANCE,
        description = "Convert between currencies",
        iconName = "ic_category_finance",
        keywords = listOf("currency", "convert"),
        calculatorType = CalculatorType.CONVERTER,
        supportsFavorite = true,
        supportsHistory = true
    )

    val financeEMI = ToolDefinition(
        id = "finance_emi",
        title = "EMI Calculator",
        category = ToolCategory.FINANCE,
        description = "Calculate equated monthly installment",
        iconName = "ic_category_finance",
        keywords = listOf("emi", "loan", "monthly", "payment"),
        calculatorType = CalculatorType.CONVERTER,
        supportsFavorite = true,
        supportsHistory = true
    )

    // ==========================================================================
    // HEALTH TOOLS
    // ==========================================================================
    val healthBMI = ToolDefinition(
        id = "health_bmi",
        title = "BMI Calculator",
        category = ToolCategory.HEALTH,
        description = "Calculate Body Mass Index",
        iconName = "ic_category_health",
        keywords = listOf("bmi", "body mass index"),
        calculatorType = CalculatorType.CONVERTER,
        supportsFavorite = true,
        supportsHistory = true
    )

    // ==========================================================================
    // DATE & TIME TOOLS
    // ==========================================================================
    val datetimeAge = ToolDefinition(
        id = "datetime_age",
        title = "Age Calculator",
        category = ToolCategory.DATETIME,
        description = "Calculate age from date of birth",
        iconName = "ic_category_datetime",
        keywords = listOf("age", "birthday", "calculate age"),
        calculatorType = CalculatorType.CONVERTER,
        supportsFavorite = true,
        supportsHistory = true
    )

    // ==========================================================================
    // EVERYDAY TOOLS
    // ==========================================================================
    val everydayMileage = ToolDefinition(
        id = "everyday_mileage",
        title = "Mileage Calculator",
        category = ToolCategory.EVERYDAY,
        description = "Calculate fuel cost, mileage",
        iconName = "ic_category_everyday",
        keywords = listOf("mileage", "fuel", "cost"),
        calculatorType = CalculatorType.CONVERTER,
        supportsFavorite = true,
        supportsHistory = true
    )

    // ==========================================================================
    // SHOPPING TOOLS
    // ==========================================================================
    val shoppingListCalculator = ToolDefinition(
        id = "shopping_list",
        title = "Shopping List",
        category = ToolCategory.SHOPPING,
        description = "Calculate subtotal, tax, total for shopping items",
        iconName = "ic_category_shopping",
        keywords = listOf("shopping", "list", "subtotal", "tax", "total"),
        calculatorType = CalculatorType.CONVERTER,
        supportsFavorite = true,
        supportsHistory = true
    )

    // ==========================================================================
    // ALL TOOLS COLLECTION (ordered by category then order)
    // ==========================================================================
    @get:Suppress("UNCHECKED_CAST")
    @get:Serializable
    private val _allTools = listOf(
        // Algebra tools
        percentage,
        percentageChange,
        percentageDifference,
        average,
        weightedAverage,
        ratio,
        proportion,
        // Statistics tools
        statisticsMean,
        statisticsMedian,
        statisticsMode,
        statisticsRange,
        statisticsVariance,
        statisticsStdDeviation,
        // Geometry tools
        geometrySquare,
        geometryRectangle,
        geometryTriangle,
        // Trigonometry tools
        trigonometrySin,
        trigonometryCos,
        trigonometryTan,
        // Calculus tools
        calculusDerivative,
        // Physics tools
        physicsSpeed,
        physicsForce,
        // Chemistry tools
        chemistryMolarMass,
        // Electronics tools
        electronicsOhmsLaw,
        // Computer science tools
        binaryConverter,
        // Unit converters
        converterLength,
        converterTemperature,
        // Finance tools
        financeCurrencyConverter,
        financeEMI,
        // Health tools
        healthBMI,
        // Date & time tools
        datetimeAge,
        // Everyday tools
        everydayMileage,
        // Shopping tools
        shoppingListCalculator
    ).sortedBy { (tool) -> (tool.category.order, tool.order) }

    /** Get all tools in category order */
    @get:Suppress("UNCHECKED_CAST")
    @get:Serializable
    val allTools: List<ToolDefinition> = _allTools

    /** Get tools by category */
    @get:Suppress("UNCHECKED_CAST")
    @get:Serializable
    fun getTools(category: ToolCategory): List<ToolDefinition> {
        return _allTools.filter { it.category == category }
    }

    /** Search tools by query */
    fun searchTools(query: String): List<ToolDefinition> {
        if (query.isBlank()) return _allTools
        val lowerQuery = query.lowercase()
        return _allTools.filter { tool ->
            tool.searchTerms.any { it.lowercase().contains(lowerQuery) }
        }
    }

    /** Get tool by ID */
    @get:Suppress("UNCHECKED_CAST")
    fun getTool(id: String): ToolDefinition? {
        return _allTools.firstOrNull { it.id == id }
    }

    /** Get favorite tools */
    @get:Suppress("UNCHECKED_CAST")
    fun getFavorites(favoriteIds: Set<String>): List<ToolDefinition> {
        return favoriteIds.mapNotNull { getTool(it) }?.sortedBy { it.category.order }.thenBy { it.order } ?: emptyList()
    }

    /** Get recent tools */
    @get:Suppress("UNCHECKED_CAST")
    fun getRecent(recentIds: List<String>): List<ToolDefinition> {
        return recentIds.mapNotNull { getTool(it) }?.distinctBy { it.id } ?: emptyList()
    }
}