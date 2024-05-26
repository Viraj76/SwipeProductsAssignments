package com.example.swipeproducts.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey



/*
    Note :

    Here in the given API , all fields have some duplicates so we can not create a
    field as a PrimaryKey.

    i.e. I have created PrimaryKey based on all the fields, instead there are some data
    who has all fields same.

    One thing we can do here is , we can check all the products before inserting it , it is feasible
    with our API but not a good way to do it when the API will have bigger data

    i.e. having a Unique identifier in the api is better way to handle duplicates.

 */
@Entity(primaryKeys = ["image", "price", "product_name", "product_type", "tax"])
data class Product(
    val image: String = "",
    val price: Double = 0.0,
    val product_name: String = "",
    val product_type: String = "",
    var tax: Double = 0.0
)