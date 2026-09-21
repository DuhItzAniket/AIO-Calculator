package com.aio.calculator.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.startup.Initializer
import androidx.startup.StartupLogger
import com.aio.calculator.core.database.dao.CalculationHistoryDao
import com.aio.calculator.core.database.dao.FavoriteDao
import com.aio.calculator.core.database.dao.SavedCalculationDao
import com.aio.calculator.core.database.dao.ShoppingListDao
import com.aio.calculator.core.database.dao.ShoppingItemDao
import com.aio.calculator.core.database.entity.CalculationHistoryEntity
import com.aio.calculator.core.database.entity.FavoriteEntity
import com.aio.calculator.core.database.entity.SavedCalculationEntity
import com.aio.calculator.core.database.entity.ShoppingListEntity
import com.aio.calculator.core.database.entity.ShoppingItemEntity

@Database(
    entities = [
        CalculationHistoryEntity::class,
        FavoriteEntity::class,
        SavedCalculationEntity::class,
        ShoppingListEntity::class,
        ShoppingItemEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AioDatabase : RoomDatabase() {
    abstract fun calculationHistoryDao(): CalculationHistoryDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun savedCalculationDao(): SavedCalculationDao
    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun shoppingItemDao(): ShoppingItemDao

    companion object {
        @Volatile
        private var INSTANCE: AioDatabase? = null

        fun getInstance(context: Context): AioDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AioDatabase::class.java,
                    "aio_calculator.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}

class AioDatabaseInitializer : Initializer<AioDatabase> {
    override fun create(context: Context): AioDatabase {
        StartupLogger.log("Initializing AioDatabase")
        return AioDatabase.getInstance(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}