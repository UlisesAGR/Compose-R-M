package com.compose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument

object Graph {
    const val ROOT_GRAPH = "rootGraph"
    const val WELCOME_GRAPH = "welcomeGraph"
    const val MAIN_GRAPH = "mainGraph"
}

sealed class WelcomeRoute(val route: String) {
    data object Welcome : WelcomeRoute(route = "welcome")
}

sealed class MainRoute(val route: String) {
    data object List : MainRoute(route = "list")
    data object Favorite : MainRoute(route = "favorite")
}

sealed class DetailRoute(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList(),
) {
    data object Detail : DetailRoute(
        route = "detail/{characterId}",
        navArguments = listOf(
            navArgument("characterId") {
                type = NavType.IntType
            },
        )
    ) {
        fun createRoute(characterId: Int) = "detail/${characterId}"
    }

    companion object {
        const val CHARACTER_ID_KEY = "characterId"
    }
}

fun navigateTo(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screenRoute ->
            popUpTo(screenRoute) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavHostController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}
