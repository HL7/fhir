This profile sets minimum expectations for the [Procedure] resource to record, search and fetch procedures associated with a patient. It identifies which core elements, extensions, vocabularies and value sets **SHALL** be present in the resource when using this profile.

**Example Usage Scenarios:**

The following are example usage scenarios for the DAF-Procedure profile:

-   Query for procedures performed on a Patient
-   Query for all patients who have had a specific procedure
-   Query for procedures performed during a time period


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each Procedure must have:**

1.  a patient
1.  a status
1.  a code that identifies the type of procedure performed on the patient
1.  when the procedure was performed


**Profile specific implementation guidance:**


 - Based upon the 2015 Edition Certification Requirements, either SNOMED-CT or CPT-4/HCPC procedure codes are requied and  [ICD-10-PCS codes] MAY be supported as translations to them. If choosing to primarily to dental procedures, the [Code on Dental Procedures and Nomenclature (CDT Code)] may be used.




  [SNOMED CT]: http://hl7.org/fhir/valueset-procedure-code.html
  [CPT-4/HCPC for procedures]: http://hl7.org/fhir/valueset-procedure-code.html
  [ICD-10-PCS codes]: http://www.icd10data.com/icd10pcs
  [Code on Dental Procedures and Nomenclature (CDT Code)]: http://www.ada.org/en/publications/cdt/
  [ProcedureStatus]: http://hl7-fhir.github.io/valueset-procedure-status.html
  [Procedure]: http://hl7-fhir.github.io/procedure.html
