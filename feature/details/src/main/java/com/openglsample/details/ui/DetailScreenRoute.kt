package com.openglsample.details.ui

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.model.data.DessertLookUp
import com.example.uiresources.components.AbpTopBar
import com.example.uiresources.dimen.Dimensions
import com.openglsample.details.DessertDetailsViewModel
import com.openglsample.details.DetailScreenState

@Composable
fun DetailScreenRoute(
    meal_id: String = "",
    detailsViewModel: DessertDetailsViewModel = hiltViewModel()
) {

    detailsViewModel.getDessertDetails(meal_id)
    val detailScreenState by detailsViewModel.arrDessertLookUpDetails.collectAsStateWithLifecycle()
    DetailScreen(detailScreenState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailScreen(detailScreenState: DetailScreenState) {
    when (detailScreenState) {
        is DetailScreenState.DessertLookUpDetails -> {
            NestedScrollConnectionSample(detailScreenState.data[0])
        }
        else -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NestedScrollConnectionSample(data: DessertLookUp) {
    // here we use LazyColumn that has build-in nested scroll, but we want to act like a
    // parent for this LazyColumn and participate in its nested scroll.
    // Let's make a collapsing toolbar for LazyColumn

    val toolbarHeightPx = with(LocalDensity.current) {
        Dimensions.detailImageHeight.roundToPx().toFloat()
    }

    // our offset to collapse toolbar
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
    // now, let's create connection to the nested scroll system and listen to the scroll
    // happening inside child LazyColumn
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // try to consume before LazyColumn to collapse toolbar if needed, hence pre-scroll
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.value + delta
                toolbarOffsetHeightPx.value = newOffset.coerceIn(-toolbarHeightPx, 0f)

                // here's the catch: let's pretend we consumed 0 in any case, since we want
                // LazyColumn to scroll anyway for good UX
                // We're basically watching scroll without taking it
                return Offset.Zero
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                return super.onPostScroll(consumed, available, source)
            }
        }
    }
    val scrollState = rememberScrollState()
    Box(
        Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {

        Log.v("TAGToolbarOffset","${toolbarOffsetHeightPx.value}:${toolbarHeightPx}")
        Log.v("TAG","${0.8 * toolbarHeightPx}")
        IngredientsDetailContent(data, toolbarOffsetHeightPx.value, scrollState)
        if(toolbarOffsetHeightPx.value < -(0.8 * toolbarHeightPx)){
            TopBar(title = data.strMeal ?: "", modifier = Modifier.align(Alignment.TopCenter))
        }
    }
}

@Composable
fun IngredientsDetailContent(
    data: DessertLookUp,
    offset: Float,
    scrollState: ScrollState,
    visibility: Boolean = true
) {
    Column(modifier = Modifier.verticalScroll(scrollState)) {

        Header(data, offset)
        repeat(data.ingredientsList.size) {
            Row(modifier = Modifier
                .padding(start = 10.dp, top = 10.dp, end = 10.dp)
                .graphicsLayer {
                    translationY = offset
                }) {
                Text(
                    text = data.ingredientsList[it].first ?: "",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                Text(
                    text = data.ingredientsList[it].second ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

}

@Composable
fun Header(
    data: DessertLookUp,
    offset: Float
) {

    Box {
        AsyncImage(
            model = data.strMealThumb,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimensions.bannerImageHeight)
                .graphicsLayer {
                    translationY = offset
                }
        )
        IngredientsTab(modifier = Modifier.align(Alignment.BottomCenter), offset = offset)
    }

}

@Composable
fun Content(data: DessertLookUp, scrollState: ScrollState) {

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Spacer(modifier = Modifier.height(Dimensions.detailImageHeight + 10.dp))
        repeat(data.ingredientsList.size) {
            Row(modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)) {
                Text(
                    text = data.ingredientsList[it].first ?: "",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                Text(
                    text = data.ingredientsList[it].second ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

}

@Composable
fun TopBar(title: String, modifier: Modifier) {
    AbpTopBar(toolbarTitle = title, modifier = modifier)
}


@Composable
fun IngredientsTab(tabClick: (Int) -> Unit = {}, offset: Float, modifier: Modifier) {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Ingredients", "Instructions")
    TabRow(selectedTabIndex = state,
        modifier = modifier.graphicsLayer {
            translationY = offset
        },
        containerColor = Color.Black.copy(alpha = 0.5f),
        divider = {},
        indicator = @Composable {
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(it[state]),
                color = Color.White
            )
        }) {
        titles.forEachIndexed { index, s ->
            Tab(selected = state == index,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.5f),
                onClick = {
                    state = index
                    tabClick(state)
                },

                text = {
                    Text(
                        text = s,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )
        }
    }
}
