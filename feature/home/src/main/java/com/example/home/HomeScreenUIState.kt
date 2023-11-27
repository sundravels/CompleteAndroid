package com.example.home

import com.example.model.data.DessertImages

sealed interface HomeScreenUIState {

    object Loading:HomeScreenUIState
    object Error:HomeScreenUIState
    data class Shown(val imageList:List<DessertImages>):HomeScreenUIState
    data class Success(val dessertsMap:Map<String,List<DessertImages>>):HomeScreenUIState
}