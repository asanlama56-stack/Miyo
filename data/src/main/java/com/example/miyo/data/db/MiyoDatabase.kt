package com.example.miyo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.miyo.data.db.dao.MangaDao
import com.example.miyo.data.db.dao.ReadingHistoryDao
import com.example.miyo.data.db.dao.DownloadDao
import com.example.miyo.data.db.dao.CategoryDao
import com.example.miyo.data.db.entity.*

/**
 * Room database for Miyo app, storing manga, reading progress, downloads, and categories.
 */
@Database(
    entities = [
        MangaEntity::class,
        ReadingHistoryEntity::class,
        DownloadEntity::class,
        CategoryEntity::class,
        MangaCategoryJoin::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class MiyoDatabase : RoomDatabase() {
    abstract fun mangaDao(): MangaDao
    abstract fun readingHistoryDao(): ReadingHistoryDao
    abstract fun downloadDao(): DownloadDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: MiyoDatabase? = null

        fun getInstance(context: Context): MiyoDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MiyoDatabase::class.java,
                    "miyo.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
