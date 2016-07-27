#### Complete Summary of the Mandatory Requirements

1.  One status in `Observation.status` which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
    -    [ObservationStatus] value set.
1.  One category in `Observation.category` which must have:
    -   a fixed `Observation.category.coding.system`=“http://hl7.org/fhir/observation-category”
    -   a fixed `Observation.category.coding.code`=“laboratory”
1.  One code in `Observation.code` which has an [extensible](http://hl7-fhir.github.io/terminologies.html#extensible) binding to:
    -  [US Laboratory Observation Profile Observation Name Codes] value set. 
    -   Other additional codes are allowed - e.g. system specific codes. All codes SHALL have an system value
1.  Either one `Observation.value` or one code in `Observation.DataAbsentReason` (unless the Observation.code is a panel code)
    -   Each Observation.valueQuantity must have:
        -   One numeric value in `Observation.valueQuantity.value`
        -   a fixed `Observation.valueQuantity.system`=“http://unitsofmeasure.org”
        -   a UCUM unit code in `Observation.valueQuantity.code` taken from the “Units” column in the table
    -   Each Observation.valueCodeableConcept must have either:
        1.  a code in `Observation.valueCodeableConcept.coding.code` and code system in `Observation.valueCodeableConcept.coding.sytem`
            -   [SNOMED CT] is preferred binding

        1.  OR text in `Observation.valueCodeableConcept.text`
    -   Observation.DataAbsentReason is bound to [Observation Value Absent Reason] Value set

1.  One patient in `Observation.subject`

Each Observation should have:

1.  A date and time in `effectiveDateTime` or `effectivePeriod`
1.  A reference range if applicable in `Observation.referenceRange`

  [SNOMED CT]: http://snomed.info/sct
  [Observation Value Absent Reason]: http://hl7-fhir.github.io/valueset-observation-valueabsentreason.html
  [UCUM]: http://unitsofmeasure.org
  [LOINC]: http://loinc.org
  [US Laboratory Observation Profile Observation Name Codes]:/uslab-obs-codes.html
  [ObservationStatus]: http://hl7-fhir.github.io/valueset-observation-status.html