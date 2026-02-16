# Design: ktumblr Infrastructure Renewal

## Overview

This design document describes the implementation approach for modernizing the ktumblr build infrastructure. The target state mirrors the patterns established in the sibling projects (kslack, kmisskey, kbsky, kmastodon).

## Current vs Target State

```
Current (ktumblr)                    Target (aligned with kslack)
─────────────────                    ────────────────────────────
settings.gradle (Groovy)      →     settings.gradle.kts (Kotlin DSL)
No convention plugins          →     plugins/ with root + module publications
Gradle 8.5                     →     Gradle 9.3.1
Kotlin 2.0.20                  →     Kotlin 2.3.10
Ktor 2.3.12                    →     Ktor 3.4.0
Inline maven-publish           →     Vanniktech maven-publish + Dokka + Signing
No git-versioning              →     me.qoomon.git-versioning
No Swift Package support       →     multiplatform-swiftpackage
tool/setup_js.sh + setup_pods.sh →  Removed (CI handles directly)
No AGENTS.md                   →     AGENTS.md with full documentation
```

## File Change Summary

### New Files (7)

| File | Purpose |
|------|---------|
| `plugins/build.gradle.kts` | Convention plugin build config |
| `plugins/settings.gradle.kts` | Convention plugin settings with version catalog |
| `plugins/src/main/kotlin/root.publications.gradle.kts` | Sonatype Nexus publishing |
| `plugins/src/main/kotlin/module.publications.gradle.kts` | Maven publish + Dokka + Signing |
| `settings.gradle.kts` | Kotlin DSL settings (replaces `settings.gradle`) |
| `.github/workflows/swiftpackage.yml` | Swift Package CI workflow |
| `AGENTS.md` | Agent documentation |

### Modified Files (9)

| File | Changes |
|------|---------|
| `build.gradle.kts` | Full rewrite to kslack pattern |
| `core/build.gradle.kts` | Module publications, HostManager, compiler options |
| `all/build.gradle.kts` | Swift Package, XCFramework fixes, modern JS API |
| `gradle/libs.versions.toml` | All versions upgraded, new entries added |
| `gradle.properties` | Extended with parallel, dokka, maven central settings |
| `gradle/wrapper/gradle-wrapper.properties` | Gradle 8.5 → 9.3.1 |
| `.github/workflows/snapshot-publish.yml` | Renamed from publish.yml, simplified |
| `.github/workflows/js.yml` | Production library, remove refresh-deps |
| `.github/workflows/pods.yml` | Remove refresh-deps, use -x check |
| `Makefile` | Updated flags (-x check instead of -x test) |
| `README.md` | Fix Mastodon → Tumblr references |

### Deleted Files (3)

| File | Reason |
|------|--------|
| `settings.gradle` | Replaced by `settings.gradle.kts` |
| `tool/setup_js.sh` | No longer needed (CI handles directly) |
| `tool/setup_pods.sh` | No longer needed (CI handles directly) |

---

## Detailed Design

### 1. Convention Plugins (`plugins/`)

Follows the composite build pattern used by all reference projects.

**`plugins/build.gradle.kts`:**
```kotlin
plugins {
    `kotlin-dsl`
}
dependencies {
    implementation(libs.nexus.publish)
    implementation(libs.maven.publish)
    implementation(libs.dokka)
}
```

**`plugins/settings.gradle.kts`:**
```kotlin
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}
```

**`plugins/src/main/kotlin/root.publications.gradle.kts`:**
```kotlin
plugins {
    id("io.github.gradle-nexus.publish-plugin")
}
nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}
```

**`plugins/src/main/kotlin/module.publications.gradle.kts`:**
```kotlin
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinMultiplatform

plugins {
    id("maven-publish")
    id("signing")
    id("org.jetbrains.dokka")
    id("org.jetbrains.dokka-javadoc")
    id("com.vanniktech.maven.publish")
}

publishing {
    repositories {
        maven {
            url = uri("https://repo.repsy.io/mvn/uakihir0/public")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("PASSWORD")
            }
        }
    }
    publications.withType<MavenPublication> {
        pom {
            name.set("ktumblr")
            description.set("Kotlin multiplatform Tumblr client library.")
            url.set("https://github.com/uakihir0/ktumblr")
            licenses {
                license {
                    name.set("MIT License")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("uakihir0")
                    name.set("URUSHIHARA Akihiro")
                    email.set("a.urusihara@gmail.com")
                }
            }
            scm {
                url.set("https://github.com/uakihir0/ktumblr")
            }
        }
    }
}

mavenPublishing {
    configure(
        KotlinMultiplatform(
            javadocJar = JavadocJar.Dokka("dokkaGeneratePublicationHtml")
        )
    )
    if (project.hasProperty("mavenCentralUsername") ||
        System.getenv("ORG_GRADLE_PROJECT_mavenCentralUsername") != null
    ) signAllPublications()
}

signing {
    if (project.hasProperty("mavenCentralUsername") ||
        System.getenv("ORG_GRADLE_PROJECT_mavenCentralUsername") != null
    ) useGpgCmd()
}
```

