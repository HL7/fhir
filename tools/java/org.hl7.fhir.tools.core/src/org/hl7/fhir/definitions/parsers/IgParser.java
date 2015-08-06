package org.hl7.fhir.definitions.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.definitions.generators.specification.ToolResourceUtilities;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.ConstraintStructure;
import org.hl7.fhir.definitions.model.Dictionary;
import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.Example.ExampleType;
import org.hl7.fhir.definitions.model.ImplementationGuideDefn;
import org.hl7.fhir.definitions.model.LogicalModel;
import org.hl7.fhir.definitions.model.MappingSpace;
import org.hl7.fhir.definitions.model.Operation;
import org.hl7.fhir.definitions.model.Profile;
import org.hl7.fhir.definitions.model.Profile.ConformancePackageSourceType;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.ConceptMap;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.DataElement;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.ImplementationGuide;
import org.hl7.fhir.instance.model.ImplementationGuide.GuideDependencyType;
import org.hl7.fhir.instance.model.ImplementationGuide.GuidePageKind;
import org.hl7.fhir.instance.model.ImplementationGuide.GuideResourcePurpose;
import org.hl7.fhir.instance.model.ImplementationGuide.ImplementationGuideDependencyComponent;
import org.hl7.fhir.instance.model.ImplementationGuide.ImplementationGuidePackageComponent;
import org.hl7.fhir.instance.model.ImplementationGuide.ImplementationGuidePackageResourceComponent;
import org.hl7.fhir.instance.model.ImplementationGuide.ImplementationGuidePageComponent;
import org.hl7.fhir.instance.model.OperationOutcome.IssueType;
import org.hl7.fhir.instance.model.NamingSystem;
import org.hl7.fhir.instance.model.OperationDefinition;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.SearchParameter;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.TestScript;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.utils.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.Logger.LogMessageType;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class IgParser {

  private Logger logger;
  private WorkerContext context;
  private Calendar genDate;
  private ProfileKnowledgeProvider pkp;
  private Map<String, BindingSpecification> commonBindings;
  private Map<String, MappingSpace> mappings;
  private String committee;

  public IgParser(Logger logger, WorkerContext context, Calendar genDate, ProfileKnowledgeProvider pkp, Map<String, BindingSpecification> commonBindings, String committee, Map<String, MappingSpace> mappings) {
    super();
    this.logger = logger;
    this.context = context;
    this.genDate = genDate;
    this.pkp = pkp;
    this.commonBindings = commonBindings;
    this.committee = committee;
    this.mappings = mappings;
  }

  public void load(String rootDir, ImplementationGuideDefn igd, List<ValidationMessage> issues, Set<String> loadedIgs) throws Exception {
    logger.log(" ..."+igd.getName(), LogMessageType.Process);
    
    // first: parse the IG, then use it 
    String myRoot = Utilities.path(rootDir, "guides", igd.getCode());
    CSFile file = new CSFile(Utilities.path(rootDir, igd.getSource()));
    ImplementationGuide ig = (ImplementationGuide) new XmlParser().parse(new FileInputStream(file));
    if (!ig.getUrl().startsWith("http://hl7.org/fhir/")) // for things published in the hl7.org/fhir namespace...
      throw new Exception("Illegal namespace");
    if (!ig.getUrl().equals("http://hl7.org/fhir/"+ig.getId()))
      throw new Exception("Illegal URL");
    if (!ig.hasName())
      throw new Exception("no name on IG");
    ig.setDateElement(new DateTimeType(genDate));
    igd.setName(ig.getName());
    igd.setIg(ig);
    
    Map<String, Resource> resources = new HashMap<String, Resource>();

    for (ImplementationGuideDependencyComponent d : ig.getDependency()) {
      if (d.getType() != GuideDependencyType.REFERENCE)
        throw new Exception("Unsupported dependency type on "+ig.getName()+": "+d.getType().toCode());
      if (!loadedIgs.contains(d.getUri()))
        throw new Exception("Dependency on "+ig.getName()+" not satisfied: "+d.getUri());
    }
    loadedIgs.add(ig.getUrl());
    for (UriType bin : ig.getBinary()) {
      if (!new File(Utilities.path(myRoot, bin.getValue())).exists()) 
        throw new Exception("Binary dependency in "+ig.getName()+" not found: "+bin.getValue());
      igd.getImageList().add(bin.getValue());
    }
    processPage(ig.getPage(), igd);
    igd.setPage(ig.getPage().getSource());

    for (ImplementationGuidePackageComponent p : ig.getPackage()) {
      if (!p.hasName())
        throw new Exception("no name on package in IG "+ig.getName());

      // first pass - verify the resources can be loaded
      for (ImplementationGuidePackageResourceComponent r : p.getResource()) {
        if (!r.hasSource())
          throw new Exception("no source on resource in package "+p.getName()+" in IG "+ig.getName());
        File fn = new File(Utilities.path(myRoot, r.getSourceUriType().getValue()));
        if (!fn.exists())
          throw new Exception("Source "+r.getSourceUriType().getValue()+" resource in package "+p.getName()+" in IG "+ig.getName()+" could not be located");

        String id = Utilities.changeFileExt(fn.getName(), "");
        
        if (r.getPurpose() == GuideResourcePurpose.EXAMPLE) {
          if (!r.hasName()) // which means that non conformance resources must be named
            throw new Exception("no name on resource in package "+p.getName()+" in IG "+ig.getName());
          Example example = new Example(r.getName(), id, r.getDescription(), fn, false, ExampleType.XmlFile, false);
          example.setIg(igd.getCode());
          igd.getExamples().add(example);
        } else if (r.getPurpose() == GuideResourcePurpose.TERMINOLOGY) {
          ValueSet vs = (ValueSet) new XmlParser().parse(new FileInputStream(fn));
//          if (id.contains(File.separator))
            if (id.startsWith("valueset-"))
              id = id.substring(9);
          vs.setId(id);
          vs.setUrl("http://hl7.org/fhir/ValueSet/"+id);
          vs.setUserData(ToolResourceUtilities.NAME_RES_IG, igd);
          vs.setUserData("path", igd.getPath()+"valueset-"+id+".html");
          vs.setUserData("filename", "valueset-"+id);
          vs.setUserData("committee", committee);
          igd.getValueSets().add(vs);
          if (!r.hasName())
            r.setName(vs.getName());
          if (!r.hasDescription())
            r.setDescription(vs.getDescription());
          r.setUserData(ToolResourceUtilities.RES_ACTUAL_RESOURCE, vs);
          r.setSource(new UriType(fn.getName()));
        } else if (r.getPurpose() == GuideResourcePurpose.PROFILE) {
          throw new Error("Not implemented yet");
        } else if (r.getPurpose() == GuideResourcePurpose.DICTIONARY) {
          Dictionary d = new Dictionary(id, r.getName(), igd.getCode(), fn.getAbsolutePath(), igd);
          igd.getDictionaries().add(d);
        }


        //        if (r.hasExampleFor()) {
        //          if (!resources.containsKey(r.getExampleFor().getReference()))
        //            throw new Exception("Unable to resolve example-for reference to "+r.getExampleFor().getReference());
        //        }

      }
      // second pass: load the spreadsheets
      for (Extension ex : p.getExtension()) {
        if (ex.getUrl().equals(ToolResourceUtilities.EXT_PROFILE_SPREADSHEET)) {
          String s = ((UriType) ex.getValue()).getValue();
          File fn = new File(Utilities.path(myRoot, s));
          if (!fn.exists())
            throw new Exception("Spreadsheet "+s+" in package "+p.getName()+" in IG "+ig.getName()+" could not be located");          
          Profile pr = new Profile(igd.getCode());
          ex.setUserData(ToolResourceUtilities.NAME_RES_PROFILE, pr);
          pr.setSource(fn.getAbsolutePath());
          pr.setSourceType(ConformancePackageSourceType.Spreadsheet);
          SpreadsheetParser sparser = new SpreadsheetParser(pr.getCategory(), new CSFileInputStream(pr.getSource()), Utilities.noString(pr.getId()) ? pr.getSource() : pr.getId(), igd, 
                rootDir, logger, null, context.getVersion(), context, genDate, false, igd.getExtensions(), pkp, false, committee, mappings);
          sparser.getBindings().putAll(commonBindings);
          sparser.setFolder(Utilities.getDirectoryForFile(pr.getSource()));
          sparser.parseConformancePackage(pr, null, Utilities.getDirectoryForFile(pr.getSource()), pr.getCategory(), issues);
//          System.out.println("load "+pr.getId()+" from "+s);
          igd.getProfiles().add(pr);
          // what remains to be done now is to update the package with the loaded resources, but we need to wait for all the profiles to generated, so we'll do that later
          for (BindingSpecification bs : sparser.getBindings().values()) {
            if (!commonBindings.containsValue(bs) && bs.getValueSet() != null) {
              ValueSet vs  = bs.getValueSet();
              String path = vs.getUserString("path");
              path = path.substring(path.lastIndexOf("/")+1);              
              ig.getPackage().get(0).addResource().setName(vs.getName()).setDescription(vs.getDescription()).setSource(new UriType(path)).setUserData(ToolResourceUtilities.RES_ACTUAL_RESOURCE, vs);
            }
          }
        }
        if (ex.getUrl().equals(ToolResourceUtilities.EXT_LOGICAL_SPREADSHEET)) {
          File fn = new CSFile(Utilities.path(myRoot, ((UriType) ex.getValue()).getValue()));
//          String source = Utilities.path(file.getParent(), e.getAttribute("source"));
          String s = fn.getName();
          if (s.endsWith("-spreadsheet.xml"))
            s = s.substring(0, s.length()-16);
          String id = igd.getCode()+"-"+s;
          SpreadsheetParser sparser = new SpreadsheetParser(igd.getCode(), new CSFileInputStream(fn), id, igd, rootDir, logger, null, context.getVersion(), context, genDate, false, igd.getExtensions(), pkp, false, committee, mappings);
          sparser.getBindings().putAll(commonBindings);
          sparser.setFolder(Utilities.getDirectoryForFile(fn.getAbsolutePath()));
          LogicalModel lm = sparser.parseLogicalModel();
          lm.setId(id);
          lm.setSource(fn.getAbsolutePath());
          lm.getResource().setName(lm.getId());
          igd.getLogicalModels().add(lm);
        }
      }
    }
    
//    // second, parse the old ig, and use that. This is being phased out
//    CSFile file = new CSFile(Utilities.path(rootDir, igd.getSource()));
//    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//    factory.setNamespaceAware(true); 
//    DocumentBuilder builder = factory.newDocumentBuilder();
//    Document xdoc = builder.parse(new CSFileInputStream(file));
//    Element root = xdoc.getDocumentElement();
//    if (!root.getNodeName().equals("ig")) 
//      throw new Exception("wrong base node");
//    Element e = XMLUtil.getFirstChild(root);
//    while (e != null) {
//      if (e.getNodeName().equals("dependsOn")) {
//        // we ignore this for now
//      } else if (e.getNodeName().equals("publishing")) {
////        if (e.hasAttribute("homepage")) 
////          igd.setPage(e.getAttribute("homepage"));
//      } else if (e.getNodeName().equals("page")) {
////        igd.getPageList().add(e.getAttribute("source"));
//      } else if (e.getNodeName().equals("image")) {
////        moved above igd.getImageList().add(e.getAttribute("source"));
//      } else if (e.getNodeName().equals("valueset")) {
////        XmlParser xml = new XmlParser();
////        ValueSet vs = (ValueSet) xml.parse(new CSFileInputStream(Utilities.path(file.getParent(), e.getAttribute("source"))));
////        String id = Utilities.changeFileExt(new File(Utilities.path(file.getParent(), e.getAttribute("source"))).getName(), "");
////        if (id.startsWith("valueset-"))
////          id = id.substring(9);
////        if (!vs.hasId() || !vs.hasUrl()) {
////          vs.setId(id);
////          vs.setUrl("http://hl7.org/fhir/ValueSet/"+vs.getId());
////        }
////        vs.setUserData(ToolResourceUtilities.NAME_RES_IG, igd);
////        vs.setUserData("path", igd.getCode()+File.separator+"valueset-"+vs.getId()+".html");
////        vs.setUserData("filename", "valueset-"+vs.getId());
////        vs.setUserData("committee", committee);
////        igd.getValueSets().add(vs);
//      } else if (e.getNodeName().equals("acronym")) {
//        igd.getTlas().put(e.getAttribute("target"), e.getAttribute("id"));        
//      } else if (e.getNodeName().equals("example")) {
////        String filename = e.getAttribute("source");
////        File efile = new File(Utilities.path(file.getParent(), filename));
////        Example example = new Example(e.getAttribute("name"), Utilities.changeFileExt(efile.getName(), ""), e.getAttribute("name"), efile, false, ExampleType.XmlFile, false);
////        example.setIg(igd.getCode());
////        igd.getExamples().add(example);
//      } else if (e.getNodeName().equals("profile")) {
////        moved above
////        Profile p = new Profile(igd.getCode());
////        p.setSource(Utilities.path(file.getParent(), e.getAttribute("source")));
////        if ("spreadsheet".equals(e.getAttribute("type"))) {
////          p.setSourceType(ConformancePackageSourceType.Spreadsheet);
////          SpreadsheetParser sparser = new SpreadsheetParser(p.getCategory(), new CSFileInputStream(p.getSource()), Utilities.noString(p.getId()) ? p.getSource() : p.getId(), igd, 
////              rootDir, logger, null, context.getVersion(), context, genDate, false, igd.getExtensions(), pkp, false, committee, mappings);
////          sparser.getBindings().putAll(commonBindings);
////          sparser.setFolder(Utilities.getDirectoryForFile(p.getSource()));
////          sparser.parseConformancePackage(p, null, Utilities.getDirectoryForFile(p.getSource()), p.getCategory(), issues);
////          for (BindingSpecification bs : sparser.getBindings().values()) {
////            if (!commonBindings.containsValue(bs) && bs.getValueSet() != null) {
////              ValueSet vs  = bs.getValueSet();
////              String path = vs.getUserString("filename")+".xml";
////              ig.getPackage().get(0).addResource().setName(vs.getName()).setDescription(vs.getDescription()).setSource(new UriType(path)).setUserData(ToolResourceUtilities.RES_ACTUAL_RESOURCE, vs);
////            }
////          }
////        } else {
////          throw new Exception("Unknown profile type in IG : "+e.getNodeName());
////          // parseConformanceDocument(p, p.getId(), new File(p.getSource()), p.getCategory());    
////        } 
////        
////        String id = e.getAttribute("id"); 
////        if (Utilities.noString(id))
////          id = Utilities.changeFileExt(e.getAttribute("source"), "");
////        igd.getProfiles().add(p);
////        Element ex = XMLUtil.getFirstChild(e);
////        while (ex != null) {
////          if (ex.getNodeName().equals("example")) {
////            String filename = ex.getAttribute("source");
////            Example example = new Example(ex.getAttribute("name"), Utilities.changeFileExt(Utilities.getFileNameForName(filename), ""), ex.getAttribute("name"), new File(Utilities.path(file.getParent(), filename)), false, ExampleType.XmlFile, false);
////            p.getExamples().add(example);
////          } else
////            throw new Exception("Unknown element name in IG: "+ex.getNodeName());
////          ex = XMLUtil.getNextSibling(ex);
////        }
//      } else if (e.getNodeName().equals("dictionary")) {
////        Dictionary d = new Dictionary(e.getAttribute("id"), e.getAttribute("name"), igd.getCode(), Utilities.path(Utilities.path(file.getParent(), e.getAttribute("source"))), igd);
////        igd.getDictionaries().add(d);
//      } else if (e.getNodeName().equals("logicalModel")) {
////        String source = Utilities.path(file.getParent(), e.getAttribute("source"));
////        String id = igd.getCode()+"-"+e.getAttribute("id");
////        SpreadsheetParser sparser = new SpreadsheetParser(igd.getCode(), new CSFileInputStream(source), id, igd, rootDir, logger, null, context.getVersion(), context, genDate, false, igd.getExtensions(), pkp, false, committee, mappings);
////        sparser.getBindings().putAll(commonBindings);
////        sparser.setFolder(Utilities.getDirectoryForFile(source));
////        LogicalModel lm = sparser.parseLogicalModel(source);
////        lm.setId(id);
////        lm.setSource(source);
////        lm.getResource().setName(lm.getId());
////        igd.getLogicalModels().add(lm);
//      } else
//        throw new Exception("Unknown element name in IG: "+e.getNodeName());
//      e = XMLUtil.getNextSibling(e);
//    }    
  }

  private void processPage(ImplementationGuidePageComponent page, ImplementationGuideDefn igd) throws Exception {
    if (!page.hasName())
      throw new Exception("Page "+page.getSource()+" has no name");
    if (page.getKind() == GuidePageKind.PAGE || page.getKind() == GuidePageKind.DIRECTORY || page.getKind() == GuidePageKind.LIST) {
      checkExists(igd, page.getSource());
      igd.getPageList().add(page.getSource());
    }
    for (ImplementationGuidePageComponent pp : page.getPage()) {
      processPage(pp, igd);
    }
  }

  private void checkExists(ImplementationGuideDefn igd, String source) throws Exception {
    File f = new File(Utilities.path(igd.getPath(), source));
    if (f.exists())
      throw new Exception("Unable to find file "+source);
    
  }
  
  
}
