build:
	./gradlew \
	core:clean \
	core:assemble \
	-x check

pods:
	./gradlew \
	all:assembleKtumblrXCFramework \
	all:podPublishXCFramework \
	-x check

version:
	./gradlew version --no-daemon --console=plain -q

.PHONY: build pods version
