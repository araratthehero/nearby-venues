package com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.network

import com.mnatsakanyan.android.nearbyvenues.data.venue.model.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.QueryMap

internal interface PlacesService {
    /**
     * Get venue recommendations.
     *
     * See [the docs](https://developer.foursquare.com/reference/places-nearby)
     */
    @GET("places/nearby")
    suspend fun getVenueRecommendations(@QueryMap
                                        query: Map<String, String>): ResponseWrapper
}
