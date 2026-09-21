package com.aio.calculator.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aio.calculator.core.database.entity.SavedCalculationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedCalculationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(saved: SavedCalculationEntity): Long

    @Query("UPDATE saved_calculations SET name = :name, inputData = :inputData, result = :result, displayExpression = :displayExpression, updatedTimestamp = :updatedTimestamp WHERE id = :id")
    suspend fun update(id: Long, name: String, inputData: String, result: String, displayExpression: String, updatedTimestamp: Long)

    @Query("DELETE FROM saved_calculations WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM saved_calculations ORDER BY updatedTimestamp DESC")
    fun getAllSaved(): Flow<List<SavedCalculationEntity>>

    @Query("SELECT * FROM saved_calculations WHERE id = :id")
    suspend fun getById(id: Long): SavedCalculationEntity?
}