package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class ObservationValueabsentreasonEnumFactory implements EnumFactory<ObservationValueabsentreason> {

  public ObservationValueabsentreason fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("unknown".equals(codeString))
      return ObservationValueabsentreason.UNKNOWN;
    if ("asked".equals(codeString))
      return ObservationValueabsentreason.ASKED;
    if ("temp".equals(codeString))
      return ObservationValueabsentreason.TEMP;
    if ("notasked".equals(codeString))
      return ObservationValueabsentreason.NOTASKED;
    if ("masked".equals(codeString))
      return ObservationValueabsentreason.MASKED;
    if ("unsupported".equals(codeString))
      return ObservationValueabsentreason.UNSUPPORTED;
    if ("astext".equals(codeString))
      return ObservationValueabsentreason.ASTEXT;
    if ("error".equals(codeString))
      return ObservationValueabsentreason.ERROR;
    if ("NaN".equals(codeString))
      return ObservationValueabsentreason.NAN;
    throw new IllegalArgumentException("Unknown ObservationValueabsentreason code '"+codeString+"'");
  }

  public String toCode(ObservationValueabsentreason code) {
    if (code == ObservationValueabsentreason.UNKNOWN)
      return "unknown";
    if (code == ObservationValueabsentreason.ASKED)
      return "asked";
    if (code == ObservationValueabsentreason.TEMP)
      return "temp";
    if (code == ObservationValueabsentreason.NOTASKED)
      return "notasked";
    if (code == ObservationValueabsentreason.MASKED)
      return "masked";
    if (code == ObservationValueabsentreason.UNSUPPORTED)
      return "unsupported";
    if (code == ObservationValueabsentreason.ASTEXT)
      return "astext";
    if (code == ObservationValueabsentreason.ERROR)
      return "error";
    if (code == ObservationValueabsentreason.NAN)
      return "NaN";
    return "?";
  }


}

