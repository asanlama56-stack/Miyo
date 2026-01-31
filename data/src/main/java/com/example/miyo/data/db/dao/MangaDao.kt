package com.example.miyo.data.db.dao

import androidx.room.*
import com.example.miyo.data.db.entity.MangaEntity
import com.example.miyo.data.db.entity.ReadingHistoryEntity
import com.example.miyo.data.db.entity.DownloadEntity
import com.example.miyo.data.db.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MangaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManga(manga: MangaEntity)

    @Update
    suspend fun updateManga(manga: MangaEntity)

    @Delete
    suspend fun deleteManga(manga: MangaEntity)

    @Query("SELECT * FROM manga WHERE id = :id")
    suspend fun getMangaById(id: Long): MangaEntity?

    @Query("SELECT * FROM manga ORDER BY dateAdded DESC")
    fun getAllManga(): Flow<List<MangaEntity>>

    @Query("SELECT * FROM manga WHERE title LIKE :query LIMIT 50")
    suspend fun searchManga(query: String): List<MangaEntity>
}

@Dao
interface ReadingHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: ReadingHistoryEntity)

    @Update
    suspend fun updateHistory(history: ReadingHistoryEntity)

    @Query("SELECT * FROM reading_history WHERE mangaId = :mangaId ORDER BY dateRead DESC LIMIT 1")
    suspend fun getLastRead(mangaId: Long): ReadingHistoryEntity?

    @Query("SELECT * FROM reading_history ORDER BY dateRead DESC LIMIT 50")
    fun getRecentHistory(): Flow<List<ReadingHistoryEntity>>
}

@Dao
interface DownloadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownload(download: DownloadEntity)

    @Update
    suspend fun updateDownload(download: DownloadEntity)

    @Delete
    suspend fun deleteDownload(download: DownloadEntity)

    @Query("SELECT * FROM downloads WHERE status = :status ORDER BY dateAdded DESC")
    fun getDownloadsByStatus(status: String = "QUEUED"): Flow<List<DownloadEntity>>

    @Query("SELECT * FROM downloads WHERE mangaId = :mangaId")
    suspend fun getDownloadsForManga(mangaId: Long): List<DownloadEntity>
}

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)

    @Update
    suspend fun updateCategory(category: CategoryEntity)

    @Delete
    suspend fun deleteCategory(category: CategoryEntity)

    @Query("SELECT * FROM categories ORDER BY `order` ASC")
    fun getAllCategories(): Flow<List<CategoryEntity>>
}
