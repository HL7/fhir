package org.hl7.fhir.definitions.parsers.spreadsheets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hl7.fhir.definitions.BuildExtensions;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.r5.context.BaseWorkerContext;
import org.hl7.fhir.r5.formats.JsonParser;
import org.hl7.fhir.r5.model.BooleanType;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r5.model.CanonicalType;
import org.hl7.fhir.r5.model.CodeType;
import org.hl7.fhir.r5.model.DomainResource;
import org.hl7.fhir.r5.model.Element;
import org.hl7.fhir.r5.model.ElementDefinition;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionExampleComponent;
import org.hl7.fhir.r5.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.r5.model.Extension;
import org.hl7.fhir.r5.model.IdType;
import org.hl7.fhir.r5.model.ImplementationGuide;
import org.hl7.fhir.r5.model.ImplementationGuide.ImplementationGuideDefinitionResourceComponent;
import org.hl7.fhir.r5.model.ListResource;
import org.hl7.fhir.r5.model.ListResource.ListResourceEntryComponent;
import org.hl7.fhir.r5.model.OperationDefinition;
import org.hl7.fhir.r5.model.OperationDefinition.OperationDefinitionParameterBindingComponent;
import org.hl7.fhir.r5.model.OperationDefinition.OperationDefinitionParameterComponent;
import org.hl7.fhir.r5.model.SearchParameter;
import org.hl7.fhir.r5.model.StringType;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.utils.ToolingExtensions;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Utilities;

public class SpreadSheetCreator extends SpreadSheetBase {
  
  private int invRowCount;
  private int bindingRowCount;
  private CellStyle headerStyle;
  private CellStyle boldStyle;
  private CellStyle wrapStyle;
  
  public SpreadSheetCreator(BaseWorkerContext context, String srcFolder, String resourceName) {
    super(context, srcFolder, resourceName);
  }

  public void generateSpreadsheet() throws FHIRFormatError, FileNotFoundException, IOException {
    String tgt = fnSpreadsheet();
    File t = new File(tgt);
    if (t.exists()) {
      t.delete();
    }
    if (t.exists()) {
      System.out.println(tgt+" not updated - open in excel");
    } else {
      XSSFWorkbook excel = new XSSFWorkbook();

      makeStyles(excel);
      XSSFSheet bindings = addResourceSheets(excel);
      addSearchParams(excel);
      addOperations(excel, bindings);
      addExamples(excel);
      addPacks(excel);
      try (FileOutputStream outputStream = new FileOutputStream(tgt)) {
        excel.write(outputStream);
      } catch (Exception e) {
        System.out.println("Error writing to "+tgt+" "+e.getMessage());
      }
      new File(tgt).setLastModified(date);
      IniFile ini = new IniFile(Utilities.changeFileExt(tgt, ".datestamp"));
      ini.setLongProperty("spreadsheet", "date", date, "Never change this date manually");
      ini.save();
    }
  }

  private void makeStyles(XSSFWorkbook excel) {
    headerStyle = excel.createCellStyle();

    XSSFFont font = excel.createFont();
    font.setColor(IndexedColors.WHITE.getIndex());
    font.setBold(true);
    font.setItalic(false);

    headerStyle.setFillBackgroundColor(IndexedColors.BLUE_GREY.getIndex());
    headerStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    headerStyle.setFont(font);

    wrapStyle = excel.createCellStyle();
    wrapStyle.setWrapText(true);

    boldStyle = excel.createCellStyle();

    XSSFFont fontB = excel.createFont();
    fontB.setColor(IndexedColors.BLACK.getIndex());
    fontB.setBold(true);
    boldStyle.setFont(fontB);
  }

  private XSSFSheet addResourceSheets(XSSFWorkbook excel) throws FHIRFormatError, FileNotFoundException, IOException {
    StructureDefinition sd = (StructureDefinition) parseXml(fnSD());
    sd.setText(null);
    XSSFSheet resource = excel.createSheet(SpreadSheetCreator.SN_RESOURCE);
    XSSFSheet bindings = excel.createSheet(SpreadSheetCreator.SN_BINDINGS);
    XSSFSheet invariants = excel.createSheet(SpreadSheetCreator.SN_INVARIANTS);
    addResourceColumns(resource, sd);
    addBindingColumns(bindings, sd);
    addInvariantColumns(invariants, sd);
    int rowCount = 0;
    invRowCount = 1;
    bindingRowCount = 1;
    for (ElementDefinition ed : sd.getDifferential().getElement()) {
      rowCount++;
      addElements(resource, bindings, invariants, ed, rowCount);
    }
    return bindings;
  }
  

