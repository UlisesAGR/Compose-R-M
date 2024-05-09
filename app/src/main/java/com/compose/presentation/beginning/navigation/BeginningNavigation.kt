package com.compose.presentation.beginning.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.presentation.beginning.welcome.WelcomeScreen

@Composable
fun BeginningNavigation(endNavigation: () -> Unit) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BeginningNavItem.Welcome.baseRoute,
    ) {
        composable(BeginningNavItem.Welcome.baseRoute) {
            WelcomeScreen {
                endNavigation()
            }
        }
    }
}
