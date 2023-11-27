package com.example.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.home.HomeScreenUIState
import com.example.home.HomeScreenViewModel
import com.example.home.navigation.SeeAllScreenType
import com.example.home.navigation.navigateToSeeAllScreen
import com.example.model.data.DessertImages
import com.example.uiresources.RecipeSize
import com.example.uiresources.components.HighlyRated
import com.example.uiresources.components.HomeScreenClickHandler
import com.example.uiresources.components.RecommendedForYou
import com.example.uiresources.theme.Purple200
import com.example.uiresources.theme.Purple500
import com.openglsample.details.navigation.navigateToDetailScreen
import sundravels.androidbestpractices.search.navigation.navigateToSearchScreen


@Composable
fun HomeScreenRoute(
    navHostController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {

    val homeScreenState by homeScreenViewModel.homeScreenState.collectAsStateWithLifecycle()
    HomeScreen(
        homeScreenUIState = homeScreenState,
        homeScreenViewModel::addToFavourites,
        search = {
            navHostController.navigateToSearchScreen()
        }
    ) {
        when (it) {
            is HomeScreenClickHandler.DETAIl_SCREEN -> {
                navHostController.navigateToDetailScreen(meal_id = it.id)
            }
            is HomeScreenClickHandler.VIEW_ALL_SCREEN -> {
                navHostController.navigateToSeeAllScreen(
                    seeAllScreenType = when (it.type) {
                        0 -> SeeAllScreenType.RECOMMENDED_FOR_YOU.type
                        else -> SeeAllScreenType.HIGHLY_RATED.type
                    }
                )
            }
        }
    }
}


@Composable
internal fun HomeScreen(
    homeScreenUIState: HomeScreenUIState,
    favourite: (String, Boolean) -> Unit,
    search: () -> Unit,
    detailFn: (HomeScreenClickHandler) -> Unit
) {

    when (homeScreenUIState) {
        is HomeScreenUIState.Loading, HomeScreenUIState.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    color = Purple500, modifier = Modifier
                        .align(Alignment.Center)
                )
            }

        }
        is HomeScreenUIState.Success -> {
            HomeScreenDetails(
                categoriesMap = homeScreenUIState.dessertsMap,
                favourite = favourite,
                detailFn = detailFn,
                search = search
            )
        }
        else -> {}
    }

}

@Composable
fun HomeScreenDetails(
    categoriesMap: Map<String, List<DessertImages>>,
    modifier: Modifier = Modifier,
    favourite: (String, Boolean) -> Unit,
    detailFn: (HomeScreenClickHandler) -> Unit,
    search: () -> Unit,
) {


    LazyColumn {
        item {
            HomeScreenTopBar(search)
        }

        itemsIndexed(items = categoriesMap.keys.toList()) { index, recipeName ->
            Column {
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp)
                ) {
                    Text(
                        text = recipeName,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "View All",
                        style = MaterialTheme.typography.bodySmall,
                        color = Purple200,
                        modifier = Modifier.clickable {
                            detailFn(HomeScreenClickHandler.VIEW_ALL_SCREEN(index))
                        }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                LazyRow(
                    content = {
                        items(items = categoriesMap.get(recipeName) ?: listOf()) {

                            when (recipeName) {
                                stringResource(id = com.example.uiresources.R.string.recommended_for_you) -> RecommendedForYou(
                                    dessertImages = it,
                                    fn = favourite,
                                    detailFn = detailFn,
                                    modifier = Modifier.size(RecipeSize.RECOMMENDED_FOR_YOU.size)
                                )
                                stringResource(id = com.example.uiresources.R.string.highly_rated) -> HighlyRated(
                                    dessertImages = it,
                                    fn = favourite,
                                    detailFn = detailFn,
                                    modifier = Modifier.size(RecipeSize.HIGHLY_RATED.size)
                                )
                            }

                        }
                    },
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(start = 15.dp, end = 15.dp)
                )


            }

        }


    }

}
