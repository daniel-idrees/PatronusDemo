package com.patronusgroup.presentation.detail.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.patronusgroup.presentation.nav.DetailScreen
import com.patronusgroup.presentation.nav.detailScreenArgumentIdKey

fun NavGraphBuilder.detailScreen(
    content: @Composable (NavBackStackEntry) -> Unit,
) = composable(DetailScreen.route, content = content)

fun NavController.navigateToDetail(id: String, navOptions: NavOptions? = null) {
    this.navigate(DetailScreen.routeTo(id), navOptions)
}

private fun DetailScreen.routeTo(id: String) =
    this.route.replace("{$detailScreenArgumentIdKey}", id)
