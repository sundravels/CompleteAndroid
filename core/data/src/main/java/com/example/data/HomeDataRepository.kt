package com.example.data

import com.example.model.data.UserImages

interface HomeDataRepository {
   val images:kotlinx.coroutines.flow.Flow<List<UserImages>>
}