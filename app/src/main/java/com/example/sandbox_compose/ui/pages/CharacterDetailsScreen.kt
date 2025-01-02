package com.example.sandbox_compose.ui.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.example.sandbox_compose.core.data.mapper.CharacterDto
import com.example.sandbox_compose.core.data.remote.KtorClient
import com.example.sandbox_compose.ui.components.CharacterDetailsNamePlateComponent
import com.example.sandbox_compose.ui.components.DataPoint
import com.example.sandbox_compose.ui.components.DataPointComponent
import com.example.sandbox_compose.ui.theme.RickAction
import kotlinx.coroutines.delay

@Composable
fun CharacterDetailsScreen(
       ktorClient: KtorClient,
       characterId: Int,
       onEpisodeClick: (Int) -> Unit
) {
    var character by remember { mutableStateOf<CharacterDto?>(null) }
    val characterDataPoints: List<DataPoint> by remember {
        derivedStateOf {
            buildList {
                character?.let { character ->
                    add(DataPoint("Last known location", character.location.name))
                    add(DataPoint("Species", character.species))
                    add(DataPoint("Gender", character.gender.displayName))
                    character.type.takeIf { it.isNotEmpty() }?.let { type ->
                        add(DataPoint("Type", type))
                    }
                    add(DataPoint("Origin", character.origin.name))
                    add(DataPoint("Episode count", character.episodeUrls.size.toString()))
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        delay(500)
        ktorClient
               .getCharacters(characterId)
               .onSuccess {
                   character = it
               }.onFailure { exception ->
                   // todo handle exception
               }
    })

    LazyColumn(
           modifier = Modifier.fillMaxSize(),
           contentPadding = PaddingValues(all = 16.dp)
    ) {
        if (character == null) {
            item { LoadingState() }
            return@LazyColumn
        }
        // Name plate
        item {
            CharacterDetailsNamePlateComponent(
                   name = character!!.name,
                   status = character!!.status
            )
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }
        // Image
        item {
            SubcomposeAsyncImage(
                   model = character!!.imageUrl,
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
        items(characterDataPoints) {
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
                              onEpisodeClick(character!!.id)
                          }
                          .padding(vertical = 8.dp)
                          .fillMaxWidth()
            )
        }

        item { Spacer(modifier = Modifier.height(64.dp)) }
    }
}

@Composable
private fun LoadingState() {
    CircularProgressIndicator(
           modifier = Modifier
                  .fillMaxSize()
                  .padding(all = 128.dp),
           color = RickAction
    )
}
