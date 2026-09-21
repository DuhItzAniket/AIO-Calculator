package com.aio.calculator.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "favorites")
@Serializable
data class FavoriteEntity(
    @PrimaryKey val toolId: String,
    val addedTimestamp: Long = System.currentTimeMillis()
)