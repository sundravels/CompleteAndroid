package com.sundravels.androidbestpractices.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.network.di.AbpDispatchers.IO
import com.example.network.di.Dispatcher
import com.sundravels.androidbestpractices.core.datastore.UserPreferencesOuterClass.UserPreferences
import com.sundravels.androidbestpractices.data.UserPreferencesSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


private const val USER_PREFERENCES_NAME = "user_preferences"
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun dataStoreModule(userPreferencesSerializer:UserPreferencesSerializer,
    @ApplicationContext context: Context):DataStore<UserPreferences> =DataStoreFactory.create(serializer = userPreferencesSerializer,
 scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    ){ context.dataStoreFile(USER_PREFERENCES_NAME) }
}