  private void addResourceColumns(XSSFSheet sheet, StructureDefinition sd) {
    Row row = sheet.createRow(0);
    row.setRowStyle(headerStyle);
    int columnCount = 0;
    addCellHeading(CN_PATH, row, sheet, columnCount++, longestPath(sd));
    addCellHeading(CN_ALIASES, row, sheet, columnCount++, 20);
    addCellHeading(CN_CARD, row, sheet, columnCount++, 6);
    addCellHeading(CN_INV, row, sheet, columnCount++, 4);
    addCellHeading(CN_TYPE, row, sheet, columnCount++, 30);
    addCellHeading(CN_HIERARCHY, row, sheet, columnCount++, 4);
    addCellHeading(CN_IS_MODIFIER, row, sheet, columnCount++, 4);
    addCellHeading(CN_MODIFIER_REASON, row, sheet, columnCount++, 20);
    addCellHeading(CN_SUMMARY, row, sheet, columnCount++, 4);
    addCellHeading(CN_BINDING, row, sheet, columnCount++, 12);
    addCellHeading(CN_EXAMPLE, row, sheet, columnCount++, 12);
    addCellHeading(CN_MISSING_MEANING, row, sheet, columnCount++, 10);
    addCellHeading(CN_SHORT_NAME, row, sheet, columnCount++, 25);
    addCellHeading(CN_DEFINITION, row, sheet, columnCount++, 80);
    addCellHeading(CN_REQUIREMENTS, row, sheet, columnCount++, 40);
    addCellHeading(CN_COMMENTS, row, sheet, columnCount++, 40);
    addCellHeading(CN_STATUS, row, sheet, columnCount++, 40);
    addCellHeading(CN_UML, row, sheet, columnCount++, 10);
    addCellHeading(CN_DISPLAY_HINT, row, sheet, columnCount++, 20);
    addCellHeading(CN_COMMITTEE_NOTES, row, sheet, columnCount++, 20);
  }
  
  private void addBindingColumns(XSSFSheet sheet, StructureDefinition sd) {
    Row row = sheet.createRow(0); 
    row.setRowStyle(headerStyle);
    int columnCount = 0;
    addCellHeading(CN_BINDING_NAME, row, sheet, columnCount++, 15);
    addCellHeading(CN_DEFINITION, row, sheet, columnCount++, 30);
    addCellHeading(CN_STRENGTH, row, sheet, columnCount++, 10);
    addCellHeading(CN_VALUE_SET, row, sheet, columnCount++, 30);
    addCellHeading(CN_DESCRIPTION, row, sheet, columnCount++, 30);
    addCellHeading(CN_OID, row, sheet, columnCount++, 10);
    addCellHeading(CN_URI, row, sheet, columnCount++, 30);
    addCellHeading(CN_WEBSITE_EMAIL, row, sheet, columnCount++, 30);
    addCellHeading(CN_V2, row, sheet, columnCount++, 10);
    addCellHeading(CN_V3, row, sheet, columnCount++, 10);
    addCellHeading(CN_COPYRIGHT, row, sheet, columnCount++, 30);
    addCellHeading(CN_COMMITTEE_NOTES, row, sheet, columnCount++, 30);
  }

