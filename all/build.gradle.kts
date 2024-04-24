import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.cocoapods)
}

kotlin {
    js(IR) {
        moduleName = "ktumblr-js"
        nodejs()
        browser()
        binaries.library()
        generateTypeScriptDefinitions()
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

tasks.podPublishXCFramework {
    doLast {
        exec {
            executable = "sh"
            args = listOf("../tool/setup_pods.sh")
        }
    }
}

tasks.getByName("jsBrowserDevelopmentLibraryDistribution") {
    doLast {
        exec {
            executable = "sh"
            args = listOf("../tool/setup_js.sh")
        }
    }
}

