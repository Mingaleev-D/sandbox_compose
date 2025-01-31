package com.example.sandbox_compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sandbox_compose.ui.pages.detail.DetailPage
import com.example.sandbox_compose.ui.pages.headline.HeadlinePage
import com.example.sandbox_compose.ui.pages.home.HomePage
import com.example.sandbox_compose.ui.pages.search.SearchPage
import com.example.sandbox_compose.utils.Constants

@Composable
fun NavGraphSetup(
       navController: NavHostController = rememberNavController(),
       navActions: NavigationActions,
       openDrawer: () -> Unit,
) {
    NavHost(
           navController = navController,
           startDestination = Routes.HomePage().route
    ) {
        composable(
               route = Routes.HomePage().route
        ) {
            HomePage(
                   onViewMoreClicked = navActions.navigateToHeadlinePage,
                   onHeadlineItemClick = {
                       navActions.navigateToDetailsPage(it, Routes.HeadlinesPage().route)
                   },
                   openDrawer = openDrawer,
                   onSearchClicked = navActions.navigateToSearchPage,
                   onDiscoverItemClick = {
                       navActions.navigateToDetailsPage(it, Routes.DiscoverPage().route)
                   }
            )
        }
        composable(route = Routes.HeadlinesPage().route) {
            HeadlinePage(
                   onItemClick = {
                       navActions.navigateToDetailsPage(
                              it,
                              Routes.HeadlinesPage().route
                       )
                   }
            )
        }

        composable(
               route = Routes.DetailsPage().routeWithArgs,
               arguments = listOf(
                      navArgument(name = Constants.articleId) {
                          type = NavType.IntType
                      },
                      navArgument(name = Constants.screenType) {
                          type = NavType.StringType
                      }
               )
        ) {
            DetailPage(onBack = {
                navController.navigateUp()
            })
        }

        composable(
               route = Routes.SearchPage().route
        ) {
            SearchPage(
                   onSearchItemClick = {
                       navActions.navigateToDetailsPage(
                              it.id,
                              Routes.SearchPage().route
                       )
                   },
                   navigateUp = {
                       navController.navigateUp()
                   }
            )
        }
    }
}

class NavigationActions(
       navController: NavController,
) {

    val navigateToHomePage: () -> Unit = {
        navController.navigateToSingleTop(
               Routes.HomePage().route
        )
    }
    val navigateToDetailsPage: (id: Int, screenType: String) -> Unit = { id, screenType ->
        navController.navigateToSingleTop(
               route = "${Routes.DetailsPage().route}/$id&$screenType"
        )
    }
    val navigateToHeadlinePage: () -> Unit = {
        navController.navigateToSingleTop(
               Routes.HeadlinesPage().route
        )
    }
    val navigateToFavoritePage: () -> Unit = {
        navController.navigateToSingleTop(
               Routes.FavoritesPage().route
        )
    }
    val navigateToSettingsPage: () -> Unit = {
        navController.navigateToSingleTop(
               Routes.SettingsPage().route
        )
    }
    val navigateToSearchPage: () -> Unit = {
        navController.navigateToSingleTop(
               Routes.SearchPage().route
        )
    }

}

fun NavController.navigateToSingleTop(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
