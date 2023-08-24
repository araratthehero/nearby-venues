package com.mnatsakanyan.android.nearbyvenues.domain.venue.fake

import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Venue
import com.mnatsakanyan.android.nearbyvenues.domain.venue.VenueRecommendationsRepository
import kotlinx.coroutines.flow.Flow

internal class FakeFailedVenueRecommendationsRepository : VenueRecommendationsRepository {

    private var exception = Exception("Some exception when fetching venue recommendations")

    override fun getVenueRecommendations(location: GeoLocation): Flow<List<Venue>> {
        throw exception
    }

    fun setException(exception: Exception) {
        this.exception = exception
    }
}
