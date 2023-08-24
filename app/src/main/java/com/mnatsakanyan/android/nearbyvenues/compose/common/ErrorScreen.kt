package com.mnatsakanyan.android.nearbyvenues.compose.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mnatsakanyan.android.nearbyvenues.R
import com.mnatsakanyan.android.nearbyvenues.compose.theme.Dimen.paddingMedium

@Composable
internal fun ErrorScreen(
        modifier: Modifier = Modifier,
        text: String,
        painter: Painter = painterResource(id = R.drawable.ic_failed_doughnut),
        buttonText: String = stringResource(id = R.string.error_retry),
        onButtonClick: () -> Unit
) {
    MessageScreen(
            modifier = modifier
                    .fillMaxSize(),
            text = text,
            painter = painter,
            footer = {
                Button(
                        modifier = Modifier.padding(paddingMedium),
                        onClick = { onButtonClick.invoke() }
                ) {
                    Text(text = buttonText)
                }
            }
    )
}

@Preview
@Composable
private fun ErrorScreenPreview() {
    ErrorScreen(
            text = "Failed text"
    ) {}
}
