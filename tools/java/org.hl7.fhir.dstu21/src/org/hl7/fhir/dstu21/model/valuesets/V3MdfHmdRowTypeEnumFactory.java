package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3MdfHmdRowTypeEnumFactory implements EnumFactory<V3MdfHmdRowType> {

  public V3MdfHmdRowType fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("assoc".equals(codeString))
      return V3MdfHmdRowType.ASSOC;
    if ("attr".equals(codeString))
      return V3MdfHmdRowType.ATTR;
    if ("class".equals(codeString))
      return V3MdfHmdRowType.CLASS;
    if ("hmd".equals(codeString))
      return V3MdfHmdRowType.HMD;
    if ("item".equals(codeString))
      return V3MdfHmdRowType.ITEM;
    if ("stc".equals(codeString))
      return V3MdfHmdRowType.STC;
    throw new IllegalArgumentException("Unknown V3MdfHmdRowType code '"+codeString+"'");
  }

  public String toCode(V3MdfHmdRowType code) {
    if (code == V3MdfHmdRowType.ASSOC)
      return "assoc";
    if (code == V3MdfHmdRowType.ATTR)
      return "attr";
    if (code == V3MdfHmdRowType.CLASS)
      return "class";
    if (code == V3MdfHmdRowType.HMD)
      return "hmd";
    if (code == V3MdfHmdRowType.ITEM)
      return "item";
    if (code == V3MdfHmdRowType.STC)
      return "stc";
    return "?";
  }


}

