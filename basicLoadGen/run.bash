JAVA_HOME='/java/Linux_x86_64_64bit/latest'

cd `dirname $0`

cpuNum=$1
threadsPerCpu=$2
load=$3
durationSec=$4

${JAVA_HOME}/bin/java \
  -classpath "classes:lib/*" \
  LoadGen \
  -cpuNum ${cpuNum} \
  -threadsPerCpu ${threadsPerCpu} \
  -load ${load} \
  -durationSec ${durationSec} 


exit

# results for given parameters -cpuNum 12 -threadsPerCpu 1 -load 0.1 -durationSec 60
#
# top - 08:00:53 up 19 days, 14 min,  9 users,  load average: 0.05, 0.08, 0.11
# Tasks: 245 total,   1 running, 244 sleeping,   0 stopped,   0 zombie
# %Cpu0  : 10.0 us,  0.0 sy,  0.0 ni, 90.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
# %Cpu1  : 10.0 us,  0.0 sy,  0.0 ni, 90.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
# %Cpu2  : 10.0 us,  0.3 sy,  0.0 ni, 89.7 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
# %Cpu3  : 10.0 us,  0.0 sy,  0.0 ni, 90.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
# %Cpu4  : 10.0 us,  0.0 sy,  0.0 ni, 90.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
# %Cpu5  : 10.0 us,  0.0 sy,  0.0 ni, 90.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
# %Cpu6  : 10.0 us,  0.0 sy,  0.0 ni, 90.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
# %Cpu7  : 10.0 us,  0.0 sy,  0.0 ni, 90.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
# %Cpu8  : 10.0 us,  0.0 sy,  0.0 ni, 90.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
# %Cpu9  : 10.0 us,  0.0 sy,  0.0 ni, 90.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
# %Cpu10 : 10.0 us,  0.0 sy,  0.0 ni, 90.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
# %Cpu11 : 10.0 us,  0.3 sy,  0.0 ni, 89.7 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
# KiB Mem : 12138156 total,  5049004 free,  2479336 used,  4609816 buff/cache
# KiB Swap:  1048572 total,   838528 free,   210044 used.  8936996 avail Mem
