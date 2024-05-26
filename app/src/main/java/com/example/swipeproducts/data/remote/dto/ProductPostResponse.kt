package com.example.swipeproducts.data.remote.dto

import com.example.swipeproducts.domain.models.Product

data class ProductPostResponse(
    val message : String,
    val product_details : Product,
    val product_id : Int,
    val success : Boolean
)
