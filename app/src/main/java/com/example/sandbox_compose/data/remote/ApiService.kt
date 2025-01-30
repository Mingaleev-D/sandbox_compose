package com.example.sandbox_compose.data.remote

import com.example.sandbox_compose.data.model.NewsyRemoteDTO
import com.example.sandbox_compose.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines?")
    suspend fun getHeadlines(
           @Query("country") country: String,
           @Query("category") category: String,
           @Query("language") language: String,
           @Query("page") page: Int,
           @Query("pageSize") pageSize: Int,
           @Query("apiKey") apiKey: String = Constants.API_KEY,
    ): NewsyRemoteDTO
}
