package com.example.swipeproducts.presentation.products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeproducts.domain.models.Product
import com.example.swipeproducts.domain.usecases.products.data_classes.ProductUseCases
import com.example.swipeproducts.utils.Resource
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProductsViewModel : ViewModel() , KoinComponent{

    private val productUseCases : ProductUseCases by inject()

    fun getProductList() {
        viewModelScope.launch {
            productUseCases.getProductList().collect{resource ->


                when(resource){
                    is Resource.Loading ->{
                        Log.d("viewmodel", "loading")
                    }
                    is Resource.Success ->{
                        val data  = resource.data
                        for (i in data!!){
                            Log.d("viewmodel",i.toString())
                        }
                    }
                    is Resource.Error -> {
                        Log.d("viewmodel",resource.message.toString())
                    }

                }

            }
        }
    }
}