package com.example.swipeproducts.domain.usecases.products

import com.example.swipeproducts.data.remote.dto.ProductPostResponse
import com.example.swipeproducts.domain.models.Product
import com.example.swipeproducts.domain.repository.ProductsRepository
import com.example.swipeproducts.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody

class PostProducts(
    private val productsRepository: ProductsRepository
) {
    operator fun invoke(
        productName: String,
        productType: String,
        price: String,
        tax: String,
        image: List<MultipartBody.Part>?
    ) : Flow<Resource<ProductPostResponse>> = flow{

        emit(Resource.Loading())

        try {
            emit(Resource.Success(data = productsRepository.postProduct(productName, productType, price, tax, image)))

        }
        catch (e : Exception){
            emit(Resource.Error(message = e.message.toString()))
        }


    }.flowOn(Dispatchers.IO)
}