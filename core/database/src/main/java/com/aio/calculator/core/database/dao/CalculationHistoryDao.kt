package com.aio.calculator.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aio.calculator.core.database.entity.CalculationHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CalculationHistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(history: CalculationHistoryEntity): Long

    @Query("SELECT * FROM calculation_history ORDER BY timestamp DESC LIMIT 100")
    fun getRecentHistory(): Flow<List<CalculationHistoryEntity>>

    @Query("SELECT * FROM calculation_history WHERE toolId = :toolId ORDER BY timestamp DESC LIMIT 50")
    fun getHistoryForTool(toolId: String): Flow<List<CalculationHistoryEntity>>

    @Query("DELETE FROM calculation_history WHERE timestamp < :cutoff")
    suspend fun deleteOldHistory(cutoff: Long)

    @Query("DELETE FROM calculation_history")
    suspend fun clearAllHistory()
}