  private void addInvariantColumns(XSSFSheet sheet, StructureDefinition sd) {
    Row row = sheet.createRow(0); 
    row.setRowStyle(headerStyle);
    int columnCount = 0;
    addCellHeading(CN_ID, row, sheet, columnCount++, 15);
    addCellHeading(CN_NAME, row, sheet, columnCount++, 30);
    addCellHeading(CN_SEVERITY, row, sheet, columnCount++, 10);
    addCellHeading(CN_BEST_PRACTICE, row, sheet, columnCount++, 10);
    addCellHeading(CN_CONTEXT, row, sheet, columnCount++, 30);
    addCellHeading(CN_ENGLISH, row, sheet, columnCount++, 30);
    addCellHeading(CN_EXPRESSION, row, sheet, columnCount++, 10);
    addCellHeading(CN_X_PATH, row, sheet, columnCount++, 30);
    addCellHeading(CN_BEST_PRACTICE_COMMENT, row, sheet, columnCount++, 30);
    addCellHeading(CN_COMMITTEE_NOTES, row, sheet, columnCount++, 30);
  }

  
  private void addElements(XSSFSheet resources, XSSFSheet bindings, XSSFSheet invariants, ElementDefinition ed, int rowCount) throws IOException {
    Row row = resources.createRow(rowCount);    
    int columnCount = 0;
    ed.removeExtension("http://hl7.org/fhir/StructureDefinition/elementdefinition-heirarchy");
    addCell(ed.getPath(), row, columnCount++); // Path
    addCell(aliases(ed), row, columnCount++); // Aliases
    addCell(ed.getMin()+".."+ed.getMax(), row, columnCount++); // Card.
    addCell(inv(ed), row, columnCount++); // Inv.
    addCell(type(ed), row, columnCount++); // Type
    addCell(typeHeirarchy(ed, BuildExtensions.EXT_HIERARCHY), row, columnCount++); 
    addCell(bool(ed.getIsModifierElement()), row, columnCount++); // Is Modifier
    addCell(ed.getIsModifierReason(), row, columnCount++); // Modifier Reason
    addCell(bool(ed.getIsSummaryElement()), row, columnCount++); // Summary
    addCell(bind(ed, bindings), row, columnCount++); // Binding
    addCell(ex(ed), row, columnCount++); // Example
    addCell(ed.getMeaningWhenMissing(), row, columnCount++); // Missing Meaning
    addCell(ed.getShort(), row, columnCount++); // Short Name
    addCell(ed.getDefinition(), row, columnCount++); // Definition
    addCell(ed.getRequirements(), row, columnCount++); // Requirements
    addCell(ed.getComment(), row, columnCount++); // Comments
    addCell(status(ed), row, columnCount++); // Status
    addCell(uml(ed), row, columnCount++); // UML
    addCell(ext(ed, BuildExtensions.EXT_HINT), row, columnCount++); // Display Hint
    addCell(ext(ed, BuildExtensions.EXT_COMMITTEE_NOTES), row, columnCount++); // Commitee Notes    
    for (ElementDefinitionConstraintComponent inv : ed.getConstraint()) {
      row = invariants.createRow(invRowCount++);    
      columnCount = 0;
      addCell(inv.getKey(), row, columnCount++); 
      addCell(ext(inv, BuildExtensions.EXT_NAME), row, columnCount++);
      addCell(inv.getSeverity().toCode(), row, columnCount++);
      addCell(ext(inv, BuildExtensions.EXT_BEST_PRACTICE), row, columnCount++);
      addCell(ed.getPath(), row, columnCount++); 
      addCell(inv.getHuman(), row, columnCount++); 
      addCell(inv.getExpression(), row, columnCount++); 
      addCell(inv.getXpath(), row, columnCount++); // Example
      addCell(ext(inv, BuildExtensions.EXT_BEST_PRACTICE_EXPLANATION), row, columnCount++); // Example
      addCell(ext(inv, BuildExtensions.EXT_COMMITTEE_NOTES), row, columnCount++); // Commitee Notes          
    }
  }
  
  private String typeHeirarchy(ElementDefinition ed, String extHeirarchy) {
    for (TypeRefComponent tr : ed.getType()) {
      if (BuildExtensions.readBoolExtension(tr, BuildExtensions.EXT_HIERARCHY)) {
        return "true";
      }
    }
    return null;
  }

  private String type(ElementDefinition ed) {
    if (ed.hasContentReference()) {
      return ed.getContentReference();
    }
    List<String> tl = new ArrayList<>();
    for (TypeRefComponent tr : ed.getType()) {
      String s;
      if (tr.getCode().equals("canonical") && tr.hasExtension(BuildExtensions.EXT_PATTERN)) {
        s = "canonical("+tr.getExtensionString(BuildExtensions.EXT_PATTERN).substring(40)+")";
      } else {
        s = summary(tr);
        if (s == null) {
          return null;
        }
      }
      tl.add(s);
    }
    
    return String.join(" | ", tl);
  }

  private String summary(TypeRefComponent tr) {
    String res = tr.getWorkingCode();
    if (Utilities.existsInList(res, "Element", "BackboneElement")) {
      return null;
    }
    if (tr.hasProfile()) {
      if (tr.getProfile().size() == 1) {
        res = tr.getProfile().get(0).primitiveValue().substring(40);
      } else {
        throw new Error("Not supported yet");
      }
    }
    if (tr.hasTargetProfile()) {
      List<String> tl = new ArrayList<>();
      for (CanonicalType tp : tr.getTargetProfile()) {
        tl.add(tp.primitiveValue().substring(40));
      }      
      res = res + "("+String.join("|", tl)+")";
    }
    return res;
  }

  private String uml(ElementDefinition ed) {
    sortExtensions(ed);
    if (ed.hasExtension(BuildExtensions.EXT_UML_BREAK) || ed.hasExtension(BuildExtensions.EXT_UML_DIR)) {
      if (ed.hasExtension(BuildExtensions.EXT_UML_BREAK) && ed.hasExtension(BuildExtensions.EXT_UML_DIR)) {
        return ed.getExtensionString(BuildExtensions.EXT_UML_DIR) + " | " + ed.getExtensionString(BuildExtensions.EXT_UML_BREAK);                
      } else if (ed.hasExtension(BuildExtensions.EXT_UML_DIR)) {
        return ed.getExtensionString(BuildExtensions.EXT_UML_DIR);        
      } else {
        return "| "+ed.getExtensionString(BuildExtensions.EXT_UML_BREAK);        
      }
    } else {
      return null;
    }
  }

