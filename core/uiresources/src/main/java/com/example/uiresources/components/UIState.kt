package com.example.uiresources.components

import com.example.model.data.UserImages


sealed interface UIState{

    object Loading:UIState

    object Error:UIState

    data class Success(val data:List<UserImages>):UIState
}