package com.example.sandbox_compose.domain.repository

import com.example.sandbox_compose.domain.model.GameUIModel

interface GamesRepository {
    suspend fun getListGames():List<GameUIModel>
}
