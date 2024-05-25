package com.example.swipeproducts.domain.repository

import com.example.swipeproducts.domain.models.Product

interface ProductsRepository {

   suspend fun getProductList() : List<Product>
}