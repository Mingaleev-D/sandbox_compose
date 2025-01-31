package com.example.sandbox_compose.ui.navigation

import com.example.sandbox_compose.utils.Constants

sealed class Routes {
    data class HomePage(val route: String = "home") : Routes()
    data class HeadlinesPage(val route: String = "headline") : Routes()
    data class DiscoverPage(val route: String = "discover") : Routes()
    data class DetailsPage(
           val route: String = "home",
           val id: String = Constants.articleId,
           val screen:String = Constants.screenType,
           val routeWithArgs:String = "$route/{${Constants.articleId}}&{${Constants.screenType}}"
    ) : Routes()
    data class SearchPage(val route: String = "search") : Routes()
    data class FavoritesPage(val route: String = "favorites") : Routes()
    data class SettingsPage(val route: String = "settings") : Routes()
}
