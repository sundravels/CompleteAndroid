package com.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.HomeDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val homeDataRepository: HomeDataRepository) :
    ViewModel() {

       var images =   homeDataRepository.images.map { if(it.isEmpty()){
               HomeScreenUIState.Loading
           }else{
               HomeScreenUIState.Shown(it)
           }
       }



}