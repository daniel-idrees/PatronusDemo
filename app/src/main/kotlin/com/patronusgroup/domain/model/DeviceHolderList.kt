package com.patronusgroup.domain.model

import com.patronusgroup.domain.model.enums.Gender
import com.patronusgroup.domain.model.enums.Sticker

data class DeviceHolder(
    val id: Int,
    val fullName: String,
    val gender: Gender?,
    val phoneNumber: String,
    val imageUrl: String?,
    val stickers: List<Sticker>,
    val nameInitials: String,
)
