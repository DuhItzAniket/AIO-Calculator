package com.aio.calculator.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.aio.calculator.core.common.AngleMode
import com.aio.calculator.core.common.DecimalPrecision
import com.aio.calculator.core.common.ThemeMode
import kotlinx.coroutines.flow.first

private val Context.settingsDataStore by preferencesDataStore(name = "settings_preferences")

/** Local settings repository backed by Preferences DataStore. */
class SettingsRepository(private val context: Context) {
    private object Keys {
        val theme = stringPreferencesKey("theme")
        val angle = stringPreferencesKey("angle")
        val precision = intPreferencesKey("precision")
        val currency = stringPreferencesKey("currency")
        val autoUpdate = booleanPreferencesKey("auto_update_rates")
        val recentTools = stringPreferencesKey("recent_tools")
    }

    suspend fun getThemeMode(): ThemeMode = context.settingsDataStore.data.first()[Keys.theme]
        ?.let { runCatching { ThemeMode.valueOf(it) }.getOrNull() } ?: ThemeMode.SYSTEM_DEFAULT

    suspend fun setThemeMode(value: ThemeMode) { context.settingsDataStore.edit { it[Keys.theme] = value.name } }

    suspend fun getAngleMode(): AngleMode = context.settingsDataStore.data.first()[Keys.angle]
        ?.let { runCatching { AngleMode.valueOf(it) }.getOrNull() } ?: AngleMode.DEGREES

    suspend fun setAngleMode(value: AngleMode) { context.settingsDataStore.edit { it[Keys.angle] = value.name } }

    suspend fun getDecimalPrecision(): DecimalPrecision = context.settingsDataStore.data.first()[Keys.precision]
        ?.let { DecimalPrecision.values().getOrNull(it) } ?: DecimalPrecision.AUTO

    suspend fun setDecimalPrecision(value: DecimalPrecision) { context.settingsDataStore.edit { it[Keys.precision] = value.ordinal } }

    suspend fun getDefaultCurrency(): String = context.settingsDataStore.data.first()[Keys.currency] ?: "USD"
    suspend fun setDefaultCurrency(value: String) { context.settingsDataStore.edit { it[Keys.currency] = value } }

    suspend fun getAutoUpdateRates(): Boolean = context.settingsDataStore.data.first()[Keys.autoUpdate] ?: true
    suspend fun setAutoUpdateRates(value: Boolean) { context.settingsDataStore.edit { it[Keys.autoUpdate] = value } }

    suspend fun getRecentTools(): List<String> = context.settingsDataStore.data.first()[Keys.recentTools]
        ?.split('|')?.filter(String::isNotBlank) ?: emptyList()

    suspend fun setRecentTools(value: List<String>) {
        context.settingsDataStore.edit { it[Keys.recentTools] = value.joinToString("|") }
    }
}
