#### Complete Summary of the Mandatory Requirements

1.  One patient reference in `Procedure.patient`
1.  One Identification of the procedure in `Procedure.code`which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
    -   [SNOMED CT] Value set or
    -   [CPT-4/HCPC for procedures] value set.

1.  A date or a time period in `Procedure.performedDateTime` or `Procedure.performedPeriod`
1.  A status code in Procedure.status which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
    -  `[ProcedureStatus]` value set.


  [SNOMED CT]: http://hl7.org/fhir/valueset-procedure-code.html
  [CPT-4/HCPC for procedures]: CPT-4/HCPC_for_procedures "wikilink"
  [ProcedureStatus]: http://hl7.org/fhir/valueset-procedure-status.html
  [DAF Procedure Profile]: http://hl7.org/fhir/daf/daf-Procedure.html
  [ICD-10-PCS codes]: ICD-10-PCS_codes "wikilink"
  [Code on Dental Procedures and Nomenclature (CDT Code)]: CDT_ValueSet "wikilink"
 
