package com.aio.calculator.core.common

import kotlinx.serialization.Serializable

/**
 * Represents a history item for display
 */
@Serializable
data class HistoryItem(
    val id: Long,
    val timestamp: Long,
    val toolId: String,
    val toolTitle: String,
    val toolIcon: String,
    val inputData: String,
    val result: String,
    val displayExpression: String
) {
    fun getFormattedTime(): String {
        val diff = System.currentTimeMillis() - timestamp
        return when {
            diff < 60000 -> "Just now"
            diff < 3600000 -> "${diff / 60000}m ago"
            diff < 86400000 -> "${diff / 3600000}h ago"
            else -> "${diff / 86400000}d ago"
        }
    }
}