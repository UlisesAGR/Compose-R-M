package com.compose.presentation.main.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.domain.model.Character
import com.compose.presentation.main.favorite.viewmodel.FavoriteState
import com.compose.presentation.main.favorite.viewmodel.FavoriteViewModel
import com.compose.ui.widgets.Dialog
import com.compose.ui.widgets.ProgressIndicator

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    padding: PaddingValues,
    characterSelected: (Character) -> Unit,
) {
    var show by rememberSaveable { mutableStateOf(true) }
    val state = viewModel.state.observeAsState(FavoriteState.Loading(isLoading = true)).value

    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is FavoriteState.Loading ->
                ProgressIndicator(
                    isLoading = state.isLoading,
                    modifier = Modifier.align(Alignment.Center)
                )

            is FavoriteState.Data ->
                FavoriteList(padding, state.characters, characterSelected)

            is FavoriteState.Error ->
                Dialog(
                    isShow = show,
                    icon = Icons.AutoMirrored.Filled.Message,
                    text = state.message,
                    onConfirmation = {
                        show = false
                    },
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewListScreen() {
    FavoriteScreen(viewModel = hiltViewModel(), PaddingValues()) {}
}
