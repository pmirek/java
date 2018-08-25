# get javaDir
source ${HOME}/env/current.cfg

${javaDir}/bin/java MbeanGet  "box1.com:9999" "java.lang:type=Runtime|VmVendor|VmVersion;MomMetrics:name=NmvPss|SendCount"
