package com.aio.calculator.core.design

import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * AIO Calculator shape system
 * Consistent corner radius throughout the app
 */
object AioShapes {
    // Extra small - 4dp
    val ExtraSmall = androidx.compose.foundation.shape.RoundedCornerShape(4.dp)

    // Small - 8dp
    val Small = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)

    // Medium - 12dp
    val Medium = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)

    // Large - 16dp
    val Large = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)

    // Extra large - 24dp
    val ExtraLarge = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)

    // Full - 50% (pill/circle)
    val Full = androidx.compose.foundation.shape.RoundedCornerShape(50.dp)

    // Calculator buttons
    val CalculatorButton = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
    val CalculatorButtonSmall = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
    val CalculatorDisplay = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)

    // Cards
    val Card = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
    val CardElevated = androidx.compose.foundation.shape.RoundedCornerShape(20.dp)

    // Chips
    val Chip = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)

    // Bottom sheet
    val BottomSheet = androidx.compose.foundation.shape.RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
}

/**
 * Material 3 Shapes configuration
 */
val AioShapesScheme = Shapes(
    extraSmall = AioShapes.ExtraSmall,
    small = AioShapes.Small,
    medium = AioShapes.Medium,
    large = AioShapes.Large,
    extraLarge = AioShapes.ExtraLarge
)