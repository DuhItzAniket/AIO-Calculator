package com.aio.calculator.feature.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
 * Scientific Calculator Screen
 */
@Composable
fun ScientificCalculatorScreen(
    onNavigateToBasic: () -> Unit,
    onOpenDrawer: () -> Unit,
    onOpenHistory: () -> Unit
) {
    val engine = remember { CalculatorEngine(AngleMode.DEGREES) }
    var expression by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }
    var showResult by remember { mutableStateOf(false) }
    var angleMode by remember { mutableStateOf(AngleMode.DEGREES) }
    val calcColors = CalculatorColors()

    val displayText = if (showResult && result != null) result!! else expression

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        // Top App Bar
        TopAppBar(
            title = { Text(text = "Scientific Calculator") },
            navigationIcon = {
                IconButton(onClick = onOpenDrawer) {
                    Icon(Icons.Default.Menu, contentDescription = "Open menu")
                }
            },
            actions = {
                IconButton(onClick = onOpenHistory) {
                    Icon(Icons.Default.History, contentDescription = "History")
                }
                IconButton(onClick = onNavigateToBasic) {
                    Icon(Icons.Default.Calculate, contentDescription = "Basic mode")
                }
                // Angle mode toggle
                IconButton(onClick = { angleMode = if (angleMode == AngleMode.DEGREES) AngleMode.RADIANS else AngleMode.DEGREES }) {
                    Text(text = angleMode.displayName, fontSize = 12.sp)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = calcColors.displayBackground,
                titleContentColor = calcColors.displayText
            )
        )

        // Display
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
                    fontFamily = FontFamily.Monospace,
                    color = calcColors.displayText,
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.TextOverflow.Ellipsis,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        // Scientific Buttons
        ScientificButtonGrid(
            expression = expression,
            angleMode = angleMode,
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
            onConstantClick = { constant ->
                expression += constant
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
fun ScientificButtonGrid(
    expression: String,
    angleMode: AngleMode,
    onDigitClick: (String) -> Unit,
    onOperatorClick: (String) -> Unit,
    onFunctionClick: (String) -> Unit,
    onConstantClick: (String) -> Unit,
    onClearClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEqualsClick: () -> Unit,
    calcColors: CalculatorColors
) {
    val rows = arrayOf(
        arrayOf("C", "⌫", "(", ")", "^", "π"),
        arrayOf("sin", "cos", "tan", "ln", "log", "e"),
        arrayOf("asin", "acos", "atan", "sinh", "cosh", "tanh"),
        arrayOf("x²", "xʸ", "√", "1/x", "!", "%"),
        arrayOf("7", "8", "9", "÷", "DEG", "RAD"),
        arrayOf("4", "5", "6", "×", "(", ")"),
        arrayOf("1", "2", "3", "−", "ANS", "M+"),
        arrayOf("0", ".", "±", "+", "=", "=")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .weight(1f),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        rows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { label ->
                    val span = if (label == "=") 2 else 1
                    ScientificButton(
                        text = label,
                        onClick = when {
                            label in "0123456789." -> { onDigitClick(label) }
                            label in "+−×÷^" -> { onOperatorClick(label) }
                            label in "sin cos tan ln log asin acos atan sinh cosh tanh x² xʸ √ 1/x ! %" -> { onFunctionClick(mapScientificFunction(label)) }
                            label in "π e" -> { onConstantClick(mapConstant(label)) }
                            label == "C" -> { onClearClick() }
                            label == "⌫" -> { onDeleteClick() }
                            label == "=" -> { onEqualsClick() }
                            label == "ANS" -> { onDigitClick("ANS") }
                            label == "M+" -> { /* memory add */ }
                            label in "DEG RAD" -> { /* handled in top bar */ }
                            label in "()" -> { onFunctionClick(label) }
                            else -> {}
                        },
                        isOperator = label in "+−×÷^=",
                        isFunction = label in "sin cos tan ln log asin acos atan sinh cosh tanh x² xʸ √ 1/x ! % π e ANS M+ DEG RAD ()",
                        span = span,
                        calcColors = calcColors
                    )
                }
            }
        }
    }
}

private fun mapScientificFunction(label: String): String {
    return when (label) {
        "x²" -> "^2"
        "xʸ" -> "^"
        "√" -> "sqrt("
        "1/x" -> "1/("
        "!" -> "fact("
        "%" -> "/100*"
        "±" -> "*-1"
        else -> label.toLowerCase() + "("
    }
}

private fun mapConstant(label: String): String {
    return when (label) {
        "π" -> "pi"
        "e" -> "e"
        else -> label
    }
}

@Composable
fun ScientificButton(
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

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .weight(span.toFloat()),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = if (isOperator) FontWeight.Bold else FontWeight.Medium,
            maxLines = 1,
            overflow = androidx.compose.ui.text.TextOverflow.Ellipsis
        )
    }
}

private fun formatResult(value: Double): String {
    if (value == value.toLong().toDouble()) {
        return value.toLong().toString()
    }
    return "%.10f".format(value).replace(Regex("0+$"), "").replace(Regex("\\.$"), "")
}