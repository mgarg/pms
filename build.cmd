mvn install:install-file -Dfile=libs\ojdbc6.jar -DgroupId=oracle -DartifactId=com.sample -Dversion=1.0 -Dpackaging=jar
mvn assembly:assembly
copy target\pms-1.0-SNAPSHOT-jar-with-dependencies.jar bin\