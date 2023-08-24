package com.mnatsakanyan.android.nearbyvenues.data.venue.repository

import com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.fake.FakeFailedVenueDataSource
import com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.fake.FakeVenueDataSource
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class VenueRecommendationsRepositoryImplTest {

    private val location = GeoLocation(52.380190, 4.902973)

    private val dataSource = FakeVenueDataSource()
    private val failedDataSource = FakeFailedVenueDataSource()

    private val repository = VenueRecommendationsRepositoryImpl(dataSource, Dispatchers.Unconfined)
    private val failedRepository =
            VenueRecommendationsRepositoryImpl(failedDataSource, Dispatchers.Unconfined)

    @Test
    fun getVenueRecommendationsReturnsResults() = runTest {
        val expectedVenues = listOf("First venue", "Second venue")
        dataSource.setVenuesByName(expectedVenues)

        val result = repository.getVenueRecommendations(location).first()

        assertEquals(expectedVenues, result?.map { it.name })
    }

    @Test
    fun getVenueRecommendationsWhenEmptyReturnsResults() = runTest {
        dataSource.setVenuesByName(listOf())

        val result = repository.getVenueRecommendations(location).first()

        assert(result?.isEmpty() == true)
    }

    @Test(expected = IllegalArgumentException::class)
    fun getVenueRecommendationsWhenErrorThrowsException() = runTest {
        failedDataSource.setException(IllegalArgumentException("Some exception"))

        failedRepository.getVenueRecommendations(location).first()
    }
}
