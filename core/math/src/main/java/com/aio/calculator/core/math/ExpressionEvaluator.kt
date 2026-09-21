package com.aio.calculator.core.math

import com.aio.calculator.core.common.CalculationError
import com.aio.calculator.core.common.InvalidInputError
import com.aio.calculator.core.common.Result
import com.aio.calculator.core.common.Result.failure
import com.aio.calculator.core.common.Result.success
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.function.Function
import net.objecthunter.exp4j.operator.Operator

/**
 * Mathematical expression evaluator using exp4j library
 * Safe, deterministic, no arbitrary code execution
 */
class ExpressionEvaluator(
    private val angleMode: AngleMode = AngleMode.DEGREES
) {
    private val functions = mutableMapOf<String, Function>()
    private val operators = mutableMapOf<String, Operator>()

    init {
        registerDefaultFunctions()
    }

    private fun registerDefaultFunctions() {
        // Trigonometric functions
        functions["sin"] = Function("sin", 1) { args -> sin(args[0]) }
        functions["cos"] = Function("cos", 1) { args -> cos(args[0]) }
        functions["tan"] = Function("tan", 1) { args -> tan(args[0]) }
        functions["asin"] = Function("asin", 1) { args -> asin(args[0]) }
        functions["acos"] = Function("acos", 1) { args -> acos(args[0]) }
        functions["atan"] = Function("atan", 1) { args -> atan(args[0]) }

        // Hyperbolic functions
        functions["sinh"] = Function("sinh", 1) { args -> Math.sinh(args[0]) }
        functions["cosh"] = Function("cosh", 1) { args -> Math.cosh(args[0]) }
        functions["tanh"] = Function("tanh", 1) { args -> Math.tanh(args[0]) }

        // Logarithmic functions
        functions["log"] = Function("log", 1) { args -> Math.log10(args[0]) }
        functions["ln"] = Function("ln", 1) { args -> Math.log(args[0]) }
        functions["log2"] = Function("log2", 1) { args -> Math.log(args[0]) / Math.log(2.0) }

        // Other functions
        functions["sqrt"] = Function("sqrt", 1) { args -> Math.sqrt(args[0]) }
        functions["cbrt"] = Function("cbrt", 1) { args -> Math.cbrt(args[0]) }
        functions["abs"] = Function("abs", 1) { args -> Math.abs(args[0]) }
        functions["floor"] = Function("floor", 1) { args -> Math.floor(args[0]) }
        functions["ceil"] = Function("ceil", 1) { args -> Math.ceil(args[0]) }
        functions["round"] = Function("round", 1) { args -> Math.round(args[0]) }
        functions["fact"] = Function("fact", 1) { args -> factorial(args[0].toInt()) }
        functions["exp"] = Function("exp", 1) { args -> Math.exp(args[0]) }
        functions["pow"] = Function("pow", 2) { args -> Math.pow(args[0], args[1]) }

        // Constants
        functions["pi"] = Function("pi", 0) { Math.PI }
        functions["e"] = Function("e", 0) { Math.E }
        functions["phi"] = Function("phi", 0) { (1 + Math.sqrt(5.0)) / 2 }
    }

    private fun sin(x: Double): Double {
        return when (angleMode) {
            AngleMode.DEGREES -> Math.sin(Math.toRadians(x))
            AngleMode.RADIANS -> Math.sin(x)
        }
    }

    private fun cos(x: Double): Double {
        return when (angleMode) {
            AngleMode.DEGREES -> Math.cos(Math.toRadians(x))
            AngleMode.RADIANS -> Math.cos(x)
        }
    }

    private fun tan(x: Double): Double {
        return when (angleMode) {
            AngleMode.DEGREES -> Math.tan(Math.toRadians(x))
            AngleMode.RADIANS -> Math.tan(x)
        }
    }

    private fun asin(x: Double): Double {
        val result = Math.asin(x)
        return when (angleMode) {
            AngleMode.DEGREES -> Math.toDegrees(result)
            AngleMode.RADIANS -> result
        }
    }

    private fun acos(x: Double): Double {
        val result = Math.acos(x)
        return when (angleMode) {
            AngleMode.DEGREES -> Math.toDegrees(result)
            AngleMode.RADIANS -> result
        }
    }

    private fun atan(x: Double): Double {
        val result = Math.atan(x)
        return when (angleMode) {
            AngleMode.DEGREES -> Math.toDegrees(result)
            AngleMode.RADIANS -> result
        }
    }

    private fun factorial(n: Int): Double {
        if (n < 0) throw IllegalArgumentException("Factorial of negative number")
        if (n > 170) throw IllegalArgumentException("Factorial too large")
        var result = 1.0
        for (i in 2..n) {
            result *= i
        }
        return result
    }

    fun evaluate(expression: String): Result<Double> {
        val sanitized = sanitizeExpression(expression)
        return try {
            val expr = ExpressionBuilder(sanitized)
                .functions(*functions.values.toTypedArray())
                .build()
            val result = expr.evaluate()

            if (result.isNaN()) {
                failure(CalculationError("Result is not a number"))
            } else if (result.isInfinite()) {
                failure(CalculationError("Result is infinite"))
            } else {
                success(result)
            }
        } catch (e: Exception) {
            failure(CalculationError("Invalid expression: ${e.message}"))
        }
    }

    private fun sanitizeExpression(expression: String): String {
        return expression
            .trim()
            .replace("×", "*")
            .replace("÷", "/")
            .replace("^", "**")
            .replace("%", "/100*")
            .replace("π", "pi")
            .replace("φ", "phi")
    }

    fun setAngleMode(mode: AngleMode) {
        // Angle mode is set at construction time for thread safety
    }
}

