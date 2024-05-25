package com.example.swipeproducts.data.di

import com.example.swipeproducts.data.remote.api.ProductsAPI
import com.example.swipeproducts.data.repository.ProductsRepositoryImpl
import com.example.swipeproducts.domain.repository.ProductsRepository
import com.example.swipeproducts.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


fun provideProductsAPI() : ProductsAPI{
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(ProductsAPI::class.java)
}


fun provideProductsRepository(productsAPI: ProductsAPI) : ProductsRepository{
    return ProductsRepositoryImpl(productsAPI)
}

val dataModules = module {
    // providing instance only once ,
    single { provideProductsAPI() }
    single { provideProductsRepository(get()) }

}
