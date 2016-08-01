# DAF-Core Home Page

## Introduction

DAF-Core IG focuses on defining the minimum mandatory requirements for recording, searching for and fetching patient information.  The data elements, extension and terminology identified by the various profiles in the DAF-Core IG are based on [ONC 2015 Edition Common Clinical Data Set (CCDS)] as well as essential administrative and conformance requirements.  The profiles described in this IG are used by the [DAF] and [DAF-Research] IGs and are intended to be leveraged as a common core by other US Realm implementations as well. 


## DAF-Core Profiles

The following table lists the [profiles] and the base FHIR Resources from which they were derived.  Each profile defines the minimum mandatory elements, extensions and terminology requirements that **MUST** be present.  For each profile requirements and quidance are given in a simple narrative summary.  A formal hierarchical table that presents a [logical view] of the content in both a differential and snapshot view is also provided. The necessary terminology and other profile artifacts included the profile [StructureDefinitions] are referenced and examples are provided.  

|DAF-Core Profile|Base Resource|
|-------------- |---------------|
|[AllergyIntolerance](daf-core-allergyintolerance.html)|AllergyIntolerance|
|[Assessment and Plan of Treatment](daf-core-careplan.html)|CarePlan|
|[CareTeam](daf-core-careteam.html)|CareTeam|
|[Condition](daf-core-condition.html)|Condition|
|[Device-UDI](daf-core-device.html)|Device|
|[Goal](daf-core-goal.html)|Goal|
|[Immunization](daf-core-immunization.html)|Immunization|
|[Lab DiagnosticReport](daf-core-diagnosticreport.html)|DiagnosticReport|
|[Lab Observation](daf-core-resultobs.html)|Observation|
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


## Extensions

The following table lists the DAF-Core extensions.  
	 
Extension | Used by DAF-core Profile | 
--------------------|---------------|
[ Allergies-VSD1](allergyintolerance-daf-core.html) | [AllergyIntolerance](daf-core-allergyintolerance.html)|


##  Terminology (may already have  List of all DAF core value sets: [terminologies](terminologies-daf-core.html-daf-core.html))
The tables in this this section list all the [Value sets], [CodeSystem],  [ConceptMap] and [NamingSystem] resources that are unique to DAF-Core and not part of the FHIR core standard.  

### ValueSets

The following table lists the DAF-Core value sets.  

 ValueSet| Used by DAF-core Profile | 
--------------------|---------------|
[ Allergies-VSD1](allergyintolerance-daf-core.html) | [AllergyIntolerance](daf-core-allergyintolerance.html)|


###  CodesSystems

 The following table lists the DAF-Core code systems.  
 
 CodeSystem| Used by DAF-core Profile | 
--------------------|---------------|
[ Allergies-VSD1](allergyintolerance-daf-core.html) | [AllergyIntolerance](daf-core-allergyintolerance.html)|

###  ConceptMaps

 The following table lists the DAF-Core concept maps.  
 
 ConceptMaps| Used by DAF-core Profile | 
--------------------|---------------|
[ Allergies-VSD1](allergyintolerance-daf-core.html) | [AllergyIntolerance](daf-core-allergyintolerance.html)|

###  NamingSystem

 The following table lists the DAF-Core naming systems.  
 
 NamingSystem| Used by DAF-core Profile | 
--------------------|---------------|
[ Allergies-VSD1](allergyintolerance-daf-core.html) | [AllergyIntolerance](daf-core-allergyintolerance.html)|



[DAF]: daf.html
[DAF-Core]: daf-core.html
[DAF-Research]: daf-research.html
[ONC]: http://www.healthit.gov/newsroom/about-onc 
[Data Access Framework (DAF)]: http://wiki.siframework.org/Data+Access+Framework+Homepage
[PCORnet]: http://www.pcornet.org/
[Argonaut]: http://argonautwiki.hl7.org/index.php?title=Main_Page
[ONC 2015 Edition Common Clinical Data Set (CCDS)]: https://www.healthit.gov/sites/default/files/2015Ed_CCG_CCDS.pdf
[profiles]: http://hl7-fhir.github.io/profiling.html
[logical view]: http://hl7-fhir.github.io/formats.html#table
[StructureDefinitions]: http://hl7-fhir.github.io/structuredefinition.html
[Value sets]: http://hl7-fhir.github.io/valueset.html
[CodeSystem]: http://hl7-fhir.github.io/codesystem.html
[ConceptMap]: http://hl7-fhir.github.io/conceptmap.html
[NamingSystem]: http://hl7-fhir.github.io/namingsystem.html




