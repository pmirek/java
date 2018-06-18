JAVA_HOME='/java/Linux_x86_64_64bit/latest'

cd `dirname $0`
rm -rf classes/*

mySources=`find src -name "*.java"`
if [ ! -z "${mySources}" ]; then
 ${JAVA_HOME}/bin/javac -d "classes" -classpath "lib/*" ${mySources}
fi
