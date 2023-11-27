package sundravels.androidbestpractices.search

import com.example.model.data.DessertImages


sealed interface SearchUIState {

    val searchInput: String

    data class DessertList(
        val dessertList: List<DessertImages>,
        override val searchInput: String
    ) : SearchUIState

}