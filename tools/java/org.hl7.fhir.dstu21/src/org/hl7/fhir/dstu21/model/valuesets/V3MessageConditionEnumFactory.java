package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3MessageConditionEnumFactory implements EnumFactory<V3MessageCondition> {

  public V3MessageCondition fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("0".equals(codeString))
      return V3MessageCondition._0;
    if ("100".equals(codeString))
      return V3MessageCondition._100;
    if ("101".equals(codeString))
      return V3MessageCondition._101;
    if ("102".equals(codeString))
      return V3MessageCondition._102;
    if ("103".equals(codeString))
      return V3MessageCondition._103;
    if ("200".equals(codeString))
      return V3MessageCondition._200;
    if ("201".equals(codeString))
      return V3MessageCondition._201;
    if ("202".equals(codeString))
      return V3MessageCondition._202;
    if ("203".equals(codeString))
      return V3MessageCondition._203;
    if ("204".equals(codeString))
      return V3MessageCondition._204;
    if ("205".equals(codeString))
      return V3MessageCondition._205;
    if ("206".equals(codeString))
      return V3MessageCondition._206;
    if ("207".equals(codeString))
      return V3MessageCondition._207;
    throw new IllegalArgumentException("Unknown V3MessageCondition code '"+codeString+"'");
  }

  public String toCode(V3MessageCondition code) {
    if (code == V3MessageCondition._0)
      return "0";
    if (code == V3MessageCondition._100)
      return "100";
    if (code == V3MessageCondition._101)
      return "101";
    if (code == V3MessageCondition._102)
      return "102";
    if (code == V3MessageCondition._103)
      return "103";
    if (code == V3MessageCondition._200)
      return "200";
    if (code == V3MessageCondition._201)
      return "201";
    if (code == V3MessageCondition._202)
      return "202";
    if (code == V3MessageCondition._203)
      return "203";
    if (code == V3MessageCondition._204)
      return "204";
    if (code == V3MessageCondition._205)
      return "205";
    if (code == V3MessageCondition._206)
      return "206";
    if (code == V3MessageCondition._207)
      return "207";
    return "?";
  }


}

