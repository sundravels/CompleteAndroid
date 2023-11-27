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
    private val getUserImageUseCase: GetUserImageUseCase
) :
    ViewModel() {
    private val imagesData = dessertsRepository.getImages()

    val homeScreenState =
        dessertsRepository.mapToHomeCategories(getUserImageUseCase).map(HomeScreenUIState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = HomeScreenUIState.Loading
            )

    fun addToFavourites(id: String, isFavourite: Boolean) {
        viewModelScope.launch {
            dessertsRepository.addToFavourites(id, isFavourite)
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    private fun DessertsRepository.mapToHomeCategories(
        getUserImageUseCase: GetUserImageUseCase
    ): Flow<HashMap<String, List<DessertImages>>> = imagesData.map {
        it.favouriteImagesIds.ifEmpty {
            null
        }
    }.flatMapLatest {
        getUserImageUseCase()
    }

}

