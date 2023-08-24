package com.mnatsakanyan.android.nearbyvenues.nearbyvenues

import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Venue

internal sealed interface NearbyVenuesUiState {
    object Loading : NearbyVenuesUiState

    object Error : NearbyVenuesUiState

    object PermissionError : NearbyVenuesUiState

    object GPSError : NearbyVenuesUiState

    data class Venues(
            val venues: List<Venue>?,
    ) : NearbyVenuesUiState
}
