package com.mnatsakanyan.android.nearbyvenues.data.location.datasource

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.location.LocationManager
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GPSException
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.LocationException
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.PermissionException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
internal class LocationDataSourceImpl @Inject constructor(
        private val application: Application,
        private val fusedLocationProviderClient: FusedLocationProviderClient,
        private val locationManager: LocationManager
) : LocationDataSource {

    override suspend fun getLocation(): GeoLocation {
        if (!hasPermissions()) {
            throw PermissionException("Location permission not granted")
        } else if (!isGpsEnabled()) {
            throw GPSException("GPS not enabled")
        }

        val location = getLastLocation().await() ?: getCurrentLocation().await()
        return location ?: throw LocationException("Can not retrieve location")
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation(): Task<Location> = fusedLocationProviderClient.lastLocation

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation(): Task<Location> =
            fusedLocationProviderClient.getCurrentLocation(
                    PRIORITY_HIGH_ACCURACY,
                    CancellationTokenSource().token
            )

    @SuppressLint("MissingPermission")
    private suspend fun Task<Location>.await(): GeoLocation? {
        return suspendCancellableCoroutine { continuation ->
            if (isComplete) {
                if (isSuccessful) {
                    continuation.resume(result.toGeoLocation()) {}
                } else if (isCanceled) {
                    continuation.resume(null) {}
                }
                return@suspendCancellableCoroutine
            }
            addOnSuccessListener { location ->
                if (continuation.isActive) {
                    if (location == null) {
                        continuation.resume(null) {}
                    } else {
                        continuation.resume(location.toGeoLocation()) {}
                    }
                }
            }
            addOnFailureListener {
                if (continuation.isActive) {
                    continuation.resume(null) {}
                }
            }
            addOnCanceledListener {
                if (continuation.isActive) {
                    continuation.cancel()
                }
            }
        }
    }

    private fun isGpsEnabled() =
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                    locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    private fun hasPermissions(): Boolean {
        val hasAccessFineLocationPermission =
                application.checkSelfPermission(ACCESS_FINE_LOCATION) == PERMISSION_GRANTED
        val hasAccessCoarseLocationPermission =
                application.checkSelfPermission(ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED

        return hasAccessFineLocationPermission || hasAccessCoarseLocationPermission
    }

    private fun Location.toGeoLocation() = GeoLocation(
            latitude = latitude,
            longitude = longitude
    )
}
