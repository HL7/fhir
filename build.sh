NAME="Continuous Integration Build"
SVNREV=$(git log -1 | grep svn/fhir@ | grep -oP 'svn/fhir@\K([0-9]+)')

antBuild (){
  ./publish.sh -svn \'$SVNREV\' -name \'$NAME\' -url http://hl7-fhir.github.io/
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
