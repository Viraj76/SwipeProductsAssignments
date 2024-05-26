package com.example.swipeproducts.domain.usecases.notification

import com.example.swipeproducts.data.dto.notification.Notification
import com.example.swipeproducts.domain.models.Product
import com.example.swipeproducts.domain.repository.NotificationRepository
import com.example.swipeproducts.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call

class SendNotification(
    private val notificationRepository: NotificationRepository
) {
    operator fun invoke(notification: Notification) : Flow<Resource<Call<Notification>>> = flow{

        emit(Resource.Loading())

        try {
            emit(Resource.Success(data = notificationRepository.sendNotification(notification)))

        }
        catch (e : Exception){
            emit(Resource.Error(message = e.message.toString()))
        }


    }.flowOn(Dispatchers.IO)
}