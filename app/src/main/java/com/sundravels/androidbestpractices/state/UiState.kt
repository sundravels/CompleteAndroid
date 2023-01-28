package com.sundravels.androidbestpractices.state


//Generic class to handle State
sealed class UiState<T>{
  data class Success<T>(val data:T):UiState<T>()
  data class Error<T>(val errorMessage:String):UiState<T>()
  object Loading:UiState<String>()
}

sealed class UiIntent{
  object GetData:UiIntent()
  object RefreshData:UiIntent()
}

//Common Class to Handle Data
data class DataHandler<T>(val data:T,val isLoading:Boolean)

enum class Loader{
  SHOW,HIDE
}
