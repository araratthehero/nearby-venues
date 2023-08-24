package com.mnatsakanyan.android.nearbyvenues.data.venue.network

import com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.query.VenueRecommendationsQueryBuilder
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class VenueRecommendationsQueryBuilderTest {

    @Test
    fun buildCreatesQueryWithLatitudeLongitude() {
        val queryBuilder = VenueRecommendationsQueryBuilder()
        val latitude = 1.111
        val longitude = 2.222

        queryBuilder.setLatitudeLongitude(latitude, longitude)
        val query = queryBuilder.build()

        assertEquals("$latitude,$longitude", query["ll"])
    }

    @Test
    fun buildCreatesQueryWithoutLatitudeLongitude() {
        val queryBuilder = VenueRecommendationsQueryBuilder()

        val query = queryBuilder.build()

        assertNull(query["ll"])
    }
}
