package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3HL7DefinedRosePropertyEnumFactory implements EnumFactory<V3HL7DefinedRoseProperty> {

  public V3HL7DefinedRoseProperty fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("ApplicationRoleI".equals(codeString))
      return V3HL7DefinedRoseProperty.APPLICATIONROLEI;
    if ("Cardinality".equals(codeString))
      return V3HL7DefinedRoseProperty.CARDINALITY;
    if ("DeleteFromMIM".equals(codeString))
      return V3HL7DefinedRoseProperty.DELETEFROMMIM;
    if ("DevelopingCommit".equals(codeString))
      return V3HL7DefinedRoseProperty.DEVELOPINGCOMMIT;
    if ("DTsymbol".equals(codeString))
      return V3HL7DefinedRoseProperty.DTSYMBOL;
    if ("EndState".equals(codeString))
      return V3HL7DefinedRoseProperty.ENDSTATE;
    if ("HMD".equals(codeString))
      return V3HL7DefinedRoseProperty.HMD;
    if ("ID".equals(codeString))
      return V3HL7DefinedRoseProperty.ID;
    if ("InstancedDTsymbo".equals(codeString))
      return V3HL7DefinedRoseProperty.INSTANCEDDTSYMBO;
    if ("IsPrimitiveDT".equals(codeString))
      return V3HL7DefinedRoseProperty.ISPRIMITIVEDT;
    if ("IsReferenceDT".equals(codeString))
      return V3HL7DefinedRoseProperty.ISREFERENCEDT;
    if ("IsSubjectClass".equals(codeString))
      return V3HL7DefinedRoseProperty.ISSUBJECTCLASS;
    if ("MandatoryInclusi".equals(codeString))
      return V3HL7DefinedRoseProperty.MANDATORYINCLUSI;
    if ("MayRepeat".equals(codeString))
      return V3HL7DefinedRoseProperty.MAYREPEAT;
    if ("MIM_id".equals(codeString))
      return V3HL7DefinedRoseProperty.MIMID;
    if ("ModelDate".equals(codeString))
      return V3HL7DefinedRoseProperty.MODELDATE;
    if ("ModelDescription".equals(codeString))
      return V3HL7DefinedRoseProperty.MODELDESCRIPTION;
    if ("ModelID".equals(codeString))
      return V3HL7DefinedRoseProperty.MODELID;
    if ("ModelName".equals(codeString))
      return V3HL7DefinedRoseProperty.MODELNAME;
    if ("ModelVersion".equals(codeString))
      return V3HL7DefinedRoseProperty.MODELVERSION;
    if ("MsgID".equals(codeString))
      return V3HL7DefinedRoseProperty.MSGID;
    if ("Organization".equals(codeString))
      return V3HL7DefinedRoseProperty.ORGANIZATION;
    if ("RcvResp".equals(codeString))
      return V3HL7DefinedRoseProperty.RCVRESP;
    if ("RespComm_id".equals(codeString))
      return V3HL7DefinedRoseProperty.RESPCOMMID;
    if ("StartState".equals(codeString))
      return V3HL7DefinedRoseProperty.STARTSTATE;
    if ("StateAttribute".equals(codeString))
      return V3HL7DefinedRoseProperty.STATEATTRIBUTE;
    if ("StateTransition".equals(codeString))
      return V3HL7DefinedRoseProperty.STATETRANSITION;
    if ("V23_Datatype".equals(codeString))
      return V3HL7DefinedRoseProperty.V23DATATYPE;
    if ("V23_Fields".equals(codeString))
      return V3HL7DefinedRoseProperty.V23FIELDS;
    if ("Vocab_domain".equals(codeString))
      return V3HL7DefinedRoseProperty.VOCABDOMAIN;
    if ("Vocab_strength".equals(codeString))
      return V3HL7DefinedRoseProperty.VOCABSTRENGTH;
    if ("zhxID".equals(codeString))
      return V3HL7DefinedRoseProperty.ZHXID;
    throw new IllegalArgumentException("Unknown V3HL7DefinedRoseProperty code '"+codeString+"'");
  }

  public String toCode(V3HL7DefinedRoseProperty code) {
    if (code == V3HL7DefinedRoseProperty.APPLICATIONROLEI)
      return "ApplicationRoleI";
    if (code == V3HL7DefinedRoseProperty.CARDINALITY)
      return "Cardinality";
    if (code == V3HL7DefinedRoseProperty.DELETEFROMMIM)
      return "DeleteFromMIM";
    if (code == V3HL7DefinedRoseProperty.DEVELOPINGCOMMIT)
      return "DevelopingCommit";
    if (code == V3HL7DefinedRoseProperty.DTSYMBOL)
      return "DTsymbol";
    if (code == V3HL7DefinedRoseProperty.ENDSTATE)
      return "EndState";
    if (code == V3HL7DefinedRoseProperty.HMD)
      return "HMD";
    if (code == V3HL7DefinedRoseProperty.ID)
      return "ID";
    if (code == V3HL7DefinedRoseProperty.INSTANCEDDTSYMBO)
      return "InstancedDTsymbo";
    if (code == V3HL7DefinedRoseProperty.ISPRIMITIVEDT)
      return "IsPrimitiveDT";
    if (code == V3HL7DefinedRoseProperty.ISREFERENCEDT)
      return "IsReferenceDT";
    if (code == V3HL7DefinedRoseProperty.ISSUBJECTCLASS)
      return "IsSubjectClass";
    if (code == V3HL7DefinedRoseProperty.MANDATORYINCLUSI)
      return "MandatoryInclusi";
    if (code == V3HL7DefinedRoseProperty.MAYREPEAT)
      return "MayRepeat";
    if (code == V3HL7DefinedRoseProperty.MIMID)
      return "MIM_id";
    if (code == V3HL7DefinedRoseProperty.MODELDATE)
      return "ModelDate";
    if (code == V3HL7DefinedRoseProperty.MODELDESCRIPTION)
      return "ModelDescription";
    if (code == V3HL7DefinedRoseProperty.MODELID)
      return "ModelID";
    if (code == V3HL7DefinedRoseProperty.MODELNAME)
      return "ModelName";
    if (code == V3HL7DefinedRoseProperty.MODELVERSION)
      return "ModelVersion";
    if (code == V3HL7DefinedRoseProperty.MSGID)
      return "MsgID";
    if (code == V3HL7DefinedRoseProperty.ORGANIZATION)
      return "Organization";
    if (code == V3HL7DefinedRoseProperty.RCVRESP)
      return "RcvResp";
    if (code == V3HL7DefinedRoseProperty.RESPCOMMID)
      return "RespComm_id";
    if (code == V3HL7DefinedRoseProperty.STARTSTATE)
      return "StartState";
    if (code == V3HL7DefinedRoseProperty.STATEATTRIBUTE)
      return "StateAttribute";
    if (code == V3HL7DefinedRoseProperty.STATETRANSITION)
      return "StateTransition";
    if (code == V3HL7DefinedRoseProperty.V23DATATYPE)
      return "V23_Datatype";
    if (code == V3HL7DefinedRoseProperty.V23FIELDS)
      return "V23_Fields";
    if (code == V3HL7DefinedRoseProperty.VOCABDOMAIN)
      return "Vocab_domain";
    if (code == V3HL7DefinedRoseProperty.VOCABSTRENGTH)
      return "Vocab_strength";
    if (code == V3HL7DefinedRoseProperty.ZHXID)
      return "zhxID";
    return "?";
  }


}

