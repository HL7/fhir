package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class Icd10EnumFactory implements EnumFactory<Icd10> {

  public Icd10 fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("123456".equals(codeString))
      return Icd10._123456;
    if ("123457".equals(codeString))
      return Icd10._123457;
    if ("987654".equals(codeString))
      return Icd10._987654;
    if ("123987".equals(codeString))
      return Icd10._123987;
    if ("112233".equals(codeString))
      return Icd10._112233;
    if ("997755".equals(codeString))
      return Icd10._997755;
    if ("321789".equals(codeString))
      return Icd10._321789;
    throw new IllegalArgumentException("Unknown Icd10 code '"+codeString+"'");
  }

  public String toCode(Icd10 code) {
    if (code == Icd10._123456)
      return "123456";
    if (code == Icd10._123457)
      return "123457";
    if (code == Icd10._987654)
      return "987654";
    if (code == Icd10._123987)
      return "123987";
    if (code == Icd10._112233)
      return "112233";
    if (code == Icd10._997755)
      return "997755";
    if (code == Icd10._321789)
      return "321789";
    return "?";
  }


}

