package com.mnatsakanyan.android.nearbyvenues.domain.location.fake

import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation
import com.mnatsakanyan.android.nearbyvenues.domain.location.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeLocationRepository : LocationRepository {
    override fun getLocation(): Flow<GeoLocation> = flow {
        emit(GeoLocation(52.380190, 4.902973))
    }
}
