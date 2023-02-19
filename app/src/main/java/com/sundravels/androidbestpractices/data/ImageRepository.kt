package com.sundravels.androidbestpractices.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepository @Inject constructor(private val imageDataSource: ImageDataSource) {
    suspend fun getImages():Images{
        return imageDataSource.getImages()
    }

}