package com.compose.presentation.main.favorite.viewmodel

import com.compose.domain.model.Character

sealed class FavoriteState {
    internal data class Loading(
        val isLoading: Boolean,
    ) : FavoriteState()

    internal data class Data(
        val characters: List<Character>,
    ) : FavoriteState()

    internal data class Error(
        val message: String,
    ) : FavoriteState()
}
