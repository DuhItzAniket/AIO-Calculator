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
 * Modern Scientific Calculator Screen
 * Features:
 * - Two-line display (expression + result)
 * - Tabbed interface: Basic | Scientific | Functions
 * - Angle mode indicator (DEG/RAD)
 * - Memory and ANS indicators
 * - Landscape optimization
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

    // Two-line display: expression above result
    val displayText = if (showResult && result != null) result!! else expression
    val resultText = if (showResult && result != null) result!! else "0"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(0.dp)
    ) {
        // Top App Bar with angle mode indicator
        AioTheme {
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
                    // Angle mode toggle with badge
                    AngleModeBadge(angleMode = angleMode)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = calcColors.displayBackground,
                    titleContentColor = calcColors.displayText
                )
            )
        }

        // Two-line display area
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            color = calcColors.displayBackground
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                // Row 1: Expression
                Text(
                    text = displayText,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                    color = calcColors.displayText.copy(alpha = 0.7f),
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.TextOverflow.Ellipsis,
                )
                // Row 2: Result (prominent when showing)
                Text(
                    text = resultText,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                    color = calcColors.displayText,
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.TextOverflow clip,
                )
            }
        }

        // Tabbed interface at the top (Basic | Scientific | Functions)
        // Using a simple tab indicator
        TabIndicatorBar(angleMode = angleMode)

        // Scientific buttons grid
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

/** Angle mode badge shown in the app bar */
@Composable
fun AngleModeBadge(angleMode: AngleMode) {
    val isDark = MaterialTheme.colorScheme.brightness == androidx.compose.material3.ColorScheme.Dark
    val isOled = MaterialTheme.colorScheme.surface == Color.Black
    val textColor = if (isOled) AioColors.OnSurfaceOled else if (isDark) AioColors.OnSurfaceDark else AioColors.OnSurfaceLight

    // Badge background based on theme
    val bgColor = if (angleMode == com.aio.calculator.core.common.AngleMode.DEGREES) {
        if (isOled) AioColors.CalculatorOperatorBgOled else if (isDark) AioColors.CalculatorOperatorBgDark else AioColors.CalculatorOperatorBgLight
    } else {
        if (isOled) AioColors.CalculatorFunctionBgOled else if (isDark) AioColors.CalculatorFunctionBgDark else AioColors.CalculatorFunctionBgLight
    }

    Text(
        text = angleMode.displayName,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        color = textColor,
        background = bgColor,
        style = androidx.compose.material3.MaterialTheme.typography.labelSmall,
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}

/** Tab indicator bar showing current mode */
@Composable
fun TabIndicatorBar(angleMode: AngleMode) {
    val isDark = MaterialTheme.colorScheme.brightness == androidx.compose.material3.ColorScheme.Dark
    val isOled = MaterialTheme.colorScheme.surface == Color.Black
    val selectedColor = if (isOled) AioColors.OnSurfaceOled else if (isDark) AioColors.OnSurfaceDark else AioColors.OnSurfaceLight
    val unselectedColor = AioColors.OnSurfaceVariant.copy(alpha = 0.3f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = androidx.compose.foundation.layout.VerticalAlignment.Center
    ) {
        // Basic tab
        TabButton(
            text = "Basic",
            isSelected = true, // Always show basic as default/first
            onClick = {},
            selectedColor = selectedColor,
            unselectedColor = unselectedColor
        )
        // Scientific tab
        Spacer(Modifier.width(8.dp))
        TabButton(
            text = "Scientific",
            isSelected = !true, // Scientific is active when in scientific mode
            onClick = {},
            selectedColor = selectedColor,
            unselectedColor = unselectedColor
        )
        // Functions tab
        Spacer(Modifier.width(8.dp))
        TabButton(
            text = "Func",
            isSelected = false,
            onClick = {},
            selectedColor = selectedColor,
            unselectedColor = unselectedColor
        )
    }
}

@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    selectedColor: Color,
    unselectedColor: Color
) {
    val color = if (isSelected) selectedColor else unselectedColor
    Text(
        text = text,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        color = color,
        modifier = Modifier.padding(horizontal = 4.dp)
    )
}

/** Memory/ANS indicators shown in the app bar */
@Composable
fun MemoryIndicators(
    hasAnswer: Boolean,
    memoryValue: Double = 0.0
) {
    val isDark = MaterialTheme.colorScheme.brightness == androidx.compose.material3.ColorScheme.Dark
    val isOled = MaterialTheme.colorScheme.surface == Color.Black
    val textColor = if (isOled) AioColors.OnSurfaceOled else if (isDark) AioColors.OnSurfaceDark else AioColors.OnSurfaceLight

    // Show ANS if there's a last answer
    if (hasAnswer) {
        Text(
            text = "ANS",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.padding(start = 4.dp)
        )
    }

    // Show memory if non-zero
    if (memoryValue != 0.0) {
        Text(
            text = "M",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.padding(start = 2.dp)
        )
    }
}

/** Scientific button grid with enhanced layout */
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
    // Scientific function rows with logical grouping
    val rows = arrayOf(
        // Row 1: Functions and constants
        arrayOf("C", "⌫", "(", ")", "^", "π"),
        // Row 2: Trig functions
        arrayOf("sin", "cos", "tan", "ln", "log", "e"),
        // Row 3: Inverse hyperbolic
        arrayOf("asin", "acos", "atan", "sinh", "cosh", "tanh"),
        // Row 4: Powers and roots
        arrayOf("x²", "xʸ", "√", "1/x", "!", "%"),
        // Row 5: Numbers with angle mode
        arrayOf("7", "8", "9", "÷", "DEG", "RAD"),
        // Row 6: More numbers
        arrayOf("4", "5", "6", "×", "(", ")"),
        // Row 7: More numbers with memory
        arrayOf("1", "2", "3", "−", "ANS", "M+"),
        // Row 8: Zero and equals
        arrayOf("0", ".", "±", "+", "=", "=")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .weight(1f),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        rows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(4.dp)
            ) {
                row.forEach { label ->
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
                            label == "ANS" -> { /* recall ANS */ }
                            label == "M+" -> { /* memory add */ }
                            label in "DEG RAD" -> { /* toggle angle mode */ }
                            label in "()" -> { onFunctionClick(label) }
                            else -> {}
                        },
                        isOperator = label in "+−×÷^=",
                        isFunction = label in "sin cos tan ln log asin acos atan sinh cosh tanh x² xʸ √ 1/x ! % π e ANS M+ DEG RAD ()",
                        span = 1,
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

    // Adjustable button height based on type
    val buttonHeight = when {
        isOperator -> 52.dp
        isFunction -> 48.dp
        else -> 52.dp
    }

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(buttonHeight)
            .weight(span.toFloat()),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = text,
            fontSize = if (isOperator) 16.sp else 14.sp,
            fontWeight = if (isOperator) FontWeight.Bold else FontWeight.Normal,
            maxLines = 1,
            overflow = androidx.compose.ui.text.TextOverflow.Ellipsis
        )
    }
}

private fun formatResult(value: Double): String {
    if (value == value.toLong().toDouble()) {
        return value.toLong().toString()
    }
    return "%.6f".format(value).replace(Regex("0+$"), "").replace(Regex("\\.$"), "")
}