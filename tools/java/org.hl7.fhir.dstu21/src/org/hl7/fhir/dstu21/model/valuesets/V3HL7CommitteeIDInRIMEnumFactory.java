package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3HL7CommitteeIDInRIMEnumFactory implements EnumFactory<V3HL7CommitteeIDInRIM> {

  public V3HL7CommitteeIDInRIM fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("C00".equals(codeString))
      return V3HL7CommitteeIDInRIM.C00;
    if ("C01".equals(codeString))
      return V3HL7CommitteeIDInRIM.C01;
    if ("C02".equals(codeString))
      return V3HL7CommitteeIDInRIM.C02;
    if ("C03".equals(codeString))
      return V3HL7CommitteeIDInRIM.C03;
    if ("C04".equals(codeString))
      return V3HL7CommitteeIDInRIM.C04;
    if ("C06".equals(codeString))
      return V3HL7CommitteeIDInRIM.C06;
    if ("C09".equals(codeString))
      return V3HL7CommitteeIDInRIM.C09;
    if ("C10".equals(codeString))
      return V3HL7CommitteeIDInRIM.C10;
    if ("C12".equals(codeString))
      return V3HL7CommitteeIDInRIM.C12;
    if ("C20".equals(codeString))
      return V3HL7CommitteeIDInRIM.C20;
    if ("C21".equals(codeString))
      return V3HL7CommitteeIDInRIM.C21;
    throw new IllegalArgumentException("Unknown V3HL7CommitteeIDInRIM code '"+codeString+"'");
  }

  public String toCode(V3HL7CommitteeIDInRIM code) {
    if (code == V3HL7CommitteeIDInRIM.C00)
      return "C00";
    if (code == V3HL7CommitteeIDInRIM.C01)
      return "C01";
    if (code == V3HL7CommitteeIDInRIM.C02)
      return "C02";
    if (code == V3HL7CommitteeIDInRIM.C03)
      return "C03";
    if (code == V3HL7CommitteeIDInRIM.C04)
      return "C04";
    if (code == V3HL7CommitteeIDInRIM.C06)
      return "C06";
    if (code == V3HL7CommitteeIDInRIM.C09)
      return "C09";
    if (code == V3HL7CommitteeIDInRIM.C10)
      return "C10";
    if (code == V3HL7CommitteeIDInRIM.C12)
      return "C12";
    if (code == V3HL7CommitteeIDInRIM.C20)
      return "C20";
    if (code == V3HL7CommitteeIDInRIM.C21)
      return "C21";
    return "?";
  }


}

