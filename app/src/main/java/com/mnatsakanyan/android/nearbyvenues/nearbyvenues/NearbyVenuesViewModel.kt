package com.mnatsakanyan.android.nearbyvenues.nearbyvenues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Result
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Result.Error
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Result.GPSError
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Result.Loading
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Result.PermissionError
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Result.Success
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Venue
import com.mnatsakanyan.android.nearbyvenues.domain.usecase.GetVenueRecommendationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class NearbyVenuesViewModel @Inject constructor(
        getVenueRecommendationsUseCase: GetVenueRecommendationsUseCase
) : ViewModel() {

    private val _triggerGetVenueRecommendationsUseCase = MutableSharedFlow<Unit>(replay = 1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<NearbyVenuesUiState> = _triggerGetVenueRecommendationsUseCase
            .flatMapLatest { getVenueRecommendationsUseCase().mapToUiState() }
            .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.Lazily,
                    initialValue = NearbyVenuesUiState.Loading,
            )

    init {
        onRefresh()
    }

    fun onRefresh() {
        _triggerGetVenueRecommendationsUseCase.tryEmit(Unit)
    }

    private fun Flow<Result<List<Venue>?>>.mapToUiState() = map { result ->
        when (result) {
            is Success -> NearbyVenuesUiState.Venues(venues = result.data)
            is Loading -> NearbyVenuesUiState.Loading
            is Error -> NearbyVenuesUiState.Error
            is PermissionError -> NearbyVenuesUiState.PermissionError
            is GPSError -> NearbyVenuesUiState.GPSError
        }
    }
}
