package com.kunila.ocean.data.datasource

import com.kunila.ocean.domain.model.ProductData

class ProductDataSource {

    fun loadProductData(): List<ProductData> {
        val products = mutableListOf<ProductData>()
        products.add(ProductData(1,"AquaSense Buoy", "Test Description 1",75000.00, "images/oceanbuoy.png", 0))
        return products
    }
}