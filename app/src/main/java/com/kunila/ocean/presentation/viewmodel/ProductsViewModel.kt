package com.kunila.ocean.presentation.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunila.ocean.domain.model.ProductData
import com.kunila.ocean.domain.usecase.GetProductDataUseCase
import com.kunila.ocean.presentation.events.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductDataUseCase: GetProductDataUseCase
): ViewModel(){

    private val _data = mutableStateOf<List<ProductData>>(emptyList())
    val data: State<List<ProductData>> get() = _data

    private val _uiEvent = MutableSharedFlow<UIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        loadProductData()
    }
    private fun loadProductData()
    {
        _data.value = getProductDataUseCase()
    }

    fun onOrderNowClicked(product: ProductData) {
        // Handle order now button click

        // Emit event for UI response
        viewModelScope.launch {
            _uiEvent.emit(UIEvent.OrderProduct(product))
        }
    }
}