  private String ext(Element ed, String uri) {
    sortExtensions(ed);
    if (ed.hasExtension(uri)) {
      return ed.getExtensionString(uri);
    } else {
      return null;
    }
  }

  private String ext(DomainResource res, String uri) {
    sortExtensions(res);
    if (res.hasExtension(uri)) {
      return ToolingExtensions.readStringExtension(res, uri);
    } else {
      return null;
    }
  }


  private String ex(ElementDefinition ed) throws IOException {
    if (ed.hasExample()) {
      List<String> exl = new ArrayList<>();
      if (ed.getExample().size() == 1 && (!ed.getExample().get(0).hasLabel() || ed.getExample().get(0).getLabel().equals("General"))) {
        if (ed.getExample().get(0).getValue().isPrimitive()) {
          return ed.getExample().get(0).getValue().primitiveValue();
        } else {
          return new JsonParser().composeString(ed.getExample().get(0).getValue(), "??");
        }        
      } else {
        for (ElementDefinitionExampleComponent ex : ed.getExample()) {
          if (ex.getValue().isPrimitive()) {
            exl.add("{ \""+Utilities.escapeJson(ex.getLabel())+"\" : \""+ Utilities.escapeJson(ex.getValue().primitiveValue())+"\"}");
          } else {
            exl.add("{ \""+Utilities.escapeJson(ex.getLabel())+"\" : "+ new JsonParser().composeString(ex.getValue(), "??")+"}");
          }
        }
        return "["+String.join(";", exl)+"]";
      }
    }
    return null;
  }

  private String bind(OperationDefinitionParameterComponent ed, XSSFSheet bindings) {
    if (ed.hasBinding()) {
      Row row = bindings.createRow(bindingRowCount++); 
      int columnCount = 0;
      String name;
      OperationDefinitionParameterBindingComponent bs = ed.getBinding();
      sortExtensions(ed.getBinding());
      if (bs.hasExtension(BuildExtensions.EXT_BINDING_NAME)) {
        name = bs.getExtensionString(BuildExtensions.EXT_BINDING_NAME);
      } else {
        name = "??";
      }      
      addCell(name, row, columnCount++);
      addCell(ext(bs, BuildExtensions.EXT_DEFINITION), row, columnCount++);
      addCell(bs.getStrength().toCode(), row, columnCount++);
      addCell(bs.getValueSet(), row, columnCount++);
      addCell(null, row, columnCount++);
      addCell(ext(bs, BuildExtensions.EXT_VS_OID), row, columnCount++);
      addCell(ext(bs, BuildExtensions.EXT_URI), row, columnCount++);
      addCell(ext(bs, BuildExtensions.EXT_WEBSITE), row, columnCount++);
      addCell(ext(bs, BuildExtensions.EXT_V2_MAP), row, columnCount++);
      addCell(ext(bs, BuildExtensions.EXT_V3_MAP), row, columnCount++);
      addCell(ext(bs, BuildExtensions.EXT_COPYRIGHT), row, columnCount++);
      addCell(ext(bs, BuildExtensions.EXT_COMMITTEE_NOTES), row, columnCount++);
      
      return name;
    }
    return null;
  }

  
  private String bind(ElementDefinition ed, XSSFSheet bindings) {
    if (ed.hasBinding()) {
      Row row = bindings.createRow(bindingRowCount++); 
      int columnCount = 0;
      String name;
      ElementDefinitionBindingComponent bs = ed.getBinding();
      if (bs.hasExtension(BuildExtensions.EXT_NAME) && bs.hasExtension(BuildExtensions.EXT_BINDING_NAME)) {
        bs.removeExtension(BuildExtensions.EXT_NAME);
      }
      sortExtensions(bs);
      if (bs.hasExtension(BuildExtensions.EXT_BINDING_NAME)) {
        name = bs.getExtensionString(BuildExtensions.EXT_BINDING_NAME);
      } else {
        name = "??";
      }      
      addCell(name, row, columnCount++);
      addCell(ext(bs, BuildExtensions.EXT_DEFINITION), row, columnCount++);
      addCell(bs.getStrength().toCode(), row, columnCount++);
      addCell(bs.getValueSet(), row, columnCount++);
      addCell(bs.getDescription(), row, columnCount++);
      addCell(ext(bs, BuildExtensions.EXT_VS_OID), row, columnCount++);
      addCell(ext(bs, BuildExtensions.EXT_URI), row, columnCount++);
      addCell(ext(bs, BuildExtensions.EXT_WEBSITE), row, columnCount++);
      addCell(ext(bs, BuildExtensions.EXT_V2_MAP), row, columnCount++);
      addCell(ext(bs, BuildExtensions.EXT_V3_MAP), row, columnCount++);
      addCell(ext(bs, BuildExtensions.EXT_COPYRIGHT), row, columnCount++);
      addCell(ext(bs, BuildExtensions.EXT_COMMITTEE_NOTES), row, columnCount++);
      
      return name;
    }
    return null;
  }

