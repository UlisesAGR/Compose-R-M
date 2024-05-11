package com.compose.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class NavItem(
    val baseRoute: String,
    private val navArgs: List<NavArg> = emptyList(),
) {
    val route = run {
        //example baseRoute/{arg1}/{arg2}
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString(separator = "/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

    data object Home : NavItem("home")
    data object Detail : NavItem("detail", listOf(NavArg.CharacterId)) {
        fun createNavRoute(characterId: Int) = "$baseRoute/$characterId"
    }
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    CharacterId("characterId", NavType.IntType)
}

fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args,
    ) {
        content(it)
    }
}

@Suppress("DEPRECATION")
inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T =
    requireNotNull(arguments?.get(arg.key)) as T

inline fun <reified T> SavedStateHandle.findArg(arg: NavArg): T =
    requireNotNull(this[arg.key]) as T
