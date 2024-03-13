package com.csx4109.fragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.csx4109.fragmentdemo.databinding.ActivityGpsactivityBinding
import com.csx4109.fragmentdemo.utils.PermissionHandler
import com.csx4109.fragmentdemo.viewModels.GPSViewModel

class GPSActivity : AppCompatActivity() {
    private val view: ActivityGpsactivityBinding by lazy { ActivityGpsactivityBinding.inflate(layoutInflater) }
    private val viewModel: GPSViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
        // Check for app's permission
        // If we have permission, do nothing. But if not, ask user for permission
        PermissionHandler.requestPermissionIfRequired(this, PermissionHandler.GPS)

        // Click btn, get user location and update ui
        view.btnGetLocation.setOnClickListener {
            // get user location
            viewModel.getCurrentLocation()
        }

        viewModel.userLocation.observe(this) {
            view.tvUserLocation.text = "User Location: ${it.latitude} ${it.longitude}"
        }

        // If user did not provide permission or gps is not available -> tell something to user
        viewModel.error.observe(this) {
            AlertDialog.Builder(this)
                .setTitle("Something went wrong")
                .setMessage(it.message)
                .setPositiveButton("Ok") { dialog, _ -> dialog.cancel()}
                .show()
        }
    }
}