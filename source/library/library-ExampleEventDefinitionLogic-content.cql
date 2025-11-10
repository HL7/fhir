library ExampleEventDefinitionLogic

using FHIR

include FHIRHelpers

codesystem "LOINC": 'http://loinc.org'

code "LA6577-6|http://loinc.org": 'LA6577-6' from "LOINC"

valueset "Stick Test Codes": 'http://example.org/ValueSet/stick-test-codes'

context Patient

parameter %context Encounter

define StickTest:
  [Observation: "Stick Test Codes"] O
    where O.status in { 'final', 'amended', 'corrected' }

// These functions are all candidates for [FHIRCommon](https://hl7.org/fhir/uv/cql/Library-FHIRCommon.html)
// Or perhaps a FHIRCommonObservation library
define fluent function negative(observations List<Observation>):
  observations O
    where O.interpretation ~ "LA6577-6|http://loinc.org"

define fluent function during(observations List<Observation>, encounter Encounter):
  observations O
    where O.issued during minute of encounter.period

define fluent function within(observations List<Observation>, quantity Quantity):
  observations O
    where (O.issued + quantity) on or after minute of Now()

define fluent function consecutively(observations List<Observation>):
  observations O
    sort by issued

define fluent function consecutivelyAfter(observations List<Observation>, observation Observation):
  observations O
    where O.issued on or after observation.issued
    sort by issued