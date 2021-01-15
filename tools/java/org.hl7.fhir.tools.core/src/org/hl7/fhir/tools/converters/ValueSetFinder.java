package org.hl7.fhir.tools.converters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.hl7.fhir.definitions.generators.specification.ToolResourceUtilities;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.definitions.parsers.CodeListToValueSetParser;
import org.hl7.fhir.definitions.parsers.ValueSetGenerator;
import org.hl7.fhir.definitions.parsers.spreadsheets.BindingsParser;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.formats.IParser.OutputStyle;
import org.hl7.fhir.r5.formats.XmlParser;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.CodeType;
import org.hl7.fhir.r5.model.Constants;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.r5.model.Enumerations.BindingStrength;
import org.hl7.fhir.r5.model.Enumerations.PublicationStatus;
import org.hl7.fhir.r5.terminologies.CodeSystemUtilities;
import org.hl7.fhir.r5.terminologies.ValueSetUtilities;
import org.hl7.fhir.r5.utils.ToolingExtensions;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xls.XLSXmlParser;
import org.hl7.fhir.utilities.xls.XLSXmlParser.Sheet;

public class ValueSetFinder {
  
  public static void main(String[] args) throws Exception {
    visitSpreadSheets(new File(args[0]));    
  }

  private static void visitSpreadSheets(File fd) throws Exception {
    for (File f : fd.listFiles()) {
      if (f.isDirectory()) {
        visitSpreadSheets(f);
      } else if (f.getName().endsWith("-spreadsheet.xml")) {
        checkBindings(f);
      }
    }
    
  }

  private static void checkBindings(File f) throws Exception {
    System.out.println(f.getName());
    XLSXmlParser xls = new XLSXmlParser(new FileInputStream(f), f.getName());
    Sheet sheet = xls.getSheets().get("Bindings");
    if (sheet != null) {
      String sfx = "";
      processSheet(xls, sheet, Utilities.getDirectoryForFile(f.getAbsolutePath()), sfx);
    }    
  }

  private static void processSheet(XLSXmlParser xls, Sheet sheet, String dir, String sfx) throws Exception {
    for (int row = 0; row < sheet.rows.size(); row++) {
      String bindingName = sheet.getColumn(row, "Binding Name");
      if (Utilities.noString(bindingName) || bindingName.startsWith("!")) continue;

      BindingMethod method = BindingsParser.readBinding(sheet.getColumn(row, "Binding"), "??");
      String ref = sheet.getColumn(row, "Reference");
      if (method == BindingMethod.CodeList) {
        if (ref.startsWith("#valueset-"))
          throw new Exception("don't start code list references with #valueset-");
        String vfn = Utilities.path(dir, "valueset-"+sfx+ref.substring(1)+".xml");
        String cfn = Utilities.path(dir, "codesystem-"+sfx+ref.substring(1)+".xml");
        File vf = new File(vfn);
        File cf = new File(cfn);
        if (!vf.exists()) {
          System.out.println("Produce "+vfn);
          ValueSet vs = ValueSetUtilities.makeShareable(new ValueSet());
          vs.setVersion(Constants.VERSION);
          vs.setId(sfx+ref.substring(1));
          vs.setUrl("http://hl7.org/fhir/ValueSet/"+sfx+ref.substring(1));
          vs.setDescription(sheet.getColumn(row, "Description"));
          vs.setName(bindingName);
          vs.setStatus(PublicationStatus.fromCode(sheet.getColumn(row, "Status")));
          vs.setCopyright(sheet.getColumn(row, "Copyright"));
          
          Sheet css = xls.getSheets().get(ref.substring(1));
          if (css == null) {
            throw new Exception("Error parsing binding "+bindingName+": code list reference '"+ref+"' not resolved");
          }

          CodeSystem cs = CodeSystemUtilities.makeShareable(new CodeSystem());
          cs.setVersion(Constants.VERSION);
          cs.setId(sfx+ref.substring(1));
          cs.setUrl("http://hl7.org/fhir/"+sfx+ref.substring(1));          
          cs.setDescription(sheet.getColumn(row, "Description"));
          cs.setName(bindingName);
          cs.setStatus(PublicationStatus.fromCode(sheet.getColumn(row, "Status")));
          cs.setCopyright(sheet.getColumn(row, "Copyright"));
          
          vs.getCompose().addInclude().setSystem(cs.getUrl());
          
          processCodes(cs, css);
          new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(vf), vs);
          new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(cf), cs);
        }
      }
  }


  }

  private static void processCodes(CodeSystem cs, Sheet sheet) throws Exception {
    Map<String, ConceptDefinitionComponent> concepts = new HashMap<>();
    for (int row = 0; row < sheet.rows.size(); row++) {
      if (Utilities.noString(sheet.getColumn(row, "System"))) {

        ConceptDefinitionComponent cc = new ConceptDefinitionComponent(); 
        cc.setUserData("id", sheet.getColumn(row, "Id"));
        cc.setCode(sheet.getColumn(row, "Code"));
        concepts.put(cc.getCode(),  cc);
        cc.setDisplay(sheet.getColumn(row, "Display"));
        if (sheet.getColumn(row, "Abstract").toUpperCase().equals("Y"))
          CodeSystemUtilities.setNotSelectable(cs, cc);
        if (cc.hasCode() && !cc.hasDisplay())
          cc.setDisplay(Utilities.humanize(cc.getCode()));
        cc.setDefinition(Utilities.appendPeriod(sheet.getColumn(row, "Definition")));
        if (!Utilities.noString(sheet.getColumn(row, "Comment")))
          ToolingExtensions.addCSComment(cc, sheet.getColumn(row, "Comment"));
        String parent = sheet.getColumn(row, "Parent");
        if (Utilities.noString(parent))
          cs.addConcept(cc);
        else {
          concepts.get(parent).addConcept(cc);
        }
      }
    }
  }
  
  
}
