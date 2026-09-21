package com.aio.calculator.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aio.calculator.core.database.entity.ShoppingListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list: ShoppingListEntity): Long

    @Query("UPDATE shopping_lists SET name = :name, discount = :discount, taxRate = :taxRate, budget = :budget, updatedTimestamp = :updatedTimestamp WHERE id = :id")
    suspend fun update(id: Long, name: String, discount: Double, taxRate: Double, budget: Double, updatedTimestamp: Long)

    @Query("DELETE FROM shopping_lists WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM shopping_lists ORDER BY updatedTimestamp DESC")
    fun getAllLists(): Flow<List<ShoppingListEntity>>

    @Query("SELECT * FROM shopping_lists WHERE id = :id")
    suspend fun getById(id: Long): ShoppingListEntity?
}