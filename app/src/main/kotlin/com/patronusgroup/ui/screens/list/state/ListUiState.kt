package com.patronusgroup.ui.screens.list.state

import com.patronusgroup.domain.model.DeviceHolder

sealed class ListUiState {
    data class Success(val detail: List<DeviceHolder>) : ListUiState()
    object Error : ListUiState()
    object Loading : ListUiState()
    object Empty : ListUiState()
}
