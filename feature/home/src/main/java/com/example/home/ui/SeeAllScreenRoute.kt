package com.example.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.home.HomeScreenUIState
import com.example.home.SeeAllScreenViewModel
import com.example.home.navigation.SeeAllScreenType
import com.example.model.data.DessertImages
import com.example.uiresources.R
import com.example.uiresources.components.DessertsList
import com.example.uiresources.components.HomeScreenClickHandler
import com.example.uiresources.theme.Purple500
import com.openglsample.details.navigation.navigateToDetailScreen


@Composable
fun SeeAllScreenRoute(
    navHostController: NavHostController,
    seeAllScreenType: String,
    seeAllScreenViewModel: SeeAllScreenViewModel = hiltViewModel(),
    backPress: () -> Unit
) {
    val homeScreenState by seeAllScreenViewModel.seeAllScreenState.collectAsStateWithLifecycle()

    SeeAllScreen(
        seeAllScreenType = seeAllScreenType,
        homeScreenUIState = homeScreenState,
        seeAllScreenViewModel = seeAllScreenViewModel,
        navHostController,
        backPress
    )

}

@Composable
internal fun SeeAllScreen(
    seeAllScreenType: String,
    homeScreenUIState: HomeScreenUIState,
    seeAllScreenViewModel: SeeAllScreenViewModel,
    navHostController: NavHostController,
    backPress: () -> Unit
) {

    when (homeScreenUIState) {
        is HomeScreenUIState.Success -> {
            SeeAllScreenContent(seeAllScreenType, desserts = when (seeAllScreenType) {
                SeeAllScreenType.RECOMMENDED_FOR_YOU.type -> homeScreenUIState.dessertsMap.get(
                    SeeAllScreenType.RECOMMENDED_FOR_YOU.type
                )
                else -> homeScreenUIState.dessertsMap.get(SeeAllScreenType.HIGHLY_RATED.type)
            }, favourite = { id, isFavourite ->
                seeAllScreenViewModel.addToFavourites(id, isFavourite)
            }, detailFn = {
                navHostController.navigateToDetailScreen(meal_id = (it as HomeScreenClickHandler.DETAIl_SCREEN).id)
            }, backPress
            )
        }
        else -> {}
    }

}

@Composable
internal fun SeeAllScreenContent(
    seeAllScreenType: String,
    desserts: List<DessertImages>?,
    favourite: (String, Boolean) -> Unit,
    detailFn: (HomeScreenClickHandler) -> Unit,
    backPress: () -> Unit
) {

    LazyColumn(content = {
        item {
            SeeAllTopBar(seeAllScreenType = seeAllScreenType, backPress)
        }

        desserts?.let {
            items(it) { dessertImages ->
                DessertsList(dessertImages, favourite, detailFn)
            }
        }


    })
}

@Composable
fun SeeAllTopBar(seeAllScreenType: String, backPress: () -> Unit) {

    Column(modifier = Modifier.padding(top = 30.dp, start = 15.dp, end = 15.dp)) {

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_navigate_before_24),
            contentDescription = null,
            modifier = Modifier
                .drawBehind {
                    drawCircle(color = Purple500, radius = (0.8f * size.height))
                }
                .clickable {
                    backPress()
                }, tint = Color.White
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = seeAllScreenType,
            style = MaterialTheme.typography.titleLarge.copy(MaterialTheme.colorScheme.onBackground)
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp),
            thickness = 0.2.dp,
            color = MaterialTheme.colorScheme.outline
        )

    }
}