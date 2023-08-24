package com.mnatsakanyan.android.nearbyvenues.domain.venue.fake

import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Result
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Result.Loading
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Venue
import com.mnatsakanyan.android.nearbyvenues.domain.usecase.GetVenueRecommendationsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetVenueRecommendationsUseCase : GetVenueRecommendationsUseCase {

    private var result: Result<List<Venue>?> = Loading

    override fun invoke(): Flow<Result<List<Venue>?>> = flow {
        emit(result)
    }

    fun setResult(result: Result<List<Venue>?>) {
        this.result = result
    }
}
