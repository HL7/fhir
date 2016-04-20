library WCCABA version '1'

using FHIR // version '1.4'

/*

BMI Percent	 WCC, ABA	 BMI percentile measurements
"BMI Percentile"
ICD10CM
2 years

Body Mass Index (BMI) Value	 ABA	 value identifying body mass
"BMI Value"
ICD10CM
2 years

Height	ABA, WCC	Value of height plus units, ie 70 inches or 5 feet 10 inches
No value set, but must be retrieved for ABA compliance data to be used in a standard supplmenetal data source according to NCQA General Guideline 34 under the heading 'Required Data Elements'
2 years

Weight	ABA, WCC	Value of weight plus units, ie 85 kg or 200 lbs
No value set, but must be retrieved for ABA compliance data to be used in a standard supplmenetal data source according to NCQA General Guideline 34 under the heading 'Required Data Elements'
2 years

Uses the DAF Profiles for Body Height/Body Weight
*/

valueset "BMI Percentile": 'BMI Percentile'
valueset "BMI Value": 'BMI Value'
valueset "Body Height": 'Body Height' // LOINC 8302-2
valueset "Body Weight": 'Body Weight' // LOINC 3141-9

parameter MeasurementPeriod Interval<DateTime>

context Patient

define "Lookback Interval Two Years":
  Interval[start of MeasurementPeriod - 2 years, end of MeasurementPeriod]

// TODO: Need to verify the demographic here
define "In Demographic":
    AgeInYearsAt(start of MeasurementPeriod) >= 18

/* BMI Percent	 BMI percentile measurements */

define "BMI Percent":
  [Observation: "BMI Percentile"] P
    where P.status.value in { 'final', 'amended' }
      and P.effectiveDateTime.value during "Lookback Interval Two Years"

/* Body Mass Index (BMI) Value	  value identifying body mass */

define "Body Mass Index (BMI) Value":
  [Observation: "BMI Value"] V
    where V.status.value in { 'final', 'amended' }
      and V.effectiveDateTime.value during "Lookback Interval Two Years"

/* Height */

define "Height":
  [Observation: "Body Height"] O
    where O.status.value in { 'final', 'amended' }
      and O.effectiveDateTime.value during "Lookback Interval Two Years"

/* Body Weight */
define "Weight":
  [Observation: "Body Weight"] O
    where O.status.value in { 'final', 'amended' }
      and O.effectiveDateTime.value during "Lookback Interval Two Years"

