package org.hl7.fhir.definitions.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.definitions.generators.specification.ToolResourceUtilities;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.ConstraintStructure;
import org.hl7.fhir.definitions.model.Dictionary;
import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.Example.ExampleType;
import org.hl7.fhir.definitions.model.ImplementationGuideDefn;
import org.hl7.fhir.definitions.model.LogicalModel;
import org.hl7.fhir.definitions.model.Profile;
import org.hl7.fhir.definitions.model.Profile.ConformancePackageSourceType;
import org.hl7.fhir.definitions.model.WorkGroup;
import org.hl7.fhir.igtools.spreadsheets.CodeSystemConvertor;
import org.hl7.fhir.igtools.spreadsheets.MappingSpace;
import org.hl7.fhir.r5.conformance.ProfileUtilities;
import org.hl7.fhir.r5.conformance.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.r5.context.MetadataResourceManager;
import org.hl7.fhir.r5.formats.XmlParser;
import org.hl7.fhir.r5.model.BooleanType;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.CodeType;
import org.hl7.fhir.r5.model.ConceptMap;
import org.hl7.fhir.r5.model.DateTimeType;
import org.hl7.fhir.r5.model.Enumerations.FHIRVersion;
import org.hl7.fhir.r5.model.Extension;
import org.hl7.fhir.r5.model.ImplementationGuide;
import org.hl7.fhir.r5.model.ImplementationGuide.ImplementationGuideDefinitionGroupingComponent;
import org.hl7.fhir.r5.model.ImplementationGuide.ImplementationGuideDefinitionPageComponent;
import org.hl7.fhir.r5.model.ImplementationGuide.ImplementationGuideDefinitionResourceComponent;
import org.hl7.fhir.r5.model.ImplementationGuide.ImplementationGuideDependsOnComponent;
import org.hl7.fhir.r5.model.Reference;
import org.hl7.fhir.r5.model.Resource;
import org.hl7.fhir.r5.model.ResourceType;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.r5.model.UriType;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.utils.ToolingExtensions;
import org.hl7.fhir.tools.publisher.BuildWorkerContext;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.Logger.LogMessageType;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.validation.ValidationMessage;

public class IgParser {
  public enum GuidePageKind {
    LIST, DIRECTORY, PAGE, EXAMPLE, INCLUDE, DICTIONARY, TOC, RESOURCE;

    public String getDisplay() {
      // TODO Auto-generated method stub
      return null;
    }

    public String toCode() {
      // TODO Auto-generated method stub
      return null;
    }

  }
  public static GuidePageKind getKind(ImplementationGuideDefinitionPageComponent page) {
return null;
  }

  public static List<CodeType> getType(ImplementationGuideDefinitionPageComponent page) {
    return null;
  }

  private Logger logger;
  private BuildWorkerContext context;
  private Calendar genDate;
  private ProfileKnowledgeProvider pkp;
  private Map<String, BindingSpecification> commonBindings;
  private Map<String, MappingSpace> mappings;
  private WorkGroup committee;
  private Map<String, ConstraintStructure> profileIds;
  private MetadataResourceManager<CodeSystem> codeSystems;
  private OIDRegistry registry;
  private MetadataResourceManager<ConceptMap> maps;
  private Map<String, WorkGroup> workgroups;
  private boolean exceptionIfExcelNotNormalised;


