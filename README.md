## ArchRebuild

A modern Android starter project built with **Kotlin**, **Jetpack Compose**, **Hilt**, and **Room**.

## Why this project

- **Modern Android stack**: Compose UI, DI with Hilt, local persistence with Room
- **Architecture-focused**: repository pattern, model mapping, DI modules, coroutine dispatchers
- **Test-friendly**: shared test utilities and fake implementations for faster, reliable tests

## Tech stack

- **Language**: Kotlin
- **UI**: Jetpack Compose (Material 3)
- **DI**: Hilt
- **Database**: Room
- **Async**: Kotlin Coroutines + Flow
- **Build**: Gradle Kotlin DSL + Version Catalog (`libs.versions.toml`)
- **Codegen**: KSP

## Modules

- **`app`**: Main Android app module (UI, DI, data, app entry points)
- **`shared-test`**: Reusable testing utilities (fakes, test modules, rules, custom test runner)

## Project structure (high level)

- **`app/src/main/java/.../data`**: data/domain models, repositories, mapping
- **`app/src/main/java/.../di`**: Hilt modules for data, database, and coroutine dispatchers/scope
- **`app/src/main/java/.../ui`**: Compose UI and theme
- **`app/src/test` / `app/src/androidTest`**: unit and instrumented tests
- **`shared-test/src/main/java`**: shared fakes and test DI modules

## Getting started

### Prerequisites

- **Android Studio** (latest stable recommended)
- **JDK 17**
- **Android SDK** installed and configured

### Run the app

1. Clone the repository
2. Open it in Android Studio
3. Sync Gradle
4. Run the `app` configuration on an emulator or device

## Build & test

From project root:

- **Debug build**: `./gradlew assembleDebug` (Windows: `gradlew.bat assembleDebug`)
- **Unit tests**: `./gradlew test` (Windows: `gradlew.bat test`)
- **Instrumented tests**: `./gradlew connectedAndroidTest` (Windows: `gradlew.bat connectedAndroidTest`)

## Use as a starter template

When starting a new app from this project, update:

- **`settings.gradle.kts`**: `rootProject.name`
- **`app/build.gradle.kts`**: `namespace`, `applicationId`
- **Package names** under `app/src` and `shared-test/src`
- **`AndroidManifest.xml`**: app label, theme, and application class (if needed)

Then make an initial clean commit as your baseline.

## Notes

- The codebase is being refactored to the `app.example.archrebuild` package naming.
- A useful next step is moving shared contracts/models into a neutral core module to reduce coupling.

## License

MIT
