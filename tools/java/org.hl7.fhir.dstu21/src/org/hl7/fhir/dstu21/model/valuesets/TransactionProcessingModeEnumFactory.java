package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class TransactionProcessingModeEnumFactory implements EnumFactory<TransactionProcessingMode> {

  public TransactionProcessingMode fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("ignore".equals(codeString))
      return TransactionProcessingMode.IGNORE;
    if ("update".equals(codeString))
      return TransactionProcessingMode.UPDATE;
    if ("delete".equals(codeString))
      return TransactionProcessingMode.DELETE;
    throw new IllegalArgumentException("Unknown TransactionProcessingMode code '"+codeString+"'");
  }

  public String toCode(TransactionProcessingMode code) {
    if (code == TransactionProcessingMode.IGNORE)
      return "ignore";
    if (code == TransactionProcessingMode.UPDATE)
      return "update";
    if (code == TransactionProcessingMode.DELETE)
      return "delete";
    return "?";
  }


}

