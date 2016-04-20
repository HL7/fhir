library CBP version '1'

using FHIR // version '1.4'

/*
Blood Pressure		value providing blood pressure
"Systolic  Less Than 140"
"Systolic Greater Than/Equal To 140"
"Diastolic Less Than 80"
"Diastolic 80-89"
"Diastolic Greater Than/Equal To 90"
CPT
1 year

Hx HTN	Earliest date practice was aware of hypertension
"Essential Hypertension"
ICD10CM
3 years

*/

/*
// These are valuesets containing CPT codes indicating these concepts
valueset "Systolic Less Than 140": '2.16.840.1.113883.3.464.1004.1243'
valueset "Systolic Greater Than/Equal To 140": '2.16.840.1.113883.3.464.1004.1242'
valueset "Diastolic Less Than 80": '2.16.840.1.113883.3.464.1004.1084'
valueset "Diastolic 80-89": '2.16.840.1.113883.3.464.1004.1082'
valueset "Diastolic Greater Than/Equal To 90": '2.16.840.1.113883.3.464.1004.1083'
valueset "Essential Hypertension": '2.16.840.1.113883.3.464.1004.1122'
*/

// Using the DAF profiles, we could also communicate the requirement with value-based filtering
valueset "Systolic Blood Pressure": 'Systolic Blood Pressure' // TODO: LOINC 8480-6
valueset "Diastolic Blood Pressure": 'Diastolic Blood Pressure' // TODO: LOINC 8462-4

parameter MeasurementPeriod Interval<DateTime>

context Patient

define "Lookback Interval One Year":
  Interval[start of MeasurementPeriod - 1 years, end of MeasurementPeriod]

define "Lookback Interval Three Years":
  Interval[start of MeasurementPeriod - 3 years, end of MeasurementPeriod]

// TODO: Need to verify the demographic here
define "In Demographic":
    AgeInYearsAt(start of MeasurementPeriod) >= 40

// Blood Pressure

/*
// Using NCQA CPT Code Groupings
define "BP: Systolic Less Than 140":
    [Observation: "Systolic Less Than 140"] O
        where O.status.value in { 'final', 'amended' }
            and O.effectiveDateTime.value during "Lookback Interval One Year"

define "BP: Systolic Greater Than/Equal To 140":
    [Observation: "Systolic Greater Than/Equal To 140"] O
        where O.status.value in { 'final', 'amended' }
            and O.effectiveDateTime.value during "Lookback Interval One Year"

define "BP: Diastolic Greater Less Than 80":
    [Observation: "Diastolic Less Than 80"] O
        where O.status.value in { 'final', 'amended' }
            and O.effectiveDateTime.value during "Lookback Interval One Year"

define "BP: Diastolic 80-89":
    [Observation: "Diastolic 80-89"] O
        where O.status.value in { 'final', 'amended' }
            and O.effectiveDateTime.value during "Lookback Interval One Year"

define "BP: Diastolic Greater Than/Equal To 90":
    [Observation: "Diastolic Greater Than/Equal To 90"] O
        where O.status.value in { 'final', 'amended' }
            and O.effectiveDateTime.value during "Lookback Interval One Year"

*/

// Using DAF Profiles (LOINC Code)
define "BP: Systolic":
    [Observation: "Systolic Blood Pressure"] O
        where O.status.value in { 'final', 'amended' }
            and O.effectiveDateTime.value during "Lookback Interval One Year"

define "BP: Diastolic":
    [Observation: "Diastolic Blood Pressure"] O
        where O.status.value in { 'final', 'amended' }
            and O.effectiveDateTime.value during "Lookback Interval One Year"

define "BP: Systolic Less Than 140":
    "BP: Systolic" S
        where S.valueQuantity.value.value < 140

define "BP: Systolic Greater Than/Equal To 140":
    "BP: Systolic" S
        where S.valueQuantity.value.value >= 140

define "BP: Diastolic Less Than 80":
    "BP: Diastolic" D
        where D.valueQuantity.value.value < 80

define "BP: Diastolic 80-89":
    "BP: Diastolic" D
        where D.valueQuantity.value.value between 80 and 89

define "BP: Diastolic Greater Than/Equal To 90":
    "BP: Diastolic" D
        where D.valueQuantity.value.value >= 90

// Hx HTN

define "Hypertension Diagnosis":
  [Condition: "Essential Hypertension"] H
    where H.clinicalStatus.value = 'active'
        and H.verificationStatus.value = 'confirmed'
        and H.onsetDateTime.value during "Lookback Interval Three Years"

// Date of first diagnosis of Hypertension
define "Hx HTN":
  First("Hypertension Diagnosis" D sort by D.onsetDateTime.value).dateRecorded.value

