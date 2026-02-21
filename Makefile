build:
	./gradlew \
	core:clean \
	core:assemble \
	-x check --refresh-dependencies

pods:
	./gradlew \
	all:assembleKtumblrXCFramework \
  	all:podPublishXCFramework \
	-x check --refresh-dependencies

version:
	 ./gradlew version --no-daemon --console=plain -q

.PHONY: build pods version