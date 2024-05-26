package com.example.swipeproducts.data.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.swipeproducts.data.local.manager.LocalUserManagerImpl
import com.example.swipeproducts.data.local.room.ProductsDao
import com.example.swipeproducts.data.local.room.ProductsDatabase
import com.example.swipeproducts.data.remote.api.NotificationAPI
import com.example.swipeproducts.data.remote.api.ProductsAPI
import com.example.swipeproducts.data.repository.NotificationRepositoryImpl
import com.example.swipeproducts.data.repository.ProductsRepositoryImpl
import com.example.swipeproducts.domain.local.manager.LocalUserManager
import com.example.swipeproducts.domain.repository.NotificationRepository
import com.example.swipeproducts.domain.repository.ProductsRepository
import com.example.swipeproducts.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.math.sin



fun provideLocalUserManager(
    application: Application
) : LocalUserManager=
     LocalUserManagerImpl(application)



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

fun provideProductDataBase(
    application: Application
) : ProductsDatabase{
    return Room.databaseBuilder(
        context = application,
        klass = ProductsDatabase::class.java,
        name = "Product DB",
    ).fallbackToDestructiveMigration()
        .build()
}
fun provideProductsDao(productsDatabase: ProductsDatabase) : ProductsDao{
    return productsDatabase.productsDao
}

fun provideProductsRepository(
    productsAPI: ProductsAPI,
    productsDao: ProductsDao
) : ProductsRepository{
    return ProductsRepositoryImpl(productsAPI , productsDao)
}

fun provideNotificationAPI() : NotificationAPI{
       return Retrofit.Builder()
            .baseUrl("https://fcm.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NotificationAPI::class.java)
}

fun provideNotificationRepository(
    notificationAPI: NotificationAPI
) : NotificationRepository{
    return NotificationRepositoryImpl(notificationAPI = notificationAPI)
}




val dataModules = module {
    // providing instance only once ,
    single { provideLocalUserManager(application = androidApplication()) }
    single { provideProductsAPI() }
    single { provideProductsDao(get()) }
    single { provideProductsRepository(get(),get()) }
    single { provideProductDataBase(get()) }
    single { provideNotificationAPI() }
    single { provideNotificationRepository(get()) }
}
