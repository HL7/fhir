package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class ModifiersEnumFactory implements EnumFactory<Modifiers> {

  public Modifiers fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("A".equals(codeString))
      return Modifiers.A;
    if ("B".equals(codeString))
      return Modifiers.B;
    if ("C".equals(codeString))
      return Modifiers.C;
    if ("E".equals(codeString))
      return Modifiers.E;
    if ("X".equals(codeString))
      return Modifiers.X;
    throw new IllegalArgumentException("Unknown Modifiers code '"+codeString+"'");
  }

  public String toCode(Modifiers code) {
    if (code == Modifiers.A)
      return "A";
    if (code == Modifiers.B)
      return "B";
    if (code == Modifiers.C)
      return "C";
    if (code == Modifiers.E)
      return "E";
    if (code == Modifiers.X)
      return "X";
    return "?";
  }


}

