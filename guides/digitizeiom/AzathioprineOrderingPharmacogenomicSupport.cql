library AzathioprineOrderingPharmacogenomicSupport version '1'

using FHIR // version '1.4'

/*

For each order of Azathioprine for a patient

  Select all records from the patient's records that contain the LOINC observation code: 79713-4 (TPMT gene)

    If no records were found
      Alert the user with the following message:
        "TPMT test is recommended before using a thiopurine (mercaptopurine, thioguanine, and azathioprine).
        A TPMT genotype test does not appear to have been ordered for this patient."

    Else if the records contain conflicting statuses: “LA25391-6^Normal Metabolizer^LN-ANS” and another of
    “LA10317-8^Intermediate Metabolizer^LN-ANS" or "LA9657-3^Poor Metabolizer^LN-ANS Metabolizer^LN-ANS”, alert
    the user with the following message:
      "There are two or more conflicting TPMT results for this patient which must be resolved.
      Consult a clinical pharmacist or laboratory professional to resolve."  TODO: Should we enable this consult in the CQL?

    Else if the records contain the status “LA10317-8^Intermediate Metabolizer^LN-ANS"
      Alert the user with the following messages:
        High Level Message: "Testing has been performed and indicates that this patient is at INCREASED risk
          for myelotoxicity with standard dosing of thiopurine therapy."
        Explanation: "Based on a test result, this patient is predicted to be an intermediate TPMT metabolizer.
          The patient is at risk for myelosuppression with normal doses of azathioprine. A normal starting dose
          of azathioprine (e.g. 2-3 mg/kg/day) should be reduced to 0.6 – 2 mg/kg/day. Please consult a
          <<recommended clinical professional for consultation, for example some institutions might direct to a clinical pharmacist>>.
          [The alert may also refer the user to additional information within the system.]"

    Else if the most recent record contains the status "LA9657-3^Poor Metabolizer^LN-ANS Metabolizer^LN-ANS”
      Alert the user with the following messages:
        High-level Message: "Testing has been performed and indicates that this patient is at VERY HIGH risk
          for myelotoxicity with standard dosing of thiopurine therapy"
        Explanation: "Based on the result, this patient is predicted to have low or absent TPMT activity.
          The patient is at high risk for life-threatening myelosuppression with normal doses of azathioprine.
          Azathioprine should be avoided, or if azathioprine is given, start by reducing the does by 10-fold and
          administer thrice weekly instead of daily. Please consult a
          <<recommended clinical professional for consultation, for example some institutions might direct to a clinical pharmacist>>..
          [The alert may also refer the user to additional information within the system.]"

*/

codesystem LOINC: 'http://loinc.org'
codesystem DiagnosticPriority: 'http://hl7.org/fhir/diagnostic-order-priority'

define "Needs TPMT Test":
  not exists ("TPMT Results")

define "Should Notify User of Conflicting Statuses":
  exists ("Normal Status")
  and exists ("Intermediate Status")
    or exists ("Poor Status")

define "Should Notify User to Lower Dosage":
  exists ("Intermediate Status")
    and not "Should Notify User of Conflicting Statuses"

define "Should Notify User of Poor Status in Most Recent Record":
  "Most Recent Record Poor Status"

define "Normal Status":
  "TPMT Results" T
    where T.valueCodeableConcept = Code { code: 'LA25391-6', system: 'http://loinc.org', display: 'Normal Metabolizer' }

define "Intermediate Status":
  "TPMT Results" T
    where T.valueCodeableConcept = Code { code: 'LA10317-8', system: 'http://loinc.org', display: 'Intermediate Metabolizer' }

define "Poor Status":
  "TPMT Results" T
    where T.valueCodeableConcept = Code { code: 'LA9657-3', system: 'http://loinc.org', display: 'Poor Metabolizer' }

define "Most Recent Record Poor Status":
  Last("TPMT Results" T sort by T.effectiveDateTime).valueCodeableConcept =
    Code { code: 'LA9657-3', system: 'http://loinc.org', display: 'Poor Metabolizer' }

define "Genotype Results for TPMT":
  Code '79713-4' from LOINC display 'TPMT gene product metabolic activity interpretation'

define "TPMT Results":
  [Observation: "Genotype Results for TPMT"]

