pluginManagement {
    includeBuild("plugins")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "ktumblr"

include("core")

val osName = System.getProperty("os.name").lowercase(java.util.Locale.getDefault())
if (osName.contains("mac")) {
    include("all")
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
