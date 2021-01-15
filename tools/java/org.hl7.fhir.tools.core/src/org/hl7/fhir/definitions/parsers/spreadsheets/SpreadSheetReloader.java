package org.hl7.fhir.definitions.parsers.spreadsheets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xerces.dom3.as.DOMImplementationAS;
import org.hl7.fhir.definitions.BuildExtensions;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.definitions.parsers.TypeParser;
import org.hl7.fhir.r5.model.Extension;
import org.hl7.fhir.r5.model.ImplementationGuide;
import org.hl7.fhir.r5.model.IntegerType;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.r5.context.BaseWorkerContext;
import org.hl7.fhir.r5.model.BooleanType;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r5.model.CanonicalType;
import org.hl7.fhir.r5.model.CodeType;
import org.hl7.fhir.r5.model.DomainResource;
import org.hl7.fhir.r5.model.Element;
import org.hl7.fhir.r5.model.ElementDefinition;
import org.hl7.fhir.r5.model.ElementDefinition.ConstraintSeverity;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.r5.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.r5.model.Enumerations.BindingStrength;
import org.hl7.fhir.r5.model.Enumerations.FHIRAllTypes;
import org.hl7.fhir.r5.model.Enumerations.OperationParameterUse;
import org.hl7.fhir.r5.model.Enumerations.PublicationStatus;
import org.hl7.fhir.r5.model.Enumerations.SearchParamType;
import org.hl7.fhir.r5.model.ImplementationGuide.ImplementationGuideDefinitionResourceComponent;
import org.hl7.fhir.r5.model.ListResource;
import org.hl7.fhir.r5.model.ListResource.ListResourceEntryComponent;
import org.hl7.fhir.r5.model.OperationDefinition.OperationDefinitionParameterBindingComponent;
import org.hl7.fhir.r5.model.OperationDefinition.OperationDefinitionParameterComponent;
import org.hl7.fhir.r5.model.OperationDefinition.OperationKind;
import org.hl7.fhir.r5.model.MarkdownType;
import org.hl7.fhir.r5.model.OperationDefinition;
import org.hl7.fhir.r5.model.ResourceFactory;
import org.hl7.fhir.r5.model.SearchParameter;
import org.hl7.fhir.r5.model.StringType;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.UriType;
import org.hl7.fhir.r5.utils.ToolingExtensions;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.json.JsonTrackingParser;

import com.google.gson.JsonObject;

public class SpreadSheetReloader extends SpreadSheetBase {


  public enum ExtensionType {
    Code, String, Boolean, Markdown, Uri, Integer
  }

  private String xlsx;

  public SpreadSheetReloader(BaseWorkerContext context, String srcFolder, String resourceName) {
    super(context, srcFolder, resourceName);
  }

  protected boolean useLoadingDates() {
    return false;
  }

