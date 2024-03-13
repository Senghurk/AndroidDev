package com.csx4109.fragmentdemo.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.os.Build

enum class AppNotificationChannels(val id: String, val notiName: String, val importance: Int) {
    NORMAL("normal", "Normal notification without any urgency", NotificationManager.IMPORTANCE_DEFAULT),
    URGENT("urgent", "Urgent notification like fire", NotificationManager.IMPORTANCE_HIGH)
}

object NotificationHandler {
    fun initNotificationChannel(context: Context) {
        // chanel for noti is only available for android 8 or later
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // channel group
            val channelGroup = NotificationChannelGroup("announcement", "Announcement")
            manager.createNotificationChannelGroup(channelGroup)

            // channel
            AppNotificationChannels.entries.forEach {
                val channel = NotificationChannel(it.id, it.notiName, it.importance)
                channel.group = channelGroup.id
                manager.createNotificationChannel(channel)
            }
        }
    }
}