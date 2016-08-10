#### Complete Summary of the Mandatory Requirements

1.  One patient reference in `Procedure.patient`
1.  A status code in Procedure.status which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
-  [ProcedureStatus] value set.
1.  One Identification of the procedure in `Procedure.code`which has an [required](http://hl7-fhir.github.io/terminologies.html#required) binding to:
    -   [SNOMED CT] Value set or
    -   [CPT-4/HCPC for procedures] value set.
1.  A date or a time period in `Procedure.performedDateTime` or `Procedure.performedPeriod`


  [SNOMED CT]: valueset-daf-procedure-type.html
  [CPT-4/HCPC for procedures]: valueset-daf-procedure-type.html
  [ICD-10-PCS codes]: http://www.icd10data.com/icd10pcs
  [Code on Dental Procedures and Nomenclature (CDT Code)]: http://www.ada.org/en/publications/cdt/
  [ProcedureStatus]: http://hl7-fhir.github.io/valueset-procedure-status.html