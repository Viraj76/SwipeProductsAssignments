package com.example.swipeproducts

import android.app.Application
import com.example.swipeproducts.data.di.dataModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class SwipeProductsApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        module {
            dataModules    // we have created this in data layer for instance of api and repo
        }
       startKoin{
           androidContext(this@SwipeProductsApplication)
       }
    }
}