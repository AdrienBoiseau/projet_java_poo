#!/usr/bin/env bash
export TERM=xterm
clear
echo Debut des tests
javac -verbose exec.java
javac -verbose timetable_generator/*.java
java exec
clear
