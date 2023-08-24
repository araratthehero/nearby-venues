package com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.fake

import com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.VenueDataSource
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Venue

internal class FakeFailedVenueDataSource : VenueDataSource {

    private var exception = Exception("Some exception when fetching venue recommendations")

    override suspend fun getVenueRecommendations(location: GeoLocation): List<Venue>? {
        throw exception
    }

    fun setException(exception: Exception) {
        this.exception = exception
    }
}
