package com.example.swipeproducts.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.swipeproducts.domain.models.Product
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductsDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(products : List<Product>)

    @Query("SELECT * FROM Product")
    fun getProducts() : Flow<List<Product>>
}