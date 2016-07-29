SET guidename=sdc
rem java -jar "%saxonhome%saxon9.jar" -s resources/%guidename%.xml -o %guidename%.json -xsl igToConfig.xslt
java -jar "%saxonhome%saxon9.jar" -s resources/%guidename%.xml -o temp/_data/pages.json -xsl igToData.xslt
java -jar ../../publish/org.hl7.fhir.igpublisher.jar
pause