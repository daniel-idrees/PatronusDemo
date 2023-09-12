package com.patronusgroup.ui.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patronusgroup.ui.screens.detail.state.DetailUiState
import com.patronusgroup.ui.screens.detail.viewmodel.DetailViewModel
import com.patronusgroup.ui.screens.views.ErrorView
import com.patronusgroup.ui.screens.views.LoadingView

@Composable
fun DetailScreenView(
    viewModel: DetailViewModel,
    id: String,
) {
    val viewState by viewModel.detailUiState.collectAsStateWithLifecycle()
    when (viewState) {
        is DetailUiState.Loading -> LoadingView()
        is DetailUiState.Success -> DetailScreen()
        else -> ErrorView() {}
    }
}

@Composable
private fun DetailScreen() {
}
