package com.patronusgroup.data.dto

import com.patronusgroup.domain.model.enums.Sticker
import io.kotest.matchers.shouldBe
import org.junit.Test

class DtoMapperHelperTest {
    @Test
    fun testGetFullName() {
        `getFullName should return full name`(
            "Max",
            "Mustermann",
            "Max Mustermann",
        )

        `getFullName should return full name`(
            null,
            "Mustermann",
            "Mustermann",
        )

        `getFullName should return full name`(
            "Max",
            null,
            "Max",
        )

        `getFullName should return full name`(
            null,
            null,
            "",
        )
    }

    @Test
    fun testGetStickerList() {
        `getStickerList should return list of stickers`(
            list = listOf("Fam", "Ban"),
            expectedList = listOf(Sticker.FAM, Sticker.BAN),
        )
        `getStickerList should return list of stickers`(
            list = listOf("Ban"),
            expectedList = listOf(Sticker.BAN),
        )
        `getStickerList should return list of stickers`(
            list = listOf("Fam"),
            expectedList = listOf(Sticker.FAM),
        )
        `getStickerList should return list of stickers`(
            list = null,
            expectedList = listOf(),
        )
    }

    @Test
    fun testGetFullAddress() {
        `getFullAddress should return formatted address`(
            addStreet = "123 Imaginary Street",
            addZip = "45678",
            addCity = "Imaginary City",
            expectedAddress = "123 Imaginary Street, 45678 Imaginary City",
        )
        `getFullAddress should return formatted address`(
            addStreet = null,
            addZip = "45678",
            addCity = null,
            expectedAddress = "45678",
        )
        `getFullAddress should return formatted address`(
            addStreet = "123 Imaginary Street",
            addZip = null,
            addCity = "Imaginary City",
            expectedAddress = "123 Imaginary Street, Imaginary City",
        )

        `getFullAddress should return formatted address`(
            addStreet = null,
            addZip = "45678",
            addCity = "Imaginary City",
            expectedAddress = "45678 Imaginary City",
        )
        `getFullAddress should return formatted address`(
            addStreet = null,
            addZip = null,
            addCity = null,
            expectedAddress = "",
        )
    }

    @Test
    fun testGetFormattedPhoneNumber() {
        `getFormattedPhoneNumber should return formatted address`(
            countryCode = DtoMapperHelper.CountryCode.USA,
            phoneNumber = "123-456",
            expectedPhoneNumber = "+1 123-456",
        )

        `getFormattedPhoneNumber should return formatted address`(
            countryCode = DtoMapperHelper.CountryCode.USA,
            phoneNumber = null,
            expectedPhoneNumber = "",
        )

        `getFormattedPhoneNumber should return formatted address`(
            countryCode = DtoMapperHelper.CountryCode.USA,
            phoneNumber = "",
            expectedPhoneNumber = "",
        )
    }

    @Test
    fun testGetNameInitials() {
        `getNameInitials should return initials of first and last name`(
            firstName = "Max",
            lastName = "Mustermann",
            expectedInitials = "MM",
        )

        `getNameInitials should return initials of first and last name`(
            firstName = null,
            lastName = "Lustermann",
            expectedInitials = "L",
        )

        `getNameInitials should return initials of first and last name`(
            firstName = "Max",
            lastName = null,
            expectedInitials = "M",
        )

        `getNameInitials should return initials of first and last name`(
            firstName = null,
            lastName = null,
            expectedInitials = "",
        )
    }

    private fun `getFullName should return full name`(
        firstName: String?,
        lastName: String?,
        expectedFullName: String,
    ) {
        val result = DtoMapperHelper.getFullName(firstName, lastName)
        result shouldBe expectedFullName
    }

    private fun `getStickerList should return list of stickers`(
        list: List<String>?,
        expectedList: List<Sticker>,
    ) {
        val result = DtoMapperHelper.getStickerList(list)
        result shouldBe expectedList
    }

    private fun `getFullAddress should return formatted address`(
        addStreet: String?,
        addZip: String?,
        addCity: String?,
        expectedAddress: String,
    ) {
        val result = DtoMapperHelper.getFullAddress(
            AddressDto(
                street = addStreet,
                zip = addZip,
                city = addCity,
            ),
        )
        result shouldBe expectedAddress
    }

    private fun `getFormattedPhoneNumber should return formatted address`(
        countryCode: DtoMapperHelper.CountryCode,
        phoneNumber: String?,
        expectedPhoneNumber: String,
    ) {
        val result = DtoMapperHelper.getFormattedPhoneNumber(
            countryCode,
            phoneNumber,
        )
        result shouldBe expectedPhoneNumber
    }

    private fun `getNameInitials should return initials of first and last name`(
        firstName: String?,
        lastName: String?,
        expectedInitials: String,
    ) {
        val result = DtoMapperHelper.getNameInitials(firstName, lastName)
        result shouldBe expectedInitials
    }
}
