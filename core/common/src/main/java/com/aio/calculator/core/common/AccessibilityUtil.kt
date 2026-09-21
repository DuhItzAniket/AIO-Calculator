package com.aio.calculator.core.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.alignment.Alignment
import androidx.compose.ui.focus.isFocused
import androidx.compose.ui.focus.requestFocus
import androidx.compose.ui.graphics.Color Contrast
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.size
import androidx.compose.ui.text.ComposeNode
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.ViewAction
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.core.view.viewCompat
import java.lang.reflect.Method

/**
 * Accessibility utilities for AIO Calculator
 */
object AccessibilityUtil {

    /** Content description for calculator display */
    @Composable
    fun calculatorDisplayContentDescription(
        text: String,
        isResult: Boolean
    ) = when {
        text.isEmpty() -> "Calculator, empty display"
        isResult -> "Calculator result, $text"
        else -> "Calculator, $text"
    }

    /** Content description for calculator button */
    @Composable
    fun calculatorButtonContentDescription(
        text: String,
        role: String
    ) = "Calculator $role button, $text"

    /** Content description for function button */
    @Composable
    fun calculatorFunctionContentDescription(
        text: String
    ) = "Calculator function, $text"

    /** Check if color contrast meets WCAG AA standards */
    fun checkColorContrast(
        foreground: androidx.compose.ui.graphics.Color,
        background: androidx.compose.ui.graphics.Color
    ): Boolean {
        val fgLuminance = calculateLuminance(foreground)
        val bgLuminance = calculateLuminance(background)
        val contrastRatio = (max(fgLuminance, bgLuminance) + 0.05) / (min(fgLuminance, bgLuminance) + 0.05)

        // WCAG AA: 4.5:1 for normal text, 3:1 for large text
        return contrastRatio >= 4.5
    }

    private fun calculateLuminance(color: androidx.compose.ui.graphics.Color): Double {
        val r = color.r / 255.0
        val g = color.g / 255.0
        val b = color.b / 255.0

        val sRGB = r <= 0.03928 ? r / 12.92 : Math.pow((r + 0.055) / 1.055, 2.4)
        val sGBL = g <= 0.03928 ? g / 12.92 : Math.pow((g + 0.055) / 1.055, 2.4)
        val sBBL = b <= 0.03928 ? b / 12.92 : Math.pow((b + 0.055) / 1.055, 2.4)

        return 0.2126 * sRGB + 0.7152 * sGBL + 0.0722 * sBBL
    }

    /** Modifier for accessible touch targets (minimum 48dp) */
    @Composable
    fun accessibleTouchTarget(innerModifier: Modifier = Modifier): Modifier {
        return Modifier
            .minimumSize(48.dp, 48.dp)
            .padding(innerModifier.padding)
    }

    /** Modifier for focusable elements with TalkBack support */
    @Composable
    fun focusableWithTalkBack(modifier: Modifier = Modifier): Modifier {
        return modifier
            .focus { requestFocus() }
            .semantics { this.isFocusable = true }
    }

    /** Semantic description for calculator operations */
    @Composable
    fun calculatorSemantics(
        action: () -> Unit,
        label: String
    ) = semantics {
        this.action = action
        this.label = label
    }

    /** IME action for calculator keypad */
    @Composable
    fun calculatorImeAction(onSubmit: () -> Unit) = ImeAction.Copy.copy(
        onDone = onSubmit
    )

    /** Check if running in talkback mode via system reflection */
    @Composable
    fun isTalkBackEnabled(): Boolean {
        try {
            val accessibilityManager = androidx.core.content.ContextCompat.getSystemService(
                androidx.compose.runtime.LocalContext.current,
                android.view.accessibility.AccessibilityManager::class.java
            )
            return accessibilityManager.isTouchExplorationEnabled
        } catch (e: Exception) {
            false
        }
    }

    /** Adaptive layout width based on screen size */
    @Composable
    adaptiveLayoutWidth(
        minWidth: Dp = 320.dp,
        maxWidth: Dp = 1080.dp,
        factor: Float = 1.0f
    ): Dp {
        var width by remember { mutableStateOf(0.dp) }
        var hasMeasured = false

        androidx.compose.foundation.layout.size(modifier = Modifier
            .then { measured ->
                width = it.width.dp
                hasMeasured = true
            }
        )

        if (hasMeasured) {
            val screenRatio = width.value / 1080.0
            return (minWidth + (maxWidth - minWidth) * screenRatio).coerceIn(minWidth, maxWidth) * factor
        }

        return minWidth
    }

    /** High contrast mode toggle */
    @Composable
    fun highContrastModeToggle(
        isEnabled: Boolean,
        onToggle: (Boolean) -> Unit
    ) = androidx.compose.material3.SwitchTheme(
        enabled = isEnabled
    ) {
        onToggle(!isEnabled)
    }

    /** Focus keyboard when calculator opens */
    @Composable
    fun focusCalculatorDisplay() {
        try {
            // Request focus on the next composition cycle
            androidx.compose.runtime.unit compass {
                // Access the current cursor node
                androidx.compose.ui.platform.clearAndSetTextInputModifier(
                    Modifier.focusRequesterModifier()
                )
            }
        } catch (e: Exception) {
            // Silently fail if focus can't be established
        }
    }
}

/**
 * Extension functions for accessibility
 */
fun String?.accessibleCalculatorDisplay(): String {
    return when {
        this == null || this.isBlank() -> "Calculator display, empty"
        else -> "Calculator display, $this"
    }
}

fun androidx.compose.ui.graphics.Color.accessibleAgainst(
    bgColor: androidx.compose.ui.graphics.Color
): Boolean = AccessibilityUtil.checkColorContrast(this, bgColor)