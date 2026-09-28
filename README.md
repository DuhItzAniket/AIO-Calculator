# AIO Calculator

An offline-first Android calculator and everyday calculation toolkit built with Kotlin, Jetpack Compose, Material 3, Room, and Preferences DataStore.

## What it includes

- Basic and scientific expression evaluation with angle modes and memory operations.
- Persistent history, saved calculations, favorites, and recent tools.
- Searchable specialist tools for percentages, statistics, geometry, trigonometry, physics, chemistry, electronics, finance, health, and dates.
- Offline unit conversion and currency conversion with Room-backed cached rates.
- Formula and scientific-constants libraries.
- Shopping lists with item quantities, tax, discounts, budgets, persistence, and text sharing.
- Light, dark, OLED, system, and Android dynamic-color themes.
- Adaptive calculator layout for phones and wide screens.

## Project structure

```text
app/                       Application shell, navigation, screens, and app view models
core/common/               Shared models, registry, constants, and utilities
core/math/                 Deterministic calculator engine and formula logic
core/units/                Offline unit conversion
core/currency/             Currency conversion and rate-provider integration
core/database/             Room database, entities, and DAOs
core/datastore/            Preferences DataStore settings repository
core/design/               Material theme, colors, typography, and shapes
core/formula/              Formula library data
core/navigation/           Shared route definitions
feature/calculator/        Basic/scientific calculator Compose UI
docs/                      Product specification, plan, release evidence, and transcript
```

## Requirements

- Android Studio with the bundled Android Studio JBR (Java 17).
- Android SDK 36.
- An Android device or emulator running API 26 or newer for manual testing.

The Gradle wrapper pins Gradle 8.7. The project currently uses AGP 8.6.1 and Kotlin 1.9.24.

## Build and verify

From the repository root:

```powershell
./gradlew.bat --no-daemon --max-workers=1 --console=plain :app:assembleDebug
```

Run the JVM test suites:

```powershell
./gradlew.bat --no-daemon --max-workers=1 --console=plain `
  :app:testDebugUnitTest `
  :core:common:testDebugUnitTest `
  :core:math:testDebugUnitTest `
  :core:units:testDebugUnitTest `
  :core:currency:testDebugUnitTest
```

Build the optimized release variant:

```powershell
./gradlew.bat --no-daemon --max-workers=1 --console=plain :app:assembleRelease
```

The release build enables R8 minification and resource shrinking. The generated APK is unsigned and is not suitable for store distribution until a private signing configuration is supplied.

## Documentation

- [Product specification](docs/PRODUCT_SPEC.md)
- [Development plan and checkpoint ledger](docs/DEVELOPMENT_PLAN.md)
- [Architecture guide](docs/ARCHITECTURE.md)
- [Release checklist and verification](docs/RELEASE.md)
- [Verification transcript](docs/SESSION_TRANSCRIPT.md)
- [Contributing guide](CONTRIBUTING.md)
- [Changelog](CHANGELOG.md)

## Current verification notes

The debug and release builds pass, including Room KSP, unit tests, lint, R8, and resource shrinking. Runtime UI, rotation, accessibility, and share-sheet checks still need to be exercised on an attached emulator or physical device. Baseline-profile generation is intentionally deferred until a device is available.

## License

No license has been declared for this repository yet. Add the project owner’s chosen license before distributing the source or publishing the application.
