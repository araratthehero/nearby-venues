package com.mnatsakanyan.android.nearbyvenues.data.venue.datasource

import com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.query.VenueRecommendationsQueryBuilder
import com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.network.PlacesService
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Venue
import javax.inject.Inject

internal class VenueDataSourceImpl @Inject constructor(
        private val placesService: PlacesService
) : VenueDataSource {

    override suspend fun getVenueRecommendations(location: GeoLocation): List<Venue>? {
        val query = VenueRecommendationsQueryBuilder().setLatitudeLongitude(location.latitude,
                                                                            location.longitude)
                .build()
        return placesService.getVenueRecommendations(query).results
    }
}
