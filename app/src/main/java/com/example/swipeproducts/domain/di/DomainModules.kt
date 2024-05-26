package com.example.swipeproducts.domain.di

import com.example.swipeproducts.domain.repository.NotificationRepository
import com.example.swipeproducts.domain.repository.ProductsRepository
import com.example.swipeproducts.domain.usecases.notification.SendNotification
import com.example.swipeproducts.domain.usecases.notification.data_classes.NotificationUseCase
import com.example.swipeproducts.domain.usecases.products.GetProductList
import com.example.swipeproducts.domain.usecases.products.PostProducts
import com.example.swipeproducts.domain.usecases.products.data_classes.ProductUseCases
import org.koin.dsl.module
import kotlin.math.sin


fun provideProductUseCases(productsRepository: ProductsRepository) : ProductUseCases{
    return ProductUseCases(
        getProductList = GetProductList(productsRepository),
        postProducts = PostProducts(productsRepository)
    )
}

fun provideNotificationUseCases(notificationRepository: NotificationRepository) : NotificationUseCase{
    return NotificationUseCase(sendNotification = SendNotification(notificationRepository))
}

val domainModule = module {
    single { provideProductUseCases(get()) }
    single { provideNotificationUseCases(get()) }
}