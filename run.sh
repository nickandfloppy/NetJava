#!/usr/bin/bash
javac -classpath "lib/*":. netjava/Bot.java
java -classpath "lib/*": netjava/Bot
