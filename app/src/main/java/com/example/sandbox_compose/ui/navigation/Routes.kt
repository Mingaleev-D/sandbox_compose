package com.example.sandbox_compose.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object HomePage : Routes()
    @Serializable
    data object SearchPage: Routes()
    @Serializable
    data object FavoritesPage : Routes()
    @Serializable
    data class FullImagePage(val imageId: String) : Routes()
    @Serializable
    data class ProfilePage(val profileLink: String) : Routes()
}
