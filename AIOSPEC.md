# AIO Calculator implementation specification

## Product scope

AIO Calculator is an offline-first Android calculator application. The first release is built around a dependable expression engine, basic and scientific calculator screens, persistent history/favorites/saved calculations, discoverable specialist tools, converters, finance/date/health utilities, shopping lists, formula/constants libraries, accessibility, and adaptive layouts.

## Engineering decisions

- Kotlin, Jetpack Compose, Material 3, Android API 26+.
- Gradle 8.7, Android Gradle Plugin 8.6.1, Kotlin 1.9.24, KSP 1.9.24-1.0.20.
- Room owns durable calculation data; Preferences DataStore owns user settings and recent-tool identifiers.
- `core:math` is deterministic and side-effect free; UI and persistence are layered around it.
- Each checkpoint must pass the affected tests/build, have documented evidence, and be pushed to `opencode/aio-calculator` before the next checkpoint begins.

## Current verified scope

- The project imports all declared modules and the app dependency graph resolves.
- The debug APK builds successfully with the Android Studio JBR and the pinned wrapper.
- The basic and scientific calculator surfaces evaluate expressions through the shared engine.
- The remaining specialist tools and production navigation/persistence flows are intentionally tracked as later checkpoints in `DEVELOPMENT_PLAN.md`.

## Non-negotiable quality gates

- No generated build output or local SDK paths are committed.
- No placeholder action is presented as a completed product feature.
- Build/test evidence is recorded in `SESSION_TRANSCRIPT.md` for every pushed checkpoint.
