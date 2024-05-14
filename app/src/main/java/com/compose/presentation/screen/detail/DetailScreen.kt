package com.compose.presentation.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.R
import com.compose.data.network.utils.Result
import com.compose.domain.utils.Constants
import com.compose.presentation.screen.detail.viewmodel.DetailState
import com.compose.presentation.screen.detail.viewmodel.DetailViewModel
import com.compose.ui.utils.UIComponent
import com.compose.ui.utils.launchWeb
import com.compose.ui.utils.showToast
import com.compose.ui.widgets.BottomSheet
import com.compose.ui.widgets.DetailsTopBar
import com.compose.ui.widgets.ProgressIndicator

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    var show by rememberSaveable { mutableStateOf(true) }

    val state = viewModel.state.observeAsState(DetailState.Loading(isLoading = true)).value
    val sideEffect = viewModel.components.observeAsState().value

    LaunchedEffect(key1 = sideEffect) {
        sideEffect?.let {
            when (sideEffect) {
                is UIComponent.Toast ->
                    context.showToast(sideEffect.message)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        DetailsTopBar(
            title = stringResource(id = R.string.character_detail),
            onBack = onBack,
            onFavorite = {
                viewModel.existInFavorites()
            },
            onShare = {
                context.apply {
                    when (launchWeb(Constants.URL_DOCUMENTATION)) {
                        is Result.Success ->
                            showToast(text = getString(R.string.open_documentation_api))

                        is Result.Error ->
                            showToast(text = getString(R.string.error_open_web))
                    }
                }
            },
        )
        Box(modifier = Modifier.fillMaxSize()) {
            when (state) {
                is DetailState.Loading ->
                    ProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        isLoading = state.isLoading,
                    )

                is DetailState.Data ->
                    CardDetail(character = state.character)

                is DetailState.Error ->
                    BottomSheet(
                        isShow = show,
                        icon = Icons.AutoMirrored.Filled.Message,
                        message = state.message,
                        onDismiss = {
                            onBack()
                            show = false
                        },
                    )
            }
        }
    }
}
