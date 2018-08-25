# get javaDir
source ${HOME}/env/current.cfg

cd `dirname $0`

rm *.class

mySources=`find src -name "*.java"`
if [ ! -z "${mySources}" ]; then
    ${javaDir}/bin/javac  -d . ${mySources}
    if [ $? != 0 ]; then exit; fi
fi
