package com.aio.calculator.core.design

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * AIO Calculator typography system
 * Clean, readable, professional typography
 */
object AioTypography {
    // Font families
    val FontFamilyDefault = FontFamily.Default
    val FontFamilyMonospace = FontFamily.Monospace

    // Display styles - large headlines
    val DisplayLarge = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = -0.25.sp
    )

    val DisplayMedium = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    )

    val DisplaySmall = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    )

    // Headline styles
    val HeadlineLarge = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    )

    val HeadlineMedium = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    )

    val HeadlineSmall = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    )

    // Title styles
    val TitleLarge = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    )

    val TitleMedium = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    )

    val TitleSmall = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    )

    // Body styles
    val BodyLarge = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

    val BodyMedium = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    )

    val BodySmall = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    )

    // Label styles
    val LabelLarge = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    )

    val LabelMedium = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

    val LabelSmall = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

    // Calculator specific
    val CalculatorDisplay = TextStyle(
        fontFamily = FontFamilyMonospace,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
        lineHeight = 56.sp,
        letterSpacing = 0.sp
    )

    val CalculatorDisplaySmall = TextStyle(
        fontFamily = FontFamilyMonospace,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    )

    val CalculatorButton = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    )

    val CalculatorFunction = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    )

    // Result display
    val ResultPrimary = TextStyle(
        fontFamily = FontFamilyMonospace,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    )

    val ResultSecondary = TextStyle(
        fontFamily = FontFamilyMonospace,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    )

    val ResultFormula = TextStyle(
        fontFamily = FontFamilyMonospace,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    )
}

/**
 * Material 3 Typography configuration
 */
val AioTypographyScheme = Typography(
    displayLarge = AioTypography.DisplayLarge,
    displayMedium = AioTypography.DisplayMedium,
    displaySmall = AioTypography.DisplaySmall,
    headlineLarge = AioTypography.HeadlineLarge,
    headlineMedium = AioTypography.HeadlineMedium,
    headlineSmall = AioTypography.HeadlineSmall,
    titleLarge = AioTypography.TitleLarge,
    titleMedium = AioTypography.TitleMedium,
    titleSmall = AioTypography.TitleSmall,
    bodyLarge = AioTypography.BodyLarge,
    bodyMedium = AioTypography.BodyMedium,
    bodySmall = AioTypography.BodySmall,
    labelLarge = AioTypography.LabelLarge,
    labelMedium = AioTypography.LabelMedium,
    labelSmall = AioTypography.LabelSmall
)