package com.example.swipeproducts.presentation.add_products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeproducts.data.dto.ProductPostResponse
import com.example.swipeproducts.data.dto.notification.Notification
import com.example.swipeproducts.domain.usecases.notification.data_classes.NotificationUseCase
import com.example.swipeproducts.domain.usecases.products.PostProducts
import com.example.swipeproducts.domain.usecases.products.data_classes.ProductUseCases
import com.example.swipeproducts.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.internal.isSensitiveHeader
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject

class AddProductsViewModel : ViewModel() , KoinComponent {

    private val productUseCases: ProductUseCases by inject()  // field injection
    private val notificationUseCase : NotificationUseCase by inject()

    // state for monitoring posting status
    private val _postProduct = MutableStateFlow(PostProductState())
    val postProduct = _postProduct



    // post products
    fun postProducts(
        productName: String,
        productType: String,
        price: String,
        tax: String,
        image: MultipartBody.Part?
    ){
        viewModelScope.launch {
            productUseCases.postProducts(productName, productType, price, tax, image).collect{resource ->
                when(resource){
                    is Resource.Loading ->{
                        _postProduct.value = PostProductState(loading = true)
                    }
                    is Resource.Success ->{
                        _postProduct.value = PostProductState(data =  resource.data!!)
                    }
                    is Resource.Error ->{
                    }
                }
            }
        }
    }

    // resetting the state flow of products after posting to the server
    fun update(){
        _postProduct.value = PostProductState()
    }

    // sending notification once the posting products has been done
    fun sendNotification(notification: Notification){
        viewModelScope.launch {
            // we should always collect a flow other wise code will not run
            notificationUseCase.sendNotification(notification).collect{ }

        }
    }

    override fun onCleared() {
        super.onCleared()
//        Log.d("viewmodelll" , "cleared")
    }
}