package com.example.data.di

import com.example.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun imagesRepositoryModule(offlineImagesRepository: OfflineDessertsRepository):DessertsRepository

    @Binds
    fun userImagesRepository(offlineUserImagesRepository: OfflineUserImagesRepository):UserDataRepository

    @Binds
    fun dessertLookUpRepository(dessertsRepository: DessertDetailsRepository):DessertLookUpRepository
}

