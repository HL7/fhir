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


public enum V3HL7DefinedRoseProperty {

        /**
         * Holds the unique identifier of an Application Role (stereotype of class).
         */
        APPLICATIONROLEI, 
        /**
         * Records the constraints on the cardinality of attributes as documented in the RIM and in MIMs.
         */
        CARDINALITY, 
        /**
         * Used in constructing a Message Information Model to indicate which attributes to omit from the MIM.
         */
        DELETEFROMMIM, 
        /**
         * Holds the id of the HL7 committee developing the model like "C00."
         */
        DEVELOPINGCOMMIT, 
        /**
         * Holds the symbol for a defined data type (stereotype of class).
         */
        DTSYMBOL, 
        /**
         * The HL7 MDF says that leaf level use cases should be connected to a state transition of the subject class.  The use case diagram provides the link to the subject class.  The modeler must use three properties to identify the state transition -- this property and the two following ones named StartState and StateTransition.  The first two are, as their names imply, the names of the starting state and ending state of the transition, and the third is the name of the transition.
         */
        ENDSTATE, 
        /**
         * The identifier of the HMD from which the message structure for the message transferred by an interaction is drawn.
         */
        HMD, 
        /**
         * Holds the unique identifier of use cases, interactions and storyboards.
         */
        ID, 
        /**
         * Holds the data type assigned to the generic type parameter when an instantiation of generic type is recorded.
         */
        INSTANCEDDTSYMBO, 
        /**
         * Indicates that a data type definition is for a primitive data type (stereotype of class).
         */
        ISPRIMITIVEDT, 
        /**
         * Indicates that the type for a data type component (attribute of a data type stereotype of class) is found by reference.
         */
        ISREFERENCEDT, 
        /**
         * Set true for classes that are subject classes.
         */
        ISSUBJECTCLASS, 
        /**
         * Indicates with a value of "True" whether the inclusion of an attribute in an HMD and in the messages derived from that HMD is mandatory.  The default is not mandatory, and use of mandatory inclusion in the RIM is deprecated.
         */
        MANDATORYINCLUSI, 
        /**
         * Indicates with values of "True" or "False" whether an attribute may repeat in an HMD.  The default is non-repeating.
         */
        MAYREPEAT, 
        /**
         * Used in a subject area category that holds a MIM.  It provides the unique identifier for the MIM.  The first portion of this identifier should be the ModelID of the RIM from which the MIM is derived.
         */
        MIMID, 
        /**
         * A text version of the last modified date formatted like "19970606"
         */
        MODELDATE, 
        /**
         * Contains the textual description of the model.
         */
        MODELDESCRIPTION, 
        /**
         * Holds the unique identifier assigned to this model.
         */
        MODELID, 
        /**
         * Holds the formal name for the model
         */
        MODELNAME, 
        /**
         * A text version of the version number like "V 30-08"
         */
        MODELVERSION, 
        /**
         * The identifier of the message structure within the HMD (above) that defines the message transferred by an interaction.
         */
        MSGID, 
        /**
         * This is the organization defining the model, "Health_Level_Seven"
         */
        ORGANIZATION, 
        /**
         * This property holds the identifier of the follow-on interaction, when the receiving application role for an interaction has the responsibility to initiate a follow-on interaction.
         */
        RCVRESP, 
        /**
         * Captures the identifier of the responsible committee for all subject areas and categories.
         */
        RESPCOMMID, 
        /**
         * (See description of EndState above).
         */
        STARTSTATE, 
        /**
         * For classes that are subject classes, this component provides the name of the state attribute for the class.  Only one state attribute component may appear for a given class.
         */
        STATEATTRIBUTE, 
        /**
         * (See description of EndState above.)
         */
        STATETRANSITION, 
        /**
         * This component can document the Version 2.3 datatype for an attribute that is related to or derived from data fields in HL7 Version 2.3.
         */
        V23DATATYPE, 
        /**
         * This component provides a reference to the source Version 2.x field for an attribute that is related to or derived from data fields in HL7 Version 2.3 standard.  Concatenate multiple values with commas, if multiple references to Version 2.x exist for an attribute.
         */
        V23FIELDS, 
        /**
         * Captures the identifier (name) of the vocabulary domain that constrains the values of coded attributes.  This property is captured both for RIM attributes and in MIMs.
         */
        VOCABDOMAIN, 
        /**
         * Captures the strength of encoding for the elements of a coded attribute. This property is captured both for RIM attributes and in MIMs.
         */
        VOCABSTRENGTH, 
        /**
         * This component tracks the version history of each element of the model.  It contains the unique element identifier assigned to each model element.  The repository assigns values for this element.  Modelers should not change these values or assign new ones, but they may copy them to indicate an element's historic predecessor.
         */
        ZHXID, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3HL7DefinedRoseProperty fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ApplicationRoleI".equals(codeString))
          return APPLICATIONROLEI;
        if ("Cardinality".equals(codeString))
          return CARDINALITY;
        if ("DeleteFromMIM".equals(codeString))
          return DELETEFROMMIM;
        if ("DevelopingCommit".equals(codeString))
          return DEVELOPINGCOMMIT;
        if ("DTsymbol".equals(codeString))
          return DTSYMBOL;
        if ("EndState".equals(codeString))
          return ENDSTATE;
        if ("HMD".equals(codeString))
          return HMD;
        if ("ID".equals(codeString))
          return ID;
        if ("InstancedDTsymbo".equals(codeString))
          return INSTANCEDDTSYMBO;
        if ("IsPrimitiveDT".equals(codeString))
          return ISPRIMITIVEDT;
        if ("IsReferenceDT".equals(codeString))
          return ISREFERENCEDT;
        if ("IsSubjectClass".equals(codeString))
          return ISSUBJECTCLASS;
        if ("MandatoryInclusi".equals(codeString))
          return MANDATORYINCLUSI;
        if ("MayRepeat".equals(codeString))
          return MAYREPEAT;
        if ("MIM_id".equals(codeString))
          return MIMID;
        if ("ModelDate".equals(codeString))
          return MODELDATE;
        if ("ModelDescription".equals(codeString))
          return MODELDESCRIPTION;
        if ("ModelID".equals(codeString))
          return MODELID;
        if ("ModelName".equals(codeString))
          return MODELNAME;
        if ("ModelVersion".equals(codeString))
          return MODELVERSION;
        if ("MsgID".equals(codeString))
          return MSGID;
        if ("Organization".equals(codeString))
          return ORGANIZATION;
        if ("RcvResp".equals(codeString))
          return RCVRESP;
        if ("RespComm_id".equals(codeString))
          return RESPCOMMID;
        if ("StartState".equals(codeString))
          return STARTSTATE;
        if ("StateAttribute".equals(codeString))
          return STATEATTRIBUTE;
        if ("StateTransition".equals(codeString))
          return STATETRANSITION;
        if ("V23_Datatype".equals(codeString))
          return V23DATATYPE;
        if ("V23_Fields".equals(codeString))
          return V23FIELDS;
        if ("Vocab_domain".equals(codeString))
          return VOCABDOMAIN;
        if ("Vocab_strength".equals(codeString))
          return VOCABSTRENGTH;
        if ("zhxID".equals(codeString))
          return ZHXID;
        throw new Exception("Unknown V3HL7DefinedRoseProperty code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case APPLICATIONROLEI: return "ApplicationRoleI";
            case CARDINALITY: return "Cardinality";
            case DELETEFROMMIM: return "DeleteFromMIM";
            case DEVELOPINGCOMMIT: return "DevelopingCommit";
            case DTSYMBOL: return "DTsymbol";
            case ENDSTATE: return "EndState";
            case HMD: return "HMD";
            case ID: return "ID";
            case INSTANCEDDTSYMBO: return "InstancedDTsymbo";
            case ISPRIMITIVEDT: return "IsPrimitiveDT";
            case ISREFERENCEDT: return "IsReferenceDT";
            case ISSUBJECTCLASS: return "IsSubjectClass";
            case MANDATORYINCLUSI: return "MandatoryInclusi";
            case MAYREPEAT: return "MayRepeat";
            case MIMID: return "MIM_id";
            case MODELDATE: return "ModelDate";
            case MODELDESCRIPTION: return "ModelDescription";
            case MODELID: return "ModelID";
            case MODELNAME: return "ModelName";
            case MODELVERSION: return "ModelVersion";
            case MSGID: return "MsgID";
            case ORGANIZATION: return "Organization";
            case RCVRESP: return "RcvResp";
            case RESPCOMMID: return "RespComm_id";
            case STARTSTATE: return "StartState";
            case STATEATTRIBUTE: return "StateAttribute";
            case STATETRANSITION: return "StateTransition";
            case V23DATATYPE: return "V23_Datatype";
            case V23FIELDS: return "V23_Fields";
            case VOCABDOMAIN: return "Vocab_domain";
            case VOCABSTRENGTH: return "Vocab_strength";
            case ZHXID: return "zhxID";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/HL7DefinedRoseProperty";
        }
        public String getDefinition() {
          switch (this) {
            case APPLICATIONROLEI: return "Holds the unique identifier of an Application Role (stereotype of class).";
            case CARDINALITY: return "Records the constraints on the cardinality of attributes as documented in the RIM and in MIMs.";
            case DELETEFROMMIM: return "Used in constructing a Message Information Model to indicate which attributes to omit from the MIM.";
            case DEVELOPINGCOMMIT: return "Holds the id of the HL7 committee developing the model like 'C00.'";
            case DTSYMBOL: return "Holds the symbol for a defined data type (stereotype of class).";
            case ENDSTATE: return "The HL7 MDF says that leaf level use cases should be connected to a state transition of the subject class.  The use case diagram provides the link to the subject class.  The modeler must use three properties to identify the state transition -- this property and the two following ones named StartState and StateTransition.  The first two are, as their names imply, the names of the starting state and ending state of the transition, and the third is the name of the transition.";
            case HMD: return "The identifier of the HMD from which the message structure for the message transferred by an interaction is drawn.";
            case ID: return "Holds the unique identifier of use cases, interactions and storyboards.";
            case INSTANCEDDTSYMBO: return "Holds the data type assigned to the generic type parameter when an instantiation of generic type is recorded.";
            case ISPRIMITIVEDT: return "Indicates that a data type definition is for a primitive data type (stereotype of class).";
            case ISREFERENCEDT: return "Indicates that the type for a data type component (attribute of a data type stereotype of class) is found by reference.";
            case ISSUBJECTCLASS: return "Set true for classes that are subject classes.";
            case MANDATORYINCLUSI: return "Indicates with a value of 'True' whether the inclusion of an attribute in an HMD and in the messages derived from that HMD is mandatory.  The default is not mandatory, and use of mandatory inclusion in the RIM is deprecated.";
            case MAYREPEAT: return "Indicates with values of 'True' or 'False' whether an attribute may repeat in an HMD.  The default is non-repeating.";
            case MIMID: return "Used in a subject area category that holds a MIM.  It provides the unique identifier for the MIM.  The first portion of this identifier should be the ModelID of the RIM from which the MIM is derived.";
            case MODELDATE: return "A text version of the last modified date formatted like '19970606'";
            case MODELDESCRIPTION: return "Contains the textual description of the model.";
            case MODELID: return "Holds the unique identifier assigned to this model.";
            case MODELNAME: return "Holds the formal name for the model";
            case MODELVERSION: return "A text version of the version number like 'V 30-08'";
            case MSGID: return "The identifier of the message structure within the HMD (above) that defines the message transferred by an interaction.";
            case ORGANIZATION: return "This is the organization defining the model, 'Health_Level_Seven'";
            case RCVRESP: return "This property holds the identifier of the follow-on interaction, when the receiving application role for an interaction has the responsibility to initiate a follow-on interaction.";
            case RESPCOMMID: return "Captures the identifier of the responsible committee for all subject areas and categories.";
            case STARTSTATE: return "(See description of EndState above).";
            case STATEATTRIBUTE: return "For classes that are subject classes, this component provides the name of the state attribute for the class.  Only one state attribute component may appear for a given class.";
            case STATETRANSITION: return "(See description of EndState above.)";
            case V23DATATYPE: return "This component can document the Version 2.3 datatype for an attribute that is related to or derived from data fields in HL7 Version 2.3.";
            case V23FIELDS: return "This component provides a reference to the source Version 2.x field for an attribute that is related to or derived from data fields in HL7 Version 2.3 standard.  Concatenate multiple values with commas, if multiple references to Version 2.x exist for an attribute.";
            case VOCABDOMAIN: return "Captures the identifier (name) of the vocabulary domain that constrains the values of coded attributes.  This property is captured both for RIM attributes and in MIMs.";
            case VOCABSTRENGTH: return "Captures the strength of encoding for the elements of a coded attribute. This property is captured both for RIM attributes and in MIMs.";
            case ZHXID: return "This component tracks the version history of each element of the model.  It contains the unique element identifier assigned to each model element.  The repository assigns values for this element.  Modelers should not change these values or assign new ones, but they may copy them to indicate an element's historic predecessor.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case APPLICATIONROLEI: return "Application Role";
            case CARDINALITY: return "Attribute Cardinality";
            case DELETEFROMMIM: return "MIM Delete";
            case DEVELOPINGCOMMIT: return "Developing committee";
            case DTSYMBOL: return "Data type symbol";
            case ENDSTATE: return "End state";
            case HMD: return "HMD identifier";
            case ID: return "Identifier";
            case INSTANCEDDTSYMBO: return "Data type instantiated";
            case ISPRIMITIVEDT: return "Primitive data type";
            case ISREFERENCEDT: return "Reference data type";
            case ISSUBJECTCLASS: return "Subject class";
            case MANDATORYINCLUSI: return "Mandatory";
            case MAYREPEAT: return "Attribute may repeat";
            case MIMID: return "MIM Identifier";
            case MODELDATE: return "Model date";
            case MODELDESCRIPTION: return "Model description";
            case MODELID: return "Model identifier";
            case MODELNAME: return "Model name";
            case MODELVERSION: return "Model version";
            case MSGID: return "Message type identifier";
            case ORGANIZATION: return "Developing organization";
            case RCVRESP: return "Receiver responsibility";
            case RESPCOMMID: return "Responsible committee ID";
            case STARTSTATE: return "Start state";
            case STATEATTRIBUTE: return "State attribute";
            case STATETRANSITION: return "State transition";
            case V23DATATYPE: return "Version 2 data type";
            case V23FIELDS: return "Version 2 Field reference";
            case VOCABDOMAIN: return "Vocabulary domain";
            case VOCABSTRENGTH: return "Vocabulary strength";
            case ZHXID: return "History identifier";
            default: return "?";
          }
    }


}

