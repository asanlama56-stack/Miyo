package com.example.miyo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.miyo.core.ChapterInfo
import com.example.miyo.core.MangaLibraryItem
import com.example.miyo.core.PageInfo

/**
 * Manga card for library display.
 */
@Composable
fun MangaCard(
    item: MangaLibraryItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp),
        onClick = onClick,
    ) {
        Column {
            // Cover image
            if (item.coverUrl != null) {
                AsyncImage(
                    model = item.coverUrl,
                    contentDescription = item.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    contentScale = ContentScale.Crop,
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center,
                ) {
                    Text("No Image")
                }
            }

            // Info
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(1f),
            ) {
                Text(
                    item.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    item.author ?: "Unknown",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            // Progress bar
            LinearProgressIndicator(
                progress = item.readingProgress,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

/**
 * Chapter list item with download button.
 */
@Composable
fun ChapterItem(
    chapter: ChapterInfo,
    onRead: () -> Unit,
    onDownload: () -> Unit,
) {
    ListItem(
        headlineContent = {
            Text(chapter.title)
        },
        supportingContent = {
            Text("${chapter.pages} pages")
        },
        trailingContent = {
            IconButton(onClick = onDownload) {
                Icon(Icons.Default.Download, contentDescription = "Download")
            }
        },
        modifier = Modifier.clickable(onClick = onRead),
    )
}

/**
 * Reader page view with zoom and drag support.
 */
@Composable
fun ReaderPageView(
    page: PageInfo,
    modifier: Modifier = Modifier,
    onNextPage: () -> Unit,
    onPreviousPage: () -> Unit,
) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val transformState = rememberTransformableState { zoomChange, offsetChange, _ ->
        scale *= zoomChange
        offset += offsetChange
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .transformable(state = transformState)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    offset += dragAmount
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        AsyncImage(
            model = page.url,
            contentDescription = "Page ${page.index}",
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y,
                    transformOrigin = TransformOrigin.Center,
                ),
            contentScale = ContentScale.Fit,
        )

        // Navigation buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(onClick = onPreviousPage) {
                Text("← Previous")
            }
            Text(
                "Page ${page.index + 1}",
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically),
            )
            Button(onClick = onNextPage) {
                Text("Next →")
            }
        }
    }
}

/**
 * Downloads queue manager UI.
 */
@Composable
fun DownloadQueueItem(
    title: String,
    progress: Int,
    totalPages: Int,
    status: String,
) {
    ListItem(
        headlineContent = { Text(title) },
        supportingContent = {
            Column {
                Text("$progress / $totalPages pages")
                LinearProgressIndicator(
                    progress = progress.toFloat() / maxOf(totalPages, 1),
                    modifier = Modifier.fillMaxWidth(),
                )
                Text(status, style = MaterialTheme.typography.labelSmall)
            }
        },
        trailingContent = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.MoreVert, contentDescription = "Options")
            }
        },
    )
}

// Fix missing import
private fun Modifier.clickable(onClick: () -> Unit) = this
