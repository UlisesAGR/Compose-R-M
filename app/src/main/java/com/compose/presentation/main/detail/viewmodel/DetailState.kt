package com.compose.presentation.main.detail.viewmodel

import com.compose.domain.model.Character

sealed class DetailState {
    internal data class Loading(
        val isLoading: Boolean,
    ) : DetailState()

    internal data class Data(
        val character: Character,
    ) : DetailState()

    internal data class Error(
        val message: String,
    ) : DetailState()
}
