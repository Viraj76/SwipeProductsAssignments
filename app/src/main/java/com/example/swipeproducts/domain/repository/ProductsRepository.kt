package com.example.swipeproducts.domain.repository

import com.example.swipeproducts.data.remote.dto.ProductPostResponse
import com.example.swipeproducts.domain.models.Product
import okhttp3.MultipartBody
import java.util.ArrayList

interface ProductsRepository {

   suspend fun getProductList() : List<Product>

   suspend fun postProduct(
      productName: String,
      productType: String,
      price: String,
      tax: String,
      image: List<MultipartBody.Part>?
   ) : ProductPostResponse
}