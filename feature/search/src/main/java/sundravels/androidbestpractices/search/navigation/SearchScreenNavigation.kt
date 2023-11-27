package sundravels.androidbestpractices.search.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions


const val search_screen_route = "search_screen_route"



fun NavHostController.navigateToSearchScreen(navOptions: NavOptions?=null){
    this.navigate(search_screen_route)
}

