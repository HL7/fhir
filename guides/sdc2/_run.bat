SET guidename=sdc
java -jar "%saxonhome%saxon9.jar" -s resources\%guidename%.xml -o %guidename%.json -xsl igToConfig.xslt
java -jar "%saxonhome%saxon9.jar" -s resources\%guidename%.xml -o pages\_data\pages.json -xsl igToData.xslt
java -jar "%saxonhome%saxon9.jar" -s resources\%guidename%.xml -o pages\_includes\content-toc.html -xsl igToTOC.xslt
java -jar "%saxonhome%saxon9.jar" -s resources\%guidename%.xml -o pages\empty.txt -xsl igToPages.xslt
del pages\empty.txt
java -jar ../../publish/org.hl7.fhir.igpublisher.jar
pause