package com.aio.calculator.core.design

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

/**
 * AIO Calculator custom color palette
 * Defines semantic colors for the app with full theme support
 */
object AioColors {
    // ==========================================================================
    // PRIMARY BRAND COLORS
    // ==========================================================================
    // Main brand blue - used for primary actions, buttons, highlights
    val Primary = Color(0xFF006E7F)
    val PrimaryContainer = Color(0xFF97F0FF)
    val OnPrimary = Color(0xFFFFFFFF)
    val OnPrimaryContainer = Color(0xFF002127)

    // Secondary action color - used for secondary buttons, accents
    val Secondary = Color(0xFF4A6367)
    val SecondaryContainer = Color(0xFFCCE8EC)
    val OnSecondary = Color(0xFFFFFFFF)
    val OnSecondaryContainer = Color(0xFF061F22)

    // Tertiary/accent color - used for highlights, special functions
    val Tertiary = Color(0xFF7C5A00)
    val TertiaryContainer = Color(0xFFFFDD95)
    val OnTertiary = Color(0xFFFFFFFF)
    val OnTertiaryContainer = Color(0xFF271900)

    // ==========================================================================
    // ERROR COLORS
    // ==========================================================================
    val Error = Color(0xFFBA1A1A)
    val ErrorContainer = Color(0xFFFFDAD6)
    val OnError = Color(0xFFFFFFFF)
    val OnErrorContainer = Color(0xFF410002)

    // ==========================================================================
    // SURFACE COLORS - LIGHT THEME
    // ==========================================================================
    // Main surface - used for calculator display, card backgrounds
    val SurfaceLight = Color(0xFFFAFDFC)
    // Alternative surface - used for cards, containers
    val SurfaceLightAlt = Color(0xFFE8EFF5)
    // Surface variant - used for grids, lists background
    val SurfaceVariantLight = Color(0xFFDBE5E7)
    // On surface - main text color on light surfaces
    val OnSurfaceLight = Color(0xFF191D1D)
    // Surface outline - used for borders, dividers
    val OutlineLight = Color(0xFF6F797A)
    // Surface outline variant - for alternating rows
    val OutlineVariantLight = Color(0xFFBFC9CA)

    // ==========================================================================
    // SURFACE COLORS - DARK THEME
    // ==========================================================================
    val SurfaceDark = Color(0xFF111414)
    val OnSurfaceDark = Color(0xFFE0E4E4)
    val SurfaceVariantDark = Color(0xFF3F494A)
    val OnSurfaceVariantDark = Color(0xFFBFC9CA)
    val OutlineDark = Color(0xFF899394)
    val OutlineVariantDark = Color(0xFF3F494A)

    // ==========================================================================
    // OLED BLACK SURFACES - TRUE BLACK FOR OLED
    // ==========================================================================
    // Pure black for OLED panels - saves battery
    val SurfaceOled = Color.Black
    val OnSurfaceOled = Color.White
    val SurfaceContainerOled = Color(0xFF1A1A1A)
    val SurfaceContainerHighOled = Color(0xFF222222)
    val SurfaceContainerHighestOled = Color(0xFF2A2A2A)

    // ==========================================================================
    // INVERSE COLORS - FOR OVERLAYS, MODALS
    // ==========================================================================
    val InverseSurface = Color(0xFFE0E4E4)
    val InverseOnSurface = Color(0xFF191D1D)
    val InversePrimary = Color(0xFF4FD8EC)

    // ==========================================================================
    // CALCULATOR-SPECIFIC COLORS
    // ==========================================================================
    // Calculator button backgrounds
    val CalculatorButtonBgLight = Color(0xFFF0F4F4)
    val CalculatorButtonBgDark = Color(0xFF2A2F2F)
    val CalculatorButtonBgOled = Color(0xFF1A1A1A)

    // Calculator operator buttons (+, -, ×, ÷, =)
    val CalculatorOperatorBgLight = Color(0xFF006E7F)
    val CalculatorOperatorBgDark = Color(0xFF4FD8EC)
    val CalculatorOperatorBgOled = Color(0xFF006E7F)
    val CalculatorOperatorTextLight = Color(0xFFFFFFFF)
    val CalculatorOperatorTextDark = Color(0xFF002127)
    val CalculatorOperatorTextOled = Color(0xFFFFFFFF)

