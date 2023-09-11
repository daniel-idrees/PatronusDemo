package com.patronusgroup.ui.screens.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlebookfinder.domain.di.IoDispatcher
import com.patronusgroup.domain.usecase.GetDeviceHolderDetailUseCase
import com.patronusgroup.ui.screens.detail.state.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDeviceHolderDetailUseCase: GetDeviceHolderDetailUseCase,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _detailUiState =
        MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState>
        get() = _detailUiState

    fun getDetails(id: String) {
        viewModelScope.launch(ioDispatcher) {
            _detailUiState.emit(DetailUiState.Loading)
            val detail = getDeviceHolderDetailUseCase.get(id)
            if (detail != null) {
                _detailUiState.emit(DetailUiState.Success(detail))
            } else {
                _detailUiState.emit(DetailUiState.Error)
            }
        }
    }
}
