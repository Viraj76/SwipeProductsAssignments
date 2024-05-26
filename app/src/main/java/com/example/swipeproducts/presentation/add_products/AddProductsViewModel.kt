package com.example.swipeproducts.presentation.add_products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeproducts.data.remote.dto.ProductPostResponse
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

    // state for monitoring posting status

    private val _postProduct = MutableStateFlow(PostProductState())
    val postProduct = _postProduct

    fun update(){
        _postProduct.value = PostProductState()
    }
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
                        Log.d("addprodviewm" , resource.data.toString())
                        _postProduct.value = PostProductState(data =  resource.data!!)
                    }
                    is Resource.Error ->{
                        Log.d("addprodviewm" , " error ${resource.message.toString()}")
                    }
                }
            }
        }
    }
}