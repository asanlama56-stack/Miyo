package com.example.miyo.data.repository

import android.content.Context
import android.os.Environment
import com.example.miyo.data.db.MiyoDatabase
import com.example.miyo.data.db.entity.DownloadEntity
import com.example.miyo.data.db.entity.MangaEntity
import com.example.miyo.data.db.entity.ReadingHistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.koitharu.kotatsu.parsers.model.Manga
import org.koitharu.kotatsu.parsers.model.MangaChapter
import java.io.File

/**
 * Repository for manga library operations using Room database.
 */
class MangaLibraryRepository(context: Context) {
    private val db = MiyoDatabase.getInstance(context)
    private val mangaDao = db.mangaDao()
    private val historyDao = db.readingHistoryDao()
    private val downloadDao = db.downloadDao()
    private val categoryDao = db.categoryDao()
    private val storageDir = File(
        context.getExternalFilesDir(null) ?: context.filesDir,
        "miyo_library"
    ).apply { mkdirs() }

    // Manga Library Operations
    suspend fun addMangaToLibrary(manga: Manga) = withContext(Dispatchers.IO) {
        val entity = MangaEntity(
            id = manga.id,
            title = manga.title,
            url = manga.url,
            sourceId = 0, // TODO: map source
            coverUrl = manga.coverUrl,
            author = manga.author,
            description = manga.description,
        )
        mangaDao.insertManga(entity)
    }

    suspend fun removeMangaFromLibrary(mangaId: Long) = withContext(Dispatchers.IO) {
        mangaDao.deleteManga(MangaEntity(id = mangaId, title = "", url = "", sourceId = 0))
    }

    fun getAllMangaFlow(): Flow<List<MangaEntity>> = mangaDao.getAllManga()

    suspend fun searchManga(query: String): List<MangaEntity> = withContext(Dispatchers.IO) {
        mangaDao.searchManga("%$query%")
    }

    // Reading Progress Operations
    suspend fun updateReadingProgress(
        mangaId: Long,
        chapterId: Long,
        chapterTitle: String,
        currentPage: Int,
        totalPages: Int,
    ) = withContext(Dispatchers.IO) {
        val history = ReadingHistoryEntity(
            mangaId = mangaId,
            chapterId = chapterId,
            chapterTitle = chapterTitle,
            currentPage = currentPage,
            totalPages = totalPages,
        )
        historyDao.insertHistory(history)
    }

    suspend fun getReadingProgress(mangaId: Long): ReadingHistoryEntity? = withContext(Dispatchers.IO) {
        historyDao.getLastRead(mangaId)
    }

    fun getRecentlyReadFlow(): Flow<List<ReadingHistoryEntity>> = historyDao.getRecentHistory()

    // Download Management
    suspend fun addDownloadToQueue(
        mangaId: Long,
        chapterId: Long,
        chapterTitle: String,
        sourceId: Int,
        totalPages: Int,
    ) = withContext(Dispatchers.IO) {
        val download = DownloadEntity(
            mangaId = mangaId,
            chapterId = chapterId,
            chapterTitle = chapterTitle,
            sourceId = sourceId,
            totalPages = totalPages,
            status = "QUEUED",
        )
        downloadDao.insertDownload(download)
    }

    suspend fun updateDownloadProgress(
        downloadId: Long,
        progress: Int,
        status: String = "IN_PROGRESS",
    ) = withContext(Dispatchers.IO) {
        // Find and update the download entity
        // Note: This requires a proper DAO update method with specific ID
        // For now, we'll track this via downloadDao.updateDownload()
    }

    fun getQueuedDownloadsFlow(): Flow<List<DownloadEntity>> = downloadDao.getDownloadsByStatus("QUEUED")

    fun getCompletedDownloadsFlow(): Flow<List<DownloadEntity>> = downloadDao.getDownloadsByStatus("COMPLETED")

    suspend fun getDownloadsForManga(mangaId: Long): List<DownloadEntity> = withContext(Dispatchers.IO) {
        downloadDao.getDownloadsForManga(mangaId)
    }

    // Storage Utilities
    fun getMangaStorageDir(mangaId: Long): File {
        return File(storageDir, "manga_$mangaId").apply { mkdirs() }
    }

    fun getChapterStorageDir(mangaId: Long, chapterId: Long): File {
        return File(getMangaStorageDir(mangaId), "chapter_$chapterId").apply { mkdirs() }
    }

    fun getTotalStorageUsage(): Long {
        return storageDir.walkTopDown().sumOf { it.length() }
    }
}
