package com.example.data

import com.example.network.model.NetworkImage

interface HomeDataRepository {
   val images:kotlinx.coroutines.flow.Flow<List<NetworkImage>>
}