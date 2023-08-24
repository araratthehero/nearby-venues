package com.mnatsakanyan.android.nearbyvenues.nearbyvenues

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mnatsakanyan.android.nearbyvenues.R
import com.mnatsakanyan.android.nearbyvenues.compose.common.ErrorScreen
import com.mnatsakanyan.android.nearbyvenues.compose.common.LoadingScreen
import com.mnatsakanyan.android.nearbyvenues.compose.common.MessageScreen
import com.mnatsakanyan.android.nearbyvenues.compose.common.VenuesTopBar
import com.mnatsakanyan.android.nearbyvenues.compose.theme.Dimen.paddingMedium
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Venue
import com.mnatsakanyan.android.nearbyvenues.nearbyvenues.NearbyVenuesUiState.Error
import com.mnatsakanyan.android.nearbyvenues.nearbyvenues.NearbyVenuesUiState.GPSError
import com.mnatsakanyan.android.nearbyvenues.nearbyvenues.NearbyVenuesUiState.Loading
import com.mnatsakanyan.android.nearbyvenues.nearbyvenues.NearbyVenuesUiState.PermissionError
import com.mnatsakanyan.android.nearbyvenues.nearbyvenues.NearbyVenuesUiState.Venues
import com.mnatsakanyan.android.nearbyvenues.nearbyvenues.compose.EmptyScreen
import com.mnatsakanyan.android.nearbyvenues.nearbyvenues.compose.VenueListItem
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@Composable
internal fun NearbyVenuesScreen(
        modifier: Modifier = Modifier,
        viewModel: NearbyVenuesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NearbyVenuesScreen(
            modifier = modifier,
            uiState = uiState,
            onRetryButtonClick = viewModel::onRefresh,
            onRefreshButtonClick = viewModel::onRefresh,
            onPermissionGranted = viewModel::onRefresh
    )
}

@Composable
internal fun NearbyVenuesScreen(
        modifier: Modifier = Modifier,
        uiState: NearbyVenuesUiState,
        onRetryButtonClick: () -> Unit,
        onRefreshButtonClick: () -> Unit,
        onPermissionGranted: () -> Unit
) {
    Scaffold(
            modifier = modifier,
            topBar = {
                VenuesTopBar(
                        title = stringResource(id = R.string.nearby_venues_title),
                        subTitle = stringResource(id = R.string.nearby_venues_sub_title),
                        action = {
                            Icon(
                                    modifier = Modifier
                                            .clickable(onClick = onRefreshButtonClick)
                                            .padding(paddingMedium),
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = null,
                                    tint = colorScheme.primary
                            )
                        }
                )
            },
            content = { padding ->
                NearbyVenuesContent(
                        modifier = modifier.padding(padding),
                        uiState = uiState,
                        onRetryButtonClick = onRetryButtonClick,
                        onPermissionGranted = onPermissionGranted
                )
            }
    )
}

@Composable
internal fun NearbyVenuesContent(
        modifier: Modifier = Modifier,
        uiState: NearbyVenuesUiState,
        onRetryButtonClick: () -> Unit,
        onPermissionGranted: () -> Unit
) {
    when (uiState) {
        is Loading -> LoadingScreen(modifier = modifier)
        is Error -> ErrorScreen(
                modifier = modifier,
                text = stringResource(id = R.string.nearby_venues_loading_failed_text),
                onButtonClick = onRetryButtonClick
        )
        is PermissionError -> NearbyVenuesPermissionContent(
                modifier = modifier,
                onPermissionGranted = onPermissionGranted
        )
        is GPSError -> ErrorScreen(
                modifier = modifier,
                text = stringResource(id = R.string.nearby_venues_location_disabled),
                painter = painterResource(id = R.drawable.ic_no_location),
                onButtonClick = onRetryButtonClick
        )

        is Venues -> if (uiState.venues.isNullOrEmpty()) {
            EmptyScreen(
                    modifier = modifier,
                    text = stringResource(id = R.string.nearby_venues_empty_text)
            )
        } else {
            NearbyVenuesVenuesContent(
                    modifier = modifier,
                    venues = uiState.venues
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun NearbyVenuesPermissionContent(
        modifier: Modifier = Modifier,
        onPermissionGranted: () -> Unit
) {
    val locationPermissions = rememberMultiplePermissionsState(
            permissions = listOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
            )
    )

    LaunchedEffect(locationPermissions.allPermissionsGranted) {
        if (locationPermissions.allPermissionsGranted) {
            onPermissionGranted.invoke()
        }
    }

    if (locationPermissions.shouldShowRationale) {
        ErrorScreen(
                modifier = modifier,
                text = stringResource(id = R.string.nearby_venues_permission_description),
                painter = painterResource(id = R.drawable.ic_no_permission),
                buttonText = stringResource(id = R.string.permission_grant),
                onButtonClick = {
                    locationPermissions.launchMultiplePermissionRequest()
                }
        )
    } else {
        MessageScreen(
                modifier = modifier.fillMaxSize(),
                text = stringResource(id = R.string.nearby_venues_permission_denied_description),
                painter = painterResource(id = R.drawable.ic_no_permission),
        )
    }
}

@Composable
internal fun NearbyVenuesVenuesContent(
        modifier: Modifier = Modifier,
        venues: List<Venue>
) {
    LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                    horizontal = paddingMedium,
                    vertical = paddingMedium
            )
    ) {
        items(
                count = venues.size
        ) { index ->
            VenueListItem(
                    item = venues[index]
            )
        }
    }
}
