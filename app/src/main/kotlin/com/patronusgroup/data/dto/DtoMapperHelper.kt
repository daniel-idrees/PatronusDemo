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

    fun getAddressStreetAndZip(address: AddressDto?): String {
        address?.let {
            val street = it.street ?: ""
            val zip = it.zip ?: ""
            val separator = if (street.isNotEmpty() && zip.isNotEmpty()) ", " else ""
            return "$street$separator$zip"
        } ?: return ""
    }

    inline fun <reified T : Enum<T>> String?.getEnumOrNull(): T? =
        enumValues<T>().firstOrNull { it.name.equals(this, ignoreCase = true) }
}
