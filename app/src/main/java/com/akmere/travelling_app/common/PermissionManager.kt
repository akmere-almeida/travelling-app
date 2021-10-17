package com.akmere.travelling_app.common

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import com.akmere.travelling_app.R
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.annotations.AfterPermissionGranted

class PermissionManager(private val activity: Activity) {
    fun requestPermissions(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, activity)
    }

    @SuppressLint("MissingPermission")
    @AfterPermissionGranted(REQUEST_CODE_LOCATION)
    fun checkLocationPermission(
        onLocationGranted: (location: Location) -> Unit
    ) {
        if (EasyPermissions.hasPermissions(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        ) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
            fusedLocationClient.getCurrentLocation(
                LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY, null
            ).addOnSuccessListener {
                onLocationGranted.invoke(it)
            }
        } else {
            EasyPermissions.requestPermissions(
                host = activity,
                rationale = activity.getString(R.string.permission_location),
                requestCode = REQUEST_CODE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            )
        }
    }

    companion object {
        private const val REQUEST_CODE_LOCATION = 777
    }
}
