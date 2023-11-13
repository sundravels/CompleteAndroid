package com.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.DessertsImagesQuery
import com.example.data.repository.DessertsRepository
import com.example.domain.GetUserImageUseCase
import com.example.model.data.DessertImages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val dessertsRepository: DessertsRepository,
    private val getUserImageUseCase: GetUserImageUseCase) :
    ViewModel() {
       private val imagesData =  dessertsRepository.getImages()

    val feedState = dessertsRepository.mapToUserImages(getUserImageUseCase).
         map(HomeScreenUIState::Shown)
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeScreenUIState.Loading
    )

    fun addToFavourites(id:String,isFavourite:Boolean){
        viewModelScope.launch {
            dessertsRepository.addToFavourites(id,isFavourite)
        }
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    private fun DessertsRepository.mapToUserImages(
        getUserImageUseCase: GetUserImageUseCase
    ):Flow<List<DessertImages>> = imagesData.map {
        it.favouriteImagesIds.ifEmpty {
            null
        }
    }.flatMapLatest {
        getUserImageUseCase(DessertsImagesQuery(null))
    }

}

