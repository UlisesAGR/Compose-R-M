package com.compose.ui.utils

sealed class UIComponent {
    internal data class Toast(
        val message: String,
    ) : UIComponent()
}
