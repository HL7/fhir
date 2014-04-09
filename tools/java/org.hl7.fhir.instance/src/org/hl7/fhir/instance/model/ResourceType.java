package org.hl7.fhir.instance.model;

public enum ResourceType {
    Condition,
    Supply,
    Organization,
    Group,
    ValueSet,
    ImmunizationRecommendation,
    Appointment,
    MedicationDispense,
    MedicationPrescription,
    Slot,
    AppointmentResponse,
    MedicationStatement,
    Composition,
    Questionnaire,
    OperationOutcome,
    Conformance,
    Media,
    Other,
    Profile,
    DocumentReference,
    Immunization,
    OrderResponse,
    ConceptMap,
    ImagingStudy,
    Practitioner,
    CarePlan,
    Provenance,
    Device,
    Query,
    Order,
    Procedure,
    Substance,
    DiagnosticReport,
    Medication,
    MessageHeader,
    DocumentManifest,
    Availability,
    MedicationAdministration,
    Encounter,
    SecurityEvent,
    List,
    DeviceObservationReport,
    FamilyHistory,
    Location,
    AllergyIntolerance,
    Observation,
    RelatedPerson,
    Specimen,
    Alert,
    Namespace,
    Patient,
    AdverseReaction,
    DiagnosticOrder,
    Binary;

    public String getPath() {;
      switch (this) {
    case Condition:
      return "condition";
    case Supply:
      return "supply";
    case Organization:
      return "organization";
    case Group:
      return "group";
    case ValueSet:
      return "valueset";
    case ImmunizationRecommendation:
      return "immunizationrecommendation";
    case Appointment:
      return "appointment";
    case MedicationDispense:
      return "medicationdispense";
    case MedicationPrescription:
      return "medicationprescription";
    case Slot:
      return "slot";
    case AppointmentResponse:
      return "appointmentresponse";
    case MedicationStatement:
      return "medicationstatement";
    case Composition:
      return "composition";
    case Questionnaire:
      return "questionnaire";
    case OperationOutcome:
      return "operationoutcome";
    case Conformance:
      return "conformance";
    case Media:
      return "media";
    case Other:
      return "other";
    case Profile:
      return "profile";
    case DocumentReference:
      return "documentreference";
    case Immunization:
      return "immunization";
    case OrderResponse:
      return "orderresponse";
    case ConceptMap:
      return "conceptmap";
    case ImagingStudy:
      return "imagingstudy";
    case Practitioner:
      return "practitioner";
    case CarePlan:
      return "careplan";
    case Provenance:
      return "provenance";
    case Device:
      return "device";
    case Query:
      return "query";
    case Order:
      return "order";
    case Procedure:
      return "procedure";
    case Substance:
      return "substance";
    case DiagnosticReport:
      return "diagnosticreport";
    case Medication:
      return "medication";
    case MessageHeader:
      return "messageheader";
    case DocumentManifest:
      return "documentmanifest";
    case Availability:
      return "availability";
    case MedicationAdministration:
      return "medicationadministration";
    case Encounter:
      return "encounter";
    case SecurityEvent:
      return "securityevent";
    case List:
      return "list";
    case DeviceObservationReport:
      return "deviceobservationreport";
    case FamilyHistory:
      return "familyhistory";
    case Location:
      return "location";
    case AllergyIntolerance:
      return "allergyintolerance";
    case Observation:
      return "observation";
    case RelatedPerson:
      return "relatedperson";
    case Specimen:
      return "specimen";
    case Alert:
      return "alert";
    case Namespace:
      return "namespace";
    case Patient:
      return "patient";
    case AdverseReaction:
      return "adversereaction";
    case DiagnosticOrder:
      return "diagnosticorder";
    case Binary:
      return "binary";
    }
      return null;
  }

}
