library CMS9 version '4'

using FHIR // version '1.4'

codesystem "Example Data Elements": 'http://example.org/dataelements'
codesystem "Breastfeeding Intention Code System": 'http://example.org/codesystem-breastfeeding-intention'

valueset "Feeding Intention-Breast": '2.16.840.1.113762.1.4.1045.29'
valueset "Feeding Intention-Not-To-Breast": 'TBD' // Code '636111000124114' from "SNOMED-CT" display: 'Mother refuses to breastfeed (situation)'
valueset "Galactosemia": '2.16.840.1.113883.3.117.1.7.1.35'
valueset "Single Live Birth": '2.16.840.1.113883.3.117.1.7.1.25'
valueset "Single Live Born Newborn Born in Hospital": '2.16.840.1.113883.3.117.1.7.1.26'
valueset "Encounter Inpatient": '2.16.840.1.113883.3.666.5.307'
valueset "Estimated Gestational Age at Birth": '2.16.840.1.113762.1.4.1045.47'
valueset "Parenteral Nutrition": '2.16.840.1.113883.3.117.1.7.1.38'
valueset "Breast Milk": '2.16.840.1.113883.3.117.1.7.1.30'
valueset "Dietary Intake Other than Breast Milk": '2.16.840.1.113883.3.117.1.7.1.27'
valueset "Neonatal Intensive Care Unit (NICU)": '2.16.840.1.113883.3.117.1.7.1.75'
valueset "Patient Expired": '2.16.840.1.113883.3.117.1.7.1.309'
valueset "Discharge To Acute Care Facility": '2.16.840.1.113883.3.117.1.7.1.87'

valueset "Ethnicity CDCREC": '2.16.840.1.114222.4.11.837'
valueset "Payer SOP": '2.16.840.1.114222.4.11.3591'
valueset "Race CDCREC": '2.16.840.1.114222.4.11.836'
valueset "ONC Administrative Sex AdministrativeGender": '2.16.840.1.113762.1.4.1'

parameter "Measurement Period" Interval<DateTime>

// NOTE: The patient in context is the infant, not the mother
context Patient

define function ToInterval(period FHIR.Period):
    Interval[period."start".value, period."end".value]

define function ToQuantity(quantity FHIR.Quantity):
    System.Quantity { value: quantity.value.value, unit: quantity.unit.value }

define function ToConcept(concept FHIR.CodeableConcept):
    System.Concept {
        codes: concept.coding C return System.Code { code: C.code.value, system: C.system.value, version: C.version.value, display: C.display.value },
        display: concept.text.value
    }

define "Inpatient Encounter":
  [Encounter: "Encounter Inpatient"] Encounter
    where Encounter.status.value = 'finished'
      and duration in days of ToInterval(Encounter.period) <= 120
      and Encounter.period."end".value in "Measurement Period"

define "Gestational Age at Birth <= 37 weeks":
  [Observation: "Estimated Gestational Age at Birth"] Observation
    where Observation.status.value in { 'final', 'amended' }
      and ToQuantity(Observation.valueQuantity) >= 37 weeks

define "Single Live Birth Condition":
  ([Condition: "Single Live Birth"]
    union [Condition: "Single Live Born Newborn Born in Hospital"]) Condition
    where Condition.clinicalStatus.value = 'active'
      and Condition.verificationStatus.value = 'confirmed'

define "Infant Birth Date":
  Last("Single Live Birth Condition").abatementDateTime.value

define "Parenteral Nutrition Procedure":
  [Procedure: "Parenteral Nutrition"] Procedure
    where Procedure.status.value = 'completed'

define "Galactosemia Diagnosis":
  [Condition: "Galactosemia"] Condition
    where Condition.clinicalStatus.value = 'active'
      and Condition.verificationStatus.value = 'confirmed'

define "Live Birth Infants without Parenteral Nutrition":
  "Inpatient Encounter" Encounter
    with "Gestational Age at Birth <= 37 weeks" GestationalAge such that GestationalAge.effectiveDateTime.value in ToInterval(Encounter.period)
    with "Single Live Birth Condition" Birth such that Birth.onsetDateTime.value in ToInterval(Encounter.period)
    without "Galactosemia Diagnosis" Galactosemia such that Galactosemia.onsetDateTime.value in ToInterval(Encounter.period)
    without "Parenteral Nutrition Procedure" Nutrition such that Nutrition.performedDateTime.value in ToInterval(Encounter.period)

// Region: Initial Population 1

define "Initial Population 1":
  "Live Birth Infants without Parenteral Nutrition"

// EndRegion: Initial Population 1

// Region: Denominator 1

define "Denominator 1":
  true

// EndRegion: Denominator 1

// Region: Denominator Exclusions 1

