package com.patronusgroup.data.dto

import com.squareup.moshi.Json

data class DeviceHolderListResponse(
    @field:Json(name = "customers")
    val deviceHolders: List<DeviceHolderDto> = listOf(),
)

data class DeviceHolderDto(
    val id: Int? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val gender: String? = null,
    val phoneNumber: String? = null,
    val imageUrl: String? = null,
    val stickers: List<String>? = null,
)
