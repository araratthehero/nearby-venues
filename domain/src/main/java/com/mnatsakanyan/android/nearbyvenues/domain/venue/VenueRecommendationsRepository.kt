package com.mnatsakanyan.android.nearbyvenues.domain.venue

import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Venue
import kotlinx.coroutines.flow.Flow

interface VenueRecommendationsRepository {
    fun getVenueRecommendations(location: GeoLocation): Flow<List<Venue>?>
}
