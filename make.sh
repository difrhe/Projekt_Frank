#!/bin/sh

PATH="/C/Programme/7-Zip:/C/Programme/java/jdk1.6.0_30/bin:$PATH"
export PATH

HOME=`pwd`
export HOME

rm -f Crypte_Passwd.class

# java -version

javac.exe -cp "${HOME}/lib/org.jar" Crypte_Passwd.java

exit 0
