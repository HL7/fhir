package org.hl7.fhir.instance.model;

// Generated on Wed, Mar 25, 2015 13:49+1100 for FHIR v0.4.0

public enum ResourceType {
    Condition,
    Supply,
    ProcedureRequest,
    DeviceComponent,
    DeviceMetric,
    Communication,
    Organization,
    ProcessRequest,
    Group,
    ValueSet,
    Coverage,
    ImmunizationRecommendation,
    Appointment,
    MedicationDispense,
    MedicationPrescription,
    Slot,
    PaymentNotice,
    Contraindication,
    AppointmentResponse,
    MedicationStatement,
    EpisodeOfCare,
    Questionnaire,
    Composition,
    OperationOutcome,
    Conformance,
    NamingSystem,
    Media,
    Binary,
    Other,
    HealthcareService,
    VisionPrescription,
    DocumentReference,
    Immunization,
    Bundle,
    Subscription,
    OrderResponse,
    ConceptMap,
    Practitioner,
    ImagingStudy,
    CarePlan,
    Provenance,
    Device,
    StructureDefinition,
    Order,
    Procedure,
    Substance,
    DeviceUseRequest,
    DiagnosticReport,
    Medication,
    MessageHeader,
    Schedule,
    DocumentManifest,
    DataElement,
    EligibilityRequest,
    QuestionnaireAnswers,
    MedicationAdministration,
    Encounter,
    PaymentReconciliation,
    List,
    DeviceUseStatement,
    OperationDefinition,
    Goal,
    ImagingObjectSelection,
    SearchParameter,
    NutritionOrder,
    ClaimResponse,
    ReferralRequest,
    ClinicalImpression,
    BodySite,
    CommunicationRequest,
    Claim,
    RiskAssessment,
    FamilyHistory,
    EnrollmentRequest,
    Location,
    ExplanationOfBenefit,
    AllergyIntolerance,
    Observation,
    Contract,
    SupportingDocumentation,
    RelatedPerson,
    Basic,
    ProcessResponse,
    Specimen,
    Alert,
    AuditEvent,
    EnrollmentResponse,
    Patient,
    EligibilityResponse,
    CarePlan2,
    Person,
    DiagnosticOrder,
    Parameters;


    public String getPath() {;
      switch (this) {
    case Condition:
      return "condition";
    case Supply:
      return "supply";
    case ProcedureRequest:
      return "procedurerequest";
    case DeviceComponent:
      return "devicecomponent";
    case DeviceMetric:
      return "devicemetric";
    case Communication:
      return "communication";
    case Organization:
      return "organization";
    case ProcessRequest:
      return "processrequest";
    case Group:
      return "group";
    case ValueSet:
      return "valueset";
    case Coverage:
      return "coverage";
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
    case PaymentNotice:
      return "paymentnotice";
    case Contraindication:
      return "contraindication";
    case AppointmentResponse:
      return "appointmentresponse";
    case MedicationStatement:
      return "medicationstatement";
    case EpisodeOfCare:
      return "episodeofcare";
    case Questionnaire:
      return "questionnaire";
    case Composition:
      return "composition";
    case OperationOutcome:
      return "operationoutcome";
    case Conformance:
      return "conformance";
    case NamingSystem:
      return "namingsystem";
    case Media:
      return "media";
    case Binary:
      return "binary";
    case Other:
      return "other";
    case HealthcareService:
      return "healthcareservice";
    case VisionPrescription:
      return "visionprescription";
    case DocumentReference:
      return "documentreference";
    case Immunization:
      return "immunization";
    case Bundle:
      return "bundle";
    case Subscription:
      return "subscription";
    case OrderResponse:
      return "orderresponse";
    case ConceptMap:
      return "conceptmap";
    case Practitioner:
      return "practitioner";
    case ImagingStudy:
      return "imagingstudy";
    case CarePlan:
      return "careplan";
    case Provenance:
      return "provenance";
    case Device:
      return "device";
    case StructureDefinition:
      return "structuredefinition";
    case Order:
      return "order";
    case Procedure:
      return "procedure";
    case Substance:
      return "substance";
    case DeviceUseRequest:
      return "deviceuserequest";
    case DiagnosticReport:
      return "diagnosticreport";
    case Medication:
      return "medication";
    case MessageHeader:
      return "messageheader";
    case Schedule:
      return "schedule";
    case DocumentManifest:
      return "documentmanifest";
    case DataElement:
      return "dataelement";
    case EligibilityRequest:
      return "eligibilityrequest";
    case QuestionnaireAnswers:
      return "questionnaireanswers";
    case MedicationAdministration:
      return "medicationadministration";
    case Encounter:
      return "encounter";
    case PaymentReconciliation:
      return "paymentreconciliation";
    case List:
      return "list";
    case DeviceUseStatement:
      return "deviceusestatement";
    case OperationDefinition:
      return "operationdefinition";
    case Goal:
      return "goal";
    case ImagingObjectSelection:
      return "imagingobjectselection";
    case SearchParameter:
      return "searchparameter";
    case NutritionOrder:
      return "nutritionorder";
    case ClaimResponse:
      return "claimresponse";
    case ReferralRequest:
      return "referralrequest";
    case ClinicalImpression:
      return "clinicalimpression";
    case BodySite:
      return "bodysite";
    case CommunicationRequest:
      return "communicationrequest";
    case Claim:
      return "claim";
    case RiskAssessment:
      return "riskassessment";
    case FamilyHistory:
      return "familyhistory";
    case EnrollmentRequest:
      return "enrollmentrequest";
    case Location:
      return "location";
    case ExplanationOfBenefit:
      return "explanationofbenefit";
    case AllergyIntolerance:
      return "allergyintolerance";
    case Observation:
      return "observation";
    case Contract:
      return "contract";
    case SupportingDocumentation:
      return "supportingdocumentation";
    case RelatedPerson:
      return "relatedperson";
    case Basic:
      return "basic";
    case ProcessResponse:
      return "processresponse";
    case Specimen:
      return "specimen";
    case Alert:
      return "alert";
    case AuditEvent:
      return "auditevent";
    case EnrollmentResponse:
      return "enrollmentresponse";
    case Patient:
      return "patient";
    case EligibilityResponse:
      return "eligibilityresponse";
    case CarePlan2:
      return "careplan2";
    case Person:
      return "person";
    case DiagnosticOrder:
      return "diagnosticorder";
    case Parameters:
      return "parameters";
    }
      return null;
  }

}
