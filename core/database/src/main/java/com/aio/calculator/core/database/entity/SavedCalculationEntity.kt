package com.aio.calculator.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_calculations")
data class SavedCalculationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val toolId: String,
    val inputData: String, // JSON
    val result: String,
    val displayExpression: String,
    val createdTimestamp: Long = System.currentTimeMillis(),
    val updatedTimestamp: Long = System.currentTimeMillis()
)
