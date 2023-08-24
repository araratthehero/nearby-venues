package com.mnatsakanyan.android.nearbyvenues.domain.usecase

import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Result
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Venue
import kotlinx.coroutines.flow.Flow

interface GetVenueRecommendationsUseCase {
    operator fun invoke(): Flow<Result<List<Venue>?>>
}
