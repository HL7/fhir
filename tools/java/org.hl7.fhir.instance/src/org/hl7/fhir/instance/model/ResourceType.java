package org.hl7.fhir.instance.model;

public enum ResourceType {
    Provenance,
    Condition,
    CarePlan,
    Supply,
    Device,
    Query,
    Order,
    Organization,
    Procedure,
    Substance,
    DiagnosticReport,
    Group,
    ValueSet,
    Medication,
    MessageHeader,
    ImmunizationRecommendation,
    DocumentManifest,
    MedicationDispense,
    MedicationPrescription,
    MedicationAdministration,
    Encounter,
    SecurityEvent,
    MedicationStatement,
    List,
    Questionnaire,
    Composition,
    DeviceObservationReport,
    OperationOutcome,
    Conformance,
    Media,
    FamilyHistory,
    Other,
    Profile,
    Location,
    Observation,
    AllergyIntolerance,
    DocumentReference,
    Immunization,
    RelatedPerson,
    Specimen,
    OrderResponse,
    Alert,
    ConceptMap,
    Patient,
    Practitioner,
    AdverseReaction,
    ImagingStudy,
    DiagnosticOrder,
    Binary;

    public String getPath() {;
      switch (this) {
    case Provenance:
      return "provenance";
    case Condition:
      return "condition";
    case CarePlan:
      return "careplan";
    case Supply:
      return "supply";
    case Device:
      return "device";
    case Query:
      return "query";
    case Order:
      return "order";
    case Organization:
      return "organization";
    case Procedure:
      return "procedure";
    case Substance:
      return "substance";
    case DiagnosticReport:
      return "diagnosticreport";
    case Group:
      return "group";
    case ValueSet:
      return "valueset";
    case Medication:
      return "medication";
    case MessageHeader:
      return "messageheader";
    case ImmunizationRecommendation:
      return "immunizationrecommendation";
    case DocumentManifest:
      return "documentmanifest";
    case MedicationDispense:
      return "medicationdispense";
    case MedicationPrescription:
      return "medicationprescription";
    case MedicationAdministration:
      return "medicationadministration";
    case Encounter:
      return "encounter";
    case SecurityEvent:
      return "securityevent";
    case MedicationStatement:
      return "medicationstatement";
    case List:
      return "list";
    case Questionnaire:
      return "questionnaire";
    case Composition:
      return "composition";
    case DeviceObservationReport:
      return "deviceobservationreport";
    case OperationOutcome:
      return "operationoutcome";
    case Conformance:
      return "conformance";
    case Media:
      return "media";
    case FamilyHistory:
      return "familyhistory";
    case Other:
      return "other";
    case Profile:
      return "profile";
    case Location:
      return "location";
    case Observation:
      return "observation";
    case AllergyIntolerance:
      return "allergyintolerance";
    case DocumentReference:
      return "documentreference";
    case Immunization:
      return "immunization";
    case RelatedPerson:
      return "relatedperson";
    case Specimen:
      return "specimen";
    case OrderResponse:
      return "orderresponse";
    case Alert:
      return "alert";
    case ConceptMap:
      return "conceptmap";
    case Patient:
      return "patient";
    case Practitioner:
      return "practitioner";
    case AdverseReaction:
      return "adversereaction";
    case ImagingStudy:
      return "imagingstudy";
    case DiagnosticOrder:
      return "diagnosticorder";
    case Binary:
      return "binary";
    }
      return null;
  }

}
