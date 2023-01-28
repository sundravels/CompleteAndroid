package com.sundravels.networklayer.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun okHttpModule():OkHttpClient = OkHttpClient.Builder().build()


//    @Provides
//    fun okHttpInterceptor() =
//

    @Provides
    fun retrofitInstance(okHttpClient: OkHttpClient) = Retrofit.Builder().baseUrl("").client(okHttpClient).build()
}