package com.example.swipeproducts.domain.models

import androidx.room.Entity


@Entity
data class Product(
    val image: String,
    val price: Double,
    val product_name: String,
    val product_type: String,
    val tax: Double
)