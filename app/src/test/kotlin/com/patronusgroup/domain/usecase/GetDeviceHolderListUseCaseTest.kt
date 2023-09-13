package com.patronusgroup.domain.usecase

import com.patronusgroup.domain.model.DeviceHolder
import com.patronusgroup.domain.model.enums.Gender
import com.patronusgroup.domain.model.enums.Sticker
import com.patronusgroup.domain.repository.DeviceHolderRepository
import io.kotest.common.runBlocking
import io.kotest.matchers.shouldBe
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

class GetDeviceHolderListUseCaseTest {
    private val deviceHolderRepository: DeviceHolderRepository = mock()
    private val subject = GetDeviceHolderListUseCase(deviceHolderRepository)

    private val mockId = "123"
    private val mockFirstName = "FirstName"
    private val mockLastName = "LastName"
    private val mockUrl = "mockUrl"
    private val mockPhoneNumber = "123-456789"
    private val mockGender = Gender.MALE
    private val mockNameInitials = "FL"

    private val expectedList = listOf(
        DeviceHolder(
            id = mockId.toInt(),
            fullName = "$mockFirstName $mockLastName",
            gender = mockGender,
            phoneNumber = mockPhoneNumber,
            imageUrl = mockUrl,
            stickers = listOf(Sticker.FAM, Sticker.BAN),
            nameInitials = mockNameInitials,
        ),
    )

    @Test
    fun `get should return detail if the repository returns the detail`() {
        runBlocking {
            whenever(deviceHolderRepository.getDeviceHolderList()) doReturn expectedList
            val result = subject.get()
            verify(deviceHolderRepository).getDeviceHolderList()
            verifyNoMoreInteractions(deviceHolderRepository)
            result shouldBe expectedList
        }
    }

    @Test
    fun `get should return null if the repository returns null`() {
        runBlocking {
            whenever(deviceHolderRepository.getDeviceHolderList()) doReturn null
            val result = subject.get()
            verify(deviceHolderRepository).getDeviceHolderList()
            verifyNoMoreInteractions(deviceHolderRepository)
            result shouldBe null
        }
    }
}
