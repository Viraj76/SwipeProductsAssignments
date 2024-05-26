package com.example.swipeproducts.data.remote.api

import com.example.swipeproducts.data.remote.dto.ProductPostResponse
import com.example.swipeproducts.data.remote.dto.ProductsDTO
import com.example.swipeproducts.domain.models.Product
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ProductsAPI {

    @GET("api/public/get")
    suspend fun getProductsList() : List<Product>

    @Multipart
    @POST("api/public/add")
    suspend fun addProduct(
        @Part("product_name") productName: okhttp3.RequestBody,
        @Part("product_type") productType: okhttp3.RequestBody,
        @Part("price") price: okhttp3.RequestBody,
        @Part("tax") tax: okhttp3.RequestBody,
        @Part files: MultipartBody.Part?
    ): ProductPostResponse
}