# Miyo
Instructions --

‎
‎
‎> Project Title: Miyo
‎
‎Objective:
‎Build a full-featured Kotlin-based Android manga reader and downloader app named Miyo, inspired by Tachiyomi, Mihon, and Kotatsu. The app must be modern, fast, offline-friendly, and extensible.
‎
‎Core Requirements:
‎
‎Written in Kotlin using modern Android architecture
‎
‎Follows MVVM / Clean Architecture
‎
‎Uses Jetpack Compose for UI
‎
‎Modular and scalable codebase
‎
‎
‎Features (Must Match or Exceed Tachiyomi, Mihon, and Kotatsu):
‎
‎Sources & Parsing
‎
‎Multiple online manga sources (extensions or built-in parsers)
‎
‎Support for manga, manhwa, and manhua
‎
‎Cloudflare / anti-bot handling where applicable
‎
‎Local manga import (CBZ, ZIP, folders)
‎
‎
‎Library Management
‎
‎Personal manga library
‎
‎Categories, tags, and filters
‎
‎Reading progress tracking
‎
‎Status tracking (reading, completed, on-hold, dropped)
‎
‎
‎Reader Features
‎
‎Page, vertical, and webtoon reading modes
‎
‎Left-to-right and right-to-left support
‎
‎Zoom, pan, and double-tap controls
‎
‎Customizable reader settings (brightness, orientation, gestures)
‎
‎
‎Download & Offline
‎
‎Background downloads
‎
‎Download queue management
‎
‎Offline reading
‎
‎Storage location selection
‎
‎
‎Updates & Tracking
‎
‎Automatic manga updates
‎
‎New chapter notifications
‎
‎Manual and scheduled refresh
‎
‎
‎Search & Discovery
‎
‎Global search across sources
‎
‎Advanced filters (genre, author, status, rating)
‎
‎Popular, latest, and recommended sections
‎
‎
‎Customization & Settings
‎
‎Light, dark, and AMOLED themes
‎
‎Per-source and per-manga settings
‎
‎Backup and restore (local and cloud)
‎
‎
‎Performance & UX
‎
‎Smooth scrolling and fast page loading
‎
‎Efficient image caching
‎
‎Battery-friendly background tasks
‎
‎
‎Security & Privacy
‎
‎No forced accounts
‎
‎All data stored locally unless user enables cloud backup
‎
‎
‎Extras (Optional but Preferred):
‎
‎Extension/plugin system like Tachiyomi
‎
‎MAL / AniList / Kitsu tracking integration
‎
‎Cross-device sync
‎
‎Tablet-optimized UI
‎
‎
‎Goal:
‎Miyo should feel like a next-generation successor to Tachiyomi, Mihon, and Kotatsu—combining their best features with a clean, modern Kotlin codebase and polished user experience
‎
‎app should use its own root directory for storing data
‎
‎
‎Implementation here
‎>
‎
‎
‎
‎allprojects {
‎    repositories {
‎        ...
‎        maven { url 'https://jitpack.io' }  
‎    }
‎}
‎Add the dependency
‎
‎For Java/Kotlin project:
‎
‎
‎dependencies {
‎    implementation("com.github.KotatsuApp:kotatsu-parsers:$parsers_version")
‎}
‎For Android project:
‎
‎
‎dependencies {
‎    implementation("com.github.KotatsuApp:kotatsu-parsers:$parsers_version") {
‎        exclude group: 'org.json', module: 'json'
‎    }
‎}
‎Versions are available on JitPack
‎
‎When used in Android projects, core library desugaring with the NIO specification should be enabled to support Java 8+ features.
‎
‎Usage in code
‎
‎
‎val parser = mangaLoaderContext.newParserInstance(MangaParserSource.MANGADEX)
‎mangaLoaderContext is an implementation of the MangaLoaderContext class.
‎
‎See examples of Android and Non-Android implementation.
‎
‎
‎Synchronization server
‎Instructions for installing the synchronization server.
‎
‎Installation
‎Docker
‎Build image container:
‎
‎
‎docker build github.com/KotatsuApp/kotatsu-syncserver.git -t kotatsuapp/syncserver
‎Run container:
‎
‎
‎docker run -d -p 8081:8080 \
‎-e DATABASE_HOST=your_db_host \
‎-e DATABASE_USER=your_db_user \
‎-e DATABASE_PASSWORD=your_db_password \
‎-e DATABASE_NAME=your_db_name \
‎-e DATABASE_PORT=your_db_port \
‎-e JWT_SECRET=your_secret \
‎--restart always \
‎--name kotatsu-sync kotatsuapp/syncserver
‎Systemd
‎Requirements:
‎
‎JDK 11+
‎Gradle 7.0+
‎Commands:
‎
‎
‎git clone https://github.com/KotatsuApp/kotatsu-syncserver.git
‎cd kotatsu-syncserver && ./gradlew shadowJar
‎Then edit file kotatsu-sync.service, change replaceme fields with your values and specify the kotatsu-syncserver-0.0.1.jar file location (it can be found in build/libs directory after buliding)
‎
‎
‎cp kotatsu-sync.service /etc/systemd/system
‎systemctl enable kotatsu-sync
‎systemctl daemon-reload
‎systemctl start kotatsu-sync
‎
‎------
‎
‎Also don't forget to Clone the Kotatsu app using
‎
 git clone https://github.com/KotatsuApp/Kotatsu.git
 ‎
 ‎ and see how they implement their system, what they have. like use it as a reference, this is a must! structure should be well planned, create a Todo list md file too. 
 ‎
 ‎Make sure the app has all of Kotatsu's Capabilities, Features. should have Smooth integration. also don't forget to add 12+ Theme Selection Modal upon downloading and opening the app.
 ‎
 ‎as well as don't forget to Properly plan the Build Gradle, Gradlew and YAML.
 ‎
 ‎for every turn you must make a notes on what had been changed on a file called Update md
 ‎
 ‎so you can properly plan the Gradle, Gradlew and YAML for GitHub Cli, and more. there should be no build errors and anything, you should respect android safe areas. Use proper Kotlin Tools and Online libraries if needed