  private String bool(BooleanType e) {
    if (e.hasValue()) {
      return Boolean.toString(e.booleanValue());
    } else {
      return null;
    }
  }

  private String inv(ElementDefinition ed) {
    List<String> cond = new ArrayList<>();
    for (IdType c : ed.getCondition()) {
      cond.add(c.primitiveValue());
    }
    return String.join(",", cond);
  }

  private String aliases(ElementDefinition ed) {
    List<String> aliases = new ArrayList<>();
    for (StringType c : ed.getAlias()) {
      aliases.add(c.primitiveValue());
    }
    return String.join(",", aliases);
  }
  
  private int longestPath(StructureDefinition sd) {
    int res = 0;
    for (ElementDefinition ed : sd.getDifferential().getElement()) {
      res = Integer.max(res, ed.getPath().length());
    }
    return res;
  }
  
  private void addPacks(XSSFWorkbook excel) throws FHIRFormatError, FileNotFoundException, IOException {
    ListResource list = (ListResource) parseXml(fnPacks());
    list.setText(null);
    for (ListResourceEntryComponent li : list.getEntry()) {
      String ref = li.getItem().getReference();
      ref = ref.substring(ref.indexOf("/")+1);
      String id = ref.contains("-") ? ref.substring(ref.indexOf("-")+1) : ref;
      XSSFSheet sheet = excel.createSheet("Pack "+id);
      addPackColumns(sheet);
      addPack(sheet, ref);
    }        
  }

  private void addPackColumns(XSSFSheet sheet) {
    Row row = sheet.createRow(0); 
    row.setRowStyle(headerStyle);
    int columnCount = 0;
    addCellHeading(CN_NAME, row, sheet, columnCount++, 20);
    addCellHeading(CN_VALUE, row, sheet, columnCount++, 100);    
  }

  private void addPack(XSSFSheet sheet, String ref) throws FHIRFormatError, FileNotFoundException, IOException {
    ImplementationGuide ig = (ImplementationGuide) parseXml(fnIG(ref));
    ig.setText(null);
    int rowCount = 1;
    sortExtensions(ig);
    addPackRow(sheet, CN_ID, ig.getId(), rowCount++);
    addPackRow(sheet, CN_CODE, BuildExtensions.readStringExtension(ig, BuildExtensions.EXT_CODE), rowCount++);
    addPackRow(sheet, CN_NAME, ig.getName(), rowCount++);
    addPackRow(sheet, CN_TITLE, ig.getTitle(), rowCount++);
    addPackRow(sheet, CN_VERSION, ig.getVersion(), rowCount++);
    addPackRow(sheet, CN_DESCRIPTION, ig.getDescription(), rowCount++);
    addPackRow(sheet, CN_PUBLISHER, ig.getPublisher(), rowCount++);
    addPackRow(sheet, CN_STATUS, ig.getStatus().toCode(), rowCount++);
    addPackRow(sheet, CN_EXPERIMENTAL, ig.getExperimentalElement().primitiveValue(), rowCount++);
    addPackRow(sheet, CN_DATE, ig.getDateElement().primitiveValue(), rowCount++);
    addPackRow(sheet, CN_FMM, BuildExtensions.readStringExtension(ig, BuildExtensions.EXT_FMM_LEVEL), rowCount++);
    addPackRow(sheet, CN_WORK_GROUP, BuildExtensions.readStringExtension(ig, BuildExtensions.EXT_WORKGROUP), rowCount++);
    addPackRow(sheet, CN_INTRODUCTION, BuildExtensions.readStringExtension(ig, BuildExtensions.EXT_INTRODUCTION), rowCount++);
    addPackRow(sheet, CN_NOTES, BuildExtensions.readStringExtension(ig, BuildExtensions.EXT_NOTES), rowCount++);

    for (ImplementationGuideDefinitionResourceComponent res : ig.getDefinition().getResource()) {
      String r = res.getReference().getReference();
      String id = r.substring(r.indexOf("/")+1);
      if (r.startsWith("SearchParameter/")) {
        addPackRow(sheet, CN_SEARCH_PARAMETER, id, rowCount++);
      } else if (r.startsWith("StructureDefinition/")) {
        String fn = fnExt(id); 
        if (new File(fn).exists()) {
          addPackRow(sheet, CN_EXTENSION, id, rowCount++);          
        } else {
          addPackRow(sheet, CN_PROFILE, id, rowCount++);
        }
      } else {
        addPackRow(sheet, CN_EXAMPLE, r, rowCount++);        
      }
    }
  }

