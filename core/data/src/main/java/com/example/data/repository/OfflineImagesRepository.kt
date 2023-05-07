package com.example.data.repository

import com.example.data.Synchronizer
import com.example.data.model.asEntity
import com.example.database.dao.ImagesDao
import com.example.database.model.ImagesEntity
import com.example.database.model.asUserImage
import com.example.model.data.UserImages
import com.example.network.AbpNetworkSource
import com.example.network.model.NetworkImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineImagesRepository @Inject constructor(
    private val imagesDao: ImagesDao,
    private val abpNetworkSource: AbpNetworkSource
):ImagesRepository{
    override fun getImages(): Flow<List<UserImages>> = imagesDao.getAllImages().map {
        it.map(ImagesEntity::asUserImage)
    }
    override suspend fun syncWith(synchronizer: Synchronizer) = kotlin.runCatching {
        val imageResource = abpNetworkSource.getImages()
        imageResource.map(NetworkImage::asEntity).forEach {
            imagesDao.insert(it)
        }
    }.isSuccess
}