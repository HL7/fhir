package org.hl7.fhir.instance.model;

public enum ResourceType {
    Organization,
    Group,
    ValueSet,
    Coverage,
    Test,
    MedicationDispense,
    MedicationPrescription,
    DeviceLog,
    DeviceCapabilities,
    MedicationStatement,
    Protocol,
    Questionnaire,
    OperationOutcome,
    Conformance,
    Other,
    Profile,
    Visit,
    DocumentReference,
    Immunization,
    Problem,
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
    Study,
    MedicationAdministration,
    SecurityEvent,
    List,
    Picture,
    Document,
    Message,
    FamilyHistory,
    Location,
    AllergyIntolerance,
    Observation,
    ImmunizationProfile,
    Specimen,
    DeviceObservation,
    Alert,
    Patient,
    AdverseReaction,
    DiagnosticOrder,
    Appointment,
    InterestOfCare,
    RelatedPerson,
    Binary;

    public String getPath() {;
      switch (this) {
    case Organization:
      return "organization";
    case Group:
      return "group";
    case ValueSet:
      return "valueset";
    case Coverage:
      return "coverage";
    case Test:
      return "test";
    case MedicationDispense:
      return "medicationdispense";
    case MedicationPrescription:
      return "medicationprescription";
    case DeviceLog:
      return "devicelog";
    case DeviceCapabilities:
      return "devicecapabilities";
    case MedicationStatement:
      return "medicationstatement";
    case Protocol:
      return "protocol";
    case Questionnaire:
      return "questionnaire";
    case OperationOutcome:
      return "operationoutcome";
    case Conformance:
      return "conformance";
    case Other:
      return "other";
    case Profile:
      return "profile";
    case Visit:
      return "visit";
    case DocumentReference:
      return "documentreference";
    case Immunization:
      return "immunization";
    case Problem:
      return "problem";
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
    case Study:
      return "study";
    case MedicationAdministration:
      return "medicationadministration";
    case SecurityEvent:
      return "securityevent";
    case List:
      return "list";
    case Picture:
      return "picture";
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
    case RelatedPerson:
      return "relatedperson";
    case Binary:
      return "binary";
    }
      return null;
  }

}
