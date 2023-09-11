package com.patronusgroup.domain.model

import com.patronusgroup.domain.model.enums.Gender
import com.patronusgroup.domain.model.enums.Sticker

data class DeviceHolderDetail(
    val imageUrl: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val fullName: String? = null,
    val stickers: List<Sticker>? = null,
    val gender: Gender? = null,
    val phoneNumber: String? = null,
    val addressStreetAndZip: String? = null,
    val city: String? = null,
)
