library CMS146 version '2'

/* CMS 146v2
 *
 * ============================================================================
 * QDM Logic
 * ============================================================================
 * Initial Patient Population =
 *     AND: "Patient Characteristic Birthdate: birth date" >= 2 year(s) starts before start of "Measurement Period"
 *     AND: "Patient Characteristic Birthdate: birth date" < 18 year(s) starts before start of "Measurement Period"
 *     AND:
 *         AND: "Occurrence A of Encounter, Performed: Ambulatory/ED Visit" during "Measurement Period"
 *         AND: "Medication, Order: Antibiotic Medications" <= 3 day(s) starts after start of "Occurrence A of Encounter, Performed: Ambulatory/ED Visit"
 *         AND:
 *             OR: "Occurrence A of Encounter, Performed: Ambulatory/ED Visit" during
 *                 OR: "Occurrence A of Diagnosis, Active: Acute Pharyngitis"
 *                 OR: "Occurrence A of Diagnosis, Active: Acute Tonsillitis"
 *             OR:
 *                 OR: "Occurrence A of Diagnosis, Active: Acute Pharyngitis"
 *                 OR: "Occurrence A of Diagnosis, Active: Acute Tonsillitis"
 *                 starts during "Occurrence A of Encounter, Performed: Ambulatory/ED Visit"
 *
 * Denominator =
 *     AND: "Initial Patient Population"
 *
 * Denominator Exclusions =
 *     AND: "Medication, Active: Antibiotic Medications" <= 30 day(s) starts before start of
 *         OR: "Occurrence A of Diagnosis, Active: Acute Pharyngitis"
 *         OR: "Occurrence A of Diagnosis, Active: Acute Tonsillitis"
 *
 * Numerator =
 *     AND:
 *         OR: "Laboratory Test, Result: Group A Streptococcus Test (result)" <= 3 day(s) starts before or during "Occurrence A of Encounter, Performed: Ambulatory/ED Visit"
 *         OR: "Laboratory Test, Result: Group A Streptococcus Test (result)" <= 3 day(s) starts after end of "Occurrence A of Encounter, Performed: Ambulatory/ED Visit"
 *
 * Denominator Exceptions =
 *     None
 * ============================================================================
 */

using FHIR

valueset "Acute Pharyngitis": '2.16.840.1.113883.3.464.1003.102.12.1011'
valueset "Acute Tonsillitis": '2.16.840.1.113883.3.464.1003.102.12.1012'
valueset "Ambulatory/ED Visit": '2.16.840.1.113883.3.464.1003.101.12.1061'
valueset "Antibiotic Medications": '2.16.840.1.113883.3.464.1003.196.12.1001'
valueset "Group A Streptococcus Test": '2.16.840.1.113883.3.464.1003.198.12.1012'

parameter MeasurementPeriod Interval<DateTime>

context Patient

define InDemographic:
    AgeInYearsAt(start of MeasurementPeriod) >= 2 and AgeInYearsAt(start of MeasurementPeriod) < 18

define Pharyngitis:
    [Condition: "Acute Pharyngitis"] union [Condition: "Acute Tonsillitis"]

define Antibiotics:
    [MedicationOrder: "Antibiotic Medications"]

define MeasurementPeriodEncounters:
    [Encounter: "Ambulatory/ED Visit"] E
        where InDemographic and Interval[E.period."start".value, E.period."end".value] during MeasurementPeriod

define PharyngitisEncounters:
    MeasurementPeriodEncounters E
        with Pharyngitis P
            such that Interval[P.onsetDateTime.value, P.abatementDateTime.value] includes Interval[E.period."start".value, E.period."end".value]
                or P.onsetDateTime.value in E.period
        with Antibiotics A such that A.dateWritten 3 days or less after start of E.period

define PharyngitisWithPriorAntibiotics:
    Pharyngitis P
        with Antibiotics A such that A.dateWritten 30 days or less before P.onsetDateTime

define ExcludedEncounters:
    PharyngitisEncounters E
        with PharyngitisWithPriorAntibiotics P
            such that Interval[P.onsetDateTime, P.abatementDate] includes E.period
                or P.onsetDateTime in E.period

define StrepTestEncounters:
    PharyngitisEncounters E
        with [DiagnosticReport: "Group A Streptococcus Test"] T
            such that T.result is not null
                and T.issued in Interval[start of E.period - 3 days, end of E.period + 3 days]

