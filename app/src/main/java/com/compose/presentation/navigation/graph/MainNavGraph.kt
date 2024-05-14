package com.compose.presentation.navigation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.presentation.navigation.DetailRoute
import com.compose.presentation.navigation.Graph
import com.compose.presentation.navigation.MainRoute
import com.compose.presentation.screen.detail.DetailScreen
import com.compose.presentation.screen.favorite.FavoriteScreen
import com.compose.presentation.screen.list.ListScreen

@Composable
fun MainNavGraph(
    rootNavController: NavHostController,
    mainNavController: NavHostController,
    padding: PaddingValues,
) {
    NavHost(
        navController = mainNavController,
        route = Graph.MAIN_GRAPH,
        startDestination = MainRoute.List.route,
    ) {
        composable(route = MainRoute.List.route) {
            ListScreen(
                innerPadding = padding,
                onCharacterClick = { character ->
                    mainNavController.navigate(
                        route = DetailRoute.Detail.createRoute(characterId = character.id)
                    )
                }
            )
        }
        composable(route = MainRoute.Favorite.route) {
            FavoriteScreen(
                innerPadding = padding,
                onCharacterClick = { character ->
                    mainNavController.navigate(
                        route = DetailRoute.Detail.createRoute(characterId = character.id)
                    )
                }
            )
        }
        composable(
            route = DetailRoute.Detail.route,
            arguments = DetailRoute.Detail.navArguments,
        ) {
            DetailScreen(
                onBack = {
                    mainNavController.navigateUp()
                }
            )
        }
    }
}
