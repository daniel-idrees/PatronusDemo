package com.patronusgroup.data.dto

import com.patronusgroup.domain.model.DeviceHolder
import com.patronusgroup.domain.model.enums.Gender
import com.patronusgroup.domain.model.enums.Sticker
import io.kotest.matchers.shouldBe
import org.junit.Test

class DeviceHolderListDtoMapperTest {
    private val mockId = "123"
    private val mockUrl = "mockUrl"

    @Test
    fun `Dto should be mapped correctly to domain model`() {
        `DeviceHolderListResponse should be mapped correctly to DeviceHolder`(
            firstName = "Max",
            lastName = "Mustermann",
            stickers = listOf("Fam", "Ban"),
            gender = "MALE",
            phoneNumber = "123-456789",
            expectedFullName = "Max Mustermann",
            expectedStickers = listOf(Sticker.FAM, Sticker.BAN),
            expectedPhoneNumber = "+1 123-456789",
            expectedNameInitials = "MM",
        )
        `DeviceHolderListResponse should be mapped correctly to DeviceHolder`(
            firstName = "Max",
            lastName = null,
            stickers = listOf("Ban", "Fam"),
            gender = "FEMALE",
            phoneNumber = "456-456789",
            expectedFullName = "Max",
            expectedStickers = listOf(Sticker.BAN, Sticker.FAM),
            expectedPhoneNumber = "+1 456-456789",
            expectedNameInitials = "M",
        )
        `DeviceHolderListResponse should be mapped correctly to DeviceHolder`(
            firstName = null,
            lastName = "Mustermann",
            stickers = listOf("Fam"),
            gender = "FEMALE",
            phoneNumber = "456-456789",
            expectedFullName = "Mustermann",
            expectedStickers = listOf(Sticker.FAM),
            expectedPhoneNumber = "+1 456-456789",
            expectedNameInitials = "M",
        )
        `DeviceHolderListResponse should be mapped correctly to DeviceHolder`(
            firstName = null,
            lastName = null,
            stickers = null,
            gender = null,
            phoneNumber = null,
            expectedFullName = "",
            expectedStickers = listOf(),
            expectedPhoneNumber = "",
            expectedNameInitials = "",
        )
    }

    private fun `DeviceHolderListResponse should be mapped correctly to DeviceHolder`(
        firstName: String?,
        lastName: String?,
        stickers: List<String>?,
        gender: String?,
        phoneNumber: String?,
        expectedFullName: String,
        expectedStickers: List<Sticker>,
        expectedPhoneNumber: String,
        expectedNameInitials: String,
    ) {
        val response = DeviceHolderListResponse(
            listOf(
                DeviceHolderDto(
                    id = mockId.toInt(),
                    firstName = firstName,
                    lastName = lastName,
                    gender = gender,
                    phoneNumber = phoneNumber,
                    imageUrl = mockUrl,
                    stickers = stickers,
                ),
            ),
        )

        val expected = listOf(
            DeviceHolder(
                id = mockId.toInt(),
                fullName = expectedFullName,
                gender = gender?.let { Gender.valueOf(it) },
                phoneNumber = expectedPhoneNumber,
                imageUrl = mockUrl,
                stickers = expectedStickers,
                nameInitials = expectedNameInitials,
            ),
        )

        val result = response.toDeviceHolderList()

        result shouldBe expected
    }
}
