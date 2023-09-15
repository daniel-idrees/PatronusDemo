package com.patronusgroup.ui.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.patronusgroup.presentation.detail.DetailScreenView
import com.patronusgroup.presentation.detail.navigation.detailScreen
import com.patronusgroup.presentation.detail.navigation.navigateToDetail
import com.patronusgroup.presentation.list.ListScreenView
import com.patronusgroup.presentation.list.navigation.listScreen
import com.patronusgroup.presentation.nav.ListScreen

@Composable
fun MainNavHost(isNetworkAvailable: Boolean) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ListScreen.route) {
        listScreen {
            ListScreenView(
                viewModel = hiltViewModel(),
                isInternetConnected = isNetworkAvailable,
                navigateToDetail = { id -> navController.navigateToDetail(id) },
            )
        }

        detailScreen {
            DetailScreenView(
                viewModel = hiltViewModel(),
            ) { navController.popBackStack(ListScreen.route, false) }
        }
    }
}
