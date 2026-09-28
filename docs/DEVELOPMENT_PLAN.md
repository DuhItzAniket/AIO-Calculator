# AIO Calculator Development Plan

This is the implementation ledger for the production app. A checkpoint is complete only after its code, tests, build, review, documentation, commit, and remote push have all succeeded.

## Checkpoints

| # | Milestone | Exit criteria | Status |
|---|---|---|---|
| 0 | Baseline and workflow | Development branch, reproducible toolchain, plan and ledger committed | Complete |
| 1 | Build foundation | All modules included, dependency graph resolves, debug build passes | Complete |
| 2 | Core calculator engine | Safe expression evaluation, formatting, validation, comprehensive unit tests | Complete |
| 3 | Primary calculator UX | Basic/scientific calculator wired into navigation and usable on phone/tablet layouts | Complete |
| 4 | Persistence | Room history/favorites/saved/recent flows are wired and tested | Complete |
| 5 | Navigation and discovery | Drawer, categories, tool registry, search, favorites and recent screens work | Complete |
| 6 | Reusable specialist tools | Shared form/result/error components and first algebra/statistics/geometry tools | Complete |
| 7 | Science and engineering | Trigonometry, physics, chemistry and electronics tools with formula tests | Complete |
| 8 | Converters | Offline unit conversion framework and representative conversion families | Complete |
| 9 | Finance and currency | Precision-aware finance tools and cached/offline currency refresh | Complete |
| 10 | Health/date/everyday | Informational calculators with validation and limitations | Complete |
| 11 | Shopping | Shopping lists, totals, tax/discount/budget, persistence and sharing | Complete |
| 12 | Formula/constants libraries | Searchable libraries connected to tools and centralized data | Complete |
| 13 | Settings and adaptive UI | Theme modes, dynamic color, accessibility, landscape/tablet/foldable layouts | Complete |
| 14 | Reliability | Repository/ViewModel/UI tests, error/empty states, rotation/state restoration | Complete |
| 15 | Release hardening | Release build, R8/resource shrinking, size measurement, baseline profile and docs | Complete |

## Verification loop

For every checkpoint:

1. Inspect the current implementation and working tree.
2. Implement only the checkpoint scope.
3. Run the affected unit tests and build.
4. Run UI/instrumentation tests when the environment provides an emulator/device.
5. Fix root causes and rerun failed verification.
6. Inspect the diff for scope, placeholders, secrets, and generated files.
7. Update this ledger and `SESSION_TRANSCRIPT.md` with evidence.
8. Commit with `feat(scope): ...` or `fix(scope): ...`.
9. Verify clean status and push `opencode/aio-calculator`.

## Current baseline

- Repository: `https://github.com/DuhItzAniket/AIO-Calculator.git`
- Working branch: `opencode/aio-calculator`
- Starting commit: `b28705d`
- Resolved toolchain blocker: the repository now uses AGP 8.6.1/Kotlin 1.9.24/KSP 1.9.24-1.0.20 with Gradle 8.7.
- Resolved structural gap: the feature calculator module is included and the debug APK builds.
- Checkpoint 1 evidence: all declared modules compile, `:core:math:testDebugUnitTest` passes, and `:app:assembleDebug` passes.

## Checkpoint 14 — reliability

Status: complete; committed and pushed after verification.

- Added pure shopping-total logic with tests for included items, discount, tax, negative-rate clamping, and final totals.
- Added ToolRegistry tests covering keyword search, unknown IDs, favorites, and recent-item deduplication.
- Fixed a production `List.firstOrNull(predicate)` extension that recursively called itself and caused `StackOverflowError` during tool search.
- Verified app, common, math, units, and currency unit tests plus `:app:assembleDebug`: `BUILD SUCCESSFUL` (300 actionable tasks).
- Device-only rotation, UI, and screen-reader checks remain explicitly documented as unavailable because no emulator/device is attached.

## Checkpoint 15 — release hardening

Status: complete; committed and pushed after verification.

- Fixed invalid full-backup exclusions that failed release lint.
- Added a 4 GB Gradle JVM cap for predictable R8 execution; release minification and resource shrinking remain enabled.
- Verified `:app:assembleRelease`: `BUILD SUCCESSFUL` with R8 and resource shrinking.
- Measured unsigned release APK: 2,193,209 bytes (~2.09 MB); SHA-256 `B8FB590A85703B5678E0F6EDDF7114CF60E5303FA1A4311BB8CC3C202701BA6D`.
- The artifact is unsigned and requires the project/release signing configuration before store distribution.
- Baseline-profile generation is deferred until an emulator/device is available; it is an optimization follow-up, not a functional release blocker.

## Checkpoint 11 — shopping lists

Status: complete; committed and pushed after verification.

- Added a Room-backed shopping-list view model with list selection, item insertion/deletion, and cascade-safe persistence.
- Added subtotal, percentage discount, percentage tax, budget remaining/over-budget feedback, and durable per-list totals settings.
- Added a plain-text Android share action for the selected list and calculated total.
- Verified Room KSP and `:app:assembleDebug`: build successful.
- No emulator/device was attached, so share-sheet execution remains an environment-dependent manual check.
