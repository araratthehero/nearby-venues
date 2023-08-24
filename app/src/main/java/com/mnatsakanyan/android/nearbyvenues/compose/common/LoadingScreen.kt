package com.mnatsakanyan.android.nearbyvenues.compose.common

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mnatsakanyan.android.nearbyvenues.R
import com.mnatsakanyan.android.nearbyvenues.compose.theme.Dimen.paddingSmall
import com.mnatsakanyan.android.nearbyvenues.compose.theme.Dimen.spinnerSize

@Composable
internal fun LoadingScreen(
        modifier: Modifier = Modifier,
        text: String = stringResource(id = R.string.loading_text)
) {
    MessageScreen(
            modifier = modifier.fillMaxSize(),
            header = {
                LoadingAnimation()
            },
            text = text
    )
}

@Composable
fun LoadingAnimation(
        modifier: Modifier = Modifier,
        circleColor: Color = colorScheme.onBackground,
        animationDelay: Int = 2000
) {
    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                    animation = tween(animationDelay)
            )
    )

    Box(
            modifier = modifier
                    .size(size = spinnerSize)
                    .scale(scale = scale)
                    .border(
                            width = paddingSmall,
                            color = circleColor.copy(alpha = 1 - scale),
                            shape = CircleShape
                    )
    )
}

@Preview
@Composable
private fun LoadingScreenPreview() {
    LoadingScreen()
}
