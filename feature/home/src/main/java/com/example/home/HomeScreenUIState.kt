package com.example.home

import com.example.model.data.UserImages
import com.example.network.model.NetworkImage

sealed interface HomeScreenUIState {

    object Loading:HomeScreenUIState
    object Error:HomeScreenUIState
    data class Shown(val imageList:List<UserImages>):HomeScreenUIState
}