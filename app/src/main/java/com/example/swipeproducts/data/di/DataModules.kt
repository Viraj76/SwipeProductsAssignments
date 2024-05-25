package com.example.swipeproducts.data.di

import com.example.swipeproducts.data.remote.api.ProductsAPI
import com.example.swipeproducts.data.repository.ProductsRepositoryImpl
import com.example.swipeproducts.domain.repository.ProductsRepository
import com.example.swipeproducts.utils.Constants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun provideProductsAPI() : ProductsAPI{
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
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
