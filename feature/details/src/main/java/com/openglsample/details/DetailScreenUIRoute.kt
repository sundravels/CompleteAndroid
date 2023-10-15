package com.openglsample.details

import com.example.model.data.DessertLookUp


sealed interface DetailScreenState{
    object Loading: DetailScreenState
    object Error:DetailScreenState

    data class DessertLookUpDetails(val data:List<DessertLookUp>):DetailScreenState
}