package com.example.sandbox_compose.ui.pages.games

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sandbox_compose.ui.pages.components.GameListComp

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
            Row(
                 horizontalArrangement = Arrangement.Center,
                 modifier = Modifier.fillMaxWidth().padding(6.dp)
            ) {
                Text(
                       text = "FREETOGAMES",
                       style = TextStyle(
                              fontFamily = FontFamily.Cursive,
                              fontWeight = FontWeight.Bold,
                              color = Color.White,
                              fontSize = 28.sp
                       ),
                )
            }
        }
        if (state.gamesList.isNotEmpty()) {
            item {
                GameListComp(
                       title = "All Games",
                       postersImg = state.gamesList.map { it.thumbnail }
                )
            }
        }
    }

    if (state.isLoading) {
        Box(
               modifier = Modifier.fillMaxSize(),
               contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

    }
}
