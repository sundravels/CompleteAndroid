package com.example.network.retrofit

import android.util.Log
import com.example.network.AbpNetworkSource
import com.example.network.model.NetworkImage
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RetrofitAbpNetwork @Inject constructor(private val retrofit: Retrofit):AbpNetworkSource {

    private val retrofitAbpNetworkApiInterface = retrofit.create(RetrofitAbpNetworkApiInterface::class.java)

    override suspend fun getImages(): List<NetworkImage> = retrofitAbpNetworkApiInterface.getImages()

}