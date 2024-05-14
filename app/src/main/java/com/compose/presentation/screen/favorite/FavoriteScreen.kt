package com.compose.presentation.screen.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.R
import com.compose.domain.model.Character
import com.compose.presentation.common.CharacterShimmerEffect
import com.compose.presentation.common.EmptyScreen
import com.compose.presentation.screen.favorite.viewmodel.FavoriteState
import com.compose.presentation.screen.favorite.viewmodel.FavoriteViewModel
import com.compose.ui.theme.typography
import com.compose.ui.widgets.BottomSheet

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    innerPadding: PaddingValues,
    onCharacterClick: (Character) -> Unit,
) {
    var show by rememberSaveable { mutableStateOf(true) }
    val state = viewModel.state.observeAsState(FavoriteState.Loading(isLoading = true)).value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Text(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding)),
            text = stringResource(R.string.favorites),
            style = typography.titleLarge,
        )
        Box(modifier = Modifier.fillMaxSize()) {
            when (state) {
                is FavoriteState.Loading ->
                    CharacterShimmerEffect(state.isLoading)

                is FavoriteState.Data -> {
                    if (state.characters.isNotEmpty()) {
                        FavoriteList(
                            state.characters,
                            onCharacterClick = { character ->
                                onCharacterClick(character)
                            },
                        )
                    } else {
                        EmptyScreen(
                            icon = R.drawable.il_logo_words,
                            message = stringResource(R.string.you_dont_have_favorite_characters),
                        )
                    }
                }

                is FavoriteState.Error ->
                    BottomSheet(
                        isShow = show,
                        icon = Icons.AutoMirrored.Filled.Message,
                        message = state.message,
                        onDismiss = {
                            show = false
                        },
                    )
            }
        }
    }
}
