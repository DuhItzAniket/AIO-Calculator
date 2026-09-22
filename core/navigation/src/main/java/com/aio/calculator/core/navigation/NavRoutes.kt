package com.aio.calculator.core.navigation

/** Stable route names shared by the app shell and feature modules. */
object NavRoutes {
    const val CALCULATOR = "calculator"
    const val CATEGORY = "category/{category}"
    const val TOOL = "tool/{toolId}"
    const val FAVORITES = "favorites"
    const val RECENT = "recent"
    const val HISTORY = "history"
    const val SAVED = "saved"
    const val SETTINGS = "settings"
    const val ABOUT = "about"
    const val FORMULA_LIBRARY = "formula_library"
    const val CONSTANTS_LIBRARY = "constants_library"
    const val SHOPPING = "shopping"

    fun categoryRoute(category: String): String = "category/$category"

    fun toolRoute(toolId: String): String = "tool/$toolId"
}
