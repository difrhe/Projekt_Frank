#!/bin/sh

PATH="/C/Programme/7-Zip:/C/Programme/java/jdk1.6.0_30/bin:$PATH"
export PATH

HOME=`pwd`
export HOME

JAVA_CP="${HOME}:${HOME}/lib:${HOME}/lib/org.jar"
export JAVA_CP

rm -f Main_Logger.class

javac.exe -cp "${HOME}/lib/org.jar" Main_Logger.java

exit 0
