package com.csx4109.fragmentdemo.viewModels

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.csx4109.fragmentdemo.utils.PermissionHandler
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import java.lang.Exception

class GPSViewModel(private val app: Application): AndroidViewModel(app) {
    private val locationManager: LocationManager by lazy { app.getSystemService(Context.LOCATION_SERVICE) as LocationManager }
    private val locationClient: FusedLocationProviderClient by lazy { LocationServices.getFusedLocationProviderClient(app) }

    val userLocation: MutableLiveData<Location> = MutableLiveData()
    val error: MutableLiveData<Throwable> = MutableLiveData()

    @SuppressLint("MissingPermission")
    fun getCurrentLocation() {
        if (!PermissionHandler.hasRequiredPermissions(app, PermissionHandler.GPS)) {
            error.postValue(Exception("Missing location permission"))
        } else {
            val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if (isGpsEnabled && isNetworkEnabled) {
                // get location
                val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 3000)
                    .setWaitForAccurateLocation(true)
                    .build()

                val callback = object: LocationCallback() {
                    override fun onLocationResult(result: LocationResult) {
                        super.onLocationResult(result)
                        result.locations.lastOrNull()?.let {
                            userLocation.postValue(it)
                        }
                    }
                }

                locationClient.requestLocationUpdates(
                    request,
                    callback,
                    Looper.getMainLooper()
                )
            } else {
                error.postValue(Exception("GPS or Network are not available"))
            }
        }
    }
}