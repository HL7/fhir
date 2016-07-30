Both the MedicationOrder and [MedicationStatement] resources can be used to record a patient's medication.   For more information about the context for their usages, refer to the medication domains's [boundaries section].  This profile sets minimum expectations for use of the MedicationOrder resource to record, search and fetch medications associated with a patient within the DAF FHIR IG. It identifies which core elements, extensions, vocabularies and value sets must be Supported by clients and servers. For the definition of Supported please refer to DAF FHIR IG. The data elements identified by the profile are based on ONC 2015 Edition Common Clinical Data Set(CCDS).


##### Mandatory Data Elements and Terminology


The following data-elements are mandatory (i.e data MUST be present). These are presented below in a simple human-readable explanation.  Profile specific guidance and an [example](#example) are provided as well.  The [**Formal Profile Definition**](#profile) below provides the  formal summary, definitions, and  terminology requirements.  

**Each MedicationOrder must have:**

1.  a date for when written
1.  a status
1.  a patient
1.  a prescriber
1.  a medication


**Profile specific implementation guidance:**

*  The MedicationStatement and MedicationOrder resources can represent a medication, using either a code or reference to a [Medication] resource.  The server application can choose one way or both methods,  but the client application must support both methods.  More specific guidance is provided in the [Conformance] resource for this profile

* Additional elements from [DAF MedicationOrder Profile](daf-MedicationOrder.html) may be present.

 [DAF MedicationStatement Profile]: http://hl7.org/fhir/us/daf/medicationstatement-daf.html
  [DAF MedicationOrder Profile]: http://hl7.org/fhir/us/daf/medicationorder-daf.html
  [Medication Clinical Drug (RxNorm)]: valueset-daf-medication-codes.html
  [MedicationOrderStatus]: http://hl7.org/fhir/us/daf/valueset-medication-order-status.html
[MedicationStatementStatus]: http://hl7.org/fhir/us/daf/valueset-medication-statement-status.html
[MedicationStatement]:daf-core-medicationstatement.html
 [MedicationOrder]: daf-core-medicationorder.html
 [Medication]:daf-core-medication.html
 [Conformance]: daf-core-medicationstatement-conformance.html
 [boundaries section]: http://hl7-fhir.github.io/medicationorder.html#bnr

 