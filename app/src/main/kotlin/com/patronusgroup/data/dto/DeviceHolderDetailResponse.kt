package com.patronusgroup.data.dto

import com.patronusgroup.data.dto.DtoMapperHelper.getEnumOrNull
import com.patronusgroup.data.dto.DtoMapperHelper.getFormattedPhoneNumber
import com.patronusgroup.data.dto.DtoMapperHelper.getStickerList
import com.patronusgroup.domain.model.DeviceHolderDetail
import com.patronusgroup.domain.model.enums.Gender
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

fun DeviceHolderDetailResponse.toDeviceHolderDetail(): DeviceHolderDetail =
    DeviceHolderDetail(
        imageUrl = imageUrl,
        latitude = latitude,
        longitude = longitude,
        fullName = DtoMapperHelper.getFullName(firstName, lastName),
        stickers = getStickerList(stickers),
        gender = gender.getEnumOrNull<Gender>(),
        phoneNumber = getFormattedPhoneNumber(DtoMapperHelper.CountryCode.USA, phoneNumber),
        addressStreetAndZip = DtoMapperHelper.getAddressStreetAndZip(address),
        city = address?.city,
    )
