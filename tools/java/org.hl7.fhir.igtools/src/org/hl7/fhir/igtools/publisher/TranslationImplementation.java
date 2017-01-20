package org.hl7.fhir.igtools.publisher;

import java.util.Date;

import org.hl7.fhir.dstu3.model.Enumeration;
import org.hl7.fhir.dstu3.model.PrimitiveType;
import org.hl7.fhir.dstu3.utils.TranslatingUtilities.TranslationServices;

public class TranslationImplementation implements TranslationServices {

  // marker is used to see what content is not being translated (debugging)
  private static final String MARKER1 = ""; // "^";
  private static final String MARKER2 = ""; // "^^";
  
  // -- configuration -------------------------------------------------------
  
  private String lang; 
  
  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  // -- services -------------------------------------------------------
  
  public String translate(String context, String string) {
    return string == null ? null : MARKER2+string+MARKER2;
  }

  public String translate(String context, String string, Object... args) {
    return MARKER2+String.format(string, args)+MARKER2;
  }

  public String gt(@SuppressWarnings("rawtypes") PrimitiveType md) {
    String sd = md.asStringValue();
    return sd == null ? null : MARKER1+sd+MARKER1;
  }

  public String toStr(int v) {
    return Integer.toString(v);
  }

  public String toStr(Date date) {
    return date.toString();
  }

  public String egt(@SuppressWarnings("rawtypes") Enumeration<? extends Enum> enumeration) {
    return MARKER1+enumeration.primitiveValue()+MARKER1;
  }


}
