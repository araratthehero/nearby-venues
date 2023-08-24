package com.mnatsakanyan.android.nearbyvenues.domain.location.fake

import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation
import com.mnatsakanyan.android.nearbyvenues.domain.location.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeFailedLocationRepository : LocationRepository {

    private var exception = Exception("Some exception when fetching Location")

    override fun getLocation(): Flow<GeoLocation> = flow {
        throw exception
    }

    fun setException(exception: Exception) {
        this.exception = exception
    }
}
