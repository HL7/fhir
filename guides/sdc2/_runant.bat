SET JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8
call ant -buildfile framework/build.xml -Dguidename=sdc -Dspec=http://hl7-fhir.github.io/
pause