package com.aio.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aio.calculator.core.database.entity.CalculationHistoryEntity
import com.aio.calculator.core.database.entity.SavedCalculationEntity
import com.aio.calculator.core.common.ToolDefinition
import com.aio.calculator.core.design.AioTheme
import com.aio.calculator.core.navigation.NavRoutes
import com.aio.calculator.feature.calculator.BasicCalculatorScreen
import com.aio.calculator.feature.calculator.ScientificCalculatorScreen
import com.aio.calculator.history.AppHistoryViewModel
import com.aio.calculator.data.AppFavoritesViewModel
import com.aio.calculator.data.AppRecentViewModel
import com.aio.calculator.data.AppSavedViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val historyViewModel: AppHistoryViewModel by viewModels()
    private val favoritesViewModel: AppFavoritesViewModel by viewModels()
    private val savedViewModel: AppSavedViewModel by viewModels()
    private val recentViewModel: AppRecentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AioCalculatorApp(historyViewModel, favoritesViewModel, savedViewModel, recentViewModel) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AioCalculatorApp(
    historyViewModel: AppHistoryViewModel,
    favoritesViewModel: AppFavoritesViewModel,
    savedViewModel: AppSavedViewModel,
    recentViewModel: AppRecentViewModel,
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val history by historyViewModel.history.collectAsState()
    val favorites by favoritesViewModel.favorites.collectAsState()
    val saved by savedViewModel.saved.collectAsState()
    val recent by recentViewModel.recent.collectAsState()

    fun openDrawer() = scope.launch { drawerState.open() }
    fun navigate(route: String) {
        scope.launch {
            drawerState.close()
            navController.navigate(route)
        }
    }

    AioTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Text("AIO Calculator", modifier = Modifier.padding(20.dp))
                    NavigationDrawerItem(
                        label = { Text("Calculator") },
                        selected = false,
                        onClick = { navigate(NavRoutes.CALCULATOR) },
                    )
                    NavigationDrawerItem(
                        label = { Text("Scientific") },
                        selected = false,
                        onClick = { navigate(NavRoutes.toolRoute("scientific")) },
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    NavigationDrawerItem(
                        label = { Text("History") },
                        selected = false,
                        onClick = { navigate(NavRoutes.HISTORY) },
                    )
                    NavigationDrawerItem(
                        label = { Text("Favorites (${favorites.size})") },
                        selected = false,
                        onClick = { navigate(NavRoutes.FAVORITES) },
                    )
                    NavigationDrawerItem(
                        label = { Text("Recent (${recent.size})") },
                        selected = false,
                        onClick = { navigate(NavRoutes.RECENT) },
                    )
                    NavigationDrawerItem(
                        label = { Text("Saved (${saved.size})") },
                        selected = false,
                        onClick = { navigate(NavRoutes.SAVED) },
                    )
                }
            },
        ) {
            NavHost(navController = navController, startDestination = NavRoutes.CALCULATOR) {
                composable(NavRoutes.CALCULATOR) {
                    BasicCalculatorScreen(
                        onNavigateToScientific = { navigate(NavRoutes.toolRoute("scientific")) },
                        onOpenDrawer = { openDrawer() },
                        onOpenHistory = { navigate(NavRoutes.HISTORY) },
                        onCalculation = { expression, result ->
                            historyViewModel.record(expression, result)
                            recentViewModel.add("basic_calculator")
                        },
                    )
                }
                composable(NavRoutes.TOOL) {
                    ScientificCalculatorScreen(
                        onNavigateToBasic = { navController.popBackStack() },
                        onOpenDrawer = { openDrawer() },
                        onOpenHistory = { navigate(NavRoutes.HISTORY) },
                        onCalculation = { expression, result ->
                            historyViewModel.record(expression, result)
                            recentViewModel.add("scientific_calculator")
                        },
                    )
                }
                composable(NavRoutes.HISTORY) {
                    HistoryScreen(
                        items = history,
                        onOpenDrawer = { openDrawer() },
                        onClear = historyViewModel::clear,
                        onSave = savedViewModel::save,
                    )
                }
                composable(NavRoutes.FAVORITES) {
                    ToolListScreen("Favorites", favorites, { openDrawer() }, favoritesViewModel::toggle)
                }
                composable(NavRoutes.RECENT) {
                    ToolListScreen("Recent", recent, { openDrawer() }, recentViewModel::add)
                }
                composable(NavRoutes.SAVED) {
                    SavedScreen(saved, { openDrawer() }, savedViewModel::delete)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HistoryScreen(
    items: List<CalculationHistoryEntity>,
    onOpenDrawer: () -> Unit,
    onClear: () -> Unit,
    onSave: (CalculationHistoryEntity) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("History") },
                navigationIcon = { Button(onClick = onOpenDrawer) { Text("Menu") } },
                actions = { Button(onClick = onClear) { Text("Clear") } },
            )
        },
    ) { paddingValues ->
        if (items.isEmpty()) {
            Text("No calculations yet", modifier = Modifier.padding(paddingValues).padding(24.dp))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 16.dp)) {
                items(items, key = { it.id }) { item ->
                    Column(modifier = Modifier.padding(vertical = 12.dp)) {
                        Text(item.displayExpression)
                        Text("= ${item.result}")
                        Button(onClick = { onSave(item) }) { Text("Save") }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ToolListScreen(
    title: String,
    tools: List<ToolDefinition>,
    onOpenDrawer: () -> Unit,
    onToolAction: (String) -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(title) }, navigationIcon = { Button(onClick = onOpenDrawer) { Text("Menu") } })
    }) { paddingValues ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 16.dp)) {
            items(tools, key = { it.id }) { tool ->
                Button(onClick = { onToolAction(tool.id) }, modifier = Modifier.padding(vertical = 6.dp)) {
                    Text("${tool.title}: ${tool.description}")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SavedScreen(
    items: List<SavedCalculationEntity>,
    onOpenDrawer: () -> Unit,
    onDelete: (Long) -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Saved calculations") }, navigationIcon = { Button(onClick = onOpenDrawer) { Text("Menu") } })
    }) { paddingValues ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 16.dp)) {
            items(items, key = { it.id }) { item ->
                Column(modifier = Modifier.padding(vertical = 12.dp)) {
                    Text(item.name)
                    Text("= ${item.result}")
                    Button(onClick = { onDelete(item.id) }) { Text("Delete") }
                }
            }
        }
    }
}
