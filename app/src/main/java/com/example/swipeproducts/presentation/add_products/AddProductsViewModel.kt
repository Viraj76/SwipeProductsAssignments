package com.example.swipeproducts.presentation.add_products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeproducts.domain.usecases.products.PostProducts
import com.example.swipeproducts.domain.usecases.products.data_classes.ProductUseCases
import com.example.swipeproducts.utils.Resource
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.internal.isSensitiveHeader
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject

class AddProductsViewModel : ViewModel() , KoinComponent{

    private val productUseCases : ProductUseCases by inject()  // field injection


    fun postProducts(
        productName: String,
        productType: String,
        price: String,
        tax: String,
        image: List<MultipartBody.Part>?
    ){
        viewModelScope.launch {
            productUseCases.postProducts(productName, productType, price, tax, image).collect{resource ->
                when(resource){
                    is Resource.Loading ->{
                        Log.d("addprodviewm" , "loading")
                    }
                    is Resource.Success ->{
                        Log.d("addprodviewm" , resource.data.toString())
                    }
                    is Resource.Error ->{
                        Log.d("addprodviewm" , resource.message.toString())
                    }
                }
            }
        }
    }
}