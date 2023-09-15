package com.patronusgroup.presentation.ui.screens.list.viewmodel

import com.patronusgroup.domain.model.DeviceHolder
import com.patronusgroup.domain.usecase.GetDeviceHolderListUseCase
import com.patronusgroup.presentation.list.state.ListUiState
import com.patronusgroup.presentation.list.viewmodel.ListViewModel
import com.patronusgroup.presentation.ui.screens.MainDispatcherRule
import io.kotest.common.runBlocking
import io.kotest.matchers.shouldBe
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class ListViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getDeviceHolderListUseCase: GetDeviceHolderListUseCase = mock()

    private val subject = ListViewModel(
        getDeviceHolderListUseCase,
        mainDispatcherRule.testDispatcher,
    )

    @Test
    fun `get should return list if the repository returns list`() {
        val mockDeviceHolderList: List<DeviceHolder> = mock()
        runBlocking {
            whenever(getDeviceHolderListUseCase.get()) doReturn mockDeviceHolderList
            subject.loadData()
            subject.listUiState.value shouldBe ListUiState.Success(mockDeviceHolderList)
        }
    }

    @Test
    fun `get should return null if the repository returns null`() {
        runBlocking {
            whenever(getDeviceHolderListUseCase.get()) doReturn null
            subject.loadData()
            subject.listUiState.value shouldBe ListUiState.Error
        }
    }
}
