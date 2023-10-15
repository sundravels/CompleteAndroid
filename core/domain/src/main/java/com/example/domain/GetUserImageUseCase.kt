package com.example.domain

import com.example.data.repository.ImagesQuery
import com.example.data.repository.OfflineDessertsRepository
import com.example.data.repository.OfflineUserImagesRepository
import com.example.model.data.UserData
import com.example.model.data.DessertImages
import com.example.model.data.mapToUserImages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetUserImageUseCase @Inject constructor (
    private val offlineUserImagesRepository: OfflineUserImagesRepository,
    private val offlineImagesRepository: OfflineDessertsRepository
) {

    operator fun invoke(imagesQuery: ImagesQuery):Flow<List<DessertImages>> = offlineUserImagesRepository.getImages(imagesQuery).mapData(offlineImagesRepository.getImages())

    private fun Flow<List<DessertImages>>.mapData(images: Flow<UserData>):Flow<List<DessertImages>> = combine(images) { dessertImages: List<DessertImages>, userData: UserData ->
        dessertImages.mapToUserImages(dessertImages, userData)
    }

}