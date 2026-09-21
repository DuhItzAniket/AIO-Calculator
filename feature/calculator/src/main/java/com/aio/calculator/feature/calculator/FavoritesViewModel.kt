package com.aio.calculator.feature.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aio.calculator.core.common.ToolDefinition
import com.aio.calculator.core.common.ToolRegistry
import com.aio.calculator.core.database.AioDatabase
import com.aio.calculator.core.database.dao.FavoriteDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val database: AioDatabase,
    private val toolRegistry: ToolRegistry
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<ToolDefinition>>(emptyList())
    val favorites: StateFlow<List<ToolDefinition>> = _favorites

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            _isLoading.value = true
            database.favoriteDao().getFavoriteIds()
                .map { favoriteIds ->
                    toolRegistry.getFavorites(favoriteIds.toSet())
                }
                .collect { tools ->
                    _favorites.value = tools
                    _isLoading.value = false
                }
        }
    }

    fun toggleFavorite(toolId: String) {
        viewModelScope.launch {
            val isCurrentlyFavorite = database.favoriteDao().isFavorite(toolId).firstOrNull() != null
            if (isCurrentlyFavorite) {
                database.favoriteDao().remove(toolId)
            } else {
                val entity = com.aio.calculator.core.database.entity.FavoriteEntity(toolId = toolId)
                database.favoriteDao().insert(entity)
            }
            loadFavorites()
        }
    }

    fun isFavorite(toolId: String): Boolean {
        return _favorites.value.any { it.id == toolId }
    }
}