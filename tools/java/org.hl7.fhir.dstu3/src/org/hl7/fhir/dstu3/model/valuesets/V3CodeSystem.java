package org.hl7.fhir.dstu3.model.valuesets;

/*
  Copyright (c) 2011+, HL7, Inc.
  All rights reserved.
  
  Redistribution and use in source and binary forms, with or without modification, 
  are permitted provided that the following conditions are met:
  
   * Redistributions of source code must retain the above copyright notice, this 
     list of conditions and the following disclaimer.
   * Redistributions in binary form must reproduce the above copyright notice, 
     this list of conditions and the following disclaimer in the documentation 
     and/or other materials provided with the distribution.
   * Neither the name of HL7 nor the names of its contributors may be used to 
     endorse or promote products derived from this software without specific 
     prior written permission.
  
  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
  IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
  INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
  POSSIBILITY OF SUCH DAMAGE.
  
*/

// Generated on Sat, Jun 20, 2015 11:30+1000 for FHIR v0.5.0


public enum V3CodeSystem {

        /**
         * Five character alphabetic codes fit into current claims processing software or onto standard paper claim forms. ABC Codes give business parity to licensed CAM and nurse providers who file claims to insurance companies. .
         */
        ABCCODES, 
        /**
         * AcknowledgementCondition
         */
        ACKNOWLEDGEMENTCONDITION, 
        /**
         * A site specific problem code
         */
        ACKNOWLEDGEMENTDETAILCODE, 
        /**
         * Acknowledgement Detail Type
         */
        ACKNOWLEDGEMENTDETAILTYPE, 
        /**
         * Acknowledgement code as described in HL7 message processing rules.
         */
        ACKNOWLEDGEMENTTYPE, 
        /**
         * Index for Radiological Diagnosis Revised, 3rd Edition 1986, American College of Radiology, Reston, VA.
         */
        ACR, 
        /**
         * ActClass
         */
        ACTCLASS, 
        /**
         * The table that provides the detailed or rich codes for the Act classes.
         */
        ACTCODE, 
        /**
         * A qualitative measure of the degree of exposure to the causative agent.  This includes concepts such as "low", "medium" and "high".  This quantifies how the quantity that was available to be administered to the target differs from typical or background levels of the substance.
         */
        ACTEXPOSURELEVELCODE, 
        /**
         * Processing consideration and clarification codes.
         */
        ACTINVOICEELEMENTMODIFIER, 
        /**
         * ActMood
         */
        ACTMOOD, 
        /**
         * ActPriority
         */
        ACTPRIORITY, 
        /**
         * ActReason
         */
        ACTREASON, 
        /**
         * ActRelationshipCheckpoint
         */
        ACTRELATIONSHIPCHECKPOINT, 
        /**
         * ActRelationshipJoin
         */
        ACTRELATIONSHIPJOIN, 
        /**
         * ActRelationshipSplit
         */
        ACTRELATIONSHIPSPLIT, 
        /**
         * Used to indicate that the target of the relationship will be a filtered subset of the total related set of targets.

                        Used when there is a need to limit the number of components to the first, the last, the next, the total, the average or some other filtered or calculated subset.
         */
        ACTRELATIONSHIPSUBSET, 
        /**
         * The source is an excerpt from the target.
         */
        ACTRELATIONSHIPTYPE, 
        /**
         * An anatomical location on an organism which can be the focus of an act.
         */
        ACTSITE, 
        /**
         * Contains the names (codes) for each of the states in the state-machine of the RIM Act class.
         */
        ACTSTATUS, 
        /**
         * ActUncertainty
         */
        ACTUNCERTAINTY, 
        /**
         * Description: A jurisdictional mandate in the US relating to privacy.
         */
        ACTUSPRIVACYLAW, 
        /**
         * Discussion: The hierarchical nature of these concepts shows composition.  E.g. "Street Name" is part of "Street Address Line"
         */
        ADDRESSPARTTYPE, 
        /**
         * Description: Uses of Addresses.   Lloyd to supply more complete description.
         */
        ADDRESSUSE, 
        /**
         * The gender of a person used for adminstrative purposes (as opposed to clinical gender)
         */
        ADMINISTRATIVEGENDER, 
        /**
         * Description: The AHFS Pharmacologic-Therapeutic Classification has been in use in hospitals in the United States since its inception in 1959. An integral part of the American Hospital Formulary Service, the AHFS classification allows the grouping of drugs with similar pharmacologic, therapeutic, and/or chemical characteristics. Today, the AHFS classification is used by many people outside of hospitals.
         */
        AHFS, 
        /**
         * American Indian and Alaska Native languages currently being used in the United States.
         */
        AMERICANINDIANALASKANATIVELANGUAGES, 
        /**
         * WHO Collaborating Centre for International Drug Monitoring, Box 26, S-751 03, Uppsala, Sweden.
         */
        ART, 
        /**
         * American Society for Testing & Materials and CPT4 (see Appendix X1 of Specification E1238 and Appendix X2 of Specification E1467).
         */
        AS4, 
        /**
         * ASTM's diagnostic codes and test result coding/grading systems for clinical neurophysiology. See ASTM Specification E1467, Appendix 2.
         */
        AS4E, 
        /**
         * Reference cultures (microorganisms, tissue cultures, etc.), related biological materials and associated data. American Type Culture Collection, 12301 Parklawn Dr, Rockville MD, 20852. (301) 881-2600. http://www.atcc.org
         */
        ATC, 
        /**
         * Description: Coded concepts representing the Binding Realms used for Context Binding of terminology in HL7 models.  Where concepts align with nations, the country codes from ISO 3166-1 2-character alpha are used for the codes.  For those realms where they do not,. codes are created for the concepts.  These codes are generally carried in InfrastructureRoot.realmcode, and are used in Context Binding statements.
         */
        BINDINGREALM, 
        /**
         * Description: HL7 version 2.x Body site used in chapter(s) 4; HL7 table 0163
         */
        BODYSITE, 
        /**
         * American Medical Association, P.O. Box 10946, Chicago IL  60610.
         */
        C4, 
        /**
         * American Medical Association, P.O. Box 10946, Chicago IL  60610.
         */
        C5, 
        /**
         * Calendar
         */
        CALENDAR, 
        /**
         * CalendarCycle
         */
        CALENDARCYCLE, 
        /**
         * CalendarType
         */
        CALENDARTYPE, 
        /**
         * CAM & Nursing Coding Vocabulary Set
         */
        CAMNCVS, 
        /**
         * These include unique codes for each unique chemical, including all generic drugs.  The codes do not distinguish among different dosing forms.  When multiple equivalent CAS numbers exist, use the first one listed in USAN. USAN 1990 and the USP dictionary of drug names, William M. Heller, Ph.D., Executive Editor, United States Pharmacopeial Convention, Inc., 12601 Twinbrook Parkway, Rockville, MD 20852.
         */
        CAS, 
        /**
         * CCI - Canadian Classification of Health Interventions, developed to accompany ICD-10-CA, maintained by CIHI (Canadian Institute for Health Information).

                        For example: sections  3.AA-3.BZ  Diagnostic Imaging Interventions on the Nervous System 3.AN.^ ^.^ ^    Diagnostic Imaging Interventions on the Brain 3.AN.40. ^ ^   Magnetic Resonance Imaging, Brain Incudes:        That for meninges, ventricles, cerebellum, brain stem, cisterna [of brain], posterior fossa MRI, brain 3.AN.40.VA   without contrast 3.AN.40.VC   following intravenous injection of contrast 3.AN.40.VZ   following percutaneous injection of contrast

                        CIHI Toronto Attn: Director of Standards 90 Eglinton Avenue, Suite 300 Toronto, Ontario Canada M4P 2Y3

                        Phone: (416) 481.2002 Fax: (416) 481-2950

                        www.cihi.ca
         */
        CCI, 
        /**
         * American Dental Association's Current Dental Terminology (CDT-2) code.  American Dental Association, 211 E. Chicago Avenue,. Chicago, Illinois 60611.
         */
        CD2, 
        /**
         * Public Health Practice Program Office, Centers for Disease Control and Prevention, 4770 Buford Highway, Atlanta, GA, 30421.  Also available via FTP: ftp.cdc.gov/pub/laboratory _info/CLIA and Gopher: gopher.cdc.gov:70/11/laboratory_info/CLIA
         */
        CDCA, 
        /**
         * Public Health Practice Program Office, Centers for Disease Control and Prevention, 4770 Buford Highway, Atlanta, GA, 30421.  Also available via FTP: ftp.cdc.gov/pub/laboratory _info/CLIA and Gopher: gopher.cdc.gov:70/11/laboratory_info/CLIA
         */
        CDCM, 
        /**
         * CDC Surveillance Codes. For data unique to specific public health surveillance requirements. Epidemiology Program Office, Centers for Disease Control and Prevention, 1600 Clifton Rd, Atlanta, GA, 30333. (404) 639-3661.
         */
        CDS, 
        /**
         * CEN PT007. A quite comprehensive set of ECG diagnostic codes (abbreviations) and descriptions published as a pre-standard by CEN TC251. Available from CEN TC251 secretariat, c/o Georges DeMoor, State University Hospital Gent, De Pintelaan 185-5K3, 9000 Ge
         */
        CE, 
        /**
         * Internet Assigned Numbers Authority (IANA) Charset Types
         */
        CHARSET, 
        /**
         * Simon Leeming, Beth Israel Hospital, Boston MA.  Codes for radiology reports.
         */
        CLP, 
        /**
         * Code systems used in HL7 standards.
         */
        CODESYSTEM, 
        /**
         * HL7 Code System Type
         */
        CODESYSTEMTYPE, 
        /**
         * Identifies how to interpret the instance of the code, codeSystem value in a set of translations.  Since HL7 (or a government body) may mandate that codes from certain code systems be sent in conformant messages, other synonyms that are sent in the translation set need to be distinguished among the originally captured source, the HL7 specified code, or some future role.  When this code is NULL, it indicates that the translation is an undefined type.  When valued, this property must contain one of the following values:

                        SRC - Source (or original) code HL7 - HL7 Specified or Mandated SH - both HL7 mandated and the original code (precoordination)

                        There may be additional values added to this value set as we work through the use of codes in messages and determine other Use Cases requiring special interpretation of the translations.
         */
        CODINGRATIONALE, 
        /**
         * Describes the type of communication function that the associated entity plays in the associated transmission.
         */
        COMMUNICATIONFUNCTIONTYPE, 
        /**
         * CompressionAlgorithm
         */
        COMPRESSIONALGORITHM, 
        /**
         * Possible Concept Code Relationships
         */
        CONCEPTCODERELATIONSHIP, 
        /**
         * Indicates whether the concept that is the target should be interpreted as itself, or whether it should be expanded to include its child concepts, or both when it is included in the source domain/valueset.
         */
        CONCEPTGENERALITY, 
        /**
         * HL7 Value Set and Coded Concept Property Codes
         */
        CONCEPTPROPERTY, 
        /**
         * HL7 Coded Concept Status
         */
        CONCEPTSTATUS, 
        /**
         * Confidentiality
         */
        CONFIDENTIALITY, 
        /**
         * The type of cap associated with a container
         */
        CONTAINERCAP, 
        /**
         * A material in a blood collection container that facilites the separation of of blood cells from serum or plasma
         */
        CONTAINERSEPARATOR, 
        /**
         * Description:Identifies the order in which content should be processed.
         */
        CONTENTPROCESSINGMODE, 
        /**
         * The styles of context conduction usable by relationships within a static model derived from tyhe HL7 Reference Information Model.
         */
        CONTEXTCONDUCTIONSTYLE, 
        /**
         * This table contains the control codes that are used to manage the propagation and scope of a particular ActRelationship or Participation  within a set of Acts.
         */
        CONTEXTCONTROL, 
        /**
         * CAN/CSA-Z795-96 (R2001) - This Standard provides a framework for consistent recording and classification of information on work-related injuries and diseases in Canada (injury coding).

                        It is constituted of Nature of injury, body part, side of body. For example: Cut or laceration of the Upper Arm,  Left Injury = 03400; body part = 31100; side of body = L

                        Code set is maintained by the Canadian Standards Association (CSA).

                        The Canadian Standards Association 5060 Spectrum Way Mississauga, Ontario Canada L4W 5N6

                        Phone: (416) 747-4000 1-800-463-6727 Fax: (416) 747-2473
         */
        CSAID, 
        /**
         * International coding system for adverse drug reactions. In the USA, maintained by the FDA, Rockville, MD.
         */
        CST, 
        /**
         * The currency unit as defined in ISO 4217
         */
        CURRENCY, 
        /**
         * National Immunization Program, Centers for Disease Control and Prevention, 1660 Clifton Road, Atlanta, GA, 30333
         */
        CVX, 
        /**
         * DataOperation
         */
        DATAOPERATION, 
        /**
         * DataType
         */
        DATATYPE, 
        /**
         * From the Message Standards Classes table of the SNOMED-DICOM-Microglossary. College of American Pathologists, Skokie, IL, 60077-1034
         */
        DCL, 
        /**
         * Dean Bidgood, MD; Duke University Medical Center, Durham NC. Digital Imaging and Communications in Medicine (DICOM).  From NEMA Publications PS-3.1 - PS 3.12: The ACR-NEMA DICOM Standard. National Electrical Manufacturers Association (NEMA). Rosslyn, VA,
         */
        DCM, 
        /**
         * Dentition
         */
        DENTITION, 
        /**
         * Domain values for the Device.Alert_levelCode
         */
        DEVICEALERTLEVEL, 
        /**
         * Identifies the current completion state of a clinical document.
         */
        DOCUMENTCOMPLETION, 
        /**
         * Identifies the storage status of a document.
         */
        DOCUMENTSTORAGE, 
        /**
         * HL7 Image Management Special Interest Group, Health Level Seven, Ann Arbor, MI.
         */
        DQL, 
        /**
         * Available from Euclides Foundation International nv, Excelsiorlaan 4A, B-1930 Zaventem, Belgium; Phone: 32 2 720 90 60.
         */
        E, 
        /**
         * Available from Euclides Foundation International nv (see above)
         */
        E5, 
        /**
         * Available from Euclides Foundation International nv, Excelsiorlaan 4A, B-1930 Zaventem, Belgium; Phone: 32 2 720 90 60.
         */
        E6, 
        /**
         * Available from Euclides Foundation International nv (see above)
         */
        E7, 
        /**
         * The status of an entry as it pertains to its review and incorporation into the HL7 domain specification database.
         */
        EDITSTATUS, 
        /**
         * Years of education that a person has completed
         */
        EDUCATIONLEVEL, 
        /**
         * EmployeeJobClass
         */
        EMPLOYEEJOBCLASS, 
        /**
         * EncounterAccident
         */
        ENCOUNTERACCIDENT, 
        /**
         * The level of resource intensiveness of patient care.
         */
        ENCOUNTERACUITY, 
        /**
         * EncounterAdmissionSource
         */
        ENCOUNTERADMISSIONSOURCE, 
        /**
         * This domain is defined in UB92 and applies to US realm only
         */
        ENCOUNTERREFERRALSOURCE, 
        /**
         * EncounterSpecialCourtesy
         */
        ENCOUNTERSPECIALCOURTESY, 
        /**
         * Classifies the Entity class and all of its subclasses.  The terminology is hierarchical.  At the top is this  HL7-defined domain of high-level categories (such as represented by the Entity subclasses). Each of these terms must be harmonized and is specializable. The value sets beneath are drawn from multiple, frequently external, domains that reflect much more fine-grained typing.
         */
        ENTITYCLASS, 
        /**
         * EntityCode
         */
        ENTITYCODE, 
        /**
         * EntityDeterminer in natural language grammar is the class of words that comprises articles, demonstrative pronouns, and quantifiers. In the RIM, determiner is a structural code in the Entity class to distinguish whether any given Entity object stands for some, any one, or a specific thing.
         */
        ENTITYDETERMINER, 
        /**
         * EntityHandling
         */
        ENTITYHANDLING, 
        /**
         * EntityNamePartQualifier
         */
        ENTITYNAMEPARTQUALIFIER, 
        /**
         * Description:The qualifier is a set of codes each of which specifies a certain subcategory of the name part in addition to the main name part type. For example, a given name may be flagged as a nickname, a family name may be a pseudonym or a name of public records.
         */
        ENTITYNAMEPARTQUALIFIERR2, 
        /**
         * EntityNamePartType
         */
        ENTITYNAMEPARTTYPE, 
        /**
         * Description:Indicates whether the name part is a given name, family name, prefix, suffix, etc.
         */
        ENTITYNAMEPARTTYPER2, 
        /**
         * EntityNameUse
         */
        ENTITYNAMEUSE, 
        /**
         * Description:A set of codes advising a system or user which name in a set of names to select for a given purpose.
         */
        ENTITYNAMEUSER2, 
        /**
         * The vocabulary table for the Entity.riskCode attribute
         */
        ENTITYRISK, 
        /**
         * The status of an instance of the RIM Entity class.
         */
        ENTITYSTATUS, 
        /**
         * Enzyme Committee of the International Union of Biochemistry and Molecular Biology. Enzyme Nomenclature: Recommendations on the Nomenclature and Classification of Enzyme-Catalysed Reactions. London: Academic Press, 1992.
         */
        ENZC, 
        /**
         * Description:The set of values found in the Coord Axis Code column of the Coordinate Axis table as maintained in the EPSG geodetic parameter dataset. These define the axis for coordinate systems for geographic coordinates.
         */
        EPSGCA, 
        /**
         * Description: The set of values found in the Coord Axis Code column of the Coordinate Axis table as maintained in the EPSG geodetic parameter dataset. These define the axis for coordinate systems for geographic coordinates.
         */
        EPSGCRS, 
        /**
         * Description: The EPSG (European Petroleum Survey Group) dataset represents all Datums, coordinate references (projected and 2D geographic) and coordinate systems (including Cartesian coordinate systems) used in surveying worldwide.  Each record includes a 4-8 digit unique identifier. The current version is available from http://www.epsg.org/.  The database contains over 4000 records covering spatial data applications worldwide.
         */
        EPSGGEODETICPARAMETERDATASET, 
        /**
         * EquipmentAlertLevel
         */
        EQUIPMENTALERTLEVEL, 
        /**
         * In the United States, federal standards for classifying data on ethnicity determine the categories used by federal agencies and exert a strong influence on categorization by state and local agencies and private sector organizations. The federal standards do not conceptually define ethnicity, and they recognize the absence of an anthropological or scientific basis for ethnicity classification.  Instead, the federal standards acknowledge that ethnicity is a social-political construct in which an individual's own identification with a particular ethnicity is preferred to observer identification.  The standards specify two minimum ethnicity categories: Hispanic or Latino, and Not Hispanic or Latino.  The standards define a Hispanic or Latino as a person of "Mexican, Puerto Rican, Cuban, South or Central America, or other Spanish culture or origin, regardless of race." The standards stipulate that ethnicity data need not be limited to the two minimum categories, but any expansion must be collapsible to those categories.  In addition, the standards stipulate that an individual can be Hispanic or Latino or can be Not Hispanic or Latino, but cannot be both.
         */
        ETHNICITY, 
        /**
         * Code for the mechanism by which the exposure agent was exchanged or potentially exchanged by the participants involved in the exposure.
         */
        EXPOSUREMODE, 
        /**
         * National Drug Data File. Proprietary product of First DataBank, Inc. (800) 633-3453, or http://www.firstdatabank.com.
         */
        FDDC, 
        /**
         * Used for drug-diagnosis interaction checking. Proprietary product of First DataBank, Inc. As above for FDDC.
         */
        FDDX, 
        /**
         * Dept. of Health & Human Services, Food & Drug Administration, Rockville, MD 20857. (device & analyte process codes).
         */
        FDK, 
        /**
         * GenderStatus
         */
        GENDERSTATUS, 
        /**
         * GTSAbbreviation
         */
        GTSABBREVIATION, 
        /**
         * Health Industry Business Communications Council, 5110 N. 40th St., Ste 120, Phoenix, AZ 85018.
         */
        HB, 
        /**
         * Description:
                        

                        A code assigned to any component that has medicinal properties, and supplies pharmacological activity or other direct effect in the diagnosis, cure, mitigation, treatment or prevention of disease, or to affect the structure or any function of the body of man or other animals.  http://www.hc-sc.gc.ca/dhp-mps/prodpharma/databasdon/index_e.html
         */
        HCAIC, 
        /**
         * Description: Codes for particular grouping of active ingredients.  This is the first 5 characters of active ingredient group number.  http://www.hc-sc.gc.ca/dhp-mps/prodpharma/databasdon/index_e.html
         */
        HCAIGC, 
        /**
         * Description: Codes for particular collections of active ingredients combined at specific strengths.  http://www.hc-sc.gc.ca/dhp-mps/prodpharma/databasdon/index_e.html
         */
        HCAIGN, 
        /**
         * Description: A Drug Identification Number (DIN) is a number assigned by Health Canada to a drug product prior to being marketed in Canada. It consists of eight digits (numbers) generated by a computer system in the Submission and Information Policy Division.  http://www.hc-sc.gc.ca/dhp-mps/prodpharma/databasdon/index_e.html
         */
        HCDIN, 
        /**
         * Description: A unique identifier assigned to natural health products that have been issued a product licence by Health Canada.  http://www.hc-sc.gc.ca/dhp-mps/prodnatur/applications/licen-prod/lnhpd-bdpsnh-eng.php
         */
        HCNPN, 
        /**
         * HealthcareProviderTaxonomyHIPAA
         */
        HEALTHCAREPROVIDERTAXONOMYHIPAA, 
        /**
         * A comprehensive classification of locations and settings where healthcare services are provided. This value set is based on the National Healthcare Safety Network (NHSN) location code system that has been developed over a number of years through CDC's interaction with a variety of healthcare facilities and is intended to serve a variety of reporting needs where coding of healthcare service locations is required.
         */
        HEALTHCARESERVICELOCATION, 
        /**
         * Home Health Care Classification System; Virginia Saba, EdD, RN; Georgetown University School of Nursing; Washington, DC.
         */
        HHC, 
        /**
         * Health Outcomes Institute codes for outcome variables available (with responses) from Stratis Health (formerly Foundation for Health Care Evaluation and Health Outcomes Institute), 2901 Metro Drive, Suite 400, Bloomington, MN, 55425-1525; (612) 854-3306 (voice); (612) 853-8503 (fax); dziegen@winternet.com. See examples in the Implementation Guide.
         */
        HI, 
        /**
         * Description: Codes for concepts describing the approval level of HL7 artifacts.  This code system reflects the concepts expressed in HL7's Governance & Operations Manual (GOM) past and present.
         */
        HL7APPROVALSTATUS, 
        /**
         * HL7CMETAttribution
         */
        HL7CMETATTRIBUTION, 
        /**
         * Holds the codes used to identify the committees and SIGS of HL7 in RIM repository tables.
         */
        HL7COMMITTEEIDINRIM, 
        /**
         * These concepts represent theconformance requirments defined for including or valuing an element of an HL7 message.  The concepts apply equally to conformance profiles  defined for Version 2.x messgaes as defined by the Conformance SIG, and to the conformance columns for Version 3 messages as specified in the HMD.
         */
        HL7CONFORMANCEINCLUSION, 
        /**
         * The property Ids that HL7 has defined for customizing Rational Rose.
         */
        HL7DEFINEDROSEPROPERTY, 
        /**
         * Description: Codes identifying types of HL7 Implementation Technology Specifications
         */
        HL7ITSTYPE, 
        /**
         * HL7 implementation technology specification versions. These codes will document the ITS type and version for message encoding. The code will appear in the instances based upon rules expressed in the ITS, and do not appear in the abstract message, either as it is presented to received from the ITS.
         */
        HL7ITSVERSIONCODE, 
        /**
         * Description: Codes for HL7 publishing 'domain's (specific content area)
         */
        HL7PUBLISHINGDOMAIN, 
        /**
         * Description: Codes for HL7 publishing 'section's (major business categories)
         */
        HL7PUBLISHINGSECTION, 
        /**
         * Description: Codes for HL7 publishing 'sub-section's (business sub-categories)
         */
        HL7PUBLISHINGSUBSECTION, 
        /**
         * This code system holds version codes for the Version 3 standards. Values are to be determined by HL7 and added with each new version of the HL7 Standard.
         */
        HL7STANDARDVERSIONCODE, 
        /**
         * The possible modes of updating that occur when an attribute is received by a system that already contains values for that attribute.
         */
        HL7UPDATEMODE, 
        /**
         * Description: Identifies allowed codes for HL7aTMs v3 conformance property.
         */
        HL7V3CONFORMANCE, 
        /**
         * Description: Based on concepts for resolutions from HL7 ballot spreadsheet according to HL7's Governance & Operations Manual (GOM).
         */
        HL7VOTERESOLUTION, 
        /**
         * Health Care Financing Administration (HCFA) Common Procedure Coding System (HCPCS)  modifiers.
         */
        HPC, 
        /**
         * HtmlLinkType values are drawn from HTML 4.0 and describe the relationship between the current document and the anchor that is the target of the link
         */
        HTMLLINKTYPE, 
        /**
         * World Health Publications, Albany, NY.
         */
        I10, 
        /**
         * Procedure Coding System (ICD-10-PCS).  See http://www/hcfa.gov/stats/icd10.icd10.htm for more information.
         */
        I10P, 
        /**
         * World Health Publications, Albany, NY.
         */
        I9, 
        /**
         * Commission on Professional and Hospital Activities, 1968 Green Road, Ann Arbor, MI 48105 (includes all procedures and diagnostic tests).
         */
        I9C, 
        /**
         * International Society of Blood Transfusion.  Blood Group Terminology 1990.  VOX Sanquines 1990 58(2):152-169.
         */
        IBT, 
        /**
         * International Classification of Health Problems in Primary Care, Classification Committee of World Organization of National Colleges, Academies and Academic Associations of General Practitioners (WONCA), 3rd edition.  An adaptation of ICD9 intended for use in General Medicine, Oxford University Press.
         */
        IC2, 
        /**
         * Canadian Coding Standards ICD-10 CA. These standards are a compilation of international rules of coding as established by the World Health Organization (International Classification of Diseases, 10th Revision, Volume 2) and the Diagnosis Typing Standard developed to denote case complexity for application in Canadian facilities.

                        For example: 

                        
                           
                              L40		Psoriasis

                           
                           
                              L40.0		Psoriasis vulgaris

                              
                                 
                                    Nummular psoriasis

                                 
                                 
                                    Plaque psoriasis

                                 
                              
                           
                           
                              L40.1		Generalized pustular psoriasis

                              
                                 
                                    Impetigo herpetiformis

                                 
                                 
                                    Von ZumbuschaTMs disease

                                 
                              
                           
                           
                              L40.2 		Acrodermatitis continua

                           
                           
                              L40.3		Pustulosis palmaris et plantaris

                           
                           
                              L40.4		Guttate psoriasis

                           
                           
                              L40.5*		Arthropathic psoriasis (M07.0-M07.3*)(M09.0*)

                           
                           
                              L40.8		Other psoriasis

                              
                                 
                                    Erythroderma psoraticum

                                 
                                 
                                    Erythrodermic psoriasis

                                 
                                 
                                    Flexural psoriasis

                                 
                              
                           
                           
                              L40.9		Psoriasis unspecified

                           
                        
                        They are maintained by CIHI (Canadian Institute for Health Information).

                        CIHI Toronto

                        Attn: Director of Standards

                        90 Eglinton Avenue, Suite 300 

                        Toronto, Ontario

                        Canada

                        M4P 2Y3

                        Phone: (416) 481.2002

                        Fax: (416) 481-2950 

                        www.cihi.ca
         */
        ICD10CA, 
        /**
         * International Classification of Diseases for Oncology, 2nd Edition.  World Health Organization: Geneva, Switzerland, 1990.  Order from: College of American Pathologists, 325 Waukegan Road, Northfield, IL, 60093-2750.  (847) 446-8800.
         */
        ICDO, 
        /**
         * Commission on Professional and Hospital Activities, 1968 Green Road, Ann Arbor, MI 48105.
         */
        ICS, 
        /**
         * International Classification of Sleep Disorders Diagnostic and Coding Manual, 1990, available from American Sleep Disorders Association, 604 Second Street SW, Rochester, MN  55902
         */
        ICSD, 
        /**
         * Specifies the reliability with which the identifier is known. This attribute MAY be used to assist with identifier matching algorithms.
         */
        IDENTIFIERRELIABILITY, 
        /**
         * Description: Codes to specify the scope in which the identifier applies to the object with which it is associated, and used in the datatype property II.
         */
        IDENTIFIERSCOPE, 
        /**
         * Language identifiers as defined by IETF RFC 1766: Tags for the Identification of Languages, or its successor on the IETF Standards Track. The biblio ref for RFC 1766 is: IETF (Internet Engineering Task Force), RFC 1766: Tag
         */
        IETF1766, 
        /**
         * from OID registry
         */
        IETF3066, 
        /**
         * IntegrityCheckAlgorithm
         */
        INTEGRITYCHECKALGORITHM, 
        /**
         * ISO/IEC 21000-6:2004 describes a Rights Data Dictionary which comprises a set of clear, consistent, structured, integrated and uniquely identified terms to support the MPEG-21 Rights Expression Language (REL), ISO/IEC 21000-5. Annex A specifies the methodology for and structure of the RDD Dictionary, and specifies how further Terms may be defined under the governance of a Registration Authority, requirements for which are described in Annex C.

                        Taken together, these specifications and the RDD Dictionary and Database make up the RDD System. Use of the RDD System will facilitate the accurate exchange and processing of information between interested parties involved in the administration of rights in, and use of, Digital Items, and in particular it is intended to support ISO/IEC 21000-5 (REL). Clause 6 describes how ISO/IEC 21000-6:2004 relates to ISO/IEC 21000-5.

                        As well as providing definitions of terms for use in ISO/IEC 21000-5, the RDD System is designed to support the mapping of terms from different namespaces. Such mapping will enable the transformation of metadata from the terminology of one namespace (or Authority) into that of another namespace. Mapping, to ensure minimum ambiguity or loss of semantic integrity, will be the responsibility of the Registration Authority. Provision of automated trm look-up is also a requirement.

                        The RDD Dictionary is a prescriptive dctionary, in the sense that it defines a single meaning for a trm represented by a particular RddAuthorized TermName, but it is also inclusive in that it can recognize the prescription of other Headwords and definitions by other Authorities and incorporates them through mappings. The RDD Dictionary also supports the circumstance that the same name may have different meanings under different Authorities. ISO/IEC 21000-6:2004describes audit provisions so that additions, amendments and deletions to Terms and their attributes can be tracked.

                        ISO/IEC 21000-6:2004 recognizes legal definitions as and only as Terms from other Authorities that can be mapped into the RDD Dictionary. Therefore Terms that are directly authorized by the RDD Registration Authority neither define nor prescribe intellectual property rights or other legal entities.
         */
        ISO2100062004ERDD, 
        /**
         * Two character country codes
         */
        ISO31661, 
        /**
         * Three character country codes
         */
        ISO31662, 
        /**
         * Numeric country codes
         */
        ISO31663, 
        /**
         * ISO 4217 currency code
         */
        ISO4217, 
        /**
         * Codes used by IUPAC/IFF to identify the component (analyte) measured. Contact Henrik Olesen, as above for IUPP.
         */
        IUPC, 
        /**
         * International Union of Pure and Applied Chemistry/International Federation of Clinical Chemistry. The Silver Book: Compendium of terminology and nomenclature of properties in clinical laboratory sciences. Oxford: Blackwell Scientific Publishers, 1995. Henrik Olesen, M.D., D.M.Sc., Chairperson, Department of Clinical Chemistry, KK76.4.2, Rigshospitalet, University Hospital of Copenhagen, DK-2200, Copenhagen. http://inet.uni-c.dk/~qukb7642/
         */
        IUPP, 
        /**
         * Clinical examination classification code.  Japan Association of Clinical Pathology.  Version 8, 1990.  A multiaxial code  including a subject code (e.g., Rubella = 5f395, identification code (e.g., virus ab IGG), a specimen code (e.g., serum =023) and a method code (e.g., ELISA = 022)
         */
        JC8, 
        /**
         * LanguageAbilityMode
         */
        LANGUAGEABILITYMODE, 
        /**
         * LanguageAbilityProficiency
         */
        LANGUAGEABILITYPROFICIENCY, 
        /**
         * A code depicting the living arrangements of a person
         */
        LIVINGARRANGEMENT, 
        /**
         * Regenstrief Institute, c/o LOINC, 1050 Wishard Blvd., 5th floor, Indianapolis, IN  46202.  317/630-7433.   Available from the Regenstrief Institute server at http://www.regenstrief.org/loinc/loinc.htm.  Also available via HL7 file server: FTP/Gopher (www.mcis.duke.edu/standards/ termcode/loinclab and www.mcis.duke.edu/standards/termcode/loinclin) and World Wide Web (http:// www.mcis.duke.edu/ standards/termcode/loincl.htm).   January 2000 version has identifiers, synonyms and cross-reference codes for reporting over 26,000 laboratory and related observations and 1,500 clinical measures.
         */
        LN, 
        /**
         * Tells a receiver to ignore just the local markup tags (local_markup, local_header, local_attr) when value="markup", or to ignore the local markup tags and all contained content when value="all"
         */
        LOCALMARKUPIGNORE, 
        /**
         * LocalRemoteControlState
         */
        LOCALREMOTECONTROLSTATE, 
        /**
         * The status of an instance of the RIM Participation class.
         */
        MANAGEDPARTICIPATIONSTATUS, 
        /**
         * The closeness or quality of the mapping between the HL7 concept (as represented by the HL7 concept identifier) and the source coding system. The values are patterned after the similar relationships used in the UMLS Metathesaurus. Because the HL7 coding sy
         */
        MAPRELATIONSHIP, 
        /**
         * MaritalStatus
         */
        MARITALSTATUS, 
        /**
         * MaterialType
         */
        MATERIALTYPE, 
        /**
         * The nomenclature relates primarily to vital signs monitoring, but also includes semantics of other medical devices that are commonly used in acute care settings.  There are multiple coding partitions each of which has a systematic name consisting of a set of base concepts and differentiating criteria.
         */
        MDC, 
        /**
         * Codes Used for drug-diagnosis interaction checking. Proprietary product. Hierarchical drug codes for identifying drugs down to manufacturer and pill size. MediSpan, Inc., 8425 Woodfield Crossing Boulevard, Indianapolis, IN 46240. Tel: (800) 428-4495.  WWW: http://www.espan.com/medispan/pages/ medhome.html. As above for MGPI.
         */
        MDDX, 
        /**
         * MDFAttributeType
         */
        MDFATTRIBUTETYPE, 
        /**
         * Code to identify the source of a Message Element Type represented in the 'of MET' column of an HMD.
         */
        MDFHMDMETSOURCETYPE, 
        /**
         * The row type codes for the tabular representation of a Hierarchical Message Description.
         */
        MDFHMDROWTYPE, 
        /**
         * The row types for the tabular representation of an R-MIM.
         */
        MDFRMIMROWTYPE, 
        /**
         * The standard prefixes used in Rose for RIM subject areas that determine the role or function of each subject area.
         */
        MDFSUBJECTAREAPREFIX, 
        /**
         * Proprietary Codes for identifying drugs. Proprietary product of Medical Economics Data, Inc. (800) 223-0581.
         */
        MEDC, 
        /**
         * MEDCIN contains more than 175,000 clinical data elements arranged in a hierarchy, with each item having weighted links to relevant diagnoses.  The clinical data elements are organized into six basic termtypes designed to accommodate information relevant to a clinical encounter.  The basic termtypes in MEDCIN's terminological hierarchy are as follows:

                        Symptoms History Physical Examination Tests Diagnoses Therapy

                        Within this basic structure, MEDCIN terms are further organized in a ten level terminological hierarchy, supplemented by an optional, multi-hierarchical diagnostic index.  For example, the symptom of "difficulty breathing" is placed in the terminological hierarchy as a subsidiary (or "child") finding of "pulmonary symptoms" although the presence (or absence) of difficulty breathing can related to conditions as diverse as myocardial infarction, bronchitis, pharyngeal foreign bodies, asthma, pulmonary embolism, etc.  MEDCIN's diagnostic index provides more than 800 such links for difficulty breathing.
         */
        MEDCIN, 
        /**
         * Internet Assigned Numbers Authority (IANA) Mime Media Types
         */
        MEDIATYPE, 
        /**
         * Dr. Louise Wood, Medicines Control Agency, Market Towers, 1 Nine Elms Lane, London SW85NQ, UK   Tel: (44)0 171-273-0000 WWW:  http://www.open.gov.uk/mca/mcahome.htm
         */
        MEDR, 
        /**
         * Used for drug-diagnosis interaction checking. Proprietary product of Medical Economics Data, Inc. (800) 223-0581.
         */
        MEDX, 
        /**
         * MessageCondition
         */
        MESSAGECONDITION, 
        /**
         * Indicates that the receiver has messages for the sender
         */
        MESSAGEWAITINGPRIORITY, 
        /**
         * Medispan hierarchical drug codes for identifying drugs down to manufacturer and pill size.  Proprietary product of MediSpan, Inc., 8425 Woodfield Crossing Boulevard, Indianapolis, IN 46240. Tel: (800) 428-4495.
         */
        MGPI, 
        /**
         * IETF MIME media types
         */
        MIME, 
        /**
         * ModifyIndicator
         */
        MODIFYINDICATOR, 
        /**
         * Medical Subject Headings (MeSH). Bethesda (MD): National Library of Medicine, 2004
         */
        MSH, 
        /**
         * Broadly, the fields and values in the Multum Lexicon and the VantageRx Database are intended to be available for use in any HL7 message that includes a reference to non-veterinary drug products or active ingredients that are either approved for sale by the FDA or readily available in the United States.  The following inter-related definitions recently circulated by us to the HL7 Vocabulary Technical Committee explain the scope of what we mean by "drug product" and "active ingredient."  (A definition for "drug ingredient" is also provided here because the definition of "active ingredient" is reliant on this term.)

                        Drug Product A drug product is a manufactured or extemporaneously-compounded physiologically-active material intended by the preparer to achieve therapeutic, diagnostic, or preventative effects via biochemical mechanisms when applied to an epithelial surface or placed in an internal body space of a targeted organism.

                        Drug Ingredient A drug ingredient is a chemical compound or biologic agent that occurs in a drug product.

                        Active Ingredient An active ingredient is a drug ingredient that mediates one or more of the intended therapeutic, diagnostic, or preventative effects of a drug product and is present in sufficient quantities to achieve such effects according to the allopathic tradition of healthcare practice.
         */
        MULTUM, 
        /**
         * National Immunization Program, Centers for Disease Control and Prevention, 1660 Clifton Road, Atlanta, GA, 30333
         */
        MVX, 
        /**
         * NAACCR Cancer Registry
         */
        NAACCR, 
        /**
         * North American Industry Classification System(NAICS) for the United States, a new economic classification system that replaces the 1987 Standard Industrial Classification (SIC) for statistical purposes.  NAICS is a system for classifying establishments by type of economic activity.  Its purposes are:  (1) to facilitate the collection, tabulation, presentation, and analysis of data relating to establishments, and (2) to promote uniformity and comparability in the presentation of statistical data describing the economy.  NAICS will be used by Federal statistical agencies that collect or publish data by industry.
         */
        NAICS, 
        /**
         * North American Nursing Diagnosis Association, Philadelphia, PA.
         */
        NDA, 
        /**
         * These provide unique codes for each distinct drug, dosing form, manufacturer, and packaging. (Available from the National Drug Code Directory, FDA, Rockville, MD, and other sources.)
         */
        NDC, 
        /**
         * Iowa Intervention Project, College of Nursing, University of Iowa, Iowa City, Iowa
         */
        NIC, 
        /**
         * The NMMDS is the minimum set of items of information with uniform definitions and categories concerning the specific dimension of the context of patient care delivery.  It represents the minimum data used to support the management and administration of patient/nursing care delivery across all types of settings.  The NMMDS is composed of seventeen (17) data elements organized into three categories: environment, nurse resources, and financial resources.  See Tables 1-3 for the elements and related definitions organized by each categories.  The NMMDS most appropriately focuses at the first level of accountability for patient/client/family/community nursing care: this may be the delivery unit, service, or center of excellence level.  The NMMDS supports numerous constructed variables as well as aggregation of data at the unit, institution, network, and system, etc levels.  This minimum data set provides the structure for the collection of uniform information that influences quality of patient care, directly and indirectly.
         */
        NMMDS, 
        /**
         * NOC - Nursing Outcome Codes
         */
        NOC, 
        /**
         * The UB-92 data element specifications are developed and maintained by the NUBC.  The data element specifications are for use in EDI billing and payment transactions and related business applications.  There is a proprietary  fee. Available from the National Uniform Billing Committee of the American Hospital Association, One North Franklin, Chicago, IL 60606.  "UB-92 National Uniform Billing Data Element Specifications as developed by the National Uniform Billing Committee as of August 13, 1999".   url:  http://www.nubc.org
         */
        NUBCUB92, 
        /**
         * The Provider Taxonomy Code List is published (released) twice a year on July 1st and January 1st. The July publication is effective for use on October 1st and the January publication is effective for use on April 1st. The time between the publication release and the effective date is considered an implementation period to allow providers, payers and vendors an opportunity to incorporate any changes into their systems. This listing includes Active codes approved for use effective April 1st, 2003, version 3.0; and codes that are New and approved for use effective October 1st, 2003, version 3.1.
         */
        NUCCPROVIDERCODES, 
        /**
         * NullFlavor
         */
        NULLFLAVOR, 
        /**
         * ObservationInterpretation
         */
        OBSERVATIONINTERPRETATION, 
        /**
         * ObservationMethod
         */
        OBSERVATIONMETHOD, 
        /**
         * This domain is the root domain to which all HL7-recognized value sets for the Observation.value attribute will be linked when Observation.value has a coded data type.
         */
        OBSERVATIONVALUE, 
        /**
         * Omaha Visiting Nurse Association, Omaha, NB.
         */
        OHA, 
        /**
         * Description: Codes to identify products and services that do not have DIN's and which need to be billed.  http://www.atlanticpharmaceutical.ca/default.asp?mn=5.23
         */
        OPINIONS, 
        /**
         * OrderableDrugForm
         */
        ORDERABLEDRUGFORM, 
        /**
         * OrganizationNameType
         */
        ORGANIZATIONNAMETYPE, 
        /**
         * ParameterizedDataType
         */
        PARAMETERIZEDDATATYPE, 
        /**
         * This code is used to specify the exact function an actor had in a service in all necessary detail. This domain may include local extensions (CWE).
         */
        PARTICIPATIONFUNCTION, 
        /**
         * Identifies the primary means by which an Entity participates in an Act.
         */
        PARTICIPATIONMODE, 
        /**
         * ParticipationSignature
         */
        PARTICIPATIONSIGNATURE, 
        /**
         * ParticipationType
         */
        PARTICIPATIONTYPE, 
        /**
         * Patient VIP code
         */
        PATIENTIMPORTANCE, 
        /**
         * Describes payment terms for a financial transaction, used in an invoice.

                        This is typically expressed as a responsibility of the acceptor or payor of an invoice.
         */
        PAYMENTTERMS, 
        /**
         * PeriodicIntervalOfTimeAbbreviation
         */
        PERIODICINTERVALOFTIMEABBREVIATION, 
        /**
         * A code identifying a person's disability.
         */
        PERSONDISABILITYTYPE, 
        /**
         * The PNDS provides standardized terms and codes for patient problems/nursing diagnoses, nursing interventions including actual or expected (goal) outcomes.   The PNDS provides standardized terms and codes for nursing diagnoses (a subset of NANDA), nursing interventions and outcomes. The outcomes and interventions are in a relational database. The PNDS intervention and outcome statements are attached in an Access Database. The NANDA diagnoses in the PNDS have already been registered by HL7.
         */
        PNDS, 
        /**
         * HCFA Place of Service Codes for Professional Claims (see http://www.hcfa.gov/medicare/poscode.htm).
         */
        POS, 
        /**
         * PostalAddressUse
         */
        POSTALADDRESSUSE, 
        /**
         * ProbabilityDistributionType
         */
        PROBABILITYDISTRIBUTIONTYPE, 
        /**
         * Identifies the technique used to perform a procedure.
         */
        PROCEDUREMETHOD, 
        /**
         * ProcessingID
         */
        PROCESSINGID, 
        /**
         * ProcessingMode
         */
        PROCESSINGMODE, 
        /**
         * The domain of coded values used as parameters within QueryByParameter queries.
         */
        QUERYPARAMETERVALUE, 
        /**
         * QueryPriority
         */
        QUERYPRIORITY, 
        /**
         * Values in this domain specify the units of a query quantity limited request.
         */
        QUERYQUANTITYUNIT, 
        /**
         * Definition: Defines the units associated with the magnitude of the maximum size limit of a query response that can be accepted by the requesting application.
         */
        QUERYREQUESTLIMIT, 
        /**
         * Values in this domain allow a query response system to return a precise response status.
         */
        QUERYRESPONSE, 
        /**
         * State attributes for Query event
         */
        QUERYSTATUSCODE, 
        /**
         * In the United States, federal standards for classifying data on race determine the categories used by federal agencies and exert a strong influence on categorization by state and local agencies and private sector organizations.  The federal standards do not conceptually define race, and they recognize the absence of an anthropological or scientific basis for racial classification.  Instead, the federal standards acknowledge that race is a social-political construct in which an individual's own identification with one more race categories is preferred to observer identification. The standards use a variety of features to define five minimum race categories. Among these features are descent from "the original peoples" of a specified region or nation.  The minimum race categories are American Indian or Alaska Native, Asian, Black or African American, Native Hawaiian or Other Pacific Islander, and White.  The federal standards stipulate that race data need not be limited to the five minimum categories, but any expansion must be collapsible to those categories.
         */
        RACE, 
        /**
         * The Read Clinical Classification of Medicine, Park View Surgery, 26 Leicester Rd., Loughborough LE11 2AG (includes drug procedure and other codes, as well as diagnostic codes).
         */
        RC, 
        /**
         * The Read Codes Four Byte Set consists of 4 alphanumeric characters. This version contains approximately 40,000 codes arranged in a hierarchical structure.

                        Top level hierarchy sections:	 Disorders Findings Surgical procedures Investigations Occupations Drugs
         */
        RCFB, 
        /**
         * The Read Codes Version 2 contains over 70,000 coded concepts arranged in a hierarchical structure.

                        Top level hierarchy sections:	 Disorders Findings Surgical procedures Investigations Occupations Drugs
         */
        RCV2, 
        /**
         * RelationalOperator
         */
        RELATIONALOPERATOR, 
        /**
         * RelationshipConjunction
         */
        RELATIONSHIPCONJUNCTION, 
        /**
         * Assigment of spiritual faith affiliation
         */
        RELIGIOUSAFFILIATION, 
        /**
         * Specifies whether a response is expected from the addressee of this interaction and what level of detail that response should include
         */
        RESPONSELEVEL, 
        /**
         * ResponseModality
         */
        RESPONSEMODALITY, 
        /**
         * Specifies the mode, immediate versus deferred or queued, by which a receiver should communicate its receiver responsibilities.
         */
        RESPONSEMODE, 
        /**
         * RoleClass
         */
        ROLECLASS, 
        /**
         * Specific classification codes for further qualifying RoleClass codes.
         */
        ROLECODE, 
        /**
         * Description: Codes representing possible states of a RoleLink, as defined by the RoleLink class state machine.
         */
        ROLELINKSTATUS, 
        /**
         * RoleLinkType
         */
        ROLELINKTYPE, 
        /**
         * The status of an instance of the RIM Role class.
         */
        ROLESTATUS, 
        /**
         * RouteOfAdministration
         */
        ROUTEOFADMINISTRATION, 
        /**
         * Description: The South Carolina Department of Health and Environmental Control GIS Spatial Data Accuracy Tiers have been derived from the National Standard for Spatial Data Accuracy as a means to categorize the accuracy of spatial data assignment utilizing a variety of tools for capturing coordinates including digitizers, geocoding software and global positioning system devices.
         */
        SCDHECGISSPATIALACCURACYTIERS, 
        /**
         * College of American Pathologists, Skokie, IL, 60077-1034. (formerly designated as 99SDM).
         */
        SDM, 
        /**
         * Sequencing
         */
        SEQUENCING, 
        /**
         * SetOperator
         */
        SETOPERATOR, 
        /**
         * Systemized Nomenclature of Medicine, 2nd Edition 1984 Vols 1, 2, College of American Pathologists, Skokie, IL.
         */
        SNM, 
        /**
         * SNOMED International, 1993 Vols 1-4, College of American Pathologists, Skokie, IL, 60077-1034..
         */
        SNM3, 
        /**
         * College of American Pathologists, 5202 Old Orchard Road, Skokie, IL 60077-1034.
         */
        SNT, 
        /**
         * A code indicating the type of special arrangements provided for a patient encounter (e.g., wheelchair, stretcher, interpreter, attendant, seeing eye dog). For encounters in intention moods, this information can be used to identify special arrangements that will need to be made for the incoming patient.
         */
        SPECIALARRANGEMENT, 
        /**
         * SpecimenType
         */
        SPECIMENTYPE, 
        /**
         * The style code is used within the CDA/SPL narrative block to give the instance author some control over various aspects of style
         */
        STYLETYPE, 
        /**
         * Identifies what sort of change is permitted or has occurred between the therapy that was ordered and the therapy that was/will be provided.
         */
        SUBSTANCEADMINSUBSTITUTION, 
        /**
         * Identifies what sort of change is permitted or has occurred between the item that was ordered/requested and the one that was/will be provided.
         */
        SUBSTITUTIONCONDITION, 
        /**
         * These values are defined within the XHTML 4.0 Table Model
         */
        TABLECELLHORIZONTALALIGN, 
        /**
         * These values are defined within the XHTML 4.0 Table Model
         */
        TABLECELLSCOPE, 
        /**
         * These values are defined within the XHTML 4.0 Table Model
         */
        TABLECELLVERTICALALIGN, 
        /**
         * These values are defined within the XHTML 4.0 Table Model
         */
        TABLEFRAME, 
        /**
         * These values are defined within the XHTML 4.0 Table Model
         */
        TABLERULES, 
        /**
         * TargetAwareness
         */
        TARGETAWARENESS, 
        /**
         * TelecommunicationAddressUse
         */
        TELECOMMUNICATIONADDRESSUSE, 
        /**
         * Description: Concepts that define the telecommunication capabilities of a particular device. Used to identify the expected capabilities to be found at a particular telecommunication address.
         */
        TELECOMMUNICATIONCAPABILITIES, 
        /**
         * TimingEvent
         */
        TIMINGEVENT, 
        /**
         * Description:A code specifying the meaning and purpose of every TransmissionRelationship instance. Each of its values implies specific constraints to what kinds of Transmission objects can be related and in which way.
         */
        TRANSMISSIONRELATIONSHIPTYPECODE, 
        /**
         * INDIAN ENTITIES RECOGNIZED AND ELIGIBLE TO RECEIVE SERVICES FROM THE UNITED STATES BUREAU OF INDIAN AFFAIRS
         */
        TRIBALENTITYUS, 
        /**
         * Description:Trigger Event ID as published in the standard.
         */
        TRIGGEREVENTID, 
        /**
         * Uniform Clinical Data Systems. Ms. Michael McMullan, Office of Peer Review Health Care Finance Administration, The Meadows East Bldg., 6325 Security Blvd., Baltimore, MD 21207; (301) 966 6851.
         */
        UC, 
        /**
         * Unified Code for Units of Measure
         */
        UCUM, 
        /**
         * Universal Medical Device Nomenclature System.  ECRI, 5200 Butler Pike, Plymouth Meeting, PA  19462 USA.  Phone: 215-825-6000, Fax: 215-834-1275.
         */
        UMD, 
        /**
         * National Library of Medicine, 8600 Rockville Pike, Bethesda, MD 20894.
         */
        UML, 
        /**
         * UnitsOfMeasureCaseInsensitive
         */
        UNITSOFMEASURE, 
        /**
         * The Uniform Code Council.  8163 Old Yankee Road, Suite J, Dayton, OH  45458; (513) 435 3070
         */
        UPC, 
        /**
         * A Universal Resource Locator (URL) is a type of telecommunications address specified as Internet standard RFC 1738 [http://www.ietf.org/rfc/rfc1738.txt].  The URL specifies the protocol and the contact point defined by that protocol for the resource.
         */
        URLSCHEME, 
        /**
         * The manufacturer of a vaccine.
         */
        VACCINEMANUFACTURER, 
        /**
         * The kind of vaccine.
         */
        VACCINETYPE, 
        /**
         * Vocabulary domain qualifiers are concepts that are used in domain constraints to specify behavior of the new domain.
         */
        VOCABULARYDOMAINQUALIFIER, 
        /**
         * World Health organization record number code. A unique sequential number is assigned to each unique single component drug and to each multi-component drug.  Eight digits are allotted to each such code, six to identify the active agent, and 2 to identify the salt, of single content drugs.  Six digits are assigned to each unique combination of drugs in a dispensing unit.  The six digit code is identified by W1, the 8 digit code by W2.
         */
        W1W2, 
        /**
         * With ASTM extensions (see Implementation Guide), the WHO codes can be used to report serum (and other) levels, patient compliance with drug usage instructions, average daily doses and more (see Appendix X1 the Implementation Guide).
         */
        W4, 
        /**
         * WHO's ATC codes provide a hierarchical classification of drugs by therapeutic class.  They are linked to the record number codes listed above.
         */
        WC, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3CodeSystem fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ABCcodes".equals(codeString))
          return ABCCODES;
        if ("AcknowledgementCondition".equals(codeString))
          return ACKNOWLEDGEMENTCONDITION;
        if ("AcknowledgementDetailCode".equals(codeString))
          return ACKNOWLEDGEMENTDETAILCODE;
        if ("AcknowledgementDetailType".equals(codeString))
          return ACKNOWLEDGEMENTDETAILTYPE;
        if ("AcknowledgementType".equals(codeString))
          return ACKNOWLEDGEMENTTYPE;
        if ("ACR".equals(codeString))
          return ACR;
        if ("ActClass".equals(codeString))
          return ACTCLASS;
        if ("ActCode".equals(codeString))
          return ACTCODE;
        if ("ActExposureLevelCode".equals(codeString))
          return ACTEXPOSURELEVELCODE;
        if ("ActInvoiceElementModifier".equals(codeString))
          return ACTINVOICEELEMENTMODIFIER;
        if ("ActMood".equals(codeString))
          return ACTMOOD;
        if ("ActPriority".equals(codeString))
          return ACTPRIORITY;
        if ("ActReason".equals(codeString))
          return ACTREASON;
        if ("ActRelationshipCheckpoint".equals(codeString))
          return ACTRELATIONSHIPCHECKPOINT;
        if ("ActRelationshipJoin".equals(codeString))
          return ACTRELATIONSHIPJOIN;
        if ("ActRelationshipSplit".equals(codeString))
          return ACTRELATIONSHIPSPLIT;
        if ("ActRelationshipSubset".equals(codeString))
          return ACTRELATIONSHIPSUBSET;
        if ("ActRelationshipType".equals(codeString))
          return ACTRELATIONSHIPTYPE;
        if ("ActSite".equals(codeString))
          return ACTSITE;
        if ("ActStatus".equals(codeString))
          return ACTSTATUS;
        if ("ActUncertainty".equals(codeString))
          return ACTUNCERTAINTY;
        if ("ActUSPrivacyLaw".equals(codeString))
          return ACTUSPRIVACYLAW;
        if ("AddressPartType".equals(codeString))
          return ADDRESSPARTTYPE;
        if ("AddressUse".equals(codeString))
          return ADDRESSUSE;
        if ("AdministrativeGender".equals(codeString))
          return ADMINISTRATIVEGENDER;
        if ("AHFS".equals(codeString))
          return AHFS;
        if ("AmericanIndianAlaskaNativeLanguages".equals(codeString))
          return AMERICANINDIANALASKANATIVELANGUAGES;
        if ("ART".equals(codeString))
          return ART;
        if ("AS4".equals(codeString))
          return AS4;
        if ("AS4E".equals(codeString))
          return AS4E;
        if ("ATC".equals(codeString))
          return ATC;
        if ("BindingRealm".equals(codeString))
          return BINDINGREALM;
        if ("BodySite".equals(codeString))
          return BODYSITE;
        if ("C4".equals(codeString))
          return C4;
        if ("C5".equals(codeString))
          return C5;
        if ("Calendar".equals(codeString))
          return CALENDAR;
        if ("CalendarCycle".equals(codeString))
          return CALENDARCYCLE;
        if ("CalendarType".equals(codeString))
          return CALENDARTYPE;
        if ("CAMNCVS".equals(codeString))
          return CAMNCVS;
        if ("CAS".equals(codeString))
          return CAS;
        if ("CCI".equals(codeString))
          return CCI;
        if ("CD2".equals(codeString))
          return CD2;
        if ("CDCA".equals(codeString))
          return CDCA;
        if ("CDCM".equals(codeString))
          return CDCM;
        if ("CDS".equals(codeString))
          return CDS;
        if ("CE".equals(codeString))
          return CE;
        if ("Charset".equals(codeString))
          return CHARSET;
        if ("CLP".equals(codeString))
          return CLP;
        if ("CodeSystem".equals(codeString))
          return CODESYSTEM;
        if ("CodeSystemType".equals(codeString))
          return CODESYSTEMTYPE;
        if ("CodingRationale".equals(codeString))
          return CODINGRATIONALE;
        if ("CommunicationFunctionType".equals(codeString))
          return COMMUNICATIONFUNCTIONTYPE;
        if ("CompressionAlgorithm".equals(codeString))
          return COMPRESSIONALGORITHM;
        if ("ConceptCodeRelationship".equals(codeString))
          return CONCEPTCODERELATIONSHIP;
        if ("ConceptGenerality".equals(codeString))
          return CONCEPTGENERALITY;
        if ("ConceptProperty".equals(codeString))
          return CONCEPTPROPERTY;
        if ("ConceptStatus".equals(codeString))
          return CONCEPTSTATUS;
        if ("Confidentiality".equals(codeString))
          return CONFIDENTIALITY;
        if ("ContainerCap".equals(codeString))
          return CONTAINERCAP;
        if ("ContainerSeparator".equals(codeString))
          return CONTAINERSEPARATOR;
        if ("ContentProcessingMode".equals(codeString))
          return CONTENTPROCESSINGMODE;
        if ("ContextConductionStyle".equals(codeString))
          return CONTEXTCONDUCTIONSTYLE;
        if ("ContextControl".equals(codeString))
          return CONTEXTCONTROL;
        if ("CSAID".equals(codeString))
          return CSAID;
        if ("CST".equals(codeString))
          return CST;
        if ("Currency".equals(codeString))
          return CURRENCY;
        if ("CVX".equals(codeString))
          return CVX;
        if ("DataOperation".equals(codeString))
          return DATAOPERATION;
        if ("DataType".equals(codeString))
          return DATATYPE;
        if ("DCL".equals(codeString))
          return DCL;
        if ("DCM".equals(codeString))
          return DCM;
        if ("Dentition".equals(codeString))
          return DENTITION;
        if ("DeviceAlertLevel".equals(codeString))
          return DEVICEALERTLEVEL;
        if ("DocumentCompletion".equals(codeString))
          return DOCUMENTCOMPLETION;
        if ("DocumentStorage".equals(codeString))
          return DOCUMENTSTORAGE;
        if ("DQL".equals(codeString))
          return DQL;
        if ("E".equals(codeString))
          return E;
        if ("E5".equals(codeString))
          return E5;
        if ("E6".equals(codeString))
          return E6;
        if ("E7".equals(codeString))
          return E7;
        if ("EditStatus".equals(codeString))
          return EDITSTATUS;
        if ("EducationLevel".equals(codeString))
          return EDUCATIONLEVEL;
        if ("EmployeeJobClass".equals(codeString))
          return EMPLOYEEJOBCLASS;
        if ("EncounterAccident".equals(codeString))
          return ENCOUNTERACCIDENT;
        if ("EncounterAcuity".equals(codeString))
          return ENCOUNTERACUITY;
        if ("EncounterAdmissionSource".equals(codeString))
          return ENCOUNTERADMISSIONSOURCE;
        if ("EncounterReferralSource".equals(codeString))
          return ENCOUNTERREFERRALSOURCE;
        if ("EncounterSpecialCourtesy".equals(codeString))
          return ENCOUNTERSPECIALCOURTESY;
        if ("EntityClass".equals(codeString))
          return ENTITYCLASS;
        if ("EntityCode".equals(codeString))
          return ENTITYCODE;
        if ("EntityDeterminer".equals(codeString))
          return ENTITYDETERMINER;
        if ("EntityHandling".equals(codeString))
          return ENTITYHANDLING;
        if ("EntityNamePartQualifier".equals(codeString))
          return ENTITYNAMEPARTQUALIFIER;
        if ("EntityNamePartQualifierR2".equals(codeString))
          return ENTITYNAMEPARTQUALIFIERR2;
        if ("EntityNamePartType".equals(codeString))
          return ENTITYNAMEPARTTYPE;
        if ("EntityNamePartTypeR2".equals(codeString))
          return ENTITYNAMEPARTTYPER2;
        if ("EntityNameUse".equals(codeString))
          return ENTITYNAMEUSE;
        if ("EntityNameUseR2".equals(codeString))
          return ENTITYNAMEUSER2;
        if ("EntityRisk".equals(codeString))
          return ENTITYRISK;
        if ("EntityStatus".equals(codeString))
          return ENTITYSTATUS;
        if ("ENZC".equals(codeString))
          return ENZC;
        if ("EPSG_CA".equals(codeString))
          return EPSGCA;
        if ("EPSG_CRS".equals(codeString))
          return EPSGCRS;
        if ("EPSG-GeodeticParameterDataset".equals(codeString))
          return EPSGGEODETICPARAMETERDATASET;
        if ("EquipmentAlertLevel".equals(codeString))
          return EQUIPMENTALERTLEVEL;
        if ("Ethnicity".equals(codeString))
          return ETHNICITY;
        if ("ExposureMode".equals(codeString))
          return EXPOSUREMODE;
        if ("FDDC".equals(codeString))
          return FDDC;
        if ("FDDX".equals(codeString))
          return FDDX;
        if ("FDK".equals(codeString))
          return FDK;
        if ("GenderStatus".equals(codeString))
          return GENDERSTATUS;
        if ("GTSAbbreviation".equals(codeString))
          return GTSABBREVIATION;
        if ("HB".equals(codeString))
          return HB;
        if ("HC-AIC".equals(codeString))
          return HCAIC;
        if ("HC-AIGC".equals(codeString))
          return HCAIGC;
        if ("HC-AIGN".equals(codeString))
          return HCAIGN;
        if ("HC-DIN".equals(codeString))
          return HCDIN;
        if ("HC-NPN".equals(codeString))
          return HCNPN;
        if ("HealthcareProviderTaxonomyHIPAA".equals(codeString))
          return HEALTHCAREPROVIDERTAXONOMYHIPAA;
        if ("HealthcareServiceLocation".equals(codeString))
          return HEALTHCARESERVICELOCATION;
        if ("HHC".equals(codeString))
          return HHC;
        if ("HI".equals(codeString))
          return HI;
        if ("hl7ApprovalStatus".equals(codeString))
          return HL7APPROVALSTATUS;
        if ("hl7CMETAttribution".equals(codeString))
          return HL7CMETATTRIBUTION;
        if ("HL7CommitteeIDInRIM".equals(codeString))
          return HL7COMMITTEEIDINRIM;
        if ("HL7ConformanceInclusion".equals(codeString))
          return HL7CONFORMANCEINCLUSION;
        if ("HL7DefinedRoseProperty".equals(codeString))
          return HL7DEFINEDROSEPROPERTY;
        if ("hl7ITSType".equals(codeString))
          return HL7ITSTYPE;
        if ("HL7ITSVersionCode".equals(codeString))
          return HL7ITSVERSIONCODE;
        if ("hl7PublishingDomain".equals(codeString))
          return HL7PUBLISHINGDOMAIN;
        if ("hl7PublishingSection".equals(codeString))
          return HL7PUBLISHINGSECTION;
        if ("hl7PublishingSubSection".equals(codeString))
          return HL7PUBLISHINGSUBSECTION;
        if ("HL7StandardVersionCode".equals(codeString))
          return HL7STANDARDVERSIONCODE;
        if ("HL7UpdateMode".equals(codeString))
          return HL7UPDATEMODE;
        if ("hl7V3Conformance".equals(codeString))
          return HL7V3CONFORMANCE;
        if ("hl7VoteResolution".equals(codeString))
          return HL7VOTERESOLUTION;
        if ("HPC".equals(codeString))
          return HPC;
        if ("HtmlLinkType".equals(codeString))
          return HTMLLINKTYPE;
        if ("I10".equals(codeString))
          return I10;
        if ("I10P".equals(codeString))
          return I10P;
        if ("I9".equals(codeString))
          return I9;
        if ("I9C".equals(codeString))
          return I9C;
        if ("IBT".equals(codeString))
          return IBT;
        if ("IC2".equals(codeString))
          return IC2;
        if ("ICD-10-CA".equals(codeString))
          return ICD10CA;
        if ("ICDO".equals(codeString))
          return ICDO;
        if ("ICS".equals(codeString))
          return ICS;
        if ("ICSD".equals(codeString))
          return ICSD;
        if ("IdentifierReliability".equals(codeString))
          return IDENTIFIERRELIABILITY;
        if ("IdentifierScope".equals(codeString))
          return IDENTIFIERSCOPE;
        if ("IETF1766".equals(codeString))
          return IETF1766;
        if ("IETF3066".equals(codeString))
          return IETF3066;
        if ("IntegrityCheckAlgorithm".equals(codeString))
          return INTEGRITYCHECKALGORITHM;
        if ("iso21000-6-2004E-RDD".equals(codeString))
          return ISO2100062004ERDD;
        if ("ISO3166-1".equals(codeString))
          return ISO31661;
        if ("ISO3166-2".equals(codeString))
          return ISO31662;
        if ("ISO3166-3".equals(codeString))
          return ISO31663;
        if ("ISO4217".equals(codeString))
          return ISO4217;
        if ("IUPC".equals(codeString))
          return IUPC;
        if ("IUPP".equals(codeString))
          return IUPP;
        if ("JC8".equals(codeString))
          return JC8;
        if ("LanguageAbilityMode".equals(codeString))
          return LANGUAGEABILITYMODE;
        if ("LanguageAbilityProficiency".equals(codeString))
          return LANGUAGEABILITYPROFICIENCY;
        if ("LivingArrangement".equals(codeString))
          return LIVINGARRANGEMENT;
        if ("LN".equals(codeString))
          return LN;
        if ("LocalMarkupIgnore".equals(codeString))
          return LOCALMARKUPIGNORE;
        if ("LocalRemoteControlState".equals(codeString))
          return LOCALREMOTECONTROLSTATE;
        if ("ManagedParticipationStatus".equals(codeString))
          return MANAGEDPARTICIPATIONSTATUS;
        if ("MapRelationship".equals(codeString))
          return MAPRELATIONSHIP;
        if ("MaritalStatus".equals(codeString))
          return MARITALSTATUS;
        if ("MaterialType".equals(codeString))
          return MATERIALTYPE;
        if ("MDC".equals(codeString))
          return MDC;
        if ("MDDX".equals(codeString))
          return MDDX;
        if ("MDFAttributeType".equals(codeString))
          return MDFATTRIBUTETYPE;
        if ("MdfHmdMetSourceType".equals(codeString))
          return MDFHMDMETSOURCETYPE;
        if ("MdfHmdRowType".equals(codeString))
          return MDFHMDROWTYPE;
        if ("MdfRmimRowType".equals(codeString))
          return MDFRMIMROWTYPE;
        if ("MDFSubjectAreaPrefix".equals(codeString))
          return MDFSUBJECTAREAPREFIX;
        if ("MEDC".equals(codeString))
          return MEDC;
        if ("MEDCIN".equals(codeString))
          return MEDCIN;
        if ("MediaType".equals(codeString))
          return MEDIATYPE;
        if ("MEDR".equals(codeString))
          return MEDR;
        if ("MEDX".equals(codeString))
          return MEDX;
        if ("MessageCondition".equals(codeString))
          return MESSAGECONDITION;
        if ("MessageWaitingPriority".equals(codeString))
          return MESSAGEWAITINGPRIORITY;
        if ("MGPI".equals(codeString))
          return MGPI;
        if ("MIME".equals(codeString))
          return MIME;
        if ("ModifyIndicator".equals(codeString))
          return MODIFYINDICATOR;
        if ("MSH".equals(codeString))
          return MSH;
        if ("MULTUM".equals(codeString))
          return MULTUM;
        if ("MVX".equals(codeString))
          return MVX;
        if ("NAACCR".equals(codeString))
          return NAACCR;
        if ("NAICS".equals(codeString))
          return NAICS;
        if ("NDA".equals(codeString))
          return NDA;
        if ("NDC".equals(codeString))
          return NDC;
        if ("NIC".equals(codeString))
          return NIC;
        if ("NMMDS".equals(codeString))
          return NMMDS;
        if ("NOC".equals(codeString))
          return NOC;
        if ("NUBC-UB92".equals(codeString))
          return NUBCUB92;
        if ("NUCCProviderCodes".equals(codeString))
          return NUCCPROVIDERCODES;
        if ("NullFlavor".equals(codeString))
          return NULLFLAVOR;
        if ("ObservationInterpretation".equals(codeString))
          return OBSERVATIONINTERPRETATION;
        if ("ObservationMethod".equals(codeString))
          return OBSERVATIONMETHOD;
        if ("ObservationValue".equals(codeString))
          return OBSERVATIONVALUE;
        if ("OHA".equals(codeString))
          return OHA;
        if ("OPINIONS".equals(codeString))
          return OPINIONS;
        if ("OrderableDrugForm".equals(codeString))
          return ORDERABLEDRUGFORM;
        if ("OrganizationNameType".equals(codeString))
          return ORGANIZATIONNAMETYPE;
        if ("ParameterizedDataType".equals(codeString))
          return PARAMETERIZEDDATATYPE;
        if ("ParticipationFunction".equals(codeString))
          return PARTICIPATIONFUNCTION;
        if ("ParticipationMode".equals(codeString))
          return PARTICIPATIONMODE;
        if ("ParticipationSignature".equals(codeString))
          return PARTICIPATIONSIGNATURE;
        if ("ParticipationType".equals(codeString))
          return PARTICIPATIONTYPE;
        if ("PatientImportance".equals(codeString))
          return PATIENTIMPORTANCE;
        if ("PaymentTerms".equals(codeString))
          return PAYMENTTERMS;
        if ("PeriodicIntervalOfTimeAbbreviation".equals(codeString))
          return PERIODICINTERVALOFTIMEABBREVIATION;
        if ("PersonDisabilityType".equals(codeString))
          return PERSONDISABILITYTYPE;
        if ("PNDS".equals(codeString))
          return PNDS;
        if ("POS".equals(codeString))
          return POS;
        if ("PostalAddressUse".equals(codeString))
          return POSTALADDRESSUSE;
        if ("ProbabilityDistributionType".equals(codeString))
          return PROBABILITYDISTRIBUTIONTYPE;
        if ("ProcedureMethod".equals(codeString))
          return PROCEDUREMETHOD;
        if ("ProcessingID".equals(codeString))
          return PROCESSINGID;
        if ("ProcessingMode".equals(codeString))
          return PROCESSINGMODE;
        if ("QueryParameterValue".equals(codeString))
          return QUERYPARAMETERVALUE;
        if ("QueryPriority".equals(codeString))
          return QUERYPRIORITY;
        if ("QueryQuantityUnit".equals(codeString))
          return QUERYQUANTITYUNIT;
        if ("QueryRequestLimit".equals(codeString))
          return QUERYREQUESTLIMIT;
        if ("QueryResponse".equals(codeString))
          return QUERYRESPONSE;
        if ("QueryStatusCode".equals(codeString))
          return QUERYSTATUSCODE;
        if ("Race".equals(codeString))
          return RACE;
        if ("RC".equals(codeString))
          return RC;
        if ("RCFB".equals(codeString))
          return RCFB;
        if ("RCV2".equals(codeString))
          return RCV2;
        if ("RelationalOperator".equals(codeString))
          return RELATIONALOPERATOR;
        if ("RelationshipConjunction".equals(codeString))
          return RELATIONSHIPCONJUNCTION;
        if ("ReligiousAffiliation".equals(codeString))
          return RELIGIOUSAFFILIATION;
        if ("ResponseLevel".equals(codeString))
          return RESPONSELEVEL;
        if ("ResponseModality".equals(codeString))
          return RESPONSEMODALITY;
        if ("ResponseMode".equals(codeString))
          return RESPONSEMODE;
        if ("RoleClass".equals(codeString))
          return ROLECLASS;
        if ("RoleCode".equals(codeString))
          return ROLECODE;
        if ("RoleLinkStatus".equals(codeString))
          return ROLELINKSTATUS;
        if ("RoleLinkType".equals(codeString))
          return ROLELINKTYPE;
        if ("RoleStatus".equals(codeString))
          return ROLESTATUS;
        if ("RouteOfAdministration".equals(codeString))
          return ROUTEOFADMINISTRATION;
        if ("SCDHEC-GISSpatialAccuracyTiers".equals(codeString))
          return SCDHECGISSPATIALACCURACYTIERS;
        if ("SDM".equals(codeString))
          return SDM;
        if ("Sequencing".equals(codeString))
          return SEQUENCING;
        if ("SetOperator".equals(codeString))
          return SETOPERATOR;
        if ("SNM".equals(codeString))
          return SNM;
        if ("SNM3".equals(codeString))
          return SNM3;
        if ("SNT".equals(codeString))
          return SNT;
        if ("SpecialArrangement".equals(codeString))
          return SPECIALARRANGEMENT;
        if ("SpecimenType".equals(codeString))
          return SPECIMENTYPE;
        if ("StyleType".equals(codeString))
          return STYLETYPE;
        if ("SubstanceAdminSubstitution".equals(codeString))
          return SUBSTANCEADMINSUBSTITUTION;
        if ("SubstitutionCondition".equals(codeString))
          return SUBSTITUTIONCONDITION;
        if ("TableCellHorizontalAlign".equals(codeString))
          return TABLECELLHORIZONTALALIGN;
        if ("TableCellScope".equals(codeString))
          return TABLECELLSCOPE;
        if ("TableCellVerticalAlign".equals(codeString))
          return TABLECELLVERTICALALIGN;
        if ("TableFrame".equals(codeString))
          return TABLEFRAME;
        if ("TableRules".equals(codeString))
          return TABLERULES;
        if ("TargetAwareness".equals(codeString))
          return TARGETAWARENESS;
        if ("TelecommunicationAddressUse".equals(codeString))
          return TELECOMMUNICATIONADDRESSUSE;
        if ("TelecommunicationCapabilities".equals(codeString))
          return TELECOMMUNICATIONCAPABILITIES;
        if ("TimingEvent".equals(codeString))
          return TIMINGEVENT;
        if ("TransmissionRelationshipTypeCode".equals(codeString))
          return TRANSMISSIONRELATIONSHIPTYPECODE;
        if ("TribalEntityUS".equals(codeString))
          return TRIBALENTITYUS;
        if ("TriggerEventID".equals(codeString))
          return TRIGGEREVENTID;
        if ("UC".equals(codeString))
          return UC;
        if ("UCUM".equals(codeString))
          return UCUM;
        if ("UMD".equals(codeString))
          return UMD;
        if ("UML".equals(codeString))
          return UML;
        if ("UnitsOfMeasure".equals(codeString))
          return UNITSOFMEASURE;
        if ("UPC".equals(codeString))
          return UPC;
        if ("URLScheme".equals(codeString))
          return URLSCHEME;
        if ("VaccineManufacturer".equals(codeString))
          return VACCINEMANUFACTURER;
        if ("VaccineType".equals(codeString))
          return VACCINETYPE;
        if ("VocabularyDomainQualifier".equals(codeString))
          return VOCABULARYDOMAINQUALIFIER;
        if ("W1-W2".equals(codeString))
          return W1W2;
        if ("W4".equals(codeString))
          return W4;
        if ("WC".equals(codeString))
          return WC;
        throw new Exception("Unknown V3CodeSystem code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ABCCODES: return "ABCcodes";
            case ACKNOWLEDGEMENTCONDITION: return "AcknowledgementCondition";
            case ACKNOWLEDGEMENTDETAILCODE: return "AcknowledgementDetailCode";
            case ACKNOWLEDGEMENTDETAILTYPE: return "AcknowledgementDetailType";
            case ACKNOWLEDGEMENTTYPE: return "AcknowledgementType";
            case ACR: return "ACR";
            case ACTCLASS: return "ActClass";
            case ACTCODE: return "ActCode";
            case ACTEXPOSURELEVELCODE: return "ActExposureLevelCode";
            case ACTINVOICEELEMENTMODIFIER: return "ActInvoiceElementModifier";
            case ACTMOOD: return "ActMood";
            case ACTPRIORITY: return "ActPriority";
            case ACTREASON: return "ActReason";
            case ACTRELATIONSHIPCHECKPOINT: return "ActRelationshipCheckpoint";
            case ACTRELATIONSHIPJOIN: return "ActRelationshipJoin";
            case ACTRELATIONSHIPSPLIT: return "ActRelationshipSplit";
            case ACTRELATIONSHIPSUBSET: return "ActRelationshipSubset";
            case ACTRELATIONSHIPTYPE: return "ActRelationshipType";
            case ACTSITE: return "ActSite";
            case ACTSTATUS: return "ActStatus";
            case ACTUNCERTAINTY: return "ActUncertainty";
            case ACTUSPRIVACYLAW: return "ActUSPrivacyLaw";
            case ADDRESSPARTTYPE: return "AddressPartType";
            case ADDRESSUSE: return "AddressUse";
            case ADMINISTRATIVEGENDER: return "AdministrativeGender";
            case AHFS: return "AHFS";
            case AMERICANINDIANALASKANATIVELANGUAGES: return "AmericanIndianAlaskaNativeLanguages";
            case ART: return "ART";
            case AS4: return "AS4";
            case AS4E: return "AS4E";
            case ATC: return "ATC";
            case BINDINGREALM: return "BindingRealm";
            case BODYSITE: return "BodySite";
            case C4: return "C4";
            case C5: return "C5";
            case CALENDAR: return "Calendar";
            case CALENDARCYCLE: return "CalendarCycle";
            case CALENDARTYPE: return "CalendarType";
            case CAMNCVS: return "CAMNCVS";
            case CAS: return "CAS";
            case CCI: return "CCI";
            case CD2: return "CD2";
            case CDCA: return "CDCA";
            case CDCM: return "CDCM";
            case CDS: return "CDS";
            case CE: return "CE";
            case CHARSET: return "Charset";
            case CLP: return "CLP";
            case CODESYSTEM: return "CodeSystem";
            case CODESYSTEMTYPE: return "CodeSystemType";
            case CODINGRATIONALE: return "CodingRationale";
            case COMMUNICATIONFUNCTIONTYPE: return "CommunicationFunctionType";
            case COMPRESSIONALGORITHM: return "CompressionAlgorithm";
            case CONCEPTCODERELATIONSHIP: return "ConceptCodeRelationship";
            case CONCEPTGENERALITY: return "ConceptGenerality";
            case CONCEPTPROPERTY: return "ConceptProperty";
            case CONCEPTSTATUS: return "ConceptStatus";
            case CONFIDENTIALITY: return "Confidentiality";
            case CONTAINERCAP: return "ContainerCap";
            case CONTAINERSEPARATOR: return "ContainerSeparator";
            case CONTENTPROCESSINGMODE: return "ContentProcessingMode";
            case CONTEXTCONDUCTIONSTYLE: return "ContextConductionStyle";
            case CONTEXTCONTROL: return "ContextControl";
            case CSAID: return "CSAID";
            case CST: return "CST";
            case CURRENCY: return "Currency";
            case CVX: return "CVX";
            case DATAOPERATION: return "DataOperation";
            case DATATYPE: return "DataType";
            case DCL: return "DCL";
            case DCM: return "DCM";
            case DENTITION: return "Dentition";
            case DEVICEALERTLEVEL: return "DeviceAlertLevel";
            case DOCUMENTCOMPLETION: return "DocumentCompletion";
            case DOCUMENTSTORAGE: return "DocumentStorage";
            case DQL: return "DQL";
            case E: return "E";
            case E5: return "E5";
            case E6: return "E6";
            case E7: return "E7";
            case EDITSTATUS: return "EditStatus";
            case EDUCATIONLEVEL: return "EducationLevel";
            case EMPLOYEEJOBCLASS: return "EmployeeJobClass";
            case ENCOUNTERACCIDENT: return "EncounterAccident";
            case ENCOUNTERACUITY: return "EncounterAcuity";
            case ENCOUNTERADMISSIONSOURCE: return "EncounterAdmissionSource";
            case ENCOUNTERREFERRALSOURCE: return "EncounterReferralSource";
            case ENCOUNTERSPECIALCOURTESY: return "EncounterSpecialCourtesy";
            case ENTITYCLASS: return "EntityClass";
            case ENTITYCODE: return "EntityCode";
            case ENTITYDETERMINER: return "EntityDeterminer";
            case ENTITYHANDLING: return "EntityHandling";
            case ENTITYNAMEPARTQUALIFIER: return "EntityNamePartQualifier";
            case ENTITYNAMEPARTQUALIFIERR2: return "EntityNamePartQualifierR2";
            case ENTITYNAMEPARTTYPE: return "EntityNamePartType";
            case ENTITYNAMEPARTTYPER2: return "EntityNamePartTypeR2";
            case ENTITYNAMEUSE: return "EntityNameUse";
            case ENTITYNAMEUSER2: return "EntityNameUseR2";
            case ENTITYRISK: return "EntityRisk";
            case ENTITYSTATUS: return "EntityStatus";
            case ENZC: return "ENZC";
            case EPSGCA: return "EPSG_CA";
            case EPSGCRS: return "EPSG_CRS";
            case EPSGGEODETICPARAMETERDATASET: return "EPSG-GeodeticParameterDataset";
            case EQUIPMENTALERTLEVEL: return "EquipmentAlertLevel";
            case ETHNICITY: return "Ethnicity";
            case EXPOSUREMODE: return "ExposureMode";
            case FDDC: return "FDDC";
            case FDDX: return "FDDX";
            case FDK: return "FDK";
            case GENDERSTATUS: return "GenderStatus";
            case GTSABBREVIATION: return "GTSAbbreviation";
            case HB: return "HB";
            case HCAIC: return "HC-AIC";
            case HCAIGC: return "HC-AIGC";
            case HCAIGN: return "HC-AIGN";
            case HCDIN: return "HC-DIN";
            case HCNPN: return "HC-NPN";
            case HEALTHCAREPROVIDERTAXONOMYHIPAA: return "HealthcareProviderTaxonomyHIPAA";
            case HEALTHCARESERVICELOCATION: return "HealthcareServiceLocation";
            case HHC: return "HHC";
            case HI: return "HI";
            case HL7APPROVALSTATUS: return "hl7ApprovalStatus";
            case HL7CMETATTRIBUTION: return "hl7CMETAttribution";
            case HL7COMMITTEEIDINRIM: return "HL7CommitteeIDInRIM";
            case HL7CONFORMANCEINCLUSION: return "HL7ConformanceInclusion";
            case HL7DEFINEDROSEPROPERTY: return "HL7DefinedRoseProperty";
            case HL7ITSTYPE: return "hl7ITSType";
            case HL7ITSVERSIONCODE: return "HL7ITSVersionCode";
            case HL7PUBLISHINGDOMAIN: return "hl7PublishingDomain";
            case HL7PUBLISHINGSECTION: return "hl7PublishingSection";
            case HL7PUBLISHINGSUBSECTION: return "hl7PublishingSubSection";
            case HL7STANDARDVERSIONCODE: return "HL7StandardVersionCode";
            case HL7UPDATEMODE: return "HL7UpdateMode";
            case HL7V3CONFORMANCE: return "hl7V3Conformance";
            case HL7VOTERESOLUTION: return "hl7VoteResolution";
            case HPC: return "HPC";
            case HTMLLINKTYPE: return "HtmlLinkType";
            case I10: return "I10";
            case I10P: return "I10P";
            case I9: return "I9";
            case I9C: return "I9C";
            case IBT: return "IBT";
            case IC2: return "IC2";
            case ICD10CA: return "ICD-10-CA";
            case ICDO: return "ICDO";
            case ICS: return "ICS";
            case ICSD: return "ICSD";
            case IDENTIFIERRELIABILITY: return "IdentifierReliability";
            case IDENTIFIERSCOPE: return "IdentifierScope";
            case IETF1766: return "IETF1766";
            case IETF3066: return "IETF3066";
            case INTEGRITYCHECKALGORITHM: return "IntegrityCheckAlgorithm";
            case ISO2100062004ERDD: return "iso21000-6-2004E-RDD";
            case ISO31661: return "ISO3166-1";
            case ISO31662: return "ISO3166-2";
            case ISO31663: return "ISO3166-3";
            case ISO4217: return "ISO4217";
            case IUPC: return "IUPC";
            case IUPP: return "IUPP";
            case JC8: return "JC8";
            case LANGUAGEABILITYMODE: return "LanguageAbilityMode";
            case LANGUAGEABILITYPROFICIENCY: return "LanguageAbilityProficiency";
            case LIVINGARRANGEMENT: return "LivingArrangement";
            case LN: return "LN";
            case LOCALMARKUPIGNORE: return "LocalMarkupIgnore";
            case LOCALREMOTECONTROLSTATE: return "LocalRemoteControlState";
            case MANAGEDPARTICIPATIONSTATUS: return "ManagedParticipationStatus";
            case MAPRELATIONSHIP: return "MapRelationship";
            case MARITALSTATUS: return "MaritalStatus";
            case MATERIALTYPE: return "MaterialType";
            case MDC: return "MDC";
            case MDDX: return "MDDX";
            case MDFATTRIBUTETYPE: return "MDFAttributeType";
            case MDFHMDMETSOURCETYPE: return "MdfHmdMetSourceType";
            case MDFHMDROWTYPE: return "MdfHmdRowType";
            case MDFRMIMROWTYPE: return "MdfRmimRowType";
            case MDFSUBJECTAREAPREFIX: return "MDFSubjectAreaPrefix";
            case MEDC: return "MEDC";
            case MEDCIN: return "MEDCIN";
            case MEDIATYPE: return "MediaType";
            case MEDR: return "MEDR";
            case MEDX: return "MEDX";
            case MESSAGECONDITION: return "MessageCondition";
            case MESSAGEWAITINGPRIORITY: return "MessageWaitingPriority";
            case MGPI: return "MGPI";
            case MIME: return "MIME";
            case MODIFYINDICATOR: return "ModifyIndicator";
            case MSH: return "MSH";
            case MULTUM: return "MULTUM";
            case MVX: return "MVX";
            case NAACCR: return "NAACCR";
            case NAICS: return "NAICS";
            case NDA: return "NDA";
            case NDC: return "NDC";
            case NIC: return "NIC";
            case NMMDS: return "NMMDS";
            case NOC: return "NOC";
            case NUBCUB92: return "NUBC-UB92";
            case NUCCPROVIDERCODES: return "NUCCProviderCodes";
            case NULLFLAVOR: return "NullFlavor";
            case OBSERVATIONINTERPRETATION: return "ObservationInterpretation";
            case OBSERVATIONMETHOD: return "ObservationMethod";
            case OBSERVATIONVALUE: return "ObservationValue";
            case OHA: return "OHA";
            case OPINIONS: return "OPINIONS";
            case ORDERABLEDRUGFORM: return "OrderableDrugForm";
            case ORGANIZATIONNAMETYPE: return "OrganizationNameType";
            case PARAMETERIZEDDATATYPE: return "ParameterizedDataType";
            case PARTICIPATIONFUNCTION: return "ParticipationFunction";
            case PARTICIPATIONMODE: return "ParticipationMode";
            case PARTICIPATIONSIGNATURE: return "ParticipationSignature";
            case PARTICIPATIONTYPE: return "ParticipationType";
            case PATIENTIMPORTANCE: return "PatientImportance";
            case PAYMENTTERMS: return "PaymentTerms";
            case PERIODICINTERVALOFTIMEABBREVIATION: return "PeriodicIntervalOfTimeAbbreviation";
            case PERSONDISABILITYTYPE: return "PersonDisabilityType";
            case PNDS: return "PNDS";
            case POS: return "POS";
            case POSTALADDRESSUSE: return "PostalAddressUse";
            case PROBABILITYDISTRIBUTIONTYPE: return "ProbabilityDistributionType";
            case PROCEDUREMETHOD: return "ProcedureMethod";
            case PROCESSINGID: return "ProcessingID";
            case PROCESSINGMODE: return "ProcessingMode";
            case QUERYPARAMETERVALUE: return "QueryParameterValue";
            case QUERYPRIORITY: return "QueryPriority";
            case QUERYQUANTITYUNIT: return "QueryQuantityUnit";
            case QUERYREQUESTLIMIT: return "QueryRequestLimit";
            case QUERYRESPONSE: return "QueryResponse";
            case QUERYSTATUSCODE: return "QueryStatusCode";
            case RACE: return "Race";
            case RC: return "RC";
            case RCFB: return "RCFB";
            case RCV2: return "RCV2";
            case RELATIONALOPERATOR: return "RelationalOperator";
            case RELATIONSHIPCONJUNCTION: return "RelationshipConjunction";
            case RELIGIOUSAFFILIATION: return "ReligiousAffiliation";
            case RESPONSELEVEL: return "ResponseLevel";
            case RESPONSEMODALITY: return "ResponseModality";
            case RESPONSEMODE: return "ResponseMode";
            case ROLECLASS: return "RoleClass";
            case ROLECODE: return "RoleCode";
            case ROLELINKSTATUS: return "RoleLinkStatus";
            case ROLELINKTYPE: return "RoleLinkType";
            case ROLESTATUS: return "RoleStatus";
            case ROUTEOFADMINISTRATION: return "RouteOfAdministration";
            case SCDHECGISSPATIALACCURACYTIERS: return "SCDHEC-GISSpatialAccuracyTiers";
            case SDM: return "SDM";
            case SEQUENCING: return "Sequencing";
            case SETOPERATOR: return "SetOperator";
            case SNM: return "SNM";
            case SNM3: return "SNM3";
            case SNT: return "SNT";
            case SPECIALARRANGEMENT: return "SpecialArrangement";
            case SPECIMENTYPE: return "SpecimenType";
            case STYLETYPE: return "StyleType";
            case SUBSTANCEADMINSUBSTITUTION: return "SubstanceAdminSubstitution";
            case SUBSTITUTIONCONDITION: return "SubstitutionCondition";
            case TABLECELLHORIZONTALALIGN: return "TableCellHorizontalAlign";
            case TABLECELLSCOPE: return "TableCellScope";
            case TABLECELLVERTICALALIGN: return "TableCellVerticalAlign";
            case TABLEFRAME: return "TableFrame";
            case TABLERULES: return "TableRules";
            case TARGETAWARENESS: return "TargetAwareness";
            case TELECOMMUNICATIONADDRESSUSE: return "TelecommunicationAddressUse";
            case TELECOMMUNICATIONCAPABILITIES: return "TelecommunicationCapabilities";
            case TIMINGEVENT: return "TimingEvent";
            case TRANSMISSIONRELATIONSHIPTYPECODE: return "TransmissionRelationshipTypeCode";
            case TRIBALENTITYUS: return "TribalEntityUS";
            case TRIGGEREVENTID: return "TriggerEventID";
            case UC: return "UC";
            case UCUM: return "UCUM";
            case UMD: return "UMD";
            case UML: return "UML";
            case UNITSOFMEASURE: return "UnitsOfMeasure";
            case UPC: return "UPC";
            case URLSCHEME: return "URLScheme";
            case VACCINEMANUFACTURER: return "VaccineManufacturer";
            case VACCINETYPE: return "VaccineType";
            case VOCABULARYDOMAINQUALIFIER: return "VocabularyDomainQualifier";
            case W1W2: return "W1-W2";
            case W4: return "W4";
            case WC: return "WC";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/CodeSystem";
        }
        public String getDefinition() {
          switch (this) {
            case ABCCODES: return "Five character alphabetic codes fit into current claims processing software or onto standard paper claim forms. ABC Codes give business parity to licensed CAM and nurse providers who file claims to insurance companies. .";
            case ACKNOWLEDGEMENTCONDITION: return "AcknowledgementCondition";
            case ACKNOWLEDGEMENTDETAILCODE: return "A site specific problem code";
            case ACKNOWLEDGEMENTDETAILTYPE: return "Acknowledgement Detail Type";
            case ACKNOWLEDGEMENTTYPE: return "Acknowledgement code as described in HL7 message processing rules.";
            case ACR: return "Index for Radiological Diagnosis Revised, 3rd Edition 1986, American College of Radiology, Reston, VA.";
            case ACTCLASS: return "ActClass";
            case ACTCODE: return "The table that provides the detailed or rich codes for the Act classes.";
            case ACTEXPOSURELEVELCODE: return "A qualitative measure of the degree of exposure to the causative agent.  This includes concepts such as 'low', 'medium' and 'high'.  This quantifies how the quantity that was available to be administered to the target differs from typical or background levels of the substance.";
            case ACTINVOICEELEMENTMODIFIER: return "Processing consideration and clarification codes.";
            case ACTMOOD: return "ActMood";
            case ACTPRIORITY: return "ActPriority";
            case ACTREASON: return "ActReason";
            case ACTRELATIONSHIPCHECKPOINT: return "ActRelationshipCheckpoint";
            case ACTRELATIONSHIPJOIN: return "ActRelationshipJoin";
            case ACTRELATIONSHIPSPLIT: return "ActRelationshipSplit";
            case ACTRELATIONSHIPSUBSET: return "Used to indicate that the target of the relationship will be a filtered subset of the total related set of targets.\r\n\n                        Used when there is a need to limit the number of components to the first, the last, the next, the total, the average or some other filtered or calculated subset.";
            case ACTRELATIONSHIPTYPE: return "The source is an excerpt from the target.";
            case ACTSITE: return "An anatomical location on an organism which can be the focus of an act.";
            case ACTSTATUS: return "Contains the names (codes) for each of the states in the state-machine of the RIM Act class.";
            case ACTUNCERTAINTY: return "ActUncertainty";
            case ACTUSPRIVACYLAW: return "Description: A jurisdictional mandate in the US relating to privacy.";
            case ADDRESSPARTTYPE: return "Discussion: The hierarchical nature of these concepts shows composition.  E.g. 'Street Name' is part of 'Street Address Line'";
            case ADDRESSUSE: return "Description: Uses of Addresses.   Lloyd to supply more complete description.";
            case ADMINISTRATIVEGENDER: return "The gender of a person used for adminstrative purposes (as opposed to clinical gender)";
            case AHFS: return "Description: The AHFS Pharmacologic-Therapeutic Classification has been in use in hospitals in the United States since its inception in 1959. An integral part of the American Hospital Formulary Service, the AHFS classification allows the grouping of drugs with similar pharmacologic, therapeutic, and/or chemical characteristics. Today, the AHFS classification is used by many people outside of hospitals.";
            case AMERICANINDIANALASKANATIVELANGUAGES: return "American Indian and Alaska Native languages currently being used in the United States.";
            case ART: return "WHO Collaborating Centre for International Drug Monitoring, Box 26, S-751 03, Uppsala, Sweden.";
            case AS4: return "American Society for Testing & Materials and CPT4 (see Appendix X1 of Specification E1238 and Appendix X2 of Specification E1467).";
            case AS4E: return "ASTM's diagnostic codes and test result coding/grading systems for clinical neurophysiology. See ASTM Specification E1467, Appendix 2.";
            case ATC: return "Reference cultures (microorganisms, tissue cultures, etc.), related biological materials and associated data. American Type Culture Collection, 12301 Parklawn Dr, Rockville MD, 20852. (301) 881-2600. http://www.atcc.org";
            case BINDINGREALM: return "Description: Coded concepts representing the Binding Realms used for Context Binding of terminology in HL7 models.  Where concepts align with nations, the country codes from ISO 3166-1 2-character alpha are used for the codes.  For those realms where they do not,. codes are created for the concepts.  These codes are generally carried in InfrastructureRoot.realmcode, and are used in Context Binding statements.";
            case BODYSITE: return "Description: HL7 version 2.x Body site used in chapter(s) 4; HL7 table 0163";
            case C4: return "American Medical Association, P.O. Box 10946, Chicago IL  60610.";
            case C5: return "American Medical Association, P.O. Box 10946, Chicago IL  60610.";
            case CALENDAR: return "Calendar";
            case CALENDARCYCLE: return "CalendarCycle";
            case CALENDARTYPE: return "CalendarType";
            case CAMNCVS: return "CAM & Nursing Coding Vocabulary Set";
            case CAS: return "These include unique codes for each unique chemical, including all generic drugs.  The codes do not distinguish among different dosing forms.  When multiple equivalent CAS numbers exist, use the first one listed in USAN. USAN 1990 and the USP dictionary of drug names, William M. Heller, Ph.D., Executive Editor, United States Pharmacopeial Convention, Inc., 12601 Twinbrook Parkway, Rockville, MD 20852.";
            case CCI: return "CCI - Canadian Classification of Health Interventions, developed to accompany ICD-10-CA, maintained by CIHI (Canadian Institute for Health Information).\r\n\n                        For example: sections  3.AA-3.BZ  Diagnostic Imaging Interventions on the Nervous System 3.AN.^ ^.^ ^    Diagnostic Imaging Interventions on the Brain 3.AN.40. ^ ^   Magnetic Resonance Imaging, Brain Incudes:        That for meninges, ventricles, cerebellum, brain stem, cisterna [of brain], posterior fossa MRI, brain 3.AN.40.VA   without contrast 3.AN.40.VC   following intravenous injection of contrast 3.AN.40.VZ   following percutaneous injection of contrast\r\n\n                        CIHI Toronto Attn: Director of Standards 90 Eglinton Avenue, Suite 300 Toronto, Ontario Canada M4P 2Y3\r\n\n                        Phone: (416) 481.2002 Fax: (416) 481-2950\r\n\n                        www.cihi.ca";
            case CD2: return "American Dental Association's Current Dental Terminology (CDT-2) code.  American Dental Association, 211 E. Chicago Avenue,. Chicago, Illinois 60611.";
            case CDCA: return "Public Health Practice Program Office, Centers for Disease Control and Prevention, 4770 Buford Highway, Atlanta, GA, 30421.  Also available via FTP: ftp.cdc.gov/pub/laboratory _info/CLIA and Gopher: gopher.cdc.gov:70/11/laboratory_info/CLIA";
            case CDCM: return "Public Health Practice Program Office, Centers for Disease Control and Prevention, 4770 Buford Highway, Atlanta, GA, 30421.  Also available via FTP: ftp.cdc.gov/pub/laboratory _info/CLIA and Gopher: gopher.cdc.gov:70/11/laboratory_info/CLIA";
            case CDS: return "CDC Surveillance Codes. For data unique to specific public health surveillance requirements. Epidemiology Program Office, Centers for Disease Control and Prevention, 1600 Clifton Rd, Atlanta, GA, 30333. (404) 639-3661.";
            case CE: return "CEN PT007. A quite comprehensive set of ECG diagnostic codes (abbreviations) and descriptions published as a pre-standard by CEN TC251. Available from CEN TC251 secretariat, c/o Georges DeMoor, State University Hospital Gent, De Pintelaan 185-5K3, 9000 Ge";
            case CHARSET: return "Internet Assigned Numbers Authority (IANA) Charset Types";
            case CLP: return "Simon Leeming, Beth Israel Hospital, Boston MA.  Codes for radiology reports.";
            case CODESYSTEM: return "Code systems used in HL7 standards.";
            case CODESYSTEMTYPE: return "HL7 Code System Type";
            case CODINGRATIONALE: return "Identifies how to interpret the instance of the code, codeSystem value in a set of translations.  Since HL7 (or a government body) may mandate that codes from certain code systems be sent in conformant messages, other synonyms that are sent in the translation set need to be distinguished among the originally captured source, the HL7 specified code, or some future role.  When this code is NULL, it indicates that the translation is an undefined type.  When valued, this property must contain one of the following values:\r\n\n                        SRC - Source (or original) code HL7 - HL7 Specified or Mandated SH - both HL7 mandated and the original code (precoordination)\r\n\n                        There may be additional values added to this value set as we work through the use of codes in messages and determine other Use Cases requiring special interpretation of the translations.";
            case COMMUNICATIONFUNCTIONTYPE: return "Describes the type of communication function that the associated entity plays in the associated transmission.";
            case COMPRESSIONALGORITHM: return "CompressionAlgorithm";
            case CONCEPTCODERELATIONSHIP: return "Possible Concept Code Relationships";
            case CONCEPTGENERALITY: return "Indicates whether the concept that is the target should be interpreted as itself, or whether it should be expanded to include its child concepts, or both when it is included in the source domain/valueset.";
            case CONCEPTPROPERTY: return "HL7 Value Set and Coded Concept Property Codes";
            case CONCEPTSTATUS: return "HL7 Coded Concept Status";
            case CONFIDENTIALITY: return "Confidentiality";
            case CONTAINERCAP: return "The type of cap associated with a container";
            case CONTAINERSEPARATOR: return "A material in a blood collection container that facilites the separation of of blood cells from serum or plasma";
            case CONTENTPROCESSINGMODE: return "Description:Identifies the order in which content should be processed.";
            case CONTEXTCONDUCTIONSTYLE: return "The styles of context conduction usable by relationships within a static model derived from tyhe HL7 Reference Information Model.";
            case CONTEXTCONTROL: return "This table contains the control codes that are used to manage the propagation and scope of a particular ActRelationship or Participation  within a set of Acts.";
            case CSAID: return "CAN/CSA-Z795-96 (R2001) - This Standard provides a framework for consistent recording and classification of information on work-related injuries and diseases in Canada (injury coding).\r\n\n                        It is constituted of Nature of injury, body part, side of body. For example: Cut or laceration of the Upper Arm,  Left Injury = 03400; body part = 31100; side of body = L\r\n\n                        Code set is maintained by the Canadian Standards Association (CSA).\r\n\n                        The Canadian Standards Association 5060 Spectrum Way Mississauga, Ontario Canada L4W 5N6\r\n\n                        Phone: (416) 747-4000 1-800-463-6727 Fax: (416) 747-2473";
            case CST: return "International coding system for adverse drug reactions. In the USA, maintained by the FDA, Rockville, MD.";
            case CURRENCY: return "The currency unit as defined in ISO 4217";
            case CVX: return "National Immunization Program, Centers for Disease Control and Prevention, 1660 Clifton Road, Atlanta, GA, 30333";
            case DATAOPERATION: return "DataOperation";
            case DATATYPE: return "DataType";
            case DCL: return "From the Message Standards Classes table of the SNOMED-DICOM-Microglossary. College of American Pathologists, Skokie, IL, 60077-1034";
            case DCM: return "Dean Bidgood, MD; Duke University Medical Center, Durham NC. Digital Imaging and Communications in Medicine (DICOM).  From NEMA Publications PS-3.1 - PS 3.12: The ACR-NEMA DICOM Standard. National Electrical Manufacturers Association (NEMA). Rosslyn, VA,";
            case DENTITION: return "Dentition";
            case DEVICEALERTLEVEL: return "Domain values for the Device.Alert_levelCode";
            case DOCUMENTCOMPLETION: return "Identifies the current completion state of a clinical document.";
            case DOCUMENTSTORAGE: return "Identifies the storage status of a document.";
            case DQL: return "HL7 Image Management Special Interest Group, Health Level Seven, Ann Arbor, MI.";
            case E: return "Available from Euclides Foundation International nv, Excelsiorlaan 4A, B-1930 Zaventem, Belgium; Phone: 32 2 720 90 60.";
            case E5: return "Available from Euclides Foundation International nv (see above)";
            case E6: return "Available from Euclides Foundation International nv, Excelsiorlaan 4A, B-1930 Zaventem, Belgium; Phone: 32 2 720 90 60.";
            case E7: return "Available from Euclides Foundation International nv (see above)";
            case EDITSTATUS: return "The status of an entry as it pertains to its review and incorporation into the HL7 domain specification database.";
            case EDUCATIONLEVEL: return "Years of education that a person has completed";
            case EMPLOYEEJOBCLASS: return "EmployeeJobClass";
            case ENCOUNTERACCIDENT: return "EncounterAccident";
            case ENCOUNTERACUITY: return "The level of resource intensiveness of patient care.";
            case ENCOUNTERADMISSIONSOURCE: return "EncounterAdmissionSource";
            case ENCOUNTERREFERRALSOURCE: return "This domain is defined in UB92 and applies to US realm only";
            case ENCOUNTERSPECIALCOURTESY: return "EncounterSpecialCourtesy";
            case ENTITYCLASS: return "Classifies the Entity class and all of its subclasses.  The terminology is hierarchical.  At the top is this  HL7-defined domain of high-level categories (such as represented by the Entity subclasses). Each of these terms must be harmonized and is specializable. The value sets beneath are drawn from multiple, frequently external, domains that reflect much more fine-grained typing.";
            case ENTITYCODE: return "EntityCode";
            case ENTITYDETERMINER: return "EntityDeterminer in natural language grammar is the class of words that comprises articles, demonstrative pronouns, and quantifiers. In the RIM, determiner is a structural code in the Entity class to distinguish whether any given Entity object stands for some, any one, or a specific thing.";
            case ENTITYHANDLING: return "EntityHandling";
            case ENTITYNAMEPARTQUALIFIER: return "EntityNamePartQualifier";
            case ENTITYNAMEPARTQUALIFIERR2: return "Description:The qualifier is a set of codes each of which specifies a certain subcategory of the name part in addition to the main name part type. For example, a given name may be flagged as a nickname, a family name may be a pseudonym or a name of public records.";
            case ENTITYNAMEPARTTYPE: return "EntityNamePartType";
            case ENTITYNAMEPARTTYPER2: return "Description:Indicates whether the name part is a given name, family name, prefix, suffix, etc.";
            case ENTITYNAMEUSE: return "EntityNameUse";
            case ENTITYNAMEUSER2: return "Description:A set of codes advising a system or user which name in a set of names to select for a given purpose.";
            case ENTITYRISK: return "The vocabulary table for the Entity.riskCode attribute";
            case ENTITYSTATUS: return "The status of an instance of the RIM Entity class.";
            case ENZC: return "Enzyme Committee of the International Union of Biochemistry and Molecular Biology. Enzyme Nomenclature: Recommendations on the Nomenclature and Classification of Enzyme-Catalysed Reactions. London: Academic Press, 1992.";
            case EPSGCA: return "Description:The set of values found in the Coord Axis Code column of the Coordinate Axis table as maintained in the EPSG geodetic parameter dataset. These define the axis for coordinate systems for geographic coordinates.";
            case EPSGCRS: return "Description: The set of values found in the Coord Axis Code column of the Coordinate Axis table as maintained in the EPSG geodetic parameter dataset. These define the axis for coordinate systems for geographic coordinates.";
            case EPSGGEODETICPARAMETERDATASET: return "Description: The EPSG (European Petroleum Survey Group) dataset represents all Datums, coordinate references (projected and 2D geographic) and coordinate systems (including Cartesian coordinate systems) used in surveying worldwide.  Each record includes a 4-8 digit unique identifier. The current version is available from http://www.epsg.org/.  The database contains over 4000 records covering spatial data applications worldwide.";
            case EQUIPMENTALERTLEVEL: return "EquipmentAlertLevel";
            case ETHNICITY: return "In the United States, federal standards for classifying data on ethnicity determine the categories used by federal agencies and exert a strong influence on categorization by state and local agencies and private sector organizations. The federal standards do not conceptually define ethnicity, and they recognize the absence of an anthropological or scientific basis for ethnicity classification.  Instead, the federal standards acknowledge that ethnicity is a social-political construct in which an individual's own identification with a particular ethnicity is preferred to observer identification.  The standards specify two minimum ethnicity categories: Hispanic or Latino, and Not Hispanic or Latino.  The standards define a Hispanic or Latino as a person of 'Mexican, Puerto Rican, Cuban, South or Central America, or other Spanish culture or origin, regardless of race.' The standards stipulate that ethnicity data need not be limited to the two minimum categories, but any expansion must be collapsible to those categories.  In addition, the standards stipulate that an individual can be Hispanic or Latino or can be Not Hispanic or Latino, but cannot be both.";
            case EXPOSUREMODE: return "Code for the mechanism by which the exposure agent was exchanged or potentially exchanged by the participants involved in the exposure.";
            case FDDC: return "National Drug Data File. Proprietary product of First DataBank, Inc. (800) 633-3453, or http://www.firstdatabank.com.";
            case FDDX: return "Used for drug-diagnosis interaction checking. Proprietary product of First DataBank, Inc. As above for FDDC.";
            case FDK: return "Dept. of Health & Human Services, Food & Drug Administration, Rockville, MD 20857. (device & analyte process codes).";
            case GENDERSTATUS: return "GenderStatus";
            case GTSABBREVIATION: return "GTSAbbreviation";
            case HB: return "Health Industry Business Communications Council, 5110 N. 40th St., Ste 120, Phoenix, AZ 85018.";
            case HCAIC: return "Description:\n                        \r\n\n                        A code assigned to any component that has medicinal properties, and supplies pharmacological activity or other direct effect in the diagnosis, cure, mitigation, treatment or prevention of disease, or to affect the structure or any function of the body of man or other animals.  http://www.hc-sc.gc.ca/dhp-mps/prodpharma/databasdon/index_e.html";
            case HCAIGC: return "Description: Codes for particular grouping of active ingredients.  This is the first 5 characters of active ingredient group number.  http://www.hc-sc.gc.ca/dhp-mps/prodpharma/databasdon/index_e.html";
            case HCAIGN: return "Description: Codes for particular collections of active ingredients combined at specific strengths.  http://www.hc-sc.gc.ca/dhp-mps/prodpharma/databasdon/index_e.html";
            case HCDIN: return "Description: A Drug Identification Number (DIN) is a number assigned by Health Canada to a drug product prior to being marketed in Canada. It consists of eight digits (numbers) generated by a computer system in the Submission and Information Policy Division.  http://www.hc-sc.gc.ca/dhp-mps/prodpharma/databasdon/index_e.html";
            case HCNPN: return "Description: A unique identifier assigned to natural health products that have been issued a product licence by Health Canada.  http://www.hc-sc.gc.ca/dhp-mps/prodnatur/applications/licen-prod/lnhpd-bdpsnh-eng.php";
            case HEALTHCAREPROVIDERTAXONOMYHIPAA: return "HealthcareProviderTaxonomyHIPAA";
            case HEALTHCARESERVICELOCATION: return "A comprehensive classification of locations and settings where healthcare services are provided. This value set is based on the National Healthcare Safety Network (NHSN) location code system that has been developed over a number of years through CDC's interaction with a variety of healthcare facilities and is intended to serve a variety of reporting needs where coding of healthcare service locations is required.";
            case HHC: return "Home Health Care Classification System; Virginia Saba, EdD, RN; Georgetown University School of Nursing; Washington, DC.";
            case HI: return "Health Outcomes Institute codes for outcome variables available (with responses) from Stratis Health (formerly Foundation for Health Care Evaluation and Health Outcomes Institute), 2901 Metro Drive, Suite 400, Bloomington, MN, 55425-1525; (612) 854-3306 (voice); (612) 853-8503 (fax); dziegen@winternet.com. See examples in the Implementation Guide.";
            case HL7APPROVALSTATUS: return "Description: Codes for concepts describing the approval level of HL7 artifacts.  This code system reflects the concepts expressed in HL7's Governance & Operations Manual (GOM) past and present.";
            case HL7CMETATTRIBUTION: return "HL7CMETAttribution";
            case HL7COMMITTEEIDINRIM: return "Holds the codes used to identify the committees and SIGS of HL7 in RIM repository tables.";
            case HL7CONFORMANCEINCLUSION: return "These concepts represent theconformance requirments defined for including or valuing an element of an HL7 message.  The concepts apply equally to conformance profiles  defined for Version 2.x messgaes as defined by the Conformance SIG, and to the conformance columns for Version 3 messages as specified in the HMD.";
            case HL7DEFINEDROSEPROPERTY: return "The property Ids that HL7 has defined for customizing Rational Rose.";
            case HL7ITSTYPE: return "Description: Codes identifying types of HL7 Implementation Technology Specifications";
            case HL7ITSVERSIONCODE: return "HL7 implementation technology specification versions. These codes will document the ITS type and version for message encoding. The code will appear in the instances based upon rules expressed in the ITS, and do not appear in the abstract message, either as it is presented to received from the ITS.";
            case HL7PUBLISHINGDOMAIN: return "Description: Codes for HL7 publishing 'domain's (specific content area)";
            case HL7PUBLISHINGSECTION: return "Description: Codes for HL7 publishing 'section's (major business categories)";
            case HL7PUBLISHINGSUBSECTION: return "Description: Codes for HL7 publishing 'sub-section's (business sub-categories)";
            case HL7STANDARDVERSIONCODE: return "This code system holds version codes for the Version 3 standards. Values are to be determined by HL7 and added with each new version of the HL7 Standard.";
            case HL7UPDATEMODE: return "The possible modes of updating that occur when an attribute is received by a system that already contains values for that attribute.";
            case HL7V3CONFORMANCE: return "Description: Identifies allowed codes for HL7aTMs v3 conformance property.";
            case HL7VOTERESOLUTION: return "Description: Based on concepts for resolutions from HL7 ballot spreadsheet according to HL7's Governance & Operations Manual (GOM).";
            case HPC: return "Health Care Financing Administration (HCFA) Common Procedure Coding System (HCPCS)  modifiers.";
            case HTMLLINKTYPE: return "HtmlLinkType values are drawn from HTML 4.0 and describe the relationship between the current document and the anchor that is the target of the link";
            case I10: return "World Health Publications, Albany, NY.";
            case I10P: return "Procedure Coding System (ICD-10-PCS).  See http://www/hcfa.gov/stats/icd10.icd10.htm for more information.";
            case I9: return "World Health Publications, Albany, NY.";
            case I9C: return "Commission on Professional and Hospital Activities, 1968 Green Road, Ann Arbor, MI 48105 (includes all procedures and diagnostic tests).";
            case IBT: return "International Society of Blood Transfusion.  Blood Group Terminology 1990.  VOX Sanquines 1990 58(2):152-169.";
            case IC2: return "International Classification of Health Problems in Primary Care, Classification Committee of World Organization of National Colleges, Academies and Academic Associations of General Practitioners (WONCA), 3rd edition.  An adaptation of ICD9 intended for use in General Medicine, Oxford University Press.";
            case ICD10CA: return "Canadian Coding Standards ICD-10 CA. These standards are a compilation of international rules of coding as established by the World Health Organization (International Classification of Diseases, 10th Revision, Volume 2) and the Diagnosis Typing Standard developed to denote case complexity for application in Canadian facilities.\r\n\n                        For example: \r\n\n                        \n                           \n                              L40		Psoriasis\r\n\n                           \n                           \n                              L40.0		Psoriasis vulgaris\r\n\n                              \n                                 \n                                    Nummular psoriasis\r\n\n                                 \n                                 \n                                    Plaque psoriasis\r\n\n                                 \n                              \n                           \n                           \n                              L40.1		Generalized pustular psoriasis\r\n\n                              \n                                 \n                                    Impetigo herpetiformis\r\n\n                                 \n                                 \n                                    Von ZumbuschaTMs disease\r\n\n                                 \n                              \n                           \n                           \n                              L40.2 		Acrodermatitis continua\r\n\n                           \n                           \n                              L40.3		Pustulosis palmaris et plantaris\r\n\n                           \n                           \n                              L40.4		Guttate psoriasis\r\n\n                           \n                           \n                              L40.5*		Arthropathic psoriasis (M07.0-M07.3*)(M09.0*)\r\n\n                           \n                           \n                              L40.8		Other psoriasis\r\n\n                              \n                                 \n                                    Erythroderma psoraticum\r\n\n                                 \n                                 \n                                    Erythrodermic psoriasis\r\n\n                                 \n                                 \n                                    Flexural psoriasis\r\n\n                                 \n                              \n                           \n                           \n                              L40.9		Psoriasis unspecified\r\n\n                           \n                        \n                        They are maintained by CIHI (Canadian Institute for Health Information).\r\n\n                        CIHI Toronto\r\n\n                        Attn: Director of Standards\r\n\n                        90 Eglinton Avenue, Suite 300 \r\n\n                        Toronto, Ontario\r\n\n                        Canada\r\n\n                        M4P 2Y3\r\n\n                        Phone: (416) 481.2002\r\n\n                        Fax: (416) 481-2950 \r\n\n                        www.cihi.ca";
            case ICDO: return "International Classification of Diseases for Oncology, 2nd Edition.  World Health Organization: Geneva, Switzerland, 1990.  Order from: College of American Pathologists, 325 Waukegan Road, Northfield, IL, 60093-2750.  (847) 446-8800.";
            case ICS: return "Commission on Professional and Hospital Activities, 1968 Green Road, Ann Arbor, MI 48105.";
            case ICSD: return "International Classification of Sleep Disorders Diagnostic and Coding Manual, 1990, available from American Sleep Disorders Association, 604 Second Street SW, Rochester, MN  55902";
            case IDENTIFIERRELIABILITY: return "Specifies the reliability with which the identifier is known. This attribute MAY be used to assist with identifier matching algorithms.";
            case IDENTIFIERSCOPE: return "Description: Codes to specify the scope in which the identifier applies to the object with which it is associated, and used in the datatype property II.";
            case IETF1766: return "Language identifiers as defined by IETF RFC 1766: Tags for the Identification of Languages, or its successor on the IETF Standards Track. The biblio ref for RFC 1766 is: IETF (Internet Engineering Task Force), RFC 1766: Tag";
            case IETF3066: return "from OID registry";
            case INTEGRITYCHECKALGORITHM: return "IntegrityCheckAlgorithm";
            case ISO2100062004ERDD: return "ISO/IEC 21000-6:2004 describes a Rights Data Dictionary which comprises a set of clear, consistent, structured, integrated and uniquely identified terms to support the MPEG-21 Rights Expression Language (REL), ISO/IEC 21000-5. Annex A specifies the methodology for and structure of the RDD Dictionary, and specifies how further Terms may be defined under the governance of a Registration Authority, requirements for which are described in Annex C.\r\n\n                        Taken together, these specifications and the RDD Dictionary and Database make up the RDD System. Use of the RDD System will facilitate the accurate exchange and processing of information between interested parties involved in the administration of rights in, and use of, Digital Items, and in particular it is intended to support ISO/IEC 21000-5 (REL). Clause 6 describes how ISO/IEC 21000-6:2004 relates to ISO/IEC 21000-5.\r\n\n                        As well as providing definitions of terms for use in ISO/IEC 21000-5, the RDD System is designed to support the mapping of terms from different namespaces. Such mapping will enable the transformation of metadata from the terminology of one namespace (or Authority) into that of another namespace. Mapping, to ensure minimum ambiguity or loss of semantic integrity, will be the responsibility of the Registration Authority. Provision of automated trm look-up is also a requirement.\r\n\n                        The RDD Dictionary is a prescriptive dctionary, in the sense that it defines a single meaning for a trm represented by a particular RddAuthorized TermName, but it is also inclusive in that it can recognize the prescription of other Headwords and definitions by other Authorities and incorporates them through mappings. The RDD Dictionary also supports the circumstance that the same name may have different meanings under different Authorities. ISO/IEC 21000-6:2004describes audit provisions so that additions, amendments and deletions to Terms and their attributes can be tracked.\r\n\n                        ISO/IEC 21000-6:2004 recognizes legal definitions as and only as Terms from other Authorities that can be mapped into the RDD Dictionary. Therefore Terms that are directly authorized by the RDD Registration Authority neither define nor prescribe intellectual property rights or other legal entities.";
            case ISO31661: return "Two character country codes";
            case ISO31662: return "Three character country codes";
            case ISO31663: return "Numeric country codes";
            case ISO4217: return "ISO 4217 currency code";
            case IUPC: return "Codes used by IUPAC/IFF to identify the component (analyte) measured. Contact Henrik Olesen, as above for IUPP.";
            case IUPP: return "International Union of Pure and Applied Chemistry/International Federation of Clinical Chemistry. The Silver Book: Compendium of terminology and nomenclature of properties in clinical laboratory sciences. Oxford: Blackwell Scientific Publishers, 1995. Henrik Olesen, M.D., D.M.Sc., Chairperson, Department of Clinical Chemistry, KK76.4.2, Rigshospitalet, University Hospital of Copenhagen, DK-2200, Copenhagen. http://inet.uni-c.dk/~qukb7642/";
            case JC8: return "Clinical examination classification code.  Japan Association of Clinical Pathology.  Version 8, 1990.  A multiaxial code  including a subject code (e.g., Rubella = 5f395, identification code (e.g., virus ab IGG), a specimen code (e.g., serum =023) and a method code (e.g., ELISA = 022)";
            case LANGUAGEABILITYMODE: return "LanguageAbilityMode";
            case LANGUAGEABILITYPROFICIENCY: return "LanguageAbilityProficiency";
            case LIVINGARRANGEMENT: return "A code depicting the living arrangements of a person";
            case LN: return "Regenstrief Institute, c/o LOINC, 1050 Wishard Blvd., 5th floor, Indianapolis, IN  46202.  317/630-7433.   Available from the Regenstrief Institute server at http://www.regenstrief.org/loinc/loinc.htm.  Also available via HL7 file server: FTP/Gopher (www.mcis.duke.edu/standards/ termcode/loinclab and www.mcis.duke.edu/standards/termcode/loinclin) and World Wide Web (http:// www.mcis.duke.edu/ standards/termcode/loincl.htm).   January 2000 version has identifiers, synonyms and cross-reference codes for reporting over 26,000 laboratory and related observations and 1,500 clinical measures.";
            case LOCALMARKUPIGNORE: return "Tells a receiver to ignore just the local markup tags (local_markup, local_header, local_attr) when value='markup', or to ignore the local markup tags and all contained content when value='all'";
            case LOCALREMOTECONTROLSTATE: return "LocalRemoteControlState";
            case MANAGEDPARTICIPATIONSTATUS: return "The status of an instance of the RIM Participation class.";
            case MAPRELATIONSHIP: return "The closeness or quality of the mapping between the HL7 concept (as represented by the HL7 concept identifier) and the source coding system. The values are patterned after the similar relationships used in the UMLS Metathesaurus. Because the HL7 coding sy";
            case MARITALSTATUS: return "MaritalStatus";
            case MATERIALTYPE: return "MaterialType";
            case MDC: return "The nomenclature relates primarily to vital signs monitoring, but also includes semantics of other medical devices that are commonly used in acute care settings.  There are multiple coding partitions each of which has a systematic name consisting of a set of base concepts and differentiating criteria.";
            case MDDX: return "Codes Used for drug-diagnosis interaction checking. Proprietary product. Hierarchical drug codes for identifying drugs down to manufacturer and pill size. MediSpan, Inc., 8425 Woodfield Crossing Boulevard, Indianapolis, IN 46240. Tel: (800) 428-4495.  WWW: http://www.espan.com/medispan/pages/ medhome.html. As above for MGPI.";
            case MDFATTRIBUTETYPE: return "MDFAttributeType";
            case MDFHMDMETSOURCETYPE: return "Code to identify the source of a Message Element Type represented in the 'of MET' column of an HMD.";
            case MDFHMDROWTYPE: return "The row type codes for the tabular representation of a Hierarchical Message Description.";
            case MDFRMIMROWTYPE: return "The row types for the tabular representation of an R-MIM.";
            case MDFSUBJECTAREAPREFIX: return "The standard prefixes used in Rose for RIM subject areas that determine the role or function of each subject area.";
            case MEDC: return "Proprietary Codes for identifying drugs. Proprietary product of Medical Economics Data, Inc. (800) 223-0581.";
            case MEDCIN: return "MEDCIN contains more than 175,000 clinical data elements arranged in a hierarchy, with each item having weighted links to relevant diagnoses.  The clinical data elements are organized into six basic termtypes designed to accommodate information relevant to a clinical encounter.  The basic termtypes in MEDCIN's terminological hierarchy are as follows:\r\n\n                        Symptoms History Physical Examination Tests Diagnoses Therapy\r\n\n                        Within this basic structure, MEDCIN terms are further organized in a ten level terminological hierarchy, supplemented by an optional, multi-hierarchical diagnostic index.  For example, the symptom of 'difficulty breathing' is placed in the terminological hierarchy as a subsidiary (or 'child') finding of 'pulmonary symptoms' although the presence (or absence) of difficulty breathing can related to conditions as diverse as myocardial infarction, bronchitis, pharyngeal foreign bodies, asthma, pulmonary embolism, etc.  MEDCIN's diagnostic index provides more than 800 such links for difficulty breathing.";
            case MEDIATYPE: return "Internet Assigned Numbers Authority (IANA) Mime Media Types";
            case MEDR: return "Dr. Louise Wood, Medicines Control Agency, Market Towers, 1 Nine Elms Lane, London SW85NQ, UK   Tel: (44)0 171-273-0000 WWW:  http://www.open.gov.uk/mca/mcahome.htm";
            case MEDX: return "Used for drug-diagnosis interaction checking. Proprietary product of Medical Economics Data, Inc. (800) 223-0581.";
            case MESSAGECONDITION: return "MessageCondition";
            case MESSAGEWAITINGPRIORITY: return "Indicates that the receiver has messages for the sender";
            case MGPI: return "Medispan hierarchical drug codes for identifying drugs down to manufacturer and pill size.  Proprietary product of MediSpan, Inc., 8425 Woodfield Crossing Boulevard, Indianapolis, IN 46240. Tel: (800) 428-4495.";
            case MIME: return "IETF MIME media types";
            case MODIFYINDICATOR: return "ModifyIndicator";
            case MSH: return "Medical Subject Headings (MeSH). Bethesda (MD): National Library of Medicine, 2004";
            case MULTUM: return "Broadly, the fields and values in the Multum Lexicon and the VantageRx Database are intended to be available for use in any HL7 message that includes a reference to non-veterinary drug products or active ingredients that are either approved for sale by the FDA or readily available in the United States.  The following inter-related definitions recently circulated by us to the HL7 Vocabulary Technical Committee explain the scope of what we mean by 'drug product' and 'active ingredient.'  (A definition for 'drug ingredient' is also provided here because the definition of 'active ingredient' is reliant on this term.)\r\n\n                        Drug Product A drug product is a manufactured or extemporaneously-compounded physiologically-active material intended by the preparer to achieve therapeutic, diagnostic, or preventative effects via biochemical mechanisms when applied to an epithelial surface or placed in an internal body space of a targeted organism.\r\n\n                        Drug Ingredient A drug ingredient is a chemical compound or biologic agent that occurs in a drug product.\r\n\n                        Active Ingredient An active ingredient is a drug ingredient that mediates one or more of the intended therapeutic, diagnostic, or preventative effects of a drug product and is present in sufficient quantities to achieve such effects according to the allopathic tradition of healthcare practice.";
            case MVX: return "National Immunization Program, Centers for Disease Control and Prevention, 1660 Clifton Road, Atlanta, GA, 30333";
            case NAACCR: return "NAACCR Cancer Registry";
            case NAICS: return "North American Industry Classification System(NAICS) for the United States, a new economic classification system that replaces the 1987 Standard Industrial Classification (SIC) for statistical purposes.  NAICS is a system for classifying establishments by type of economic activity.  Its purposes are:  (1) to facilitate the collection, tabulation, presentation, and analysis of data relating to establishments, and (2) to promote uniformity and comparability in the presentation of statistical data describing the economy.  NAICS will be used by Federal statistical agencies that collect or publish data by industry.";
            case NDA: return "North American Nursing Diagnosis Association, Philadelphia, PA.";
            case NDC: return "These provide unique codes for each distinct drug, dosing form, manufacturer, and packaging. (Available from the National Drug Code Directory, FDA, Rockville, MD, and other sources.)";
            case NIC: return "Iowa Intervention Project, College of Nursing, University of Iowa, Iowa City, Iowa";
            case NMMDS: return "The NMMDS is the minimum set of items of information with uniform definitions and categories concerning the specific dimension of the context of patient care delivery.  It represents the minimum data used to support the management and administration of patient/nursing care delivery across all types of settings.  The NMMDS is composed of seventeen (17) data elements organized into three categories: environment, nurse resources, and financial resources.  See Tables 1-3 for the elements and related definitions organized by each categories.  The NMMDS most appropriately focuses at the first level of accountability for patient/client/family/community nursing care: this may be the delivery unit, service, or center of excellence level.  The NMMDS supports numerous constructed variables as well as aggregation of data at the unit, institution, network, and system, etc levels.  This minimum data set provides the structure for the collection of uniform information that influences quality of patient care, directly and indirectly.";
            case NOC: return "NOC - Nursing Outcome Codes";
            case NUBCUB92: return "The UB-92 data element specifications are developed and maintained by the NUBC.  The data element specifications are for use in EDI billing and payment transactions and related business applications.  There is a proprietary  fee. Available from the National Uniform Billing Committee of the American Hospital Association, One North Franklin, Chicago, IL 60606.  'UB-92 National Uniform Billing Data Element Specifications as developed by the National Uniform Billing Committee as of August 13, 1999'.   url:  http://www.nubc.org";
            case NUCCPROVIDERCODES: return "The Provider Taxonomy Code List is published (released) twice a year on July 1st and January 1st. The July publication is effective for use on October 1st and the January publication is effective for use on April 1st. The time between the publication release and the effective date is considered an implementation period to allow providers, payers and vendors an opportunity to incorporate any changes into their systems. This listing includes Active codes approved for use effective April 1st, 2003, version 3.0; and codes that are New and approved for use effective October 1st, 2003, version 3.1.";
            case NULLFLAVOR: return "NullFlavor";
            case OBSERVATIONINTERPRETATION: return "ObservationInterpretation";
            case OBSERVATIONMETHOD: return "ObservationMethod";
            case OBSERVATIONVALUE: return "This domain is the root domain to which all HL7-recognized value sets for the Observation.value attribute will be linked when Observation.value has a coded data type.";
            case OHA: return "Omaha Visiting Nurse Association, Omaha, NB.";
            case OPINIONS: return "Description: Codes to identify products and services that do not have DIN's and which need to be billed.  http://www.atlanticpharmaceutical.ca/default.asp?mn=5.23";
            case ORDERABLEDRUGFORM: return "OrderableDrugForm";
            case ORGANIZATIONNAMETYPE: return "OrganizationNameType";
            case PARAMETERIZEDDATATYPE: return "ParameterizedDataType";
            case PARTICIPATIONFUNCTION: return "This code is used to specify the exact function an actor had in a service in all necessary detail. This domain may include local extensions (CWE).";
            case PARTICIPATIONMODE: return "Identifies the primary means by which an Entity participates in an Act.";
            case PARTICIPATIONSIGNATURE: return "ParticipationSignature";
            case PARTICIPATIONTYPE: return "ParticipationType";
            case PATIENTIMPORTANCE: return "Patient VIP code";
            case PAYMENTTERMS: return "Describes payment terms for a financial transaction, used in an invoice.\r\n\n                        This is typically expressed as a responsibility of the acceptor or payor of an invoice.";
            case PERIODICINTERVALOFTIMEABBREVIATION: return "PeriodicIntervalOfTimeAbbreviation";
            case PERSONDISABILITYTYPE: return "A code identifying a person's disability.";
            case PNDS: return "The PNDS provides standardized terms and codes for patient problems/nursing diagnoses, nursing interventions including actual or expected (goal) outcomes.   The PNDS provides standardized terms and codes for nursing diagnoses (a subset of NANDA), nursing interventions and outcomes. The outcomes and interventions are in a relational database. The PNDS intervention and outcome statements are attached in an Access Database. The NANDA diagnoses in the PNDS have already been registered by HL7.";
            case POS: return "HCFA Place of Service Codes for Professional Claims (see http://www.hcfa.gov/medicare/poscode.htm).";
            case POSTALADDRESSUSE: return "PostalAddressUse";
            case PROBABILITYDISTRIBUTIONTYPE: return "ProbabilityDistributionType";
            case PROCEDUREMETHOD: return "Identifies the technique used to perform a procedure.";
            case PROCESSINGID: return "ProcessingID";
            case PROCESSINGMODE: return "ProcessingMode";
            case QUERYPARAMETERVALUE: return "The domain of coded values used as parameters within QueryByParameter queries.";
            case QUERYPRIORITY: return "QueryPriority";
            case QUERYQUANTITYUNIT: return "Values in this domain specify the units of a query quantity limited request.";
            case QUERYREQUESTLIMIT: return "Definition: Defines the units associated with the magnitude of the maximum size limit of a query response that can be accepted by the requesting application.";
            case QUERYRESPONSE: return "Values in this domain allow a query response system to return a precise response status.";
            case QUERYSTATUSCODE: return "State attributes for Query event";
            case RACE: return "In the United States, federal standards for classifying data on race determine the categories used by federal agencies and exert a strong influence on categorization by state and local agencies and private sector organizations.  The federal standards do not conceptually define race, and they recognize the absence of an anthropological or scientific basis for racial classification.  Instead, the federal standards acknowledge that race is a social-political construct in which an individual's own identification with one more race categories is preferred to observer identification. The standards use a variety of features to define five minimum race categories. Among these features are descent from 'the original peoples' of a specified region or nation.  The minimum race categories are American Indian or Alaska Native, Asian, Black or African American, Native Hawaiian or Other Pacific Islander, and White.  The federal standards stipulate that race data need not be limited to the five minimum categories, but any expansion must be collapsible to those categories.";
            case RC: return "The Read Clinical Classification of Medicine, Park View Surgery, 26 Leicester Rd., Loughborough LE11 2AG (includes drug procedure and other codes, as well as diagnostic codes).";
            case RCFB: return "The Read Codes Four Byte Set consists of 4 alphanumeric characters. This version contains approximately 40,000 codes arranged in a hierarchical structure.\r\n\n                        Top level hierarchy sections:	 Disorders Findings Surgical procedures Investigations Occupations Drugs";
            case RCV2: return "The Read Codes Version 2 contains over 70,000 coded concepts arranged in a hierarchical structure.\r\n\n                        Top level hierarchy sections:	 Disorders Findings Surgical procedures Investigations Occupations Drugs";
            case RELATIONALOPERATOR: return "RelationalOperator";
            case RELATIONSHIPCONJUNCTION: return "RelationshipConjunction";
            case RELIGIOUSAFFILIATION: return "Assigment of spiritual faith affiliation";
            case RESPONSELEVEL: return "Specifies whether a response is expected from the addressee of this interaction and what level of detail that response should include";
            case RESPONSEMODALITY: return "ResponseModality";
            case RESPONSEMODE: return "Specifies the mode, immediate versus deferred or queued, by which a receiver should communicate its receiver responsibilities.";
            case ROLECLASS: return "RoleClass";
            case ROLECODE: return "Specific classification codes for further qualifying RoleClass codes.";
            case ROLELINKSTATUS: return "Description: Codes representing possible states of a RoleLink, as defined by the RoleLink class state machine.";
            case ROLELINKTYPE: return "RoleLinkType";
            case ROLESTATUS: return "The status of an instance of the RIM Role class.";
            case ROUTEOFADMINISTRATION: return "RouteOfAdministration";
            case SCDHECGISSPATIALACCURACYTIERS: return "Description: The South Carolina Department of Health and Environmental Control GIS Spatial Data Accuracy Tiers have been derived from the National Standard for Spatial Data Accuracy as a means to categorize the accuracy of spatial data assignment utilizing a variety of tools for capturing coordinates including digitizers, geocoding software and global positioning system devices.";
            case SDM: return "College of American Pathologists, Skokie, IL, 60077-1034. (formerly designated as 99SDM).";
            case SEQUENCING: return "Sequencing";
            case SETOPERATOR: return "SetOperator";
            case SNM: return "Systemized Nomenclature of Medicine, 2nd Edition 1984 Vols 1, 2, College of American Pathologists, Skokie, IL.";
            case SNM3: return "SNOMED International, 1993 Vols 1-4, College of American Pathologists, Skokie, IL, 60077-1034..";
            case SNT: return "College of American Pathologists, 5202 Old Orchard Road, Skokie, IL 60077-1034.";
            case SPECIALARRANGEMENT: return "A code indicating the type of special arrangements provided for a patient encounter (e.g., wheelchair, stretcher, interpreter, attendant, seeing eye dog). For encounters in intention moods, this information can be used to identify special arrangements that will need to be made for the incoming patient.";
            case SPECIMENTYPE: return "SpecimenType";
            case STYLETYPE: return "The style code is used within the CDA/SPL narrative block to give the instance author some control over various aspects of style";
            case SUBSTANCEADMINSUBSTITUTION: return "Identifies what sort of change is permitted or has occurred between the therapy that was ordered and the therapy that was/will be provided.";
            case SUBSTITUTIONCONDITION: return "Identifies what sort of change is permitted or has occurred between the item that was ordered/requested and the one that was/will be provided.";
            case TABLECELLHORIZONTALALIGN: return "These values are defined within the XHTML 4.0 Table Model";
            case TABLECELLSCOPE: return "These values are defined within the XHTML 4.0 Table Model";
            case TABLECELLVERTICALALIGN: return "These values are defined within the XHTML 4.0 Table Model";
            case TABLEFRAME: return "These values are defined within the XHTML 4.0 Table Model";
            case TABLERULES: return "These values are defined within the XHTML 4.0 Table Model";
            case TARGETAWARENESS: return "TargetAwareness";
            case TELECOMMUNICATIONADDRESSUSE: return "TelecommunicationAddressUse";
            case TELECOMMUNICATIONCAPABILITIES: return "Description: Concepts that define the telecommunication capabilities of a particular device. Used to identify the expected capabilities to be found at a particular telecommunication address.";
            case TIMINGEVENT: return "TimingEvent";
            case TRANSMISSIONRELATIONSHIPTYPECODE: return "Description:A code specifying the meaning and purpose of every TransmissionRelationship instance. Each of its values implies specific constraints to what kinds of Transmission objects can be related and in which way.";
            case TRIBALENTITYUS: return "INDIAN ENTITIES RECOGNIZED AND ELIGIBLE TO RECEIVE SERVICES FROM THE UNITED STATES BUREAU OF INDIAN AFFAIRS";
            case TRIGGEREVENTID: return "Description:Trigger Event ID as published in the standard.";
            case UC: return "Uniform Clinical Data Systems. Ms. Michael McMullan, Office of Peer Review Health Care Finance Administration, The Meadows East Bldg., 6325 Security Blvd., Baltimore, MD 21207; (301) 966 6851.";
            case UCUM: return "Unified Code for Units of Measure";
            case UMD: return "Universal Medical Device Nomenclature System.  ECRI, 5200 Butler Pike, Plymouth Meeting, PA  19462 USA.  Phone: 215-825-6000, Fax: 215-834-1275.";
            case UML: return "National Library of Medicine, 8600 Rockville Pike, Bethesda, MD 20894.";
            case UNITSOFMEASURE: return "UnitsOfMeasureCaseInsensitive";
            case UPC: return "The Uniform Code Council.  8163 Old Yankee Road, Suite J, Dayton, OH  45458; (513) 435 3070";
            case URLSCHEME: return "A Universal Resource Locator (URL) is a type of telecommunications address specified as Internet standard RFC 1738 [http://www.ietf.org/rfc/rfc1738.txt].  The URL specifies the protocol and the contact point defined by that protocol for the resource.";
            case VACCINEMANUFACTURER: return "The manufacturer of a vaccine.";
            case VACCINETYPE: return "The kind of vaccine.";
            case VOCABULARYDOMAINQUALIFIER: return "Vocabulary domain qualifiers are concepts that are used in domain constraints to specify behavior of the new domain.";
            case W1W2: return "World Health organization record number code. A unique sequential number is assigned to each unique single component drug and to each multi-component drug.  Eight digits are allotted to each such code, six to identify the active agent, and 2 to identify the salt, of single content drugs.  Six digits are assigned to each unique combination of drugs in a dispensing unit.  The six digit code is identified by W1, the 8 digit code by W2.";
            case W4: return "With ASTM extensions (see Implementation Guide), the WHO codes can be used to report serum (and other) levels, patient compliance with drug usage instructions, average daily doses and more (see Appendix X1 the Implementation Guide).";
            case WC: return "WHO's ATC codes provide a hierarchical classification of drugs by therapeutic class.  They are linked to the record number codes listed above.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case ABCCODES: return "ABCcodes";
            case ACKNOWLEDGEMENTCONDITION: return "AcknowledgementCondition";
            case ACKNOWLEDGEMENTDETAILCODE: return "AcknowledgementDetailCode";
            case ACKNOWLEDGEMENTDETAILTYPE: return "Acknowledgement Detail Type";
            case ACKNOWLEDGEMENTTYPE: return "AcknowledgementType";
            case ACR: return "American College of Radiology finding codes";
            case ACTCLASS: return "ActClass";
            case ACTCODE: return "ActCode";
            case ACTEXPOSURELEVELCODE: return "ActExposureLevelCode";
            case ACTINVOICEELEMENTMODIFIER: return "ActInvoiceElementModifier";
            case ACTMOOD: return "ActMood";
            case ACTPRIORITY: return "ActPriority";
            case ACTREASON: return "ActReason";
            case ACTRELATIONSHIPCHECKPOINT: return "ActRelationshipCheckpoint";
            case ACTRELATIONSHIPJOIN: return "ActRelationshipJoin";
            case ACTRELATIONSHIPSPLIT: return "ActRelationshipSplit";
            case ACTRELATIONSHIPSUBSET: return "ActRelationshipSubset";
            case ACTRELATIONSHIPTYPE: return "ActRelationshipType";
            case ACTSITE: return "ActSite";
            case ACTSTATUS: return "ActStatus";
            case ACTUNCERTAINTY: return "ActUncertainty";
            case ACTUSPRIVACYLAW: return "Act US Privacy Law";
            case ADDRESSPARTTYPE: return "AddressPartType";
            case ADDRESSUSE: return "AddressUse";
            case ADMINISTRATIVEGENDER: return "AdministrativeGender";
            case AHFS: return "AHFS Pharmacologic-Therapeutic Classification";
            case AMERICANINDIANALASKANATIVELANGUAGES: return "AmericanIndianAlaskaNativeLanguages";
            case ART: return "WHO Adverse Reaction Terms";
            case AS4: return "ASTM E1238/ E1467 Universal";
            case AS4E: return "AS4 Neurophysiology Codes";
            case ATC: return "American Type Culture Collection";
            case BINDINGREALM: return "BindingRealm";
            case BODYSITE: return "HL7 table 0163 - Body site";
            case C4: return "CPT-4";
            case C5: return "CPT-5";
            case CALENDAR: return "Calendar";
            case CALENDARCYCLE: return "CalendarCycle";
            case CALENDARTYPE: return "CalendarType";
            case CAMNCVS: return "CAMNCVS";
            case CAS: return "Chemical abstract codes";
            case CCI: return "Canadian Classification of Health Interventions";
            case CD2: return "CDT-2 Codes";
            case CDCA: return "CDC Analyte Codes";
            case CDCM: return "CDC Methods/Instruments Codes";
            case CDS: return "CDC Surveillance";
            case CE: return "CEN ECG diagnostic codes";
            case CHARSET: return "Charset";
            case CLP: return "CLIP";
            case CODESYSTEM: return "CodeSystem";
            case CODESYSTEMTYPE: return "HL7 Code System Type";
            case CODINGRATIONALE: return "CodingRationale";
            case COMMUNICATIONFUNCTIONTYPE: return "CommunicationFunctionType";
            case COMPRESSIONALGORITHM: return "CompressionAlgorithm";
            case CONCEPTCODERELATIONSHIP: return "Possible Concept Code Relationships";
            case CONCEPTGENERALITY: return "ConceptGenerality";
            case CONCEPTPROPERTY: return "HL7 Value Set and Coded Concept Property Codes";
            case CONCEPTSTATUS: return "HL7 Coded Concept Status";
            case CONFIDENTIALITY: return "Confidentiality";
            case CONTAINERCAP: return "ContainerCap";
            case CONTAINERSEPARATOR: return "ContainerSeparator";
            case CONTENTPROCESSINGMODE: return "ContentProcessingMode";
            case CONTEXTCONDUCTIONSTYLE: return "ContextConductionStyle";
            case CONTEXTCONTROL: return "ContextControl";
            case CSAID: return "CAN/CSA-Z795-96 (R2001)";
            case CST: return "COSTART";
            case CURRENCY: return "Currency";
            case CVX: return "CDC Vaccine Codes";
            case DATAOPERATION: return "DataOperation";
            case DATATYPE: return "DataType";
            case DCL: return "DICOM Class Label";
            case DCM: return "DICOM modality codes";
            case DENTITION: return "Dentition";
            case DEVICEALERTLEVEL: return "DeviceAlertLevel";
            case DOCUMENTCOMPLETION: return "DocumentCompletion";
            case DOCUMENTSTORAGE: return "DocumentStorage";
            case DQL: return "DICOM Query Label";
            case E: return "EUCLIDES";
            case E5: return "Euclides quantity codes";
            case E6: return "Euclides Lab method codes";
            case E7: return "Euclides Lab equipment codes";
            case EDITSTATUS: return "EditStatus";
            case EDUCATIONLEVEL: return "Education Level";
            case EMPLOYEEJOBCLASS: return "EmployeeJobClass";
            case ENCOUNTERACCIDENT: return "EncounterAccident";
            case ENCOUNTERACUITY: return "Encounter Acuity";
            case ENCOUNTERADMISSIONSOURCE: return "EncounterAdmissionSource";
            case ENCOUNTERREFERRALSOURCE: return "EncounterReferralSource";
            case ENCOUNTERSPECIALCOURTESY: return "EncounterSpecialCourtesy";
            case ENTITYCLASS: return "EntityClass";
            case ENTITYCODE: return "EntityCode";
            case ENTITYDETERMINER: return "EntityDeterminer";
            case ENTITYHANDLING: return "EntityHandling";
            case ENTITYNAMEPARTQUALIFIER: return "EntityNamePartQualifier";
            case ENTITYNAMEPARTQUALIFIERR2: return "EntityNamePartQualifierR2";
            case ENTITYNAMEPARTTYPE: return "EntityNamePartType";
            case ENTITYNAMEPARTTYPER2: return "EntityNamePartTypeR2";
            case ENTITYNAMEUSE: return "EntityNameUse";
            case ENTITYNAMEUSER2: return "EntityNameUseR2";
            case ENTITYRISK: return "EntityRisk";
            case ENTITYSTATUS: return "EntityStatus";
            case ENZC: return "Enzyme Codes";
            case EPSGCA: return "European Petroleum Survey Group Geodetic Parameter Dataset Coordinate Axis";
            case EPSGCRS: return "European Petroleum Survey Group Geodetic Parameter Dataset Coordinate Reference System";
            case EPSGGEODETICPARAMETERDATASET: return "EPSG Geodetic Parameter Dataset";
            case EQUIPMENTALERTLEVEL: return "EquipmentAlertLevel";
            case ETHNICITY: return "Ethnicity";
            case EXPOSUREMODE: return "ExposureMode";
            case FDDC: return "First DataBank Drug Codes";
            case FDDX: return "First DataBank Diagnostic Codes";
            case FDK: return "FDA K10";
            case GENDERSTATUS: return "GenderStatus";
            case GTSABBREVIATION: return "GTSAbbreviation";
            case HB: return "HIBCC";
            case HCAIC: return "Active Ingredient Code";
            case HCAIGC: return "Active Ingredient Group Code";
            case HCAIGN: return "Active Ingredient Group Number";
            case HCDIN: return "Health Canada Drug Id Number";
            case HCNPN: return "Health Canada Natural Product Number";
            case HEALTHCAREPROVIDERTAXONOMYHIPAA: return "HealthcareProviderTaxonomyHIPAA";
            case HEALTHCARESERVICELOCATION: return "Healthcare Service Location";
            case HHC: return "Home Health Care";
            case HI: return "Health Outcomes";
            case HL7APPROVALSTATUS: return "HL7ApprovalStatus";
            case HL7CMETATTRIBUTION: return "HL7CMETAttribution";
            case HL7COMMITTEEIDINRIM: return "HL7CommitteeIDInRIM";
            case HL7CONFORMANCEINCLUSION: return "HL7ConformanceInclusion";
            case HL7DEFINEDROSEPROPERTY: return "HL7DefinedRoseProperty";
            case HL7ITSTYPE: return "HL7ITSType";
            case HL7ITSVERSIONCODE: return "HL7 ITS Version Code";
            case HL7PUBLISHINGDOMAIN: return "HL7PublishingDomain";
            case HL7PUBLISHINGSECTION: return "HL7PublishingSection";
            case HL7PUBLISHINGSUBSECTION: return "HL7PublishingSubSection";
            case HL7STANDARDVERSIONCODE: return "HL7StandardVersionCode";
            case HL7UPDATEMODE: return "HL7UpdateMode";
            case HL7V3CONFORMANCE: return "HL7V3Conformance";
            case HL7VOTERESOLUTION: return "HL7VoteResolution";
            case HPC: return "HCFA Procedure Codes (HCPCS)";
            case HTMLLINKTYPE: return "HtmlLinkType";
            case I10: return "ICD-10";
            case I10P: return "ICD-10 Procedure Codes";
            case I9: return "ICD9";
            case I9C: return "ICD-9CM";
            case IBT: return "ISBT";
            case IC2: return "ICHPPC-2";
            case ICD10CA: return "CanadianDiagnosisCodesICD-10-CA";
            case ICDO: return "International Classification of Diseases for Oncology";
            case ICS: return "ICCS";
            case ICSD: return "International Classification of Sleep Disorders";
            case IDENTIFIERRELIABILITY: return "IdentifierReliability";
            case IDENTIFIERSCOPE: return "IdentifierScope";
            case IETF1766: return "IETF RFC 1766";
            case IETF3066: return "Tags for the Identification of Languages";
            case INTEGRITYCHECKALGORITHM: return "IntegrityCheckAlgorithm";
            case ISO2100062004ERDD: return "ISO/IEC 21000-6:2004(E) Rights Data Dictionary";
            case ISO31661: return "ISO 3166 2 Character Country Codes";
            case ISO31662: return "ISO 3166 3 Character Country Codes";
            case ISO31663: return "ISO 3166 Numeric country Codes";
            case ISO4217: return "ISO4217";
            case IUPC: return "IUPAC/IFCC Component Codes";
            case IUPP: return "IUPAC/IFCC Property Codes";
            case JC8: return "Japanese Chemistry";
            case LANGUAGEABILITYMODE: return "LanguageAbilityMode";
            case LANGUAGEABILITYPROFICIENCY: return "LanguageAbilityProficiency";
            case LIVINGARRANGEMENT: return "LivingArrangement";
            case LN: return "Logical Observation Identifier Names and Codes (LOINC)";
            case LOCALMARKUPIGNORE: return "LocalMarkupIgnore";
            case LOCALREMOTECONTROLSTATE: return "LocalRemoteControlState";
            case MANAGEDPARTICIPATIONSTATUS: return "ManagedParticipationStatus";
            case MAPRELATIONSHIP: return "MapRelationship";
            case MARITALSTATUS: return "MaritalStatus";
            case MATERIALTYPE: return "MaterialType";
            case MDC: return "ISO 11073-10101 Health informatics - Point-of-care";
            case MDDX: return "Medispan Diagnostic Codes";
            case MDFATTRIBUTETYPE: return "MDFAttributeType";
            case MDFHMDMETSOURCETYPE: return "MdfHmdMetSourceType";
            case MDFHMDROWTYPE: return "MdfHmdRowType";
            case MDFRMIMROWTYPE: return "MdfRmimRowType";
            case MDFSUBJECTAREAPREFIX: return "MDFSubjectAreaPrefix";
            case MEDC: return "Medical Economics Drug Codes";
            case MEDCIN: return "MEDCIN";
            case MEDIATYPE: return "MediaType";
            case MEDR: return "Medical Dictionary for Drug Regulatory Affairs (MEDRA)";
            case MEDX: return "Medical Economics Diagnostic Codes";
            case MESSAGECONDITION: return "MessageCondition";
            case MESSAGEWAITINGPRIORITY: return "Message Waiting Priority";
            case MGPI: return "Medispan GPI";
            case MIME: return "MIME";
            case MODIFYINDICATOR: return "ModifyIndicator";
            case MSH: return "MeSH";
            case MULTUM: return "Multum Lexicon";
            case MVX: return "CDC Vaccine Manufacturer Codes";
            case NAACCR: return "NAACCR";
            case NAICS: return "North American Industry Classification System";
            case NDA: return "NANDA";
            case NDC: return "National drug codes";
            case NIC: return "Nursing Intervention Classification";
            case NMMDS: return "Nursing Management Minimum Data Set";
            case NOC: return "NOC";
            case NUBCUB92: return "National Uniform Billing Council, UB 92";
            case NUCCPROVIDERCODES: return "NUCC Health Care Provider Taxonomy";
            case NULLFLAVOR: return "NullFlavor";
            case OBSERVATIONINTERPRETATION: return "ObservationInterpretation";
            case OBSERVATIONMETHOD: return "ObservationMethod";
            case OBSERVATIONVALUE: return "ObservationValue";
            case OHA: return "Omaha System";
            case OPINIONS: return "Online Product Identification Number Index of Nova Scotia";
            case ORDERABLEDRUGFORM: return "OrderableDrugForm";
            case ORGANIZATIONNAMETYPE: return "OrganizationNameType";
            case PARAMETERIZEDDATATYPE: return "ParameterizedDataType";
            case PARTICIPATIONFUNCTION: return "ParticipationFunction";
            case PARTICIPATIONMODE: return "ParticipationMode";
            case PARTICIPATIONSIGNATURE: return "ParticipationSignature";
            case PARTICIPATIONTYPE: return "ParticipationType";
            case PATIENTIMPORTANCE: return "PatientImportance";
            case PAYMENTTERMS: return "PaymentTerms";
            case PERIODICINTERVALOFTIMEABBREVIATION: return "PeriodicIntervalOfTimeAbbreviation";
            case PERSONDISABILITYTYPE: return "PersonDisabilityType";
            case PNDS: return "Perioperative Nursing Data Set";
            case POS: return "POS Codes";
            case POSTALADDRESSUSE: return "PostalAddressUse";
            case PROBABILITYDISTRIBUTIONTYPE: return "ProbabilityDistributionType";
            case PROCEDUREMETHOD: return "ProcedureMethod";
            case PROCESSINGID: return "ProcessingID";
            case PROCESSINGMODE: return "ProcessingMode";
            case QUERYPARAMETERVALUE: return "QueryParameterValue";
            case QUERYPRIORITY: return "QueryPriority";
            case QUERYQUANTITYUNIT: return "QueryQuantityUnit";
            case QUERYREQUESTLIMIT: return "QueryRequestLimit";
            case QUERYRESPONSE: return "QueryResponse";
            case QUERYSTATUSCODE: return "QueryStatusCode";
            case RACE: return "Race";
            case RC: return "Read Classification";
            case RCFB: return "The Read Codes Four Byte Set:";
            case RCV2: return "The Read Codes Version 2";
            case RELATIONALOPERATOR: return "RelationalOperator";
            case RELATIONSHIPCONJUNCTION: return "RelationshipConjunction";
            case RELIGIOUSAFFILIATION: return "Religious Affiliation";
            case RESPONSELEVEL: return "ResponseLevel";
            case RESPONSEMODALITY: return "ResponseModality";
            case RESPONSEMODE: return "ResponseMode";
            case ROLECLASS: return "RoleClass";
            case ROLECODE: return "RoleCode";
            case ROLELINKSTATUS: return "RoleLink Status";
            case ROLELINKTYPE: return "RoleLinkType";
            case ROLESTATUS: return "RoleStatus";
            case ROUTEOFADMINISTRATION: return "RouteOfAdministration";
            case SCDHECGISSPATIALACCURACYTIERS: return "SCDHEC GIS Spatial Accuracy Tiers";
            case SDM: return "SNOMED- DICOM Microglossary";
            case SEQUENCING: return "Sequencing";
            case SETOPERATOR: return "SetOperator";
            case SNM: return "Systemized Nomenclature of Medicine (SNOMED)";
            case SNM3: return "SNOMED International";
            case SNT: return "SNOMED topology codes (anatomic sites)";
            case SPECIALARRANGEMENT: return "SpecialArrangement";
            case SPECIMENTYPE: return "SpecimenType";
            case STYLETYPE: return "StyleType";
            case SUBSTANCEADMINSUBSTITUTION: return "SubstanceAdminSubstitution";
            case SUBSTITUTIONCONDITION: return "SubstitutionCondition";
            case TABLECELLHORIZONTALALIGN: return "TableCellHorizontalAlign";
            case TABLECELLSCOPE: return "TableCellScope";
            case TABLECELLVERTICALALIGN: return "TableCellVerticalAlign";
            case TABLEFRAME: return "TableFrame";
            case TABLERULES: return "TableRules";
            case TARGETAWARENESS: return "TargetAwareness";
            case TELECOMMUNICATIONADDRESSUSE: return "TelecommunicationAddressUse";
            case TELECOMMUNICATIONCAPABILITIES: return "TelecommunicationCapabilities";
            case TIMINGEVENT: return "TimingEvent";
            case TRANSMISSIONRELATIONSHIPTYPECODE: return "TransmissionRelationshipTypeCode";
            case TRIBALENTITYUS: return "TribalEntityUS";
            case TRIGGEREVENTID: return "TriggerEventID";
            case UC: return "UCDS";
            case UCUM: return "UCUM";
            case UMD: return "MDNS";
            case UML: return "Unified Medical Language";
            case UNITSOFMEASURE: return "UnitsOfMeasureCaseInsensitive";
            case UPC: return "Universal Product Code";
            case URLSCHEME: return "URLScheme";
            case VACCINEMANUFACTURER: return "VaccineManufacturer";
            case VACCINETYPE: return "VaccineType";
            case VOCABULARYDOMAINQUALIFIER: return "VocabularyDomainQualifier";
            case W1W2: return "WHO rec# drug codes";
            case W4: return "WHO rec# code with ASTM extension";
            case WC: return "WHO ATC";
            default: return "?";
          }
    }


}

