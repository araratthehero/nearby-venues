package com.mnatsakanyan.android.nearbyvenues.domain.usecase

import com.mnatsakanyan.android.nearbyvenues.domain.location.LocationRepository
import com.mnatsakanyan.android.nearbyvenues.domain.venue.VenueRecommendationsRepository
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Result
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Venue
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.asResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetVenueRecommendationsUseCaseImpl @Inject constructor(
        private val locationRepository: LocationRepository,
        private val venueRecommendationsRepository: VenueRecommendationsRepository
) : GetVenueRecommendationsUseCase {

    override fun invoke(): Flow<Result<List<Venue>?>> =
            locationRepository.getLocation().flatMapLatest { location ->
                venueRecommendationsRepository.getVenueRecommendations(location)
            }.asResult()
}
