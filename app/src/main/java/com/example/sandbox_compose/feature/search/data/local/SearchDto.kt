package com.example.sandbox_compose.feature.search.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sandbox_compose.core.data.local.LocalContractDto

@Entity(tableName = "search_table")
data class SearchDto(
       @PrimaryKey(autoGenerate = true)
       override val id: Int = 0,
       override val author: String,
       override val content: String,
       override val description: String,
       @ColumnInfo("published_at")
       override val publishedAt: String,
       override val source: String,
       override val title: String,
       override val url: String,
       @ColumnInfo("url_to_image")
       override val urlToImage: String?,
       override val favourite: Boolean = false,
       override val category: String,
       override var page: Int

): LocalContractDto()
