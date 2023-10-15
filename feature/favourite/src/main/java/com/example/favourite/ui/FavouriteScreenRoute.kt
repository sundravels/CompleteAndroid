package com.example.favourite.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.favourite.FavouritesViewModel
import com.example.model.data.DessertImages
import com.example.uiresources.components.ImageFeed
import com.example.uiresources.components.UIState
import com.example.uiresources.theme.Purple500
import com.openglsample.details.navigation.navigateToDetailScreen


@Composable
fun FavouriteScreenRoute(favouritesViewModel: FavouritesViewModel = hiltViewModel(), navHostController: NavHostController) {
    val favouriteScreenState by favouritesViewModel.favouriteState.collectAsStateWithLifecycle()
    FavouriteScreen(favouriteScreenState, favourite = favouritesViewModel::updateFavourites){
     navHostController.navigateToDetailScreen()
    }
}

@Composable
internal fun FavouriteScreen(favouriteScreenState: UIState,favourite: (String, Boolean) -> Unit,detailFn:(String?)->Unit) {
    when(favouriteScreenState){
        is UIState.Loading,UIState.Error -> {
            CircularProgressIndicator(color = Purple500)
        }
        is UIState.Success ->{
            HomeScreenList(imageList = favouriteScreenState.data, favourite = favourite, detailFn =detailFn)
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenList(
    imageList: List<DessertImages>,
    modifier: Modifier = Modifier,
    favourite:(String,Boolean)->Unit,
    detailFn:(String?)->Unit
) {
    ImageFeed(modifier = modifier, column = 2, imageList = imageList , favourite = favourite,detailFn)
}

