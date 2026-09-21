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
 * Defines semantic colors for the app
 */
object AioColors {
    // Primary brand colors - Blue/Teal theme
    val Primary = Color(0xFF006E7F)
    val PrimaryContainer = Color(0xFF97F0FF)
    val OnPrimary = Color(0xFFFFFFFF)
    val OnPrimaryContainer = Color(0xFF002127)

    // Secondary colors
    val Secondary = Color(0xFF4A6367)
    val SecondaryContainer = Color(0xFFCCE8EC)
    val OnSecondary = Color(0xFFFFFFFF)
    val OnSecondaryContainer = Color(0xFF061F22)

    // Tertiary colors - Accent
    val Tertiary = Color(0xFF7C5A00)
    val TertiaryContainer = Color(0xFFFFDD95)
    val OnTertiary = Color(0xFFFFFFFF)
    val OnTertiaryContainer = Color(0xFF271900)

    // Error colors
    val Error = Color(0xFFBA1A1A)
    val ErrorContainer = Color(0xFFFFDAD6)
    val OnError = Color(0xFFFFFFFF)
    val OnErrorContainer = Color(0xFF410002)

    // Surface colors - Light
    val Surface = Color(0xFFFAFDFC)
    val OnSurface = Color(0xFF191D1D)
    val SurfaceVariant = Color(0xFFDBE5E7)
    val OnSurfaceVariant = Color(0xFF3F494A)
    val Outline = Color(0xFF6F797A)
    val OutlineVariant = Color(0xFFBFC9CA)

    // Surface colors - Dark
    val SurfaceDark = Color(0xFF111414)
    val OnSurfaceDark = Color(0xFFE0E4E4)
    val SurfaceVariantDark = Color(0xFF3F494A)
    val OnSurfaceVariantDark = Color(0xFFBFC9CA)
    val OutlineDark = Color(0xFF899394)
    val OutlineVariantDark = Color(0xFF3F494A)

    // OLED Black surfaces
    val SurfaceOled = Color(0xFF000000)
    val OnSurfaceOled = Color(0xFFFFFFFF)
    val SurfaceContainerOled = Color(0xFF1A1A1A)
    val SurfaceContainerHighOled = Color(0xFF222222)
    val SurfaceContainerHighestOled = Color(0xFF2A2A2A)

    // Inverse colors
    val InverseSurface = Color(0xFFE0E4E4)
    val InverseOnSurface = Color(0xFF191D1D)
    val InversePrimary = Color(0xFF4FD8EC)

    // Shadow
    val Shadow = Color(0xFF000000)

    // Scrim
    val Scrim = Color(0xFF000000)

    // Calculator specific
    val CalculatorButtonBg = Color(0xFFF0F4F4)
    val CalculatorButtonText = Color(0xFF191D1D)
    val CalculatorOperatorBg = Color(0xFF006E7F)
    val CalculatorOperatorText = Color(0xFFFFFFFF)
    val CalculatorFunctionBg = Color(0xFFDBE5E7)
    val CalculatorFunctionText = Color(0xFF191D1D)
    val CalculatorDisplayBg = Color(0xFFFAFDFC)
    val CalculatorDisplayText = Color(0xFF191D1D)

    // Dark calculator
    val CalculatorButtonBgDark = Color(0xFF2A2F2F)
    val CalculatorButtonTextDark = Color(0xFFE0E4E4)
    val CalculatorOperatorBgDark = Color(0xFF4FD8EC)
    val CalculatorOperatorTextDark = Color(0xFF002127)
    val CalculatorFunctionBgDark = Color(0xFF3F494A)
    val CalculatorFunctionTextDark = Color(0xFFE0E4E4)
    val CalculatorDisplayBgDark = Color(0xFF111414)
    val CalculatorDisplayTextDark = Color(0xFFE0E4E4)

    // OLED calculator
    val CalculatorButtonBgOled = Color(0xFF1A1A1A)
    val CalculatorButtonTextOled = Color(0xFFFFFFFF)
    val CalculatorOperatorBgOled = Color(0xFF006E7F)
    val CalculatorOperatorTextOled = Color(0xFFFFFFFF)
    val CalculatorFunctionBgOled = Color(0xFF222222)
    val CalculatorFunctionTextOled = Color(0xFFFFFFFF)
    val CalculatorDisplayBgOled = Color(0xFF000000)
    val CalculatorDisplayTextOled = Color(0xFFFFFFFF)
}

/**
 * Light color scheme
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
    surface = AioColors.Surface,
    onSurface = AioColors.OnSurface,
    surfaceContainer = AioColors.SurfaceVariant,
    surfaceContainerLow = AioColors.Surface,
    surfaceContainerHigh = AioColors.SurfaceVariant,
    surfaceContainerHighest = AioColors.OutlineVariant,
    onSurfaceVariant = AioColors.OnSurfaceVariant,
    outline = AioColors.Outline,
    outlineVariant = AioColors.OutlineVariant,
    inverseSurface = AioColors.InverseSurface,
    inverseOnSurface = AioColors.InverseOnSurface,
    inversePrimary = AioColors.InversePrimary,
    shadow = AioColors.Shadow,
    scrim = AioColors.Scrim,
    surfaceTint = AioColors.Primary
)

/**
 * Dark color scheme
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
    shadow = AioColors.Shadow,
    scrim = AioColors.Scrim,
    surfaceTint = AioColors.PrimaryContainer
)

/**
 * OLED Black color scheme
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
    shadow = AioColors.Shadow,
    scrim = AioColors.Scrim,
    surfaceTint = AioColors.PrimaryContainer
)

/**
 * Dynamic color scheme (placeholder - will be resolved at runtime)
 */
@Composable
fun DynamicColorScheme(): ColorScheme {
    // This will be overridden by the dynamic color implementation
    return DarkColorScheme
}