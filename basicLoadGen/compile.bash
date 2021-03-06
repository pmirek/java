# get javaDir
source ${HOME}/env/current.cfg

cd `dirname $0`
rm -rf classes/*

mySources=`find src -name "*.java"`
if [ ! -z "${mySources}" ]; then
 ${javaDir}/bin/javac -d "classes" -classpath "lib/*" ${mySources}
fi
