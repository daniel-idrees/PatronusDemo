package com.patronusgroup.ui.screens.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.patronusgroup.domain.model.DeviceHolderDetail
import com.patronusgroup.domain.usecase.GetDeviceHolderDetailUseCase
import com.patronusgroup.ui.screens.MainDispatcherRule
import com.patronusgroup.ui.screens.detail.state.DetailUiState
import io.kotest.common.runBlocking
import io.kotest.matchers.shouldBe
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class DetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockId = "mockId"

    private val getDeviceHolderDetailUseCase: GetDeviceHolderDetailUseCase = mock()
    private val savedStateHandle: SavedStateHandle = mock {
        whenever(this.mock.get<String>("id")) doReturn mockId
    }

    private val subject = DetailViewModel(
        getDeviceHolderDetailUseCase,
        mainDispatcherRule.testDispatcher,
        savedStateHandle,
    )

    @Test
    fun `getDetails should return detail if the repository returns detail`() {
        val mockDeviceHolderDetail: DeviceHolderDetail = mock()
        runBlocking {
            whenever(getDeviceHolderDetailUseCase.get(mockId)) doReturn mockDeviceHolderDetail
            subject.loadData()
            subject.detailUiState.value shouldBe DetailUiState.Success(mockDeviceHolderDetail)
        }
    }

    @Test
    fun `getDetails should return null if the repository returns null`() {
        runBlocking {
            whenever(getDeviceHolderDetailUseCase.get(mockId)) doReturn null
            subject.loadData()
            subject.detailUiState.value shouldBe DetailUiState.Error
        }
    }
}
