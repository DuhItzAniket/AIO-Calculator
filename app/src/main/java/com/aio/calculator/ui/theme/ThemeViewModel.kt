package com.aio.calculator.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.aio.calculator.core.common.AngleMode
import com.aio.calculator.core.common.DecimalPrecision
import com.aio.calculator.core.common.ThemeMode
import com.aio.calculator.core.datastore.SettingsRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/** Lightweight settings state holder for the application UI. */
class ThemeViewModel(application: Application) : AndroidViewModel(application) {
    private val settings = SettingsRepository(application)
    private val _uiState = MutableStateFlow(ThemeUiState())
    val uiState: StateFlow<ThemeUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = ThemeUiState(
                themeMode = settings.getThemeMode(),
                angleMode = settings.getAngleMode(),
                decimalPrecision = settings.getDecimalPrecision(),
                defaultCurrency = settings.getDefaultCurrency(),
                autoUpdateRates = settings.getAutoUpdateRates(),
            )
        }
    }

    fun setThemeMode(value: ThemeMode) {
        _uiState.update { it.copy(themeMode = value) }
        viewModelScope.launch { settings.setThemeMode(value) }
    }

    fun setAngleMode(value: AngleMode) {
        _uiState.update { it.copy(angleMode = value) }
        viewModelScope.launch { settings.setAngleMode(value) }
    }

    fun setDecimalPrecision(value: DecimalPrecision) {
        _uiState.update { it.copy(decimalPrecision = value) }
        viewModelScope.launch { settings.setDecimalPrecision(value) }
    }

    fun setDefaultCurrency(value: String) {
        _uiState.update { it.copy(defaultCurrency = value) }
        viewModelScope.launch { settings.setDefaultCurrency(value) }
    }

    fun setAutoUpdateRates(value: Boolean) {
        _uiState.update { it.copy(autoUpdateRates = value) }
        viewModelScope.launch { settings.setAutoUpdateRates(value) }
    }
}

data class ThemeUiState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM_DEFAULT,
    val angleMode: AngleMode = AngleMode.DEGREES,
    val decimalPrecision: DecimalPrecision = DecimalPrecision.AUTO,
    val defaultCurrency: String = "USD",
    val autoUpdateRates: Boolean = true,
)
