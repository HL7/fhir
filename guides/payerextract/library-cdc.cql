library CDC version '1'

using FHIR // version '1.4'

/*

CDC Observation:         Result Description:                                       HL7 Message:     NCQA Value Set Name:                  Code Types:     Time Range:
Heamoglobin A1C          A1c value, normal adult is generally less than 7.0.       ORU              "HbA1c Tests"                           CPT, LOINC      1 year
Observation              Provide numbers only, units are assumed.                                   "HbA1c Level Less than 7.0"
                                                                                                    "HbA1c Level 7.0-9.0"
                                                                                                    "HbA1c Level Greater Than 9.0"

ARB                      Indicates that the provider prescribed an                 RDE              Not in the value set, available         NDC             1 year
Medication               ARB on the date of service                                                 from ncqa.org as "CDC-L" in doc
                                                                                                    or xls format

ACE                      Indicates that the provider prescribed an                 RDE              Not in the value set, available         NDC             1 year
Medication               ACE on the date of service                                                 from ncqa.org as "CDC-L" in doc
                                                                                                    or xls format

Dilated Eye Exam         Indicated a Dilated Eye Exam was Performed                ORU              "Diabetic Retinal Screening"            CPT, HCPCS      2 years
Procedure                                                                                         "Diabetic Retinal Screening Negative"
                                                                                                    "Diabetic Retinal Screening With Eye Care Professional"

Macroalbumin             Positive or negative value, normal adults are negative    ORU              "Urine Protein Tests"                   CPT, LOINC      N/A
Observation

Macroalbumin             Microalbumin level in urine, less than 30 is              ORU              "Urine Protein Tests"                   CPT, LOINC      1 year
Observation              normal range.  Provide numbers only,
                         units are assumed.

Retinopathy Positive     1 = Diabetic retinopathy detected on dilated/retinal      N/A              "Diabetic Retinal Screening"            CPT, HCPCS      2 years
Procedure              eye exam                                                                   "Diabetic Retinal Screening Negative"
                                                                                                    "Diabetic Retinal Screening With Eye Care Professional"

*/

// NCQA Value Sets
// https://ecqi.healthit.gov/system/files/ecqm/2014/EP/measures/CMS122v3.html
// "Laboratory Test, Result: HbA1c Laboratory Test" using "HbA1c Laboratory Test Grouping Value Set (2.16.840.1.113883.3.464.1003.198.12.1013)"
valueset "HbA1c Tests": '2.16.840.1.113883.3.464.1004.1116'
valueset "HbA1c Level Less than 7.0": '2.16.840.1.113883.3.464.1004.1115'
valueset "HbA1c Level 7.0-9.0": '2.16.840.1.113883.3.464.1004.1113'
valueset "HbA1c Level Greater Than 9.0": '2.16.840.1.113883.3.464.1004.1114'

valueset "Diabetic Retinal Screening": '2.16.840.1.113883.3.464.1004.1078'
valueset "Diabetic Retinal Screening Negative": '2.16.840.1.113883.3.464.1004.1079'
valueset "Diabetic Retinal Screening With Eye Care Professional": '2.16.840.1.113883.3.464.1004.1080'

// https://ecqi.healthit.gov/system/files/ecqm/2014/EP/measures/CMS134v4_1.html
// "Laboratory Test, Performed: Urine Protein Tests" using "Urine Protein Tests Grouping Value Set (2.16.840.1.113883.3.464.1003.109.12.1024)"
// "Medication, Active: ACE Inhibitor or ARB" using "ACE Inhibitor or ARB Grouping Value Set (2.16.840.1.113883.3.526.3.1139)"
valueset "Urine Macroalbumin Tests": '2.16.840.1.113883.3.464.1004.1257'
valueset "Positive Urine Macroalbumin Tests": '2.16.840.1.113883.3.464.1004.1216'
valueset "Urine Protein Test": 'Urine Protein Test'

parameter MeasurementPeriod Interval<DateTime>

context Patient

