package com.example.sandbox_compose.feature.details.data.local

import androidx.room.Dao
import androidx.room.Query
import com.example.sandbox_compose.feature.details.data.model.DetailDto

@Dao
interface DetailDao {

    @Query("SELECT * FROM headline_table WHERE id=:id")
    suspend fun getHeadlineArticleById(id: Int): DetailDto


    @Query("SELECT * FROM discover_article WHERE id=:id")
    suspend fun getDiscoverArticleById(id: Int): DetailDto

//    @Query("SELECT * FROM search_table WHERE id=:id")
//    suspend fun getSearchArticleById(id: Int): DetailDto
}
