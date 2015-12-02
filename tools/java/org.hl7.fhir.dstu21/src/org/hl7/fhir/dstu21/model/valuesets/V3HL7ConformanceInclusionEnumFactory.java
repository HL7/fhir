package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3HL7ConformanceInclusionEnumFactory implements EnumFactory<V3HL7ConformanceInclusion> {

  public V3HL7ConformanceInclusion fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("_InclusionNotMandatory".equals(codeString))
      return V3HL7ConformanceInclusion._INCLUSIONNOTMANDATORY;
    if ("NP".equals(codeString))
      return V3HL7ConformanceInclusion.NP;
    if ("NR".equals(codeString))
      return V3HL7ConformanceInclusion.NR;
    if ("RE".equals(codeString))
      return V3HL7ConformanceInclusion.RE;
    if ("X".equals(codeString))
      return V3HL7ConformanceInclusion.X;
    if ("RQ".equals(codeString))
      return V3HL7ConformanceInclusion.RQ;
    if ("M".equals(codeString))
      return V3HL7ConformanceInclusion.M;
    throw new IllegalArgumentException("Unknown V3HL7ConformanceInclusion code '"+codeString+"'");
  }

  public String toCode(V3HL7ConformanceInclusion code) {
    if (code == V3HL7ConformanceInclusion._INCLUSIONNOTMANDATORY)
      return "_InclusionNotMandatory";
    if (code == V3HL7ConformanceInclusion.NP)
      return "NP";
    if (code == V3HL7ConformanceInclusion.NR)
      return "NR";
    if (code == V3HL7ConformanceInclusion.RE)
      return "RE";
    if (code == V3HL7ConformanceInclusion.X)
      return "X";
    if (code == V3HL7ConformanceInclusion.RQ)
      return "RQ";
    if (code == V3HL7ConformanceInclusion.M)
      return "M";
    return "?";
  }


}

