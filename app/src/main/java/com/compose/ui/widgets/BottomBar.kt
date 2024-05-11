package com.compose.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
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
import androidx.navigation.NavHostController
import com.compose.R
import com.compose.domain.model.BottomNavigationItem
import com.compose.presentation.main.navigation.NavItem

@Composable
fun BottomBar(navController: NavHostController) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        var selectedItem by rememberSaveable { mutableIntStateOf(0) }
        val bottomNavigationItems = remember {
            listOf(
                BottomNavigationItem(
                    title = "Home",
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                    route = NavItem.Home.route,
                ),
            )
        }
        bottomNavigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    if (selectedItem != index) {
                        selectedItem = index
                        navController.navigate(item.route)
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
