package com.aio.calculator.shopping

import com.aio.calculator.core.database.entity.ShoppingItemEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class ShoppingTotalsTest {
    @Test
    fun calculatesSubtotalDiscountAndTax() {
        val result = ShoppingTotalsCalculator.calculate(
            items = listOf(
                ShoppingItemEntity(listId = 1, name = "Milk", price = 10.0, quantity = 2),
                ShoppingItemEntity(listId = 1, name = "Excluded", price = 100.0, included = false),
            ),
            discountPercent = 10.0,
            taxRatePercent = 5.0,
        )

        assertEquals(20.0, result.subtotal, 0.000001)
        assertEquals(2.0, result.discountAmount, 0.000001)
        assertEquals(1.0, result.taxAmount, 0.000001)
        assertEquals(19.0, result.total, 0.000001)
    }

    @Test
    fun negativeRatesAreClamped() {
        val result = ShoppingTotalsCalculator.calculate(
            items = listOf(ShoppingItemEntity(listId = 1, name = "Item", price = 12.0)),
            discountPercent = -10.0,
            taxRatePercent = -5.0,
        )

        assertEquals(12.0, result.total, 0.000001)
    }
}
