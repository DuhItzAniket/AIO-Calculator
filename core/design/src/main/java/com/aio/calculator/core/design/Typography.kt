package com.aio.calculator.core.design

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * AIO Calculator typography system
 * Clean, readable, professional typography optimized for calculator UI
 */
object AioTypography {
    // Font families
    val FontFamilyDefault = FontFamily.Default
    val FontFamilyMonospace = FontFamily.Monospace

    // ==========================================================================
    // DISPLAY STYLES - Large headlines for calculator display
    // ==========================================================================
    // Main calculator display - large, bold, monospace
    val CalculatorDisplay = TextStyle(
        fontFamily = FontFamilyMonospace,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
        lineHeight = 56.sp,
        letterSpacing = 0.sp
    )

    // Calculator display smaller version - for secondary information
    val CalculatorDisplaySmall = TextStyle(
        fontFamily = FontFamilyMonospace,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    )

    // ==========================================================================
    // CALCULATOR BUTTON TEXTS
    // ==========================================================================
    // Main calculator button - medium size
    val CalculatorButton = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    )

    // Calculator function buttons (sin, cos, √, etc.) - smaller
    val CalculatorFunction = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    )

    // Calculator small function (for compact layouts)
    val CalculatorFunctionSmall = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    )

    // ==========================================================================
    // RESULT DISPLAY STYLES
    // ==========================================================================
    // Primary result - large, prominent result display
    val ResultPrimary = TextStyle(
        fontFamily = FontFamilyMonospace,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    )

    // Secondary result - smaller result text (formula, details)
    val ResultSecondary = TextStyle(
        fontFamily = FontFamilyMonospace,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    )

    // Formula result - for formula library display
    val ResultFormula = TextStyle(
        fontFamily = FontFamilyMonospace,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    )

    // ==========================================================================
    // HEADER & TITLE STYLES
    // ==========================================================================
    // Page titles in calculator screens
    val CalculatorTitle = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    )

    // Subtitle text under titles
    val CalculatorSubtitle = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    )

    // ==========================================================================
    // BODY & LABEL STYLES
    // ==========================================================================
    // Regular body text
    val BodyRegular = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    )

    // Small body text
    val BodySmall = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    )

    // Caption text (e.g., "DEG", "RAD", memory status)
    val Caption = TextStyle(
        fontFamily = FontFamilyDefault,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.5.sp
    )

    // ==========================================================================
    // MONOSPACE STYLES (for expressions, formulas)
    // ==========================================================================
    // Monospace bold for expressions
    val ExpressionBold = TextStyle(
        fontFamily = FontFamilyMonospace,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

    // Monospace regular for expressions
    val ExpressionRegular = TextStyle(
        fontFamily = FontFamilyMonospace,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.5.sp
    )

    // Monospace small for compact expression display
    val ExpressionSmall = TextStyle(
        fontFamily = FontFamilyMonospace,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    )

    // ==========================================================================
    // Material 3 Typography configuration
    // ==========================================================================
    val AioTypographyScheme = Typography(
        displayLarge = TextStyle(
            fontFamily = FontFamilyDefault,
            fontWeight = FontWeight.Bold,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = -0.25.sp
        ),
        displayMedium = TextStyle(
            fontFamily = FontFamilyDefault,
            fontWeight = FontWeight.Bold,
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp
        ),
        displaySmall = TextStyle(
            fontFamily = FontFamilyDefault,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = FontFamilyDefault,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = FontFamilyDefault,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = FontFamilyDefault,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp
        ),
        titleLarge = TextStyle(
            fontFamily = FontFamilyDefault,
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = FontFamilyDefault,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        titleSmall = TextStyle(
            fontFamily = FontFamilyDefault,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = FontFamilyDefault,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = FontFamilyDefault,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        ),
        bodySmall = TextStyle(
            fontFamily = FontFamilyDefault,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        ),
        labelLarge = TextStyle(
            fontFamily = FontFamilyDefault,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        labelMedium = TextStyle(
            fontFamily = FontFamilyDefault,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        labelSmall = TextStyle(
            fontFamily = FontFamilyDefault,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
    )
}