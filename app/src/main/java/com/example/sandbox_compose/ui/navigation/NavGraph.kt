package com.example.sandbox_compose.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.sandbox_compose.R
import com.example.sandbox_compose.ui.pages.components.Dimens.ExtraSmallPadding2
import com.example.sandbox_compose.ui.pages.components.Dimens.IconSize
import com.example.sandbox_compose.ui.pages.home.HomePage
import com.example.sandbox_compose.ui.pages.home.HomeViewModel
import com.example.sandbox_compose.ui.pages.onboarding.OnboardingPage
import com.example.sandbox_compose.ui.pages.onboarding.OnboardingViewModel

@Composable
fun NavGraphSetup(
       startDestination: String,
) {
    val navController = rememberNavController()

    NavHost(
           navController = navController,
           startDestination = startDestination,
    ) {
        navigation(
               route = Routes.AppStartNavigation.routes,
               startDestination = Routes.OnBoardingPages.routes,
        ) {
            composable(route = Routes.OnBoardingPages.routes) {
                val viewModelOnBoard: OnboardingViewModel = hiltViewModel()
                OnboardingPage(
                       onEvent = viewModelOnBoard::onEvent
                )
            }
        }

        navigation(
               route = Routes.NewsNavigation.routes,
               startDestination = Routes.NewsNavigationPage.routes,
        ) {
            composable(route = Routes.NewsNavigationPage.routes) {
                NewsNavigator()
            }
        }
    }

}

@Composable
fun NewsNavigator() {
    val bottomNavigationItems = remember {
        listOf(
               BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
               BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
               BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Routes.HomePages.routes -> 0
        Routes.SearchPages.routes -> 1
        Routes.BookmarkPages.routes -> 2
        else -> 0
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        NewsBottomNavigation(
               items = bottomNavigationItems,
               selectedItem = selectedItem,
               onItemClick = { index ->
                   when (index) {
                       0 -> navigateToTab(
                              navController = navController,
                              route = Routes.HomePages.routes
                       )

                       1 -> navigateToTab(
                              navController = navController,
                              route = Routes.SearchPages.routes
                       )

                       2 -> navigateToTab(
                              navController = navController,
                              route = Routes.BookmarkPages.routes
                       )
                   }
               }
        )
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
               navController = navController,
               startDestination = Routes.HomePages.routes,
               modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Routes.HomePages.routes) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomePage(
                       articles = articles,
                       navigate = { navigateToTab(navController = navController, route = it) })
            }
            composable(route = Routes.SearchPages.routes) {
            }
            composable(route = Routes.DetailsPages.routes) {
            }
            composable(route = Routes.BookmarkPages.routes) {
            }
        }
    }
}

private fun navigateToTab(
       navController: NavController,
       route: String
) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun NewsBottomNavigation(
       items: List<BottomNavigationItem>,
       selectedItem: Int,
       onItemClick: (Int) -> Unit
) {
    NavigationBar(
           modifier = Modifier.fillMaxWidth(),
           containerColor = MaterialTheme.colorScheme.background,
           tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                   selected = index == selectedItem,
                   onClick = { onItemClick(index) },
                   icon = {
                       Column(horizontalAlignment = CenterHorizontally) {
                           androidx.compose.material3.Icon(
                                  painter = painterResource(id = item.icon),
                                  contentDescription = null,
                                  modifier = Modifier.size(IconSize),
                           )
                           Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                           Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                       }
                   },
                   colors = NavigationBarItemDefaults.colors(
                          selectedIconColor = MaterialTheme.colorScheme.primary,
                          selectedTextColor = MaterialTheme.colorScheme.primary,
                          unselectedIconColor = colorResource(id = R.color.body),
                          unselectedTextColor = colorResource(id = R.color.body),
                          indicatorColor = MaterialTheme.colorScheme.background
                   ),
            )
        }
    }
}

data class BottomNavigationItem(
       @DrawableRes val icon: Int,
       val text: String
)
//@Preview
//@Preview(uiMode = UI_MODE_NIGHT_YES)
//@Composable
//fun NewsBottomNavigationPreview() {
//    NewsAppTheme(dynamicColor = false) {
//        NewsBottomNavigation(items = listOf(
//               BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
//               BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
//               BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
//        ), selectedItem = 0, onItemClick = {})
//    }
//}
