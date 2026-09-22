package com.aio.calculator.core.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.ui.unit.dp

/** Small, dependency-light accessibility helpers shared by calculator screens. */
object AccessibilityUtil {
    fun calculatorDisplayContentDescription(text: String, isResult: Boolean): String = when {
        text.isBlank() -> "Calculator, empty display"
        isResult -> "Calculator result, $text"
        else -> "Calculator, $text"
    }

    fun calculatorButtonContentDescription(text: String, role: String): String = "Calculator $role button, $text"
    fun calculatorFunctionContentDescription(text: String): String = "Calculator function, $text"

    fun checkColorContrast(foreground: androidx.compose.ui.graphics.Color, background: androidx.compose.ui.graphics.Color): Boolean {
        fun channel(value: Float): Double {
            val normalized = value.toDouble()
            return if (normalized <= 0.03928) normalized / 12.92 else Math.pow((normalized + 0.055) / 1.055, 2.4)
        }
        fun luminance(color: androidx.compose.ui.graphics.Color): Double =
            0.2126 * channel(color.red) + 0.7152 * channel(color.green) + 0.0722 * channel(color.blue)
        val ratio = (maxOf(luminance(foreground), luminance(background)) + 0.05) /
            (minOf(luminance(foreground), luminance(background)) + 0.05)
        return ratio >= 4.5
    }

    fun accessibleTouchTarget(modifier: Modifier = Modifier): Modifier = modifier.sizeIn(minWidth = 48.dp, minHeight = 48.dp)

    @Composable
    fun withContentDescription(modifier: Modifier, description: String): Modifier = modifier.semantics { contentDescription = description }
}

fun String?.accessibleCalculatorDisplay(): String = if (isNullOrBlank()) "Calculator display, empty" else "Calculator display, $this"
fun androidx.compose.ui.graphics.Color.accessibleAgainst(background: androidx.compose.ui.graphics.Color): Boolean = AccessibilityUtil.checkColorContrast(this, background)
