package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3MdfRmimRowTypeEnumFactory implements EnumFactory<V3MdfRmimRowType> {

  public V3MdfRmimRowType fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("assoc".equals(codeString))
      return V3MdfRmimRowType.ASSOC;
    if ("attr".equals(codeString))
      return V3MdfRmimRowType.ATTR;
    if ("class".equals(codeString))
      return V3MdfRmimRowType.CLASS;
    if ("rmim".equals(codeString))
      return V3MdfRmimRowType.RMIM;
    if ("stc".equals(codeString))
      return V3MdfRmimRowType.STC;
    throw new IllegalArgumentException("Unknown V3MdfRmimRowType code '"+codeString+"'");
  }

  public String toCode(V3MdfRmimRowType code) {
    if (code == V3MdfRmimRowType.ASSOC)
      return "assoc";
    if (code == V3MdfRmimRowType.ATTR)
      return "attr";
    if (code == V3MdfRmimRowType.CLASS)
      return "class";
    if (code == V3MdfRmimRowType.RMIM)
      return "rmim";
    if (code == V3MdfRmimRowType.STC)
      return "stc";
    return "?";
  }


}

