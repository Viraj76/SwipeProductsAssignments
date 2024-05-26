package com.example.swipeproducts.presentation.products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeproducts.domain.models.Product
import com.example.swipeproducts.domain.usecases.app_entry.data_classes.AppEntryUseCases
import com.example.swipeproducts.domain.usecases.products.data_classes.ProductUseCases
import com.example.swipeproducts.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProductsViewModel : ViewModel(), KoinComponent {


    private val productUseCases: ProductUseCases by inject()  // field injection

    private val appEntryUseCases: AppEntryUseCases by inject()

    private val _productList = MutableStateFlow(ProductsState())
    val productList = _productList

    private val _appEntry = MutableStateFlow(false)
    val appEntry = _appEntry


     fun readAppEntry(callback: AppEntryCallback) {
        Log.d("saving", "init called")
        viewModelScope.launch {
            appEntryUseCases.readUserEntry().collect { isEntered ->
                Log.d("saving", "viewmolde $isEntered")
                _appEntry.value = isEntered
                callback.onAppEntryRead(isEntered)
            }
        }
    }

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


    fun saveAppUserEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveUserEntry()
        }
    }
}