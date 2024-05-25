package com.example.swipeproducts

import android.app.Application

class SwipeProductsApplication : Application() {


    override fun onCreate() {
        super.onCreate()

       startKoin{

       }
    }
}