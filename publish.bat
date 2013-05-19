@ECHO off
ECHO Running publication process now

for /f "usebackq" %%x in (`dir /od /b "%ProgramFiles%\java\jdk*"`) do set newestJDK="%ProgramFiles%\java\%%x\bin\java.exe"
echo Using %newestJDK%

%newestJDK% -jar tools\bin\org.hl7.fhir.tools.jar "%CD%" 

PAUSE