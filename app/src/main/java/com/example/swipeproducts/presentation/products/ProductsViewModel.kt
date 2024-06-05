package com.example.swipeproducts.presentation.products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeproducts.domain.usecases.app_entry.data_classes.AppEntryUseCases
import com.example.swipeproducts.domain.usecases.products.data_classes.ProductUseCases
import com.example.swipeproducts.utils.AppEntryCallback
import com.example.swipeproducts.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProductsViewModel : ViewModel(), KoinComponent {


    private val productUseCases: ProductUseCases by inject()  // field injection

    private val appEntryUseCases: AppEntryUseCases by inject()

    // state flows for collecting the required data
    private val _productList = MutableStateFlow(ProductsState())
    val productList = _productList

    private val _appEntry = MutableStateFlow(false)
    val appEntry = _appEntry


    // reading app entry first
     fun readAppEntry(callback: AppEntryCallback) {
        viewModelScope.launch {
            appEntryUseCases.readUserEntry().collect { isEntered ->
                _appEntry.value = isEntered
                callback.onAppEntryRead(isEntered)
            }
        }
    }

    // get all the products
    fun getProductList() {
        viewModelScope.launch {
            productUseCases.getProductList().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _productList.value = ProductsState(loading = true)
                    }

                    is Resource.Success -> {
                        val data = resource.data
                        Log.d("count", data?.size.toString())
                        for (i in data!!) {
                            Log.d("viewmodel", i.toString())
                        }
                        _productList.value = ProductsState(productList = resource.data)
                    }

                    is Resource.Error -> {
                        Log.d("viewmodel", resource.message.toString())
                        _productList.value = ProductsState(error = resource.message!!)
                    }
                }
            }
        }
    }


    // saving app entry
    fun saveAppUserEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveUserEntry()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("viewmodelll" , "cleared")
    }
}