  public IgParser(Logger logger, BuildWorkerContext context, Calendar genDate, ProfileKnowledgeProvider pkp, Map<String, BindingSpecification> commonBindings, WorkGroup committee, Map<String, MappingSpace> mappings, Map<String, ConstraintStructure> profileIds, MetadataResourceManager<CodeSystem> codeSystems, OIDRegistry registry, MetadataResourceManager<ConceptMap> maps, Map<String, WorkGroup> workgroups, boolean exceptionIfExcelNotNormalised) {
    super();
    this.logger = logger;
    this.context = context;
    this.genDate = genDate;
    this.pkp = pkp;
    this.commonBindings = commonBindings;
    this.committee = committee;
    this.mappings = mappings;
    this.profileIds = profileIds;
    this.codeSystems = codeSystems;
    this.registry = registry;
    this.maps = maps;
    this.workgroups = workgroups;
    this.exceptionIfExcelNotNormalised = exceptionIfExcelNotNormalised;
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

    for (ImplementationGuideDependsOnComponent d : ig.getDependsOn()) {
      if (!loadedIgs.contains(d.getUri()))
        throw new Exception("Dependency on "+ig.getName()+" not satisfied: "+d.getUri());
    }
    loadedIgs.add(ig.getUrl());
//    for (UriType bin : ig.getBinary()) {
//      if (!new File(Utilities.path(myRoot, bin.getValue())).exists()) 
//        throw new Exception("Binary dependency in "+ig.getName()+" not found: "+bin.getValue());
//      igd.getImageList().add(bin.getValue());
//    }
    processPage(ig.getDefinition().getPage(), igd);

    List<Example> exr = new ArrayList<Example>();
    
    // first pass - verify the resources can be loaded
      for (ImplementationGuideDefinitionResourceComponent r : ig.getDefinition().getResource()) {
        if (!r.hasReference())
          throw new Exception("no source on resource in IG "+ig.getName());
        CSFile fn = new CSFile(Utilities.path(myRoot, r.getReference().getReference()));
        if (!fn.exists())
          throw new Exception("Source "+r.getReference().getReference()+" resource in IG "+ig.getName()+" could not be located @ "+fn.getAbsolutePath());

        String id = Utilities.changeFileExt(fn.getName(), "");
        // we're going to try and load the resource directly.
        // if that fails, then we'll treat it as an example.
        boolean isExample = r.hasExample();
        ResourceType rt = null;
        try {
          rt = new XmlParser().parse(new FileInputStream(fn)).getResourceType();
        } catch (Exception e) {
          rt = null;
          isExample = true;
        }
        
        if (isExample) {
          if (!r.hasName()) // which means that non conformance resources must be named
            throw new Exception("no name on resource in IG "+ig.getName());
          Example example = new Example(r.getName(), id, r.getDescription(), fn, false, ExampleType.XmlFile, false);
          example.setIg(igd.getCode());
          if (r.hasExampleCanonicalType()) {
            example.setExampleFor(r.getExampleCanonicalType().asStringValue());
            example.setRegistered(true);
            exr.add(example);
          }
          igd.getExamples().add(example);
          r.setUserData(ToolResourceUtilities.NAME_RES_EXAMPLE, example);
          r.setReference(new Reference(example.getId()+".html"));
        } else if (rt == ResourceType.ValueSet) {
          ValueSet vs = (ValueSet) new XmlParser().parse(new FileInputStream(fn));
          if (id.startsWith("valueset-"))
            id = id.substring(9);
          vs.setId(id);
          if (vs.getUrl()==null) {
            // Asserting this all the time causes issues for non-HL7 URL value sets
            vs.setUrl("http://hl7.org/fhir/ValueSet/"+id);
          }
          vs.setUserData(ToolResourceUtilities.NAME_RES_IG, igd);
          vs.setUserData("path", igd.getPath()+"valueset-"+id+".html");
          vs.setUserData("filename", "valueset-"+id);
          if (committee != null) {
          if (!vs.hasExtension(ToolingExtensions.EXT_WORKGROUP)) {
            vs.addExtension().setUrl(ToolingExtensions.EXT_WORKGROUP).setValue(new CodeType(committee.getCode()));
          } else {
            String ec = ToolingExtensions.readStringExtension(vs, ToolingExtensions.EXT_WORKGROUP);
            if (!ec.equals(committee.getCode()))
              System.out.println("ValueSet "+vs.getUrl()+" WG mismatch 2: is "+ec+", want to set to "+committee);
          } 
          }
          new CodeSystemConvertor(codeSystems).convert(new XmlParser(), vs, fn.getAbsolutePath());
//          if (id.contains(File.separator))
          igd.getValueSets().add(vs);
          if (!r.hasName())
            r.setName(vs.getName());
          if (!r.hasDescription())
            r.setDescription(vs.getDescription());
          r.setUserData(ToolResourceUtilities.RES_ACTUAL_RESOURCE, vs);
          r.setReference(new Reference(fn.getName()));
        } else if (rt == ResourceType.StructureDefinition) {
          StructureDefinition sd;
          sd = (StructureDefinition) new XmlParser().parse(new CSFileInputStream(fn));
          new ProfileUtilities(context, null, pkp).setIds(sd, false);
          if (sd.getKind() == StructureDefinitionKind.LOGICAL) {
            fn = new CSFile(Utilities.path(myRoot, r.getReference().getReference()));
            LogicalModel lm = new LogicalModel(sd);
            lm.setSource(fn.getAbsolutePath());
            lm.setId(sd.getId());
            igd.getLogicalModels().add(lm);        
            
          } else if ("Extension".equals(sd.getType())) {
            sd.setId(tail(sd.getUrl()));
            sd.setUserData(ToolResourceUtilities.NAME_RES_IG, igd.getCode());
            ToolResourceUtilities.updateUsage(sd, igd.getCode());
            
            this.context.cacheResource(sd);            
          } else {
            Profile pr = new Profile(igd.getCode());
            pr.setSource(fn.getAbsolutePath());
            pr.setTitle(sd.getName());
            if (!sd.hasId())
              sd.setId(tail(sd.getUrl()));
//Lloyd: This causes issues for profiles & extensions defined outside of HL7
//            sd.setUrl("http://hl7.org/fhir/StructureDefinition/"+sd.getId());
            pr.forceMetadata("id", sd.getId()+"-profile");
            pr.setSourceType(ConformancePackageSourceType.StructureDefinition);
            ConstraintStructure cs = new ConstraintStructure(sd, igd, wg(sd), fmm(sd), sd.getExperimental());
            pr.getProfiles().add(cs);
            igd.getProfiles().add(pr);
          }
        } else if (rt == ResourceType.Bundle) {
          Dictionary d = new Dictionary(id, r.getName(), igd.getCode(), fn.getAbsolutePath(), igd);
          igd.getDictionaries().add(d);
        } else 
          logger.log("Implementation Guides do not yet support "+rt.toString(), LogMessageType.Process);
//          throw new Error("Not implemented yet - type = "+rt.toString());


        //        if (r.hasExampleFor()) {
        //          if (!resources.containsKey(r.getExampleFor().getReference()))
        //            throw new Exception("Unable to resolve example-for reference to "+r.getExampleFor().getReference());
        //        }

      }
      // second pass: load the spreadsheets
      for (ImplementationGuideDefinitionGroupingComponent p : ig.getDefinition().getGrouping()) {
        if (!p.hasName())
          throw new Exception("no name on package in IG "+ig.getName());
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
          SpreadsheetParser sparser = new SpreadsheetParser(pr.getCategory(), new CSFileInputStream(pr.getSource()), Utilities.noString(pr.getId()) ? pr.getSource() : pr.getId(), pr.getSource(), igd, 
                rootDir, logger, registry, FHIRVersion.fromCode(context.getVersion()), context, genDate, false, pkp, false, committee, mappings, profileIds, codeSystems, maps, workgroups, exceptionIfExcelNotNormalised);
          sparser.getBindings().putAll(commonBindings);
          sparser.setFolder(Utilities.getDirectoryForFile(pr.getSource()));
          sparser.parseConformancePackage(pr, null, Utilities.getDirectoryForFile(pr.getSource()), pr.getCategory(), issues, null);
//          System.out.println("load "+pr.getId()+" from "+s);
          igd.getProfiles().add(pr);
          // what remains to be done now is to update the package with the loaded resources, but we need to wait for all the profiles to generated, so we'll do that later
          for (BindingSpecification bs : sparser.getBindings().values()) {
            if (!commonBindings.containsValue(bs) && bs.getValueSet() != null) {
              ValueSet vs  = bs.getValueSet();
              String path = vs.getUserString("path");
              path = path.substring(path.lastIndexOf("/")+1);              
              ig.getDefinition().addResource().setName(vs.getName()).setDescription(vs.getDescription()).setReference(new Reference(path)).setUserData(ToolResourceUtilities.RES_ACTUAL_RESOURCE, vs);
            }
          }
          // now, register resources for all the things in the spreadsheet
          for (ValueSet vs : sparser.getValuesets()) 
            ig.getDefinition().addResource().setExample(new BooleanType(false)).setName(vs.getName()).setDescription(vs.getDescription()).setReference(new Reference("valueset-"+vs.getId()+".html")).setUserData(ToolResourceUtilities.RES_ACTUAL_RESOURCE, vs);
          for (StructureDefinition exd : pr.getExtensions()) 
            ig.getDefinition().addResource().setExample(new BooleanType(false)).setName(exd.getName()).setDescription(exd.getDescription()).setReference(new Reference("extension-"+exd.getId().toLowerCase()+".html")).setUserData(ToolResourceUtilities.RES_ACTUAL_RESOURCE, exd);
          for (ConstraintStructure cs : pr.getProfiles()) {
            cs.setResourceInfo(ig.getDefinition().addResource());
            cs.getResourceInfo().setExample(new BooleanType(false)).setName(cs.getDefn().getName()).setDescription(cs.getDefn().getDefinition()).setReference(new Reference(cs.getId().toLowerCase()+".html"));
          }
        }
        if (ex.getUrl().equals(ToolResourceUtilities.EXT_LOGICAL_SPREADSHEET)) {
          File fn = new CSFile(Utilities.path(myRoot, ((UriType) ex.getValue()).getValue()));
//          String source = Utilities.path(file.getParent(), e.getAttribute("source"));
          String s = fn.getName();
          if (s.endsWith("-spreadsheet.xml"))
            s = s.substring(0, s.length()-16);
          String id = igd.getCode()+"-"+s;
          SpreadsheetParser sparser = new SpreadsheetParser(igd.getCode(), new CSFileInputStream(fn), id, fn.getAbsolutePath(), igd, rootDir, logger, registry, FHIRVersion.fromCode(context.getVersion()), context, genDate, false, pkp, false, committee, mappings, profileIds, codeSystems, maps, workgroups, exceptionIfExcelNotNormalised);
          sparser.getBindings().putAll(commonBindings);
          sparser.setFolder(Utilities.getDirectoryForFile(fn.getAbsolutePath()));
          LogicalModel lm = sparser.parseLogicalModel();
          lm.setId(id);
          lm.setSource(fn.getAbsolutePath());
          lm.getResource().setName(lm.getId());
          igd.getLogicalModels().add(lm);
        }
      }
      ToolingExtensions.removeExtension(p, ToolResourceUtilities.EXT_PROFILE_SPREADSHEET);
      ToolingExtensions.removeExtension(p, ToolResourceUtilities.EXT_LOGICAL_SPREADSHEET);
    }
    for (Example ex : exr) {
      Profile tp = null;
      for (Profile pr : igd.getProfiles()) {
        if (("StructureDefinition/"+pr.getId()).equals(ex.getExampleFor())) {
          tp = pr;
          break;
        } else for (ConstraintStructure cc : pr.getProfiles()) {
          if (("StructureDefinition/"+cc.getId()).equals(ex.getExampleFor())) {
            tp = pr;
            break;
          }
        }
      }
      if (tp != null)
        tp.getExamples().add(ex);
      else
        throw new Exception("no profile found matching exampleFor = "+ex.getExampleFor());
    }
    igd.numberPages();
    
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

  private String tail(String url) {
    return url.substring(url.lastIndexOf("/")+1);
  }

  private void processPage(ImplementationGuideDefinitionPageComponent page, ImplementationGuideDefn igd) throws Exception {
    if (!page.hasTitle())
      throw new Exception("Page "+page.getNameUrlType().getValue()+" has no name");
    if (getKind(page) == null || getKind(page) == GuidePageKind.PAGE || getKind(page) == GuidePageKind.DIRECTORY || getKind(page) == GuidePageKind.LIST || getKind(page) == GuidePageKind.RESOURCE) {
      checkExists(igd, page.getNameUrlType().getValue());
      igd.getPageList().add(page.getNameUrlType().getValue());
    }
    for (ImplementationGuideDefinitionPageComponent pp : page.getPage()) {
      processPage(pp, igd);
    }
  }

  private void checkExists(ImplementationGuideDefn igd, String source) throws Exception {
    File f = new File(Utilities.path(igd.getPath(), source));
    if (f.exists())
      throw new Exception("Unable to find file "+source);
    
  }
  

  private String fmm(StructureDefinition ed) {
    return Integer.toString(ToolingExtensions.readIntegerExtension(ed, ToolingExtensions.EXT_FMM_LEVEL, 1)); // default fmm level
  }




  private WorkGroup wg(StructureDefinition ed) {
    return workgroups.get(ToolingExtensions.readStringExtension(ed, ToolingExtensions.EXT_WORKGROUP));
  }
}
