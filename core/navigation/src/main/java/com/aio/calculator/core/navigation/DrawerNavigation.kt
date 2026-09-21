package com.aio.calculator.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.ListItemHeadline
import androidx.compose.material3.ListItemLeadingIcon
import androidx.compose.material3.ListItemTrailingIcon
import androidx.compose.material3.ListItemTwoLine
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aio.calculator.core.common.ToolCategory
import com.aio.calculator.core.design.AioColors
import com.aio.calculator.core.design.AioTheme
import com.aio.calculator.core.design.AioTypography
import com.aio.calculator.core.design.CalculatorColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AioNavigationDrawer(
    navController: NavController,
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
    onNavigateToShopping: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onNavigateToCategory = onNavigateToCategory,
                onNavigateToTool = onNavigateToTool,
                onNavigateToFavorites = onNavigateToFavorites,
                onNavigateToRecent = onNavigateToRecent,
                onNavigateToHistory = onNavigateToHistory,
                onNavigateToSaved = onNavigateToSaved,
                onNavigateToSettings = onNavigateToSettings,
                onNavigateToAbout = onNavigateToAbout,
                onNavigateToFormulaLibrary = onNavigateToFormulaLibrary,
                onNavigateToConstantsLibrary = onNavigateToConstantsLibrary,
                onNavigateToShopping = onNavigateToShopping,
                onDrawerClose = { scope.launch { drawerState.close() } }
            )
        },
        content = {
            NavHost(navController, startDestination = NavRoutes.CALCULATOR) {
                composable(NavRoutes.CALCULATOR) {
                    CalculatorScreenContent(onMenuClick = { scope.launch { drawerState.open() } })
                }
                composable(NavRoutes.CALCULATOR_SCIENTIFIC) {
                    ScientificCalculatorScreenContent(onMenuClick = { scope.launch { drawerState.open() } })
                }
                // Other destinations would be added here
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(
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
    onDrawerClose: () -> Unit
) {
    val calcColors = CalculatorColors()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(calcColors.displayBackground)
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "AIO Calculator",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = calcColors.displayText
            )
            Text(
                text = "All-in-One Calculator",
                fontSize = 14.sp,
                color = calcColors.displayText.copy(alpha = 0.7f)
            )
        }

        androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(8.dp))

        // Calculator section
        DrawerSection(
            title = "Calculator",
            onDrawerClose = onDrawerClose
        ) {
            NavigationDrawerItem(
                label = { Text(text = "Basic Calculator") },
                leadingIcon = { Icon(Icons.Default.Calculate, contentDescription = null) },
                onClick = {
                    onDrawerClose()
                    onNavigateToTool("basic_calculator")
                }
            )
            NavigationDrawerItem(
                label = { Text(text = "Scientific Calculator") },
                leadingIcon = { Icon(Icons.Default.Functions, contentDescription = null) },
                onClick = {
                    onDrawerClose()
                    onNavigateToTool("scientific_calculator")
                }
            )
        }

        // Categories section
        DrawerSection(
            title = "Categories",
            onDrawerClose = onDrawerClose
        ) {
            ToolCategory.allCategories().forEach { category ->
                NavigationDrawerItem(
                    label = { Text(text = category.displayName) },
                    leadingIcon = {
                        // Would load vector drawable here
                        Icon(Icons.Default.Category, contentDescription = null)
                    },
                    onClick = {
                        onDrawerClose()
                        onNavigateToCategory(category)
                    }
                )
            }
        }

        // Utility section
        DrawerSection(
            title = "Utility",
            onDrawerClose = onDrawerClose
        ) {
            NavigationDrawerItem(
                label = { Text(text = "Favorites") },
                leadingIcon = { Icon(Favorite, contentDescription = null) },
                onClick = { onDrawerClose(); onNavigateToFavorites() }
            )
            NavigationDrawerItem(
                label = { Text(text = "Recent") },
                leadingIcon = { Icon(Icons.Default.History, contentDescription = null) },
                onClick = { onDrawerClose(); onNavigateToRecent() }
            )
            NavigationDrawerItem(
                label = { Text(text = "History") },
                leadingIcon = { Icon(History, contentDescription = null) },
                onClick = { onDrawerClose(); onNavigateToHistory() }
            )
            NavigationDrawerItem(
                label = { Text(text = "Saved Calculations") },
                leadingIcon = { Icon(Icons.Default.Bookmark, contentDescription = null) },
                onClick = { onDrawerClose(); onNavigateToSaved() }
            )
            NavigationDrawerItem(
                label = { Text(text = "Formula Library") },
                leadingIcon = { Icon(Icons.Default.MenuBook, contentDescription = null) },
                onClick = { onDrawerClose(); onNavigateToFormulaLibrary() }
            )
            NavigationDrawerItem(
                label = { Text(text = "Constants Library") },
                leadingIcon = { Icon(Icons.Default.Science, contentDescription = null) },
                onClick = { onDrawerClose(); onNavigateToConstantsLibrary() }
            )
            NavigationDrawerItem(
                label = { Text(text = "Shopping") },
                leadingIcon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
                onClick = { onDrawerClose(); onNavigateToShopping() }
            )
        }

        // Settings section
        DrawerSection(
            title = "Settings",
            onDrawerClose = onDrawerClose
        ) {
            NavigationDrawerItem(
                label = { Text(text = "Settings") },
                leadingIcon = { Icon(Settings, contentDescription = null) },
                onClick = { onDrawerClose(); onNavigateToSettings() }
            )
            NavigationDrawerItem(
                label = { Text(text = "About") },
                leadingIcon = { Icon(Icons.Default.Info, contentDescription = null) },
                onClick = { onDrawerClose(); onNavigateToAbout() }
            )
        }
    }
}

@Composable
fun DrawerSection(
    title: String,
    onDrawerClose: () -> Unit,
    content: @Composable () -> Unit
) {
    val calcColors = CalculatorColors()
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title.uppercase(),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = calcColors.displayText.copy(alpha = 0.5f),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        content()
    }
}

@Composable
fun CalculatorScreenContent(onMenuClick: () -> Unit) {
    val calcColors = CalculatorColors()
    TopAppBar(
        title = { Text(text = "Calculator") },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(Icons.Default.Menu, contentDescription = "Open menu")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = calcColors.displayBackground,
            titleContentColor = calcColors.displayText
        )
    ) {
        // Calculator content would go here
    }
}

@Composable
fun ScientificCalculatorScreenContent(onMenuClick: () -> Unit) {
    val calcColors = CalculatorColors()
    TopAppBar(
        title = { Text(text = "Scientific Calculator") },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(Icons.Default.Menu, contentDescription = "Open menu")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = calcColors.displayBackground,
            titleContentColor = calcColors.displayText
        )
    ) {
        // Scientific calculator content would go here
    }
}