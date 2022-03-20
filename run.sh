#!/usr/bin/bash
find . -name '*.class' -delete -type f
javac -classpath "lib/*":. netjava/Bot.java
java -classpath "lib/*": netjava/Bot
