package com.patronusgroup.data.repository

import com.patronusgroup.data.dto.MapperHelper.toDeviceHolderDetail
import com.patronusgroup.data.dto.MapperHelper.toDeviceHolderList
import com.patronusgroup.data.network.DeviceHolderService
import com.patronusgroup.domain.model.DeviceHolder
import com.patronusgroup.domain.model.DeviceHolderDetail
import com.patronusgroup.domain.repository.DeviceHolderRepository
import javax.inject.Inject

class DeviceHolderRepositoryImpl @Inject constructor(
    private val deviceHolderService: DeviceHolderService,
) : DeviceHolderRepository {
    override suspend fun getDeviceHolderList(): List<DeviceHolder>? =
        runCatching {
            val response = deviceHolderService.getDeviceHolderList().toDeviceHolderList()
            response
        }.onFailure {
            // TODO log
        }.getOrNull()

    override suspend fun getDeviceHolderDetail(id: String): DeviceHolderDetail? =
        runCatching {
            val response = deviceHolderService.getDeviceHolderDetail(id).toDeviceHolderDetail()
            response
        }.onFailure {
            // TODO log
        }.getOrNull()
}
