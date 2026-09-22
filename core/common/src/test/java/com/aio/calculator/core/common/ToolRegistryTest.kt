package com.aio.calculator.core.common

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ToolRegistryTest {
    @Test
    fun searchFindsToolsByTitleAndKeyword() {
        assertEquals("Currency Converter", ToolRegistry.search("money").single().title)
        assertNotNull(ToolRegistry.getTool("shopping_list"))
    }

    @Test
    fun favoritesAndRecentsIgnoreUnknownIdsAndDeduplicateRecents() {
        assertEquals(1, ToolRegistry.getFavorites(setOf("percentage", "missing")).size)
        val recent = ToolRegistry.getRecent(listOf("percentage", "percentage", "missing"))
        assertEquals(listOf("percentage"), recent.map { it.id })
        assertTrue(ToolRegistry.allTools.isNotEmpty())
    }
}