    // Calculator function buttons (sin, cos, √, etc.)
    val CalculatorFunctionBgLight = Color(0xFFDBE5E7)
    val CalculatorFunctionBgDark = Color(0xFF3F494A)
    val CalculatorFunctionBgOled = Color(0xFF222222)
    val CalculatorFunctionTextLight = Color(0xFF191D1D)
    val CalculatorFunctionTextDark = Color(0xFFE0E4E4)
    val CalculatorFunctionTextOled = Color(0xFFFFFFFF)

    // Calculator display backgrounds
    val CalculatorDisplayBgLight = Color(0xFFFAFDFC)
    val CalculatorDisplayBgDark = Color(0xFF111414)
    val CalculatorDisplayBgOled = Color.Black
    val CalculatorDisplayTextLight = Color(0xFF191D1D)
    val CalculatorDisplayTextDark = Color(0xFFE0E4E4)
    val CalculatorDisplayTextOled = Color.White

    // ==========================================================================
    // THEME SWITCHING HELPERS
    // ==========================================================================
    // Get button background for current theme mode
    fun getButtonBg(isDark: Boolean, isOled: Boolean): Color {
        return if (isOled) CalculatorButtonBgOled
               else if (isDark) CalculatorButtonBgDark
               else CalculatorButtonBgLight
    }

    // Get operator background for current theme mode
    fun getOperatorBg(isDark: Boolean, isOled: Boolean): Color {
        return if (isOled) CalculatorOperatorBgOled
               else if (isDark) CalculatorOperatorBgDark
               else CalculatorOperatorBgLight
    }

    // Get function background for current theme mode
    fun getFunctionBg(isDark: Boolean, isOled: Boolean): Color {
        return if (isOled) CalculatorFunctionBgOled
               else if (isDark) CalculatorFunctionBgDark
               else CalculatorFunctionBgLight
    }

    // Get display background for current theme mode
    fun getDisplayBg(isDark: Boolean, isOled: Boolean): Color {
        return if (isOled) CalculatorDisplayBgOled
               else if (isDark) CalculatorDisplayBgDark
               else CalculatorDisplayBgLight
    }

    // Get text color for current theme mode
    fun getTextColor(isDark: Boolean, isOled: Boolean): Color {
        return if (isOled) OnSurfaceOled
               else if (isDark) OnSurfaceDark
               else OnSurfaceLight
    }
}

/**
 * Light color scheme - enhanced with all surface variants
 */
val LightColorScheme: ColorScheme = lightColorScheme(
    primary = AioColors.Primary,
    primaryContainer = AioColors.PrimaryContainer,
    onPrimary = AioColors.OnPrimary,
    onPrimaryContainer = AioColors.OnPrimaryContainer,
    secondary = AioColors.Secondary,
    secondaryContainer = AioColors.SecondaryContainer,
    onSecondary = AioColors.OnSecondary,
    onSecondaryContainer = AioColors.OnSecondaryContainer,
    tertiary = AioColors.Tertiary,
    tertiaryContainer = AioColors.TertiaryContainer,
    onTertiary = AioColors.OnTertiary,
    onTertiaryContainer = AioColors.OnTertiaryContainer,
    error = AioColors.Error,
    errorContainer = AioColors.ErrorContainer,
    onError = AioColors.OnError,
    onErrorContainer = AioColors.OnErrorContainer,
    surface = AioColors.SurfaceLight,
    onSurface = AioColors.OnSurfaceLight,
    surfaceContainer = AioColors.SurfaceVariantLight,
    surfaceContainerLow = AioColors.SurfaceLightAlt,
    surfaceContainerHigh = AioColors.SurfaceVariantLight,
    surfaceContainerHighest = AioColors.OutlineVariantLight,
    onSurfaceVariant = AioColors.OnSurfaceVariantLight,
    outline = AioColors.OutlineLight,
    outlineVariant = AioColors.OutlineVariantLight,
    inverseSurface = AioColors.InverseSurface,
    inverseOnSurface = AioColors.InverseOnSurface,
    inversePrimary = AioColors.InversePrimary,
    scrim = AioColors.Scrim,
    surfaceTint = AioColors.Primary
)

