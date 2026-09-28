package com.aio.calculator.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aio.calculator.core.database.AioDatabase
import com.aio.calculator.core.database.entity.CalculationHistoryEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppHistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AioDatabase.getInstance(application)
    val history: StateFlow<List<CalculationHistoryEntity>> = database.calculationHistoryDao()
        .getRecentHistory()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun record(expression: String, result: String) {
        viewModelScope.launch {
            database.calculationHistoryDao().insert(
                CalculationHistoryEntity(
                    toolId = "basic_calculator",
                    inputData = expression,
                    result = result,
                    displayExpression = expression,
                )
            )
        }
    }

    fun clear() {
        viewModelScope.launch { database.calculationHistoryDao().clearAllHistory() }
    }
}
