package com.csx4109.fragmentdemo

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.csx4109.fragmentdemo.databinding.ActivityNotificationBinding
import com.csx4109.fragmentdemo.utils.AppNotificationChannels
import com.csx4109.fragmentdemo.utils.NotificationHandler
import com.csx4109.fragmentdemo.utils.PermissionHandler

class NotificationActivity : AppCompatActivity() {
    private val view: ActivityNotificationBinding by lazy {
        ActivityNotificationBinding.inflate(
            layoutInflater
        )
    }
    private val notificationManager by lazy { this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
        PermissionHandler.requestPermissionIfRequired(this, PermissionHandler.NOTIFICATION)

        view.btnTriggerNoti.setOnClickListener {
            // create 2 notifications
            // 1. normal
            val normalNotification =
                NotificationCompat.Builder(this, AppNotificationChannels.NORMAL.id).apply {
                    setSmallIcon(R.drawable.baseline_home_24)
                    setContentTitle("Tell me why!")
                    setContentText("Tell me why, ain't nothing but a heartache")
                }.build()

            // 2. urgent - when click -> go to university list page
            val pendingIntent = TaskStackBuilder.create(this).run {
                val intent = Intent(this@NotificationActivity, UniversityListActivity::class.java)
                addNextIntentWithParentStack(intent)
                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            }
            val urgentNotification =
                NotificationCompat.Builder(this, AppNotificationChannels.URGENT.id).apply {
                    setSmallIcon(R.drawable.baseline_person_24)
                    setContentTitle("Urgent!!!")
                    setContentText("Your house is on fire!")
                    setContentIntent(pendingIntent)
                }.build()

            notificationManager.notify(0, normalNotification)
            notificationManager.notify(1, urgentNotification)
        }
    }
}