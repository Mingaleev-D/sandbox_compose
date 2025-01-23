package com.example.sandbox_compose.domain.repository

import com.example.sandbox_compose.domain.model.GameUIModel
import kotlinx.coroutines.CancellationException

interface GamesRepository {
    suspend fun getListGames():Result<List<GameUIModel>>
    suspend fun getUpcomingGames():Result<List<GameUIModel>>
}
