# AIO Calculator Development Plan

This is the implementation ledger for the production app. A checkpoint is complete only after its code, tests, build, review, documentation, commit, and remote push have all succeeded.

## Checkpoints

| # | Milestone | Exit criteria | Status |
|---|---|---|---|
| 0 | Baseline and workflow | Development branch, reproducible toolchain, plan and ledger committed | Complete |
| 1 | Build foundation | All modules included, dependency graph resolves, debug build passes | Complete |
| 2 | Core calculator engine | Safe expression evaluation, formatting, validation, comprehensive unit tests | In progress |
| 3 | Primary calculator UX | Basic/scientific calculator wired into navigation and usable on phone/tablet layouts | Not started |
| 4 | Persistence | Room history/favorites/saved/recent flows are wired and tested | Not started |
| 5 | Navigation and discovery | Drawer, categories, tool registry, search, favorites and recent screens work | Not started |
| 6 | Reusable specialist tools | Shared form/result/error components and first algebra/statistics/geometry tools | Not started |
| 7 | Science and engineering | Trigonometry, physics, chemistry and electronics tools with formula tests | Not started |
| 8 | Converters | Offline unit conversion framework and representative conversion families | Not started |
| 9 | Finance and currency | Precision-aware finance tools and cached/offline currency refresh | Not started |
| 10 | Health/date/everyday | Informational calculators with validation and limitations | Not started |
| 11 | Shopping | Shopping lists, totals, tax/discount/budget, persistence and sharing | Not started |
| 12 | Formula/constants libraries | Searchable libraries connected to tools and centralized data | Not started |
| 13 | Settings and adaptive UI | Theme modes, dynamic color, accessibility, landscape/tablet/foldable layouts | Not started |
| 14 | Reliability | Repository/ViewModel/UI tests, error/empty states, rotation/state restoration | Not started |
| 15 | Release hardening | Release build, R8/resource shrinking, size measurement, baseline profile and docs | Not started |

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
