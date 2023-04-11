package com.example.network.retrofit

import com.example.network.model.NetworkImage
import retrofit2.http.GET

interface RetrofitAbpNetworkApiInterface {
    @GET("raw/wgkJgazE")
    suspend fun getImages(): List<NetworkImage>

}

@kotlinx.serialization.Serializable
 data class NetworkResponse<T>(
    val data: T
)