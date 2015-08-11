package org.hl7.fhir.tools.publisher;

import java.io.ByteArrayInputStream;
/*
Copyright (c) 2011+, HL7, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.

*/
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.generators.specification.BaseGenerator;
import org.hl7.fhir.definitions.generators.specification.DataTypeTableGenerator;
import org.hl7.fhir.definitions.generators.specification.DictHTMLGenerator;
import org.hl7.fhir.definitions.generators.specification.JsonSpecGenerator;
import org.hl7.fhir.definitions.generators.specification.MappingsGenerator;
import org.hl7.fhir.definitions.generators.specification.ResourceTableGenerator;
import org.hl7.fhir.definitions.generators.specification.SvgGenerator;
import org.hl7.fhir.definitions.generators.specification.TerminologyNotesGenerator;
import org.hl7.fhir.definitions.generators.specification.ToolResourceUtilities;
import org.hl7.fhir.definitions.generators.specification.XmlSpecGenerator;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.Compartment;
import org.hl7.fhir.definitions.model.ConstraintStructure;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.Dictionary;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.EventDefn;
import org.hl7.fhir.definitions.model.EventUsage;
import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.ImplementationGuideDefn;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.LogicalModel;
import org.hl7.fhir.definitions.model.Operation;
import org.hl7.fhir.definitions.model.Operation.OperationExample;
import org.hl7.fhir.definitions.model.OperationParameter;
import org.hl7.fhir.definitions.model.PrimitiveType;
import org.hl7.fhir.definitions.model.Profile;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameterDefn;
import org.hl7.fhir.definitions.model.SearchParameterDefn.SearchType;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.definitions.model.W5Entry;
import org.hl7.fhir.definitions.model.WorkGroup;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.definitions.parsers.BindingNameRegistry;
import org.hl7.fhir.definitions.parsers.TypeParser;
import org.hl7.fhir.definitions.validation.ValueSetValidator;
import org.hl7.fhir.instance.client.FHIRSimpleClient;
import org.hl7.fhir.instance.formats.FormatUtilities;
import org.hl7.fhir.instance.formats.IParser;
import org.hl7.fhir.instance.formats.IParser.OutputStyle;
import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.instance.model.Bundle.BundleType;
import org.hl7.fhir.instance.model.CodeType;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.ConceptMap;
import org.hl7.fhir.instance.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionSlicingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.SlicingRules;
import org.hl7.fhir.instance.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.instance.model.Enumerations.SearchParamType;
import org.hl7.fhir.instance.model.ImplementationGuide.ImplementationGuidePackageComponent;
import org.hl7.fhir.instance.model.ImplementationGuide.ImplementationGuidePackageResourceComponent;
import org.hl7.fhir.instance.model.ImplementationGuide.ImplementationGuidePageComponent;
import org.hl7.fhir.instance.model.NamingSystem;
import org.hl7.fhir.instance.model.NamingSystem.NamingSystemIdentifierType;
import org.hl7.fhir.instance.model.NamingSystem.NamingSystemUniqueIdComponent;
import org.hl7.fhir.instance.model.OperationDefinition.OperationParameterUseEnumFactory;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Quantity;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.SearchParameter;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.StructureDefinition.ExtensionContext;
import org.hl7.fhir.instance.model.StructureDefinition.StructureDefinitionMappingComponent;
import org.hl7.fhir.instance.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.OperationOutcome.IssueType;
import org.hl7.fhir.instance.terminologies.ValueSetExpander.ValueSetExpansionOutcome;
import org.hl7.fhir.instance.terminologies.ValueSetExpansionCache;
import org.hl7.fhir.instance.utils.NarrativeGenerator;
import org.hl7.fhir.instance.utils.ProfileComparer;
import org.hl7.fhir.instance.utils.ProfileComparer.ProfileComparison;
import org.hl7.fhir.instance.utils.ProfileUtilities;
import org.hl7.fhir.instance.utils.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.instance.utils.ResourceUtilities;
import org.hl7.fhir.instance.utils.ToolingExtensions;
import org.hl7.fhir.instance.utils.Translations;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Piece;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Row;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.TableModel;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.hl7.fhir.utilities.xml.XhtmlGenerator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.github.rjeschke.txtmark.Processor;

public class PageProcessor implements Logger, ProfileKnowledgeProvider  {

  public class SectionSorter implements Comparator<String> {

    @Override
    public int compare(String arg0, String arg1) {
      String[] p0 = arg0.split("\\.");
      String[] p1 = arg1.split("\\.");
      for (int i = 0; i < Math.min(p0.length, p1.length); i++) {
        if (Utilities.isInteger(p0[i]) && Utilities.isInteger(p1[i])) {
          int i0 = Integer.parseInt(p0[i]);
          int i1 = Integer.parseInt(p1[i]);
          if (i0 != i1) {
            if (i0 < i1)
              return -1;
            else
              return 1;
          }
        } else {
           int c = p0[i].compareTo(p1[i]);
           if (c != 0)
             return c;
        }
      }
      if (p0.length > p1.length)
        return 1;
      if (p0.length < p1.length)
        return -1;
      return 0;
    }
  }

  private static final String SIDEBAR_SPACER = "<p>&#xA0;</p>\r\n";

  private final List<String> suppressedMessages = new ArrayList<String>();
  private Definitions definitions;
  private FolderManager folders;
  private String version;
  private Navigation navigation;
  private final List<PlatformGenerator> referenceImplementations = new ArrayList<PlatformGenerator>();
  private IniFile ini;
  private final Calendar genDate = Calendar.getInstance();
  private final Date start = new Date();
  private final Map<String, String> prevSidebars = new HashMap<String, String>();
  private String svnRevision;
  private final List<String> orderedResources = new ArrayList<String>();
  private final Map<String, SectionTracker> sectionTrackerCache = new HashMap<String, SectionTracker>();
  private final Map<String, TocEntry> toc = new HashMap<String, TocEntry>();
  private Document v2src;
  private Document v3src;
  private final QaTracker qa = new QaTracker();
  private Bundle v3Valuesets;
  private Bundle v2Valuesets;
  private final Map<String, ValueSet> codeSystems = new HashMap<String, ValueSet>();
  private final Map<String, ValueSet> valueSets = new HashMap<String, ValueSet>();
  private final Map<String, ConceptMap> conceptMaps = new HashMap<String, ConceptMap>();
  private final Map<String, StructureDefinition> profiles = new HashMap<String, StructureDefinition>();
  private final Map<String, Resource> igResources = new HashMap<String, Resource>();
  private final Map<String, String> svgs = new HashMap<String, String>();
  private Translations translations = new Translations();
  private final BreadCrumbManager breadCrumbManager = new BreadCrumbManager(translations);
  private String publicationType = "Local Build ("+System.getenv("COMPUTERNAME")+")";
  private String publicationNotice = "";
  private BindingNameRegistry registry;
  private String oid; // technical identifier associated with the page being built
  private EPubManager epub;
  private String baseURL = "http://hl7.org/fhir/DSTU1/";
  private SpecificationTerminologyServices terminologyServices;
  private final String tsServer; // terminology to use
  private final WorkerContext workerContext;
//  private List<ValidationMessage> collectedValidationErrors = new ArrayList<ValidationMessage>();
  private List<ValidationMessage> validationErrors = new ArrayList<ValidationMessage>();
  private long lastSecs = 0;
  private Set<String> searchTypeUsage = new HashSet<String>();
  private ValueSetValidator vsValidator;

  public PageProcessor(String tsServer) throws URISyntaxException {
    super();
    this.tsServer = tsServer;
    FHIRSimpleClient client = new FHIRSimpleClient();
    try {
      client.initialize(tsServer);
    } catch(Exception e) {
      System.out.println("Warning @ PageProcessor client initialize: " + e.getLocalizedMessage());
    }
    workerContext = new WorkerContext(null, client, codeSystems, valueSets, conceptMaps, profiles);
  }

//  public final static String DEF_TS_SERVER = "http://fhir-dev.healthintersections.com.au/open";
  public final static String DEV_TS_SERVER = "http://local.healthintersections.com.au:980/open";
  
  public final static String WEB_PUB_NAME = "DSTU2 Ballot Source";
  public final static String CI_PUB_NAME = "Current Build";
  
  public final static String WEB_PUB_NOTICE =
      "<p style=\"background-color: gold; border:1px solid maroon; padding: 5px;\">\r\n"+
          "This is the DSTU2 <b>ballot</b> version of FHIR, and not intended for implementation. See the <a href=\"http://hl7.org/fhir/directory.html\">Directory of published versions</a>\r\n"+
          "</p>\r\n";
  
  public final static String CI_PUB_NOTICE = 
      "<p style=\"background-color: gold; border:1px solid maroon; padding: 5px;\">\r\n"+
          "This is the Continuous Integration Build of FHIR (will be incorrect/inconsistent at times). See the <a href=\"http://hl7.org/fhir/directory.html\">Directory of published versions</a>\r\n"+
          "</p>\r\n";

  private static final String TOO_MANY_CODES_TEXT = "This value set has >10,000 codes in it. In order to keep the publication size manageable, value sets this large are not expanded here";
  private static final String NO_CODESYSTEM_TEXT = "This value set refers to code systems that the FHIR Publication Tooling does not support";
      
//  private boolean notime;
  
  private String dictForDt(String dt) throws Exception {
	  File tmp = Utilities.createTempFile("tmp", ".tmp");
	  DictHTMLGenerator gen = new DictHTMLGenerator(new FileOutputStream(tmp), this, "");
	  TypeParser tp = new TypeParser();
	  TypeRef t = tp.parse(dt, false, null, definitions).get(0);
	  
	  ElementDefn e;
	  if (t.getName().equals("Resource"))
	    e = definitions.getBaseResources().get("DomainResource").getRoot();
	  else
	    e = definitions.getElementDefn(t.getName());
	  if (e == null) {
		  gen.close();
		  throw new Exception("unable to find definition for "+ dt);
	  } 
	  else {
		  gen.generate(e);
		  gen.close();
	  }
	  String val = TextFile.fileToString(tmp.getAbsolutePath())+"\r\n";
	  tmp.delete();
	  return val; 
  }

  private String tsForDt(String dt) throws Exception {
	  File tmp = Utilities.createTempFile("tmp", ".tmp");
	  tmp.deleteOnExit();
	  TerminologyNotesGenerator gen = new TerminologyNotesGenerator(new FileOutputStream(tmp), this);
	  TypeParser tp = new TypeParser();
	  TypeRef t = tp.parse(dt, false, null, definitions).get(0);
	  ElementDefn e = definitions.getElementDefn(t.getName());
	  if (e == null) {
		  gen.close();
		  throw new Exception("unable to find definition for "+ dt);
	  } 
	  else {
		  gen.generate("", e);
		  gen.close();
	  }
	  String val = TextFile.fileToString(tmp.getAbsolutePath())+"\r\n";
	  tmp.delete();
	  return val;
  }
  
  private String treeForDt(String dt) throws Exception {
    DataTypeTableGenerator gen = new DataTypeTableGenerator(folders.dstDir, this, dt, false);
    return new XhtmlComposer().compose(gen.generate(definitions.getElementDefn(dt)));
  }
  
  private String xmlForDt(String dt, String pn) throws Exception {
	  File tmp = Utilities.createTempFile("tmp", ".tmp");
	  XmlSpecGenerator gen = new XmlSpecGenerator(new FileOutputStream(tmp), pn == null ? null : pn.substring(0, pn.indexOf("."))+"-definitions.html", null, this, "");
	  TypeParser tp = new TypeParser();
	  TypeRef t = tp.parse(dt, false, null, definitions).get(0);
	  ElementDefn e = definitions.getElementDefn(t.getName());
	  if (e == null) {
		  gen.close();
		  throw new Exception("unable to find definition for "+ dt);
	  } 
	  else {
		  gen.generate(e, e.getName().equals("Element") || e.getName().equals("BackboneElement"));
		  gen.close();
	  }
	  String val = TextFile.fileToString(tmp.getAbsolutePath())+"\r\n";
	  tmp.delete();
	  return val; 
  }

  private String jsonForDt(String dt, String pn) throws Exception {
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    JsonSpecGenerator gen = new JsonSpecGenerator(b, pn == null ? null : pn.substring(0, pn.indexOf("."))+"-definitions.html", null, this, "");
    TypeParser tp = new TypeParser();
    TypeRef t = tp.parse(dt, false, null, definitions).get(0);
    ElementDefn e = definitions.getElementDefn(t.getName());
    if (e == null) {
      gen.close();
      throw new Exception("unable to find definition for "+ dt);
    } 
    else {
      gen.generate(e, false, false);
      gen.close();
    }
    String val = new String(b.toByteArray())+"\r\n";
    return val; 
  }

 
  private String generateSideBar(String prefix) throws Exception {
    if (prevSidebars.containsKey(prefix))
      return prevSidebars.get(prefix);
    List<String> links = new ArrayList<String>();
    
    StringBuilder s = new StringBuilder();
    s.append("<div class=\"sidebar\">\r\n");
    s.append("<p><a href=\"http://hl7.org/fhir\" title=\"Fast Healthcare Interoperability Resources - Home Page\"><img border=\"0\" src=\""+prefix+"flame16.png\" style=\"vertical-align: text-bottom\"/></a> "+
      "<a href=\"http://hl7.org/fhir\" title=\"Fast Healthcare Interoperability Resources - Home Page\"><b>FHIR</b></a>&reg; v"+getVersion()+" &copy; <a href=\"http://hl7.org\">HL7</a></p>\r\n");

    for (Navigation.Category c : navigation.getCategories()) {
      if (!"nosidebar".equals(c.getMode())) {
        if (c.getLink() != null) {
          s.append("  <h2><a href=\""+prefix+c.getLink()+".html\">"+c.getName()+"</a></h2>\r\n");
          links.add(c.getLink());
        }
        else
          s.append("  <h2>"+c.getName()+"</h2>\r\n");
        s.append("  <ul>\r\n");
        for (Navigation.Entry e : c.getEntries()) {
          if (e.getLink() != null) {
            links.add(e.getLink());
            s.append("    <li><a href=\""+prefix+e.getLink()+".html\">"+Utilities.escapeXml(e.getName())+"</a></li>\r\n");
          } else
            s.append("    <li>"+e.getName()+"</li>\r\n");
        }
        if (c.getEntries().size() ==0 && c.getLink().equals("resourcelist")) {
          List<String> list = new ArrayList<String>();
          list.addAll(definitions.getResources().keySet());
          Collections.sort(list);

          for (String rn : list) {
          //  if (!links.contains(rn.toLowerCase())) {
              ResourceDefn r = definitions.getResourceByName(rn);
              orderedResources.add(r.getName());
              s.append("    <li><a href=\""+prefix+rn.toLowerCase()+".html\">"+Utilities.escapeXml(r.getName())+"</a></li>\r\n");
          //  }
          }

        }
        s.append("  </ul>\r\n");
      }
    }
    // s.append(SIDEBAR_SPACER);
    s.append("<p><a href=\"http://gforge.hl7.org/gf/project/fhir/\" title=\"SVN Link\">Build "+svnRevision+"</a> (<a href=\"qa.html\">QA Page</a>)</p><p> <a href=\"http://hl7.org\"><img width=\"42\" height=\"50\" border=\"0\" src=\""+prefix+"hl7logo.png\"/></a></p>\r\n");

    s.append("</div>\r\n");
    prevSidebars.put(prefix, s.toString());
    return prevSidebars.get(prefix);
  }

  private String combineNotes(String location, List<String> followUps, String notes, String prefix) throws Exception {
    String s = "";
    if (notes != null && !notes.equals(""))
      s = notes;
    if (followUps.size() > 0)
      if (!s.isEmpty())
        s = s + "\r\n\r\nFollow ups: "+Utilities.asCSV(followUps);
      else
        s = "Follow ups: "+Utilities.asCSV(followUps);
    return processMarkdown(location, s, prefix);      
  }

  private String describeMsg(List<String> resources, List<String> aggregations) {
    if (resources.isEmpty() && aggregations.isEmpty())
      return "<font color=\"silver\">--</font>";
    else {
      String s = resources.isEmpty() ? "" : Utilities.asCSV(resources);
      
      if (aggregations.isEmpty())
        return s;
      else
        return s + "<br/>"+Utilities.asHtmlBr("&nbsp;"+resources.get(0), aggregations)+"";
    }      
  }


  public String processPageIncludes(String file, String src, String type, Map<String, String> others, Resource resource, List<String> tabs, String crumbTitle, ImplementationGuideDefn ig) throws Exception {
    return processPageIncludes(file, src, type, others, file, resource, tabs, crumbTitle, ig);
  }
  
  public String processPageIncludes(String file, String src, String type, Map<String, String> others, String pagePath, Resource resource, List<String> tabs, String crumbTitle, ImplementationGuideDefn ig) throws Exception {
    return processPageIncludes(file, src, type, others, pagePath, resource, tabs, crumbTitle, null, ig);
  }
  
  public String processPageIncludes(String file, String src, String type, Map<String, String> others, String pagePath, Resource resource, List<String> tabs, String crumbTitle, Object object, ImplementationGuideDefn ig) throws Exception {
    String workingTitle = null;
    int level = ig == null ? file.contains(File.separator) ? 1 : 0 : ig.isCore() ? 0 : 1;
    boolean even = false;    
    String name = file.substring(0,file.lastIndexOf("."));
    
    while (src.contains("<%") || src.contains("[%"))
    {
      int i1 = src.indexOf("<%");
      int i2 = src.indexOf("%>");
      if (i1 == -1) {
        i1 = src.indexOf("[%");
        i2 = src.indexOf("%]");
      }
      String s1 = src.substring(0, i1);
      String s2 = src.substring(i1 + 2, i2).trim();
      String s3 = src.substring(i2+2);

      String[] com = s2.split(" ");
      if (com.length == 4 && com[0].equals("edt")) {
        if (tabs != null) 
          tabs.add("tabs-"+com[1]);
        src = s1+orgDT(com[1], xmlForDt(com[1], com[2]), treeForDt(com[1]), umlForDt(com[1], com[3]), umlForDt(com[1], com[3]+"b"), profileRef(com[1]), tsForDt(com[1]), jsonForDt(com[1], com[2]))+s3;
      } else if (com.length == 3 && com[0].equals("dt")) {
        if (tabs != null) 
          tabs.add("tabs-"+com[1]);
        src = s1+orgDT(com[1], xmlForDt(com[1], file), treeForDt(com[1]), umlForDt(com[1], com[2]), umlForDt(com[1], com[2]+"b"), profileRef(com[1]), tsForDt(com[1]), jsonForDt(com[1], file))+s3;
      } else if (com.length == 2 && com[0].equals("dt.constraints")) 
        src = s1+genConstraints(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dt.restrictions")) 
        src = s1+genRestrictions(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dictionary"))
        src = s1+dictForDt(com[1])+s3;
      else if (com[0].equals("othertabs"))
        src = s1 + genOtherTabs(com[1], tabs) + s3;
      else if (com[0].equals("dtheader"))
        src = s1+dtHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("edheader"))
        src = s1+edHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("elheader"))
        src = s1+elHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("extheader"))
        src = s1+extHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("narrheader"))
        src = s1+narrHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("profilesheader"))
        src = s1+profilesHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("refheader"))
        src = s1+refHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("resourcesheader"))
        src = s1+resourcesHeader(com.length > 1 ? com[1] : null)+s3;
//      else if (com[0].equals("formatsheader"))
//        src = s1+formatsHeader(name, com.length > 1 ? com[1] : null)+s3;
//      else if (com[0].equals("resourcesheader"))
//        src = s1+resourcesHeader(name, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("txheader"))
        src = s1+txHeader(name, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("txheader0"))
        src = s1+(level > 0 ? "" : txHeader(name, com.length > 1 ? com[1] : null))+s3;
      else if (com[0].equals("fmtheader"))
        src = s1+fmtHeader(name, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("cmpheader"))
        src = s1+cmpHeader(name, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("dictheader"))
        src = s1+dictHeader(name, com.length > 1 ? com[1] : "")+s3;
