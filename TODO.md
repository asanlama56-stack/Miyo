# Miyo — Implementation TODO

High-level plan to implement the Miyo Android manga reader.

1. Project scaffolding
   - Create Android app using Kotlin + Jetpack Compose
   - Use MVVM + Clean Architecture
   - Modules: `app`, `core`, `data`, `domain`, `ui`, `extensions`
   - Add Gradle Wrapper (`gradlew`) and `settings.gradle.kts` / `build.gradle.kts`

2. Dependency & build configuration
   - Add `jitpack.io` repository in `allprojects`/settings
   - Add `com.github.KotatsuApp:kotatsu-parsers` dependency (exclude `org.json` for Android)
   - Enable core library desugaring for Java 8+ (NIO support)
   - Configure proguard/R8 rules for parsers and networking

3. Sources & parsing
   - Implement extension/plugin system for multiple sources
   - Add built-in parsers for MangaDex and other popular sites
   - Implement Cloudflare anti-bot handling where needed
   - Support local imports (CBZ, ZIP, folders)

4. Library management
   - Local manga library with categories, tags, filters
   - Reading progress and status tracking
   - Backup & restore (local + optional cloud)

5. Reader features
   - Page/vertical/webtoon modes
   - RTL/LTR support, zoom, pan, gestures
   - Customizable reader settings
   - Respect Android safe areas (insets)

6. Downloads & offline
   - Background download manager and queue
   - Storage location selection (app root directory by default)

7. Updates & tracking
   - Auto-update checks, notifications, scheduled/manual refresh
   - Optional integration with MAL/AniList/Kitsu

8. Search & discovery
   - Global search across sources, advanced filters, popular/latest sections

9. Customization & themes
   - Implement 12+ theme selection modal on first-run / download
   - Light, Dark, AMOLED themes and per-source preferences

10. CI / Build / Release
    - Add `gradlew` and `gradle` configuration
    - GitHub Actions workflow: `/.github/workflows/android.yml` for build/test
    - Linting, formatting (Kotlin), and unit/instrumentation tests

11. Sync server & docs
    - Document sync server setup (Docker and systemd) in `docs/` and README
    - Add instructions to clone Kotatsu reference implementation

12. QA & polish
    - Performance: caching, smooth scrolling, battery-friendly tasks
    - Security & privacy: local-first, optional cloud opt-in

Notes:
- Start by scaffolding the Gradle/Kotlin project and CI.
- Create `Update.md` entries for every change per repo requirement.
- Use Kotatsu as a reference (clone the repo) — include as a submodule or docs link.


Next action: scaffold the Android project structure and initial Gradle files (in progress).

Progress:
- Created initial project scaffolding files: `settings.gradle.kts`, `build.gradle.kts`, `gradle.properties`.
- Created `app` module skeleton with `build.gradle.kts`, `AndroidManifest.xml`, and `MainActivity.kt`.
- Added GitHub Actions CI workflow and `.gitignore`.

Next: implement parser integration, extension system, reader components, downloads manager, and tests.

---

Immediate actions requested by user (completed/in-progress):

- Clone Kotatsu repository into `Reference` folder: DONE (see `Reference/`).
- Ensure app stores data in its own root directory by default (plan to implement in storage module).
- Add 12+ theme selection modal on first-run: PLANNED (UI flow + themes module).
- Plan and include `gradlew`, `build.gradle.kts`, and GitHub Actions YAML to avoid build errors: IN PROGRESS (scaffold added; verify builds and fix errors).
- Ensure every file change is logged into `Update.md` after each turn: ONGOING (Update.md updated).
- Respect Android safe areas (insets) across UI components: PLANNED (Compose Insets / WindowInsets APIs).
- Use Kotatsu as reference for parsers/extensions and mirror architecture where appropriate: IN PROGRESS.

If you want, I can now:
- run a local Gradle sync and attempt a debug build to surface config issues,
- or start implementing the `extensions` module and a sample Kotatsu-based parser.