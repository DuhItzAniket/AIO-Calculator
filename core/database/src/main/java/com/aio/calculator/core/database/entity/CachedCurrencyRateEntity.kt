package com.aio.calculator.core.database.entity

import androidx.room.Entity

@Entity(tableName = "currency_rates", primaryKeys = ["baseCurrency", "quoteCurrency"])
data class CachedCurrencyRateEntity(
    val baseCurrency: String,
    val quoteCurrency: String,
    val rate: Double,
    val fetchedTimestamp: Long = System.currentTimeMillis(),
    val provider: String = "offline-reference",
)
