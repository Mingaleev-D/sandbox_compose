package com.example.sandbox_compose.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sandbox_compose.feature.discover.data.local.DiscoverArticleDao
import com.example.sandbox_compose.feature.discover.data.local.DiscoverArticleDto
import com.example.sandbox_compose.feature.discover.data.local.DiscoverKeys
import com.example.sandbox_compose.feature.discover.data.local.DiscoverRemoteKeyDao
import com.example.sandbox_compose.feature.headline.data.local.HeadlineDao
import com.example.sandbox_compose.feature.headline.data.local.HeadlineDto
import com.example.sandbox_compose.feature.headline.data.local.HeadlineRemoteKey
import com.example.sandbox_compose.feature.headline.data.local.HeadlineRemoteKeyDao

@Database(
       entities = [
           HeadlineDto::class,
           HeadlineRemoteKey::class,
               DiscoverArticleDto::class,
               DiscoverKeys::class,
           //    SearchDto::class,
           //    SearchRemoteKey::class,
           //    SettingsDto::class
       ], version = 1, exportSchema = false
)
abstract class NewsyArticleDatabase : RoomDatabase() {

    abstract fun headlineDao(): HeadlineDao
    abstract fun headlineRemoteDao(): HeadlineRemoteKeyDao
    abstract fun discoverArticleDao(): DiscoverArticleDao
    abstract fun discoverRemoteKeyDao(): DiscoverRemoteKeyDao
    //    abstract fun detailDao(): DetailDao
    //    abstract fun searchArticleDao(): SearchArticleDao
    //    abstract fun searchKeyDao(): SearchRemoteKeyDao
    //    abstract fun favouriteDao(): FavouriteDao
    //    abstract fun settingDao(): SettingDao
}
