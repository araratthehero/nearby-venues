package com.mnatsakanyan.android.nearbyvenues.data.venue.datasource

import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Venue

internal interface VenueDataSource {

    suspend fun getVenueRecommendations(location: GeoLocation): List<Venue>?
}
