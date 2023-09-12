package com.patronusgroup.ui.nav

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.patronusgroup.ui.screens.detail.DetailScreenView
import com.patronusgroup.ui.screens.list.ListScreenView
import com.patronusgroup.ui.theme.PatronusDemoTheme

@Composable
fun DeviceApp() {
    val navController = rememberNavController()
    PatronusDemoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            MainNavHost(navController)
        }
    }
}

@Composable
private fun MainNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ListScreen.route) {
        toListScreen {
            ListScreenView(
                viewModel = hiltViewModel(),
            ) { id -> navController.navigate(DetailScreen.routeTo(id)) }
        }

        toDetailScreen { backStackEntry ->
            val id = backStackEntry
                .arguments?.getString(detailScreenArgumentIdKey) ?: return@toDetailScreen
            DetailScreenView(
                viewModel = hiltViewModel(),
                id,
            )
        }
    }
}

private fun NavGraphBuilder.toListScreen(
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) = composable(ListScreen.route, content = content)

private fun NavGraphBuilder.toDetailScreen(
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) = composable(DetailScreen.route, content = content)

private fun DetailScreen.routeTo(id: String) = this.route.replace("{$detailScreenArgumentIdKey}", id)
