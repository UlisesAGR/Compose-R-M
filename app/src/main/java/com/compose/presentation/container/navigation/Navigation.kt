package com.compose.presentation.container.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.compose.presentation.container.detail.DetailScreen
import com.compose.presentation.container.list.ListScreen

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
            ListScreen(padding) { character ->
                navController.navigate(NavItem.Detail.createNavRoute(character.id))
            }
        }
        composable(NavItem.Detail) {
            DetailScreen(
                characterId = it.findArg(NavArg.CharacterId),
                onBack = { navController.navigateUp() },
            )
        }
    }
}
