package com.aio.calculator.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aio.calculator.core.database.entity.CachedCurrencyRateEntity

@Dao
interface CurrencyRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(rate: CachedCurrencyRateEntity)

    @Query("SELECT * FROM currency_rates WHERE baseCurrency = :base AND quoteCurrency = :quote LIMIT 1")
    suspend fun get(base: String, quote: String): CachedCurrencyRateEntity?

    @Query("SELECT MAX(fetchedTimestamp) FROM currency_rates")
    suspend fun latestTimestamp(): Long?
}
