package com.patronusgroup.data.di

import com.patronusgroup.data.network.DeviceHolderService
import com.patronusgroup.data.repository.DeviceHolderRepositoryImpl
import com.patronusgroup.domain.repository.DeviceHolderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun providesBookRepository(
        deviceHolderService: DeviceHolderService,
    ): DeviceHolderRepository = DeviceHolderRepositoryImpl(deviceHolderService)
}
