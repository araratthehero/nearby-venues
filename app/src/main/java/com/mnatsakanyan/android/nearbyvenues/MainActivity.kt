package com.mnatsakanyan.android.nearbyvenues

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mnatsakanyan.android.nearbyvenues.compose.theme.VenuesTheme
import com.mnatsakanyan.android.nearbyvenues.navigation.VenuesNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VenuesTheme {
                VenuesNavHost()
            }
        }
    }
}
