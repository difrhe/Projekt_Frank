#!/bin/sh

PATH="/C/Programme/7-Zip:/C/Programme/java/jdk1.6.0_30/bin:$PATH"
export PATH

HOME=`pwd`
export HOME

JAVA_CP="${HOME}:${HOME}/lib:${HOME}/lib/org.jar"
export JAVA_CP

if [ "${1}" = "-start" ]
then
 java.exe -cp "${JAVA_CP}" Main_Logger -start

 exit 0
fi

if [ "${1}" = "-loop" ]
then
 java.exe -cp "${JAVA_CP}" Main_Logger -loop

 exit 0
fi

echo
echo "Usage ${0} : {-start|-loop}"
echo

exit 0

