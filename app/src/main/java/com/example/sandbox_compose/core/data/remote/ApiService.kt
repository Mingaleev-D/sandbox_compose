package com.example.sandbox_compose.core.data.remote

import com.example.sandbox_compose.core.data.model.NewsyRemoteDTO
import com.example.sandbox_compose.feature.search.data.remote.model.SearchArticleRemoteDto
import com.example.sandbox_compose.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {

        private const val DISCOVER_END_POINT = "top-headlines"
        private const val SEARCH_END_POINT = "everything"
    }

    @GET("top-headlines?")
    suspend fun getHeadlines(
           @Query("country") country: String,
           @Query("category") category: String,
           @Query("language") language: String,
           @Query("page") page: Int,
           @Query("pageSize") pageSize: Int,
           @Query("apiKey") apiKey: String = Constants.API_KEY,
    ): NewsyRemoteDTO

    @GET(DISCOVER_END_POINT)
    suspend fun getDiscoverHeadlines(
           @Query("apiKey") key: String = Constants.API_KEY,
           @Query("category") category: String,
           @Query("country") country: String,
           @Query("language") language: String,
           @Query("page") page: Int,
           @Query("pageSize") pageSize: Int,
    ): NewsyRemoteDTO


    @GET(SEARCH_END_POINT)
    suspend fun fetchSearchArticle(
           @Query("q") query: String,
           @Query("page") page: Int,
           @Query("apiKey") key: String = Constants.API_KEY,
    ): SearchArticleRemoteDto
}
