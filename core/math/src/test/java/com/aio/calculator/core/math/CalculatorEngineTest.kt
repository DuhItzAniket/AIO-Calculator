package com.aio.calculator.core.math

import com.aio.calculator.core.common.AngleMode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CalculatorEngineTest {
    @Test
    fun memoryOperationsAreDeterministic() {
        val engine = CalculatorEngine()

        engine.setMemory(10.0)
        engine.addToMemory(2.5)
        engine.subtractFromMemory(1.5)

        assertEquals(11.0, engine.getMemory(), 0.0001)
        engine.clearMemory()
        assertEquals(0.0, engine.getMemory(), 0.0001)
    }

    @Test
    fun lastAnswerUpdatesOnlyAfterSuccessfulEvaluation() {
        val engine = CalculatorEngine()

        engine.evaluate("6 * 7")
        assertEquals(42.0, engine.getLastAnswer(), 0.0001)

        engine.evaluate("1 / 0")
        assertEquals(42.0, engine.getLastAnswer(), 0.0001)
    }

    @Test
    fun invalidAndNonFiniteExpressionsReturnFailure() {
        val evaluator = ExpressionEvaluator()

        assertTrue(evaluator.evaluate("").isFailure())
        assertTrue(evaluator.evaluate("1++2").isFailure())
        assertTrue(evaluator.evaluate("1/0").isFailure())
        assertTrue(evaluator.evaluate("sqrt(-1)").isFailure())
    }

    @Test
    fun scientificFunctionsRespectAngleMode() {
        val degrees = ExpressionEvaluator(AngleMode.DEGREES)
        val radians = ExpressionEvaluator(AngleMode.RADIANS)

        assertEquals(1.0, degrees.evaluate("sin(90)").getOrThrow(), 0.0001)
        assertEquals(1.0, radians.evaluate("sin(pi/2)").getOrThrow(), 0.0001)
        assertEquals(90.0, degrees.evaluate("asin(1)").getOrThrow(), 0.0001)
    }

    @Test
    fun alternateOperatorsAndConstantsAreNormalized() {
        val evaluator = ExpressionEvaluator()

        assertEquals(8.0, evaluator.evaluate("2**3").getOrThrow(), 0.0001)
        assertEquals(100.0, evaluator.evaluate("25%400").getOrThrow(), 0.0001)
        assertEquals(Math.PI, evaluator.evaluate("π").getOrThrow(), 0.0001)
    }
}