  public void process() throws Exception {
    xlsx = fnSpreadsheet();
    IniFile ini = new IniFile(Utilities.changeFileExt(xlsx, ".datestamp"));
    File t = new File(xlsx);
    File cfg = new File(Utilities.changeFileExt(xlsx, ".datestamp"));
    if (t.exists() && cfg.exists() && t.lastModified() > ini.getLongProperty("spreadsheet", "date")) {
      date = t.lastModified();
      // ok, we read the spreadsheet
      XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(xlsx));
      processResource(excel);
      processSearchParams(excel);
      processExamples(excel);
      processOperations(excel);
      processPacks(excel);
    }  
  }

  private void processResource(XSSFWorkbook excel) throws Exception {
    StructureDefinition sd = (StructureDefinition) parseXml(fnSD());
    XSSFSheet res = getSheet(excel, SN_RESOURCE);
    XSSFSheet bindings = getSheet(excel, SN_BINDINGS);
    XSSFSheet invariants = getSheet(excel, SN_INVARIANTS);

    List<ElementDefinition> oldElements = sd.getDifferential().getElement();
    sd.getDifferential().setElement(new ArrayList<ElementDefinition>());

    XSSFRow cols = res.getRow(0);
    for (int i = 1; i <= res.getLastRowNum(); i++) {
      XSSFRow row = res.getRow(i);
      if (row != null && hasValue(row, cols, "Path")) {
        String p = getValue(row, cols, "Path");
        ElementDefinition ed = getED(oldElements, p);
        sd.getDifferential().getElement().add(ed);
        readElementDefinition(row, cols, ed, bindings, invariants);
      }
    }   
    saveXml(fnSD(), sd);
  }


  private void readElementDefinition(XSSFRow row, XSSFRow cols, ElementDefinition ed, XSSFSheet bindings, XSSFSheet invariants) throws Exception {
    ed.getAlias().clear();
    for (String s : splitValue(row, cols, CN_ALIASES, "\\,")) {
      ed.addAlias(s);
    }
    String[] card = splitValue(row, cols, CN_CARD, "\\.\\.");
    if (card.length == 2) {
      ed.setMin(Integer.parseInt(card[0]));
      ed.setMax(card[1]);
    }
    ed.getCondition().clear();
    for (String s : splitValue(row, cols, CN_INV, "\\,")) {
      ed.addCondition(s);
    }

    parseType(ed, getValue(row, cols, CN_TYPE), getValue(row, cols, CN_HIERARCHY));

    if (hasValue(row, cols, CN_IS_MODIFIER)) {
      ed.setIsModifier(Utilities.existsInList(getValue(row, cols, CN_IS_MODIFIER), "1", "true", "y", "Y", "TRUE"));
    } else {
      ed.setIsModifierElement(null);
    }
    ed.setIsModifierReason(getValue(row, cols, CN_MODIFIER_REASON));
    if (hasValue(row, cols, CN_SUMMARY)) {
      ed.setIsSummary(Utilities.existsInList(getValue(row, cols, CN_SUMMARY), "1", "true", "y", "Y", "TRUE"));
    } else {
      ed.setIsSummaryElement(null);      
    }

    parseBinding(ed, getValue(row, cols, CN_BINDING), bindings);
    parseExamples(ed, getValue(row, cols, CN_EXAMPLE), bindings);

    ed.setMeaningWhenMissing(getValue(row, cols, CN_MISSING_MEANING));
    ed.setShort(getValue(row, cols, CN_SHORT_NAME));
    ed.setDefinition(getValue(row, cols, CN_DEFINITION));
    ed.setRequirements(getValue(row, cols, CN_REQUIREMENTS));
    ed.setComment(getValue(row, cols, CN_COMMENTS));

    parseStatus(ed, getValue(row, cols, CN_STATUS));
    parseUml(ed, getValue(row, cols, CN_UML), bindings);

    readExt(ed, row, cols, CN_DISPLAY_HINT, BuildExtensions.EXT_HINT, ExtensionType.String);
    readExt(ed, row, cols, CN_COMMITTEE_NOTES, BuildExtensions.EXT_COMMITTEE_NOTES, ExtensionType.String);  
    parseInvariants(invariants, ed);
  }

  private void parseInvariants(XSSFSheet invariants, ElementDefinition ed) throws Exception {

    List<ElementDefinitionConstraintComponent> oldInvs = ed.getConstraint();
    ed.setConstraint(new ArrayList<>());

    XSSFRow cols = invariants.getRow(0);
    for (int i = 1; i <= invariants.getLastRowNum(); i++) {
      XSSFRow row = invariants.getRow(i);
      if (row != null && hasValue(row, cols, CN_CONTEXT) && ed.getPath().equals(getValue(row, cols, CN_CONTEXT))) {
        String n = getValue(row, cols, CN_ID);
        ElementDefinitionConstraintComponent inv = getInv(oldInvs, n);
        ed.getConstraint().add(inv);
        readInvariant(row, cols, inv);
      }
    }    
  }

  private void readInvariant(XSSFRow row, XSSFRow cols, ElementDefinitionConstraintComponent inv) {
    readExt(inv, row, cols, CN_NAME, BuildExtensions.EXT_NAME, ExtensionType.String);
    readExt(inv, row, cols, CN_BEST_PRACTICE, BuildExtensions.EXT_BEST_PRACTICE, ExtensionType.Boolean);
    readExt(inv, row, cols, CN_BEST_PRACTICE_COMMENT, BuildExtensions.EXT_BEST_PRACTICE_EXPLANATION, ExtensionType.Markdown);
    readExt(inv, row, cols, CN_COMMITTEE_NOTES, BuildExtensions.EXT_COMMITTEE_NOTES, ExtensionType.Boolean);
    inv.setSeverity(ConstraintSeverity.fromCode(getValue(row, cols, CN_SEVERITY)));
    inv.setHuman(getValue(row, cols, CN_ENGLISH));
    inv.setExpression(getValue(row, cols, CN_EXPRESSION));
    inv.setXpath(getValue(row, cols, CN_X_PATH));
  }

  private ElementDefinitionConstraintComponent getInv(List<ElementDefinitionConstraintComponent> list, String n) {
    for (ElementDefinitionConstraintComponent t : list) {
      if (n.equals(t.getKey())) {
        return t;
      }
    }
    ElementDefinitionConstraintComponent t = new ElementDefinitionConstraintComponent();
    t.setKey(n);
    return t;
  }

  private void parseType(ElementDefinition ed, String value, String hierarchy) throws Exception {
    ed.getType().clear();
    ed.setContentReferenceElement(null);
    if (ed.getPath().equals("Resource.id")) {
      ed.addType().setCode("http://hl7.org/fhirpath/System.String").addExtension("http://hl7.org/fhir/StructureDefinition/structuredefinition-fhir-type", new UriType("id"));      
    } else {
      if (Utilities.noString(value)) {
        if (ed.getPath().contains(".")) {
          ed.addType().setCode("BackboneElement");
        }
      } else if (value.startsWith("#")) {
        ed.setContentReference(value);
      } else {
        List<TypeRef> tl = new TypeParser().parse(value, false, null, context, false);
        for (TypeRef tr : tl) {
          TypeRefComponent t = ed.addType().setCode(tr.getName());
          if ("SimpleQuantity".equals(t.getCode())) {
            t.setCode("Quantity");
            t.addProfile("http://hl7.org/fhir/StructureDefinition/SimpleQuantity");
          }
          for (String p : tr.getParams()) {
            if (p.equals("Definition")) {
              t.addExtension(BuildExtensions.EXT_PATTERN, new CanonicalType("http://hl7.org/fhir/StructureDefinition/Definition"));
              t.addTargetProfile("http://hl7.org/fhir/StructureDefinition/ActivityDefinition");
              t.addTargetProfile("http://hl7.org/fhir/StructureDefinition/EventDefinition");
              t.addTargetProfile("http://hl7.org/fhir/StructureDefinition/EvidenceVariable");
              t.addTargetProfile("http://hl7.org/fhir/StructureDefinition/Measure");
              t.addTargetProfile("http://hl7.org/fhir/StructureDefinition/OperationDefinition");
              t.addTargetProfile("http://hl7.org/fhir/StructureDefinition/PlanDefinition");
              t.addTargetProfile("http://hl7.org/fhir/StructureDefinition/Questionnaire");
              t.addTargetProfile("http://hl7.org/fhir/StructureDefinition/SubscriptionTopic");
            } else {
              t.addTargetProfile("http://hl7.org/fhir/StructureDefinition/"+p);
            }
          }
          if (Utilities.existsInList(tr.getName(), "canonical", "Reference") && !Utilities.noString(hierarchy)) {
            t.addExtension(BuildExtensions.EXT_HIERARCHY, new BooleanType(Utilities.existsInList(hierarchy, "true", "True", "TRUE", "1", "y", "Y")));
          }
        }
      }
    }
  }

  private void parseBinding(ElementDefinition ed, String value, XSSFSheet bindings) {
    if (Utilities.noString(value)) {
      ed.setBinding(null);
    } else {
      XSSFRow cols = bindings.getRow(0);
      XSSFRow row = lookupRow(bindings, cols, CN_BINDING_NAME, value);
      if (row == null) {
        throw new FHIRException("Unable to find binding "+value+" in "+xlsx);
      }
      ElementDefinitionBindingComponent bs = ed.getBinding();
      bs.removeExtension(BuildExtensions.EXT_NAME);
      readExt(bs, row, cols, CN_BINDING_NAME, BuildExtensions.EXT_BINDING_NAME, ExtensionType.String);
      readExt(bs, row, cols, CN_DEFINITION, BuildExtensions.EXT_DEFINITION, ExtensionType.String);
      bs.setStrength(BindingStrength.fromCode(getValue(row, cols, CN_STRENGTH)));
      bs.setValueSet(getValue(row, cols, CN_VALUE_SET));
      readExt(bs, row, cols, CN_OID, BuildExtensions.EXT_VS_OID, ExtensionType.String);
      readExt(bs, row, cols, CN_URI, BuildExtensions.EXT_URI, ExtensionType.String);
      readExt(bs, row, cols, CN_WEBSITE_EMAIL, BuildExtensions.EXT_WEBSITE, ExtensionType.Uri);
      readExt(bs, row, cols, CN_V2, BuildExtensions.EXT_V2_MAP, ExtensionType.String);
      readExt(bs, row, cols, CN_V3, BuildExtensions.EXT_V3_MAP, ExtensionType.String);
      readExt(bs, row, cols, CN_COPYRIGHT, BuildExtensions.EXT_COPYRIGHT, ExtensionType.String);
      readExt(bs, row, cols, CN_COMMITTEE_NOTES, BuildExtensions.EXT_COMMITTEE_NOTES, ExtensionType.Markdown);
    }
  }

  private void parseBinding(OperationDefinitionParameterComponent param, String value, XSSFSheet bindings) {
    if (Utilities.noString(value)) {
      param.setBinding(null);
    } else {
      XSSFRow cols = bindings.getRow(0);
      XSSFRow row = lookupRow(bindings, cols, CN_BINDING_NAME, value);
      if (row == null) {
        throw new FHIRException("Unable to find binding "+value+" in "+xlsx);
      }
      OperationDefinitionParameterBindingComponent bs = param.getBinding();
      bs.removeExtension(BuildExtensions.EXT_NAME);
      readExt(bs, row, cols, CN_BINDING_NAME, BuildExtensions.EXT_BINDING_NAME, ExtensionType.String);
      readExt(bs, row, cols, CN_DEFINITION, BuildExtensions.EXT_DEFINITION, ExtensionType.String);
      bs.setStrength(BindingStrength.fromCode(getValue(row, cols, CN_STRENGTH)));
      bs.setValueSet(getValue(row, cols, CN_VALUE_SET));
      readExt(bs, row, cols, CN_OID, BuildExtensions.EXT_VS_OID, ExtensionType.String);
      readExt(bs, row, cols, CN_URI, BuildExtensions.EXT_URI, ExtensionType.String);
      readExt(bs, row, cols, CN_WEBSITE_EMAIL, BuildExtensions.EXT_WEBSITE, ExtensionType.Uri);
      readExt(bs, row, cols, CN_V2, BuildExtensions.EXT_V2_MAP, ExtensionType.String);
      readExt(bs, row, cols, CN_V3, BuildExtensions.EXT_V3_MAP, ExtensionType.String);
      readExt(bs, row, cols, CN_COPYRIGHT, BuildExtensions.EXT_COPYRIGHT, ExtensionType.String);
      readExt(bs, row, cols, CN_COMMITTEE_NOTES, BuildExtensions.EXT_COMMITTEE_NOTES, ExtensionType.Markdown);
    }
  }

  private void parseExamples(ElementDefinition ed, String value, XSSFSheet bindings) throws IOException {
    ed.getExample().clear();
    if (!Utilities.noString(value)) {
      value = value.trim();
      if (value.startsWith("{") || value.startsWith("[")) {
        JsonObject json = JsonTrackingParser.parseJson(value);
        throw new Error("Not done yet");
      } else {
        if (ed.getType().size() == 1) {
          ed.addExample().setLabel("General").setValue(ResourceFactory.createPrimitive(ed.getTypeFirstRep().getCode(), value));
        }
      }
    }
  }

  private void parseStatus(Element ed, String value) {
    ed.removeExtension(BuildExtensions.EXT_FMM_LEVEL);
    ed.removeExtension(BuildExtensions.EXT_STANDARDS_STATUS);
    ed.removeExtension(BuildExtensions.EXT_NORMATIVE_VERSION);

    if (!Utilities.noString(value) && value.contains("/")) {
      String[] p = value.split("\\/");
      if (Utilities.noString(p[0].trim())) {
        ed.removeExtension(BuildExtensions.EXT_FMM_LEVEL);
      } else {
        ed.addExtension(BuildExtensions.EXT_FMM_LEVEL, new IntegerType(p[0]));        
      }
      if (p[1].contains(";")) {
        ed.addExtension(BuildExtensions.EXT_STANDARDS_STATUS, new CodeType(p[1].substring(0, p[1].indexOf(";"))));                
        ed.addExtension(BuildExtensions.EXT_NORMATIVE_VERSION, new CodeType(p[1].substring(p[1].indexOf("from=")+5)));                
      } else {
        ed.addExtension(BuildExtensions.EXT_STANDARDS_STATUS, new CodeType(p[1]));        
      }
    }
    sortExtensions(ed);
  }

  private void parseStatus(DomainResource ed, String value) {
    ed.getExtension().removeIf(ext -> ext.getUrl().equals(BuildExtensions.EXT_FMM_LEVEL));
    ed.getExtension().removeIf(ext -> ext.getUrl().equals(BuildExtensions.EXT_STANDARDS_STATUS));
    ed.getExtension().removeIf(ext -> ext.getUrl().equals(BuildExtensions.EXT_NORMATIVE_VERSION));

    if (!Utilities.noString(value) && value.contains("/")) {
      String[] p = value.split("\\/");
      if (Utilities.noString(p[0].trim())) {
        ed.getExtension().removeIf(ext -> ext.getUrl().equals(BuildExtensions.EXT_FMM_LEVEL));
      } else {
        ed.addExtension(BuildExtensions.EXT_FMM_LEVEL, new IntegerType(p[0]));        
      }
      if (p[1].contains(";")) {
        ed.addExtension(BuildExtensions.EXT_STANDARDS_STATUS, new CodeType(p[1].substring(0, p[1].indexOf(";"))));                
        ed.addExtension(BuildExtensions.EXT_NORMATIVE_VERSION, new CodeType(p[1].substring(p[1].indexOf("from=")+5)));                
      } else {
        ed.addExtension(BuildExtensions.EXT_STANDARDS_STATUS, new CodeType(p[1]));        
      }
    }
    sortExtensions(ed);
  }

  private void parseUml(ElementDefinition ed, String value, XSSFSheet bindings) {
    ed.removeExtension(BuildExtensions.EXT_UML_BREAK);
    ed.removeExtension(BuildExtensions.EXT_UML_DIR);

    if (!Utilities.noString(value)) {
      if (value.contains("|")) {
        String[] p = value.split("\\|");
        if (Utilities.noString(p[0].trim())) {
          ed.addExtension(BuildExtensions.EXT_UML_BREAK, new CodeType(p[1].trim()));                          
        } else {
          ed.addExtension(BuildExtensions.EXT_UML_DIR, new CodeType(p[0].trim()));                
          ed.addExtension(BuildExtensions.EXT_UML_BREAK, new CodeType(p[1].trim()));                
        }        
      } else {
        ed.addExtension(BuildExtensions.EXT_UML_DIR, new CodeType(value));                
      }
    }
    sortExtensions(ed);
  }

  private Extension extFactory(String url, String value, ExtensionType extType) {
    switch (extType) {
    case Boolean:
      return new Extension(url, new BooleanType(Utilities.existsInList(value, "1", "y", "Y", "true", "True", "TRUE")));
    case Code:
      return new Extension(url, new CodeType(value));
    case String:
      return new Extension(url, new StringType(value));
    case Uri:
      return new Extension(url, new UriType(value));
    case Markdown:
      return new Extension(url, new MarkdownType(value));
    case Integer:
      return new Extension(url, new IntegerType(value));
    }
    throw new FHIRException("Shouldn't happen");    
  }

  private void readExt(Element e, XSSFRow row, XSSFRow cols, String colName, String url, ExtensionType extType) {
    if (e.hasExtension(url)) {
      e.removeExtension(url);
    }
    String s = getValue(row, cols, colName);
    if (!Utilities.noString(s)) {
      e.addExtension(extFactory(url, s, extType));
    }
    sortExtensions(e);
  }

  private void readExt(Element e, String s, String url, ExtensionType extType) {
    if (e.hasExtension(url)) {
      e.removeExtension(url);
    }
    if (!Utilities.noString(s)) {
      e.addExtension(extFactory(url, s, extType));
    }
    sortExtensions(e);
  }

  private void readExt(DomainResource dr, XSSFRow row, XSSFRow cols, String colName, String url, ExtensionType extType) {
    if (dr.hasExtension(url)) {
      BuildExtensions.removeExtension(dr,  url);
    }
    String s = getValue(row, cols, colName);
    if (!Utilities.noString(s)) {
      dr.addExtension(extFactory(url, s, extType));
    }
    sortExtensions(dr);
  }

  private void readExt(DomainResource e, String s, String url, ExtensionType extType) {
    if (e.hasExtension(url)) {
      BuildExtensions.removeExtension(e,  url);
    }
    if (!Utilities.noString(s)) {
      e.addExtension(extFactory(url, s, extType));
    }
    sortExtensions(e);
  }

  private ElementDefinition getED(List<ElementDefinition> list, String p) {    
    for (ElementDefinition ed : list) {
      if (ed.getPath().equals(p)) {
        return ed;
      }
    }
    return new ElementDefinition(p);
  }

  private String[] splitValue(XSSFRow row, XSSFRow cols, String colName, String regex) {
    if (hasValue(row, cols, colName)) {
      return getValue(row, cols, colName).split(regex);
    } else {
      return new String[] {};
    }
  }

  private String getValue(XSSFRow row, XSSFRow cols, String colName) {
    int col = -1;
    for (int i =0; i < cols.getLastCellNum(); i++) {
      XSSFCell cell = cols.getCell(i);
      if (cell != null && colName.equals(cell.getStringCellValue())) {
        col = i;
      }
    }
    if (col >= 0) {
      XSSFCell cell = row.getCell(col);
      if (cell != null) {
        return cell.getStringCellValue();
      }      
    }
    return null;
  }

  private boolean hasValue(XSSFRow row, XSSFRow cols, String colName) {
    int col = -1;
    for (int i =0; i < cols.getLastCellNum(); i++) {
      XSSFCell cell = cols.getCell(i);

      if (cell != null && colName.equals(cell.getStringCellValue())) {
        col = i;
      }
    }
    if (col >= 0) {
      XSSFCell cell = row.getCell(col);
      if (cell != null) {
        return !Utilities.noString(cell.getStringCellValue());
      }      
    }
    return false;
  }

  private XSSFRow lookupRow(XSSFSheet sheet, XSSFRow cols, String cnName, String value) {
    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
      XSSFRow row = sheet.getRow(i);
      if (row != null && hasValue(row, cols, cnName)) {
        if (value.equals(getValue(row, cols, cnName))) {
          return row;
        }
      }
    }
    return null;
  }

  private XSSFSheet getSheet(XSSFWorkbook excel, String name) {
    XSSFSheet sheet = excel.getSheet(name);
    if (sheet == null) {
      throw new FHIRException("Unable to find sheet "+name+" in "+xlsx);
    }
    return sheet;
  }

  private void processSearchParams(XSSFWorkbook excel) throws FHIRFormatError, FileNotFoundException, IOException {
    Bundle bnd = (Bundle) parseXml(fnSP());
    XSSFSheet src = getSheet(excel, SN_SEARCH);

    List<BundleEntryComponent> oldSPs = bnd.getEntry();
    bnd.setEntry(new ArrayList<>());

    XSSFRow cols = src.getRow(0);
    for (int i = 1; i <= src.getLastRowNum(); i++) {
      XSSFRow row = src.getRow(i);
      if (row != null && hasValue(row, cols, CN_NAME)) {
        String c = getValue(row, cols, CN_NAME);
        SearchParameter sp = getSp(oldSPs, c);
        bnd.addEntry().setResource(sp);
        readSearchParameter(row, cols, sp);
      }
    }    
    saveXml(fnSP(), bnd);
  }

  private void readSearchParameter(XSSFRow row, XSSFRow cols, SearchParameter sp) {
    sp.setType(SearchParamType.fromCode(getValue(row, cols, CN_TYPE)));
    sp.setExpression(getValue(row, cols, CN_EXPRESSION));
    sp.setDescription(getValue(row, cols, CN_DESCRIPTION));
    sp.setXpath(getValue(row, cols, CN_X_PATH));
    sp.getTarget().clear();
    for (String s : splitValue(row, cols, CN_TARGET_TYPES, "\\|")) {
      sp.getTarget().add(new CodeType(s));
    }
    readExt(sp, row, cols, CN_PATH, BuildExtensions.EXT_PATH, ExtensionType.String);
    readExt(sp, row, cols, CN_COMMITTEE_NOTES, BuildExtensions.EXT_COMMITTEE_NOTES, ExtensionType.String);
  }

  private SearchParameter getSp(List<BundleEntryComponent> list, String c) {
    for (BundleEntryComponent be : list) {
      if (c.equals(((SearchParameter) be.getResource()).getCode())) {
        return (SearchParameter) be.getResource();
      }
    }
    return new SearchParameter().setCode(c);
  }

  private void processExamples(XSSFWorkbook excel) throws FHIRFormatError, FileNotFoundException, IOException {
    ListResource list = (ListResource) parseXml(fnOperations());
    XSSFSheet src = getSheet(excel, SN_SEARCH);

    List<ListResourceEntryComponent> oldEx = list.getEntry();
    list.setEntry(new ArrayList<>());

    XSSFRow cols = src.getRow(0);
    for (int i = 1; i < src.getLastRowNum(); i++) {
      XSSFRow row = src.getRow(i);
      if (row != null && hasValue(row, cols, CN_NAME)) {
        String c = getValue(row, cols, CN_NAME);
        ListResourceEntryComponent li = getExample(oldEx, c);
        list.addEntry(li);
        readExample(row, cols, li);
      }
    }    
  }

  private ListResourceEntryComponent getExample(List<ListResourceEntryComponent> oldEx, String c) {
    for (ListResourceEntryComponent li : oldEx) {
      if (c.equals(li.getItem().getDisplay())) {
        return li;
      }
    }

    ListResourceEntryComponent res = new ListResourceEntryComponent();
    res.getItem().setDisplay(c);
    return res;
  }


  private void readExample(XSSFRow row, XSSFRow cols, ListResourceEntryComponent li) {
    li.getFlag().addCoding(BuildExtensions.EXT_EXAMPLE_TYPE, getValue(row, cols, CN_TYPE), null);
    readExt(li, row, cols, CN_DESCRIPTION, BuildExtensions.EXT_DESCRIPTION, ExtensionType.Markdown);    
    li.getItem().setReference(getValue(row, cols, CN_IDENTITY));
    readExt(li, row, cols, CN_SOURCE, BuildExtensions.EXT_TITLE, ExtensionType.String);    
    readExt(li, row, cols, CN_COMMITTEE_NOTES, BuildExtensions.EXT_COMMITTEE_NOTES, ExtensionType.String);    
  }

  private void processOperations(XSSFWorkbook excel) throws IOException {
    ListResource list = (ListResource) parseXml(fnOperations());
    XSSFSheet src = getSheet(excel, SN_OPERATIONS);
    XSSFSheet bindings = getSheet(excel, SN_BINDINGS);
    list.getEntry().clear();

    XSSFRow cols = src.getRow(0);
    OperationDefinition opd = null;
    Map<String, OperationDefinitionParameterComponent> params = new HashMap<>();
    for (int i = 1; i <= src.getLastRowNum(); i++) {
      XSSFRow row = src.getRow(i);
      if (row != null && hasValue(row, cols, CN_NAME)) {
        String name = getValue(row, cols, CN_NAME);
        if (name == null || !name.contains(".")) {
          if (opd != null) {
            saveXml(fnOpDef(resourceName+"-"+opd.getCode()), opd);
          }
          opd = processOperation(row, cols, name, list.getEntry());
          moveParams(opd, params);
          list.addEntry().getItem().setReference("OperationDefinition/"+resourceName+"-"+opd.getCode());
        } else {
          processOperationParameter(row, cols, name, opd, params, bindings);
        }        
      }
    }
    if (opd != null) {
      saveXml(fnOpDef(resourceName+"-"+opd.getCode()), opd);
    }
    saveXml(fnOperations(), list);
  }


  private void moveParams(OperationDefinition opd, Map<String, OperationDefinitionParameterComponent> params) {
    params.clear();
    for (OperationDefinitionParameterComponent p : opd.getParameter()) {
      String path = p.getName()+(p.hasUse() ? ":"+p.getUse() : "");      
      params.put(path, p);
      moveParts(path, p, params);
      p.getPart().clear();
    }    
    opd.getParameter().clear();
  }

  private void moveParts(String path, OperationDefinitionParameterComponent pp, Map<String, OperationDefinitionParameterComponent> params) {
    for (OperationDefinitionParameterComponent p : pp.getPart()) {
      String npath = path+"."+(p.hasUse() ? ":"+p.getUse() : "");
      params.put(npath, p);
      moveParts(npath, p, params);
      p.getPart().clear();
    }    
  }

  private OperationDefinition processOperation(XSSFRow row, XSSFRow cols, String name, List<ListResourceEntryComponent> entry) throws IOException {
    String fn = fnOpDef(resourceName+"-"+name);
    OperationDefinition opd = null;
    if (new File(fn).exists()) {
      opd = (OperationDefinition) parseXml(fn);
    } else {
      opd = new OperationDefinition();
      opd.setId(name);
      opd.setUrl("http://hl7.org/fhir/Operationdefinition/"+name);
      opd.setName(name);
      opd.setCode(name);
    }
    opd.setSystem(false);
    opd.setType(false);
    opd.setInstance(false);
    for (String s : splitValue(row, cols, CN_USE, "\\|")) {
      String n = s.trim();
      switch (n) { 
      case "System": opd.setSystem(true); break;
      case "Type": opd.setType(true); break;
      case "Instance": opd.setInstance(true); break;
      }
    }
    opd.setAffectsState(false);
    opd.setKind(null);
    for (String s : splitValue(row, cols, CN_TYPE, "\\|")) {
      String n = s.trim();
      if (n.equals("affects-state")) {
        opd.setAffectsState(true);        
      } else {
        opd.setKind(OperationKind.fromCode(n));
      }
    }
    opd.setTitle(getValue(row, cols, CN_TITLE));
    opd.setDescription(getValue(row, cols, CN_DOCUMENTATION));
    opd.setComment(getValue(row, cols, CN_COMMENTS));
    readExt(opd, row, cols, CN_FOOTER, BuildExtensions.EXT_FOOTER, ExtensionType.Markdown);
    readExt(opd, row, cols, CN_FOOTER2, BuildExtensions.EXT_FOOTER2, ExtensionType.Markdown);

    opd.getExtension().removeIf(n -> ("1".equals(n.getExtensionString(BuildExtensions.EXT_OP_EXAMPLE_LIST))));
    opd.getExtension().removeIf(n -> ("2".equals(n.getExtensionString(BuildExtensions.EXT_OP_EXAMPLE_LIST))));
    
    for (String s : splitValue(row, cols, CN_EXAMPLE1_REQUEST, "\\|")) {
      Extension ext = opd.addExtension().setUrl(BuildExtensions.EXT_OP_EXAMPLE);
      ext.addExtension(BuildExtensions.EXT_OP_EXAMPLE_LIST, new StringType("1"));
      ext.addExtension(BuildExtensions.EXT_OP_EXAMPLE_RESPONSE, new BooleanType(false));
      ext.addExtension(BuildExtensions.EXT_OP_EXAMPLE_CONTENT, new StringType(s));
    }
    for (String s : splitValue(row, cols, CN_EXAMPLE1_RESPONSE, "\\|")) {
      Extension ext = opd.addExtension().setUrl(BuildExtensions.EXT_OP_EXAMPLE);
      ext.addExtension(BuildExtensions.EXT_OP_EXAMPLE_LIST, new StringType("1"));
      ext.addExtension(BuildExtensions.EXT_OP_EXAMPLE_RESPONSE, new BooleanType(true));
      ext.addExtension(BuildExtensions.EXT_OP_EXAMPLE_CONTENT, new StringType(s));
    }        
    for (String s : splitValue(row, cols, CN_EXAMPLE2_REQUEST, "\\|")) {
      Extension ext = opd.addExtension().setUrl(BuildExtensions.EXT_OP_EXAMPLE);
      ext.addExtension(BuildExtensions.EXT_OP_EXAMPLE_LIST, new StringType("2"));
      ext.addExtension(BuildExtensions.EXT_OP_EXAMPLE_RESPONSE, new BooleanType(false));
      ext.addExtension(BuildExtensions.EXT_OP_EXAMPLE_CONTENT, new StringType(s));
    }

    for (String s : splitValue(row, cols, CN_EXAMPLE2_RESPONSE, "\\|")) {
      Extension ext = opd.addExtension().setUrl(BuildExtensions.EXT_OP_EXAMPLE);
      ext.addExtension(BuildExtensions.EXT_OP_EXAMPLE_LIST, new StringType("2"));
      ext.addExtension(BuildExtensions.EXT_OP_EXAMPLE_RESPONSE, new BooleanType(true));
      ext.addExtension(BuildExtensions.EXT_OP_EXAMPLE_CONTENT, new StringType(s));
    }        
    parseStatus(opd, getValue(row, cols, CN_STATUS));
    readExt(opd, row, cols, CN_COMMITTEE_NOTES, BuildExtensions.EXT_COMMITTEE_NOTES, ExtensionType.String);      
    return opd;
  }



  private void processOperationParameter(XSSFRow row, XSSFRow cols, String name, OperationDefinition opd, Map<String, OperationDefinitionParameterComponent> params, XSSFSheet bindings) {
    String pname = name.substring(name.lastIndexOf(".")+1);
    String context = name.substring(0, name.lastIndexOf("."));

    OperationParameterUse use = OperationParameterUse.fromCode(getValue(row, cols, CN_USE));

    OperationDefinitionParameterComponent param = params.get(pname+ (use != null ? ":"+use : ""));
    if (param == null) {
      param = new OperationDefinitionParameterComponent().setName(pname).setUse(use);
    }
    OperationDefinitionParameterComponent ctxt = findParam(opd, context);
    if (ctxt == null) {
      opd.getParameter().add(param);
    } else {
      ctxt.getPart().add(param);      
    }

    param.setMinElement(null);
    param.setMaxElement(null);
    String[] card = splitValue(row, cols, CN_CARD, "\\.\\.");
    if (card.length == 2) {
      param.setMin(Integer.parseInt(card[0]));
      param.setMax(card[1]);
    }
    String t = getValue(row, cols, CN_TYPE);
    if (!Utilities.noString(t)) {
      if (t.contains(" | ")) {
        param.setType(FHIRAllTypes.ELEMENT);
        for (String s : t.split("\\|")) {
          param.addExtension(BuildExtensions.EXT_ALLOWED_TYPE, new UriType(s.trim()));
        }
      } else {
        if (t.contains("/")) {
          param.setSearchType(SearchParamType.fromCode(t.substring(t.lastIndexOf("/")+1).trim()));
          t = t.substring(0, t.indexOf("/")).trim();
        }
        param.setType(FHIRAllTypes.fromCode(t));
      }
    }

    parseBinding(param, getValue(row, cols, CN_BINDING), bindings);
    param.setDocumentation(getValue(row, cols, CN_DOCUMENTATION));
    parseStatus(param, getValue(row, cols, CN_STATUS));
    readExt(param, row, cols, CN_COMMITTEE_NOTES, BuildExtensions.EXT_COMMITTEE_NOTES, ExtensionType.String);          
  }  

  private OperationDefinitionParameterComponent findParam(OperationDefinition opd, String context) {
    if (opd.getCode().equals(context)) {
      return null;
    }
    for (int i = opd.getParameter().size() - 1; i >= 0; i--) {
      OperationDefinitionParameterComponent p = opd.getParameter().get(i);
      String path = opd.getCode()+"."+p.getName();
      if (path.equals(context)) {
        return p;
      }
      if (context.startsWith(path)) {
        return findParam(path, p, context);
      }
    }
    throw new Error("Unable to find parameter "+context);
  }

  private OperationDefinitionParameterComponent findParam(String path, OperationDefinitionParameterComponent pp, String context) {
    for (int i = pp.getPart().size() - 1; i >= 0; i--) {
      OperationDefinitionParameterComponent p = pp.getPart().get(i);
      String npath = path+"."+p.getName();
      if (npath.equals(context)) {
        return p;
      }
      if (context.startsWith(npath)) {
        return findParam(npath, p, context);
      }
    }   
    throw new Error("Unable to find parameter "+context);
  }

  private void processPacks(XSSFWorkbook excel) throws FHIRFormatError, FileNotFoundException, IOException {
    ListResource list = (ListResource) parseXml(fnPacks());
    list.getEntry().clear();

    for (int i = 0; i < excel.getNumberOfSheets(); i++) {
      XSSFSheet sheet = excel.getSheetAt(i);
      if (sheet.getSheetName().startsWith("Pack ")) {
        String n = sheet.getSheetName().substring(5);
        list.addEntry().getItem().setReference("ImplementationGuide/"+resourceName+"-"+n);
        processPack(sheet, n);
      }
    }
    saveXml(fnPacks(), list);
  }

  private void processPack(XSSFSheet src, String ref) throws FHIRFormatError, FileNotFoundException, IOException {
    ImplementationGuide ig = (ImplementationGuide) parseXml(fnIG(resourceName+"-"+ref));

    ig.getDefinition().getResource().clear();
    XSSFRow cols = src.getRow(0);
    for (int i = 1; i <= src.getLastRowNum(); i++) {
      XSSFRow row = src.getRow(i);
      String n = getValue(row, cols, CN_NAME);
      String v = getValue(row, cols, CN_VALUE);
      if (Utilities.noString(v)) {
        v = null;
      }
      switch (n) {
      case CN_ID: ig.setId(v); break;
      case CN_CODE: readExt(ig, v, BuildExtensions.EXT_CODE, ExtensionType.Code);
      case CN_NAME: ig.setName(v); break;
      case CN_TITLE: ig.setTitle(v); break;
      case CN_VERSION: ig.setVersion(v); break;
      case CN_DESCRIPTION: ig.setDescription(v); break;
      case CN_PUBLISHER: ig.setPublisher(v); break;
      case CN_STATUS: ig.setStatus(PublicationStatus.fromCode(v)); break;
      case CN_EXPERIMENTAL: ig.getExperimentalElement().setValueAsString(v); break;
      case CN_DATE: ig.getDateElement().setValueAsString(v); break;
      case CN_FMM: readExt(ig, v, BuildExtensions.EXT_FMM_LEVEL, ExtensionType.Integer); break;
      case CN_WORK_GROUP: readExt(ig, v, BuildExtensions.EXT_WORKGROUP, ExtensionType.Code); break;
      case CN_INTRODUCTION: readExt(ig, v, BuildExtensions.EXT_INTRODUCTION, ExtensionType.String); break;
      case CN_NOTES: readExt(ig, v, BuildExtensions.EXT_NOTES, ExtensionType.String); break;
      case CN_EXTENSION: if (v != null) { ig.getDefinition().addResource().getReference().setReference("StructureDefinition/"+v); } break;
      case CN_PROFILE:  if (v != null) { ig.getDefinition().addResource().getReference().setReference("StructureDefinition/"+v); } break;
      case CN_EXAMPLE:  if (v != null) { ig.getDefinition().addResource().getReference().setReference(v); } break;
      case CN_SEARCH_PARAMETER:  if (v != null) { ig.getDefinition().addResource().getReference().setReference("SearchParameter/"+v); } break;
      } 
    }
    saveXml(fnIG(resourceName+"-"+ref), ig);
  }

}
