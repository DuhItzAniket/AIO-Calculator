package com.aio.calculator.shopping

import com.aio.calculator.core.database.entity.ShoppingItemEntity

data class ShoppingTotals(
    val subtotal: Double,
    val discountAmount: Double,
    val taxAmount: Double,
    val total: Double,
)

object ShoppingTotalsCalculator {
    fun calculate(items: List<ShoppingItemEntity>, discountPercent: Double, taxRatePercent: Double): ShoppingTotals {
        val subtotal = items.filter { it.included }.sumOf { it.price * it.quantity }
        val discountAmount = subtotal * discountPercent.coerceAtLeast(0.0) / 100.0
        val taxAmount = subtotal * taxRatePercent.coerceAtLeast(0.0) / 100.0
        return ShoppingTotals(
            subtotal = subtotal,
            discountAmount = discountAmount,
            taxAmount = taxAmount,
            total = subtotal - discountAmount + taxAmount,
        )
    }
}
