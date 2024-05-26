package com.example.swipeproducts.data.repository

import com.example.swipeproducts.data.dto.notification.Notification
import com.example.swipeproducts.data.remote.api.NotificationAPI
import com.example.swipeproducts.domain.repository.NotificationRepository
import retrofit2.Call

class NotificationRepositoryImpl(
    private val notificationAPI: NotificationAPI
) : NotificationRepository {
    override suspend fun sendNotification(notification: Notification): Call<Notification> {
        return notificationAPI.sendNotification(notification)
    }
}