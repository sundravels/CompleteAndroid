package sundravels.androidbestpractices.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.model.data.DessertImages
import com.example.uiresources.components.DessertsList
import com.example.uiresources.components.HomeScreenClickHandler
import com.example.uiresources.icon.AppIcons
import com.example.uiresources.theme.Purple500
import com.openglsample.details.navigation.navigateToDetailScreen
import sundravels.androidbestpractices.search.SearchScreenViewModel

@Composable
fun SearchScreenRoute(
    navHostController: NavHostController,
    searchScreenViewModel: SearchScreenViewModel = hiltViewModel()
) {

    val searchText by searchScreenViewModel.searchText.collectAsStateWithLifecycle()
    val dessertList by searchScreenViewModel.dessertList.collectAsStateWithLifecycle()
    SearchScreen(
        searchText,
        dessertList,
        searchRecipes = searchScreenViewModel::updateSearchInput,
        favourite = searchScreenViewModel::addToFavourites,
        detail = {
            navHostController.navigateToDetailScreen(meal_id = (it as HomeScreenClickHandler.DETAIl_SCREEN).id)
        }
    ) {
        navHostController.popBackStack()
    }

}

@Composable
fun SearchScreen(
    searchText: String,
    dessertList: List<DessertImages>,
    searchRecipes: (input: String) -> Unit,
    favourite: (String, Boolean) -> Unit,
    detail: (HomeScreenClickHandler) -> Unit,
    backPress: () -> Unit
) {
    SearchScreenContent(
        searchText,
        dessertList,
        searchRecipes,
        favourite,
        detail,
        backPress
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenContent(
    searchText: String,
    dessertList: List<DessertImages>,
    searchRecipes: (input: String) -> Unit,
    favourite: (String, Boolean) -> Unit,
    detail: (HomeScreenClickHandler) -> Unit,
    backPress: () -> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 40.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(AppIcons.ic_navigate_before),
            contentDescription = null,
            modifier = Modifier
                .drawBehind {
                    drawCircle(color = Purple500, radius = 0.8f * size.height)
                }
                .clickable {
                    backPress()
                })

        Spacer(modifier = Modifier.height(30.dp))


        TextField(
            value = searchText,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    "Search Recipes",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.outline,
                        fontWeight = FontWeight.Normal
                    )
                )
            },
            onValueChange = searchRecipes,
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null, tint = Purple500)

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colorScheme.outline,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                textColor = MaterialTheme.colorScheme.onBackground
            )
        )
    }



    SearchScreenList(
        dessertList,
        favourite,
        detail
    )


}

@Composable
fun SearchScreenList(
    list: List<DessertImages>,
    favourite: (String, Boolean) -> Unit,
    detail: (HomeScreenClickHandler) -> Unit
) {

    LazyColumn(modifier = Modifier.padding(top = 180.dp)) {

        items(list) {
            DessertsList(dessertImages = it, favourite = favourite, detailFn = detail)
        }
    }

}
