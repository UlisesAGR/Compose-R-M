package com.compose.presentation.navigation.bottom

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.compose.R
import com.compose.presentation.navigation.MainRoute

data class NavigationItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

@Composable
fun Context.getNavigationItems() = listOf(
    NavigationItem(
        route = MainRoute.List.route,
        title = this.getString(R.string.home),
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    ),
    NavigationItem(
        route = MainRoute.Favorite.route,
        title = this.getString(R.string.favorites),
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.Favorite,
    ),
)
