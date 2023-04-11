package com.example.domain.state

import android.provider.MediaStore.Images

sealed interface HomeUiState{

    object Loading : HomeUiState
    object Error: HomeUiState
    data class Data(val list: ArrayList<Images>) : HomeUiState
}