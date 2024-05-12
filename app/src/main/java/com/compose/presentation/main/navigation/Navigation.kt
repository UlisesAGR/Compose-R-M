package com.compose.presentation.main.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.compose.presentation.main.detail.DetailScreen
import com.compose.presentation.main.favorite.FavoriteScreen
import com.compose.presentation.main.list.ListScreen

@Composable
fun Navigation(
    navController: NavHostController,
    padding: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = NavItem.Home.route,
    ) {
        composable(NavItem.Home) {
            ListScreen(
                padding = padding,
                characterSelected = { character ->
                    navController.navigate(NavItem.Detail.createNavRoute(character.id))
                },
            )
        }
        composable(NavItem.Favorite) {
            FavoriteScreen(
                padding = padding,
            ) { character ->
                navController.navigate(NavItem.Detail.createNavRoute(character.id))
            }
        }
        composable(NavItem.Detail) {
            DetailScreen(
                onBack = { navController.navigateUp() },
            )
        }
    }
}
