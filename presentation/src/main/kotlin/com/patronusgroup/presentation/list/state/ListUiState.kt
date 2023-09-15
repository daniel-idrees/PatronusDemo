package com.patronusgroup.presentation.list.state

import com.patronusgroup.domain.model.DeviceHolder

sealed class ListUiState {
    data class Success(val deviceHolders: List<DeviceHolder>) : ListUiState()
    object Error : ListUiState()
    object Loading : ListUiState()
}
