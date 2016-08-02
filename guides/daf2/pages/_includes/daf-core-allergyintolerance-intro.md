#### U.S. Data Access Framework (DAF) Core AllergyIntolerance Profile


##### Scope and Usage

This profile sets minimum expectations for the [AllergyIntolerance] resource to record, search and fetch allergies/adverse events associated with a patient.  It identifies the mandatory core elements, extensions, vocabularies and value sets which **SHALL** be present in the AllergyIntolerance resource when using this profile.



##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#summary) below provides the  formal summary, definitions, and  terminology requirements.  

**Each AllergyIntolerance must have:**

1.  a patient
2.  a code which tells you what the patient is allergic to. 
3.  a status of the allergy. 

**Profile specific implementation guidance:**

* Representing No Known Allergies: No Known Allergies will be represented using the DAF-AllergyIntolerance profile with appropriate negation code in AllergyIntolerence.code.
* Additional elements from [DAF AllergyIntolerance Profile](daf-allergyintolerance.html) may be present.


[AllergyIntolerance]: http://hl7-fhir.github.io/allergyintolerance.html