### 2. Root Build Configuration

**`build.gradle.kts`:**
```kotlin
plugins {
    id("root.publications")
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.kotlin.serialization).apply(false)
    alias(libs.plugins.kotlin.cocoapods).apply(false)
    alias(libs.plugins.dokka).apply(false)
    alias(libs.plugins.maven.publish).apply(false)
    alias(libs.plugins.git.versioning)
}

allprojects {
    group = "work.socialhub.ktumblr"
    version = "0.0.1-SNAPSHOT"
    repositories {
        mavenCentral()
        maven { url = uri("https://repo.repsy.io/mvn/uakihir0/public") }
    }
}

gitVersioning.apply {
    refs {
        considerTagsOnBranches = true
        tag("v(?<version>.*)") {
            version = "\${ref.version}"
        }
    }
}

tasks.wrapper {
    gradleVersion = "8.14.3"
    distributionType = Wrapper.DistributionType.ALL
}
```

### 3. Core Module Build Configuration

**`core/build.gradle.kts`:**
```kotlin
import org.gradle.api.tasks.compile.JavaCompile
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.konan.target.HostManager

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    id("module.publications")
}

kotlin {
    jvm {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    js(IR) {
        nodejs()
        browser()
    }

    if (HostManager.hostIsMac) {
        iosX64()
        iosArm64()
        iosSimulatorArm64()
        macosX64()
        macosArm64()
    }

    compilerOptions {
        freeCompilerArgs.add("-Xenable-suspend-function-exporting")
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.js.ExperimentalJsExport")
            }
        }
        commonMain.dependencies {
            implementation(libs.ktor.core)
            implementation(libs.kmpcommon)
            implementation(libs.khttpclient)
            implementation(libs.datetime)
            implementation(libs.serialization.json)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.coroutines.test)
        }
        jvmTest.dependencies {
            implementation(libs.slf4j.simple)
        }
    }
}

tasks.named<Test>("jvmTest") {
    useJUnitPlatform()
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(11)
}
```

### 4. All Module Build Configuration

**`all/build.gradle.kts`:**
```kotlin
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.cocoapods)
    alias(libs.plugins.swiftpackage)
    id("module.publications")
}

kotlin {
    jvmToolchain(11)
    jvm()

    js(IR) {
        nodejs()
        browser()
        binaries.library()
        compilerOptions {
            generateTypeScriptDefinitions()
        }
    }

    val xcf = XCFramework("ktumblr")
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
        macosX64(),
        macosArm64(),
    ).forEach {
        it.binaries.framework {
            export(project(":core"))
            baseName = "ktumblr"
            xcf.add(this)
        }
    }

    cocoapods {
        name = "ktumblr"
        version = "0.0.1"
        summary = "ktumblr is Tumblr library for Kotlin Multiplatform."
        homepage = "https://github.com/uakihir0/ktumblr"
        authors = "Akihiro Urushihara"
        license = "MIT"
        framework { baseName = "ktumblr" }
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.js.ExperimentalJsExport")
            }
        }
        commonMain.dependencies {
            api(project(":core"))
        }
    }
}

multiplatformSwiftPackage {
    swiftToolsVersion("5.7")
    targetPlatforms {
        iOS { v("15") }
        macOS { v("12.0") }
    }
}

tasks.configureEach {
    if (name.contains("assembleKtumblr") && name.contains("XCFramework")) {
        mustRunAfter(tasks.matching { it.name.contains("FatFramework") })
    }
}

tasks.podPublishXCFramework {
    doLast {
        providers.exec {
            executable = "sh"
            args = listOf(project.projectDir.path + "/../tool/rename_podfile.sh")
        }.standardOutput.asText.get()
    }
}
```

### 5. Version Catalog

