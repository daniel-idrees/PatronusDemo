package com.patronusgroup.data.dto

import com.patronusgroup.domain.model.enums.Sticker
import io.kotest.matchers.shouldBe
import org.junit.Test

class DtoMapperHelperTest {
    @Test
    fun testFullName() {
        getFullName(
            "Max",
            "Mustermann",
            "Max Mustermann",
        )

        getFullName(
            null,
            "Mustermann",
            "Mustermann",
        )

        getFullName(
            "Max",
            null,
            "Max",
        )

        getFullName(
            null,
            null,
            "",
        )
    }

    @Test
    fun testStickerList() {
        getStickerList(
            list = listOf("Fam", "Ban"),
            expectedList = listOf(Sticker.FAM, Sticker.BAN),
        )
        getStickerList(
            list = listOf("Ban"),
            expectedList = listOf(Sticker.BAN),
        )
        getStickerList(
            list = listOf("Fam"),
            expectedList = listOf(Sticker.FAM),
        )
        getStickerList(
            list = null,
            expectedList = listOf(),
        )
    }

    @Test
    fun testAddressStreetAndZip() {
        getAddressStreetAndZip(
            addStreet = "123 Imaginary Street",
            addZip = "45678",
            expectedAddress = "123 Imaginary Street, 45678",
        )
        getAddressStreetAndZip(
            addStreet = null,
            addZip = "45678",
            expectedAddress = "45678",
        )
        getAddressStreetAndZip(
            addStreet = "123 Imaginary Street",
            addZip = null,
            expectedAddress = "123 Imaginary Street",
        )
        getAddressStreetAndZip(
            addStreet = null,
            addZip = null,
            expectedAddress = "",
        )
    }

    private fun getFullName(
        firstName: String?,
        lastName: String?,
        expectedFullName: String,
    ) {
        val result = DtoMapperHelper.getFullName(firstName, lastName)
        result shouldBe expectedFullName
    }

    private fun getStickerList(list: List<String>?, expectedList: List<Sticker>) {
        val result = DtoMapperHelper.getStickerList(list)
        result shouldBe expectedList
    }

    private fun getAddressStreetAndZip(
        addStreet: String?,
        addZip: String?,
        expectedAddress: String,
    ) {
        val result = DtoMapperHelper.getAddressStreetAndZip(
            AddressDto(
                street = addStreet,
                zip = addZip,
            ),
        )
        result shouldBe expectedAddress
    }
}
