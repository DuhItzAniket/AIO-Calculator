package com.aio.calculator.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument

/**
 * Navigation routes for the app
 */
object NavRoutes {
    const val CALCULATOR = "calculator"
    const val CALCULATOR_SCIENTIFIC = "calculator/scientific"
    const val CATEGORY = "category/${NavArgs.CATEGORY}"
    const val TOOL = "tool/${NavArgs.TOOL_ID}"
    const val FAVORITES = "favorites"
    const val RECENT = "recent"
    const val HISTORY = "history"
    const val SAVED = "saved"
    const val SETTINGS = "settings"
    const val ABOUT = "about"
    const val FORMULA_LIBRARY = "formula_library"
    const val CONSTANTS_LIBRARY = "constants_library"
    const val SHOPPING = "shopping"
    const val SHOPPING_LIST = "shopping/list/${NavArgs.LIST_ID}"

    object NavArgs {
        const val CATEGORY = "category"
        const val TOOL_ID = "toolId"
        const val LIST_ID = "listId"
    }

    fun categoryRoute(category: String) = "category/$category"
    fun toolRoute(toolId: String) = "tool/$toolId"
    fun shoppingListRoute(listId: String) = "shopping/list/$listId"
}

/**
 * Navigation graph builder
 */
@Composable
fun AioNavGraph(
    navGraphBuilder: NavGraphBuilder,
    startDestination: String = NavRoutes.CALCULATOR
) {
    navGraphBuilder.composable(startDestination) {
        // Calculator screen - will be implemented in feature module
    }

    navGraphBuilder.composable(NavRoutes.CALCULATOR_SCIENTIFIC) {
        // Scientific calculator screen
    }

    navGraphBuilder.composable(
        route = NavRoutes.CATEGORY,
        arguments = listOf(navArgument(NavRoutes.NavArgs.CATEGORY) { type = androidx.navigation.NavType.StringType })
    ) { backStackEntry ->
        val category = backStackEntry.getString() ?: ""
        // Category screen
    }

    navGraphBuilder.composable(
        route = NavRoutes.TOOL,
        arguments = listOf(navArgument(NavRoutes.NavArgs.TOOL_ID) { type = androidx.navigation.NavType.StringType })
    ) { backStackEntry ->
        val toolId = backStackEntry.getString() ?: ""
        // Tool screen
    }

    navGraphBuilder.composable(NavRoutes.FAVORITES) {
        // Favorites screen
    }

    navGraphBuilder.composable(NavRoutes.RECENT) {
        // Recent screen
    }

    navGraphBuilder.composable(NavRoutes.HISTORY) {
        // History screen
    }

    navGraphBuilder.composable(NavRoutes.SAVED) {
        // Saved calculations screen
    }

    navGraphBuilder.composable(NavRoutes.SETTINGS) {
        // Settings screen
    }

    navGraphBuilder.composable(NavRoutes.ABOUT) {
        // About screen
    }

    navGraphBuilder.composable(NavRoutes.FORMULA_LIBRARY) {
        // Formula library screen
    }

    navGraphBuilder.composable(NavRoutes.CONSTANTS_LIBRARY) {
        // Constants library screen
    }

    navGraphBuilder.composable(NavRoutes.SHOPPING) {
        // Shopping screen
    }

    navGraphBuilder.composable(
        route = NavRoutes.SHOPPING_LIST,
        arguments = listOf(navArgument(NavRoutes.NavArgs.LIST_ID) { type = androidx.navigation.NavType.StringType })
    ) { backStackEntry ->
        val listId = backStackEntry.getString() ?: ""
        // Shopping list screen
    }
}