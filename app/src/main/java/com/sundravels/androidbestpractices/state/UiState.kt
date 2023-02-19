package com.sundravels.androidbestpractices.state


//Generic class to handle State
sealed class DataHandler<T>{
  data class Success<T>(val data:T):DataHandler<T>()
  data class Error<T>(val errorMessage:String):DataHandler<T>()
  object Loading:DataHandler<String>()
}

sealed class UiIntent{
  object GetData:UiIntent()
  object RefreshData:UiIntent()
}

//Common Class to Handle Data
data class UiState<T>(val data:T,val isLoading:Boolean=false)

enum class Loader{
  SHOW,HIDE
}
