package com.aio.calculator.shopping

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aio.calculator.core.database.AioDatabase
import com.aio.calculator.core.database.entity.ShoppingItemEntity
import com.aio.calculator.core.database.entity.ShoppingListEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppShoppingViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AioDatabase.getInstance(application)
    val lists: StateFlow<List<ShoppingListEntity>> = database.shoppingListDao().getAllLists()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    private val _selectedListId = MutableStateFlow<Long?>(null)
    val selectedListId: StateFlow<Long?> = _selectedListId
    val items: StateFlow<List<ShoppingItemEntity>> = _selectedListId.flatMapLatest { id ->
        if (id == null) kotlinx.coroutines.flow.flowOf(emptyList()) else database.shoppingItemDao().getItemsForList(id)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun selectList(id: Long) { _selectedListId.value = id }

    fun createList(name: String) {
        if (name.isBlank()) return
        viewModelScope.launch {
            val id = database.shoppingListDao().insert(ShoppingListEntity(name = name.trim()))
            _selectedListId.value = id
        }
    }

    fun addItem(name: String, price: Double, quantity: Int) {
        val listId = _selectedListId.value ?: return
        if (name.isBlank() || price < 0.0 || quantity <= 0) return
        viewModelScope.launch {
            database.shoppingItemDao().insert(ShoppingItemEntity(listId = listId, name = name.trim(), price = price, quantity = quantity))
        }
    }

    fun updateSelectedList(discount: Double, taxRate: Double, budget: Double) {
        val list = lists.value.firstOrNull { it.id == _selectedListId.value } ?: return
        if (discount < 0.0 || taxRate < 0.0 || budget < 0.0) return
        viewModelScope.launch {
            database.shoppingListDao().update(
                id = list.id,
                name = list.name,
                discount = discount,
                taxRate = taxRate,
                budget = budget,
                updatedTimestamp = System.currentTimeMillis(),
            )
        }
    }

    fun deleteItem(id: Long) { viewModelScope.launch { database.shoppingItemDao().delete(id) } }
}
