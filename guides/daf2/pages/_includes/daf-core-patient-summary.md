#### Complete Summary of the Mandatory Requirements


1.  One or more medical record numbers in `Patient.identifier`
    -   each Patient.identifier must have:
        -   an `identifier.system`
        -   an `identifier.value` that is unique within the system.

2.  One or more names in `Patient.name`
    -   each Patient.name must have:
        -   a `name.family`
        -   a `name.given`

3.  One administrative gender in `Patient.gender`
    -   Patient.gender is bound to [AdministrativeGender] Value set (Code Set)

  [AdministrativeGender]: http://hl7-fhir.github.io/valueset-administrative-gender.html
  
  
#### Summary of the Must Suport Requirements

Additionally your system must return:

1.  A date of birth in `Patient.birthDate`
2.  One or more languages spoken in `Patient.communication.language` which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
    -    [Common Languages] 
3.  One or more races codes in [US Core Patient Race] extension which:
    - Must include at least one code from [US Core Race Category]
    - May include additional codes from [US Core Race]
 
4.  An ethnicitiy code in [US Core Patient Ethnicity] extension which which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to: 
    -  [US Core Ethnicity]
5.  One Birth Sex in the [US Core Patient Birth Sex] extension which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
    -   [US Core Birth Sex]
    

  [Patient.birthDate]: http://hl7.org/fhir/us/daf/daf-patient-definitions.html#daf-patient.Patient.birthDate
  [Patient.communication.language]: http://hl7.org/fhir/us/daf/daf-patient-definitions.html#daf-patient.Patient.communication.language
  [Common Languages]: http://hl7-fhir.github.io/valueset-languages.html
  [US Core Patient Birth Sex]: http://hl7-fhir.github.io/extension-us-core-birthsex.html
  [US Core Birth Sex]: http://hl7-fhir.github.io/valueset-usrealm-birthsex.html
  [US Core Patient Race]: http://hl7-fhir.github.io/v3/Race/vs.html
  [US Core Race Category]: valueset-daf-race.html
  [US Core Race]: http://hl7-fhir.github.io/extension-us-core-race.html
 [US Core Patient Ethnicity]: http://hl7-fhir.github.io/extension-us-core-ethnicity.html
 [US Core Ethnicity]: http://hl7-fhir.github.io/extension-us-core-ethnicity.html