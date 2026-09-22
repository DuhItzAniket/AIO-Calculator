package com.aio.calculator.ui.theme

import androidx.lifecycle.ViewModel
import com.aio.calculator.core.common.AngleMode
import com.aio.calculator.core.common.DecimalPrecision
import com.aio.calculator.core.common.ThemeMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/** Lightweight settings state holder for the application UI. */
class ThemeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ThemeUiState())
    val uiState: StateFlow<ThemeUiState> = _uiState

    fun setThemeMode(value: ThemeMode) = _uiState.update { it.copy(themeMode = value) }
    fun setAngleMode(value: AngleMode) = _uiState.update { it.copy(angleMode = value) }
    fun setDecimalPrecision(value: DecimalPrecision) = _uiState.update { it.copy(decimalPrecision = value) }
    fun setDefaultCurrency(value: String) = _uiState.update { it.copy(defaultCurrency = value) }
    fun setAutoUpdateRates(value: Boolean) = _uiState.update { it.copy(autoUpdateRates = value) }
}

data class ThemeUiState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM_DEFAULT,
    val angleMode: AngleMode = AngleMode.DEGREES,
    val decimalPrecision: DecimalPrecision = DecimalPrecision.AUTO,
    val defaultCurrency: String = "USD",
    val autoUpdateRates: Boolean = true,
)
