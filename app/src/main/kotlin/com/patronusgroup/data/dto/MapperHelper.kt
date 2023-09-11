package com.patronusgroup.data.dto

import com.patronusgroup.domain.model.DeviceHolder
import com.patronusgroup.domain.model.DeviceHolderDetail
import com.patronusgroup.domain.model.enums.Gender
import com.patronusgroup.domain.model.enums.Sticker

object MapperHelper {

    fun DeviceHolderListResponse.toDeviceHolderList(): List<DeviceHolder> {
        val list = arrayListOf<DeviceHolder>()
        deviceHolders.forEach { holder ->
            with(holder) {
                id?.let { holderId ->
                    list.add(
                        DeviceHolder(
                            id = holderId,
                            fullName = getFullName(firstName, lastName),
                            gender = gender.getEnumOrNull<Gender>(),
                            phoneNumber = phoneNumber,
                            imageUrl = imageUrl,
                            stickers = stickers.getStickerList(),
                        ),
                    )
                }
            }
        }
        return list
    }

    fun DeviceHolderDetailResponse.toDeviceHolderDetail(): DeviceHolderDetail =
        DeviceHolderDetail(
            imageUrl = imageUrl,
            latitude = latitude,
            longitude = longitude,
            fullName = getFullName(firstName, lastName),
            stickers = stickers.getStickerList(),
            gender = gender.getEnumOrNull<Gender>(),
            phoneNumber = phoneNumber,
            addressStreetAndZip = getAddressStreetAndZip(address),
            city = address?.city,
        )

    private fun getFullName(firstName: String?, lastName: String?): String {
        val fName = firstName ?: ""
        val lName = lastName ?: ""
        val separator = if (fName.isNotEmpty() && lName.isNotEmpty()) " " else ""

        return "$fName$separator$lName"
    }

    private fun List<String>?.getStickerList(): List<Sticker> {
        val stickers = arrayListOf<Sticker>()

        this?.forEach { sticker ->
            val enumValue = sticker.getEnumOrNull<Sticker>()
            enumValue?.let { s ->
                stickers.add(s)
            }
        }

        return stickers
    }

    private fun getAddressStreetAndZip(address: AddressDto?): String {
        address?.let {
            val street = it.street ?: ""
            val zip = it.zip ?: ""
            val separator = if (street.isNotEmpty() && zip.isNotEmpty()) " ," else ""
            return "$street$separator$zip"
        } ?: return ""
    }

    private inline fun <reified T : Enum<T>> String?.getEnumOrNull(): T? =
        enumValues<T>().firstOrNull { it.name.equals(this, ignoreCase = true) }
}
