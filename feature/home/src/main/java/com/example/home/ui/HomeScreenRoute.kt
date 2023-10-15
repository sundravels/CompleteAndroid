package com.example.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.model.data.DessertImages
import com.example.uiresources.components.ImageFeed
import com.example.uiresources.theme.Purple500
import com.openglsample.details.navigation.detailScreenRoute
import com.openglsample.details.navigation.navigateToDetailScreen
import com.openglsample.details.ui.DetailScreenRoute

@Composable
 fun HomeScreenRoute(
    navHostController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel= hiltViewModel()
){

    val homeScreenState by homeScreenViewModel.feedState.collectAsStateWithLifecycle()
    HomeScreen(homeScreenUIState = homeScreenState,homeScreenViewModel::addToFavourites){
        navHostController.navigateToDetailScreen(meal_id = it)
    }
}


@Composable
internal fun HomeScreen(homeScreenUIState: HomeScreenUIState,favourite:(String,Boolean)->Unit,detailFn:(String?)->Unit){

    when(homeScreenUIState){
        is HomeScreenUIState.Loading,HomeScreenUIState.Error -> {
            CircularProgressIndicator(color = Purple500)
        }
        is HomeScreenUIState.Shown ->{
            HomeScreenList(imageList = homeScreenUIState.imageList, favourite=favourite, detailFn = detailFn)
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
    ImageFeed(modifier = modifier, column = 2, imageList = imageList , favourite = favourite, detailFn = detailFn)
}
