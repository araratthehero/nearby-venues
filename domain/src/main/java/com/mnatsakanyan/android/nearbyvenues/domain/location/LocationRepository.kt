package com.mnatsakanyan.android.nearbyvenues.domain.location

import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocation(): Flow<GeoLocation>
}
