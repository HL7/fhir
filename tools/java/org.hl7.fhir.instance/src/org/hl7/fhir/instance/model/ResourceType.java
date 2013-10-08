package org.hl7.fhir.instance.model;

public enum ResourceType {
    Condition,
    Supply,
    Organization,
    Group,
    ValueSet,
    ImmunizationRecommendation,
    MedicationDispense,
    DeviceLog,
    MedicationPrescription,
    DeviceCapabilities,
    MedicationStatement,
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
    DeviceData,
    Device,
    Query,
    Order,
    Procedure,
    Substance,
    DiagnosticReport,
    Medication,
    DocumentManifest,
    MedicationAdministration,
    Encounter,
    SecurityEvent,
    List,
    Document,
    Message,
    FamilyHistory,
    Location,
    AllergyIntolerance,
    Observation,
    RelatedPerson,
    Specimen,
    DeviceObservation,
    Alert,
    Patient,
    AdverseReaction,
    DiagnosticOrder,
    Appointment,
    AssessmentDefinition,
    InterestOfCare,
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
    case MedicationDispense:
      return "medicationdispense";
    case DeviceLog:
      return "devicelog";
    case MedicationPrescription:
      return "medicationprescription";
    case DeviceCapabilities:
      return "devicecapabilities";
    case MedicationStatement:
      return "medicationstatement";
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
    case DeviceData:
      return "devicedata";
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
    case DocumentManifest:
      return "documentmanifest";
    case MedicationAdministration:
      return "medicationadministration";
    case Encounter:
      return "encounter";
    case SecurityEvent:
      return "securityevent";
    case List:
      return "list";
    case Document:
      return "document";
    case Message:
      return "message";
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
    case DeviceObservation:
      return "deviceobservation";
    case Alert:
      return "alert";
    case Patient:
      return "patient";
    case AdverseReaction:
      return "adversereaction";
    case DiagnosticOrder:
      return "diagnosticorder";
    case Appointment:
      return "appointment";
    case AssessmentDefinition:
      return "assessmentdefinition";
    case InterestOfCare:
      return "interestofcare";
    case Binary:
      return "binary";
    }
      return null;
  }

}
