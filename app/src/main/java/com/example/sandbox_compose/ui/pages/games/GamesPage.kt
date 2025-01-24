package com.example.sandbox_compose.ui.pages.games

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sandbox_compose.ui.pages.components.GameListComp
import com.example.sandbox_compose.ui.pages.components.GamePosterSize
import com.example.sandbox_compose.ui.pages.components.HeaderGamesComp
import com.example.sandbox_compose.ui.pages.components.ImageFilterComp
import com.example.sandbox_compose.ui.pages.components.LoadingBarComp
import com.example.sandbox_compose.ui.pages.components.RecommendedComp

@Composable
fun GamesPage(
       modifier: Modifier = Modifier,
       viewModel: GamesViewModel = hiltViewModel()
) {
    val state = viewModel.gameState


    LazyVerticalGrid(
           columns = GridCells.Fixed(2),
           modifier = modifier.fillMaxSize()
    ) {
        //
        // header
        item(
               span = {
                   GridItemSpan(2)
               }
        ) {
            HeaderGamesComp()
        }
        //
        //All Games
        if (state.gamesList.isNotEmpty()) {
            item(
                   span = {
                       GridItemSpan(2)
                   }
            ) {
                GameListComp(
                       title = "All Games",
                       postersImg = state.gamesList.map { it.thumbnail }
                )
            }
        }
        //
        //Upcoming
        if (state.upComingGames.isNotEmpty()) {
            item(
                   span = {
                       GridItemSpan(2)
                   }
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                GameListComp(
                       title = "Upcoming Games",
                       postersImg = state.upComingGames.map { it.thumbnail }
                )
            }
        }
        //
        //ImageFilter
        if (!state.isLoading) {
            item(
                   span = {
                       GridItemSpan(2)
                   }
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                RecommendedComp(
                       selected = state.selectedFilter,
                       onFilterClick = {
                           viewModel.onEvent(GameEvent.ChangeFilter(it))
                       },
                )
            }
            items(state.filterGames) {
                ImageFilterComp(
                       imageUrl = it.thumbnail,
                       posterSize = GamePosterSize.SMALL,
                       onClick = {}
                )
            }
        }
    }

    if (state.isLoading) {
        Box(
               modifier = Modifier.fillMaxSize(),
               contentAlignment = Alignment.Center
        ) {
            LoadingBarComp()
        }

    }
}
