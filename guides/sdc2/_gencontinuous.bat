SET JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8
java -jar framework\ant\ant-launcher.jar -buildfile framework/build.xml -Dcontinuous=Yes
pause