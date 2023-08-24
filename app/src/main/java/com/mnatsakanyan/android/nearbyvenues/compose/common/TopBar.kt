package com.mnatsakanyan.android.nearbyvenues.compose.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mnatsakanyan.android.nearbyvenues.compose.theme.Dimen.paddingMedium

@Composable
internal fun VenuesTopBar(
        modifier: Modifier = Modifier,
        title: String,
        subTitle: String? = null,
        action: @Composable (() -> Unit)? = null
) {
    Row(
            modifier = modifier
                    .fillMaxWidth()
                    .padding(paddingMedium)
    ) {
        Column(
                modifier = Modifier
                        .padding(paddingMedium)
                        .weight(1f)
        ) {
            Text(
                    text = title,
                    textAlign = TextAlign.Start,
                    style = typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(paddingMedium))

            subTitle?.let { subTitle ->
                Text(
                        text = subTitle,
                        textAlign = TextAlign.Start,
                        style = typography.titleMedium
                )
            }
        }
        action?.let { action ->
            Row(
                    modifier = Modifier
                            .padding(top = paddingMedium, end = paddingMedium),
                    horizontalArrangement = Arrangement.End
            ) {
                action()
            }
        }
    }
}

@Preview
@Composable
fun VenuesTopBarPreview() {
    VenuesTopBar(
            title = "Hey visitor",
            subTitle = "Here you can find some venues near you!"
    )
}

@Preview
@Composable
fun VenuesTopBarWithoutSubTitlePreview() {
    VenuesTopBar(title = "Hey visitor")
}

@Preview
@Composable
fun VenuesTopBarWithActionPreview() {
    VenuesTopBar(
            title = "Hey visitor",
            subTitle = "Here you can find some venues near you!",
            action = {
                Icon(
                        modifier = Modifier.padding(paddingMedium),
                        imageVector = Icons.Default.Close,
                        contentDescription = null
                )
            }
    )
}
