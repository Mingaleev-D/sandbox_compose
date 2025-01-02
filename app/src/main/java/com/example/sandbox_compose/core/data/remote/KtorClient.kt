package com.example.sandbox_compose.core.data.remote

import com.example.sandbox_compose.core.data.mapper.CharacterDto
import com.example.sandbox_compose.core.data.mapper.EpisodeDto
import com.example.sandbox_compose.core.data.remote.model.RemoteCharacter
import com.example.sandbox_compose.core.data.remote.model.RemoteEpisode
import com.example.sandbox_compose.core.data.remote.model.toDomainCharacter
import com.example.sandbox_compose.core.data.remote.model.toDomainEpisode
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
    private var characterCache = mutableMapOf<Int, CharacterDto>()

    suspend fun getCharacters(id: Int): ApiOperation<CharacterDto> {
        characterCache[id]?.let { return ApiOperation.Success(it) }
        return safeApiCall {
            client.get("character/$id")
                   .body<RemoteCharacter>()
                   .toDomainCharacter()
                   .also { characterCache[id] = it }
        }
    }

    suspend fun getEpisodes(episodesIds: List<Int>): ApiOperation<List<EpisodeDto>> {
        val idsCommaSeparated = episodesIds.joinToString(separator = ",")
        return safeApiCall {
            client.get("episode/$idsCommaSeparated")
                   .body<List<RemoteEpisode>>()
                   .map { it.toDomainEpisode() }
            //.also { characterCache[id] = it }
        }
    }

    private inline fun <T> safeApiCall(apiCall: () -> T): ApiOperation<T> {
        return try {
            ApiOperation.Success(data = apiCall())
        } catch (e: Exception) {
            ApiOperation.Failure(exception = e)
        }
    }
}

sealed interface ApiOperation<T> {
    data class Success<T>(val data: T) : ApiOperation<T>
    data class Failure<T>(val exception: Exception) : ApiOperation<T>

    fun onSuccess(block: (T) -> Unit): ApiOperation<T> {
        if (this is Success) block(data)
        return this
    }

    fun onFailure(block: (Exception) -> Unit): ApiOperation<T> {
        if (this is Failure) block(exception)
        return this
    }
}
