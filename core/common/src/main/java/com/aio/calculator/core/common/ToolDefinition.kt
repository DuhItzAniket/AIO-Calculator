package com.aio.calculator.core.common

import kotlinx.serialization.Serializable

/**
 * Metadata definition for a calculator tool
 * This is the single source of truth for tool information
 */
@Serializable
data class ToolDefinition(
    val id: String,
    val title: String,
    val category: ToolCategory,
    val description: String,
    val iconName: String,
    val keywords: List<String> = emptyList(),
    val aliases: List<String> = emptyList(),
    val calculatorType: CalculatorType = CalculatorType.FORM_BASED,
    val supportsFavorite: Boolean = true,
    val supportsHistory: Boolean = true,
    val route: String? = null,
    val order: Int = 0
) {
    /**
     * All searchable terms for this tool
     */
    val searchTerms: List<String> get() = listOf(title, category.displayName) + keywords + aliases

    /**
     * Check if a query matches this tool
     */
    fun matches(query: String): Boolean {
        val lowerQuery = query.lowercase()
        return searchTerms.any { it.lowercase().contains(lowerQuery) }
    }
}

/**
 * Registry for all calculator tools
 * This is populated at build time or initialization
 */
object ToolRegistry {
    private var _tools: List<ToolDefinition> = emptyList()
    private var _toolsById: Map<String, ToolDefinition> = emptyMap()
    private var _toolsByCategory: Map<ToolCategory, List<ToolDefinition>> = emptyMap()

    val tools: List<ToolDefinition>
        get() = _tools

    val toolsById: Map<String, ToolDefinition>
        get() = _toolsById

    val toolsByCategory: Map<ToolCategory, List<ToolDefinition>>
        get() = _toolsByCategory

    fun initialize(tools: List<ToolDefinition>) {
        _tools = tools.sortedBy { it.category.order }.thenBy { it.order }
        _toolsById = _tools.associateBy { it.id }
        _toolsByCategory = _tools.groupBy { it.category }.mapValues { it.value.sortedBy { it.order } }
    }

    fun getTool(id: String): ToolDefinition? = _toolsById[id]

    fun getTools(category: ToolCategory): List<ToolDefinition> = _toolsByCategory[category] ?: emptyList()

    fun search(query: String): List<ToolDefinition> {
        if (query.isBlank()) return _tools
        return _tools.filter { it.matches(query) }
    }

    fun getFavorites(favoriteIds: Set<String>): List<ToolDefinition> =
        favoriteIds.mapNotNull { _toolsById[it] }

    fun getRecent(recentIds: List<String>): List<ToolDefinition> =
        recentIds.mapNotNull { _toolsById[it] }.distinct()
}