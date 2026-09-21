package com.aio.calculator

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.startup.Initializer
import androidx.startup.StartupLogger
import com.aio.calculator.core.database.AioDatabaseInitializer

class AioApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize startup components
        StartupLogger.setLogLevel(StartupLogger.LogLevel.DEBUG)
    }
}