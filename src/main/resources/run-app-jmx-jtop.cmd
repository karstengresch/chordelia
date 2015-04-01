java -client -Dhibernate.cglib.use_reflection_optimizer=false -Dcom.sun.management.jmxremote.port=1290 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -XX:+HeapDumpOnOutOfMemoryError -jar Quintett-0.0.5-jar-with-dependencies.jar -db j -t 11 -ps j -pl n
REM -server -Xmx768m -Xms256m -XX:MaxPermSize=512m -XX:+HeapDumpOnOutOfMemoryError -XX:+UseParallelGC -XX:-UseGCOverheadLimit 
REM -XX:+CMSClassUnloadingEnabled -XX:+CMSPermGenSweepingEnabled 
   