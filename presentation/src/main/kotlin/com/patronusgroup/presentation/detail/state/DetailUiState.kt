package com.patronusgroup.presentation.detail.state

import com.patronusgroup.domain.model.DeviceHolderDetail

sealed class DetailUiState {
    data class Success(val detail: DeviceHolderDetail) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}
