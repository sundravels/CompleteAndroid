package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.AbpDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


const val database_name = "abp_database"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): AbpDatabase = Room.databaseBuilder(context,AbpDatabase::class.java,
        database_name).build()
}