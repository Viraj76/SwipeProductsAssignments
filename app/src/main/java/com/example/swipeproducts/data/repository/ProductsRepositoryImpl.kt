package com.example.swipeproducts.data.repository

import com.example.swipeproducts.data.remote.api.ProductsAPI
import com.example.swipeproducts.domain.models.Product
import com.example.swipeproducts.domain.repository.ProductsRepository

class ProductsRepositoryImpl(
    private val productsAPI: ProductsAPI
) : ProductsRepository {
    override suspend fun getProductList(): List<Product> {
        return productsAPI.getProductsList().productList
    }
}