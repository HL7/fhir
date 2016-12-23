#!/bin/bash
name="ig10"
path= /Users/ehaas/Documents/FHIR/build/tests/ig10/
echo "================================================================="
echo === Publish $name IG!!! $(date -u) ===
echo "================================================================="
git status
java -jar /Users/ehaas/Downloads/org.hl7.fhir.igpublisher.jar -ig "${path}ig.json"
