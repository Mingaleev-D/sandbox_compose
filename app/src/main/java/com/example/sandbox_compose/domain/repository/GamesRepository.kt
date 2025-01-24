package com.example.sandbox_compose.domain.repository

import com.example.sandbox_compose.domain.model.GameUIModel

interface GamesRepository {

    suspend fun getListGames(): Result<List<GameUIModel>>
    suspend fun getUpcomingGames(): Result<List<GameUIModel>>
    suspend fun getRecommendedGames(itemSort: String): Result<List<GameUIModel>>
}
