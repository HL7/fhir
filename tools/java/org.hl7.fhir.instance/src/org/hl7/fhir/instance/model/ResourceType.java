package org.hl7.fhir.instance.model;

public enum ResourceType {
    Appointment,
    ReferralRequest,
    Provenance,
    Questionnaire,
    Query,
    DocumentManifest,
    Specimen,
    AllergyIntolerance,
    CarePlan,
    MedicationPrescription,
    OperationOutcome,
    FamilyHistory,
    Medication,
    Procedure,
    List,
    ConceptMap,
    Subscription,
    ValueSet,
    OperationDefinition,
    DocumentReference,
    Order,
    Availability,
    Immunization,
    SecurityEvent,
    Device,
    Media,
    Conformance,
    RelatedPerson,
    Namespace,
    Practitioner,
    AppointmentResponse,
    Observation,
    MedicationAdministration,
    DeviceObservationReport,
    Slot,
    Contraindication,
    MedicationStatement,
    RiskAssessment,
    Group,
    Organization,
    MedicationDispense,
    Supply,
    DiagnosticReport,
    ImagingStudy,
    Profile,
    DataElement,
    QuestionnaireAnswers,
    AdverseReaction,
    Encounter,
    Substance,
    Condition,
    Composition,
    DiagnosticOrder,
    Patient,
    OrderResponse,
    Alert,
    MessageHeader,
    ImmunizationRecommendation,
    Location,
    Other,
    Binary;

    public String getPath() {;
      switch (this) {
    case Appointment:
      return "appointment";
    case ReferralRequest:
      return "referralrequest";
    case Provenance:
      return "provenance";
    case Questionnaire:
      return "questionnaire";
    case Query:
      return "query";
    case DocumentManifest:
      return "documentmanifest";
    case Specimen:
      return "specimen";
    case AllergyIntolerance:
      return "allergyintolerance";
    case CarePlan:
      return "careplan";
    case MedicationPrescription:
      return "medicationprescription";
    case OperationOutcome:
      return "operationoutcome";
    case FamilyHistory:
      return "familyhistory";
    case Medication:
      return "medication";
    case Procedure:
      return "procedure";
    case List:
      return "list";
    case ConceptMap:
      return "conceptmap";
    case Subscription:
      return "subscription";
    case ValueSet:
      return "valueset";
    case OperationDefinition:
      return "operationdefinition";
    case DocumentReference:
      return "documentreference";
    case Order:
      return "order";
    case Availability:
      return "availability";
    case Immunization:
      return "immunization";
    case SecurityEvent:
      return "securityevent";
    case Device:
      return "device";
    case Media:
      return "media";
    case Conformance:
      return "conformance";
    case RelatedPerson:
      return "relatedperson";
    case Namespace:
      return "namespace";
    case Practitioner:
      return "practitioner";
    case AppointmentResponse:
      return "appointmentresponse";
    case Observation:
      return "observation";
    case MedicationAdministration:
      return "medicationadministration";
    case DeviceObservationReport:
      return "deviceobservationreport";
    case Slot:
      return "slot";
    case Contraindication:
      return "contraindication";
    case MedicationStatement:
      return "medicationstatement";
    case RiskAssessment:
      return "riskassessment";
    case Group:
      return "group";
    case Organization:
      return "organization";
    case MedicationDispense:
      return "medicationdispense";
    case Supply:
      return "supply";
    case DiagnosticReport:
      return "diagnosticreport";
    case ImagingStudy:
      return "imagingstudy";
    case Profile:
      return "profile";
    case DataElement:
      return "dataelement";
    case QuestionnaireAnswers:
      return "questionnaireanswers";
    case AdverseReaction:
      return "adversereaction";
    case Encounter:
      return "encounter";
    case Substance:
      return "substance";
    case Condition:
      return "condition";
    case Composition:
      return "composition";
    case DiagnosticOrder:
      return "diagnosticorder";
    case Patient:
      return "patient";
    case OrderResponse:
      return "orderresponse";
    case Alert:
      return "alert";
    case MessageHeader:
      return "messageheader";
    case ImmunizationRecommendation:
      return "immunizationrecommendation";
    case Location:
      return "location";
    case Other:
      return "other";
    case Binary:
      return "binary";
    }
      return null;
  }

}
