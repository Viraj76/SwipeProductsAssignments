package com.example.swipeproducts.presentation.products

import com.example.swipeproducts.domain.models.Product

data class ProductsState(
    val loading : Boolean = false,
    val productList : List<Product>  = emptyList(),
    val error : String = ""
)