package com.mnatsakanyan.android.nearbyvenues.domain.venue.fake

import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation
import com.mnatsakanyan.android.nearbyvenues.domain.venue.VenueRecommendationsRepository
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.GeoCode
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Location
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Main
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Venue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeVenueRecommendationsRepository : VenueRecommendationsRepository {

    private var venues = listOf<Venue>()

    override fun getVenueRecommendations(location: GeoLocation): Flow<List<Venue>?> = flow {
        emit(venues)
    }

    fun setVenuesByName(venueNames: List<String>) {
        venues = venueNames.map { name -> generateVenue(name) }
    }

    private fun generateVenue(name: String) = Venue(
            categories = listOf(),
            distance = 0,
            geocode = GeoCode(Main(52.380190, 4.902973)),
            location = Location("", "", "", ""),
            name = name
    )
}
