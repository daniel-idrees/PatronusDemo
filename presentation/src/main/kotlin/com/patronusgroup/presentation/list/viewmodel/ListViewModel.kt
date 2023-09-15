package com.patronusgroup.presentation.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patronusgroup.domain.di.IoDispatcher
import com.patronusgroup.domain.model.DeviceHolder
import com.patronusgroup.domain.usecase.GetDeviceHolderListUseCase
import com.patronusgroup.presentation.list.state.ListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getDeviceHolderListUseCase: GetDeviceHolderListUseCase,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _listUiState =
        MutableStateFlow<ListUiState>(ListUiState.Loading)
    val listUiState: StateFlow<ListUiState>
        get() = _listUiState

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch(ioDispatcher) {
            _listUiState.emit(ListUiState.Loading)
            val list = getDeviceHolderListUseCase.get()
            updateUiState(list)
        }
    }

    private suspend fun updateUiState(list: List<DeviceHolder>?) {
        val viewState =
            if (list != null) ListUiState.Success(list) else ListUiState.Error
        _listUiState.emit(viewState)
    }
}
