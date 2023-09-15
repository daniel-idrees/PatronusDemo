package com.patronusgroup.data.dto

import com.patronusgroup.domain.model.DeviceHolderDetail
import com.patronusgroup.domain.model.enums.Gender
import com.patronusgroup.domain.model.enums.Sticker
import io.kotest.matchers.shouldBe
import org.junit.Test

internal class DeviceHolderDetailDtoMapperTest {
    private val mockId = "123"
    private val mockUrl = "mockUrl"

    private val mockLongitude = 22.789
    private val mockLatitude = 22.980
    private val mockState = "Imaginary State"
    private val mockCountry = "ImaginaryLand"

    @Test
    fun `Dto should be mapped correctly to domain model`() {
        `DeviceHolderDetailResponse should map correctly to domain model`(
            firstName = "Max",
            lastName = "Mustermann",
            stickers = listOf("Fam", "Ban"),
            gender = "MALE",
            phoneNumber = "453-452780",
            addressStreet = "123 Imaginary Street",
            addressZip = "45678",
            addressCity = "Imaginary City",
            expectedFullName = "Max Mustermann",
            expectedStickers = listOf(
                Sticker.FAM,
                Sticker.BAN,
            ),
            expectedFullAddress = "123 Imaginary Street, 45678 Imaginary City",
            expectedNameInitials = "MM",
            expectedPhoneNumber = "+1 453-452780",
        )

        `DeviceHolderDetailResponse should map correctly to domain model`(
            firstName = "Max",
            lastName = null,
            stickers = listOf("Ban", "Fam"),
            gender = "FEMALE",
            addressStreet = "123 Imaginary Street",
            addressZip = null,
            addressCity = "City",
            phoneNumber = "423-123345",
            expectedFullName = "Max",
            expectedStickers = listOf(
                Sticker.BAN,
                Sticker.FAM,
            ),
            expectedFullAddress = "123 Imaginary Street, City",
            expectedNameInitials = "M",
            expectedPhoneNumber = "+1 423-123345",
        )

        `DeviceHolderDetailResponse should map correctly to domain model`(
            firstName = null,
            lastName = "Mustermann",
            stickers = null,
            gender = null,
            phoneNumber = "123-456789",
            addressStreet = null,
            addressZip = "87654",
            addressCity = "City",
            expectedFullName = "Mustermann",
            expectedStickers = listOf(),
            expectedFullAddress = "87654 City",
            expectedNameInitials = "M",
            expectedPhoneNumber = "+1 123-456789",
        )

        `DeviceHolderDetailResponse should map correctly to domain model`(
            firstName = null,
            lastName = null,
            stickers = null,
            gender = null,
            phoneNumber = null,
            addressStreet = null,
            addressZip = null,
            addressCity = null,
            expectedFullName = "",
            expectedStickers = listOf(),
            expectedFullAddress = "",
            expectedNameInitials = "",
            expectedPhoneNumber = "",
        )
    }

    private fun `DeviceHolderDetailResponse should map correctly to domain model`(
        firstName: String?,
        lastName: String?,
        stickers: List<String>?,
        gender: String?,
        phoneNumber: String?,
        addressStreet: String?,
        addressZip: String?,
        addressCity: String?,
        expectedFullName: String,
        expectedStickers: List<Sticker>,
        expectedPhoneNumber: String,
        expectedFullAddress: String,
        expectedNameInitials: String,
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
            phoneNumber = phoneNumber,
            address = AddressDto(
                street = addressStreet,
                zip = addressZip,
                city = addressCity,
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
            phoneNumber = expectedPhoneNumber,
            fullAddress = expectedFullAddress,
            nameInitials = expectedNameInitials,
        )

        val result = response.toDeviceHolderDetail()

        result shouldBe expected
    }
}
