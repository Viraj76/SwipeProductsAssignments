package com.example.swipeproducts.domain

import com.example.swipeproducts.domain.repository.ProductsRepository
import com.example.swipeproducts.domain.usecases.products.GetProductList
import com.example.swipeproducts.domain.usecases.products.data_classes.ProductUseCases
import org.koin.dsl.module


fun provideProductUseCases(productsRepository: ProductsRepository) : ProductUseCases{
    return ProductUseCases(
        getProductList = GetProductList(productsRepository)
    )
}

val domainModule = module {
    single { provideProductUseCases(get()) }
}