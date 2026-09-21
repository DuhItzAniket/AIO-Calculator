package com.aio.calculator.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferencesKeys
import androidx.datastore.preferences.core.mutations
import androidx.datastore.preferences.rxjava3.RxDataStore
import androidx.datastore.preferences.rxjava3.preferencesDataStore
import com.aio.calculator.core.common.AngleMode
import com.aio.calculator.core.common.DecimalPrecision
import com.aio.calculator.core.common.ThemeMode
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor(
    private val context: Context
) {
    private val dataStore = context.preferencesDataStore("settings_preferences")

    private val THEME_MODE = PreferencesKeys.stringKey("theme_mode")
    private val ANGLE_MODE = PreferencesKeys.stringKey("angle_mode")
    private val DECIMAL_PRECISION = PreferencesKeys.stringKey("decimal_precision")
    private val DEFAULT_CURRENCY = PreferencesKeys.stringKey("default_currency")
    private val AUTO_UPDATE_RATES = PreferencesKeys.booleanKey("auto_update_rates")
    private val RECENT_TOOLS = PreferencesKeys.stringSetKey("recent_tools")

    fun getThemeMode(): ThemeMode {
        return try {
            dataStore.data
                .map { preferences -> preferences[THEME_MODE] ?: ThemeMode.SYSTEM_DEFAULT.name }
                .firstOrDefault()
                .let { ThemeMode.valueOf(it) }
        } catch (e: Exception) {
            ThemeMode.SYSTEM_DEFAULT
        }
    }

    suspend fun setThemeMode(themeMode: ThemeMode) {
        dataStore.edit { preferences ->
            preferences[THEME_MODE] = themeMode.name
        }
    }

    fun getAngleMode(): AngleMode {
        return try {
            dataStore.data
                .map { preferences -> preferences[ANGLE_MODE] ?: AngleMode.DEGREES.name }
                .firstOrDefault()
                .let { AngleMode.valueOf(it) }
        } catch (e: Exception) {
            AngleMode.DEGREES
        }
    }

    suspend fun setAngleMode(angleMode: AngleMode) {
        dataStore.edit { preferences ->
            preferences[ANGLE_MODE] = angleMode.name
        }
    }

    fun getDecimalPrecision(): DecimalPrecision {
        return try {
            dataStore.data
                .map { preferences -> preferences[DECIMAL_PRECISION] ?: DecimalPrecision.AUTO.name }
                .firstOrDefault()
                .let { DecimalPrecision.valueOf(it) }
        } catch (e: Exception) {
            DecimalPrecision.AUTO
        }
    }

    suspend fun setDecimalPrecision(precision: DecimalPrecision) {
        dataStore.edit { preferences ->
            preferences[DECIMAL_PRECISION] = precision.name
        }
    }

    fun getDefaultCurrency(): String {
        return try {
            dataStore.data
                .map { preferences -> preferences[DEFAULT_CURRENCY] ?: "USD" }
                .firstOrDefault()
        } catch (e: Exception) {
            "USD"
        }
    }

    suspend fun setDefaultCurrency(currency: String) {
        dataStore.edit { preferences ->
            preferences[DEFAULT_CURRENCY] = currency
        }
    }

    fun getAutoUpdateRates(): Boolean {
        return try {
            dataStore.data
                .map { preferences -> preferences[AUTO_UPDATE_RATES] ?: true }
                .firstOrDefault()
        } catch (e: Exception) {
            true
        }
    }

    suspend fun setAutoUpdateRates(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[AUTO_UPDATE_RATES] = enabled
        }
    }

    fun getRecentTools(): List<String> {
        return try {
            dataStore.data
                .map { preferences -> preferences[RECENT_TOOLS] ?: emptySet() }
                .firstOrDefault()
                .toList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun setRecentTools(tools: List<String>) {
        dataStore.edit { preferences ->
            preferences[RECENT_TOOLS] = tools.toSet()
        }
    }
}