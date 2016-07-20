This profile sets minimum expectations for use of the Patient resource to record, search and fetch basic demographics and other administrative information about an individual patient. It identifies which core elements, extensions, vocabularies and value sets must be Supported by clients and servers. For the definition of Supported please refer to DAF FHIR IG. The data elements identified by the profile are based on ONC 2015 Edition Common Clinical Data Set(CCDS).


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

*<a name="must_support"></a> **Must Support:**

In the context of DAF-core profiles, Supported on any data element SHALL be interpreted as follows: (NOTE: The definition of Supported is derived from HL7v2 concept "Required by may be empty - RE" described in [HL7v2 V28_CH02B_Conformance.doc]())

  1. Servers SHALL be capable of representing the data element in the Patient resource if the data is available in their system.
  1. Servers SHALL be capable of including the data element as part of the query results as specified by the DAF conformance resources.
  1. Clients SHALL be capable of processing resource instances containing the data elements.
  1. In situations where information on a particular data element is missing and the DAF Responder knows the precise reason for the absence of data. Servers SHALL send the reason for the missing information using values from the value set where they exist or using the [dataAbsentReason](http://hl7-fhir.github.io/extension-data-absent-reason.html) extension. 
  1. Clients SHALL be able to process resource instances containing data elements asserting missing information.
  1. NOTE: Servers or Clients who do not have the capability to store or return a data element listed as "Must Support" in the DAF-Core profiles can still claim conformance to the DAF profiles using the DAF conformance resources.

* Additional elements from [DAF Patient Profile](daf-Patient.html) may be present.