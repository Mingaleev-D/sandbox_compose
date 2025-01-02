package com.example.sandbox_compose.core.data.remote

import com.example.sandbox_compose.core.data.mapper.CharacterDto
import com.example.sandbox_compose.core.data.remote.model.RemoteCharacter
import com.example.sandbox_compose.core.data.remote.model.toDomainCharacter
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient {

    private val client = HttpClient(OkHttp) {
        defaultRequest {
            url("https://rickandmortyapi.com/api/")
        }
        install(Logging) {
            logger = Logger.SIMPLE
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getCharacters(id: Int): CharacterDto {
        return client.get("character/$id")
               .body<RemoteCharacter>()
               .toDomainCharacter()
    }
}
