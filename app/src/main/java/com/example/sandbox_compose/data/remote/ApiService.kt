package com.example.sandbox_compose.data.remote

import com.example.sandbox_compose.data.model.RemoteGameListItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getAllGamesList(): List<RemoteGameListItem>

    @GET("games")
    suspend fun getUpcomingGames(
           @Query("sort-by") sortBy: String = "alphabetical"
    ): List<RemoteGameListItem>

    @GET("games")
    suspend fun getRecommendedGames(
           @Query("sort-by") sortBy: String
    ): List<RemoteGameListItem>
}
