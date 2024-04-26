> [日本語](./docs/README_ja.md)

# ktumblr

![Maven metadata URL](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo.repsy.io%2Fmvn%2Fuakihir0%2Fpublic%2Fwork%2Fsocialhub%2Fkmastodon%2Fcore%2Fmaven-metadata.xml)

![badge][badge-js]
![badge][badge-jvm]
![badge][badge-ios]
![badge][badge-mac]

**This library is a Mastodon client library designed for [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html).**
It depends on [khttpclient] and uses Ktor Client internally.
Therefore, this library is available on platforms supported by Kotlin Multiplatform and Ktor Client.
The behavior on each platform depends on [khttpclient].

## Usage

Below is how to use it with Gradle in Kotlin on the respective platforms.
**If you want to use it on Apple platforms, please refer to [ktumblr-cocoapods](https://github.com/uakihir0/ktumblr-cocoapods).**
**For usage in JavaScript, please refer to [ktumblr.js](https://github.com/uakihir0/ktumblr.js).**
Refer to the test code for how to call each API.

```kotlin:build.gradle.kts
repositories {
    mavenCentral()
+   maven { url = uri("https://repo.repsy.io/mvn/uakihir0/public") }
}

dependencies {
+   implementation("work.socialhub.ktumblr:core:0.0.1-SNAPSHOT")
}
```

### Authentication

First, create an application and request a URL for the user to authenticate.

```kotlin
WIP
```

After the user authenticates, the code is passed via the query in the redirected URL, so use it to obtain the access token as follows.

```kotlin
WIP
```

### Create Post

```kotlin
WIP
```

## License

MIT License

## Author

[Akihiro Urushihara](https://github.com/uakihir0)

[khttpclient]: https://github.com/uakihir0/khttpclient
[badge-android]: http://img.shields.io/badge/-android-6EDB8D.svg
[badge-android-native]: http://img.shields.io/badge/support-[AndroidNative]-6EDB8D.svg
[badge-wearos]: http://img.shields.io/badge/-wearos-8ECDA0.svg
[badge-jvm]: http://img.shields.io/badge/-jvm-DB413D.svg
[badge-js]: http://img.shields.io/badge/-js-F8DB5D.svg
[badge-js-ir]: https://img.shields.io/badge/support-[IR]-AAC4E0.svg
[badge-nodejs]: https://img.shields.io/badge/-nodejs-68a063.svg
[badge-linux]: http://img.shields.io/badge/-linux-2D3F6C.svg
[badge-windows]: http://img.shields.io/badge/-windows-4D76CD.svg
[badge-wasm]: https://img.shields.io/badge/-wasm-624FE8.svg
[badge-apple-silicon]: http://img.shields.io/badge/support-[AppleSilicon]-43BBFF.svg
[badge-ios]: http://img.shields.io/badge/-ios-CDCDCD.svg
[badge-mac]: http://img.shields.io/badge/-macos-111111.svg
[badge-watchos]: http://img.shields.io/badge/-watchos-C0C0C0.svg
[badge-tvos]: http://img.shields.io/badge/-tvos-808080.svg