package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.instance.model.Boolean;
import org.hl7.fhir.instance.model.Code;
import org.hl7.fhir.instance.model.Element;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionComponent;
import org.hl7.fhir.instance.model.String_;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.Utilities;


public class ToolingExtensions {

  public static final String EXT_COMMENT = "http://hl7.org/fhir/Profile/tools-extensions#comment";
  public static final String EXT_DISPLAY = "http://hl7.org/fhir/Profile/tools-extensions#display";
  public static final String EXT_DEFINITION = "http://hl7.org/fhir/Profile/tools-extensions#definition";
  public static final String EXT_DEPRECATED = "http://hl7.org/fhir/Profile/tools-extensions#deprecated";
  public static final String EXT_ISSUE_SOURCE = "http://hl7.org/fhir/Profile/tools-extensions#issue-source";
  public static final String EXT_SUBSUMES = "http://hl7.org/fhir/Profile/tools-extensions#subsumes";
  public static final String EXT_DISPLAY_HINT = "http://hl7.org/fhir/Profile/tools-extensions#display-hint";

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

  public static void markDeprecated(Element nc) throws Exception {
    nc.getExtensions().add(Factory.newExtension(EXT_DEPRECATED, Factory.newBoolean(true), true));   
  }

  public static void addSubsumes(ValueSetDefineConceptComponent nc, String code) throws Exception {
    nc.getModifierExtensions().add(Factory.newExtension(EXT_SUBSUMES, Factory.newCode(code), true));   
  }

  public static void addDefinition(Code nc, String definition) throws Exception {
    if (!Utilities.noString(definition))
      nc.getExtensions().add(Factory.newExtension(EXT_DEFINITION, Factory.newString_(definition), true));   
  }

  public static void addDisplayHint(ElementDefinitionComponent def, String hint) throws Exception {
    if (!Utilities.noString(hint))
      def.getExtensions().add(Factory.newExtension(EXT_DISPLAY_HINT, Factory.newString_(hint), true));   
  }

  public static String getDisplayHint(ElementDefinitionComponent def) throws Exception {
    return readStringExtension(def, EXT_DISPLAY_HINT);    
  }

  public static void addDisplay(Element def, String hint) throws Exception {
    if (!Utilities.noString(hint))
      def.getExtensions().add(Factory.newExtension(EXT_DISPLAY, Factory.newString_(hint), true));   
  }

  public static String getDisplay(Element def) throws Exception {
    return readStringExtension(def, EXT_DISPLAY);    
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

  public static String readBooleanExtension(Element c, String uri) {
    Extension ex = c.getExtension(uri);
    if (ex == null)
      return null;
    if (!(ex.getValue() instanceof Boolean))
      return null;
    return java.lang.Boolean.toString(((Boolean) ex.getValue()).getValue());
  }

  public static boolean findBooleanExtension(Element c, String uri) {
    Extension ex = c.getExtension(uri);
    if (ex == null)
      return false;
    if (!(ex.getValue() instanceof Boolean))
      return false;
    return true;
  }

  public static String getComment(ValueSetDefineConceptComponent c) {
    return readStringExtension(c, EXT_COMMENT);    
  }

  public static String getDeprecated(ValueSetDefineConceptComponent c) {
    return readBooleanExtension(c, EXT_DEPRECATED);    
  }

  public static boolean hasComment(ValueSetDefineConceptComponent c) {
    return findStringExtension(c, EXT_COMMENT);    
  }

  public static boolean hasDeprecated(ValueSetDefineConceptComponent c) {
    return findBooleanExtension(c, EXT_DEPRECATED);    
  }

  public static List<Code> getSubsumes(ValueSetDefineConceptComponent c) {
    List<Code> res = new ArrayList<Code>();

    for (Extension e : c.getExtensions()) {
      if (EXT_SUBSUMES.equals(e.getUrlSimple()))
        res.add((Code) e.getValue());
    }
    return res;
  }

}
