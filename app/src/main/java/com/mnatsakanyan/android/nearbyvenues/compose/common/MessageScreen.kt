package com.mnatsakanyan.android.nearbyvenues.compose.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import com.mnatsakanyan.android.nearbyvenues.compose.theme.Dimen.paddingLarge
import com.mnatsakanyan.android.nearbyvenues.compose.theme.Dimen.paddingMedium

@Composable
internal fun MessageScreen(
        modifier: Modifier = Modifier,
        text: String,
        header: @Composable () -> Unit = {},
        footer: @Composable () -> Unit = {}
) {
    Column(
            modifier = modifier.background(colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        header()
        Text(
                modifier = Modifier.padding(paddingLarge),
                style = typography.titleMedium,
                color = colorScheme.onBackground,
                text = text,
                textAlign = TextAlign.Center
        )
        footer()
    }
}

@Composable
internal fun MessageScreen(
        modifier: Modifier = Modifier,
        text: String,
        painter: Painter? = null,
        footer: @Composable () -> Unit = {}
) {
    MessageScreen(
            modifier = modifier,
            text = text,
            header = {
                painter?.let { painter ->
                    Icon(
                            modifier = Modifier.padding(paddingMedium),
                            painter = painter,
                            contentDescription = null,
                            tint = colorScheme.onBackground
                    )
                }
            },
            footer = footer
    )
}
