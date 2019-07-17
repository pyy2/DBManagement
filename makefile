JC = javac
JFLAGS = -cp './src/jar/postgresql-42.2.5.jar:.'
JAR = https://jdbc.postgresql.org/download/postgresql-42.2.5.jar

default: all

all:
	mkdir -p ./build
	mkdir -p ./src/jar
	cd ./src/jar && wget -N $(JAR)
	$(JC) $(JFLAGS) -d './build' src/*.java

clean:
	rm -r build
	rm -r src/jar
<<<<<<< HEAD

ft:
	cd build && java $(JFLAGS2) BoutiqueCoffee
=======
>>>>>>> 061cef9c1990d189aaa12cccec5703ae1850d1d9
