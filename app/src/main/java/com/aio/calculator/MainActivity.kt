package com.aio.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aio.calculator.core.design.AioTheme
import com.aio.calculator.core.navigation.NavRoutes
import com.aio.calculator.feature.calculator.BasicCalculatorScreen
import com.aio.calculator.feature.calculator.ScientificCalculatorScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AioCalculatorApp() }
    }
}

@Composable
private fun AioCalculatorApp() {
    val navController = rememberNavController()
    AioTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
            NavHost(navController = navController, startDestination = NavRoutes.CALCULATOR) {
                composable(NavRoutes.CALCULATOR) {
                    BasicCalculatorScreen(
                        onNavigateToScientific = { navController.navigate(NavRoutes.toolRoute("scientific")) },
                        onOpenDrawer = { },
                        onOpenHistory = { },
                    )
                }
                composable(NavRoutes.TOOL) {
                    ScientificCalculatorScreen(
                        onNavigateToBasic = { navController.popBackStack() },
                        onOpenDrawer = { },
                        onOpenHistory = { },
                    )
                }
            }
        }
    }
}
