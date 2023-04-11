package com.example.network

import com.example.network.model.NetworkImage
import kotlinx.coroutines.flow.Flow

//interface containing all the api calls
interface AbpNetworkSource {
    suspend fun getImages():List<NetworkImage>
}