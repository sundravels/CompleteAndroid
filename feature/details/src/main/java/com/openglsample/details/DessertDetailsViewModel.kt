package com.openglsample.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.DessertLookUpRepository
import com.example.model.data.DessertLookUp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DessertDetailsViewModel @Inject constructor(private val dessertLookUpRepository: DessertLookUpRepository
):ViewModel() {


    private var _arrDessertLookUpDetails = MutableStateFlow<DetailScreenState>(
        DetailScreenState.Loading
    )
    var arrDessertLookUpDetails:StateFlow<DetailScreenState> = _arrDessertLookUpDetails

    fun getDessertDetails(id:String){

        viewModelScope.launch {
            _arrDessertLookUpDetails.value = DetailScreenState.DessertLookUpDetails(dessertLookUpRepository.getDessertDetails(id) )
        }
    }
}