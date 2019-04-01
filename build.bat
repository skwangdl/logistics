set JAVA_HOME=D:\Program Files\Java\jdk1.8.0_45
set ANT_HOME=D:\Program Files\ant-1.7.0
set path=%ANT_HOME%\bin;%path%
set CLASSPATH=%JAVA_HOME%\lib\tools.jar;%JAVA_HOME%\lib\dt.jar
set ANT_OPTS=-Xms128m -Xmx256m

set SERVER_NAME=Tomcat8
set normal_xml=build_normal.xml
set mant_xml=build_maintenance.xml

SC Query %SERVER_NAME% | FindStr -C:"STOPPED">NUL
  if ERRORLEVEL 1 ( 
ant -f %normal_xml% %1) else (
ant -f %mant_xml% %1)