/**
 * High-level calculator engine with memory and history
 */
class CalculatorEngine(
    private val angleMode: AngleMode = AngleMode.DEGREES
) {
    private val evaluator = ExpressionEvaluator(angleMode)
    private var memory: Double = 0.0
    private var lastAnswer: Double = 0.0

    fun evaluate(expression: String): Result<Double> {
        val result = evaluator.evaluate(expression)
        result.onSuccess { lastAnswer = it }
        return result
    }

    fun getMemory(): Double = memory

    fun setMemory(value: Double) {
        memory = value
    }

    fun addToMemory(value: Double) {
        memory += value
    }

    fun subtractFromMemory(value: Double) {
        memory -= value
    }

    fun clearMemory() {
        memory = 0.0
    }

    fun getLastAnswer(): Double = lastAnswer

    fun clearLastAnswer() {
        lastAnswer = 0.0
    }
}

/**
 * Scientific constants
 */
object ScientificConstants {
    const val PI = Math.PI
    const val E = Math.E
    const val PHI = (1 + Math.sqrt(5.0)) / 2
    const val SPEED_OF_LIGHT = 299792458.0 // m/s
    const val GRAVITATIONAL_CONSTANT = 6.67430e-11 // m^3 kg^-1 s^-2
    const val PLANCK_CONSTANT = 6.62607015e-34 // J s
    const val BOLTZMANN_CONSTANT = 1.380649e-23 // J/K
    const val AVOGADRO_CONSTANT = 6.02214076e23 // mol^-1
    const val ELEMENTARY_CHARGE = 1.602176634e-19 // C
    const val GAS_CONSTANT = 8.314462618 // J/(mol·K)
    const val ELECTRON_MASS = 9.1093837015e-31 // kg
    const val PROTON_MASS = 1.67262192369e-27 // kg
    const val NEUTRON_MASS = 1.67492749804e-27 // kg
    const val ATOMIC_MASS_UNIT = 1.66053906660e-27 // kg
    const val FARADAY_CONSTANT = 96485.33212 // C/mol
    const val STEFAN_BOLTZMANN = 5.670374419e-8 // W/(m^2·K^4)
    const val VACUUM_PERMEABILITY = 1.25663706212e-6 // N/A^2
    const val VACUUM_PERMITTIVITY = 8.8541878128e-12 // F/m
}