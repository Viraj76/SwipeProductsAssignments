package com.example.swipeproducts.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities{
    val notificationApi : NotificationAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://fcm.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NotificationAPI::class.java)
    }
}