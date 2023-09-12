package com.patronusgroup.data.repository

import com.patronusgroup.data.dto.AddressDto
import com.patronusgroup.data.dto.DeviceHolderDetailResponse
import com.patronusgroup.data.dto.DeviceHolderDto
import com.patronusgroup.data.dto.DeviceHolderListResponse
import com.patronusgroup.data.network.DeviceHolderService
import com.patronusgroup.domain.model.DeviceHolder
import com.patronusgroup.domain.model.DeviceHolderDetail
import com.patronusgroup.domain.model.enums.Gender
import com.patronusgroup.domain.model.enums.Sticker
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

class DeviceHolderRepositoryImplTest {
    private val deviceHolderService: DeviceHolderService = mock()

    private val subject = DeviceHolderRepositoryImpl(deviceHolderService)

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
    private val mockState = "Imaginary State"
    private val mockCountry = "ImaginaryLand"

    private val mockListResponse = DeviceHolderListResponse(
        listOf(
            DeviceHolderDto(
                id = mockId.toInt(),
                firstName = mockFirstName,
                lastName = mockLastName,
                gender = mockGender.value,
                phoneNumber = mockPhoneNumber,
                imageUrl = mockUrl,
                stickers = listOf("Fam", "Ban"),
            ),
        ),
    )

    private val expectedList = listOf(
        DeviceHolder(
            id = mockId.toInt(),
            fullName = "$mockFirstName $mockLastName",
            gender = mockGender,
            phoneNumber = mockPhoneNumber,
            imageUrl = mockUrl,
            stickers = listOf(Sticker.FAM, Sticker.BAN),
        ),
    )

    private val mockDetailResponse = DeviceHolderDetailResponse(
        id = mockId.toInt(),
        imageUrl = mockUrl,
        latitude = mockLatitude,
        longitude = mockLongitude,
        firstName = mockFirstName,
        lastName = mockLastName,
        stickers = listOf("Fam", "Ban"),
        gender = mockGender.value,
        phoneNumber = mockPhoneNumber,
        address = AddressDto(
            street = mockAddStreet,
            zip = mockAddZip,
            city = mockCity,
            state = mockState,
            country = mockCountry,
        ),
    )

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
    fun `getDeviceHolderList should return the list when api response is successful`() {
        runBlocking {
            whenever(deviceHolderService.getDeviceHolderList()) doReturn mockListResponse
            val result = subject.getDeviceHolderList()
            verify(deviceHolderService).getDeviceHolderList()
            verifyNoMoreInteractions(deviceHolderService)
            result shouldBe expectedList
        }
    }

    @Test
    fun `getDeviceHolderList should return null when api response throws exception`() {
        runBlocking {
            whenever(deviceHolderService.getDeviceHolderList()) doThrow IllegalArgumentException()
            val result = subject.getDeviceHolderList()
            verify(deviceHolderService).getDeviceHolderList()
            verifyNoMoreInteractions(deviceHolderService)
            result shouldNotBe expectedList
            result shouldBe null
        }
    }

    @Test
    fun `getDeviceHolderDetail should return detail when api response is successful`() {
        runBlocking {
            whenever(deviceHolderService.getDeviceHolderDetail(mockId)) doReturn mockDetailResponse
            val result = subject.getDeviceHolderDetail(mockId)
            verify(deviceHolderService).getDeviceHolderDetail(mockId)
            verifyNoMoreInteractions(deviceHolderService)
            result shouldBe expectedDetail
        }
    }

    @Test
    fun `getDeviceHolderDetail should return null when api response throws exception`() {
        runBlocking {
            whenever(deviceHolderService.getDeviceHolderDetail(mockId)) doThrow IllegalArgumentException()
            val result = subject.getDeviceHolderDetail(mockId)
            verify(deviceHolderService).getDeviceHolderDetail(mockId)
            verifyNoMoreInteractions(deviceHolderService)
            result shouldNotBe expectedDetail
            result shouldBe null
        }
    }
}
