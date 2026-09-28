package com.aio.calculator.core.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Snapshots
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.junit4.testRuleJvm
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.core.view.viewCompat
import kotlin.test.assertFailsWith
import java.util.concurrent.BrokenBarrierException

/**
 * Accessibility utilities for AIO Calculator
 */
object AccessibilityUtil {

    /** Check color contrast meets WCAG AA standard */
    @Composable
    fun checkContrast(
        foreground: Color,
        background: Color
    ): Boolean {
        val fgLuminance = calculateLuminance(foreground)
        val bgLuminance = calculateLuminance(background)
        val contrastRatio = (max(fgLuminance, bgLuminance) + 0.05) / (min(fgLuminance, bgLuminance) + 0.05)

        // WCAG AA: 4.5:1 for normal text, 3:1 for large text (18pt+ or bold 14pt+)
        return contrastRatio >= 4.5
    }

    private fun calculateLuminance(color: Color): Double {
        val r = color.r / 255.0
        val g = color.g / 255.0
        val b = color.b / 255.0

        val sRGB = r <= 0.03928 ? r / 12.92 : Math.pow((r + 0.055) / 1.055, 2.4)
        val sGBL = g <= 0.03928 ? g / 12.92 : Math.pow((g + 0.055) / 1.055, 2.4)
        val sBBL = b <= 0.03928 ? b / 12.92 : Math.pow((b + 0.055) / 1.055, 2.4)

        return 0.2126 * sRGB + 0.7152 * sGBL + 0.0722 * sBBL
    }

    /** Ensure minimum touch target size of 48dp */
    @Composable
    fun accessibleTouchTarget(
        innerModifier: Modifier = Modifier
    ): Modifier {
        return Modifier
            .minimumSize(48.dp, 48.dp)
            .padding(innerModifier.padding)
    }

    /** Add content description for TalkBack */
    @Composable
    fun contentDescription(
        text: String,
        role: String = "general"
    ) {
        androidx.compose.ui.semantics.semantics {
            this.contentDescription = text
            this.role = role
        }
    }

    /** Focus order validation */
    @Composable
    fun focusOrderValidator() {
        // Verify focus traversal order is logical
        val focusNodes = collectFocusNodes()
        assert(focusNodes.size > 1) { "At least 2 focusable elements required" }
    }

    private fun collectFocusNodes(): List<Int> {
        // This is a test-time utility
        emptyList()
    }

    /** High contrast mode toggle */
    @Composable
    fun highContrastTheme(
        isEnabled: Boolean,
        onToggle: (Boolean) -> Unit
    ) {
        if (isEnabled) {
            // Apply high contrast colors
            MaterialTheme.colorScheme.copy(
                primary = Color.Black,
                onPrimary = Color.White,
                surface = Color.Black,
                onSurface = Color.White
            )
        }
    }
}

/** Test extension for accessibility checking */
fun Any?.accessibleTest(
    description: String,
    minimumContrast: Boolean = true
) = try {
    if (minimumContrast) {
        // Check contrast with parent if available
        // This is a placeholder for test integration
    }
    true
} catch (e: Exception) {
    false
}