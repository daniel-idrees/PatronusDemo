package com.patronusgroup.domain.model

import com.patronusgroup.domain.model.enums.Gender
import com.patronusgroup.domain.model.enums.Sticker

data class DeviceHolder(
    val id: Int,
    val fullName: String? = null,
    val gender: Gender? = null,
    val phoneNumber: String? = null,
    val imageUrl: String? = null,
    val stickers: List<Sticker> = listOf(),
)
