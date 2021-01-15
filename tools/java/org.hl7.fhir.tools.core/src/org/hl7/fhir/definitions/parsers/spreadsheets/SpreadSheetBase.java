package org.hl7.fhir.definitions.parsers.spreadsheets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.r5.context.BaseWorkerContext;
import org.hl7.fhir.r5.formats.IParser.OutputStyle;
import org.hl7.fhir.r5.formats.XmlParser;
import org.hl7.fhir.r5.model.DomainResource;
import org.hl7.fhir.r5.model.Element;
import org.hl7.fhir.r5.model.Extension;
import org.hl7.fhir.r5.model.Resource;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.Utilities;

public class SpreadSheetBase {
  
  public class ExtensionSorter implements Comparator<Extension> {

    @Override
    public int compare(Extension o1, Extension o2) {
      if (!o1.getUrl().equals(o2.getUrl())) {
        return o1.getUrl().compareTo(o2.getUrl());
      } else if (o1.hasExtension() || o2.hasExtension()) {
        return urls(o1).compareTo(urls(o2));
      } else if (!o1.getValue().fhirType().equals(o2.getValue().fhirType())) {
        return o1.getUrl().compareTo(o2.getUrl());
      } else if (o1.getValue().isPrimitive()) {
        return o1.getValue().primitiveValue().compareTo(o2.getValue().primitiveValue());
      } else {
        return 0;
      }
    }

    private String urls(Extension src) {
      List<String> urls = new ArrayList<>();
      for (Extension ext : src.getExtension()) {
        urls.add(ext.getUrl());
      }
      Collections.sort(urls);
      return String.join("|", urls);
    }

  }

  private String srcFolder; 
  protected String resourceName;
  protected long date;
  protected BaseWorkerContext context;
  
  protected static final String CN_TARGET_TYPES = "Target Types";
  protected static final String CN_EXAMPLE1_RESPONSE = "Example.Response";
  protected static final String CN_EXAMPLE1_REQUEST = "Example.Request";
  protected static final String CN_EXAMPLE2_RESPONSE = "Example2.Response";
  protected static final String CN_EXAMPLE2_REQUEST = "Example2.Request";
  protected static final String CN_FOOTER2 = "Footer2";
  protected static final String CN_FOOTER = "Footer";
  protected static final String CN_DOCUMENTATION = "Documentation";
  protected static final String CN_USE = "Use";
  protected static final String CN_SOURCE = "Source";
  protected static final String CN_IDENTITY = "Identity";
  protected static final String CN_NOTES = "Notes";
  protected static final String CN_INTRODUCTION = "Introduction";
  protected static final String CN_WORK_GROUP = "Work Group";
  protected static final String CN_FMM = "FMM";
  protected static final String CN_DATE = "Date";
  protected static final String CN_EXPERIMENTAL = "Experimental";
  protected static final String CN_PUBLISHER = "Publisher";
  protected static final String CN_VERSION = "Version";
  protected static final String CN_TITLE = "Title";
  protected static final String CN_CODE = "Code";
  protected static final String CN_BEST_PRACTICE_COMMENT = "Best Practice Comment";
  protected static final String CN_X_PATH = "XPath";
  protected static final String CN_EXPRESSION = "Expression";
  protected static final String CN_ENGLISH = "English";
  protected static final String CN_CONTEXT = "Context";
  protected static final String CN_BEST_PRACTICE = "Best Practice";
  protected static final String CN_SEVERITY = "Severity";
  protected static final String CN_NAME = "Name";
  protected static final String CN_ID = "Id";
  protected static final String CN_COPYRIGHT = "Copyright";
  protected static final String CN_V3 = "v3";
  protected static final String CN_V2 = "v2";
  protected static final String CN_WEBSITE_EMAIL = "Website/Email";
  protected static final String CN_URI = "URI";
  protected static final String CN_OID = "OID";
  protected static final String CN_DESCRIPTION = "Description";
  protected static final String CN_VALUE_SET = "ValueSet";
  protected static final String CN_STRENGTH = "Strength";
  protected static final String CN_BINDING_NAME = "Binding Name";
  protected static final String CN_COMMITTEE_NOTES = "Committee Notes";
  protected static final String CN_DISPLAY_HINT = "Display Hint";
  protected static final String CN_UML = "UML";
  protected static final String CN_STATUS = "Status";
  protected static final String CN_COMMENTS = "Comments";
  protected static final String CN_REQUIREMENTS = "Requirements";
  protected static final String CN_DEFINITION = "Definition";
  protected static final String CN_SHORT_NAME = "Short Name";
  protected static final String CN_MISSING_MEANING = "Missing Meaning";
  protected static final String CN_EXAMPLE = "Example";
  protected static final String CN_EXTENSION = "Extension";
  protected static final String CN_PROFILE = "Profile";
  protected static final String CN_BINDING = "Binding";
  protected static final String CN_SUMMARY = "Summary";
  protected static final String CN_SEARCH_PARAMETER = "Search Parameter";
  protected static final String CN_MODIFIER_REASON = "Modifier Reason";
  protected static final String CN_IS_MODIFIER = "Is Modifier";
  protected static final String CN_TYPE = "Type";
  protected static final String CN_HIERARCHY = "Hierarchy";
  protected static final String CN_INV = "Inv.";
  protected static final String CN_CARD = "Card.";
  protected static final String CN_ALIASES = "Aliases";
  protected static final String CN_PATH = "Path";
  protected static final String CN_VALUE = "Value";
  protected static final String SN_INVARIANTS = "Invariants";
  protected static final String SN_BINDINGS = "Bindings";
  protected static final String SN_RESOURCE = "Resource";
  protected static final String SN_EXAMPLES = "Examples";
  protected static final String SN_SEARCH = "Search";
  protected static final String SN_OPERATIONS = "Operations";
  
