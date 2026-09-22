package com.aio.calculator.feature.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aio.calculator.core.common.Result
import com.aio.calculator.core.database.AioDatabase
import com.aio.calculator.core.database.dao.SavedCalculationDao
import com.aio.calculator.core.database.entity.SavedCalculationEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SavedCalculationsViewModel(
    private val database: AioDatabase
) : ViewModel() {

    private val _savedCalculations = MutableStateFlow<List<SavedCalculationEntity>>(emptyList())
    val savedCalculations: StateFlow<List<SavedCalculationEntity>> = _savedCalculations

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadSavedCalculations()
    }

    private fun loadSavedCalculations() {
        viewModelScope.launch {
            _isLoading.value = true
            database.savedCalculationDao().getAllSaved()
                .collect { items ->
                    _savedCalculations.value = items
                    _isLoading.value = false
                }
        }
    }

    fun saveCalculation(
        name: String,
        toolId: String,
        inputData: String,
        result: String,
        displayExpression: String
    ): Result<Long> {
        val entity = SavedCalculationEntity(
            name = name,
            toolId = toolId,
            inputData = inputData,
            result = result,
            displayExpression = displayExpression
        )
        viewModelScope.launch {
            database.savedCalculationDao().insert(entity)
        }
        return Result.success(0L)
    }

    fun updateSavedCalculation(
        id: Long,
        name: String,
        inputData: String,
        result: String,
        displayExpression: String
    ): Result<Unit> {
        viewModelScope.launch {
            database.savedCalculationDao().update(
                id, name, inputData, result, displayExpression, System.currentTimeMillis()
            )
        }
        return Result.success(Unit)
    }

    fun deleteSavedCalculation(id: Long): Result<Unit> {
        viewModelScope.launch {
            database.savedCalculationDao().delete(id)
        }
        return Result.success(Unit)
    }

    fun duplicateSavedCalculation(id: Long): Result<Long> {
        viewModelScope.launch {
            val original = database.savedCalculationDao().getById(id)
            if (original != null) {
                database.savedCalculationDao().insert(
                    original.copy(
                        id = 0,
                        name = "${original.name} (Copy)",
                        createdTimestamp = System.currentTimeMillis(),
                        updatedTimestamp = System.currentTimeMillis(),
                    )
                )
            }
        }
        return Result.success(0L)
    }
}
