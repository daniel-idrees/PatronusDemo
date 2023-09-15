package com.patronusgroup.presentation.detail.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.patronusgroup.presentation.nav.DetailScreen
import com.patronusgroup.presentation.nav.detailScreenArgumentIdKey

fun NavGraphBuilder.detailScreen(
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) = composable(DetailScreen.route, content = content)

fun NavController.navigateToDetail(id: String) {
    this.navigate(
        DetailScreen.routeTo(id),
        navOptions {
            this.launchSingleTop = true
        },
    )
}

private fun DetailScreen.routeTo(id: String) =
    this.route.replace("{$detailScreenArgumentIdKey}", id)
