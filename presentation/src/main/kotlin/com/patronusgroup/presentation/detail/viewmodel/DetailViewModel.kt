package com.patronusgroup.presentation.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patronusgroup.core.di.IoDispatcher
import com.patronusgroup.domain.model.DeviceHolderDetail
import com.patronusgroup.domain.usecase.GetDeviceHolderDetailUseCase
import com.patronusgroup.presentation.detail.state.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDeviceHolderDetailUseCase: GetDeviceHolderDetailUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _detailUiState =
        MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState>
        get() = _detailUiState

    private val id = savedStateHandle.get<String>("id") ?: ""

    init {
        loadData()
    }

    fun loadData() {
        if (id.isNotEmpty()) {
            getDetails()
        } else {
            _detailUiState.value = DetailUiState.Error
        }
    }

    private fun getDetails() {
        viewModelScope.launch(ioDispatcher) {
            _detailUiState.emit(DetailUiState.Loading)
            val detail = getDeviceHolderDetailUseCase.get(id)
            updateUiState(detail)
        }
    }

    private suspend fun updateUiState(detail: DeviceHolderDetail?) {
        val viewState =
            if (detail != null) DetailUiState.Success(detail) else DetailUiState.Error
        _detailUiState.emit(viewState)
    }
}
