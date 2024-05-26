package com.example.swipeproducts.data.repository

import android.util.Log
import com.example.swipeproducts.data.dto.notification.Notification
import com.example.swipeproducts.data.remote.api.NotificationAPI
import com.example.swipeproducts.domain.repository.NotificationRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationRepositoryImpl(
    val notificationAPI: NotificationAPI
) : NotificationRepository {
    override fun sendNotification(notification: Notification): Call<Notification> {
        Log.d("sendnoti", "repo")
        notificationAPI.sendNotification(notification).enqueue(object :
            Callback<Notification> {
            override fun onResponse(
                call: Call<Notification>,
                response: Response<Notification>
            ) {
                if (response.isSuccessful) {

                }
            }

            override fun onFailure(call: Call<Notification>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        return notificationAPI.sendNotification(notification)
    }
}