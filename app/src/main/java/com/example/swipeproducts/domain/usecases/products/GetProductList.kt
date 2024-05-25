package com.example.swipeproducts.domain.usecases.products

import com.example.swipeproducts.domain.models.Product
import com.example.swipeproducts.domain.repository.ProductsRepository
import com.example.swipeproducts.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetProductList(
    private val productsRepository: ProductsRepository
) {

    operator fun invoke() : Flow<Resource<List<Product>>> = flow{

        emit(Resource.Loading())

        try {
            emit(Resource.Success(data = productsRepository.getProductList()))

        }
        catch (e : Exception){
            emit(Resource.Error(message = e.message.toString()))
        }


    }.flowOn(Dispatchers.IO)
}