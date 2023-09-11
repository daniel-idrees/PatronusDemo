package com.patronusgroup.domain.repository

import com.patronusgroup.domain.model.DeviceHolder

interface DeviceHolderRepository {
    suspend fun getDeviceHolderList(): List<DeviceHolder>?
    suspend fun getDeviceHolderDetail(id: String): DeviceHolder?
}
