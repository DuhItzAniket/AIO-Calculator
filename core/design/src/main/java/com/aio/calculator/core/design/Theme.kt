package com.aio.calculator.core.design

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import com.aio.calculator.core.common.ThemeMode

@Composable
fun AioTheme(
    themeMode: ThemeMode = ThemeMode.SYSTEM_DEFAULT,
    dynamicColorAvailable: Boolean = false,
    content: @Composable () -> Unit
) {
    val systemDark = isSystemInDarkTheme()
    val colorScheme = when (themeMode) {
        ThemeMode.LIGHT -> LightColorScheme
        ThemeMode.DARK -> DarkColorScheme
        ThemeMode.OLED_BLACK -> OledBlackColorScheme
        ThemeMode.SYSTEM_DEFAULT -> if (systemDark) DarkColorScheme else LightColorScheme
        ThemeMode.DYNAMIC_COLOR -> if (dynamicColorAvailable) {
            if (systemDark) DarkColorScheme else LightColorScheme
        } else if (systemDark) DarkColorScheme else LightColorScheme
    }
    val context = LocalContext.current

    MaterialTheme(colorScheme = colorScheme, shapes = AioShapesScheme, typography = AioTypographyScheme) {
        SideEffect { updateSystemBars(context, colorScheme) }
        content()
    }
}

private fun updateSystemBars(context: Context, colorScheme: androidx.compose.material3.ColorScheme) {
    val window = (context as? Activity)?.window ?: return
    val isLight = colorScheme.surface.luminance() > 0.5f
    WindowCompat.getInsetsController(window, window.decorView).apply {
        isAppearanceLightStatusBars = isLight
        isAppearanceLightNavigationBars = isLight
    }
    window.statusBarColor = colorScheme.surface.toArgb()
    window.navigationBarColor = colorScheme.surface.toArgb()
}

@Composable
fun currentThemeMode(): ThemeMode {
    val surface = MaterialTheme.colorScheme.surface
    return when {
        surface == Color.Black -> ThemeMode.OLED_BLACK
        surface.luminance() < 0.5f -> ThemeMode.DARK
        else -> ThemeMode.LIGHT
    }
}

@Composable
fun CalculatorColors(): CalculatorColorScheme {
    val surface = MaterialTheme.colorScheme.surface
    val isOled = surface == Color.Black
    val isDark = surface.luminance() < 0.5f
    return when {
        isOled -> CalculatorColorScheme(AioColors.CalculatorButtonBgOled, AioColors.CalculatorButtonTextOled, AioColors.CalculatorOperatorBgOled, AioColors.CalculatorOperatorTextOled, AioColors.CalculatorFunctionBgOled, AioColors.CalculatorFunctionTextOled, AioColors.CalculatorDisplayBgOled, AioColors.CalculatorDisplayTextOled)
        isDark -> CalculatorColorScheme(AioColors.CalculatorButtonBgDark, AioColors.CalculatorButtonTextDark, AioColors.CalculatorOperatorBgDark, AioColors.CalculatorOperatorTextDark, AioColors.CalculatorFunctionBgDark, AioColors.CalculatorFunctionTextDark, AioColors.CalculatorDisplayBgDark, AioColors.CalculatorDisplayTextDark)
        else -> CalculatorColorScheme(AioColors.CalculatorButtonBg, AioColors.CalculatorButtonText, AioColors.CalculatorOperatorBg, AioColors.CalculatorOperatorText, AioColors.CalculatorFunctionBg, AioColors.CalculatorFunctionText, AioColors.CalculatorDisplayBg, AioColors.CalculatorDisplayText)
    }
}

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
