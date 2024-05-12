package com.compose.presentation.main.common

import android.annotation.SuppressLint
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.R

@Composable
fun CharacterShimmerEffect(isLoading: Boolean) {
    if (isLoading) {
        Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding))) {
            repeat(5) {
                CharacterShimmer(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding)))
            }
        }
    }
}

@Composable
private fun CharacterShimmer(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.image_small))
                    .shimmer()
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(dimensionResource(id = R.dimen.padding)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .shimmer()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(15.dp)
                        .shimmer()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .padding(vertical = dimensionResource(id = R.dimen.padding_small))
                        .height(16.dp)
                        .shimmer()
                )
            }
        }
    }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
private fun Modifier.shimmer() = composed {
    val transition = rememberInfiniteTransition(
        label = stringResource(R.string.shimmer_transition)
    )
    val alpha = transition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse,
        ),
        label = stringResource(R.string.shimmer),
    ).value
    background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
}

@Preview(showBackground = true)
@Composable
private fun PreviewCharacterShimmer(modifier: Modifier = Modifier) {
    CharacterShimmer(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding)))
}
