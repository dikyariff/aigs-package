package com.aigs.base.data.repository

import com.aigs.base.data.response.Product
import com.aigs.base.data.remote.ProductsService

class ProductRepository(private val api: ProductsService) {
    suspend fun getProducts(): List<Product> {
        return api.getProducts().products
    }
}