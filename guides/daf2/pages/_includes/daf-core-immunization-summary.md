#### Complete Summary of the Mandatory Requirements

1.  One status in `Immunization.status` which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
-  [Immunization Status] value set.
1.  One dateTime in `Immunization.date`
1.  One vaccine code in `Immunization.vaccineCode` which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
-  [CVX code set].
1.  One patient in `Immunization.patient`
1.  One boolean value in `Immunization.wasNotGiven`
1.  One boolean value in `Immunization.reported`

  [Immunization Status]: valueset-daf-core-immunization-status.html
  [CVX code set]: http://hl7.org/fhir/daf/valueset-daf-cvx.html
  [DAF Immunization Profile]: http://hl7.org/fhir/daf/daf-immunization.html
  [NDC vaccine codes]: NDC_vaccine_codes "wikilink"
  [translations]: Implementation_Guide#Definitions "wikilink"
  [NDC to CVX concept map]: NDC_to_CVX_concept_map "wikilink"