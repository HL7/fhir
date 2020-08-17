@ECHO off
ECHO Running publication process now

SET PATH=%~dp0apache-ant\bin;%PATH%;

for /f "usebackq" %%x in (`dir /od /b "%ProgramFiles%\java\jdk*"`) do set newestJDK=%ProgramFiles%\java\%%x
set JAVA_HOME=%newestJDK%
echo Using %JAVA_HOME%

set ANT_OPTS=-Xms96m 
call ant Publisher -Dargs="%* -web"

PAUSE
