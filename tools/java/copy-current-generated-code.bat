@echo off
REM Dirmerge is a utility that scans source, and copies to dest. if the file already exists in source, it checks if they are different, and if they are, brings up winmerge if the genmark is not found
IF "%fhirbase%"="" THEN SET fhirbase=C:\work\org.hl7.fhir
%fhirbase%\build\tools\bin\dirmerge %fhirbase%\build\implementations\java\org.hl7.fhir.instance\src\org\hl7\fhir\instance\formats %fhirbase%\build\tools\java\org.hl7.fhir.instance\src\org\hl7\fhir\instance\formats java "// Generated on"
%fhirbase%\build\tools\bin\dirmerge %fhirbase%\build\implementations\java\org.hl7.fhir.instance\src\org\hl7\fhir\instance\model %fhirbase%\build\tools\java\org.hl7.fhir.instance\src\org\hl7\fhir\instance\model java "// Generated on"
%fhirbase%\build\tools\bin\dirmerge %fhirbase%\build\implementations\java\org.hl7.fhir.instance\src\org\hl7\fhir\instance\utils %fhirbase%\build\tools\java\org.hl7.fhir.instance\src\org\hl7\fhir\instance\utils java
%fhirbase%\build\tools\bin\dirmerge %fhirbase%\build\implementations\java\org.hl7.fhir.instance\src\org\hl7\fhir\instance\validation %fhirbase%\build\tools\java\org.hl7.fhir.instance\src\org\hl7\fhir\instance\validation java
%fhirbase%\build\tools\bin\dirmerge %fhirbase%\build\implementations\java\org.hl7.fhir.instance\src\org\hl7\fhir\instance\test %fhirbase%\build\tools\java\org.hl7.fhir.instance\src\org\hl7\fhir\instance\test java
%fhirbase%\build\tools\bin\dirmerge %fhirbase%\build\implementations\java\org.hl7.fhir.instance\src\org\hl7\fhir\instance\client %fhirbase%\build\tools\java\org.hl7.fhir.instance\src\org\hl7\fhir\instance\client java


