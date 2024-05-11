package com.compose.presentation.main.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.R
import com.compose.data.network.utils.Result
import com.compose.domain.utils.Constants
import com.compose.presentation.main.container.viewmodel.ContainerState
import com.compose.presentation.main.container.viewmodel.ContainerViewModel
import com.compose.ui.utils.launchWeb
import com.compose.ui.utils.showToast
import com.compose.ui.widgets.DetailsTopBar
import com.compose.ui.widgets.Dialog
import com.compose.ui.widgets.ProgressIndicator

@Composable
fun DetailScreen(
    onBack: () -> Unit,
    viewModel: ContainerViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var show by rememberSaveable { mutableStateOf(true) }
    val state = viewModel.state.observeAsState(ContainerState.Loading(isLoading = true)).value

    Column(modifier = Modifier.fillMaxSize()) {
        DetailsTopBar(
            title = stringResource(id = R.string.character_detail),
            onBack = onBack,
            onFavorite = {},
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
                is ContainerState.Loading ->
                    ProgressIndicator(
                        isLoading = state.isLoading,
                        modifier = Modifier.align(Alignment.Center)
                    )

                is ContainerState.Data ->
                    CardDetail(state.character)

                is ContainerState.Error ->
                    Dialog(
                        isShow = show,
                        icon = Icons.AutoMirrored.Filled.Message,
                        text = state.message,
                        onConfirmation = {
                            onBack()
                            show = false
                        },
                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDetailScreen() {
    DetailScreen(onBack = {})
}

