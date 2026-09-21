package com.aio.calculator.feature.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aio.calculator.core.common.ToolDefinition
import com.aio.calculator.core.common.ToolRegistry
import com.aio.calculator.core.datastore.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecentViewModel(
    private val toolRegistry: ToolRegistry,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _recent = MutableStateFlow<List<ToolDefinition>>(emptyList())
    val recent: StateFlow<List<ToolDefinition>> = _recent

    private val MAX_RECENT = 10

    init {
        loadRecent()
    }

    private fun loadRecent() {
        viewModelScope.launch {
            val recentIds = settingsRepository.getRecentTools()
            _recent.value = toolRegistry.getRecent(recentIds)
        }
    }

    fun addRecent(toolId: String) {
        viewModelScope.launch {
            var recentIds = settingsRepository.getRecentTools().toMutableList()
            // Remove if already exists
            recentIds.remove(toolId)
            // Add to front
            recentIds.add(0, toolId)
            // Limit size
            if (recentIds.size > MAX_RECENT) {
                recentIds = recentIds.take(MAX_RECENT)
            }
            settingsRepository.setRecentTools(recentIds)
            loadRecent()
        }
    }

    fun clearRecent() {
        viewModelScope.launch {
            settingsRepository.setRecentTools(emptyList())
            _recent.value = emptyList()
        }
    }
}