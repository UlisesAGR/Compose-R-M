package com.compose.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.compose.presentation.navigation.Graph
import com.compose.presentation.navigation.WelcomeRoute
import com.compose.presentation.screen.welcome.WelcomeScreen

fun NavGraphBuilder.welcomeNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Graph.WELCOME_GRAPH,
        startDestination = WelcomeRoute.Welcome.route,
    ) {
        composable(route = WelcomeRoute.Welcome.route) {
            WelcomeScreen(
                onClick = {
                    rootNavController.navigate(Graph.MAIN_GRAPH) {
                        popUpTo(WelcomeRoute.Welcome.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
