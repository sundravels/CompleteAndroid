package com.example.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.ImagesQuery
import com.example.data.repository.DessertsRepository
import com.example.domain.GetUserImageUseCase
import com.example.uiresources.components.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val dessertsRepository: DessertsRepository,
    private val getUserImageUseCase: GetUserImageUseCase
) : ViewModel() {
    private val userData = dessertsRepository.getImages()

    @OptIn(ExperimentalCoroutinesApi::class)
    val favouriteState: StateFlow<UIState> = userData.filterNot {
        it.favouriteImagesIds.isEmpty()
    }.map {
        getUserImageUseCase(ImagesQuery(it.favouriteImagesIds))
    }.flatMapLatest {
        it.map(UIState::Success)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UIState.Loading
    )

    fun updateFavourites(id: String, isAdded: Boolean) {
        viewModelScope.launch {
            dessertsRepository.addToFavourites(id = id, isFavourites = isAdded)
        }
    }

}