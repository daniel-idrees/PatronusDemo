package com.patronusgroup.domain.usecase

import com.patronusgroup.domain.repository.DeviceHolderRepository
import javax.inject.Inject

class GetDeviceHolderListUseCase @Inject constructor(
    private val deviceHolderRepository: DeviceHolderRepository,
) {
    suspend fun get() = deviceHolderRepository.getDeviceHolderList()
}
