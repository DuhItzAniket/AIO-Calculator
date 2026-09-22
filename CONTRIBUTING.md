# Contributing

Thanks for helping improve AIO Calculator.

## Development workflow

1. Create a focused branch from the current development branch.
2. Keep changes scoped to one feature or bug fix.
3. Add or update unit tests for deterministic logic.
4. Run the affected tests and a debug build before opening a pull request.
5. Update the relevant documentation and changelog entry.

## Commit messages

Use a short conventional prefix:

```text
feat(scope): add a capability
fix(scope): correct a defect
test(scope): improve coverage
docs(scope): update documentation
chore(scope): maintain tooling or release configuration
```

## Pull requests

Please include:

- What changed and why.
- Verification commands and their results.
- Screenshots or a short recording for meaningful UI changes.
- Any known device, emulator, network, or signing limitations.

Avoid committing generated build output, local SDK paths, secrets, signing keys, or personal IDE state.
