#### Complete Summary of the Mandatory Requirements

1.  Conditionally One clinical status in `Condition.clinicalStatus`
    -   [Invariants]
        -   required if `Condition.verificationStatus != ‘entered-in-error‘`
        -   not present if `Condition.verificationStatus = ‘entered-in-error‘`
    -   Condition.clinicalStatus is bound to [Condition Clinical Status Codes] value set
1.  One code in `Condition.verificationStatus`
    -   Condition.clnicalStatus is bound to [ConditionVerificationStatus] value set
1.  One Identification of the problem or health concern in `Condition.code`which has an [extensible](http://hl7-fhir.github.io/terminologies.html#extensible) binding to:
    -   [Problem Value Set] value set.
1.  One patient reference in `Condition.patient`



#### problems and health concerns category codes

The [DAF Condition Category Codes] supports the separate concepts of problems and health concerns in `Condition.category` in order for API consumers to be able to separate health concerns and problems. However this is not mandatory for 2015 certification.

  [extensible]: Implementation_Guide#Extensible_binding_for_CodeableConcept_Datatype "wikilink"
  [Problem Value Set]: valueset-daf-problem.html
  [Invariants]: http://hl7-fhir.github.io/conformance-rules.html#constraints
  [Condition Clinical Status Codes]: http://hl7-fhir.github.io/valueset-condition-clinical.html
  [ConditionVerificationStatus]: http://hl7-fhir.github.io/valueset-condition-ver-status.html
  [DAF Condition Profile]: http://hl7.org/fhir/us/daf/daf-condition.html
 [DAF Condition Category Codes]: valueset-daf-condition-category.html