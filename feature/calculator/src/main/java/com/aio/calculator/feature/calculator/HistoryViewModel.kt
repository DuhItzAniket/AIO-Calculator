package com.aio.calculator.feature.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aio.calculator.core.common.HistoryItem
import com.aio.calculator.core.common.Result
import com.aio.calculator.core.common.ToolRegistry
import com.aio.calculator.core.database.AioDatabase
import com.aio.calculator.core.database.dao.CalculationHistoryDao
import com.aio.calculator.core.database.entity.CalculationHistoryEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap

class HistoryViewModel(
    private val database: AioDatabase,
    private val toolRegistry: ToolRegistry
) : ViewModel() {

    private val _history = MutableStateFlow<List<HistoryItem>>(emptyList())
    val history: StateFlow<List<HistoryItem>> = _history

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadHistory()
    }

    private fun loadHistory() {
        viewModelScope.launch {
            _isLoading.value = true
            database.calculationHistoryDao().getRecentHistory()
                .map { entities ->
                    entities.map { entity ->
                        val tool = toolRegistry.getTool(entity.toolId)
                        HistoryItem(
                            id = entity.id,
                            timestamp = entity.timestamp,
                            toolId = entity.toolId,
                            toolTitle = tool?.title ?: entity.toolId,
                            toolIcon = tool?.iconName ?: "ic_category_everyday",
                            inputData = entity.inputData,
                            result = entity.result,
                            displayExpression = entity.displayExpression
                        )
                    }
                }
                .collect { items ->
                    _history.value = items
                    _isLoading.value = false
                }
        }
    }

    fun addToHistory(
        toolId: String,
        inputData: String,
        result: String,
        displayExpression: String
    ) {
        viewModelScope.launch {
            val entity = CalculationHistoryEntity(
                toolId = toolId,
                inputData = inputData,
                result = result,
                displayExpression = displayExpression
            )
            database.calculationHistoryDao().insert(entity)
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            _isLoading.value = true
            database.calculationHistoryDao().clearAllHistory()
            _history.value = emptyList()
            _isLoading.value = false
        }
    }

    fun deleteHistoryItem(id: Long) {
        viewModelScope.launch {
            database.calculationHistoryDao().deleteOldHistory(id)
            _history.value = _history.value.filter { it.id != id }
        }
    }

    fun restoreCalculation(item: HistoryItem): Result<String> {
        // Return the input data for restoration
        return Result.success(item.inputData)
    }
}
