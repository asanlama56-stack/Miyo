# Miyo — Modern Kotlin Android Manga Reader

A fast, offline-friendly, and extensible manga reader app inspired by Tachiyomi, Mihon, and Kotatsu, built with modern Android architecture using Jetpack Compose and Kotlin.

## Features

✅ **Implemented:**
- Modular architecture with `core`, `data`, `extensions`, and `app` modules
- 13+ theme selection modal (Light, Dark, AMOLED, and colored variants)
- Jetpack Compose UI with Material 3 Design System
- Room database for offline library management
- Reading progress tracking
- Manga library with search and filtering
- Downloads queue manager with background processing
- Kotatsu parser integration via `KotatsuParserProvider`
- Page reader with zoom, drag, and navigation controls
- Android safe areas (insets) support

🚧 **In Progress / Planned:**
- Full Kotatsu parser implementation with multiple sources
- Backup & restore (local and cloud)
- MAL / AniList / Kitsu tracking integration
- Cross-device sync
- WebView-based Cloudflare/anti-bot handling
- Extension/plugin system UI

## Architecture

```
Miyo/
├── app/                    # Main Android app module
│   ├── build.gradle.kts   # Dependencies and build config
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/com/example/miyo/
│   │   │   ├── MainActivity.kt           # App entry point with theme modal
│   │   │   ├── ui/
│   │   │   │   ├── screens/             # Compose screens (Library, Reader, Downloads)
│   │   │   │   ├── components/          # Reusable UI components
│   │   │   │   └── theme/               # Theme definitions
│   │   │   └── ...
│
├── core/                   # Shared models and interfaces
│   ├── src/main/kotlin/
│   │   └── com/example/miyo/core/
│   │       ├── MangaParser.kt           # Parser interface
│   │       └── Models.kt                # Data models (AppTheme, ReaderSettings, etc.)
│
├── data/                   # Data layer (Room DB, repositories)
│   ├── build.gradle.kts
│   └── src/main/java/
│       └── com/example/miyo/data/
│           ├── db/
│           │   ├── MiyoDatabase.kt      # Room database singleton
│           │   ├── dao/                 # DAOs (MangaDao, ReadingHistoryDao, etc.)
│           │   └── entity/              # Room entities
│           ├── downloader/
│           │   └── DownloadsManager.kt  # Background download queue
│           └── repository/
│               └── MangaLibraryRepository.kt  # High-level operations
│
├── extensions/             # Parser provider (Kotatsu integration)
│   ├── build.gradle.kts
│   └── src/main/kotlin/
│       └── com/example/miyo/extensions/
│           └── KotatsuParserProvider.kt # Real parser instances
│
├── Reference/              # Kotatsu reference implementation (cloned)
│
├── gradle/
├── gradlew                 # Gradle wrapper
├── gradlew.bat
├── settings.gradle.kts     # Project configuration
├── build.gradle.kts        # Root build file
├── gradle.properties       # Gradle properties
│
├── .github/
│   └── workflows/
│       └── android.yml     # GitHub Actions CI
│
├── README.md               # This file
├── TODO.md                 # Detailed task list
└── Update.md               # Changelog
```

## Build & Run

### Prerequisites
- Android SDK 24+ (API 34 compilation target)
- Gradle 9.2+ (or use `./gradlew`)
- JDK 11+

### Build Debug APK
```bash
./gradlew assembleDebug
```

### Run Tests
```bash
./gradlew test
```

### Generate Gradle Wrapper
```bash
gradle wrapper
```

## Gradle Configuration

- **JDK Compatibility:** Java 11 with core library desugaring for Java 8+ features
- **Kotlin Version:** 1.9.22
- **Compose Version:** 2024.02.00 (BOM)
- **Android Gradle Plugin:** 8.2.2
- **Dependencies:**
  - AndroidX: Compose, Room, Lifecycle, Activity
  - OkHttp 4.11.0 for networking
  - Kotatsu Parsers (JitPack)
  - Coil 2.5.0 for image loading
  - Coroutines 1.7.3

## GitHub Actions CI

The workflow (`.github/workflows/android.yml`) builds debug APK on every push/PR. Requires SDK setup in CI environment.

## Themes (13+ Options)

- **Light:** Standard Material Light theme
- **Dark:** Standard Material Dark theme
- **AMOLED:** Pure black background for OLED screens
- **Light Blue, Dark Blue**
- **Light Green, Dark Green**
- **Light Purple, Dark Purple**
- **Light Orange, Dark Orange**
- **Light Red, Dark Red**

Theme selection modal appears on first app open and can be changed in settings.

## Data Storage

- **Database:** Room database stored in `context.getExternalFilesDir("miyo_library")`
- **Cache:** Image cache via Coil
- **Downloads:** Managed via `DownloadsManager` with status tracking (QUEUED, IN_PROGRESS, COMPLETED, FAILED)
- **Reading Progress:** Tracked per manga/chapter with resume functionality

## Reader Features

- **Reading Modes:** Page, Vertical, Webtoon
- **Direction:** Left-to-Right (LTR) and Right-to-Left (RTL)
- **Controls:** 
  - Pinch-to-zoom
  - Drag to pan
  - Double-tap to toggle zoom
  - Page navigation buttons
  - Brightness adjustment
  - Orientation lock

## Parser Integration

The `extensions` module provides `KotatsuParserProvider` which wraps the official Kotatsu parsers library:

```kotlin
val parserProvider = KotatsuParserProvider(loaderContext)
val parser = parserProvider.newParser(MangaParserSource.MANGADEX)
```

To use custom loaders:
1. Implement `MangaLoaderContext` (see `Reference/app/src/.../MangaLoaderContextImpl.kt`)
2. Pass to `KotatsuParserProvider`
3. Create parsers on demand

## Next Steps

1. **SDK & Build Setup:** Configure Android SDK path for full APK assembly
2. **Parser Testing:** Add unit tests for Kotatsu parser integration
3. **Network Handling:** Implement Cloudflare/anti-bot handling (WebView integration)
4. **Extension System:** Build UI and architecture for user-installed extensions
5. **Cloud Backup:** Integrate Firebase or custom server for data sync
6. **Testing:** Add instrumentation tests and Compose previews

## License

Miyo is inspired by Tachiyomi, Mihon, and Kotatsu. Refer to `Reference/` for Kotatsu's original license and implementation details.

---

**Status:** Alpha (Core features working, UI complete, awaiting SDK/emulator setup for full testing)

For updates and progress, see [Update.md](Update.md) and [TODO.md](TODO.md).
