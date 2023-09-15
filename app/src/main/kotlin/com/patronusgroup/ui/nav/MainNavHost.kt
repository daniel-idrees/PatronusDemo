package com.patronusgroup.ui.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.patronusgroup.presentation.detail.DetailScreenView
import com.patronusgroup.presentation.detail.navigation.detailScreen
import com.patronusgroup.presentation.detail.navigation.navigateToDetail
import com.patronusgroup.presentation.list.ListScreenView
import com.patronusgroup.presentation.list.navigation.listScreen
import com.patronusgroup.presentation.nav.ListScreen
import com.patronusgroup.presentation.style.PatronusDemoTheme

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
        listScreen {
            ListScreenView(
                viewModel = hiltViewModel(),
            ) { id -> navController.navigateToDetail(id) }
        }

        detailScreen {
            DetailScreenView(
                viewModel = hiltViewModel(),
                navController::popBackStack,
            )
        }
    }
}
