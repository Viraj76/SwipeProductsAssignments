package com.example.swipeproducts.domain.usecases.products.data_classes

import com.example.swipeproducts.domain.usecases.products.GetProductList
import com.example.swipeproducts.domain.usecases.products.PostProducts

data class ProductUseCases(
    val getProductList: GetProductList,
    val postProducts  : PostProducts
)
