# 315240564
# Zultiar

compile: bin
	find src -name "*.java" > sources.txt
	javac -d bin -cp biuoop-1.4.jar @sources.txt
	rm -rf sources.txt
run: jar
	java -jar ass6game.jar
jar:
	echo "Main-Class: Ass6Game" > Manifest.mf
	mkdir uber-jar
	jar -xf biuoop-1.4.jar
	rm -rf META-INF
	cp -r biuoop uber-jar/
	cp -r resources uber-jar/
	cp -r bin/* uber-jar/
	jar cfm ass6game.jar Manifest.mf -C uber-jar . -C resources .
	rm -rf uber-jar
	rm -rf Manifest.mf
bin:
	mkdir bin