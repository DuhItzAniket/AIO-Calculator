package com.aio.calculator.feature.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aio.calculator.core.design.CalculatorColorScheme
import com.aio.calculator.core.design.CalculatorColors
import com.aio.calculator.core.math.CalculatorEngine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicCalculatorScreen(
    onNavigateToScientific: () -> Unit,
    onOpenDrawer: () -> Unit,
    onOpenHistory: () -> Unit,
    onCalculation: (expression: String, result: String) -> Unit = { _, _ -> },
) {
    CalculatorSurface("Calculator", onOpenDrawer, onOpenHistory, listOf("√", "π"), onNavigateToScientific, onCalculation)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CalculatorSurface(
    title: String,
    onOpenDrawer: () -> Unit,
    onOpenHistory: () -> Unit,
    extraLabels: List<String>,
    onNavigateToOtherMode: () -> Unit,
    onCalculation: (expression: String, result: String) -> Unit,
) {
    val engine = remember { CalculatorEngine() }
    val colors = CalculatorColors()
    var expression by remember { mutableStateOf("") }
    var display by remember { mutableStateOf("0") }

    fun append(value: String) {
        expression += value
        display = expression.ifBlank { "0" }
    }
    fun evaluate() {
        if (expression.isBlank()) return
        val input = expression
        engine.evaluate(expression).onSuccess { value ->
            display = formatResult(value)
            expression = display
            onCalculation(input, display)
        }.onFailure { display = "Error" }
    }

    val rows = listOf(
        listOf("C", "DEL", "(", ")"),
        listOf("7", "8", "9", "÷"),
        listOf("4", "5", "6", "×"),
        listOf("1", "2", "3", "−"),
        listOf("0", ".", "+", "="),
        extraLabels,
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = { Button(onClick = onOpenDrawer) { Text("Menu") } },
                actions = {
                    Button(onClick = onOpenHistory) { Text("History") }
                    Button(onClick = onNavigateToOtherMode) { Text("Mode") }
                },
            )
        },
    ) { paddingValues ->
        BoxWithConstraints(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)) {
            val wideLayout = maxWidth >= 600.dp
            if (wideLayout) {
                Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    DisplayText(display, colors, Modifier.weight(1f))
                    Keyboard(rows, colors, Modifier.weight(1f), ::evaluate, ::append, { expression = expression.dropLast(1); display = expression.ifBlank { "0" } }) { expression = ""; display = "0" }
                }
            } else {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    DisplayText(display, colors, Modifier.weight(1f))
                    Keyboard(rows, colors, Modifier, ::evaluate, ::append, { expression = expression.dropLast(1); display = expression.ifBlank { "0" } }) { expression = ""; display = "0" }
                }
            }
        }
    }
}

@Composable
private fun DisplayText(display: String, colors: CalculatorColorScheme, modifier: Modifier) {
    Text(
        text = display,
        modifier = modifier.fillMaxWidth(),
        fontSize = 42.sp,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.End,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = colors.displayText,
    )
}

@Composable
private fun Keyboard(
    rows: List<List<String>>,
    colors: CalculatorColorScheme,
    modifier: Modifier,
    evaluate: () -> Unit,
    append: (String) -> Unit,
    delete: () -> Unit,
    clear: () -> Unit,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        rows.forEach { row ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                row.forEach { label ->
                    KeyButton(label, colors, Modifier.weight(1f)) {
                        when (label) {
                            "C" -> clear()
                            "DEL" -> delete()
                            "=" -> evaluate()
                            "÷" -> append("/")
                            "×" -> append("*")
                            "−" -> append("-")
                            "√" -> append("sqrt(")
                            "π" -> append("pi")
                            else -> append(label)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun KeyButton(label: String, colors: CalculatorColorScheme, modifier: Modifier, onClick: () -> Unit) {
    val operator = label in setOf("+", "−", "×", "÷", "=")
    Button(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (operator) colors.operatorBackground else colors.buttonBackground,
            contentColor = if (operator) colors.operatorText else colors.buttonText,
        ),
    ) { Text(label, fontSize = 18.sp) }
}

private fun formatResult(value: Double): String =
    if (value == value.toLong().toDouble()) value.toLong().toString()
    else "%.10f".format(value).trimEnd('0').trimEnd('.')
