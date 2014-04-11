NAME="Continuous Integration Build"

prepareToBuild (){
  git clean -d -f
}

toolsBuild (){
  prepareToBuild
  java -jar -Xmx1024m tools/bin/org.hl7.fhir.tools.jar `pwd` -name "$NAME"
  checkStatus
}

antBuild (){
  prepareToBuild
  ant -f tools/java/org.hl7.fhir.tools.core/build.xml cleanall Publisher -Dargs=\"$(pwd) -name $NAME\"
  checkStatus
}

checkStatus (){
  if [ $? -eq 0 -a ! -f fhir-error-dump.txt ]
  then
    echo "Build status OK"
  else
    echo "error dump:"
    cat fhir-error-dump.txt
    exit 1
  fi
}

antBuild
toolsBuild
