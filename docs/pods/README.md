> [日本語](./README_ja.md)

# ktumblr cocoapods

This repository is the Cocoapods repository for [ktumblr]. [ktumblr] is a Mastodon client library created using Kotlin Multiplatform.
Therefore, it can be built and used on Apple devices such as iOS. Here, we distribute the built XCFramework via Cocoapods.
Additionally, this repository is automatically committed by GitHub Actions for [ktumblr]. Please submit issues or pull requests to [ktumblr].

## Usage

### Podfile

Please add the following lines to your Podfile.
There are no versions in this repository, and there are branches that match the versions of [ktumblr].
You can determine which version of [ktumblr] to use by specifying the branch of this repository.
Please check the [branch list](https://github.com/uakihir0/ktumblr-cocoapods/branches) to find the branch corresponding to the version.
Additionally, different versions are used for Debug and Release builds.

```ruby
target '{{PROJECT_NAME}}' do
  use_frameworks!

  # Pods for ktumblr
  pod 'ktumblr-debug', 
    :configuration => ['Debug'], 
    :git => 'https://github.com/uakihir0/ktumblr-cocoapods/', 
    :branch => '{{BRANCH_NAME}}'
  pod 'ktumblr-release', 
    :configuration => ['Release'], 
    :git => 'https://github.com/uakihir0/ktumblr-cocoapods/', 
    :branch => '{{BRANCH_NAME}}'
  ...
end
```

### Requesting

It is also possible to use in Objective-C, but the following is the usage in Swift.
Please also check the README of [ktumblr] for detailed usage.

```swift
WIP
```

## License

MIT License

## Author

[Akihiro Urushihara](https://github.com/uakihir0)


[ktumblr]: https://github.com/uakihir0/ktumblr