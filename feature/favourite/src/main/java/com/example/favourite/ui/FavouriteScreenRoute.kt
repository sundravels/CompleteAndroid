package com.example.favourite.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.favourite.FavouritesViewModel
import com.example.model.data.DessertImages
import com.example.uiresources.components.*
import com.example.uiresources.icon.AppIcons
import com.example.uiresources.theme.Purple500
import com.openglsample.details.navigation.navigateToDetailScreen


@Composable
fun FavouriteScreenRoute(
    favouritesViewModel: FavouritesViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val favouriteScreenState by favouritesViewModel.favouriteState.collectAsStateWithLifecycle()
    FavouriteScreen(favouriteScreenState, favourite = favouritesViewModel::updateFavourites) {
        when (it) {
            is HomeScreenClickHandler.DETAIl_SCREEN -> {
                navHostController.navigateToDetailScreen(meal_id = it.id)
            }
            else -> {}
        }
    }
}

@Composable
internal fun FavouriteScreen(
    favouriteScreenState: UIState,
    favourite: (String, Boolean) -> Unit,
    detailFn: (HomeScreenClickHandler) -> Unit
) {
    when (favouriteScreenState) {
        is UIState.Loading, UIState.Error -> {
            CircularProgressIndicator(color = Purple500)
        }
        is UIState.Success -> {
            FavouriteScreen(
                imageList = favouriteScreenState.data,
                favourite = favourite,
                detailFn = detailFn
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)


@Composable
fun FavouriteScreen(
    imageList: List<DessertImages>,
    favourite: (String, Boolean) -> Unit,
    detailFn: (HomeScreenClickHandler) -> Unit
) {

    FavouriteFeed(
        imageList,
        favourite,
        detailFn
    )
}

@Composable
fun FavouriteFeed(
    imageList: List<DessertImages>,
    favourite: (String, Boolean) -> Unit,
    detailFn: (HomeScreenClickHandler) -> Unit
) {

    LazyColumn(content = {

        item {
            FavouriteTopBar()
        }

        items(imageList) {
            FavouriteFeedItem(dessertImages = it, favourite = favourite, detailFn = detailFn)
        }

    })
}

@Composable
fun FavouriteFeedItem(
    dessertImages: DessertImages,
    favourite: (String, Boolean) -> Unit,
    detailFn: (HomeScreenClickHandler) -> Unit
) {

    DessertsList(dessertImages,favourite, detailFn)
}

