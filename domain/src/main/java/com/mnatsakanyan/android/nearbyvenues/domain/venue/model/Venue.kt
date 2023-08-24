package com.mnatsakanyan.android.nearbyvenues.domain.venue.model

data class Venue(
        val categories: List<Category>,
        val distance: Int,
        val geocode: GeoCode?,
        val location: Location,
        val name: String
)
