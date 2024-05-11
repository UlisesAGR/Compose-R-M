package com.compose.presentation.main.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.compose.R
import com.compose.domain.model.Character
import com.compose.ui.theme.typography
import com.compose.ui.utils.setValue
import com.compose.ui.widgets.LoadImage
import com.compose.ui.widgets.animatedColorText

@Composable
fun ItemCharacter(
    character: Character,
    onClick: () -> Unit,
) {
    var clickEnable by remember { mutableStateOf(true) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = clickEnable) {
                clickEnable = false
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            LoadImage(
                modifier = Modifier.size(dimensionResource(id = R.dimen.image_small)),
                data = character.image,
                placeholder = android.R.drawable.progress_horizontal,
                error = android.R.drawable.presence_offline,
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(dimensionResource(id = R.dimen.padding)),
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = character.name.setValue(),
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis,
                    style = typography.titleMedium,
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.padding_small)),
                    text = character.specie.setValue(),
                    maxLines = 1,
                    softWrap = true,
                    style = typography.labelSmall,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.padding)),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
                ) {
                    Icon(
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.icon_small))
                            .align(Alignment.CenterVertically),
                        imageVector = Icons.Default.Circle,
                        tint = animatedColorText(character.status),
                        contentDescription = stringResource(R.string.circle_icon),
                    )
                    Text(
                        text = character.status.toString(),
                        maxLines = 1,
                        softWrap = true,
                        style = typography.labelSmall,
                    )
                }
            }
        }
    }
}
