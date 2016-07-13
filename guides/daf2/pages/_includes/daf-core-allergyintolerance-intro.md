#### U.S. Data Access Framework (DAF) Core AllergyIntolerance Profile


##### Scope and Usage

This profile sets minimum expectations for use of the AllergyIntolerance resource to record allergies/adverse events associated with a patient within the DAF FHIR IG. It identifies which core elements, extensions, vocabularies and value sets must be Supported by clients and servers. For the definition of Supported please refer to DAF FHIR IG. The data elements identified by the profile are based on ONC 2015 Edition Common Clinical Data Set(CCDS).


##### Mandatory Data Elements


The following data-elements are mandatory (i.e data MUST be present). These are presented below in simple human-readable explanation as well as profile specific implementation guidance.  The [**Formal Profile Definition**](#profile) below provides a formal summary and machine processable structuredefinition of the requirements, constraints and extensions of the AllergyIntolerance resource.  An [example](#example) is provided below to demonstrate the requirement.

**Each AllergyIntolerance must have:**

1.  a patient
2.  a code which tells you what the patient is allergic to
3.  a status of the allergy

**Profile specific implementation guidance:**

* Representing No Known Allergies: No Known Allergies will be represented using the DAF-AllergyIntolerance profile with appropriate negation code in AllergyIntolerence.substance.
* Additional elements from [DAF AllergyIntolerance Profile](daf-allergyintolerance.html) may be present.


##### Terminology

The following value sets ( code set ) are listed for each of the mandatory elements listed above that use codes.  They are presented alongside their binding strength which describes whether the codes are required or not. The [**Formal Profile Definition**](#profile) below provides a formal summary of all the terminology for this profile.

2.  a code which tells you what the patient is allergic to  - uses [ABC ValueSet]() which is [required]()
3.  a status of the allergy - uses [ABC ValueSet]() which is required but [extensible]() (note: for demo purposes)
