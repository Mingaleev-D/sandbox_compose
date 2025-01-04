package com.example.sandbox_compose.core.data.repository

import com.example.sandbox_compose.core.data.mapper.CharacterDto
import com.example.sandbox_compose.core.data.mapper.CharacterPageDto
import com.example.sandbox_compose.core.data.mapper.EpisodeDto
import com.example.sandbox_compose.core.data.remote.ApiOperation
import com.example.sandbox_compose.core.data.remote.KtorClient
import javax.inject.Inject

class CharacterRepo @Inject constructor(
       private val ktorClient: KtorClient
) {

    suspend fun fetchCharacterPage(
           page: Int,
           params: Map<String, String> = emptyMap()
    ): ApiOperation<CharacterPageDto> {
        return ktorClient.getCharacterByPage(pageNumber = page, queryParams = params)
    }


    suspend fun fetchCharacter(characterId: Int): ApiOperation<CharacterDto> {
        return ktorClient.getCharacters(characterId)
    }

    suspend fun fetchAllEpisodes(): ApiOperation<List<EpisodeDto>> = ktorClient.getAllEpisodes()

    suspend fun fetchAllCharactersByName(searchQuery: String): ApiOperation<List<CharacterDto>> {
        return ktorClient.searchAllCharactersByName(searchQuery)
    }
}
