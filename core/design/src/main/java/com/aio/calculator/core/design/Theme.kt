package com.aio.calculator.core.design

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import com.aio.calculator.core.common.ThemeMode

/**
 * AIO Calculator Theme
 * Centralized theme configuration supporting multiple theme modes
 */
@Composable
fun AioTheme(
    themeMode: ThemeMode = ThemeMode.SYSTEM_DEFAULT,
    dynamicColorAvailable: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeMode) {
        ThemeMode.SYSTEM_DEFAULT -> MaterialTheme.colorScheme
        ThemeMode.LIGHT -> LightColorScheme
        ThemeMode.DARK -> DarkColorScheme
        ThemeMode.OLED_BLACK -> OledBlackColorScheme
        ThemeMode.DYNAMIC_COLOR -> if (dynamicColorAvailable) MaterialTheme.colorScheme else DarkColorScheme
    }

    val shapes = AioShapesScheme
    val typography = AioTypographyScheme

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = shapes,
        typography = typography,
        content = content
    )

    // Update system bars
    SideEffect {
        val context = LocalContext.current
        updateSystemBars(context, colorScheme)
    }
}

/**
 * Update system bars to match theme
 */
private fun updateSystemBars(context: Context, colorScheme: androidx.compose.material3.ColorScheme) {
    val window = (context as? androidx.activity.ComponentActivity)?.window
        ?: return

    val isLight = colorScheme.brightness == androidx.compose.material3.ColorScheme.Light

    WindowCompat.getInsetsController(window, window.decorView).apply {
        isAppearanceLightStatusBars = isLight
        isAppearanceLightNavigationBars = isLight
    }

    window.statusBarColor = colorScheme.surface.toArgb()
    window.navigationBarColor = colorScheme.surface.toArgb()
}

/**
 * Get the current theme mode from the color scheme
 */
@Composable
fun currentThemeMode(): ThemeMode {
    val colorScheme = MaterialTheme.colorScheme
    val isDark = colorScheme.brightness == androidx.compose.material3.ColorScheme.Dark

    // Check if it's OLED black
    val isOled = colorScheme.surface == Color.Black

    return if (isOled) {
        ThemeMode.OLED_BLACK
    } else if (isDark) {
        ThemeMode.DARK
    } else {
        ThemeMode.LIGHT
    }
}

/**
 * Calculator-specific colors
 */
@Composable
fun CalculatorColors(): CalculatorColorScheme {
    val colorScheme = MaterialTheme.colorScheme
    val isDark = colorScheme.brightness == androidx.compose.material3.ColorScheme.Dark
    val isOled = colorScheme.surface == Color.Black

    return if (isOled) {
        CalculatorColorScheme(
            buttonBackground = AioColors.CalculatorButtonBgOled,
            buttonText = AioColors.CalculatorButtonTextOled,
            operatorBackground = AioColors.CalculatorOperatorBgOled,
            operatorText = AioColors.CalculatorOperatorTextOled,
            functionBackground = AioColors.CalculatorFunctionBgOled,
            functionText = AioColors.CalculatorFunctionTextOled,
            displayBackground = AioColors.CalculatorDisplayBgOled,
            displayText = AioColors.CalculatorDisplayTextOled
        )
    } else if (isDark) {
        CalculatorColorScheme(
            buttonBackground = AioColors.CalculatorButtonBgDark,
            buttonText = AioColors.CalculatorButtonTextDark,
            operatorBackground = AioColors.CalculatorOperatorBgDark,
            operatorText = AioColors.CalculatorOperatorTextDark,
            functionBackground = AioColors.CalculatorFunctionBgDark,
            functionText = AioColors.CalculatorFunctionTextDark,
            displayBackground = AioColors.CalculatorDisplayBgDark,
            displayText = AioColors.CalculatorDisplayTextDark
        )
    } else {
        CalculatorColorScheme(
            buttonBackground = AioColors.CalculatorButtonBg,
            buttonText = AioColors.CalculatorButtonText,
            operatorBackground = AioColors.CalculatorOperatorBg,
            operatorText = AioColors.CalculatorOperatorText,
            functionBackground = AioColors.CalculatorFunctionBg,
            functionText = AioColors.CalculatorFunctionText,
            displayBackground = AioColors.CalculatorDisplayBg,
            displayText = AioColors.CalculatorDisplayText
        )
    }
}

/**
 * Calculator color scheme data class
 */
data class CalculatorColorScheme(
    val buttonBackground: Color,
    val buttonText: Color,
    val operatorBackground: Color,
    val operatorText: Color,
    val functionBackground: Color,
    val functionText: Color,
    val displayBackground: Color,
    val displayText: Color
)

import androidx.compose.ui.platform.LocalContext