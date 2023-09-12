package com.patronusgroup.data.dto

import com.patronusgroup.domain.model.DeviceHolder
import com.patronusgroup.domain.model.enums.Gender
import com.patronusgroup.domain.model.enums.Sticker
import io.kotest.matchers.shouldBe
import org.junit.Test

class DeviceHolderListDtoMapperTest {
    private val mockId = "123"
    private val mockUrl = "mockUrl"
    private val mockPhoneNumber = "123-456789"

    @Test
    fun `Dto should be mapped correctly to domain model`() {
        `DeviceHolderListResponse should be mapped correctly to DeviceHolder`(
            firstName = "Max",
            lastName = "Mustermann",
            stickers = listOf("Fam", "Ban"),
            gender = "MALE",
            expectedFullName = "Max Mustermann",
            expectedStickers = listOf(Sticker.FAM, Sticker.BAN),
        )
        `DeviceHolderListResponse should be mapped correctly to DeviceHolder`(
            firstName = "Max",
            lastName = null,
            stickers = listOf("Ban", "Fam"),
            gender = "FEMALE",
            expectedFullName = "Max",
            expectedStickers = listOf(Sticker.BAN, Sticker.FAM),
        )
        `DeviceHolderListResponse should be mapped correctly to DeviceHolder`(
            firstName = null,
            lastName = "Mustermann",
            stickers = listOf("Fam"),
            gender = "FEMALE",
            expectedFullName = "Mustermann",
            expectedStickers = listOf(Sticker.FAM),
        )
        `DeviceHolderListResponse should be mapped correctly to DeviceHolder`(
            firstName = null,
            lastName = null,
            stickers = null,
            gender = null,
            expectedFullName = "",
            expectedStickers = listOf(),
        )
    }

    private fun `DeviceHolderListResponse should be mapped correctly to DeviceHolder`(
        firstName: String?,
        lastName: String?,
        stickers: List<String>?,
        gender: String?,
        expectedFullName: String,
        expectedStickers: List<Sticker>,
    ) {
        val response = DeviceHolderListResponse(
            listOf(
                DeviceHolderDto(
                    id = mockId.toInt(),
                    firstName = firstName,
                    lastName = lastName,
                    gender = gender,
                    phoneNumber = mockPhoneNumber,
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
                phoneNumber = mockPhoneNumber,
                imageUrl = mockUrl,
                stickers = expectedStickers,
            ),
        )

        val result = response.toDeviceHolderList()

        result shouldBe expected
    }
}
