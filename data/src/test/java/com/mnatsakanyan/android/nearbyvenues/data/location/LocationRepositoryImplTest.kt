package com.mnatsakanyan.android.nearbyvenues.data.location

import com.mnatsakanyan.android.nearbyvenues.data.location.datasource.fake.FakeFailedLocationDataSource
import com.mnatsakanyan.android.nearbyvenues.data.location.datasource.fake.FakeLocationDataSource
import com.mnatsakanyan.android.nearbyvenues.data.location.repository.LocationRepositoryImpl
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class LocationRepositoryImplTest {
    private val dataSource = FakeLocationDataSource()
    private val failedDataSource = FakeFailedLocationDataSource()

    private val repository = LocationRepositoryImpl(dataSource, Unconfined)
    private val fakeRepository = LocationRepositoryImpl(failedDataSource, Unconfined)

    @Test
    fun getLocationReturnsResult() = runTest {
        val expectedLocation = GeoLocation(52.380190, 4.902973)
        dataSource.setLocation(expectedLocation)

        val result = repository.getLocation().first()

        assertEquals(expectedLocation, result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun getLocationWhenErrorThrowsException() = runTest {
        failedDataSource.setException(IllegalArgumentException("Some exception"))

        fakeRepository.getLocation().first()
    }
}
