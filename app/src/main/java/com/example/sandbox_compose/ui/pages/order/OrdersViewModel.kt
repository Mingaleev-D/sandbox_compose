package com.example.sandbox_compose.ui.pages.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_compose.data.remote.ResultWrapper
import com.example.sandbox_compose.domain.model.OrdersData
import com.example.sandbox_compose.domain.usecase.OrderListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrdersViewModel(
       private val orderListUseCase: OrderListUseCase
) : ViewModel() {

    private val _ordersEvent = MutableStateFlow<OrdersEvent>(OrdersEvent.Loading)
    val ordersEvent = _ordersEvent.asStateFlow()

    init {
        getOrderList()
    }

    fun filterOrders(
           list: List<OrdersData>,
           filter: String
    ): List<OrdersData> {
        val filteredList = list.filter { it.status == filter }
        return filteredList
    }

    private fun getOrderList() {
        viewModelScope.launch {
            val result = orderListUseCase.execute()

            when (result) {
                is ResultWrapper.Success -> {
                    val data = result.value
                    _ordersEvent.value = OrdersEvent.Success(data.`data`)
                }

                is ResultWrapper.Failure -> {
                    _ordersEvent.value = OrdersEvent.Error("Something went wrong")
                }
            }
        }
    }
}

sealed class OrdersEvent {
    object Loading : OrdersEvent()
    data class Success(val data: List<OrdersData>) : OrdersEvent()
    data class Error(val errorMsg: String) : OrdersEvent()
}
