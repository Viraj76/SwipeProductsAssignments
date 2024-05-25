package com.example.swipeproducts.data.repository

import android.util.Log
import androidx.room.Dao
import com.example.swipeproducts.data.local.room.ProductsDao
import com.example.swipeproducts.data.remote.api.ProductsAPI
import com.example.swipeproducts.domain.models.Product
import com.example.swipeproducts.domain.repository.ProductsRepository

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
}