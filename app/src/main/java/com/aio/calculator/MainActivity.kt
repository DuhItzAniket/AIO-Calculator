package com.aio.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aio.calculator.core.common.AngleMode
import com.aio.calculator.core.common.ThemeMode
import com.aio.calculator.core.design.AioTheme
import com.aio.calculator.core.design.CalculatorColors
import com.aio.calculator.core.navigation.NavRoutes
import com.aio.calculator.core.navigation.DrawerNavigation
import com.aio.calculator.feature.calculator.BasicCalculatorScreen
import com.aio.calculator.feature.calculator.ScientificCalculatorScreen
import com.aio.calculator.ui.theme.ThemeViewModel

class MainActivity : ComponentActivity() {
    private val themeViewModel: ThemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            val uiState by themeViewModel.uiState.collectAsStateWithLifecycle()

            AioTheme(
                themeMode = uiState.themeMode,
                dynamicColorAvailable = false // Will be implemented later
            ) {
                Surface(
                    modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                    color = androidx.compose.material3.MaterialTheme.colorScheme.surface
                ) {
                    DrawerNavigation(
                        navController = navController,
                        onNavigateToCategory = { category ->
                            navController.navigate(NavRoutes.categoryRoute(category.name))
                        },
                        onNavigateToTool = { toolId ->
                            navController.navigate(NavRoutes.toolRoute(toolId))
                        },
                        onNavigateToFavorites = { navController.navigate(NavRoutes.FAVORITES) },
                        onNavigateToRecent = { navController.navigate(NavRoutes.RECENT) },
                        onNavigateToHistory = { navController.navigate(NavRoutes.HISTORY) },
                        onNavigateToSaved = { navController.navigate(NavRoutes.SAVED) },
                        onNavigateToSettings = { navController.navigate(NavRoutes.SETTINGS) },
                        onNavigateToAbout = { navController.navigate(NavRoutes.ABOUT) },
                        onNavigateToFormulaLibrary = { navController.navigate(NavRoutes.FORMULA_LIBRARY) },
                        onNavigateToConstantsLibrary = { navController.navigate(NavRoutes.CONSTANTS_LIBRARY) },
                        onNavigateToShopping = { navController.navigate(NavRoutes.SHOPPING) }
                    )
                }
            }
        }
    }
}