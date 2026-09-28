package com.aio.calculator.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aio.calculator.core.common.ToolDefinition
import com.aio.calculator.core.common.ToolRegistry
import com.aio.calculator.core.database.AioDatabase
import com.aio.calculator.core.database.entity.CalculationHistoryEntity
import com.aio.calculator.core.database.entity.SavedCalculationEntity
import com.aio.calculator.core.database.entity.FavoriteEntity
import com.aio.calculator.core.datastore.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppFavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AioDatabase.getInstance(application)
    val favorites: StateFlow<List<ToolDefinition>> = database.favoriteDao().getFavoriteIds()
        .map { ToolRegistry.getFavorites(it.toSet()) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun toggle(toolId: String) {
        viewModelScope.launch {
            val dao = database.favoriteDao()
            if (dao.isFavorite(toolId).first() == null) dao.insert(FavoriteEntity(toolId)) else dao.remove(toolId)
        }
    }
}

class AppSavedViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AioDatabase.getInstance(application)
    val saved: StateFlow<List<SavedCalculationEntity>> = database.savedCalculationDao().getAllSaved()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun save(item: CalculationHistoryEntity) {
        viewModelScope.launch {
            database.savedCalculationDao().insert(
                SavedCalculationEntity(
                    name = item.displayExpression,
                    toolId = item.toolId,
                    inputData = item.inputData,
                    result = item.result,
                    displayExpression = item.displayExpression,
                )
            )
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch { database.savedCalculationDao().delete(id) }
    }
}

class AppRecentViewModel(application: Application) : AndroidViewModel(application) {
    private val settings = SettingsRepository(application)
    private val _recent = MutableStateFlow<List<ToolDefinition>>(emptyList())
    val recent: StateFlow<List<ToolDefinition>> = _recent

    init { reload() }

    fun add(toolId: String) {
        viewModelScope.launch {
            val ids = settings.getRecentTools().toMutableList().apply {
                remove(toolId)
                add(0, toolId)
            }.take(10)
            settings.setRecentTools(ids)
            reload()
        }
    }

    private fun reload() {
        viewModelScope.launch { _recent.value = ToolRegistry.getRecent(settings.getRecentTools()) }
    }
}
