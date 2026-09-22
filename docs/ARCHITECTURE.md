# Architecture

## Application layers

The app is split into a small application shell, reusable core libraries, and the calculator feature module.

- `app` owns the Compose navigation shell, app-scoped view models, settings screen, shopping UI, and tool routing.
- `feature:calculator` owns the basic/scientific calculator surfaces and their local interaction state.
- `core:math` contains deterministic expression evaluation and specialist formulas. It has no Android UI or persistence responsibilities.
- `core:database` contains Room entities and DAOs for history, favorites, saved calculations, shopping lists/items, and cached currency rates.
- `core:datastore` contains the Preferences DataStore repository for theme, angle mode, precision, currency, auto-refresh, and recent-tool settings.
- `core:currency` provides offline conversion behavior and the network rate provider/cache policy.
- `core:units`, `core:formula`, and `core:common` provide reusable domain data and utilities.
- `core:design` provides the Material 3 theme and calculator color schemes.

## Data flow

```text
Compose screen
    -> app/feature ViewModel
        -> Room DAO or DataStore repository
            -> StateFlow
                -> Compose UI
```

Calculations are evaluated locally. Currency rates may be refreshed from the configured provider, but the last valid Room cache is used when the network is unavailable or the cache is still fresh.

## Persistence boundaries

Room is used for user-created and durable relational data. Preferences DataStore is used for small key/value preferences. Generated databases, Gradle output, IDE metadata, local SDK paths, and crash dumps are excluded from version control.

## Release behavior

The release build enables R8 and resource shrinking. See [RELEASE.md](RELEASE.md) for the verified build commands, artifact details, signing status, and device-test limitations.
