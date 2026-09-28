package com.aio.calculator.feature.converter

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
 * Unit Converter Screen - Base converter screen
 */
@Composable
fun ConverterScreen(
    onBack: () -> Unit,
    converterType: ConverterType,
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
                    text = converterType.displayName,
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

        // Conversion area
        ConverterConversionArea(converterType = converterType)
    }
}

/** Conversion area composable */
@Composable
fun ConverterConversionArea(
    converterType: ConverterType
) {
    // This will be implemented for each converter type
    // Placeholder for now
    Surface(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        color = CalculatorColors().displayBackground
    ) {
        Text(
            text = "${converterType.displayName} Converter",
            fontSize = 18.sp,
            color = CalculatorColors().displayText
        )
    }
}

/** Converter Type enum */
enum class ConverterType(
    val displayName: String,
    val description: String
) {
    LENGTH("Length", "Convert between length units"),
    TEMPERATURE("Temperature", "Convert between temperature units"),
    WEIGHT("Weight", "Convert between weight units"),
    VOLUME("Volume", "Convert between volume units"),
    TIME("Time", "Convert between time units"),
    AREA("Area", "Convert between area units"),
    SPEED("Speed", "Convert between speed units"),
    DATA("Data", "Convert between data units"),
    ENERGY("Energy", "Convert between energy units"),
    POWER("Power", "Convert between power units");
}