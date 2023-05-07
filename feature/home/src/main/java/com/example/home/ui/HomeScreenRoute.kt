package com.example.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.model.data.UserImages
import com.example.network.model.NetworkImage
import com.example.uiresources.theme.Purple500

@Composable
 fun HomeScreenRoute(
    homeScreenViewModel: HomeScreenViewModel= hiltViewModel()
){

    val homeScreenState by homeScreenViewModel.images.collectAsStateWithLifecycle(initialValue = HomeScreenUIState.Loading)
    HomeScreen(homeScreenUIState = homeScreenState)
}


@Composable
internal fun HomeScreen(homeScreenUIState: HomeScreenUIState){

    when(homeScreenUIState){
        is HomeScreenUIState.Loading,HomeScreenUIState.Error -> {
            CircularProgressIndicator(color = Purple500)
        }
        is HomeScreenUIState.Shown ->{
            HomeScreenList(imageList = homeScreenUIState.imageList)
        }
    }

}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenList(
    imageList: List<UserImages>,
    modifier: Modifier = Modifier
) {

    LazyVerticalStaggeredGrid(
        modifier = modifier.padding(10.dp), columns = StaggeredGridCells.Fixed(2), content = {
            items(imageList) {
                homeGridItem(
                    userImages = it
                )
            }
        }, verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyStaggeredGridItemScope.homeGridItem(userImages: UserImages) {
    Card(elevation = CardDefaults.elevatedCardElevation(), shape = RoundedCornerShape(5.dp)) {
        AsyncImage(model = userImages.regularUrl, contentDescription = null)
    }

}