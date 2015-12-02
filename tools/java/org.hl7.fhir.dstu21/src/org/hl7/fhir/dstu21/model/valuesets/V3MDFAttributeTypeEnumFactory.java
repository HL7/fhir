package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3MDFAttributeTypeEnumFactory implements EnumFactory<V3MDFAttributeType> {

  public V3MDFAttributeType fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("ADDR".equals(codeString))
      return V3MDFAttributeType.ADDR;
    if ("CD".equals(codeString))
      return V3MDFAttributeType.CD;
    if ("COM".equals(codeString))
      return V3MDFAttributeType.COM;
    if ("DESC".equals(codeString))
      return V3MDFAttributeType.DESC;
    if ("DTTM".equals(codeString))
      return V3MDFAttributeType.DTTM;
    if ("EXPR".equals(codeString))
      return V3MDFAttributeType.EXPR;
    if ("FRC".equals(codeString))
      return V3MDFAttributeType.FRC;
    if ("ID".equals(codeString))
      return V3MDFAttributeType.ID;
    if ("IND".equals(codeString))
      return V3MDFAttributeType.IND;
    if ("NBR".equals(codeString))
      return V3MDFAttributeType.NBR;
    if ("NM".equals(codeString))
      return V3MDFAttributeType.NM;
    if ("PHON".equals(codeString))
      return V3MDFAttributeType.PHON;
    if ("QTY".equals(codeString))
      return V3MDFAttributeType.QTY;
    if ("TIME".equals(codeString))
      return V3MDFAttributeType.TIME;
    if ("TMR".equals(codeString))
      return V3MDFAttributeType.TMR;
    if ("TXT".equals(codeString))
      return V3MDFAttributeType.TXT;
    if ("VALUE".equals(codeString))
      return V3MDFAttributeType.VALUE;
    throw new IllegalArgumentException("Unknown V3MDFAttributeType code '"+codeString+"'");
  }

  public String toCode(V3MDFAttributeType code) {
    if (code == V3MDFAttributeType.ADDR)
      return "ADDR";
    if (code == V3MDFAttributeType.CD)
      return "CD";
    if (code == V3MDFAttributeType.COM)
      return "COM";
    if (code == V3MDFAttributeType.DESC)
      return "DESC";
    if (code == V3MDFAttributeType.DTTM)
      return "DTTM";
    if (code == V3MDFAttributeType.EXPR)
      return "EXPR";
    if (code == V3MDFAttributeType.FRC)
      return "FRC";
    if (code == V3MDFAttributeType.ID)
      return "ID";
    if (code == V3MDFAttributeType.IND)
      return "IND";
    if (code == V3MDFAttributeType.NBR)
      return "NBR";
    if (code == V3MDFAttributeType.NM)
      return "NM";
    if (code == V3MDFAttributeType.PHON)
      return "PHON";
    if (code == V3MDFAttributeType.QTY)
      return "QTY";
    if (code == V3MDFAttributeType.TIME)
      return "TIME";
    if (code == V3MDFAttributeType.TMR)
      return "TMR";
    if (code == V3MDFAttributeType.TXT)
      return "TXT";
    if (code == V3MDFAttributeType.VALUE)
      return "VALUE";
    return "?";
  }


}

