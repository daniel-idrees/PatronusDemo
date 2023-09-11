package com.patronusgroup.data.dto

import com.patronusgroup.domain.model.DeviceHolder

data class DeviceHolderDetailResponse(
    val id: String? = null,
)

fun DeviceHolderDetailResponse.map(): DeviceHolder {
    val deviceHolder = DeviceHolder(id)
    // TODO implement mapping
    return deviceHolder
}
