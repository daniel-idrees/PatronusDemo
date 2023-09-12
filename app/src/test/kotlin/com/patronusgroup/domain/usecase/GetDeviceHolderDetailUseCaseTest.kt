package com.patronusgroup.domain.usecase

import com.patronusgroup.domain.model.DeviceHolderDetail
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

class GetDeviceHolderDetailUseCaseTest {
    private val deviceHolderRepository: DeviceHolderRepository = mock()
    private val subject = GetDeviceHolderDetailUseCase(deviceHolderRepository)

    private val mockId = "123"
    private val mockFirstName = "firstName"
    private val mockLastName = "lastName"
    private val mockUrl = "mockUrl"
    private val mockPhoneNumber = "123-456789"
    private val mockGender = Gender.MALE

    private val mockLongitude = 22.789
    private val mockLatitude = 22.980
    private val mockAddStreet = "123 Imaginary street"
    private val mockAddZip = "45678"
    private val mockCity = "Imaginary City"

    private val expectedDetail = DeviceHolderDetail(
        imageUrl = mockUrl,
        latitude = mockLatitude,
        longitude = mockLongitude,
        fullName = "$mockFirstName $mockLastName",
        stickers = listOf(Sticker.FAM, Sticker.BAN),
        gender = mockGender,
        phoneNumber = mockPhoneNumber,
        addressStreetAndZip = "$mockAddStreet, $mockAddZip",
        city = mockCity,
    )

    @Test
    fun `get should return the list if the repository returns the list`() {
        runBlocking {
            whenever(deviceHolderRepository.getDeviceHolderDetail(mockId)) doReturn expectedDetail
            val result = subject.get(mockId)
            verify(deviceHolderRepository).getDeviceHolderDetail(mockId)
            verifyNoMoreInteractions(deviceHolderRepository)
            result shouldBe expectedDetail
        }
    }

    @Test
    fun `get should return null if the repository returns null`() {
        runBlocking {
            whenever(deviceHolderRepository.getDeviceHolderDetail(mockId)) doReturn null
            val result = subject.get(mockId)
            verify(deviceHolderRepository).getDeviceHolderDetail(mockId)
            verifyNoMoreInteractions(deviceHolderRepository)
            result shouldBe null
        }
    }
}
