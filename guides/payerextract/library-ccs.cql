library CCS version '1'

using FHIR // version '1.4'

/*

HPV Test      	Indicates an HPV test was performed on the indicated date of service.
                If this is present within 4 days of a cervical cytology screening, that screening is valid for 5 years.
                If this is not present within 4 days of a cervical cytology screening, that screening is only valid for 3 years.
                "HPV Tests"
                CPT, LOINC
                5 years

Pap Smear       Indicates a Pap Smear was provided on the indicated date of service, must include result for compliance
                "Cervical Cytology"
                CPT, HCPCS, LOINC
                5 years

Hx Hysterectomy	Date of total hysterectomy
                "Absence of Cervix"
                CPT, ICD10PCS, ICD10CM
                Unlimited

*/

valueset "HPV Tests": 'HPV Tests'
valueset "Cervical Cytology": '2.16.840.1.113883.3.464.1004.1208'
valueset "Hysterectomy": 'Hysterectomy'
valueset "Absence of Cervix": 'Absence of Cervix'

parameter MeasurementPeriod Interval<DateTime>

context Patient

define "Lookback Interval Five Years":
  Interval[start of MeasurementPeriod - 5 years, end of MeasurementPeriod]

// TODO: Need to verify the demographic here
define "In Demographic":
    Patient.gender.value = 'female'
        and AgeInYearsAt(start of MeasurementPeriod) >= 40

/* Pap Smear       Indicates a Pap Smear was provided on the indicated date of service, must include result for compliance */

define "Pap Smear Procedure":
  [Procedure: "Cervical Cytology"] H
    where H.status.value = 'completed'
        and H.performedDateTime.value during "Lookback Interval Five Years"

define "Pap Smear DiagnosticReport":
  [DiagnosticReport: "Cervical Cytology"] R
    where R.status.value = 'completed'
        and R.effectiveDateTime.value during "Lookback Interval Five Years"

define "Pap Smear Observation":
  [Observation: "Cervical Cytology"] R
    where R.status.value in { 'final', 'amended' }
      and R.effectiveDateTime.value during "Lookback Interval Five Years"

/*
  HPV Test      	Indicates an HPV test was performed on the indicated date of service.
*/

define "HPV Test Procedure":
  [Procedure: "HPV Tests"] H
    where H.status.value = 'completed'
        and H.performedDateTime.value during "Lookback Interval Five Years"

define "HPV Test DiagnosticReport":
  [DiagnosticReport: "HPV Tests"] R
    where R.status.value = 'completed'
        and R.effectiveDateTime.value during "Lookback Interval Five Years"

define "HPV Test Observation":
  [Observation: "HPV Tests"] R
    where R.status.value in { 'final', 'amended' }
      and R.effectiveDateTime.value during "Lookback Interval Five Years"

/* Hx Hysterectomy	Date of total hysterectomy */

define "Hysterectomy Procedure":
  [Procedure: "Hysterectomy"] A
    where A.status.value = 'completed'

define "Hx Hysterectomy Procedure":
  [Procedure: "Absence of Cervix"] A
    where A.status.value = 'completed'

