package com.example.data.repository

import com.example.data.Syncable
import com.example.model.data.DessertImages

interface UserDataRepository: Syncable  {
    fun getImages(imagesQuery: ImagesQuery):kotlinx.coroutines.flow.Flow<List<DessertImages>>
}