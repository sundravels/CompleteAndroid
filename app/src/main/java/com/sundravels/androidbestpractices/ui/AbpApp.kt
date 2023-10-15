package com.sundravels.androidbestpractices.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.favourite.navigation.favourite_screen_route
import com.example.favourite.ui.FavouriteScreenRoute
import com.example.home.HomeScreenRoute
import com.example.home.navigation.homeScreenRoute
import com.example.uiresources.components.AbpBottomNavigationBar
import com.example.uiresources.components.AbpBottomNavigationBarItem
import com.example.uiresources.icon.Icon
import com.example.uiresources.theme.Purple200
import com.openglsample.details.navigation.detailScreenMealId
import com.openglsample.details.navigation.detailScreenRoute
import com.openglsample.details.ui.DetailScreenRoute
import com.sundravels.androidbestpractices.navigation.TopLevelNavigation
import kotlin.math.roundToInt


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
                Log.v("newOffset", "${newOffset}:${delta}")
                toolBarOffsetHeightPx = newOffset.coerceIn(-topAppBarHeight, 0f)
                Log.v("TAGtoolBarOffsetHeightPx","${toolBarOffsetHeightPx}")

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
        backgroundColor = MaterialTheme.colorScheme.background,
        topBar = {
            //to manage empty space of topbar, when scrolled
            if(toolBarOffsetHeightPx in -10f..0f){
                AbpTopBar(abpAppState, toolBarOffsetHeightPx)
            }
        },
        bottomBar = {
            AbpNavigation(abpAppState::navigate, abpAppState.currentDestination)
        }, modifier = Modifier.nestedScroll(nestedScrollConnection)
    ) {
        NavHost(
            navController = abpAppState.navController,
            startDestination = homeScreenRoute
        ) {
            composable(homeScreenRoute) {
                HomeScreenRoute(navHostController = abpAppState.navController)
            }
            composable(favourite_screen_route) {
                FavouriteScreenRoute(navHostController = abpAppState.navController)
            }
            composable(
                "${detailScreenRoute}/{${detailScreenMealId}}",
                arguments = listOf(navArgument(detailScreenMealId) { type = NavType.StringType })
            ) {
                DetailScreenRoute("${it.arguments?.getString(detailScreenMealId)}")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbpTopBar(
    abpAppState: AbpAppState,
    toolBarOffsetHeightPx: Float
) {
    val currentDestination = abpAppState.destination
    if (currentDestination != null) {
        TopAppBar(
            title = {
                Text(
                    stringResource(id = currentDestination.title), maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                )
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painterResource(id = com.example.uiresources.R.drawable.baseline_settings_24),
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier.offset {
                IntOffset(x = 0, y = toolBarOffsetHeightPx.roundToInt())
            },
            navigationIcon = {

            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Purple200.copy(alpha = 0.1f)
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbpNavigation(navigate: (TopLevelNavigation) -> Unit, currentDestination: NavDestination?) {

    AbpBottomNavigationBar(
        modifier = Modifier.height(60.dp),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.outline
    ) {

        topLevelNavigationList.forEach { bottomBarDestination ->

            AbpBottomNavigationBarItem(
                icon = {
                    when (bottomBarDestination.icon) {
                        is Icon.DrawableResource -> Icon(
                            painterResource(id = bottomBarDestination.icon.id),
                            contentDescription = null,
                            tint = if (currentDestination.isTopLevelDestinationInHierarchy(
                                    bottomBarDestination
                                )
                            ) Purple200 else Color.Black
                        )
                        is Icon.ImageVectors -> Icon(
                            painterResource(id = 0),
                            contentDescription = null,
                            tint = if (currentDestination.isTopLevelDestinationInHierarchy(
                                    bottomBarDestination
                                )
                            ) Purple200 else Color.Black
                        )
                    }
                },
                label = { },
                selected = currentDestination.isTopLevelDestinationInHierarchy(bottomBarDestination),
                onClick = {
                    navigate(bottomBarDestination)
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White,
                    selectedIconColor = Purple200,
                    unselectedIconColor = Color.Unspecified
                )
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelNavigation) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false


