package com.example.database.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.database.dao.WishListDao
import com.example.database.entity.WishListEntity


@Database(
    entities = [WishListEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun wishlistDao() : WishListDao
}