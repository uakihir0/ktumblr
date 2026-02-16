# Requirements: ktumblr Infrastructure Renewal

## Background

ktumblr is a Tumblr client library for Kotlin Multiplatform. Its build infrastructure has become outdated compared to the sibling projects (kmisskey, kbsky, kmastodon, kslack), which have already been modernized to a unified pattern. This renewal aligns ktumblr with those projects.

## Goals

1. **Modernize the Gradle build system** to match the conventions used by kmisskey, kbsky, kmastodon, and kslack
2. **Upgrade all dependencies** to their latest stable versions
3. **Introduce convention plugins** for consistent publishing and build configuration
4. **Update CI/CD pipelines** to follow the unified workflow pattern
5. **Create AGENTS.md** documentation for AI-assisted development
6. **Fix existing bugs** in README.md (incorrect Mastodon references)

## Functional Requirements

### FR-1: Dependency Upgrades

| Dependency | Current | Target |
|------------|---------|--------|
| Kotlin | 2.0.20 | 2.3.10 |
| Gradle Wrapper | 8.5 | 9.3.1 |
| Ktor Client | 2.3.12 | 3.4.0 |
| kotlinx-coroutines | 1.9.0 | 1.10.2 |
| kotlinx-serialization | 1.7.3 | 1.10.0 |
| kotlinx-datetime | 0.6.1 | 0.7.1-0.6.x-compat |
| khttpclient | 0.0.3-SNAPSHOT | 0.0.8 |

### FR-2: Build System Modernization

- Convert `settings.gradle` (Groovy) to `settings.gradle.kts` (Kotlin DSL)
- Introduce `plugins/` directory with convention plugins (`root.publications`, `module.publications`)
- Add git-versioning plugin for semantic versioning from git tags
- Add Dokka 2.1.0 for API documentation generation
- Add Vanniktech maven-publish plugin for Maven Central publishing support
- Add Swift Package Manager support via multiplatform-swiftpackage plugin
- Add foojay-resolver-convention for JVM toolchain auto-provisioning
- Use `HostManager.hostIsMac` to conditionally compile native targets (macOS only)
- Add `-Xenable-suspend-function-exporting` compiler option for JS interop

### FR-3: Module Build Configuration

- `core/build.gradle.kts`: Replace inline publishing with `module.publications` convention plugin
- `all/build.gradle.kts`: Add Swift Package support, fix XCFramework task dependencies, modernize JS TypeScript generation API
- Remove `tool/setup_js.sh` and `tool/setup_pods.sh` (functionality moved to CI directly)

### FR-4: CI/CD Pipeline Updates

- Rename `publish.yml` to `snapshot-publish.yml` and simplify (remove matrix strategy, use `publishAllPublicationsToMavenRepository`)
- Update `js.yml` to use `productionLibrary` distribution and remove `--refresh-dependencies`
- Update `pods.yml` to remove `--refresh-dependencies` and use `-x check`
- Add new `swiftpackage.yml` workflow for Swift Package Manager distribution

### FR-5: Documentation

- Create `AGENTS.md` with project overview, API concepts, architecture, directory structure, testing instructions, implementation guidelines, naming conventions, and key file references
- Fix `README.md`: correct Mastodon references to Tumblr, fix Maven metadata URL

### FR-6: Gradle Properties

- Add parallel build, daemon, incremental compilation settings
- Add Dokka V2 mode configuration
- Add Maven Central publishing properties
- Add Java toolchain auto-provisioning settings

## Non-Functional Requirements

### NFR-1: Compatibility

- All existing source code must compile without errors after the upgrade
- Ktor 2.x to 3.x migration may require source code adjustments (khttpclient 0.0.8 likely absorbs these changes)
- Existing test suite must pass

### NFR-2: Consistency

- Build configuration patterns must match kslack/kmisskey/kbsky/kmastodon exactly
- File naming, plugin usage, and CI workflow structure must be uniform across all sibling projects

### NFR-3: Platform Support

- Maintain all existing platform targets: JVM, JS (IR), iOS (x64, arm64, simulatorArm64), macOS (x64, arm64)
- Native targets must be conditionally compiled (macOS host only)

## Acceptance Criteria

1. `./gradlew build -x check` completes successfully
2. `./gradlew :core:jvmTest` passes (network-independent tests)
3. `./gradlew version --no-daemon --console=plain -q` outputs the project version
4. All Gradle configuration files follow the kslack/reference project patterns
5. `AGENTS.md` exists and contains all required sections
6. `README.md` no longer contains Mastodon references
7. `plugins/` directory exists with 4 convention plugin files
8. CI workflows are updated and include `swiftpackage.yml`
