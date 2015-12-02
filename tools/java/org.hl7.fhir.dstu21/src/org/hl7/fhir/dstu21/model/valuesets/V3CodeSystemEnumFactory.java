package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3CodeSystemEnumFactory implements EnumFactory<V3CodeSystem> {

  public V3CodeSystem fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("ABCcodes".equals(codeString))
      return V3CodeSystem.ABCCODES;
    if ("AcknowledgementCondition".equals(codeString))
      return V3CodeSystem.ACKNOWLEDGEMENTCONDITION;
    if ("AcknowledgementDetailCode".equals(codeString))
      return V3CodeSystem.ACKNOWLEDGEMENTDETAILCODE;
    if ("AcknowledgementDetailType".equals(codeString))
      return V3CodeSystem.ACKNOWLEDGEMENTDETAILTYPE;
    if ("AcknowledgementType".equals(codeString))
      return V3CodeSystem.ACKNOWLEDGEMENTTYPE;
    if ("ACR".equals(codeString))
      return V3CodeSystem.ACR;
    if ("ActClass".equals(codeString))
      return V3CodeSystem.ACTCLASS;
    if ("ActCode".equals(codeString))
      return V3CodeSystem.ACTCODE;
    if ("ActExposureLevelCode".equals(codeString))
      return V3CodeSystem.ACTEXPOSURELEVELCODE;
    if ("ActInvoiceElementModifier".equals(codeString))
      return V3CodeSystem.ACTINVOICEELEMENTMODIFIER;
    if ("ActMood".equals(codeString))
      return V3CodeSystem.ACTMOOD;
    if ("ActPriority".equals(codeString))
      return V3CodeSystem.ACTPRIORITY;
    if ("ActReason".equals(codeString))
      return V3CodeSystem.ACTREASON;
    if ("ActRelationshipCheckpoint".equals(codeString))
      return V3CodeSystem.ACTRELATIONSHIPCHECKPOINT;
    if ("ActRelationshipJoin".equals(codeString))
      return V3CodeSystem.ACTRELATIONSHIPJOIN;
    if ("ActRelationshipSplit".equals(codeString))
      return V3CodeSystem.ACTRELATIONSHIPSPLIT;
    if ("ActRelationshipSubset".equals(codeString))
      return V3CodeSystem.ACTRELATIONSHIPSUBSET;
    if ("ActRelationshipType".equals(codeString))
      return V3CodeSystem.ACTRELATIONSHIPTYPE;
    if ("ActSite".equals(codeString))
      return V3CodeSystem.ACTSITE;
    if ("ActStatus".equals(codeString))
      return V3CodeSystem.ACTSTATUS;
    if ("ActUncertainty".equals(codeString))
      return V3CodeSystem.ACTUNCERTAINTY;
    if ("ActUSPrivacyLaw".equals(codeString))
      return V3CodeSystem.ACTUSPRIVACYLAW;
    if ("AddressPartType".equals(codeString))
      return V3CodeSystem.ADDRESSPARTTYPE;
    if ("AddressUse".equals(codeString))
      return V3CodeSystem.ADDRESSUSE;
    if ("AdministrativeGender".equals(codeString))
      return V3CodeSystem.ADMINISTRATIVEGENDER;
    if ("AHFS".equals(codeString))
      return V3CodeSystem.AHFS;
    if ("AmericanIndianAlaskaNativeLanguages".equals(codeString))
      return V3CodeSystem.AMERICANINDIANALASKANATIVELANGUAGES;
    if ("ART".equals(codeString))
      return V3CodeSystem.ART;
    if ("AS4".equals(codeString))
      return V3CodeSystem.AS4;
    if ("AS4E".equals(codeString))
      return V3CodeSystem.AS4E;
    if ("ATC".equals(codeString))
      return V3CodeSystem.ATC;
    if ("BindingRealm".equals(codeString))
      return V3CodeSystem.BINDINGREALM;
    if ("BodySite".equals(codeString))
      return V3CodeSystem.BODYSITE;
    if ("C4".equals(codeString))
      return V3CodeSystem.C4;
    if ("C5".equals(codeString))
      return V3CodeSystem.C5;
    if ("Calendar".equals(codeString))
      return V3CodeSystem.CALENDAR;
    if ("CalendarCycle".equals(codeString))
      return V3CodeSystem.CALENDARCYCLE;
    if ("CalendarType".equals(codeString))
      return V3CodeSystem.CALENDARTYPE;
    if ("CAMNCVS".equals(codeString))
      return V3CodeSystem.CAMNCVS;
    if ("CAS".equals(codeString))
      return V3CodeSystem.CAS;
    if ("CCI".equals(codeString))
      return V3CodeSystem.CCI;
    if ("CD2".equals(codeString))
      return V3CodeSystem.CD2;
    if ("CDCA".equals(codeString))
      return V3CodeSystem.CDCA;
    if ("CDCM".equals(codeString))
      return V3CodeSystem.CDCM;
    if ("CDS".equals(codeString))
      return V3CodeSystem.CDS;
    if ("CE".equals(codeString))
      return V3CodeSystem.CE;
    if ("Charset".equals(codeString))
      return V3CodeSystem.CHARSET;
    if ("CLP".equals(codeString))
      return V3CodeSystem.CLP;
    if ("CodeSystem".equals(codeString))
      return V3CodeSystem.CODESYSTEM;
    if ("CodeSystemType".equals(codeString))
      return V3CodeSystem.CODESYSTEMTYPE;
    if ("CodingRationale".equals(codeString))
      return V3CodeSystem.CODINGRATIONALE;
    if ("CommunicationFunctionType".equals(codeString))
      return V3CodeSystem.COMMUNICATIONFUNCTIONTYPE;
    if ("CompressionAlgorithm".equals(codeString))
      return V3CodeSystem.COMPRESSIONALGORITHM;
    if ("ConceptCodeRelationship".equals(codeString))
      return V3CodeSystem.CONCEPTCODERELATIONSHIP;
    if ("ConceptGenerality".equals(codeString))
      return V3CodeSystem.CONCEPTGENERALITY;
    if ("ConceptProperty".equals(codeString))
      return V3CodeSystem.CONCEPTPROPERTY;
    if ("ConceptStatus".equals(codeString))
      return V3CodeSystem.CONCEPTSTATUS;
    if ("Confidentiality".equals(codeString))
      return V3CodeSystem.CONFIDENTIALITY;
    if ("ContainerCap".equals(codeString))
      return V3CodeSystem.CONTAINERCAP;
    if ("ContainerSeparator".equals(codeString))
      return V3CodeSystem.CONTAINERSEPARATOR;
    if ("ContentProcessingMode".equals(codeString))
      return V3CodeSystem.CONTENTPROCESSINGMODE;
    if ("ContextConductionStyle".equals(codeString))
      return V3CodeSystem.CONTEXTCONDUCTIONSTYLE;
    if ("ContextControl".equals(codeString))
      return V3CodeSystem.CONTEXTCONTROL;
    if ("CSAID".equals(codeString))
      return V3CodeSystem.CSAID;
    if ("CST".equals(codeString))
      return V3CodeSystem.CST;
    if ("Currency".equals(codeString))
      return V3CodeSystem.CURRENCY;
    if ("CVX".equals(codeString))
      return V3CodeSystem.CVX;
    if ("DataOperation".equals(codeString))
      return V3CodeSystem.DATAOPERATION;
    if ("DataType".equals(codeString))
      return V3CodeSystem.DATATYPE;
    if ("DCL".equals(codeString))
      return V3CodeSystem.DCL;
    if ("DCM".equals(codeString))
      return V3CodeSystem.DCM;
    if ("Dentition".equals(codeString))
      return V3CodeSystem.DENTITION;
    if ("DeviceAlertLevel".equals(codeString))
      return V3CodeSystem.DEVICEALERTLEVEL;
    if ("DocumentCompletion".equals(codeString))
      return V3CodeSystem.DOCUMENTCOMPLETION;
    if ("DocumentStorage".equals(codeString))
      return V3CodeSystem.DOCUMENTSTORAGE;
    if ("DQL".equals(codeString))
      return V3CodeSystem.DQL;
    if ("E".equals(codeString))
      return V3CodeSystem.E;
    if ("E5".equals(codeString))
      return V3CodeSystem.E5;
    if ("E6".equals(codeString))
      return V3CodeSystem.E6;
    if ("E7".equals(codeString))
      return V3CodeSystem.E7;
    if ("EditStatus".equals(codeString))
      return V3CodeSystem.EDITSTATUS;
    if ("EducationLevel".equals(codeString))
      return V3CodeSystem.EDUCATIONLEVEL;
    if ("EmployeeJobClass".equals(codeString))
      return V3CodeSystem.EMPLOYEEJOBCLASS;
    if ("EncounterAccident".equals(codeString))
      return V3CodeSystem.ENCOUNTERACCIDENT;
    if ("EncounterAcuity".equals(codeString))
      return V3CodeSystem.ENCOUNTERACUITY;
    if ("EncounterAdmissionSource".equals(codeString))
      return V3CodeSystem.ENCOUNTERADMISSIONSOURCE;
    if ("EncounterReferralSource".equals(codeString))
      return V3CodeSystem.ENCOUNTERREFERRALSOURCE;
    if ("EncounterSpecialCourtesy".equals(codeString))
      return V3CodeSystem.ENCOUNTERSPECIALCOURTESY;
    if ("EntityClass".equals(codeString))
      return V3CodeSystem.ENTITYCLASS;
    if ("EntityCode".equals(codeString))
      return V3CodeSystem.ENTITYCODE;
    if ("EntityDeterminer".equals(codeString))
      return V3CodeSystem.ENTITYDETERMINER;
    if ("EntityHandling".equals(codeString))
      return V3CodeSystem.ENTITYHANDLING;
    if ("EntityNamePartQualifier".equals(codeString))
      return V3CodeSystem.ENTITYNAMEPARTQUALIFIER;
    if ("EntityNamePartQualifierR2".equals(codeString))
      return V3CodeSystem.ENTITYNAMEPARTQUALIFIERR2;
    if ("EntityNamePartType".equals(codeString))
      return V3CodeSystem.ENTITYNAMEPARTTYPE;
    if ("EntityNamePartTypeR2".equals(codeString))
      return V3CodeSystem.ENTITYNAMEPARTTYPER2;
    if ("EntityNameUse".equals(codeString))
      return V3CodeSystem.ENTITYNAMEUSE;
    if ("EntityNameUseR2".equals(codeString))
      return V3CodeSystem.ENTITYNAMEUSER2;
    if ("EntityRisk".equals(codeString))
      return V3CodeSystem.ENTITYRISK;
    if ("EntityStatus".equals(codeString))
      return V3CodeSystem.ENTITYSTATUS;
    if ("ENZC".equals(codeString))
      return V3CodeSystem.ENZC;
    if ("EPSG_CA".equals(codeString))
      return V3CodeSystem.EPSGCA;
    if ("EPSG_CRS".equals(codeString))
      return V3CodeSystem.EPSGCRS;
    if ("EPSG-GeodeticParameterDataset".equals(codeString))
      return V3CodeSystem.EPSGGEODETICPARAMETERDATASET;
    if ("EquipmentAlertLevel".equals(codeString))
      return V3CodeSystem.EQUIPMENTALERTLEVEL;
    if ("Ethnicity".equals(codeString))
      return V3CodeSystem.ETHNICITY;
    if ("ExposureMode".equals(codeString))
      return V3CodeSystem.EXPOSUREMODE;
    if ("FDDC".equals(codeString))
      return V3CodeSystem.FDDC;
    if ("FDDX".equals(codeString))
      return V3CodeSystem.FDDX;
    if ("FDK".equals(codeString))
      return V3CodeSystem.FDK;
    if ("GenderStatus".equals(codeString))
      return V3CodeSystem.GENDERSTATUS;
    if ("GTSAbbreviation".equals(codeString))
      return V3CodeSystem.GTSABBREVIATION;
    if ("HB".equals(codeString))
      return V3CodeSystem.HB;
    if ("HC-AIC".equals(codeString))
      return V3CodeSystem.HCAIC;
    if ("HC-AIGC".equals(codeString))
      return V3CodeSystem.HCAIGC;
    if ("HC-AIGN".equals(codeString))
      return V3CodeSystem.HCAIGN;
    if ("HC-DIN".equals(codeString))
      return V3CodeSystem.HCDIN;
    if ("HC-NPN".equals(codeString))
      return V3CodeSystem.HCNPN;
    if ("HealthcareProviderTaxonomyHIPAA".equals(codeString))
      return V3CodeSystem.HEALTHCAREPROVIDERTAXONOMYHIPAA;
    if ("HealthcareServiceLocation".equals(codeString))
      return V3CodeSystem.HEALTHCARESERVICELOCATION;
    if ("HHC".equals(codeString))
      return V3CodeSystem.HHC;
    if ("HI".equals(codeString))
      return V3CodeSystem.HI;
    if ("hl7ApprovalStatus".equals(codeString))
      return V3CodeSystem.HL7APPROVALSTATUS;
    if ("hl7CMETAttribution".equals(codeString))
      return V3CodeSystem.HL7CMETATTRIBUTION;
    if ("HL7CommitteeIDInRIM".equals(codeString))
      return V3CodeSystem.HL7COMMITTEEIDINRIM;
    if ("HL7ConformanceInclusion".equals(codeString))
      return V3CodeSystem.HL7CONFORMANCEINCLUSION;
    if ("HL7DefinedRoseProperty".equals(codeString))
      return V3CodeSystem.HL7DEFINEDROSEPROPERTY;
    if ("hl7ITSType".equals(codeString))
      return V3CodeSystem.HL7ITSTYPE;
    if ("HL7ITSVersionCode".equals(codeString))
      return V3CodeSystem.HL7ITSVERSIONCODE;
    if ("hl7PublishingDomain".equals(codeString))
      return V3CodeSystem.HL7PUBLISHINGDOMAIN;
    if ("hl7PublishingSection".equals(codeString))
      return V3CodeSystem.HL7PUBLISHINGSECTION;
    if ("hl7PublishingSubSection".equals(codeString))
      return V3CodeSystem.HL7PUBLISHINGSUBSECTION;
    if ("HL7StandardVersionCode".equals(codeString))
      return V3CodeSystem.HL7STANDARDVERSIONCODE;
    if ("HL7UpdateMode".equals(codeString))
      return V3CodeSystem.HL7UPDATEMODE;
    if ("hl7V3Conformance".equals(codeString))
      return V3CodeSystem.HL7V3CONFORMANCE;
    if ("hl7VoteResolution".equals(codeString))
      return V3CodeSystem.HL7VOTERESOLUTION;
    if ("HPC".equals(codeString))
      return V3CodeSystem.HPC;
    if ("HtmlLinkType".equals(codeString))
      return V3CodeSystem.HTMLLINKTYPE;
    if ("I10".equals(codeString))
      return V3CodeSystem.I10;
    if ("I10P".equals(codeString))
      return V3CodeSystem.I10P;
    if ("I9".equals(codeString))
      return V3CodeSystem.I9;
    if ("I9C".equals(codeString))
      return V3CodeSystem.I9C;
    if ("IBT".equals(codeString))
      return V3CodeSystem.IBT;
    if ("IC2".equals(codeString))
      return V3CodeSystem.IC2;
    if ("ICD-10-CA".equals(codeString))
      return V3CodeSystem.ICD10CA;
    if ("ICDO".equals(codeString))
      return V3CodeSystem.ICDO;
    if ("ICS".equals(codeString))
      return V3CodeSystem.ICS;
    if ("ICSD".equals(codeString))
      return V3CodeSystem.ICSD;
    if ("IdentifierReliability".equals(codeString))
      return V3CodeSystem.IDENTIFIERRELIABILITY;
    if ("IdentifierScope".equals(codeString))
      return V3CodeSystem.IDENTIFIERSCOPE;
    if ("IETF1766".equals(codeString))
      return V3CodeSystem.IETF1766;
    if ("IETF3066".equals(codeString))
      return V3CodeSystem.IETF3066;
    if ("IntegrityCheckAlgorithm".equals(codeString))
      return V3CodeSystem.INTEGRITYCHECKALGORITHM;
    if ("iso21000-6-2004E-RDD".equals(codeString))
      return V3CodeSystem.ISO2100062004ERDD;
    if ("ISO3166-1".equals(codeString))
      return V3CodeSystem.ISO31661;
    if ("ISO3166-2".equals(codeString))
      return V3CodeSystem.ISO31662;
    if ("ISO3166-3".equals(codeString))
      return V3CodeSystem.ISO31663;
    if ("ISO4217".equals(codeString))
      return V3CodeSystem.ISO4217;
    if ("IUPC".equals(codeString))
      return V3CodeSystem.IUPC;
    if ("IUPP".equals(codeString))
      return V3CodeSystem.IUPP;
    if ("JC8".equals(codeString))
      return V3CodeSystem.JC8;
    if ("LanguageAbilityMode".equals(codeString))
      return V3CodeSystem.LANGUAGEABILITYMODE;
    if ("LanguageAbilityProficiency".equals(codeString))
      return V3CodeSystem.LANGUAGEABILITYPROFICIENCY;
    if ("LivingArrangement".equals(codeString))
      return V3CodeSystem.LIVINGARRANGEMENT;
    if ("LN".equals(codeString))
      return V3CodeSystem.LN;
    if ("LocalMarkupIgnore".equals(codeString))
      return V3CodeSystem.LOCALMARKUPIGNORE;
    if ("LocalRemoteControlState".equals(codeString))
      return V3CodeSystem.LOCALREMOTECONTROLSTATE;
    if ("ManagedParticipationStatus".equals(codeString))
      return V3CodeSystem.MANAGEDPARTICIPATIONSTATUS;
    if ("MapRelationship".equals(codeString))
      return V3CodeSystem.MAPRELATIONSHIP;
    if ("MaritalStatus".equals(codeString))
      return V3CodeSystem.MARITALSTATUS;
    if ("MaterialType".equals(codeString))
      return V3CodeSystem.MATERIALTYPE;
    if ("MDC".equals(codeString))
      return V3CodeSystem.MDC;
    if ("MDDX".equals(codeString))
      return V3CodeSystem.MDDX;
    if ("MDFAttributeType".equals(codeString))
      return V3CodeSystem.MDFATTRIBUTETYPE;
    if ("MdfHmdMetSourceType".equals(codeString))
      return V3CodeSystem.MDFHMDMETSOURCETYPE;
    if ("MdfHmdRowType".equals(codeString))
      return V3CodeSystem.MDFHMDROWTYPE;
    if ("MdfRmimRowType".equals(codeString))
      return V3CodeSystem.MDFRMIMROWTYPE;
    if ("MDFSubjectAreaPrefix".equals(codeString))
      return V3CodeSystem.MDFSUBJECTAREAPREFIX;
    if ("MEDC".equals(codeString))
      return V3CodeSystem.MEDC;
    if ("MEDCIN".equals(codeString))
      return V3CodeSystem.MEDCIN;
    if ("MediaType".equals(codeString))
      return V3CodeSystem.MEDIATYPE;
    if ("MEDR".equals(codeString))
      return V3CodeSystem.MEDR;
    if ("MEDX".equals(codeString))
      return V3CodeSystem.MEDX;
    if ("MessageCondition".equals(codeString))
      return V3CodeSystem.MESSAGECONDITION;
    if ("MessageWaitingPriority".equals(codeString))
      return V3CodeSystem.MESSAGEWAITINGPRIORITY;
    if ("MGPI".equals(codeString))
      return V3CodeSystem.MGPI;
    if ("MIME".equals(codeString))
      return V3CodeSystem.MIME;
    if ("ModifyIndicator".equals(codeString))
      return V3CodeSystem.MODIFYINDICATOR;
    if ("MSH".equals(codeString))
      return V3CodeSystem.MSH;
    if ("MULTUM".equals(codeString))
      return V3CodeSystem.MULTUM;
    if ("MVX".equals(codeString))
      return V3CodeSystem.MVX;
    if ("NAACCR".equals(codeString))
      return V3CodeSystem.NAACCR;
    if ("NAICS".equals(codeString))
      return V3CodeSystem.NAICS;
    if ("NDA".equals(codeString))
      return V3CodeSystem.NDA;
    if ("NDC".equals(codeString))
      return V3CodeSystem.NDC;
    if ("NIC".equals(codeString))
      return V3CodeSystem.NIC;
    if ("NMMDS".equals(codeString))
      return V3CodeSystem.NMMDS;
    if ("NOC".equals(codeString))
      return V3CodeSystem.NOC;
    if ("NUBC-UB92".equals(codeString))
      return V3CodeSystem.NUBCUB92;
    if ("NUCCProviderCodes".equals(codeString))
      return V3CodeSystem.NUCCPROVIDERCODES;
    if ("NullFlavor".equals(codeString))
      return V3CodeSystem.NULLFLAVOR;
    if ("ObservationInterpretation".equals(codeString))
      return V3CodeSystem.OBSERVATIONINTERPRETATION;
    if ("ObservationMethod".equals(codeString))
      return V3CodeSystem.OBSERVATIONMETHOD;
    if ("ObservationValue".equals(codeString))
      return V3CodeSystem.OBSERVATIONVALUE;
    if ("OHA".equals(codeString))
      return V3CodeSystem.OHA;
    if ("OPINIONS".equals(codeString))
      return V3CodeSystem.OPINIONS;
    if ("OrderableDrugForm".equals(codeString))
      return V3CodeSystem.ORDERABLEDRUGFORM;
    if ("OrganizationNameType".equals(codeString))
      return V3CodeSystem.ORGANIZATIONNAMETYPE;
    if ("ParameterizedDataType".equals(codeString))
      return V3CodeSystem.PARAMETERIZEDDATATYPE;
    if ("ParticipationFunction".equals(codeString))
      return V3CodeSystem.PARTICIPATIONFUNCTION;
    if ("ParticipationMode".equals(codeString))
      return V3CodeSystem.PARTICIPATIONMODE;
    if ("ParticipationSignature".equals(codeString))
      return V3CodeSystem.PARTICIPATIONSIGNATURE;
    if ("ParticipationType".equals(codeString))
      return V3CodeSystem.PARTICIPATIONTYPE;
    if ("PatientImportance".equals(codeString))
      return V3CodeSystem.PATIENTIMPORTANCE;
    if ("PaymentTerms".equals(codeString))
      return V3CodeSystem.PAYMENTTERMS;
    if ("PeriodicIntervalOfTimeAbbreviation".equals(codeString))
      return V3CodeSystem.PERIODICINTERVALOFTIMEABBREVIATION;
    if ("PersonDisabilityType".equals(codeString))
      return V3CodeSystem.PERSONDISABILITYTYPE;
    if ("PNDS".equals(codeString))
      return V3CodeSystem.PNDS;
    if ("POS".equals(codeString))
      return V3CodeSystem.POS;
    if ("PostalAddressUse".equals(codeString))
      return V3CodeSystem.POSTALADDRESSUSE;
    if ("ProbabilityDistributionType".equals(codeString))
      return V3CodeSystem.PROBABILITYDISTRIBUTIONTYPE;
    if ("ProcedureMethod".equals(codeString))
      return V3CodeSystem.PROCEDUREMETHOD;
    if ("ProcessingID".equals(codeString))
      return V3CodeSystem.PROCESSINGID;
    if ("ProcessingMode".equals(codeString))
      return V3CodeSystem.PROCESSINGMODE;
    if ("QueryParameterValue".equals(codeString))
      return V3CodeSystem.QUERYPARAMETERVALUE;
    if ("QueryPriority".equals(codeString))
      return V3CodeSystem.QUERYPRIORITY;
    if ("QueryQuantityUnit".equals(codeString))
      return V3CodeSystem.QUERYQUANTITYUNIT;
    if ("QueryRequestLimit".equals(codeString))
      return V3CodeSystem.QUERYREQUESTLIMIT;
    if ("QueryResponse".equals(codeString))
      return V3CodeSystem.QUERYRESPONSE;
    if ("QueryStatusCode".equals(codeString))
      return V3CodeSystem.QUERYSTATUSCODE;
    if ("Race".equals(codeString))
      return V3CodeSystem.RACE;
    if ("RC".equals(codeString))
      return V3CodeSystem.RC;
    if ("RCFB".equals(codeString))
      return V3CodeSystem.RCFB;
    if ("RCV2".equals(codeString))
      return V3CodeSystem.RCV2;
    if ("RelationalOperator".equals(codeString))
      return V3CodeSystem.RELATIONALOPERATOR;
    if ("RelationshipConjunction".equals(codeString))
      return V3CodeSystem.RELATIONSHIPCONJUNCTION;
    if ("ReligiousAffiliation".equals(codeString))
      return V3CodeSystem.RELIGIOUSAFFILIATION;
    if ("ResponseLevel".equals(codeString))
      return V3CodeSystem.RESPONSELEVEL;
    if ("ResponseModality".equals(codeString))
      return V3CodeSystem.RESPONSEMODALITY;
    if ("ResponseMode".equals(codeString))
      return V3CodeSystem.RESPONSEMODE;
    if ("RoleClass".equals(codeString))
      return V3CodeSystem.ROLECLASS;
    if ("RoleCode".equals(codeString))
      return V3CodeSystem.ROLECODE;
    if ("RoleLinkStatus".equals(codeString))
      return V3CodeSystem.ROLELINKSTATUS;
    if ("RoleLinkType".equals(codeString))
      return V3CodeSystem.ROLELINKTYPE;
    if ("RoleStatus".equals(codeString))
      return V3CodeSystem.ROLESTATUS;
    if ("RouteOfAdministration".equals(codeString))
      return V3CodeSystem.ROUTEOFADMINISTRATION;
    if ("SCDHEC-GISSpatialAccuracyTiers".equals(codeString))
      return V3CodeSystem.SCDHECGISSPATIALACCURACYTIERS;
    if ("SDM".equals(codeString))
      return V3CodeSystem.SDM;
    if ("Sequencing".equals(codeString))
      return V3CodeSystem.SEQUENCING;
    if ("SetOperator".equals(codeString))
      return V3CodeSystem.SETOPERATOR;
    if ("SNM".equals(codeString))
      return V3CodeSystem.SNM;
    if ("SNM3".equals(codeString))
      return V3CodeSystem.SNM3;
    if ("SNT".equals(codeString))
      return V3CodeSystem.SNT;
    if ("SpecialArrangement".equals(codeString))
      return V3CodeSystem.SPECIALARRANGEMENT;
    if ("SpecimenType".equals(codeString))
      return V3CodeSystem.SPECIMENTYPE;
    if ("StyleType".equals(codeString))
      return V3CodeSystem.STYLETYPE;
    if ("SubstanceAdminSubstitution".equals(codeString))
      return V3CodeSystem.SUBSTANCEADMINSUBSTITUTION;
    if ("SubstitutionCondition".equals(codeString))
      return V3CodeSystem.SUBSTITUTIONCONDITION;
    if ("TableCellHorizontalAlign".equals(codeString))
      return V3CodeSystem.TABLECELLHORIZONTALALIGN;
    if ("TableCellScope".equals(codeString))
      return V3CodeSystem.TABLECELLSCOPE;
    if ("TableCellVerticalAlign".equals(codeString))
      return V3CodeSystem.TABLECELLVERTICALALIGN;
    if ("TableFrame".equals(codeString))
      return V3CodeSystem.TABLEFRAME;
    if ("TableRules".equals(codeString))
      return V3CodeSystem.TABLERULES;
    if ("TargetAwareness".equals(codeString))
      return V3CodeSystem.TARGETAWARENESS;
    if ("TelecommunicationAddressUse".equals(codeString))
      return V3CodeSystem.TELECOMMUNICATIONADDRESSUSE;
    if ("TelecommunicationCapabilities".equals(codeString))
      return V3CodeSystem.TELECOMMUNICATIONCAPABILITIES;
    if ("TimingEvent".equals(codeString))
      return V3CodeSystem.TIMINGEVENT;
    if ("TransmissionRelationshipTypeCode".equals(codeString))
      return V3CodeSystem.TRANSMISSIONRELATIONSHIPTYPECODE;
    if ("TribalEntityUS".equals(codeString))
      return V3CodeSystem.TRIBALENTITYUS;
    if ("TriggerEventID".equals(codeString))
      return V3CodeSystem.TRIGGEREVENTID;
    if ("UC".equals(codeString))
      return V3CodeSystem.UC;
    if ("UCUM".equals(codeString))
      return V3CodeSystem.UCUM;
    if ("UMD".equals(codeString))
      return V3CodeSystem.UMD;
    if ("UML".equals(codeString))
      return V3CodeSystem.UML;
    if ("UnitsOfMeasure".equals(codeString))
      return V3CodeSystem.UNITSOFMEASURE;
    if ("UPC".equals(codeString))
      return V3CodeSystem.UPC;
    if ("URLScheme".equals(codeString))
      return V3CodeSystem.URLSCHEME;
    if ("VaccineManufacturer".equals(codeString))
      return V3CodeSystem.VACCINEMANUFACTURER;
    if ("VaccineType".equals(codeString))
      return V3CodeSystem.VACCINETYPE;
    if ("VocabularyDomainQualifier".equals(codeString))
      return V3CodeSystem.VOCABULARYDOMAINQUALIFIER;
    if ("W1-W2".equals(codeString))
      return V3CodeSystem.W1W2;
    if ("W4".equals(codeString))
      return V3CodeSystem.W4;
    if ("WC".equals(codeString))
      return V3CodeSystem.WC;
    throw new IllegalArgumentException("Unknown V3CodeSystem code '"+codeString+"'");
  }

  public String toCode(V3CodeSystem code) {
    if (code == V3CodeSystem.ABCCODES)
      return "ABCcodes";
    if (code == V3CodeSystem.ACKNOWLEDGEMENTCONDITION)
      return "AcknowledgementCondition";
    if (code == V3CodeSystem.ACKNOWLEDGEMENTDETAILCODE)
      return "AcknowledgementDetailCode";
    if (code == V3CodeSystem.ACKNOWLEDGEMENTDETAILTYPE)
      return "AcknowledgementDetailType";
    if (code == V3CodeSystem.ACKNOWLEDGEMENTTYPE)
      return "AcknowledgementType";
    if (code == V3CodeSystem.ACR)
      return "ACR";
    if (code == V3CodeSystem.ACTCLASS)
      return "ActClass";
    if (code == V3CodeSystem.ACTCODE)
      return "ActCode";
    if (code == V3CodeSystem.ACTEXPOSURELEVELCODE)
      return "ActExposureLevelCode";
    if (code == V3CodeSystem.ACTINVOICEELEMENTMODIFIER)
      return "ActInvoiceElementModifier";
    if (code == V3CodeSystem.ACTMOOD)
      return "ActMood";
    if (code == V3CodeSystem.ACTPRIORITY)
      return "ActPriority";
    if (code == V3CodeSystem.ACTREASON)
      return "ActReason";
    if (code == V3CodeSystem.ACTRELATIONSHIPCHECKPOINT)
      return "ActRelationshipCheckpoint";
    if (code == V3CodeSystem.ACTRELATIONSHIPJOIN)
      return "ActRelationshipJoin";
    if (code == V3CodeSystem.ACTRELATIONSHIPSPLIT)
      return "ActRelationshipSplit";
    if (code == V3CodeSystem.ACTRELATIONSHIPSUBSET)
      return "ActRelationshipSubset";
    if (code == V3CodeSystem.ACTRELATIONSHIPTYPE)
      return "ActRelationshipType";
    if (code == V3CodeSystem.ACTSITE)
      return "ActSite";
    if (code == V3CodeSystem.ACTSTATUS)
      return "ActStatus";
    if (code == V3CodeSystem.ACTUNCERTAINTY)
      return "ActUncertainty";
    if (code == V3CodeSystem.ACTUSPRIVACYLAW)
      return "ActUSPrivacyLaw";
    if (code == V3CodeSystem.ADDRESSPARTTYPE)
      return "AddressPartType";
    if (code == V3CodeSystem.ADDRESSUSE)
      return "AddressUse";
    if (code == V3CodeSystem.ADMINISTRATIVEGENDER)
      return "AdministrativeGender";
    if (code == V3CodeSystem.AHFS)
      return "AHFS";
    if (code == V3CodeSystem.AMERICANINDIANALASKANATIVELANGUAGES)
      return "AmericanIndianAlaskaNativeLanguages";
    if (code == V3CodeSystem.ART)
      return "ART";
    if (code == V3CodeSystem.AS4)
      return "AS4";
    if (code == V3CodeSystem.AS4E)
      return "AS4E";
    if (code == V3CodeSystem.ATC)
      return "ATC";
    if (code == V3CodeSystem.BINDINGREALM)
      return "BindingRealm";
    if (code == V3CodeSystem.BODYSITE)
      return "BodySite";
    if (code == V3CodeSystem.C4)
      return "C4";
    if (code == V3CodeSystem.C5)
      return "C5";
    if (code == V3CodeSystem.CALENDAR)
      return "Calendar";
    if (code == V3CodeSystem.CALENDARCYCLE)
      return "CalendarCycle";
    if (code == V3CodeSystem.CALENDARTYPE)
      return "CalendarType";
    if (code == V3CodeSystem.CAMNCVS)
      return "CAMNCVS";
    if (code == V3CodeSystem.CAS)
      return "CAS";
    if (code == V3CodeSystem.CCI)
      return "CCI";
    if (code == V3CodeSystem.CD2)
      return "CD2";
    if (code == V3CodeSystem.CDCA)
      return "CDCA";
    if (code == V3CodeSystem.CDCM)
      return "CDCM";
    if (code == V3CodeSystem.CDS)
      return "CDS";
    if (code == V3CodeSystem.CE)
      return "CE";
    if (code == V3CodeSystem.CHARSET)
      return "Charset";
    if (code == V3CodeSystem.CLP)
      return "CLP";
    if (code == V3CodeSystem.CODESYSTEM)
      return "CodeSystem";
    if (code == V3CodeSystem.CODESYSTEMTYPE)
      return "CodeSystemType";
    if (code == V3CodeSystem.CODINGRATIONALE)
      return "CodingRationale";
    if (code == V3CodeSystem.COMMUNICATIONFUNCTIONTYPE)
      return "CommunicationFunctionType";
    if (code == V3CodeSystem.COMPRESSIONALGORITHM)
      return "CompressionAlgorithm";
    if (code == V3CodeSystem.CONCEPTCODERELATIONSHIP)
      return "ConceptCodeRelationship";
    if (code == V3CodeSystem.CONCEPTGENERALITY)
      return "ConceptGenerality";
    if (code == V3CodeSystem.CONCEPTPROPERTY)
      return "ConceptProperty";
    if (code == V3CodeSystem.CONCEPTSTATUS)
      return "ConceptStatus";
    if (code == V3CodeSystem.CONFIDENTIALITY)
      return "Confidentiality";
    if (code == V3CodeSystem.CONTAINERCAP)
      return "ContainerCap";
    if (code == V3CodeSystem.CONTAINERSEPARATOR)
      return "ContainerSeparator";
    if (code == V3CodeSystem.CONTENTPROCESSINGMODE)
      return "ContentProcessingMode";
    if (code == V3CodeSystem.CONTEXTCONDUCTIONSTYLE)
      return "ContextConductionStyle";
    if (code == V3CodeSystem.CONTEXTCONTROL)
      return "ContextControl";
    if (code == V3CodeSystem.CSAID)
      return "CSAID";
    if (code == V3CodeSystem.CST)
      return "CST";
    if (code == V3CodeSystem.CURRENCY)
      return "Currency";
    if (code == V3CodeSystem.CVX)
      return "CVX";
    if (code == V3CodeSystem.DATAOPERATION)
      return "DataOperation";
    if (code == V3CodeSystem.DATATYPE)
      return "DataType";
    if (code == V3CodeSystem.DCL)
      return "DCL";
    if (code == V3CodeSystem.DCM)
      return "DCM";
    if (code == V3CodeSystem.DENTITION)
      return "Dentition";
    if (code == V3CodeSystem.DEVICEALERTLEVEL)
      return "DeviceAlertLevel";
    if (code == V3CodeSystem.DOCUMENTCOMPLETION)
      return "DocumentCompletion";
    if (code == V3CodeSystem.DOCUMENTSTORAGE)
      return "DocumentStorage";
    if (code == V3CodeSystem.DQL)
      return "DQL";
    if (code == V3CodeSystem.E)
      return "E";
    if (code == V3CodeSystem.E5)
      return "E5";
    if (code == V3CodeSystem.E6)
      return "E6";
    if (code == V3CodeSystem.E7)
      return "E7";
    if (code == V3CodeSystem.EDITSTATUS)
      return "EditStatus";
    if (code == V3CodeSystem.EDUCATIONLEVEL)
      return "EducationLevel";
    if (code == V3CodeSystem.EMPLOYEEJOBCLASS)
      return "EmployeeJobClass";
    if (code == V3CodeSystem.ENCOUNTERACCIDENT)
      return "EncounterAccident";
    if (code == V3CodeSystem.ENCOUNTERACUITY)
      return "EncounterAcuity";
    if (code == V3CodeSystem.ENCOUNTERADMISSIONSOURCE)
      return "EncounterAdmissionSource";
    if (code == V3CodeSystem.ENCOUNTERREFERRALSOURCE)
      return "EncounterReferralSource";
    if (code == V3CodeSystem.ENCOUNTERSPECIALCOURTESY)
      return "EncounterSpecialCourtesy";
    if (code == V3CodeSystem.ENTITYCLASS)
      return "EntityClass";
    if (code == V3CodeSystem.ENTITYCODE)
      return "EntityCode";
    if (code == V3CodeSystem.ENTITYDETERMINER)
      return "EntityDeterminer";
    if (code == V3CodeSystem.ENTITYHANDLING)
      return "EntityHandling";
    if (code == V3CodeSystem.ENTITYNAMEPARTQUALIFIER)
      return "EntityNamePartQualifier";
    if (code == V3CodeSystem.ENTITYNAMEPARTQUALIFIERR2)
      return "EntityNamePartQualifierR2";
    if (code == V3CodeSystem.ENTITYNAMEPARTTYPE)
      return "EntityNamePartType";
    if (code == V3CodeSystem.ENTITYNAMEPARTTYPER2)
      return "EntityNamePartTypeR2";
    if (code == V3CodeSystem.ENTITYNAMEUSE)
      return "EntityNameUse";
    if (code == V3CodeSystem.ENTITYNAMEUSER2)
      return "EntityNameUseR2";
    if (code == V3CodeSystem.ENTITYRISK)
      return "EntityRisk";
    if (code == V3CodeSystem.ENTITYSTATUS)
      return "EntityStatus";
    if (code == V3CodeSystem.ENZC)
      return "ENZC";
    if (code == V3CodeSystem.EPSGCA)
      return "EPSG_CA";
    if (code == V3CodeSystem.EPSGCRS)
      return "EPSG_CRS";
    if (code == V3CodeSystem.EPSGGEODETICPARAMETERDATASET)
      return "EPSG-GeodeticParameterDataset";
    if (code == V3CodeSystem.EQUIPMENTALERTLEVEL)
      return "EquipmentAlertLevel";
    if (code == V3CodeSystem.ETHNICITY)
      return "Ethnicity";
    if (code == V3CodeSystem.EXPOSUREMODE)
      return "ExposureMode";
    if (code == V3CodeSystem.FDDC)
      return "FDDC";
    if (code == V3CodeSystem.FDDX)
      return "FDDX";
    if (code == V3CodeSystem.FDK)
      return "FDK";
    if (code == V3CodeSystem.GENDERSTATUS)
      return "GenderStatus";
    if (code == V3CodeSystem.GTSABBREVIATION)
      return "GTSAbbreviation";
    if (code == V3CodeSystem.HB)
      return "HB";
    if (code == V3CodeSystem.HCAIC)
      return "HC-AIC";
    if (code == V3CodeSystem.HCAIGC)
      return "HC-AIGC";
    if (code == V3CodeSystem.HCAIGN)
      return "HC-AIGN";
    if (code == V3CodeSystem.HCDIN)
      return "HC-DIN";
    if (code == V3CodeSystem.HCNPN)
      return "HC-NPN";
    if (code == V3CodeSystem.HEALTHCAREPROVIDERTAXONOMYHIPAA)
      return "HealthcareProviderTaxonomyHIPAA";
    if (code == V3CodeSystem.HEALTHCARESERVICELOCATION)
      return "HealthcareServiceLocation";
    if (code == V3CodeSystem.HHC)
      return "HHC";
    if (code == V3CodeSystem.HI)
      return "HI";
    if (code == V3CodeSystem.HL7APPROVALSTATUS)
      return "hl7ApprovalStatus";
    if (code == V3CodeSystem.HL7CMETATTRIBUTION)
      return "hl7CMETAttribution";
    if (code == V3CodeSystem.HL7COMMITTEEIDINRIM)
      return "HL7CommitteeIDInRIM";
    if (code == V3CodeSystem.HL7CONFORMANCEINCLUSION)
      return "HL7ConformanceInclusion";
    if (code == V3CodeSystem.HL7DEFINEDROSEPROPERTY)
      return "HL7DefinedRoseProperty";
    if (code == V3CodeSystem.HL7ITSTYPE)
      return "hl7ITSType";
    if (code == V3CodeSystem.HL7ITSVERSIONCODE)
      return "HL7ITSVersionCode";
    if (code == V3CodeSystem.HL7PUBLISHINGDOMAIN)
      return "hl7PublishingDomain";
    if (code == V3CodeSystem.HL7PUBLISHINGSECTION)
      return "hl7PublishingSection";
    if (code == V3CodeSystem.HL7PUBLISHINGSUBSECTION)
      return "hl7PublishingSubSection";
    if (code == V3CodeSystem.HL7STANDARDVERSIONCODE)
      return "HL7StandardVersionCode";
    if (code == V3CodeSystem.HL7UPDATEMODE)
      return "HL7UpdateMode";
    if (code == V3CodeSystem.HL7V3CONFORMANCE)
      return "hl7V3Conformance";
    if (code == V3CodeSystem.HL7VOTERESOLUTION)
      return "hl7VoteResolution";
    if (code == V3CodeSystem.HPC)
      return "HPC";
    if (code == V3CodeSystem.HTMLLINKTYPE)
      return "HtmlLinkType";
    if (code == V3CodeSystem.I10)
      return "I10";
    if (code == V3CodeSystem.I10P)
      return "I10P";
    if (code == V3CodeSystem.I9)
      return "I9";
    if (code == V3CodeSystem.I9C)
      return "I9C";
    if (code == V3CodeSystem.IBT)
      return "IBT";
    if (code == V3CodeSystem.IC2)
      return "IC2";
    if (code == V3CodeSystem.ICD10CA)
      return "ICD-10-CA";
    if (code == V3CodeSystem.ICDO)
      return "ICDO";
    if (code == V3CodeSystem.ICS)
      return "ICS";
    if (code == V3CodeSystem.ICSD)
      return "ICSD";
    if (code == V3CodeSystem.IDENTIFIERRELIABILITY)
      return "IdentifierReliability";
    if (code == V3CodeSystem.IDENTIFIERSCOPE)
      return "IdentifierScope";
    if (code == V3CodeSystem.IETF1766)
      return "IETF1766";
    if (code == V3CodeSystem.IETF3066)
      return "IETF3066";
    if (code == V3CodeSystem.INTEGRITYCHECKALGORITHM)
      return "IntegrityCheckAlgorithm";
    if (code == V3CodeSystem.ISO2100062004ERDD)
      return "iso21000-6-2004E-RDD";
    if (code == V3CodeSystem.ISO31661)
      return "ISO3166-1";
    if (code == V3CodeSystem.ISO31662)
      return "ISO3166-2";
    if (code == V3CodeSystem.ISO31663)
      return "ISO3166-3";
    if (code == V3CodeSystem.ISO4217)
      return "ISO4217";
    if (code == V3CodeSystem.IUPC)
      return "IUPC";
    if (code == V3CodeSystem.IUPP)
      return "IUPP";
    if (code == V3CodeSystem.JC8)
      return "JC8";
    if (code == V3CodeSystem.LANGUAGEABILITYMODE)
      return "LanguageAbilityMode";
    if (code == V3CodeSystem.LANGUAGEABILITYPROFICIENCY)
      return "LanguageAbilityProficiency";
    if (code == V3CodeSystem.LIVINGARRANGEMENT)
      return "LivingArrangement";
    if (code == V3CodeSystem.LN)
      return "LN";
    if (code == V3CodeSystem.LOCALMARKUPIGNORE)
      return "LocalMarkupIgnore";
    if (code == V3CodeSystem.LOCALREMOTECONTROLSTATE)
      return "LocalRemoteControlState";
    if (code == V3CodeSystem.MANAGEDPARTICIPATIONSTATUS)
      return "ManagedParticipationStatus";
    if (code == V3CodeSystem.MAPRELATIONSHIP)
      return "MapRelationship";
    if (code == V3CodeSystem.MARITALSTATUS)
      return "MaritalStatus";
    if (code == V3CodeSystem.MATERIALTYPE)
      return "MaterialType";
    if (code == V3CodeSystem.MDC)
      return "MDC";
    if (code == V3CodeSystem.MDDX)
      return "MDDX";
    if (code == V3CodeSystem.MDFATTRIBUTETYPE)
      return "MDFAttributeType";
    if (code == V3CodeSystem.MDFHMDMETSOURCETYPE)
      return "MdfHmdMetSourceType";
    if (code == V3CodeSystem.MDFHMDROWTYPE)
      return "MdfHmdRowType";
    if (code == V3CodeSystem.MDFRMIMROWTYPE)
      return "MdfRmimRowType";
    if (code == V3CodeSystem.MDFSUBJECTAREAPREFIX)
      return "MDFSubjectAreaPrefix";
    if (code == V3CodeSystem.MEDC)
      return "MEDC";
    if (code == V3CodeSystem.MEDCIN)
      return "MEDCIN";
    if (code == V3CodeSystem.MEDIATYPE)
      return "MediaType";
    if (code == V3CodeSystem.MEDR)
      return "MEDR";
    if (code == V3CodeSystem.MEDX)
      return "MEDX";
    if (code == V3CodeSystem.MESSAGECONDITION)
      return "MessageCondition";
    if (code == V3CodeSystem.MESSAGEWAITINGPRIORITY)
      return "MessageWaitingPriority";
    if (code == V3CodeSystem.MGPI)
      return "MGPI";
    if (code == V3CodeSystem.MIME)
      return "MIME";
    if (code == V3CodeSystem.MODIFYINDICATOR)
      return "ModifyIndicator";
    if (code == V3CodeSystem.MSH)
      return "MSH";
    if (code == V3CodeSystem.MULTUM)
      return "MULTUM";
    if (code == V3CodeSystem.MVX)
      return "MVX";
    if (code == V3CodeSystem.NAACCR)
      return "NAACCR";
    if (code == V3CodeSystem.NAICS)
      return "NAICS";
    if (code == V3CodeSystem.NDA)
      return "NDA";
    if (code == V3CodeSystem.NDC)
      return "NDC";
    if (code == V3CodeSystem.NIC)
      return "NIC";
    if (code == V3CodeSystem.NMMDS)
      return "NMMDS";
    if (code == V3CodeSystem.NOC)
      return "NOC";
    if (code == V3CodeSystem.NUBCUB92)
      return "NUBC-UB92";
    if (code == V3CodeSystem.NUCCPROVIDERCODES)
      return "NUCCProviderCodes";
    if (code == V3CodeSystem.NULLFLAVOR)
      return "NullFlavor";
    if (code == V3CodeSystem.OBSERVATIONINTERPRETATION)
      return "ObservationInterpretation";
    if (code == V3CodeSystem.OBSERVATIONMETHOD)
      return "ObservationMethod";
    if (code == V3CodeSystem.OBSERVATIONVALUE)
      return "ObservationValue";
    if (code == V3CodeSystem.OHA)
      return "OHA";
    if (code == V3CodeSystem.OPINIONS)
      return "OPINIONS";
    if (code == V3CodeSystem.ORDERABLEDRUGFORM)
      return "OrderableDrugForm";
    if (code == V3CodeSystem.ORGANIZATIONNAMETYPE)
      return "OrganizationNameType";
    if (code == V3CodeSystem.PARAMETERIZEDDATATYPE)
      return "ParameterizedDataType";
    if (code == V3CodeSystem.PARTICIPATIONFUNCTION)
      return "ParticipationFunction";
    if (code == V3CodeSystem.PARTICIPATIONMODE)
      return "ParticipationMode";
    if (code == V3CodeSystem.PARTICIPATIONSIGNATURE)
      return "ParticipationSignature";
    if (code == V3CodeSystem.PARTICIPATIONTYPE)
      return "ParticipationType";
    if (code == V3CodeSystem.PATIENTIMPORTANCE)
      return "PatientImportance";
    if (code == V3CodeSystem.PAYMENTTERMS)
      return "PaymentTerms";
    if (code == V3CodeSystem.PERIODICINTERVALOFTIMEABBREVIATION)
      return "PeriodicIntervalOfTimeAbbreviation";
    if (code == V3CodeSystem.PERSONDISABILITYTYPE)
      return "PersonDisabilityType";
    if (code == V3CodeSystem.PNDS)
      return "PNDS";
    if (code == V3CodeSystem.POS)
      return "POS";
    if (code == V3CodeSystem.POSTALADDRESSUSE)
      return "PostalAddressUse";
    if (code == V3CodeSystem.PROBABILITYDISTRIBUTIONTYPE)
      return "ProbabilityDistributionType";
    if (code == V3CodeSystem.PROCEDUREMETHOD)
      return "ProcedureMethod";
    if (code == V3CodeSystem.PROCESSINGID)
      return "ProcessingID";
    if (code == V3CodeSystem.PROCESSINGMODE)
      return "ProcessingMode";
    if (code == V3CodeSystem.QUERYPARAMETERVALUE)
      return "QueryParameterValue";
    if (code == V3CodeSystem.QUERYPRIORITY)
      return "QueryPriority";
    if (code == V3CodeSystem.QUERYQUANTITYUNIT)
      return "QueryQuantityUnit";
    if (code == V3CodeSystem.QUERYREQUESTLIMIT)
      return "QueryRequestLimit";
    if (code == V3CodeSystem.QUERYRESPONSE)
      return "QueryResponse";
    if (code == V3CodeSystem.QUERYSTATUSCODE)
      return "QueryStatusCode";
    if (code == V3CodeSystem.RACE)
      return "Race";
    if (code == V3CodeSystem.RC)
      return "RC";
    if (code == V3CodeSystem.RCFB)
      return "RCFB";
    if (code == V3CodeSystem.RCV2)
      return "RCV2";
    if (code == V3CodeSystem.RELATIONALOPERATOR)
      return "RelationalOperator";
    if (code == V3CodeSystem.RELATIONSHIPCONJUNCTION)
      return "RelationshipConjunction";
    if (code == V3CodeSystem.RELIGIOUSAFFILIATION)
      return "ReligiousAffiliation";
    if (code == V3CodeSystem.RESPONSELEVEL)
      return "ResponseLevel";
    if (code == V3CodeSystem.RESPONSEMODALITY)
      return "ResponseModality";
    if (code == V3CodeSystem.RESPONSEMODE)
      return "ResponseMode";
    if (code == V3CodeSystem.ROLECLASS)
      return "RoleClass";
    if (code == V3CodeSystem.ROLECODE)
      return "RoleCode";
    if (code == V3CodeSystem.ROLELINKSTATUS)
      return "RoleLinkStatus";
    if (code == V3CodeSystem.ROLELINKTYPE)
      return "RoleLinkType";
    if (code == V3CodeSystem.ROLESTATUS)
      return "RoleStatus";
    if (code == V3CodeSystem.ROUTEOFADMINISTRATION)
      return "RouteOfAdministration";
    if (code == V3CodeSystem.SCDHECGISSPATIALACCURACYTIERS)
      return "SCDHEC-GISSpatialAccuracyTiers";
    if (code == V3CodeSystem.SDM)
      return "SDM";
    if (code == V3CodeSystem.SEQUENCING)
      return "Sequencing";
    if (code == V3CodeSystem.SETOPERATOR)
      return "SetOperator";
    if (code == V3CodeSystem.SNM)
      return "SNM";
    if (code == V3CodeSystem.SNM3)
      return "SNM3";
    if (code == V3CodeSystem.SNT)
      return "SNT";
    if (code == V3CodeSystem.SPECIALARRANGEMENT)
      return "SpecialArrangement";
    if (code == V3CodeSystem.SPECIMENTYPE)
      return "SpecimenType";
    if (code == V3CodeSystem.STYLETYPE)
      return "StyleType";
    if (code == V3CodeSystem.SUBSTANCEADMINSUBSTITUTION)
      return "SubstanceAdminSubstitution";
    if (code == V3CodeSystem.SUBSTITUTIONCONDITION)
      return "SubstitutionCondition";
    if (code == V3CodeSystem.TABLECELLHORIZONTALALIGN)
      return "TableCellHorizontalAlign";
    if (code == V3CodeSystem.TABLECELLSCOPE)
      return "TableCellScope";
    if (code == V3CodeSystem.TABLECELLVERTICALALIGN)
      return "TableCellVerticalAlign";
    if (code == V3CodeSystem.TABLEFRAME)
      return "TableFrame";
    if (code == V3CodeSystem.TABLERULES)
      return "TableRules";
    if (code == V3CodeSystem.TARGETAWARENESS)
      return "TargetAwareness";
    if (code == V3CodeSystem.TELECOMMUNICATIONADDRESSUSE)
      return "TelecommunicationAddressUse";
    if (code == V3CodeSystem.TELECOMMUNICATIONCAPABILITIES)
      return "TelecommunicationCapabilities";
    if (code == V3CodeSystem.TIMINGEVENT)
      return "TimingEvent";
    if (code == V3CodeSystem.TRANSMISSIONRELATIONSHIPTYPECODE)
      return "TransmissionRelationshipTypeCode";
    if (code == V3CodeSystem.TRIBALENTITYUS)
      return "TribalEntityUS";
    if (code == V3CodeSystem.TRIGGEREVENTID)
      return "TriggerEventID";
    if (code == V3CodeSystem.UC)
      return "UC";
    if (code == V3CodeSystem.UCUM)
      return "UCUM";
    if (code == V3CodeSystem.UMD)
      return "UMD";
    if (code == V3CodeSystem.UML)
      return "UML";
    if (code == V3CodeSystem.UNITSOFMEASURE)
      return "UnitsOfMeasure";
    if (code == V3CodeSystem.UPC)
      return "UPC";
    if (code == V3CodeSystem.URLSCHEME)
      return "URLScheme";
    if (code == V3CodeSystem.VACCINEMANUFACTURER)
      return "VaccineManufacturer";
    if (code == V3CodeSystem.VACCINETYPE)
      return "VaccineType";
    if (code == V3CodeSystem.VOCABULARYDOMAINQUALIFIER)
      return "VocabularyDomainQualifier";
    if (code == V3CodeSystem.W1W2)
      return "W1-W2";
    if (code == V3CodeSystem.W4)
      return "W4";
    if (code == V3CodeSystem.WC)
      return "WC";
    return "?";
  }


}

