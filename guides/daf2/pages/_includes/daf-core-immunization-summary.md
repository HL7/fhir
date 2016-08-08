#### Complete Summary of the Mandatory Requirements

1.  One status in `Immunization.status` which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
-  [Immunization Status] value set.
1.  One dateTime in `Immunization.date`
1.  One vaccine code in `Immunization.vaccineCode` which has:
-   a [required](http://hl7-fhir.github.io/terminologies.html#required) binding to the [CVX] value set
-   SHOULD have a translation to the to the [NDC] value set
1.  One patient in `Immunization.patient`
1.  One boolean value in `Immunization.wasNotGiven`
1.  One boolean value in `Immunization.reported`

  [Immunization Status]: valueset-daf-core-immunization-status.html
  [CVX]: valueset-daf-cvx.html
  [NDC]: valueset-daf-ndc-vaccine-codes.html