package com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.fake

import com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.VenueDataSource
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.GeoCode
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Location
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Main
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Venue

internal class FakeVenueDataSource : VenueDataSource {

    private var venues = listOf<Venue>()

    override suspend fun getVenueRecommendations(location: GeoLocation): List<Venue> = venues

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