define "Missing TPMT Test Alert":
  CommunicationRequest {
    sender: "CDS System",
    recipient: "Primary Provider",
    payload: {
      Tuple {
        content: 'TPMT test is recommended before using a thiopurine (mercaptopurine, thioguanine, and azathioprine).
        A TPMT genotype test does not appear to have been ordered for this patient.'
        },
      Tuple { content: "Create TPMT Test" }
    },
    status: 'proposed',
    encounter: "Active Azathioprine Order",
    scheduled: Now(),
    reason: "Missing TPMT Test",
    requestedOn: Now(),
    subject: Patient,
    priority: Code 'urgent' from DiagnosticPriority display 'Urgent'
  }

define "Create Diagnostic Order for TPMT Test":
  DiagnosticOrder {
    status: 'proposed',
    priority: Code 'urgent' from DiagnosticPriority display 'Urgent',
    subject: Patient,
    encounter: "Missing TPMT Test Alert",
    orderer: Practitioner,
    reason: "TPMT Results",
    event: {
      Tuple {
        status: 'proposed',
        description: "This event was raised because the patient has been prescribed Azathioprine without a Genotype Test",
        dateTime: Now(),
        actor: Device
      }
    },
    item: {
      Tuple {
        code: "Genotype Results for TPMT"
      }
    }
  }

define "Conflicting TPMT Test Results Alert":
  CommunicationRequest {
    sender: "CDS System",
    recipient: "Primary Provider",
    payload: {
      Tuple {
        content: 'There are two or more conflicting TPMT results for this patient which must be resolved.
        Consult a clinical pharmacist or laboratory professional to resolve.'
        },
      Tuple { content: "Conflicting TPMT Test Results"}
    },
    status: 'proposed',
    encounter: "TPMT Test Results",
    scheduled: Now(),
    reason: "Conflicting TPMT Test Results",
    requestedOn: Now(),
    subject: Patient,
    priority: Code 'routine' from DiagnosticPriority display 'Routine'
  }

define "Intermediate TPMT Metabolizer Detected Alert":
  CommunicationRequest {
    sender: "CDS System",
    recipient: "Primary Provider",
    payload: {
      Tuple {
        content: 'Testing has been performed and indicates that this patient is at INCREASED risk
        for myelotoxicity with standard dosing of thiopurine therapy.'
        },
      Tuple {
        content: 'Based on a test result, this patient is predicted to be an intermediate TPMT metabolizer.
          The patient is at risk for myelosuppression with normal doses of azathioprine. A normal starting dose
          of azathioprine (e.g. 2-3 mg/kg/day) should be reduced to 0.6 – 2 mg/kg/day. Please consult a
          <<recommended clinical professional for consultation, for example some institutions might direct to a clinical pharmacist>>.
          [The alert may also refer the user to additional information within the system.]'
        },
      Tuple { content: "Reduce Medication Dosage" }
    },
    status: 'proposed',
    encounter: "Active Azathioprine Order",
    scheduled: Now(),
    reason: "Intermediate TPMT Metabolizer Detected",
    requestedOn: Now(),
    subject: Patient,
    priority: Code 'urgent' from DiagnosticPriority display 'Urgent'
  }

define "Poor TPMT Metabolizer Detected Alert":
  CommunicationRequest {
    sender: "CDS System",
    recipient: "Primary Provider",
    payload: {
      Tuple {
        content: 'Testing has been performed and indicates that this patient is at VERY HIGH risk
          for myelotoxicity with standard dosing of thiopurine therapy'
        },
      Tuple {
        content: 'Based on the result, this patient is predicted to have low or absent TPMT activity.
          The patient is at high risk for life-threatening myelosuppression with normal doses of azathioprine.
          Azathioprine should be avoided, or if azathioprine is given, start by reducing the does by 10-fold and
          administer thrice weekly instead of daily. Please consult a
          <<recommended clinical professional for consultation, for example some institutions might direct to a clinical pharmacist>>..
          [The alert may also refer the user to additional information within the system.]'
        },
      Tuple { content: "Reduce Medication Dosage" }
    },
    status: 'proposed',
    encounter: "Active Azathioprine Order",
    scheduled: Now(),
    reason: "Poor TPMT Metabolizer Detected",
    requestedOn: Now(),
    subject: Patient,
    priority: Code 'urgent' from DiagnosticPriority display 'Urgent'
  }
