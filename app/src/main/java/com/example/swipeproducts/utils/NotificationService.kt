package com.example.swipeproducts.utils



import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.swipeproducts.R
import com.example.swipeproducts.presentation.main_activity.MainActivity

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

class NotificationService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)


        val channelId = "swipe"
        val channel = NotificationChannel(channelId , "swiper_products", NotificationManager.IMPORTANCE_HIGH).apply {
            description = "swipe products posted messages"
            enableLights(true)
        }


        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)

        // pending intent , when user clicks on the notification it should open MainActivity

        val pendingIntent = PendingIntent.getActivity(this,0, Intent(this , MainActivity::class.java),PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this , channelId)
            .setContentTitle(message.data["title"])
            .setContentText(message.data["body"])
            .setSmallIcon(R.drawable.icon)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        manager.notify(Random.nextInt() , notification)

    }
}