**`gradle/libs.versions.toml`:**
```toml
[versions]
kotlin = "2.3.10"
dokka = "2.1.0"
maven-publish = "0.36.0"
serialization = "1.10.0"
coroutines = "1.10.2"

[libraries]
kmpcommon = "work.socialhub:kmpcommon:0.0.1-SNAPSHOT"
khttpclient = "work.socialhub:khttpclient:0.0.8"
ktor-core = "io.ktor:ktor-client-core:3.4.0"
datetime = "org.jetbrains.kotlinx:kotlinx-datetime:0.7.1-0.6.x-compat"
coroutines-core = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-core", version.ref = "coroutines" }
coroutines-test = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-test", version.ref = "coroutines" }
serialization-json = { module = "org.jetbrains.kotlinx:kotlinx-serialization-json", version.ref = "serialization" }
nexus-publish = "io.github.gradle-nexus.publish-plugin:io.github.gradle-nexus.publish-plugin.gradle.plugin:2.0.0"
maven-publish = { module = "com.vanniktech:gradle-maven-publish-plugin", version.ref = "maven-publish" }
dokka = { module = "org.jetbrains.dokka:dokka-gradle-plugin", version.ref = "dokka" }
slf4j-simple = "org.slf4j:slf4j-simple:2.0.17"

[plugins]
kotlin-multiplatform = { id = "org.jetbrains.kotlin.multiplatform", version.ref = "kotlin" }
kotlin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }
kotlin-cocoapods = { id = "org.jetbrains.kotlin.native.cocoapods", version.ref = "kotlin" }
maven-publish = { id = "com.vanniktech.maven.publish", version.ref = "maven-publish" }
dokka = { id = "org.jetbrains.dokka", version.ref = "dokka" }
swiftpackage = "io.github.luca992.multiplatform-swiftpackage:2.3.0"
git-versioning = "me.qoomon.git-versioning:6.4.4"
```

### 6. CI/CD Workflows

**`.github/workflows/snapshot-publish.yml`** (renamed from `publish.yml`):
- Trigger: push to main
- Build: `build -x check`
- Publish: `publishAllPublicationsToMavenRepository -x check`

**`.github/workflows/js.yml`** (updated):
- Change to `productionLibrary` distribution
- Remove `--refresh-dependencies`

**`.github/workflows/pods.yml`** (updated):
- Remove `--refresh-dependencies`
- Use `-x check` consistently

**`.github/workflows/swiftpackage.yml`** (new):
- Build Swift Package via `all:createSwiftPackage`
- Push to `ktumblr-spm` repository on main branch

### 7. AGENTS.md Structure

```
# Agent Documentation

## Overview
- Tumblr client library for Kotlin Multiplatform
- Uses khttpclient (Ktor Client internally)

## Key Concepts
- Tumblr REST API structure (GET/POST to api.tumblr.com/v2/)
- OAuth2 authentication flow
- Post types (legacy: text, photo, video, audio, link, quote, answer, chat)

## Architecture
- Resource-based API design (@JsExport interfaces)
- Factory pattern (TumblrFactory)
- AbstractResourceImpl base class
- Platform support (BlockingUtil if applicable)

## Directory Structure
- core/ - Main REST API client
- all/ - Aggregated module for platform distribution
- plugins/ - Gradle convention plugins

## Testing
- ./gradlew :core:jvmTest

## Implementation Guidelines
- Steps to add a new API endpoint
- Request/Response class organization

## Naming Conventions
| Type | Pattern | Example |
|------|---------|---------|
| Request | {Action}Request | CreatePostRequest |
| Response | {Action}Response | CreatePostResponse |
| Resource | {Category}Resource | BlogResource |
| Impl | {Category}ResourceImpl | BlogResourceImpl |
| Entity | Singular form | Post, Blog |

## Serialization
- kotlinx.serialization with @Serializable
- Custom PostSerializer for polymorphic post types

## Key File References
(table of key files with paths)
```

---

## Execution Order

1. Create `plugins/` directory (4 files)
2. Delete `settings.gradle`, create `settings.gradle.kts`
3. Update `gradle/libs.versions.toml`
4. Update `gradle.properties`
5. Update `gradle/wrapper/gradle-wrapper.properties` (Gradle 9.3.1)
6. Rewrite root `build.gradle.kts`
7. Update `core/build.gradle.kts`
8. Update `all/build.gradle.kts`
9. Clean up `tool/` directory
10. Update `Makefile`
11. Update CI workflows (rename + modify 3, add 1)
12. Run build verification (`./gradlew build -x check`)
13. Fix any compilation errors in source code
14. Fix `README.md`
15. Create `AGENTS.md`

## Verification

```shell
# Build passes
./gradlew build -x check

# JVM tests pass
./gradlew :core:jvmTest

# Version task works (git-versioning)
./gradlew version --no-daemon --console=plain -q
```

## Risk Considerations

- **Ktor 2.x → 3.x**: khttpclient 0.0.8 uses Ktor 3.x internally, so direct Ktor API usage in ktumblr may need updates. The `AbstractResourceImpl` class is the primary risk area.
- **Kotlin 2.0 → 2.3**: Generally backward-compatible, but K2 compiler may surface new warnings or errors.
- **Gradle 8.5 → 9.3.1**: May introduce deprecation warnings in build scripts. The new convention plugin approach addresses most of these.
