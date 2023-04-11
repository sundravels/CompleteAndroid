package com.example.network.module

import com.example.network.BuildConfig
import com.example.network.module.constants.NetworkConstants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun getJson() = Json {
        ignoreUnknownKeys = true
    }
    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            if(BuildConfig.DEBUG){
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }

        }).
    build()

    @Provides
    @Singleton
    fun retrofitInstance(json: Json): Retrofit = Retrofit.Builder()
        .baseUrl(NetworkConstants.BASE_URL).client(okHttpClient())
        .addConverterFactory(
            @OptIn(ExperimentalSerializationApi::class)
            json.asConverterFactory("application/json".toMediaType()),
        ).build()
}