package com.example.swipeproducts.data.dto

import com.example.swipeproducts.domain.models.Product

data class ProductPostResponse(
    val message : String = "",
    val product_details : Product = Product(),
    val product_id : Int = 0,
    val success : Boolean = false
)
