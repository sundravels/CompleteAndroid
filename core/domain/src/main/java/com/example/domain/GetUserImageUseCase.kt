package com.example.domain

import com.example.data.repository.ImagesQuery
import com.example.data.repository.OfflineImagesRepository
import com.example.data.repository.OfflineUserImagesRepository
import com.example.model.data.UserData
import com.example.model.data.UserImages
import com.example.model.data.mapToUserImages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetUserImageUseCase @Inject constructor (
    private val offlineUserImagesRepository: OfflineUserImagesRepository,
    private val offlineImagesRepository: OfflineImagesRepository
) {

    operator fun invoke(imagesQuery: ImagesQuery):Flow<List<UserImages>> = offlineUserImagesRepository.getImages(imagesQuery).mapData(offlineImagesRepository.getImages())

    private fun Flow<List<UserImages>>.mapData(images: Flow<UserData>):Flow<List<UserImages>> = combine(images) { userImages: List<UserImages>, userData: UserData ->
        userImages.mapToUserImages(userImages, userData)
    }

}