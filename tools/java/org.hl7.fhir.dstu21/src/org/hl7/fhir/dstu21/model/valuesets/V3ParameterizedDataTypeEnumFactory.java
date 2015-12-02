package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3ParameterizedDataTypeEnumFactory implements EnumFactory<V3ParameterizedDataType> {

  public V3ParameterizedDataType fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("BAG<T>".equals(codeString))
      return V3ParameterizedDataType.BAG_T_;
    if ("LIST<T>".equals(codeString))
      return V3ParameterizedDataType.LIST_T_;
    if ("SET<T>".equals(codeString))
      return V3ParameterizedDataType.SET_T_;
    if ("EIVL<T>".equals(codeString))
      return V3ParameterizedDataType.EIVL_T_;
    if ("IVL<T>".equals(codeString))
      return V3ParameterizedDataType.IVL_T_;
    if ("PIVL<T>".equals(codeString))
      return V3ParameterizedDataType.PIVL_T_;
    if ("T".equals(codeString))
      return V3ParameterizedDataType.T;
    if ("ANT<T>".equals(codeString))
      return V3ParameterizedDataType.ANT_T_;
    if ("HXIT<T>".equals(codeString))
      return V3ParameterizedDataType.HXIT_T_;
    if ("NPPD<T>".equals(codeString))
      return V3ParameterizedDataType.NPPD_T_;
    if ("PPD<T>".equals(codeString))
      return V3ParameterizedDataType.PPD_T_;
    if ("UVN<T>".equals(codeString))
      return V3ParameterizedDataType.UVN_T_;
    if ("UVP<T>".equals(codeString))
      return V3ParameterizedDataType.UVP_T_;
    throw new IllegalArgumentException("Unknown V3ParameterizedDataType code '"+codeString+"'");
  }

  public String toCode(V3ParameterizedDataType code) {
    if (code == V3ParameterizedDataType.BAG_T_)
      return "BAG<T>";
    if (code == V3ParameterizedDataType.LIST_T_)
      return "LIST<T>";
    if (code == V3ParameterizedDataType.SET_T_)
      return "SET<T>";
    if (code == V3ParameterizedDataType.EIVL_T_)
      return "EIVL<T>";
    if (code == V3ParameterizedDataType.IVL_T_)
      return "IVL<T>";
    if (code == V3ParameterizedDataType.PIVL_T_)
      return "PIVL<T>";
    if (code == V3ParameterizedDataType.T)
      return "T";
    if (code == V3ParameterizedDataType.ANT_T_)
      return "ANT<T>";
    if (code == V3ParameterizedDataType.HXIT_T_)
      return "HXIT<T>";
    if (code == V3ParameterizedDataType.NPPD_T_)
      return "NPPD<T>";
    if (code == V3ParameterizedDataType.PPD_T_)
      return "PPD<T>";
    if (code == V3ParameterizedDataType.UVN_T_)
      return "UVN<T>";
    if (code == V3ParameterizedDataType.UVP_T_)
      return "UVP<T>";
    return "?";
  }


}

