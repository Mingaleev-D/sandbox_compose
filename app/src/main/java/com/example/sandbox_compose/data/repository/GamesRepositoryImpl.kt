package com.example.sandbox_compose.data.repository

import com.example.sandbox_compose.data.mapper.toUIModelList
import com.example.sandbox_compose.data.remote.ApiService
import com.example.sandbox_compose.domain.repository.GamesRepository
import kotlinx.coroutines.CancellationException
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
       private val apiService: ApiService
) : GamesRepository {

    override suspend fun getListGames() = resultOf {
        apiService.getAllGamesList().toUIModelList()
    }

    override suspend fun getUpcomingGames() = resultOf {
        apiService.getUpcomingGames().toUIModelList()
    }

    override suspend fun getRecommendedGames(itemSort: String) = resultOf {
        apiService.getRecommendedGames(itemSort).toUIModelList()
    }
}

// https://www.droidcon.com/2022/04/06/resilient-use-cases-with-kotlin-result-coroutines-and-annotations/
inline fun <T, R> T.resultOf(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }
}
