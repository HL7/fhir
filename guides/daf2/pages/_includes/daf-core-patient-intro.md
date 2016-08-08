This profile sets minimum expectations for the [Patient] resource to record, search and fetch basic demographics and other administrative information about an individual patient. It identifies which core elements, extensions, vocabularies and value sets **SHALL** be present in the resource when using this profile.


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each Patient must have:**

1. a patient identifier (e.g. MRN)
1. a patient name
1. a gender

**2015 Edition Certification Requirements ([Must Support](#must_support)).**

In addition, based upon the 2015 Edition Certification Requirements, the following data-elements must be supported.
 
'''If the data is present, Patient shall include:'''

1. a birth sex
1. a birth date
1. a communication language
1. a race
1. an ethnicity


**Profile specific implementation guidance:**

* none

[Patient]: http://hl7-fhir.github.io/patient.html
