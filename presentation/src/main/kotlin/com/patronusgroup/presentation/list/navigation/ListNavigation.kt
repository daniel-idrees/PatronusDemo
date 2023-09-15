package com.patronusgroup.presentation.list.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.patronusgroup.presentation.nav.ListScreen

fun NavGraphBuilder.listScreen(
    content: @Composable (NavBackStackEntry) -> Unit,
) = composable(ListScreen.route, content = content)

fun NavController.navigateToList(navOptions: NavOptions? = null) {
    this.navigate(ListScreen.route, navOptions)
}
