package com.patronusgroup.core.di

import com.patronusgroup.core.util.ConnectivityManagerNetworkMonitor
import com.patronusgroup.core.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ConnectivityModule {

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}
