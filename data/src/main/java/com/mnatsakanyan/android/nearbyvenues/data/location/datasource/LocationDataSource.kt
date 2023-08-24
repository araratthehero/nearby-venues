package com.mnatsakanyan.android.nearbyvenues.data.location.datasource

import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation

internal interface LocationDataSource {

    suspend fun getLocation(): GeoLocation
}
