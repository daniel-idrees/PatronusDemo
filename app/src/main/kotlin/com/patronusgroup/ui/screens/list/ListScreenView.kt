package com.patronusgroup.ui.screens.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patronusgroup.ui.screens.list.state.ListUiState
import com.patronusgroup.ui.screens.list.viewmodel.ListViewModel
import com.patronusgroup.ui.screens.views.ErrorView
import com.patronusgroup.ui.screens.views.LoadingView

@Composable
fun ListScreenView(viewModel: ListViewModel, navigateToDetail: () -> Unit) {
    val viewState by viewModel.listUiState.collectAsStateWithLifecycle()
    when (viewState) {
        is ListUiState.Loading -> LoadingView()
        is ListUiState.Success -> ListScreen(navigateToDetail)
        else -> ErrorView(viewModel::getList)
    }
}

@Composable
private fun ListScreen(navigateToDetail: () -> Unit) {
}
