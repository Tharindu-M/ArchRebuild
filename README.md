## ArchRebuild

A modern Android starter project built with **Kotlin + Jetpack Compose + Hilt + Room**, designed as a clean base for new app development.

## Why this project

- **Modern stack**: Compose UI, dependency injection with Hilt, local persistence with Room
- **Architecture-first**: repository pattern, model mapping, DI modules, coroutine dispatchers
- **Test-ready**: shared test utilities and fake implementations for faster testing

## Tech stack

- **Language**: Kotlin
- **UI**: Jetpack Compose (Material 3)
- **DI**: Hilt
- **Database**: Room
- **Async**: Kotlin Coroutines + Flow
- **Build**: Gradle Kotlin DSL + Version Catalog (`libs.versions.toml`)
- **Codegen**: KSP

## Modules

- **`app`**: Main Android application module (UI, DI, data, app entry points)
- **`shared-test`**: Reusable testing utilities (fakes, test modules, rules, custom test runner)

## Project structure (high level)

- **`app/src/main/java/.../data`**: domain/data models, repository contracts + implementation, local/network mapping
- **`app/src/main/java/.../di`**: Hilt modules for repository, data sources, database, coroutine dispatchers/scope
- **`app/src/main/java/.../ui`**: Compose UI and theming
- **`app/src/test` / `app/src/androidTest`**: unit and instrumented tests
- **`shared-test/src/main/java`**: shared fake repositories/DAOs and test DI modules

## Getting started

### Prerequisites

- **Android Studio** (latest stable recommended)
- **JDK 17**
- **Android SDK** configured in your local environment

### Open and run

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle
4. Run the `app` configuration on an emulator/device

## Build & test

From project root:

- **Debug build**: `.\gradlew.bat assembleDebug`
- **Unit tests**: `.\gradlew.bat test`
- **Instrumented tests**: `.\gradlew.bat connectedAndroidTest`

> On macOS/Linux, use `./gradlew` instead of `.\gradlew.bat`.

## Using this as a starter template

When creating a new app from this base, update:

- **`settings.gradle.kts`**: `rootProject.name`
- **`app/build.gradle.kts`**: `namespace`, `applicationId`
- **Package names** under `app/src` and `shared-test/src`
- **`AndroidManifest.xml`**: app label/theme/application class if needed

Then make an initial clean commit for your new project baseline.

## Notes

- This project is currently being refactored from old package naming to `app.example.archrebuild`.
- Recommended next improvement: move shared contracts/models to a neutral core module to reduce coupling between `app` and `shared-test`.

## License

MIT, Apache-2.0
