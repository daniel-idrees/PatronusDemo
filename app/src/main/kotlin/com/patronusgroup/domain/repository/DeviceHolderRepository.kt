package com.patronusgroup.domain.repository

interface DeviceHolderRepository {
    suspend fun getDeviceHolderList()
    suspend fun getDeviceHolderDetail(id: String)
}
