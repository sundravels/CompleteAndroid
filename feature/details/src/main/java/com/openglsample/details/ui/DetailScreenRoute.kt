package com.openglsample.details.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.model.data.DessertLookUp
import com.example.uiresources.R
import com.example.uiresources.components.AbpTopBar
import com.example.uiresources.dimen.Dimensions
import com.example.uiresources.theme.DarkPurpleGray10
import com.example.uiresources.theme.DarkPurpleGray90
import com.example.uiresources.theme.DarkPurpleGray99
import com.example.uiresources.theme.PurpleGray50
import com.openglsample.details.DessertDetailsViewModel
import com.openglsample.details.DetailScreenState
import sundravels.samples.utilities.extensions.Empty

@Composable
fun DetailScreenRoute(
    meal_id: String = "",
    detailsViewModel: DessertDetailsViewModel = hiltViewModel(),
    backPress: () -> Unit = {}
) {

    detailsViewModel.getDessertDetails(meal_id)
    val detailScreenState by detailsViewModel.arrDessertLookUpDetails.collectAsStateWithLifecycle()
    val isFavourite by detailsViewModel.isFavourite.collectAsStateWithLifecycle()
    DetailScreen(detailScreenState, detailsViewModel::addToFavourites, isFavourite.favouriteImagesIds.contains(meal_id),backPress)
}

@Composable
internal fun DetailScreen(
    detailScreenState: DetailScreenState,
    addToFavourites: (String, Boolean) -> Unit,
    isFavourite:Boolean,
    backPress: () -> Unit
) {
    when (detailScreenState) {
        is DetailScreenState.DessertLookUpDetails -> {
            IngredientsDetailContent(
                detailScreenState.data,
                addToFavourites,
                isFavourite,
                backPress
            )
        }
        else -> {}
    }
}


@Composable
fun IngredientsDetailContent(
    data: List<DessertLookUp>,
    addToFavourites: (String, Boolean) -> Unit,
    isFavourite:Boolean,
    backPress: () -> Unit
) {

    val stateList = rememberLazyListState()


    val scrollOffsetPx = with(LocalDensity.current) {
        (Dimensions.tobAppbar.toPx() / 2) + ((Dimensions.tobAppbar.toPx() / 2) / 2)
    }
    val scrollOffset = remember { mutableStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // try to consume before LazyColumn to collapse toolbar if needed, hence pre-scroll
                val delta = available.y
                val offset = scrollOffset.value + delta
                if (stateList.firstVisibleItemIndex <= 0) {
                    scrollOffset.value = offset.coerceIn(-scrollOffsetPx, 0f)
                }
                return Offset.Zero
            }
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            // attach as a parent to the nested scroll system
            .nestedScroll(nestedScrollConnection)
    ) {

        //toolbar
        AbpTopBar(dessertLookUp = data[0], backPress = backPress, addToFavourites = addToFavourites, isFavourite = isFavourite)

        RecipeToolbarTitle(data[0].strMeal ?: String.Empty(), scrollOffset.value)

        LazyColumn(
            state = stateList,
            modifier = Modifier
                .padding(top = Dimensions.tobAppbar)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            item {
                data[0].let {
                    RecipeDetails(
                        it.strMeal ?: String.Empty(),
                        "${it.strArea}"
                    )
                }
            }
            item { DetailImage(image = data[0].strMealThumb ?: String.Empty()) }

            item {

                Row(
                    modifier = Modifier.padding(
                        PaddingValues(
                            start = 10.dp,
                            end = 10.dp,
                            top = 10.dp
                        )
                    ), verticalAlignment = Alignment.CenterVertically
                ) {
                    data[0].strMeal?.let {
                        RecipeTitle(
                            str = stringResource(id = R.string.str_ingredients_for),
                            MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onBackground),
                            Modifier
                                .weight(1f),
                        )

                    }
                    Text(
                        text = stringResource(id = R.string.str_serving),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.Red,
                            fontSize = 16.sp
                        ),
                        textAlign = TextAlign.Justify,
                    )
                }

            }
            item {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.ten_dp)))
            }
            items(data[0].ingredientsList) {
                RecipeIngredients(it)
            }
            item {
                data[0].strInstructions?.let { RecipeInstructions(strInstructions = it) }
            }
        }


    }

}

@Composable
fun RecipeTitle(str: String, style: TextStyle, modifier: Modifier = Modifier) {
    Text(text = str, style = style, modifier = modifier)
}

@Composable
fun RecipeToolbarTitle(toolbarTitle: String, offset: Float) {
    Text(
        text = toolbarTitle,
        maxLines = 1,
        color =MaterialTheme.colorScheme.onBackground,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground),
        modifier = Modifier
            .padding(top = with(LocalDensity.current) {
                Log.v("TAGToolbarTitle", "${offset}:${Dimensions.tobAppbar + offset.toDp()}")
                Dimensions.tobAppbar + offset.toDp()
            }, start = dimensionResource(id = R.dimen.sixty_dp))
            .fillMaxWidth(),
        textAlign = TextAlign.Start
    )
}

@Composable
fun RecipeDetails(recipeName: String, recipeType: String) {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(Dimensions.tobAppbar)
    ) {
        RecipeTitle(
            str = recipeName,
            MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground),
        )
        Row {
            RecipeTitle(
                str = recipeType,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontStyle = FontStyle.Italic
                )
            )
        }

    }
}

@Composable
fun DetailImage(image: String) {
    Box{
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .height(Dimensions.bannerImageHeight)
        )
        Row(modifier = Modifier.background(color = Color.Black.copy(alpha = 0.5f)).align(Alignment.BottomCenter).padding(horizontal = 10.dp, vertical = 10.dp)) {
            Text(
                text = stringResource(R.string.str_click_here_for_more_info),
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                modifier = Modifier.weight(1f)
            )
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null, tint = Color.White)
        }
    }

}

@Composable
fun RecipeIngredients(pair: Pair<String?, String?>) {
    Column(modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)) {
        Row {
            Text(
                text = pair.first ?: String.Empty(),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal, color = MaterialTheme.colorScheme.onBackground)
            )
            Text(
                text = pair.second ?: String.Empty(),
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Divider(
            modifier = Modifier.height(0.5.dp),
            thickness = 0.5.dp,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Composable
fun RecipeInstructions(strInstructions: String) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(PaddingValues(vertical = 15.dp))
    ) {
        RecipeTitle(
            str = stringResource(id = R.string.str_instructions).uppercase(java.util.Locale.getDefault()),
            MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onBackground),
            Modifier.padding(PaddingValues(10.dp))
        )
        Text(
            text = strInstructions,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal, color = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}
