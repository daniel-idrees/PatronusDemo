package com.patronusgroup.data.repository

import com.patronusgroup.data.dto.map
import com.patronusgroup.data.network.DeviceHolderService
import com.patronusgroup.domain.model.DeviceHolder
import com.patronusgroup.domain.repository.DeviceHolderRepository
import javax.inject.Inject

class DeviceHolderRepositoryImpl @Inject constructor(
    private val deviceHolderService: DeviceHolderService,
) : DeviceHolderRepository {
    override suspend fun getDeviceHolderList(): List<DeviceHolder>? =
        runCatching {
            deviceHolderService.getDeviceHolderList().map()
        }.onFailure {
            // TODO log
        }.getOrNull()

    override suspend fun getDeviceHolderDetail(id: String): DeviceHolder? =
        runCatching {
            deviceHolderService.getDeviceHolderDetail(id).map()
        }.onFailure {
            // TODO log
        }.getOrNull()
}
