package com.aio.calculator.core.formula

import kotlinx.serialization.Serializable

/**
 * Represents a mathematical/scientific/engineering formula
 */
@Serializable
data class FormulaDefinition(
    val id: String,
    val name: String,
    val category: FormulaCategory,
    val equation: String,
    val variables: String, // JSON or formatted string: "V = I × R"
    val units: String,
    val description: String,
    val associatedToolId: String? = null,
    val order: Int = 0
)

/**
 * Formula categories
 */
@Serializable
enum class FormulaCategory(
    val displayName: String,
    val order: Int
) {
    MATHEMATICS(1, "Mathematics"),
    PHYSICS(2, "Physics"),
    CHEMISTRY(3, "Chemistry"),
    ELECTRONICS(4, "Electronics"),
    FINANCE(5, "Finance"),
    GEOMETRY(6, "Geometry");

    companion object {
        val allCategories: List<FormulaCategory> = values().sortedBy { it.order }
    }
}

/**
 * Formula Library - SINGLE SOURCE OF TRUTH for all formulas
 * Categories: Mathematics, Physics, Chemistry, Electronics, Finance, Geometry
 * Each formula contains: name, equation, variables, units, short explanation, calculator/tool association
 */
object FormulaLibrary {

    // ==========================================================================
    // MATHEMATICS FORMULAS
    // ==========================================================================
    val pythagoreanTheorem = FormulaDefinition(
        id = "pythagorean_theorem",
        name = "Pythagorean Theorem",
        category = FormulaCategory.MATHEMATICS,
        equation = "a² + b² = c²",
        variables = "a, b = legs of right triangle; c = hypotenuse",
        units = "all lengths in same unit (cm, m, in, ft)",
        description = "Relates the three sides of a right-angled triangle",
        associatedToolId = "geometry_triangle",
        order = 1
    )

    val quadraticFormula = FormulaDefinition(
        id = "quadratic_formula",
        name = "Quadratic Formula",
        category = FormulaCategory.MATHEMATICS,
        equation = "x = (-b ± √(b² - 4ac)) / 2a",
        variables = "a, b, c = coefficients of ax² + bx + c = 0; x = solutions",
        units = "x is unitless or same as input variable",
        description = "Finds roots of quadratic equations",
        associatedToolId = "algebra_quadratic",
        order = 2
    )

    val area_circle = FormulaDefinition(
        id = "area_circle",
        name = "Circle Area",
        category = FormulaCategory.MATHEMATICS,
        equation = "A = πr²",
        variables = "A = area; r = radius",
        units = "area in square units; radius in length units",
        description = "Calculate area of a circle",
        associatedToolId = "geometry_circle",
        order = 3
    )

    val circumference_circle = FormulaDefinition(
        id = "circumference_circle",
        name = "Circle Circumference",
        category = FormulaCategory.MATHEMATICS,
        equation = "C = 2πr",
        variables = "C = circumference; r = radius",
        units = "circumference in length units; radius in length units",
        description = "Calculate circumference of a circle",
        associatedToolId = "geometry_circle",
        order = 4
    )

    val volume_sphere = FormulaDefinition(
        id = "volume_sphere",
        name = "Sphere Volume",
        category = FormulaCategory.MATHEMATICS,
        equation = "V = (4/3)πr³",
        variables = "V = volume; r = radius",
        units = "volume in cubic units; radius in length units",
        description = "Calculate volume of a sphere",
        associatedToolId = "geometry_sphere",
        order = 5
    )

    // ==========================================================================
    // PHYSICS FORMULAS
    // ==========================================================================
    val newton_second = FormulaDefinition(
        id = "newton_second",
        name = "Newton's Second Law",
        category = FormulaCategory.PHYSICS,
        equation = "F = m × a",
        variables = "F = force (N); m = mass (kg); a = acceleration (m/s²)",
        units = "force in newtons; mass in kilograms; acceleration in m/s²",
        description = "Relates force, mass, and acceleration",
        associatedToolId = "physics_force",
        order = 1
    )

    val ideal_gas_law = FormulaDefinition(
        id = "ideal_gas_law",
        name = "Ideal Gas Law",
        category = FormulaCategory.PHYSICS,
        equation = "PV = nRT",
        variables = "P = pressure; V = volume; n = amount of substance; R = gas constant; T = temperature",
        units = "P in Pa; V in m³; n in mol; R = 8.314 J/(mol·K); T in Kelvin",
        description = "State equation for ideal gases",
        associatedToolId = "physics_ideal_gas",
        order = 2
    )

    val E_mc2 = FormulaDefinition(
        id = "e_mc2",
        name = "Einstein's Mass-Energy Equivalence",
        category = FormulaCategory.PHYSICS,
        equation = "E = mc²",
        variables = "E = energy; m = mass; c = speed of light in vacuum",
        units = "E in joules; m in kilograms; c = 299,792,458 m/s",
        description = "Relates mass and energy",
        associatedToolId = null,
        order = 3
    )

    // ==========================================================================
    // CHEMISTRY FORMULAS
    // ==========================================================================
    val molarity = FormulaDefinition(
        id = "molarity",
        name = "Molarity",
        category = FormulaCategory.CHEMISTRY,
        equation = "M = moles of solute / liters of solution",
        variables = "M = molarity; moles = amount of solute; liters = volume of solution",
        units = "M in mol/L; volume in liters",
        description = "Concentration of a solution",
        associatedToolId = "chemistry_molar_mass",
        order = 1
    )

