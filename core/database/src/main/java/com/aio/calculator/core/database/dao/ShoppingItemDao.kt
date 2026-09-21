package com.aio.calculator.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aio.calculator.core.database.entity.ShoppingItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ShoppingItemEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ShoppingItemEntity>)

    @Query("UPDATE shopping_items SET name = :name, price = :price, quantity = :quantity, included = :included, category = :category, note = :note, sortOrder = :sortOrder WHERE id = :id")
    suspend fun update(id: Long, name: String, price: Double, quantity: Int, included: Boolean, category: String?, note: String?, sortOrder: Int)

    @Query("DELETE FROM shopping_items WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM shopping_items WHERE listId = :listId")
    suspend fun deleteAllForList(listId: Long)

    @Query("SELECT * FROM shopping_items WHERE listId = :listId ORDER BY sortOrder ASC")
    fun getItemsForList(listId: Long): Flow<List<ShoppingItemEntity>>
}