define "Lookback Interval One Year":
  Interval[start of MeasurementPeriod - 1 years, end of MeasurementPeriod]

define "Lookback Interval Two Years":
  Interval[start of MeasurementPeriod - 2 years, end of MeasurementPeriod]

// TODO: Need to verify the demographic here
define "In Demographic":
    AgeInYearsAt(start of MeasurementPeriod) >= 40

// Heamoglobin A1C Observation

define "Heamoglobin A1C Tests":
  [Observation : "HbA1c Tests"] T
    where T.status.value in { 'final', 'amended' }
      and T.effectiveDateTime.value during "Lookback Interval One Year"

define "Heamoglobin A1C Level Less than 7.0":
  [Observation : "HbA1c Level Less than 7.0"] H
    where H.status.value in { 'final', 'amended' }
      and H.effectiveDateTime.value during "Lookback Interval One Year"

define "Heamoglobin A1C Level 7.0-9.0":
  [Observation: "HbA1c Level 7.0-9.0"] H
    where H.status.value in { 'final', 'amended' }
      and H.effectiveDateTime.value during "Lookback Interval One Year"

define "Heamoglobin A1C Level Greater than 9.0":
  [Observation: "HbA1c Level Greater Than 9.0"] H
    where H.status.value in { 'final', 'amended' }
      and H.effectiveDateTime.value in "Lookback Interval One Year"

// Angiotensin II receptor blockers (ARB) Medication Order

define "ARB Medication Order":
  [MedicationOrder : "ARB"] A
    where A.status.value = 'completed'
      and A.dateWritten.value during "Lookback Interval One Year"

// Angiotensin Converting Enzyme (ACE) Medication Order
// The following list of medications is from the CDC-L_2015(final).xls document found here:
// https://www.ncqa.org/HEDISQualityMeasurement/HEDISMeasures/HEDIS2015/HEDIS2015NDCLicense/HEDIS2015FinalNDCLists.aspx
// Possible ACE medications to consider:
// Lotensin
// Benazepril Hydrochloride
// Captopril
// Epaned
// Enalapril Maleate
// Vasotec
// Fosinopril Sodium
// Prinivil
// Lisinopril
// Zestril
// Univasc
// Moexipril Hydrochloride
// Aceon
// Perindopril Erbumine
// Quinapril Hydrochloride
// Accupril
// Ramipril
// Altace
// Mavik
// Trandolapril

define "ACE Medication Order":
  [MedicationOrder : "ACE"] A
    where A.status.value = 'completed'
      and A.dateWritten.value during "Lookback Interval One Year"

// Dilated Eye Exam Observation

define "Diabetic Retinal Screening Eye Exam":
  [Procedure : "Diabetic Retinal Screening"] D
    where D.status.value = 'completed'
      and D.performedDateTime.value during "Lookback Interval Two Years"

define "Diabetic Retinal Screening Negative Eye Exam":
  [Procedure : "Diabetic Retinal Screening Negative"] D
    where D.status.value = 'completed'
      and D.performedDateTime.value during "Lookback Interval Two Years"

define "Diabetic Retinal Screening With Eye Care Professional Eye Exam":
  [Procedure : "Diabetic Retinal Screening With Eye Care Professional"] D
    where D.status.value = 'completed'
      and D.performedDateTime.value during "Lookback Interval Two Years"

// Macroalbumin Observation

define "Macroalbumin Results":
  [Observation: "Urine Macroalbumin Tests"] M
    where M.status.value in { 'final', 'amended' }
      and M.effectiveDateTime.value during "Lookback Interval One Year"

define "Positive Macroalbumin Tests":
  [Observation: "Positive Urine Macroalbumin Tests"] M
    where M.status.value in { 'final', 'amended' }
      and M.effectiveDateTime.value during "Lookback Interval One Year"

// Retinopathy Positive

define "Diabetic Retinopathy Detected":
  [Observation : "Diabetic Retinal Screening"] D
    where D.status.value in { 'final', 'amended' }
      and D.effectiveDateTime.value during "Lookback Interval Two Years"
