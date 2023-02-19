package com.sundravels.androidbestpractices.data

import com.sundravels.androidbestpractices.api.ImageService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageDataSource @Inject constructor(val imageService: ImageService) {


    suspend fun getImages():Images{
        return Images()
    }
}