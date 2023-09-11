package com.patronusgroup.data.dto

import com.patronusgroup.domain.model.DeviceHolder

data class DeviceHolderListResponse(
    val id: String? = null,
)

fun DeviceHolderListResponse.map(): List<DeviceHolder> {
    val list = arrayListOf<DeviceHolder>()
    // TODO implement mapping
    return list
}