  private void addPackRow(XSSFSheet sheet, String name, String value, int i) {
    Row row = sheet.createRow(i); 
    int columnCount = 0;
    addCell(name, row, columnCount++);
    addCell(value, row, columnCount++);   
  }

  private void addExamples(XSSFWorkbook excel) throws FHIRFormatError, FileNotFoundException, IOException {
    ListResource list = (ListResource) parseXml(fnExamples());
    list.setText(null);
    XSSFSheet sheet = excel.createSheet(SN_EXAMPLES);
    addExampleColumns(sheet);
    int rowCount = 0;
    for (ListResourceEntryComponent li : list.getEntry()) {
      rowCount++;
      addExample(sheet, li, rowCount);
    }    
  }

  private void addExample(XSSFSheet sheet, ListResourceEntryComponent li, int rowCount) {
    Row row = sheet.createRow(rowCount++);
    int columnCount = 0;
    addCell(li.getItem().getDisplay(), row, columnCount++); 
    addCell(flag(li), row, columnCount++); 
    addCell(ext(li, BuildExtensions.EXT_DESCRIPTION), row, columnCount++); 
    addCell(li.getItem().getReference(), row, columnCount++); 
    addCell(ext(li, BuildExtensions.EXT_TITLE), row, columnCount++);
    addCell(ext(li, BuildExtensions.EXT_COMMITTEE_NOTES), row, columnCount++);
  }

  private String flag(ListResourceEntryComponent li) {
    return li.getFlag().getCode(BuildExtensions.EXT_EXAMPLE_TYPE);
  }

  private void addExampleColumns(XSSFSheet sheet) {
    Row row = sheet.createRow(0); 
    row.setRowStyle(headerStyle);
    int columnCount = 0;
    addCellHeading(CN_NAME, row, sheet, columnCount++, 15);
    addCellHeading(CN_TYPE, row, sheet, columnCount++, 10);
    addCellHeading(CN_DESCRIPTION, row, sheet, columnCount++, 20);
    addCellHeading(CN_IDENTITY, row, sheet, columnCount++, 30);
    addCellHeading(CN_SOURCE, row, sheet, columnCount++, 30);
    addCellHeading(CN_COMMITTEE_NOTES, row, sheet, columnCount++, 30);   
  }


  private void addOperations(XSSFWorkbook excel, XSSFSheet bindings) throws FHIRFormatError, FileNotFoundException, IOException {
    ListResource list = (ListResource) parseXml(fnOperations());
    list.setText(null);
    XSSFSheet sheet = excel.createSheet(SN_OPERATIONS);
    addOperationColumns(sheet);
    int rowCount = 1;
    for (ListResourceEntryComponent li : list.getEntry()) {
      String ref = li.getItem().getReference();
      ref = ref.substring(ref.indexOf("/")+1);
      OperationDefinition opd = (OperationDefinition) parseXml(fnOpDef(ref));
      opd.setText(null);
      rowCount = addOperation(sheet, bindings, opd, rowCount);
    }
  }

  private int addOperation(XSSFSheet sheet, XSSFSheet bindings, OperationDefinition opd, int rowCount) {
    Row row = sheet.createRow(rowCount++);
    int columnCount = 0;
    addCell(opd.getCode(), row, columnCount++); 
    addCell(use(opd), row, columnCount++); 
    addCell("", row, columnCount++); 
    addCell(type(opd), row, columnCount++); 
    addCell("", row, columnCount++); 
    addCell(opd.present(), row, columnCount++); 
    addCell(opd.getDescription(), row, columnCount++); 
    addCell(opd.getComment(), row, columnCount++); 
    addCell(ext(opd, BuildExtensions.EXT_FOOTER), row, columnCount++); 
    addCell(ext(opd, BuildExtensions.EXT_FOOTER2), row, columnCount++); 
    addCell(exlist(opd, false, "1"), row, columnCount++); 
    addCell(exlist(opd, true, "1"), row, columnCount++); 
    addCell(exlist(opd, false, "2"), row, columnCount++); 
    addCell(exlist(opd, true, "2"), row, columnCount++); 
    addCell(status(opd), row, columnCount++); 
    addCell(ext(opd, BuildExtensions.EXT_COMMITTEE_NOTES), row, columnCount++);
    for (OperationDefinitionParameterComponent param : opd.getParameter()) {
      rowCount = addOpParam(sheet, bindings, opd.getCode(), param, rowCount);
    }
    return rowCount;
  }

  
  private int addOpParam(XSSFSheet sheet, XSSFSheet bindings, String path, OperationDefinitionParameterComponent param, int rowCount) {
    path = path+"."+param.getName();
    Row row = sheet.createRow(rowCount++);
    int columnCount = 0;
    addCell(path, row, columnCount++); 
    addCell(use(param), row, columnCount++); 
    addCell(""+param.getMin()+".."+param.getMax(), row, columnCount++); 
    addCell(type(param), row, columnCount++); 
    addCell(bind(param, bindings), row, columnCount++); 
    addCell(null, row, columnCount++); 
    addCell(param.getDocumentation(), row, columnCount++); 
    addCell(null, row, columnCount++); 
    addCell(null, row, columnCount++); 
    addCell(null, row, columnCount++); 
    addCell(null, row, columnCount++); 
    addCell(null, row, columnCount++); 
    addCell(status(param), row, columnCount++); 
    addCell(ext(param, BuildExtensions.EXT_COMMITTEE_NOTES), row, columnCount++);
    for (OperationDefinitionParameterComponent pp : param.getPart()) {
      rowCount = addOpParam(sheet, bindings, path, pp, rowCount);
    }
    return rowCount;
  }

