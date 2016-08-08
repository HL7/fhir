SET JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8
call ant -buildfile build.xml -Dguidename=uslab -Dspec=http://hl7-fhir.github.io/
pause