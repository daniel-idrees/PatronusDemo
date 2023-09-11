package com.patronusgroup.ui.screens.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlebookfinder.domain.di.IoDispatcher
import com.patronusgroup.domain.usecase.GetDeviceHolderListUseCase
import com.patronusgroup.ui.screens.list.state.ListUiState
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
    init {
        getList()
    }

    private val _listUiState =
        MutableStateFlow<ListUiState>(ListUiState.Loading)
    val listUiState: StateFlow<ListUiState>
        get() = _listUiState

    fun getList() {
        viewModelScope.launch(ioDispatcher) {
            _listUiState.emit(ListUiState.Loading)
            val list = getDeviceHolderListUseCase.get()
            if (list != null) {
                _listUiState.emit(ListUiState.Success(list))
            } else {
                _listUiState.emit(ListUiState.Error)
            }
        }
    }
}
