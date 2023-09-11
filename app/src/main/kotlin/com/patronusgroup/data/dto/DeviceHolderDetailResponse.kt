package com.patronusgroup.data.dto

import com.squareup.moshi.Json

data class DeviceHolderDetailResponse(
    val id: Int? = null,
    val imageUrl: String? = null,
    @field:Json(name = "currentLatitude")
    val latitude: Double? = null,
    @field:Json(name = "currentLongitude")
    val longitude: Double? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val stickers: List<String>? = null,
    val gender: String? = null,
    val phoneNumber: String? = null,
    val address: AddressDto? = null,
)

data class AddressDto(
    val street: String? = null,
    val city: String? = null,
    val state: String? = null,
    val zip: String? = null,
    val country: String? = null,
)
