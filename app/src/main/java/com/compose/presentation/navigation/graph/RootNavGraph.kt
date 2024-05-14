package com.compose.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.presentation.navigation.Graph
import com.compose.presentation.screen.main.MainScreen

@Composable
fun RootNavGraph() {
    val rootNavController: NavHostController = rememberNavController()
    NavHost(
        navController = rootNavController,
        route = Graph.ROOT_GRAPH,
        startDestination = Graph.WELCOME_GRAPH,
    ) {
        welcomeNavGraph(rootNavController)
        composable(route = Graph.MAIN_GRAPH) {
            MainScreen(rootNavController)
        }
    }
}
