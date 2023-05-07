package com.example.database.di

import com.example.database.AbpDatabase
import com.example.database.dao.ImagesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DaoModule{
    @Provides
    fun provideImagesDao(appDatabase: AbpDatabase):ImagesDao = appDatabase.imagesDao()

}