package com.csx4109.fragmentdemo

import android.app.Application
import com.csx4109.fragmentdemo.utils.NotificationHandler

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        NotificationHandler.initNotificationChannel(this)
    }
}