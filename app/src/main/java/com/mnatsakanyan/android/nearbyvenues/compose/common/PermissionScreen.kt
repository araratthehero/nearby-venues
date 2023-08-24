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
internal fun PermissionScreen(
        modifier: Modifier = Modifier,
        text: String,
        painter: Painter,
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
                    Text(text = stringResource(id = R.string.permission_grant))
                }
            }
    )
}

@Preview
@Composable
private fun PermissionScreenPreview() {
    PermissionScreen(
            text = "Permission is not granted",
            painter = painterResource(id = R.drawable.ic_no_permission)
    ) {}
}
