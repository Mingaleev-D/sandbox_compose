package com.example.sandbox_compose.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.sandbox_compose.ui.pages.favorites.FavoritesPage
import com.example.sandbox_compose.ui.pages.full_image.FullImagePage
import com.example.sandbox_compose.ui.pages.full_image.FullImageViewModel
import com.example.sandbox_compose.ui.pages.home.HomePage
import com.example.sandbox_compose.ui.pages.home.HomeViewModel
import com.example.sandbox_compose.ui.pages.profile.ProfilePage
import com.example.sandbox_compose.ui.pages.search.SearchPage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraphSetup(
       navController: NavHostController,
       scrollBehavior: TopAppBarScrollBehavior,
       snackbarHostState: SnackbarHostState
) {
    val viewModel = hiltViewModel<HomeViewModel>()

    NavHost(
           navController = navController,
           startDestination = Routes.HomePage
    ) {
        composable<Routes.HomePage> {
            HomePage(
                   snackbarHostState = snackbarHostState,
                   snackbarEvent = viewModel.snackbarEvent,
                   scrollBehavior = scrollBehavior,
                   images = viewModel.images,
                   onImageClick = { imageId ->
                       navController.navigate(Routes.FullImagePage(imageId))
                   },
                   onSearchClick = { navController.navigate(Routes.SearchPage) },
                   onFABClick = { navController.navigate(Routes.FavoritesPage) }
            )
        }
        composable<Routes.SearchPage> {
            SearchPage(
                   onBackClick = { navController.navigateUp() }
            )
        }
        composable<Routes.FavoritesPage> {
            FavoritesPage(
                   onBackClick = { navController.navigateUp() }
            )
        }
        composable<Routes.FullImagePage> {
            //            backStackEntry ->
            //            val imageId = backStackEntry.toRoute<Routes.FullImagePage>().imageId
            val fullImageViewModel = hiltViewModel<FullImageViewModel>()
            FullImagePage(
                   snackbarHostState = snackbarHostState,
                   snackbarEvent = fullImageViewModel.snackbarEvent,
                   image = fullImageViewModel.image,
                   onBackClick = { navController.navigateUp() },
                   onPhotographerNameClick = { profileLinks ->
                       navController.navigate(Routes.ProfilePage(profileLinks))
                   },
                   onImageDownloadClick = { imageId, imageUrl ->
                       fullImageViewModel.downloadImage(imageId, imageUrl)
                   }
            )
        }
        composable<Routes.ProfilePage> { backStackEntry ->
            val profileLink = backStackEntry.toRoute<Routes.ProfilePage>().profileLink
            ProfilePage(
                   profileLink = profileLink,
                   onBackClick = { navController.navigateUp() }
            )
        }
    }
}
