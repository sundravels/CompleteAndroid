package com.openglsample.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.DessertLookUpRepository
import com.example.data.repository.DessertsRepository
import com.example.model.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DessertDetailsViewModel @Inject constructor(
    private val dessertLookUpRepository: DessertLookUpRepository,
    private val dessertsRepository: DessertsRepository,
    ) : ViewModel() {

    val isFavourite =
        dessertsRepository.getImages().filterNot { it.favouriteImagesIds.isEmpty() }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UserData(
                setOf()
            )
        )

    private var _arrDessertLookUpDetails = MutableStateFlow<DetailScreenState>(
        DetailScreenState.Loading
    )
    var arrDessertLookUpDetails: StateFlow<DetailScreenState> = _arrDessertLookUpDetails

    fun getDessertDetails(id: String) {
        viewModelScope.launch {
            _arrDessertLookUpDetails.value =
                DetailScreenState.DessertLookUpDetails(dessertLookUpRepository.getDessertDetails(id))
        }
    }

    fun addToFavourites(id: String, isFavourite: Boolean) {
        viewModelScope.launch {
            dessertsRepository.addToFavourites(id, isFavourite)
        }
    }

}