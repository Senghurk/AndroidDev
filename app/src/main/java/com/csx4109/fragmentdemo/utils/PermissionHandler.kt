package com.csx4109.fragmentdemo.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionHandler {
    // check and request if needed
    fun requestPermissionIfRequired(app: AppCompatActivity, permission: Array<String>) {
        if (!hasRequiredPermissions(app, permission)) {
            requestPermissions(app, permission)
        }
    }

    // check if permissions is granted already or not
    fun hasRequiredPermissions(
        context: Context,
        permissions: Array<String>
    ): Boolean {
        return permissions.all { permission ->
            ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    // request permission
    fun requestPermissions(activity: Activity, permissions: Array<String>) {
        ActivityCompat.requestPermissions(activity, permissions, 0)
    }

    val GPS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val CAMERA = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO
    )

    val NOTIFICATION = arrayOf(
        Manifest.permission.POST_NOTIFICATIONS
    )
}