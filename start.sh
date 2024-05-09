#!/bin/sh

PATH="/C/Programme/7-Zip:/C/Programme/java/jdk1.6.0_30/bin:$PATH"
export PATH

HOME=`pwd`
export HOME

JAVA_CP="${HOME}:${HOME}/lib:${HOME}/lib/org.jar"
export JAVA_CP

#echo ${JAVA_CP}

if [ "${1}" = "-s" -a "x${2}" != "x" -a "x${3}" != "x" ]
then
 java.exe -cp "${JAVA_CP}" Crypte_Passwd -s ${2} ${3}

 exit 0
fi

if [ "${1}" = "-c" -a "x${2}" != "x" -a "x${3}" != "x" ]
then
 java.exe -cp "${JAVA_CP}" Crypte_Passwd -c ${2} ${3}

 exit 0
fi


if [ "${1}" = "-salt" -a "x${2}" != "x" ]
then
 SALT=`echo "${2}" | openssl.exe sha1 | awk '{print $2}' | cut -c 1-16`

 echo "Salt = ${SALT}"

 exit 0
fi

echo
echo "Usage ${0} : {-s|-c}"
echo

exit 0
