package com.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.ImagesQuery
import com.example.data.repository.ImagesRepository
import com.example.domain.GetUserImageUseCase
import com.example.model.data.UserImages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val imagesRepository: ImagesRepository,
private val getUserImageUseCase: GetUserImageUseCase) :
    ViewModel() {
       private val imagesData =  imagesRepository.getImages()

    val feedState = imagesRepository.mapToUserImages(getUserImageUseCase).
         map(HomeScreenUIState::Shown)
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeScreenUIState.Loading
    )

    fun addToFavourites(id:String,isFavourite:Boolean){
        viewModelScope.launch {
            imagesRepository.addToFavourites(id,isFavourite)
        }
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    private fun ImagesRepository.mapToUserImages(
        getUserImageUseCase: GetUserImageUseCase
    ):Flow<List<UserImages>> = imagesData.map {
        it.favouriteImagesIds.ifEmpty {
            null
        }
    }.flatMapLatest {
        getUserImageUseCase(ImagesQuery(null))
    }

}

