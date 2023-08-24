package com.mnatsakanyan.android.nearbyvenues.nearbyvenues.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mnatsakanyan.android.nearbyvenues.R
import com.mnatsakanyan.android.nearbyvenues.compose.common.MessageScreen

@Composable
internal fun EmptyScreen(
        modifier: Modifier = Modifier,
        text: String,
        painter: Painter = painterResource(id = R.drawable.ic_sad_face)
) {
    MessageScreen(
            modifier = modifier
                    .fillMaxSize(),
            text = text,
            painter = painter
    )
}

@Preview
@Composable
private fun EmptyScreenPreview() {
    EmptyScreen(
            text = "Empty text"
    )
}
