1) Download and install JDK 7+ (make sure your JAVA_HOME environment variable points to the JDK, not a JRE).
2) Download and install Maven 3.1.0+.
3) mvn appfuse:full-source
4) mvn jetty:run
5) If OOM, set your JAVA_OPTS environment variable to -Xmx1024M -XX:PermSize=512m
