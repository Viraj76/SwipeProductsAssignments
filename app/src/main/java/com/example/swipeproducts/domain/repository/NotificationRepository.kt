package com.example.swipeproducts.domain.repository

import com.example.swipeproducts.data.dto.notification.Notification
import retrofit2.Call

interface NotificationRepository {

    suspend fun sendNotification(notification: Notification) : Call<Notification>
}