FHIR Test Cases
===============

The test cases for FHIR implementers are not maintained in this repository.
They live in the fhir-test-cases repository:

  https://github.com/FHIR/fhir-test-cases

They are released as testcases.zip on GitHub, and on Maven Central as
org.hl7.fhir.testcases:fhir-test-cases:

  https://github.com/FHIR/fhir-test-cases/releases

Tests are grouped by FHIR version (r4, r4b, r5, r6), plus some shared folders.
Some useful places to start:

  r5/patch/json-patch-tests.json    JSON Patch
  r5/patch/xml-patch-tests.xml      XML Patch
  r5/patch/fhir-patch-tests.xml     FHIRPath Patch
  r6/fhirpath/tests-fhir-r6.xml     FHIRPath
  validator/manifest.json           Validator test cases

The test cases for the invariants defined in this specification are in
source/[resource]/invariant-tests, and are run as part of the build.

About this folder
-----------------

This folder used to hold material left over from older builds