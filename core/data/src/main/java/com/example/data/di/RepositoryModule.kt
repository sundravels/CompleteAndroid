package com.example.data.di

import com.example.data.HomeDataRepository
import com.example.data.repository.GetHomeDataRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun homeDataRepositoryModule(
        getHomeDataRepository: GetHomeDataRepository
    ):HomeDataRepository
}