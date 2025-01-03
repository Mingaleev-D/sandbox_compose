package com.example.sandbox_compose.core.data.repository

import com.example.sandbox_compose.core.data.remote.KtorClient
import javax.inject.Inject

class CharacterRepo @Inject constructor(
       private val ktorClient: KtorClient
) {

    suspend fun fetchCharacter(characterId: Int) = ktorClient.getCharacters(characterId)
}
