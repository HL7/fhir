#### Complete Summary of the Mandatory Requirements

1.  One status in `Observation.status`which has an [extensible](http://hl7-fhir.github.io/terminologies.html#extensible) binding to:
    -    [ObservationStatus] value set.
1.  One category in `Observation.category` which must have:
    -   a fixed `Observation.category.coding.system`=“http://hl7.org/fhir/observation-category”
    -   a fixed `Observation.category.coding.code`=“vital-signs”

1.  A code in `Observation.code`
    -   a fixed `Observation.code.coding.system`=“http://loinc.org”
    -   a LOINC code in `Observation.code.coding.code` which has an [extensible](http://hl7-fhir.github.io/terminologies.html#extensible) binding to:
         -   [Vital Signs] value set.

1.  Either one `Observation.valueQuantity` or, if there is no value, one code in `Observation.DataAbsentReason` (Note: See the comments in the table for exceptions)
    -   Each Observation.valueQuantity must have:
        -   One numeric value in `Observation.valueQuantity.value`
        -   a fixed '''Observation.valueQuantity.system="http://unitsofmeasure"

  [Vital Signs]: Argonaut_Vital_Signs "wikilink"
  [Vital Signs Units]: Argonaut_Vital_Signs_Units "wikilink"
  [extensible bindings]: Implementation_Guide#Extensible_binding_for_CodeableConcept_Datatype "wikilink"
  [using multiple codes]: Implementation_Guide#Using_multiple_codes_with_CodeableConcept_Datatype "wikilink"
  [ObservationStatus]: http://hl7-fhir.github.io/valueset-observation-status.html
