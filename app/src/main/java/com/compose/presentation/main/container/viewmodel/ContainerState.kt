package com.compose.presentation.main.container.viewmodel

import com.compose.domain.model.Character

sealed class ContainerState {
    internal data class Loading(
        val isLoading: Boolean,
    ) : ContainerState()

    internal data class Data(
        val character: Character,
    ) : ContainerState()

    internal data class Error(
        val message: String,
    ) : ContainerState()
}
