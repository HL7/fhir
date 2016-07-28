This profile sets minimum expectations for use of the Procedure resource to record, search and fetch procedures associated with a patient within the DAF FHIR IG. It identifies which core elements, extensions, vocabularies and value sets must be Supported by clients and servers. For the definition of Supported please refer to DAF FHIR IG. The data elements identified by the profile are based on ONC 2015 Edition Common Clinical Data Set(CCDS).


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each Procedure must have:**

1.  a patient
1.  a code that identifies the type of procedure performed on the patient
1.  when the procedure was performed
1.  a status

**Profile specific implementation guidance:**


- Based upon the 2015 Edition Certification Requirements, [ICD-10-PCS codes] MAY be supported as translations to either SNOMED-CT or CPT-4/HCPC. If choosing to primarily to dental procedures, the [Code on Dental Procedures and Nomenclature (CDT Code)] may be used.

* Additional elements from [DAF Procedure Profile](daf-Procedure.html) may be present.



  [SNOMED CT]: http://hl7.org/fhir/valueset-procedure-code.html
  [CPT-4/HCPC for procedures]: CPT-4/HCPC_for_procedures "wikilink"
  [ProcedureStatus]: http://hl7.org/fhir/valueset-procedure-status.html
  [DAF Procedure Profile]: http://hl7.org/fhir/daf/daf-Procedure.html
  [ICD-10-PCS codes]: ICD-10-PCS_codes "wikilink"
  [Code on Dental Procedures and Nomenclature (CDT Code)]: CDT_ValueSet "wikilink"
