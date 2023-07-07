package com.example.favourite.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.favourite.FavouritesViewModel
import com.example.model.data.UserImages
import com.example.uiresources.components.ImageFeed
import com.example.uiresources.components.UIState
import com.example.uiresources.theme.Purple500


@Composable
fun FavouriteScreenRoute(favouritesViewModel: FavouritesViewModel = hiltViewModel()) {
    val favouriteScreenState by favouritesViewModel.favouriteState.collectAsStateWithLifecycle()
    FavouriteScreen(favouriteScreenState, favourite = favouritesViewModel::updateFavourites)
}

@Composable
internal fun FavouriteScreen(favouriteScreenState: UIState,favourite: (String, Boolean) -> Unit) {
    when(favouriteScreenState){
        is UIState.Loading,UIState.Error -> {
            CircularProgressIndicator(color = Purple500)
        }
        is UIState.Success ->{
            HomeScreenList(imageList = favouriteScreenState.data, favourite = favourite)
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenList(
    imageList: List<UserImages>,
    modifier: Modifier = Modifier,
    favourite:(String,Boolean)->Unit
) {
    ImageFeed(modifier = modifier, column = 2, imageList = imageList , favourite = favourite)
}

