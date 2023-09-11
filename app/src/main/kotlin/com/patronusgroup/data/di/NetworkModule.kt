package com.patronusgroup.data.di

import com.patronusgroup.data.network.DeviceHolderService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().build()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        client: OkHttpClient,
    ): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)

    @Provides
    @Singleton
    fun provideDeviceHolderService(
        retrofitBuilder: Retrofit.Builder,
    ): DeviceHolderService = retrofitBuilder
        .baseUrl("https://api.code-challenge.patronus-group.com/")
        .build()
        .create(DeviceHolderService::class.java)
}
