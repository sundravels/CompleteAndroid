package com.example.data.repository

import com.example.data.HomeDataRepository
import com.example.model.data.Images
import com.example.network.AbpNetworkSource
import com.example.network.model.NetworkImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


class GetHomeDataRepository @Inject constructor(private val abpNetworkSource: AbpNetworkSource):HomeDataRepository {
    //    val images: Flow<List<NetworkImage>> = flow {
//        abpNetworkSource.getImages()
//    }
    override val images: Flow<List<NetworkImage>>
        get() = flow {
            emit(abpNetworkSource.getImages())
        }


}