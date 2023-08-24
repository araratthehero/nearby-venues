package com.mnatsakanyan.android.nearbyvenues.data.location.repository

import com.mnatsakanyan.android.nearbyvenues.data.location.datasource.LocationDataSource
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation
import com.mnatsakanyan.android.nearbyvenues.domain.location.LocationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class LocationRepositoryImpl @Inject constructor(
        private val dataSource: LocationDataSource,
        private val dispatcher: CoroutineDispatcher
) : LocationRepository {
    override fun getLocation(): Flow<GeoLocation> = flow {
        emit(dataSource.getLocation())
    }.flowOn(dispatcher)
}
