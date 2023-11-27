package com.sundravels.androidbestpractices.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.favourite.navigation.favourite_screen_route
import com.example.favourite.ui.FavouriteScreenRoute
import com.example.home.navigation.homeScreenRoute
import com.example.home.navigation.seeAllScreenRoute
import com.example.home.navigation.seeAllType
import com.example.home.ui.HomeScreenRoute
import com.example.home.ui.SeeAllScreenRoute
import com.example.uiresources.components.AbpBottomNavigationBar
import com.example.uiresources.components.AbpBottomNavigationBarItem
import com.example.uiresources.icon.Icon
import com.example.uiresources.theme.*
import com.openglsample.details.navigation.detailScreenMealId
import com.openglsample.details.navigation.detailScreenRoute
import com.openglsample.details.ui.DetailScreenRoute
import com.sundravels.androidbestpractices.navigation.TopLevelNavigation
import sundravels.androidbestpractices.search.navigation.search_screen_route
import sundravels.androidbestpractices.search.ui.SearchScreenRoute


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AbpApp(abpAppState: AbpAppState) {


    val topAppBarHeight = with(LocalDensity.current) {
        64.dp.roundToPx().toFloat()
    }
    var toolBarOffsetHeightPx by remember {
        mutableStateOf(0f)
    }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // try to consume before LazyColumn to collapse toolbar if needed, hence pre-scroll
                val delta = available.y

                val newOffset = toolBarOffsetHeightPx + delta
                toolBarOffsetHeightPx = newOffset.coerceIn(-topAppBarHeight, 0f)

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
                Log.v("TAG", "onpost")
                return super.onPostScroll(consumed, available, source)
            }
        }
    }


    Scaffold(
        topBar = {
//            //to manage empty space of topbar, when scrolled
//            if (toolBarOffsetHeightPx in -10f..0f) {
//                AbpTopBar(abpAppState)
//            }

            {}
        },
        bottomBar = {
            AbpNavigation(abpAppState::navigate, abpAppState.currentDestination)
        }, modifier = Modifier
            .nestedScroll(nestedScrollConnection),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = abpAppState.navController,
            startDestination = homeScreenRoute,
            modifier = Modifier.padding(it)
        ) {
            composable(homeScreenRoute) {
                HomeScreenRoute(navHostController = abpAppState.navController)
            }
            composable(search_screen_route){
                SearchScreenRoute(abpAppState.navController)
            }
            composable(favourite_screen_route) {
                FavouriteScreenRoute(navHostController = abpAppState.navController)
            }
            composable("${seeAllScreenRoute}/{${seeAllType}}",
            arguments = listOf(navArgument(seeAllType){
                type = NavType.StringType
            })
            ){
                it.arguments?.getString(seeAllType)?.let { it1 ->
                    SeeAllScreenRoute(abpAppState.navController,seeAllScreenType = it1){
                        abpAppState.navController.popBackStack()
                    }
                }
            }
            composable(
                "${detailScreenRoute}/{${detailScreenMealId}}",
                arguments = listOf(navArgument(detailScreenMealId) { type = NavType.StringType }
                )
            ) {
                DetailScreenRoute(meal_id = "${it.arguments?.getString(detailScreenMealId)}") {
                    abpAppState.navController.popBackStack()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbpTopBar(
    abpAppState: AbpAppState
) {
    val currentDestination = abpAppState.destination
    val context = LocalContext.current
    if (currentDestination != null) {


    }
}

@Composable
fun AbpNavigation(navigate: (TopLevelNavigation) -> Unit, currentDestination: NavDestination?) {

    if (currentDestination.isTopLevelDestinationInHierarchy(TopLevelNavigation.HOME) || currentDestination.isTopLevelDestinationInHierarchy(
            TopLevelNavigation.FAVOURITES
        )
    ) {
        AbpBottomNavigationBar(content = {
            topLevelNavigationList.forEach { bottomBarDestination ->

                AbpBottomNavigationBarItem(
                    icon = {
                        when (bottomBarDestination.icon) {
                            is Icon.DrawableResource -> Icon(
                                painterResource(id = bottomBarDestination.icon.id),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                            is Icon.ImageVectors -> Icon(
                                painterResource(id = 0),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(id = bottomBarDestination.text),
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground)
                        )

                    },
                    selected = currentDestination.isTopLevelDestinationInHierarchy(
                        bottomBarDestination
                    ),
                    onClick = {
                        navigate(bottomBarDestination)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Purple200,
                        selectedIconColor = MaterialTheme.colorScheme.onBackground,
                        selectedTextColor = MaterialTheme.colorScheme.onBackground,
                        unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                        unselectedTextColor = MaterialTheme.colorScheme.onBackground
                    )

                )
            }
        })
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelNavigation) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false


