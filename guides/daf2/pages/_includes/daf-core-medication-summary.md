#### Complete Summary of the Mandatory Requirements

1.  Either:
    -  One drug code in `Medication.code` which has an [extensible](http://hl7-fhir.github.io/terminologies.html#extensible) binding to:
        -  [Medication Clinical Drug (RxNorm)] value set
    -  or a list of ingredients in `Medication.product`


  [DAF MedicationStatement Profile]: http://hl7.org/fhir/daf/medicationstatement-daf.html
  [DAF MedicationOrder Profile]: http://hl7.org/fhir/daf/medicationorder-daf.html
  [`MedicationOrder` `Example`]: MedicationOrder_Example "wikilink"
  [Medication Clinical Drug (RxNorm)]: http://hl7.org/fhir/daf/valueset-daf-medication-codes.html