/**
 * Dark color scheme - enhanced with all surface variants
 */
val DarkColorScheme: ColorScheme = darkColorScheme(
    primary = AioColors.PrimaryContainer,
    primaryContainer = AioColors.Primary,
    onPrimary = AioColors.OnPrimaryContainer,
    onPrimaryContainer = AioColors.OnPrimary,
    secondary = AioColors.SecondaryContainer,
    secondaryContainer = AioColors.Secondary,
    onSecondary = AioColors.OnSecondaryContainer,
    onSecondaryContainer = AioColors.OnSecondary,
    tertiary = AioColors.TertiaryContainer,
    tertiaryContainer = AioColors.Tertiary,
    onTertiary = AioColors.OnTertiaryContainer,
    onTertiaryContainer = AioColors.OnTertiary,
    error = AioColors.ErrorContainer,
    errorContainer = AioColors.Error,
    onError = AioColors.OnErrorContainer,
    onErrorContainer = AioColors.OnError,
    surface = AioColors.SurfaceDark,
    onSurface = AioColors.OnSurfaceDark,
    surfaceContainer = AioColors.SurfaceVariantDark,
    surfaceContainerLow = AioColors.SurfaceDark,
    surfaceContainerHigh = AioColors.SurfaceVariantDark,
    surfaceContainerHighest = AioColors.OutlineVariantDark,
    onSurfaceVariant = AioColors.OnSurfaceVariantDark,
    outline = AioColors.OutlineDark,
    outlineVariant = AioColors.OutlineVariantDark,
    inverseSurface = AioColors.InverseSurface,
    inverseOnSurface = AioColors.InverseOnSurface,
    inversePrimary = AioColors.InversePrimary,
    scrim = AioColors.Scrim,
    surfaceTint = AioColors.PrimaryContainer
)

/**
 * OLED Black color scheme - true black for maximum battery savings
 */
val OledBlackColorScheme: ColorScheme = darkColorScheme(
    primary = AioColors.PrimaryContainer,
    primaryContainer = AioColors.Primary,
    onPrimary = AioColors.OnPrimaryContainer,
    onPrimaryContainer = AioColors.OnPrimary,
    secondary = AioColors.SecondaryContainer,
    secondaryContainer = AioColors.Secondary,
    onSecondary = AioColors.OnSecondaryContainer,
    onSecondaryContainer = AioColors.OnSecondary,
    tertiary = AioColors.TertiaryContainer,
    tertiaryContainer = AioColors.Tertiary,
    onTertiary = AioColors.OnTertiaryContainer,
    onTertiaryContainer = AioColors.OnTertiary,
    error = AioColors.ErrorContainer,
    errorContainer = AioColors.Error,
    onError = AioColors.OnErrorContainer,
    onErrorContainer = AioColors.OnError,
    surface = AioColors.SurfaceOled,
    onSurface = AioColors.OnSurfaceOled,
    surfaceContainer = AioColors.SurfaceContainerOled,
    surfaceContainerLow = AioColors.SurfaceOled,
    surfaceContainerHigh = AioColors.SurfaceContainerHighOled,
    surfaceContainerHighest = AioColors.SurfaceContainerHighestOled,
    onSurfaceVariant = AioColors.OnSurfaceOled,
    outline = AioColors.OutlineDark,
    outlineVariant = AioColors.OutlineVariantDark,
    inverseSurface = AioColors.InverseSurface,
    inverseOnSurface = AioColors.InverseOnSurface,
    inversePrimary = AioColors.InversePrimary,
    scrim = AioColors.Scrim,
    surfaceTint = AioColors.PrimaryContainer
)

/**
 * Dynamic color scheme (placeholder - resolved at runtime)
 */
@Composable
fun DynamicColorScheme(): ColorScheme {
    // Will be overridden by dynamic color implementation
    return DarkColorScheme
}
