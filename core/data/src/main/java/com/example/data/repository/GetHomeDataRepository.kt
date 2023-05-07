package com.example.data.repository

import com.example.data.HomeDataRepository
import com.example.network.AbpNetworkSource
import com.example.network.model.NetworkImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


//class GetHomeDataRepository @Inject constructor(private val abpNetworkSource: AbpNetworkSource) :
//    HomeDataRepository {
//    override val images: Flow<List<NetworkImage>>
//        get() = flow {
//            emit(abpNetworkSource.getImages())
//        }
//
//
//}