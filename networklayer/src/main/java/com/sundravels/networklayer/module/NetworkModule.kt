package com.sundravels.networklayer.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun okHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    fun retrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(NetworkConstants.BASE_URL).client(okHttpClient())
        .addConverterFactory(GsonConverterFactory.create()).build()
}