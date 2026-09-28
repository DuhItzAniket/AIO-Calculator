package com.aio.calculator.feature.finance

import androidx.compose.foundation.layout.Arrangement
import androidx.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aio.calculator.core.common.ConverterType
import com.aio.calculator.core.design.CalculatorColors

/**
 * Finance Calculator Screen - Financial calculations
 */
@Composable
fun FinanceCalculatorScreen(
    onBack: () -> Unit,
    calculatorType: ConverterType,
    onReset: () -> Unit
) {
    val calcColors = CalculatorColors()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            color = calcColors.surface
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Finance Calculator",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = calcColors.onSurface
                )

                Button(
                    onClick = onReset,
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = calcColors.surfaceContainerHigh,
                        contentColor = calcColors.onSurfaceContainer
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Reset",
                        fontSize = 12.sp,
                        color = calcColors.onSurfaceContainer
                    )
                }
            }
        }

        // Finance calculations area
        FinanceConversionArea(calculatorType = calculatorType)
    }
}

/** Finance conversion area */
@Composable
fun FinanceConversionArea(
    calculatorType: ConverterType
) {
    Surface(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        color = CalculatorColors().displayBackground
    ) {
        Text(
            text = "${calculatorType.displayName} Calculator",
            fontSize = 18.sp,
            color = CalculatorColors().displayText
        )
    }
}