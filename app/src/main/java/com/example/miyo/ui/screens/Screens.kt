package com.example.miyo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.miyo.core.AppTheme
import com.example.miyo.core.ChapterInfo
import com.example.miyo.core.MangaLibraryItem
import com.example.miyo.core.PageInfo
import com.example.miyo.ui.components.ChapterItem
import com.example.miyo.ui.components.DownloadQueueItem
import com.example.miyo.ui.components.MangaCard
import com.example.miyo.ui.components.ReaderPageView

@Composable
fun ThemeSelectionModal(
    onThemeSelected: (AppTheme) -> Unit,
    onDismiss: () -> Unit,
) {
    val themes = remember { AppTheme.entries.toList() }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Theme") },
        text = {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(themes) { theme ->
                    ThemeItem(theme) {
                        onThemeSelected(theme)
                        onDismiss()
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
    )
}

@Composable
private fun ThemeItem(
    theme: AppTheme,
    onClick: () -> Unit,
) {
    val backgroundColor = when (theme) {
        AppTheme.LIGHT -> MaterialTheme.colorScheme.surface
        AppTheme.DARK -> MaterialTheme.colorScheme.surface
        AppTheme.AMOLED -> MaterialTheme.colorScheme.background
        AppTheme.LIGHT_BLUE -> Color(0xFFE3F2FD)
        AppTheme.DARK_BLUE -> Color(0xFF0D47A1)
        AppTheme.LIGHT_GREEN -> Color(0xFFF1F8E9)
        AppTheme.DARK_GREEN -> Color(0xFF1B5E20)
        AppTheme.LIGHT_PURPLE -> Color(0xFFF3E5F5)
        AppTheme.DARK_PURPLE -> Color(0xFF4A148C)
        AppTheme.LIGHT_ORANGE -> Color(0xFFFFE0B2)
        AppTheme.DARK_ORANGE -> Color(0xFFE65100)
        AppTheme.LIGHT_RED -> Color(0xFFFFEBEE)
        AppTheme.DARK_RED -> Color(0xFF1A1A1A)
    }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, shape = MaterialTheme.shapes.medium)
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            theme.name.replace("_", " "),
            style = MaterialTheme.typography.bodyMedium,
        )
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(backgroundColor, shape = MaterialTheme.shapes.small),
        )
    }
}

@Composable
fun LibraryScreen() {
    var selectedManga by remember { mutableStateOf<MangaLibraryItem?>(null) }
    var showMangaDetails by remember { mutableStateOf(false) }

    val sampleMangaList = listOf(
        MangaLibraryItem(
            id = 1,
            title = "One Piece",
            coverUrl = null,
            author = "Eiichiro Oda",
            status = "ONGOING",
            readingProgress = 0.75f,
        ),
        MangaLibraryItem(
            id = 2,
            title = "Attack on Titan",
            coverUrl = null,
            author = "Hajime Isayama",
            status = "COMPLETED",
            readingProgress = 1.0f,
        ),
    )

    if (showMangaDetails && selectedManga != null) {
        MangaDetailsScreen(
            manga = selectedManga!!,
            onBack = { showMangaDetails = false },
        )
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(sampleMangaList) { manga ->
                MangaCard(
                    item = manga,
                    onClick = {
                        selectedManga = manga
                        showMangaDetails = true
                    },
                )
            }
        }
    }
}

@Composable
fun MangaDetailsScreen(manga: MangaLibraryItem, onBack: () -> Unit) {
    val chapters = listOf(
        ChapterInfo(
            id = 1,
            title = "Chapter 1",
            number = 1f,
            scanlator = "Team A",
            dateUploaded = System.currentTimeMillis(),
            pages = 20,
        ),
        ChapterInfo(
            id = 2,
            title = "Chapter 2",
            number = 2f,
            scanlator = "Team A",
            dateUploaded = System.currentTimeMillis(),
            pages = 18,
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Button(onClick = onBack) {
            Text("← Back")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(manga.title, style = MaterialTheme.typography.headlineMedium)
        Text(manga.author ?: "Unknown", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(chapters) { chapter ->
                ChapterItem(
                    chapter = chapter,
                    onRead = {},
                    onDownload = {},
                )
            }
        }
    }
}

@Composable
fun ReaderScreen() {
    var currentPageIndex by remember { mutableStateOf(0) }
    val pages = listOf(
        PageInfo(0, "https://via.placeholder.com/400x600?text=Page+1"),
        PageInfo(1, "https://via.placeholder.com/400x600?text=Page+2"),
        PageInfo(2, "https://via.placeholder.com/400x600?text=Page+3"),
    )

    if (currentPageIndex >= 0 && currentPageIndex < pages.size) {
        ReaderPageView(
            page = pages[currentPageIndex],
            onNextPage = { if (currentPageIndex < pages.size - 1) currentPageIndex++ },
            onPreviousPage = { if (currentPageIndex > 0) currentPageIndex-- },
        )
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("No pages available")
        }
    }
}

@Composable
fun DownloadsScreen() {
    val downloadQueue = listOf(
        Triple("One Piece - Chapter 1", 15, 20),
        Triple("Attack on Titan - Chapter 5", 8, 18),
    )

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TopAppBar(
            title = { Text("Downloads") },
        )

        if (downloadQueue.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text("No downloads in queue")
            }
        } else {
            LazyColumn {
                items(downloadQueue.size) { index ->
                    val (title, progress, total) = downloadQueue[index]
                    DownloadQueueItem(
                        title = title,
                        progress = progress,
                        totalPages = total,
                        status = if (progress < total) "DOWNLOADING" else "COMPLETED",
                    )
                }
            }
        }
    }
}
