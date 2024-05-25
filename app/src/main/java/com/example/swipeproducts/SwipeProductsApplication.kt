package com.example.swipeproducts

import android.app.Application
import com.example.swipeproducts.data.di.dataModules
import com.example.swipeproducts.domain.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class SwipeProductsApplication : Application() {


    override fun onCreate() {
        super.onCreate()


       startKoin{
           // providing all modules here , which are responsible for creating the instances.
           modules (
               dataModules, domainModule
           )
           androidContext(this@SwipeProductsApplication)
       }
    }
}