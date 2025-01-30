package com.example.sandbox_compose.feature.headline.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "headline_table")
data class HeadlineDto(
       @PrimaryKey(autoGenerate = true)
       override val id: Int = 0,
       override val author: String,
       override val content: String,
       override val description: String,
       @ColumnInfo(name = "published_at")
       override val publishedAt: String,
       override val source: String,
       override val title: String,
       override val url: String,
       @ColumnInfo(name = "url_to_image")
       override val urlToImage: String?,
       override val favourite: Boolean = false,
       override val category: String,
       override var page: Int
) : LocalContractDto()
