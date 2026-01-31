package com.example.miyo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.koitharu.kotatsu.parsers.model.Manga

/**
 * Room entity for storing manga library entries.
 */
@Entity(tableName = "manga")
data class MangaEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val url: String,
    val sourceId: Int,
    val coverUrl: String?,
    val status: String = "ONGOING",
    val author: String? = null,
    val description: String? = null,
    val tags: String? = null, // JSON serialized
    val rating: Float = 0f,
    val isTracking: Boolean = false,
    val dateAdded: Long = System.currentTimeMillis(),
    val dateUpdated: Long = System.currentTimeMillis(),
)

/**
 * Room entity for tracking reading progress.
 */
@Entity(tableName = "reading_history")
data class ReadingHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val mangaId: Long,
    val chapterId: Long,
    val chapterTitle: String,
    val currentPage: Int = 0,
    val totalPages: Int = 0,
    val dateRead: Long = System.currentTimeMillis(),
)

/**
 * Room entity for managing downloads.
 */
@Entity(tableName = "downloads")
data class DownloadEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val mangaId: Long,
    val chapterId: Long,
    val chapterTitle: String,
    val sourceId: Int,
    val status: String = "QUEUED", // QUEUED, IN_PROGRESS, COMPLETED, FAILED
    val progress: Int = 0,
    val totalPages: Int = 0,
    val localPath: String? = null,
    val dateAdded: Long = System.currentTimeMillis(),
)

/**
 * Room entity for manga categories/collections.
 */
@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val order: Int = 0,
)

/**
 * Join table for many-to-many relationship between manga and categories.
 */
@Entity(tableName = "manga_category", primaryKeys = ["mangaId", "categoryId"])
data class MangaCategoryJoin(
    val mangaId: Long,
    val categoryId: Long,
)
