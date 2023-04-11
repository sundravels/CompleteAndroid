package com.example.network.di

import com.example.network.AbpNetworkSource
import com.example.network.retrofit.RetrofitAbpNetwork
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun abpNetworkSource(retrofitAbpNetwork: RetrofitAbpNetwork):AbpNetworkSource
}