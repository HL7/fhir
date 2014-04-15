### To run the FHIR Publisher

1. Run `publish.bat` (windows) or `publish.sh` (OSX/Linux)
2. Wait for it to finish (~10 minutes)

See also: [FHIR Build Process](http://wiki.hl7.org/index.php?title=FHIR_Build_Process)

### To build and run the FHIR Publisher via ant
First install [ant 1.9+](http://ant.apache.org/bindownload.cgi).

Then invoke as:

```
ant -f tools/java/org.hl7.fhir.tools.core/build.xml \
       cleanall 
       Publisher \
       -Dargs=\"$(pwd)\"
```

---

### To build tools.jar via ant
```
ant -f tools/java/org.hl7.fhir.tools.core/build.xml \
       cleanall \
       build-tools-jar
```

### To build tools.jar via Eclispe:

(First set up the FHIR project in Eclipse -- documentation needed!)

1. file.. export... find runnable jar file
2. choose your launch configuration.
3. export destination = [root]\tools\bin\org.hl7.fhir.tools.jar
4. package required libraries into generated jar
5. finish
6. note it generates warnings... ignore these
