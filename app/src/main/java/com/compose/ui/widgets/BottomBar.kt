package com.compose.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.compose.R
import com.compose.domain.model.BottomNavigationItem
import com.compose.presentation.main.navigation.NavItem
import com.compose.presentation.main.navigation.navigateTo

@Composable
fun BottomBar(navController: NavHostController, backStackState: NavBackStackEntry?) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        val bottomNavigationItems = remember {
            listOf(
                BottomNavigationItem(
                    title = "Home",
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                    route = NavItem.Home.route,
                ),
                BottomNavigationItem(
                    title = "Favorite",
                    selectedIcon = Icons.Filled.Favorite,
                    unselectedIcon = Icons.Outlined.Favorite,
                    route = NavItem.Favorite.route,
                ),
            )
        }

        var selectedItem by rememberSaveable { mutableIntStateOf(0) }
        selectedItem = when (backStackState?.destination?.route) {
            NavItem.Home.route -> 0
            NavItem.Favorite.route -> 1
            else -> 0
        }

        bottomNavigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    if (selectedItem != index) {
                        selectedItem = index
                        navigateTo(
                            navController = navController,
                            route = item.route,
                        )
                    }
                },
                alwaysShowLabel = true,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            modifier = Modifier.size(dimensionResource(id = R.dimen.icons)),
                            imageVector = item.unselectedIcon,
                            contentDescription = item.title,
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = item.title, style = MaterialTheme.typography.labelSmall)
                    }
                }
            )
        }
    }
}
