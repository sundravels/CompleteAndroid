package com.example.data.di

import com.example.data.repository.ImagesRepository
import com.example.data.repository.OfflineImagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

//    @Binds
//    fun homeDataRepositoryModule(
//        getHomeDataRepository: GetHomeDataRepository
//    ):HomeDataRepository

    @Binds
    fun imagesRepositoryModule(offlineImagesRepository: OfflineImagesRepository):ImagesRepository
}