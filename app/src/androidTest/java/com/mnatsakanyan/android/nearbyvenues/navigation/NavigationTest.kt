package com.mnatsakanyan.android.nearbyvenues.navigation

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.mnatsakanyan.android.nearbyvenues.MainActivity
import com.mnatsakanyan.android.nearbyvenues.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NavigationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun firstScreenIsNearbyVenues() {
        composeTestRule.apply {
            onNodeWithText(composeTestRule.activity.resources.getString(R.string.nearby_venues_sub_title)).assertExists()
        }
    }
}
