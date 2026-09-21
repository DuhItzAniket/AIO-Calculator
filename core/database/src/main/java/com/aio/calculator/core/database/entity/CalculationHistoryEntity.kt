package com.aio.calculator.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "calculation_history")
@Serializable
data class CalculationHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val toolId: String,
    val inputData: String, // JSON
    val result: String,
    val displayExpression: String
)