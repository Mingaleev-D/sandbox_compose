package com.example.sandbox_compose.data.remote

import com.example.sandbox_compose.data.model.RemoteEverythingNews
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "6a921a4ac59c4660b121ca39c86300ac"

interface ApiService {

    @GET("everything")
    suspend fun getEverythingNews(
           @Query("sources") sources: String,
           @Query("page") page: Int,
           @Query("apiKey") apiKey: String = API_KEY
    ): RemoteEverythingNews
}
