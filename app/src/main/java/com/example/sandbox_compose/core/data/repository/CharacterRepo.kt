package com.example.sandbox_compose.core.data.repository

import com.example.sandbox_compose.core.data.mapper.CharacterDto
import com.example.sandbox_compose.core.data.mapper.CharacterPageDto
import com.example.sandbox_compose.core.data.remote.ApiOperation
import com.example.sandbox_compose.core.data.remote.KtorClient
import javax.inject.Inject

class CharacterRepo @Inject constructor(
       private val ktorClient: KtorClient
) {

    suspend fun fetchCharacterPage(page: Int): ApiOperation<CharacterPageDto> {
        return ktorClient.getCharacterByPage(pageNumber = page)
    }

    suspend fun fetchCharacter(characterId: Int): ApiOperation<CharacterDto> {
        return ktorClient.getCharacters(characterId)
    }
}
