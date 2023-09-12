package com.patronusgroup.data.dto

import com.patronusgroup.data.dto.DtoMapperHelper.getEnumOrNull
import com.patronusgroup.data.dto.DtoMapperHelper.getStickerList
import com.patronusgroup.domain.model.DeviceHolder
import com.patronusgroup.domain.model.enums.Gender
import com.squareup.moshi.Json

data class DeviceHolderListResponse(
    @field:Json(name = "customers")
    val deviceHolders: List<DeviceHolderDto> = listOf(),
)

fun DeviceHolderListResponse.toDeviceHolderList(): List<DeviceHolder> {
    val list = arrayListOf<DeviceHolder>()
    deviceHolders.forEach { holder ->
        with(holder) {
            id?.let { holderId ->
                list.add(
                    DeviceHolder(
                        id = holderId,
                        fullName = DtoMapperHelper.getFullName(firstName, lastName),
                        gender = gender.getEnumOrNull<Gender>(),
                        phoneNumber = phoneNumber,
                        imageUrl = imageUrl,
                        stickers = getStickerList(stickers),
                    ),
                )
            }
        }
    }
    return list
}
