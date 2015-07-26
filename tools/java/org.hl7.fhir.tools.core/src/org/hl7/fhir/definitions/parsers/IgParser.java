package org.hl7.fhir.definitions.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.definitions.generators.specification.ToolResourceUtilities;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.Dictionary;
import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.Example.ExampleType;
import org.hl7.fhir.definitions.model.ImplementationGuideDefn;
import org.hl7.fhir.definitions.model.LogicalModel;
import org.hl7.fhir.definitions.model.Profile;
import org.hl7.fhir.definitions.model.Profile.ConformancePackageSourceType;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.ImplementationGuide;
import org.hl7.fhir.instance.model.ImplementationGuide.GuideDependencyType;
import org.hl7.fhir.instance.model.ImplementationGuide.ImplementationGuideDependencyComponent;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.utils.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.instance.validation.ValidationMessage;
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
  private String committee;

  public IgParser(Logger logger, WorkerContext context, Calendar genDate, ProfileKnowledgeProvider pkp, Map<String, BindingSpecification> commonBindings, String committee) {
    super();
    this.logger = logger;
    this.context = context;
    this.genDate = genDate;
    this.pkp = pkp;
    this.commonBindings = commonBindings;
    this.committee = committee;
  }

  public void load(String rootDir, ImplementationGuideDefn igd, List<ValidationMessage> issues, Set<String> loadedIgs) throws Exception {
    logger.log(" ..."+igd.getName(), LogMessageType.Process);
    
    // first: parse the IG, then use it 
    String myRoot = Utilities.path(rootDir, "guides", igd.getCode());
    ImplementationGuide ig = (ImplementationGuide) new XmlParser().parse(new FileInputStream(Utilities.path(myRoot, "ig.xml")));
    if (!ig.getUrl().startsWith("http://hl7.org/fhir/")) // for things published in the hl7.org/fhir namespace...
      throw new Exception("Illegal namespace");
    if (!ig.getUrl().equals("http://hl7.org/fhir/"+ig.getId()))
      throw new Exception("Illegal URL");
    if (!ig.hasName())
      throw new Exception("no name on IG");
    ig.setDateElement(new DateTimeType(genDate));
    igd.setName(ig.getName());
    
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
    
    
    
    // second, parse the old ig, and use that. This is being phased out
    CSFile file = new CSFile(Utilities.path(rootDir, igd.getSource()));
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true); 
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document xdoc = builder.parse(new CSFileInputStream(file));
    Element root = xdoc.getDocumentElement();
    if (!root.getNodeName().equals("ig")) 
      throw new Exception("wrong base node");
    Element e = XMLUtil.getFirstChild(root);
    while (e != null) {
      if (e.getNodeName().equals("dependsOn")) {
        // we ignore this for now
      } else if (e.getNodeName().equals("publishing")) {
        if (e.hasAttribute("homepage"))
          igd.setPage(e.getAttribute("homepage"));
      } else if (e.getNodeName().equals("page")) {
        igd.getPageList().add(e.getAttribute("source"));
      } else if (e.getNodeName().equals("image")) {
//        moved above igd.getImageList().add(e.getAttribute("source"));
      } else if (e.getNodeName().equals("valueset")) {
        XmlParser xml = new XmlParser();
        ValueSet vs = (ValueSet) xml.parse(new CSFileInputStream(Utilities.path(file.getParent(), e.getAttribute("source"))));
        String id = Utilities.changeFileExt(new File(Utilities.path(file.getParent(), e.getAttribute("source"))).getName(), "");
        if (id.startsWith("valueset-"))
          id = id.substring(9);
        if (!vs.hasId() || !vs.hasUrl()) {
          vs.setId(id);
          vs.setUrl("http://hl7.org/fhir/ValueSet/"+vs.getId());
        }
        vs.setUserData(ToolResourceUtilities.NAME_RES_IG, igd);
        vs.setUserData("path", igd.getCode()+File.separator+"valueset-"+vs.getId()+".html");
        vs.setUserData("filename", "valueset-"+vs.getId());
        vs.setUserData("committee", committee);
        igd.getValueSets().add(vs);
      } else if (e.getNodeName().equals("acronym")) {
        igd.getTlas().put(e.getAttribute("target"), e.getAttribute("id"));        
      } else if (e.getNodeName().equals("example")) {
        String filename = e.getAttribute("source");
        File efile = new File(Utilities.path(file.getParent(), filename));
        Example example = new Example(e.getAttribute("name"), Utilities.changeFileExt(efile.getName(), ""), e.getAttribute("name"), efile, false, ExampleType.XmlFile, false);
        example.setIg(igd.getCode());
        igd.getExamples().add(example);
      } else if (e.getNodeName().equals("profile")) {
        Profile p = new Profile(igd.getCode());
        p.setSource(Utilities.path(file.getParent(), e.getAttribute("source")));
        if ("spreadsheet".equals(e.getAttribute("type"))) {
          p.setSourceType(ConformancePackageSourceType.Spreadsheet);
          SpreadsheetParser sparser = new SpreadsheetParser(p.getCategory(), new CSFileInputStream(p.getSource()), Utilities.noString(p.getId()) ? p.getSource() : p.getId(), igd, 
              rootDir, logger, null, context.getVersion(), context, genDate, false, igd.getExtensions(), pkp, false, committee);
          sparser.getBindings().putAll(commonBindings);
          sparser.setFolder(Utilities.getDirectoryForFile(p.getSource()));
          sparser.parseConformancePackage(p, null, Utilities.getDirectoryForFile(p.getSource()), p.getCategory(), issues);
        } else {
          throw new Exception("Unknown profile type in IG: "+e.getNodeName());
          // parseConformanceDocument(p, p.getId(), new File(p.getSource()), p.getCategory());    
        } 
        
        String id = e.getAttribute("id");
        if (Utilities.noString(id))
          id = Utilities.changeFileExt(e.getAttribute("source"), "");
        igd.getProfiles().add(p);
        Element ex = XMLUtil.getFirstChild(e);
        while (ex != null) {
          if (ex.getNodeName().equals("example")) {
            String filename = ex.getAttribute("source");
            Example example = new Example(ex.getAttribute("name"), Utilities.changeFileExt(Utilities.getFileNameForName(filename), ""), ex.getAttribute("name"), new File(Utilities.path(file.getParent(), filename)), false, ExampleType.XmlFile, false);
            p.getExamples().add(example);
          } else
            throw new Exception("Unknown element name in IG: "+ex.getNodeName());
          ex = XMLUtil.getNextSibling(ex);
        }
      } else if (e.getNodeName().equals("dictionary")) {
        Dictionary d = new Dictionary(e.getAttribute("id"), e.getAttribute("name"), igd.getCode(), Utilities.path(Utilities.path(file.getParent(), e.getAttribute("source"))), igd);
        igd.getDictionaries().add(d);
      } else if (e.getNodeName().equals("logicalModel")) {
        String source = Utilities.path(file.getParent(), e.getAttribute("source"));
        String id = igd.getCode()+"-"+e.getAttribute("id");
        SpreadsheetParser sparser = new SpreadsheetParser(igd.getCode(), new CSFileInputStream(source), id, igd, rootDir, logger, null, context.getVersion(), context, genDate, false, igd.getExtensions(), pkp, false, committee);
        sparser.getBindings().putAll(commonBindings);
        sparser.setFolder(Utilities.getDirectoryForFile(source));
        LogicalModel lm = sparser.parseLogicalModel(source);
        lm.setId(id);
        lm.setSource(source);
        lm.getResource().setName(lm.getId());
        igd.getLogicalModels().add(lm);
      } else
        throw new Exception("Unknown element name in IG: "+e.getNodeName());
      e = XMLUtil.getNextSibling(e);
    }    
  }
  
  
}
