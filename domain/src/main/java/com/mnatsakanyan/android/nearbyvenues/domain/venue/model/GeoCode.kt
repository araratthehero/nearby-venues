package com.mnatsakanyan.android.nearbyvenues.domain.venue.model

data class GeoCode(
        val main: Main?
)

data class Main(
        val latitude: Double,
        val longitude: Double,
)
