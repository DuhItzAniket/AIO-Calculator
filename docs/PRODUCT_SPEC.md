# AIO Calculator product specification

## Product scope

AIO Calculator is an offline-first Android calculator application that combines everyday arithmetic with scientific, educational, finance, health, date, conversion, and shopping utilities in one searchable app.

## Engineering decisions

- Kotlin, Jetpack Compose, Material 3, Android API 26+.
- Gradle 8.7, Android Gradle Plugin 8.6.1, Kotlin 1.9.24, KSP 1.9.24-1.0.20.
- Room owns durable calculation data; Preferences DataStore owns user settings and recent-tool identifiers.
- `core:math` is deterministic and side-effect free; UI and persistence are layered around it.
- Checkpoint evidence is recorded in [`DEVELOPMENT_PLAN.md`](DEVELOPMENT_PLAN.md) and [`SESSION_TRANSCRIPT.md`](SESSION_TRANSCRIPT.md).

## Current verified scope

- The project imports all declared modules and the app dependency graph resolves.
- The debug APK builds successfully with the Android Studio JBR and the pinned wrapper.
- The basic and scientific calculator surfaces evaluate expressions through the shared engine.
- The complete first-release scope is implemented on the `opencode/aio-calculator` branch.
- The debug APK and minified, resource-shrunk release APK both build successfully.
- Device-only UI, rotation, screen-reader, and share-sheet checks require an attached emulator or physical device.

## Non-negotiable quality gates

- No generated build output or local SDK paths are committed.
- No placeholder action is presented as a completed product feature.
- Build/test evidence is recorded in [`SESSION_TRANSCRIPT.md`](SESSION_TRANSCRIPT.md) for every pushed checkpoint.
- Release artifacts are unsigned until a private signing configuration is supplied by the release owner.