  private String type(OperationDefinitionParameterComponent param) {
    if (param.hasExtension(BuildExtensions.EXT_ALLOWED_TYPE)) {
     List<String>  tl = new ArrayList<>();
     for (Extension t : param.getExtensionsByUrl(BuildExtensions.EXT_ALLOWED_TYPE)) {
       tl.add(t.getValue().primitiveValue());
     }
     return String.join(" | ", tl);
    } else {
       return param.hasType() ? param.getType().toCode()+(param.hasSearchType() ? " / "+param.getSearchType().toCode() : "") : null;
    }
  }

  private String use(OperationDefinitionParameterComponent param) {
    return param.hasUse() ? param.getUse().toCode() : null;
  }

  private String status(DomainResource dr) {
    sortExtensions(dr);
    String fmm = BuildExtensions.readStringExtension(dr,  BuildExtensions.EXT_FMM_LEVEL);
    String ss = BuildExtensions.readStringExtension(dr,  BuildExtensions.EXT_STANDARDS_STATUS);
    String nv = BuildExtensions.readStringExtension(dr,  BuildExtensions.EXT_NORMATIVE_VERSION);
    return status(fmm, ss, nv);
  }

  public String status(String fmm, String ss, String nv) {
    String nvm = "Normative".equalsIgnoreCase(ss) && !Utilities.noString(nv) ? "; from="+nv : "";
    if (Utilities.noString(fmm) && Utilities.noString(ss)) {
      return null;
    } else if (Utilities.noString(fmm)) {
      return "/"+ss+nvm;
    } else if (Utilities.noString(ss)) {
      return fmm;
    } else {
      return fmm+"/"+ss+nvm;
    }
  }

  private String status(Element e) {
    sortExtensions(e);
    String fmm = BuildExtensions.readStringExtension(e,  BuildExtensions.EXT_FMM_LEVEL);
    String ss = BuildExtensions.readStringExtension(e,  BuildExtensions.EXT_STANDARDS_STATUS);
    String nv = BuildExtensions.readStringExtension(e,  BuildExtensions.EXT_NORMATIVE_VERSION);
    return status(fmm, ss, nv);
  }

  private String exlist(OperationDefinition opd, boolean isResponse, String lcode) {
    sortExtensions(opd);
    List<String> tl = new ArrayList<>();
    for (Extension ext : opd.getExtensionsByUrl(BuildExtensions.EXT_OP_EXAMPLE)) {
      String list = ext.getExtensionString(BuildExtensions.EXT_OP_EXAMPLE_LIST);
      String resp = ext.getExtensionString(BuildExtensions.EXT_OP_EXAMPLE_RESPONSE);
      if (lcode.equals(list) && ("true".equals(resp)) == isResponse) {
        tl.add(ext.getExtensionString(BuildExtensions.EXT_OP_EXAMPLE_CONTENT));
      }
    }
    return String.join(" | ", tl);
  }

  private String type(OperationDefinition opd) {
    String s = opd.getKind().toCode();
    if (opd.getAffectsState()) {
      s = s + " | affects-state";
    }
    return s;
  }

  private String use(OperationDefinition opd) {
    List<String> tl = new ArrayList<>();
    if (opd.getSystem()) {
      tl.add("System");
    }
    if (opd.getType()) {
      tl.add("Type");
    }
    if (opd.getInstance()) {
      tl.add("Instance");
    }
    return String.join(" | ", tl);
  }

