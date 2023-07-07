package com.sundravels.androidbestpractices.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.favourite.navigation.favourite_screen_route
import com.example.favourite.ui.FavouriteScreenRoute
import com.example.home.HomeScreenRoute
import com.example.home.navigation.homeScreenRoute
import com.example.uiresources.components.AbpBottomNavigationBar
import com.example.uiresources.components.AbpBottomNavigationBarItem
import com.example.uiresources.icon.Icon
import com.example.uiresources.theme.Purple200
import com.sundravels.androidbestpractices.navigation.TopLevelNavigation


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AbpApp(abpAppState: AbpAppState) {
    Scaffold(
        topBar = {
            AbpTopBar(abpAppState)
        },
        bottomBar = {
            AbpNavigation(abpAppState::navigate,abpAppState.currentDestination)
        }) {
        NavHost(
            navController = abpAppState.navController,
            startDestination = homeScreenRoute,
            modifier = Modifier.padding(PaddingValues(horizontal = 10.dp))
        ) {
            composable(homeScreenRoute) {
                HomeScreenRoute()
            }
            composable(favourite_screen_route) {
                FavouriteScreenRoute()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbpTopBar(abpAppState: AbpAppState){
    val currentDestination = abpAppState.destination
    if(currentDestination!=null){
        CenterAlignedTopAppBar(
            modifier = Modifier,
            title = {
                Text(stringResource(id =currentDestination.title?:0), maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.Black,
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
            navigationIcon = {

            }

        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbpNavigation(navigate: (TopLevelNavigation) -> Unit, currentDestination: NavDestination?) {

    AbpBottomNavigationBar(modifier = Modifier.height(60.dp), containerColor = Color.White, contentColor = Color.White) {

        topLevelNavigationList.forEach { bottomBarDestination ->

            AbpBottomNavigationBarItem(icon = {
                when (bottomBarDestination.icon) {
                    is Icon.DrawableResource -> Icon(
                        painterResource(id = bottomBarDestination.icon.id),
                        contentDescription = null,
                        tint = if(currentDestination.isTopLevelDestinationInHierarchy(bottomBarDestination)) Purple200 else Color.Black
                    )
                    is Icon.ImageVectors -> Icon(
                        painterResource(id = 0),
                        contentDescription = null,
                        tint = if(currentDestination.isTopLevelDestinationInHierarchy(bottomBarDestination)) Purple200 else Color.Black
                    )
                }
            },
                label = {  },
                selected = currentDestination.isTopLevelDestinationInHierarchy(bottomBarDestination),
                onClick = {
                    navigate(bottomBarDestination)
                }, colors = NavigationBarItemDefaults.colors(indicatorColor = Color.White, selectedIconColor = Purple200, unselectedIconColor = Color.Unspecified)
            )
        }
    }
}
private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelNavigation) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false


