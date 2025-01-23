package com.example.sandbox_compose.ui.pages.games

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sandbox_compose.ui.pages.components.GameListComp
import com.example.sandbox_compose.ui.pages.components.HeaderGamesComp
import com.example.sandbox_compose.ui.pages.components.LoadingBarComp

@Composable
fun GamesPage(
       modifier: Modifier = Modifier,
       gameViewModel: GamesViewModel = hiltViewModel()
) {
    val state = gameViewModel.gameState

    LazyColumn(
           modifier = modifier.fillMaxSize()
    ) {
        item {
            HeaderGamesComp()
        }

        if (state.gamesList.isNotEmpty()) {
            item {
                GameListComp(
                       title = "All Games",
                       postersImg = state.gamesList.map { it.thumbnail }
                )
            }
        }

        if (state.upComingGames.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
                GameListComp(
                       title = "Upcoming Games",
                       postersImg = state.upComingGames.map { it.thumbnail }
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
