package com.example.sandbox_compose.ui.navigation

sealed class Routes(
       val routes:String
) {

    object OnBoardingPages : Routes("onboarding")
    object HomePages : Routes("home")
    object SearchPages : Routes("search")
    object BookmarkPages : Routes("bookmark")
    object DetailsPages : Routes("details")
    object AppStartNavigation: Routes("app_start")
    object NewsNavigation: Routes("newsNav")
    object NewsNavigationPage: Routes("newsNavPages") //NewsNavigationPage
}