//      else if (com[0].equals("atomheader"))
//        src = s1+atomHeader(name, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("codelist"))
        src = s1+codelist((ValueSet) resource, com.length > 1 ? com[1] : null, false, true)+s3;
      else if (com[0].equals("linkcodelist"))
        src = s1+codelist((ValueSet) resource, com.length > 1 ? com[1] : null, true, false)+s3;
      else if (com[0].equals("toc1"))
        src = s1 + generateToc(com[1], 1) + s3;
      else if (com[0].equals("toc2"))
        src = s1 + generateToc(com[1], 2) + s3;
      else if (com[0].equals("codetoc"))
        src = s1+codetoc(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("resheader")) {
        StructureDefinition sd = (StructureDefinition) resource;
        src = s1+resHeader(sd.getId().toLowerCase(), sd.getId(), com.length > 1 ? com[1] : null)+s3;
      } else if (com[0].equals("aresheader"))
        src = s1+abstractResHeader("document", "Document", com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("onthispage"))
        src = s1+onThisPage(s2.substring(com[0].length()+1))+s3;
      else if (com[0].equals("maponthispage"))
          src = s1+mapOnThisPage(null)+s3;
      else if (com[0].equals("res-category")) {
        even = false;
        src = s1+resCategory(s2.substring(com[0].length()+1))+s3;
      } else if (com[0].equals("res-item")) {
        even = !even;
        src = s1+resItem(com[1], even)+s3;
      } else if (com[0].equals("resdesc")) {
        src = s1+resDesc(com[1])+s3;
      } else if (com[0].equals("sidebar"))
        src = s1+generateSideBar(com.length > 1 ? com[1] : "")+s3;
      else if (com[0].equals("svg"))
        src = s1+svgs.get(com[1])+s3;
      else if (com[0].equals("diagram"))
        src = s1+new SvgGenerator(this, genlevel(level)).generate(folders.srcDir+ com[1], com[2])+s3;
      else if (com[0].equals("file"))
        src = s1+TextFile.fileToString(folders.srcDir + com[1]+".html")+s3;
      else if (com[0].equals("v2xref"))
        src = s1 + xreferencesForV2(name, com[1]) + s3;      
      else if (com[0].equals("vs-warning"))
        src = s1 + vsWarning((ValueSet) resource) + s3;      
      else if (com[0].equals("conceptmaplistv2"))
        src = s1 + conceptmaplist("http://hl7.org/fhir/ValueSet/v2-"+(name.contains("|") ? name.substring(0,name.indexOf("|")) : name), com[1]) + s3;      
      else if (com[0].equals("conceptmaplistv3"))
        src = s1 + conceptmaplist("http://hl7.org/fhir/ValueSet/v3-"+(name.contains("|") ? name.substring(0,name.indexOf("|")) : name), com[1]) + s3;      
      else if (com[0].equals("conceptmaplistvs")) {
        ValueSet vs = (ValueSet) resource;
        String ref;
        if (vs == null) {
          ref = "http://hl7.org/fhir/ValueSet/"+Utilities.fileTitle(file);
        } else {
          ref = vs.getUrl();
        }
        src = s1 + conceptmaplist(ref, com[1]) + s3;
      } else if (com[0].equals("settitle")) {
        workingTitle = s2.substring(9).replace("{", "<%").replace("}", "%>");
        src = s1+s3;
      } else if (com[0].equals("dtmappings")) {
        src = s1 + genDataTypeMappings(com[1]) + s3;
      } else if (com[0].equals("dtusage")) {
        src = s1 + genDataTypeUsage(com[1]) + s3;
      }  else if (com[0].equals("v3xref")) {
        src = s1 + xreferencesForV3(name, com[1].equals("true")) + s3;      
      }  else if (com[0].equals("reflink")) {
        src = s1 + reflink(com[1]) + s3;      
      } else if (com[0].equals("setlevel")) {
        level = Integer.parseInt(com[1]);
        src = s1+s3;
      } else if (com[0].equals("w5")) {
          src = s1+genW5("true".equals(com[1]))+s3;
      } else if (com[0].equals("res-ref-list")) {
        src = s1+genResRefList(com[1])+s3;
      } else if (com[0].equals("sclist")) {
        src = s1+genScList(com[1])+s3;
      } else if (com[0].equals("xcm")) {
        src = s1+getXcm(com[1])+s3;
      } else if (com[0].equals("fmm")) {
        src = s1+getFmm(com[1])+s3;
      } else if (com[0].equals("wg")) {
        src = s1+getWgLink(file, com[1])+s3;
      } else if (com[0].equals("wgt")) {
        src = s1+getWgTitle(com[1])+s3;
      } else if (com[0].equals("ig.registry")) {
        src = s1+buildIgRegistry(ig, com[1])+s3;
      } else if (com[0].equals("profileheader")) {
        src = s1+profileHeader(((StructureDefinition) resource).getId().toLowerCase(), com[1])+s3;
      } else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
      else if (com[0].equals("pageheader"))
        src = s1+pageHeader(name.toUpperCase().substring(0, 1)+name.substring(1))+s3;
      else if (com[0].equals("header"))
        src = s1+TextFile.fileToString(folders.srcDir + "header.html")+s3;
      else if (com[0].equals("newheader"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader.html")+s3;
      else if (com[0].equals("newheader1"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader1.html")+s3;
      else if (com[0].equals("footer"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer.html")+s3;
      else if (com[0].equals("newfooter"))
        src = s1+TextFile.fileToString(folders.srcDir + "newfooter.html")+s3;
      else if (com[0].equals("footer1"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer1.html")+s3;
      else if (com[0].equals("footer2"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer2.html")+s3;
      else if (com[0].equals("footer3"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer3.html")+s3;
      else if (com[0].equals("title"))
        src = s1+(workingTitle == null ? Utilities.escapeXml(name.toUpperCase().substring(0, 1)+name.substring(1)) : workingTitle)+s3;
      else if (com[0].equals("xtitle"))
        src = s1+Utilities.escapeXml(name.toUpperCase().substring(0, 1)+name.substring(1))+s3;
      else if (com[0].equals("name"))
        src = s1+name+s3;
      else if (com[0].equals("name.tail"))
        src = s1+fileTail(name)+s3;
      else if (com[0].equals("piperesources"))
        src = s1+pipeResources()+s3;
      else if (com[0].equals("enteredInErrorTable"))
        src = s1+enteredInErrorTable()+s3;
      else if (com[0].equals("canonicalname"))
        src = s1+makeCanonical(name)+s3;
      else if (com[0].equals("version"))
        src = s1+version+s3;
      else if (com[0].equals("gendate"))
        src = s1+Config.DATE_FORMAT().format(new Date())+s3;
      else if (com[0].equals("maindiv"))
        src = s1+"<div class=\"content\">"+s3;
      else if (com[0].equals("/maindiv"))
        src = s1+"</div>"+s3;
      else if (com[0].equals("v2Index"))
        src = s1+genV2Index()+s3;
      else if (com[0].equals("v3Index-cs"))
        src = s1+genV3CSIndex()+s3;
      else if (com[0].equals("v3Index-vs"))
        src = s1+genV3VSIndex()+s3;
      else if (com[0].equals("id"))
        src = s1+(name.contains("|") ? name.substring(0,name.indexOf("|")) : name)+s3;
      else if (com[0].equals("ver"))
        src = s1+(name.contains("|") ? name.substring(name.indexOf("|")+1) : "??")+s3;
      else if (com[0].equals("v2Table"))
        src = s1+genV2Table(name)+s3;
      else if (com[0].equals("v2TableVer"))
        src = s1+genV2TableVer(name)+s3;
      else if (com[0].equals("v3CodeSystem"))
        src = s1+genV3CodeSystem(name)+s3;
      else if (com[0].equals("v3ValueSet"))
        src = s1+genV3ValueSet(name)+s3;      
      else if (com[0].equals("events"))
        src = s1 + getEventsTable(pagePath)+ s3;
      else if (com[0].equals("resourcecodes"))
        src = s1 + genResCodes() + s3;
      else if (com[0].equals("datatypecodes"))
        src = s1 + genDTCodes() + s3;
      else if (com[0].equals("allparams"))
        src = s1 + allParamlist() + s3;      
//      else if (com[0].equals("bindingtable-codelists"))
//        src = s1 + genBindingTable(true) + s3;
//      else if (com[0].equals("bindingtable"))
//        src = s1 + genBindingsTable() + s3;
      else if (com[0].equals("codeslist"))
        src = s1 + genCodeSystemsTable() + s3;
      else if (com[0].equals("valuesetslist"))
        src = s1 + genValueSetsTable(ig) + s3;
      else if (com[0].equals("extensionslist"))
        src = s1 + genExtensionsTable() + s3;
      else if (com[0].equals("igvaluesetslist"))
        src = s1 + genIGValueSetsTable() + s3;
      else if (com[0].equals("conceptmapslist"))
        src = s1 + genConceptMapsTable() + s3;
//      else if (com[0].equals("bindingtable-others"))
//        src = s1 + genBindingTable(false) + s3;
      else if (com[0].equals("resimplall"))
          src = s1 + genResImplList() + s3;
      else if (com[0].equals("impllist"))
        src = s1 + genReferenceImplList(pagePath) + s3;
      else if (com[0].equals("txurl"))
        src = s1 + "http://hl7.org/fhir/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vstxurl"))
        src = s1 + "http://hl7.org/fhir/ValueSet/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vsurl")) {
        if (resource != null)
          src = s1 + ((ValueSet) resource).getUrl() + s3;
        else {
          throw new Error("fix this");
//          BindingSpecification bs = definitions.getBindingByName(Utilities.fileTitle(file));
//          if (bs == null) {
//            src = s1 + "http://hl7.org/fhir/ValueSet/"+Utilities.fileTitle(file) + s3;
//          } else {
//            String reference = bs.getReference();
//            if (reference.startsWith("valueset-"))
//              reference = reference.substring(9);
//            src = s1 + "http://hl7.org/fhir/ValueSet/"+reference + s3;
//          }
        }
      } else if (com[0].equals("txdef"))
        src = s1 + generateCodeDefinition(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("vsdef"))
        src = s1 + (resource != null ? Utilities.escapeXml(((ValueSet) resource).getDescription()) : generateValueSetDefinition(Utilities.fileTitle(file))) + s3;
      else if (com[0].equals("txoid"))
        src = s1 + generateOID((ValueSet) resource, false) + s3;
      else if (com[0].equals("vsoid"))
        src = s1 + generateOID((ValueSet) resource, true) + s3;
      else if (com[0].equals("txname"))
        src = s1 + Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vsname"))
        src = s1 + ((ValueSet) resource).getName() + s3;
      else if (com[0].equals("vsref")) {
        if (resource != null)
          src = s1 + Utilities.fileTitle((String) resource.getUserData("filename")) + s3;
        else {
          throw new Error("fix this");
//        BindingSpecification bs = definitions.getBindingByName(Utilities.fileTitle(file));
//        if (bs == null)
//          src = s1 + Utilities.fileTitle(file) + s3;
//        else
//          src = s1 + bs.getReference() + s3;
        }
      } else if (com[0].equals("txdesc"))
        src = s1 + generateDesc((ValueSet) resource) + s3;
      else if (com[0].equals("vsdesc"))
        src = s1 + (resource != null ? new XhtmlComposer().compose(((ValueSet) resource).getText().getDiv()) :  generateVSDesc(Utilities.fileTitle(file))) + s3;
      else if (com[0].equals("txusage"))
        src = s1 + generateBSUsage((ValueSet) resource) + s3;
      else if (com[0].equals("vsusage"))
        src = s1 + generateBSUsage((ValueSet) resource) + s3;
      else if (com[0].equals("vssummary"))
        src = s1 + "todo" + s3;
      else if (com[0].equals("compartmentlist"))
        src = s1 + compartmentlist() + s3;
      else if (com[0].equals("qa"))
        src = s1 + qa.report(this, validationErrors) + s3;
      else if (com[0].equals("comp-title"))
        src = s1 + compTitle(name) + s3;
      else if (com[0].equals("comp-desc"))
        src = s1 + compDesc(name) + s3;
      else if (com[0].equals("comp-uri"))
        src = s1 + compUri(name) + s3;
      else if (com[0].equals("comp-identity"))
        src = s1 + compIdentity(name) + s3;
      else if (com[0].equals("comp-membership"))
        src = s1 + compMembership(name) + s3;
      else if (com[0].equals("comp-resources"))
        src = s1 + compResourceMap(name) + s3;
      else if (com[0].equals("breadcrumb"))
        src = s1 + breadCrumbManager.make(name) + s3;
      else if (com[0].equals("navlist"))
        src = s1 + breadCrumbManager.navlist(name, genlevel(level)) + s3;
      else if (com[0].equals("breadcrumblist"))
        src = s1 + ((ig == null || ig.isCore()) ? breadCrumbManager.makelist(name, type, "", crumbTitle) : ig.makeList(name, type, genlevel(level), crumbTitle)) + s3;      
      else if (com[0].equals("year"))
        src = s1 + new SimpleDateFormat("yyyy").format(new Date()) + s3;      
      else if (com[0].equals("revision"))
        src = s1 + svnRevision + s3;  
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;      
      else if (com[0].equals("pub-notice"))
        src = s1 + publicationNotice + s3;
      else if (com[0].equals("vssource"))
        src = s1 + vsSource((ValueSet) resource) + s3;      
      else if (com[0].equals("vsxref"))
        src = s1 + xreferencesForFhir(name) + s3;      
      else if (com[0].equals("vsexpansion"))
        src = s1 + expandValueSet(Utilities.fileTitle(file), resource == null ? null : ((ValueSet) resource)) + s3;
      else if (com[0].equals("vscld"))
        src = s1 + vsCLD(Utilities.fileTitle(file), resource == null ? null : ((ValueSet) resource)) + s3;
      else if (com[0].equals("vsexpansionig"))
        src = s1 + expandValueSetIG((ValueSet) resource) + s3;
      else if (com[0].equals("v3expansion"))
        src = s1 + expandV3ValueSet(name) + s3;
      else if (com[0].equals("level"))
        src = s1 + genlevel(level) + s3;  
      else if (com[0].equals("archive"))
        src = s1 + makeArchives() + s3;  
      else if (com[0].equals("pagepath"))
        src = s1 + pagePath + s3;  
      else if (com[0].equals("rellink"))
        src = s1 + Utilities.URLEncode(pagePath) + s3;  
      else if (com[0].equals("baseURL"))
        src = s1 + Utilities.URLEncode(baseURL) + s3;  
      else if (com[0].equals("profilelist"))
        src = s1 + genProfilelist() + s3;  
      else if (com[0].equals("igprofileslist"))
        src = s1 + genIGProfilelist() + s3;  
      else if (com[0].equals("operationslist"))
        src = s1 + genOperationList() + s3;  
      else if (com[0].equals("example.profile.link"))
        src = s1 + genExampleProfileLink(resource) + s3;  
      else if (com[0].equals("id_regex"))
        src = s1 + FormatUtilities.ID_REGEX + s3;  
      else if (com[0].equals("resourcecount"))
        src = s1 + Integer.toString(definitions.getResources().size()) + s3;  
      else if (others != null && others.containsKey(com[0]))  
        src = s1 + others.get(com[0]) + s3; 
      else if (com[0].equals("status-codes"))
        src = s1 + genStatusCodes() + s3;        
      else if (com[0].equals("dictionary.name")) {
        String n = name.contains(File.separator) ? name.substring(name.lastIndexOf(File.separator)+1) : name;
        src = s1 + definitions.getDictionaries().get(n).getName() + s3;        
      } else if (com[0].equals("dictionary.view"))
        src = s1 + ResourceUtilities.representDataElementCollection(this.workerContext, (Bundle) resource, true, "hspc-qnlab-de") + s3;        
      else if (com[0].equals("search-param-pack") && resource instanceof SearchParameter)
        src = s1 + ((SearchParameter) resource).getUserData("pack") + s3;        
      else if (com[0].equals("search-param-name") && resource instanceof SearchParameter)
        src = s1 + ((SearchParameter) resource).getName() + s3;        
      else if (com[0].equals("search-param-url") && resource instanceof SearchParameter)
        src = s1 + ((SearchParameter) resource).getUrl() + s3;        
      else if (com[0].equals("search-param-type") && resource instanceof SearchParameter)
        src = s1 + ((SearchParameter) resource).getType().toCode() + s3;        
      else if (com[0].equals("search-param-definition") && resource instanceof SearchParameter)
        src = s1 + ((SearchParameter) resource).getDescription() + s3;        
      else if (com[0].equals("search-param-paths") && resource instanceof SearchParameter)
        src = s1 + (((SearchParameter) resource).hasXpath() ? ((SearchParameter) resource).getXpath() : "") + s3;        
      else if (com[0].equals("search-param-targets") && resource instanceof SearchParameter) {
        CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
        for (CodeType t : ((SearchParameter) resource).getTarget())
          b.append(t.asStringValue());
        src = s1 + b.toString() + s3;
      }
      else if (com[0].startsWith("!"))
        src = s1 + s3;  
      else if (com[0].equals("txsummary"))
        src = s1 + txsummary((ValueSet) resource) + s3;
      else if (com[0].equals("pc.title"))
        src = s1 +Utilities.escapeXml(((ProfileComparer) object).getTitle()) + s3;
      else if (com[0].equals("pc.left"))
        src = s1 + genPCLink(((ProfileComparer) object).getLeftName(), ((ProfileComparer) object).getLeftLink()) + s3;
      else if (com[0].equals("pc.right"))
        src = s1 + genPCLink(((ProfileComparer) object).getRightName(), ((ProfileComparer) object).getRightLink()) + s3;
      else if (com[0].equals("pc.table"))
        src = s1 + genPCTable((ProfileComparer) object) + s3;
      else if (com[0].equals("pc.valuesets"))
        src = s1 + "<p>todo</p>"+s3;
      else if (com[0].equals("cmp.left"))
        src = s1 + genPCLink(((ProfileComparison) object).getLeft().getName(), ((ProfileComparison) object).getLeft().getUserString("path")) + s3;
      else if (com[0].equals("cmp.right"))
        src = s1 + genPCLink(((ProfileComparison) object).getRight().getName(), ((ProfileComparison) object).getRight().getUserString("path")) + s3;
      else if (com[0].equals("cmp.messages"))
        src = s1 + "<p>"+genCmpMessages(((ProfileComparison) object))+"</p>"+s3;
      else if (com[0].equals("cmp.subset"))
        src = s1 + genCompModel(((ProfileComparison) object).getSubset(), "intersection", file.substring(0,file.indexOf(".")), genlevel(level))+s3;
      else if (com[0].equals("cmp.superset"))
        src = s1 + genCompModel(((ProfileComparison) object).getSuperset(), "union", file.substring(0,file.indexOf(".")), genlevel(level))+s3;
      else if (com[0].equals("identifierlist"))
        src = s1 + genIdentifierList()+s3;
      else if (com[0].equals("internalsystemlist"))
        src = s1 + genCSList()+s3;
      else if (com[0].equals("example-usage"))
        src = s1+s3;
      else if (com[0].equals("ig.title"))
        src = s1+ig.getName()+s3;
      else if (com[0].equals("ig.wglink"))
        src = s1+igLink(ig)+s3;
      else if (com[0].equals("ig.wgt"))
        src = s1+ig.getCommittee()+s3;
      else if (com[0].equals("ig.fmm"))
        src = s1+ig.getFmm()+s3;
      else if (com[0].equals("ig.ballot"))
        src = s1+ig.getBallot()+s3;
      else if (com[0].equals("igvsregistries"))
        src = s1+igVsRegistryList()+s3;
      else if (com[0].equals("operations")) {
        Profile p = (Profile) object;
        src = s1 + genOperations(p.getOperations(), p.getTitle(), p.getId(), "../") + s3;  
      } else if (com[0].equals("operations-summary"))
        src = s1 + genOperationsSummary(((Profile) object).getOperations()) + s3;  
      else if (com[0].equals("ig.opcount"))
        src = s1 + genOpCount(((Profile) object).getOperations()) + s3;  
      else if (com[0].equals("fhir-path"))
        src = s1 + "../" + s3;  
      else
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
    }
    return src;
  }

  private String buildIgRegistry(ImplementationGuideDefn ig, String types) throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"codes\">\r\n");
    b.append("<tr><td><b>Id</b></td><td><b>Name</b></td><td><b>Description</b></td></tr>\r\n");
    for (String type : types.split("\\,")) {
      List<String> ids = new ArrayList<String>();
      Map<String, ImplementationGuidePackageResourceComponent> map = new HashMap<String, ImplementationGuidePackageResourceComponent>();
      for (ImplementationGuidePackageComponent p : ig.getIg().getPackage()) {
        for (ImplementationGuidePackageResourceComponent r : p.getResource()) {
          Resource ar = (Resource) r.getUserData(ToolResourceUtilities.RES_ACTUAL_RESOURCE);
          if (ar != null && ar.getResourceType().toString().equals(type)) {
            String id = ar.getId();
            ids.add(id);
            map.put(id, r);
          }
        }
      }
      if (ids.size() > 0) {
        Collections.sort(ids);
        b.append("<tr><td colspan=\"3\" style=\"background: #DFDFDF\"><b>"+Utilities.pluralizeMe(type)+"</b></td></tr>\r\n");
        for (String id : ids) {
          ImplementationGuidePackageResourceComponent r = map.get(id);
          b.append("<tr><td><a href=\""+Utilities.changeFileExt(r.getSourceUriType().asStringValue(), ".html")+"\">"+id+"</a></td><td>"+Utilities.escapeXml(r.getName())+"</td><td>"+Utilities.escapeXml(r.getDescription())+"</td></tr>\r\n");
        }
      }
    }
    b.append("</table>\r\n");
    return b.toString();
  }

  private String vsWarning(ValueSet resource) throws Exception {
    String warning = ToolingExtensions.readStringExtension(resource, "http://hl7.org/fhir/StructureDefinition/valueset-warning");
    if (Utilities.noString(warning))
      return "";
    return "<div class=\"warning\">\r\n<p><b>Note for Implementer:</b></p>"+processMarkdown("vs-warning", warning, "")+"</div>\r\n";
  }

  private String fileTail(String name) {
    int i = name.lastIndexOf(File.separator);
    return name.substring(i+1);
  }

  private String getWgLink(String filename, String code) {
    definitions.page(filename).setWgCode(code);
    return definitions.getWorkgroups().containsKey(code) ? definitions.getWorkgroups().get(code).getUrl() : "?"+code+"?";
  }

  private String getWgTitle(String code) {
    return definitions.getWorkgroups().containsKey(code) ? definitions.getWorkgroups().get(code).getName() : "?"+code+"?";
  }

  private String genIdentifierList() throws Exception {
    StringBuilder b = new StringBuilder();
    for (NamingSystem ns : definitions.getNamingSystems()) {
      b.append("<tr>\r\n");
      b.append("  <td>"+Utilities.escapeXml(ns.getName())+"</td>\r\n");
      String uri = getUri(ns);
      String oid = getOid(ns);
      b.append("  <td>"+Utilities.escapeXml(uri)+"</td>\r\n");
      b.append("  <td>"+(oid == null ? "" : oid)+"</td>\r\n");
      String country = getCountry(ns);
      country = country == null ? "" : " ("+country+")";
      if (ns.hasType()) {
        Coding c = ns.getType().getCoding().get(0);
        if (c == null)
          b.append("  <td>"+Utilities.escapeXml(ns.getType().getText())+country+"</td>\r\n");
        else {
         if (c.getSystem().equals("http://hl7.org/fhir/identifier-type")) 
           b.append("  <td><a href=\"valueset-identifier-type.html#"+c.getCode()+"\">"+c.getCode()+"</a>"+country+"</td>\r\n");
         else if (c.getSystem().equals("http://hl7.org/fhir/v2/0203")) 
           b.append("  <td><a href=\"v2/0203/index.html#"+c.getCode()+"\">"+c.getCode()+"</a>"+country+"</td>\r\n");
         else 
           throw new Exception("Unknown Identifier Type System");
        }        
      } else
        b.append("  <td>"+country+"</td>\r\n");
      b.append("  <td>"+Utilities.escapeXml(ns.getDescription())+"</td>\r\n");
      b.append("</tr>\r\n");
    }
    return b.toString();
  }

  private String getCountry(NamingSystem ns) {
    for (CodeableConcept cc : ns.getUseContext()) {
      for (Coding c : cc.getCoding()) {
        if (c.getSystem().equals("urn:iso:std:iso:3166"))
          return c.hasDisplay() ? c.getDisplay() : c.getCode();
      }
    }
    return null;
  }

  private String getOid(NamingSystem ns) {
    for (NamingSystemUniqueIdComponent ui : ns.getUniqueId()) {
      if (ui.getType() == NamingSystemIdentifierType.OID && ui.hasValue())
        return ui.getValue();
    }
    return "";
  }

  private String getUri(NamingSystem ns) {
    for (NamingSystemUniqueIdComponent ui : ns.getUniqueId()) {
      if (ui.getType() == NamingSystemIdentifierType.URI && ui.hasValue())
        return ui.getValue();
    }
    return "";
  }

  private String genCompModel(StructureDefinition sd, String name, String base, String prefix) throws Exception {
    if (sd == null)
      return "<p style=\"color: maroon\">No "+name+" could be generated</p>\r\n";
    return new XhtmlComposer().compose(new ProfileUtilities(workerContext).generateTable("??", sd, false, folders.dstDir, false, this, base, true, prefix)); 
  }

  private String genCmpMessages(ProfileComparison cmp) {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append("<tr><td><b>Path</b></td><td><b>Message</b></td></tr>\r\n");
    b.append("<tr><td colspan=\"2\" style=\"background: #eeeeee\">Errors Detected</td></tr>\r\n");
    boolean found = false;
    for (ValidationMessage vm : cmp.getMessages())
      if (vm.getLevel() == IssueSeverity.ERROR || vm.getLevel() == IssueSeverity.FATAL) {
        found = true;
        b.append("<tr><td>"+vm.getLocation()+"</td><td>"+vm.getHtml()+(vm.getLevel() == IssueSeverity.FATAL ? "(<span style=\"color: maroon\">This error terminated the comparison process</span>)" : "")+"</td></tr>\r\n");
      }
    if (!found)
    b.append("<tr><td colspan=\"2\">(None)</td></tr>\r\n");
    
    boolean first = true;
    for (ValidationMessage vm : cmp.getMessages())
      if (vm.getLevel() == IssueSeverity.WARNING) {
        if (first) {
          first = false;
          b.append("<tr><td colspan=\"2\" style=\"background: #eeeeee\">Warnings about the comparison</td></tr>\r\n");
        }
        b.append("<tr><td>"+vm.getLocation()+"</td><td>"+vm.getHtml()+"</td></tr>\r\n");
      }
    first = true;
    for (ValidationMessage vm : cmp.getMessages())
      if (vm.getLevel() == IssueSeverity.INFORMATION) {
        if (first) {
          b.append("<tr><td colspan=\"2\" style=\"background: #eeeeee\">Notes about differences (e.g. definitions)</td></tr>\r\n");
          first = false;
        }
        b.append("<tr><td>"+vm.getLocation()+"</td><td>"+vm.getHtml()+"</td></tr>\r\n");
      }
    b.append("</table>\r\n");
    return b.toString();
  }

  private String genPCTable(ProfileComparer pc) {
    StringBuilder b = new StringBuilder();
    
    b.append("<table class=\"grid\">\r\n");
    b.append("<tr>");
    b.append(" <td><b>Left</b></td>");
    b.append(" <td><b>Right</b></td>");
    b.append(" <td><b>Comparison</b></td>");
    b.append(" <td><b>Error #</b></td>");
    b.append(" <td><b>Warning #</b></td>");
    b.append(" <td><b>Hint #</b></td>");
    b.append("</tr>");
    
    for (ProfileComparison cmp : pc.getComparisons()) {
      b.append("<tr>");
      b.append(" <td><a href=\""+cmp.getLeft().getUserString("path")+"\">"+Utilities.escapeXml(cmp.getLeft().getName())+"</a></td>");
      b.append(" <td><a href=\""+cmp.getRight().getUserString("path")+"\">"+Utilities.escapeXml(cmp.getRight().getName())+"</a></td>");
      b.append(" <td><a href=\""+pc.getId()+"."+cmp.getId()+".html\">Click Here</a></td>");
      b.append(" <td>"+cmp.getErrorCount()+"</td>");
      b.append(" <td>"+cmp.getWarningCount()+"</td>");
      b.append(" <td>"+cmp.getHintCount()+"</td>");
      b.append("</tr>");
    }
    b.append("</table>\r\n");
    
    return b.toString();
  }

  private String genPCLink(String leftName, String leftLink) {
    return "<a href=\""+leftLink+"\">"+Utilities.escapeXml(leftName)+"</a>";
  }

  private String genScList(String path) throws Exception {
    ResourceDefn r = definitions.getResourceByName(path.substring(0, path.indexOf(".")));
    if (r == null)
      throw new Exception("Unable to process sclist (1): "+path);
    ElementDefn e = r.getRoot().getElementByName(path.substring(path.indexOf(".")+1));
    if (e == null)
      throw new Exception("Unable to process sclist (2): "+path);
    if (e.typeCode().equals("boolean"))
      return "true | false";
    else {
      StringBuilder b = new StringBuilder();
      boolean first = true;
      for (ConceptDefinitionComponent cc : e.getBinding().getValueSet().getCodeSystem().getConcept()) {
        if (first)
          first = false;
        else
          b.append(" | ");
        b.append("<span title=\""+cc.getDisplay()+": "+Utilities.escapeXml(cc.getDefinition())+"\">"+cc.getCode()+"</span>");
      }
      return b.toString();
    }      
  }

  private String txsummary(ValueSet vs) {
    String c = "";
    if (vs.hasCopyright())
      c = "<tr><td>Copyright:</td><td>"+Utilities.escapeXml(vs.getCopyright())+"</td></tr>\r\n";
    if (vs.hasCodeSystem()) {
      return c+"<tr><td>System URL:</td><td>"+vs.getCodeSystem().getSystem()+"</td></tr>\r\n<tr><td>System OID:</td><td>"+ToolingExtensions.getOID(vs.getCodeSystem())+"</td></tr>";
    } else
      return c+"";
  }

  private String genExampleProfileLink(Resource resource) {
    if (resource == null || !(resource instanceof StructureDefinition))
      return "";
    StructureDefinition sd = (StructureDefinition) resource;
    if (!sd.hasConstrainedType())
      return "";
    String pack = "";
    if (sd.hasUserData("pack")) {
      Profile p = (Profile) sd.getUserData("pack");
      ImplementationGuideDefn ig = definitions.getIgs().get(p.getCategory());
      if (Utilities.noString(ig.getPage()))  
        pack = " ("+ig.getName()+"))";
      else
        pack = " (<a href=\""+ig.getPage()+"\">"+ig.getName()+"</a>)";
      if (!p.getTitle().equals(sd.getName()))
        pack = " in <a href=\""+p.getId()+".html\">"+p.getTitle()+"</a> "+pack;
    }
    if (sd.hasUserData("path"))
      return "This example conforms to the <a href=\""+sd.getUserData("path")+"\">profile "+(sd.getName())+"</a>"+pack+".";
    else
      return "This example conforms to the <a href=\""+sd.getId().toLowerCase()+".html\">profile "+(sd.getName())+"</a>"+pack+".";
  }

  private String umlForDt(String dt, String id) throws Exception {
    File tmp = Utilities.createTempFile("tmp", ".tmp");
    tmp.deleteOnExit();
    try {
      String s = "\r\n[diagram]\r\n"+
          "classes="+dt+"\r\n"+
          "element-attributes=true\r\n";
      TextFile.stringToFileNoPrefix(s, tmp.getAbsolutePath());
      return new SvgGenerator(this, "").generate(tmp.getAbsolutePath(), id);
    } finally {
      tmp.delete();
    }
  }

  private String genExtensionsTable() throws Exception {
    StringBuilder s = new StringBuilder();
    
    s.append("<table class=\"list\">\r\n");
    List<String> names = new ArrayList<String>();
    names.addAll(workerContext.getExtensionDefinitions().keySet());
    Collections.sort(names);
    for (ImplementationGuideDefn ig : definitions.getSortedIgs()) {
      boolean started = false;
      for (String n : names) {
        StructureDefinition ed = workerContext.getExtensionDefinitions().get(n);
        if (ig.getCode().equals(ToolResourceUtilities.getUsage(ed))) {
          if (!started) {
            started = true;
            genStructureExampleCategory(s, ig.getName());            
          }
          genExtensionRow(ig, s, ed);
        }
      }
    }
    s.append("</table>\r\n");
    return s.toString();
  }

  private void genExtensionRow(ImplementationGuideDefn ig, StringBuilder s, StructureDefinition ed) throws Exception {
    s.append("<tr>");
    s.append("<td><a href=\""+ed.getUserString("path")+"\">"+ed.getId()+"</a></td>");
    s.append("<td>"+Utilities.escapeXml(ed.getName())+"</td>");
    s.append("<td>");
    boolean first = true;
    if (ed.getContextType() == ExtensionContext.RESOURCE) {
      for (StringType t : ed.getContext()) {
        if (first) 
          first = false;
        else
          s.append(",<br/> ");
        String ref = Utilities.oidRoot(t.getValue());
        if (ref.startsWith("@"))
          ref = ref.substring(1);
        if (definitions.hasResource(ref))
          s.append("<a href=\""+ref.toLowerCase()+".html\">"+t.getValue()+"</a>");
        else
          s.append(t.getValue());
      }
    } else if (ed.getContextType() == ExtensionContext.DATATYPE) {
        for (StringType t : ed.getContext()) {
          if (first) 
            first = false;
          else
            s.append(",<br/> ");
          String ref = Utilities.oidRoot(t.getValue());
          if (ref.startsWith("@"))
            ref = ref.substring(1);
          if (definitions.hasElementDefn(ref)) {
            s.append("<a href=\""+definitions.getSrcFile(ref)+".html#"+Utilities.oidRoot(t.getValue())+"\">"+t.getValue()+"</a>");
          } else
            s.append(t.getValue());
        }
    } else
      throw new Error("Not done yet");
    s.append("</td>");
//    s.append("<td><a href=\"extension-"+ed.getId().toLowerCase()+ ".xml.html\">XML</a></td>");
//    s.append("<td><a href=\"extension-"+ed.getId().toLowerCase()+ ".json.html\">JSON</a></td>");
    s.append("</tr>");
  }

  private String vsSource(ValueSet vs) {
    if (vs == null)
      return "by the FHIR project";
    if (vs == null || vs.getContact().isEmpty() || vs.getContact().get(0).getTelecom().isEmpty() || vs.getContact().get(0).getTelecom().get(0).getSystem() != ContactPointSystem.OTHER || vs.getContact().get(0).getTelecom().get(0).getValue().startsWith("http://hl7.org/fhir"))
      return "by the FHIR project";
    return " at <a href=\""+vs.getContact().get(0).getTelecom().get(0).getValue()+"\">"+vs.getContact().get(0).getTelecom().get(0).getValue()+"</a>";
  }

  private String orgDT(String name, String xml, String tree, String uml1, String uml2, String ref, String ts, String json) {
    StringBuilder b = new StringBuilder();
    b.append("<div id=\"tabs-").append(name).append("\">\r\n");
    b.append(" <ul>\r\n");
    b.append("  <li><a href=\"#tabs-"+name+"-struc\">Structure</a></li>\r\n");
    b.append("  <li><a href=\"#tabs-"+name+"-uml\">UML</a></li>\r\n");
    b.append("  <li><a href=\"#tabs-"+name+"-xml\">XML</a></li>\r\n");
    b.append("  <li><a href=\"#tabs-"+name+"-json\">JSON</a></li>\r\n");
    b.append("  <li><a href=\"#tabs-"+name+"-all\">All</a></li>\r\n");
    b.append(" </ul>\r\n");
    b.append(" <div id=\"tabs-"+name+"-struc\">\r\n");
    b.append("  <div id=\"tbl\">\r\n");
    b.append("   <p><b>Structure</b></p>\r\n");
    b.append("   <div id=\"tbl-inner\">\r\n");
    b.append("    "+tree+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append(" </div>\r\n");
    b.append("\r\n");
    b.append(" <div id=\"tabs-"+name+"-uml\">\r\n");
    b.append("  <div id=\"uml\">\r\n");
    b.append("   <p><b>UML Diagram</b></p>\r\n");
    b.append("   <div id=\"uml-inner\">\r\n");
    b.append("    "+uml1+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append(" </div>\r\n");
    b.append("\r\n");
    b.append(" <div id=\"tabs-"+name+"-xml\">\r\n");
    b.append("  <div id=\"xml\">\r\n");
    b.append("   <p><b>XML Template</b></p>\r\n");
    b.append("   <div id=\"xml-inner\">\r\n");
    b.append("    "+xml+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append(" </div>\r\n");
    b.append("\r\n");
    b.append(" <div id=\"tabs-"+name+"-json\">\r\n");
    b.append("  <div id=\"json\">\r\n");
    b.append("   <p><b>JSON Template</b></p>\r\b");
    b.append("   <div id=\"json-inner\">\r\n");
    b.append("    "+json+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append(" </div>\r\n");
    b.append("\r\n");
    b.append(" <div id=\"tabs-"+name+"-all\">\r\n");
    b.append("  <div id=\"tbla\">\r\n");
    b.append("   <a name=\"tbl\"> </a>\r\n");
    b.append("   <p><b>Structure</b></p>\r\n");
    b.append("   <div id=\"tbl-inner\">\r\n");
    b.append("    "+tree+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append("\r\n");
    b.append("  <div id=\"umla\">\r\n");
    b.append("   <a name=\"uml\"> </a>\r\n");
    b.append("   <p><b>UML Diagram</b></p>\r\n");
    b.append("   <div id=\"uml-inner\">\r\n");
    b.append("    "+uml2+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append("\r\n");
    b.append("  <div id=\"xmla\">\r\n");
    b.append("   <a name=\"xml\"> </a>\r\n");
    b.append("   <p><b>XML Template</b></p>\r\n");
    b.append("   <div id=\"xml-inner\">\r\n");
    b.append("     "+xml+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append("\r\n");
    b.append("  <div id=\"jsona\">\r\n");
    b.append("   <a name=\"json\"> </a>\r\n");
    b.append("   <p><b>JSON Template</b></p>\r\n");
    b.append("   <div id=\"json-inner\">\r\n");
    b.append("     "+json+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append(" </div>\r\n");
    b.append("</div>\r\n");
    return b.toString();
  }

  private String allParamlist() {
    ResourceDefn rd = definitions.getBaseResources().get("Resource");
    List<String> names = new ArrayList<String>();
    names.addAll(rd.getSearchParams().keySet());
    Collections.sort(names);
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (String n  : names) 
      b.append(n);
    return b.toString();
  }

  private String makeCanonical(String name) {
    int i = name.lastIndexOf(".");
    if (i == -1)
      throw new Error("unable to get canonical name for "+name);
    return name.substring(0, i)+".canonical"+name.substring(i);
  }

  private String genIGProfilelist() {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append("  <tr>\r\n");
    b.append("    <td><b>Name</b></td>\r\n");
    b.append("    <td><b>Usage</b></td>\r\n");
    b.append("  </tr>\r\n");
    
    List<String> names = new ArrayList<String>();
    for (Resource ae : igResources.values()) {
      if (ae instanceof StructureDefinition) 
        names.add(ae.getId());
    }
    Collections.sort(names);
    
    for (String s : names) {
      @SuppressWarnings("unchecked")
      StructureDefinition ae  = (StructureDefinition) igResources.get(s);
      b.append("  <tr>\r\n");
      b.append("    <td><a href=\""+((String) ae.getUserData("path")).replace(".xml", ".html")+"\">"+Utilities.escapeXml(ae.getName())+"</a></td>\r\n");
      b.append("    <td>").append(Utilities.escapeXml(ae.getDescription())).append("</td>\r\n");
      b.append(" </tr>\r\n");
    }
    b.append("</table>\r\n");
   
    return b.toString();
  }

  private String genOperationList() throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">");
    b.append(" <tr><td colspan=\"2\"><b>Base Operations (All resource types)</b></td></tr>\r\n");
    for (ResourceDefn r : definitions.getBaseResources().values()) {
      genOperationDetails(b, r.getName(), r.getOperations(), true);
    }
    b.append(" <tr><td colspan=\"2\"><b>Operations Defined by Resource Types</b></td></tr>\r\n");
    for (String n : definitions.sortedResourceNames()) {
      ResourceDefn r = definitions.getResourceByName(n);
      genOperationDetails(b, n, r.getOperations(), false);
    }
    b.append(" <tr><td colspan=\"2\"><b>Operations Defined by Implementation Guides</b></td></tr>\r\n");
    for (ImplementationGuideDefn ig : definitions.getSortedIgs()) {
      for (Profile p : ig.getProfiles()) {
        if (!p.getOperations().isEmpty())
          genOperationDetails(b, ig.getCode()+File.separator+p.getId(), p.getOperations(), false);
      }
    }
    b.append("</table>");
    return b.toString();
  }

  private void genOperationDetails(StringBuilder b, String n, List<Operation> oplist, boolean isAbstract) {
    for (Operation op : oplist) {
      b.append("<tr><td><a href=\"").append(n.toLowerCase()).append("-operations.html#").append(op.getName()).append("\">");
      b.append(Utilities.escapeXml(op.getTitle()));
      b.append("</a></td><td>");
      boolean first = true;
      if (op.isSystem()) {
        first = false;
        b.append("[base]/$");
        b.append(op.getName());
      }
      if (op.isType()) {
        if (first) 
          first = false;
        else 
          b.append(" | ");
        b.append("[base]/");
        if (isAbstract)
          b.append("["+n+"]");
        else
          b.append(n);
        b.append("/$");
        b.append(op.getName());
      }
      if (op.isInstance()) {
        if (first) 
          first = false;
        else 
          b.append(" | ");
        b.append("[base]/");
        if (isAbstract)
          b.append("["+n+"]");
        else
          b.append(n);
        b.append("/[id]/$");
        b.append(op.getName());
      }
      b.append("</td></tr>");
    }
  }

  private String genProfilelist() throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append("  <tr>\r\n");
    b.append("    <td><b>Name</b></td>\r\n");
    b.append("    <td><b>Usage</b></td>\r\n");
    b.append("  </tr>\r\n");
    
    b.append("  <tr>\r\n");
    b.append("    <td colspan=\"2\"><b>General</b></td>\r\n");
    b.append("  </tr>\r\n");
    List<String> names = new ArrayList<String>();
    names.addAll(definitions.getPackMap().keySet());
    Collections.sort(names);
    for (String s : names) {
      Profile ap = definitions.getPackMap().get(s);
      ImplementationGuideDefn ig = definitions.getIgs().get(ap.getCategory());
      b.append("  <tr>\r\n");
      b.append("    <td><a href=\"").append(((ig == null || ig.isCore()) ? "" : ig.getCode()+"/")+ap.getId()).append(".html\">").append(Utilities.escapeXml(ap.getTitle())).append("</a></td>\r\n");
      b.append("    <td>").append(Utilities.escapeXml(ap.getDescription())).append("</td>\r\n");
      b.append(" </tr>\r\n");
    }
// todo-profiles - do we want to list these here?    
//    for (String n : definitions.sortedResourceNames()) {
//      ResourceDefn r = definitions.getResourceByName(n);
//      if (!r.getProfiles().isEmpty()) {
//        b.append("  <tr>\r\n");
//        b.append("    <td colspan=\"2\"><b>"+r.getName()+"</b></td>\r\n");
//        b.append("  </tr>\r\n");
//        for (RegisteredProfile p : r.getProfiles()) {
//          b.append("  <tr>\r\n");
//          b.append("    <td><a href=\""+p.getDestFilenameNoExt()+".html\">"+Utilities.escapeXml(p.getName())+"</a></td>\r\n");
//          b.append("    <td>"+Utilities.escapeXml(p.getProfile().getSource().getDescription())+"</td>\r\n");
//          b.append(" </tr>\r\n");
//        }
//      }
//    }
    b.append("</table>\r\n");
   
    return b.toString();
  }

  private String profileRef(String name) {
    return "Alternate definitions: Resource StructureDefinition (<a href=\""+name+".profile.xml.html\">XML</a>, <a href=\""+name+".profile.json.html\">JSON</a>)";
  }

  private String reflink(String name) {
    for (PlatformGenerator t : referenceImplementations) 
      if (t.getName().equals(name))
        return t.getReference(version);
    return "??";
  }

  private String conceptmaplist(String id, String level) {
    List<ConceptMap> cmaps = new ArrayList<ConceptMap>();
    for (ConceptMap cm : conceptMaps.values()) {
      if (((Reference) cm.getSource()).getReference().equals(id) || ((Reference) cm.getTarget()).getReference().equals(id))
        cmaps.add(cm);
    }
    if (cmaps.size() == 0)
      return "";
    else {
      String prefix = "";
      if (level.equals("l1"))
        prefix = "../";
      else if (level.equals("l2"))
        prefix = "../../";
      else if (level.equals("l3"))
        prefix = "../../../";
      StringBuilder b = new StringBuilder();
      b.append("<p>Concept Maps for this value set:</p>");
      b.append("<table class=\"grid\">\r\n");
      for (ConceptMap cm : cmaps) {
        b.append(" <tr><td>");
        if (((Reference) cm.getSource()).getReference().equals(id)) {
          b.append("to <a href=\"").append(getValueSetRef(prefix, ((Reference) cm.getTarget()).getReference())).append("\">")
                  .append(describeValueSetByRef(((Reference) cm.getTarget()).getReference()));
        } else {
          b.append("from <a href=\"").append(getValueSetRef(prefix, ((Reference) cm.getSource()).getReference())).append("\">")
                  .append(describeValueSetByRef(((Reference) cm.getSource()).getReference()));
        }
        b.append("</a></td><td><a href=\"").append(prefix).append(cm.getUserData("path")).append("\">").append(cm.getName())
                .append("</a></td><td><a href=\"").append(prefix).append(Utilities.changeFileExt((String) cm.getUserData("path"), ".xml.html"))
                .append("\">XML</a></td><td><a href=\"").append(prefix).append(Utilities.changeFileExt((String) cm.getUserData("path"), ".json.html")).append("\">JSON</a></td></tr>");
      }
      b.append("</table>\r\n");
      return b.toString();
    }
  }

  private String getValueSetRef(String prefix, String ref) {
    ValueSet vs = valueSets.get(ref);
    if (vs == null) {
      if (ref.equals("http://snomed.info/id"))
        return "http://snomed.info";
      else 
        return ref;
    } else
      return prefix+vs.getUserData("path");
  }

  private String describeValueSetByRef(String ref) {
    ValueSet vs = valueSets.get(ref);
    if (vs == null) {
      if (ref.equals("http://snomed.info/id"))
        return "Snomed CT";
      else 
        return ref;
    } else
      return vs.getName();
  }

  private String xreferencesForV2(String name, String level) {
    if (!valueSets.containsKey("http://hl7.org/fhir/ValueSet/v2-"+name))
      return ". ";
    String n = valueSets.get("http://hl7.org/fhir/ValueSet/v2-"+name).getName().replace("-", "").replace(" ", "").replace("_", "").toLowerCase();
    StringBuilder b = new StringBuilder();
    String pfx = "../../";
    if (level.equals("l3"))
      pfx = "../../../";
    ValueSet ae = findRelatedValueset(n, valueSets, "http://hl7.org/fhir/ValueSet/");
    if (ae != null)
      b.append(". Related FHIR content: <a href=\"").append(pfx).append(ae.getUserData("path")).append("\">").append(ae.getName()).append("</a>");
    ae = findRelatedValueset(n, valueSets, "http://hl7.org/fhir/ValueSet/v3-");
    if (ae != null)
      b.append(". Related v3 content: <a href=\"").append(pfx).append(ae.getUserData("path")).append("\">").append(ae.getName()).append("</a>");
    return b.toString()+". ";
  }

  private String xreferencesForFhir(String name) {
    String n = name.replace("-", "").toLowerCase();
    StringBuilder b = new StringBuilder();
    ValueSet ae = findRelatedValueset(n, valueSets, "http://hl7.org/fhir/ValueSet/v2-");
    if (ae != null)
      b.append(". Related v2 content: <a href=\"").append(ae.getUserData("path")).append("\">").append(ae.getName()).append("</a>");
    ae = findRelatedValueset(n, valueSets, "http://hl7.org/fhir/ValueSet/v3-");
    if (ae != null)
      b.append(". Related v3 content: <a href=\"").append(ae.getUserData("path")).append("\">").append(ae.getName()).append("</a>");
    return b.toString()+". ";
  }

  private String xreferencesForV3(String name, boolean vs) {
    String n = name.replace("-", "").replace(" ", "").replace("_", "").toLowerCase();
    StringBuilder b = new StringBuilder();
    ValueSet ae = findRelatedValueset(n, valueSets, "http://hl7.org/fhir/ValueSet/v2-");
    String path = vs ? "../../../" : "../../";
    if (ae != null)
      b.append(". Related v2 content: <a href=\"").append(path).append(ae.getUserData("path")).append("\">").append(ae.getName()).append("</a>");
    ae = findRelatedValueset(n, valueSets, "http://hl7.org/fhir/ValueSet/");
    if (ae != null)
      b.append(". Related FHIR content: <a href=\"").append(path).append(ae.getUserData("path")).append("\">").append(ae.getName()).append("</a>");
    return b.toString()+". ";
  }

  private ValueSet findRelatedValueset(String n, Map<String, ValueSet> vslist, String prefix) {
    for (String s : vslist.keySet()) {
      ValueSet ae = vslist.get(s); 
      String url = ae.getUrl();
      if (url.startsWith(prefix)) {
        String name = url.substring(prefix.length()).replace("-", "").replace(" ", "").replace("_", "").toLowerCase();
        if (n.equals(name))
          return ae;
        name = ae.getName().replace("-", "").replace(" ", "").replace("_", "").toLowerCase();
        if (n.equals(name))
          return ae;
      }
    }
    return null;
  }

  public String genlevel(int level) {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < level; i++) {
      b.append("../");
    }
    return b.toString();
  }

  private String compTitle(String name) {
    String n = name.split("\\-")[1];
    return definitions.getCompartmentByName(n).getTitle();
  }

  private String compDesc(String name) {
    String n = name.split("\\-")[1];
    return definitions.getCompartmentByName(n).getDescription();
  }

  private String compUri(String name) {
    String n = name.split("\\-")[1];
    return definitions.getCompartmentByName(n).getUri();
  }

  private String compIdentity(String name) {
    String n = name.split("\\-")[1];
    return definitions.getCompartmentByName(n).getIdentity();
  }

  private String compMembership(String name) {
    String n = name.split("\\-")[1];
    return definitions.getCompartmentByName(n).getMembership();
  }

  private String compResourceMap(String name) throws Exception {
    String n = name.split("\\-")[1];
    StringBuilder in = new StringBuilder();
    StringBuilder out = new StringBuilder();
    Map<ResourceDefn, String> map = definitions.getCompartmentByName(n).getResources();
    for (String rn : definitions.sortedResourceNames()) {
      ResourceDefn rd = definitions.getResourceByName(rn);
      String rules = map.get(rd);
      if (Utilities.noString(rules)) {
        out.append(" <li><a href=\"").append(rd.getName().toLowerCase()).append(".html\">").append(rd.getName()).append("</a></li>\r\n");
      } else if (!rules.equals("{def}")) {
        in.append(" <tr><td><a href=\"").append(rd.getName().toLowerCase()).append(".html\">").append(rd.getName()).append("</a></td><td>").append(rules.replace("|", "or")).append("</td></tr>\r\n");
      }
    }
    return "<p>\r\nThe following resources may be in this compartment:\r\n</p>\r\n" +
        "<table class=\"grid\">\r\n"+
        " <tr><td><b>Resource</b></td><td><b>Inclusion Criteria</b></td></tr>\r\n"+
        in.toString()+
        "</table>\r\n"+
        "<p>\r\nA resource is in this compartment if the nominated search parameter (or chain) refers to the patient resource that defines the compartment.\r\n</p>\r\n" +
        "<p>\r\n\r\n</p>\r\n" +        
        "<p>\r\nThe following resources are never in this compartment:\r\n</p>\r\n" +
        "<ul>\r\n"+
        out.toString()+
        "</ul>\r\n";
  }

  private String compartmentlist() {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append(" <tr><td><b>Title</b></td><td><b>Description</b></td><td><b>Identity</b></td><td><b>Membership</b></td></tr>\r\n");
    for (Compartment c : definitions.getCompartments()) {
      b.append(" <tr><td><a href=\"compartment-").append(c.getName()).append(".html\">").append(c.getTitle()).append("</a></td><td>")
              .append(Utilities.escapeXml(c.getDescription())).append("</td>").append("<td>").append(Utilities.escapeXml(c.getIdentity())).append("</td><td>").append(Utilities.escapeXml(c.getMembership())).append("</td></tr>\r\n");
    }
    b.append("</table>\r\n");
    return b.toString();
  }

  private String genV3CodeSystem(String name) throws Exception {
    ValueSet vs = codeSystems.get("http://hl7.org/fhir/v3/"+name);
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".xml"), vs);
    cloneToXhtml(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".xml", folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".xml.html", vs.getName(), vs.getDescription(), 2, false, "v3:cs:"+name, "CodeSystem", null);
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".json"), vs);
    jsonToXhtml(Utilities.path(folders.dstDir, "v3", name, "v3-"+name+".json"), Utilities.path("v3", name, "v3-"+name+".json.html"), "v3-"+name+".json", vs.getName(), vs.getDescription(), 2, r2Json(vs), "v3:cs:"+name, "CodeSystem", null);

    return new XhtmlComposer().compose(vs.getText().getDiv());
  }

  private String genV3ValueSet(String name) throws Exception {
    ValueSet vs = valueSets.get("http://hl7.org/fhir/ValueSet/v3-"+FormatUtilities.makeId(name));
    if (vs == null)
      throw new Exception("unable to find v3 value set "+name);
    IParser xml = new XmlParser().setOutputStyle(OutputStyle.PRETTY);
    xml.compose(new FileOutputStream(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".xml"), vs);
    cloneToXhtml(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".xml", folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".xml.html", vs.getName(), vs.getDescription(), 3, false, "v3:vs:"+name, "ValueSet", null);
    IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
    json.compose(new FileOutputStream(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".json"), vs);
    jsonToXhtml(Utilities.path(folders.dstDir, "v3", name, "v3-"+name+".json"), Utilities.path("v3", name, "v3-"+name+".json.html"), "v3-"+name+".json", vs.getName(), vs.getDescription(), 2, r2Json(vs), "v3:vs:"+name, "ValueSet", null);

    return new XhtmlComposer().compose(vs.getText().getDiv()).replace("href=\"v3/", "href=\"../");
  }

  private String genV2TableVer(String name) throws Exception {
    String[] n = name.split("\\|");
    ValueSet vs = codeSystems.get("http://hl7.org/fhir/v2/"+n[0]+"/"+n[1]);
    IParser xml = new XmlParser().setOutputStyle(OutputStyle.PRETTY);
    xml.compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".xml"), vs);
    cloneToXhtml(folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".xml", folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".xml.html", vs.getName(), vs.getDescription(), 3, false, "v2:tbl"+name, "V2 Table", null);
    IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
    json.compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".json"), vs);
    jsonToXhtml(Utilities.path(folders.dstDir, "v2", n[0], n[1], "v2-"+n[0]+"-"+n[1]+".json"), Utilities.path("v2", n[0], n[1], "v2-"+n[0]+"-"+n[1]+".json.html"), "v2-"+n[0]+"-"+n[1]+".json", vs.getName(), vs.getDescription(), 3, r2Json(vs), "v2:tbl"+name, "V2 Table", null);
    addToValuesets(v2Valuesets, vs);
    
    return new XhtmlComposer().compose(vs.getText().getDiv());
  }

  private String genV2Table(String name) throws Exception {
    ValueSet vs = codeSystems.get("http://hl7.org/fhir/v2/"+name);
    IParser xml = new XmlParser().setOutputStyle(OutputStyle.PRETTY);
    xml.compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".xml"), vs);
    cloneToXhtml(folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".xml", folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".xml.html", vs.getName(), vs.getDescription(), 2, false, "v2:tbl"+name, "V2 Table", null);
    IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
    json.compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".json"), vs);
    jsonToXhtml(Utilities.path(folders.dstDir, "v2", name, "v2-"+name+".json"), Utilities.path("v2", name, "v2-"+name+".json.html"), "v2-"+name+".json", vs.getName(), vs.getDescription(), 2, r2Json(vs), "v2:tbl"+name, "V2 Table", null);
    addToValuesets(v2Valuesets, vs);
    return new XhtmlComposer().compose(vs.getText().getDiv());
  }

  private String r2Json(ValueSet vs) throws Exception {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
    json.setSuppressXhtml("Snipped for Brevity");
    json.compose(bytes, vs);
    return new String(bytes.toByteArray());
  }

  private void cloneToXhtml(String src, String dst, String name, String description, int level, boolean adorn, String pageType, String crumbTitle, ImplementationGuideDefn ig) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();

    Document xdoc = builder.parse(new CSFileInputStream(new CSFile(src)));
