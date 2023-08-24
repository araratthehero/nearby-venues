package com.mnatsakanyan.android.nearbyvenues.domain.venue.usecase

import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Result
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GPSException
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.PermissionException
import com.mnatsakanyan.android.nearbyvenues.domain.usecase.GetVenueRecommendationsUseCaseImpl
import com.mnatsakanyan.android.nearbyvenues.domain.location.fake.FakeFailedLocationRepository
import com.mnatsakanyan.android.nearbyvenues.domain.venue.fake.FakeFailedVenueRecommendationsRepository
import com.mnatsakanyan.android.nearbyvenues.domain.location.fake.FakeLocationRepository
import com.mnatsakanyan.android.nearbyvenues.domain.venue.fake.FakeVenueRecommendationsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetVenueRecommendationsUseCaseImplTest {

    private val locationRepository = FakeLocationRepository()
    private val venueRecommendationsRepository = FakeVenueRecommendationsRepository()
    private val failedLocationRepository = FakeFailedLocationRepository()
    private val failedVenueRecommendationsRepository = FakeFailedVenueRecommendationsRepository()

    private val useCase =
            GetVenueRecommendationsUseCaseImpl(locationRepository, venueRecommendationsRepository)

    @Test
    fun invokeWhenCalledReturnsLoading() = runTest {
        val result = useCase.invoke().first()

        assertTrue(result is Result.Loading)
    }

    @Test
    fun invokeReturnsSuccess() = runTest {
        val expectedVenues = listOf("First venue", "Second venue")
        venueRecommendationsRepository.setVenuesByName(expectedVenues)

        val result = useCase.invoke().last()

        assertEquals(expectedVenues, (result as Result.Success).data?.map { it.name })
    }

    @Test
    fun invokeWhenEmptyReturnsSuccess() = runTest {
        venueRecommendationsRepository.setVenuesByName(listOf())

        val result = useCase.invoke().last()

        assert((result as Result.Success).data?.isEmpty() == true)
    }

    @Test
    fun invokeWhenLocationFailsReturnsError() = runTest {
        val failedUseCase = GetVenueRecommendationsUseCaseImpl(failedLocationRepository,
                                                               venueRecommendationsRepository)
        val expectedResult = Exception("Some exception")
        failedLocationRepository.setException(expectedResult)

        val result = failedUseCase.invoke().last()

        assertEquals(expectedResult.message, (result as Result.Error).exception?.message)
    }

    @Test
    fun invokeWhenLocationFailsWithPermissionErrorReturnsPermissionError() = runTest {
        val failedUseCase = GetVenueRecommendationsUseCaseImpl(failedLocationRepository,
                                                               venueRecommendationsRepository)
        val expectedResult = PermissionException("Permission exception")
        failedLocationRepository.setException(expectedResult)

        val result = failedUseCase.invoke().last()

        assertEquals(expectedResult.message, (result as Result.PermissionError).exception?.message)
    }

    @Test
    fun invokeWhenLocationFailsWithGPSErrorReturnsGPSError() = runTest {
        val failedUseCase = GetVenueRecommendationsUseCaseImpl(failedLocationRepository,
                                                               venueRecommendationsRepository)
        val expectedResult = GPSException("GPS exception")
        failedLocationRepository.setException(expectedResult)

        val result = failedUseCase.invoke().last()

        assertEquals(expectedResult.message, (result as Result.GPSError).exception?.message)
    }

    @Test
    fun invokeWhenVenueRecommendationsFailsReturnsError() = runTest {
        val failedUseCase = GetVenueRecommendationsUseCaseImpl(locationRepository,
                                                               failedVenueRecommendationsRepository)
        val expectedResult = Exception("Some exception")
        failedVenueRecommendationsRepository.setException(expectedResult)

        val result = failedUseCase.invoke().last()

        assertEquals(expectedResult.message, (result as Result.Error).exception?.message)
    }
}