define "Denominator Exclusions 1":
  "Inpatient Encounter" Encounter
    // TODO: Cannot perform this Location.type traversal because we cannot resolve references yet
    //where exists (Encounter.location Location where ToConcept(Location.type) in "Neonatal Intensive Care Unit (NICU)")
    where ((Encounter.hospitalization Hospitalization where ToConcept(Hospitalization.dischargeDisposition) in "Patient Expired") is not null)
      or ((Encounter.hospitalization Hospitalization where ToConcept(Hospitalization.dischargeDisposition) in "Discharge To Acute Care Facility") is not null)

// EndRegion: Denominator Exclusions 1

// Region: Numerator 1

define "Breast Milk Feeding":
  [MedicationAdministration: "Breast Milk"] Feeding
    where Feeding.status.value = 'completed'

define "Other Than Breast Milk Feeding":
  [MedicationAdministration: "Dietary Intake Other than Breast Milk"] OtherFeeding
    where OtherFeeding.status.value = 'completed'

define "Numerator 1":
  "Inpatient Encounter" Encounter
    with "Breast Milk Feeding" Feeding such that Feeding.effectiveTimeDateTime.value in ToInterval(Encounter.period)
    without "Other Than Breast Milk Feeding" OtherFeeding such that OtherFeeding.effectiveTimeDateTime.value in ToInterval(Encounter.period)

// EndRegion: Numerator 1

// Region: Numerator Exclusions 1
// NONE
// End Region: Numerator Exclusions 1

// Region: Denominator Exceptions 1
// NONE
// End Region: Denominator Exceptions 1

// Region: Stratification 1
// NONE
// End Region: Stratification 1

// Region: Initial Population 2

define "Initial Population 2":
  "Live Birth Infants without Parenteral Nutrition"

// EndRegion: Initial Population 2

// Region: Denominator 2

// EndRegion: Denominator 2

// Region: Denominator Exclusions 2

// NOTE: This assumes a value set exists, but since we are using the "Assessment" pattern in the connect-a-thon,
// we switch to the specific code definition below
//define "Intention Not To Breastfeed":
//  "Inpatient Encounter" Encounter
//    with [RiskAssessment: "Feeding Intention-Not-To-Breastfeed"] RiskAssessment
//      such that RiskAssessment.date in Encounter.period

// Using the specific code
define "Breastfeeding Intention Code":
  Code 'breastfeeding-intention' from "Example Data Elements"

define "Positive Breastfeeding Intention Code":
  Code 'positive' from "Breastfeeding Intention Code System" display 'Intention to breastfeed'

define "Negative Breastfeeding Intention Code":
  Code 'negative' from "Breastfeeding Intention Code System" display 'Intention not to breastfeed'

//define "Intention Not To Breastfeed":
//  "Inpatient Encounter" Encounter
//    with [RiskAssessment: "Intention Not To Breastfeed Code"] RiskAssessment
//      such that RiskAssessment.date.value in ToInterval(Encounter.period)

// Actually, the above won't work because what we really want is the "type", not the "method"
// That would ordinarily be fine, but "type" is represented as an extension right now (http://example.org/extensions/riskassessment-type)
// So... we'll retrieve all risk assessments and filter in the CQL to the ones with this assessment type

define "Breastfeeding Intention Assessment":
  [RiskAssessment] Assessment
    where exists (
        Assessment.extension Extension
            where Extension.url.value = 'http://example.org/extensions/riskassessment-type'
              and ToConcept(Extension.value.ofType(FHIR.CodeableConcept)) = "Breastfeeding Intention Code"
    )

define "Intention Not To Breastfeed":
  "Inpatient Encounter" Encounter
    with "Breastfeeding Intention Assessment" Assessment
      such that Assessment.date.value in ToInterval(Encounter.period)

define "Denominator Exclusions 2":
  "Denominator Exclusions 1"
    union "Intention Not To Breastfeed"

// EndRegion: Denominator Exclusions 2

// Region: Numerator 2

define "Numerator 2":
  "Numerator 1"

// EndRegion: Numerator 2

// Region: Numerator Exclusions 2
// NONE
// End Region: Numerator Exclusions 2

// Region: Denominator Exceptions 2
// NONE
// End Region: Denominator Exceptions 2

// Region: Stratification 2
// NONE
// End Region: Stratification 2

// Region: Supplemental Data

/* TODO: Update these for FHIR, these are the QDM data elements...
define "Ethnicity": ["Patient Characteristic Ethnicity": "Ethnicity CDCREC"]
define "Payer": ["Patient Characteristic Payer": "Payer SOP"]
define "Race": ["Patient Characteristic Race": "Race CDCREC"]
define "Sex": ["Patient Characteristic Sex": "ONC Administrative Sex AdministrativeGender"]
*/

// EndRegion: Supplemental Data
