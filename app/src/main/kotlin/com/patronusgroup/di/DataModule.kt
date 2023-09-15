package com.patronusgroup.di

import com.patronusgroup.data.network.DeviceHolderService
import com.patronusgroup.data.repository.DeviceHolderRepositoryImpl
import com.patronusgroup.domain.repository.DeviceHolderRepository
import com.patronusgroup.domain.usecase.GetDeviceHolderDetailUseCase
import com.patronusgroup.domain.usecase.GetDeviceHolderListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DataModule {
    @Provides
    @Singleton
    fun providesDeviceHolderRepository(
        deviceHolderService: DeviceHolderService,
    ): DeviceHolderRepository = DeviceHolderRepositoryImpl(deviceHolderService)

    @Provides
    fun provideGetDeviceHolderDetailUseCase(
        deviceHolderRepository: DeviceHolderRepository,
    ): GetDeviceHolderDetailUseCase = GetDeviceHolderDetailUseCase(deviceHolderRepository)

    @Provides
    fun provideGetDeviceHolderListUseCase(
        deviceHolderRepository: DeviceHolderRepository,
    ): GetDeviceHolderListUseCase = GetDeviceHolderListUseCase(deviceHolderRepository)
}
