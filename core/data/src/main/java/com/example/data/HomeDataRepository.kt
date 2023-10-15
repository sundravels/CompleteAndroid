package com.example.data

import com.example.model.data.DessertImages

interface HomeDataRepository {
   val images:kotlinx.coroutines.flow.Flow<List<DessertImages>>
}