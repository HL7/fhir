java -Dfile.encoding=UTF-8 -jar ../../tools/bin/plantuml.jar -nometadata -tsvg -o "../../input" "*.plantuml"
cd ../../input
ren "*.svg" "*.svg.html"
move "*.svg.html" ../source
pause