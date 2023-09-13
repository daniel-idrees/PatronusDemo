package com.patronusgroup.data.dto

import com.patronusgroup.domain.model.enums.Sticker

object DtoMapperHelper {

    fun getFullName(firstName: String?, lastName: String?): String {
        val fName = firstName ?: ""
        val lName = lastName ?: ""
        val separator = if (fName.isNotEmpty() && lName.isNotEmpty()) " " else ""

        return "$fName$separator$lName"
    }

    fun getStickerList(list: List<String>?): List<Sticker> {
        val stickers = arrayListOf<Sticker>()

        list?.forEach { sticker ->
            val enumValue = sticker.getEnumOrNull<Sticker>()
            enumValue?.let { s ->
                stickers.add(s)
            }
        }

        return stickers
    }

    fun getFullAddress(address: AddressDto?): String {
        address?.let {
            val street = it.street ?: ""
            val zip = it.zip ?: ""
            val city = it.city ?: ""
            val streetZipSeparator =
                if (street.isNotEmpty() && (zip.isNotEmpty() || city.isNotEmpty())) ", " else ""
            val zipCitySeparator = if (zip.isNotEmpty() && city.isNotEmpty()) " " else ""
            return "$street$streetZipSeparator$zip$zipCitySeparator$city"
        } ?: return ""
    }

    fun getFormattedPhoneNumber(countryCode: CountryCode, phoneNumber: String?): String {
        return if (phoneNumber.isNullOrBlank()) {
            ""
        } else {
            val code = countryCode.code
            "$code $phoneNumber"
        }
    }

    fun getNameInitials(firstName: String?, lastName: String?): String {
        val firstInitial = firstName?.firstOrNull() ?: ""
        val secondInitial = lastName?.firstOrNull() ?: ""
        return "$firstInitial$secondInitial"
    }

    enum class CountryCode(val code: String) {
        GERMANY("+49"),
        USA("+1"),
    }

    inline fun <reified T : Enum<T>> String?.getEnumOrNull(): T? =
        enumValues<T>().firstOrNull { it.name.equals(this, ignoreCase = true) }
}
