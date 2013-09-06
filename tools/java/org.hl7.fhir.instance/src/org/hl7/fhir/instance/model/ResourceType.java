package org.hl7.fhir.instance.model;

public enum ResourceType {
    Condition,
    Supply,
    Organization,
    Group,
    ValueSet,
    Coverage,
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
    ImmunizationProfile,
    RelatedPerson,
    Specimen,
    DeviceObservation,
    Alert,
    Patient,
    AdverseReaction,
    DiagnosticOrder,
    Appointment,
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
    case Coverage:
      return "coverage";
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
    case ImmunizationProfile:
      return "immunizationprofile";
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
    case InterestOfCare:
      return "interestofcare";
    case Binary:
      return "binary";
    }
      return null;
  }

}