//    XhtmlGenerator xhtml = new XhtmlGenerator(null);
//    xhtml.generate(xdoc, new CSFile(dst), name, description, level, adorn);
    
    String n = new File(dst).getName();
    n = n.substring(0, n.length()-9);
    XhtmlGenerator xhtml = new XhtmlGenerator(new ExampleAdorner(definitions, genlevel(level)));
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    xhtml.generate(xdoc, b, name, description, level, adorn, n+".xml.html");
    String html = TextFile.fileToString(folders.srcDir + "template-example-xml.html").replace("<%setlevel 0%>", "<%setlevel "+Integer.toString(level)+"%>").replace("<%example%>", b.toString());
    html = processPageIncludes(n+".xml.html", html, pageType, null, null, null, crumbTitle, ig);
    TextFile.stringToFile(html, dst);
    
//    epub.registerFile(dst, description, EPubManager.XHTML_TYPE);
    epub.registerExternal(dst);
  }

  public void jsonToXhtml(String src, String dst, String link, String name, String description, int level, String json, String pageType, String crumbTitle, ImplementationGuideDefn ig) throws Exception {

    json = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml(description) + "</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(json)+ "\r\n</pre>\r\n</div>\r\n";
    String html = TextFile.fileToString(folders.srcDir + "template-example-json.html").replace("<%setlevel 0%>", "<%setlevel "+Integer.toString(level)+"%>").replace("<%example%>", json);
    html = processPageIncludes(dst, html, pageType, null, null, null, crumbTitle, ig);
    TextFile.stringToFile(html, getFolders().dstDir + dst);
//    epub.registerFile(dst, description, EPubManager.XHTML_TYPE);
    epub.registerExternal(dst);
//
//    
//    FileOutputStream outs = new FileOutputStream(folders.dstDir+ dst);
//    OutputStreamWriter out = new OutputStreamWriter(outs, "UTF-8");
//    
//    out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\r\n");
//    out.write("<head>\r\n");
//    out.write(" <title>Example Instance for "+name+"</title>\r\n");
//    out.write(" <link rel=\"Stylesheet\" href=\"");
//    for (int i = 0; i < level; i++)
//      out.write("../");
//    out.write("fhir.css\" type=\"text/css\" media=\"screen\"/>\r\n");
//    out.write("</head>\r\n");
//    out.write("<body>\r\n");
//    out.write("<p>&nbsp;</p>\r\n"); 
//    out.write("<div class=\"example\">\r\n");
//    out.write("<p>"+Utilities.escapeXml(description)+"</p>\r\n"); 
//    out.write("<p><a href=\""+link+"\">Raw JSON</a></p>\r\n"); 
//    out.write("<pre class=\"json\">\r\n");
//    out.write(Utilities.escapeXml(json));    
//    out.write("</pre>\r\n");
//    out.write("</div>\r\n");
//    out.write("</body>\r\n");
//    out.write("</html>\r\n");
//    out.flush();
//    outs.close();
//    epub.registerFile(dst, description, EPubManager.XHTML_TYPE);
  }

  
  private String genV2Index() {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"grid\">\r\n");
    s.append(" <tr><td><b>URI</b></td><td><b>ID</b></td><td><b>Comments</b></td></tr>\r\n");
    Element e = XMLUtil.getFirstChild(v2src.getDocumentElement());
    while (e != null) {
      String src = e.getAttribute("state");
      if ("include".equals(src) || "versioned".equals(src)) {
        String id = Utilities.padLeft(e.getAttribute("id"), '0', 4);
        String name = "";
        // we use the latest description of the table
        Element c = XMLUtil.getFirstChild(e);
        while (c != null) {
          name = c.getAttribute("desc");
          c = XMLUtil.getNextSibling(c);
        }
        if ("versioned".equals(src)) {
          
          List<String> versions = new ArrayList<String>();   
          
          s.append(" <tr><td>http://hl7.org/fhir/v2/").append(id).append("</td><td>").append(id).append("</td><td>").append(name).append("<br/>Version Dependent. Use one of:<ul>");
          c = XMLUtil.getFirstChild(e);
          while (c != null) {
            Element g = XMLUtil.getFirstChild(c);
            if (g != null && !versions.contains(c.getAttribute("namespace")))
              versions.add(c.getAttribute("namespace"));            
            c = XMLUtil.getNextSibling(c);
          }
          for (String v : versions)
            if (!Utilities.noString(v))
              s.append(" <li><a href=\"v2/").append(id).append("/").append(v).append("/index.html\">").append(v).append("</a></li>");
          s.append("</ul></td></tr>\r\n");
        } else
          s.append(" <tr><td><a href=\"v2/").append(id).append("/index.html\">http://hl7.org/fhir/v2/").append(id).append("</a></td><td>").append(name).append("</td><td></td></tr>\r\n");
      }
      e = XMLUtil.getNextSibling(e);
    }
    
    s.append("</table>\r\n");
    return s.toString();
  }

  private String genV3CSIndex() {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"grid\">\r\n");
    s.append(" <tr><td><b>Name (URI = http://hl7.org/fhir/v3/...)</b></td><td><b>Description</b></td><td><b>OID</b></td></tr>\r\n");
    
    List<String> names = new ArrayList<String>();
    Map<String, Resource> map = new HashMap<String, Resource>();
    
    for (BundleEntryComponent e : v3Valuesets.getEntry()) {
      ValueSet vs = (ValueSet)e.getResource();
      if (vs.hasCodeSystem()) {
        String n = vs.getCodeSystem().getSystem();
        names.add(n);
        map.put(n, vs);
      }
    }
    Collections.sort(names);

    for (String n : names) {
      Resource e = map.get(n);
      ValueSet vs = (ValueSet)e;
      String id = tail(vs.getUrl()).substring(3);
      if (vs.getCodeSystem() == null)
        throw new Error("VS "+vs.getUrl()+" has no define");
      String oid = ToolingExtensions.getOID(vs.getCodeSystem());
      if (oid != null)
        oid = oid.substring(8);
      s.append(" <tr><td><a href=\"v3/").append(id).append("/index.html\">").append(Utilities.escapeXml(id))
              .append("</a></td><td>").append(Utilities.escapeXml(vs.getDescription())).append("</td><td>").append(oid == null ? "--" : oid).append("</td></tr>\r\n");
    }
    
    s.append("</table>\r\n");
    return s.toString();
  }

  private String genV3VSIndex() {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"grid\">\r\n");
    s.append(" <tr><td><b>Name (URI = http://hl7.org/fhir/ValueSet/v3-...) </b></td><td><b>Name</b></td><td><b>OID</b></td></tr>\r\n");
    
    List<String> names = new ArrayList<String>();
    Map<String, Resource> map = new HashMap<String, Resource>();
    
    for (BundleEntryComponent e : v3Valuesets.getEntry()) {
      ValueSet vs = (ValueSet) e.getResource();
      if (!vs.hasCodeSystem()) {
        String n = vs.getUrl();
        names.add(n);
        map.put(n, vs);
      }
    }
    Collections.sort(names);

    for (String n : names) {
      Resource e = map.get(n);
      ValueSet vs = (ValueSet)e;
      String id = tail(vs.getUrl()).substring(3);
      String oid = ToolingExtensions.getOID(vs);
      if (oid != null)
        oid = oid.substring(8);
      String[] desc = vs.getDescription().split("\\(OID \\= ");
      s.append(" <tr><td><a href=\"v3/").append(id).append("/index.html\">").append(Utilities.escapeXml(id))
            .append("</a></td><td>").append(Utilities.escapeXml(vs.getDescription())).append("</td><td>").append(oid == null ? "--" : oid).append("</td></tr>\r\n");
//      s.append(" <tr><td><a href=\"ValueSet/vs-").append(id).append("/index.html\">")
//              .append(id).append("</a></td><td>").append(desc[0]).append("</td><td>").append(oid == null ? "==" : oid).append("</td></tr>\r\n");
    }
    
    s.append("</table>\r\n");
    return s.toString();
  }

  private String tail(String id) {
    int i = id.lastIndexOf("/");
    return id.substring(i+1);
  }

  private String genDataTypeMappings(String name) throws Exception {
    if (name.equals("primitives")) {
      StringBuilder b = new StringBuilder();
      b.append("<table class=\"grid\">\r\n");
      b.append("<tr>");
      b.append("<td><b>Data Type</b></td>");
      b.append("<td><b>V2</b></td>");
      b.append("<td><b>RIM</b></td>");
      b.append("</tr>");
      List<String> names = new ArrayList<String>();
      names.addAll(definitions.getPrimitives().keySet());
      Collections.sort(names);
      for (String n : names) {
        DefinedCode dc = definitions.getPrimitives().get(n);
        if (dc instanceof PrimitiveType) {
          PrimitiveType pt = (PrimitiveType) dc;
          b.append("<tr>");
          b.append("<td>").append(n).append("</td>");
          b.append("<td>").append(pt.getV2()).append("</td>");
          b.append("<td>").append(pt.getV3()).append("</td>");
          b.append("</tr>");
        }
      }
      b.append("</table>\r\n");      
      return b.toString();
    } else {
      List<ElementDefn> list = new ArrayList<ElementDefn>();
      //    list.addAll(definitions.getStructures().values());
      //    list.addAll(definitions.getTypes().values());
      //    list.addAll(definitions.getInfrastructure().values());
      list.add(definitions.getElementDefn(name));
      MappingsGenerator maps = new MappingsGenerator(definitions);
      maps.generate(list);
      return maps.getMappings();
    }
  }

  private String resItem(String name, boolean even) throws Exception {
    String color = even ? "#EFEFEF" : "#FFFFFF";
    if (definitions.hasResource(name)) {
      ResourceDefn r = definitions.getResourceByName(name);
      return
          "<tr bgcolor=\""+color+"\"><td><a href=\""+name.toLowerCase()+".html\">"+name+"</a></td><td>"+aliases(r.getRoot().getAliases())+"</td><td>"+Utilities.escapeXml(r.getDefinition())+"</td></tr>\r\n";

    } else 
      return 
          "<tr bgcolor=\""+color+"\"><td>"+name+"</td><td>(Not defined yet)</td><td></td><td></td></tr>\r\n";

  }

  private String resDesc(String name) throws Exception {
    if (definitions.hasResource(name)) {
      ResourceDefn r = definitions.getResourceByName(name);
      return Utilities.escapeXml(r.getDefinition());
    } else 
      return " ";

  }

  private String aliases(List<String> aliases) {
    if (aliases == null || aliases.size() == 0)
      return "";
    StringBuilder b = new StringBuilder();
    b.append(aliases.get(0));
    for (int i = 1; i < aliases.size() - 1; i++) {
      b.append(", ").append(aliases.get(i));
    }
    return b.toString();
  }

  private String resCategory(String string) {
    String[] parts = string.split("\\|");
    return
        "<tr><td colspan=\"3\"><hr/></td></tr>\r\n"+
        "<tr><th colspan=\"3\">"+parts[0]+"<a name=\""+parts[0].toLowerCase().replace(" ", "")+"\"> </a></th></tr>\r\n"+
        "<tr><td colspan=\"3\">"+Utilities.escapeXml(parts[1])+"</td></tr>\r\n";
  }

  private String onThisPage(String tail) {
    String[] entries = tail.split("\\|");
    StringBuilder b = new StringBuilder();
    b.append("<div class=\"col-3\"><div class=\"itoc\">\r\n<p>On This Page:</p>\r\n");
    for (String e : entries) {
      String[] p = e.split("#");
      if (p.length == 2)
        b.append("<p class=\"link\"><a href=\"#"+p[1]+"\">"+Utilities.escapeXml(p[0])+"</a></p>");
      if (p.length == 1)
        b.append("<p class=\"link\"><a href=\"#\">"+Utilities.escapeXml(p[0])+"</a></p>");
    }
    b.append("\r\n</div></div>\r\n");
    return b.toString();
  }

  private String mapOnThisPage(String mappings) {
    if (mappings == null) {
      List<ElementDefn> list = new ArrayList<ElementDefn>();
      list.addAll(definitions.getStructures().values());
      list.addAll(definitions.getTypes().values());
      list.addAll(definitions.getInfrastructure().values());
      MappingsGenerator maps = new MappingsGenerator(definitions);
      maps.generate(list);
      mappings = maps.getMappingsList();
    }
    if (Utilities.noString(mappings))
      return "";
    
    String[] entries = mappings.split("\\|");
    StringBuilder b = new StringBuilder();
    b.append("<div class=\"itoc\">\r\n<p>Mappings:</p>\r\n");
    for (String e : entries) {
      String[] p = e.split("#");
      if (p.length == 2)
        b.append("<p class=\"link\"><a href=\"#"+p[1]+"\">"+Utilities.escapeXml(p[0])+"</a></p>");
      if (p.length == 1)
        b.append("<p class=\"link\"><a href=\"#\">"+Utilities.escapeXml(p[0])+"</a></p>");
    }
    b.append("</div>\r\n");
    return b.toString();
  }

  private static class TocSort implements Comparator<String> {

    @Override
	public int compare(String arg0, String arg1) {
      String[] a0 = arg0.split("\\.");
      String[] a1 = arg1.split("\\.");
      for (int i = 0; i < Math.min(a0.length, a1.length); i++) {
        int i0 = Integer.parseInt(a0[i]);
        int i1 = Integer.parseInt(a1[i]);
        if (i0 != i1)
          return i0-i1;
      }
      return (a0.length - a1.length);
    }
  }
  
  public class TocItem {
    TocEntry entry;
    Row row;
    int depth;
    public TocItem(TocEntry entry, Row row, int depth) {
      super();
      this.entry = entry;
      this.row = row;
      this.depth = depth;
    }
  }


  private String generateToc(String title, int part) throws Exception {
    // return breadCrumbManager.makeToc();
    StringBuilder b = new StringBuilder();
    b.append("<ul>\r\n");
    List<String> entries = new ArrayList<String>();
    entries.addAll(toc.keySet());
    Collections.sort(entries, new SectionSorter());
    Set<String> pages = new HashSet<String>();
    HeirarchicalTableGenerator gen = new HeirarchicalTableGenerator(folders.dstDir, false);
    TableModel model = gen.new TableModel();
    model.getTitles().add(gen.new Title(null, model.getDocoRef(), "ToC Part "+Integer.toString(part)+" : "+title, "Table of Contents", null, 0));
    Deque<TocItem> stack = new ArrayDeque<TocItem>();
    
    for (String s : entries) {
      TocEntry t = toc.get(s);
      if (!s.startsWith("?") && ((part == 1 && rootInd(s) < 4) || (part == 2 && rootInd(s) >= 4))) {
        String nd = s;
        while (nd.endsWith(".0"))
          nd = nd.substring(0, nd.length()-2);
        int d = Utilities.charCount(nd, '.');
        if (d < 4 && !pages.contains(t.getLink())) {
          b.append(" <li>");
          for (int i = 0; i < d; i++)
            b.append("&nbsp;");
          b.append(" <a href=\"");
          b.append(t.getLink());
          b.append("\">");
          b.append(nd);
          b.append(" ");
          b.append(Utilities.escapeXml(t.getText()));
          b.append("</a></li>\r\n");
          pages.add(t.getLink());    
          while (!stack.isEmpty() && stack.getFirst().depth >= d)
            stack.pop();
          Row row = gen.new Row();
          row.setIcon("icon_page.gif", null);
          String td = t.getText();
          if (!stack.isEmpty()) {
            if (td.startsWith(stack.getFirst().entry.getText()+" - "))
              td = td.substring(stack.getFirst().entry.getText().length()+3);
            else if (td.startsWith(stack.getFirst().entry.getText()))
              td = td.substring(stack.getFirst().entry.getText().length());
          }
          row.getCells().add(gen.new Cell(null, t.getLink(), nd+" "+td, t.getText(), null));
          if (stack.isEmpty())
            model.getRows().add(row);
          else
            stack.getFirst().row.getSubRows().add(row);
          stack.push(new TocItem(t,  row, d));
        }
      }
    }
    b.append("</ul>\r\n");
   
    return /*b.toString()+*/new XhtmlComposer().compose(gen.generate(model, ""));
  }

  private int rootInd(String s) {
    if (s.contains("."))
      s = s.substring(0, s.indexOf("."));
    return s.contains("?") ? 100 : Integer.parseInt(s);
  }

  private String generateBSUsage(ValueSet vs) throws Exception {        
    StringBuilder b = new StringBuilder();
    for (ResourceDefn r : definitions.getBaseResources().values()) {
      scanForUsage(b, vs, r.getRoot(), r.getName().toLowerCase()+".html#def");
      scanForOperationUsage(b, vs, r, r.getName().toLowerCase()+"-operations.html#");
      scanForProfileUsage(b, vs, r);
    }
    for (ResourceDefn r : definitions.getResources().values()) {
      scanForUsage(b, vs, r.getRoot(), r.getName().toLowerCase()+".html#def");
      scanForOperationUsage(b, vs, r, r.getName().toLowerCase()+"-operations.html#");
      scanForProfileUsage(b, vs, r);
    }
    for (ElementDefn e : definitions.getInfrastructure().values()) {
      if (e.getName().equals("Reference")) {
        scanForUsage(b, vs, e, "references.html#"+e.getName());
      } else if (e.getName().equals("Extension")) {
        scanForUsage(b, vs, e, "extensibility.html#"+e.getName());
      } else if (e.getName().equals("Narrative")) {
        scanForUsage(b, vs, e, "narrative.html#"+e.getName());
      } else {
        scanForUsage(b, vs, e, "formats.html#"+e.getName());
      }
    }
    for (ElementDefn e : definitions.getTypes().values())
      if (!definitions.dataTypeIsSharedInfo(e.getName())) {
        if (e.getName().equals("Reference")) 
          scanForUsage(b, vs, e, "references.html#"+e.getName());
        else
          scanForUsage(b, vs, e, "datatypes.html#"+e.getName());
      }
    for (ElementDefn e : definitions.getStructures().values())
      if (!definitions.dataTypeIsSharedInfo(e.getName()))
        scanForUsage(b, vs, e, "datatypes.html#"+e.getName());

    for (ValueSet vsi : valueSets.values()) {
      String path = (String) vsi.getUserData("path");
      if (vs.hasCompose()) {
        for (UriType t : vs.getCompose().getImport()) {
          if (t.getValue().equals(vs.getUrl())) 
            b.append(" <li>Imported into Valueset <a href=\"").append(path).append("\">").append(Utilities.escapeXml(vs.getName())).append("</a></li>");
        }
        for (ConceptSetComponent t : vsi.getCompose().getInclude()) {
          if (vs.hasCodeSystem() && t.getSystem().equals(vs.getCodeSystem().getSystem())) 
            b.append(" <li>Included in Valueset <a href=\"").append(path.startsWith("valueset-") ? path : /* "valueset-" +*/ path).append("\">").append(Utilities.escapeXml(vs.getName())).append("</a></li>");
        }
        for (ConceptSetComponent t : vsi.getCompose().getExclude()) {
          if (vs.hasCodeSystem() && t.getSystem().equals(vs.getCodeSystem().getSystem())) 
            b.append(" <li>Excluded in Valueset <a href=\"").append(path.startsWith("valueset-") ? path :/* "valueset-" + */path).append("\">").append(Utilities.escapeXml(vs.getName())).append("</a></li>");
        }
      }
    }
    if (b.length() == 0)
      return "<p>\r\nThis value set is not currently used\r\n</p>\r\n";
    else
      return "<p>\r\nThis value set is used in the following places:\r\n</p>\r\n<ul>\r\n"+b.toString()+"</ul>\r\n";
  }

  private void scanForOperationUsage(StringBuilder b, ValueSet vs, ResourceDefn r, String page) {
    for (Operation op : r.getOperations()) {
      for (OperationParameter p : op.getParameters()) {
        if (p.getBs() != null && p.getBs().getValueSet() == vs) {
          b.append(" <li><a href=\"").append(page).append(op.getName()).append("\">Operation Parameter $")          
          .append(op.getName()).append(".").append(p.getName()).append("</a> ").append("</li>\r\n");
        }
      }
    }    
  }

  private void scanForProfileUsage(StringBuilder b, ValueSet vs, ResourceDefn r) {
    for (Profile ap : r.getConformancePackages()) {
      for (ConstraintStructure p : ap.getProfiles()) {
        for (ElementDefinition ed : p.getResource().getSnapshot().getElement()) {
          if (ed.hasBinding()) {
            if (isValueSetMatch(ed.getBinding().getValueSet(), vs))
              b.append(" <li><a href=\"").append(p.getId()).append(".html\">StructureDefinition ")
              .append(p.getTitle()).append(": ").append(ed.getPath()).append("</a> ").append(getBindingTypeDesc(ed.getBinding())).append("</li>\r\n");
          }
        }
      }
    }
  }

  private boolean isValueSetMatch(Type ref, ValueSet vs) {
    if (ref == null)
      return false;
    if (ref instanceof UriType)
      return ((UriType) ref).getValue().equals(vs.getUrl());
    return ((Reference) ref).hasReference() && ((Reference) ref).getReference().endsWith("/"+vs.getId());
  }

  private String getBindingTypeDesc(ElementDefinitionBindingComponent binding) {
    if (binding.getStrength() == null)
      return "";
    else
      return binding.getStrength().getDisplay();
  }

  private void scanForUsage(StringBuilder b, ValueSet vs, ElementDefn e, String ref) {
    scanForUsage(b, vs, e, "", ref);
    
  }

  private void scanForUsage(StringBuilder b, ValueSet vs, ElementDefn e, String path, String ref) {
    path = path.equals("") ? e.getName() : path+"."+e.getName();
    if (e.hasBinding() && e.getBinding().getValueSet() == vs) {
      b.append(" <li><a href=\"").append(ref).append("\">").append(path).append("</a> ").append(getBSTypeDesc(e.getBinding())).append("</li>\r\n");
    }
    for (ElementDefn c : e.getElements()) {
      scanForUsage(b, vs, c, path, ref);
    }
  }

  private String getBSTypeDesc(BindingSpecification cd) {
    if (cd == null || cd.getStrength() == null) // partial build
      return "Unknown";
    return "(<a href=\"terminologies.html#"+cd.getStrength().toCode()+"\">"+cd.getStrength().getDisplay()+"</a>)";
  }

  private String generateCodeDefinition(String name) {
    throw new Error("fix this");
//    BindingSpecification cd = definitions.getBindingByURL("#"+name);
//    return Utilities.escapeXml(cd.getDefinition());
  }

  private String generateValueSetDefinition(String name) {
    throw new Error("fix this");
//    BindingSpecification cd = definitions.getBindingByURL(name);
//    if (cd == null)
//      return definitions.getExtraValuesets().get(name).getDescription();
//    else
//      return Utilities.escapeXml(cd.getDefinition());
  }

  private void generateCode(BindingSpecification cd, StringBuilder s, boolean hasSource, boolean hasId, boolean hasComment, boolean hasDefinition, boolean hasParent, int level, DefinedCode c) {
    String id = hasId ? "<td>"+fixNull(c.getId())+"</td>" : "";
    String src = "";
    if (hasSource) {
      if (Utilities.noString(c.getSystem())) {
        src = "<td></td>";
      } else {
        String url = c.getSystem();
        url = fixUrlReference(url);
        src = "<td><a href=\""+url+"\">"+codeSystemDescription(c.getSystem())+"</a></td>";
      }
    }
    String lvl = hasParent ? "<td>"+Integer.toString(level)+"</td>" : "";
    String indent = "";
    for (int i = 1; i < level; i++)
      indent = indent + "&nbsp;&nbsp;";
    if (hasComment)
      s.append("    <tr>"+id+src+lvl+"<td>"+indent+Utilities.escapeXml(c.getCode())+"</td><td>"+Utilities.escapeXml(c.getDefinition())+"</td><td>"+Utilities.escapeXml(c.getComment())+"</td></tr>\r\n");
    else if (hasDefinition)
      s.append("    <tr>"+id+src+lvl+"<td>"+indent+Utilities.escapeXml(c.getCode())+"</td><td colspan=\"2\">"+Utilities.escapeXml(c.getDefinition())+"</td></tr>\r\n");
    else
      s.append("    <tr>"+id+src+lvl+"<td colspan=\"3\">"+indent+Utilities.escapeXml(c.getCode())+"</td></tr>\r\n");
    
    for (DefinedCode ch : c.getChildCodes()) {
      generateCode(cd, s, hasSource, hasId, hasComment, hasDefinition, hasParent, level+1, ch);
    }
  }

  private String fixNull(String id) {
    return id == null ? "" : id;
  }

  private String codeSystemDescription(String system) {
    return system.substring(system.lastIndexOf("/")+1);
  }

  private String genProfileConstraints(StructureDefinition res) throws Exception {
    StringBuilder b = new StringBuilder();
    for (ElementDefinition e : res.getSnapshot().getElement()) {      
      for (ElementDefinitionConstraintComponent inv : e.getConstraint()) {
        if (!e.getPath().contains("."))
          b.append("<li><b title=\"Formal Invariant Identifier\">"+inv.getKey()+"</b>: "+Utilities.escapeXml(inv.getHuman())+" (xpath: <span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getXpath())+"</span>)</li>");
        else
          b.append("<li><b title=\"Formal Invariant Identifier\">"+inv.getKey()+"</b>: On "+e.getPath()+": "+Utilities.escapeXml(inv.getHuman())+" (xpath on "+presentPath(e.getPath())+": <span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getXpath())+"</span>)</li>");
      }
    }    
    if (b.length() > 0)
      return "<p>Constraints</p><ul>"+b+"</ul>";
    else
      return "";
  }
  
  private String genExtensionConstraints(StructureDefinition ed) throws Exception {
    StringBuilder b = new StringBuilder();
    for (ElementDefinition e : ed.getSnapshot().getElement()) {      
      for (ElementDefinitionConstraintComponent inv : e.getConstraint()) {
        if (!e.getPath().contains("."))
          b.append("<li><b title=\"Formal Invariant Identifier\">"+inv.getKey()+"</b>: "+Utilities.escapeXml(inv.getHuman())+" (xpath: <span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getXpath())+"</span>)</li>");
        else
          b.append("<li><b title=\"Formal Invariant Identifier\">"+inv.getKey()+"</b>: On "+e.getPath()+": "+Utilities.escapeXml(inv.getHuman())+" (xpath on "+presentPath(e.getPath())+": <span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getXpath())+"</span>)</li>");
      }
    }    
    if (b.length() > 0)
      return "<p>Constraints</p><ul>"+b+"</ul>";
    else
      return "";
  }
  
  private String genResourceTable(ResourceDefn res, String prefix) throws Exception {
    ElementDefn e = res.getRoot();
    ResourceTableGenerator gen = new ResourceTableGenerator(folders.dstDir, this, res.getName()+"-definitions.html", false);
    return new XhtmlComposer().compose(gen.generate(e, prefix));
  }
  
  private String genResourceConstraints(ResourceDefn res) throws Exception {
    ElementDefn e = res.getRoot();
    Map<String, String> invs = new HashMap<String, String>();
    generateConstraints(res.getName(), e, invs, true);
    List<String> ids = new ArrayList<String>();
    for (String n : invs.keySet()) {
      ids.add(n);
    }
    Collections.sort(ids);
    StringBuilder b = new StringBuilder();
    for (String n : ids) {
      b.append(invs.get(n));
    }
    if (b.length() > 0)
      return "<h3>Constraints</h3><ul>"+b+"</ul>";
    else
      return "";
  }
  
  private String genRestrictions(String name) throws Exception {
    StringBuilder b = new StringBuilder();
    StringBuilder b2 = new StringBuilder();
    for (ProfiledType c : definitions.getConstraints().values()) {
      if (c.getBaseType().equals(name)) {
        b.append("<a name=\""+c.getName()+"\"> </a><a name=\""+c.getName().toLowerCase()+"\"> </a>\r\n");
        b2.append(" <tr><td>"+c.getName()+"</td><td>"+Utilities.escapeXml(c.getDefinition())+"</td><td>StructureDefinition (<a href=\""+c.getName().toLowerCase()+
            ".profile.xml.html\">XML</a>, <a href=\""+c.getName().toLowerCase()+".profile.json.html\">JSON</a>)</td>");
        b2.append("<td>"+genDataTypeUsage(c.getName())+"</td>");
        b2.append("</tr>\r\n");
      }
    }
    if (b.length() > 0) 
      return b.toString()+"<table class=\"list\">\r\n"+b2.toString()+"</table>\r\n";
    else
      return "";
  }

  private String genConstraints(String name) throws Exception {
    ElementDefn e = definitions.getElementDefn(name);
    Map<String, String> invs = new HashMap<String, String>();
    generateConstraints(name, e, invs, true);
    List<String> ids = new ArrayList<String>();
    for (String n : invs.keySet()) {
      ids.add(n);
    }
    Collections.sort(ids);
    StringBuilder b = new StringBuilder();
    for (String n : ids) {
      b.append(invs.get(n));
    }
    if (b.length() > 0)
      return "<ul>"+b+"</ul>";
    else
      return "";
  }

  private void generateConstraints(String path, ElementDefn e, Map<String, String> invs, boolean base) {  
    for (Invariant inv : e.getInvariants().values()) {
      if (base)
        invs.put(inv.getId(), "<li><b title=\"Formal Invariant Identifier\">"+inv.getId()+"</b>: "+Utilities.escapeXml(inv.getEnglish())+" (xpath: <span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getXpath())+"</span>)</li>");
      else
        invs.put(inv.getId(), "<li><b title=\"Formal Invariant Identifier\">"+inv.getId()+"</b>: On "+path+": "+Utilities.escapeXml(inv.getEnglish())+" (xpath on "+presentPath(path)+": <span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getXpath())+"</span>)</li>");
    }
    for (ElementDefn c : e.getElements()) {
      generateConstraints(path+"."+c.getName(), c, invs, false);
    }    
  }

  private String presentPath(String path) {
    String[] parts = path.split("\\.");
    StringBuilder s = new StringBuilder();
    for (String p : parts) {
      if (s.length() > 0)
        s.append("/");
      s.append("f:"+p);
    }
    return s.toString();
      
  }

  private String pageHeader(String n) {
    return "<div class=\"navtop\"><ul class=\"navtop\"><li class=\"spacerright\" style=\"width: 500px\"><span>&nbsp;</span></li></ul></div>\r\n";
  }
  
  private String dtHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Data Types", "datatypes.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "datatypes-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "datatypes-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", "datatypes-mappings.html", mode==null || "mappings".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String edHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Element Definition", "elementdefinition.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "elementdefinition-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "elementdefinition-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", "elementdefinition-mappings.html", mode==null || "mappings".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String elHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Element", "element.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "element-definitions.html", mode==null || "definitions".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String extHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Extensiblity", "extensibility.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Defining Extensions", "defining-extensions.html", mode==null || "defining".equals(mode)));
    b.append(makeHeaderTab("Examples", "extensibility-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "extensibility-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Registry", "extensibility-registry.html", mode==null || "registry".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String narrHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Narrative", "narrative.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "narrative-example.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "narrative-definitions.html", mode==null || "definitions".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String profilesHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Profiling FHIR", "profiling.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "profiling-examples.html", mode==null || "examples".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String resourcesHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Resource Definitions", "resource.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "resources-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "resources-definitions.html", mode==null || "definitions".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String refHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("References", "references.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "references-definitions.html", mode==null || "definitions".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

 

//  private String resourcesHeader(String n, String mode) {
//      if (n.contains("-"))
//      n = n.substring(0, n.indexOf('-'));
//    StringBuilder b = new StringBuilder();
//    b.append("<div class=\"navtop\">");
//    b.append("<ul class=\"navtop\"><li class=\"spacerleft\"><span>&nbsp;</span></li>");
//    if (mode == null || mode.equals("content"))
//      b.append("<li class=\"selected\"><span>Content</span></li>");
//    else
//      b.append("<li class=\"nselected\"><span><a href=\""+n+".html\">Content</a></span></li>");
//    if ("definitions".equals(mode))
//      b.append("<li class=\"selected\"><span>Detailed Descriptions</span></li>");
//    else
//      b.append("<li class=\"nselected\"><span><a href=\""+n+"-definitions.html\">Detailed Descriptions</a></span></li>");
//    b.append("<li class=\"spacerright\" style=\"width: 270px\"><span>&nbsp;</span></li>");
//    b.append("</ul></div>\r\n");
//    return b.toString();
//  }

//  private String formatsHeader(String n, String mode) {
//    if (n.contains("-"))
//      n = n.substring(0, n.indexOf('-'));
//    StringBuilder b = new StringBuilder();
//    b.append("<div class=\"navtop\">");
//    b.append("<ul class=\"navtop\"><li class=\"spacerleft\"><span>&nbsp;</span></li>");
//    if (mode == null || mode.equals("content"))
//      b.append("<li class=\"selected\"><span>Content</span></li>");
//    else
//      b.append("<li class=\"nselected\"><span><a href=\""+n+".html\">Content</a></span></li>");
//    if ("examples".equals(mode))
//      b.append("<li class=\"selected\"><span>Examples</span></li>");
//    else
//      b.append("<li class=\"nselected\"><span><a href=\""+n+"-examples.html\">Examples</a></span></li>");
//    if ("definitions".equals(mode))
//      b.append("<li class=\"selected\"><span>Detailed Descriptions</span></li>");
//    else
//      b.append("<li class=\"nselected\"><span><a href=\""+n+"-definitions.html\">Detailed Descriptions</a></span></li>");
//    b.append("<li class=\"spacerright\" style=\"width: 270px\"><span>&nbsp;</span></li>");
//    b.append("</ul></div>\r\n");
//    return b.toString();
//  }

  private String profileHeader(String n, String mode) {
    StringBuilder b = new StringBuilder();

    if (n.endsWith(".xml"))
      n = n.substring(0, n.length()-4);
    
    b.append("<ul class=\"nav nav-tabs\">");
    
    b.append(makeHeaderTab("Content", n+".html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", n+"-definitions.html", "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", n+"-mappings.html", "mappings".equals(mode)));
    b.append(makeHeaderTab("HTML Form", n+"-questionnaire.html", "questionnaire".equals(mode)));
    b.append(makeHeaderTab("XML", n+".profile.xml.html", "xml".equals(mode)));
    b.append(makeHeaderTab("JSON", n+".profile.json.html", "json".equals(mode)));

    b.append("</ul>\r\n");

    return b.toString();   
  }

  private String dictHeader(String n, String mode) {
    StringBuilder b = new StringBuilder();

    if (n.endsWith(".xml"))
      n = n.substring(0, n.length()-4);
    
    b.append("<ul class=\"nav nav-tabs\">");
    
    b.append(makeHeaderTab("Content", n+".html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("XML", n+".xml.html", "xml".equals(mode)));
    b.append(makeHeaderTab("JSON", n+".json.html", "json".equals(mode)));

    b.append("</ul>\r\n");

    return b.toString();   
  }

  private String extDefnHeader(String n, String mode) {
    StringBuilder b = new StringBuilder();

    if (n.endsWith(".xml"))
      n = n.substring(0, n.length()-4);
    
    b.append("<ul class=\"nav nav-tabs\">");
    
    b.append(makeHeaderTab("Content", n+".html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", n+"-definitions.html", "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", n+"-mappings.html", "mappings".equals(mode)));
    b.append(makeHeaderTab("XML", n+".xml.html", "xml".equals(mode)));
    b.append(makeHeaderTab("JSON", n+".json.html", "json".equals(mode)));

    b.append("</ul>\r\n");

    return b.toString();   
  }

  private String txHeader(String n, String mode) {
    if (n.contains("-"))
      n = n.substring(0, n.indexOf('-'));
    StringBuilder b = new StringBuilder();
    String pfx = "";
    if ("l1".equals(mode))
      pfx = "../";
    if ("l2".equals(mode))
      pfx = "../../";
    if ("l3".equals(mode))
      pfx = "../../../";

    b.append("<ul class=\"nav nav-tabs\">");    
    b.append(makeHeaderTab("Using Codes", pfx + "terminologies.html", mode==null || "content".equals(mode)));
    b.append(makeHeaderTab("Code Systems", pfx + "terminologies-systems.html", "systems".equals(mode)));
    b.append(makeHeaderTab("Value Sets", pfx + "terminologies-valuesets.html", "valuesets".equals(mode)));
    b.append(makeHeaderTab("v2 Tables", pfx + "terminologies-v2.html", "v2".equals(mode)));
    b.append(makeHeaderTab("v3 Namespaces", pfx + "terminologies-v3.html", "v3".equals(mode)));
    b.append(makeHeaderTab("Concept Maps", pfx + "terminologies-conceptmaps.html", "conceptmaps".equals(mode)));
    b.append(makeHeaderTab("Identifier Systems", pfx + "identifier-registry.html", "idsystems".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String fmtHeader(String n, String mode) {
    if (n.contains("-"))
      n = n.substring(0, n.indexOf('-'));
    StringBuilder b = new StringBuilder();
    String pfx = "";
    if ("l1".equals(mode))
      pfx = "../";
    if ("l2".equals(mode))
      pfx = "../../";
    if ("l3".equals(mode))
      pfx = "../../../";

    b.append("<ul class=\"nav nav-tabs\">");    
    b.append(makeHeaderTab("Formats", pfx + "formats.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("XML", pfx + "xml.html", "xml".equals(mode)));
    b.append(makeHeaderTab("JSON", pfx + "json.html", "json".equals(mode)));
    b.append(makeHeaderTab("RDF", pfx + "rdf.html", "rdf".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String cmpHeader(String n, String mode) {
    if (n.contains("-"))
      n = n.substring(0, n.indexOf('-'));
    StringBuilder b = new StringBuilder();
    String pfx = "";
    if ("l1".equals(mode))
      pfx = "../";
    if ("l2".equals(mode))
      pfx = "../../";
    if ("l3".equals(mode))
      pfx = "../../../";

    b.append("<ul class=\"nav nav-tabs\">");    
    b.append(makeHeaderTab("Comparison Appendix", pfx + "comparison.html", mode==null || "content".equals(mode)));
    b.append(makeHeaderTab("V2 Messaging", pfx + "comparison-v2.html", "v2".equals(mode)));
    b.append(makeHeaderTab("V3 (Messaging)", pfx + "comparison-v3.html", "v3".equals(mode)));
    b.append(makeHeaderTab("CDA", pfx + "comparison-cda.html", "cda".equals(mode)));
    b.append(makeHeaderTab("Other", pfx + "comparison-other.html", "misc".equals(mode)));
    
    b.append("</ul>\r\n");
    return b.toString();
  }

//  private String atomHeader(String n, String mode) {
//    if (n.contains("-"))
//      n = n.substring(0, n.indexOf('-'));
//    StringBuilder b = new StringBuilder();
//    b.append("<div class=\"navtop\">");
//    b.append("<ul class=\"navtop\"><li class=\"spacerleft\"><span>&nbsp;</span></li>");
//    if (mode == null || mode.equals("content"))
//      b.append("<li class=\"selected\"><span>Content</span></li>");
//    else
//      b.append("<li class=\"nselected\"><span><a href=\""+n+".html\">Content</a></span></li>");
//    if ("examples".equals(mode))
//      b.append("<li class=\"selected\"><span>Examples</span></li>");
//    else
//      b.append("<li class=\"nselected\"><span><a href=\""+n+"-examples.html\">Examples</a></span></li>");
//    b.append("<li class=\"spacerright\" style=\"width: 370px\"><span>&nbsp;</span></li>");
//    b.append("</ul></div>\r\n");
//    return b.toString();
//  }

  private String codelist(ValueSet vs, String mode, boolean links, boolean heading) throws Exception {
    if (vs == null)
      vs = definitions.getValuesets().get(mode);
    if (vs == null)
      throw new Exception("No ValueSet for "+mode);
    if (!vs.hasCodeSystem())
      throw new Exception("Code list '"+mode+"' is empty/not defined");
    boolean hasComments = false;
    for (ConceptDefinitionComponent c : vs.getCodeSystem().getConcept())
      hasComments = hasComments || checkHasComment(c);
    
    StringBuilder b = new StringBuilder();
    if (heading && !Utilities.noString(vs.getDescription()))
      b.append("<h3>"+vs.getDescription()+"</h3>\r\n");
    b.append("<table class=\"codes\">\r\n");
    for (ConceptDefinitionComponent c : vs.getCodeSystem().getConcept()) {
      genCodeItem(links, hasComments, b, c);
    }
    b.append("</table>\r\n");
    return b.toString();
  }

  private void genCodeItem(boolean links, boolean hasComments, StringBuilder b, ConceptDefinitionComponent c) {
    if (hasComments)
      b.append(" <tr><td>"+(links ? "<a href=\"#"+c.getCode()+"\">"+c.getCode()+"</a>" : c.getCode())+"</td><td>"+Utilities.escapeXml(c.getDefinition())+"</td><td>"+Utilities.escapeXml(ToolingExtensions.getComment(c))+"</td></tr>\r\n");
    else
      b.append(" <tr><td>"+(links ? "<a href=\"#"+c.getCode()+"\">"+c.getCode()+"</a>" : c.getCode())+"</td><td>"+Utilities.escapeXml(c.getDefinition())+"</td></tr>\r\n");
    for (ConceptDefinitionComponent cc : c.getConcept()) {
      genCodeItem(links, hasComments, b, cc);
    }
  }

  private boolean checkHasComment(ConceptDefinitionComponent c) {
    if (ToolingExtensions.getComment(c) != null)
      return true;
    for (ConceptDefinitionComponent cc : c.getConcept())
      if (checkHasComment(cc))
        return true;
    return false;
  }

  private String codetoc(String n) throws Exception {
    ValueSet vs = definitions.getBoundValueSets().get(n);
    if (vs == null)
      throw new Exception("Unable to find value set '"+n+"'");
    if (!vs.hasCodeSystem() || !vs.getCodeSystem().hasConcept())
      throw new Exception("ValueSet '"+n+"' is empty/not defined");
    
    StringBuilder b = new StringBuilder();
    for (ConceptDefinitionComponent c : vs.getCodeSystem().getConcept())
      b.append("<a href=\"#"+c.getCode()+"\">"+c.getDisplay()+"</a><br/>\r\n");
    return b.toString();
  }

  private String makeHeaderTab(String tabName, String path, Boolean selected)
  {
    StringBuilder b = new StringBuilder();
    
    if(!selected)
    {
      b.append("<li>");
      b.append(String.format("<a href=\"%s\">%s</a>", path, tabName));
    }
    else
    {
      b.append("<li class=\"active\">");
      b.append(String.format("<a href=\"#\">%s</a>", tabName));
    }

    b.append("</li>");
    
    return b.toString();    
  }
  
  private String resHeader(String n, String title, String mode) throws Exception {
    StringBuilder b = new StringBuilder();
    if (n.contains("-"))
      n = n.substring(0, n.indexOf('-'));

    boolean hasOps = !definitions.getResourceByName(title).getOperations().isEmpty();
    boolean isAbstract = definitions.getResourceByName(title).isAbstract();
    b.append("<ul class=\"nav nav-tabs\">");
    
    b.append(makeHeaderTab("Content", n+".html", mode==null || "content".equals(mode)));
    if (!isAbstract)
      b.append(makeHeaderTab("Examples", n+"-examples.html", "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", n+"-definitions.html", "definitions".equals(mode)));
    if (!isAbstract)
      b.append(makeHeaderTab("Mappings", n+"-mappings.html", "mappings".equals(mode)));
    if (!isAbstract)
      b.append(makeHeaderTab("Profiles", n+"-profiles.html", "profiles".equals(mode)));
    if (!isAbstract)
      b.append(makeHeaderTab("HTML Form", n+"-questionnaire.html", "questionnaire".equals(mode)));
    if (hasOps)
      b.append(makeHeaderTab("Operations", n+"-operations.html", "operations".equals(mode)));

    b.append("</ul>\r\n");

    return b.toString();   
  }

  private String lmHeader(String n, String title, String mode) throws Exception {
    StringBuilder b = new StringBuilder();

    b.append("<ul class=\"nav nav-tabs\">");
    
    b.append(makeHeaderTab("Content", n+".html", mode==null || "content".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", n+"-definitions.html", "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", n+"-mappings.html", "mappings".equals(mode)));
    b.append("</ul>\r\n");

    return b.toString();   
  }

  private String abstractResHeader(String n, String title, String mode) throws Exception {
    StringBuilder b = new StringBuilder();
    if (n.contains("-"))
      n = n.substring(0, n.indexOf('-'));

    boolean hasOps = !definitions.getResourceByName(title).getOperations().isEmpty();
    boolean hasExamples = !definitions.getResourceByName(title).getExamples().isEmpty();
    b.append("<ul class=\"nav nav-tabs\">");
    
    b.append(makeHeaderTab("Content", n+".html", mode==null || "content".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", n+"-definitions.html", "definitions".equals(mode)));
    if (hasExamples)
      b.append(makeHeaderTab("Examples", n+"-examples.html", "operations".equals(mode)));
    if (hasOps)
      b.append(makeHeaderTab("Operations", n+"-operations.html", "operations".equals(mode)));

    b.append("</ul>\r\n");

    return b.toString();   
  }

  private String genCodeSystemsTable() throws Exception {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"codes\">\r\n");
    List<String> names = new ArrayList<String>();
    names.addAll(codeSystems.keySet());
    

//    for (String n : definitions.getBindings().keySet()) {
//      if ((definitions.getBindingByName(n).getBinding() == Binding.CodeList && !definitions.getBindingByName(n).getVSSources().contains("")) || 
//          (definitions.getBindingByName(n).getBinding() == Binding.Special))
//        names.add(definitions.getBindingByName(n).getReference().substring(1));
//    }
//
////  not this one      Logical Interactions (RESTful framework)  http://hl7.org/fhir/rest-operations 2.16.840.1.113883.6.308
//    s.append(" <tr><td><a href=\""+cd.getReference().substring(1)+".html\">http://hl7.org/fhir/"+cd.getReference().substring(1)+"</a></td><td>"+Utilities.escapeXml(cd.getDefinition())+"</td></tr>\r\n");
//      
    Collections.sort(names);
    for (String n : names) {
      if (n.startsWith("http://hl7.org") && !n.startsWith("http://hl7.org/fhir/v2") && !n.startsWith("http://hl7.org/fhir/v3")) {
        // BindingSpecification cd = definitions.getBindingByReference("#"+n);
        ValueSet ae = codeSystems.get(n);
        if (ae == null)
          s.append(" <tr><td><a href=\"" + "??" + ".html\">").append(n).append("</a></td><td>").append("??").append("</td></tr>\r\n");
        else {
          s.append(" <tr><td><a href=\"").append(ae.getUserData("path")).append("\">").append(n).append("</a></td><td>").append(ae.getDescription()).append("</td></tr>\r\n");
        }
      }
    }
    s.append("</table>\r\n");
    return s.toString();
  }

  private String genConceptMapsTable() throws Exception {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"codes\">\r\n");
    s.append(" <tr><td><b>Name</b></td><td><b>Source</b></td><td><b>Target</b></td></tr>\r\n");
    List<String> sorts = new ArrayList<String>();
    sorts.addAll(conceptMaps.keySet());
    Collections.sort(sorts);
    
    for (String sn : sorts) {
      ConceptMap ae = conceptMaps.get(sn);
      //String n = sn.substring(23);
      ConceptMap cm = ae;
      s.append(" <tr><td><a href=\"").append(ae.getUserData("path")).append("\">").append(cm.getName()).append("</a></td>")
              .append("<td><a href=\"").append(getValueSetRef("", ((Reference) cm.getSource()).getReference())).append("\">").append(describeValueSetByRef(((Reference) cm.getSource()).getReference())).append("</a></td>")
              .append("<td><a href=\"").append(getValueSetRef("", ((Reference) cm.getTarget()).getReference())).append("\">").append(describeValueSetByRef(((Reference) cm.getTarget()).getReference())).append("</a></td></tr>\r\n");
    }
    s.append("</table>\r\n");
    return s.toString();
  }


  @SuppressWarnings("unchecked")
  private String genIGValueSetsTable() throws Exception {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"codes\">\r\n");
    s.append(" <tr><td><b>Name</b></td><td><b>Definition</b></td><td><b>Source</b></td><td></td></tr>\r\n");
    List<String> namespaces = new ArrayList<String>();
    Map<String, ValueSet> vslist = new HashMap<String, ValueSet>();
    for (String sn : igResources.keySet()) {
      if (igResources.get(sn) instanceof ValueSet) {
        vslist.put(sn, (ValueSet) igResources.get(sn));
        String n = getNamespace(sn);
        if (!namespaces.contains(n))
          namespaces.add(n);
      }
    }
    Collections.sort(namespaces);
    for (String n : namespaces)  
      generateVSforNS(s, n, valueSets, false, null);
    s.append("</table>\r\n");
    return s.toString();
  }
  
  private String genValueSetsTable(ImplementationGuideDefn ig) throws Exception {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"codes\">\r\n");
    s.append(" <tr><td><b>Name</b></td><td><b>Definition</b></td><td><b>Source</b></td><td><b>Id</b></td><td><b>Usage</b></td></tr>\r\n");
    List<String> namespaces = new ArrayList<String>();
    for (String sn : valueSets.keySet()) {
      String n = getNamespace(sn);
      if (!n.equals("http://hl7.org/fhir/ValueSet") && !namespaces.contains(n) && !sn.startsWith("http://hl7.org/fhir/ValueSet/v2-") && !sn.startsWith("http://hl7.org/fhir/ValueSet/v3-"))
        namespaces.add(n);
    }
    Collections.sort(namespaces);
    generateVSforNS(s, "http://hl7.org/fhir/ValueSet", valueSets, true, ig);
    for (String n : namespaces)  
      generateVSforNS(s, n, valueSets, true, ig);
    s.append("</table>\r\n");
    return s.toString();
  }

  private void generateVSforNS(StringBuilder s, String ns, Map<String, ValueSet> vslist, boolean hasId, ImplementationGuideDefn ig) {
    List<String> sorts = new ArrayList<String>();
    for (String sn : vslist.keySet()) {
      ValueSet vs = vslist.get(sn);
      ImplementationGuideDefn vig = (ImplementationGuideDefn) vs.getUserData(ToolResourceUtilities.NAME_RES_IG);
      if (ig == vig) {
        String n = getNamespace(sn);
        if (ns.equals(n) && !sn.startsWith("http://hl7.org/fhir/ValueSet/v2-") && !sn.startsWith("http://hl7.org/fhir/ValueSet/v3-"))
          sorts.add(sn);
      }
    }
    if (!sorts.isEmpty()) {
      s.append(" <tr><td colspan=\"5\" style=\"background: #DFDFDF\"><b>Namespace: </b>"+ns+"</td></tr>\r\n");
      Collections.sort(sorts);
      for (String sn : sorts) {
        ValueSet ae = valueSets.get(sn);
        String n = getTail(sn);
        ValueSet vs = ae;
        if (wantPublish(vs)) {
          String path = (String) ae.getUserData("path");
          s.append(" <tr><td><a href=\""+pathTail(Utilities.changeFileExt(path, ".html"))+"\">"+n+"</a></td><td>"+Utilities.escapeXml(vs.getDescription())+"</td><td>"+sourceSummary(vs)+"</td>");
          if (hasId)
            s.append("<td>"+Utilities.oidTail(ToolingExtensions.getOID(ae))+"</td>");
          if (ig == null) { // just suppressing errors for now. bring them back later
            String usage = usageSummary(vs);
            s.append("<td>"+usage+"</td>");
          } else 
            s.append("<td>todo</td>");
          s.append("</tr>\r\n");
        }
      }
    }
  }

  private String pathTail(String path) {
    if (path.contains("/"))
      return path.substring(path.lastIndexOf("/")+1);
    else if (path.contains(File.separator))
      return path.substring(path.lastIndexOf(File.separator)+1);
    else
      return path;
  }

  private String usageSummary(ValueSet vs) {
    String s = (String) vs.getUserData(ToolResourceUtilities.NAME_SPEC_USAGE);
    if (Utilities.noString(s))
      return "??";
    else {
      String[] ps = s.split("\\,");
      CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
      for (String p : ps) {
        if (!definitions.getIgs().containsKey(p))
          b.append(p);
        else if (!Utilities.noString(definitions.getIgs().get(p).getPage()))
          b.append("<a href=\""+definitions.getIgs().get(p).getCode()+"/"+definitions.getIgs().get(p).getPage()+"\" title=\""+definitions.getIgs().get(p).getName()+"\">"+p+"</a>");
        else
          b.append("<span title=\""+definitions.getIgs().get(p).getCode()+"/"+definitions.getIgs().get(p).getName()+"\">"+p+"</span>");
      }
      return b.toString();
    }
  }

  private boolean wantPublish(ValueSet vs) {
    String s = (String) vs.getUserData(ToolResourceUtilities.NAME_SPEC_USAGE);
    if (Utilities.noString(s))
      return true;
    else {
      String[] ps = s.split("\\,");
      CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
      for (String p : ps) {
        if (!definitions.getIgs().containsKey(p))
          return true;
        else 
          return true;
      }
      return false;
    }
  }

  private String getTail(String sn) {
    return sn.substring(getNamespace(sn).length()+1);
  }

  private String getNamespace(String sn) {
    return sn.contains("/") ? sn.substring(0, sn.lastIndexOf("/")) : sn;
  }

  private String sourceSummary(ValueSet vs) {
    StringBuilder b = new StringBuilder();
    List<String> done = new ArrayList<String>();
    if (vs.hasCodeSystem()) {
      String n = "Internal";
      if (vs.getDescription().contains("Connectathon")) n = "IHE";
      if (vs.getDescription().contains("IHE")) n = "IHE";
      if (vs.getCodeSystem().getSystem().startsWith("http://") && !(vs.getCodeSystem().getSystem().startsWith("http://hl7.org"))) n = "External";
      if (vs.getCodeSystem().getSystem().equals("http://nema.org/dicom/dicm")) n = "DICOM";
      
      b.append(", "+n);
    }
    if (vs.hasCompose())
      for (ConceptSetComponent c : vs.getCompose().getInclude()) {
        String uri = c.getSystem();
        String n = "Other";
        if (uri != null) {
          if ("http://snomed.info/sct".equals(uri)) n = "SNOMED CT"; 
          if ("http://loinc.org".equals(uri)) n = "LOINC";
          if ("http://nema.org/dicom/dicm".equals(uri)) n = "DICOM";
          if ("http://hl7.org/fhir/resource-types".equals(uri)) n = "FHIR";
          if ("http://hl7.org/fhir/restful-interaction".equals(uri)) n = "FHIR";
          if ("http://unitsofmeasure.org".equals(uri)) n = "FHIR";
          if (uri.startsWith("http://hl7.org/fhir/v3/"))  n = "V3";
          if (uri.startsWith("http://hl7.org/fhir/v2/"))  n = "V2";
        }
        if (!done.contains(n))
          b.append(", ").append(n);
        done.add(n);
      }
    return b.length() == 0 ? "" : b.substring(2);
  }


//  private String genBindingsTable() {
//    assert(false);
//    StringBuilder s = new StringBuilder();
//    s.append("<table class=\"codes\">\r\n");
//    s.append(" <tr><td><b>Name</b></td><td><b>Definition</b></td><td><b>Type</b></td><td><b>Reference</b></td></tr>\r\n");
//    List<String> names = new ArrayList<String>();
//    for (String n : definitions.getBindings().keySet()) {
//      names.add(n);
//    }
//    Collections.sort(names);
//    for (String n : names) {
//      if (!n.startsWith("*")) {
//        BindingSpecification cd = definitions.getBindingByName(n);
//        if (cd.getElementType() != ElementType.Unknown) {
//          s.append(" <tr><td>"+Utilities.escapeXml(cd.getName())+"</td><td>"+Utilities.escapeXml(cd.getDefinition())+"</td><td>");
//          if (cd.getBinding() == Binding.Reference) 
//            s.append("Reference");
//          else if (cd.getElementType() == ElementType.Simple) 
//            s.append("Code List");
//          else if (cd.getBinding() == Binding.Unbound)
//            s.append("??");
//          else 
//            s.append("Value Set");
//
//          if (cd.getBinding() == Binding.Special) {
//
//            if (cd.getName().equals("MessageEvent"))
//              s.append("</td><td><a href=\"message-events.html\">http://hl7.org/fhir/valueset/message-events.html</a></td></tr>\r\n");
//            else if (cd.getName().equals("ResourceType"))
//              s.append("</td><td><a href=\"resource-types.html\">http://hl7.org/fhir/valueset/resource-types.html</a></td></tr>\r\n");
//            else if (cd.getName().equals("DataType"))
//              s.append("</td><td><a href=\"data-types.html\">http://hl7.org/fhir/valueset/data-types.html</a></td></tr>\r\n");
//            else if (cd.getName().equals("FHIRDefinedType"))
//              s.append("</td><td><a href=\"defined-types.html\">http://hl7.org/fhir/valueset/defined-types.html</a></td></tr>\r\n");
//            else 
//              s.append("</td><td>???</td></tr>\r\n");
//
//          } else if (cd.getBinding() == Binding.CodeList)
//            s.append("</td><td><a href=\""+cd.getReference().substring(1)+".html\">http://hl7.org/fhir/"+cd.getReference().substring(1)+"</a></td></tr>\r\n");          
//          else if (cd.getBinding() == Binding.ValueSet) { 
//            if (cd.getReferredValueSet() != null) {
//              if (cd.getReference().startsWith("http://hl7.org/fhir/v3/vs")) 
//                s.append("</td><td><a href=\"v3/"+cd.getReference().substring(26)+"/index.html\">"+cd.getReference()+"</a></td></tr>\r\n");          
//              else if (cd.getReference().startsWith("http://hl7.org/fhir")) 
//                s.append("</td><td><a href=\""+cd.getReference().substring(23)+".html\">"+cd.getReference()+"</a></td></tr>\r\n");          
//              else
//                s.append("</td><td><a href=\""+cd.getReference()+".html\">"+cd.getReferredValueSet().getUrl()+"</a></td></tr>\r\n");          
//            } else 
//              s.append("</td><td><a href=\""+cd.getReference()+".html\">??</a></td></tr>\r\n");          
//          } else if (cd.hasReference())
//            s.append("</td><td><a href=\""+cd.getReference()+"\">"+Utilities.escapeXml(cd.getDescription())+"</a></td></tr>\r\n");
//          else if (Utilities.noString(cd.getDescription()))
//            s.append("</td><td style=\"color: grey\">??</td></tr>\r\n");
//          else
//            s.append("</td><td>? "+Utilities.escapeXml(cd.getBinding().toString())+": "+Utilities.escapeXml(cd.getDescription())+"</td></tr>\r\n");
//        }
//      }
//    }
//    s.append("</table>\r\n");
//    return s.toString();
//  }
//  
//  private String genBindingTable(boolean codelists) {
//    StringBuilder s = new StringBuilder();
//    s.append("<table class=\"codes\">\r\n");
//    List<String> names = new ArrayList<String>();
//    for (String n : definitions.getBindings().keySet()) {
//      if ((codelists && definitions.getBindingByName(n).getBinding() == Binding.CodeList) || (!codelists && definitions.getBindingByName(n).getBinding() != Binding.CodeList))
//       names.add(n);
//    }
//    Collections.sort(names);
//    for (String n : names) {
//      if (!n.startsWith("*")) {
//        BindingSpecification cd = definitions.getBindingByName(n);
//        if (cd.getBinding() == Binding.CodeList || cd.getBinding() == Binding.Special)
//          s.append("  <tr><td title=\""+Utilities.escapeXml(cd.getDefinition())+"\">"+cd.getName()+"<br/><font color=\"grey\">http://hl7.org/fhir/sid/"+cd.getReference().substring(1)+"</font></td><td>");
//        else
//          s.append("  <tr><td title=\""+Utilities.escapeXml(cd.getDefinition())+"\">"+cd.getName()+"</td><td>");
//        if (cd.getBinding() == Binding.Unbound) {
//          s.append("Definition: "+Utilities.escapeXml(cd.getDefinition()));
//        } else if (cd.getBinding() == Binding.CodeList) {
//          assert(cd.getStrength() == BindingStrength.REQUIRED);
//          s.append("Required codes: ");
//          s.append("    <table class=\"codes\">\r\n");
//          boolean hasComment = false;
//          boolean hasDefinition = false;
//          for (DefinedCode c : cd.getCodes()) {
//            hasComment = hasComment || c.hasComment();
//            hasDefinition = hasDefinition || c.hasDefinition();
//          }
//          for (DefinedCode c : cd.getCodes()) {
//            if (hasComment)
//              s.append("    <tr><td>"+Utilities.escapeXml(c.getCode())+"</td><td>"+Utilities.escapeXml(c.getDefinition())+"</td><td>"+Utilities.escapeXml(c.getComment())+"</td></tr>");
//            else if (hasDefinition)
//              s.append("    <tr><td>"+Utilities.escapeXml(c.getCode())+"</td><td colspan=\"2\">"+Utilities.escapeXml(c.getDefinition())+"</td></tr>");
//            else
//              s.append("    <tr><td colspan=\"3\">"+Utilities.escapeXml(c.getCode())+"</td></tr>");
//          }
//          s.append("    </table>\r\n");
//        } else if (cd.getBinding() == Binding.ValueSet) {
//          if (cd.hasReference())
//            s.append("<a href=\""+cd.getReference()+"\">Value Set "+cd.getDescription()+"</a>");
//          else
//            s.append("Value Set "+cd.getDescription());
//          s.append(" (<a href=\"terminologies.html#"+cd.getStrength().toCode()+"\">"+cd.getStrength().getDisplay()+"</a>)");
//        } else if (cd.getBinding() == Binding.Reference) {
//            s.append("See <a href=\""+cd.getReference()+"\">"+cd.getReference()+"</a>");
//        } else if (cd.getBinding() == Binding.Special) {
//          if (cd.getName().equals("MessageEvent"))
//            s.append("See the <a href=\"message.html#Events\"> Event List </a>in the messaging framework");
//          else if (cd.getName().equals("ResourceType"))
//            s.append("See the <a href=\"terminologies.html#ResourceType\"> list of defined Resource Types</a>");
//          else if (cd.getName().equals("FHIRContentType"))
//            s.append("See the <a href=\"terminologies.html#fhircontenttypes\"> list of defined Resource and Data Types</a>");
//          else 
//            s.append("<a href=\"datatypes.html\">Any defined data Type name</a> (including <a href=\"resource.html#Resource\">Resource</a>)");
//        }        
//        s.append("</td></tr>\r\n");
//      }
//      
//    }
//    s.append("</table>\r\n");
//    return s.toString();
//  }

  private String getEventsTable(String resource) throws Exception {
    List<String> codes = new ArrayList<String>();
    codes.addAll(definitions.getEvents().keySet());
    Collections.sort(codes);
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"grid\">\r\n");
    s.append(" <tr><th>Code</th><th>Category</th><th>Description</th><th>Request Resources</th><th>Response Resources</th><th>Notes</th></tr>\r\n");
    for (String c : codes) {
      EventDefn e = definitions.getEvents().get(c);
      if (e.getUsages().size() == 1) {
        EventUsage u = e.getUsages().get(0);
        s.append(" <tr><td>"+e.getCode()+"<a name=\""+e.getCode()+"\"> </a></td><td>"+(e.getCategory() == null ? "??" : e.getCategory().toString())+"</td><td>"+e.getDefinition()+"</td>");
        s.append("<td>"+describeMsg(u.getRequestResources(), u.getRequestAggregations())+"</td><td>"+
            describeMsg(u.getResponseResources(), u.getResponseAggregations())+"</td><td>"+combineNotes(resource, e.getFollowUps(), u.getNotes(), "")+"</td></tr>\r\n");
      } else {
        boolean first = true;
        for (EventUsage u : e.getUsages()) {
          if (first)
            s.append(" <tr><td rowspan=\""+Integer.toString(e.getUsages().size())+"\">"+e.getCode()+"</td><td rowspan=\""+Integer.toString(e.getUsages().size())+"\">"+e.getDefinition()+"</td>");
          else
            s.append(" <tr>");
          first = false;
          s.append("<td>"+describeMsg(u.getRequestResources(), u.getRequestAggregations())+"</td><td>"+
              describeMsg(u.getResponseResources(), u.getResponseAggregations())+"</td><td>"+
              combineNotes(resource, e.getFollowUps(), u.getNotes(), "")+"</td></tr>\r\n");
        }
      }
    }
    s.append("</table>\r\n");
    return s.toString();
  }

  private String genResCodes() {
    StringBuilder html = new StringBuilder();
    List<String> names = new ArrayList<String>();
    names.addAll(definitions.getKnownResources().keySet());
    Collections.sort(names);
    for (String n : names) {
      DefinedCode c = definitions.getKnownResources().get(n);
      String htmlFilename = c.getComment();
           
      html.append("  <tr><td><a href=\""+htmlFilename+".html\">"+c.getCode()+"</a></td><td>"+Utilities.escapeXml(c.getDefinition())+"</td></tr>");
    }       
    return html.toString();
  }

  private String genDTCodes() {
    StringBuilder html = new StringBuilder();
    List<String> names = new ArrayList<String>();
    names.addAll(definitions.getTypes().keySet());
    names.addAll(definitions.getStructures().keySet());
    names.addAll(definitions.getInfrastructure().keySet());
    Collections.sort(names);
    for (String n : names) {
      if (!definitions.dataTypeIsSharedInfo(n)) {
        ElementDefn c = definitions.getTypes().get(n);
        if (c == null)
          c = definitions.getStructures().get(n);
        if (c == null)
          c = definitions.getInfrastructure().get(n);
        if (c.getName().equals("Extension"))
          html.append("  <tr><td><a href=\"extensibility.html\">"+c.getName()+"</a></td><td>"+Utilities.escapeXml(c.getDefinition())+"</td></tr>");
        else if (c.getName().equals("Narrative"))
          html.append("  <tr><td><a href=\"narrative.html#"+c.getName()+"\">"+c.getName()+"</a></td><td>"+Utilities.escapeXml(c.getDefinition())+"</td></tr>");
        else if (c.getName().equals("Reference") )
          html.append("  <tr><td><a href=\"references.html#"+c.getName()+"\">"+c.getName()+"</a></td><td>"+Utilities.escapeXml(c.getDefinition())+"</td></tr>");
        else
          html.append("  <tr><td><a href=\"datatypes.html#"+c.getName()+"\">"+c.getName()+"</a></td><td>"+Utilities.escapeXml(c.getDefinition())+"</td></tr>");
      }       
    }
    return html.toString();
  }

  private String genResImplList() {
    StringBuilder html = new StringBuilder();
    List<String> res = new ArrayList<String>();
    for (ResourceDefn n: definitions.getResources().values())
      res.add(n.getName());
    for (DefinedCode c : definitions.getKnownResources().values()) {
      if (res.contains(c.getComment()))
        html.append("  <tr><td>"+c.getCode()+"</td><td></td><td><a href=\""+c.getComment()+".xsd\">Schema</a></td><td><a href=\""+c.getComment()+".xml\">Example</a></td><td><a href=\""+c.getComment()+".json\">JSON Example</a></td>\r\n");
    }       
    return html.toString();

  }

  private String genReferenceImplList(String location) throws Exception {
    StringBuilder s = new StringBuilder();
    for (PlatformGenerator gen : referenceImplementations) {
      if (gen.wantListAsDownload())
        s.append("<tr><td><a href=\""+gen.getReference(version)+"\">"+gen.getTitle()+"</a></td><td>"+processMarkdown(location, gen.getDescription(version, svnRevision), "")+"</td></tr>\r\n");
    }
    return s.toString();
  }


  String processPageIncludesForPrinting(String file, String src, Resource resource, ImplementationGuideDefn ig) throws Exception {
    boolean even = false;
    List<String> tabs = new ArrayList<String>();

    while (src.contains("<%") || src.contains("[%"))
	  {
		  int i1 = src.indexOf("<%");
		  int i2 = src.indexOf("%>");
		  if (i1 == -1) {
			  i1 = src.indexOf("[%");
			  i2 = src.indexOf("%]");
		  }

      String s1 = src.substring(0, i1);
      String s2 = src.substring(i1 + 2, i2).trim();
      String s3 = src.substring(i2+2);
      String name = file.substring(0,file.indexOf(".")); 

      String[] com = s2.split(" ");
      if (com.length == 3 && com[0].equals("edt")) {
        if (tabs != null) 
          tabs.add("tabs-"+com[1]);
        src = s1+orgDT(com[1], xmlForDt(com[1], com[2]), treeForDt(com[1]), umlForDt(com[1], com[2]), umlForDt(com[1], com[2]+"b"), profileRef(com[1]), tsForDt(com[1]), jsonForDt(com[1], com[2]))+s3;
      } else if (com.length == 2 && com[0].equals("dt")) {
        if (tabs != null) 
          tabs.add("tabs-"+com[1]);
        src = s1+xmlForDt(com[1], null)+tsForDt(com[1])+s3;
      } else if (com.length == 2 && com[0].equals("dt.constraints")) 
        src = s1+genConstraints(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dt.restrictions")) 
        src = s1+genRestrictions(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dictionary"))
        src = s1+dictForDt(com[1])+s3;
      else if (com[0].equals("pageheader") || com[0].equals("dtheader") || com[0].equals("edheader") || com[0].equals("elheader") || com[0].equals("extheader") || com[0].equals("narrheader") || com[0].equals("formatsheader") || com[0].equals("resourcesheader") || 
          com[0].equals("txheader") || com[1].equals("txheader0") || com[0].equals("refheader") || com[0].equals("extrasheader") || com[0].equals("profilesheader") || com[0].equals("fmtheader") || 
          com[0].equals("igheader") || com[0].equals("cmpheader") || com[0].equals("atomheader") || com[0].equals("dictheader"))
        src = s1+s3;
      else if (com[0].equals("resheader"))
        src = s1+resHeader(name, "Document", com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("aresheader"))
        src = s1+abstractResHeader(name, "Document", com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("codelist"))
        src = s1+codelist((ValueSet) resource, com.length > 1 ? com[1] : null, false, true)+s3;
      else if (com[0].equals("linkcodelist"))
        src = s1+codelist((ValueSet) resource, com.length > 1 ? com[1] : null, true, false)+s3;
      else if (com[0].equals("codetoc"))
        src = s1+codetoc(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("res-category")) {
        src = s1+resCategory(s2.substring(com[0].length()+1))+s3;
        even = false;
      } else if (com[0].equals("res-item")) {
        even = !even;
        src = s1+resItem(com[1], even)+s3;
      } else if (com[0].equals("resdesc")) {
        src = s1+resDesc(com[1])+s3;
      } else if (com[0].equals("sidebar"))
        src = s1+generateSideBar(com.length > 1 ? com[1] : "")+s3;
      else if (com[0].equals("w5"))
        src = s1+genW5("true".equals(com[1]))+s3;
      else if (com[0].equals("vs-warning"))
        src = s1 + vsWarning((ValueSet) resource) + s3;      
      else if (com[0].equals("file"))
        src = s1+TextFile.fileToString(folders.srcDir + com[1]+".html")+s3;
      else  if (com[0].equals("conceptmaplistvs")) {
        throw new Error("Fix this");
//        BindingSpecification bs = definitions.getBindingByName(Utilities.fileTitle(file));
//        String ref;
//        if (bs == null) {
//          ref = "http://hl7.org/fhir/ValueSet/"+Utilities.fileTitle(file);
//        } else {
//          ref = bs.getReference();
//          if (ref.startsWith("valueset-"))
//            ref = ref.substring(9);
//          ref = "http://hl7.org/fhir/ValueSet/"+ref;
//        }
//        src = s1 + conceptmaplist(ref, com[1]) + s3;
      }  else if (com[0].equals("dtmappings"))
        src = s1 + genDataTypeMappings(com[1]) + s3;
      else if (com[0].equals("dtusage")) 
        src = s1 + genDataTypeUsage(com[1]) + s3;
      else if (com[0].equals("othertabs"))
        src = s1 + genOtherTabs(com[1], tabs) + s3;
      else if (com[0].equals("toc1"))
        src = s1 + generateToc(com[1], 1) + s3;
      else if (com[0].equals("toc2"))
        src = s1 + generateToc(com[1], 2) + s3;
      else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
      else if (com[0].equals("header"))
        src = s1+TextFile.fileToString(folders.srcDir + "header.html")+s3;
      else if (com[0].equals("newheader"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader.html")+s3;
      else if (com[0].equals("newheader1"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader1.html")+s3;
      else if (com[0].equals("footer"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer.html")+s3;
      else if (com[0].equals("newfooter"))
        src = s1+TextFile.fileToString(folders.srcDir + "newfooter.html")+s3;
      else if (com[0].equals("footer1"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer1.html")+s3;
      else if (com[0].equals("footer2"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer2.html")+s3;
      else if (com[0].equals("footer3"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer3.html")+s3;
      else if (com[0].equals("title"))
        src = s1+Utilities.escapeXml(name.toUpperCase().substring(0, 1)+name.substring(1))+s3;
      else if (com[0].equals("xtitle"))
        src = s1+Utilities.escapeXml(name.toUpperCase().substring(0, 1)+name.substring(1))+s3;
      else if (com[0].equals("name"))
        src = s1+name+s3;
      else if (com[0].equals("version"))
        src = s1+ini.getStringProperty("FHIR", "version")+s3;
      else if (com[0].equals("gendate"))
        src = s1+Config.DATE_FORMAT().format(new Date())+s3;
      else if (com[0].equals("maindiv"))
        src = s1+s3;
      else if (com[0].equals("/maindiv"))
        src = s1+s3;
      else if (com[0].equals("enteredInErrorTable"))
        src = s1+enteredInErrorTable()+s3;
      else if (com[0].equals("events"))
        src = s1 + getEventsTable(file)+ s3;
      else if (com[0].equals("resourcecodes"))
        src = s1 + genResCodes() + s3;
      else if (com[0].equals("datatypecodes"))
        src = s1 + genDTCodes() + s3;
//      else if (com[0].equals("bindingtable-codelists"))
//        src = s1 + genBindingTable(true) + s3;
//      else if (com[0].equals("bindingtable"))
//        src = s1 + genBindingsTable() + s3;
//      else if (com[0].equals("bindingtable-others"))
//        src = s1 + genBindingTable(false) + s3;
      else if (com[0].equals("codeslist"))
        src = s1 + genCodeSystemsTable() + s3;
      else if (com[0].equals("valuesetslist"))
        src = s1 + genValueSetsTable(ig) + s3;
      else if (com[0].equals("igvaluesetslist"))
        src = s1 + genIGValueSetsTable() + s3;
      else if (com[0].equals("resimplall"))
        src = s1 + genResImplList() + s3;
      else if (com[0].equals("impllist"))
        src = s1 + genReferenceImplList(file) + s3;
      else if (com[0].equals("txurl"))
        src = s1 + "http://hl7.org/fhir/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vstxurl"))
        src = s1 + "http://hl7.org/fhir/ValueSet/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vsurl")) {
        if (resource != null)
          src = s1 + ((ValueSet) resource).getUrl() + s3;
        else {
          throw new Error("Fix this");
//          BindingSpecification bs = definitions.getBindingByName(Utilities.fileTitle(file));
//          if (bs == null) {
//            src = s1 + "http://hl7.org/fhir/ValueSet/"+Utilities.fileTitle(file) + s3;
//          } else {
//            String reference = bs.getReference();
//            if (reference.startsWith("valueset-"))
//              reference = reference.substring(9);
//            src = s1 + "http://hl7.org/fhir/ValueSet/"+reference + s3;
//          }
        }
      } else if (com[0].equals("txdef"))
        src = s1 + generateCodeDefinition(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("vsxref"))
        src = s1 + xreferencesForFhir(name) + s3;      
      else if (com[0].equals("vsdef"))
        src = s1 + (resource != null ? Utilities.escapeXml(((ValueSet) resource).getDescription()) : generateValueSetDefinition(Utilities.fileTitle(file))) + s3;
      else if (com[0].equals("txusage"))
        src = s1 + generateBSUsage((ValueSet) resource) + s3;
      else if (com[0].equals("vsusage"))
        src = s1 + generateBSUsage((ValueSet) resource) + s3;
      else if (com[0].equals("vssummary"))
        src = s1 + "todo" + s3;
      else if (com[0].equals("piperesources"))
        src = s1+pipeResources()+s3;
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;      
//      else if (com[0].equals("vsexpansion"))
//        src = s1 + expandValueSet(Utilities.fileTitle(file), resource == null ? null : (ValueSet) resource) + s3;
      else if (com[0].equals("vsexpansionig"))
        src = s1 + expandValueSetIG((ValueSet) resource) + s3;
      else if (com[0].equals("pub-notice"))
        src = s1 + publicationNotice + s3;      
      else if (com[0].startsWith("!"))
        src = s1 + s3;  
      else 
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
    }
    return src;
  } 

  private String genOtherTabs(String mode, List<String> tabs) {
    StringBuilder b = new StringBuilder();
    if (tabs != null) {
      if (mode.equals("setup")) {
        for (String s : tabs) 
          b.append("$( '#"+s+"' ).tabs({ active: currentTabIndex, activate: function( event, ui ) { store(ui.newTab.index()); } });\r\n");
      }
      if (mode.equals("store")) {
        for (String s : tabs) 
          b.append("  $( '#"+s+"' ).tabs('option', 'active', currentTab);\r\n");      
      }
    }
    return b.toString();
  }

  private String generateOID(ValueSet vs, boolean isVs) throws Exception {
    if (vs == null)
      return "";
    if (isVs)
      return ToolingExtensions.getOID(vs);
    else if (vs.hasCodeSystem())
      return ToolingExtensions.getOID(vs.getCodeSystem());
    else
      return "";
  }

  private String generateDesc(ValueSet vs) {
    throw new Error("Fix this");
//    BindingSpecification cd = definitions.getBindingByReference("#"+fileTitle);
//    List<String> vslist = cd.getVSSources();
//    StringBuilder b = new StringBuilder();
//    if (vslist.contains("")) {
//      b.append("This value set defines its own codes");
//      vslist.remove(0);
//      if (vslist.size() > 0)
//        b.append(" and includes codes taken from");
//    } else 
//      b.append("This is a value set with codes taken from ");
//    int i = 0;
//    for (String n : cd.getVSSources()) {
//      i++;
//      if (Utilities.noString(n)) {
//        //b.append("Codes defined internally");
//      } else {
//        String an = fixUrlReference(n);
//        b.append("<a href=\""+an+"\">"+n+"</a>");
//      }
//      if (i == vslist.size() - 1)
//        b.append(" and ");
//      else if (vslist.size() > 1 && i != vslist.size() )
//        b.append(", ");
//    }
//    return b.toString()+":";
  }

  private String fixUrlReference(String n) {
    if (n.startsWith("urn:ietf:rfc:"))
      return "http://tools.ietf.org/html/rfc"+n.split("\\:")[3];
    if (codeSystems.containsKey(n))
      return (String) codeSystems.get(n).getUserData("path");
    return n;
  }

  private String expandValueSetIG(ValueSet vs) throws Exception {
    if (!hasDynamicContent(vs))
      return "";
    try {
      ValueSetExpansionOutcome result = workerContext.getTerminologyServices().expand(vs);
      if (result.getError() != null)
        return "<hr/>\r\n<div style=\"background-color: Floralwhite; border:1px solid maroon; padding: 5px;\"><!--1-->"+processExpansionError(result.getError())+"</div>";
      ValueSet exp = result.getValueset();
      if (exp == vs)
        throw new Exception("Expansion cannot be the same instance");
      exp.setCompose(null);
      exp.setCodeSystem(null);
      exp.setText(null); 
      exp.setDescription("Value Set Contents (Expansion) for "+vs.getName()+" at "+Config.DATE_FORMAT().format(new Date()));
      new NarrativeGenerator("", "", workerContext).generate(exp);
      return "<hr/>\r\n<div style=\"background-color: Floralwhite; border:1px solid maroon; padding: 5px;\">"+new XhtmlComposer().compose(exp.getText().getDiv())+"</div>";
    } catch (Exception e) {
      return "<hr/>\r\n<div style=\"background-color: Floralwhite; border:1px solid maroon; padding: 5px;\"><!--2-->"+processExpansionError(e.getMessage())+"</div>";
    }
  }
  
  private String processExpansionError(String error) {
    if (error.contains("Too many codes"))
      return TOO_MANY_CODES_TEXT;
    if (error.contains("unable to provide support"))
      return NO_CODESYSTEM_TEXT;
    return "This value set could not be expanded by the publication tooling: "+Utilities.escapeXml(error);
  }

  private String expandValueSet(String fileTitle, ValueSet vs) throws Exception {
    if (vs == null) 
      throw new Exception("no vs?");
    if (hasUnfixedContent(vs)) {
      String s = "<p>&nbsp;</p>\r\n<a name=\"expansion\"> </a>\r\n<h2>Expansion</h2>\r\n";
      return s + expandVS(vs, "", "");
    } else
      return "";
  }
  
  private boolean hasUnfixedContent(ValueSet vs) {
    if (vs.hasExpansion())
      return true;
    if (vs.hasCompose()) {
      if (vs.getCompose().hasImport())
        return true;
      for (ConceptSetComponent inc : vs.getCompose().getInclude())
        if (inc.hasFilter() || !inc.hasConcept())
          return true;
      for (ConceptSetComponent exc : vs.getCompose().getExclude())
        if (exc.hasFilter() || !exc.hasConcept())
          return true;
    }
    return false;
  }

  private String vsCLD(String fileTitle, ValueSet vs) throws Exception {
    if (vs == null) 
      throw new Exception("no vs?");
    ValueSet vs1 = vs.copy();
    vs1.setExpansion(null);
    vs1.setText(null);
    ImplementationGuideDefn ig = (ImplementationGuideDefn) vs.getUserData(ToolResourceUtilities.NAME_RES_IG);
    if (ig != null && !ig.isCore())
      new NarrativeGenerator("../", ig.getCode()+"/", workerContext).generate(vs1, null, false);
    else
      new NarrativeGenerator("", "", workerContext).generate(vs1, null, false);
    return "<hr/>\r\n<div style=\"background-color: Floralwhite; border:1px solid maroon; padding: 5px;\">"+new XhtmlComposer().compose(vs1.getText().getDiv())+"</div>";
  }
  
  private String expandV3ValueSet(String name) throws Exception {
    ValueSet vs = valueSets.get("http://hl7.org/fhir/ValueSet/v3-"+name);
    return expandVS(vs, "../../", "v3/"+name);
  }
  
  public ValueSet expandValueSet(ValueSet vs) throws Exception {
    ValueSetExpansionOutcome result = workerContext.getTerminologyServices().expand(vs);
    if (result.getError() != null)
      return null;
    else
      return result.getValueset();
  }


  private String stack(Exception e) {
    StringBuilder b = new StringBuilder();
    for (StackTraceElement s : e.getStackTrace()) {
      b.append("<br/>&nbsp;&nbsp;"+s.toString());
    }
    return b.toString();
  }

  private boolean hasDynamicContent(ValueSet vs) {
    if (vs.hasCompose()) {
      if (vs.getCompose().getImport().size() > 0)
        return true;
      for (ConceptSetComponent t : vs.getCompose().getInclude()) {
        if (t.getFilter().size() > 0)
          return true;
        if (t.getConcept().size() == 0)
          return true;
      }
      for (ConceptSetComponent t : vs.getCompose().getExclude()) {
        if (t.getFilter().size() > 0)
          return true;
        if (t.getConcept().size() == 0)
          return true;
      }
    }
    return false;
  }

  private String generateVSDesc(String fileTitle) throws Exception {
    throw new Error("Fix this");
//    BindingSpecification cd = definitions.getBindingByName(fileTitle);
//    if (cd == null)
//      return new XhtmlComposer().compose(definitions.getExtraValuesets().get(fileTitle).getText().getDiv());
//    else if (cd.getReferredValueSet().hasText() && cd.getReferredValueSet().getText().hasDiv())
//      return new XhtmlComposer().compose(cd.getReferredValueSet().getText().getDiv());
//    else
//      return cd.getReferredValueSet().getDescription();
  }

  String processPageIncludesForBook(String file, String src, String type, Resource resource, ImplementationGuideDefn ig) throws Exception {
    String workingTitle = null;
    int level = 0;
    boolean even = false;
    List<String> tabs = new ArrayList<String>();

    while (src.contains("<%") || src.contains("[%"))
	  {
		  int i1 = src.indexOf("<%");
		  int i2 = src.indexOf("%>");
		  if (i1 == -1) {
			  i1 = src.indexOf("[%");
			  i2 = src.indexOf("%]");
		  }

      String s1 = src.substring(0, i1);
      String s2 = src.substring(i1 + 2, i2).trim();
      String s3 = src.substring(i2+2);
      String name = file.substring(0,file.indexOf(".")); 

      String[] com = s2.split(" ");
      if (com.length == 3 && com[0].equals("edt")) {
        if (tabs != null) 
          tabs.add("tabs-"+com[1]);
        src = s1+orgDT(com[1], xmlForDt(com[1], com[2]), treeForDt(com[1]), umlForDt(com[1], com[2]), umlForDt(com[1], com[2]+"b"), profileRef(com[1]), tsForDt(com[1]), jsonForDt(com[1], com[2]))+s3;
      } else if (com.length == 3 && com[0].equals("dt")) {
        if (tabs != null) 
          tabs.add("tabs-"+com[1]);
        src = s1+xmlForDt(com[1], null)+tsForDt(com[1])+s3;
      } else if (com.length == 2 && com[0].equals("dt.constraints")) 
        src = s1+genConstraints(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dt.restrictions")) 
        src = s1+genRestrictions(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dictionary"))
        src = s1+dictForDt(com[1])+s3;
      else if (com[0].equals("pageheader") || com[0].equals("dtheader") || com[0].equals("edheader") || com[0].equals("elheader") || com[0].equals("extheader") || com[0].equals("resourcesheader") || 
          com[0].equals("formatsheader") || com[0].equals("narrheader") || com[0].equals("refheader") ||  com[0].equals("extrasheader") || com[0].equals("profilesheader") ||
          com[0].equals("txheader") || com[0].equals("txheader0") || com[0].equals("fmtheader") || com[0].equals("igheader") || com[0].equals("cmpheader") || com[0].equals("atomheader") || com[0].equals("dictheader")) 
        src = s1+s3;
      else if (com[0].equals("resheader"))
        src = s1+s3;
      else if (com[0].equals("aresheader"))
        src = s1+s3;
      else if (com[0].equals("othertabs"))
        src = s1 + genOtherTabs(com[1], tabs) + s3;
      else if (com[0].equals("dtmappings"))
        src = s1 + genDataTypeMappings(com[1]) + s3;
      else if (com[0].equals("dtusage")) 
        src = s1 + genDataTypeUsage(com[1]) + s3;
      else if (com[0].equals("w5"))
        src = s1+genW5("true".equals(com[1]))+s3;
      else if (com[0].equals("codelist"))
        src = s1+codelist((ValueSet) resource, com.length > 1 ? com[1] : null, false, true)+s3;
      else if (com[0].equals("linkcodelist"))
        src = s1+codelist((ValueSet) resource, com.length > 1 ? com[1] : null, true, false)+s3;
      else if (com[0].equals("codetoc"))
        src = s1+codetoc(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("vs-warning"))
        src = s1 + vsWarning((ValueSet) resource) + s3;      
      else if (com[0].equals("maponthispage"))
          src = s1+s3;
      else if (com[0].equals("onthispage"))
          src = s1+s3;
      else if (com[0].equals("conceptmaplistvs")) {
        ValueSet vs = (ValueSet) resource;
        String ref;
        if (vs == null) {
          ref = "http://hl7.org/fhir/ValueSet/"+Utilities.fileTitle(file);
        } else {
          ref = vs.getUrl();
        }
        src = s1 + conceptmaplist(ref, com[1]) + s3;
      }  else if (com[0].equals("res-category")) {
        src = s1+resCategory(s2.substring(com[0].length()+1))+s3;
        even = false;
      } else if (com[0].equals("res-item")) {
        even = !even;
        src = s1+resItem(com[1], even)+s3;
      } else if (com[0].equals("resdesc")) {
        src = s1+resDesc(com[1])+s3;
      } else if (com[0].equals("sidebar"))
        src = s1+s3;
      else if (com[0].equals("svg"))
        src = s1+svgs.get(com[1])+s3;
      else if (com[0].equals("diagram"))
        src = s1+new SvgGenerator(this, genlevel(level)).generate(folders.srcDir+ com[1], com[2])+s3;
      else if (com[0].equals("file"))
        src = s1+/*TextFile.fileToString(folders.srcDir + com[1]+".html")+*/s3;
      else if (com[0].equals("settitle")) {
        workingTitle = s2.substring(9).replace("{", "<%").replace("}", "%>");
        src = s1+s3;
      }  else if (com[0].equals("reflink")) {
        src = s1 + reflink(com[1]) + s3;      
      } else if (com[0].equals("res-ref-list")) {
        src = s1+genResRefList(com[1])+s3;
      } else if (com[0].equals("sclist")) {
        src = s1+genScList(com[1])+s3;
      } else if (com[0].equals("xcm")) {
        src = s1+getXcm(com[1])+s3;
      } else if (com[0].equals("fmm")) {
        src = s1+getFmm(com[1])+s3;
      } else if (com[0].equals("wg")) {
        src = s1+getWgLink(file, com[1])+s3;
      } else if (com[0].equals("wgt")) {
        src = s1+getWgTitle(com[1])+s3;
      } else if (com[0].equals("toc1")) {
        src = s1 + generateToc(com[1], 1) + s3;
      } else if (com[0].equals("toc2")) {
        src = s1 + generateToc(com[1], 2) + s3;
      } else if (com[0].equals("ig.registry")) {
        src = s1+buildIgRegistry(ig, com[1])+s3;
      } else if (com[0].equals("setlevel")) {
        level = Integer.parseInt(com[1]);
        src = s1+s3;
      } else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
      else if (com[0].equals("header"))
        src = s1+s3;
      else if (com[0].equals("newheader"))
        src = s1+s3;
      else if (com[0].equals("newheader1"))
        src = s1+s3;
      else if (com[0].equals("footer"))
        src = s1+s3;
      else if (com[0].equals("newfooter"))
        src = s1+s3;
      else if (com[0].equals("footer1"))
        src = s1+s3;
      else if (com[0].equals("footer2"))
        src = s1+s3;
      else if (com[0].equals("footer3"))
        src = s1+s3;
      else if (com[0].equals("title"))
        src = s1+(workingTitle == null ? Utilities.escapeXml(name.toUpperCase().substring(0, 1)+name.substring(1)) : workingTitle)+s3;
      else if (com[0].equals("xtitle"))
        src = s1+Utilities.escapeXml(name.toUpperCase().substring(0, 1)+name.substring(1))+s3;
      else if (com[0].equals("name"))
        src = s1+name+s3;
      else if (com[0].equals("version"))
        src = s1+ini.getStringProperty("FHIR", "version")+s3;
      else if (com[0].equals("gendate"))
        src = s1+Config.DATE_FORMAT().format(new Date())+s3;
      else if (com[0].equals("maindiv"))
        src = s1+s3;
      else if (com[0].equals("/maindiv"))
        src = s1+s3;
      else if (com[0].equals("events"))
        src = s1 + getEventsTable(file)+ s3;
      else if (com[0].equals("resourcecodes"))
        src = s1 + genResCodes() + s3;
      else if (com[0].equals("enteredInErrorTable"))
        src = s1+enteredInErrorTable()+s3;
      else if (com[0].equals("datatypecodes"))
        src = s1 + genDTCodes() + s3;
//      else if (com[0].equals("bindingtable-codelists"))
//        src = s1 + genBindingTable(true) + s3;
      else if (com[0].equals("codeslist"))
        src = s1 + genCodeSystemsTable() + s3;
      else if (com[0].equals("valuesetslist"))
        src = s1 + genValueSetsTable(ig) + s3;
      else if (com[0].equals("igvaluesetslist"))
        src = s1 + genIGValueSetsTable() + s3;
      else if (com[0].equals("conceptmapslist"))
        src = s1 + genConceptMapsTable() + s3;
//      else if (com[0].equals("bindingtable"))
//        src = s1 + genBindingsTable() + s3;
//      else if (com[0].equals("bindingtable-others"))
//        src = s1 + genBindingTable(false) + s3;
      else if (com[0].equals("vsxref"))
        src = s1 + xreferencesForFhir(name) + s3;      
      else if (com[0].equals("resimplall"))
        src = s1 + genResImplList() + s3;
      else if (com[0].equals("impllist"))
        src = s1 + genReferenceImplList(file) + s3;
      else if (com[0].equals("txurl"))
        src = s1 + "http://hl7.org/fhir/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vstxurl"))
        src = s1 + "http://hl7.org/fhir/ValueSet/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vsurl")) {
        if (resource != null)
          src = s1 + ((ValueSet) resource).getUrl() + s3;
        else {
          throw new Error("Fix this");
//          BindingSpecification bs = definitions.getBindingByName(Utilities.fileTitle(file));
//          if (bs == null) {
//            src = s1 + "http://hl7.org/fhir/ValueSet/"+Utilities.fileTitle(file) + s3;
//          } else {
//            String reference = bs.getReference();
//            if (reference.startsWith("valueset-"))
//              reference = reference.substring(9);
//            src = s1 + "http://hl7.org/fhir/ValueSet/"+reference + s3;
//          }
        }
      } else if (com[0].equals("txdef"))
        src = s1 + generateCodeDefinition(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("vsexpansion"))
        src = s1 + expandValueSet(Utilities.fileTitle(file), resource == null ? null : (ValueSet) resource) + s3;
      else if (com[0].equals("vsexpansionig"))
        src = s1 + expandValueSetIG((ValueSet) resource) + s3;
      else if (com[0].equals("vsdef"))
        src = s1 + (resource != null ? Utilities.escapeXml(((ValueSet) resource).getDescription()) : generateValueSetDefinition(Utilities.fileTitle(file))) + s3;
      else if (com[0].equals("txoid"))
        src = s1 + generateOID((ValueSet) resource, false) + s3;
      else if (com[0].equals("vsoid"))
        src = s1 + generateOID((ValueSet) resource, true) + s3;
      else if (com[0].equals("txname"))
        src = s1 + Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vsname"))
        src = s1 + ((ValueSet) resource).getName() + s3;
      else if (com[0].equals("vsref")) {
        if (resource != null)
          src = s1 + Utilities.fileTitle((String) resource.getUserData("filename")) + s3;
        else {
          throw new Error("Fix this");
//          BindingSpecification bs = definitions.getBindingByName(Utilities.fileTitle(file));
//          if (bs == null)
//            src = s1 + Utilities.fileTitle(file) + s3;
//          else
//            src = s1 + bs.getReference() + s3;
        }
      } else if (com[0].equals("txdesc"))
        src = s1 + generateDesc((ValueSet) resource) + s3;
      else if (com[0].equals("vsdesc"))
        src = s1 + (resource != null ? Utilities.escapeXml(((ValueSet) resource).getDescription()) :  generateVSDesc(Utilities.fileTitle(file))) + s3;
      else if (com[0].equals("txusage"))
        src = s1 + generateBSUsage((ValueSet) resource) + s3;
      else if (com[0].equals("vsusage"))
        src = s1 + generateBSUsage((ValueSet) resource) + s3;
      else if (com[0].equals("v2Index"))
        src = s1+genV2Index()+s3;
      else if (com[0].equals("v3Index-cs"))
        src = s1+genV3CSIndex()+s3;
      else if (com[0].equals("v3Index-vs"))
        src = s1+genV3VSIndex()+s3;
      else if (com[0].equals("vssummary"))
        src = s1 + "todo" + s3;
      else if (com[0].equals("compartmentlist"))
        src = s1 + compartmentlist() + s3;
      else if (com[0].equals("comp-title"))
        src = s1 + compTitle(name) + s3;
      else if (com[0].equals("comp-desc"))
        src = s1 + compDesc(name) + s3;
      else if (com[0].equals("comp-uri"))
        src = s1 + compUri(name) + s3;
      else if (com[0].equals("comp-identity"))
        src = s1 + compIdentity(name) + s3;
      else if (com[0].equals("comp-membership"))
        src = s1 + compMembership(name) + s3;
      else if (com[0].equals("comp-resources"))
        src = s1 + compResourceMap(name) + s3;
      else if (com[0].equals("breadcrumb"))
        src = s1 + breadCrumbManager.make(name) + s3;
      else if (com[0].equals("navlist"))
        src = s1 + breadCrumbManager.navlist(name, genlevel(level)) + s3;
      else if (com[0].equals("breadcrumblist")) {
        String crumbTitle = (workingTitle == null ? Utilities.escapeXml(name.toUpperCase().substring(0, 1)+name.substring(1)) : workingTitle);
        src = s1 + ((ig == null || ig.isCore()) ? breadCrumbManager.makelist(name, type, genlevel(level), crumbTitle) : ig.makeList(name, type, genlevel(level), crumbTitle)) + s3;      
      }else if (com[0].equals("year"))
        src = s1 + new SimpleDateFormat("yyyy").format(new Date()) + s3;      
      else if (com[0].equals("revision"))
        src = s1 + svnRevision + s3;      
      else if (com[0].equals("level"))
        src = s1 + genlevel(level) + s3;  
      else if (com[0].equals("piperesources"))
        src = s1+pipeResources()+s3;
      else if (com[0].equals("archive"))
        src = s1 + makeArchives() + s3;  
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;      
      else if (com[0].equals("pub-notice"))
        src = s1 + publicationNotice + s3;      
      else if (com[0].equals("profilelist"))
        src = s1 + genProfilelist() + s3;  
      else if (com[0].equals("extensionslist"))
        src = s1 + genExtensionsTable() + s3;  
      else if (com[0].equals("igprofileslist"))
        src = s1 + genIGProfilelist() + s3;  
      else if (com[0].equals("operationslist"))
        src = s1 + genOperationList() + s3;  
      else if (com[0].equals("id_regex"))
        src = s1 + FormatUtilities.ID_REGEX + s3;  
      else if (com[0].equals("allparams"))
        src = s1 + allParamlist() + s3;      
      else if (com[0].equals("resourcecount"))
        src = s1 + Integer.toString(definitions.getResources().size()) + s3;  
      else if (com[0].equals("status-codes"))
        src = s1 + genStatusCodes() + s3;        
      else if (com[0].equals("dictionary.name"))
        src = s1 + definitions.getDictionaries().get(name) + s3;        
      else if (com[0].equals("dictionary.view"))
        src = s1 + ResourceUtilities.representDataElementCollection(this.workerContext, (Bundle) resource, true, "hspc-QuantitativeLab-dataelements") + s3;        
      else if (com[0].startsWith("!"))
        src = s1 + s3;
      else if (com[0].equals("identifierlist"))
        src = s1 + genIdentifierList()+s3;
      else if (com[0].equals("internalsystemlist"))
        src = s1 + genCSList()+s3;
      else if (com[0].equals("ig.title"))
        src = s1+ig.getName()+s3;
      else if (com[0].equals("ig.wglink"))
        src = s1+igLink(ig)+s3;
      else if (com[0].equals("ig.wgt"))
        src = s1+ig.getCommittee()+s3;
      else if (com[0].equals("ig.fmm"))
        src = s1+ig.getFmm()+s3;
      else if (com[0].equals("ig.ballot"))
        src = s1+ig.getBallot()+s3;
      else if (com[0].equals("igvsregistries"))
        src = s1+igVsRegistryList()+s3;        
      else if (com[0].equals("fhir-path"))
        src = s1 + "../" + s3;  
      else 
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
    }
    return src;
  } 

  private String igVsRegistryList() {
    StringBuilder b = new StringBuilder();
    for (ImplementationGuideDefn ig : definitions.getSortedIgs()) {
      if (!ig.isCore()) {
        boolean found = false;
        for (ValueSet vs : valueSets.values()) {
          if (vs.getUserData(ToolResourceUtilities.NAME_RES_IG) == ig)
            found = true;
        }
        ImplementationGuidePageComponent p = ig.getVSRegistry();
        if (found && p != null) {
          b.append(" <li><a href=\"");
          b.append(ig.getCode());
          b.append("/"+p.getSource()+"\">");
          b.append(ig.getName()+" "+p.getName());
          b.append("</a></li>\r\n");
        }
      }
    }
    return b.toString();
  }


  private String igLink(ImplementationGuideDefn ig) {
    WorkGroup wg = definitions.getWorkgroups().get(ig.getCommittee());
    return wg == null ? "?"+ig.getCommittee()+"?" : wg.getUrl();
  }

  private String pipeResources() {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (String n : definitions.sortedResourceNames()) {
      if (first)
        first = false;
      else
        b.append("|");
      b.append(n);
    }
    return b.toString();
  }

  private String enteredInErrorTable() throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append("<tr><td><b>Resource</b></td><td><b>Status</b></td></tr>");
    for (String n : definitions.sortedResourceNames()) {
      String s = definitions.getResourceByName(n).getEnteredInErrorStatus();
      b.append("<tr><td><a href=\""+n.toLowerCase()+".html\">"+n+"</a></td><td>"+Utilities.escapeXml(s)+"</td></tr>");
    }
    b.append("</table>\r\n");
    return b.toString();
  }

  private String genDataTypeUsage(String tn) {
    StringBuilder b = new StringBuilder();
    for (ElementDefn e : definitions.getTypes().values()) {
      if (usesType(e, tn)) {
        b.append(", <a href=\"#").append(e.getName()).append("\">").append(e.getName()).append("</a>");
      }
    }
    List<String> resources = new ArrayList<String>();
    for (ResourceDefn e : definitions.getResources().values()) {
      if (usesType(e.getRoot(), tn)) {
        resources.add(e.getName());
      }
    }
    for (ResourceDefn e : definitions.getBaseResources().values()) {
      if (usesType(e.getRoot(), tn)) {
        resources.add(e.getName());
      }
    }
    Collections.sort(resources);
    for (String n : resources)
      b.append(", <a href=\"").append(n.toLowerCase()).append(".html\">").append(n).append("</a>");

    if (b.toString().length() < 2)
      return "(not used as yet)";
    
    String s = b.toString().substring(2);
    int i = s.lastIndexOf(", ");
    if ( i > 1)
      s = s.substring(0, i)+" and"+s.substring(i+1);
    return s;
  }

  private boolean usesType(ElementDefn e, String tn) {
    if (usesType(e.getTypes(), tn)) 
      return true;
    for (ElementDefn c : e.getElements()) 
      if (usesType(c, tn))
        return true;
    return false;
  }

  private boolean usesType(List<TypeRef> types, String tn) {
    for (TypeRef t : types) {
      if (t.getName().equals(tn)) 
        return true; 
      // no need to check parameters
    }
    return false;
  }

  String processResourceIncludes(String name, ResourceDefn resource, String xml, String json, String tx, String dict, String src, String mappings, String mappingsList, String type, String pagePath, ImplementationGuideDefn ig) throws Exception {
    String workingTitle = Utilities.escapeXml(resource.getName());
    List<String> tabs = new ArrayList<String>();
    int level = (ig == null || ig.isCore()) ? 0 : 1;  
    
    while (src.contains("<%") || src.contains("[%"))
    {
      int i1 = src.indexOf("<%");
      int i2 = src.indexOf("%>");
      if (i1 == -1) {
        i1 = src.indexOf("[%");
        i2 = src.indexOf("%]");
      }
      String s1 = src.substring(0, i1);
      String s2 = src.substring(i1 + 2, i2).trim();
      String s3 = src.substring(i2+2);

      String[] com = s2.split(" ");
      if (com[0].equals("resheader"))
        src = s1+resHeader(name, resource.getName(), com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("aresheader"))
        src = s1+abstractResHeader(name, resource.getName(), com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("lmheader"))
        src = s1+lmHeader(name, resource.getName(), com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("sidebar"))
        src = s1+generateSideBar(com.length > 1 ? com[1] : "")+s3;
      else if (com[0].equals("file"))
        src = s1+TextFile.fileToString(folders.srcDir + com[1]+".html")+s3;
      else if (com[0].equals("settitle")) {
        workingTitle = s2.substring(9).replace("{", "<%").replace("}", "%>");
        src = s1+s3;
      }
      else if (com[0].equals("othertabs"))
        src = s1 + genOtherTabs(com[1], tabs) + s3;
      else if (com[0].equals("svg"))
        src = s1+new SvgGenerator(this, genlevel(level)).generate(resource, com[1])+s3;        
      else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing resource "+name);
      else if (com[0].equals("pageheader"))
        src = s1+pageHeader(resource.getName())+s3;
      else if (com[0].equals("maponthispage"))
          src = s1+mapOnThisPage(mappingsList)+s3;
      else if (com[0].equals("header"))
        src = s1+TextFile.fileToString(folders.srcDir + "header.html")+s3;
      else if (com[0].equals("newheader"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader.html")+s3;
      else if (com[0].equals("newheader1"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader1.html")+s3;
      else if (com[0].equals("footer"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer.html")+s3;
      else if (com[0].equals("newfooter"))
        src = s1+TextFile.fileToString(folders.srcDir + "newfooter.html")+s3;
      else if (com[0].equals("footer1"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer1.html")+s3;
      else if (com[0].equals("footer2"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer2.html")+s3;
      else if (com[0].equals("footer3"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer3.html")+s3;
      else if (com[0].equals("title"))
        src = s1+workingTitle+s3;
      else if (com[0].equals("xtitle"))
        src = s1+Utilities.escapeXml(resource.getName())+s3;
      else if (com[0].equals("status"))
        src = s1+resource.getStatus()+s3;
      else if (com[0].equals("draft-note"))
        src = s1+getDraftNote(resource)+s3;
      else if (com[0].equals("introduction")) 
        src = s1+loadXmlNotes(name, "introduction", true, resource.getRoot().getDefinition(), resource, tabs, null)+s3;
      else if (com[0].equals("notes")) 
        src = s1+loadXmlNotes(name, "notes", false, null, resource, tabs, null)+s3;
      else if (com[0].equals("examples")) 
        src = s1+produceExamples(resource)+s3;
      else if (com[0].equals("profiles")) 
        src = s1+produceProfiles(resource)+s3;
      else if (com[0].equals("wg")) 
        src = s1+(resource.getWg() == null ?  "(No assigned work group)" : "<a _target=\"blank\" href=\""+resource.getWg().getUrl()+"\">"+resource.getWg().getName()+"</a> Work Group")+s3;
      else if (com[0].equals("fmm")) 
        src = s1+"<a href=\"resource.html#maturity\">Maturity Level</a>: "+resource.getFmmLevel()+s3;
      else if (com[0].equals("complinks")) 
        src = s1+getCompLinks(resource)+s3;
      else if (com[0].equals("example-list")) 
        src = s1+produceExampleList(resource)+s3;
      else if (com[0].equals("name"))
        src = s1+name+s3;
      else if (com[0].equals("search"))
        src = s1+getSearch(resource)+s3;
      else if (com[0].equals("asearch"))
        src = s1+getAbstractSearch(resource)+s3;
      else if (com[0].equals("version"))
        src = s1+ini.getStringProperty("FHIR", "version")+s3;
      else if (com[0].equals("gendate"))
        src = s1+Config.DATE_FORMAT().format(new Date())+s3;
      else if (com[0].equals("definition"))
        src = s1+resource.getRoot().getDefinition()+s3;
      else if (com[0].equals("xml"))
        src = s1+xml+s3;
      else if (com[0].equals("json"))
        src = s1+json+s3;
      else if (com[0].equals("tx"))
        src = s1+tx+s3;
      else if (com[0].equals("inv"))
        src = s1+genResourceConstraints(resource)+s3;
      else if (com[0].equals("resource-table"))
        src = s1+genResourceTable(resource, genlevel(level))+s3;
      else if (com[0].equals("plural"))
        src = s1+Utilities.pluralizeMe(name)+s3;
      else if (com[0].equals("dictionary"))
        src = s1+dict+s3;
      else if (com[0].equals("mappings"))
          src = s1+mappings+s3;
      else if (com[0].equals("mappingslist"))
          src = s1+mappingsList+s3;
      else if (com[0].equals("breadcrumb"))
        src = s1 + breadCrumbManager.make(name) + s3;
      else if (com[0].equals("navlist"))
        src = s1 + breadCrumbManager.navlist(name, genlevel(level)) + s3;
      else if (com[0].equals("breadcrumblist"))
        src = s1 + ((ig == null || ig.isCore()) ? breadCrumbManager.makelist(name, type, genlevel(level), workingTitle) : ig.makeList(name, type, genlevel(level), workingTitle)) + s3;      
      else if (com[0].equals("year"))
        src = s1 + new SimpleDateFormat("yyyy").format(new Date()) + s3;      
      else if (com[0].equals("revision"))
        src = s1 + svnRevision + s3;      
      else if (com[0].equals("level"))
        src = s1 + genlevel(level) + s3;  
      else if (com[0].equals("atitle"))
        src = s1 + abstractResourceTitle(resource) + s3;  
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;      
      else if (com[0].equals("example-header"))
        src = s1 + loadXmlNotesFromFile(Utilities.path(folders.srcDir, name.toLowerCase(), name+"-examples-header.xml"), false, null, resource, tabs, null)+s3;
      else if (com[0].equals("pub-notice"))
        src = s1 + publicationNotice + s3;      
      else if (com[0].equals("resref"))
        src = s1 + getReferences(resource.getName()) + s3;      
      else if (com[0].equals("pagepath"))
        src = s1 + pagePath + s3;  
      else if (com[0].equals("rellink"))
        src = s1 + Utilities.URLEncode(pagePath) + s3;  
      else if (com[0].equals("baseURL"))
        src = s1 + Utilities.URLEncode(baseURL) + s3;  
      else if (com[0].equals("operations"))
        src = s1 + genOperations(resource.getOperations(), resource.getName(), resource.getName().toLowerCase(), "") + s3;  
      else if (com[0].equals("operations-summary"))
        src = s1 + genOperationsSummary(resource.getOperations()) + s3;  
      else if (com[0].equals("opcount"))
        src = s1 + genOpCount(resource.getOperations()) + s3;  
      else if (com[0].startsWith("!"))
        src = s1 + s3;  
      else if (com[0].equals("resurl")) {
        if (isAggregationEndpoint(resource.getName()))
          src = s1+s3;
        else
          src = s1+"<p>The resource name as it appears in a  RESTful URL is <a href=\"http.html#root\">[root]</a>/"+name+"/</p>"+s3;
      } else 
        throw new Exception("Instruction <%"+s2+"%> not understood parsing resource "+name);

    }
    return src;
  }

  private String getCompLinks(ResourceDefn resource) {
    List<String> names = new ArrayList<String>();
    for (Compartment comp : definitions.getCompartments()) {
      if (comp.getResources().containsKey(resource) && !Utilities.noString(comp.getResources().get(resource)))
        names.add(comp.getName());
    }
    StringBuilder b = new StringBuilder();
    b.append("<a href=\"compartments.html\">Compartments</a>: ");
    if (names.isEmpty())
      b.append("Not linked to any defined compartments");
    else {
      Collections.sort(names);
      boolean first = true;
      for (String name : names) {
        if (first)
          first = false;
        else
          b.append(", ");
        b.append("<a href=\"compartment-"+name+".html\">"+definitions.getCompartmentByName(name).getTitle()+"</a>");
      }
    }
    return b.toString();
  }

  private String getDraftNote(ResourceDefn resource) {
    if ("draft".equals(resource.getStatus()))
      return "<p style=\"background-color: salmon; border:1px solid maroon; padding: 5px;\">This resource is <a href=\"ballot-intro.html#draft\">marked as a draft</a>.</p>";
    else
      return "";
  }

  private String abstractResourceTitle(ResourceDefn resource) {
    if (resource.getName().equals("Resource"))
      return "Base Resource Definitions";
    else 
      return resource.getName() + " Resource";
  }

  private String genOpCount(List<Operation> oplist) {
    return Integer.toString(oplist.size()) + (oplist.size() == 1 ? " operation" : " operations");
  }

  private String genOperationsSummary(List<Operation> oplist) throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"list\">\r\n");
    for (Operation op : oplist) {
      b.append("<tr><td><a href=\"#"+op.getName()+"\">$"+Utilities.escapeXml(op.getName())+"</a></td><td>"+Utilities.escapeXml(op.getTitle())+"</td></tr>\r\n");      
    }
    b.append("</table>\r\n");
    return b.toString();
  }
  
  private String genOperations(List<Operation> oplist, String n, String id, String prefix) throws Exception {
    StringBuilder b = new StringBuilder();
    for (Operation op : oplist) {
      b.append("<h3>").append(Utilities.escapeXml(op.getTitle())).append("<a name=\"").append(op.getName()).append("\"> </a></h3>\r\n");
      b.append(processMarkdown(n, op.getDoco(), prefix)+"\r\n");
      b.append("<p><a href=\"operation-"+id+"-"+op.getName().toLowerCase()+".html\">Formal Definition</a> (as a <a href=\""+prefix+"operationdefinition.html\">OperationDefinition</a>).</p>\r\n");
      if (op.isSystem())
        b.append("<p>URL: [base]/$").append(op.getName()).append("</p>\r\n");
      if (op.isType())
        b.append("<p>URL: [base]/").append(n).append("/$").append(op.getName()).append("</p>\r\n");
      if (op.isInstance())
        b.append("<p>URL: [base]/").append(n).append("/[id]/$").append(op.getName()).append("</p>\r\n");
      if (!op.getParameters().isEmpty()) {
        b.append("<table class=\"grid\">\r\n");
        if (hasParameters(op.getParameters(), "In")) {
          genParameterHeader(b, "In");
          for (OperationParameter p : op.getParameters()) 
            genOperationParameter(n, "In", "", b, op, p, prefix);
        }
        if (hasParameters(op.getParameters(), "Out")) {
          genParameterHeader(b, "Out");
          for (OperationParameter p : op.getParameters()) 
            genOperationParameter(n, "Out", "", b, op, p, prefix);
        }
        b.append("</table>\r\n");
      }
      b.append(processMarkdown(n, op.getFooter(), prefix)).append("\r\n");
      if (op.getExamples().size() > 0) {
        b.append("<h4>Examples</h4>\r\n");
        for (OperationExample ex : op.getExamples())
          if (!ex.isResponse())
            renderExample(b, ex, "Request");
        for (OperationExample ex : op.getExamples())
          if (ex.isResponse())
            renderExample(b, ex, "Response");
      }
      b.append("<p>&nbsp;</p>");
    }
    return b.toString();
  }

  private void renderExample(StringBuilder b, OperationExample ex, String type) throws Exception {
    if (Utilities.noString(ex.getComment()))
      b.append("<p>"+type+":</p>\r\n");
    else  
      b.append("<p>"+Utilities.capitalize(ex.getComment())+" ("+type+"):</p>\r\n");

    b.append("<pre>\r\n");
    String[] lines = ex.getContent().split("\\r\\n");
    for (String l : lines) {
      if (l.startsWith("$bundle ")) {
        b.append(Utilities.escapeXml("<Bundle xml=\"http://hl7.org/fhir\">\r\n"));
        b.append(Utilities.escapeXml("  <id value=\""+UUID.randomUUID().toString().toLowerCase()+"\"/>\r\n"));
        b.append(Utilities.escapeXml("  <type value=\"searchset\"/>\r\n"));
        Example e = getExampleByRef(l.substring(8));
        addExample(b, e);
        for (Example x : e.getInbounds()) {
          addExample(b, x);
        }
        b.append(Utilities.escapeXml("</Bundle>\r\n"));
      } else {
        b.append(l);
        b.append("\r\n");
      }
    }
    b.append("</pre>\r\n");
  }

  private void addExample(StringBuilder b, Example x) throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
    b.append(Utilities.escapeXml("  <entry>\r\n"));
    b.append(Utilities.escapeXml("    <fullUrl value=\"http://hl7.org/fhir/"+x.getResourceName()+"/"+x.getId()+"\"/>\r\n"));
    b.append(Utilities.escapeXml("    <resource>\r\n"));
    ByteArrayOutputStream bo = new ByteArrayOutputStream();
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    DOMSource source = new DOMSource(x.getXml());
    StringWriter writer =  new StringWriter();
    transformer.transform(source, new StreamResult(writer));
    String[] lines = writer.getBuffer().toString().split("\\n");
    for (String l : lines) {
      b.append("     ");
      if (l.contains("xmlns:xsi=")) {
        b.append(Utilities.escapeXml(l.substring(0, l.indexOf("xmlns:xsi=")-1)));
        b.append(">\r\n");
      } else {
        b.append(Utilities.escapeXml(l));
        b.append("\r\n");
      }
    }
    b.append(Utilities.escapeXml("    </resource>\r\n"));
    b.append(Utilities.escapeXml("  </entry>\r\n"));
  }

  private Example getExampleByRef(String bundle) throws Exception {
    String[] parts = bundle.split("\\/");
    ResourceDefn r = definitions.getResourceByName(parts[0]);
    for (Example e : r.getExamples()) {
      if (e.getId().equals(parts[1]))
        return e;
    }
    throw new Exception("unable to resolve "+bundle);
  }

  private boolean hasParameters(List<OperationParameter> parameters, String mode) {
    for (OperationParameter p : parameters) {
      if (mode.equalsIgnoreCase(p.getUse()))
        return true; 
    }
    return false;
  }

  private void genParameterHeader(StringBuilder b, String mode) {
    b.append("<tr><td colspan=\"5\"><b>").append(mode).append(" Parameters:</b></td></tr>\r\n");
    b.append("<tr><td>");
    b.append("<b>Name</b>");
    b.append("</td><td>");
    b.append("<b>Cardinality</b>");
    b.append("</td><td>");
    b.append("<b>Type</b>");
    b.append("</td><td>");
    b.append("<b>Binding</b>");
    b.append("</td><td>");
    b.append("<b>Profile</b>");
    b.append("</td><td>");
    b.append("<b>Documentation</b>");
    b.append("</td></tr>");
  }

  private void genOperationParameter(String resource, String mode, String path, StringBuilder b, Operation op, OperationParameter p, String prefix) throws Exception {
    if (!Utilities.noString(p.getUse()) && !mode.equalsIgnoreCase(p.getUse()))
      return;
    
    b.append("<tr><td>");
    b.append(path+p.getName());
    b.append("</td><td>");
    b.append(p.describeCardinality());
    b.append("</td><td>");
    String t = p.getType();
    if (definitions.hasResource(t)) {
      b.append("<a href=\"");
      b.append(prefix);
      b.append(t.toLowerCase());
      b.append(".html\">");
      b.append(t);
      b.append("</a>");
    } else if (definitions.hasPrimitiveType(t)) {
      b.append("<a href=\""+prefix+"datatypes.html#");
      b.append(t);
      b.append("\">");
      b.append(t);
      b.append("</a>");

    } else if (definitions.hasElementDefn(t)) {
      b.append("<a href=\"");
      b.append(prefix);
      b.append(definitions.getSrcFile(t));
      b.append(".html#");
      b.append(t);
      b.append("\">");
      b.append(t);
      b.append("</a>");

    } else if (t.startsWith("Reference(")) {
      b.append("<a href=\""+prefix+"references.html#Reference\">Reference</a>");
      String pn = t.substring(0, t.length()-1).substring(10);
      b.append("(");
      boolean first = true;
      for (String tn : pn.split("\\|")) {
        if (first)
          first = false;
        else
          b.append("|");
        b.append("<a href=\"");
        b.append(prefix);
        b.append(tn.toLowerCase());
        b.append(".html\">");
        b.append(tn);
        b.append("</a>");
      }
      b.append(")");
    } else if (!t.equals("Tuple")) {
      b.append(t);
    }
    b.append("</td><td>");
    if (p.getBs() != null && p.getBs().getBinding() != BindingMethod.Unbound) {
      b.append("<a href=\""+BaseGenerator.getBindingLink(prefix, p.getBs())+"\">"+(p.getBs().getValueSet() != null ? p.getBs().getValueSet().getName() : p.getBs().getName())+"</a>");
      b.append(" (<a href=\""+prefix+"terminologies.html#"+p.getBs().getStrength().toCode()+"\">"+p.getBs().getStrength().getDisplay()+"</a>)");
    }
    b.append("</td><td>");
    if (!Utilities.noString(p.getProfile())) {
      StructureDefinition sd = profiles.get(p.getProfile());
      if (sd != null)
        b.append("<a href=\""+prefix+sd.getUserString("path")+"\">"+sd.getName()+"</a>");
      else
        b.append(p.getProfile()+" (unknown)");
    }
    b.append("</td><td>");
    b.append(processMarkdown(resource, p.getDoc(), prefix));
    if (p.getName().equals("return") && isOnlyOutParameter(op.getParameters(), p) && definitions.hasResource(t))
      b.append("<p>Note: as this the only out parameter, it is a resource, and it has the name 'return', the result of this operation is returned directly as a resource</p>");
    b.append("</td></tr>");
    if (p.getParts() != null)
      for (OperationParameter pp : p.getParts())
        genOperationParameter(resource, mode, path+p.getName()+".", b, op, pp, prefix);
  }


  private boolean isOnlyOutParameter(List<OperationParameter> parameters, OperationParameter p) {
    for (OperationParameter q : parameters)
      if (q != p && q.getUse().equals("out"))
        return false;
    return p.getUse().equals("out");
  }

  private String getReferences(String name) throws Exception {
    List<String> refs = new ArrayList<String>();
    for (String rn : definitions.sortedResourceNames()) {
      if (!rn.equals(name)) {
        ResourceDefn r = definitions.getResourceByName(rn);
        if (usesReference(r.getRoot(), name)) {
          refs.add(rn);
        }
      }
    }
    if (refs.size() == 1)
      return "<p>This resource is referenced by <a href=\""+refs.get(0).toLowerCase()+".html\">"+refs.get(0).toLowerCase()+"</a></p>\r\n";
    else if (refs.size() > 1)
      return "<p>This resource is referenced by "+asLinks(refs)+"</p>\r\n";
    else
      return "";
  }

  private String asLinks(List<String> refs) {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < refs.size(); i++) {
      if (i == refs.size() - 1)
        b.append(" and ");
      else if (i > 0)
        b.append(", ");
      b.append("<a href=\"").append(refs.get(i).toLowerCase()).append(".html\">").append(refs.get(i)).append("</a>");
    }
      return b.toString();
  }

  private boolean usesReference(ElementDefn e, String name) {
    if (usesReference(e.getTypes(), name))
      return true;
    for (ElementDefn c : e.getElements()) {
      if (usesReference(c, name))
        return true;
    }
    return false;
  }

  private boolean usesReference(List<TypeRef> types, String name) {
    for (TypeRef t : types) {
      if (t.getName().equals("Reference")) {
        for (String p : t.getParams()) {
          if (p.equals(name))
            return true;
        }
      }
    }
    return false;
  }

  /*
  private String prepWikiName(String name) {
    return Utilities.noString(name) ? "Index" : Utilities.capitalize(Utilities.fileTitle(name));
  }
  */
  
  private String getSearch(ResourceDefn resource) {
    if (resource.getSearchParams().size() == 0)
      return "";
    else {
      StringBuilder b = new StringBuilder();
      b.append("<h2>Search Parameters</h2>\r\n");
      if (resource.getName().equals("Query"))
        b.append("<p>Search parameters for this resource. The <a href=\"#all\">common parameters</a> also apply.</p>\r\n");
      else
        b.append("<p>Search parameters for this resource. The <a href=\"search.html#all\">common parameters</a> also apply. See <a href=\"search.html\">Searching</a> for more information about searching in REST, messaging, and services.</p>\r\n");
      b.append("<table class=\"list\">\r\n");
      b.append("<tr><td><b>Name</b></td><td><b>Type</b></td><td><b>Description</b></td><td><b>Paths</b></td></tr>\r\n");
      List<String> names = new ArrayList<String>();
      names.addAll(resource.getSearchParams().keySet());
      Collections.sort(names);
      for (String name : names)  {
        SearchParameterDefn p = resource.getSearchParams().get(name);
        b.append("<tr><td>").append(p.getCode()).append("</td><td><a href=\"search.html#").append(p.getType()).append("\">").append(p.getType()).append("</a></td><td>")
                .append(Utilities.escapeXml(p.getDescription())).append("</td><td>").append(presentPaths(p.getPaths())).append(p.getType() == SearchType.reference ? p.getTargetTypesAsText() : "").append("</td></tr>\r\n");
      }
      b.append("</table>\r\n");
      return b.toString();
    }
  }

  private String getAbstractSearch(ResourceDefn resource) {
    if (resource.getSearchParams().size() == 0)
      return "";
    else {
      StringBuilder b = new StringBuilder();
      b.append("<h2>Search Parameters</h2>\r\n");
      b.append("<p>Common search parameters defined by this resource. See <a href=\"search.html\">Searching</a> for more information about searching in REST, messaging, and services.</p>\r\n");
      b.append("<table class=\"list\">\r\n");
      b.append("<tr><td><b>Name</b></td><td><b>Type</b></td><td><b>Description</b></td><td><b>Paths</b></td></tr>\r\n");
      List<String> names = new ArrayList<String>();
      names.addAll(resource.getSearchParams().keySet());
      Collections.sort(names);
      for (String name : names)  {
        SearchParameterDefn p = resource.getSearchParams().get(name);
        b.append("<tr><td>").append(p.getCode()).append("</td><td><a href=\"search.html#").append(p.getType()).append("\">").append(p.getType())
                .append("</a></td><td>").append(Utilities.escapeXml(p.getDescription())).append("</td><td>").append(presentPaths(p.getPaths())).append(p.getType() == SearchType.reference ? p.getTargetTypesAsText() : "").append("</td></tr>\r\n");
      }
      b.append("</table>\r\n");
      return b.toString();
    }
  }

  private String getSearch(Profile pack) {
    if (pack.getSearchParameters().size() == 0)
      return "";
    else {
      StringBuilder b = new StringBuilder();
      b.append("<h2>Search Parameters</h2>\r\n");       
      b.append("<p>Search parameters defined by this structure. See <a href=\"search.html\">Searching</a> for more information about searching in REST, messaging, and services.</p>\r\n");
      b.append("<table class=\"list\">\r\n");
      b.append("<tr><td><b>Name</b></td><td><b>Type</b></td><td><b>Description</b></td><td><b>Paths</b></td></tr>\r\n");
      List<String> names = new ArrayList<String>();
      for (SearchParameter t : pack.getSearchParameters())
        names.add(t.getName());
      Collections.sort(names);
      for (String name : names)  {
        SearchParameter p = null;
        for (SearchParameter t : pack.getSearchParameters())
          if (t.getName().equals(name))
            p = t;
        b.append("<tr><td>").append(p.getName()).append("</td><td><a href=\"search.html#").append(p.getType().toCode()).append("\">").append(p.getType().toCode())
                .append("</a></td><td>").append(Utilities.escapeXml(p.getDescription())).append("</td><td>").append(p.getXpath() == null ? "" : p.getXpath()).append("</td></tr>\r\n");
      }
      b.append("</table>\r\n");
      return b.toString();
    }
  }

  private String presentPaths(List<String> paths) {
    if (paths == null || paths.size() == 0)
      return "";
    if (paths.size() == 1)
      return paths.get(0);
    StringBuilder b = new StringBuilder();
    for (String p : paths) {
      if (b.length() != 0)
        b.append(", "); 
      b.append(p);
    }
    return b.toString();
  }

  private String produceExamples(ResourceDefn resource) {
    StringBuilder s = new StringBuilder();
    for (Example e: resource.getExamples()) {
        s.append("<tr><td>").append(Utilities.escapeXml(e.getDescription())).append("</td><td><a href=\"")
                .append(e.getTitle()).append(".xml\">source</a></td><td><a href=\"").append(e.getTitle()).append(".xml.html\">formatted</a></td></tr>");
    }
    return s.toString();
  }

  private String produceProfiles(ResourceDefn resource) {
    StringBuilder s = new StringBuilder();
    int count = 0;
    for (ImplementationGuideDefn ig : definitions.getSortedIgs()) {
      boolean started = false;
      for (Profile ap: resource.getConformancePackages()) {
        if (ig.getCode().equals(ap.getCategory())) {
          produceProfileLine(s, ig, started, ap);
          started = true;
          count++;
        }
      }
      for (Profile ap: definitions.getPackList()) {
        if (ig.getCode().equals(ap.getCategory()) && ap.coversResource(resource)) {
          produceProfileLine(s, ig, started, ap);
          started = true;
          count++;
        }
      }
    }
    if (count == 0)
      s.append("<tr><td colspan=\"2\">No Profiles defined for this resource</td></tr>");
    return s.toString();
  }

  private void produceProfileLine(StringBuilder s, ImplementationGuideDefn ig, boolean started, Profile ap) {
    if (!started) 
      s.append("  <tr><td colspan=\"2\"><b>"+Utilities.escapeXml(ig.getName())+"</b></td></tr>\r\n");
    s.append("  <tr>\r\n");
    String ref = (ig.isCore() ? "" : ig.getCode()+File.separator)+ap.getId().toLowerCase()+".html";
    if ("profile".equals(ap.metadata("navigation")) && ap.getProfiles().size() == 1)
      ref = (ig.isCore() ? "" : ig.getCode()+File.separator)+ap.getProfiles().get(0).getId()+".html";
    s.append("    <td><a href=\"").append(ref).append("\">").append(Utilities.escapeXml(ap.getTitle())).append("</a></td>\r\n");
    s.append("    <td>").append(Utilities.escapeXml(ap.getDescription())).append("</td>\r\n");
    s.append(" </tr>\r\n");
  }

  private String produceExampleList(ResourceDefn resource) throws Exception {
    if (resource.getName().equals("StructureDefinition")) {
      return produceStructureDefinitionExamples();
    } else {
      StringBuilder s = new StringBuilder();
      s.append("<p>Example List:</p>\r\n<table class=\"list\">\r\n");
      for (Example e: resource.getExamples()) {
        if (e.isRegistered())
          produceExampleListEntry(s, e, null, null);
      }
      for (Profile p : resource.getConformancePackages()) {
        for (Example e: p.getExamples()) {
          produceExampleListEntry(s, e, p, null);
        }        
      }
      for (Profile p : definitions.getPackList()) {
        ImplementationGuideDefn ig = definitions.getIgs().get(p.getCategory());
        for (Example e: p.getExamples()) {
          String rn = e.getResourceName();
          if (Utilities.noString(rn))
            rn = e.getXml().getDocumentElement().getNodeName();
          if (rn.equals(resource.getName()))
            produceExampleListEntry(s, e, p, ig);
        }        
      }
      s.append("<tr><td colspan=\"4\">&nbsp;</td></tr></table>\r\n");
      return s.toString();
    }
  }

  private void produceExampleListEntry(StringBuilder s, Example e, Profile pack, ImplementationGuideDefn ig) {
    String prefix = (ig == null || ig.isCore()) ? "" : ig.getCode()+File.separator; 
    if (e.getTitle().equals("conformance-base") || e.getTitle().equals("conformance-base2") || e.getTitle().equals("profiles-resources"))
      s.append("<tr><td>"+Utilities.escapeXml(e.getDescription())+"</td>");
    else
      s.append("<tr><td><a href=\""+prefix+e.getTitle()+".html\">"+Utilities.escapeXml(e.getDescription())+"</a></td>");
    s.append("<td><a href=\""+prefix+e.getTitle()+".xml.html\">XML</a></td>");
    s.append("<td><a href=\""+prefix+e.getTitle()+".json.html\">JSON</a></td>");
    if (pack == null)
      s.append("<td></td>");
    else
      s.append("<td>for Profile <a href=\""+prefix+pack.getId()+".html\">"+Utilities.escapeXml(pack.getTitle())+"</a></td>");
    s.append("</tr>");
  }

  
    
  private String produceStructureDefinitionExamples() throws Exception {
    StringBuilder s = new StringBuilder();
    
    s.append("<div id=\"tabs\">\r\n");
    s.append("<ul>\r\n");
    s.append("  <li><a href=\"#tabs-1\">Base Types</a></li>\r\n");
    s.append("  <li><a href=\"#tabs-2\">Resources</a></li>\r\n");
    s.append("  <li><a href=\"#tabs-3\">Constraints</a></li>\r\n");
    s.append("  <li><a href=\"#tabs-4\">Extensions</a></li>\r\n");
    s.append("</ul>\r\n");
    s.append("<div id=\"tabs-1\">\r\n");

    // base types
    s.append("<table class=\"list\">\r\n");
    genStructureExampleCategory(s, "Abstract Types");
    genStructureExample(s, "element.html", "element.profile", "element", "Element");
    genStructureExample(s, "backboneelement.html", "backboneelement.profile", "backboneelement", "BackBoneElement");
    genStructureExample(s, "resource.html", "resource.profile", "resource", "Resource");
    genStructureExample(s, "domainresource.html", "domainresource.profile", "domainresource", "DomainResource");
    
    genStructureExampleCategory(s, "Primitive Types");
    List<String> names = new ArrayList<String>();
    names.addAll(definitions.getPrimitives().keySet());
    Collections.sort(names);
    for (String n : names) {
      DefinedCode dc = definitions.getPrimitives().get(n);
      genStructureExample(s, "datatypes.html#"+dc.getCode(), dc.getCode().toLowerCase()+".profile", dc.getCode().toLowerCase(), dc.getCode());
    }

    genStructureExampleCategory(s, "Data Types");
    names.clear();
    names.addAll(definitions.getTypes().keySet());
    names.addAll(definitions.getStructures().keySet());
    names.addAll(definitions.getInfrastructure().keySet());
    Collections.sort(names);
    for (String n : names) {
      org.hl7.fhir.definitions.model.TypeDefn t = definitions.getTypes().get(n);
      if (t == null)
        t = definitions.getStructures().get(n);
      if (t == null)
        t = definitions.getInfrastructure().get(n);
      genStructureExample(s, getLinkFor(t.getName()), t.getName().toLowerCase()+".profile",  t.getName().toLowerCase(), t.getName());
    }
    s.append("</table>\r\n");
    
    s.append("</div>\r\n");
    s.append("<div id=\"tabs-2\">\r\n");

    s.append("<table class=\"list\">\r\n");

    genStructureExampleCategory(s, "Resources");
    for (String n : definitions.sortedResourceNames()) {
      ResourceDefn r = definitions.getResources().get(n);
      genStructureExample(s, r.getName().toLowerCase()+".html", r.getName().toLowerCase()+".profile", r.getName().toLowerCase(), r.getName());
    }
    s.append("</table>\r\n");
    
    s.append("</div>\r\n");
    s.append("<div id=\"tabs-3\">\r\n");

    s.append("<table class=\"list\">\r\n");
    Map<String, ConstraintStructure> constraints = new HashMap<String, ConstraintStructure>();
    for (Profile pp : definitions.getPackList()) { 
      for (ConstraintStructure p : pp.getProfiles())
        constraints.put(p.getId(), p);
    }
    for (String rn : definitions.sortedResourceNames()) 
      for (Profile ap: definitions.getResourceByName(rn).getConformancePackages())
        for (ConstraintStructure p : ap.getProfiles()) 
          constraints.put(p.getId(), p);
    names.clear();
    names.addAll(constraints.keySet());
    Collections.sort(names);
    for (ImplementationGuideDefn ig : definitions.getSortedIgs()) {
      boolean started = false;
      for (String n : names) {
        ConstraintStructure p = constraints.get(n);
        if (ig == p.getUsage()) {
          if (!started) {
            started = true;
            genStructureExampleCategory(s, ig.getName());            
          }
          String prefix = ig.isCore() ? "" : ig.getCode()+"/";
          genStructureExample(s, prefix+ p.getId().toLowerCase()+".html", prefix+ p.getId().toLowerCase()+".profile", p.getId().toLowerCase(), p.getTitle());
        }
      }
    }
    s.append("</table>\r\n");

    s.append("</div>\r\n");
    s.append("<div id=\"tabs-4\">\r\n");

    s.append("<table class=\"list\">\r\n");
    names.clear();
    names.addAll(workerContext.getExtensionDefinitions().keySet());
    Collections.sort(names);
    for (ImplementationGuideDefn ig : definitions.getSortedIgs()) {
      boolean started = false;
      for (String n : names) {
        StructureDefinition ed = workerContext.getExtensionDefinitions().get(n);
        if (ig.getCode().equals(ToolResourceUtilities.getUsage(ed))) {
          if (!started) {
            started = true;
            genStructureExampleCategory(s, ig.getName());            
          }
          String prefix = ig.isCore() ? "" : ig.getCode()+"/";
          genStructureExample(s, prefix+ "extension-"+ed.getId().toLowerCase()+".html", prefix+ "extension-"+ed.getId().toLowerCase(), ed.getId().toLowerCase(), ed.getUrl().startsWith("http://hl7.org/fhir/StructureDefinition/") ? ed.getUrl().substring(40) : ed.getUrl(), ed.getName());
        }
      }
    }
    s.append("</table>\r\n");
    
    s.append("</div>\r\n");
    s.append("</div>\r\n");
    s.append("\r\n");

    s.append("<script src=\"external/jquery/jquery.js\"> </script>\r\n");
    s.append("<script src=\"jquery-ui.min.js\"> </script>\r\n");
    s.append("<script>\r\n");
    s.append("try {\r\n");
    s.append("  var currentTabIndex = sessionStorage.getItem('fhir-sdelist-tab-index');\r\n");
    s.append("}\r\n");
    s.append("catch(exception){\r\n");
    s.append("}\r\n");
    s.append("\r\n");
    s.append("if (!currentTabIndex)\r\n");
    s.append("  currentTabIndex = '0';\r\n");
    s.append("  \r\n");
    s.append("$( '#tabs' ).tabs({\r\n");
    s.append("         active: currentTabIndex,\r\n");
    s.append("         activate: function( event, ui ) {\r\n");
    s.append("             var active = $('.selector').tabs('option', 'active');\r\n");
    s.append("             currentTabIndex = ui.newTab.index();\r\n");
    s.append("             document.activeElement.blur();\r\n");
    s.append("             try {\r\n");
    s.append("               sessionStorage.setItem('fhir-sdelist-tab-index', currentTabIndex);\r\n");
    s.append("             }\r\n");
    s.append("             catch(exception){\r\n");
    s.append("             }\r\n");
    s.append("         }\r\n");
    s.append("     });\r\n");
    s.append("</script>\r\n");
    s.append("\r\n");
    
    return s.toString();
  }

  private void genStructureExampleCategory(StringBuilder s, String heading) {
    s.append("<tr>");
    s.append("<td colspan=\"3\"><b>"+Utilities.escapeXml(heading)+"</b></td>");
    s.append("</tr>");

  }

  private void genStructureExample(StringBuilder s, String link, String fmtlink, String basename, String description) {
    genStructureExample(s, link, fmtlink, basename, description, null);  
  }
  
  private void genStructureExample(StringBuilder s, String link, String fmtlink, String basename, String description, String tail) {
    s.append("<tr>");
    s.append("<td><a href=\""+link+"\">"+Utilities.escapeXml(description)+"</a> "+(Utilities.noString(tail) ? "" : Utilities.escapeXml(tail))+"</td>");
    s.append("<td><a href=\""+fmtlink+ ".xml.html\">XML</a></td>");
    s.append("<td><a href=\""+fmtlink+ ".json.html\">JSON</a></td>");
    s.append("</tr>");
  }
  
  private static final String HTML_PREFIX1 = "<div xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/1999/xhtml ../../schema/fhir-xhtml.xsd\" xmlns=\"http://www.w3.org/1999/xhtml\">\r\n";
  private static final String HTML_PREFIX2 = "<div xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/1999/xhtml ../schema/fhir-xhtml.xsd\" xmlns=\"http://www.w3.org/1999/xhtml\">\r\n";
  private static final String HTML_SUFFIX = "</div>\r\n";
  
  public String loadXmlNotesFromFile(String filename, boolean checkHeaders, String definition, ResourceDefn r, List<String> tabs, ImplementationGuideDefn ig) throws Exception {
    if (!new CSFile(filename).exists()) {
      TextFile.stringToFile(HTML_PREFIX1+"\r\n<!-- content goes here -->\r\n\r\n"+HTML_SUFFIX, filename);
      return "";
    }

    String res;
    String cnt = TextFile.fileToString(filename);
    Map<String, String> others = new HashMap<String, String>();
    others.put("definition", definition);
    cnt = processPageIncludes(filename, cnt, "notes", others, null, tabs, "--", ig).trim()+"\r\n";
    if (cnt.startsWith("<div")) {
      if (!cnt.startsWith(HTML_PREFIX1) && !cnt.startsWith(HTML_PREFIX2))
        throw new Exception("unable to process start xhtml content "+filename+" : \r\n"+cnt.substring(0, HTML_PREFIX1.length())+" - should be \r\n'"+HTML_PREFIX1+"' or \r\n'"+HTML_PREFIX2+"'");
      else if (!cnt.endsWith(HTML_SUFFIX))
        throw new Exception("unable to process end xhtml content "+filename+" : "+cnt.substring(cnt.length()-HTML_SUFFIX.length()));
      else if (cnt.startsWith(HTML_PREFIX2))
        res = cnt.substring(HTML_PREFIX2.length(), cnt.length()-(HTML_SUFFIX.length()));
      else
        res = cnt.substring(HTML_PREFIX1.length(), cnt.length()-(HTML_SUFFIX.length()));
    } else {
      res = HTML_PREFIX1+cnt+HTML_SUFFIX;
      TextFile.stringToFile(res, filename);
    }
    if (checkHeaders) {
      checkFormat(filename, res, r);
      
    }
    return res;
   
  }

  private void checkFormat(String filename, String res, ResourceDefn r) throws Exception {
    XhtmlNode doc = new XhtmlParser().parse("<div>"+res+"</div>", null).getFirstElement();
    if (!doc.getFirstElement().getName().equals("div"))
      log("file \""+filename+"\": root element should be 'div'", LogMessageType.Error);
    else if (doc.getFirstElement() == null) {
      log("file \""+filename+"\": there is no 'Scope and Usage'", LogMessageType.Error);
    } else {
      XhtmlNode scope = null;
      XhtmlNode context = null;
      for (XhtmlNode x : doc.getChildNodes()) {
        if (x.getNodeType() == NodeType.Element) {
          if (!x.getName().equals("div")) {
            log("file \""+filename+"\": all child elements of the root div should be 'div's too (found '"+x.getName()+"')", LogMessageType.Error);
            return;
          } else if (x.getChildNodes().isEmpty()) {
            log("file \""+filename+"\": div/div["+Integer.toString(doc.getChildNodes().indexOf(x))+"] must have at least an h2", LogMessageType.Error);
            return;
          } else if (!x.getFirstElement().getName().equals("h2")) { 
            log("file \""+filename+"\": div/div["+Integer.toString(doc.getChildNodes().indexOf(x))+"] must start with an h2", LogMessageType.Error);
            return;
          } else {
            String s = x.getFirstElement().allText();
            if (! ((s.equals("Scope and Usage")) || (s.equals("Boundaries and Relationships")) || (s.equals("Background and Context")) ) ) {
              log("file \""+filename+"\": div/div["+Integer.toString(doc.getChildNodes().indexOf(x))+"]/h2 must be either 'Scope and Usage', 'Boundaries and Relationships', or 'Background and Context'", LogMessageType.Error);
              return;
            } else { 
              if (scope == null) {
                if (s.equals("Scope and Usage")) { 
                  scope = x;
                  if (r != null) 
                    r.setRequirements(new XhtmlComposer().composePlainText(x));
                } else {
                  log("file \""+filename+"\": 'Scope and Usage' must come first", LogMessageType.Error);
                  return;
                }
                if (s.equals("Boundaries and Relationships")) {
                  if (context != null) {
                    log("file \""+filename+"\": 'Boundaries and Relationships' must come first before 'Background and Context'", LogMessageType.Error);
                    return;
                  }
                }
                  
                if (s.equals("Background and Context"))
                  context = x;
              }
            }
            boolean found = false;
            for (XhtmlNode n : x.getChildNodes()) {
              if (!found)
                found = n == x.getFirstElement();
              else {
                if ("h1".equals(n.getName()) || "h2".equals(n.getName())) {
                  log("file \""+filename+"\": content of a <div> inner section cannot contain h1 or h2 headings", LogMessageType.Error);
                  return;
                }
              }
            }
          }
        }
      }
    }
    List<String> allowed = Arrays.asList("div", "h2", "h3", "h4", "h5", "i", "b", "code", "pre", "blockquote", "p", "a", "img", "table", "tr", "th", "td", "ol", "ul", "li", "br");
    iterateAllChildNodes(doc, allowed);
  }
  
  private boolean iterateAllChildNodes(XhtmlNode node, List<String> allowed) {
    for (XhtmlNode n : node.getChildNodes()) {
      if (n.getNodeType() == NodeType.Element) {
        if (!allowed.contains(n.getName())) {
          log("Markup uses non permitted name "+n.getName(), LogMessageType.Error);
          return false;
        }
        if (!iterateAllChildNodes(n, allowed))
          return false;
      }
    }
    return true;
  }

  private String loadXmlNotes(String name, String suffix, boolean checkHeaders, String definition, ResourceDefn resource, List<String> tabs, ImplementationGuideDefn ig) throws Exception {
    String filename;
    if (definitions.hasLogicalModel(name)) {
      LogicalModel lm = definitions.getLogicalModel(name);
      filename = Utilities.changeFileExt(lm.getSource(), "-"+suffix+".xml");
    } else
      filename = folders.srcDir + name+File.separatorChar+name+"-"+suffix+".xml";
    return loadXmlNotesFromFile(filename, checkHeaders, definition, resource, tabs, ig);
  }

  public String processProfileIncludes(String filename, String fileid, Profile pack, ConstraintStructure profile, String xml, String json, String tx, String src, String master, String path, String intro, String notes, ImplementationGuideDefn ig) throws Exception {
    String workingTitle = null;

    int level = (ig == null || ig.isCore()) ? 0 : 1;
    
    while (src.contains("<%") || src.contains("[%"))
    {
      int i1 = src.indexOf("<%");
      int i2 = src.indexOf("%>");
      if (i1 == -1) {
        i1 = src.indexOf("[%");
        i2 = src.indexOf("%]");
      }
      String s1 = src.substring(0, i1);
      String s2 = src.substring(i1 + 2, i2).trim();
      String s3 = src.substring(i2+2);

      String[] com = s2.split(" ");
      if (com[0].equals("sidebar"))
        src = s1+generateSideBar(com.length > 1 ? com[1] : "")+s3;
      else if (com[0].equals("profileheader"))
        src = s1+profileHeader(fileid, com.length > 1 ? com[1] : "")+s3;
      else if (com[0].equals("file"))
        src = s1+TextFile.fileToString(folders.srcDir + com[1]+".html")+s3;
      else if (com[0].equals("settitle")) {
        workingTitle = s2.substring(9).replace("{", "<%").replace("}", "%>");
        src = s1+s3;
      }      else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing resource "+filename);
      else if (com[0].equals("pageheader"))
        src = s1+pageHeader(pack.metadata("name"))+s3;
      else if (com[0].equals("level"))
        src = s1 + genlevel(level) + s3;  
      else if (com[0].equals("header"))
        src = s1+TextFile.fileToString(folders.srcDir + "header.html")+s3;
      else if (com[0].equals("newheader"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader.html")+s3;
      else if (com[0].equals("newheader1"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader1.html")+s3;
      else if (com[0].equals("footer"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer.html")+s3;
      else if (com[0].equals("newfooter"))
        src = s1+TextFile.fileToString(folders.srcDir + "newfooter.html")+s3;
      else if (com[0].equals("footer1"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer1.html")+s3;
      else if (com[0].equals("footer2"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer2.html")+s3;
      else if (com[0].equals("footer3"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer3.html")+s3;
      else if (com[0].equals("title"))
        src = s1+(workingTitle == null ? Utilities.escapeXml("StructureDefinition: "+profile.getTitle()) : workingTitle)+s3;
      else if (com[0].equals("xtitle"))
        src = s1+(workingTitle == null ? Utilities.escapeXml("StructureDefinition: "+profile.getTitle()) : Utilities.escapeXml(workingTitle))+s3;
      else if (com[0].equals("profiletitle"))
        src = s1+Utilities.escapeXml(pack.metadata("name"))+s3;
      else if (com[0].equals("filetitle"))
        src = s1+(filename.contains(".") ? filename.substring(0, filename.lastIndexOf(".")) : filename)+s3;
      else if (com[0].equals("name"))
        src = s1+filename+s3;
      else if (com[0].equals("date")) {
        if (!Utilities.noString(pack.metadata("date"))) {
          Date d = new SimpleDateFormat("yyyy-MM-dd").parse(pack.metadata("date"));
          src = s1+Config.DATE_FORMAT().format(d)+s3;
        }
        else
          src = s1+"[no date]"+s3;
      } else if (com[0].equals("version"))
        src = s1+ini.getStringProperty("FHIR", "version")+s3;
      else if (com[0].equals("gendate"))
        src = s1+Config.DATE_FORMAT().format(new Date())+s3;
      else if (com[0].equals("definition"))
        src = s1+Utilities.escapeXml(pack.metadata("description"))+s3;
      else if (com[0].equals("status"))
        src = s1+describeStatus(pack.metadata("status"))+s3;
      else if (com[0].equals("author"))
        src = s1+Utilities.escapeXml(pack.metadata("author.name"))+s3;
      else if (com[0].equals("xml"))
        src = s1+xml+s3;
      else if (com[0].equals("json"))
        src = s1+json+s3;
      else if (com[0].equals("profiledesc")) {
        src = s1+Utilities.escapeXml(profile.getResource().getDescription())+s3;
      } else if (com[0].equals("tx"))
        src = s1+tx+s3;
      else if (com[0].equals("inv"))
        src = s1+genProfileConstraints(profile.getResource())+s3;      
      else if (com[0].equals("plural"))
        src = s1+Utilities.pluralizeMe(filename)+s3;
      else if (com[0].equals("notes"))
        src = s1+"todo" /*Utilities.fileToString(folders.srcDir + filename+File.separatorChar+filename+".html")*/ +s3;
      else if (com[0].equals("dictionary"))
        src = s1+"todo"+s3;
      else if (com[0].equals("breadcrumb"))
        src = s1 + breadCrumbManager.make(filename) + s3;
      else if (com[0].equals("navlist"))
        src = s1 + breadCrumbManager.navlist(filename, genlevel(level)) + s3;
      else if (com[0].equals("breadcrumblist"))
        src = s1 + ((ig == null || ig.isCore()) ? breadCrumbManager.makelist(filename, "profile:"+path, genlevel(0), profile.getResource().getName()) : ig.makeList(filename, "profile:"+path, genlevel(level), profile.getResource().getName())) + s3;      
      else if (com[0].equals("year"))
        src = s1 + new SimpleDateFormat("yyyy").format(new Date()) + s3;      
      else if (com[0].equals("revision"))
        src = s1 + svnRevision + s3;      
      else if (com[0].equals("level"))
        src = s1 + genlevel(0) + s3;  
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;      
      else if (com[0].equals("pub-notice"))
        src = s1 + publicationNotice + s3;
      else if (com[0].equals("profileurl"))
        src = s1 + profile.getResource().getUrl() + s3;  
      else if (com[0].equals("baseURL"))
        src = s1 + Utilities.URLEncode(baseURL) + s3;  
      else if (com[0].equals("base-link"))
        src = s1 + baseLink(profile.getResource(), genlevel(level)) + s3;  
      else if (com[0].equals("profile-structure-table-diff"))
        src = s1 + generateProfileStructureTable(profile, true, filename, pack.getId(), genlevel(level)) + s3;      
      else if (com[0].equals("profile-structure-table"))
        src = s1 + generateProfileStructureTable(profile, false, filename, pack.getId(), genlevel(level)) + s3;      
      else if (com[0].equals("maponthispage"))
        src = s1+mapOnPageProfile(profile.getResource())+s3;
      else if (com[0].equals("mappings"))
        src = s1+mappingsProfile(profile.getResource())+s3;
      else if (com[0].equals("definitions"))
        src = s1+definitionsProfile(profile.getResource(), genlevel(level))+s3;
      else if (com[0].equals("profile.review"))
        src = s1+profileReviewLink(profile)+s3;
      else if (com[0].equals("profile.datadictionary"))
        src = s1+profileDictionaryLink(profile)+s3;      
      else if (com[0].equals("profile.tx"))
        src = s1+getTerminologyNotes(profile.getResource(), level)+s3;
      else if (com[0].equals("profile.inv"))
        src = s1+getInvariantList(profile.getResource())+s3;
      else if (com[0].equals("pagepath"))
        src = s1+filename+s3;
      else if (com[0].equals("rellink"))
        src = s1+filename+s3;
      else if (com[0].equals("summary"))
        src = s1+generateHumanSummary(pack, profile.getResource(), genlevel(level))+s3;
      else if (com[0].equals("profile-examples"))
        src = s1+s3;      
      else if (com[0].equals("profile-extensions-table"))
        src = s1+"<p><i>Todo</i></p>"+s3;
      else if (com[0].equals("definitionsonthispage"))
        src = s1+"<p><i>Todo</i></p>"+s3;
      else if (com[0].equals("profile.intro"))
        src = s1 +genProfileDoco(pack, intro)+ s3;
      else if (com[0].equals("profile.notes"))
        src = s1 +genProfileDoco(pack, notes)+ s3;
      else if (com[0].startsWith("!"))
        src = s1 + s3;  
      else if (com[0].equals("resurl")) {
         if (Utilities.noString(pack.metadata("id")))
           src = s1+s3;
         else
           src = s1+"The id of this profile is "+pack.metadata("id")+s3;
      } else 
        throw new Exception("Instruction <%"+s2+"%> not understood parsing resource "+filename);
    }
    return src;
  }

  private String genProfileDoco(Profile ap, String doco) {
    if ("profile".equals(ap.metadata("navigation")) && ap.getProfiles().size() == 1)
      return doco;
    else
      return "";
  }

  private String profileDictionaryLink(ConstraintStructure profile) {
    String uri = ToolingExtensions.readStringExtension(profile.getResource(), "http://hl7.org/fhir/StructureDefinition/datadictionary");
    if (Utilities.noString(uri))
      return "<!-- no uri -->";
    Dictionary dict = definitions.getDictionaries().get(uri);
    if (dict == null)
      return "<p>This profile specifies that the value of the "+profile.getResource().getSnapshot().getElement().get(0).getPath()+
          " resource must be a valid Observation as defined in the data dictionary (Unknown? - "+uri+").</p>";
    else
      return "<p>This profile specifies that the value of the "+profile.getResource().getSnapshot().getElement().get(0).getPath()+
          " resource must be a valid Observation as defined in the data dictionary <a href=\""+uri+".html\">"+dict.getName()+"</a>.</p>";
  }

  private String generateHumanSummary(Profile pack, StructureDefinition profile, String prefix) {
    try {
      if (profile.getDifferential() == null)
        return "<p>No Summary, as this profile has no differential</p>";

      // references
      List<String> refs = new ArrayList<String>(); // profile references
      // extensions (modifier extensions)
      List<String> ext = new ArrayList<String>(); // extensions
      // slices
      List<String> slices = new ArrayList<String>(); // Fixed Values 
      // numbers - must support, required, prohibited, fixed
      int supports = 0;
      int requireds = 0;
      int fixeds = 0;
      int prohibits = 0;
  
      for (ElementDefinition ed : profile.getDifferential().getElement()) {
        if (ed.getPath().contains(".")) {
          if (ed.getMin() == 1)
            requireds++;
          if ("0".equals(ed.getMax()))
            prohibits++;
          if (ed.getMustSupport())
            supports++;
          if (ed.hasFixed())
            fixeds++;
          
          for (TypeRefComponent t : ed.getType()) {
            if (t.hasProfile() && !definitions.hasType(t.getProfile().get(0).getValue().substring(40))) {
              if (ed.getPath().endsWith(".extension"))
                tryAdd(ext, summariseExtension(t.getProfile().get(0).getValue(), false, prefix));
              else if (ed.getPath().endsWith(".modifierExtension"))
                tryAdd(ext, summariseExtension(t.getProfile().get(0).getValue(), true, prefix));
              else
                tryAdd(refs, describeProfile(t.getProfile().get(0).getValue(), prefix));
            }
          }

          if (ed.hasSlicing() && !ed.getPath().endsWith(".extension") && !ed.getPath().endsWith(".modifierExtension"))
            tryAdd(slices, describeSlice(ed.getPath(), ed.getSlicing()));
        }
      }
      StringBuilder res = new StringBuilder("<a name=\"summary\"> </a>\r\n<p><b>\r\nSummary\r\n</b></p>\r\n");
      if (supports + requireds + fixeds + prohibits > 0) {
        boolean started = false;
        res.append("<p>");
        if (requireds > 0) {
          started = true;
          res.append("Mandatory: "+Integer.toString(requireds)+" "+(requireds > 1 ? Utilities.pluralizeMe("element") : "element")); 
        }
        if (supports > 0) {
          if (started)
            res.append(", ");
          started = true;
          res.append("Must-Support: "+Integer.toString(supports)+" "+(supports > 1 ? Utilities.pluralizeMe("element") : "element")); 
        }
        if (fixeds > 0) {
          if (started)
            res.append(", ");
          started = true;
          res.append("Fixed Value: "+Integer.toString(fixeds)+" "+(fixeds > 1 ? Utilities.pluralizeMe("element") : "element")); 
        }
        if (prohibits > 0) {
          if (started)
            res.append(", ");
          started = true;
          res.append("Prohibited: "+Integer.toString(prohibits)+" "+(prohibits > 1 ? Utilities.pluralizeMe("element") : "element")); 
        }
        res.append("</p>");        
      }
      if (!refs.isEmpty()) {
        res.append("<p><b>Structures</b></p>\r\n<p>This structure refers to these other structures:</p>\r\n<ul>\r\n");
        for (String s : refs)
          res.append(s);
        res.append("\r\n</ul>\r\n\r\n");
      }
      if (!ext.isEmpty()) {
        res.append("<p><b>Extensions</b></p>\r\n<p>This structure refers to these extensions:</p>\r\n<ul>\r\n");
        for (String s : ext)
          res.append(s);
        res.append("\r\n</ul>\r\n\r\n");
      }
      if (!slices.isEmpty()) {
        res.append("<p><b>Slices</b></p>\r\n<p>This structure defines the following <a href=\""+prefix+"profiling.html#slices\">Slices</a>:</p>\r\n<ul>\r\n");
        for (String s : slices)
          res.append(s);
        res.append("\r\n</ul>\r\n\r\n");
      }
      return res.toString();
    } catch (Exception e) {
      return "<p><i>"+Utilities.escapeXml(e.getMessage())+"</i></p>";
    }
  }

  private String describeSlice(String path, ElementDefinitionSlicingComponent slicing) {
    if (!slicing.hasDiscriminator())
      return "<li>There is a slice with no discriminator at "+path+"</li>\r\n";
    String s = "";
    if (slicing.getOrdered())
      s = "ordered";
    if (slicing.getRules() != SlicingRules.OPEN)
      s = Utilities.noString(s) ? slicing.getRules().getDisplay() : s+", "+ slicing.getRules().getDisplay();
    if (!Utilities.noString(s))
      s = " ("+s+")";
    if (slicing.getDiscriminator().size() == 1)
      return "<li>The element "+path+" is sliced based on the value of "+slicing.getDiscriminator().get(0).asStringValue()+s+"</li>\r\n";
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (StringType d : slicing.getDiscriminator()) 
      b.append(d.asStringValue());
    return "<li>The element "+path+" is sliced based on the values of "+slicing.getDiscriminator().get(0).asStringValue()+s+"</li>\r\n";
  }

  private void tryAdd(List<String> ext, String s) {
    if (!Utilities.noString(s) && !ext.contains(s))
      ext.add(s);
  }

  private String summariseExtension(String url, boolean modifier, String prefix) throws Exception {
    StructureDefinition ed = workerContext.getExtensionStructure(null, url);
    if (ed == null)
      return "<li>unable to summarise extension "+url+" (no extension found)</li>";
    if (ed.getUserData("path") == null)
      return "<li><a href=\""+prefix+"extension-"+ed.getId().toLowerCase()+".html\">"+url+"</a>"+(modifier ? " (<b>Modifier</b>) " : "")+"</li>\r\n";    
    else
      return "<li><a href=\""+prefix+ed.getUserString("path")+"\">"+url+"</a>"+(modifier ? " (<b>Modifier</b>) " : "")+"</li>\r\n";    
  }

  private String describeProfile(String url, String prefix) throws Exception {
    if (url.startsWith("http://hl7.org/fhir/StructureDefinition/") && (definitions.hasType(url.substring(40)) || definitions.hasResource(url.substring(40)) || "Resource".equals(url.substring(40))))
      return null;
    
    StructureDefinition ed = workerContext.getProfiles().get(url);
    if (ed == null) {
      // work around for case consistency problems in ballot package
      
      for (String s : workerContext.getProfiles().keySet())
        if (s.equalsIgnoreCase(url)) {
          ed = workerContext.getProfiles().get(s);
        }
    }
    if (ed == null)
      return "<li>unable to summarise profile "+url+" (no profile found)</li>";
    return "<li><a href=\""+prefix+ed.getUserString("path")+"\">"+url+"</a></li>\r\n";    
  }

  private String describeReference(ElementDefinitionBindingComponent binding) {
    if (binding.getValueSet() instanceof UriType) {
      UriType uri = (UriType) binding.getValueSet();
      return "<a href=\""+uri.asStringValue()+"\">"+uri.asStringValue()+"</a>";
    } if (binding.getValueSet() instanceof Reference) {
      Reference ref = (Reference) binding.getValueSet();
      String disp = ref.getDisplay();
      ValueSet vs = workerContext.getValueSets().get(ref.getReference());
      if (disp == null && vs != null)
        disp = vs.getName();
      return "<a href=\""+(vs == null ? ref.getReference() : vs.getUserData("filename"))+"\">"+disp+"</a>";
    }
    else
      return "??";
  }

  private String summariseValue(Type fixed) throws Exception {
    if (fixed instanceof org.hl7.fhir.instance.model.PrimitiveType)
      return ((org.hl7.fhir.instance.model.PrimitiveType) fixed).asStringValue();
    if (fixed instanceof CodeableConcept) 
      return summarise((CodeableConcept) fixed);
    if (fixed instanceof Quantity) 
      return summarise((Quantity) fixed);
    throw new Exception("Generating text summary of fixed value not yet done for type "+fixed.getClass().getName());
  }

  private String summarise(Quantity quantity) {
    String cu = "";
    if ("http://unitsofmeasure.org/".equals(quantity.getSystem()))
      cu = " (UCUM: "+quantity.getCode()+")";
    if ("http://snomed.info/sct".equals(quantity.getSystem()))
      cu = " (SNOMED CT: "+quantity.getCode()+")";
    return quantity.getValue().toString()+quantity.getUnit()+cu;
  }

  private String summarise(CodeableConcept cc) throws Exception {
    if (cc.getCoding().size() == 1 && cc.getText() == null) {
      return summarise(cc.getCoding().get(0));
    } else if (cc.getCoding().size() == 0 && cc.hasText()) {
      return "\"" + cc.getText()+"\"";
    } else 
      throw new Exception("too complex to describe");
  }

  private String summarise(Coding coding) throws Exception {
    if ("http://snomed.info/sct".equals(coding.getSystem()))
      return "SNOMED CT code "+coding.getCode()+ (coding.getDisplay() == null ? "" : "(\""+coding.getDisplay()+"\")");
    if ("http://loinc.org".equals(coding.getSystem()))
      return "LOINC code "+coding.getCode()+ (coding.getDisplay() == null ? "" : "(\""+coding.getDisplay()+"\")");
    if (workerContext.getCodeSystems().containsKey(coding.getSystem())) {
      ValueSet vs = workerContext.getCodeSystems().get(coding.getSystem());
      return "<a href=\""+vs.getUserData("filename")+"#"+coding.getCode()+"\">"+coding.getCode()+"</a>"+(coding.getDisplay() == null ? "" : "(\""+coding.getDisplay()+"\")");
    }
    throw new Exception("Unknown system "+coding.getSystem()+" generating fixed value description");
  }

  private String root(String path) {
    return path.contains(".") ? path.substring(0, path.lastIndexOf('.')) : path;
  }

  public String processExtensionIncludes(String filename, StructureDefinition ed, String xml, String json, String tx, String src, String pagePath, ImplementationGuideDefn ig) throws Exception {
    String workingTitle = null;
    int level = ig.isCore() ? 0 : 1;

    while (src.contains("<%") || src.contains("[%"))
    {
      int i1 = src.indexOf("<%");
      int i2 = src.indexOf("%>");
      if (i1 == -1) {
        i1 = src.indexOf("[%");
        i2 = src.indexOf("%]");
      }
      String s1 = src.substring(0, i1);
      String s2 = src.substring(i1 + 2, i2).trim();
      String s3 = src.substring(i2+2);

      String[] com = s2.split(" ");
      if (com[0].equals("sidebar"))
        src = s1+generateSideBar(com.length > 1 ? com[1] : "")+s3;
      else if (com[0].equals("file"))
        src = s1+TextFile.fileToString(folders.srcDir + com[1]+".html")+s3;
      else if (com[0].equals("extDefnHeader"))
        src = s1+extDefnHeader(filename, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("extension-table"))
        src = s1+generateExtensionTable(ed, filename, com[1], genlevel(level))+s3;
      else if (com[0].equals("settitle")) {
        workingTitle = s2.substring(9).replace("{", "<%").replace("}", "%>");
        src = s1+s3;
      }      else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing resource "+filename);
      else if (com[0].equals("pageheader"))
        src = s1+pageHeader(ed.getName())+s3;
      else if (com[0].equals("header"))
        src = s1+TextFile.fileToString(folders.srcDir + "header.html")+s3;
      else if (com[0].equals("newheader"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader.html")+s3;
      else if (com[0].equals("newheader1"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader1.html")+s3;
      else if (com[0].equals("footer"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer.html")+s3;
      else if (com[0].equals("newfooter"))
        src = s1+TextFile.fileToString(folders.srcDir + "newfooter.html")+s3;
      else if (com[0].equals("footer1"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer1.html")+s3;
      else if (com[0].equals("footer2"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer2.html")+s3;
      else if (com[0].equals("footer3"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer3.html")+s3;
      else if (com[0].equals("title"))
        src = s1+(workingTitle == null ? Utilities.escapeXml(ed.getName()) : workingTitle)+s3;
      else if (com[0].equals("xtitle"))
        src = s1+"Extension: "+Utilities.escapeXml(ed.getName())+s3;
      else if (com[0].equals("filetitle"))
        src = s1+(filename.contains(".") ? filename.substring(0, filename.lastIndexOf(".")) : filename)+s3;
      else if (com[0].equals("name"))
        src = s1+filename+s3;
      else if (com[0].equals("date")) {
        if (ed.hasDate())
          src = s1+ed.getDateElement().toHumanDisplay()+s3;
        else
          src = s1+"[no date]"+s3;
      } else if (com[0].equals("version"))
        src = s1+ini.getStringProperty("FHIR", "version")+s3;
      else if (com[0].equals("gendate"))
        src = s1+Config.DATE_FORMAT().format(new Date())+s3;
      else if (com[0].equals("definition"))
        src = s1+Processor.process(Utilities.escapeXml(ed.getDescription()))+s3;
      else if (com[0].equals("status"))
        src = s1+(ed.getStatus() == null ? "??" : ed.getStatus().toCode())+s3;
      else if (com[0].equals("author"))
        src = s1+Utilities.escapeXml(ed.getPublisher())+s3;
      else if (com[0].equals("xml"))
        src = s1+xml+s3;
      else if (com[0].equals("json"))
        src = s1+json+s3;
      else if (com[0].equals("tx"))
        src = s1+tx+s3;
      else if (com[0].equals("inv"))
        src = s1+genExtensionConstraints(ed)+s3;      
      else if (com[0].equals("plural"))
        src = s1+Utilities.pluralizeMe(filename)+s3;
      else if (com[0].equals("notes"))
        src = s1+"todo" /*Utilities.fileToString(folders.srcDir + filename+File.separatorChar+filename+".html")*/ +s3;
      else if (com[0].equals("dictionary"))
        src = s1+definitionsProfile(ed, genlevel(level))+s3;
      else if (com[0].equals("breadcrumb"))
        src = s1 + breadCrumbManager.make(filename) + s3;
      else if (com[0].equals("navlist"))
        src = s1 + breadCrumbManager.navlist(filename, genlevel(level)) + s3;
      else if (com[0].equals("breadcrumblist")) {
        String crumbTitle = (workingTitle == null ? Utilities.escapeXml(ed.getName()) : workingTitle);
        src = s1 + ((ig == null || ig.isCore()) ? breadCrumbManager.makelist(filename, "extension:"+ed.getName(), genlevel(level), crumbTitle) : ig.makeList(filename, "extension:"+ed.getName(), genlevel(level), crumbTitle))+ s3;      
      } else if (com[0].equals("year"))
        src = s1 + new SimpleDateFormat("yyyy").format(new Date()) + s3;      
      else if (com[0].equals("revision"))
        src = s1 + svnRevision + s3;      
      else if (com[0].equals("level"))
        src = s1 + genlevel(level) + s3;  
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;      
      else if (com[0].equals("pub-notice"))
        src = s1 + publicationNotice + s3;
      else if (com[0].equals("pagepath"))
        src = s1 + pagePath + s3;  
      else if (com[0].equals("extensionurl"))
        src = s1 + ed.getUrl() + s3;  
      else if (com[0].equals("rellink"))
        src = s1 + Utilities.URLEncode(pagePath) + s3;  
      else if (com[0].equals("baseURL"))
        src = s1 + Utilities.URLEncode(baseURL) + s3;  
      else if (com[0].equals("mappings"))
        src = s1+mappingsExtension(ed)+s3;
      else if (com[0].equals("definitions"))
        src = s1+definitionsExtension(ed, "")+s3;
      else if (com[0].equals("pubdetails")) {
        src = s1+"Extension maintained by: " +Utilities.escapeXml(ed.getPublisher())+s3;
      } else if (com[0].equals("extref"))
        src = s1+"<p>usage info: insert a list of places where this extension is used</p>"+s3;
      else if (com[0].equals("context-info"))
        src = s1+describeExtensionContext(ed)+s3;
      else if (com[0].equals("ext-name"))
        src = s1+Utilities.escapeXml(ed.getName())+s3;
      else if (com[0].startsWith("!"))
        src = s1 + s3;  
      else 
        throw new Exception("Instruction <%"+s2+"%> not understood parsing resource "+filename);
    }
    return src;
  }

  private String describeExtensionContext(StructureDefinition ed) {
    return "<p>Context of Use: "+ProfileUtilities.describeExtensionContext(ed)+"</p>";
  }

  private String generateExtensionTable(StructureDefinition ed, String filename, String full, String prefix) throws Exception {
    return new XhtmlComposer().compose(new ProfileUtilities(workerContext).generateExtensionTable(filename, ed, folders.dstDir, false, this, full.equals("true"), prefix));
  }


  private String getTerminologyNotes(StructureDefinition profile, int level) {
    List<String> txlist = new ArrayList<String>();
    Map<String, ElementDefinitionBindingComponent> txmap = new HashMap<String, ElementDefinitionBindingComponent>();
    for (ElementDefinition ed : profile.getSnapshot().getElement()) {
      if (ed.hasBinding() && !"0".equals(ed.getMax())) {
        String path = ed.getPath();
        if (ed.getType().size() == 1 && ed.getType().get(0).getCode().equals("Extension"))
          path = path + "<br/>"+ed.getType().get(0).getProfile();
        txlist.add(path);
        txmap.put(path, ed.getBinding());
      }
    }
    if (txlist.isEmpty())
      return "";
    else {
      StringBuilder b = new StringBuilder();
      b.append("<h4>Terminology Bindings</h4>\r\n");       
      b.append("<table class=\"list\">\r\n");
      b.append("<tr><td><b>Path</b></td><td><b>Name</b></td><td><b>Conformance</b></td><td><b>ValueSet</b></td></tr>\r\n");
      for (String path : txlist)  {
        ElementDefinitionBindingComponent tx = txmap.get(path);
        String vss = "";
        String vsn = "?ext";
        if (tx.hasValueSet()) {
          if (tx.getValueSet() instanceof UriType) {
            vss = "<a href=\""+((UriType)tx.getValueSet()).asStringValue()+"\">"+Utilities.escapeXml(((UriType)tx.getValueSet()).asStringValue())+"</a>";
          } else {
            String uri = ((Reference)tx.getValueSet()).getReference();
            ValueSet vs = valueSets.get(uri);
            if (vs == null)
              vss = "<a href=\""+genlevel(level)+uri+"\">"+Utilities.escapeXml(uri)+"</a>";
            else { 
              vss = "<a href=\""+genlevel(level)+vs.getUserData("path")+"\">"+Utilities.escapeXml(vs.getName())+"</a>";
              vsn = vs.getName();
            }
          }
        }
        b.append("<tr><td>").append(path).append("</td><td>").append(Utilities.escapeXml(vsn)).append("</td><td><a href=\"").
                  append(genlevel(level)).append("terminologies.html#").append(tx.getStrength().toCode()).append("\">").append(tx.getStrength().toCode()).append("</a></td><td>").append(vss).append("</td></tr>\r\n");
      }
      b.append("</table>\r\n");
      return b.toString();
      
    }
  }

  private String getInvariantList(StructureDefinition profile) {
    List<String> txlist = new ArrayList<String>();
    Map<String, List<ElementDefinitionConstraintComponent>> txmap = new HashMap<String, List<ElementDefinitionConstraintComponent>>();
    for (ElementDefinition ed : profile.getSnapshot().getElement()) {
      if (!"0".equals(ed.getMax())) {
        txlist.add(ed.getPath());
        txmap.put(ed.getPath(), ed.getConstraint());
      }
    }
    if (txlist.isEmpty())
      return "";
    else {
      StringBuilder b = new StringBuilder();
      b.append("<h4>Constraints</h4>\r\n");       
      b.append("<table class=\"list\">\r\n");
      b.append("<tr><td width=\"60\"><b>Id</b></td><td><b>Path</b></td><td><b>Details</b></td><td><b>Requirements</b></td></tr>\r\n");
      for (String path : txlist)  {
        List<ElementDefinitionConstraintComponent> invs = txmap.get(path);
        for (ElementDefinitionConstraintComponent inv : invs) {
          b.append("<tr><td>").append(inv.getKey()).append("</td><td>").append(path).append("</td><td>").append(Utilities.escapeXml(inv.getHuman()))
           .append("<br/>XPath: ").append(Utilities.escapeXml(inv.getXpath())).append("</td><td>").append(Utilities.escapeXml(inv.getRequirements())).append("</td></tr>\r\n");
        }
      }
      b.append("</table>\r\n");
      return b.toString();
      
    }
  }

  private String profileReviewLink(ConstraintStructure profile) {
    if (!profile.getUsage().isReview())
      return "";
    String s = Utilities.changeFileExt((String) profile.getResource().getUserData("filename"), "-review.xls");
    return "Use the <a href=\""+s+"\">Review Spreadsheet</a> to comment on this profile."; 
  }

  /*
  private String profileExampleList(ProfileDefn profile, Map<String, Example> examples, String example) {
    if (examples == null || examples.isEmpty())
      return "<p>No Examples Provided.</p>";
    else if (examples.size() == 1)
      return example;
    else{
      StringBuilder s = new StringBuilder();

      boolean started = false;
      List<String> names = new ArrayList<String>();
      names.addAll(examples.keySet());
      Collections.sort(names);
      for (String n : names) {
        Example e = examples.get(n);
        if (!started)
          s.append("<p>Example Index:</p>\r\n<table class=\"list\">\r\n");
        started = true;
        if (e.getFileTitle().equals("conformance-base") || e.getFileTitle().equals("conformance-base2") || e.getFileTitle().equals("profiles-resources"))
          s.append("<tr><td>"+Utilities.escapeXml(e.getDescription())+"</td>");
        else
          s.append("<tr><td><a href=\""+e.getFileTitle()+".html\">"+Utilities.escapeXml(e.getDescription())+"</a></td>");
        s.append("<td><a href=\""+e.getFileTitle()+".xml.html\">XML</a></td>");
        s.append("<td><a href=\""+e.getFileTitle()+".json.html\">JSON</a></td>");
        s.append("</tr>");
      }

      //  }
      if (started)
        s.append("</table>\r\n");
      return s.toString();
    }
  }
  */
  
  private String mappingsProfile(StructureDefinition source) {
    MappingsGenerator m = new MappingsGenerator(definitions);
    m.generate(source);
    return m.getMappings();
  }

  private String mappingsExtension(StructureDefinition ed) {
    MappingsGenerator m = new MappingsGenerator(definitions);
    m.generate(ed);
    return m.getMappings();
  }

  private String definitionsProfile(StructureDefinition source, String prefix) throws Exception {
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    DictHTMLGenerator d = new DictHTMLGenerator(b, this, prefix);
    d.generate(source);
    d.close();
    return b.toString();
  }

  private String definitionsExtension(StructureDefinition ed, String prefix) throws Exception {
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    DictHTMLGenerator d = new DictHTMLGenerator(b, this, prefix);
    d.generate(ed);
    d.close();
    return b.toString();
  }

  private String mapOnPageProfile(StructureDefinition source) {
    if (source.getMapping().size() < 2)
      return "";
    StringBuilder b = new StringBuilder();
    b.append("<div class=\"itoc\">\r\n<p>Mappings:</p>\r\n");
    for (StructureDefinitionMappingComponent map : source.getMapping()) {
      b.append("<p class=\"link\"><a href=\"#").append(map.getIdentity()).append("\">").append(map.getName()).append("</a></p>");
    }
    b.append("</div>\r\n");
    return b.toString();
  }

  private String baseLink(StructureDefinition structure, String prefix) throws Exception {
    if (structure.getBase().startsWith("http://hl7.org/fhir/StructureDefinition/")) {
      String name = structure.getBase().substring(40);
      if (definitions.hasResource(name))
        return "<a href=\""+prefix+name.toLowerCase()+".html\">"+name+"</a>";
      else if (definitions.hasElementDefn(name))
        return "<a href=\""+prefix+definitions.getSrcFile(name)+".html#"+name+"\">"+name+"</a>";  
      else {
        StructureDefinition p = definitions.getSnapShotForBase(structure.getBase());
        if (p == null)
          return "??"+name;
        return "<a href=\""+prefix+name+".html\">"+name+"</a>";  
      }
    } else {
      String[] parts = structure.getBase().split("#");
      StructureDefinition profile = new ProfileUtilities(workerContext).getProfile(structure, parts[0]);
      if (profile != null) {
        if (parts.length == 2) {
          return "<a href=\""+prefix+profile.getUserData("filename")+"."+parts[1]+".html\">the structure "+parts[1]+"</a> in <a href=\""+profile.getUserData("filename")+".html\">the "+profile.getName()+" profile</a>";
        } else {
          return "<a href=\""+prefix+profile.getUserData("filename")+".html\">the "+profile.getName()+" profile</a>";
        }
      } else
        return "<a href=\""+structure.getBase()+"\">"+structure.getBase()+"</a>";
    }
  }

  private String generateProfileStructureTable(ConstraintStructure profile, boolean diff, String filename, String baseName, String prefix) throws Exception {
    String fn = filename.contains(".") ? filename.substring(0, filename.indexOf('.')) : filename;
    String deffile = fn+"-definitions.html";
    return new XhtmlComposer().compose(new ProfileUtilities(workerContext).generateTable(deffile, profile.getResource(), diff, folders.dstDir, false, this, baseName, !diff, prefix));
  }

  private boolean isAggregationEndpoint(String name) {
    return definitions.getAggregationEndpoints().contains(name.toLowerCase());
  }   

  private String makeArchives() throws Exception {
    IniFile ini = new IniFile(folders.rootDir+"publish.ini"); 
    StringBuilder s = new StringBuilder();
    s.append("<h2>Archived Versions of FHIR</h2>");
    s.append("<p>These archives only keep the more significant past versions of FHIR, and only the book form, and are provided for purposes of supporting html diff tools. A full archive history of everything is available <a href=\"http://wiki.hl7.org/index.php?title=FHIR\">through the HL7 gForge archives</a>.</p>");
    s.append("<ul>");
    if (ini.getPropertyNames("Archives") != null) {
      for (String v : ini.getPropertyNames("Archives")) {
        s.append("<li><a href=\"http://www.hl7.org/implement/standards/FHIR/v").append(v).append("/index.htm\">Version ").append(v).append("</a>, ")
                .append(ini.getStringProperty("Archives", v)).append("</li>");
        if (!definitions.getPastVersions().contains(v))
          definitions.getPastVersions().add(v);
      }
    }
    s.append("</ul>");
    return s.toString();
  }


  private String describeStatus(String s) {
    if (s.equals("draft"))
      return "as a draft";
    if (s.equals("testing"))
      return "for testing";
    if (s.equals("production"))
      return "for production use";
    if (s.equals("withdrawn"))
      return "as withdrawn from use";
    if (s.equals("superceded"))
      return "as superceded";
    return "with unknown status '" +s+'"';
  }

  public Definitions getDefinitions() {
    return definitions;
  }

  public FolderManager getFolders() {
    return folders;
  }
  public String getVersion() {
    return version;
  }

  public Navigation getNavigation() {
    return navigation;
  }

  public List<PlatformGenerator> getReferenceImplementations() {
    return referenceImplementations;
  }

  public IniFile getIni() {
    return ini;
  }

  public void setDefinitions(Definitions definitions) {
    this.definitions = definitions;
    breadCrumbManager.setDefinitions(definitions);
    vsValidator = new ValueSetValidator(workerContext, definitions.getVsFixups(), definitions.getStyleExemptions());
  }

  public void setVersion(String version) {
    this.version = version;
    workerContext.setVersion(version);
  }

  public void setFolders(FolderManager folders) {
    this.folders = folders;
    terminologyServices = new SpecificationTerminologyServices(Utilities.path(folders.rootDir, "vscache"), tsServer, codeSystems);
    workerContext.setTerminologyServices(terminologyServices);
    epub = new EPubManager(this, validationErrors);
  }

  public void setIni(IniFile ini) {
    this.ini = ini;
    for (String s : ini.getPropertyNames("page-titles")) {
      definitions.getPageTitles().put(s, ini.getStringProperty("page-titles", s));
    }
  }

  public EPubManager getEpub() {
    return epub;
  }

  public Calendar getGenDate() {
    return genDate;
  }

  
  @Override
  public void log(String content, LogMessageType type) {
    if (suppressedMessages.contains(content) && (type == LogMessageType.Hint || type == LogMessageType.Warning))
      return;
    if (type == LogMessageType.Process) {
      Date stop = new Date();
      long l1 = start.getTime();
      long l2 = stop.getTime();
      long diff = l2 - l1;
      long secs = diff / 1000;
      float tmp = diff - lastSecs;
      float gap = tmp / 1000;
      lastSecs = diff;
      MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
      // mem.gc();
      long used = mem.getHeapMemoryUsage().getUsed() / (1024 * 1024);
      System.out.println(String.format("%1$-74s", content)+" "+String.format("%1$5s", Float.toString(gap))+" "+String.format("%1$3s", Long.toString(secs))+"sec "+String.format("%1$4s", Long.toString(used))+"MB");
    } else
      System.out.println(content);
  }
  
//  public void logNoEoln(String content) {
//    System.out.print(content);  
//    notime = true;
//  }

  
  public void setNavigation(Navigation navigation) {
    this.navigation = navigation;
  }

  public List<String> getOrderedResources() {
    return orderedResources;
  }

  public Map<String, SectionTracker> getSectionTrackerCache() {
    return sectionTrackerCache;
  }

  public Map<String, TocEntry> getToc() {
    return toc;
  }

  public String getSvnRevision() {
    return svnRevision;
  }

  public void setSvnRevision(String svnRevision) {
    this.svnRevision = svnRevision;
  }

  public Document getV2src() {
    return v2src;
  }

  public void setV2src(Document v2src) {
    this.v2src = v2src;
  }

  public Document getV3src() {
    return v3src;
  }

  public void setV3src(Document v3src) {
    this.v3src = v3src;
  }

  public QaTracker getQa() {
    return qa;
  }

  public void setV2Valuesets(Bundle v2Valuesets) {
    this.v2Valuesets = v2Valuesets;    
  }

  public void setv3Valuesets(Bundle v3Valuesets) {
    this.v3Valuesets = v3Valuesets;    
    
  }

  private void addToValuesets(Bundle atom, ValueSet vs) {
    atom.getEntry().add(new BundleEntryComponent().setResource(vs).setFullUrl(vs.getUrl()));
  }

  public Map<String, ValueSet> getCodeSystems() {
    return codeSystems;
  }

  public Map<String, ValueSet> getValueSets() {
    return valueSets;
  }

  public Map<String, ConceptMap> getConceptMaps() {
    return conceptMaps;
  }

  public Bundle getV3Valuesets() {
    return v3Valuesets;
  }

  public Bundle getV2Valuesets() {
    return v2Valuesets;
  }

  public Map<String, String> getSvgs() {
    return svgs;
  }

  public BreadCrumbManager getBreadCrumbManager() {
    return breadCrumbManager;
  }

  public String getPublicationNotice() {
    return publicationNotice;
  }

  public void setPublicationNotice(String publicationNotice) {
    this.publicationNotice = publicationNotice;
  }

  public String getPublicationType() {
    return publicationType;
  }

  public void setPublicationType(String publicationType) {
    this.publicationType = publicationType;
  }

  public void setRegistry(BindingNameRegistry registry) {
    this.registry = registry;
    
  }

  public BindingNameRegistry getRegistry() {
    return registry;
  }

  public void setId(String id) {
    this.oid = id;   
  }

  public List<String> getSuppressedMessages() {
    return suppressedMessages;
  }

  public void loadSnomed() throws Exception {
    terminologyServices.loadSnomed(Utilities.path(folders.srcDir, "snomed", "snomed.xml"));
  }

  public void saveSnomed() throws Exception {
    terminologyServices.saveSnomed(Utilities.path(folders.srcDir, "snomed", "snomed.xml"));
  }
  
  public void loadLoinc() throws Exception {
    log("Load Loinc", LogMessageType.Process);
    terminologyServices.loadLoinc(Utilities.path(folders.srcDir, "loinc", "loinc.xml"));
  }

  public SpecificationTerminologyServices getConceptLocator() {
    return terminologyServices;
  }

  public Map<String, StructureDefinition> getProfiles() {
    return profiles;
  }

  public String getBaseURL() {
    return baseURL;
  }

  public void setBaseURL(String baseURL) {
    this.baseURL = !baseURL.endsWith("/") ? baseURL : baseURL + "/";
  }

  @Override
  public boolean isDatatype(String type) {
    return definitions.hasPrimitiveType(type) || (definitions.hasElementDefn(type) && !definitions.hasResource(type));
  }

  @Override
  public boolean isResource(String type) {
    return definitions.hasResource(type);
  }

  @Override
  public boolean hasLinkFor(String type) {
    return isDatatype(type) || definitions.hasResource(type) || definitions.getBaseResources().containsKey(type);
  }

  @Override
  public String getLinkFor(String type) throws Exception {
    if (definitions.hasResource(type) || definitions.getBaseResources().containsKey(type)) 
      return type.toLowerCase()+".html";
    else if (definitions.hasType(type))
      return definitions.getSrcFile(type)+".html#"+type;
    else if (profiles.containsKey(type) && profiles.get(type).hasUserData("path"))
      return (String) profiles.get(type).getUserData("path");
    else if (profiles.containsKey("http://hl7.org/fhir/StructureDefinition/"+type) && profiles.get("http://hl7.org/fhir/StructureDefinition/"+type).hasUserData("path"))
      return (String) profiles.get("http://hl7.org/fhir/StructureDefinition/"+type).getUserData("path");
    else
      return type.toLowerCase()+".html";
  }

  public Translations getTranslations() {
    return translations;
  }

  public void setTranslations(Translations translations) {
    this.translations = translations;
  }

  public String processMarkdown(String location, String text, String prefix) throws Exception {
    if (text == null)
      return "";
    // 1. custom FHIR extensions
    text = text.replace("||", "\r\n\r\n");
    while (text.contains("[[[")) {
      String left = text.substring(0, text.indexOf("[[["));
      String linkText = text.substring(text.indexOf("[[[")+3, text.indexOf("]]]"));
      String right = text.substring(text.indexOf("]]]")+3);
      String url = "";
      String[] parts = linkText.split("\\#");
      if (parts[0].contains("/StructureDefinition/")) {
        StructureDefinition ed = workerContext.getExtensionStructure(null, parts[0]);
        if (ed == null)
          throw new Error("Unable to find extension "+parts[0]);
        url = prefix+ed.getUserData("filename")+".html";
      } 
      if (Utilities.noString(url)) {
        String[] paths = parts[0].split("\\.");
        StructureDefinition p = new ProfileUtilities(workerContext).getProfile(null, paths[0]);
        if (p != null) {
          String suffix = (paths.length > 1) ? "-definitions.html#"+parts[0] : ".html";
          if (p.getUserData("filename") == null)
            url = prefix+paths[0].toLowerCase()+suffix;
          else
            url = prefix+p.getUserData("filename")+suffix;
        } else if (definitions.hasResource(linkText)) {
          url = prefix+linkText.toLowerCase()+".html#";
        } else if (definitions.hasElementDefn(linkText)) {
          url = prefix+definitions.getSrcFile(linkText)+".html#"+linkText;
        } else if (definitions.hasPrimitiveType(linkText)) {
          url = prefix+"datatypes.html#"+linkText;
        } else if (definitions.getPageTitles().containsKey(linkText)) {
          url = prefix+definitions.getPageTitles().get(linkText);
        } else {
		      getValidationErrors().add(
              new ValidationMessage(Source.Publisher, IssueType.BUSINESSRULE, -1, -1, location, "Unresolved logical URL "+linkText, IssueSeverity.WARNING));
          //        throw new Exception("Unresolved logical URL "+url);
        }
      }
      text = left+"["+linkText+"]("+url+")"+right;
    }
    
    // 2. markdown
    String s = Processor.process(Utilities.escapeXml(text));
    return s;
  }

  public WorkerContext getWorkerContext() {
    return workerContext;
  }

  public Map<String, Resource> getIgResources() {
    return igResources;
  }

  @Override
  public BindingResolution resolveBinding(ElementDefinitionBindingComponent binding) {
    BindingResolution br = new BindingResolution();
    if (!binding.hasValueSet()) {
      br.url = "terminologies.html#unbound";
      br.display = "(unbound)";      
    } else if (binding.getValueSet() instanceof UriType) {
      String ref = ((UriType) binding.getValueSet()).getValue();
      if (ref.startsWith("http://hl7.org/fhir/ValueSet/v3-")) {
        br.url = "v3/"+ref.substring(26)+"/index.html";
        br.display = ref.substring(26);
      } else if (definitions.getValuesets().containsKey(ref)) {
        ValueSet vs = definitions.getValuesets().get(ref);
        br.url = vs.getUserString("path");
        br.display = vs.getName();
      } else {
        br.url = ref;
        if (ref.equals("http://tools.ietf.org/html/bcp47"))
          br.display = "IETF BCP-47";
        else if (ref.equals("http://www.rfc-editor.org/bcp/bcp13.txt"))
          br.display = "IETF BCP-13";
        else if (ref.equals("http://www.ncbi.nlm.nih.gov/nuccore?db=nuccore"))
          br.display = "NucCore";
        else if (ref.equals("https://rtmms.nist.gov/rtmms/index.htm#!rosetta"))
          br.display = "Rosetta";
        else if (ref.equals("http://www.iso.org/iso/country_codes.htm"))
          br.display = "ISO Country Codes";
        else
          br.display = "????";
      }
    } else {
      String ref = ((Reference) binding.getValueSet()).getReference();
      if (ref.startsWith("ValueSet/")) {
        ValueSet vs = definitions.getValuesets().get(ref.substring(8));
        if (vs == null) {
          br.url = ref.substring(9)+".html";
          br.display = ref.substring(9);
        } else {
          br.url = vs.getUserString("path");
          br.display = vs.getName(); 
        }
      } else { 
        if (ref.startsWith("http://hl7.org/fhir/ValueSet/")) {
          ValueSet vs = valueSets.get(ref);
          if (vs != null) { 
            br.url = (String) vs.getUserData("path");
            if (Utilities.noString(br.url))
              br.url = ref.substring(23)+".html";
            br.display = vs.getName(); 
          } else if (ref.substring(23).equals("use-context")) { // special case because this happens before the value set is created 
            br.url = "valueset-"+ref.substring(23)+".html";
            br.display = "Context of Use ValueSet";
          } else { 
            br.display = ref.substring(23);
            br.url = ref.substring(23)+".html";
          }
        }  else if (ref.startsWith("http://hl7.org/fhir/ValueSet/v3-")) {
          br.url = "v3/"+ref.substring(26)+"/index.html"; 
          br.display = ref.substring(26);
        }  else if (ref.startsWith("http://hl7.org/fhir/ValueSet/v2-")) {
          br.url = "v2/"+ref.substring(26)+"/index.html"; 
          br.display = ref.substring(26);
        } else {
          br.url = ref;
          br.display = "????";
        }
      }
    }
    return br;
  }

  @Override
  public String getLinkForProfile(StructureDefinition profile, String url) throws Exception {
    String fn;
    if (url.equals("http://hl7.org/fhir/markdown"))  // magic
      return "narrative.html#markdown|markdown";
      
    if (!url.startsWith("#")) {
      String[] path = url.split("#");
      profile = new ProfileUtilities(workerContext).getProfile(null, path[0]);
//      if (profile == null && url.startsWith("StructureDefinition/"))
//        return "hspc-"+url.substring(8)+".html|"+url.substring(8);
    }
    if (profile != null) {
      fn = profile.getUserString("path");
      if (fn == null) {
        fn = profile.getUserString("filename");
        if (fn != null) {
          fn = Utilities.changeFileExt(fn, ".html");
        }
      }
      if (fn == null)
        return "|??";
      return fn+"|"+profile.getName();
    }
    return null;
  }

  public String processConformancePackageIncludes(Profile pack, String src, String intro, String notes, String resourceName, ImplementationGuideDefn ig) throws Exception {
    String workingTitle = null;
    int level = (ig == null || ig.isCore()) ? 0 : 1;
    //boolean even = false;
    
    while (src.contains("<%") || src.contains("[%"))
    {
      int i1 = src.indexOf("<%");
      int i2 = src.indexOf("%>");
      if (i1 == -1) {
        i1 = src.indexOf("[%");
        i2 = src.indexOf("%]");
      }
      String s1 = src.substring(0, i1);
      String s2 = src.substring(i1 + 2, i2).trim();
      String s3 = src.substring(i2+2);

      String[] com = s2.split(" ");
      if (com[0].equals("file"))
        src = s1+TextFile.fileToString(folders.srcDir + com[1]+".html")+s3;
      else if (com[0].equals("settitle")) {
        workingTitle = s2.substring(9).replace("{", "<%").replace("}", "%>");
        src = s1+s3;
      }  else if (com[0].equals("reflink")) {
        src = s1 + reflink(com[1]) + s3;      
      } else if (com[0].equals("setlevel")) {
        level = Integer.parseInt(com[1]);
        src = s1+s3;
      } else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing profile "+pack.getId());
      else if (com[0].equals("pageheader"))
        src = s1+pageHeader(pack.getId().toUpperCase().substring(0, 1)+pack.getId().substring(1))+s3;
      else if (com[0].equals("header"))
        src = s1+TextFile.fileToString(folders.srcDir + "header.html")+s3;
      else if (com[0].equals("newheader"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader.html")+s3;
      else if (com[0].equals("newheader1"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader1.html")+s3;
      else if (com[0].equals("footer"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer.html")+s3;
      else if (com[0].equals("newfooter"))
        src = s1+TextFile.fileToString(folders.srcDir + "newfooter.html")+s3;
      else if (com[0].equals("footer1"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer1.html")+s3;
      else if (com[0].equals("footer2"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer2.html")+s3;
      else if (com[0].equals("footer3"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer3.html")+s3;
      else if (com[0].equals("title"))
        src = s1+(workingTitle == null ? Utilities.escapeXml(pack.getTitle()) : workingTitle)+s3;
      else if (com[0].equals("xtitle"))
        src = s1+Utilities.escapeXml(pack.getId().toUpperCase().substring(0, 1)+pack.getId().substring(1))+s3;
      else if (com[0].equals("name"))
        src = s1+pack.getId()+s3;
      else if (com[0].equals("package.intro"))
        src = s1+(intro == null ? pack.metadata("description") : intro) +s3;
      else if (com[0].equals("package.notes"))
        src = s1+(notes == null ? "" : notes) +s3;
      else if (com[0].equals("canonicalname"))
        src = s1+makeCanonical(pack.getId())+s3;
      else if (com[0].equals("version"))
        src = s1+version+s3;
      else if (com[0].equals("gendate"))
        src = s1+Config.DATE_FORMAT().format(new Date())+s3;
      else if (com[0].equals("maindiv"))
        src = s1+"<div class=\"content\">"+s3;
      else if (com[0].equals("/maindiv"))
        src = s1+"</div>"+s3;
      else if (com[0].equals("v2Index"))
        src = s1+genV2Index()+s3;
      else if (com[0].equals("v3Index-cs"))
        src = s1+genV3CSIndex()+s3;
      else if (com[0].equals("v3Index-vs"))
        src = s1+genV3VSIndex()+s3;
      else if (com[0].equals("id"))
        src = s1+pack.getId()+s3;
      else if (com[0].equals("events"))
        src = s1 + getEventsTable(pack.getId())+ s3;
      else if (com[0].equals("resourcecodes"))
        src = s1 + genResCodes() + s3;
      else if (com[0].equals("datatypecodes"))
        src = s1 + genDTCodes() + s3;
      else if (com[0].equals("allparams"))
        src = s1 + allParamlist() + s3;      
//      else if (com[0].equals("bindingtable-codelists"))
//        src = s1 + genBindingTable(true) + s3;
//      else if (com[0].equals("bindingtable"))
//        src = s1 + genBindingsTable() + s3;
      else if (com[0].equals("codeslist"))
        src = s1 + genCodeSystemsTable() + s3;
//      else if (com[0].equals("valuesetslist"))
//        src = s1 + genValueSetsTable() + s3;
      else if (com[0].equals("igvaluesetslist"))
        src = s1 + genIGValueSetsTable() + s3;
      else if (com[0].equals("conceptmapslist"))
        src = s1 + genConceptMapsTable() + s3;
//      else if (com[0].equals("bindingtable-others"))
//        src = s1 + genBindingTable(false) + s3;
      else if (com[0].equals("resimplall"))
          src = s1 + genResImplList() + s3;
      else if (com[0].equals("impllist"))
        src = s1 + genReferenceImplList(pack.getId()) + s3;
      else if (com[0].equals("breadcrumb"))
        src = s1 + breadCrumbManager.make(pack.getId()) + s3;
      else if (com[0].equals("navlist"))
        src = s1 + breadCrumbManager.navlist(pack.getId(), genlevel(level)) + s3;
      else if (com[0].equals("breadcrumblist"))
        src = s1 + ((ig == null || ig.isCore()) ? breadCrumbManager.makelist(pack.getId(), "profile:"+resourceName+"/"+pack.getId(), genlevel(level), pack.getTitle()): ig.makeList(pack.getId(), "profile:"+resourceName+"/"+pack.getId(), genlevel(level), pack.getTitle())) + s3;      
      else if (com[0].equals("year"))
        src = s1 + new SimpleDateFormat("yyyy").format(new Date()) + s3;      
      else if (com[0].equals("revision"))
        src = s1 + svnRevision + s3;  
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;      
      else if (com[0].equals("pub-notice"))
        src = s1 + publicationNotice + s3;      
      else if (com[0].equals("level"))
        src = s1 + genlevel(level) + s3;  
      else if (com[0].equals("pagepath"))
        src = s1 + pack.getId() + s3;  
      else if (com[0].equals("rellink"))
        src = s1 + Utilities.URLEncode(pack.getId()) + s3;  
      else if (com[0].equals("baseURL"))
        src = s1 + Utilities.URLEncode(baseURL) + s3;  
      else if (com[0].equals("description"))
        src = s1 + Utilities.escapeXml(pack.getDescription()) + s3;  
      else if (com[0].equals("package-content"))
        src = s1 + getPackageContent(pack, genlevel(level)) + s3;  
      else if (com[0].equals("package.search"))
        src = s1+getSearch(pack)+s3;
      else if (com[0].startsWith("!"))
        src = s1 + s3;  
      else 
        throw new Exception("Instruction <%"+s2+"%> not understood parsing profile "+pack.getId());
    }
    return src;
  }

  private String getPackageContent(Profile pack, String prefix) throws Exception {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"lines\">");
    if (pack.getProfiles().size() > 0) {
      s.append("<tr><td colspan=\"2\"><b>Profiles</b>: </td></tr>");
      for (ConstraintStructure p : pack.getProfiles())
        s.append("<tr><td><a href=\"").append(p.getId()).append(".html\">").append(Utilities.escapeXml(p.getTitle()))
                .append("</a></td><td>").append(Utilities.escapeXml(p.getResource().getDescription())).append("</td></tr>");
    }
    if (pack.getExtensions().size() > 0) {
      s.append("<tr><td colspan=\"2\"><b>Extensions</b>: </td></tr>");
      for (StructureDefinition ed : pack.getExtensions())
        s.append("<tr><td><a name=\"extension-").append(prefix+ed.getId()).append("\"/><a href=\"extension-").append(ed.getId().toLowerCase()).append(".html\">").append(Utilities.escapeXml(ed.getId()))
                .append("</a></td><td><b>").append(Utilities.escapeXml(ed.getName())).append("</b> : ").append(processMarkdown(pack.getId(), ed.getDescription(), prefix)).append("</td></tr>");
    }
    if (pack.getExamples().size() > 0) {
      s.append("<tr><td colspan=\"2\"><b>Examples</b>: </td></tr>");
      for (Example ex : pack.getExamples())
        s.append("<tr><td><a href=\"").append(ex.getTitle()).append(".html\">").append(Utilities.escapeXml(Utilities.changeFileExt(ex.getName(), "")))
                .append("</a></td><td>").append(processMarkdown(pack.getId(), ex.getDescription(), prefix)).append("</td></tr>");
    }
    s.append("</table>");
    
    if (pack.getSearchParameters().size() > 0) {
      // search parameters
      StringBuilder b = new StringBuilder();
      b.append("<a name=\"search\"> </a>\r\n");
      b.append("<h3>Search Parameters</h3>\r\n");
      b.append("<p>Search parameters defined by this package. See <a href=\""+prefix+"search.html\">Searching</a> for more information about searching in REST, messaging, and services.</p>\r\n");
      b.append("<table class=\"list\">\r\n");
      b.append("<tr><td><b>Name</b></td><td><b>Type</b></td><td><b>Description</b></td><td><b>Paths</b></td><td><b>Source</b></td></tr>\r\n");
      List<String> names = new ArrayList<String>();
      for (SearchParameter sp : pack.getSearchParameters())
        names.add(sp.getName());
      Collections.sort(names);
      for (String name : names)  {
        SearchParameter p = null;
        for (SearchParameter sp : pack.getSearchParameters())
          if (name.equals(sp.getName())) 
            p = sp;
        b.append("<tr><td>"+p.getName()+"</td><td><a href=\""+prefix+"search.html#"+p.getType().toCode()+"\">"+p.getType().toCode()+"</a></td>" +
            "<td>"+Utilities.escapeXml(p.getDescription())+"</td><td>"+(p.hasXpath() ? p.getXpath() : "")+(p.getType() == SearchParamType.REFERENCE && p.hasTarget() ? asText(p.getTarget()) : "")+"</td>" +
            "<td><a href=\""+p.getId()+".xml.html\">XML</a> / <a href=\""+p.getId()+".json.html\">JSON</a></td></tr>\r\n");
      }
      b.append("</table>\r\n");
      s.append(b.toString());
    }
    return s.toString();
  }

  private String asText(List<CodeType> target) {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (CodeType rn : target) {
      if (first) {
        first = false;
        b.append("<br/>(");
      } else
        b.append(", ");
      final String value = rn.getValue();
      if ("Any".equals(value))
        b.append("Any");
       else
        b.append("<a href=\"").append(value.toLowerCase()).append(".html\">").append(value).append("</a>");
    }
    if (!first)
      b.append(")");
    return b.toString();
  }

  private String genW5(boolean types) throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table border=\"1\">\r\n<tr>\r\n");
    List<W5Entry> items = new ArrayList<W5Entry>();
    for (W5Entry e : definitions.getW5list()) 
      if (e.isDisplay())
        items.add(e);
    
    b.append("<td>Resource</td>");
    for (W5Entry e : items) {
      b.append("<td><span title=\"").append(Utilities.escapeXml(definitions.getW5s().get(e.getCode()).getDescription())).append("\">").append(e.getCode()).append("</span></td>");
      
    }
    b.append("</tr>\r\n");
    processW5(b, items, "clinical", types);
    processW5(b, items, "administration", types);
    processW5(b, items, "infrastructure", types);
    
    b.append("</table>\r\n");
    
    return b.toString();
  }

  private void processW5(StringBuilder b, List<W5Entry> items, String cat, boolean types) throws Exception {
    b.append("<tr><td colspan=\"").append(Integer.toString(items.size() + 1)).append("\"><b>")
            .append(Utilities.escapeXml(definitions.getW5s().get(cat).getDescription())).append("</b></td></tr>\r\n");
    for (String rn : definitions.sortedResourceNames()) {
      ResourceDefn r = definitions.getResourceByName(rn);
      if (cat.equals(r.getRoot().getW5())) {
        b.append("<tr>\r\n <td>").append(rn).append("</td>\r\n");
        for (W5Entry e : items) {
          b.append(" <td>");
          addMatchingFields(b, r.getRoot().getElements(), r.getRoot().getName(), e.getCode(), true, types);
          b.append("</td>\r\n");
        }
        b.append("</tr>\r\n");
      }
    }
  }

  private boolean addMatchingFields(StringBuilder b, List<ElementDefn> elements, String path, String name, boolean first, boolean types) {
    for (ElementDefn ed : elements) {
      if (name.equals(ed.getW5())) {
        if (first) first = false; else b.append("<br/>");
        describeField(b, ed, types);
      }
      first = addMatchingFields(b, ed.getElements(), path+"."+ed.getName(), name, first, types);
    }
    return first;
  }

  private void describeField(StringBuilder b, ElementDefn ed, boolean types) {
    b.append(ed.getName());
    if (ed.unbounded())
      b.append("*");
    if (types) {
      b.append(" : ");
      b.append(patch(ed.typeCode()));
    }
  }

  private String patch(String typeCode) {
    if (typeCode.startsWith("Reference("))
      return typeCode.substring(0, typeCode.length()-1).substring(10);
    else
      return typeCode;
  }
  
  private String genStatusCodes() throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table border=\"1\">\r\n");
    int colcount = 0;
    for (ArrayList<String> row: definitions.getStatusCodes().values()) {
      if (row.size() > colcount)
        colcount = row.size();
    }
    b.append("<tr>");
    b.append("<td>Path</td>");
    for (int i = 0; i < colcount; i++) 
      b.append("<td>c").append(Integer.toString(i + 1)).append("</td>");
    b.append("</tr>\r\n");

    List<String> names = new ArrayList<String>();
    for (String n : definitions.getStatusCodes().keySet()) 
       names.add(n);
    Collections.sort(names);

    for (String n : names) {
      if (!n.startsWith("@")) {
        b.append("<tr>");
        ElementDefn ed = getElementDefn(n);
        if (ed == null || !ed.isModifier())
          b.append("<td>").append(n).append("</td>");
        else 
          b.append("<td><b>").append(n).append("</b></td>");
        ArrayList<String> row = definitions.getStatusCodes().get(n);
        for (int i = 0; i < colcount; i++) 
          b.append("<td>").append(i < row.size() ? row.get(i) : "").append("</td>");
        b.append("</tr>\r\n");
      }
    }
    
    b.append("</table>\r\n");
    
    return b.toString();
  }

  private ElementDefn getElementDefn(String n) throws Exception {
    String[] path = n.split("\\.");
    ElementDefn ed = definitions.getElementDefn(path[0]);
    for (int i = 1; i < path.length; i++) {
      if (ed == null)
        return null;
      ed = ed.getElementByName(path[i]);
    }
    return ed;
  }

  public String expandVS(ValueSet vs, String prefix, String base) {
    try {
      ValueSetExpansionOutcome result = workerContext.getTerminologyServices().expand(vs);
      if (result.getError() != null)
        return "<hr/>\r\n<div style=\"background-color: Floralwhite; border:1px solid maroon; padding: 5px;\"><!--3-->"+processExpansionError(result.getError())+"</div>";

      if (result.getValueset() == null)
        return "<hr/>\r\n<div style=\"background-color: Floralwhite; border:1px solid maroon; padding: 5px;\"><!--4-->"+processExpansionError("(no error returned)")+"</div>";
      ValueSet exp = result.getValueset();
      if (exp == vs)
        throw new Exception("Expansion cannot be the same instance");
      exp.setCompose(null);
      exp.setCodeSystem(null);
      exp.setText(null); 
      exp.setDescription("Value Set Contents (Expansion) for "+vs.getName()+" at "+Config.DATE_FORMAT().format(new Date()));
      
      new NarrativeGenerator(prefix, base, workerContext).generate(exp, vs, false);
      return "<hr/>\r\n<div style=\"background-color: Floralwhite; border:1px solid maroon; padding: 5px;\">"+new XhtmlComposer().compose(exp.getText().getDiv())+"</div>";
    } catch (Exception e) {
      e.printStackTrace();
      return "<hr/>\r\n<div style=\"background-color: Floralwhite; border:1px solid maroon; padding: 5px;\"><!--5-->"+processExpansionError(e instanceof NullPointerException ? "NullPointerException" : e.getMessage())+" "+Utilities.escapeXml(stack(e))+" </div>";
    }
  }

//  public List<ValidationMessage> getCollectedValidationErrors() {
//    return collectedValidationErrors;
//  }

  public List<ValidationMessage> getValidationErrors() {
    return validationErrors;
  }
  
  private String genResRefList(String n) throws Exception {
    ResourceDefn e = definitions.getResourceByName(n);
    StringBuilder b = new StringBuilder();
    b.append("<ul>\r\n");
    for (ElementDefn c : e.getRoot().getElements())
      genResRefItem(b, n.toLowerCase(), n, c);
    b.append("</ul>\r\n");
    return b.toString();
  }

  private void genResRefItem(StringBuilder b, String base, String path, ElementDefn e) {
    path = path+"."+e.getName();
    if (e.typeCode().startsWith("Reference(")) {
      b.append(" <li><a href=\"");
      b.append(base);
      b.append("-definitions.html#");
      b.append(path);
      b.append("\">");
      b.append(path);
      b.append("</a></li>\r\n");
    }
    for (ElementDefn c : e.getElements())
      genResRefItem(b, base, path, c);
  }

  public Set<String> getSearchTypeUsage() {
    return searchTypeUsage ;
  }
  
  private String getFmm(String resourceName) throws Exception {
    ResourceDefn rd = definitions.getResourceByName(resourceName);
    if (rd == null)
      throw new Exception("unable to find resource '"+resourceName+"'");
    return " <a href=\"resource.html#maturity\" style=\"color: maroon; hover: maroon; visited; maroon; opacity: 0.7\" title=\"Maturity Level\">"+rd.getFmmLevel()+"</a>";
  }
  
  private String getXcm(String param) {
    if (searchTypeUsage.contains(param)) 
      return "<span style=\"font-weight: bold\">Y</span> ";
    else
      return "<span style=\"color: grey\">N</span>";
  }

  private String genCSList() {
    StringBuilder b = new StringBuilder();
    List<String> names = new ArrayList<String>();
    names.addAll(definitions.getCodeSystems().keySet());
    Collections.sort(names);
    for (String n : names) {
      ValueSet vs = definitions.getCodeSystems().get(n);
      if (vs.hasCodeSystem() && vs.getCodeSystem().hasSystem() && vs.getCodeSystem().getSystem().startsWith("http://hl7.org/fhir") && !vs.getCodeSystem().getSystem().startsWith("http://hl7.org/fhir/v2/") && !vs.getCodeSystem().getSystem().startsWith("http://hl7.org/fhir/v3/")) {
        b.append("  <tr>\r\n");
        b.append("    <td>"+vs.getCodeSystem().getSystem()+"</td>\r\n");
        b.append("    <td><a href=\""+vs.getUserString("path")+"\">"+vs.getName()+"</a></td>\r\n");
        b.append("    <td>"+vs.getDescription()+"</td>\r\n");
        String oid = ToolingExtensions.getOID(vs.getCodeSystem());
        b.append("    <td>"+(oid == null ? "" : oid.substring(8))+"</td>\r\n");
        b.append("  </tr>\r\n");
      }
    }
    return b.toString();
  }

  public ValueSetValidator getVsValidator() {
    return vsValidator;
  }

  public void clean() {
    // recover some memory. Keep only what is needed for validation
//    definitions = null;
    navigation = null;
    ini = null;
    prevSidebars.clear();
    orderedResources.clear();
    sectionTrackerCache.clear();
    toc.clear();;
    v2src = null;
    v3src = null;
    v3Valuesets = null;
    v2Valuesets = null;
    igResources.clear();
    svgs.clear();
    translations = null;
    registry = null;
    epub = null;
    searchTypeUsage = null;
    vsValidator = null;
    System.gc();
  }

  public void clean2() {
    codeSystems.clear();
    valueSets.clear();
    conceptMaps.clear();
    profiles.clear();
    System.gc();
    
  }
  
}
