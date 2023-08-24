package com.mnatsakanyan.android.nearbyvenues.data.venue.repository

import com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.VenueDataSource
import com.mnatsakanyan.android.nearbyvenues.domain.location.model.GeoLocation
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Venue
import com.mnatsakanyan.android.nearbyvenues.domain.venue.VenueRecommendationsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class VenueRecommendationsRepositoryImpl @Inject constructor(
        private val venueDataSource: VenueDataSource,
        private val dispatcher: CoroutineDispatcher
) : VenueRecommendationsRepository {

    override fun getVenueRecommendations(location: GeoLocation): Flow<List<Venue>?> = flow {
        emit(venueDataSource.getVenueRecommendations(location))
    }.flowOn(dispatcher)
}
