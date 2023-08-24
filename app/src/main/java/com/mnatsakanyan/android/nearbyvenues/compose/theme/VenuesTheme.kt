package com.mnatsakanyan.android.nearbyvenues.compose.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
internal fun VenuesTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit,
) {
    val colorScheme = if (supportsDynamicTheming()) {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (darkTheme) darkColorScheme() else lightColorScheme()
    }

    val systemUiColor = rememberSystemUiController()
    SideEffect {
        with(systemUiColor) {
            setStatusBarColor(colorScheme.background)
            setNavigationBarColor(
                    color = colorScheme.background,
                    navigationBarContrastEnforced = false
            )
        }
    }

    CompositionLocalProvider {
        MaterialTheme(
                colorScheme = colorScheme,
                typography = typography,
                content = content,
        )
    }
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
private fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
