package org.hl7.fhir.instance.model;

public enum ResourceType {
    Provenance,
    Condition,
    CarePlan,
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
    Coverage,
    MedicationDispense,
    MedicationPrescription,
    DeviceLog,
    MedicationAdministration,
    Encounter,
    DeviceCapabilities,
    SecurityEvent,
    MedicationStatement,
    List,
    Questionnaire,
    OperationOutcome,
    Picture,
    Conformance,
    Document,
    Media,
    Message,
    FamilyHistory,
    Profile,
    Location,
    Observation,
    AllergyIntolerance,
    DocumentReference,
    Immunization,
    ImmunizationProfile,
    RelatedPerson,
    Specimen,
    OrderResponse,
    DeviceObservation,
    Alert,
    Patient,
    Practitioner,
    AdverseReaction,
    ImagingStudy,
    DiagnosticOrder,
    Appointment,
    InterestOfCare,
    Binary;

    public String getPath() {;
      switch (this) {
    case Provenance:
      return "provenance";
    case Condition:
      return "condition";
    case CarePlan:
      return "careplan";
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
    case Coverage:
      return "coverage";
    case MedicationDispense:
      return "medicationdispense";
    case MedicationPrescription:
      return "medicationprescription";
    case DeviceLog:
      return "devicelog";
    case MedicationAdministration:
      return "medicationadministration";
    case Encounter:
      return "encounter";
    case DeviceCapabilities:
      return "devicecapabilities";
    case SecurityEvent:
      return "securityevent";
    case MedicationStatement:
      return "medicationstatement";
    case List:
      return "list";
    case Questionnaire:
      return "questionnaire";
    case OperationOutcome:
      return "operationoutcome";
    case Picture:
      return "picture";
    case Conformance:
      return "conformance";
    case Document:
      return "document";
    case Media:
      return "media";
    case Message:
      return "message";
    case FamilyHistory:
      return "familyhistory";
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
    case ImmunizationProfile:
      return "immunizationprofile";
    case RelatedPerson:
      return "relatedperson";
    case Specimen:
      return "specimen";
    case OrderResponse:
      return "orderresponse";
    case DeviceObservation:
      return "deviceobservation";
    case Alert:
      return "alert";
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
    case Appointment:
      return "appointment";
    case InterestOfCare:
      return "interestofcare";
    case Binary:
      return "binary";
    }
      return null;
  }

}
