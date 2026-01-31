package com.example.miyo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.miyo.core.AppTheme
import com.example.miyo.ui.screens.LibraryScreen
import com.example.miyo.ui.screens.ThemeSelectionModal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var selectedTheme by remember { mutableStateOf(AppTheme.DARK) }
            var showThemeModal by remember { mutableStateOf(true) }
            var hasShownModal by remember { mutableStateOf(false) }

            // Show theme modal on first launch
            if (!hasShownModal) {
                LaunchedEffect(Unit) {
                    showThemeModal = true
                    hasShownModal = true
                }
            }

            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                Column(modifier = Modifier.fillMaxSize()) {
                    // Top app bar
                    TopAppBar(
                        title = { Text("Miyo — Manga Reader") },
                    )

                    // Main content
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                    ) {
                        LibraryScreen()
                    }
                }

                // Theme selection modal (shown on first app open)
                if (showThemeModal) {
                    ThemeSelectionModal(
                        onThemeSelected = { theme ->
                            selectedTheme = theme
                        },
                        onDismiss = {
                            showThemeModal = false
                        },
                    )
                }
            }
        }
    }
}