  public SpreadSheetBase(BaseWorkerContext context, String srcFolder, String resourceName) {
    super();
    this.context = context;
    this.srcFolder = srcFolder;
    this.resourceName = resourceName;
  }
  
  private String srcPath(String filename) throws IOException {
    return Utilities.path(srcFolder, resourceName.toLowerCase(), filename);
  }
  
  protected String fnSpreadsheet() throws IOException {
    return srcPath(resourceName.toLowerCase()+"-spreadsheet.xlsx");
  }
  
  protected String fnSD() throws IOException {
    return srcPath("structuredefinition-"+resourceName+".xml");
  }

  protected String fnPacks() throws IOException {
    return srcPath("list-"+resourceName+"-packs.xml");
  }
  
  protected String fnExamples() throws IOException {
    return srcPath("list-"+resourceName+"-examples.xml");
  }
  
  protected String fnOperations() throws IOException {
    return srcPath("list-"+resourceName+"-operations.xml");
  }
  
  protected String fnSP() throws IOException {
    return srcPath("bundle-"+resourceName+"-search-params.xml");
  }
  protected String fnExt(String id) throws IOException {
    return srcPath("structuredefinition-extension-"+id+".xml");
  }
  
  protected String fnIG(String ref) throws IOException { 
    return srcPath("implementationguide-"+ref+".xml");
  }
  
  protected String fnOpDef(String ref) throws IOException { 
    return srcPath("operationdefinition-"+ref+".xml");
  }
  
  
//  protected String rnLower() {
//     return resourceName.toLowerCase();
//  }
//  
//  protected String rn() {
//    return resourceName;
//  }

  protected Resource parseXml(String fn) throws FHIRFormatError, FileNotFoundException, IOException {
//    String fn = Utilities.path(srcFolder, resourceName.toLowerCase(), name);
    File f = new CSFile(fn);
    long d = f.lastModified();
    if (d == 0) {
      f.setLastModified(new Date().getTime());
      d = f.lastModified();
    }
    if (useLoadingDates()) {
      date = Long.max(date, d);
    }
    CSFileInputStream fs = new CSFileInputStream(f);
    try {
      return new XmlParser().parse(fs);
    } finally {
      fs.close();
    }
  }

  protected boolean useLoadingDates() {
    return true;
  }

  protected void saveXml(String fn, Resource res) throws IOException {
    File f = new CSFile(fn);
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(f), res);
    f.setLastModified(date);    
  }
  

  protected void sortExtensions(Element e) {
    e.getExtension().removeIf(ext -> !ext.hasValue() && !e.hasExtension());
    Collections.sort(e.getExtension(), new ExtensionSorter());
  }

  protected void sortExtensions(DomainResource e) {
    e.getExtension().removeIf(ext -> !ext.hasValue() && !e.hasExtension());
    Collections.sort(e.getExtension(), new ExtensionSorter());
  }
}
