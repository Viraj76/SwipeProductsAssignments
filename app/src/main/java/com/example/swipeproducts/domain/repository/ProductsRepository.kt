package com.example.swipeproducts.domain.repository

import com.example.swipeproducts.domain.models.Product
import java.util.ArrayList

interface ProductsRepository {

   suspend fun getProductList() : List<Product>
}