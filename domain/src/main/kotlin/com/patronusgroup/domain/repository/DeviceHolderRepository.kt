package com.patronusgroup.domain.repository

import com.patronusgroup.domain.model.DeviceHolder
import com.patronusgroup.domain.model.DeviceHolderDetail

interface DeviceHolderRepository {
    suspend fun getDeviceHolderList(): List<DeviceHolder>?
    suspend fun getDeviceHolderDetail(id: String): DeviceHolderDetail?
}
