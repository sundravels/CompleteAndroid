package com.example.data.repository

import com.example.data.Syncable
import com.example.model.data.DessertImages

interface UserDataRepository: Syncable  {
    fun getImages(dessertsImagesQuery: DessertsImagesQuery):kotlinx.coroutines.flow.Flow<List<DessertImages>>
}