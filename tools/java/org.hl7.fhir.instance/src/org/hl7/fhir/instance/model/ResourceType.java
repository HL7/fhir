package org.hl7.fhir.instance.model;

public enum ResourceType {
    Provenance,
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
    DeviceLog,
    MedicationPrescription,
    MedicationAdministration,
    DeviceCapabilities,
    SecurityEvent,
    MedicationStatement,
    List,
    Questionnaire,
    OperationOutcome,
    Picture,
    Conformance,
    Document,
    Message,
    FamilyHistory,
    Profile,
    Location,
    Observation,
    AllergyIntolerance,
    Visit,
    DocumentReference,
    Immunization,
    ImmunizationProfile,
    Problem,
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
    RelatedPerson,
    Binary;

    public String getPath() {;
      switch (this) {
    case Provenance:
      return "provenance";
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
    case DeviceLog:
      return "devicelog";
    case MedicationPrescription:
      return "medicationprescription";
    case MedicationAdministration:
      return "medicationadministration";
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
    case Visit:
      return "visit";
    case DocumentReference:
      return "documentreference";
    case Immunization:
      return "immunization";
    case ImmunizationProfile:
      return "immunizationprofile";
    case Problem:
      return "problem";
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
    case RelatedPerson:
      return "relatedperson";
    case Binary:
      return "binary";
    }
      return null;
  }

}
