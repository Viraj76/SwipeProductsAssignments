package com.example.swipeproducts.data.repository

import android.util.Log
import androidx.room.Dao
import com.example.swipeproducts.data.local.room.ProductsDao
import com.example.swipeproducts.data.remote.api.ProductsAPI
import com.example.swipeproducts.data.remote.dto.ProductPostResponse
import com.example.swipeproducts.domain.models.Product
import com.example.swipeproducts.domain.repository.ProductsRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class ProductsRepositoryImpl(
    private val productsAPI: ProductsAPI,
    private val productsDao: ProductsDao
) : ProductsRepository {
    override suspend fun getProductList(): List<Product> {

        return  try {
            val dataFromApi = productsAPI.getProductsList() // getting products from api
            productsDao.insert(dataFromApi)     // caching the products coming from api
            productsDao.getProducts()
        }
        // here the single source of data is room , if internet not available then also we can access the cached data.
        catch (e : Exception){
            Log.d("ex" , e.message.toString())
            productsDao.getProducts()
        }

    }

    override suspend fun postProduct(
        productName: String,
        productType: String,
        price: String,
        tax: String,
        image: List<MultipartBody.Part>?
    ): ProductPostResponse {
        val productNameBody = productName.toRequestBody(MultipartBody.FORM)
        val productTypeBody = productType.toRequestBody(MultipartBody.FORM)
        val priceBody = price.toRequestBody(MultipartBody.FORM)
        val taxBody = tax.toRequestBody(MultipartBody.FORM)

        return productsAPI.addProduct(productNameBody,productTypeBody,priceBody,taxBody,image)

    }
}