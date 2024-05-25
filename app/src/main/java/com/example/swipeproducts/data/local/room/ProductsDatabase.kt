package com.example.swipeproducts.data.local.room

import androidx.room.Database
import com.example.swipeproducts.data.remote.dto.ProductsDTO
import com.example.swipeproducts.domain.models.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductsDatabase {
    abstract val productsDao : ProductsDao
}