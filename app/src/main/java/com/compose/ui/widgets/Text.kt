package com.compose.ui.widgets

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.compose.R
import com.compose.domain.model.Character

@Composable
fun animatedColorText(status: Character.Status): Color {
    val infiniteTransition =
        rememberInfiniteTransition(label = stringResource(R.string.infinite_transition))
    val statusColors = when (status) {
        Character.Status.ALIVE -> Pair(MaterialTheme.colorScheme.onBackground, Color(0XFF78BA46))
        Character.Status.DEAD -> Pair(MaterialTheme.colorScheme.onBackground, Color(0xFFBA1A1A))
        Character.Status.UNKNOWN -> Pair(MaterialTheme.colorScheme.onBackground, Color(0xFF74796D))
    }
    val animatedColor by infiniteTransition.animateColor(
        initialValue = statusColors.first,
        targetValue = statusColors.second,
        animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse),
        label = stringResource(R.string.animated_color)
    )
    return animatedColor
}
