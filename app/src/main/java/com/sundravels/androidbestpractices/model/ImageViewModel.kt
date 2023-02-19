package com.sundravels.androidbestpractices.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sundravels.androidbestpractices.data.ImageRepository
import com.sundravels.androidbestpractices.data.Images
import com.sundravels.androidbestpractices.state.UiIntent
import com.sundravels.androidbestpractices.state.UiState
import com.sundravels.androidbestpractices.state.UserIntent
import com.sundravels.androidbestpractices.utils.transformResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(private val imageRepository: ImageRepository):ViewModel(),UserIntent {

    private val uiState= MutableLiveData<UiState<Images>>()
    val _uiState = uiState

    override val userIntent: Flow<UiIntent>
        get() = flow {
            UiIntent.GetData
        }

    init {
        viewModelScope.launch {
            userIntent.collect {
                when(it){
                    UiIntent.GetData -> getImage()
                    UiIntent.RefreshData -> refreshData()
                }

            }
        }
    }


    fun getImage(){
        viewModelScope.launch {
             uiState.value = transformResponse(imageRepository.getImages())
        }
    }

    fun refreshData(){
        viewModelScope.launch {

        }
    }




}