package com.compose.presentation.beginning.navigation

sealed class BeginningNavItem(val baseRoute: String) {
    data object Welcome : BeginningNavItem("welcome")
}
