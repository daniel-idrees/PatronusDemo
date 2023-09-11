package com.patronusgroup.data.network

import com.patronusgroup.data.dto.DeviceHolderDetailResponse
import com.patronusgroup.data.dto.DeviceHolderListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DeviceHolderService {
    @GET("users")
    suspend fun getDeviceHolderList(): DeviceHolderListResponse

    @GET("users")
    suspend fun getDeviceHolderDetail(@Query("id") id: String): DeviceHolderDetailResponse
}
