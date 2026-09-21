package com.aio.calculator.core.math

import com.aio.calculator.core.common.AngleMode
import com.aio.calculator.core.common.Result
import org.junit.Assert.*
import org.junit.Test

class ExpressionEvaluatorTest {

    @Test
    fun testBasicArithmetic() {
        val evaluator = ExpressionEvaluator()

        assertEquals(3.0, evaluator.evaluate("1+2").getOrThrow(), 0.001)
        assertEquals(-1.0, evaluator.evaluate("1-2").getOrThrow(), 0.001)
        assertEquals(6.0, evaluator.evaluate("2*3").getOrThrow(), 0.001)
        assertEquals(2.0, evaluator.evaluate("6/3").getOrThrow(), 0.001)
    }

    @Test
    fun testParentheses() {
        val evaluator = ExpressionEvaluator()

        assertEquals(9.0, evaluator.evaluate("(1+2)*3").getOrThrow(), 0.001)
        assertEquals(7.0, evaluator.evaluate("1+(2*3)").getOrThrow(), 0.001)
    }

    @Test
    fun testExponents() {
        val evaluator = ExpressionEvaluator()

        assertEquals(8.0, evaluator.evaluate("2^3").getOrThrow(), 0.001)
        assertEquals(16.0, evaluator.evaluate("2**4").getOrThrow(), 0.001)
        assertEquals(1.41421356, evaluator.evaluate("sqrt(2)").getOrThrow(), 0.0001)
    }

    @Test
    fun testTrigonometricDegrees() {
        val evaluator = ExpressionEvaluator(AngleMode.DEGREES)

        assertEquals(0.0, evaluator.evaluate("sin(0)").getOrThrow(), 0.001)
        assertEquals(1.0, evaluator.evaluate("sin(90)").getOrThrow(), 0.001)
        assertEquals(0.5, evaluator.evaluate("sin(30)").getOrThrow(), 0.001)
        assertEquals(1.0, evaluator.evaluate("cos(0)").getOrThrow(), 0.001)
        assertEquals(0.0, evaluator.evaluate("cos(90)").getOrThrow(), 0.001)
    }

    @Test
    fun testTrigonometricRadians() {
        val evaluator = ExpressionEvaluator(AngleMode.RADIANS)

        assertEquals(0.0, evaluator.evaluate("sin(0)").getOrThrow(), 0.001)
        assertEquals(1.0, evaluator.evaluate("sin(pi/2)").getOrThrow(), 0.001)
        assertEquals(0.5, evaluator.evaluate("sin(pi/6)").getOrThrow(), 0.001)
    }

    @Test
    fun testLogarithmic() {
        val evaluator = ExpressionEvaluator()

        assertEquals(1.0, evaluator.evaluate("log(10)").getOrThrow(), 0.001)
        assertEquals(2.0, evaluator.evaluate("log(100)").getOrThrow(), 0.001)
        assertEquals(1.0, evaluator.evaluate("ln(e)").getOrThrow(), 0.001)
    }

    @Test
    fun testFactorial() {
        val evaluator = ExpressionEvaluator()

        assertEquals(1.0, evaluator.evaluate("fact(0)").getOrThrow(), 0.001)
        assertEquals(1.0, evaluator.evaluate("fact(1)").getOrThrow(), 0.001)
        assertEquals(2.0, evaluator.evaluate("fact(2)").getOrThrow(), 0.001)
        assertEquals(6.0, evaluator.evaluate("fact(3)").getOrThrow(), 0.001)
        assertEquals(120.0, evaluator.evaluate("fact(5)").getOrThrow(), 0.001)
        assertEquals(3628800.0, evaluator.evaluate("fact(10)").getOrThrow(), 0.001)
    }

    @Test
    fun testConstants() {
        val evaluator = ExpressionEvaluator()

        assertEquals(Math.PI, evaluator.evaluate("pi").getOrThrow(), 0.0001)
        assertEquals(Math.E, evaluator.evaluate("e").getOrThrow(), 0.0001)
        assertEquals((1 + Math.sqrt(5.0)) / 2, evaluator.evaluate("phi").getOrThrow(), 0.0001)
    }

    @Test
    fun testPercentage() {
        val evaluator = ExpressionEvaluator()

        // 25% of 400 = 100
        assertEquals(100.0, evaluator.evaluate("25/100*400").getOrThrow(), 0.001)
    }

    @Test
    fun testComplexExpression() {
        val evaluator = ExpressionEvaluator()

        assertEquals(68.0, evaluator.evaluate("12 * 5 + 8").getOrThrow(), 0.001)
        assertEquals(14.0, evaluator.evaluate("sqrt(144) + 2").getOrThrow(), 0.001)
    }

    @Test
    fun testInvalidExpression() {
        val evaluator = ExpressionEvaluator()

        val result = evaluator.evaluate("1++2")
        assertTrue(result.isFailure())
    }

    @Test
    fun testDivisionByZero() {
        val evaluator = ExpressionEvaluator()

        val result = evaluator.evaluate("1/0")
        assertTrue(result.isFailure())
    }
}