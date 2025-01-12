package com.example.sandbox_compose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
       entities = [FavoriteImageEntity::class],
       version = 1,
       exportSchema = false
)
abstract class ImageVistaDatabase : RoomDatabase() {

    abstract fun favoriteImagesDao(): FavoriteImagesDao
}
