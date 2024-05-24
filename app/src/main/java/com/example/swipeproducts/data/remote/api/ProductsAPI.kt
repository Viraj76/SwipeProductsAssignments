package com.example.swipeproducts.data.remote.api

import retrofit2.http.GET

interface ProductsAPI {

    @GET("api/public/get")
    suspend fun getProductsList()
}