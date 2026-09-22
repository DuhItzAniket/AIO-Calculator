package com.aio.calculator.core.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aio.calculator.core.common.ToolCategory
import kotlinx.coroutines.launch

/**
 * Application-level drawer and route host.
 *
 * The callbacks keep navigation independent from feature implementations. This
 * allows each screen to be implemented and verified incrementally without
 * coupling the core navigation module to calculator business logic.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerNavigation(
    navController: NavHostController,
    onNavigateToCategory: (ToolCategory) -> Unit,
    onNavigateToTool: (String) -> Unit,
    onNavigateToFavorites: () -> Unit,
    onNavigateToRecent: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToSaved: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToFormulaLibrary: () -> Unit,
    onNavigateToConstantsLibrary: () -> Unit,
    onNavigateToShopping: () -> Unit,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    fun closeDrawer(action: () -> Unit) {
        scope.launch {
            drawerState.close()
            action()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("AIO Calculator", modifier = Modifier.padding(20.dp))
                NavigationDrawerItem(
                    label = { Text("Calculator") },
                    selected = false,
                    onClick = { closeDrawer { navController.navigate(NavRoutes.CALCULATOR) } },
                )
                ToolCategory.allCategories().forEach { category ->
                    NavigationDrawerItem(
                        label = { Text(category.displayName) },
                        selected = false,
                        onClick = { closeDrawer { onNavigateToCategory(category) } },
                    )
                }
                NavigationDrawerItem(
                    label = { Text("Favorites") },
                    selected = false,
                    onClick = { closeDrawer(onNavigateToFavorites) },
                )
                NavigationDrawerItem(
                    label = { Text("Recent") },
                    selected = false,
                    onClick = { closeDrawer(onNavigateToRecent) },
                )
                NavigationDrawerItem(
                    label = { Text("History") },
                    selected = false,
                    onClick = { closeDrawer(onNavigateToHistory) },
                )
                NavigationDrawerItem(
                    label = { Text("Saved") },
                    selected = false,
                    onClick = { closeDrawer(onNavigateToSaved) },
                )
                NavigationDrawerItem(
                    label = { Text("Settings") },
                    selected = false,
                    onClick = { closeDrawer(onNavigateToSettings) },
                )
                NavigationDrawerItem(
                    label = { Text("About") },
                    selected = false,
                    onClick = { closeDrawer(onNavigateToAbout) },
                )
                NavigationDrawerItem(
                    label = { Text("Formula library") },
                    selected = false,
                    onClick = { closeDrawer(onNavigateToFormulaLibrary) },
                )
                NavigationDrawerItem(
                    label = { Text("Constants library") },
                    selected = false,
                    onClick = { closeDrawer(onNavigateToConstantsLibrary) },
                )
                NavigationDrawerItem(
                    label = { Text("Shopping") },
                    selected = false,
                    onClick = { closeDrawer(onNavigateToShopping) },
                )
            }
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = NavRoutes.CALCULATOR,
            modifier = Modifier.fillMaxSize(),
        ) {
            composable(NavRoutes.CALCULATOR) {
                DestinationScreen(
                    title = "Calculator",
                    onOpenDrawer = { scope.launch { drawerState.open() } },
                )
            }
            composable(NavRoutes.FAVORITES) {
                DestinationScreen("Favorites") { scope.launch { drawerState.open() } }
            }
            composable(NavRoutes.RECENT) {
                DestinationScreen("Recent") { scope.launch { drawerState.open() } }
            }
            composable(NavRoutes.HISTORY) {
                DestinationScreen("History") { scope.launch { drawerState.open() } }
            }
            composable(NavRoutes.SAVED) {
                DestinationScreen("Saved") { scope.launch { drawerState.open() } }
            }
            composable(NavRoutes.SETTINGS) {
                DestinationScreen("Settings") { scope.launch { drawerState.open() } }
            }
            composable(NavRoutes.ABOUT) {
                DestinationScreen("About") { scope.launch { drawerState.open() } }
            }
            composable(NavRoutes.FORMULA_LIBRARY) {
                DestinationScreen("Formula library") { scope.launch { drawerState.open() } }
            }
            composable(NavRoutes.CONSTANTS_LIBRARY) {
                DestinationScreen("Constants library") { scope.launch { drawerState.open() } }
            }
            composable(NavRoutes.SHOPPING) {
                DestinationScreen("Shopping") { scope.launch { drawerState.open() } }
            }
            composable(NavRoutes.CATEGORY) { entry ->
                DestinationScreen(
                    title = entry.arguments?.getString("category") ?: "Category",
                    onOpenDrawer = { scope.launch { drawerState.open() } },
                )
            }
            composable(NavRoutes.TOOL) { entry ->
                DestinationScreen(
                    title = entry.arguments?.getString("toolId") ?: "Tool",
                    onOpenDrawer = { scope.launch { drawerState.open() } },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DestinationScreen(
    title: String,
    onOpenDrawer: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onOpenDrawer) {
                        Text("☰")
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) { }
    }
}
