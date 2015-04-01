#!/bin/sh
#
java -client -Dcom.sun.management.jmxremote.port=1090 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -XX:+HeapDumpOnOutOfMemoryError -jar Quintett-0.0.5-jar-with-dependencies.jar -db j -t 11 -ps j -pl n &
#  -Xmx768m -Xms256m -XX:MaxPermSize=512m -XX:+HeapDumpOnOutOfMemoryError 
#
#
#java -Dcom.sun.management.jmxremote.port=1090
#     -Dcom.sun.management.jmxremote.ssl=false
#     -Dcom.sun.management.jmxremote.authenticate=false
#     -jar -Xmx768m -Xms256m -XX:MaxPermSize=512m -XX:-UseGCOverheadLimit -XX:+UseParallelGC -jar Quintett-0.0.5-jar-with-dependencies.jar -t 11 -db j -pl n -ps j &
     