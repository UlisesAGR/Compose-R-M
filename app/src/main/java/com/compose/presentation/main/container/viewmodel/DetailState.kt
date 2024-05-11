package com.compose.presentation.main.container.viewmodel

import com.compose.data.network.utils.DataError.Network
import com.compose.domain.model.Character

data class DetailState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val messageError: String = "",
    val messageNetwork: Network = Network.GENERIC_ERROR,
)
