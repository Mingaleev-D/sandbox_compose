package com.example.sandbox_compose.ui.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.SubcomposeAsyncImage
import com.example.sandbox_compose.ui.components.CharacterDetailsNamePlateComponent
import com.example.sandbox_compose.ui.components.DataPointComponent
import com.example.sandbox_compose.ui.components.SimpleToolbar
import com.example.sandbox_compose.ui.theme.RickAction

@Composable
fun CharacterDetailsScreen(
       characterDetailsViewModel: CharacterDetailsViewModel = hiltViewModel(),
       characterId: Int,
       onEpisodeClick: (Int) -> Unit,
       onBackClicked: () -> Unit
) {
    val state by characterDetailsViewModel.internalStorageFlow.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        characterDetailsViewModel.fetchCharacterDetails(characterId)
    })


    Column {
        SimpleToolbar(title = "Character details", onBackAction = onBackClicked)
        LazyColumn(
               modifier = Modifier.fillMaxSize(),
               contentPadding = PaddingValues(all = 16.dp)
        ) {
            when (val viewState = state) {
                is CharacterDetailsState.Error -> {
                }

                CharacterDetailsState.Loading -> item {
                    LoadingState()
                }

                is CharacterDetailsState.Success -> {
                    item {
                        CharacterDetailsNamePlateComponent(
                               name = viewState.characterDto.name,
                               status = viewState.characterDto.status
                        )
                    }
                    item { Spacer(modifier = Modifier.height(8.dp)) }
                    // Image
                    item {
                        SubcomposeAsyncImage(
                               model = viewState.characterDto.imageUrl,
                               contentDescription = "Character image",
                               contentScale = ContentScale.Fit,
                               modifier = Modifier
                                      .fillMaxWidth()
                                      .aspectRatio(1f)
                                      .clip(RoundedCornerShape(12.dp)),
                               loading = { LoadingState() }
                        )
                    }
                    // Data points
                    items(viewState.characterDataPoints) {
                        Spacer(modifier = Modifier.height(32.dp))
                        DataPointComponent(dataPoint = it)
                    }
                    item { Spacer(modifier = Modifier.height(32.dp)) }
                    // Button
                    item {
                        Text(
                               text = "View all episodes",
                               color = RickAction,
                               fontSize = 18.sp,
                               textAlign = TextAlign.Center,
                               modifier = Modifier
                                      .padding(horizontal = 32.dp)
                                      .border(
                                             width = 1.dp,
                                             color = RickAction,
                                             shape = RoundedCornerShape(12.dp)
                                      )
                                      .clip(RoundedCornerShape(12.dp))
                                      .clickable {
                                          onEpisodeClick(viewState.characterDto.id)
                                      }
                                      .padding(vertical = 8.dp)
                                      .fillMaxWidth()
                        )
                    }
                    item { Spacer(modifier = Modifier.height(64.dp)) }
                }
            }
        }

    }

}

@Composable fun LoadingState() {
    CircularProgressIndicator(
           modifier = Modifier
                  .fillMaxSize()
                  .padding(all = 128.dp),
           color = RickAction
    )
}
