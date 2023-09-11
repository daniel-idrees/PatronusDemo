package com.patronusgroup.domain.usecase

import com.patronusgroup.domain.repository.DeviceHolderRepository
import javax.inject.Inject

class GetDeviceHolderDetailUseCase  @Inject constructor(
    private val deviceHolderRepository: DeviceHolderRepository,
) {
    suspend fun get(id: String) = deviceHolderRepository.getDeviceHolderDetail(id)
}
