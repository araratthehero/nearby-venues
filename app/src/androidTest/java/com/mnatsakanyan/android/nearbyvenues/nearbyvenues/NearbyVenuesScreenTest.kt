package com.mnatsakanyan.android.nearbyvenues.nearbyvenues

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.mnatsakanyan.android.nearbyvenues.R
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Category
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Location
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Venue
import org.junit.Rule
import org.junit.Test

class NearbyVenuesScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun progressScreenIsShownWhenUiStateIsLoading() {
        composeTestRule.setContent {
            NearbyVenuesScreen(
                    uiState = NearbyVenuesUiState.Loading,
                    onRetryButtonClick = {},
                    onRefreshButtonClick = {},
                    onPermissionGranted = {}
            )
        }

        composeTestRule
                .onNodeWithText(
                        composeTestRule.activity.resources.getString(R.string.loading_text)
                )
                .assertExists()
    }

    @Test
    fun errorScreenIsShownWhenUiStateIsError() {
        composeTestRule.setContent {
            NearbyVenuesScreen(
                    uiState = NearbyVenuesUiState.Error,
                    onRetryButtonClick = {},
                    onRefreshButtonClick = {},
                    onPermissionGranted = {}
            )
        }

        composeTestRule
                .onNodeWithText(
                        composeTestRule.activity.resources.getString(R.string.nearby_venues_loading_failed_text)
                )
                .assertExists()
    }

    @Test
    fun gpsErrorScreenIsShownWhenUiStateIsGPSError() {
        composeTestRule.setContent {
            NearbyVenuesScreen(
                    uiState = NearbyVenuesUiState.GPSError,
                    onRetryButtonClick = {},
                    onRefreshButtonClick = {},
                    onPermissionGranted = {}
            )
        }

        composeTestRule
                .onNodeWithText(
                        composeTestRule.activity.resources.getString(R.string.nearby_venues_location_disabled)
                )
                .assertExists()
    }

    @Test
    fun emptyScreenIsShownWhenUiStateIsVenuesButListIsEmpty() {
        composeTestRule.setContent {
            NearbyVenuesScreen(
                    uiState = NearbyVenuesUiState.Venues(listOf()),
                    onRetryButtonClick = {},
                    onRefreshButtonClick = {},
                    onPermissionGranted = {}
            )
        }

        composeTestRule
                .onNodeWithText(
                        composeTestRule.activity.resources.getString(R.string.nearby_venues_empty_text)
                )
                .assertExists()
    }

    @Test
    fun venuesContentScreenIsShownWhenUiStateIsVenues() {
        val venueItems = generateRecommendedVenuesList(3)
        composeTestRule.setContent {
            NearbyVenuesScreen(
                    uiState = NearbyVenuesUiState.Venues(venueItems),
                    onRetryButtonClick = {},
                    onRefreshButtonClick = {},
                    onPermissionGranted = {}
            )
        }

        venueItems.forEach { listItem ->
            composeTestRule.onNodeWithText(listItem.name).assertExists()
        }
    }

    @Test
    fun permissionErrorScreenIsShownWhenUiStateIsPermissionError() {
        composeTestRule.setContent {
            NearbyVenuesScreen(
                    uiState = NearbyVenuesUiState.PermissionError,
                    onRetryButtonClick = {},
                    onRefreshButtonClick = {},
                    onPermissionGranted = {}
            )
        }

        composeTestRule.onNode(hasText(
                composeTestRule.activity.resources.getString(R.string.nearby_venues_permission_description)
        ) or hasText(
                composeTestRule.activity.resources.getString(R.string.nearby_venues_permission_denied_description)
        )).assertExists()
    }

    private fun generateRecommendedVenuesList(sizeOfList: Int) =
            (0..sizeOfList).map { index ->
                Venue(
                        categories = listOf(Category(null, null, "Category")),
                        distance = 100,
                        geocode = null,
                        location = Location("Address of the venue", null, null, null),
                        name = "Venue name $index"
                )
            }
}
