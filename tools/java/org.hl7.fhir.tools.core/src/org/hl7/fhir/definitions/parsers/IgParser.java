package org.hl7.fhir.definitions.parsers;

import java.io.File;
import java.util.Calendar;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.Dictionary;
import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.Example.ExampleType;
import org.hl7.fhir.definitions.model.ImplementationGuide;
import org.hl7.fhir.definitions.model.LogicalModel;
import org.hl7.fhir.definitions.model.Profile;
import org.hl7.fhir.definitions.model.Profile.ConformancePackageSourceType;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.utils.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.instance.utils.WorkerContext;
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

  public IgParser(Logger logger, WorkerContext context, Calendar genDate, ProfileKnowledgeProvider pkp, Map<String, BindingSpecification> commonBindings) {
    super();
    this.logger = logger;
    this.context = context;
    this.genDate = genDate;
    this.pkp = pkp;
    this.commonBindings = commonBindings;
  }

  public void load(String rootDir, ImplementationGuide ig) throws Exception {
    logger.log(" ..."+ig.getName(), LogMessageType.Process);
    
    CSFile file = new CSFile(Utilities.path(rootDir, ig.getSource()));
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
          ig.setPage(e.getAttribute("homepage"));
      } else if (e.getNodeName().equals("page")) {
        ig.getPageList().add(e.getAttribute("source"));
      } else if (e.getNodeName().equals("image")) {
        ig.getImageList().add(e.getAttribute("source"));
      } else if (e.getNodeName().equals("valueset")) {
        XmlParser xml = new XmlParser();
        ValueSet vs = (ValueSet) xml.parse(new CSFileInputStream(Utilities.path(file.getParent(), e.getAttribute("source"))));
        if (!vs.hasId())
          vs.setId(Utilities.changeFileExt(file.getName(), ""));
        if (!vs.hasUrl())
          vs.setUrl("http://hl7.org/fhir/vs/"+vs.getId());
        ig.getValueSets().add(vs);
      } else if (e.getNodeName().equals("acronym")) {
        ig.getTlas().put(e.getAttribute("target"), e.getAttribute("id"));        
      } else if (e.getNodeName().equals("example")) {
        String filename = e.getAttribute("source");
        File efile = new File(Utilities.path(file.getParent(), filename));
        Example example = new Example(e.getAttribute("name"), Utilities.changeFileExt(efile.getName(), ""), e.getAttribute("name"), efile, false, ExampleType.XmlFile, false);
        ig.getExamples().add(example);
      } else if (e.getNodeName().equals("profile")) {
        Profile p = new Profile(ig.getCode());
        p.setSource(Utilities.path(file.getParent(), e.getAttribute("source")));
        if ("spreadsheet".equals(e.getAttribute("type"))) {
          p.setSourceType(ConformancePackageSourceType.Spreadsheet);
          SpreadsheetParser sparser = new SpreadsheetParser(p.getCategory(), new CSFileInputStream(p.getSource()), Utilities.noString(p.getId()) ? p.getSource() : p.getId(), ig, 
              rootDir, logger, null, context.getVersion(), context, genDate, false, ig.getExtensions(), pkp, false);
          sparser.getBindings().putAll(commonBindings);
          sparser.setFolder(Utilities.getDirectoryForFile(p.getSource()));
          sparser.parseConformancePackage(p, null, Utilities.getDirectoryForFile(p.getSource()), p.getCategory());
        } else {
          throw new Exception("Unknown profile type in IG: "+e.getNodeName());
          // parseConformanceDocument(p, p.getId(), new File(p.getSource()), p.getCategory());    
        } 
        
        String id = e.getAttribute("id");
        if (Utilities.noString(id))
          id = Utilities.changeFileExt(e.getAttribute("source"), "");
        ig.getProfiles().add(p);
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
        Dictionary d = new Dictionary(e.getAttribute("id"), e.getAttribute("name"), ig.getCode(), Utilities.path(Utilities.path(file.getParent(), e.getAttribute("source"))));
        ig.getDictionaries().add(d);
      } else if (e.getNodeName().equals("logicalModel")) {
        String source = Utilities.path(file.getParent(), e.getAttribute("source"));
        String id = ig.getCode()+"-"+e.getAttribute("id");
        SpreadsheetParser sparser = new SpreadsheetParser(ig.getCode(), new CSFileInputStream(source), id, ig, rootDir, logger, null, context.getVersion(), context, genDate, false, ig.getExtensions(), pkp, false);
        sparser.getBindings().putAll(commonBindings);
        sparser.setFolder(Utilities.getDirectoryForFile(source));
        LogicalModel lm = sparser.parseLogicalModel(source);
        lm.setId(id);
        lm.setSource(source);
        lm.getResource().setName(lm.getId());
        ig.getLogicalModels().add(lm);
      } else
        throw new Exception("Unknown element name in IG: "+e.getNodeName());
      e = XMLUtil.getNextSibling(e);
    }    
  }
  
  
}
