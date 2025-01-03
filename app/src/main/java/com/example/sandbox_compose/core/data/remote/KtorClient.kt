package com.example.sandbox_compose.core.data.remote

import com.example.sandbox_compose.core.data.mapper.CharacterDto
import com.example.sandbox_compose.core.data.mapper.CharacterPageDto
import com.example.sandbox_compose.core.data.mapper.EpisodeDto
import com.example.sandbox_compose.core.data.mapper.EpisodePageDto
import com.example.sandbox_compose.core.data.remote.model.RemoteCharacter
import com.example.sandbox_compose.core.data.remote.model.RemoteCharacterPage
import com.example.sandbox_compose.core.data.remote.model.RemoteEpisode
import com.example.sandbox_compose.core.data.remote.model.RemoteEpisodePage
import com.example.sandbox_compose.core.data.remote.model.toDomainCharacter
import com.example.sandbox_compose.core.data.remote.model.toDomainCharacterPage
import com.example.sandbox_compose.core.data.remote.model.toDomainEpisode
import com.example.sandbox_compose.core.data.remote.model.toDomainEpisodePage
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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
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

    suspend fun getEpisodes(episodeIds: List<Int>): ApiOperation<List<EpisodeDto>> {
        return if (episodeIds.size == 1) {
            getEpisode(episodeIds[0]).mapSuccess {
                listOf(it)
            }
        } else {
            val idsCommaSeparated = episodeIds.joinToString(separator = ",")
            safeApiCall {
                client.get("episode/$idsCommaSeparated")
                       .body<List<RemoteEpisode>>()
                       .map { it.toDomainEpisode() }
            }
        }
    }

    private suspend fun getEpisode(episodeId: Int): ApiOperation<EpisodeDto> {
        return safeApiCall {
            client.get("episode/$episodeId")
                   .body<RemoteEpisode>()
                   .toDomainEpisode()
        }
    }

    suspend fun getCharacterByPage(pageNumber: Int): ApiOperation<CharacterPageDto> {
        return safeApiCall {
            client.get("character/?page=$pageNumber")
                   .body<RemoteCharacterPage>()
                   .toDomainCharacterPage()
        }
    }

    suspend fun getEpisodesByPage(pageIndex: Int): ApiOperation<EpisodePageDto> {
        return safeApiCall {
            client.get("episode") {
                url {
                    parameters.append("page", pageIndex.toString())
                }
            }
                   .body<RemoteEpisodePage>()
                   .toDomainEpisodePage()
        }
    }

    suspend fun getAllEpisodes(): ApiOperation<List<EpisodeDto>> {
        val data = mutableListOf<EpisodeDto>()
        var exception: Exception? = null

        coroutineScope {
            launch {
                getEpisodesByPage(pageIndex = 1).onSuccess { firstPage ->
                    val totalPageCount = firstPage.info.pages
                    data.addAll(firstPage.episodes)

                    repeat(totalPageCount - 1) { index ->
                        launch {
                            getEpisodesByPage(pageIndex = index + 2).onSuccess { nextPage ->
                                data.addAll(nextPage.episodes)
                            }.onFailure { error ->
                                exception = error
                            }
                        }
                        if (exception == null) {
                            return@onSuccess
                        }
                    }
                }.onFailure {
                    exception = it
                }
            }
        }

        return exception?.let { ApiOperation.Failure(it) } ?: ApiOperation.Success(data)
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

    fun <R> mapSuccess(transform: (T) -> R): ApiOperation<R> {
        return when (this) {
            is Success -> Success(transform(data))
            is Failure -> Failure(exception)
        }
    }
}
