#!/usr/bin/env bash
cd "$(dirname "$0")" || exit
BASE_PATH=$(pwd)
BUILD_PATH=../all/build

# Make Repository
cd "$BASE_PATH" || exit
mkdir -p $BUILD_PATH/cocoapods/repository/debug
mkdir -p $BUILD_PATH/cocoapods/repository/release

# Copy Podspec
cd "$BASE_PATH" || exit
cd $BUILD_PATH/cocoapods/publish/debug || exit
cp ktumblr.podspec ../../repository/ktumblr-debug.podspec
cd ../../repository/ || exit
sed -i -e "s|'ktumblr'|'ktumblr-debug'|g" ktumblr-debug.podspec
sed -i -e "s|'ktumblr.xcframework'|'debug/ktumblr.xcframework'|g" ktumblr-debug.podspec
rm *.podspec-e
cd "$BASE_PATH" || exit
cd $BUILD_PATH/cocoapods/publish/release || exit
cp ktumblr.podspec ../../repository/ktumblr-release.podspec
cd ../../repository/ || exit
sed -i -e "s|'ktumblr'|'ktumblr-release'|g" ktumblr-release.podspec
sed -i -e "s|'ktumblr.xcframework'|'release/ktumblr.xcframework'|g" ktumblr-release.podspec
rm *.podspec-e

# Copy Framework
cd "$BASE_PATH" || exit
cd $BUILD_PATH/cocoapods/publish/debug || exit
cp -r ktumblr.xcframework ../../repository/debug/ktumblr.xcframework
cd "$BASE_PATH" || exit
cd $BUILD_PATH/cocoapods/publish/release || exit
cp -r ktumblr.xcframework ../../repository/release/ktumblr.xcframework

# Copy README
cd "$BASE_PATH" || exit
cd ../ || exit
cp ./LICENSE ./all/build/cocoapods/repository/LICENSE
cp ./docs/pods/README.md ./all/build/cocoapods/repository/README.md
cp ./docs/pods/README_ja.md ./all/build/cocoapods/repository/README_ja.md
