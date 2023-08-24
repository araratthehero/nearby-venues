package com.mnatsakanyan.android.nearbyvenues.data.location.datasource.fake

import com.mnatsakanyan.android.nearbyvenues.data.location.datasource.LocationDataSource
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation

internal class FakeFailedLocationDataSource : LocationDataSource {

    private var exception = Exception("Some exception when fetching last known exception")

    override suspend fun getLocation(): GeoLocation {
        throw exception
    }

    fun setException(exception: Exception) {
        this.exception = exception
    }
}
