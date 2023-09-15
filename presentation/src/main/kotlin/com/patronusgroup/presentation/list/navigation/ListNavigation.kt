package com.patronusgroup.presentation.list.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.patronusgroup.presentation.nav.ListScreen

fun NavGraphBuilder.listScreen(
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) = composable(ListScreen.route, content = content)

fun NavController.navigateToList() {
    this.navigate(
        ListScreen.route,
        navOptions {
            this.launchSingleTop = true
        },
    )
}
