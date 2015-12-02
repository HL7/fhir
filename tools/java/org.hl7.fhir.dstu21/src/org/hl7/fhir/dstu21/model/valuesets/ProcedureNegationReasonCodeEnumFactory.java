package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class ProcedureNegationReasonCodeEnumFactory implements EnumFactory<ProcedureNegationReasonCode> {

  public ProcedureNegationReasonCode fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("a".equals(codeString))
      return ProcedureNegationReasonCode.A;
    if ("b".equals(codeString))
      return ProcedureNegationReasonCode.B;
    if ("c".equals(codeString))
      return ProcedureNegationReasonCode.C;
    if ("d".equals(codeString))
      return ProcedureNegationReasonCode.D;
    throw new IllegalArgumentException("Unknown ProcedureNegationReasonCode code '"+codeString+"'");
  }

  public String toCode(ProcedureNegationReasonCode code) {
    if (code == ProcedureNegationReasonCode.A)
      return "a";
    if (code == ProcedureNegationReasonCode.B)
      return "b";
    if (code == ProcedureNegationReasonCode.C)
      return "c";
    if (code == ProcedureNegationReasonCode.D)
      return "d";
    return "?";
  }


}

