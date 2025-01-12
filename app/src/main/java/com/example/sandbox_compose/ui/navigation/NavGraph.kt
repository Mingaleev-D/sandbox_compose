package com.example.sandbox_compose.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.sandbox_compose.ui.pages.favorites.FavoritesPage
import com.example.sandbox_compose.ui.pages.favorites.FavoritesViewModel
import com.example.sandbox_compose.ui.pages.full_image.FullImagePage
import com.example.sandbox_compose.ui.pages.full_image.FullImageViewModel
import com.example.sandbox_compose.ui.pages.home.HomePage
import com.example.sandbox_compose.ui.pages.home.HomeViewModel
import com.example.sandbox_compose.ui.pages.profile.ProfilePage
import com.example.sandbox_compose.ui.pages.search.SearchPage
import com.example.sandbox_compose.ui.pages.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraphSetup(
       navController: NavHostController,
       scrollBehavior: TopAppBarScrollBehavior,
       snackbarHostState: SnackbarHostState,
       searchQuery: String,
       onSearchQueryChange: (String) -> Unit,
) {

    NavHost(
           navController = navController,
           startDestination = Routes.HomePage
    ) {
        composable<Routes.HomePage> {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val images = homeViewModel.images.collectAsLazyPagingItems()
            val favoriteImageIds by homeViewModel.favoriteImageIds.collectAsStateWithLifecycle()
            HomePage (
                   snackbarHostState = snackbarHostState,
                   snackbarEvent = homeViewModel.snackbarEvent,
                   scrollBehavior = scrollBehavior,
                   images = images,
                   favoriteImageIds = favoriteImageIds,
                   onImageClick = { imageId ->
                       navController.navigate(Routes.FullImagePage(imageId))
                   },
                   onSearchClick = { navController.navigate(Routes.SearchPage) },
                   onFABClick = { navController.navigate(Routes.FavoritesPage) },
                   onToggleFavoriteStatus = { homeViewModel.toggleFavoriteStatus(it) }
            )
        }
        composable<Routes.SearchPage> {
            val searchViewModel: SearchViewModel = hiltViewModel()
            val searchedImages = searchViewModel.searchImages.collectAsLazyPagingItems()
            val favoriteImageIds by searchViewModel.favoriteImageIds.collectAsStateWithLifecycle()
            SearchPage(
                   snackbarHostState = snackbarHostState,
                   snackbarEvent = searchViewModel.snackbarEvent,
                   searchedImages = searchedImages,
                   favoriteImageIds = favoriteImageIds,
                   searchQuery = searchQuery,
                   onSearchQueryChange = onSearchQueryChange,
                   onBackClick = { navController.navigateUp() },
                   onImageClick = { imageId ->
                       navController.navigate(Routes.FullImagePage(imageId))
                   },
                   onSearch = { searchViewModel.searchImages(it) },
                   onToggleFavoriteStatus = { searchViewModel.toggleFavoriteStatus(it) }
            )
        }
        composable<Routes.FavoritesPage> {
            val favoritesViewModel: FavoritesViewModel = hiltViewModel()
            val favoriteImages = favoritesViewModel.favoriteImages.collectAsLazyPagingItems()
            val favoriteImageIds by favoritesViewModel.favoriteImageIds.collectAsStateWithLifecycle()
            FavoritesPage(
                   snackbarHostState = snackbarHostState,
                   favoriteImages = favoriteImages,
                   snackbarEvent = favoritesViewModel.snackbarEvent,
                   scrollBehavior = scrollBehavior,
                   onSearchClick = { navController.navigate(Routes.SearchPage) },
                   favoriteImageIds = favoriteImageIds,
                   onBackClick = { navController.navigateUp() },
                   onImageClick = { imageId ->
                       navController.navigate(Routes.FullImagePage(imageId))
                   },
                   onToggleFavoriteStatus = { favoritesViewModel.toggleFavoriteStatus(it) }
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
