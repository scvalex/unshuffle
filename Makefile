v := 0.2

all: install

.PHONY: all install build clean push p market

install: build
	adb -s 36335A130C1900EC install -r target/unshuffle-0.1.apk

build:
	sbt android:package-debug

push:
	git push
	sbt android:github-delete
	sbt android:github-upload

clean:
	sbt clean
	rm -rf target/ project/target/

p:
	permamake.sh $$(find src/ -name '*.xml' -o -name '*.scala') Makefile

market:
	sbt android:package-release
	sbt android:prepare-market
	jarsigner -verbose -sigalg MD5withRSA -digestalg SHA1 -keystore ~/.keystore target/unshuffle-$v-market.apk scvalex
	jarsigner -verify target/unshuffle-$v-market.apk
	zipalign -v 4 target/unshuffle-$v-market.apk unshuffle-$v-market.apk
