package com.aio.calculator.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aio.calculator.core.database.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favorite: FavoriteEntity): Long

    @Query("DELETE FROM favorites WHERE toolId = :toolId")
    suspend fun remove(toolId: String)

    @Query("SELECT * FROM favorites ORDER BY addedTimestamp DESC")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorites WHERE toolId = :toolId")
    fun isFavorite(toolId: String): Flow<FavoriteEntity?>

    @Query("SELECT toolId FROM favorites")
    fun getFavoriteIds(): Flow<List<String>>
}