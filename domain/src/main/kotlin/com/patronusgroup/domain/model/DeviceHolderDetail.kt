package com.patronusgroup.domain.model

import com.patronusgroup.domain.model.enums.Gender
import com.patronusgroup.domain.model.enums.Sticker

data class DeviceHolderDetail(
    val imageUrl: String?,
    val latitude: Double?,
    val longitude: Double?,
    val fullName: String,
    val stickers: List<Sticker>,
    val gender: Gender?,
    val phoneNumber: String,
    val fullAddress: String,
    val nameInitials: String,
)
