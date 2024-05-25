package com.example.swipeproducts.data.repository

import com.example.swipeproducts.data.remote.api.ProductsAPI
import com.example.swipeproducts.domain.models.Product
import com.example.swipeproducts.domain.repository.ProductsList

class ProductsListImpl(
    private val productsAPI: ProductsAPI
) : ProductsList {
    override suspend fun getProductList(): List<Product> {
        return productsAPI.getProductsList().productList
    }
}