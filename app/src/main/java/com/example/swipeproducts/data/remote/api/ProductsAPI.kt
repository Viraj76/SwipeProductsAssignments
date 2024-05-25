package com.example.swipeproducts.data.remote.api

import com.example.swipeproducts.data.remote.dto.ProductsDTO
import com.example.swipeproducts.domain.models.Product
import retrofit2.http.GET

interface ProductsAPI {

    @GET("api/public/get")
    suspend fun getProductsList() : List<Product>
}