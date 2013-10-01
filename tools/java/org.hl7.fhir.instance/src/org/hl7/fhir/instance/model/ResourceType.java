package org.hl7.fhir.instance.model;

public enum ResourceType {
    Condition,
    Supply,
    GVFVariant,
    Organization,
    Group,
    ValueSet,
    Coverage,
    VCFMeta,
    MedicationDispense,
    DeviceLog,
    MedicationPrescription,
    DeviceCapabilities,
    MedicationStatement,
    Sequence,
    SequencingLab,
    Questionnaire,
    OperationOutcome,
    Conformance,
    Media,
    Other,
    Profile,
    DocumentReference,
    Immunization,
    Microarray,
    OrderResponse,
    ConceptMap,
    ImagingStudy,
    Practitioner,
    GVFMeta,
    VCFVariant,
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
    MedicationAdministration,
    Encounter,
    GeneExpression,
    SequencingAnalysis,
    SecurityEvent,
    List,
    Document,
    Message,
    FamilyHistory,
    Location,
    GeneticAnalysis,
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
    AssessmentDefinition,
    InterestOfCare,
    Binary;

    public String getPath() {;
      switch (this) {
    case Condition:
      return "condition";
    case Supply:
      return "supply";
    case GVFVariant:
      return "gvfvariant";
    case Organization:
      return "organization";
    case Group:
      return "group";
    case ValueSet:
      return "valueset";
    case Coverage:
      return "coverage";
    case VCFMeta:
      return "vcfmeta";
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
    case Sequence:
      return "sequence";
    case SequencingLab:
      return "sequencinglab";
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
    case Microarray:
      return "microarray";
    case OrderResponse:
      return "orderresponse";
    case ConceptMap:
      return "conceptmap";
    case ImagingStudy:
      return "imagingstudy";
    case Practitioner:
      return "practitioner";
    case GVFMeta:
      return "gvfmeta";
    case VCFVariant:
      return "vcfvariant";
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
    case MedicationAdministration:
      return "medicationadministration";
    case Encounter:
      return "encounter";
    case GeneExpression:
      return "geneexpression";
    case SequencingAnalysis:
      return "sequencinganalysis";
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
    case GeneticAnalysis:
      return "geneticanalysis";
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
