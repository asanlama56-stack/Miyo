package com.example.miyo.core

/**
 * Core data models for the Miyo app.
 */

data class MangaLibraryItem(
    val id: Long,
    val title: String,
    val coverUrl: String?,
    val author: String?,
    val status: String,
    val readingProgress: Float = 0f,
)

data class ChapterInfo(
    val id: Long,
    val title: String,
    val number: Float,
    val scanlator: String?,
    val dateUploaded: Long,
    val pages: Int,
)

data class PageInfo(
    val index: Int,
    val url: String,
    val height: Int = 0,
    val width: Int = 0,
)

enum class ReadingMode {
    PAGE,
    VERTICAL,
    WEBTOON,
}

enum class ReadingDirection {
    LTR,
    RTL,
}

/**
 * 12+ Theme options for Miyo.
 */
enum class AppTheme {
    LIGHT,
    DARK,
    AMOLED,
    LIGHT_BLUE,
    DARK_BLUE,
    LIGHT_GREEN,
    DARK_GREEN,
    LIGHT_PURPLE,
    DARK_PURPLE,
    LIGHT_ORANGE,
    DARK_ORANGE,
    LIGHT_RED,
    DARK_RED,
}

data class ReaderSettings(
    val readingMode: ReadingMode = ReadingMode.PAGE,
    val readingDirection: ReadingDirection = ReadingDirection.LTR,
    val brightness: Float = 1.0f,
    val theme: AppTheme = AppTheme.DARK,
    val pageTransition: Boolean = true,
    val doubleTapToZoom: Boolean = true,
    val volumeKeysNavigation: Boolean = true,
)
