all: install

.PHONY: all install build clean push p

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
