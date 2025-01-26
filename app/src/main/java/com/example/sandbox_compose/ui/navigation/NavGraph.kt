package com.example.sandbox_compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
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
               startDestination = Routes.HomePages.routes,
        ) {
            composable(route = Routes.HomePages.routes) {
            }
            composable(route = Routes.DetailsPages.routes) {
            }
            composable(route = Routes.SearchPages.routes) {
            }
            composable(route = Routes.BookmarkPages.routes) {
            }
        }
    }

}
