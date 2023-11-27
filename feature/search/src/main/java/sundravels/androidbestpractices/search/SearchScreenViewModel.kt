package sundravels.androidbestpractices.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.SearchRecipeRepository
import com.example.data.repository.SearchRepository
import com.example.domain.GetSearchRecipeUseCase
import com.example.model.data.DessertImages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    val searchRecipeRepository: SearchRepository,
    val searchRecipeUseCase: GetSearchRecipeUseCase
) : ViewModel() {

   private var _searchText = MutableStateFlow("")
   val searchText = _searchText.asStateFlow()


    val getImages = searchRecipeRepository.getSearchResults(searchRecipeUseCase)
    private var _dessertList = MutableStateFlow(getImages)

    val dessertList = _searchText.combine(getImages) { text, list ->
        list.filterNot { text.isEmpty() }.filter {
            it.strMeal?.startsWith(text, true) == true
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = listOf()
    )




    fun updateSearchInput(text: String) {
        _searchText.value = text
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    fun SearchRecipeRepository.getSearchResults(
        searchRecipeUseCase: GetSearchRecipeUseCase
    ): Flow<List<DessertImages>>  {
        val data = searchRecipeUseCase()
      Log.v("TAG","${data}")
        return data

    }


    fun addToFavourites(id: String, isFavourite: Boolean) {
        viewModelScope.launch {
            searchRecipeRepository.addToFavourites(id, isFavourite)
        }
    }
}
