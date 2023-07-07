package com.example.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.model.data.UserImages
import com.example.network.model.NetworkImage
import com.example.uiresources.components.ImageFeed
import com.example.uiresources.icon.AppIcons
import com.example.uiresources.icon.Icon
import com.example.uiresources.theme.Purple500

@Composable
 fun HomeScreenRoute(
    homeScreenViewModel: HomeScreenViewModel= hiltViewModel()
){

    val homeScreenState by homeScreenViewModel.feedState.collectAsStateWithLifecycle()
    HomeScreen(homeScreenUIState = homeScreenState,homeScreenViewModel::addToFavourites)
}


@Composable
internal fun HomeScreen(homeScreenUIState: HomeScreenUIState,favourite:(String,Boolean)->Unit){

    when(homeScreenUIState){
        is HomeScreenUIState.Loading,HomeScreenUIState.Error -> {
            CircularProgressIndicator(color = Purple500)
        }
        is HomeScreenUIState.Shown ->{
            HomeScreenList(imageList = homeScreenUIState.imageList, favourite=favourite)
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
