package com.aio.calculator.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aio.calculator.core.common.ThemeMode
import com.aio.calculator.core.datastore.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ThemeViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ThemeUiState())
    val uiState: StateFlow<ThemeUiState> = _uiState

    init {
        loadSettings()
    }

    private fun loadSettings() {
        viewModelScope.launch {
            val themeMode = settingsRepository.getThemeMode()
            val angleMode = settingsRepository.getAngleMode()
            val precision = settingsRepository.getDecimalPrecision()
            val currency = settingsRepository.getDefaultCurrency()
            val autoUpdate = settingsRepository.getAutoUpdateRates()

            _uiState.update {
                it.copy(
                    themeMode = themeMode,
                    angleMode = angleMode,
                    decimalPrecision = precision,
                    defaultCurrency = currency,
                    autoUpdateRates = autoUpdate
                )
            }
        }
    }

    fun setThemeMode(themeMode: ThemeMode) {
        viewModelScope.launch {
            settingsRepository.setThemeMode(themeMode)
            _uiState.update { it.copy(themeMode = themeMode) }
        }
    }

    fun setAngleMode(angleMode: AngleMode) {
        viewModelScope.launch {
            settingsRepository.setAngleMode(angleMode)
            _uiState.update { it.copy(angleMode = angleMode) }
        }
    }

    fun setDecimalPrecision(precision: com.aio.calculator.core.common.DecimalPrecision) {
        viewModelScope.launch {
            settingsRepository.setDecimalPrecision(precision)
            _uiState.update { it.copy(decimalPrecision = precision) }
        }
    }

    fun setDefaultCurrency(currency: String) {
        viewModelScope.launch {
            settingsRepository.setDefaultCurrency(currency)
            _uiState.update { it.copy(defaultCurrency = currency) }
        }
    }

    fun setAutoUpdateRates(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setAutoUpdateRates(enabled)
            _uiState.update { it.copy(autoUpdateRates = enabled) }
        }
    }
}

data class ThemeUiState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM_DEFAULT,
    val angleMode: AngleMode = AngleMode.DEGREES,
    val decimalPrecision: com.aio.calculator.core.common.DecimalPrecision = com.aio.calculator.core.common.DecimalPrecision.AUTO,
    val defaultCurrency: String = "USD",
    val autoUpdateRates: Boolean = true
)