  private void addOperationColumns(XSSFSheet sheet) {
    Row row = sheet.createRow(0); 
    row.setRowStyle(headerStyle);
    int columnCount = 0;
    addCellHeading(CN_NAME, row, sheet, columnCount++, 15);
    addCellHeading(CN_USE, row, sheet, columnCount++, 10);
    addCellHeading(CN_CARD, row, sheet, columnCount++, 20);
    addCellHeading(CN_TYPE, row, sheet, columnCount++, 30);
    addCellHeading(CN_BINDING, row, sheet, columnCount++, 30);
    addCellHeading(CN_TITLE, row, sheet, columnCount++, 30);
    addCellHeading(CN_DOCUMENTATION, row, sheet, columnCount++, 30);   
    addCellHeading(CN_COMMENTS, row, sheet, columnCount++, 30);   
    addCellHeading(CN_FOOTER, row, sheet, columnCount++, 30);   
    addCellHeading(CN_FOOTER2, row, sheet, columnCount++, 30);   
    addCellHeading(CN_EXAMPLE1_REQUEST, row, sheet, columnCount++, 30);   
    addCellHeading(CN_EXAMPLE1_RESPONSE, row, sheet, columnCount++, 30);   
    addCellHeading(CN_EXAMPLE2_REQUEST, row, sheet, columnCount++, 30);   
    addCellHeading(CN_EXAMPLE2_RESPONSE, row, sheet, columnCount++, 30);   
    addCellHeading(CN_STATUS, row, sheet, columnCount++, 30);   
    addCellHeading(CN_COMMITTEE_NOTES, row, sheet, columnCount++, 30);   
  }
  
  private void addSearchParams(XSSFWorkbook excel) throws FHIRFormatError, FileNotFoundException, IOException {
    Bundle bnd = (Bundle) parseXml(fnSP());
    XSSFSheet sheet = excel.createSheet(SN_SEARCH);
    addSearchColumns(sheet);
    int rowCount = 0;
    for (BundleEntryComponent be : bnd.getEntry()) {
      rowCount++;
      addSearchParam(sheet, (SearchParameter) be.getResource(), rowCount);
    }
  }

  private void addSearchParam(XSSFSheet sheet, SearchParameter sp, int rowCount) {
    Row row = sheet.createRow(rowCount);    
    int columnCount = 0;
    addCell(sp.getCode(), row, columnCount++); 
    addCell(sp.getType().toCode(), row, columnCount++); 
    addCell(target(sp), row, columnCount++); 
    addCell(ext(sp, BuildExtensions.EXT_PATH), row, columnCount++); 
    addCell(sp.getExpression(), row, columnCount++); 
    addCell(sp.getDescription(), row, columnCount++); 
    addCell(sp.getXpath(), row, columnCount++); 
    addCell(ext(sp, BuildExtensions.EXT_COMMITTEE_NOTES), row, columnCount++);
  }

  private String target(SearchParameter sp) {
    List<String> tl = new ArrayList<>();
    for (CodeType c : sp.getTarget()) {
      tl.add(c.primitiveValue());
    }
    return String.join("|", tl);
  }

  private void addSearchColumns(XSSFSheet sheet) {
    Row row = sheet.createRow(0); 
    row.setRowStyle(headerStyle);
    int columnCount = 0;
    addCellHeading(CN_NAME, row, sheet, columnCount++, 15);
    addCellHeading(CN_TYPE, row, sheet, columnCount++, 10);
    addCellHeading(CN_TARGET_TYPES, row, sheet, columnCount++, 20);
    addCellHeading(CN_PATH, row, sheet, columnCount++, 30);
    addCellHeading(CN_EXPRESSION, row, sheet, columnCount++, 30);
    addCellHeading(CN_DESCRIPTION, row, sheet, columnCount++, 30);
    addCellHeading(CN_X_PATH, row, sheet, columnCount++, 30);
    addCellHeading(CN_COMMITTEE_NOTES, row, sheet, columnCount++, 30);   
    sheet.createFreezePane(1, 1);
  }

  private void addCell(String text, Row row, int columnCount) {
    addCell(text, row, columnCount, false);
  }
  
  private void addCell(String text, Row row, int columnCount, boolean wrap) {
    Cell cell = row.createCell(columnCount);
    if (text != null) {
      if (text.length() > 32000) {
        cell.setCellValue(text.substring(0, 32000));        
      } else {
        cell.setCellValue(text);
      }
    } else {
      cell.setCellValue("");
    }
    if (wrap) {
      cell.setCellStyle(wrapStyle);
    } else if (columnCount == 0) {
      cell.setCellStyle(boldStyle);
    }
  }

  private void addCellHeading(String title, Row row, XSSFSheet sheet, int columnCount, int width) {
    Cell cell = row.createCell(columnCount);
    cell.setCellValue(title);
    cell.setCellStyle(headerStyle);
    sheet.setColumnWidth(columnCount, width * 255);
  }
  
  protected boolean isTestingClass() {
    return false;
  }
 
}
