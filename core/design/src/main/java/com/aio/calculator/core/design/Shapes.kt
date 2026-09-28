package com.aio.calculator.core.design

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

/**
 * AIO Calculator shape system
 * Consistent corner radius throughout the app for premium feel
 */
object AioShapes {
    // Extra small - 4dp - used for small chips, tags
    val ExtraSmall = RoundedCornerShape(4.dp)

    // Small - 8dp - used for chips, quick actions
    val Small = RoundedCornerShape(8.dp)

    // Medium - 12dp - used for buttons, cards
    val Medium = RoundedCornerShape(12.dp)

    // Large - 16dp - used for displays, containers
    val Large = RoundedCornerShape(16.dp)

    // Extra large - 24dp - used for modals, panels
    val ExtraLarge = RoundedCornerShape(24.dp)

    // Full - 50% (pill/circle shape)
    val Full = RoundedCornerShape(50.dp)

    // ==========================================================================
    // CALCULATOR-SPECIFIC SHAPES
    // ==========================================================================
    // Calculator display - largest rounded rectangle
    val CalculatorDisplay = RoundedCornerShape(16.dp)

    // Calculator button - medium buttons (0-9, operators)
    val CalculatorButton = RoundedCornerShape(12.dp)

    // Calculator function buttons (smaller functions)
    val CalculatorFunction = RoundedCornerShape(10.dp)

    // Calculator display border - emphasis
    val CalculatorDisplayBorder = RoundedCornerShape(20.dp)

    // Card elevations
    val CardStandard = RoundedCornerShape(16.dp)
    val CardElevated = RoundedCornerShape(20.dp)

    // Chip / pill shape
    val Chip = RoundedCornerShape(20.dp)

    // Bottom sheet - rounded top corners only
    val BottomSheet = RoundedCornerShape(
        topStart = 24.dp,
        topEnd = 24.dp
    )

    // ==========================================================================
    // Material 3 Shapes configuration
    // ==========================================================================
    val AioShapesScheme = Shapes(
        extraSmall = AioShapes.ExtraSmall,
        small = AioShapes.Small,
        medium = AioShapes.Medium,
        large = AioShapes.Large,
        extraLarge = AioShapes.ExtraLarge
    )
}