package com.aio.calculator.core.math

import com.aio.calculator.core.common.AngleMode
import com.aio.calculator.core.common.CalculationError
import com.aio.calculator.core.common.Result
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.function.Function

/** Safe, deterministic expression evaluation through exp4j; no code execution is involved. */
class ExpressionEvaluator(private val angleMode: AngleMode = AngleMode.DEGREES) {
    private fun unary(name: String, operation: (Double) -> Double) = object : Function(name, 1) {
        override fun apply(vararg args: Double): Double = operation(args[0])
    }

    private fun binary(name: String, operation: (Double, Double) -> Double) = object : Function(name, 2) {
        override fun apply(vararg args: Double): Double = operation(args[0], args[1])
    }

    private fun constant(name: String, value: Double) = object : Function(name, 0) {
        override fun apply(vararg args: Double): Double = value
    }

    private fun toRadians(value: Double) = if (angleMode == AngleMode.DEGREES) Math.toRadians(value) else value
    private fun fromRadians(value: Double) = if (angleMode == AngleMode.DEGREES) Math.toDegrees(value) else value

    private val functions = arrayOf(
        unary("sin") { Math.sin(toRadians(it)) },
        unary("cos") { Math.cos(toRadians(it)) },
        unary("tan") { Math.tan(toRadians(it)) },
        unary("asin") { fromRadians(Math.asin(it)) },
        unary("acos") { fromRadians(Math.acos(it)) },
        unary("atan") { fromRadians(Math.atan(it)) },
        unary("sinh") { Math.sinh(it) },
        unary("cosh") { Math.cosh(it) },
        unary("tanh") { Math.tanh(it) },
        unary("log") { Math.log10(it) },
        unary("ln") { Math.log(it) },
        unary("log2") { Math.log(it) / Math.log(2.0) },
        unary("sqrt") { Math.sqrt(it) },
        unary("cbrt") { Math.cbrt(it) },
        unary("abs") { Math.abs(it) },
        unary("floor") { Math.floor(it) },
        unary("ceil") { Math.ceil(it) },
        unary("round") { Math.round(it).toDouble() },
        unary("fact") { factorial(it) },
        unary("exp") { Math.exp(it) },
        binary("pow") { a, b -> Math.pow(a, b) },
    )

    fun evaluate(expression: String): Result<Double> {
        return try {
        val sanitized = sanitizeExpression(expression)
        require(!sanitized.contains("++")) { "Consecutive plus operators are invalid" }
        if (sanitized.isBlank()) return Result.failure(CalculationError("Expression is empty"))
        val value = ExpressionBuilder(sanitized).functions(*functions).build().evaluate()
        when {
            value.isNaN() -> Result.failure(CalculationError("Result is not a number"))
            value.isInfinite() -> Result.failure(CalculationError("Result is infinite"))
            else -> Result.success(value)
        }
        } catch (error: Exception) {
            Result.failure(CalculationError("Invalid expression", error))
        }
    }

    private fun sanitizeExpression(expression: String): String = expression.trim()
        .replace("×", "*")
        .replace("÷", "/")
        .replace("**", "^")
        .replace("%", "/100*")
        .replace("π", Math.PI.toString())
        .replace(Regex("\\bphi\\b"), ((1.0 + Math.sqrt(5.0)) / 2.0).toString())
        .replace("φ", ((1.0 + Math.sqrt(5.0)) / 2.0).toString())
        .replace(Regex("\\bpi\\b"), Math.PI.toString())
        .replace(Regex("\\be\\b"), Math.E.toString())

    private fun factorial(value: Double): Double {
        val n = value.toInt()
        require(value == n.toDouble() && n >= 0 && n <= 170) { "Factorial input must be an integer from 0 to 170" }
        var result = 1.0
        for (i in 2..n) result *= i
        return result
    }
}

class CalculatorEngine(angleMode: AngleMode = AngleMode.DEGREES) {
    private val evaluator = ExpressionEvaluator(angleMode)
    private var memory = 0.0
    private var lastAnswer = 0.0
    fun evaluate(expression: String): Result<Double> = evaluator.evaluate(expression).also { it.onSuccess { lastAnswer = it } }
    fun getMemory(): Double = memory
    fun setMemory(value: Double) { memory = value }
    fun addToMemory(value: Double) { memory += value }
    fun subtractFromMemory(value: Double) { memory -= value }
    fun clearMemory() { memory = 0.0 }
    fun getLastAnswer(): Double = lastAnswer
    fun clearLastAnswer() { lastAnswer = 0.0 }
}

object ScientificConstants {
    val PI = Math.PI
    val E = Math.E
    val PHI = (1.0 + Math.sqrt(5.0)) / 2.0
    const val SPEED_OF_LIGHT = 299792458.0
    const val GRAVITATIONAL_CONSTANT = 6.67430e-11
    const val PLANCK_CONSTANT = 6.62607015e-34
    const val BOLTZMANN_CONSTANT = 1.380649e-23
    const val AVOGADRO_CONSTANT = 6.02214076e23
    const val ELEMENTARY_CHARGE = 1.602176634e-19
    const val GAS_CONSTANT = 8.314462618
}
