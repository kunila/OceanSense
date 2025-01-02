package com.kunila.ocean.presentation.events

import com.kunila.ocean.domain.model.ProductData

sealed class UIEvent {
    data class OrderProduct(val product: ProductData) : UIEvent()
}