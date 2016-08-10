#### Complete Summary of the Mandatory Requirements


1.  One status in `MedicationStatement.status` which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
-   [MedicationStatementStatus] value set.
1.  One medication via `MedicationStatement.medicationCodeableConcept` or `MedicationStatement.medicationReference`   
-  `MedicationStatement.medicationCodeableConcept` has an [extensible](http://hl7-fhir.github.io/terminologies.html#extensible) binding to [Medication Clinical Drug (RxNorm)] value set.
1.  One patient reference in `MedicationStatement.patient`
1.  One date or period in `MedicationStatement.effectiveDateTime` or `MedicationStatment.effectivePeriod`


  [Medication Clinical Drug (RxNorm)]: valueset-daf-medication-codes.html
  [MedicationOrderStatus]: http://hl7-fhir.github.io/valueset-medication-order-status.html
[MedicationStatementStatus]: http://hl7-fhir.github.io/valueset-medication-statement-status.html


