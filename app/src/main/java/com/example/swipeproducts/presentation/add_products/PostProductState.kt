package com.example.swipeproducts.presentation.add_products

import com.example.swipeproducts.data.remote.dto.ProductPostResponse

data class PostProductState(
    val loading : Boolean = false,
    val data : ProductPostResponse ? = null,
    val error : String = ""
)
