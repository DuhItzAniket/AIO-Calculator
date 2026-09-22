package com.aio.calculator.core.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

/**
 * Extension functions for common operations
 */

// StateFlow extensions
fun <T> MutableStateFlow<T>.update(block: (T) -> T) {
    value = block(value)
}

// CoroutineScope extensions
fun CoroutineScope.launchViewModel(block: suspend () -> Unit) = launch { block() }

// String extensions
fun String.isValidNumber(): Boolean = toDoubleOrNull() != null

fun String.toDoubleSafe(): Double? = toDoubleOrNull()

fun Double.format(precision: DecimalPrecision): String = when (precision) {
    DecimalPrecision.AUTO -> this.toString()
    DecimalPrecision.ZERO -> "%.0f".format(this)
    DecimalPrecision.ONE -> "%.1f".format(this)
    DecimalPrecision.TWO -> "%.2f".format(this)
    DecimalPrecision.THREE -> "%.3f".format(this)
    DecimalPrecision.FOUR -> "%.4f".format(this)
    DecimalPrecision.FIVE -> "%.5f".format(this)
    DecimalPrecision.SIX -> "%.6f".format(this)
    DecimalPrecision.SCIENTIFIC -> "%.6e".format(this)
    DecimalPrecision.ENGINEERING -> formatEngineering(this)
}

private fun formatEngineering(value: Double): String {
    if (value == 0.0) return "0"
    val absValue = abs(value)
    val exponent = floor(log10(absValue))
    val engExponent = floor(exponent / 3) * 3
    val mantissa = value / 10.0.pow(engExponent)
    return if (engExponent == 0.0) {
        "%.3f".format(mantissa)
    } else {
        "%.3fE%+.0f".format(mantissa, engExponent)
    }
}

// Remember mutable state with delegate
@Composable
inline fun <T> rememberMutableState(initialValue: T): MutableState<T> = remember { mutableStateOf(initialValue) }

// Nullable string to boolean
fun String?.isNullOrBlank(): Boolean = this == null || this.isBlank()

// Safe list operations
fun <T> List<T>.firstOrNull(predicate: (T) -> Boolean): T? = firstOrNull { predicate(it) }

// Time formatting
fun Long.formatDuration(): String {
    val seconds = this / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    return when {
        days > 0 -> "${days}d ${hours % 24}h"
        hours > 0 -> "${hours}h ${minutes % 60}m"
        minutes > 0 -> "${minutes}m ${seconds % 60}s"
        else -> "${seconds}s"
    }
}

fun Long.formatTimestamp(): String {
    return kotlinx.datetime.Instant.fromEpochMilliseconds(this)
        .toString()
}
