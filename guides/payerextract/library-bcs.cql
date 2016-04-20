library BCS version '1'

using FHIR // version '1.4'

/*

Hx Mastectomy Bilat		Date of bilateral mastectomy	"Bilateral Mastectomy" "Unilateral Mastectomy" + "Bilateral Modifier"
"History of Bilateral Mastectomy"	CPT, ICD10CM, ICD10PCS	Unlimited

Hx Mastectomy Left	Date of left-sided unilateral mastectomy	"Unilateral Mastectomy" + "Left Modifier" same DOS
"Absence of Left Breast" "Unilateral Mastectomy Left" CPT, ICD10CM, ICD10PCS	Unlimited

Hx Mastectomy Right		Date of right-sided unilateral mastectomy		"Unilateral Mastectomy" + "Right Modifier" same DOS
"Absence of Right Breast" "Unilateral Mastectomy Right" CPT, ICD10CM, ICD10PCS	Unlimited

Mammography		Indicates mammography was provided on the indicated date of service		"Mammography"	CPT, HCPCS	27 months

*/

// NOTE: These valuesets are defined by the appropriate measures, refer to them for definitions
valueset "Bilateral Mastectomy": 'Bilateral Mastectomy'
valueset "Unilateral Mastectomy": 'Unilateral Mastectomy + Bilateral Modifier'
valueset "History of Bilateral Mastectomy": 'History of Bilateral Mastectomy'

valueset "Left Modifier": 'Left Modifier'
valueset "Absence of Left Breast": 'Absence of Left Breast'
valueset "Unilateral Mastectomy Left": 'Unilateral Mastectomy Left'

valueset "Right Modifier": 'Right Modifier'
valueset "Absence of Right Breast": 'Absence of Right Breast'
valueset "Unilateral Mastectomy Right": 'Unilateral Mastectomy Right'

valueset "Mammography": 'Mammography'

parameter MeasurementPeriod Interval<DateTime>

context Patient

define "Lookback Interval Twenty-Seven Months":
  Interval[start of MeasurementPeriod - 27 months, end of MeasurementPeriod]

// TODO: Need to verify the demographic here
define "In Demographic":
    Patient.gender.value = 'female'
        and AgeInYearsAt(start of MeasurementPeriod) >= 40

/* Hx Mastectomy Bilat		Date of bilateral mastectomy */

// performedDateTime
define "Bilateral Mastectomy Procedure":
  ([Procedure: "Bilateral Mastectomy"]
    union ([Procedure: "Unilateral Mastectomy + Bilateral Modifier"])
    union ([Procedure: "History of Bilateral Mastectomy"]) // NOTE: This assumes the history of a procedure is also recorded as a procedure in FHIR
  ) P
    where P.status.value = 'completed'

define "Hx Mastectomy Bilat":
    "Bilateral Mastectomy Procedure" P return P.performedDateTime

/* Hx Mastectomy Left	 Date of left-sided unilateral mastectomy */

define "Left Mastectomy Procedure":
  ([Procedure: "Unilateral Mastectomy + Left Modifier"]
    union ([Procedure: "Unilateral Mastectomy Left"])
  ) P
    where P.status.value = 'completed'

define "Left Mastectomy Observation":
    [Observation: "Absence of Left Breast"] O
        where O.status.value in { 'final', 'amended' }

define "Hx Mastectomy Left":
    First(
        (("Left Mastectomy Procedure" P return P.performedDateTime.value)
            union ("Left Mastectomy Observation" O return O.effectiveDateTime.value)
        ) MastectomyDates sort asc
    )

/* Hx Mastectomy Right		Date of right-sided unilateral mastectomy */

define "Right Mastectomy Procedure":
  ([Procedure: "Unilateral Mastectomy + Right Modifier"]
    union ([Procedure: "Unilateral Mastectomy Right"])
  ) P
    where P.status.value = 'completed'

define "Right Mastectomy Observation":
    [Observation: "Absence of Right Breast"] O
        where O.status.value in { 'final', 'amended' }

define "Hx Mastectomy Right":
    First(
        (("Right Mastectomy Procedure" P return P.performedDateTime.value)
            union ("Right Mastectomy Observation" O return O.effectiveDateTime.value)
        ) MastectomyDates sort asc
    )

/* Mammography		Indicates mammography was provided on the indicated date of service */

define "Mammography Procedure":
    [Procedure: "Mammography"] M
        where M.status.value = 'completed'
            and M.performedDateTime.value during "Lookback Interval Twenty-Seven Months"
