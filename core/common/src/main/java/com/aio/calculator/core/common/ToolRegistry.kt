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

    // ... [More algebra tools would be added here following the same pattern]
    // Following the spec: Percentage, Percentage change, Percentage difference, Average,
    // Weighted average, Ratio, Proportion, Fractions, Fraction simplification,
    // Decimal to fraction, Equations, Linear equations, Quadratic equations,
    // Simultaneous equations, GCF, LCM, Prime checker, Prime factorization,
    // Divisibility, Factorial, Permutations, Combinations, Number generator,
    // Fibonacci, Arithmetic sequence, Geometric sequence, Polynomial tools

    // ==========================================================================
    // STATISTICS & PROBABILITY TOOLS
    // ==========================================================================
    // Following the spec: Mean, Median, Mode, Range, Variance, Standard deviation,
    // Quartiles, Percentiles, IQR, Z-score, Weighted statistics, Basic probability,
    // Conditional probability, Bayes theorem, Binomial probability, Expected value

    // ==========================================================================
    // GEOMETRY TOOLS
    // ==========================================================================
    // Following the spec: 2D (Square, Rectangle, Triangle, Equilateral triangle,
    // Parallelogram, Rhombus, Trapezoid, Kite, Circle, Ellipse, Sector, Arc, Polygon)
    // 3D (Cube, Cuboid, Cylinder, Cone, Sphere, Hemisphere, Prism, Pyramid, Torus)
    // Calculations: area, perimeter, circumference, volume, surface area,
    // missing dimensions, triangle solver

    // ==========================================================================
    // TRIGONOMETRY TOOLS
    // ==========================================================================
    // Following the spec: sin, cos, tan, inverse functions, hyperbolic functions,
    // degree/radian conversion, Pythagorean theorem, right triangle solver,
    // law of sines, law of cosines

    // ==========================================================================
    // CALCULUS TOOLS
    // ==========================================================================
    // Following the spec: derivative, numerical derivative, integration,
    // definite integral, numerical integration, limits, roots, function analysis,
    // basic graphing infrastructure

    // ==========================================================================
    // PHYSICS TOOLS
    // ==========================================================================
    // Following the spec: speed, velocity, acceleration, distance, time, kinematics,
    // force, momentum, impulse, work, energy, power, pressure, density, torque,
    // angular velocity, angular acceleration, centripetal force, gravity, waves,
    // frequency, wavelength, period, decibel calculations, lens equation,
    // mirror equation, magnification, ideal gas law, heat, specific heat,
    // thermal expansion

    // ==========================================================================
    // CHEMISTRY TOOLS
    // ==========================================================================
    // Following the spec: molar mass, mole calculator, mass/mole conversion,
    // Avogadro conversion, molarity, molality, normality, dilution, ppm, ppb,
    // pH, pOH, concentration, ideal gas law, stoichiometry, limiting reagent,
    // theoretical yield, percentage yield, half-life, empirical formula,
    // molecular formula, periodic table

    // ==========================================================================
    // ELECTRONICS TOOLS
    // ==========================================================================
    // Following the spec: Ohm's Law, electrical power, electrical energy,
    // series resistance, parallel resistance, resistor divider, current divider,
    // resistor color code, E-series resistors, capacitor series/parallel,
    // RC time constant, capacitor charging/discharging, inductor series/parallel,
    // RL time constant, LED resistor, LED power, battery runtime, battery energy,
    // regulator power loss, buck converter efficiency, fuse sizing, wire voltage drop,
    // power budget, truth tables, Boolean algebra, binary arithmetic

    // ==========================================================================
    // COMPUTER SCIENCE TOOLS
    // ==========================================================================
    // Following the spec: binary, octal, decimal, hexadecimal, base-N,
    // bitwise AND, bitwise OR, XOR, NOT, shifts, bit masks, two's complement,
    // ASCII, Unicode, epoch time, timestamp conversion, RGB/HEX, CIDR,
    // subnet calculator, network address, broadcast address, host range,
    // wildcard mask, programming reference utilities

    // ==========================================================================
    // UNIT CONVERTERS
    // ==========================================================================
    // Following the spec: Acceleration, Angle, Area, Cooking, Data Storage,
    // Data Transfer, Energy, Force, Fuel, Length, Numeric Base, Power, Pressure,
    // Ring Size, Roman Numerals, Shoe Size, Speed, Temperature, Time, Torque,
    // Volume, Volumetric Flow, Weight, Density, Frequency, Charge, Voltage,
    // Resistance, Capacitance, Inductance, Magnetic field, Magnetic flux,
    // Concentration, Scientific units

    // ==========================================================================
    // FINANCE TOOLS
    // ==========================================================================
    // Following the spec: Currency converter, Unit price, Sales tax, GST, Tip,
    // Bill split, Discount, Profit, Loss, Markup, Margin, Loan payment, EMI,
    // Loan tenure, Interest, Simple interest, Compound interest, Amortization,
    // Prepayment, ROI, CAGR, SIP, Lump sum investment, Future value,
    // Present value, Inflation calculator, FD, RD

    // ==========================================================================
    // HEALTH TOOLS
    // ==========================================================================
    // Following the spec: BMI, BMR, TDEE, Calorie burn estimate, Body fat,
    // Macro calculator, Protein estimate, Water intake estimate, Pace, Running pace

    // ==========================================================================
    // DATE & TIME TOOLS
    // ==========================================================================
    // Following the spec: Age, Add date, Subtract date, Date difference,
    // Time interval, Business days, Week number, Day of year, Leap year,
    // Unix timestamp, Epoch conversion, Time zones

    // ==========================================================================
    // EVERYDAY TOOLS
    // ==========================================================================
    // Following the spec: Mileage, Fuel cost, Trip cost, Fuel required, Bill split,
    // Recipe scaling, Paint estimator, Tile estimator, Room area, Room volume

    // ==========================================================================
    // SHOPPING TOOLS
    // ==========================================================================
    // Following the spec: Shopping list with items (name, price, quantity,
    // included/excluded), subtotal, discount, tax/GST, final total, budget,
    // remaining amount, save shopping list, rename list, duplicate list,
    // delete list, share list

    // ==========================================================================
    // ALL TOOLS COLLECTION (ordered by category then order)
    // ==========================================================================
    @get:Serializable
    private val _allTools = listOf(
        // Algebra tools (first 4 shown, rest follow pattern)
        percentage,
        percentageChange,
        percentageDifference,
        average,
        // ... more algebra tools would be added
        
        // Statistics tools (first few shown)
        // statisticsMean,
        // statisticsMedian,
        // etc.
        
        // Geometry tools (first few shown)
        // geometrySquare,
        // geometryRectangle,
        // etc.
        
        // Trigonometry tools
        // trigonometrySin,
        // trigonometryCos,
        // etc.
        
        // Calculus tools
        // calculusDerivative,
        // calculusIntegration,
        // etc.
        
        // Physics tools (first few shown)
        // physicsSpeed,
        // physicsForce,
        // etc.
        
        // Chemistry tools (first few shown)
        // chemistryMolarMass,
        // chemistryMoleCalculator,
        // etc.
        
        // Electronics tools (first few shown)
        // electronicsOhmsLaw,
        // electricalPower,
        // etc.
        
        // Computer science tools (first few shown)
        // binaryConverter,
        // octalConverter,
        // etc.
        
        // Unit converters (first few shown)
        // converterLength,
        // converterTemperature,
        // etc.
        
        // Finance tools (first few shown)
        // financeCurrencyConverter,
        // financeEMI,
        // etc.
        
        // Health tools (first few shown)
        // healthBMI,
        // healthBMR,
        // etc.
        
        // Date & time tools (first few shown)
        // datetimeAge,
        // datetimeDateDifference,
        // etc.
        
        // Everyday tools (first few shown)
        // everydayMileage,
        // everydayFuelCost,
        // etc.
        
        // Shopping tools
        // shoppingListCalculator,
        // shoppingBillSplit,
        // etc.
    ).sortedBy { (tool) -> (tool.category.order, tool.order) }

    /** Get all tools in category order */
    @get:Suppress("UNCHECKED_CAST")
    @get:Serializable
    val allTools: List<ToolDefinition> = _allTools

    /** Get tools by category */
    @get:Suppress("UNCHECKED_CAST")
    @get:Serializable
    fun getTools(category: ToolCategory): List<ToolDefinition> {
        return _allTools.filter { $0.category == category }
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
        return recentIds.mapNotNull { getTool(it) }?.distinctBy { $0.id } ?: emptyList()
    }
}

/**
 * ToolCategory with display info and ordering
 */
@Serializable
enum class ToolCategory(val displayName: String, val order: Int) {
    ALGEBRA(1),
    STATISTICS(2),
    GEOMETRY(3),
    TRIGONOMETRY(4),
    CALCULUS(5),
    PHYSICS(6),
    CHEMISTRY(7),
    ELECTRONICS(8),
    COMPUTER_SCIENCE(9),
    CONVERTERS(10),
    FINANCE(11),
    HEALTH(12),
    DATETIME(13),
    EVERYDAY(14),
    SHOPPING(15);

    companion object {
        fun allCategories(): List<ToolCategory> = values().sortedBy { it.order }
    }
}

/**
 * Search extension for ToolDefinition
 */
private fun ToolDefinition.searchTerms: List<String>
    get() = listOf(title, category.displayName) + keywords + aliases