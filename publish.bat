@ECHO off
ECHO Running publication process now

REM for /f "usebackq" %%x in (`dir /od /b "%ProgramFiles%\java\jdk*"`) do set newestJDK=%ProgramFiles%\java\%%x
REM set JAVA_HOME=%newestJDK%
echo Using %JAVA_HOME%

set ANT_OPTS=-Xms64m 
call ant Publisher -Dargs="%*"

PAUSE
