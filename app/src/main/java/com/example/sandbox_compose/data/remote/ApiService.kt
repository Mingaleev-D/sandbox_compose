package com.example.sandbox_compose.data.remote

import com.example.sandbox_compose.data.model.RemoteGameListItem
import retrofit2.http.GET

interface ApiService {

    @GET("games")
    suspend fun getAllGamesList(): List<RemoteGameListItem>
}
