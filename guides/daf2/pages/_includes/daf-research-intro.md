# Introduction

DAF-Research IG will focus on enabling researchers to access data from multiple organizations in the context of Learning Health System (LHS). While DAF-Research IG can be applied to multiple use cases, the current requirements have been drawn heavily from PCORnet use cases and implementations. The capabilities described as part of the IG are intended to be leveraged to build our nation's data infrastructure for a Learning Health System. DAF-Research will leverage the DAF-Core IG which has been created with substantial feedback from the Argonaut project. 

# Guidance to the Reader
The following table will provide a roadmap for the reader.

|Topic of Interest|What it contains|Where can I find it|
|--------------------|--------------------------|---------------------|
|DAF-Research IG Background|The artifact provides background on LHS, PCORI and PCORnet activities.|Background[daf-research-intro.html]|
||||
||||

# Capabilities, Actors and Conformance Requirements

# Implementation Guidance 





## DAF-core profiles (FHIR resource):

|Profile Page|Base Resource|
|-------------- |---------------|
|[AllergyIntolerance](daf-core-allergyintolerance.html)|AllergyIntolerance|
|[CarePlan](daf-core-careplan.html)|CarePlan|
|[CareTeam](daf-core-careteam.html)|CareTeam|
|[Condition](daf-core-condition.html)|Condition|
|[Device-UDI](daf-core-device.html)|Device|
|[DiagnosticReport-Results](daf-core-diagnosticreport.html)|DiagnosticReport|
|[Goal](daf-core-goal.html)|Goal|
|[Immunization](daf-core-immunization.html)|Immunization|
|[Location](daf-core-location.html)|Location|
|[Medication](daf-core-medication.html)|Medication|
|[MedicationOrder](daf-core-medicationorder.html)|MedicationOrder|
|[MedicationStatement](daf-core-medicationstatement.html)|MedicationStatement|
|[Observation-Smokingstatus](daf-core-smokingstatus.html)|Observation|
|[Observation-Vitalsigns](daf-core-vitalsigns.html)|Observation|
|[Organization](daf-core-organization.html)|Organization|
|[Patient](daf-core-patient.html)|Patient|
|[Practitioner](daf-core-pract.html)|Practitioner|
|[Procedure](daf-core-procedure.html)|Procedure|


##  ValueSet definitions:( may already have  List of all DAF core value sets: [terminologies](terminologies-daf-core.html-daf-core.html))
  
 |No | VSD Title | Used by DAF-core Profile | VSD Page |
|----|--------------------|---------------|-------------- |
|1 | VSD1 | AllergyIntolerance |[ Allergies-VSD1](allergyintolerance-daf-core.html) |

 
## Extension definitions
	 
|No | Core Profile Title | Base Resource | Profile Page |
|----|--------------------|---------------|-------------- |
|1 | Allergies | AllergyIntolerance |[ Allergies ](allergyintolerance-daf-core.html) |
|2 | Assess and Plan | CarePlan |[ Assess and Plan ](careplan-daf-core.html) |


##  CodesSystem definitions
 
 |No | VSD Title | Used by DAF-core Profile | VSD Page |
|----|--------------------|---------------|-------------- |
|1 | VSD1 | AllergyIntolerance |[ Allergies-VSD1](allergyintolerance-daf-core.html) |
 
 etc... with:
 
	 * NamingSystem definitions
	 * ConceptMaps
	 * Examples
	 * Search Criteria
	 * Custom Search Parameters
	 * DAF-core conformance statements:







