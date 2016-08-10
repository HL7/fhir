### Complete Summary of the Mandatory Requirements

1.  One status in `Observation.status`which has an [extensible](http://hl7-fhir.github.io/terminologies.html#extensible) binding to:
    -    [ObservationStatus] value set.
1.  One category in `Observation.category` which must have:
    -   a fixed `Observation.category.coding.system`="http://hl7.org/fhir/observation-category"
    -   a fixed `Observation.category.coding.code`=“vital-signs”

1.  A code in `Observation.code`
    -   a fixed `Observation.code.coding.system`=“http://loinc.org”
    -   a LOINC code in `Observation.code.coding.code` which has an [extensible](http://hl7-fhir.github.io/terminologies.html#extensible) binding to:
         -   [Vital Signs] value set.
                
1.  One patient in `Observation.subject`
1.  A date and time in `effectiveDateTime` or `effectivePeriod`
1.  Either one `Observation.valueQuantity` or, if there is no value, one code in `Observation.DataAbsentReason`
    -   Each Observation.valueQuantity must have:
        -   One numeric value in `Observation.valueQuantity.value`
        -   a fixed `Observation.valueQuantity.system`="http://unitsofmeasure"
        -   a UCUM unit code in `Observation.valueQuantity.code` which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
             -   [Vital Signs Units] value set.
    -   Observation.DataAbsentReason is bound to [Observation Value
        Absent Reason] value set.

1.  When using a panel code to group component observations (Note: See
    the comments regarding blood pressure in the table above), one or
    more `Observation.component.code` each of which must have:
    -   a fixed
        `Observation.component.code.coding.system=“<http://loinc.org>”`
         -   a LOINC code in `Observation.code.coding.code` which has an [extensible](http://hl7-fhir.github.io/terminologies.html#extensible) binding to:
             -   [Vital Signs] value set.

1.  Either one `Observation.component.valueQuantity` or, if there is
    no value, one code in `Observation.component.DataAbsentReason`
    -   Each Observation.component.valueQuantity must have:
        -   One numeric value in
            `Observation.component.valueQuantity.value`
        -   a fixed `Observation.component.valueQuantity.system`=“<http://unitsofmeasure.org>”
        -   a UCUM unit code in
            `Observation.component.valueQuantity.code` which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
             -   [Vital Signs Units] value set.
    -   Observation.component.DataAbsentReason is bound to [Observation
        Value Absent Reason] value set.

1.  When using a panel code to group observations, one or more reference
    to Observations in `Observation.related.target`
    -   a fixed `Observation.related.type`=“has-member”

 [Vital Signs]: valueset-daf-observation-CCDAVitalSignResult.html
  [Vital Signs Units]: http://hl7-fhir.github.io/valueset-ucum-vitals-common.html
  [extensible bindings]: Implementation_Guide#Extensible_binding_for_CodeableConcept_Datatype "wikilink"
  [using multiple codes]: Implementation_Guide#Using_multiple_codes_with_CodeableConcept_Datatype "wikilink"
  [ObservationStatus]: http://hl7-fhir.github.io/valueset-observation-status.html
 [Observation Value Absent Reason]: http://hl7-fhir.github.io/valueset-observation-valueabsentreason.html

  