package com.mnatsakanyan.android.nearbyvenues.data.location.datasource.fake

import com.mnatsakanyan.android.nearbyvenues.data.location.datasource.LocationDataSource
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation

internal class FakeLocationDataSource : LocationDataSource {

    private var location = GeoLocation(52.380190, 4.902973)

    override suspend fun getLocation(): GeoLocation {
        return location
    }

    fun setLocation(geoLocation: GeoLocation) {
        location = geoLocation
    }
}
