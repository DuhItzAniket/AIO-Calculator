package com.aio.calculator.feature.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aio.calculator.core.common.AngleMode
import com.aio.calculator.core.design.AioTheme
import com.aio.calculator.core.design.CalculatorColors
import com.aio.calculator.core.math.CalculatorEngine

/**
 * Modern Basic Calculator Screen
 * Features:
 * - Large high-contrast display
 * - Persistent history sidebar
 * - Theme-aware colors and animations
 * - Adaptive layout for phone/ tablet
 */
@Composable
fun BasicCalculatorScreen(
    onNavigateToScientific: () -> Unit,
    onOpenDrawer: () -> Unit,
    onOpenHistory: () -> Unit
) {
    val engine = remember { CalculatorEngine() }
    var expression by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }
    var showResult by remember { mutableStateOf(false) }
    val calcColors = CalculatorColors()

    // Display text based on state
    val displayText = if (showResult && result != null) result!! else expression

    // Calculate if expression is not empty and not showing result
    val shouldCalculate = expression.isNotEmpty() && !showResult

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(0.dp)
    ) {
        // Top App Bar with theme-aware colors
        AioTheme {
            TopAppBar(
                title = { Text(text = "Calculator") },
                navigationIcon = {
                    IconButton(onClick = onOpenDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Open menu")
                    }
                },
                actions = {
                    IconButton(onClick = onOpenHistory) {
                        Icon(Icons.Default.History, contentDescription = "History")
                    }
                    IconButton(onClick = onNavigateToScientific) {
                        Icon(Icons.Default.Science, contentDescription = "Scientific mode")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = calcColors.displayBackground,
                    titleContentColor = calcColors.displayText
                )
            )
        }

        // Display area with animation
        if (showResult) {
            // Result display animation
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                color = calcColors.displayBackground
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Text(
                        text = if (result != null) result else "0",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                        color = calcColors.displayText,
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.TextOverflow.Ellipsis,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        } else {
            // Expression display
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                color = calcColors.displayBackground
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Text(
                        text = if (displayText.isEmpty()) "0" else displayText,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                        color = calcColors.displayText,
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.TextOverflow.Ellipsis,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

        // Calculator button grid with ripple animation
        CalculatorButtonGrid(
            expression = expression,
            onDigitClick = { digit ->
                if (showResult) {
                    expression = digit
                    showResult = false
                    result = null
                } else {
                    expression += digit
                }
            },
            onOperatorClick = { operator ->
                if (showResult) {
                    expression = result!! + operator
                    showResult = false
                    result = null
                } else if (expression.isNotEmpty()) {
                    val lastChar = expression.last()
                    if (lastChar in "+-*/^") {
                        expression = expression.dropLast(1) + operator
                    } else {
                        expression += operator
                    }
                }
            },
            onFunctionClick = { function ->
                expression += function
            },
            onClearClick = {
                expression = ""
                result = null
                showResult = false
            },
            onDeleteClick = {
                if (showResult) {
                    expression = result!!
                    showResult = false
                    result = null
                } else if (expression.isNotEmpty()) {
                    expression = expression.dropLast(1)
                }
            },
            onEqualsClick = {
                if (expression.isNotEmpty() && !showResult) {
                    val evalResult = engine.evaluate(expression)
                    evalResult.onSuccess { value ->
                        result = formatResult(value)
                        showResult = true
                    }
                    evalResult.onFailure { error ->
                        result = "Error"
                        showResult = true
                    }
                }
            },
            calcColors = calcColors
        )
    }
}

@Composable
fun CalculatorButtonGrid(
    expression: String,
    onDigitClick: (String) -> Unit,
    onOperatorClick: (String) -> Unit,
    onFunctionClick: (String) -> Unit,
    onClearClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEqualsClick: () -> Unit,
    calcColors: CalculatorColors
) {
    // Dynamic button grid based on available width
    val buttonCount = if ( androidx.compose.ui.platform.LocalContext.current.resources.configuration.screenWidthdpi > 600 ) {
        // Tablet mode: more buttons per row
        5
    } else {
        // Phone mode: standard 4 buttons per row
        4
    }

    val buttons = when (buttonCount) {
        5 -> arrayOf(
            arrayOf("MC", "MR", "M+", "M-", "C"),
            arrayOf("7", "8", "9", "÷", "√"),
            arrayOf("4", "5", "6", "×", "x²"),
            arrayOf("1", "2", "3", "−", "π"),
            arrayOf("0", ".", "±", "+", "=")
        )
        else -> arrayOf(
            arrayOf("MC", "MR", "M+", "M-", "C"),
            arrayOf("7", "8", "9", "÷", "√"),
            arrayOf("4", "5", "6", "×", "x²"),
            arrayOf("1", "2", "3", "−", "π"),
            arrayOf("0", ".", "±", "+", "=")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .weight(1f),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(4.dp)
    ) {
        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(4.dp)
            ) {
                row.forEach { label ->
                    CalculatorButton(
                        text = label,
                        onClick = when {
                            label in "0123456789." -> { onDigitClick(label) }
                            label in "+−×÷^" -> { onOperatorClick(label) }
                            label in "x²√πMCMRM+C" -> { onFunctionClick(mapFunction(label)) }
                            label == "C" -> { onClearClick() }
                            label == "⌫" -> { onDeleteClick() }
                            label == "=" -> { onEqualsClick() }
                            else -> {}
                        },
                        isOperator = label in "+−×÷^=",
                        isFunction = label in "x²√πMCMRM+C",
                        span = 1,
                        calcColors = calcColors
                    )
                }
            }
        }
    }
}

private fun mapFunction(label: String): String {
    return when (label) {
        "x²" -> "^2"
        "√" -> "sqrt("
        "xʸ" -> "^"
        "π" -> "pi"
        "e" -> "e"
        "±" -> "*-1"
        else -> label
    }
}

@Composable
fun CalculatorButton(
    text: String,
    onClick: () -> Unit,
    isOperator: Boolean = false,
    isFunction: Boolean = false,
    span: Int = 1,
    calcColors: CalculatorColors
) {
    val (backgroundColor, contentColor) = when {
        isOperator -> calcColors.operatorBackground to calcColors.operatorText
        isFunction -> calcColors.functionBackground to calcColors.functionText
        else -> calcColors.buttonBackground to calcColors.buttonText
    }

    // Accessible touch target with minimum 48dp
    val adjustedSize = if (isOperator) 60.dp else 52.dp

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(adjusted_size)
            .weight(span.toFloat()),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = text,
            fontSize = if (isOperator) 24.sp else 20.sp,
            fontWeight = if (isOperator) FontWeight.Bold else FontWeight.Medium
        )
    }
}

private fun formatResult(value: Double): String {
    if (value == value.toLong().toDouble()) {
        return value.toLong().toString()
    }
    return "%.6f".format(value).replace(Regex("0+$"), "").replace(Regex("\\.$"), "")
}