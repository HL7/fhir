#### Complete Summary of the Mandatory Requirements

1.  One status in `Observation.status` which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
    -    [ObservationStatus] value set.
1.  One category in `Observation.category` which must have:
    -   a fixed `Observation.category.coding.system`=“http://hl7.org/fhir/observation-category”
    -   a fixed `Observation.category.coding.code`=“laboratory”
1.  One code in `Observation.code` which has an [extensible](http://hl7-fhir.github.io/terminologies.html#extensible) binding to:
    -  [LOINC Observation Codes]
    -   Other additional codes are allowed - e.g. system specific codes. All codes SHALL have a code system value
1.  One patient in `Observation.subject`
1.  Either one `Observation.value` or one code in `Observation.DataAbsentReason` (unless the Observation.code is a panel code)
    -   Each Observation.valueQuantity must have:
        -   One numeric value in `Observation.valueQuantity.value`
        -   a fixed `Observation.valueQuantity.system`=“http://unitsofmeasure.org”
        -   a UCUM units code in `Observation.valueQuantity.code` which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
				-    [ObservationStatus] value set.
    -   Each Observation.valueCodeableConcept must have either:
        1.  a code in `Observation.valueCodeableConcept.coding.code` and code system in `Observation.valueCodeableConcept.coding.sytem` which has an [preferred](http://hl7-fhir.github.io/terminologies.html#preferred) binding to:
            -   [Observation Value Codes (SNOMED-CT)] 

        1.  OR text in `Observation.valueCodeableConcept.text`
    -   Observation.DataAbsentReason is bound to [Observation Value Absent Reason] Value set



Each Observation *SHOULD* have:

1.  A date and time in `effectiveDateTime` or `effectivePeriod`
1.  A reference range if applicable in `Observation.referenceRange`

  [Observation Value Codes (SNOMED-CT)]: valueset-daf-observation-value-codes.html
  [Observation Value Absent Reason]: http://hl7-fhir.github.io/valueset-observation-valueabsentreason.html
  [UCUM units]: valueset-daf-ucum.html
  [LOINC]: http://loinc.org
  [LOINC Observation Codes]: http://hl7-fhir.github.io/valueset-observation-codes.html
  [ObservationStatus]: http://hl7-fhir.github.io/valueset-observation-status.html