@ECHO off
ECHO Running publication process now


echo Using %JAVA_HOME%

set ANT_OPTS=-Xms96m 
call ant Publisher -Dargs="%* -web"

PAUSE
