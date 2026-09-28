# AIO Calculator - Quality Gate Checklist
## Per AIOSPEC.md §47

## BUILD
- [ ] debug build passes
- [ ] release build passes

## TESTS
- [ ] unit tests pass
- [ ] instrumentation/UI tests for critical flows pass

## UI
- [ ] light theme
- [ ] dark theme
- [ ] OLED
- [ ] dynamic color
- [ ] landscape
- [ ] large screen

## FUNCTIONALITY
- [ ] calculator works
- [ ] scientific mode works
- [ ] navigation works
- [ ] search works
- [ ] favorites work
- [ ] recent works
- [ ] history works
- [ ] saved calculations work
- [ ] shopping works
- [ ] conversions work
- [ ] finance tools work
- [ ] currency cache works
- [ ] offline mode works

## PERFORMANCE
- [ ] no obvious startup bottlenecks
- [ ] no unnecessary network requests
- [ ] no unnecessary recompositions in critical screens
- [ ] release optimization enabled

## SIZE
- [ ] release artifact size measured
- [ ] unused resources removed
- [ ] unnecessary dependencies removed

## ROBUSTNESS
- [ ] invalid input does not crash
- [ ] network failures do not crash
- [ ] database failures are handled
- [ ] screen rotation/configuration changes preserve appropriate state