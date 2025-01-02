package com.kunila.ocean.domain.usecase

import com.kunila.ocean.data.repository.ProductRepository
import com.kunila.ocean.domain.model.ProductData

class GetProductDataUseCase(private val productRepository: ProductRepository) {

    operator fun invoke(): List<ProductData> {
        return productRepository.loadProductData()
    }
}