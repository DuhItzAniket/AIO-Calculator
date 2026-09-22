package com.aio.calculator.core.common

import kotlinx.serialization.Serializable

@Serializable
data class ToolDefinition(
    val id: String,
    val title: String,
    val category: ToolCategory,
    val description: String,
    val iconName: String = "ic_category_algebra",
    val keywords: List<String> = emptyList(),
    val aliases: List<String> = emptyList(),
    val calculatorType: CalculatorType = CalculatorType.FORM_BASED,
    val supportsFavorite: Boolean = true,
    val supportsHistory: Boolean = true,
    val route: String? = null,
    val order: Int = 0
) {
    val searchTerms: List<String> get() = listOf(title, category.displayName, description) + keywords + aliases

    fun matches(query: String): Boolean {
        val normalized = query.trim().lowercase()
        return normalized.isNotEmpty() && searchTerms.any { it.lowercase().contains(normalized) }
    }
}
