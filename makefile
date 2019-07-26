JC = javac
JFLAGS = -cp './src/jar/postgresql-42.2.5.jar:.'
JFLAGS2 = -cp '../src/jar/postgresql-42.2.5.jar:.'
JAR = https://jdbc.postgresql.org/download/postgresql-42.2.5.jar

default: all

all:
	mkdir -p ./build
	mkdir -p ./src/jar
	cd ./src/jar && wget -N $(JAR)
	$(JC) $(JFLAGS) -d './build' src/*.java
	cp benchmark.csv ./build/benchmark.csv
clean:
	rm -r build
	rm -r src/jar

ft:
	cd build && java $(JFLAGS2) BCDriver
