package com.aio.calculator.feature.calculator

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScientificCalculatorScreen(
    onNavigateToBasic: () -> Unit,
    onOpenDrawer: () -> Unit,
    onOpenHistory: () -> Unit,
    onCalculation: (expression: String, result: String) -> Unit = { _, _ -> },
) {
    CalculatorSurface(
        title = "Scientific Calculator",
        onOpenDrawer = onOpenDrawer,
        onOpenHistory = onOpenHistory,
        extraLabels = listOf("sin(", "cos(", "tan(", "√"),
        onNavigateToOtherMode = onNavigateToBasic,
        onCalculation = onCalculation,
    )
}
