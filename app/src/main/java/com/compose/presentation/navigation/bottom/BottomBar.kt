package com.compose.presentation.navigation.bottom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.compose.R

@Composable
fun BottomBar(
    currentRoute: String?,
    onClick: (NavigationItem) -> Unit,
) {
    val context = LocalContext.current
    NavigationBar(containerColor = MaterialTheme.colorScheme.background) {
        context.getNavigationItems().forEachIndexed { _, navigationItem ->
            NavigationBarItem(
                selected = currentRoute == navigationItem.route,
                onClick = { onClick(navigationItem) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            modifier = Modifier.size(dimensionResource(id = R.dimen.icons)),
                            imageVector = navigationItem.unselectedIcon,
                            contentDescription = navigationItem.title,
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = navigationItem.title,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                alwaysShowLabel = true,
            )
        }
    }
}
