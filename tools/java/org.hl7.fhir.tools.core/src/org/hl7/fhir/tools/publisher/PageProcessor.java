package org.hl7.fhir.tools.publisher;
/*
Copyright (c) 2011-2014, HL7, Inc
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
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.generators.specification.DataTypeTableGenerator;
import org.hl7.fhir.definitions.generators.specification.DictHTMLGenerator;
import org.hl7.fhir.definitions.generators.specification.MappingsGenerator;
import org.hl7.fhir.definitions.generators.specification.ProfileTableGenerator;
import org.hl7.fhir.definitions.generators.specification.ResourceTableGenerator;
import org.hl7.fhir.definitions.generators.specification.SvgGenerator;
import org.hl7.fhir.definitions.generators.specification.TerminologyNotesGenerator;
import org.hl7.fhir.definitions.generators.specification.XmlSpecGenerator;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingStrength;
import org.hl7.fhir.definitions.model.BindingSpecification.ElementType;
import org.hl7.fhir.definitions.model.Compartment;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.EventDefn;
import org.hl7.fhir.definitions.model.EventUsage;
import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.ExtensionDefn;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.ProfileDefn;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.RegisteredProfile;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameter;
import org.hl7.fhir.definitions.model.SearchParameter.SearchType;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.definitions.parsers.BindingNameRegistry;
import org.hl7.fhir.definitions.parsers.TypeParser;
import org.hl7.fhir.instance.formats.JsonComposer;
import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.ConceptMap;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ProfileExtensionDefnComponent;
import org.hl7.fhir.instance.model.Profile.ProfileMappingComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
import org.hl7.fhir.instance.model.ResourceReference;
import org.hl7.fhir.instance.model.Uri;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.utils.NarrativeGenerator;
import org.hl7.fhir.instance.utils.ProfileUtilities;
import org.hl7.fhir.instance.utils.ProfileUtilities.ExtensionDefinition;
import org.hl7.fhir.instance.utils.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.instance.utils.ValueSetExpansionCache;
import org.hl7.fhir.definitions.generators.specification.GeneratorUtils;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.hl7.fhir.utilities.xml.XhtmlGenerator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PageProcessor implements Logger, ProfileKnowledgeProvider  {

  private static final String SIDEBAR_SPACER = "<p>&nbsp;</p>\r\n";
  private List<String> suppressedMessages = new ArrayList<String>();
  private Definitions definitions;
  private FolderManager folders;
  private String version;
  private Navigation navigation;
  private List<PlatformGenerator> referenceImplementations = new ArrayList<PlatformGenerator>();
  private IniFile ini;
  private Calendar genDate = Calendar.getInstance();
  private Date start = new Date();
  private Map<String, String> prevSidebars = new HashMap<String, String>();
  private String svnRevision;
  private List<String> orderedResources = new ArrayList<String>();
  private Map<String, SectionTracker> sectionTrackerCache = new HashMap<String, SectionTracker>(); 
  private Map<String, TocEntry> toc = new HashMap<String, TocEntry>();
  private Document v2src;
  private Document v3src;
  private QaTracker qa = new QaTracker();
  private AtomFeed v3Valuesets;
  private AtomFeed v2Valuesets;
  private Map<String, AtomEntry<ValueSet>> codeSystems = new HashMap<String, AtomEntry<ValueSet>>();
  private Map<String, AtomEntry<ValueSet>> valueSets = new HashMap<String, AtomEntry<ValueSet>>();
  private Map<String, AtomEntry<ConceptMap>> conceptMaps = new HashMap<String, AtomEntry<ConceptMap>>();
  private Map<String, Profile> profiles = new HashMap<String, Profile>();
  
  private Map<String, String> svgs = new HashMap<String, String>();
  private BreadCrumbManager breadCrumbManager = new BreadCrumbManager();
  private String publicationType = "Local Build ("+System.getenv("COMPUTERNAME")+")";
  private String publicationNotice = "";
  private BindingNameRegistry registry;
  private String id; // technical identifier associated with the page being built
  private EPubManager epub;
  private SpecificationTerminologyServices conceptLocator;
  private String baseURL = "http://hl7.org/implement/standards/FHIR-Develop/";
  
  public final static String PUB_NOTICE =
      "<p style=\"background-color: gold; border:1px solid maroon; padding: 5px;\">\r\n"+
          "This is the stable development version of FHIR. There's also a <a href=\"http://hl7.org/fhir\">Current DSTU</a>, and a <a href=\"http://latest.fhir.me/\">Continuous Integration Build</a> (will be incorrect/inconsistent at times).\r\n"+
          "</p>\r\n";
  
//  private boolean notime;
  
  private String dictForDt(String dt) throws Exception {
	  File tmp = File.createTempFile("tmp", ".tmp");
	  DictHTMLGenerator gen = new DictHTMLGenerator(new FileOutputStream(tmp), this);
	  TypeParser tp = new TypeParser();
	  TypeRef t = tp.parse(dt).get(0);
	  
	  ElementDefn e;
	  if (t.getName().equals("Resource"))
	    e = definitions.getBaseResource().getRoot();
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
	  File tmp = File.createTempFile("tmp", ".tmp");
	  tmp.deleteOnExit();
	  TerminologyNotesGenerator gen = new TerminologyNotesGenerator(new FileOutputStream(tmp), this);
	  TypeParser tp = new TypeParser();
	  TypeRef t = tp.parse(dt).get(0);
	  ElementDefn e = definitions.getElementDefn(t.getName());
	  if (e == null) {
		  gen.close();
		  throw new Exception("unable to find definition for "+ dt);
	  } 
	  else {
		  gen.generate(e, definitions.getBindings());
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
	  File tmp = File.createTempFile("tmp", ".tmp");
	  tmp.deleteOnExit();
	  if (dt.equals("ResourceReference") || dt.equals("Narrative") || dt.equals("Extension"))
	    pn = "base.html";
	  XmlSpecGenerator gen = new XmlSpecGenerator(new FileOutputStream(tmp), pn == null ? null : pn.substring(0, pn.indexOf("."))+"-definitions.html", null, this);
	  TypeParser tp = new TypeParser();
	  TypeRef t = tp.parse(dt).get(0);
	  ElementDefn e = definitions.getElementDefn(t.getName());
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

  private String combineNotes(List<String> followUps, String notes) {
    String s = "";
    if (notes != null && !notes.equals(""))
      s = notes;
    if (followUps.size() > 0)
      if (s != "")
        s = s + "<br/>Follow ups: "+Utilities.asCSV(followUps);
      else
        s = "Follow ups: "+Utilities.asCSV(followUps);
    return s;      
  }

  private String describeMsg(List<String> resources, List<String> aggregations) {
    if (resources.size() == 0 && aggregations.size() == 0)
      return "<font color=\"silver\">--</font>";
    else {
      String s = resources.size() == 0 ? "" : Utilities.asCSV(resources);
      
      if (aggregations.size() == 0)
        return s;
      else
        return s + "<br/>"+Utilities.asHtmlBr("&nbsp;"+resources.get(0), aggregations)+"";
    }      
  }


  public String processPageIncludes(String file, String src, String type, Map<String, String> others) throws Exception {
    return processPageIncludes(file, src, type, others, file);
  }
  public String processPageIncludes(String file, String src, String type, Map<String, String> others, String pagePath) throws Exception {
    String wikilink = "http://wiki.hl7.org/index.php?title=FHIR_"+prepWikiName(file)+"_Page";
    String workingTitle = null;
    int level = 0;
    boolean even = false;
    
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
      String name = file.substring(0,file.lastIndexOf(".")); 

      String[] com = s2.split(" ");
      if (com.length == 2 && com[0].equals("dt")) 
        src = s1+xmlForDt(com[1], file)+treeForDt(com[1])+profileRef(com[1])+tsForDt(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dt.constraints")) 
        src = s1+genConstraints(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dt.restrictions")) 
        src = s1+genRestrictions(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dictionary"))
        src = s1+dictForDt(com[1])+s3;
//      else if (com[0].equals("dtheader"))
//        src = s1+dtHeader(name, com.length > 1 ? com[1] : null)+s3;
//      else if (com[0].equals("formatsheader"))
//        src = s1+formatsHeader(name, com.length > 1 ? com[1] : null)+s3;
//      else if (com[0].equals("resourcesheader"))
//        src = s1+resourcesHeader(name, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("txheader"))
        src = s1+txHeader(name, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("cmpheader"))
        src = s1+cmpHeader(name, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("extheader"))
        src = s1+extHeader(name, com.length > 1 ? com[1] : null)+s3;
//      else if (com[0].equals("atomheader"))
//        src = s1+atomHeader(name, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("codelist"))
        src = s1+codelist(name, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("resheader"))
        src = s1+resHeader("document", "Document", com.length > 1 ? com[1] : null)+s3;
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
        src = s1+new SvgGenerator(this).generate(folders.srcDir+ com[1])+s3;
      else if (com[0].equals("file"))
        src = s1+TextFile.fileToString(folders.srcDir + com[1]+".html")+s3;
      else if (com[0].equals("v2xref"))
        src = s1 + xreferencesForV2(name, com[1]) + s3;      
      else if (com[0].equals("conceptmaplistv2"))
        src = s1 + conceptmaplist("http://hl7.org/fhir/v2/vs/"+(name.contains("|") ? name.substring(0,name.indexOf("|")) : name), com[1]) + s3;      
      else if (com[0].equals("conceptmaplistv3"))
        src = s1 + conceptmaplist("http://hl7.org/fhir/v3/vs/"+(name.contains("|") ? name.substring(0,name.indexOf("|")) : name), com[1]) + s3;      
      else if (com[0].equals("conceptmaplistvs")) {
        BindingSpecification bs = definitions.getBindingByName(Utilities.fileTitle(file));
        String ref;
        if (bs == null) {
          ref = "http://hl7.org/fhir/vs/"+Utilities.fileTitle(file);
        } else {
          ref = bs.getReference();
          if (ref.startsWith("valueset-"))
            ref = ref.substring(9);
          ref = "http://hl7.org/fhir/vs/"+ref;
        }
        src = s1 + conceptmaplist(ref, com[1]) + s3;
      }  else if (com[0].equals("setwiki")) {
        wikilink = com[1];
        src = s1+s3;
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
      } else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
      else if (com[0].equals("wiki"))
        src = s1+wikilink+s3;
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
        src = s1 + getEventsTable()+ s3;
      else if (com[0].equals("resourcecodes"))
        src = s1 + genResCodes() + s3;
      else if (com[0].equals("datatypecodes"))
        src = s1 + genDTCodes() + s3;
      else if (com[0].equals("bindingtable-codelists"))
        src = s1 + genBindingTable(true) + s3;
      else if (com[0].equals("bindingtable"))
        src = s1 + genBindingsTable() + s3;
      else if (com[0].equals("codeslist"))
        src = s1 + genCodeSystemsTable() + s3;
      else if (com[0].equals("valuesetslist"))
        src = s1 + genValueSetsTable() + s3;
      else if (com[0].equals("conceptmapslist"))
        src = s1 + genConceptMapsTable() + s3;
      else if (com[0].equals("bindingtable-others"))
        src = s1 + genBindingTable(false) + s3;
      else if (com[0].equals("resimplall"))
          src = s1 + genResImplList() + s3;
      else if (com[0].equals("impllist"))
        src = s1 + genReferenceImplList() + s3;
      else if (com[0].equals("txurl"))
        src = s1 + "http://hl7.org/fhir/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vstxurl"))
        src = s1 + "http://hl7.org/fhir/vs/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vsurl")) {
        BindingSpecification bs = definitions.getBindingByName(Utilities.fileTitle(file));
        if (bs == null) {
          src = s1 + "http://hl7.org/fhir/vs/"+Utilities.fileTitle(file) + s3;
        } else {
          String reference = bs.getReference();
          if (reference.startsWith("valueset-"))
            reference = reference.substring(9);
          src = s1 + "http://hl7.org/fhir/vs/"+reference + s3;
        }
      } else if (com[0].equals("toc"))
        src = s1 + generateToc() + s3;
      else if (com[0].equals("txdef"))
        src = s1 + generateCodeDefinition(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("vsdef"))
        src = s1 + generateValueSetDefinition(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("txoid"))
        src = s1 + generateOID(Utilities.fileTitle(file), false) + s3;
      else if (com[0].equals("vsoid"))
        src = s1 + generateOID(Utilities.fileTitle(file), true) + s3;
      else if (com[0].equals("txname"))
        src = s1 + Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vsname"))
        src = s1 + Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vsref")) {
        BindingSpecification bs = definitions.getBindingByName(Utilities.fileTitle(file));
        if (bs == null)
          src = s1 + Utilities.fileTitle(file) + s3;
        else
          src = s1 + bs.getReference() + s3;
      } else if (com[0].equals("txdesc"))
        src = s1 + generateDesc(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("vsdesc"))
        src = s1 + generateVSDesc(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("txusage"))
        src = s1 + generateBSUsage(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("vsusage"))
        src = s1 + generateBSUsage(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("txsummary"))
        src = s1 + generateCodeTable(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("vssummary"))
        src = s1 + "todo" + s3;
      else if (com[0].equals("compartmentlist"))
        src = s1 + compartmentlist() + s3;
      else if (com[0].equals("qa"))
        src = s1 + qa.report() + s3;
      else if (com[0].equals("comp-title"))
        src = s1 + compTitle(name) + s3;
      else if (com[0].equals("comp-desc"))
        src = s1 + compDesc(name) + s3;
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
        src = s1 + breadCrumbManager.makelist(name, type, genlevel(level)) + s3;      
      else if (com[0].equals("year"))
        src = s1 + new SimpleDateFormat("yyyy").format(new Date()) + s3;      
      else if (com[0].equals("revision"))
        src = s1 + svnRevision + s3;  
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;      
      else if (com[0].equals("pub-notice"))
        src = s1 + publicationNotice + s3;      
      else if (com[0].equals("vsxref"))
        src = s1 + xreferencesForFhir(name) + s3;      
      else if (com[0].equals("vsexpansion"))
        src = s1 + expandValueSet(Utilities.fileTitle(file)) + s3;
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
      else if (others != null && others.containsKey(com[0]))  
        src = s1 + others.get(com[0]) + s3; 
      
      else 
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
    }
    return src;
  }

  private String genProfilelist() throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append("  <tr>\r\n");
    b.append("    <td><b>Name</b></td>\r\n");
    b.append("    <td><b>Type</b></td>\r\n");
    b.append("    <td><b>Usage</b></td>\r\n");
    b.append("  </tr>\r\n");
    
    b.append("  <tr>\r\n");
    b.append("    <td colspan=\"2\"><b>General</b></td>\r\n");
    b.append("  </tr>\r\n");
    List<String> names = new ArrayList<String>();
    names.addAll(definitions.getProfiles().keySet());
    Collections.sort(names);
    for (String s : names) {
      ProfileDefn p = definitions.getProfiles().get(s);
      b.append("  <tr>\r\n");
      b.append("    <td><a href=\""+p.metadata("id")+".html\">"+Utilities.escapeXml(p.getSource().getNameSimple())+"</a></td>\r\n");
      b.append("    <td>"+describeProfileType(p.getSource())+"</td>\r\n");
      b.append("    <td>"+Utilities.escapeXml(p.getSource().getDescriptionSimple())+"</td>\r\n");
      b.append(" </tr>\r\n");
    }
    for (String n : definitions.sortedResourceNames()) {
      ResourceDefn r = definitions.getResourceByName(n);
      if (!r.getProfiles().isEmpty()) {
        b.append("  <tr>\r\n");
        b.append("    <td colspan=\"2\"><b>"+r.getName()+"</b></td>\r\n");
        b.append("  </tr>\r\n");
        for (RegisteredProfile p : r.getProfiles()) {
          b.append("  <tr>\r\n");
          b.append("    <td><a href=\""+p.getDestFilenameNoExt()+".html\">"+Utilities.escapeXml(p.getName())+"</a></td>\r\n");
          b.append("    <td>"+describeProfileType(p.getProfile().getSource())+"</td>\r\n");
          b.append("    <td>"+Utilities.escapeXml(p.getDescription())+"</td>\r\n");
          b.append(" </tr>\r\n");
        }
      }
    }
    b.append("</table>\r\n");
   
    return b.toString();
  }

  private String describeProfileType(Profile source) {
    if (source.getStructure().isEmpty() && source.getQuery().isEmpty())
      return source.getExtensionDefn().size() == 1 ?  "Extension" : "Extensions";
    if (source.getExtensionDefn().isEmpty() && source.getQuery().isEmpty())
      return source.getStructure().size() == 1 ?  "Constraint" : "Constraints";
    if (source.getStructure().isEmpty() && source.getExtensionDefn().isEmpty())
      return source.getQuery().size() == 1 ?  "Query" :  "Queries";
    return "Mixed";
  }

  private String profileRef(String name) {
    return "Alternate definitions: Resource Profile (<a href=\""+name+".profile.xml.html\">XML</a>, <a href=\""+name+".profile.json.html\">JSON</a>)";
  }

  private String reflink(String name) {
    for (PlatformGenerator t : referenceImplementations) 
      if (t.getName().equals(name))
        return t.getReference(version);
    return "??";
  }

  private String conceptmaplist(String id, String level) {
    List<AtomEntry<ConceptMap>> cmaps = new ArrayList<AtomEntry<ConceptMap>>();
    for (AtomEntry<ConceptMap> e : conceptMaps.values()) {
      ConceptMap cm = e.getResource();
      if (((ResourceReference) cm.getSource()).getReferenceSimple().equals(id) || ((ResourceReference) cm.getTarget()).getReferenceSimple().equals(id))
        cmaps.add(e);
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
      for (AtomEntry<ConceptMap> ae : cmaps) {
        ConceptMap cm = ae.getResource();
        b.append(" <tr><td>");
        if (((ResourceReference) cm.getSource()).getReferenceSimple().equals(id)) {
          b.append("to <a href=\""+getValueSetRef(prefix, ((ResourceReference) cm.getTarget()).getReferenceSimple())+"\">"+describeValueSetByRef(((ResourceReference) cm.getTarget()).getReferenceSimple()));
        } else {
          b.append("from <a href=\""+getValueSetRef(prefix, ((ResourceReference) cm.getSource()).getReferenceSimple())+"\">"+describeValueSetByRef(((ResourceReference) cm.getSource()).getReferenceSimple()));
        }
        b.append("</a></td><td><a href=\""+prefix+ae.getLinks().get("path")+"\">"+cm.getNameSimple()+"</a></td><td><a href=\""+prefix+Utilities.changeFileExt(ae.getLinks().get("path"), ".xml.html")+"\">XML</a></td><td><a href=\""+prefix+Utilities.changeFileExt(ae.getLinks().get("path"), ".json.html")+"\">JSON</a></td></tr>");
      }
      b.append("</table>\r\n");
      return b.toString();
    }
  }

  private String getValueSetRef(String prefix, String ref) {
    AtomEntry<ValueSet> vs = valueSets.get(ref);
    if (vs == null) {
      if (ref.equals("http://snomed.info/id"))
        return "http://snomed.info";
      else 
        return ref;
    } else
      return prefix+vs.getLinks().get("path");
  }

  private String describeValueSetByRef(String ref) {
    AtomEntry<ValueSet> vs = valueSets.get(ref);
    if (vs == null) {
      if (ref.equals("http://snomed.info/id"))
        return "Snomed CT";
      else 
        return ref;
    } else
      return vs.getResource().getNameSimple();
  }

  private String xreferencesForV2(String name, String level) {
    if (!valueSets.containsKey("http://hl7.org/fhir/v2/vs/"+name))
      return ". ";
    String n = valueSets.get("http://hl7.org/fhir/v2/vs/"+name).getResource().getNameSimple().replace("-", "").replace(" ", "").replace("_", "").toLowerCase();
    StringBuilder b = new StringBuilder();
    String pfx = "../../";
    if (level.equals("l3"))
      pfx = "../../../";
    AtomEntry<ValueSet> ae = findRelatedValueset(n, valueSets, "http://hl7.org/fhir/vs/");
    if (ae != null)
      b.append(". Related FHIR content: <a href=\""+pfx+ae.getLinks().get("path")+"\">"+ae.getResource().getNameSimple()+"</a>");
    ae = findRelatedValueset(n, valueSets, "http://hl7.org/fhir/v3/vs/");
    if (ae != null)
      b.append(". Related v3 content: <a href=\""+pfx+ae.getLinks().get("path")+"\">"+ae.getResource().getNameSimple()+"</a>");
    return b.toString()+". ";
  }

  private String xreferencesForFhir(String name) {
    String n = name.replace("-", "").toLowerCase();
    StringBuilder b = new StringBuilder();
    AtomEntry<ValueSet> ae = findRelatedValueset(n, valueSets, "http://hl7.org/fhir/v2/vs/");
    if (ae != null)
      b.append(". Related v2 content: <a href=\""+ae.getLinks().get("path")+"\">"+ae.getResource().getNameSimple()+"</a>");
    ae = findRelatedValueset(n, valueSets, "http://hl7.org/fhir/v3/vs/");
    if (ae != null)
      b.append(". Related v3 content: <a href=\""+ae.getLinks().get("path")+"\">"+ae.getResource().getNameSimple()+"</a>");
    return b.toString()+". ";
  }

  private String xreferencesForV3(String name, boolean vs) {
    String n = name.replace("-", "").replace(" ", "").replace("_", "").toLowerCase();
    StringBuilder b = new StringBuilder();
    AtomEntry<ValueSet> ae = findRelatedValueset(n, valueSets, "http://hl7.org/fhir/v2/vs/");
    String path = vs ? "../../../" : "../../";
    if (ae != null)
      b.append(". Related v2 content: <a href=\""+path+ae.getLinks().get("path")+"\">"+ae.getResource().getNameSimple()+"</a>");
    ae = findRelatedValueset(n, valueSets, "http://hl7.org/fhir/vs/");
    if (ae != null)
      b.append(". Related FHIR content: <a href=\""+path+ae.getLinks().get("path")+"\">"+ae.getResource().getNameSimple()+"</a>");
    return b.toString()+". ";
  }

  private AtomEntry<ValueSet> findRelatedValueset(String n, Map<String, AtomEntry<ValueSet>> vslist, String prefix) {
    for (String s : vslist.keySet()) {
      AtomEntry<ValueSet> ae = vslist.get(s); 
      String url = ae.getResource().getIdentifierSimple();
      if (url.startsWith(prefix)) {
        String name = url.substring(prefix.length()).replace("-", "").replace(" ", "").replace("_", "").toLowerCase();
        if (n.equals(name))
          return ae;
        name = ae.getResource().getNameSimple().replace("-", "").replace(" ", "").replace("_", "").toLowerCase();
        if (n.equals(name))
          return ae;
      }
    }
    return null;
  }

  private String genlevel(int level) {
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
        out.append(" <li><a href=\""+rd.getName().toLowerCase()+".html\">"+rd.getName()+"</a></li>\r\n");
      } else if (!rules.equals("{def}")) {
        in.append(" <tr><td><a href=\""+rd.getName().toLowerCase()+".html\">"+rd.getName()+"</a></td><td>"+rules.replace("|", "or")+"</td></tr>\r\n");
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
    b.append(" <tr><td><b>Name</b></td><td><b>Title</b></td><td><b>Description</b></td><td><b>Identity</b></td><td><b>Membership</b></td></tr>\r\n");
    for (Compartment c : definitions.getCompartments()) {
      b.append(" <tr><td><a href=\"compartment-"+c.getName()+".html\">"+c.getName()+"</a></td><td>"+c.getTitle()+"</td><td>"+Utilities.escapeXml(c.getDescription())+"</td>"+
              "<td>"+Utilities.escapeXml(c.getIdentity())+"</td><td>"+Utilities.escapeXml(c.getMembership())+"</td></tr>\r\n");
    }
    b.append("</table>\r\n");
    return b.toString();
  }

  private String genV3CodeSystem(String name) throws Exception {
    ValueSet vs = codeSystems.get("http://hl7.org/fhir/v3/"+name).getResource();
    new XmlComposer().compose(new FileOutputStream(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".xml"), vs, true);
    cloneToXhtml(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".xml", folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".xml.html", vs.getNameSimple(), vs.getDescriptionSimple(), 2, false, "v3:cs:"+name);
    new JsonComposer().compose(new FileOutputStream(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".json"), vs, false);
    jsonToXhtml(Utilities.path(folders.dstDir, "v3", name, "v3-"+name+".json"), Utilities.path("v3", name, "v3-"+name+".json.html"), "v3-"+name+".json", vs.getNameSimple(), vs.getDescriptionSimple(), 2, r2Json(vs), "v3:cs:"+name);

    return new XhtmlComposer().compose(vs.getText().getDiv());
  }

  private String genV3ValueSet(String name) throws Exception {
    ValueSet vs = valueSets.get("http://hl7.org/fhir/v3/vs/"+name).getResource();
    XmlComposer xml = new XmlComposer();
    xml.compose(new FileOutputStream(folders.dstDir+"v3"+File.separator+"vs"+File.separator+name+File.separator+"v3-"+name+".xml"), vs, true);
    cloneToXhtml(folders.dstDir+"v3"+File.separator+"vs"+File.separator+name+File.separator+"v3-"+name+".xml", folders.dstDir+"v3"+File.separator+"vs"+File.separator+name+File.separator+"v3-"+name+".xml.html", vs.getNameSimple(), vs.getDescriptionSimple(), 3, false, "v3:vs:"+name);
    JsonComposer json = new JsonComposer();
    json.compose(new FileOutputStream(folders.dstDir+"v3"+File.separator+"vs"+File.separator+name+File.separator+"v3-"+name+".json"), vs, false);
    jsonToXhtml(Utilities.path(folders.dstDir, "vs", name, "v3-"+name+".json"), Utilities.path("v3", "vs", name, "v3-"+name+".json.html"), "v3-"+name+".json", vs.getNameSimple(), vs.getDescriptionSimple(), 2, r2Json(vs), "v3:vs:"+name);

    return new XhtmlComposer().compose(vs.getText().getDiv()).replace("href=\"v3/", "href=\"../");
  }

  private String genV2TableVer(String name) throws Exception {
    String[] n = name.split("\\|");
    ValueSet vs = codeSystems.get("http://hl7.org/fhir/v2/"+n[0]+"/"+n[1]).getResource();
    XmlComposer xml = new XmlComposer();
    xml.compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".xml"), vs, true);
    cloneToXhtml(folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".xml", folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".xml.html", vs.getNameSimple(), vs.getDescriptionSimple(), 3, false, "v2:tbl"+name);
    JsonComposer json = new JsonComposer();
    json.compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".json"), vs, false);
    jsonToXhtml(Utilities.path(folders.dstDir, "v2", n[0], n[1], "v2-"+n[0]+"-"+n[1]+".json"), Utilities.path("v2", n[0], n[1], "v2-"+n[0]+"-"+n[1]+".json.html"), "v2-"+n[0]+"-"+n[1]+".json", vs.getNameSimple(), vs.getDescriptionSimple(), 3, r2Json(vs), "v2:tbl"+name);
    addToValuesets(v2Valuesets, vs, vs.getIdentifierSimple());

    return new XhtmlComposer().compose(vs.getText().getDiv());
  }

  private String genV2Table(String name) throws Exception {
    ValueSet vs = codeSystems.get("http://hl7.org/fhir/v2/"+name).getResource();
    XmlComposer xml = new XmlComposer();
    xml.compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".xml"), vs, true);
    cloneToXhtml(folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".xml", folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".xml.html", vs.getNameSimple(), vs.getDescriptionSimple(), 2, false, "v2:tbl"+name);
    JsonComposer json = new JsonComposer();
    json.compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".json"), vs, false);
    jsonToXhtml(Utilities.path(folders.dstDir, "v2", name, "v2-"+name+".json"), Utilities.path("v2", name, "v2-"+name+".json.html"), "v2-"+name+".json", vs.getNameSimple(), vs.getDescriptionSimple(), 2, r2Json(vs), "v2:tbl"+name);
    addToValuesets(v2Valuesets, vs, vs.getIdentifierSimple());
    return new XhtmlComposer().compose(vs.getText().getDiv());
  }

  private String r2Json(ValueSet vs) throws Exception {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    JsonComposer json = new JsonComposer();
    json.setSuppressXhtml("Snipped for Brevity");
    json.compose(bytes, vs, true);
    return new String(bytes.toByteArray());
  }

  private void cloneToXhtml(String src, String dst, String name, String description, int level, boolean adorn, String pageType) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();

    Document xdoc = builder.parse(new CSFileInputStream(new CSFile(src)));
//    XhtmlGenerator xhtml = new XhtmlGenerator(null);
//    xhtml.generate(xdoc, new CSFile(dst), name, description, level, adorn);
    
    String n = new File(dst).getName();
    n = n.substring(0, n.length()-9);
    XhtmlGenerator xhtml = new XhtmlGenerator(new ExampleAdorner(definitions));
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    xhtml.generate(xdoc, b, name, description, level, adorn, n+".xml.html");
    String html = TextFile.fileToString(folders.srcDir + "template-example-xml.html").replace("<%setlevel 0%>", "<%setlevel "+Integer.toString(level)+"%>").replace("<%example%>", b.toString());
    html = processPageIncludes(n+".xml.html", html, pageType, null);
    TextFile.stringToFile(html, dst);
    
//    epub.registerFile(dst, description, EPubManager.XHTML_TYPE);
    epub.registerExternal(dst);
  }

  public void jsonToXhtml(String src, String dst, String link, String name, String description, int level, String json, String pageType) throws Exception {

    json = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml(description) + "</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(json)+ "\r\n</pre>\r\n</div>\r\n";
    String html = TextFile.fileToString(folders.srcDir + "template-example-json.html").replace("<%setlevel 0%>", "<%setlevel "+Integer.toString(level)+"%>").replace("<%example%>", json);
    html = processPageIncludes(dst, html, pageType, null);
    TextFile.stringToFile(html, getFolders().dstDir + dst);
//    epub.registerFile(dst, description, EPubManager.XHTML_TYPE);
    epub.registerExternal(dst);
//
//    
//    FileOutputStream outs = new FileOutputStream(folders.dstDir+ dst);
//    OutputStreamWriter out = new OutputStreamWriter(outs);
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
          
          s.append(" <tr><td>http://hl7.org/fhir/v2/"+id+"</td><td>"+id+"</td><td>"+name+"<br/>Version Dependent. Use one of:<ul>");
          c = XMLUtil.getFirstChild(e);
          while (c != null) {
            Element g = XMLUtil.getFirstChild(c);
            if (g != null && !versions.contains(c.getAttribute("namespace")))
              versions.add(c.getAttribute("namespace"));            
            c = XMLUtil.getNextSibling(c);
          }
          for (String v : versions)
            if (!Utilities.noString(v))
              s.append(" <li><a href=\"v2/"+id+"/"+v+"/index.html\">"+v+"</a></li>");            
          s.append("</ul></td></tr>\r\n");
        } else
          s.append(" <tr><td><a href=\"v2/"+id+"/index.html\">http://hl7.org/fhir/v2/"+id+"</a></td><td>"+name+"</td><td></td></tr>\r\n");
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
    Map<String, AtomEntry> map = new HashMap<String, AtomEntry>();
    
    for (AtomEntry e : v3Valuesets.getEntryList()) {
      ValueSet vs = (ValueSet)e.getResource();
      if (vs.getDefine() != null) {
        String n = vs.getDefine().getSystemSimple();
        names.add(n);
        map.put(n, e);
      }
    }
    Collections.sort(names);

    for (String n : names) {
      AtomEntry e = map.get(n);
      ValueSet vs = (ValueSet)e.getResource();
      String id = tail(vs.getIdentifierSimple());
      String oid = e.getLinks().get("oid");
      s.append(" <tr><td><a href=\"v3/"+id+"/index.html\">"+Utilities.escapeXml(id)+"</a></td><td>"+Utilities.escapeXml(vs.getDescriptionSimple())+"</td><td>"+oid+"</td></tr>\r\n");
    }
    
    s.append("</table>\r\n");
    return s.toString();
  }

  private String genV3VSIndex() {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"grid\">\r\n");
    s.append(" <tr><td><b>Name (URI = http://hl7.org/fhir/v3/vs/...) </b></td><td><b>Name</b></td><td><b>OID</b></td></tr>\r\n");
    
    List<String> names = new ArrayList<String>();
    Map<String, AtomEntry> map = new HashMap<String, AtomEntry>();
    
    for (AtomEntry e : v3Valuesets.getEntryList()) {
      ValueSet vs = (ValueSet)e.getResource();
      if (vs.getDefine() == null) {
        String n = vs.getIdentifierSimple();
        names.add(n);
        map.put(n, e);
      }
    }
    Collections.sort(names);

    for (String n : names) {
      AtomEntry e = map.get(n);
      ValueSet vs = (ValueSet)e.getResource();
      String id = tail(vs.getIdentifierSimple());
      String oid = e.getLinks().get("oid");
      String[] desc = vs.getDescriptionSimple().split("\\(OID \\= ");
      s.append(" <tr><td><a href=\"v3/vs/"+id+"/index.html\">"+id+"</a></td><td>"+desc[0]+"</td><td>"+oid+"</td></tr>\r\n");
    }
    
    s.append("</table>\r\n");
    return s.toString();
  }

  private String tail(String id) {
    int i = id.lastIndexOf("/");
    return id.substring(i+1);
  }

  private String genDataTypeMappings(String name) throws Exception {
    List<ElementDefn> list = new ArrayList<ElementDefn>();
//    list.addAll(definitions.getStructures().values());
//    list.addAll(definitions.getTypes().values());
//    list.addAll(definitions.getInfrastructure().values());
    list.add(definitions.getElementDefn(name));
    MappingsGenerator maps = new MappingsGenerator(definitions);
    maps.generate(list);
    return maps.getMappings();
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
      b.append(", "+aliases.get(i));
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

  private class TocSort implements Comparator<String> {

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
  
  private String generateToc() {
//    List<String> entries = new ArrayList<String>();
//    entries.addAll(toc.keySet());
//    
//    Collections.sort(entries, new TocSort());
//    
//    StringBuilder b = new StringBuilder();
//    for (String s : entries) {
//      int i = 0;
//      for (char c : s.toCharArray()) {
//        if (c == '.')
//          i++;
//      }
//      TocEntry t = toc.get(s); 
//      if (i < 3) {
//        for (int j = 0; j < i; j++)
//          b.append("&nbsp;&nbsp;");
//        b.append("<a href=\""+t.getLink()+"#"+t.getValue()+"\">"+t.getValue()+"</a> "+Utilities.escapeXml(t.getText())+"<br/>\r\n");
//      }
//    }
//
//    return "<p>"+b.toString()+"</p>\r\n";
    return breadCrumbManager.makeToc();
  }

  private String generateBSUsage(String name) throws Exception {
    BindingSpecification cd = definitions.getBindingByReference("#"+name);
    if (cd == null)
      cd = definitions.getBindingByName(name);

    String csn = null;
    String vsn = null;
    
    StringBuilder b = new StringBuilder();
    if (cd != null) {
      if (cd.getReferredValueSet() != null) {
        if (cd.getReferredValueSet().getDefine() != null)
            csn = cd.getReferredValueSet().getDefine().getSystemSimple();
        vsn = cd.getReferredValueSet().getIdentifierSimple();
      }
      for (ResourceDefn r : definitions.getResources().values()) {
        scanForUsage(b, cd, r.getRoot(), r.getName().toLowerCase()+".html#def");
        scanForProfileUsage(b, cd, r);
      }
      for (ElementDefn e : definitions.getInfrastructure().values()) {
        if (e.getName().equals("ResourceReference")) {
          scanForUsage(b, cd, e, "references.html#"+e.getName());
        } else if (e.getName().equals("Extension")) {
          scanForUsage(b, cd, e, "extensibility.html#"+e.getName());
        } else if (e.getName().equals("Narrative")) {
          scanForUsage(b, cd, e, "narrative.html#"+e.getName());
        } else {
          scanForUsage(b, cd, e, "formats.html#"+e.getName());
        }
      }
      for (ElementDefn e : definitions.getTypes().values())
        if (!definitions.dataTypeIsSharedInfo(e.getName())) {
          if (e.getName().equals("ResourceReference")) 
            scanForUsage(b, cd, e, "references.html#"+e.getName());
          else
            scanForUsage(b, cd, e, "datatypes.html#"+e.getName());
        }
      for (ElementDefn e : definitions.getStructures().values())
        if (!e.getName().equals("DocumentInformation"))
          if (!definitions.dataTypeIsSharedInfo(e.getName()))
            scanForUsage(b, cd, e, "datatypes.html#"+e.getName());

    } else {
      for (AtomEntry ae : valueSets.values()) {
        if ((name+".html").equals(ae.getLinks().get("path"))) {
          ValueSet vs = (ValueSet) ae.getResource();
            if (vs.getDefine() != null)
                csn = vs.getDefine().getSystemSimple();
            vsn = vs.getIdentifierSimple();         
        }
      }
    }

    for (AtomEntry ae : valueSets.values()) {
      ValueSet vs = (ValueSet) ae.getResource();
      if (vs.getCompose() != null) {
        for (Uri t : vs.getCompose().getImport()) {
          if (t.getValue().equals(vsn)) 
            b.append(" <li>Imported into Valueset <a href=\""+(ae.getLinks().get("path").startsWith("valueset-") ? ae.getLinks().get("path"): "valueset-"+ae.getLinks().get("path"))+"\">"+Utilities.escapeXml(vs.getNameSimple())+"</a></li>");
        }
        for (ConceptSetComponent t : vs.getCompose().getInclude()) {
          if (t.getSystemSimple().equals(csn)) 
            b.append(" <li>Included in Valueset <a href=\""+(ae.getLinks().get("path").startsWith("valueset-") ? ae.getLinks().get("path"): "valueset-"+ae.getLinks().get("path"))+"\">"+Utilities.escapeXml(vs.getNameSimple())+"</a></li>");
        }
        for (ConceptSetComponent t : vs.getCompose().getExclude()) {
          if (t.getSystemSimple().equals(csn)) 
            b.append(" <li>Excluded in Valueset <a href=\""+(ae.getLinks().get("path").startsWith("valueset-") ? ae.getLinks().get("path"): "valueset-"+ae.getLinks().get("path"))+"\">"+Utilities.escapeXml(vs.getNameSimple())+"</a></li>");
        }
      }
    }
    if (b.length() == 0)
      return "<p>\r\nThese codes are not currently used\r\n</p>\r\n";
    else
      return "<p>\r\nThese codes are used in the following places:\r\n</p>\r\n<ul>\r\n"+b.toString()+"</ul>\r\n";
  }

  private void scanForProfileUsage(StringBuilder b, BindingSpecification cd, ResourceDefn r) {
    for (RegisteredProfile p : r.getProfiles()) {
      for (ExtensionDefn ex : p.getProfile().getExtensions()) {
        if (ex.getDefinition().hasBinding() && ex.getDefinition().getBindingName() != null && ex.getDefinition().getBindingName().equals(cd.getName())) {
          b.append(" <li><a href=\""+p.getDestFilenameNoExt()+".html#"+ex.getCode()+"\">Extension "+ex.getCode()+"</a> "+getBSTypeDesc(cd)+"</li>\r\n");
        }
      }
    }
  }

  private void scanForUsage(StringBuilder b, BindingSpecification cd, ElementDefn e, String ref) {
    scanForUsage(b, cd, e, "", ref);
    
  }

  private void scanForUsage(StringBuilder b, BindingSpecification cd, ElementDefn e, String path, String ref) {
    path = path.equals("") ? e.getName() : path+"."+e.getName();
    if (e.hasBinding() && e.getBindingName() != null && e.getBindingName().equals(cd.getName())) {
      b.append(" <li><a href=\""+ref+"\">"+path+"</a> "+getBSTypeDesc(cd)+"</li>\r\n");
    }
    for (ElementDefn c : e.getElements()) {
      scanForUsage(b, cd, c, path, ref);
    }
  }

  private String getBSTypeDesc(BindingSpecification cd) {
    switch (cd.getBindingStrength()) {
    case Required: return "(<a href=\"terminologies.html#code\">Fixed</a>)";
    case Preferred: return "(<a href=\"terminologies.html#incomplete\">Incomplete</a>)";
    case Example: return "(<a href=\"terminologies.html#example\">Example</a>)";
    default:
      return "Unknown";
    }
  }

  private String generateCodeDefinition(String name) {
    BindingSpecification cd = definitions.getBindingByReference("#"+name);
    return Utilities.escapeXml(cd.getDefinition());
  }

  private String generateValueSetDefinition(String name) {
    BindingSpecification cd = definitions.getBindingByName(name);
    if (cd == null)
      return definitions.getExtraValuesets().get(name).getDescriptionSimple();
    else
      return Utilities.escapeXml(cd.getDefinition());
  }

  private String generateCodeTable(String name) throws Exception {
    BindingSpecification cd = definitions.getBindingByReference("#"+name);
    if (cd.getReferredValueSet() != null) {
      return new XhtmlComposer().compose(cd.getReferredValueSet().getText().getDiv());
    } else {
      StringBuilder s = new StringBuilder();
      s.append("    <table class=\"codes\">\r\n");
      boolean hasComment = false;
      boolean hasDefinition = false;
      boolean hasParent = false;
      boolean hasSource = false;
      boolean hasId = false;
      for (DefinedCode c : cd.getCodes()) {
        hasComment = hasComment || c.hasComment();
        hasDefinition = hasDefinition || c.hasDefinition();
        hasParent = hasParent || c.hasParent();
        hasSource = hasSource || !Utilities.noString(c.getSystem());
        hasId = hasId || !Utilities.noString(c.getId());
      }
      String src = "";
      if (hasId) 
        src = src + "<td><b>Id</b></td>";
      if (hasSource) 
        src = src + "<td><b>Source</b></td>";

      String lvl = hasParent ?  "<td><b>Level</b></td>" : "";
      if (hasComment)
        s.append("    <tr>"+src+lvl+"<td><b>Code!</b></td><td><b>Definition</b></td><td><b>Comments</b></td></tr>");
      else if (hasDefinition)
        s.append("    <tr>"+src+lvl+"<td><b>Code</b></td><td colspan=\"2\"><b>Definition</b></td></tr>");
      else
        s.append("    <tr>"+src+lvl+"<td colspan=\"3\"><b>Code</b></td></tr>");

      for (DefinedCode c : cd.getChildCodes()) {
        generateCode(cd, s, hasSource, hasId, hasComment, hasDefinition, hasParent, 1, c);
      }
      s.append("    </table>\r\n");
      return s.toString();
    }
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

  private String genProfileConstraints(ResourceDefn res) throws Exception {
    ElementDefn e = res.getRoot();
    StringBuilder b = new StringBuilder();
    generateConstraints(res.getName(), e, b, true);
    if (b.length() > 0)
      return "<p>Constraints</p><ul>"+b+"</ul>";
    else
      return "";
  }
  
  
  private String genResourceTable(ResourceDefn res) throws Exception {
    ElementDefn e = res.getRoot();
    ResourceTableGenerator gen = new ResourceTableGenerator(folders.dstDir, this, res.getName()+"-definitions.html", false);
    return new XhtmlComposer().compose(gen.generate(e));
  }
  
  private String genResourceConstraints(ResourceDefn res) throws Exception {
    ElementDefn e = res.getRoot();
    StringBuilder b = new StringBuilder();
    generateConstraints(res.getName(), e, b, true);
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
        b2.append(" <tr><td>"+c.getName()+"</td><td>"+Utilities.escapeXml(c.getDefinition())+"</td><td>Profile (<a href=\""+c.getName()+".profile.xml.html\">XML</a>, <a href=\""+c.getName()+".profile.json.html\">JSON</a>)</td></tr>\r\n");
      }
    }
    if (b.length() > 0) 
      return b.toString()+"<table class=\"list\">\r\n"+b2.toString()+"</table>\r\n";
    else
      return "";
  }

  private String genConstraints(String name) throws Exception {
    ElementDefn e = definitions.getElementDefn(name);
    StringBuilder b = new StringBuilder();
    generateConstraints(name, e, b, true);
    if (b.length() > 0)
      return "<ul>"+b+"</ul>";
    else
      return "";
  }

  private void generateConstraints(String path, ElementDefn e, StringBuilder b, boolean base) {
    List<Integer> ids = new ArrayList<Integer>();
    for (String n : e.getInvariants().keySet()) {
      ids.add(Integer.parseInt(n));
    }
    Collections.sort(ids);
    
    for (Integer n : ids) {
      Invariant inv = e.getInvariants().get(n.toString());
      if (base)
        b.append("<li><b title=\"Formal Invariant Identifier\">Inv-"+inv.getId()+"</b>: "+Utilities.escapeXml(inv.getEnglish())+" (xpath: <span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getXpath())+"</span>)</li>");
      else
        b.append("<li><b title=\"Formal Invariant Identifier\">Inv-"+inv.getId()+"</b>: On "+path+": "+Utilities.escapeXml(inv.getEnglish())+" (xpath on "+presentPath(path)+": <span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getXpath())+"</span>)</li>");
    }
    for (ElementDefn c : e.getElements()) {
      generateConstraints(path+"."+c.getName(), c, b, false);
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
  
//  private String dtHeader(String n, String mode) {
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
//      b.append("<li class=\"selected\"><span>Formal Definitions</span></li>");
//    else
//      b.append("<li class=\"nselected\"><span><a href=\""+n+"-definitions.html\">Formal Definitions</a></span></li>");
//    if ("mappings".equals(mode))
//        b.append("<li class=\"selected\"><span>Mappings</span></li>");
//      else
//        b.append("<li class=\"nselected\"><span><a href=\""+n+"-mappings.html\">Mappings</a></span></li>");
//    b.append("<li class=\"spacerright\" style=\"width: 270px\"><span>&nbsp;</span></li>");
//    b.append("</ul></div>\r\n");
//    return b.toString();
//  }

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
//      b.append("<li class=\"selected\"><span>Formal Definitions</span></li>");
//    else
//      b.append("<li class=\"nselected\"><span><a href=\""+n+"-definitions.html\">Formal Definitions</a></span></li>");
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
//      b.append("<li class=\"selected\"><span>Formal Definitions</span></li>");
//    else
//      b.append("<li class=\"nselected\"><span><a href=\""+n+"-definitions.html\">Formal Definitions</a></span></li>");
//    b.append("<li class=\"spacerright\" style=\"width: 270px\"><span>&nbsp;</span></li>");
//    b.append("</ul></div>\r\n");
//    return b.toString();
//  }

  private String extHeader(String n, String mode) {
    StringBuilder b = new StringBuilder();
    if (n.contains("-"))
      n = n.substring(0, n.indexOf('-'));

    b.append("<ul class=\"nav nav-tabs\">");
    
    b.append(makeHeaderTab("Content", n+".html", mode==null || "content".equals(mode)));
    b.append(makeHeaderTab("Examples", n+"-examples.html", "examples".equals(mode)));
    b.append(makeHeaderTab("Formal Definitions", n+"-definitions.html", "definitions".equals(mode)));

    b.append("</ul>\r\n");

    return b.toString();   
  }

  private String profileHeader(String n, String mode) {
    StringBuilder b = new StringBuilder();

    if (n.endsWith(".xml"))
      n = n.substring(0, n.length()-4);
    
    b.append("<ul class=\"nav nav-tabs\">");
    
    b.append(makeHeaderTab("Content", n+".html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", n+"-examples.html", "examples".equals(mode)));
    b.append(makeHeaderTab("Formal Definitions", n+"-definitions.html", "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", n+"-mappings.html", "mappings".equals(mode)));
    b.append(makeHeaderTab("XML", n+".profile.xml.html", "xml".equals(mode)));
    b.append(makeHeaderTab("JSON", n+".profile.json.html", "json".equals(mode)));

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
    b.append(makeHeaderTab("Systems", pfx + "terminologies-systems.html", "systems".equals(mode)));
    b.append(makeHeaderTab("Value Sets", pfx + "terminologies-valuesets.html", "valuesets".equals(mode)));
    b.append(makeHeaderTab("v2 Tables", pfx + "terminologies-v2.html", "v2".equals(mode)));
    b.append(makeHeaderTab("v3 Namespaces", pfx + "terminologies-v3.html", "v3".equals(mode)));
    b.append(makeHeaderTab("Concept Maps", pfx + "terminologies-conceptmaps.html", "conceptmaps".equals(mode)));
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

  private String codelist(String n, String mode) throws Exception {
    BindingSpecification bs = definitions.getBindingByName(mode);
    if (bs == null)
      throw new Exception("Unable to find code list '"+mode+"'");
    if (bs.getCodes().size() == 0)
      throw new Exception("Code list '"+mode+"' is empty/not defined");
    boolean hasComments = false;
    for (DefinedCode c : bs.getCodes())
      hasComments = hasComments || c.hasComment();
    
    StringBuilder b = new StringBuilder();
    if (!Utilities.noString(bs.getDescription()))
    	b.append("<h3>"+bs.getDescription()+"</h3>\r\n");
    b.append("<table class=\"codes\">\r\n");
    for (DefinedCode c : bs.getCodes()) {
      if (hasComments)
        b.append(" <tr><td>"+c.getCode()+"</td><td>"+Utilities.escapeXml(c.getDefinition())+"</td><td>"+Utilities.escapeXml(c.getComment())+"</td></tr>\r\n");
      else
        b.append(" <tr><td>"+c.getCode()+"</td><td>"+Utilities.escapeXml(c.getDefinition())+"</td></tr>\r\n");
    }
    b.append("</table>\r\n");
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
  
  private String resHeader(String n, String title, String mode) {
    StringBuilder b = new StringBuilder();
    if (n.contains("-"))
      n = n.substring(0, n.indexOf('-'));

    b.append("<ul class=\"nav nav-tabs\">");
    
    b.append(makeHeaderTab("Content", n+".html", mode==null || "content".equals(mode)));
    b.append(makeHeaderTab("Examples", n+"-examples.html", "examples".equals(mode)));
    b.append(makeHeaderTab("Formal Definitions", n+"-definitions.html", "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", n+"-mappings.html", "mappings".equals(mode)));
    b.append(makeHeaderTab("Profiles", n+"-profiles.html", "profiles".equals(mode)));

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
        AtomEntry ae = codeSystems.get(n);
        if (ae == null)
          s.append(" <tr><td><a href=\""+"??"+".html\">"+n+"</a></td><td>"+"??"+"</td></tr>\r\n");
        else {
          ValueSet vs = (ValueSet) ae.getResource();
          s.append(" <tr><td><a href=\""+ae.getLinks().get("path")+"\">"+n+"</a></td><td>"+vs.getDescriptionSimple()+"</td></tr>\r\n");
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
      AtomEntry<ConceptMap> ae = conceptMaps.get(sn);
      String n = sn.substring(23);
      ConceptMap cm = ae.getResource();
      s.append(" <tr><td><a href=\""+ae.getLinks().get("path")+"\">"+cm.getNameSimple()+"</a></td>"+
          "<td><a href=\""+getValueSetRef("", ((ResourceReference) cm.getSource()).getReferenceSimple())+"\">"+describeValueSetByRef(((ResourceReference) cm.getSource()).getReferenceSimple())+"</a></td>"+
          "<td><a href=\""+getValueSetRef("", ((ResourceReference) cm.getTarget()).getReferenceSimple())+"\">"+describeValueSetByRef(((ResourceReference) cm.getTarget()).getReferenceSimple())+"</a></td></tr>\r\n");
    }
    s.append("</table>\r\n");
    return s.toString();
  }


  private String genValueSetsTable() throws Exception {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"codes\">\r\n");
    s.append(" <tr><td><b>Name</b> (in http://hl7.org/fhir/vs/)</td><td><b>Definition</b></td><td><b>Source</b></td><td><b>Id</b></td></tr>\r\n");
    List<String> sorts = new ArrayList<String>();
    sorts.addAll(valueSets.keySet());
    Collections.sort(sorts);
    
    for (String sn : sorts) {
      if (!sn.startsWith("http://hl7.org/fhir/v3") && !sn.startsWith("http://hl7.org/fhir/v2")) {
          AtomEntry ae = valueSets.get(sn);
          String n = sn.substring(23);
          ValueSet vs = (ValueSet) ae.getResource();
          s.append(" <tr><td><a href=\""+ae.getLinks().get("path")+"\">"+n+"</a></td><td>"+Utilities.escapeXml(vs.getDescriptionSimple())+"</td><td>"+sourceSummary(vs)+"</td><td>"+Utilities.oidTail(ae.getLinks().get("oid"))+"</td></tr>\r\n");
      }
    }
    s.append("</table>\r\n");
    return s.toString();
  }


  private String sourceSummary(ValueSet vs) {
    StringBuilder b = new StringBuilder();
    List<String> done = new ArrayList<String>();
    if (vs.getDefine() != null) {
      String n = "Internal";
      if (vs.getDescriptionSimple().contains("Connectathon")) n = "IHE";
      if (vs.getDescriptionSimple().contains("IHE")) n = "IHE";
      if (vs.getDefine().getSystemSimple().startsWith("http://") && !(vs.getDefine().getSystemSimple().startsWith("http://hl7.org"))) n = "External";
      if (vs.getDefine().getSystemSimple().equals("http://nema.org/dicom/dcid")) n = "DICOM";
      
      b.append(", "+n);
    }
    if (vs.getCompose() != null)
      for (ConceptSetComponent c : vs.getCompose().getInclude()) {
        String uri = c.getSystemSimple();
        String n = "Other";
        if (uri != null) {
          if ("http://snomed.info/sct".equals(uri)) n = "SNOMED CT"; 
          if ("http://loinc.org".equals(uri)) n = "LOINC";
          if ("http://nema.org/dicom/dcid".equals(uri)) n = "DICOM";
          if ("http://hl7.org/fhir/resource-types".equals(uri)) n = "FHIR";
          if ("http://hl7.org/fhir/restful-operation".equals(uri)) n = "FHIR";
          if ("http://unitsofmeasure.org".equals(uri)) n = "FHIR";
          if (uri.startsWith("http://hl7.org/fhir/v3/"))  n = "V3";
          if (uri.startsWith("http://hl7.org/fhir/v2/"))  n = "V2";
        }
        if (!done.contains(n))
          b.append(", "+n);
        done.add(n);
      }
    return b.length() == 0 ? "" : b.substring(2);
  }

  private AtomEntry getv3ValueSetByRef(String ref) {
    String vsRef = ref.replace("/vs", "");
    for (AtomEntry ae : v3Valuesets.getEntryList()) {
      if (ref.equals(ae.getLinks().get("self")) || vsRef.equals(ae.getLinks().get("self"))) 
        return ae;
    }
    return null;
  }

  private String genBindingsTable() {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"codes\">\r\n");
    s.append(" <tr><td><b>Name</b></td><td><b>Definition</b></td><td><b>Type</b></td><td><b>Reference</b></td></tr>\r\n");
    List<String> names = new ArrayList<String>();
    for (String n : definitions.getBindings().keySet()) {
      names.add(n);
    }
    Collections.sort(names);
    for (String n : names) {
      if (!n.startsWith("*")) {
        BindingSpecification cd = definitions.getBindingByName(n);
        if (cd.getElementType() != ElementType.Unknown) {
          s.append(" <tr><td>"+Utilities.escapeXml(cd.getName())+"</td><td>"+Utilities.escapeXml(cd.getDefinition())+"</td><td>");
          if (cd.getBinding() == Binding.Reference) 
            s.append("Reference");
          else if (cd.getElementType() == ElementType.Simple) 
            s.append("Code List");
          else if (cd.getBinding() == Binding.Unbound)
            s.append("??");
          else if (cd.isExample())
            s.append("Value Set (Example Only)");
          else
            s.append("Value Set");

          if (cd.getBinding() == Binding.Special) {

            if (cd.getName().equals("MessageEvent"))
              s.append("</td><td><a href=\"message-events.html\">http://hl7.org/fhir/valueset/message-events.html</a></td></tr>\r\n");
            else if (cd.getName().equals("ResourceType"))
              s.append("</td><td><a href=\"resource-types.html\">http://hl7.org/fhir/valueset/resource-types.html</a></td></tr>\r\n");
            else if (cd.getName().equals("DataType"))
              s.append("</td><td><a href=\"data-types.html\">http://hl7.org/fhir/valueset/data-types.html</a></td></tr>\r\n");
            else if (cd.getName().equals("FHIRDefinedType"))
              s.append("</td><td><a href=\"defined-types.html\">http://hl7.org/fhir/valueset/defined-types.html</a></td></tr>\r\n");
            else 
              s.append("</td><td>???</td></tr>\r\n");

          } else if (cd.getBinding() == Binding.CodeList)
            s.append("</td><td><a href=\""+cd.getReference().substring(1)+".html\">http://hl7.org/fhir/"+cd.getReference().substring(1)+"</a></td></tr>\r\n");          
          else if (cd.getBinding() == Binding.ValueSet) { 
            if (cd.getReferredValueSet() != null) {
              if (cd.getReference().startsWith("http://hl7.org/fhir/v3/vs")) 
                s.append("</td><td><a href=\"v3/"+cd.getReference().substring(26)+"/index.html\">"+cd.getReference()+"</a></td></tr>\r\n");          
              else if (cd.getReference().startsWith("http://hl7.org/fhir")) 
                s.append("</td><td><a href=\""+cd.getReference().substring(23)+".html\">"+cd.getReference()+"</a></td></tr>\r\n");          
              else
                s.append("</td><td><a href=\""+cd.getReference()+".html\">"+cd.getReferredValueSet().getIdentifierSimple()+"</a></td></tr>\r\n");          
            } else 
              s.append("</td><td><a href=\""+cd.getReference()+".html\">??</a></td></tr>\r\n");          
          } else if (cd.hasReference())
            s.append("</td><td><a href=\""+cd.getReference()+"\">"+Utilities.escapeXml(cd.getDescription())+"</a></td></tr>\r\n");
          else if (Utilities.noString(cd.getDescription()))
            s.append("</td><td style=\"color: grey\">??</td></tr>\r\n");
          else
            s.append("</td><td>? "+Utilities.escapeXml(cd.getBinding().toString())+": "+Utilities.escapeXml(cd.getDescription())+"</td></tr>\r\n");
        }
      }
    }
    s.append("</table>\r\n");
    return s.toString();
  }
  
  private String genBindingTable(boolean codelists) {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"codes\">\r\n");
    List<String> names = new ArrayList<String>();
    for (String n : definitions.getBindings().keySet()) {
      if ((codelists && definitions.getBindingByName(n).getBinding() == Binding.CodeList) || (!codelists && definitions.getBindingByName(n).getBinding() != Binding.CodeList))
       names.add(n);
    }
    Collections.sort(names);
    for (String n : names) {
      if (!n.startsWith("*")) {
        BindingSpecification cd = definitions.getBindingByName(n);
        if (cd.getBinding() == Binding.CodeList || cd.getBinding() == Binding.Special)
          s.append("  <tr><td title=\""+Utilities.escapeXml(cd.getDefinition())+"\">"+cd.getName()+"<br/><font color=\"grey\">http://hl7.org/fhir/sid/"+cd.getReference().substring(1)+"</font></td><td>");
        else
          s.append("  <tr><td title=\""+Utilities.escapeXml(cd.getDefinition())+"\">"+cd.getName()+"</td><td>");
        if (cd.getBinding() == Binding.Unbound) {
          s.append("Definition: "+Utilities.escapeXml(cd.getDefinition()));
        } else if (cd.getBinding() == Binding.CodeList) {
          if (cd.getBindingStrength() == BindingStrength.Preferred)
            s.append("Preferred codes: ");
          else if (cd.getBindingStrength() == BindingStrength.Example)
            s.append("Suggested codes: ");
          else // if (cd.getBindingStrength() == BindingStrength.Required)
            s.append("Required codes: ");
          s.append("    <table class=\"codes\">\r\n");
          boolean hasComment = false;
          boolean hasDefinition = false;
          for (DefinedCode c : cd.getCodes()) {
            hasComment = hasComment || c.hasComment();
            hasDefinition = hasDefinition || c.hasDefinition();
          }
          for (DefinedCode c : cd.getCodes()) {
            if (hasComment)
              s.append("    <tr><td>"+Utilities.escapeXml(c.getCode())+"</td><td>"+Utilities.escapeXml(c.getDefinition())+"</td><td>"+Utilities.escapeXml(c.getComment())+"</td></tr>");
            else if (hasDefinition)
              s.append("    <tr><td>"+Utilities.escapeXml(c.getCode())+"</td><td colspan=\"2\">"+Utilities.escapeXml(c.getDefinition())+"</td></tr>");
            else
              s.append("    <tr><td colspan=\"3\">"+Utilities.escapeXml(c.getCode())+"</td></tr>");
          }
          s.append("    </table>\r\n");
        } else if (cd.getBinding() == Binding.ValueSet) {
          if (cd.getBindingStrength() == BindingStrength.Preferred)
            s.append("Preferred codes: ");
          else if (cd.getBindingStrength() == BindingStrength.Example)
            s.append("Suggested codes: ");
          else // if (cd.getBindingStrength() == BindingStrength.Required)
            s.append("Required codes: ");
          if (cd.hasReference())
            s.append("<a href=\""+cd.getReference()+"\">Value Set "+cd.getDescription()+"</a>");
          else
            s.append("Value Set "+cd.getDescription());
        } else if (cd.getBinding() == Binding.Reference) {
            s.append("See <a href=\""+cd.getReference()+"\">"+cd.getReference()+"</a>");
        } else if (cd.getBinding() == Binding.Special) {
          if (cd.getName().equals("MessageEvent"))
            s.append("See the <a href=\"message.html#Events\"> Event List </a>in the messaging framework");
          else if (cd.getName().equals("ResourceType"))
            s.append("See the <a href=\"terminologies.html#ResourceType\"> list of defined Resource Types</a>");
          else if (cd.getName().equals("FHIRContentType"))
            s.append("See the <a href=\"terminologies.html#fhircontenttypes\"> list of defined Resource and Data Types</a>");
          else 
            s.append("<a href=\"datatypes.html\">Any defined data Type name</a> (including <a href=\"resources.html#Resource\">Resource</a>)");
        }        
        s.append("</td></tr>\r\n");
      }
      
    }
    s.append("</table>\r\n");
    return s.toString();
  }

  private String getEventsTable() {
    List<String> codes = new ArrayList<String>();
    codes.addAll(definitions.getEvents().keySet());
    Collections.sort(codes);
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"grid\">\r\n");
    s.append(" <tr><th>Code</th><th>Category</th><th>Description</th><th>Request</th><th>Response</th><th>Notes</th></tr>\r\n");
    for (String c : codes) {
      EventDefn e = definitions.getEvents().get(c);
      if (e.getUsages().size() == 1) {
        EventUsage u = e.getUsages().get(0);
        s.append(" <tr><td>"+e.getCode()+"<a name=\""+e.getCode()+"\"> </a></td><td>"+(e.getCategory() == null ? "??" : e.getCategory().toString())+"</td><td>"+e.getDefinition()+"</td>");
        s.append("<td>"+describeMsg(u.getRequestResources(), u.getRequestAggregations())+"</td><td>"+
            describeMsg(u.getResponseResources(), u.getResponseAggregations())+"</td><td>"+
            combineNotes(e.getFollowUps(), u.getNotes())+"</td></tr>\r\n");
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
              combineNotes(e.getFollowUps(), u.getNotes())+"</td></tr>\r\n");
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
      
      if( definitions.getFutureResources().containsKey(c.getCode()) )
    	  htmlFilename = "resourcelist";
      
      html.append("  <tr><td><a href=\""+htmlFilename+".html\">"+c.getCode()+"</a></td><td>"+Utilities.escapeXml(c.getDefinition())+"</td></tr>");
    }       
    html.append("  <tr><td><a href=\"http.html#binary\">Binary</a></td><td>Pure binary content (special case)</td></tr>");
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
        else if (c.getName().equals("ResourceReference") )
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

  private String genReferenceImplList() {
    StringBuilder s = new StringBuilder();
    for (PlatformGenerator gen : referenceImplementations) {
      s.append("<tr><td><a href=\""+gen.getReference(version)+"\">"+gen.getTitle()+"</a></td><td>"+Utilities.genMarkdown(gen.getDescription())+"</td></tr>\r\n");
    }
    return s.toString();
  }


  String processPageIncludesForPrinting(String file, String src) throws Exception {
    String wikilink = "http://wiki.hl7.org/index.php?title=FHIR_"+prepWikiName(file)+"_Page";
    boolean even = false;

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
      if (com.length == 2 && com[0].equals("dt"))
        src = s1+xmlForDt(com[1], null)+tsForDt(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dt.constraints")) 
        src = s1+genConstraints(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dt.restrictions")) 
        src = s1+genRestrictions(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dictionary"))
        src = s1+dictForDt(com[1])+s3;
      else if (com[0].equals("pageheader") || com[0].equals("dtheader") || com[0].equals("formatsheader") || com[0].equals("resourcesheader") || com[0].equals("extheader") || com[0].equals("txheader") || com[0].equals("cmpheader") || com[0].equals("atomheader"))
        src = s1+s3;
      else if (com[0].equals("resheader"))
        src = s1+resHeader(name, "Document", com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("codelist"))
        src = s1+codelist(name, com.length > 1 ? com[1] : null)+s3;
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
      else if (com[0].equals("file"))
        src = s1+TextFile.fileToString(folders.srcDir + com[1]+".html")+s3;
      else if (com[0].equals("setwiki")) {
        wikilink = com[1];
        src = s1+s3;
      }
      else if (com[0].equals("dtmappings"))
        src = s1 + genDataTypeMappings(com[1]) + s3;
      else if (com[0].equals("dtusage")) 
        src = s1 + genDataTypeUsage(com[1]) + s3;
      else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
      else if (com[0].equals("wiki"))
        src = s1+wikilink+s3;
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
        src = s1 + getEventsTable()+ s3;
      else if (com[0].equals("resourcecodes"))
        src = s1 + genResCodes() + s3;
      else if (com[0].equals("datatypecodes"))
        src = s1 + genDTCodes() + s3;
      else if (com[0].equals("bindingtable-codelists"))
        src = s1 + genBindingTable(true) + s3;
      else if (com[0].equals("bindingtable"))
        src = s1 + genBindingsTable() + s3;
      else if (com[0].equals("bindingtable-others"))
        src = s1 + genBindingTable(false) + s3;
      else if (com[0].equals("codeslist"))
        src = s1 + genCodeSystemsTable() + s3;
      else if (com[0].equals("valuesetslist"))
        src = s1 + genValueSetsTable() + s3;
      else if (com[0].equals("resimplall"))
        src = s1 + genResImplList() + s3;
      else if (com[0].equals("impllist"))
        src = s1 + genReferenceImplList() + s3;
      else if (com[0].equals("txurl"))
        src = s1 + "http://hl7.org/fhir/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vstxurl"))
        src = s1 + "http://hl7.org/fhir/vs/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vsurl")) {
        BindingSpecification bs = definitions.getBindingByName(Utilities.fileTitle(file));
        if (bs == null) {
          src = s1 + "http://hl7.org/fhir/vs/"+Utilities.fileTitle(file) + s3;
        } else {
          String reference = bs.getReference();
          if (reference.startsWith("valueset-"))
            reference = reference.substring(9);
          src = s1 + "http://hl7.org/fhir/vs/"+reference + s3;
        }
      } else if (com[0].equals("txdef"))
        src = s1 + generateCodeDefinition(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("vsdef"))
        src = s1 + generateValueSetDefinition(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("txusage"))
        src = s1 + generateBSUsage(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("vsusage"))
        src = s1 + generateBSUsage(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("txsummary"))
        src = s1 + generateCodeTable(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("vssummary"))
        src = s1 + "todo" + s3;
      else if (com[0].equals("toc"))
        src = s1 + generateToc() + s3;
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;      
      else if (com[0].equals("pub-notice"))
        src = s1 + publicationNotice + s3;      
      else 
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
    }
    return src;
  } 

  public static final String OID_TX = "2.16.840.1.113883.4.642.1.";
  public static final String OID_VS = "2.16.840.1.113883.4.642.2.";
  
  private String generateOID(String fileTitle, boolean vs) {
    BindingSpecification cd = definitions.getBindingByReference("#"+fileTitle);
    if (vs) {
      if (cd == null)
        return id != null ? OID_VS + id : OID_VS + "??";
      else
        return OID_VS + cd.getId();
    } else if (!Utilities.noString(cd.getOid()))    
      return cd.getOid();
    else if (cd.hasInternalCodes())
      return "(and the OID for the implicit code system is "+OID_TX + cd.getId()+")";
    else
      return "";
  }

  private String generateDesc(String fileTitle) {
    BindingSpecification cd = definitions.getBindingByReference("#"+fileTitle);
    List<String> vslist = cd.getVSSources();
    StringBuilder b = new StringBuilder();
    if (vslist.contains("")) {
      b.append("This value set defines its own codes");
      vslist.remove(0);
      if (vslist.size() > 0)
        b.append(" and includes codes taken from");
    } else 
      b.append("This is a value set with codes taken from ");
    int i = 0;
    for (String n : cd.getVSSources()) {
      i++;
      if (Utilities.noString(n)) {
        //b.append("Codes defined internally");
      } else {
        String an = fixUrlReference(n);
        b.append("<a href=\""+an+"\">"+n+"</a>");
      }
      if (i == vslist.size() - 1)
        b.append(" and ");
      else if (vslist.size() > 1 && i != vslist.size() )
        b.append(", ");
    }
    return b.toString()+":";
  }

  private String fixUrlReference(String n) {
    if (n.startsWith("urn:ietf:rfc:"))
      return "http://tools.ietf.org/html/rfc"+n.split("\\:")[3];
    if (codeSystems.containsKey(n))
      return codeSystems.get(n).getLinks().get("self");
    return n;
  }

  private String expandValueSet(String fileTitle) throws Exception {
    ValueSet vs = null;
    BindingSpecification cd = definitions.getBindingByName(fileTitle);
    if (cd == null)
      vs = definitions.getExtraValuesets().get(fileTitle);
    else 
      vs = cd.getReferredValueSet();
    return expandVS(vs, "");
  }
  
  private String expandV3ValueSet(String name) throws Exception {
    ValueSet vs = valueSets.get("http://hl7.org/fhir/v3/vs/"+name).getResource();
    return expandVS(vs, "../../../");
  }
  
  private String expandVS(ValueSet vs, String prefix) {
    if (!hasDynamicContent(vs))
      return "";
    try {
      ValueSetExpansionCache cache = new ValueSetExpansionCache(definitions.getValuesets(), definitions.getCodeSystems(), conceptLocator);
      ValueSet exp = cache.getExpander().expand(vs).getValueset();
      exp.setCompose(null);
      exp.setDefine(null);
      exp.setText(null); 
      exp.setDescriptionSimple("Value Set Contents (Expansion) for "+vs.getNameSimple()+" at "+Config.DATE_FORMAT().format(new Date()));
      new NarrativeGenerator(prefix, conceptLocator, codeSystems, valueSets, conceptMaps, profiles, null).generate(exp);
      return "<hr/>\r\n<div style=\"background-color: Floralwhite; border:1px solid maroon; padding: 5px;\">"+new XhtmlComposer().compose(exp.getText().getDiv())+"</div>";
    } catch (Exception e) {
      return "<hr/>\r\n<div style=\"background-color: Floralwhite; border:1px solid maroon; padding: 5px;\">This value set could not be expanded by the publication tooling: "+e.getMessage()+"</div>";
//      return "<!-- This value set could not be expanded by the publication tooling: "+e.getMessage()+" -->";
    }
  }

  private boolean hasDynamicContent(ValueSet vs) {
    if (vs.getCompose() != null) {
      if (vs.getCompose().getImport().size() > 0)
        return true;
      for (ConceptSetComponent t : vs.getCompose().getInclude()) {
        if (t.getFilter().size() > 0)
          return true;
        if (t.getCode().size() == 0)
          return true;
      }
      for (ConceptSetComponent t : vs.getCompose().getExclude()) {
        if (t.getFilter().size() > 0)
          return true;
        if (t.getCode().size() == 0)
          return true;
      }
    }
    return false;
  }

  private String generateVSDesc(String fileTitle) throws Exception {
    BindingSpecification cd = definitions.getBindingByName(fileTitle);
    if (cd == null)
      return new XhtmlComposer().compose(definitions.getExtraValuesets().get(fileTitle).getText().getDiv());
    else if (cd.getReferredValueSet().getText() != null && cd.getReferredValueSet().getText().getDiv() != null)
      return new XhtmlComposer().compose(cd.getReferredValueSet().getText().getDiv());
    else
      return cd.getReferredValueSet().getDescriptionSimple();
  }

  String processPageIncludesForBook(String file, String src, String type) throws Exception {
    String wikilink = "http://wiki.hl7.org/index.php?title=FHIR_"+prepWikiName(file)+"_Page";
    String workingTitle = null;
    int level = 0;
    boolean even = false;

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
      if (com.length == 2 && com[0].equals("dt"))
        src = s1+xmlForDt(com[1], null)+tsForDt(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dt.constraints")) 
        src = s1+genConstraints(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dt.restrictions")) 
        src = s1+genRestrictions(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dictionary"))
        src = s1+dictForDt(com[1])+s3;
      else if (com[0].equals("pageheader") || com[0].equals("dtheader") || com[0].equals("resourcesheader") || com[0].equals("formatsheader") || com[0].equals("extheader") || com[0].equals("txheader") || com[0].equals("cmpheader") || com[0].equals("atomheader"))
        src = s1+s3;
      else if (com[0].equals("resheader"))
        src = s1+s3;
      else if (com[0].equals("dtmappings"))
        src = s1 + genDataTypeMappings(com[1]) + s3;
      else if (com[0].equals("dtusage")) 
        src = s1 + genDataTypeUsage(com[1]) + s3;
      else if (com[0].equals("codelist"))
        src = s1+codelist(name, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("maponthispage"))
          src = s1+s3;
      else if (com[0].equals("onthispage"))
          src = s1+s3;
      else if (com[0].equals("res-category")) {
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
        src = s1+new SvgGenerator(this).generate(folders.srcDir+ com[1])+s3;
      else if (com[0].equals("file"))
        src = s1+/*TextFile.fileToString(folders.srcDir + com[1]+".html")+*/s3;
      else if (com[0].equals("setwiki")) {
        wikilink = com[1];
        src = s1+s3;
      } else if (com[0].equals("settitle")) {
        workingTitle = s2.substring(9).replace("{", "<%").replace("}", "%>");
        src = s1+s3;
      }  else if (com[0].equals("reflink")) {
        src = s1 + reflink(com[1]) + s3;      
      } else if (com[0].equals("setlevel")) {
        level = Integer.parseInt(com[1]);
        src = s1+s3;
      } else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
      else if (com[0].equals("wiki"))
        src = s1+wikilink+s3;
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
        src = s1 + getEventsTable()+ s3;
      else if (com[0].equals("resourcecodes"))
        src = s1 + genResCodes() + s3;
      else if (com[0].equals("datatypecodes"))
        src = s1 + genDTCodes() + s3;
      else if (com[0].equals("bindingtable-codelists"))
        src = s1 + genBindingTable(true) + s3;
      else if (com[0].equals("codeslist"))
        src = s1 + genCodeSystemsTable() + s3;
      else if (com[0].equals("valuesetslist"))
        src = s1 + genValueSetsTable() + s3;
      else if (com[0].equals("conceptmapslist"))
        src = s1 + genConceptMapsTable() + s3;
      else if (com[0].equals("bindingtable"))
        src = s1 + genBindingsTable() + s3;
      else if (com[0].equals("bindingtable-others"))
        src = s1 + genBindingTable(false) + s3;
      else if (com[0].equals("resimplall"))
        src = s1 + genResImplList() + s3;
      else if (com[0].equals("impllist"))
        src = s1 + genReferenceImplList() + s3;
      else if (com[0].equals("txurl"))
        src = s1 + "http://hl7.org/fhir/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vstxurl"))
        src = s1 + "http://hl7.org/fhir/vs/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vsurl")) {
        BindingSpecification bs = definitions.getBindingByName(Utilities.fileTitle(file));
        if (bs == null) {
          src = s1 + "http://hl7.org/fhir/vs/"+Utilities.fileTitle(file) + s3;
        } else {
          String reference = bs.getReference();
          if (reference.startsWith("valueset-"))
            reference = reference.substring(9);
          src = s1 + "http://hl7.org/fhir/vs/"+reference + s3;
        }
      } else if (com[0].equals("txdef"))
        src = s1 + generateCodeDefinition(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("vsdef"))
        src = s1 + generateValueSetDefinition(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("txoid"))
        src = s1 + generateOID(Utilities.fileTitle(file), false) + s3;
      else if (com[0].equals("vsoid"))
        src = s1 + generateOID(Utilities.fileTitle(file), true) + s3;
      else if (com[0].equals("txname"))
        src = s1 + Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vsname"))
        src = s1 + Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vsref")) {
        BindingSpecification bs = definitions.getBindingByName(Utilities.fileTitle(file));
        if (bs == null)
          src = s1 + Utilities.fileTitle(file) + s3;
        else
          src = s1 + bs.getReference() + s3;
      } else if (com[0].equals("txdesc"))
        src = s1 + generateDesc(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("vsdesc"))
        src = s1 + generateVSDesc(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("txusage"))
        src = s1 + generateBSUsage(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("vsusage"))
        src = s1 + generateBSUsage(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("txsummary"))
        src = s1 + generateCodeTable(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("v2Index"))
        src = s1+genV2Index()+s3;
      else if (com[0].equals("v3Index-cs"))
        src = s1+genV3CSIndex()+s3;
      else if (com[0].equals("v3Index-vs"))
        src = s1+genV3VSIndex()+s3;
      else if (com[0].equals("vssummary"))
        src = s1 + "todo" + s3;
      else if (com[0].equals("toc"))
        src = s1 + generateToc() + s3;
      else if (com[0].equals("compartmentlist"))
        src = s1 + compartmentlist() + s3;
      else if (com[0].equals("comp-title"))
        src = s1 + compTitle(name) + s3;
      else if (com[0].equals("comp-desc"))
        src = s1 + compDesc(name) + s3;
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
        src = s1 + breadCrumbManager.makelist(name, type, genlevel(level)) + s3;      
      else if (com[0].equals("year"))
        src = s1 + new SimpleDateFormat("yyyy").format(new Date()) + s3;      
      else if (com[0].equals("revision"))
        src = s1 + svnRevision + s3;      
      else if (com[0].equals("level"))
        src = s1 + genlevel(level) + s3;  
      else if (com[0].equals("archive"))
        src = s1 + makeArchives() + s3;  
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;      
      else if (com[0].equals("pub-notice"))
        src = s1 + publicationNotice + s3;      
      else if (com[0].equals("profilelist"))
        src = s1 + genProfilelist() + s3;  
      else 
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
    }
    return src;
  } 



  private String genDataTypeUsage(String tn) {
    StringBuilder b = new StringBuilder();
    for (ElementDefn e : definitions.getTypes().values()) {
      if (usesType(e, tn)) {
        b.append(", <a href=\"#"+e.getName()+"\">"+e.getName()+"</a>");
      }
    }
    for (ResourceDefn e : definitions.getResources().values()) {
      if (usesType(e.getRoot(), tn)) {
        b.append(", <a href=\""+e.getName().toLowerCase()+".html\">"+e.getName()+"</a>");
      }
    }
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

  String processResourceIncludes(String name, ResourceDefn resource, String xml, String tx, String dict, String src, String mappings, String mappingsList, String type, String pagePath) throws Exception {
    String wikilink = "http://wiki.hl7.org/index.php?title=FHIR_"+prepWikiName(name)+"_Page";
    String workingTitle = Utilities.escapeXml(resource.getName());
    
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
      else if (com[0].equals("sidebar"))
        src = s1+generateSideBar(com.length > 1 ? com[1] : "")+s3;
      else if (com[0].equals("file"))
        src = s1+TextFile.fileToString(folders.srcDir + com[1]+".html")+s3;
      else if (com[0].equals("setwiki")) {
        wikilink = com[1];
        src = s1+s3;
      }
      else if (com[0].equals("settitle")) {
        workingTitle = s2.substring(9).replace("{", "<%").replace("}", "%>");
        src = s1+s3;
      }
      else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing resource "+name);
      else if (com[0].equals("wiki"))
        src = s1+wikilink+s3;
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
      else if (com[0].equals("introduction")) 
        src = s1+loadXmlNotes(name, "introduction", true, resource.getRoot().getDefinition(), resource)+s3;
      else if (com[0].equals("examples")) 
        src = s1+produceExamples(resource)+s3;
      else if (com[0].equals("profiles")) 
        src = s1+produceProfiles(resource)+s3;
      else if (com[0].equals("example-list")) 
        src = s1+produceExampleList(resource)+s3;
      else if (com[0].equals("examples-book")) 
        src = s1+produceBookExamples(resource)+s3;
      else if (com[0].equals("name"))
        src = s1+name+s3;
      else if (com[0].equals("search"))
        src = s1+getSearch(resource)+s3;
      else if (com[0].equals("version"))
        src = s1+ini.getStringProperty("FHIR", "version")+s3;
      else if (com[0].equals("gendate"))
        src = s1+Config.DATE_FORMAT().format(new Date())+s3;
      else if (com[0].equals("definition"))
        src = s1+resource.getRoot().getDefinition()+s3;
      else if (com[0].equals("xml"))
        src = s1+xml+s3;
      else if (com[0].equals("tx"))
        src = s1+tx+s3;
      else if (com[0].equals("inv"))
        src = s1+genResourceConstraints(resource)+s3;
      else if (com[0].equals("resource-table"))
        src = s1+genResourceTable(resource)+s3;
      else if (com[0].equals("plural"))
        src = s1+Utilities.pluralizeMe(name)+s3;
      else if (com[0].equals("notes")) {
        src = s1+loadXmlNotes(name, "notes", false, null, resource)+s3;
      } else if (com[0].equals("dictionary"))
        src = s1+dict+s3;
      else if (com[0].equals("mappings"))
          src = s1+mappings+s3;
      else if (com[0].equals("mappingslist"))
          src = s1+mappingsList+s3;
      else if (com[0].equals("svg"))
        src = s1+new SvgGenerator(this).generate(resource)+s3;        
      else if (com[0].equals("breadcrumb"))
        src = s1 + breadCrumbManager.make(name) + s3;
      else if (com[0].equals("navlist"))
        src = s1 + breadCrumbManager.navlist(name, genlevel(0)) + s3;
      else if (com[0].equals("breadcrumblist"))
        src = s1 + breadCrumbManager.makelist(name, type, genlevel(0)) + s3;      
      else if (com[0].equals("year"))
        src = s1 + new SimpleDateFormat("yyyy").format(new Date()) + s3;      
      else if (com[0].equals("revision"))
        src = s1 + svnRevision + s3;      
      else if (com[0].equals("level"))
        src = s1 + genlevel(0) + s3;  
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;      
      else if (com[0].equals("example-header"))
        src = s1 + loadXmlNotesFromFile(Utilities.path(folders.srcDir, name.toLowerCase(), name+"-examples-header.xml"), false, null, resource)+s3;
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

  private String getReferences(String name) throws Exception {
    List<String> refs = new ArrayList<String>();
    for (String rn : definitions.sortedResourceNames()) {
      if (!rn.equals(name)) {
        ResourceDefn r = definitions.getResourceByName(rn);
        if (usesResource(r.getRoot(), name)) {
          refs.add(rn);
        }
      }
    }
    if (refs.size() == 1)
      return "<p>This resource is referenced by <a href=\""+refs.get(0).toLowerCase()+".html\">"+refs+"</a></p>\r\n";
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
      b.append("<a href=\""+refs.get(i).toLowerCase()+".html\">"+refs.get(i)+"</a>");
    }
      return b.toString();
  }

  private boolean usesResource(ElementDefn e, String name) {
    if (usesResource(e.getTypes(), name))
      return true;
    for (ElementDefn c : e.getElements()) {
      if (usesResource(c, name))
        return true;
    }
    return false;
  }

  private boolean usesResource(List<TypeRef> types, String name) {
    for (TypeRef t : types) {
      if (t.getName().equals("Resource")) {
        for (String p : t.getParams()) {
          if (p.equals(name))
            return true;
        }
      }
    }
    return false;
  }

  private String prepWikiName(String name) {
    return Utilities.noString(name) ? "Index" : Utilities.capitalize(Utilities.fileTitle(name));
  }

 

  private String getSearch(ResourceDefn resource) {
    if (resource.getSearchParams().size() == 0)
      return "";
    else {
      StringBuilder b = new StringBuilder();
      b.append("<h2>Search Parameters</h2>\r\n");
      if (resource.getName().equals("Query"))
        b.append("<p>Search parameters for this resource. The standard parameters also apply as described above.</p>\r\n");
      else
        b.append("<p>Search parameters for this resource. The standard parameters also apply. See <a href=\"search.html\">Searching</a> for more information about searching in REST, messaging, and services.</p>\r\n");
      b.append("<table class=\"list\">\r\n");
      b.append("<tr><td><b>Name</b></td><td><b>Type</b></td><td><b>Description</b></td><td><b>Paths</b></td></tr>\r\n");
      List<String> names = new ArrayList<String>();
      names.addAll(resource.getSearchParams().keySet());
      Collections.sort(names);
      for (String name : names)  {
        SearchParameter p = resource.getSearchParams().get(name);
        b.append("<tr><td>"+p.getCode()+"</td><td><a href=\"search.html#"+p.getType()+"\">"+p.getType()+"</a></td><td>"+Utilities.escapeXml(p.getDescription())+"</td><td>"+presentPaths(p.getPaths())+(p.getType() == SearchType.reference ? p.getTargetTypesAsText() : "")+"</td></tr>\r\n");
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
        s.append("<tr><td>"+Utilities.escapeXml(e.getDescription())+"</td><td><a href=\""+e.getFileTitle()+".xml\">source</a></td><td><a href=\""+e.getFileTitle()+".xml.html\">formatted</a></td></tr>");
    }
    return s.toString();
  }

  private String produceProfiles(ResourceDefn resource) {
    StringBuilder s = new StringBuilder();
    if (resource.getProfiles().size() == 0) {
      s.append("<tr><td colspan=\"2\">No Profiles defined for this resource</td></tr>");
    } else { 
      for (RegisteredProfile p: resource.getProfiles()) {
        s.append("<tr><td><a href=\""+p.getTitle()+".html\">"+Utilities.escapeXml(p.getName())+"</a></td><td>"+Utilities.escapeXml(p.getDescription())+"</td></tr>");
      }
    }
    return s.toString();
  }

  private String produceExampleList(ResourceDefn resource) throws Exception {
    StringBuilder s = new StringBuilder();
    boolean started = false;
    for (Example e: resource.getExamples()) {
      //   if (!e.isInBook()) {
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
    if (resource.getName().equals("Profile")) {
      started = true;
      for (String pn : definitions.getProfiles().keySet()) {
        ProfileDefn p = definitions.getProfiles().get(pn);
        if (!started)
          s.append("<p>Example Index:</p>\r\n<table class=\"list\">\r\n");
        started = true;
        s.append("<tr><td><a href=\""+pn+".html\">"+Utilities.escapeXml(p.metadata("description"))+"</a></td>");
        s.append("<td><a href=\""+pn+".profile.xml.html\">XML</a></td>");
        s.append("<td><a href=\""+pn+".profile.json.html\">JSON</a></td>");
        s.append("</tr>");
      }
      for (String rn : definitions.sortedResourceNames()) {
        if (!rn.equals("Profile")) {
          for (RegisteredProfile p: definitions.getResourceByName(rn).getProfiles()) {
            s.append("<tr><td><a href=\""+p.getTitle()+".html\">"+Utilities.escapeXml(p.getName())+"</a>:"+Utilities.escapeXml(p.getDescription())+"</td>"+
             "<td><a href=\""+p.getTitle()+".profile.xml.html\">XML</a></td><td><a href=\""+p.getTitle()+".profile.json.html\">JSON</a></td></tr>");
          }
        }
      }
    }

    //  }
    if (started)
      s.append("</table>\r\n");
    return s.toString();
  }

  
    
  private String produceBookExamples(ResourceDefn resource) {
    StringBuilder s = new StringBuilder();
    for (Example e: resource.getExamples()) {
     if (e.isInBook()) {
        s.append("<h3>"+Utilities.escapeXml(e.getName())+"</h3>\r\n");
        s.append("<p>XML</p>\r\n");
        s.append(e.getXhtm());
        s.append("<p>JSON</p>\r\n");
        s.append(e.getJson());
      }
    }
    return s.toString();
  }

  private static final String HTML_PREFIX1 = "<div xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/1999/xhtml ../../schema/fhir-xhtml.xsd\" xmlns=\"http://www.w3.org/1999/xhtml\">\r\n";
  private static final String HTML_PREFIX2 = "<div xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/1999/xhtml ../schema/fhir-xhtml.xsd\" xmlns=\"http://www.w3.org/1999/xhtml\">\r\n";
  private static final String HTML_SUFFIX = "</div>\r\n";
  
  public String loadXmlNotesFromFile(String filename, boolean checkHeaders, String definition, ResourceDefn r) throws Exception {
    if (!new CSFile(filename).exists()) {
      TextFile.stringToFile(HTML_PREFIX1+"\r\n<!-- content goes here -->\r\n\r\n"+HTML_SUFFIX, filename);
      return "";
    }

    String res;
    String cnt = TextFile.fileToString(filename);
    Map<String, String> others = new HashMap<String, String>();
    others.put("definition", definition);
    cnt = processPageIncludes(filename, cnt, "notes", others).trim()+"\r\n";
    if (cnt.startsWith("<div")) {
      if (!cnt.startsWith(HTML_PREFIX1) && !cnt.startsWith(HTML_PREFIX2))
        throw new Exception("unable to process start xhtml content "+filename+" : "+cnt.substring(0, HTML_PREFIX1.length())+" - should be '"+HTML_PREFIX1+"' or '"+HTML_PREFIX2+"'");
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
    List<String> allowed = Arrays.asList("div", "h2", "h3", "h4", "h5", "i", "b", "code", "pre", "blockquote", "p", "a", "img", "table", "tr", "td", "ol", "ul", "li");
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

  private String loadXmlNotes(String name, String suffix, boolean checkHeaders, String definition, ResourceDefn resource) throws Exception {
    String filename;
    if (new CSFile(folders.sndBoxDir + name).exists())
      filename = folders.sndBoxDir + name+File.separatorChar+name+"-"+suffix+".xml";
    else
      filename = folders.srcDir + name+File.separatorChar+name+"-"+suffix+".xml";
    return loadXmlNotesFromFile(filename, checkHeaders, definition, resource);
  }

  String processProfileIncludes(String filename, ProfileDefn profile, String xml, String tx, String src, String example, String intro, String notes, String master, String pagePath, ProfileStructureComponent structure, String basefilename) throws Exception {
    String wikilink = "http://wiki.hl7.org/index.php?title=FHIR_"+prepWikiName(filename)+"_Page";
    String workingTitle = null;

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
        src = s1+profileHeader(basefilename, com.length > 1 ? com[1] : "")+s3;
      else if (com[0].equals("file"))
        src = s1+TextFile.fileToString(folders.srcDir + com[1]+".html")+s3;
      else if (com[0].equals("setwiki")) {
        wikilink = com[1];
        src = s1+s3;
      }
      else if (com[0].equals("settitle")) {
        workingTitle = s2.substring(9).replace("{", "<%").replace("}", "%>");
        src = s1+s3;
      }      else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing resource "+filename);
      else if (com[0].equals("wiki"))
        src = s1+wikilink+s3;
      else if (com[0].equals("pageheader"))
        src = s1+pageHeader(profile.metadata("name"))+s3;
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
        src = s1+(workingTitle == null ? Utilities.escapeXml(Utilities.escapeXml(profile.metadata("name"))) : workingTitle)+s3;
      else if (com[0].equals("profiletitle"))
        src = s1+Utilities.escapeXml(profile.metadata("name"))+s3;
      else if (com[0].equals("filetitle"))
        src = s1+(filename.contains(".") ? filename.substring(0, filename.lastIndexOf(".")) : filename)+s3;
      else if (com[0].equals("name"))
        src = s1+filename+s3;
      else if (com[0].equals("date")) {
        if (!Utilities.noString(profile.metadata("date"))) {
          Date d = new SimpleDateFormat("yyyy-MM-dd").parse(profile.metadata("date"));
          src = s1+Config.DATE_FORMAT().format(d)+s3;
        }
        else
          src = s1+"[no date]"+s3;
      } else if (com[0].equals("version"))
        src = s1+ini.getStringProperty("FHIR", "version")+s3;
      else if (com[0].equals("gendate"))
        src = s1+Config.DATE_FORMAT().format(new Date())+s3;
      else if (com[0].equals("definition"))
        src = s1+profile.metadata("description")+s3;
      else if (com[0].equals("profile.intro"))
        src = s1+(intro == null ? profile.metadata("description") : intro) +s3;
      else if (com[0].equals("profile.notes"))
        src = s1+(notes == null ? "" : notes) +s3;
      else if (com[0].equals("example"))
        src = s1+example+s3;
      else if (com[0].equals("status"))
        src = s1+describeStatus(profile.metadata("status"))+s3;
      else if (com[0].equals("author"))
        src = s1+profile.metadata("author.name")+s3;
      else if (com[0].equals("xml"))
        src = s1+xml+s3;
      else if (com[0].equals("profiledesc")) {
        src = s1+". "+ProfileUtilities.summarise(profile.getSource(), this)+s3;
      } else if (com[0].equals("tx"))
        src = s1+tx+s3;
      else if (com[0].equals("inv"))
        src = s1+(profile.getResources().size() == 0 ? "" : genProfileConstraints(profile.getResources().get(0)))+s3;      
      else if (com[0].equals("plural"))
        src = s1+Utilities.pluralizeMe(filename)+s3;
      else if (com[0].equals("notes"))
        src = s1+"todo" /*Utilities.fileToString(folders.srcDir + filename+File.separatorChar+filename+".html")*/ +s3;
      else if (com[0].equals("dictionary"))
        src = s1+"todo"+s3;
      else if (com[0].equals("breadcrumb"))
        src = s1 + breadCrumbManager.make(filename) + s3;
      else if (com[0].equals("navlist"))
        src = s1 + breadCrumbManager.navlist(filename, genlevel(0)) + s3;
      else if (com[0].equals("breadcrumblist"))
        src = s1 + breadCrumbManager.makelist(filename, (master == null ? "profilelist.html" : "profile:"+master), genlevel(0)) + s3;      
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
      else if (com[0].equals("profile-extensions-table"))
        src = s1 + generateProfileExtensionsTable(profile, filename) + s3;
      else if (com[0].equals("profile-constraints-links"))
        src = s1 + generateProfileConstraintLinks(profile, filename) + s3;      
      else if (com[0].equals("pagepath"))
        src = s1 + pagePath + s3;  
      else if (com[0].equals("profileurl"))
        src = s1 + profile.getSource().getUrlSimple() + s3;  
      else if (com[0].equals("rellink"))
        src = s1 + Utilities.URLEncode(pagePath) + s3;  
      else if (com[0].equals("baseURL"))
        src = s1 + Utilities.URLEncode(baseURL) + s3;  
      else if (com[0].equals("base-link"))
        src = s1 + baseLink(structure) + s3;  
      else if (com[0].equals("profile-structure-table-diff"))
        src = s1 + generateProfileStructureTable(profile, structure, true, filename) + s3;      
      else if (com[0].equals("profile-structure-table"))
        src = s1 + generateProfileStructureTable(profile, structure, false, filename) + s3;      
      else if (com[0].equals("maponthispage"))
        src = s1+mapOnPageProfile(profile.getSource())+s3;
      else if (com[0].equals("definitionsonthispage"))
        src = s1+definitionsOnPageProfile(profile.getSource())+s3;
      else if (com[0].equals("mappings"))
        src = s1+mappingsProfile(profile.getSource())+s3;
      else if (com[0].equals("definitions"))
        src = s1+definitionsProfile(profile.getSource())+s3;
      else if (com[0].equals("resurl")) {
          src = s1+"The id of this profile is "+profile.metadata("id")+s3;
      } else 
        throw new Exception("Instruction <%"+s2+"%> not understood parsing resource "+filename);
    }
    return src;
  }

  private String mappingsProfile(Profile source) {
    MappingsGenerator m = new MappingsGenerator(definitions);
    m.generate(source);
    return m.getMappings();
  }

  private String definitionsProfile(Profile source) throws Exception {
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    DictHTMLGenerator d = new DictHTMLGenerator(b, this);
    d.generate(source);
    return b.toString();
  }

  private String mapOnPageProfile(Profile source) {
    if (source.getMapping().size() < 2)
      return "";
    StringBuilder b = new StringBuilder();
    b.append("<div class=\"itoc\">\r\n<p>Mappings:</p>\r\n");
    for (ProfileMappingComponent map : source.getMapping()) {
      b.append("<p class=\"link\"><a href=\"#"+map.getIdentitySimple()+"\">"+map.getNameSimple()+"</a></p>");
    }
    b.append("</div>\r\n");
    return b.toString();
  }

  private String definitionsOnPageProfile(Profile source) {
    StringBuilder b = new StringBuilder();
    b.append("<div class=\"itoc\">\r\n<p>Mappings:</p>\r\n");
    if (!source.getExtensionDefn().isEmpty())
      b.append("<p class=\"link\"><a href=\"#i0\">Extensions</a></p>");
    int i = 1;
    for (ProfileStructureComponent struc : source.getStructure()) {
      b.append("<p class=\"link\"><a href=\"#i"+Integer.toString(i)+"\">"+struc.getNameSimple()+"</a></p>");
      i++;
    }
    b.append("</div>\r\n");
    return b.toString();
  }

  private String baseLink(ProfileStructureComponent structure) throws Exception {
    if (structure.getBaseSimple().startsWith("http://hl7.org/fhir/Profile/")) {
      String name = structure.getBaseSimple().substring(28);
      if (definitions.hasResource(name))
        return "<a href=\""+name.toLowerCase()+".html\">"+name+"</a>";
      else if (definitions.hasElementDefn(name))
        return "<a href=\""+GeneratorUtils.getSrcFile(name)+"#"+name+".html\">"+name+"</a>";  
      else
        return "??"+name;
    } else {
      String[] parts = structure.getBaseSimple().split("#");
      Profile profile = definitions.getProfileByURL(parts[0]);
      if (profile != null) {
        if (parts.length == 2) {
          return "<a href=\""+profile.getTag("filename")+"."+parts[1]+".html\">the structure "+parts[1]+"</a> in <a href=\""+profile.getTag("filename")+".html\">the "+profile.getNameSimple()+" profile</a>";
        } else {
          return "<a href=\""+profile.getTag("filename")+".html\">the "+profile.getNameSimple()+" profile</a>";
        }
      } else
        return "<a href=\""+structure.getBaseSimple()+"\">"+structure.getBaseSimple()+"</a>";
    }
  }

  private String generateProfileStructureTable(ProfileDefn profile, ProfileStructureComponent structure, boolean diff, String filename) throws Exception {
    filename = filename.substring(0, filename.indexOf('.'))+"-definitions.html";
    return new XhtmlComposer().compose(new ProfileUtilities().generateTable(filename, structure, diff, folders.dstDir, false, profile.getSource(), this));
  }

  private String generateProfileConstraintLinks(ProfileDefn profile, String filename) throws Exception {
    if (profile.getSource().getStructure().isEmpty())
      return "";
    
    StringBuilder b = new StringBuilder();
    b.append("<p><b>Constraints:</b></p>\r\n");
    b.append("<table class=\"grid\">\r\n");
    b.append("<tr>");
    b.append("<th>Name</th>");
    b.append("<th>Base</th>");
    b.append("<th>Purpose</th>");
    b.append("</tr>");
    
    for (ProfileStructureComponent s : profile.getSource().getStructure()) {
      b.append("<tr>");
      b.append("<td><a href=\""+Utilities.changeFileExt(filename, "."+Utilities.getFileNameForName(s.getNameSimple()))+".html\">"+Utilities.escapeXml(s.getNameSimple())+"</a></td>");
      if (s.getBaseSimple().startsWith("http://hl7.org/fhir/Profile/")) {
        String type = s.getBaseSimple().substring(28);
        if (hasLinkFor(type)) 
          b.append("<td><a href=\""+getLinkFor(type)+"\">"+type+"</a></td>");
        else
          b.append("<td>"+type+"</td>");
      } else 
        b.append("<td><a href=\""+Utilities.escapeXml(s.getBaseSimple())+"\">"+Utilities.escapeXml(s.getBaseSimple())+"</a></td>");
      b.append("<td>"+Utilities.escapeXml(s.getPurposeSimple())+"</td>");
      b.append("</tr>");
    }
    b.append("</table>\r\n");
    return b.toString();
  }

  private String generateProfileExtensionsTable(ProfileDefn profile, String filename) throws Exception {
    if (profile.getSource().getExtensionDefn().isEmpty())
      return "";
    return "<p><b>Extensions:</b></p>\r\n"+new XhtmlComposer().compose(new ProfileUtilities().generateExtensionsTable(Utilities.changeFileExt(filename, "-definitions.html"), profile.getSource(), folders.dstDir, false, this));
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
        s.append("<li><a href=\"http://www.hl7.org/implement/standards/FHIR/v"+v+"/index.htm\">Version "+v+"</a>, "+ini.getStringProperty("Archives", v)+"</li>");
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
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public void setFolders(FolderManager folders) {
    this.folders = folders;
    conceptLocator = new SpecificationTerminologyServices(Utilities.path(folders.srcDir, "terminologies", "cache"));
    epub = new EPubManager(this);
  }

  public void setIni(IniFile ini) {
    this.ini = ini;
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
      MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
      // mem.gc();
      long used = mem.getHeapMemoryUsage().getUsed() / (1024 * 1024);
      System.out.println(String.format("%1$-74s", content)+" "+String.format("%1$3s", Long.toString(secs))+"sec "+String.format("%1$4s", Long.toString(used))+"MB");
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

  public void setV2Valuesets(AtomFeed v2Valuesets) {
    this.v2Valuesets = v2Valuesets;    
  }

  public void setv3Valuesets(AtomFeed v3Valuesets) {
    this.v3Valuesets = v3Valuesets;    
    
  }

  private void addToValuesets(AtomFeed atom, ValueSet vs, String id) {
    AtomEntry e = new AtomEntry();
    e.setId("http://hl7.org/fhir/vs/" + id);
    e.getLinks().put("self", "http://hl7.org/fhir/vs/" + id);
    e.setTitle("Valueset \"" + id+ "\" to support automated processing");
    e.setUpdated(new DateAndTime(genDate));
    e.setPublished(new DateAndTime(genDate));
    e.setAuthorName("HL7, Inc");
    e.setAuthorUri("http://hl7.org");
    e.setResource(vs);
    e.setSummary(vs.getText().getDiv());
    atom.getEntryList().add(e);
  }

  public Map<String, AtomEntry<ValueSet>> getCodeSystems() {
    return codeSystems;
  }

  public Map<String, AtomEntry<ValueSet>> getValueSets() {
    return valueSets;
  }

  public Map<String, AtomEntry<ConceptMap>> getConceptMaps() {
    return conceptMaps;
  }

  public AtomFeed getV3Valuesets() {
    return v3Valuesets;
  }

  public AtomFeed getV2Valuesets() {
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
    this.id = id;
    
  }

  public List<String> getSuppressedMessages() {
    return suppressedMessages;
  }

  public void loadSnomed() throws Exception {
    conceptLocator.loadSnomed(Utilities.path(folders.srcDir, "snomed", "snomed.xml"));
  }

  public void saveSnomed() throws Exception {
    conceptLocator.saveSnomed(Utilities.path(folders.srcDir, "snomed", "snomed.xml"));
  }
  
  public void loadLoinc() throws Exception {
    log("Load Loinc", LogMessageType.Process);
    conceptLocator.loadLoinc(Utilities.path(folders.srcDir, "loinc", "loinc.xml"));
    log("Loinc Loaded", LogMessageType.Process);
  }

  public SpecificationTerminologyServices getConceptLocator() {
    return conceptLocator;
  }

  public Map<String, Profile> getProfiles() {
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
  public boolean hasLinkFor(String type) {
    return isDatatype(type) || definitions.hasResource(type);
  }

  @Override
  public String getLinkFor(String type) throws Exception {
    if (definitions.hasResource(type)) 
      return type.toLowerCase()+".html";
    else 
      return GeneratorUtils.getSrcFile(type)+".html#"+type;
  }

  @Override
  public ExtensionDefinition getExtensionDefinition(Profile profile, String profileReference) {
    String fn;
    String code;
    if (profileReference.startsWith("#")) {
      code = profileReference.substring(1);
    } else {
      String[] path = profileReference.split("#");
      code = path[1];
      profile = definitions.getProfileByURL(path[0]);
    }
    if (profile != null) {
      fn = profile.getNameSimple();
      for (ProfileExtensionDefnComponent t : profile.getExtensionDefn()) {
        if (t.getCodeSimple().equals(code))
          return new ExtensionDefinition(fn+".html", t);
      }
    }
    return null;
  }

  
}
