package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3CurrencyEnumFactory implements EnumFactory<V3Currency> {

  public V3Currency fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("ARS".equals(codeString))
      return V3Currency.ARS;
    if ("AUD".equals(codeString))
      return V3Currency.AUD;
    if ("BRL".equals(codeString))
      return V3Currency.BRL;
    if ("CAD".equals(codeString))
      return V3Currency.CAD;
    if ("CHF".equals(codeString))
      return V3Currency.CHF;
    if ("CLF".equals(codeString))
      return V3Currency.CLF;
    if ("CNY".equals(codeString))
      return V3Currency.CNY;
    if ("DEM".equals(codeString))
      return V3Currency.DEM;
    if ("ESP".equals(codeString))
      return V3Currency.ESP;
    if ("EUR".equals(codeString))
      return V3Currency.EUR;
    if ("FIM".equals(codeString))
      return V3Currency.FIM;
    if ("FRF".equals(codeString))
      return V3Currency.FRF;
    if ("GBP".equals(codeString))
      return V3Currency.GBP;
    if ("ILS".equals(codeString))
      return V3Currency.ILS;
    if ("INR".equals(codeString))
      return V3Currency.INR;
    if ("JPY".equals(codeString))
      return V3Currency.JPY;
    if ("KRW".equals(codeString))
      return V3Currency.KRW;
    if ("MXN".equals(codeString))
      return V3Currency.MXN;
    if ("NLG".equals(codeString))
      return V3Currency.NLG;
    if ("NZD".equals(codeString))
      return V3Currency.NZD;
    if ("PHP".equals(codeString))
      return V3Currency.PHP;
    if ("RUR".equals(codeString))
      return V3Currency.RUR;
    if ("THB".equals(codeString))
      return V3Currency.THB;
    if ("TRL".equals(codeString))
      return V3Currency.TRL;
    if ("TWD".equals(codeString))
      return V3Currency.TWD;
    if ("USD".equals(codeString))
      return V3Currency.USD;
    if ("ZAR".equals(codeString))
      return V3Currency.ZAR;
    throw new IllegalArgumentException("Unknown V3Currency code '"+codeString+"'");
  }

  public String toCode(V3Currency code) {
    if (code == V3Currency.ARS)
      return "ARS";
    if (code == V3Currency.AUD)
      return "AUD";
    if (code == V3Currency.BRL)
      return "BRL";
    if (code == V3Currency.CAD)
      return "CAD";
    if (code == V3Currency.CHF)
      return "CHF";
    if (code == V3Currency.CLF)
      return "CLF";
    if (code == V3Currency.CNY)
      return "CNY";
    if (code == V3Currency.DEM)
      return "DEM";
    if (code == V3Currency.ESP)
      return "ESP";
    if (code == V3Currency.EUR)
      return "EUR";
    if (code == V3Currency.FIM)
      return "FIM";
    if (code == V3Currency.FRF)
      return "FRF";
    if (code == V3Currency.GBP)
      return "GBP";
    if (code == V3Currency.ILS)
      return "ILS";
    if (code == V3Currency.INR)
      return "INR";
    if (code == V3Currency.JPY)
      return "JPY";
    if (code == V3Currency.KRW)
      return "KRW";
    if (code == V3Currency.MXN)
      return "MXN";
    if (code == V3Currency.NLG)
      return "NLG";
    if (code == V3Currency.NZD)
      return "NZD";
    if (code == V3Currency.PHP)
      return "PHP";
    if (code == V3Currency.RUR)
      return "RUR";
    if (code == V3Currency.THB)
      return "THB";
    if (code == V3Currency.TRL)
      return "TRL";
    if (code == V3Currency.TWD)
      return "TWD";
    if (code == V3Currency.USD)
      return "USD";
    if (code == V3Currency.ZAR)
      return "ZAR";
    return "?";
  }


}

