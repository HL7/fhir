Laboratory results are grouped and summarized using the [DiagnosticReport] resource which reference [Observation] resource(s).  Each Observation resource represents an individual laboratory test and result value, a “nested” panel (such as a microbial susceptibility panel) which references other observations, or rarely a laboratory test with component result values.  This profile sets minimum expectations for the DiagnosticReport resource to record, search and fetch laboratory results associated with a patient. It identifies which core elements, extensions, vocabularies and value sets **SHALL** be present in the resource when using this profile.

**Example Usage Scenarios:**

The following are example usage scenarios for the DAF-DiagnosticReport profile:

-   Query for lab reports belonging to a Patient
-   Query for all patients who have had a specific lab test or panel available
-   Query for all patients with a lab test or panel issued by a particular provider

##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each DiagnosticReport must have:**

1.   a status
1.   a category code of 'LAB'
1.   a code (preferably a LOINC code) which tells you what is being measured
1.   a patient
1.   a time indicating when the measurement was taken
1.   a time indicating when the measurement was reported
1.   who issues the report
1.   at least one result (an Observation reference)


**Profile specific implementation guidance:**

* Additional codes that translate or map to the DiagnosticReport codes or category codes are allowed.  For example:
   -  providing both a local system codes and a LOINC code that it map to
   -  providing a more specific category codes such as “CH” (chemistry) in addition to the "LAB"  category code.


[Observation]:  http://hl7-fhir.github.io/observation.html
[DiagnosticReport]:  http://hl7-fhir.github.io/diagnosticreport.html