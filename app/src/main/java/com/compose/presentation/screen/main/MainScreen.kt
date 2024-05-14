package com.compose.presentation.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.compose.presentation.navigation.MainRoute
import com.compose.presentation.navigation.bottom.BottomBar
import com.compose.presentation.navigation.graph.MainNavGraph
import com.compose.presentation.navigation.navigateTo

@Composable
fun MainScreen(
    rootNavController: NavHostController,
    mainNavController: NavHostController = rememberNavController(),
) {
    val backStackState by mainNavController.currentBackStackEntryAsState()
    val currentRoute by remember(backStackState) {
        derivedStateOf {
            backStackState?.destination?.route
        }
    }
    val bottomScreens = listOf(
        MainRoute.List.route,
        MainRoute.Favorite.route,
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentRoute in bottomScreens) {
                BottomBar(
                    currentRoute,
                    onClick = { currentNavigationItem ->
                        navigateTo(mainNavController, currentNavigationItem.route)
                    }
                )
            }
        },
    ) { padding ->
        MainNavGraph(rootNavController, mainNavController, padding)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNavigation() {
    MainScreen(rememberNavController())
}
