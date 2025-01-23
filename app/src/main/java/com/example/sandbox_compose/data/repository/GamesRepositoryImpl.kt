package com.example.sandbox_compose.data.repository

import com.example.sandbox_compose.data.mapper.toUIModelList
import com.example.sandbox_compose.data.remote.ApiService
import com.example.sandbox_compose.domain.model.GameUIModel
import com.example.sandbox_compose.domain.repository.GamesRepository
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
       private val apiService: ApiService
) : GamesRepository {

    override suspend fun getListGames(): List<GameUIModel> {
        return apiService.getAllGamesList().toUIModelList()
    }
}
