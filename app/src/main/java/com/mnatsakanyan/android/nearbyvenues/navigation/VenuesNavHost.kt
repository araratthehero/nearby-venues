package com.mnatsakanyan.android.nearbyvenues.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mnatsakanyan.android.nearbyvenues.nearbyvenues.navigation.nearbyVenuesNavigationRoute
import com.mnatsakanyan.android.nearbyvenues.nearbyvenues.navigation.nearbyVenuesScreen

@Composable
internal fun VenuesNavHost(
        modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController(),
        startDestination: String = nearbyVenuesNavigationRoute,
) {
    NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier,
    ) {
        nearbyVenuesScreen()
    }
}
