package com.compose.presentation.main.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.compose.R
import com.compose.domain.model.Character
import com.compose.ui.theme.typography
import com.compose.ui.utils.setValue
import com.compose.ui.widgets.LoadCircularImage
import com.compose.ui.widgets.animatedColorText

@Composable
fun CardDetail(character: Character) {
    character.apply {
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
                    text = name.setValue(),
                    style = typography.titleLarge,
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.padding_small)),
                    textAlign = TextAlign.Center,
                    text = specie.setValue(),
                    style = typography.labelMedium,
                )
                LoadCircularImage(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_big))
                        .padding(dimensionResource(id = R.dimen.padding)),
                    data = image,
                    placeholder = android.R.drawable.progress_horizontal,
                    error = android.R.drawable.presence_offline,
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
                            .size(dimensionResource(id = R.dimen.icon))
                            .align(Alignment.CenterVertically),
                        imageVector = Icons.Default.Circle,
                        tint = animatedColorText(status),
                        contentDescription = stringResource(R.string.circle_icon),
                    )
                    Text(
                        text = status.toString(),
                        maxLines = 1,
                        softWrap = true,
                        style = typography.labelMedium,
                    )
                }
            }
        }
    }
}
