package com.example.sandbox_compose.feature.search.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface SearchArticleDao {
    @Query("SELECT * FROM search_table")
    fun getAllSearchArticle(): PagingSource<Int, SearchDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSearchArticles(articles: List<SearchDto>)

    @Query(
           "DELETE FROM search_table WHERE favourite = 0"
    )
    suspend fun removeAllSearchArticle()

    @Query(
           "UPDATE search_table SET favourite =:isFavourite WHERE id=:id"
    )
    suspend fun updateFavouriteArticle(isFavourite: Boolean, id: Int)

}
