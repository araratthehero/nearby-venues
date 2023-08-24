package com.mnatsakanyan.android.nearbyvenues.nearbyvenues.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mnatsakanyan.android.nearbyvenues.nearbyvenues.NearbyVenuesScreen

const val nearbyVenuesNavigationRoute = "nearby_venues_route"

fun NavGraphBuilder.nearbyVenuesScreen() {
    composable(
            route = nearbyVenuesNavigationRoute
    ) {
        NearbyVenuesScreen()
    }
}
