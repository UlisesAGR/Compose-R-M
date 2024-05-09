package com.compose.presentation.container.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.compose.ui.widgets.BottomBar

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == NavItem.Home.route
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                BottomBar(navController)
            }
        },
    ) { padding ->
        Navigation(navController, padding)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNavigation() {
    AppNavigation()
}