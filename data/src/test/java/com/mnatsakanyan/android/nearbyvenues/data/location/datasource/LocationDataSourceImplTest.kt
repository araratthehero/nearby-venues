package com.mnatsakanyan.android.nearbyvenues.data.location.datasource

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Application
import android.content.pm.PackageManager.PERMISSION_DENIED
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.location.LocationManager
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GPSException
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.LocationException
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.PermissionException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class LocationDataSourceImplTest {

    private val location = mock<Location>()
    private val application = mock<Application>()
    private val fusedLocationProviderClient = mock<FusedLocationProviderClient>()
    private val locationManager = mock<LocationManager>()

    private val locationDataSource = LocationDataSourceImpl(
            application,
            fusedLocationProviderClient,
            locationManager
    )

    @Before
    fun setUp() {
        whenever(application.checkSelfPermission(ACCESS_FINE_LOCATION)).thenReturn(
                PERMISSION_GRANTED)
        whenever(application.checkSelfPermission(ACCESS_COARSE_LOCATION)).thenReturn(
                PERMISSION_GRANTED)
        whenever(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)).thenReturn(
                true)
        whenever(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)).thenReturn(
                true)
        whenever(location.latitude).thenReturn(52.380190)
        whenever(location.longitude).thenReturn(4.902973)
    }

    @Test
    fun getLocationWithFineAndCoarseLocationPermissionsDeniedThrowsException() {
        whenever(application.checkSelfPermission(ACCESS_FINE_LOCATION)).thenReturn(
                PERMISSION_DENIED)
        whenever(application.checkSelfPermission(ACCESS_COARSE_LOCATION)).thenReturn(
                PERMISSION_DENIED)

        assertThrows(PermissionException::class.java) {
            runBlocking {
                locationDataSource.getLocation()
            }
        }
    }

    @Test
    fun getLocationWithOnlyFineLocationPermissionDeniedReturnsLocation() = runTest {
        whenever(application.checkSelfPermission(ACCESS_FINE_LOCATION)).thenReturn(
                PERMISSION_DENIED)

        testGetLastLocationSuccess()
    }

    @Test
    fun getLocationWithOnlyCoarseLocationPermissionDeniedReturnsLocation() = runTest {
        whenever(application.checkSelfPermission(ACCESS_COARSE_LOCATION)).thenReturn(
                PERMISSION_DENIED)

        testGetLastLocationSuccess()
    }

    @Test
    fun getLocationWithNetworkProviderAndGpsProviderDisabledThrowsException() {
        whenever(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)).thenReturn(
                false)
        whenever(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)).thenReturn(
                false)

        assertThrows(GPSException::class.java) {
            runBlocking {
                locationDataSource.getLocation()
            }
        }
    }

    @Test
    fun getLocationWithOnlyNetworkProviderDisabledReturnsLocation() {
        whenever(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)).thenReturn(
                false)

        testGetLastLocationSuccess()
    }

    @Test
    fun getLocationWithOnlyGPSProviderDisabledReturnsLocation() {
        whenever(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)).thenReturn(
                false)

        testGetLastLocationSuccess()
    }

    @Test
    fun getLocationWithLastLocationReturnsLastLocation() = testGetLastLocationSuccess()

    @Test
    fun getLocationWithNullLastLocationReturnsCurrentLocation() = runTest {
        val lastLocationTask = createLocationTask(location).apply {
            whenever(isCanceled).thenReturn(true)
        }
        val currentLocationTask = createLocationTask(location).apply {
            whenever(isSuccessful).thenReturn(true)
        }
        whenever(fusedLocationProviderClient.lastLocation).thenReturn(lastLocationTask)
        whenever(fusedLocationProviderClient.getCurrentLocation(any<Int>(), any())).thenReturn(
                currentLocationTask)

        val result = locationDataSource.getLocation()

        assertTrue(location.latitude.equals(result.latitude))
        assertTrue(location.longitude.equals(result.longitude))
    }

    @Test
    fun getLocationWithNullLastLocationAndErrorThrowsException() = runTest {
        val lastLocationTask = createLocationTask(location).apply {
            whenever(isCanceled).thenReturn(true)
        }
        val currentLocationTask = createLocationTask(location).apply {
            whenever(isCanceled).thenReturn(true)
        }
        whenever(fusedLocationProviderClient.lastLocation).thenReturn(lastLocationTask)
        whenever(fusedLocationProviderClient.getCurrentLocation(any<Int>(), any())).thenReturn(
                currentLocationTask)

        assertThrows(LocationException::class.java) {
            runBlocking {
                locationDataSource.getLocation()
            }
        }
    }

    private fun testGetLastLocationSuccess() = runTest {
        val lastLocationTask = createLocationTask(location).apply {
            whenever(isSuccessful).thenReturn(true)
        }
        whenever(fusedLocationProviderClient.lastLocation).thenReturn(lastLocationTask)

        val result = locationDataSource.getLocation()

        assertTrue(location.latitude.equals(result.latitude))
        assertTrue(location.longitude.equals(result.longitude))
    }

    private fun createLocationTask(location: Location) = mock<Task<Location>>().apply {
        whenever(isComplete).thenReturn(true)
        whenever(result).thenReturn(location)
    }
}
