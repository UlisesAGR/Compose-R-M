package com.compose.presentation.main.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.R
import com.compose.data.network.utils.Result
import com.compose.domain.model.Character
import com.compose.domain.utils.Constants
import com.compose.presentation.main.container.viewmodel.ContainerState
import com.compose.presentation.main.container.viewmodel.ContainerViewModel
import com.compose.ui.theme.typography
import com.compose.ui.utils.launchWeb
import com.compose.ui.utils.setValue
import com.compose.ui.widgets.DetailsTopBar
import com.compose.ui.widgets.LoadCircularImage
import com.compose.ui.widgets.ProgressIndicator
import com.compose.ui.widgets.ShowToast
import com.compose.ui.widgets.animatedColorText

@Composable
fun DetailScreen(
    onBack: () -> Unit,
    viewModel: ContainerViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state = viewModel.state.observeAsState(ContainerState.Loading(isLoading = true)).value

    Column(modifier = Modifier.fillMaxSize()) {
        DetailsTopBar(
            title = stringResource(id = R.string.character_detail),
            onBack = onBack,
            onFavorite = {},
            onShare = {
                when (context.launchWeb(Constants.URL_DOCUMENTATION)) {
                    is Result.Success -> println("Success")
                    is Result.Error -> println("Error")
                }
            },
        )

        Box(modifier = Modifier.fillMaxSize()) {
            when (state) {
                is ContainerState.Loading ->
                    ProgressIndicator(
                        isLoading = state.isLoading,
                        modifier = Modifier.align(Alignment.Center)
                    )

                is ContainerState.Data ->
                    CardDetail(state.character)

                is ContainerState.Error ->
                    context.ShowToast(text = state.message)
            }
        }
    }
}

@Composable
private fun CardDetail(character: Character) {
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

@Preview(showBackground = true)
@Composable
private fun PreviewDetailScreen() {
    DetailScreen(onBack = {})
}

