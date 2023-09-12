package com.patronusgroup.data.dto

import com.patronusgroup.domain.model.DeviceHolderDetail
import com.patronusgroup.domain.model.enums.Gender
import com.patronusgroup.domain.model.enums.Sticker
import io.kotest.matchers.shouldBe
import org.junit.Test

class DeviceHolderDetailDtoMapperTest {
    private val mockId = "123"
    private val mockUrl = "mockUrl"
    private val mockPhoneNumber = "123-456789"

    private val mockLongitude = 22.789
    private val mockLatitude = 22.980
    private val mockCity = "Imaginary City"
    private val mockState = "Imaginary State"
    private val mockCountry = "ImaginaryLand"

    @Test
    fun `Dto should be mapped correctly to domain model`() {
        `DeviceHolderDetailResponse should map correctly to domain model`(
            firstName = "Max",
            lastName = "Mustermann",
            stickers = listOf("Fam", "Ban"),
            gender = "MALE",
            addressStreet = "123 Imaginary Street",
            addressZip = "45678",
            expectedFullName = "Max Mustermann",
            expectedStickers = listOf(Sticker.FAM, Sticker.BAN),
            expectedAddressStreetAndZip = "123 Imaginary Street, 45678",
        )

        `DeviceHolderDetailResponse should map correctly to domain model`(
            firstName = "Max",
            lastName = null,
            stickers = listOf("Ban", "Fam"),
            gender = "FEMALE",
            addressStreet = "123 Imaginary Street",
            addressZip = null,
            expectedFullName = "Max",
            expectedStickers = listOf(Sticker.BAN, Sticker.FAM),
            expectedAddressStreetAndZip = "123 Imaginary Street",
        )

        `DeviceHolderDetailResponse should map correctly to domain model`(
            firstName = null,
            lastName = "Mustermann",
            stickers = null,
            gender = null,
            addressStreet = null,
            addressZip = "87654",
            expectedFullName = "Mustermann",
            expectedStickers = listOf(),
            expectedAddressStreetAndZip = "87654",
        )

        `DeviceHolderDetailResponse should map correctly to domain model`(
            firstName = null,
            lastName = null,
            stickers = null,
            gender = null,
            addressStreet = null,
            addressZip = null,
            expectedFullName = "",
            expectedStickers = listOf(),
            expectedAddressStreetAndZip = "",
        )
    }

    private fun `DeviceHolderDetailResponse should map correctly to domain model`(
        firstName: String?,
        lastName: String?,
        stickers: List<String>?,
        gender: String?,
        addressStreet: String?,
        addressZip: String?,
        expectedFullName: String,
        expectedStickers: List<Sticker>,
        expectedAddressStreetAndZip: String,
    ) {
        val response = DeviceHolderDetailResponse(
            id = mockId.toInt(),
            imageUrl = mockUrl,
            latitude = mockLatitude,
            longitude = mockLongitude,
            firstName = firstName,
            lastName = lastName,
            stickers = stickers,
            gender = gender,
            phoneNumber = mockPhoneNumber,
            address = AddressDto(
                street = addressStreet,
                zip = addressZip,
                city = mockCity,
                state = mockState,
                country = mockCountry,
            ),
        )

        val expected = DeviceHolderDetail(
            imageUrl = mockUrl,
            latitude = mockLatitude,
            longitude = mockLongitude,
            fullName = expectedFullName,
            stickers = expectedStickers,
            gender = gender?.let { Gender.valueOf(it) },
            phoneNumber = mockPhoneNumber,
            addressStreetAndZip = expectedAddressStreetAndZip,
            city = mockCity,
        )

        val result = response.toDeviceHolderDetail()

        result shouldBe expected
    }
}