    val percent_yield = FormulaDefinition(
        id = "percent_yield",
        name = "Percentage Yield",
        category = FormulaCategory.CHEMISTRY,
        equation = "% yield = (actual yield / theoretical yield) × 100",
        variables = "actual yield = mass/amount obtained; theoretical yield = mass/amount expected",
        units = "percentage (%)",
        description = "Efficiency of a chemical reaction",
        associatedToolId = "chemistry_theoretical_yield",
        order = 2
    )

    // ==========================================================================
    // ELECTRONICS FORMULAS
    // ==========================================================================
    val ohms_law = FormulaDefinition(
        id = "ohms_law",
        name = "Ohm's Law",
        category = FormulaCategory.ELECTRONICS,
        equation = "V = I × R",
        variables = "V = voltage (V); I = current (A); R = resistance (Ω)",
        units = "voltage in volts; current in amperes; resistance in ohms",
        description = "Relates voltage, current, and resistance",
        associatedToolId = "electronics_ohms_law",
        order = 1
    )

    val power_formula = FormulaDefinition(
        id = "power_formula",
        name = "Electrical Power",
        category = FormulaCategory.ELECTRONICS,
        equation = "P = V × I",
        variables = "P = power (W); V = voltage (V); I = current (A)",
        units = "power in watts; voltage in volts; current in amperes",
        description = "Calculate electrical power",
        associatedToolId = "electronics_ohms_law",
        order = 2
    )

    // ==========================================================================
    // FINANCE FORMULAS
    // ==========================================================================
    val compound_interest = FormulaDefinition(
        id = "compound_interest",
        name = "Compound Interest",
        category = FormulaCategory.FINANCE,
        equation = "A = P(1 + r/n)^(nt)",
        variables = "A = final amount; P = principal; r = annual interest rate; n = times interest applied per time; t = time in years",
        units = "A and P in same currency units; r as decimal; t in years",
        description = "Calculate compound interest growth",
        associatedToolId = "finance_emi",
        order = 1
    )

    val roi_formula = FormulaDefinition(
        id = "roi_formula",
        name = "Return on Investment",
        category = FormulaCategory.FINANCE,
        equation = "ROI = (Net Profit / Cost of Investment) × 100",
        variables = "Net Profit = total revenue - total costs; Cost of Investment = initial amount invested",
        units = "percentage (%)",
        description = "Measure investment efficiency",
        associatedToolId = null,
        order = 2
    )

    // ==========================================================================
    // GEOMETRY FORMULAS
    // ==========================================================================
    val triangle_area = FormulaDefinition(
        id = "triangle_area",
        name = "Triangle Area",
        category = FormulaCategory.GEOMETRY,
        equation = "A = (base × height) / 2",
        variables = "A = area; base = length of base; height = perpendicular height",
        units = "area in square units; base and height in length units",
        description = "Calculate area of a triangle",
        associatedToolId = "geometry_triangle",
        order = 1
    )

    val rectangle_area = FormulaDefinition(
        id = "rectangle_area",
        name = "Rectangle Area",
        category = FormulaCategory.GEOMETRY,
        equation = "A = length × width",
        variables = "A = area; length = rectangle length; width = rectangle width",
        units = "area in square units; length and width in length units",
        description = "Calculate area of a rectangle",
        associatedToolId = "geometry_rectangle",
        order = 2
    )

    val cylinder_volume = FormulaDefinition(
        id = "cylinder_volume",
        name = "Cylinder Volume",
        category = FormulaCategory.GEOMETRY,
        equation = "V = πr²h",
        variables = "V = volume; r = radius of base; h = height",
        units = "volume in cubic units; radius and height in length units",
        description = "Calculate volume of a cylinder",
        associatedToolId = null,
        order = 3
    )

    // ==========================================================================
    // ALL FORMULAS COLLECTION
    // ==========================================================================
    @get:Suppress("UNCHECKED_CAST")
    @get:Serializable
    private val _allFormulas = listOf(
        // Mathematics
        pythagoreanTheorem,
        quadraticFormula,
        area_circle,
        circumference_circle,
        volume_sphere,
        // Physics
        newton_second,
        ideal_gas_law,
        E_mc2,
        // Chemistry
        molarity,
        percent_yield,
        // Electronics
        ohms_law,
        power_formula,
        // Finance
        compound_interest,
        roi_formula,
        // Geometry
        triangle_area,
        rectangle_area,
        cylinder_volume
    ).sortedBy { (formula) -> (formula.category.order, formula.order) }

    /** Get all formulas */
    @get:Suppress("UNCHECKED_CAST")
    @get:Serializable
    val allFormulas: List<FormulaDefinition> = _allFormulas

    /** Get formulas by category */
    @get:Suppress("UNCHECKED_CAST")
    @get:Serializable
    fun getFormulas(category: FormulaCategory): List<FormulaDefinition> {
        return _allFormulas.filter { it.category == category }
    }

    /** Get formula by ID */
    @get:Suppress("UNCHECKED_CAST")
    fun getFormula(id: String): FormulaDefinition? {
        return _allFormulas.firstOrNull { it.id == id }
    }

    /** Search formulas by name or description */
    fun searchFormulas(query: String): List<FormulaDefinition> {
        if (query.isBlank()) return _allFormulas
        val lowerQuery = query.lowercase()
        return _allFormulas.filter { formula ->
            formula.name.lowercase().contains(lowerQuery) ||
            formula.description.lowercase().contains(lowerQuery) ||
            formula.equation.lowercase().contains(lowerQuery)
        }
    }
}