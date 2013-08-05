package org.hl7.fhir.instance.utils;

import org.hl7.fhir.instance.model.Code;
import org.hl7.fhir.instance.model.Element;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.String_;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.Utilities;


public class ToolingExtensions {

  public static final String EXT_COMMENT = "http://hl7.org/fhir/tools/extensions#comment";
  public static final String EXT_DISPLAY = "http://hl7.org/fhir/tools/extensions#display";
  public static final String EXT_DEFINITION = "http://hl7.org/fhir/tools/extensions#definition";
  public static final String EXT_ISSUE_SOURCE = "http://hl7.org/fhir/tools/extensions#issue-source";

  public static Extension makeIssueSource(Source source) {
    Extension ex = new Extension();
    // todo: write this up and get it published with the pack (and handle the redirect?)
    ex.setUrlSimple(ToolingExtensions.EXT_ISSUE_SOURCE);
    Code c = new Code();
    c.setValue(source.toString());
    ex.setValue(c);
    return ex;
  }

  public static void addComment(Code nc, String comment) throws Exception {
    if (!Utilities.noString(comment))
      nc.getExtensions().add(Factory.newExtension(EXT_COMMENT, Factory.newString_(comment), true));   
  }

  public static void addDefinition(Code nc, String definition) throws Exception {
    if (!Utilities.noString(definition))
      nc.getExtensions().add(Factory.newExtension(EXT_DEFINITION, Factory.newString_(definition), true));   
  }

  public static String readStringExtension(Element c, String uri) {
    Extension ex = c.getExtension(uri);
    if (ex == null)
      return null;
    if (!(ex.getValue() instanceof String_))
      return null;
    return ((String_) ex.getValue()).getValue();
  }

  public static boolean findStringExtension(Element c, String uri) {
    Extension ex = c.getExtension(uri);
    if (ex == null)
      return false;
    if (!(ex.getValue() instanceof String_))
      return false;
    return !Utilities.noString(((String_) ex.getValue()).getValue());
  }

  public static String getComment(ValueSetDefineConceptComponent c) {
    return readStringExtension(c, EXT_COMMENT);    
  }

  public static boolean hasComment(ValueSetDefineConceptComponent c) {
    return findStringExtension(c, EXT_COMMENT);    
  }

}
