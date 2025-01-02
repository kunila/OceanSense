package com.kunila.ocean.data.repository

import com.kunila.ocean.data.datasource.ProductDataSource
import com.kunila.ocean.domain.model.ProductData

class ProductRepository(private val productDataSource: ProductDataSource) {

    fun loadProductData(): List<ProductData> {
        return productDataSource.loadProductData()
    }
}