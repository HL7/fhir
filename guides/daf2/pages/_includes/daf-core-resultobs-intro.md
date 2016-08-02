Laboratory results are grouped and summarized using the [DiagnosticReport] resource which reference Observation resources.  Each Observation resource represents an individual laboratory test and result value, a “nested” panel (such as a microbial susceptibility panel) which references other observations, or rarely a laboratory test with component result values. This profile sets minimum expectations for the Observation resource resource to record, search and fetch laboratory test results associated with a patient.  It identifies which core elements, extensions, vocabularies and value sets **SHALL** be present in the resource when using this profile. The data elements identified by the profile are based on ONC 2015 Edition Common Clinical Data Set (CCDS).


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each Observation must have:**

1.   a patient
1.   a [LOINC] code, if available, which tells you what is being measured
1.   a result value and, if the result value is a numeric quantity, a standard [UCUM] unit
1.   a status
1.   a category code of 'laboratory'
 
Each Observation *should* have:

1.   a time indicating when the measurement was taken
1.   a reference range if available


**Profile specific implementation guidance:**

* Additional codes that translate or map to the Observation code or category codes are allowed.  For example:
   -  providing both a local system codes and a LOINC code that it map to
   -  providing a more specific category codes such as "chemistry', SNOMED CT concepts, or system specific codes in addition to the "laboratory" category code.
* If there is no result then you have to supply a reason unless Observation is being used to group other results then there is no value. Instead, it includes composite values or references to other Observations
* Additional elements from [DAF Observation Profile](daf-Observation.html) may be present.

 [SNOMED CT]: http://snomed.info/sct
  [Observation Value Absent Reason]: http://hl7-fhir.github.io/valueset-observation-valueabsentreason.html
  [UCUM]: http://unitsofmeasure.org
  [LOINC]: http://loinc.org
[DiagnosticReport]:daf-core-diagnosticreport.html