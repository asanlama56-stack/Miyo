package com.example.miyo.core

/**
 * Minimal parser interface for a manga source.
 * Real implementations will perform network calls and parsing.
 */
interface MangaParser {
    suspend fun fetchChapterList(seriesUrl: String): List<String>
}
