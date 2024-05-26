package com.example.swipeproducts.domain.di

import android.content.Context
import com.example.swipeproducts.domain.local.manager.LocalUserManager
import com.example.swipeproducts.domain.repository.NotificationRepository
import com.example.swipeproducts.domain.repository.ProductsRepository
import com.example.swipeproducts.domain.usecases.app_entry.ReadUserEntry
import com.example.swipeproducts.domain.usecases.app_entry.SaveUserEntry
import com.example.swipeproducts.domain.usecases.app_entry.data_classes.AppEntryUseCases
import com.example.swipeproducts.domain.usecases.notification.SendNotification
import com.example.swipeproducts.domain.usecases.notification.data_classes.NotificationUseCase
import com.example.swipeproducts.domain.usecases.products.GetProductList
import com.example.swipeproducts.domain.usecases.products.PostProducts
import com.example.swipeproducts.domain.usecases.products.data_classes.ProductUseCases
import com.example.swipeproducts.utils.NetworkManager
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.dsl.module
import kotlin.math.sin


fun provideAppEntryUseCases(
    localUserManager: LocalUserManager
) : AppEntryUseCases{
    return AppEntryUseCases(ReadUserEntry(localUserManager) , SaveUserEntry(localUserManager))
}

fun provideProductUseCases(productsRepository: ProductsRepository) : ProductUseCases{
    return ProductUseCases(
        getProductList = GetProductList(productsRepository),
        postProducts = PostProducts(productsRepository)
    )
}

fun provideNotificationUseCases(notificationRepository: NotificationRepository) : NotificationUseCase{
    return NotificationUseCase(sendNotification = SendNotification(notificationRepository))
}

fun provideFirebaseMessaging() : FirebaseMessaging{
    return FirebaseMessaging.getInstance()
}

val domainModule = module {
    single { provideAppEntryUseCases(get()) }
    single { provideProductUseCases(get()) }
    single { provideNotificationUseCases(get()) }


    single { (context: Context) -> NetworkManager(context) }    // providing single instance of network manager class
    single {  provideFirebaseMessaging() }
}