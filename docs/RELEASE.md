# Release checklist

## Verified

- [x] Debug APK assembles.
- [x] JVM unit tests pass for app, common, math, units, and currency modules.
- [x] Release lint passes.
- [x] R8 minification passes.
- [x] Resource shrinking passes.
- [x] Release APK size measured.
- [x] Backup configuration validated.
- [x] No generated build output or crash dump is tracked.

## Artifact

The local release artifact is:

```text
app/build/outputs/apk/release/app-release-unsigned.apk
```

The verified artifact was 2,193,209 bytes with SHA-256:

```text
B8FB590A85703B5678E0F6EDDF7114CF60E5303FA1A4311BB8CC3C202701BA6D
```

It is unsigned. Configure a private `signingConfig` for distribution; never commit the keystore or passwords.

## Device-dependent follow-up

An emulator or physical device is still required for runtime checks covering navigation, rotation/state restoration, screen readers, dynamic color, landscape/tablet layouts, and the shopping share sheet. Baseline-profile generation is also deferred until a device is available.
