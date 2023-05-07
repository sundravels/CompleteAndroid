package com.example.home

import androidx.lifecycle.ViewModel
import com.example.data.repository.ImagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
imagesRepository: ImagesRepository) :
    ViewModel() {
       var images =   imagesRepository.getImages().map { if(it.isEmpty()){
               HomeScreenUIState.Loading
           }else{
               HomeScreenUIState.Shown(it)
           }
       }
}