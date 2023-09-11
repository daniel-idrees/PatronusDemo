package com.patronusgroup.data.repository

import com.patronusgroup.data.network.DeviceHolderService
import com.patronusgroup.domain.repository.DeviceHolderRepository
import javax.inject.Inject

class DeviceHolderRepositoryImpl @Inject constructor(
    private val deviceHolderService: DeviceHolderService,
) : DeviceHolderRepository {
    override suspend fun getDeviceHolderList() {
        runCatching {
            val response = deviceHolderService.getDeviceHolderList()
        }.onFailure {
            // TODO log
        }
    }

    override suspend fun getDeviceHolderDetail(id: String) {
        runCatching {
            val response = deviceHolderService.getDeviceHolderDetail(id)
        }.onFailure {
            // TODO log
        }
    }
}
