package com.compose.presentation.container.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.compose.R
import com.compose.model.Character
import com.compose.ui.theme.typography
import com.compose.ui.utils.setStatusColor
import com.compose.ui.widgets.LoadImageBig
import com.compose.ui.widgets.TopBarNavigation
import com.compose.ui.widgets.animatedColorText

@Composable
fun DetailScreen(
    characterId: Int,
    onBack: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarNavigation(stringResource(id = R.string.character_detail)) {
            onBack()
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding)),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding)),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Character",
                    style = typography.titleLarge,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = characterId.toString(),
                    style = typography.labelMedium,
                )
                LoadImageBig(
                    modifier = Modifier
                        .padding((dimensionResource(id = R.dimen.padding)))
                        .clip(MaterialTheme.shapes.medium),
                    image = R.drawable.il_character,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(id = R.dimen.padding_small),
                        Alignment.CenterHorizontally,
                    ),
                ) {
                    Icon(
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.icon_small))
                            .align(Alignment.CenterVertically),
                        imageVector = Icons.Default.Circle,
                        tint = setStatusColor(Character.Status.ALIVE),
                        contentDescription = stringResource(R.string.circle_icon),
                    )
                    Text(
                        text = "Alive",
                        maxLines = 1,
                        softWrap = true,
                        style = typography.labelMedium,
                        color = animatedColorText(Character.Status.ALIVE),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDetailScreen() {
    DetailScreen(0, onBack = {})
}

