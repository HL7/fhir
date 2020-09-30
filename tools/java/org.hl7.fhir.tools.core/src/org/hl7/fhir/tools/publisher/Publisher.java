package org.hl7.fhir.tools.publisher;

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
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.generators.specification.DataTypeTableGenerator;
import org.hl7.fhir.definitions.generators.specification.DictHTMLGenerator;
import org.hl7.fhir.definitions.generators.specification.FhirTurtleGenerator;
import org.hl7.fhir.definitions.generators.specification.JsonSpecGenerator;
import org.hl7.fhir.definitions.generators.specification.MappingsGenerator;
import org.hl7.fhir.definitions.generators.specification.ProfileGenerator;
import org.hl7.fhir.definitions.generators.specification.ResourceTableGenerator;
import org.hl7.fhir.definitions.generators.specification.ReviewSpreadsheetGenerator;
import org.hl7.fhir.definitions.generators.specification.SchematronGenerator;
import org.hl7.fhir.definitions.generators.specification.SvgGenerator;
import org.hl7.fhir.definitions.generators.specification.TerminologyNotesGenerator;
import org.hl7.fhir.definitions.generators.specification.ToolResourceUtilities;
import org.hl7.fhir.definitions.generators.specification.TurtleSpecGenerator;
import org.hl7.fhir.definitions.generators.specification.W5TurtleGenerator;
import org.hl7.fhir.definitions.generators.specification.XPathQueryGenerator;
import org.hl7.fhir.definitions.generators.specification.XmlSpecGenerator;
import org.hl7.fhir.definitions.generators.xsd.SchemaGenerator;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.Compartment;
import org.hl7.fhir.definitions.model.ConstraintStructure;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.DefinedStringPattern;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.Definitions.NamespacePair;
import org.hl7.fhir.definitions.model.Definitions.PageInformation;
import org.hl7.fhir.definitions.model.Dictionary;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.Example.ExampleType;
import org.hl7.fhir.definitions.model.ImplementationGuideDefn;
import org.hl7.fhir.definitions.model.LogicalModel;
import org.hl7.fhir.definitions.model.Operation;
import org.hl7.fhir.definitions.model.PrimitiveType;
import org.hl7.fhir.definitions.model.Profile;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameterDefn;
import org.hl7.fhir.definitions.model.SearchParameterDefn.SearchType;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.WorkGroup;
import org.hl7.fhir.definitions.parsers.IgParser;
import org.hl7.fhir.definitions.parsers.IgParser.GuidePageKind;
import org.hl7.fhir.definitions.parsers.SourceParser;
import org.hl7.fhir.definitions.uml.UMLWriter;
import org.hl7.fhir.definitions.validation.ConceptMapValidator;
import org.hl7.fhir.definitions.validation.FHIRPathUsage;
import org.hl7.fhir.definitions.validation.ResourceValidator;
import org.hl7.fhir.definitions.validation.XmlValidator;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.igtools.publisher.SpecMapManager;
import org.hl7.fhir.r5.conformance.ProfileUtilities;
import org.hl7.fhir.r5.conformance.ShExGenerator;
import org.hl7.fhir.r5.conformance.ShExGenerator.HTMLLinkPolicy;
import org.hl7.fhir.r5.elementmodel.Manager;
import org.hl7.fhir.r5.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.r5.elementmodel.ParserBase.ValidationPolicy;
import org.hl7.fhir.r5.formats.FormatUtilities;
import org.hl7.fhir.r5.formats.IParser;
import org.hl7.fhir.r5.formats.IParser.OutputStyle;
import org.hl7.fhir.r5.formats.JsonParser;
import org.hl7.fhir.r5.formats.RdfParser;
import org.hl7.fhir.r5.formats.XmlParser;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r5.model.Bundle.BundleType;
import org.hl7.fhir.r5.model.CanonicalResource;
import org.hl7.fhir.r5.model.CanonicalType;
import org.hl7.fhir.r5.model.CapabilityStatement;
import org.hl7.fhir.r5.model.CapabilityStatement.CapabilityStatementRestComponent;
import org.hl7.fhir.r5.model.CapabilityStatement.CapabilityStatementRestResourceComponent;
import org.hl7.fhir.r5.model.CapabilityStatement.CapabilityStatementRestResourceSearchParamComponent;
import org.hl7.fhir.r5.model.CapabilityStatement.CapabilityStatementRestSecurityComponent;
import org.hl7.fhir.r5.model.CapabilityStatement.CapabilityStatementSoftwareComponent;
import org.hl7.fhir.r5.model.CapabilityStatement.ConditionalDeleteStatus;
import org.hl7.fhir.r5.model.CapabilityStatement.ReferenceHandlingPolicy;
import org.hl7.fhir.r5.model.CapabilityStatement.ResourceInteractionComponent;
import org.hl7.fhir.r5.model.CapabilityStatement.SystemInteractionComponent;
import org.hl7.fhir.r5.model.CapabilityStatement.SystemRestfulInteraction;
import org.hl7.fhir.r5.model.CapabilityStatement.TypeRestfulInteraction;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.r5.model.CodeType;
import org.hl7.fhir.r5.model.CompartmentDefinition;
import org.hl7.fhir.r5.model.CompartmentDefinition.CompartmentDefinitionResourceComponent;
import org.hl7.fhir.r5.model.ConceptMap;
import org.hl7.fhir.r5.model.ConceptMap.ConceptMapGroupComponent;
import org.hl7.fhir.r5.model.ConceptMap.SourceElementComponent;
import org.hl7.fhir.r5.model.ConceptMap.TargetElementComponent;
import org.hl7.fhir.r5.model.Constants;
import org.hl7.fhir.r5.model.ContactDetail;
import org.hl7.fhir.r5.model.ContactPoint;
import org.hl7.fhir.r5.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r5.model.DomainResource;
import org.hl7.fhir.r5.model.ElementDefinition;
import org.hl7.fhir.r5.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.r5.model.Enumerations.BindingStrength;
import org.hl7.fhir.r5.model.Enumerations.CapabilityStatementKind;
import org.hl7.fhir.r5.model.Enumerations.CompartmentType;
import org.hl7.fhir.r5.model.Enumerations.ConceptMapRelationship;
import org.hl7.fhir.r5.model.Enumerations.FHIRVersion;
import org.hl7.fhir.r5.model.Enumerations.PublicationStatus;
import org.hl7.fhir.r5.model.Enumerations.RestfulCapabilityMode;
import org.hl7.fhir.r5.model.Enumerations.SearchParamType;
import org.hl7.fhir.r5.model.Factory;
import org.hl7.fhir.r5.model.ImplementationGuide;
import org.hl7.fhir.r5.model.ImplementationGuide.ImplementationGuideDefinitionPageComponent;
import org.hl7.fhir.r5.model.Library;
import org.hl7.fhir.r5.model.Meta;
import org.hl7.fhir.r5.model.NamingSystem;
import org.hl7.fhir.r5.model.NamingSystem.NamingSystemIdentifierType;
import org.hl7.fhir.r5.model.NamingSystem.NamingSystemType;
import org.hl7.fhir.r5.model.NamingSystem.NamingSystemUniqueIdComponent;
import org.hl7.fhir.r5.model.Narrative;
import org.hl7.fhir.r5.model.Narrative.NarrativeStatus;
import org.hl7.fhir.r5.model.OperationDefinition;
import org.hl7.fhir.r5.model.Questionnaire;
import org.hl7.fhir.r5.model.Resource;
import org.hl7.fhir.r5.model.ResourceType;
import org.hl7.fhir.r5.model.SearchParameter;
import org.hl7.fhir.r5.model.StringType;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.r5.model.StructureDefinition.TypeDerivationRule;
import org.hl7.fhir.r5.renderers.RendererFactory;
import org.hl7.fhir.r5.renderers.ResourceRenderer;
import org.hl7.fhir.r5.renderers.utils.BaseWrappers.ResourceWrapper;
import org.hl7.fhir.r5.renderers.utils.DOMWrappers;
import org.hl7.fhir.r5.renderers.utils.RenderingContext;
import org.hl7.fhir.r5.model.UriType;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.terminologies.CodeSystemUtilities;
import org.hl7.fhir.r5.terminologies.LoincToDEConvertor;
import org.hl7.fhir.r5.terminologies.ValueSetExpander.ValueSetExpansionOutcome;
import org.hl7.fhir.r5.terminologies.ValueSetUtilities;
import org.hl7.fhir.r5.utils.EOperationOutcome;
import org.hl7.fhir.r5.utils.FHIRPathEngine;
import org.hl7.fhir.r5.utils.GraphQLSchemaGenerator;
import org.hl7.fhir.r5.utils.GraphQLSchemaGenerator.FHIROperationType;
import org.hl7.fhir.r5.utils.NPMPackageGenerator;
import org.hl7.fhir.r5.utils.NPMPackageGenerator.Category;
import org.hl7.fhir.r5.utils.QuestionnaireBuilder;
import org.hl7.fhir.r5.utils.ResourceUtilities;
import org.hl7.fhir.r5.utils.StructureMapUtilities;
import org.hl7.fhir.r5.utils.ToolingExtensions;
import org.hl7.fhir.rdf.RDFValidator;
import org.hl7.fhir.tools.converters.CDAGenerator;
import org.hl7.fhir.tools.converters.DSTU3ValidationConvertor;
import org.hl7.fhir.tools.converters.SpecNPMPackageGenerator;
import org.hl7.fhir.tools.publisher.ExampleInspector.EValidationFailed;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.CloseProtectedZipInputStream;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Logger.LogMessageType;
import org.hl7.fhir.utilities.NDJsonWriter;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.XsltUtilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.hl7.fhir.utilities.cache.FilesystemPackageCacheManager;
import org.hl7.fhir.utilities.cache.PackageGenerator.PackageType;
import org.hl7.fhir.utilities.cache.ToolsVersion;
import org.hl7.fhir.utilities.json.JSONUtil;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueType;
import org.hl7.fhir.utilities.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlDocument;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.hl7.fhir.utilities.xml.XhtmlGenerator;
import org.hl7.fhir.utilities.xml.XmlGenerator;
import org.hl7.fhir.validation.profile.ProfileValidator;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.stringtemplate.v4.ST;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * This is the entry point for the publication method for FHIR The general order
 * of publishing is Check that everything we expect to find is found Load the
 * page.getDefinitions() Produce the specification 1. reference implementations
 * 2. schemas 4. final specification Validate the XML
 *
 * @author Grahame
 *
 */
public class Publisher implements URIResolver, SectionNumberer {

  public static final String CANONICAL_BASE = "http://build.fhir.org/";
  
  public class DocumentHolder {
    public XhtmlDocument doc;
  }

  public static class Fragment {
    private String type;
    private String xml;
    private String page;
    private String id;
    private boolean json;

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getXml() {
      return xml;
    }

    public void setXml(String xml) {
      this.xml = xml;
    }

    public String getPage() {
      return page;
    }

    public void setPage(String page) {
      this.page = page;
    }

    public boolean isJson() {
      return json;
    }

    public void setJson(boolean json) {
      this.json = json;
    }

    public void setId(String id2) {
      this.id = id2;      
    }

  }

  public static class ExampleReference {
    private final String ref;
    private final String path;

    private boolean exempt;
    private String id;
    private String type;
    
    public ExampleReference(String ref, String path) {
      super();
      this.ref = ref;
      this.path = path;
      exempt = false;
      if (ref.startsWith("#")) {
        type = null;
        id = ref;
        exempt = true;
      } else if (isExemptUrl(ref)) {
        type = null;
        id = null;
        exempt = true;
      } else {
        String[] parts = ref.split("\\/");
        if (ref.contains("_history") && parts.length >= 4) {
          type = parts[parts.length-4];
          id = parts[parts.length-3];
        } else if (parts.length >= 2) {
          type = parts[parts.length-2];
          id = parts[parts.length-1];
        }
      }
    }

    private boolean isExemptUrl(String url) {
      if (url.startsWith("urn:"))
        return true;
      if (url.startsWith("http:") && !url.startsWith("http://hl7.org/fhir"))
        return true;
      return false;
    }

    public String getPath() {
      return path;
    }

    public boolean hasType() {
      return type != null;  
    }
    public String getType() {
      return type;
    }

    public String getId() {
      return id;
    }

    public boolean isExempt() {
      return exempt;
    }

    public String getRef() {
      return ref;
    }

  }

  private static final String HTTP_separator = "/";

  private String outputdir;

  private SourceParser prsr;
  private PageProcessor page;
  // private BookMaker book;

  private boolean isGenerate;
  private boolean noArchive;
  private boolean web;
  private String diffProgram;
  
  private Bundle profileBundle;
  private Bundle valueSetsFeed;
  private Bundle conceptMapsFeed;
  private Bundle dataElements;
  private Bundle externals;
  private boolean noPartialBuild;
  private List<Fragment> fragments = new ArrayList<Publisher.Fragment>();
  private Map<String, String> xmls = new HashMap<String, String>();
  private Map<String, String> jsons = new HashMap<String, String>();
  private Map<String, String> ttls = new HashMap<String, String>();
  private Map<String, Long> dates = new HashMap<String, Long>();
  private Map<String, Boolean> buildFlags = new HashMap<String, Boolean>();
  private IniFile cache;
  private String singleResource;
  private String singlePage;
  private PublisherTestSuites tester;
  private List<FHIRPathUsage> fpUsages = new ArrayList<FHIRPathUsage>();
  private List<ConceptMap> statusCodeConceptMaps = new ArrayList<ConceptMap>();
  private int cscounter = 0;
  private int vscounter = 0;
  private int cmcounter = 0;

  private ProfileGenerator pgen;

  private boolean noSound;

  private boolean doValidate;

  private boolean isCIBuild;

  private boolean isPostPR;

  private String validateId;

  public static void main(String[] args) throws Exception {
    //

    Publisher pub = new Publisher();
    pub.page = new PageProcessor(PageProcessor.DEF_TS_SERVER);
    pub.isGenerate = !(args.length > 1 && hasParam(args, "-nogen"));
    pub.doValidate = true;   
    pub.noArchive = (args.length > 1 && hasParam(args, "-noarchive"));
    pub.web = (args.length > 1 && hasParam(args, "-web"));
    pub.page.setForPublication(pub.web);
    pub.diffProgram = getNamedParam(args, "-diff");
    pub.noSound =  (args.length > 1 && hasParam(args, "-nosound"));
    pub.noPartialBuild = (args.length > 1 && hasParam(args, "-nopartial"));
    pub.validateBundles = hasParam(args, "-validate-bundles");
    pub.isPostPR = (args.length > 1 && hasParam(args, "-post-pr"));
    if (hasParam(args, "-resource"))
      pub.singleResource = getNamedParam(args, "-resource");
    if (hasParam(args, "-page"))
      pub.singlePage = getNamedParam(args, "-page");
    if (hasParam(args, "-name"))
      pub.page.setPublicationType(getNamedParam(args, "-name"));
    if (hasParam(args, "-url"))
      pub.page.setBaseURL(getNamedParam(args, "-url"));
    if (hasParam(args, "-svn"))
      pub.page.setBuildId(getNamedParam(args, "-svn"));
//    if (hasParam("args", "-noref"))
//      pub.setNoReferenceImplementations(getNamedParam(args, "-noref"));
//    if (hasParam(args, "-langfolder"))
//      pub.setAlternativeLangFolder(getNamedParam(args, "-langfolder"));
    if (pub.web) {
      pub.page.setPublicationType(PageProcessor.WEB_PUB_NAME);
      pub.page.setPublicationNotice(PageProcessor.WEB_PUB_NOTICE);
    } else {
      pub.page.setPublicationType(PageProcessor.CI_PUB_NAME);
      pub.page.setPublicationNotice(PageProcessor.CI_PUB_NOTICE);
    }
    pub.validateId = getNamedParam(args, "-validate");
    String dir = hasParam(args, "-folder") ? getNamedParam(args, "-folder") : System.getProperty("user.dir");
    pub.outputdir = hasParam(args, "-output") ? getNamedParam(args, "-output") : null; 
    pub.isCIBuild = dir.contains("/ubuntu/agents/"); 
    pub.execute(dir);
  }

  private static boolean hasParam(String[] args, String param) {
    for (String a : args)
      if (a.equals(param))
        return true;
    return false;
  }

  private static String getNamedParam(String[] args, String param) {
    boolean found = false;
    for (String a : args) {
      if (found)
        return a;
      if (a.equals(param)) {
        found = true;
      }
    }
    return null;
  }

  /**
   * Entry point to the publisher. This classes Java Main() calls this function
   * to actually produce the specification
   *
   * @param folder
   * @throws IOException 
   * @throws Exception
   */
  public void execute(String folder) throws IOException {
    tester = new PublisherTestSuites();
    
    page.log("Publish FHIR in folder " + folder + " @ " + Config.DATE_FORMAT().format(page.getGenDate().getTime()), LogMessageType.Process);
    if (web)
      page.log("Build final copy for HL7 web site", LogMessageType.Process);
    else
      page.log("Build local copy", LogMessageType.Process);
    if (outputdir != null) {
      page.log("Create output in "+outputdir, LogMessageType.Process);
    }
    page.log("Detected Java version: " + System.getProperty("java.version")+" from "+System.getProperty("java.home")+" on "+System.getProperty("os.arch"), LogMessageType.Process);
    try {
      tester.initialTests();
      page.setFolders(new FolderManager(folder, outputdir));
      if (!initialize(folder))
        throw new Exception("Unable to publish as preconditions aren't met");


      cache = new IniFile(page.getFolders().rootDir + "temp" + File.separator + "build.cache");
      loadSuppressedMessages(page.getFolders().rootDir);
      boolean doAny = false;
      for (String n : dates.keySet()) {
        Long d = cache.getLongProperty("dates", n);
        boolean b = d == null || (dates.get(n) > d);
        cache.setLongProperty("dates", n, dates.get(n).longValue(), null);
        buildFlags.put(n.toLowerCase(), b);
        doAny = doAny || b;
      }
      cache.save();
      // overriding build

      if (noPartialBuild || !doAny || !(new File(page.getFolders().dstDir + "qa.html").exists()))
        buildFlags.put("all", true); // nothing - build all
      if (singlePage != null) {
        for (String n : buildFlags.keySet())
          buildFlags.put(n, false);
        buildFlags.put("page-"+singlePage.toLowerCase(), true);
      } else if (singleResource != null) {
        for (String n : buildFlags.keySet())
          buildFlags.put(n, false);
        buildFlags.put(singleResource.toLowerCase(), true);
      }
      if (!buildFlags.get("all")) {
        if (!noSound) {
          AudioUtilities.tone(1000, 10);
          AudioUtilities.tone(1400, 10);
          AudioUtilities.tone(1800, 10);
          AudioUtilities.tone(1000, 10);
          AudioUtilities.tone(1400, 10);
          AudioUtilities.tone(1800, 10);
        }
        page.log("Partial Build (if you want a full build, just run the build again)", LogMessageType.Process);
        CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
        for (String n : buildFlags.keySet())
          if (buildFlags.get(n))
            b.append(n);
        page.log("  Build: "+b.toString(), LogMessageType.Process);
      } else {
        if (!noSound) 
          AudioUtilities.tone(1200, 30);
        page.log("Full Build", LogMessageType.Process);
      }
      if (isGenerate && page.getBuildId() == null)
        page.setBuildId(getGitBuildId());
      page.log("Version " + page.getVersion() + "-" + page.getBuildId(), LogMessageType.Hint);
      Utilities.createDirectory(page.getFolders().dstDir);
      Utilities.deleteTempFiles();

      page.getBreadCrumbManager().parse(page.getFolders().srcDir + "hierarchy.xml");
      page.loadSnomed();
      page.loadLoinc();
      page.loadUcum();
      buildFeedsAndMaps();
      prsr.setExternals(externals);

      prsr.parse(page.getGenDate(), page.getValidationErrors());
      for (String n : page.getDefinitions().sortedResourceNames())
        if (!page.getBreadCrumbManager().knowsResource(n))
          page.getValidationErrors().add(new ValidationMessage(Source.Publisher, IssueType.INVALID, -1, -1, "hierarchy.xml", "Resource not found: "+n,IssueSeverity.ERROR));

      for (String n : prsr.getErrors()) 
        page.getValidationErrors().add(new ValidationMessage(Source.Publisher, IssueType.INVALID, -1, -1, "source spreadsheets", n, IssueSeverity.ERROR));        
      
      if (web) {
        page.log("Clear Directory", LogMessageType.Process);
        Utilities.clearDirectory(page.getFolders().dstDir);
      }
      if (web || (isGenerate && buildFlags.get("all"))) {
        Utilities.createDirectory(page.getFolders().dstDir + "html");
        Utilities.createDirectory(page.getFolders().dstDir + "examples");
      }
      for (ImplementationGuideDefn ig : page.getDefinitions().getSortedIgs())
        if (!ig.isCore())
          Utilities.createDirectory(page.getFolders().dstDir + ig.getCode());

      if (buildFlags.get("all")) {
        copyStaticContent();
      }
      page.makeRenderingContext();
      loadValueSets1();
      prsr.getRegistry().commit();


      generateSCMaps();
      validate();
      processProfiles();
      checkAllOk();

      if (isGenerate) {
        produceSpecification();
        checkAllOk();
      } 

      if (doValidate)
        validationProcess();
      page.saveSnomed();
      page.getWorkerContext().saveCache();
      processWarnings(false);
      if (isGenerate && buildFlags.get("all"))
        produceQA();

      if (!buildFlags.get("all")) {
        page.log("This was a Partial Build", LogMessageType.Process);
        CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
        for (String n : buildFlags.keySet())
          if (buildFlags.get(n))
            b.append(n);
        page.log("  Build: "+b.toString(), LogMessageType.Process);
      } else
        page.log("This was a Full Build", LogMessageType.Process);
      if (!noSound) {
        AudioUtilities.tone(800, 10);
        AudioUtilities.tone(1000, 10);
        AudioUtilities.tone(1200, 10);
        AudioUtilities.tone(1000, 10);
        AudioUtilities.tone(800, 10);
      }
      page.log("Finished publishing FHIR @ " + Config.DATE_FORMAT().format(Calendar.getInstance().getTime()), LogMessageType.Process);
    } catch (Exception e) {

      if (!(e instanceof NullPointerException)) { // because NullPointerException is unexpected...
        try {
          processWarnings(e instanceof EValidationFailed);
        } catch (Exception e2) {
          page.log("  ERROR: Unable to process warnings: " + e2.getMessage(), LogMessageType.Error);
          e2.printStackTrace();
        }
      }
      if (buildFlags.containsKey("all") && !buildFlags.get("all")) {
        page.log("This was a Partial Build", LogMessageType.Process);
        CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
        for (String n : buildFlags.keySet())
          if (buildFlags.get(n))
            b.append(n);
        page.log("  Build: "+b.toString(), LogMessageType.Process);
      } else
        page.log("This was a Full Build", LogMessageType.Process);
      if (!noSound) {
        AudioUtilities.tone(800, 20);
        AudioUtilities.tone(1000, 20);
        AudioUtilities.tone(1200, 20);
      }
      try {
        Thread.sleep(50);
      } catch (InterruptedException e1) {
      }
      if (!noSound) {
        AudioUtilities.tone(800, 20);
        AudioUtilities.tone(1000, 20);
        AudioUtilities.tone(1200, 20);
      }
      try {
        Thread.sleep(50);
      } catch (InterruptedException e1) {
      }
      if (!noSound) {
        AudioUtilities.tone(800, 20);
        AudioUtilities.tone(1000, 20);
        AudioUtilities.tone(1200, 20);
      }
      page.log("FHIR build failure @ " + Config.DATE_FORMAT().format(Calendar.getInstance().getTime()), LogMessageType.Process);
      System.out.println("Error: " + e.getMessage());
      e.printStackTrace();
      TextFile.stringToFile(StringUtils.defaultString(e.getMessage()), Utilities.path(folder, "publish", "simple-error.txt"));
      System.exit(1);
    }
  }

  private String getGitBuildId() {
    String version = "";
    try {
      String[] cmd = { "git", "describe", "--tags", "--always" };
      Process p = Runtime.getRuntime().exec(cmd);
      p.waitFor();
      InputStreamReader isr = new InputStreamReader(p.getInputStream());  
      BufferedReader br = new BufferedReader(isr);  
      String line;  
      while ((line = br.readLine()) != null) {  
        version += line;  
      }  
    } catch (Exception e) {
      System.out.println("Warning @ Unable to read the git commit: " + e.getMessage() );
      version = "????";
    }
    return version;
  }

  private void generateSCMaps() throws Exception {
    page.log("Generate Status Code Concept Maps", LogMessageType.Process);
    for (ResourceDefn rd : page.getDefinitions().getResources().values()) {
      generateSCMaps(rd.getRoot().getName(), rd.getRoot(), rd); 
    }
  }

  private void generateSCMaps(String path, ElementDefn element, ResourceDefn rd) throws Exception {
    
    if (elementHasSCMapping(path)) {
      ValueSet vs = element.getBinding().getValueSet();
      if (vs == null)
        throw new Exception("Element has a Status Code binding, but no ValueSet");
      ConceptMap cm = (ConceptMap) vs.getUserData("build.statuscodes.map");
      if (cm == null) {
        cm = buildConceptMap(path, vs, rd);
        if (cm != null)
          vs.setUserData("build.statuscodes.map", cm);
      }
    }
    for (ElementDefn child : element.getElements()) {
      generateSCMaps(path+"."+child.getName(), child, rd);
    }
  }

  private ConceptMap buildConceptMap(String path, ValueSet vs, ResourceDefn rd) throws EOperationOutcome, FHIRException, IOException {
    ConceptMap cm = new ConceptMap();
    cm.setUserData("path", "sc-"+vs.getUserString("path"));
    cm.setUserData("resource-definition", rd);
    cm.setId("sc-"+vs.getId());
    cm.setUrl("http://hl7.org/fhir/ConceptMap/"+cm.getId());
    cm.setVersion(page.getVersion().toCode());   
    cm.setName(vs.getName()+"CanonicalMap");  
    cm.setTitle("Canonical Mapping for \""+vs.present()+"\""); 
    cm.setStatus(PublicationStatus.DRAFT);  
    cm.setDate(vs.getDate());  
    cm.setPublisher(vs.getPublisher());
    cm.addContact(vs.getContactFirstRep());
    cm.setDescription("Canonical Mapping for \""+vs.getDescription()+"\"");
    cm.setSource(new CanonicalType(vs.getUrl()));
    cm.setTarget(new CanonicalType("http://hl7.org/fhir/ValueSet/resource-status"));
    List<String> canonical = page.getDefinitions().getStatusCodes().get("@code");
    List<String> self = page.getDefinitions().getStatusCodes().get(path);
    ConceptMapGroupComponent grp = cm.addGroup();
    grp.setTarget("http://hl7.org/fhir/resource-status");
    grp.setSource(vs.getCompose().getIncludeFirstRep().getSystem());
    for (int i =0; i < self.size(); i++) {
      if (!Utilities.noString(self.get(i))) {
        String cc = canonical.get(i);
        String sc = self.get(i);
        SourceElementComponent e = grp.addElement();
        e.setCode(sc);
        TargetElementComponent t = e.addTarget();
        t.setCode(cc);
        t.setRelationship(ConceptMapRelationship.EQUIVALENT);
      }
    }
    if (!grp.hasElement())
      return null;
    page.getConceptMaps().see(cm, page.packageInfo());
    statusCodeConceptMaps.add(cm);
    return cm;
  }

  private boolean elementHasSCMapping(String path) {
    return page.getDefinitions().getStatusCodes().containsKey(path);
  }

  private void generateRedirects() throws Exception {
    page.log("Produce "+Integer.toString(page.getDefinitions().getRedirectList().size())+" Redirects", LogMessageType.Process);
    for (String n : page.getDefinitions().getRedirectList().keySet()) {
      NamespacePair nsp = page.getDefinitions().getRedirectList().get(n);
      generateRedirect(n, nsp.desc, nsp.page);
    }
  }

  private void generateRedirect(String n, String desc, String pn) throws Exception {
    if (!n.startsWith("http://hl7.org/fhir/"))
      throw new Error("wrong path");
    n = n.substring(20);
    String level = "../";
    for (char c : n.toCharArray())
      if (c == '/')
        level = level +"../";

    String fullFileName = Utilities.path(page.getFolders().dstDir, n.replace("/", File.separator));
    Utilities.createDirectory(fullFileName);
    // simple html version
//    String pagecnt = "<html>\r\n<head>\r\n<title>Redirect Page for "+Utilities.escapeXml(desc)+" </title>\r\n<meta http-equiv=\"REFRESH\" content=\"0;url="+
//       level+pn+"\"></HEAD>\r\n</head>\r\n<body>\r\nThis page is a redirect to "+level+pn+"\r\n</body>\r\n</html>\r\n";
    
    // asp redirection version
    String pagecnt = TextFile.fileToString(Utilities.path(page.getFolders().rootDir, "tools", "html", "redirect.asp"));
    pagecnt = pagecnt.replace("<%filename%>", Utilities.changeFileExt(pn, ""));
    
    String fn = Utilities.path(fullFileName, "index.asp");
    if (!(new File(fn).exists()))
      TextFile.stringToFile(pagecnt, fn);

  }

  @SuppressWarnings("unchecked")
  private List<StructureDefinition> listProfiles(Map<String, Resource> igResources) throws Exception {
    List<StructureDefinition> list = new ArrayList<StructureDefinition>();
    for (Resource ae : igResources.values())
      if (ae instanceof StructureDefinition) {
        processProfile((StructureDefinition) ae);
        list.add((StructureDefinition) ae);
      }
    return list;
  }

  @SuppressWarnings("unchecked")
  private void loadIgReference(Resource ae) throws Exception {
    page.getIgResources().put(ae.getId(), ae);
    if (ae instanceof ValueSet) {
      ValueSet vs = (ValueSet) ae;
      page.getValueSets().see(vs, page.packageInfo());
    }
    if (ae instanceof CodeSystem)
      page.getCodeSystems().see((CodeSystem) ae, page.packageInfo());
    if (ae instanceof ConceptMap)
      page.getConceptMaps().see((ConceptMap) ae, page.packageInfo());

    if (ae instanceof StructureDefinition)  {
      StructureDefinition sd = (StructureDefinition) ae;
      if (page.getProfiles().has(sd.getUrl()))
        throw new Exception("Duplicate Profile URL "+sd.getUrl());
      page.getProfiles().see(sd, page.packageInfo());
    }
  }

  @SuppressWarnings("unchecked")
  private void processProfiles() throws Exception {
    page.log(" ...process profiles (base)", LogMessageType.Process);
    // first, for each type and resource, we build it's master profile
    for (DefinedCode t : page.getDefinitions().getPrimitives().values()) {
      if (t instanceof PrimitiveType)
        genPrimitiveTypeProfile((PrimitiveType) t);
      else
        genPrimitiveTypeProfile((DefinedStringPattern) t);
    }
    genXhtmlProfile();
    for (TypeDefn t : page.getDefinitions().getTypes().values())
      genTypeProfile(t);
    for (TypeDefn t : page.getDefinitions().getInfrastructure().values())
      genTypeProfile(t);

    page.log(" ...process profiles (resources)", LogMessageType.Process);

    for (ResourceDefn r : page.getDefinitions().getBaseResources().values()) {
        r.setConformancePack(makeConformancePack(r));
        r.setProfile(new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), page.getVersion(), dataElements, fpUsages, page.getFolders().rootDir, page.getUml(), page.getRc()).generate(r.getConformancePack(), r, "core", false));
        if (page.getProfiles().has(r.getProfile().getUrl()))
          throw new Exception("Duplicate Profile URL "+r.getProfile().getUrl());
        page.getProfiles().see(r.getProfile(), page.packageInfo());
        ResourceTableGenerator rtg = new ResourceTableGenerator(page.getFolders().dstDir, page, null, true);
        r.getProfile().getText().setDiv(new XhtmlNode(NodeType.Element, "div"));
        r.getProfile().getText().getDiv().getChildNodes().add(rtg.generate(r, "", false));
    }

    for (String rn : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn r = page.getDefinitions().getResourceByName(rn);
      r.setConformancePack(makeConformancePack(r));
      r.setProfile(new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), page.getVersion(), dataElements, fpUsages, page.getFolders().rootDir, page.getUml(), page.getRc()).generate(r.getConformancePack(), r, "core", false));
      if (page.getProfiles().has(r.getProfile().getUrl()))
        throw new Exception("Duplicate Profile URL "+r.getProfile().getUrl());
      page.getProfiles().see(r.getProfile(), page.packageInfo());
      ResourceTableGenerator rtg = new ResourceTableGenerator(page.getFolders().dstDir, page, null, true);
      r.getProfile().getText().setDiv(new XhtmlNode(NodeType.Element, "div"));
      r.getProfile().getText().getDiv().getChildNodes().add(rtg.generate(r, "", false));
    }

    for (ResourceDefn r : page.getDefinitions().getResourceTemplates().values()) {
      r.setConformancePack(makeConformancePack(r));
      r.setProfile(new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), page.getVersion(), dataElements, fpUsages, page.getFolders().rootDir, page.getUml(), page.getRc()).generate(r.getConformancePack(), r, "core", true));
      ResourceTableGenerator rtg = new ResourceTableGenerator(page.getFolders().dstDir, page, null, true);
      r.getProfile().getText().setDiv(new XhtmlNode(NodeType.Element, "div"));
      r.getProfile().getText().getDiv().getChildNodes().add(rtg.generate(r, "", true));
      if (page.getProfiles().has(r.getProfile().getUrl()))
        throw new Exception("Duplicate Profile URL "+r.getProfile().getUrl());
      page.getProfiles().see(r.getProfile(), page.packageInfo());
    }
    
    for (ProfiledType pt : page.getDefinitions().getConstraints().values()) {
      genProfiledTypeProfile(pt);
    }

    page.log(" ...process profiles (extensions)", LogMessageType.Process);
    for (StructureDefinition ex : page.getWorkerContext().getExtensionDefinitions())
        processExtension(ex);

    for (ResourceDefn r : page.getDefinitions().getResources().values()) {
//      boolean logged = false;
      for (Profile ap : r.getConformancePackages()) {
//        if (!logged)
//          page.log(" ...  resource "+r.getName(), LogMessageType.Process);
//        logged = true;
        for (ConstraintStructure p : ap.getProfiles())
          processProfile(ap, p, ap.getId(), r);
      }
    }

    page.log(" ...process profiles (packs)", LogMessageType.Process);
    // we have profiles scoped by resources, and stand alone profiles
    for (Profile ap : page.getDefinitions().getPackList()) {
//      page.log(" ...  pack "+ap.getId(), LogMessageType.Process);
      for (ConstraintStructure p : ap.getProfiles())
        processProfile(ap, p, ap.getId(), null);
    }

    page.log(" ...process logical models", LogMessageType.Process);
    for (ImplementationGuideDefn ig : page.getDefinitions().getSortedIgs()) {
      for (LogicalModel lm : ig.getLogicalModels()) {
        page.log(" ...process logical model " + lm.getId(), LogMessageType.Process);
        if (lm.getDefinition() == null)
          lm.setDefinition(new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), page.getVersion(), dataElements, fpUsages, page.getFolders().rootDir, page.getUml(), page.getRc()).generateLogicalModel(ig, lm.getResource()));
      }
    }

    // now, validate the profiles
    for (Profile ap : page.getDefinitions().getPackList())
      for (ConstraintStructure p : ap.getProfiles())
        validateProfile(p);
    for (ResourceDefn r : page.getDefinitions().getResources().values())
      for (Profile ap : r.getConformancePackages())
        for (ConstraintStructure p : ap.getProfiles())
          validateProfile(p);
    
    page.log(" ...Check FHIR Path Expressions", LogMessageType.Process);
    StringBuilder b = new StringBuilder();
    FHIRPathEngine fp = new FHIRPathEngine(page.getWorkerContext());
    fp.setHostServices(page.getExpressionResolver());
    for (FHIRPathUsage p : fpUsages) {
      checkExpression(b, fp, p);
    }
    TextFile.stringToFile(b.toString(), Utilities.path(page.getFolders().dstDir, "fhirpaths.txt"));

    checkAllOk();
  }

  private void checkExpression(StringBuilder b, FHIRPathEngine fp, FHIRPathUsage p) {
    b.append(p.getResource() + " (" + p.getContext() + "): " + p.getExpression()+"\r\n");
    try {
      if (!"n/a".equals(p.getExpression())) {
        fp.check(null, p.getResource(), p.getContext(), p.getExpression()); 
      }
    } catch (Exception e) {
      ValidationMessage validationMessage = new ValidationMessage(Source.Publisher, IssueType.STRUCTURE, -1, -1, p.getLocation(), 
            "Expression '"+p.getExpression()+"' has illegal path ("+e.getMessage()+")", IssueSeverity.ERROR);
      page.getValidationErrors().add(validationMessage);
    }
  }

  private void processExtension(StructureDefinition ex) throws Exception {
    StructureDefinition bd = page.getDefinitions().getSnapShotForBase(ex.getBaseDefinition());
    new ProfileUtilities(page.getWorkerContext(), page.getValidationErrors(), page).generateSnapshot(bd, ex, ex.getUrl(), null, ex.getName());
  }

  private Profile makeConformancePack(ResourceDefn r) {
    Profile result = new Profile("core");
    result.setTitle("Base Profile for "+r.getName());
    return result;
  }

  private void validateProfile(ConstraintStructure p) throws Exception {
    ProfileValidator pv = new ProfileValidator(page.getWorkerContext());
    page.getValidationErrors().addAll(pv.validate(p.getResource(), true));
  }

  private void genProfiledTypeProfile(ProfiledType pt) throws Exception {
    StructureDefinition profile = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), page.getVersion(), dataElements, fpUsages, page.getFolders().rootDir, page.getUml(), page.getRc()).generate(pt, page.getValidationErrors());
    if (page.getProfiles().has(profile.getUrl()))
      throw new Exception("Duplicate Profile URL "+profile.getUrl());
    page.getProfiles().see(profile, page.packageInfo());
    pt.setProfile(profile);
    // todo: what to do in the narrative?
  }

  private void genXhtmlProfile() throws Exception {
    StructureDefinition profile = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), page.getVersion(), dataElements, fpUsages, page.getFolders().rootDir, page.getUml(), page.getRc()).generateXhtml();
    if (page.getProfiles().has(profile.getUrl()))
      throw new Exception("Duplicate Profile URL "+profile.getUrl());
    page.getProfiles().see(profile, page.packageInfo());

    //    DataTypeTableGenerator dtg = new DataTypeTableGenerator(page.getFolders().dstDir, page, t.getCode(), true);
    //    t.setProfile(profile);
    //    t.getProfile().getText().setDiv(new XhtmlNode(NodeType.Element, "div"));
    //    t.getProfile().getText().getDiv().getChildNodes().add(dtg.generate(t));
  }

  private void genPrimitiveTypeProfile(PrimitiveType t) throws Exception {
    StructureDefinition profile = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), page.getVersion(), dataElements, fpUsages, page.getFolders().rootDir, page.getUml(), page.getRc()).generate(t);
    if (page.getProfiles().has(profile.getUrl()))
      throw new Exception("Duplicate Profile URL "+profile.getUrl());
    page.getProfiles().see(profile, page.packageInfo());
    t.setProfile(profile);

    //    DataTypeTableGenerator dtg = new DataTypeTableGenerator(page.getFolders().dstDir, page, t.getCode(), true);
    //    t.setProfile(profile);
    //    t.getProfile().getText().setDiv(new XhtmlNode(NodeType.Element, "div"));
    //    t.getProfile().getText().getDiv().getChildNodes().add(dtg.generate(t));
  }


  private void genPrimitiveTypeProfile(DefinedStringPattern t) throws Exception {
    StructureDefinition profile = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), page.getVersion(), dataElements, fpUsages, page.getFolders().rootDir, page.getUml(), page.getRc()).generate(t);
    if (page.getProfiles().has(profile.getUrl()))
      throw new Exception("Duplicate Profile URL "+profile.getUrl());
    page.getProfiles().see(profile, page.packageInfo());
    t.setProfile(profile);
    //    DataTypeTableGenerator dtg = new DataTypeTableGenerator(page.getFolders().dstDir, page, t.getCode(), true);
    //    t.setProfile(profile);
    //    t.getProfile().getText().setDiv(new XhtmlNode(NodeType.Element, "div"));
    //    t.getProfile().getText().getDiv().getChildNodes().add(dtg.generate(t));
  }


  private void genTypeProfile(TypeDefn t) throws Exception {
    StructureDefinition profile;
    try {
      profile = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), page.getVersion(), dataElements, fpUsages, page.getFolders().rootDir, page.getUml(), page.getRc()).generate(t);
      page.getProfiles().see(profile, page.packageInfo());
      t.setProfile(profile);
      DataTypeTableGenerator dtg = new DataTypeTableGenerator(page.getFolders().dstDir, page, t.getName(), true);
      t.getProfile().getText().setDiv(new XhtmlNode(NodeType.Element, "div"));
      t.getProfile().getText().getDiv().getChildNodes().add(dtg.generate(t, null, false));
    } catch (Exception e) {
      throw new Exception("Error generating profile for '"+t.getName()+"': "+e.getMessage(), e);
    }
  }

  private void processProfile(Profile ap, ConstraintStructure profile, String filename, ResourceDefn baseResource) throws Exception {
//    page.log(" ...   profile "+profile.getId(), LogMessageType.Process);

    // they've either been loaded from spreadsheets, or from profile declarations
    // what we're going to do:
    //  create StructureDefinition structures if needed (create differential definitions from spreadsheets)
    if (profile.getResource() == null) {
      StructureDefinition p = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), page.getVersion(), dataElements, fpUsages, page.getFolders().rootDir, page.getUml(), page.getRc())
          .generate(ap, profile, profile.getDefn(), profile.getId(), profile.getUsage(), page.getValidationErrors(), baseResource);
      p.setUserData("pack", ap);
      profile.setResource(p);
      if (profile.getResourceInfo() != null) {
        profile.getResourceInfo().setUserData(ToolResourceUtilities.RES_ACTUAL_RESOURCE, p);
      }
      if (page.getProfiles().has(p.getUrl()))
        throw new Exception("Duplicate Profile URL "+p.getUrl());
      page.getProfiles().see(p, page.packageInfo());
    } else {
      profile.getResource().setUserData("pack", ap);
      sortProfile(profile.getResource());
      for (ElementDefinition ed : profile.getResource().getDifferential().getElement())
        if (!ed.hasId())
          throw new Exception("Missing ID");
      // special case: if the profile itself doesn't claim a date, it's date is the date of this publication
      if (!profile.getResource().hasDate())
        profile.getResource().setDate(page.getGenDate().getTime());
        if (profile.getResource().hasBaseDefinition() && !profile.getResource().hasSnapshot()) {
          // cause it probably doesn't, coming from the profile directly
          StructureDefinition base = getSnapShotForProfile(profile.getResource().getBaseDefinition());
          new ProfileUtilities(page.getWorkerContext(), page.getValidationErrors(), page).generateSnapshot(base, profile.getResource(), profile.getResource().getBaseDefinition().split("#")[0], "http://hl7.org/fhir", profile.getResource().getName());
        }
        if (page.getProfiles().has(profile.getResource().getUrl()))
          throw new Exception("Duplicate Profile URL "+profile.getResource().getUrl());
        page.getProfiles().see(profile.getResource(), page.packageInfo());
      }
    if (!Utilities.noString(filename))
      profile.getResource().setUserData("filename", filename+".html");
    if (Utilities.noString(profile.getResource().getUserString("path"))) {
      String path = "";
      ImplementationGuideDefn ig = page.getDefinitions().getUsageIG(ap.getCategory(), "processProfile");
      if (ig!=null && !ig.isCore())
          path = ig.getCode() + File.separator; 
      profile.getResource().setUserData("path", path + filename+".html");
    }
  }

  private void sortProfile(StructureDefinition diff) throws Exception {
    StructureDefinition base = page.getWorkerContext().fetchResource(StructureDefinition.class, diff.getBaseDefinition());
    if (base == null)
      throw new Exception("unable to find base profile "+diff.getUrl());
    List<String> errors = new ArrayList<String>();
    new ProfileUtilities(page.getWorkerContext(), null, page).sortDifferential(base, diff, diff.getName(), errors, false);
//    if (errors.size() > 0)
//      throw new Exception("Error sorting profile "+diff.getName()+": "+errors.toString());
  }

  
  public StructureDefinition getSnapShotForProfile(String base) throws Exception {
    String[] parts = base.split("#");
    if (parts[0].startsWith("http://hl7.org/fhir/StructureDefinition/") && parts.length == 1) {
      String name = base.substring(40);
      if (page.getDefinitions().hasResource(name))
        return page.getDefinitions().getSnapShotForType(name);
      else if (page.getDefinitions().hasType(name)) {
        TypeDefn t = page.getDefinitions().getElementDefn(name);
        if (t.getProfile().hasSnapshot())
          return t.getProfile();
        throw new Exception("unable to find snapshot for "+name);
      } //else 
//        throw new Exception("unable to find base definition for "+name);
    }
    StructureDefinition p = new ProfileUtilities(page.getWorkerContext(), page.getValidationErrors(), page).getProfile(null, parts[0]);
    if (p == null)
      throw new Exception("unable to find base definition for "+base);
    if (parts.length == 1) {
      if (p.getSnapshot() == null)
        throw new Exception("StructureDefinition "+base+" has no snapshot"); // or else we could fill it in?
      return p;
    }
    for (Resource r : p.getContained()) {
      if (r instanceof StructureDefinition && r.getId().equals(parts[1])) {
        StructureDefinition pc = (StructureDefinition) r;

      if (pc.getSnapshot() == null) {
        StructureDefinition ps = getSnapShotForProfile(pc.getBaseDefinition());
        processProfile(pc);
      }
      return pc;
      }
    }
    throw new Exception("Unable to find snapshot for "+base);
  }


  private void processProfile(StructureDefinition ae) throws Exception {
    if (ae.getDate() == null)
      ae.setDate(page.getGenDate().getTime());
    if (ae.hasBaseDefinition() && ae.hasSnapshot()) {
      // cause it probably doesn't, coming from the profile directly
      StructureDefinition base = getIgProfile(ae.getBaseDefinition());
      if (base == null)
        base = new ProfileUtilities(page.getWorkerContext(), page.getValidationErrors(), page).getProfile(null, ae.getBaseDefinition());
      new ProfileUtilities(page.getWorkerContext(), page.getValidationErrors(), page).generateSnapshot(base, ae, ae.getBaseDefinition().split("#")[0], "http://hl7.org/fhir", ae.getName());
      if (page.getProfiles().has(ae.getUrl()))
        throw new Exception("Duplicate Profile URL "+ae.getUrl());
      page.getProfiles().see(ae, page.packageInfo());
    }
  }

  public StructureDefinition getIgProfile(String base) throws Exception {
    String[] parts = base.split("#");
    StructureDefinition p = getIGProfileByURL(parts[0]);
    if (p == null)
      return null;

    processProfile(p); // this is recursive, but will terminate at the root
    if (parts.length == 1) {
      if (p.getSnapshot() == null)
        throw new Exception("StructureDefinition "+base+" has no snapshot"); // or else we could fill it in?
      return p;
    }
    for (Resource r : p.getContained()) {
      if (r instanceof StructureDefinition && r.getId().equals(parts[1])) {
        StructureDefinition pc = (StructureDefinition) r;

      if (pc.getSnapshot() == null) {
        StructureDefinition ps = getSnapShotForProfile(pc.getBaseDefinition());
        processProfile(pc);
      }
      return pc;
      }
    }
    throw new Exception("Unable to find snapshot for "+base);
  }

  @SuppressWarnings("unchecked")
  private StructureDefinition getIGProfileByURL(String url) {
    if (url.contains("#"))
      url = url.substring(0, url.indexOf('#'));
    for (Resource ae : page.getIgResources().values()) {
      if (ae instanceof StructureDefinition) {
        StructureDefinition p = (StructureDefinition) ae;
        if (p.getUrl().equals(url))
          return (StructureDefinition) ae;
      }
    }
    return null;
  }

  private void loadSuppressedMessages(String rootDir) throws Exception {
    InputStreamReader r = new InputStreamReader(new FileInputStream(rootDir + "suppressed-messages.txt"));
    StringBuilder b = new StringBuilder();
    while (r.ready()) {
      char c = (char) r.read();
      if (c == '\r' || c == '\n') {
        if (b.length() > 0)
          page.getSuppressedMessages().add(b.toString());
        b = new StringBuilder();
      } else
        b.append(c);
    }
    if (b.length() > 0)
      page.getSuppressedMessages().add(b.toString());
    r.close();
  }

  private void loadValueSets1() throws Exception {

    page.log(" ...vocab #1", LogMessageType.Process);
    generateCodeSystemsPart1();
    generateValueSetsPart1();
    for (BindingSpecification cd : page.getDefinitions().getUnresolvedBindings()) {
      String ref = cd.getReference();
      if (ref.startsWith("http://hl7.org/fhir")) {
        // we expect to be able to resolve this
        ValueSet vs = page.getDefinitions().getValuesets().get(ref);
        if (vs == null)
          vs = page.getDefinitions().getExtraValuesets().get(ref);
        if (vs == null)
          vs = page.getWorkerContext().fetchResource(ValueSet.class, ref);
        if (vs == null) {
          if (page.getDefinitions().getBoundValueSets().containsKey(ref))
            throw new Exception("Unable to resolve the value set reference "+ref+" but found it in load list");
          throw new Exception("Unable to resolve the value set reference "+ref);
        }
        cd.setValueSet(vs);
      } else {
        ValueSet vs = page.getWorkerContext().fetchResource(ValueSet.class, ref);
        if (vs != null)
          cd.setValueSet(vs);
        else if (!ref.startsWith("http://loinc.org/vs/LL"))
          System.out.println("Unresolved value set reference: "+ref);
      }
    }
    for (ImplementationGuideDefn ig : page.getDefinitions().getSortedIgs()) {
      for (BindingSpecification cd : ig.getUnresolvedBindings()) {
        String ref = cd.getReference();
        if (ref.contains("|"))
          ref = ref.substring(0, ref.indexOf("|"));
        ValueSet vs = page.getDefinitions().getValuesets().get(ref);
        if (vs == null)
          vs = ig.getValueSet(ref);
        if (vs == null)
          vs = page.getWorkerContext().fetchResource(ValueSet.class, ref);
        if (vs == null)
          throw new Exception("unable to resolve value set "+ref);
        cd.setValueSet(vs);
      }
    }
  }

  private void loadValueSets2() throws Exception {
    page.log(" ...default Capability Statements", LogMessageType.Process);

    if (isGenerate) {
      generateConformanceStatement(true, "base", false);
      generateConformanceStatement(false, "base2", false);
      generateCompartmentDefinitions();
    }
    page.log(" ...resource CodeSystem", LogMessageType.Process);
    ResourceDefn r = page.getDefinitions().getResources().get("CodeSystem");
    if (isGenerate && wantBuild("CodeSystem")) {
      produceResource1(r, false);
      produceResource2(r, false, null, false);
    }
    generateCodeSystemsPart2();
    page.log(" ...resource ValueSet", LogMessageType.Process);
    r = page.getDefinitions().getResources().get("ValueSet");
    if (isGenerate && wantBuild("ValueSet")) {
      produceResource1(r, false);
      produceResource2(r, false, null, false);
    }
    page.log(" ...value sets", LogMessageType.Process);
    generateValueSetsPart2();
    generateConceptMaps();
    page.saveSnomed();
    if (isGenerate) {
      /// regenerate. TODO: this is silly - need to generate before so that xpaths are populated. but need to generate now to fill them properly
      generateConformanceStatement(true, "base", true);
      generateConformanceStatement(false, "base2", true);
    }
    generateCodeSystemRegistry();
//    copyTerminologyToVocabPoC();
  }
  
  private void listBoundValueSets(ElementDefn element, Map<String, ValueSet> list) {
    if (element.hasBinding() && element.typeCode().equals("code") && element.getBinding().getStrength() == BindingStrength.REQUIRED && element.getBinding().getValueSet() != null) 
      list.put(element.getBinding().getValueSet().getUrl(), element.getBinding().getValueSet());
    for (ElementDefn child : element.getElements()) 
      listBoundValueSets(child, list);
  }

  private void generateCodeSystemRegistry() throws FileNotFoundException, IOException, Exception {
    XmlParser xml = new XmlParser();
    xml.setOutputStyle(OutputStyle.PRETTY);
    Bundle bnd = (Bundle) xml.parse(new CSFileInputStream(Utilities.path(page.getFolders().srcDir, "namingsystem", "namingsystem-terminologies.xml")));
    for (BundleEntryComponent entry : bnd.getEntry()) {
      NamingSystem ns = (NamingSystem) entry.getResource();
      entry.setFullUrl("http://hl7.org/fhir/NamingSystem/"+ns.getId());
      String url = null;
      for (NamingSystemUniqueIdComponent t : ns.getUniqueId()) {
        if (t.getType() == NamingSystemIdentifierType.URI)
          url = t.getValue();
      }
      if (url != null) {
        if (url.startsWith("http://hl7.org/fhir"))
          page.getDefinitions().addNs(url, "System "+ns.getName(), "terminologies-systems.html#"+url);
        page.getDefinitions().addNs(entry.getFullUrl(), ns.getId(), "terminologies-systems.html#"+url);
      }
    }
    List<String> names = new ArrayList<String>();
    Set<String> urls = new HashSet<>();
    names.addAll(page.getCodeSystems().keys());
    Collections.sort(names);
    for (String n : names) {
      CodeSystem cs = page.getCodeSystems().get(n);
      if (cs != null && !urls.contains(cs.getUrl()) && cs.hasUrl() && !cs.getUrl().startsWith("http://terminology.hl7.org")) {
        urls.add(cs.getUrl());
        if (cs.hasName()) {
          NamingSystem ns = new NamingSystem();
          ns.setId(cs.getId());
          ns.setName(cs.getName());
          ns.setStatus(cs.getStatus());
          if (!ns.hasStatus())
            ns.setStatus(PublicationStatus.DRAFT);
          ns.setKind(NamingSystemType.CODESYSTEM);
          ns.setPublisher(cs.getPublisher());
          for (ContactDetail c : cs.getContact()) {
            ContactDetail nc = ns.addContact();
            nc.setName(c.getName());
            for (ContactPoint cc : c.getTelecom()) {
              nc.getTelecom().add(cc);
            }
          }
          ns.setDate(cs.getDate());
          if (!ns.hasDate())
            ns.setDate(page.getGenDate().getTime());
          ns.setDescription(cs.getDescription());
          ns.addUniqueId().setType(NamingSystemIdentifierType.URI).setValue(cs.getUrl()).setPreferred(true);
          String oid = CodeSystemUtilities.getOID(cs);
          if (oid != null) {
            if (oid.startsWith("urn:oid:"))
              oid = oid.substring(8);
            ns.addUniqueId().setType(NamingSystemIdentifierType.OID).setValue(oid).setPreferred(false);
          }
          ns.setUserData("path", cs.getUserData("path"));
          bnd.addEntry().setResource(ns).setFullUrl("http://hl7.org/fhir/"+ns.fhirType()+"/"+ns.getId());
        }
      }
    }
    xml.compose(new FileOutputStream(Utilities.path(page.getFolders().dstDir, "namingsystem-terminologies.xml")), bnd);
    cloneToXhtml("namingsystem-terminologies", "Terminology Registry", false, "resource-instance:NamingSystem", "Terminology Registry", null, wg("vocab"));
    xml.setOutputStyle(OutputStyle.CANONICAL);
    xml.compose(new FileOutputStream(Utilities.path(page.getFolders().dstDir, "namingsystem-terminologies.canonical.xml")), bnd);
    JsonParser json = new JsonParser();
    json.setOutputStyle(OutputStyle.PRETTY);
    json.compose(new FileOutputStream(Utilities.path(page.getFolders().dstDir, "namingsystem-terminologies.json")), bnd);
    jsonToXhtml("namingsystem-terminologies", "Terminology Registry", TextFile.fileToString(Utilities.path(page.getFolders().dstDir, "namingsystem-terminologies.json")), "resource-instance:NamingSystem", "Terminology Registry", null, wg("vocab"));
    json.setOutputStyle(OutputStyle.CANONICAL);
    json.compose(new FileOutputStream(Utilities.path(page.getFolders().dstDir, "namingsystem-terminologies.canonical.json")), bnd);
    RdfParser rdf = new RdfParser();
    rdf.setOutputStyle(OutputStyle.PRETTY);
    rdf.compose(new FileOutputStream(Utilities.path(page.getFolders().dstDir, "namingsystem-terminologies.ttl")), bnd);
    ttlToXhtml("namingsystem-terminologies", "Terminology Registry", TextFile.fileToString(Utilities.path(page.getFolders().dstDir, "namingsystem-terminologies.ttl")), "resource-instance:NamingSystem", "Terminology Registry", null, wg("vocab"));

    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append(" <tr>");
    b.append("<td><b>Name</b></td>");
    b.append("<td><b>Uri</b></td>");
    b.append("<td><b>OID</b></td>");
    b.append("</tr>\r\n");
    for (BundleEntryComponent entry : bnd.getEntry()) {
      NamingSystem ns = (NamingSystem) entry.getResource();
      String uri = "";
      String oid = "";
      for (NamingSystemUniqueIdComponent id : ns.getUniqueId()) {
        if (id.getType() == NamingSystemIdentifierType.URI)
          uri = id.getValue();
        if (id.getType() == NamingSystemIdentifierType.OID)
          oid = id.getValue();
      }
      String link = "terminologies-systems.html#"+uri;
      if (ns.getUserData("path") != null)
        link = ns.getUserString("path");
      b.append(" <tr>");
      b.append("<td><a href=\""+link+"\">"+Utilities.escapeXml(ns.getName())+"</a></td>");
      b.append("<td>"+Utilities.escapeXml(uri)+"</td>");
      b.append("<td>"+Utilities.escapeXml(oid)+"</td>");
      b.append("</tr>\r\n");
    }
    b.append("</table>\r\n");
    String html = TextFile.fileToString(page.getFolders().templateDir + "template-example.html").replace("<%example%>", b.toString()).replace("<%example-usage%>", "");
    html = page.processPageIncludes("namingsystem-terminologies.html", html, "resource-instance:NamingSystem", null, bnd, null, "Example", null, null, page.getDefinitions().getWorkgroups().get("fhir"));
    TextFile.stringToFile(html, page.getFolders().dstDir + "namingsystem-terminologies.html");
    cachePage("namingsystem-terminologies.html", html, "Registered Code Systems", false);
  }

  private WorkGroup wg(String code) {
    return page.getDefinitions().getWorkgroups().get(code);
  }

  private void buildFeedsAndMaps() {
    page.setResourceBundle(new Bundle());
    page.getResourceBundle().setId("resources");
    page.getResourceBundle().setType(BundleType.COLLECTION);
    page.getResourceBundle().setMeta(new Meta().setLastUpdated(page.getGenDate().getTime()));

    profileBundle = new Bundle();
    profileBundle.setId("profiles-others");
    profileBundle.setType(BundleType.COLLECTION);
    profileBundle.setMeta(new Meta().setLastUpdated(page.getGenDate().getTime()));

    page.setTypeBundle(new Bundle());
    page.getTypeBundle().setId("types");
    page.getTypeBundle().setType(BundleType.COLLECTION);
    page.getTypeBundle().setMeta(new Meta().setLastUpdated(page.getGenDate().getTime()));

    valueSetsFeed = new Bundle();
    valueSetsFeed.setId("valuesets");
    valueSetsFeed.setType(BundleType.COLLECTION);
    valueSetsFeed.setMeta(new Meta().setLastUpdated(page.getGenDate().getTime()));

    dataElements = new Bundle();
    dataElements.setId("dataelements");
    dataElements.setType(BundleType.COLLECTION);
    dataElements.setMeta(new Meta().setLastUpdated(page.getGenDate().getTime()));

    conceptMapsFeed = new Bundle();
    conceptMapsFeed.setId("conceptmaps");
    conceptMapsFeed.setType(BundleType.COLLECTION);
    conceptMapsFeed.setMeta(new Meta().setLastUpdated(page.getGenDate().getTime()));

    externals = new Bundle();
    externals.setId("externals");
    externals.setType(BundleType.COLLECTION);
    externals.setMeta(new Meta().setLastUpdated(page.getGenDate().getTime()));

  }

  private void generateCompartmentDefinitions() throws Exception {
    for (Compartment c : page.getDefinitions().getCompartments())
      generateCompartmentDefinition(c);
  }
  
  private void generateCompartmentDefinition(Compartment c) throws Exception {
    CompartmentDefinition cpd = new CompartmentDefinition();
    cpd.setId(c.getName());
    cpd.setUrl("http://hl7.org/fhir/CompartmentDefinition/" + c.getName());
    cpd.setName("Base FHIR compartment definition for " +c.getTitle());
    cpd.setStatus(PublicationStatus.DRAFT);
    cpd.setDescription(c.getIdentity()+". "+c.getDescription());
    cpd.setExperimental(true);
    cpd.setVersion(Constants.VERSION);
    cpd.setDate(page.getGenDate().getTime());
    cpd.setPublisher("FHIR Project Team");
    cpd.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    cpd.setCode(CompartmentType.fromCode(c.getTitle()));
    cpd.setSearch(true);
    for (String rn : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn rd = page.getDefinitions().getResourceByName(rn);
      String rules = c.getResources().get(rd);
      CompartmentDefinitionResourceComponent cc = cpd.addResource().setCode(rd.getName());
      if (!Utilities.noString(rules)) {
        for (String p : rules.split("\\|"))
          cc.addParam(p.trim());
      }
    }
    RenderingContext lrc = page.getRc().copy().setLocalPrefix("").setTooCostlyNoteEmpty(PageProcessor.TOO_MANY_CODES_TEXT_EMPTY).setTooCostlyNoteNotEmpty(PageProcessor.TOO_MANY_CODES_TEXT_NOT_EMPTY);
    RendererFactory.factory(cpd, lrc).render(cpd);
    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + "compartmentdefinition-" + c.getName().toLowerCase() + ".xml");
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, cpd);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + "compartmentdefinition-" + c.getName().toLowerCase() + ".canonical.xml");
    new XmlParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, cpd);
    s.close();
    cloneToXhtml("compartmentdefinition-" + c.getName().toLowerCase(), "Compartment Definition for "+c.getName(), true, "resource-instance:CompartmentDefinition", "Compartment Definition for "+c.getName(), null, wg("fhir"));
    s = new FileOutputStream(page.getFolders().dstDir + "compartmentdefinition-" + c.getName().toLowerCase() + ".json");
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, cpd);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + "compartmentdefinition-" + c.getName().toLowerCase() + ".canonical.json");
    new JsonParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, cpd);
    s.close();
    jsonToXhtml("compartmentdefinition-" + c.getName().toLowerCase(), "Compartment Definition for "+c.getName(), resource2Json(cpd), "resource-instance:CompartmentDefinition", "Compartment Definition for "+c.getName(), null, wg("fhir"));
    s = new FileOutputStream(page.getFolders().dstDir + "compartmentdefinition-" + c.getName().toLowerCase() + ".ttl");
    new RdfParser().setOutputStyle(OutputStyle.PRETTY).compose(s, cpd);
    s.close();
    ttlToXhtml("compartmentdefinition-" + c.getName().toLowerCase(), "Compartment Definition for "+c.getName(), resource2Ttl(cpd), "resource-instance:CompartmentDefinition", "Compartment Definition for "+c.getName(), null, wg("fhir"));

    Utilities.copyFile(new CSFile(page.getFolders().dstDir + "compartmentdefinition-" + c.getName().toLowerCase() + ".xml"), new CSFile(page.getFolders().dstDir + "examples" + File.separator
        + "compartmentdefinition-" + c.getName().toLowerCase()+ ".xml"));
    addToResourceFeed(cpd, page.getResourceBundle());
  }
  
  private void generateConformanceStatement(boolean full, String name, boolean register) throws Exception {
    pgen = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), page.getVersion(), dataElements, fpUsages, page.getFolders().rootDir, page.getUml(), page.getRc());
    CapabilityStatement cpbs = new CapabilityStatement();
    cpbs.setId(FormatUtilities.makeId(name));
    cpbs.setUrl("http://hl7.org/fhir/CapabilityStatement/" + name);
    cpbs.setVersion(page.getVersion().toCode());
    cpbs.setName("Base FHIR Capability Statement " + (full ? "(Full)" : "(Empty)"));
    cpbs.setStatus(PublicationStatus.DRAFT);
    cpbs.setExperimental(true);
    cpbs.setDate(page.getGenDate().getTime());
    cpbs.setPublisher("FHIR Project Team");
    cpbs.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    cpbs.setKind(CapabilityStatementKind.CAPABILITY);
    cpbs.setSoftware(new CapabilityStatementSoftwareComponent());
    cpbs.getSoftware().setName("Insert your software name here...");
    cpbs.setFhirVersion(page.getVersion());
    cpbs.getFormat().add(Factory.newCode("xml"));
    cpbs.getFormat().add(Factory.newCode("json"));
    CapabilityStatementRestComponent rest = new CapabilityStatement.CapabilityStatementRestComponent();
    cpbs.getRest().add(rest);
    rest.setMode(RestfulCapabilityMode.SERVER);
    if (full) {
      rest.setDocumentation("All the functionality defined in FHIR");
      cpbs.setDescription("This is the base Capability Statement for FHIR. It represents a server that provides the full set of functionality defined by FHIR. It is provided to use as a template for system designers to build their own Capability Statements from");
    } else {
      rest.setDocumentation("An empty Capability Statement");
      cpbs.setDescription("This is the base Capability Statement for FHIR. It represents a server that provides the none of the functionality defined by FHIR. It is provided to use as a template for system designers to build their own Capability Statements from. A capability statement has to contain something, so this contains a read of a Capability Statement");
    }
    rest.setSecurity(new CapabilityStatementRestSecurityComponent());
    rest.getSecurity().setCors(true);
    rest.getSecurity().addService().setText("See http://docs.smarthealthit.org/").addCoding().setSystem("http://terminology.hl7.org/CodeSystem/restful-security-service").setCode("SMART-on-FHIR").setDisplay("SMART-on-FHIR");
    rest.getSecurity().setDescription("This is the Capability Statement to declare that the server supports SMART-on-FHIR. See the SMART-on-FHIR docs for the extension that would go with such a server");

    if (full) {
      for (String rn : page.getDefinitions().sortedResourceNames()) {
        ResourceDefn rd = page.getDefinitions().getResourceByName(rn);
        CapabilityStatementRestResourceComponent res = new CapabilityStatement.CapabilityStatementRestResourceComponent();
        rest.getResource().add(res);
        res.setType(rn);
        res.setProfile("http://hl7.org/fhir/StructureDefinition/" + rn);
        genConfInteraction(cpbs, res, TypeRestfulInteraction.READ, "Implemented per the specification (or Insert other doco here)");
        genConfInteraction(cpbs, res, TypeRestfulInteraction.VREAD, "Implemented per the specification (or Insert other doco here)");
        genConfInteraction(cpbs, res, TypeRestfulInteraction.UPDATE, "Implemented per the specification (or Insert other doco here)");
        genConfInteraction(cpbs, res, TypeRestfulInteraction.DELETE, "Implemented per the specification (or Insert other doco here)");
        genConfInteraction(cpbs, res, TypeRestfulInteraction.HISTORYINSTANCE, "Implemented per the specification (or Insert other doco here)");
        genConfInteraction(cpbs, res, TypeRestfulInteraction.HISTORYTYPE, "Implemented per the specification (or Insert other doco here)");
        genConfInteraction(cpbs, res, TypeRestfulInteraction.CREATE, "Implemented per the specification (or Insert other doco here)");
        genConfInteraction(cpbs, res, TypeRestfulInteraction.SEARCHTYPE, "Implemented per the specification (or Insert other doco here)");
        res.setConditionalCreate(true);
        res.setConditionalUpdate(true);
        res.setConditionalDelete(ConditionalDeleteStatus.MULTIPLE);
        res.addReferencePolicy(ReferenceHandlingPolicy.LITERAL);
        res.addReferencePolicy(ReferenceHandlingPolicy.LOGICAL);
        for (SearchParameterDefn i : rd.getSearchParams().values()) {
          res.getSearchParam().add(makeSearchParam(rn, i));
          if (i.getType().equals(SearchType.reference))
            res.getSearchInclude().add(new StringType(rn+"."+i.getCode()));
        }
        for (String rni : page.getDefinitions().sortedResourceNames()) {
          ResourceDefn rdi = page.getDefinitions().getResourceByName(rni);
          for (SearchParameterDefn ii : rdi.getSearchParams().values()) {
            if (ii.getType().equals(SearchType.reference) && ii.getTargets().contains(rn))
              res.getSearchRevInclude().add(new StringType(rni+"."+ii.getCode()));
          }
        }
      }

      genConfInteraction(cpbs, rest, SystemRestfulInteraction.TRANSACTION, "Implemented per the specification (or Insert other doco here)");
      genConfInteraction(cpbs, rest, SystemRestfulInteraction.BATCH, "Implemented per the specification (or Insert other doco here)");
      genConfInteraction(cpbs, rest, SystemRestfulInteraction.HISTORYSYSTEM, "Implemented per the specification (or Insert other doco here)");
      genConfInteraction(cpbs, rest, SystemRestfulInteraction.SEARCHSYSTEM, "Implemented per the specification (or Insert other doco here)");

      for (ResourceDefn rd : page.getDefinitions().getBaseResources().values()) {
        for (SearchParameterDefn i : rd.getSearchParams().values())
          rest.getSearchParam().add(makeSearchParam(rd.getName(), i));
        rest.getSearchParam().add(makeSearchParam("something", SearchParamType.STRING, "id", "some doco"));
        
        rest.getSearchParam().add(makeSearchParam("_list", SearchParamType.TOKEN, "Resource-list", "Retrieval of resources that are referenced by a List resource"));
        rest.getSearchParam().add(makeSearchParam("_has", SearchParamType.COMPOSITE, "Resource-has", "Provides support for reverse chaining"));
        rest.getSearchParam().add(makeSearchParam("_type", SearchParamType.TOKEN, "Resource-type", "Type of resource (when doing cross-resource search"));
        rest.getSearchParam().add(makeSearchParam("_sort", SearchParamType.TOKEN, "Resource-source", "How to sort the resources when returning"));
        rest.getSearchParam().add(makeSearchParam("_count", SearchParamType.NUMBER, "Resource-count", "How many resources to return"));
        rest.getSearchParam().add(makeSearchParam("_include", SearchParamType.TOKEN, "Resource-include", "Control over returning additional resources (see spec)"));
        rest.getSearchParam().add(makeSearchParam("_revinclude", SearchParamType.TOKEN, "Resource-revinclude", "Control over returning additional resources (see spec)"));
        rest.getSearchParam().add(makeSearchParam("_summary", SearchParamType.TOKEN, "Resource-summary", "What kind of information to return"));
        rest.getSearchParam().add(makeSearchParam("_elements", SearchParamType.STRING, "Resource-elements", "What kind of information to return"));
        rest.getSearchParam().add(makeSearchParam("_contained", SearchParamType.TOKEN, "Resource-contained", "Managing search into contained resources"));
        rest.getSearchParam().add(makeSearchParam("_containedType", SearchParamType.TOKEN, "Resource-containedType", "Managing search into contained resources"));
        
        for (Operation op : rd.getOperations())
          rest.addOperation().setName(op.getName()).setDefinition("http://hl7.org/fhir/OperationDefinition/"+rd.getName().toLowerCase()+"-"+op.getName());
      }
      for (String rn : page.getDefinitions().sortedResourceNames()) {
        ResourceDefn r = page.getDefinitions().getResourceByName(rn);
        for (Operation op : r.getOperations())
          rest.addOperation().setName(op.getName()).setDefinition("http://hl7.org/fhir/OperationDefinition/"+r.getName().toLowerCase()+"-"+op.getName());
      }
    } else {
      // don't add anything - the metadata operation is implicit
//      CapabilityStatementRestResourceComponent res = new CapabilityStatement.CapabilityStatementRestResourceComponent();
//      rest.getResource().add(res);
//      res.setType("CapabilityStatement");
//      genConfInteraction(cpbs, res, TypeRestfulInteraction.READ, "Read CapabilityStatement Resource");
    }

    if (register) {
      RenderingContext lrc = page.getRc().copy().setLocalPrefix("").setTooCostlyNoteEmpty(PageProcessor.TOO_MANY_CODES_TEXT_EMPTY).setTooCostlyNoteNotEmpty(PageProcessor.TOO_MANY_CODES_TEXT_NOT_EMPTY);
      RendererFactory.factory(cpbs, lrc).render(cpbs);
      FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + "capabilitystatement-" + name + ".xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, cpbs);
      s.close();
      s = new FileOutputStream(page.getFolders().dstDir + "capabilitystatement-" + name + ".canonical.xml");
      new XmlParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, cpbs);
      s.close();
      cloneToXhtml("capabilitystatement-" + name + "", "Basic Capability Statement", true, "resource-instance:CapabilityStatement", "Capability Statement", null, wg("fhir"));
      s = new FileOutputStream(page.getFolders().dstDir + "capabilitystatement-" + name + ".json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, cpbs);
      s.close();
      s = new FileOutputStream(page.getFolders().dstDir + "capabilitystatement-" + name + ".canonical.json");
      new JsonParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, cpbs);
      s.close();
      jsonToXhtml("capabilitystatement-" + name, "Base Capability Statement", resource2Json(cpbs), "resource-instance:CapabilityStatement", "Capability Statement", null, wg("fhir"));
      s = new FileOutputStream(page.getFolders().dstDir + "capabilitystatement-" + name + ".ttl");
      new RdfParser().setOutputStyle(OutputStyle.PRETTY).compose(s, cpbs);
      s.close();
      ttlToXhtml("capabilitystatement-" + name, "Base Capability Statement", resource2Ttl(cpbs), "resource-instance:CapabilityStatement", "Capability Statement", null, wg("fhir"));

      Utilities.copyFile(new CSFile(page.getFolders().dstDir + "capabilitystatement-" + name + ".xml"), new CSFile(page.getFolders().dstDir + "examples" + File.separator
          + "capabilitystatement-" + name + ".xml"));
    }
    if (buildFlags.get("all")) {
      RenderingContext lrc = page.getRc().copy().setLocalPrefix("");
      RendererFactory.factory(cpbs, lrc).render(cpbs);
      deletefromFeed(ResourceType.CapabilityStatement, name, page.getResourceBundle());
      addToResourceFeed(cpbs, page.getResourceBundle());
    }
  }

  private CapabilityStatementRestResourceSearchParamComponent makeSearchParam(String name, SearchParamType type, String id, String doco) throws Exception {
    CapabilityStatementRestResourceSearchParamComponent result = new CapabilityStatement.CapabilityStatementRestResourceSearchParamComponent();
    result.setName(name);
    result.setDefinition("http://hl7.org/fhir/SearchParameter/"+id);
    result.setType(type);
    result.setDocumentation(doco);
    return result;
  }
  
  private CapabilityStatementRestResourceSearchParamComponent makeSearchParam(String rn, SearchParameterDefn i) throws Exception {
    CapabilityStatementRestResourceSearchParamComponent result = new CapabilityStatement.CapabilityStatementRestResourceSearchParamComponent();
    result.setName(i.getCode());
    result.setDefinition("http://hl7.org/fhir/SearchParameter/"+i.getCommonId());
    result.setType(getSearchParamType(i.getType()));
    result.setDocumentation(i.getDescription());
    if (Utilities.noString(i.getXPath()))
      i.setXPath(new XPathQueryGenerator(page.getDefinitions(), page, page.getQa()).generateXpath(i.getPaths(), rn)); // used elsewhere later
    return result;
  }

  private SearchParamType getSearchParamType(SearchType type) {
    switch (type) {
    case number:
      return SearchParamType.NUMBER;
    case string:
      return SearchParamType.STRING;
    case date:
      return SearchParamType.DATE;
    case reference:
      return SearchParamType.REFERENCE;
    case token:
      return SearchParamType.TOKEN;
    case uri:
      return SearchParamType.URI;
    case composite:
      return SearchParamType.COMPOSITE;
    case quantity:
      return SearchParamType.QUANTITY;
    case special:
      return SearchParamType.SPECIAL;
    }
    return null;
  }

  private void genConfInteraction(CapabilityStatement conf, CapabilityStatementRestResourceComponent res, TypeRestfulInteraction op, String doco) {
    ResourceInteractionComponent t = new ResourceInteractionComponent();
    t.setCode(op);
    t.setDocumentation(doco);
    res.getInteraction().add(t);
  }

  private void genConfInteraction(CapabilityStatement conf, CapabilityStatementRestComponent res, SystemRestfulInteraction op, String doco) {
    SystemInteractionComponent t = new SystemInteractionComponent();
    t.setCode(op);
    t.setDocumentation(doco);
    res.getInteraction().add(t);
  }

  public boolean checkFile(String purpose, String dir, String file, List<String> errors, String category) throws IOException {
    CSFile f = new CSFile(dir + file);
    if (file.contains("*"))
      return true;

    if (!f.exists()) {
      errors.add("Unable to find " + purpose + " file " + file + " in " + dir);
      return false;
    } else if (category != null) {
      long d = f.lastModified();
      if ((!dates.containsKey(category) || d > dates.get(category)) && !f.getAbsolutePath().endsWith(".gen.svg") && !f.getName().contains("please-close-this-in-excel-and-return-the-build-prior-to-committing") )
        dates.put(category, d);
      return true;
    } else
      return true;
  }

  private boolean initialize(String folder) throws Exception {
    HierarchicalTableGenerator.ACTIVE_TABLES = true;
    page.setDefinitions(new Definitions());
    page.getWorkerContext().setCanRunWithoutTerminology(!web);

    page.log("Checking Source for " + folder, LogMessageType.Process);

    List<String> errors = new ArrayList<String>();

    Utilities.checkFolder(page.getFolders().rootDir, errors);
    if (checkFile("required", page.getFolders().rootDir, "publish.ini", errors, "all")) {
      checkFile("required", page.getFolders().srcDir, "navigation.xml", errors, "all");
      page.setIni(new IniFile(page.getFolders().rootDir + "publish.ini"));
      page.setVersion(FHIRVersion.fromCode(page.getIni().getStringProperty("FHIR", "version")));

      prsr = new SourceParser(page, folder, page.getDefinitions(), web, page.getVersion(), page.getWorkerContext(), page.getGenDate(), page, fpUsages, isCIBuild);
      prsr.checkConditions(errors, dates);
      page.setRegistry(prsr.getRegistry());
      page.getDiffEngine().loadFromIni(prsr.getIni());

      for (String s : page.getIni().getPropertyNames("special-pages"))
        page.getDefinitions().getStructuralPages().add(s);

      Utilities.checkFolder(page.getFolders().xsdDir, errors);
      checkFile("required", page.getFolders().srcDir, "hierarchy.xml", errors, "all");
      checkFile("required", page.getFolders().srcDir, "fhir-all.xsd", errors, "all");
      checkFile("required", page.getFolders().templateDir, "template.html", errors, "all");
      checkFile("required", page.getFolders().templateDir, "template-book.html", errors, "all");
      checkFile("required", page.getFolders().srcDir, "mappingSpaces.xml", errors, "all");
      // Utilities.checkFolder(page.getFolders().dstDir, errors);

      if (page.getIni().getPropertyNames("support") != null)
        for (String n : page.getIni().getPropertyNames("support"))
          checkFile("support", page.getFolders().srcDir, n, errors, "all");
      for (String n : page.getIni().getPropertyNames("images"))
        checkFile("image", page.getFolders().imgDir, n, errors, "all");
      for (String n : page.getIni().getPropertyNames("schema"))
        checkFile("schema", page.getFolders().srcDir, n, errors, "all");
      for (String n : page.getIni().getPropertyNames("pages"))
        checkFile("page", page.getFolders().srcDir, n, errors, "page-" + n);
      for (String n : page.getIni().getPropertyNames("files"))
        checkFile("file", page.getFolders().rootDir, n, errors, "page-" + n);
    }
    if (checkFile("translations", page.getFolders().rootDir + "implementations" + File.separator, "translations.xml", errors, null)) {
      // schema check
      XmlValidator xv = new XmlValidator(page.getValidationErrors(), page.getFolders().rootDir + "implementations", Utilities.path(page.getFolders().rootDir, "tools", "schematron"), new String[] {"translations.xsd"});
      xv.checkBySchema(Utilities.path(page.getFolders().rootDir, "implementations", "translations.xml"), true);
      Utilities.copyFile(page.getFolders().rootDir + "implementations" + File.separator + "translations.xml", page.getFolders().dstDir + "translations.xml");
      page.getTranslations().setLang("en");
      page.getTranslations().load(page.getFolders().rootDir + "implementations" + File.separator + "translations.xml");
    }

    if (errors.size() > 0)
      page.log("Unable to publish FHIR specification:", LogMessageType.Error);
    for (String e : errors) {
      page.log(e, LogMessageType.Error);
    }
    return errors.size() == 0;
  }

  private void validate() throws Exception {
    page.log("Validating", LogMessageType.Process);
    ResourceValidator val = new ResourceValidator(page.getDefinitions(), page.getTranslations(), page.getCodeSystems(), page.getFolders().srcDir, fpUsages, page.getSuppressedMessages(), page.getWorkerContext());
    val.resolvePatterns();
    ProfileValidator valp = new ProfileValidator(page.getWorkerContext());

    for (String n : page.getDefinitions().getTypes().keySet())
      page.getValidationErrors().addAll(val.checkStucture(n, page.getDefinitions().getTypes().get(n)));
    
    val.checkSearchParams(page.getValidationErrors(), page.getDefinitions().getResourceByName("Resource"));
    val.checkSearchParams(page.getValidationErrors(), page.getDefinitions().getResourceByName("DomainResource"));
    
    for (String n : page.getDefinitions().sortedResourceNames())
      if (hasBuildFlag("page-" + n.toLowerCase()))
        page.getValidationErrors().addAll(val.check(n, page.getDefinitions().getResources().get(n)));
    page.getValidationErrors().addAll(val.check("Parameters", page.getDefinitions().getResourceByName("Parameters")));

    for (String rname : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn r = page.getDefinitions().getResources().get(rname);
      checkExampleLinks(page.getValidationErrors(), r);
    }
    for (Compartment cmp : page.getDefinitions().getCompartments())
      page.getValidationErrors().addAll(val.check(cmp));
    
    page.setPatternFinder(val.getPatternFinder());
    val.report();
    val.summariseSearchTypes(page.getSearchTypeUsage());
    val.dumpParams();
    val.close();
    checkAllOk();
  }

  private void checkAllOk() throws Exception {
//    page.getCollectedValidationErrors().addAll(page.getValidationErrors());
    boolean mustDie = false;
    for (ValidationMessage e : page.getValidationErrors()) {
      if (e.getLevel() == IssueSeverity.ERROR || e.getLevel() == IssueSeverity.FATAL) {
        page.log(e.summary(), LogMessageType.Error);
        mustDie = true;
      }
    }
    if (mustDie) {
      page.log("Didn't publish FHIR due to errors @ " + Config.DATE_FORMAT().format(Calendar.getInstance().getTime()), LogMessageType.Process);
      throw new Exception("Errors executing build. Details logged.");
    }
  }

  private void processWarnings(boolean showOnlyErrors) throws Exception {
    String xslt = Utilities.path(page.getFolders().rootDir, "implementations", "xmltools", "OwnerResources.xslt");
    OutputStreamWriter s = new OutputStreamWriter(new FileOutputStream(page.getFolders().dstDir + "warnings.xml"), "UTF-8");
    s.write("<warnings>");
    for (WorkGroup wg : page.getDefinitions().getWorkgroups().values()) {
      s.write("<wg code=\""+wg.getCode()+"\" name=\""+wg.getName()+"\" url=\""+wg.getUrl()+"\"/>\r\n");
    }
    for (PageInformation pn : page.getDefinitions().getPageInfo().values()) {
      s.write("<page name=\""+pn.getName()+"\" wg=\""+pn.getWgCode()+"\" fmm=\""+pn.getFmm()+"\"/>\r\n");
    }
    try {
      s.write(new String(XsltUtilities.saxonTransform(page.getFolders().dstDir + "profiles-resources.xml", xslt)));
      s.write(new String(XsltUtilities.saxonTransform(page.getFolders().dstDir + "profiles-types.xml", xslt)));
      s.write(new String(XsltUtilities.saxonTransform(page.getFolders().dstDir + "profiles-others.xml", xslt)));
    } catch (Exception e) {
      for (ValidationMessage err : page.getValidationErrors()) {
        if (!page.getSuppressedMessages().contains(err.getDisplay()))
          System.out.println(err.summary());
      }
      System.out.println("WARNING: Unable to create warnings file - one or more profiles-* files unavailable or invalid");
      System.out.println("To determine the cause of the build failure, look in the log prior to the warning and information messages immediately above");
    }

    
    for (ValidationMessage e : page.getValidationErrors()) {
      if (!page.getSuppressedMessages().contains(e.getDisplay()))
        s.write(e.toXML());
    }

    s.write("</warnings>");
    s.flush();
    s.close();

    String xslt2 = Utilities.path(page.getFolders().rootDir, "implementations", "xmltools", "CategorizeWarnings.xslt");
    FileOutputStream s2 = new FileOutputStream(page.getFolders().dstDir + "work-group-warnings.xml");
    try {
      s2.write(XsltUtilities.saxonTransform(page.getFolders().dstDir + "warnings.xml", xslt2).getBytes("UTF8"));
    } catch (Exception e) {
      // nothing - do not want to know.
    }
    s2.flush();
    s2.close();

    String xslt3 = Utilities.path(page.getFolders().rootDir, "implementations", "xmltools", "RenderWarnings.xslt");
    try {
      String hw = XsltUtilities.saxonTransform(page.getFolders().dstDir + "work-group-warnings.xml", xslt3);
      if (!showOnlyErrors)
        page.log(hw, LogMessageType.Process);
    } catch (Exception e) {
      // nothing - do not want to know.
    }

    int i = 0;
    int w = 0;
    int ee = 0;
    for (ValidationMessage e : page.getValidationErrors()) {
      if (e.getLevel() == IssueSeverity.ERROR || e.getLevel() == IssueSeverity.FATAL) {
        ee++;
        page.log(e.summary(), LogMessageType.Hint);
      } else if (e.getLevel() == IssueSeverity.WARNING) {
        w++;
      } else if (e.getLevel() == IssueSeverity.INFORMATION) {
        i++;
      }
    }
    page.getQa().setCounts(ee, w, i);
  }

  private boolean hasBuildFlag(String n) {
    return (buildFlags.containsKey("all") && buildFlags.get("all")) || (buildFlags.containsKey(n) && buildFlags.get(n));
  }

  private boolean wantBuild(String rname) {
    rname = rname.toLowerCase();
    return buildFlags.get("all") || (!buildFlags.containsKey(rname) || buildFlags.get(rname));
  }

  private void checkExampleLinks(List<ValidationMessage> errors, ResourceDefn r) throws Exception {
    for (Example e : r.getExamples()) {
      try {
        if (e.getXml() != null) {
          List<ExampleReference> refs = new ArrayList<ExampleReference>();
          listLinks(e.getXml().getDocumentElement(), refs);
          for (ExampleReference ref : refs) {
            if (!ref.isExempt() && !resolveLink(ref, e)) {
              String path = ref.getPath().replace("/f:", ".").substring(1)+" (example "+e.getTitle()+")";
              if (ref.hasType() && page.getDefinitions().hasResource(ref.getType())) {
                errors.add(new ValidationMessage(Source.ExampleValidator, IssueType.BUSINESSRULE, -1, -1, path,
                    "Unable to resolve example reference to " + ref.getRef() + " in " + e.getTitle() + " (Possible Ids: " + listTargetIds(ref.getType())+")",
                    "Unable to resolve example reference to " + ref.getRef() + " in <a href=\""+e.getTitle() + ".html"+"\">" + e.getTitle() + "</a> (Possible Ids: " + listTargetIds(ref.getType())+")",
                    IssueSeverity.INFORMATION/*WARNING*/));
              } else {
                String regex = "((http|https)://([A-Za-z0-9\\\\\\/\\.\\:\\%\\$])*)?("+page.pipeResources()+")\\/"+FormatUtilities.ID_REGEX+"(\\/_history\\/"+FormatUtilities.ID_REGEX+")?";
                if (ref.getRef().matches(regex)) {
                  errors.add(new ValidationMessage(Source.ExampleValidator, IssueType.BUSINESSRULE, -1, -1, path,
                      "Unable to resolve example reference " + ref.getRef() + " in " + e.getTitle(),
                      "Unable to resolve example reference " + ref.getRef() + " in <a href=\""+e.getTitle() + ".html"+"\">" + e.getTitle() + "</a>",
                      IssueSeverity.INFORMATION/*WARNING*/));
                } else {
                  errors.add(new ValidationMessage(Source.ExampleValidator, IssueType.BUSINESSRULE, -1, -1, path,
                      "Unable to resolve invalid example reference " + ref.getRef() + " in " + e.getTitle(),
                      "Unable to resolve invalid example reference " + ref.getRef() + " in <a href=\""+e.getTitle() + ".html"+"\">" + e.getTitle() + "</a>",
                      IssueSeverity.WARNING));
                }
              }
//            System.out.println("unresolved reference "+ref.getRef()+" at "+path);
            }
          }
        }
      } catch (Exception ex) {
        throw new Exception("Error checking example " + e.getTitle() + ":" + ex.getMessage(), ex);
      }
    }
  }

  private String listTargetIds(String type) throws Exception {
    StringBuilder b = new StringBuilder();
    ResourceDefn r = page.getDefinitions().getResourceByName(type);
    if (r != null) {
      for (Example e : r.getExamples()) {
        if (!Utilities.noString(e.getId()))
          b.append(e.getId()).append(", ");
        if (e.getXml() != null) {
          if (e.getXml().getDocumentElement().getLocalName().equals("feed")) {
            List<Element> entries = new ArrayList<Element>();
            XMLUtil.getNamedChildren(e.getXml().getDocumentElement(), "entry", entries);
            for (Element c : entries) {
              String id = XMLUtil.getNamedChild(c, "id").getTextContent();
              if (id.startsWith("http://hl7.org/fhir/") && id.contains("@"))
                b.append(id.substring(id.indexOf("@") + 1)).append(", ");
              else
                b.append(id).append(", ");
            }
          }
        }
      }
    } else
      b.append("(unknown resource type)");
    return b.toString();
  }

  private boolean resolveLink(ExampleReference ref, Example src) throws Exception {
    if (!ref.hasType() && ref.getId() == null)
      return false;
    if (!ref.hasType() && ref.getId().startsWith("#"))
      return true;
    if (!ref.hasType() || !page.getDefinitions().hasResource(ref.getType()))
      return false;
    if (ref.getId().startsWith("#"))
      return false;
    String id = ref.getId(); 
    ResourceDefn r = page.getDefinitions().getResourceByName(ref.getType());
    for (Example e : r.getExamples()) {
      if (id.equals(e.getId())) {
        e.getInbounds().add(src);
        return true;
      }
      if (e.getXml() != null) {
        if (resolveLinkInBundle(ref, src, e, id))
          return true;
      }
    }
    // didn't find it? well, we'll look through all the other examples looking for bundles that contain it
    for (ResourceDefn rt : page.getDefinitions().getResources().values()) {
      for (Example e : rt.getExamples()) {
        if (e.getXml() != null) {
          if (resolveLinkInBundle(ref, src, e, id))
            return true;
        }
      }
    }
    // still not found?
    if (ref.type.equals("ConceptMap"))
      return page.getConceptMaps().has("http://hl7.org/fhir/"+ref.type+"/"+ref.getId());
    if (ref.type.equals("StructureDefinition")) {
      if (page.getDefinitions().hasResource(ref.getId()))
        return true;
      if (page.getProfiles().has("http://hl7.org/fhir/"+ref.type+"/"+ref.getId()) || page.getWorkerContext().hasResource(StructureDefinition.class, "http://hl7.org/fhir/"+ref.type+"/"+ref.getId()))
        return true;
      for (Profile cp : page.getDefinitions().getPackList())
        for (ConstraintStructure p : cp.getProfiles())
          if (p.getId().equals(id))
            return true;
      for (ResourceDefn rd : page.getDefinitions().getResources().values())
        for (Profile cp : rd.getConformancePackages())
          for (ConstraintStructure p : cp.getProfiles())
            if (p.getId().equals(id))
              return true;
    }
    return false;
  }

  private boolean resolveLinkInBundle(ExampleReference ref, Example src, Example e, String id) {
    if (e.getXml().getDocumentElement().getLocalName().equals("Bundle")) {
      List<Element> entries = new ArrayList<Element>();
      XMLUtil.getNamedChildren(e.getXml().getDocumentElement(), "entry", entries);
      for (Element c : entries) {
        Element resh = XMLUtil.getNamedChild(c, "resource");
        if (resh != null) {
          Element res = XMLUtil.getFirstChild(resh);
          String _id = XMLUtil.getNamedChildValue(res, "id");
          if (id.equals(_id) && ref.getType().equals(res.getLocalName())) {
            e.getInbounds().add(src);
            return true;
          }
        }
      }
    }
    return false;
  }

  private void listLinks(Element xml, List<ExampleReference> refs) throws Exception {
    if (xml.getLocalName().equals("feed")) {
      Element n = XMLUtil.getFirstChild(xml);
      while (n != null) {
        if (n.getLocalName().equals("entry")) {
          Element c = XMLUtil.getNamedChild(n, "content");
          listLinks(XMLUtil.getFirstChild(c), refs);
        }
        n = XMLUtil.getNextSibling(n);
      }
    } else {
      String n = xml.getLocalName();
      if (!n.equals("Binary")) {
        ResourceDefn r = page.getDefinitions().getResourceByName(n);
        if (r == null)
          throw new Exception("Unable to find resource definition for " + n);
        List<Element> nodes = new ArrayList<Element>();
        nodes.add(xml);
        listLinks("/f:" + n, r.getRoot(), nodes, refs);

        Element e = XMLUtil.getFirstChild(xml);
        while (e != null) {
          if (e.getNodeName().equals("contained")) {
            listLinks(XMLUtil.getFirstChild(e), refs);
          }
          e = XMLUtil.getNextSibling(e);
        }

      }
    }
  }

  private void listLinks(String path, org.hl7.fhir.definitions.model.ElementDefn d, List<Element> set, List<ExampleReference> refs) throws Exception {
    if (d.typeCode().startsWith("Reference")) {
      for (Element m : set) {
        if (XMLUtil.getNamedChild(m, "reference") != null) {
          refs.add(new ExampleReference(XMLUtil.getNamedChildValue(m, "reference"), path));
        }
      }
    }
    if (d.typeCode().startsWith("canonical")) {
      for (Element m : set) {
        if (!Utilities.noString(m.getAttribute("value"))) {
          refs.add(new ExampleReference(m.getAttribute("value"), path));
        }
      }
    }
    for (org.hl7.fhir.definitions.model.ElementDefn c : d.getElements()) {
      List<Element> cset = new ArrayList<Element>();
      for (Element p : set)
        XMLUtil.getNamedChildren(p, c.getName(), cset);
      listLinks(path + "/f:" + c.getName(), c, cset, refs);
    }
  }

  // private List<Element> xPathQuery(String path, Element e) throws Exception {
  // NamespaceContext context = new NamespaceContextMap("f",
  // "http://hl7.org/fhir", "h", "http://www.w3.org/1999/xhtml", "a", );
  //
  // XPathFactory factory = XPathFactory.newInstance();
  // XPath xpath = factory.newXPath();
  // xpath.setNamespaceContext(context);
  // XPathExpression expression= xpath.compile(path);
  // NodeList resultNodes = (NodeList)expression.evaluate(e,
  // XPathConstants.NODESET);
  // List<Element> result = new ArrayList<Element>();
  // for (int i = 0; i < resultNodes.getLength(); i++) {
  // result.add((Element) resultNodes.item(i));
  // }
  // return result;
  // }


  private void produceSpecification() throws Exception {
    page.setNavigation(new Navigation());
    page.getNavigation().parse(page.getFolders().srcDir + "navigation.xml");

    processCDA();
    page.log("Generate RDF", LogMessageType.Process);
    processRDF();

    page.log("Produce Schemas", LogMessageType.Process);
    new SchemaGenerator().generate(page.getDefinitions(), page.getIni(), page.getFolders().tmpResDir, page.getFolders().xsdDir+"codegen"+File.separator, page.getFolders().dstDir,
        page.getFolders().srcDir, page.getVersion().toCode(), Config.DATE_FORMAT().format(page.getGenDate().getTime()), true, page.getWorkerContext());
    new SchemaGenerator().generate(page.getDefinitions(), page.getIni(), page.getFolders().tmpResDir, page.getFolders().xsdDir, page.getFolders().dstDir,
        page.getFolders().srcDir, page.getVersion().toCode(), Config.DATE_FORMAT().format(page.getGenDate().getTime()), false, page.getWorkerContext());
    new org.hl7.fhir.definitions.generators.specification.json.SchemaGenerator().generate(page.getDefinitions(), page.getIni(), page.getFolders().tmpResDir, page.getFolders().xsdDir, page.getFolders().dstDir,
        page.getFolders().srcDir, page.getVersion().toCode(), Config.DATE_FORMAT().format(page.getGenDate().getTime()), page.getWorkerContext());
    new org.hl7.fhir.definitions.generators.specification.json.JsonLDDefinitionsGenerator().generate(page.getDefinitions(), page.getIni(), page.getFolders().tmpResDir, page.getFolders().dstDir,
        page.getFolders().srcDir, page.getVersion().toCode(), Config.DATE_FORMAT().format(page.getGenDate().getTime()), page.getWorkerContext());

    List<StructureDefinition> list = new ArrayList<StructureDefinition>();
    for (StructureDefinition sd : page.getWorkerContext().allStructures()) {
      if (sd.getDerivation() == TypeDerivationRule.SPECIALIZATION)
        list.add(sd);
    }
    ShExGenerator shgen = new ShExGenerator(page.getWorkerContext());
    shgen.completeModel = true;
    shgen.withComments = false;
    TextFile.stringToFile(shgen.generate(HTMLLinkPolicy.NONE, list), page.getFolders().dstDir+"fhir.shex", false);

    new XVerPathsGenerator(page.getDefinitions(), Utilities.path(page.getFolders().dstDir, "xver-paths-"+Constants.VERSION_MM+".json"), Utilities.path(page.getFolders().rootDir, "tools", "history", "release4", "xver-paths-4.0.json")).execute();
    GraphQLSchemaGenerator gql = new GraphQLSchemaGenerator(page.getWorkerContext());
    gql.generateTypes(new FileOutputStream(Utilities.path(page.getFolders().dstDir, "types.graphql")));
    Set<String> names = new HashSet<String>();
    for (StructureDefinition sd : page.getWorkerContext().allStructures()) {
      if (sd.getKind() == StructureDefinitionKind.RESOURCE && sd.getAbstract() == false && sd.getDerivation() == TypeDerivationRule.SPECIALIZATION && !names.contains(sd.getUrl())) {
        String filename = Utilities.path(page.getFolders().dstDir, sd.getName().toLowerCase() + ".graphql");
        names.add(sd.getUrl());
        List<SearchParameter> splist = new ArrayList<SearchParameter>();
        ResourceDefn rd = page.getDefinitions().getResourceByName(sd.getName());
        while (rd != null) {
          for (String n : sorted(rd.getSearchParams().keySet())) {
            SearchParameterDefn spd = rd.getSearchParams().get(n);
            if (spd.getResource() == null)
              buildSearchDefinition(rd, spd);
            splist.add(spd.getResource());
          }
          rd = "Base".equals(rd.getRoot().typeCode())  ? null : page.getDefinitions().getResourceByName(rd.getRoot().typeCode());
        }
        EnumSet<FHIROperationType> ops = EnumSet.of(FHIROperationType.READ, FHIROperationType.SEARCH, FHIROperationType.CREATE, FHIROperationType.UPDATE, FHIROperationType.DELETE);
        gql.generateResource(new FileOutputStream(filename), sd, splist, ops);
      }
    }
    
    TextFile.stringToFile(page.genBackboneElementsJson(), Utilities.path(page.getFolders().dstDir, "backbone-elements.json"));
    TextFile.stringToFile(page.genChoiceElementsJson(), Utilities.path(page.getFolders().dstDir, "choice-elements.json"));

    page.log("Produce Schematrons", LogMessageType.Process);
    for (String rname : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn r = page.getDefinitions().getResources().get(rname);
      String n = r.getName().toLowerCase();
      SchematronGenerator sch = new SchematronGenerator(page);
      sch.generate(new FileOutputStream(page.getFolders().dstDir + n + ".sch"), r, page.getDefinitions());
    }

    ResourceDefn r = page.getDefinitions().getBaseResources().get("Parameters");
    String n = r.getName().toLowerCase();
    SchematronGenerator sch = new SchematronGenerator(page);
    sch.generate(new FileOutputStream(page.getFolders().dstDir + n + ".sch"), r, page.getDefinitions());


    SchematronGenerator sg = new SchematronGenerator(page);
    sg.generate(new FileOutputStream(page.getFolders().dstDir + "fhir-invariants.sch"), page.getDefinitions());

    produceSchemaZip();

    page.log("Load R4 Definitions", LogMessageType.Process);
    loadR4Definitions();
    page.log("Produce Content", LogMessageType.Process);
    produceSpec();

    if (buildFlags.get("all")) {
      if (web) {
        generateRedirects();
      }
    }
    page.clean();
  }

  private List<String> sorted(Set<String> keys) {
    List<String> sl = new ArrayList<String>();
    sl.addAll(keys);
    Collections.sort(sl);
    return sl;
  }


  private void loadR4Definitions() throws FileNotFoundException, FHIRException, IOException {
    loadR4DefinitionBundle(page.getDiffEngine().getOriginal().getTypes(), Utilities.path(page.getFolders().rootDir, "tools", "history", "release4", "profiles-types.xml"));
    loadR4DefinitionBundle(page.getDiffEngine().getOriginal().getResources(), Utilities.path(page.getFolders().rootDir, "tools", "history", "release4", "profiles-resources.xml"));
    loadR4DefinitionBundle(page.getDiffEngine().getOriginal().getExtensions(), Utilities.path(page.getFolders().rootDir, "tools", "history", "release4", "extension-definitions.xml"));
    loadR4DefinitionBundle(page.getDiffEngine().getOriginal().getProfiles(), Utilities.path(page.getFolders().rootDir, "tools", "history", "release4", "profiles-others.xml"));
    loadValueSetBundle(page.getDiffEngine().getOriginal().getExpansions(), Utilities.path(page.getFolders().rootDir, "tools", "history", "release4", "expansions.xml"));
    loadValueSetBundle(page.getDiffEngine().getOriginal().getValuesets(), Utilities.path(page.getFolders().rootDir, "tools", "history", "release4", "valuesets.xml"));
  }

  private void loadR4DefinitionBundle(Map<String, StructureDefinition> map, String fn) throws FHIRException, FileNotFoundException, IOException {
    org.hl7.fhir.r4.model.Bundle bundle = (org.hl7.fhir.r4.model.Bundle) new org.hl7.fhir.r4.formats.XmlParser().parse(new FileInputStream(fn));
    for (org.hl7.fhir.r4.model.Bundle.BundleEntryComponent be : bundle.getEntry()) {
      if (be.getResource() instanceof org.hl7.fhir.r4.model.StructureDefinition) {
        org.hl7.fhir.r4.model.StructureDefinition sd = (org.hl7.fhir.r4.model.StructureDefinition) be.getResource();
        map.put(sd.getName(), org.hl7.fhir.convertors.conv40_50.StructureDefinition40_50.convertStructureDefinition(sd));
      }
    }
  }
  
  private static void loadValueSetBundle(Map<String, ValueSet> map, String fn) throws FHIRException, FileNotFoundException, IOException {
    org.hl7.fhir.r4.model.Bundle bundle = (org.hl7.fhir.r4.model.Bundle) new org.hl7.fhir.r4.formats.XmlParser().parse(new FileInputStream(fn));
    for (org.hl7.fhir.r4.model.Bundle.BundleEntryComponent be : bundle.getEntry()) {
      if (be.getResource() instanceof org.hl7.fhir.r4.model.ValueSet) {
        org.hl7.fhir.r4.model.ValueSet sd = (org.hl7.fhir.r4.model.ValueSet) be.getResource();
        sd.setUserData("old", "r4");
        map.put(sd.getName(), org.hl7.fhir.convertors.conv40_50.ValueSet40_50.convertValueSet(sd));
      }
    }    
  }

  private void processCDA() {
    CDAGenerator gen = new CDAGenerator();
//    gen.execute(src, dst);
    
  }

  private void processRDF() throws Exception, FileNotFoundException {
    // first, process the RIM file
    String rim = TextFile.fileToString(Utilities.path(page.getFolders().rootDir, "tools", "tx", "v3", "rim.ttl"));
    ByteArrayOutputStream tmp = new ByteArrayOutputStream();
    FhirTurtleGenerator ttl = new FhirTurtleGenerator(tmp, page.getDefinitions(), page.getWorkerContext(), page.getValidationErrors());
    ttl.executeV3(page.getValueSets(), page.getCodeSystems());
    rim = rim + tmp.toString();
    TextFile.stringToFile(rim, Utilities.path(page.getFolders().dstDir, "rim.ttl"));
    ttl = new FhirTurtleGenerator(new FileOutputStream(Utilities.path(page.getFolders().dstDir, "fhir.ttl")), page.getDefinitions(), page.getWorkerContext(), page.getValidationErrors());
    ttl.executeMain();
    W5TurtleGenerator w5 = new W5TurtleGenerator(new FileOutputStream(Utilities.path(page.getFolders().dstDir, "w5.ttl")), page.getDefinitions(), page.getWorkerContext(), page.getValidationErrors());
    w5.executeMain();
    RDFValidator val = new RDFValidator();
    val.validate(Utilities.path(page.getFolders().dstDir, "fhir.ttl"));
    val.validate(Utilities.path(page.getFolders().dstDir, "rim.ttl"));
    val.validate(Utilities.path(page.getFolders().dstDir, "w5.ttl"));
    ZipGenerator zip = new ZipGenerator(Utilities.path(page.getFolders().dstDir, "fhir.rdf.ttl.zip"));
    zip.addFileName("fhir.ttl", Utilities.path(page.getFolders().dstDir, "fhir.ttl"), false);
    zip.addFileName("rim.ttl", Utilities.path(page.getFolders().dstDir, "rim.ttl"), false);
    zip.addFileName("w5.ttl", Utilities.path(page.getFolders().dstDir, "w5.ttl"), false);
    zip.close();

    // now that the RDF is generated, run any sparql rules that have been defined
    Element test = loadDom(new FileInputStream(Utilities.path(page.getFolders().srcDir, "sparql-rules.xml")), false).getDocumentElement();
    test = XMLUtil.getFirstChild(test);
    while (test != null) {
      if (test.getNodeName().equals("assertion")) {
        String sparql = test.getTextContent();
        page.getValidationErrors().addAll(val.assertion(sparql, test.getAttribute("id"), test.getAttribute("rowtype"), test.getAttribute("message"), test.getAttribute("description"), IssueSeverity.fromCode(test.getAttribute("level"))));
      }
      test = XMLUtil.getNextSibling(test);
    }
    checkAllOk();
  }


  private void produceArchive() throws Exception {
    String target = page.getFolders().archiveDir + "v" + page.getVersion() + ".zip";
    File tf = new CSFile(target);
    if (tf.exists())
      tf.delete();

    ZipGenerator zip = new ZipGenerator(target);

    int c = 0;
    String[] files = new CSFile(page.getFolders().dstDir).list();
    for (String f : files) {
      File fn = new CSFile(page.getFolders().dstDir + f);
      if (!fn.isDirectory()) {
        if (f.endsWith(".html")) {
          String src = TextFile.fileToString(fn.getAbsolutePath());
          String srcn = src.replace("<!-- achive note -->",
              "This is an old version of FHIR retained for archive purposes. Do not use for anything else");
          if (!srcn.equals(src))
            c++;
          srcn = srcn.replace("<body>", "<body><div class=\"watermark\"/>").replace("<body class=\"book\">", "<body class=\"book\"><div class=\"watermark\"/>");
          zip.addFileSource(f, srcn, false);
          // Utilities.stringToFile(srcn, target+File.separator+f);
        } else if (f.endsWith(".css")) {
          String src = TextFile.fileToString(fn.getAbsolutePath());
          src = src.replace("#fff", "lightcyan");
          zip.addFileSource(f, src, false);
          // Utilities.stringToFile(srcn, target+File.separator+f);
        } else
          zip.addFileName(f, fn.getAbsolutePath(), false);
      } else if (!fn.getAbsolutePath().endsWith("v2") && !fn.getAbsolutePath().endsWith("v3")) {
        // used to put stuff in sub-directories. clean them out if they
        // still exist
        // Utilities.clearDirectory(fn.getAbsolutePath());
        // fn.delete();
      }
    }
    if (c < 3)
      throw new Exception("header note replacement in archive failed"); // so
    // check
    // the
    // syntax
    // of
    // the
    // string
    // constant
    // above
    zip.close();
  }

  private void produceSpec() throws Exception {
    for (ImplementationGuideDefn ig : page.getDefinitions().getSortedIgs()) {
      for (LogicalModel lm : ig.getLogicalModels()) {
        page.log(" ...logical model " + lm.getId(), LogMessageType.Process);
        produceLogicalModel(lm, ig);
      }
    }
    
    for (StructureDefinition ed : page.getWorkerContext().getExtensionDefinitions()) {
      String filename = "extension-"+(ed.getUrl().startsWith("http://fhir-registry.smarthealthit.org/StructureDefinition/") ? ed.getUrl().substring(59).toLowerCase() : ed.getUrl().substring(40).toLowerCase());
      ed.setUserData("filename", filename);
      ImplementationGuideDefn ig = page.getDefinitions().getIgs().get(ed.getUserString(ToolResourceUtilities.NAME_RES_IG));
      ed.setUserData("path", (ig.isCore() ? "" : ig.getCode()+File.separator) + filename+".html");
    }

    page.updateDiffEngineDefinitions();
    
    loadValueSets2();
    page.log(" ...extensions", LogMessageType.Process);

    for (StructureDefinition ae : page.getWorkerContext().getExtensionDefinitions())
      produceExtensionDefinition(ae);
    checkAllOk();

    page.log(" ...resource identities", LogMessageType.Process);
    for (String rname : page.getDefinitions().getBaseResources().keySet()) {
      ResourceDefn r = page.getDefinitions().getBaseResources().get(rname);
      produceResource1(r, r.isAbstract());
    }
    for (String rname : page.getDefinitions().sortedResourceNames()) {
      if (!rname.equals("ValueSet") && !rname.equals("CodeSystem") && wantBuild(rname)) {
        ResourceDefn r = page.getDefinitions().getResources().get(rname);
        produceResource1(r, false);
      }
    }
    if (buildFlags.get("all")) {
      page.log(" ...base profiles", LogMessageType.Process);
      produceBaseProfile();
    }
    for (String rname : page.getDefinitions().getBaseResources().keySet()) {
      ResourceDefn r = page.getDefinitions().getBaseResources().get(rname);
      page.log(" ...resource " + r.getName(), LogMessageType.Process);
      produceResource2(r, !rname.equals("Parameters"), rname.equals("Resource") ? "Meta" : null, false);
    }
    for (String rname : page.getDefinitions().sortedResourceNames()) {
      if (!rname.equals("ValueSet") && !rname.equals("CodeSystem") && wantBuild(rname)) {
        ResourceDefn r = page.getDefinitions().getResources().get(rname);
        page.log(" ...resource " + r.getName(), LogMessageType.Process);
        produceResource2(r, false, null, false);
      }
    }
//    for (String rname : page.getDefinitions().getResourceTemplates().keySet()) {
//        ResourceDefn r = page.getDefinitions().getResourceTemplates().get(rname);
//        produceResource2(r, false, null, true);
//    }
    
    for (Compartment c : page.getDefinitions().getCompartments()) {
      if (buildFlags.get("all")) {
        page.log(" ...compartment " + c.getName(), LogMessageType.Process);
        produceCompartment(c);
      }
    }
    Bundle searchParamsFeed = new Bundle();
    searchParamsFeed.setId("searchParams");
    searchParamsFeed.setType(BundleType.COLLECTION);
    searchParamsFeed.setMeta(new Meta().setLastUpdated(page.getResourceBundle().getMeta().getLastUpdated()));
    Set<String> uris = new HashSet<String>();
    for (ResourceDefn rd : page.getDefinitions().getBaseResources().values())
      addSearchParams(uris, searchParamsFeed, rd);
    for (String n : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn rd = page.getDefinitions().getResources().get(n);
      addSearchParams(uris, searchParamsFeed, rd);
    }
    for (Profile cp : page.getDefinitions().getPackList()) {
      addSearchParams(uris, searchParamsFeed, cp);
    }
    checkBundleURLs(searchParamsFeed);

    for (String n : page.getIni().getPropertyNames("pages")) {
      if (buildFlags.get("all") || buildFlags.get("page-" + n.toLowerCase())) {
        page.log(" ...page " + n, LogMessageType.Process);
        producePage(n, page.getIni().getStringProperty("pages", n));
      }
    }
    for (ImplementationGuideDefn ig : page.getDefinitions().getSortedIgs()) {
      for (String n : ig.getPageList()) {
        page.log(" ...ig page " + n, LogMessageType.Process);
        produceIgPage(n, ig);
      }
      for (ImplementationGuideDefinitionPageComponent page : ig.getSpecialPages()) {
        produceIgPage(ig, page);
      }
      for (Profile p : ig.getProfiles()) {
        if (!p.getOperations().isEmpty()) {
          produceIgOperations(ig, p);
        }
      }
    }
    if (page.getIni().getPropertyNames("ig-pages") != null) {
      for (String n : page.getIni().getPropertyNames("ig-pages")) {
        page.log(" ...page " + n, LogMessageType.Process);
        for (ImplementationGuideDefn ig : page.getDefinitions().getSortedIgs()) {
          if (!ig.isCore())
            produceIgPage(n, ig, page.getIni().getStringProperty("ig-pages", n));
        }
      }
    }
    for (String n : page.getDefinitions().getDictionaries().keySet()) {
      if (buildFlags.get("all")) { // || buildFlags.get("dict-" + n.toLowerCase())) {
        page.log(" ...dictionary " + n, LogMessageType.Process);
        produceDictionary(page.getDefinitions().getDictionaries().get(n));
      }
    }

    int i = 0;
    for (String n : page.getIni().getPropertyNames("sid")) {
      page.log(" ...sid " + n, LogMessageType.Process);
      produceSid(i, n, page.getIni().getStringProperty("sid", n));
      i++;
    }
    if (buildFlags.get("all")) {
      page.log(" ...check Fragments", LogMessageType.Process);
      checkFragments();

      for (Profile p : page.getDefinitions().getPackList()) {
//        if (!n.startsWith("http://")) {
          page.log(" ...Profile " + p.getId(), LogMessageType.Process);
          produceConformancePackage(null, p, null);
        //}
      }

      produceUml();
      page.getVsValidator().checkDuplicates(page.getValidationErrors());

      if (buildFlags.get("all")) {
//        if (page.getToc().containsKey("1.1"))
//          throw new Exception("Duplicate DOC Entry "+"1.1");

        page.getToc().put("1.1", new TocEntry("1.1", "Table Of Contents", "toc.html", false));
        page.log(" ...page toc.html", LogMessageType.Process);
        producePage("toc.html", null);
      }

      checkAllOk();

      page.log(" ...collections ", LogMessageType.Process);

      com.google.gson.JsonObject diff = new com.google.gson.JsonObject();
      page.getDiffEngine().getDiffAsJson(diff);
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String json = gson.toJson(diff);
      TextFile.stringToFile(json, Utilities.path(page.getFolders().dstDir, "fhir.diff.json"));

      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = dbf.newDocumentBuilder();
      Document doc = builder.newDocument();
      Element element = doc.createElement("difference");
      doc.appendChild(element);
      page.getDiffEngine().getDiffAsXml(doc, element);
      prettyPrint(doc, Utilities.path(page.getFolders().dstDir, "fhir.diff.xml"));
      
      checkBundleURLs(page.getResourceBundle());
      checkStructureDefinitions(page.getResourceBundle());
      page.getResourceBundle().getEntry().sort(new ProfileBundleSorter());
      FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + "profiles-resources.xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, page.getResourceBundle());
      s.close();
      s = new FileOutputStream(page.getFolders().dstDir + "profiles-resources.json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, page.getResourceBundle());
      s.close();
      checkBundleURLs(page.getTypeBundle());
      checkStructureDefinitions(page.getTypeBundle());
      page.getTypeBundle().getEntry().sort(new ProfileBundleSorter());
      s = new FileOutputStream(page.getFolders().dstDir + "profiles-types.xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, page.getTypeBundle());
      s.close();
      s = new FileOutputStream(page.getFolders().dstDir + "profiles-types.json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, page.getTypeBundle());
      s.close();
      
      Bundle extensionsFeed = new Bundle();
      extensionsFeed.setId("extensions");
      extensionsFeed.setType(BundleType.COLLECTION);
      extensionsFeed.setMeta(new Meta().setLastUpdated(page.getResourceBundle().getMeta().getLastUpdated()));
      Set<String> urls = new HashSet<String>();
      for (StructureDefinition ed : page.getWorkerContext().getExtensionDefinitions()) {
        if (!urls.contains(ed.getUrl())) {
          urls.add(ed.getUrl());
          extensionsFeed.getEntry().add(new BundleEntryComponent().setResource(ed).setFullUrl("http://hl7.org/fhir/"+ed.fhirType()+"/"+ed.getId()));
        }
      }
      checkBundleURLs(extensionsFeed);
      checkStructureDefinitions(extensionsFeed);
      s = new FileOutputStream(page.getFolders().dstDir + "extension-definitions.xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, extensionsFeed);
      s.close();
      s = new FileOutputStream(page.getFolders().dstDir + "extension-definitions.json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, extensionsFeed);
      s.close();
      Utilities.copyFile(page.getFolders().dstDir + "extension-definitions.xml", page.getFolders().dstDir + "examples" + File.separator + "extension-definitions.xml");

      s = new FileOutputStream(page.getFolders().dstDir + "search-parameters.xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, searchParamsFeed);
      s.close();
      s = new FileOutputStream(page.getFolders().dstDir + "search-parameters.json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, searchParamsFeed);
      s.close();
      Utilities.copyFile(page.getFolders().dstDir + "search-parameters.xml", page.getFolders().dstDir + "examples" + File.separator + "search-parameters.xml");

      for (ResourceDefn rd : page.getDefinitions().getResources().values())
        addOtherProfiles(profileBundle, rd);
      for (Profile cp : page.getDefinitions().getPackList()) {
        addOtherProfiles(profileBundle, cp);
      }
      checkBundleURLs(profileBundle);
      checkStructureDefinitions(profileBundle);
      s = new FileOutputStream(page.getFolders().dstDir + "profiles-others.xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, profileBundle);
      s.close();
      s = new FileOutputStream(page.getFolders().dstDir + "profiles-others.json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, profileBundle);
      s.close();
      Utilities.copyFile(page.getFolders().dstDir + "profiles-others.xml", page.getFolders().dstDir + "examples" + File.separator + "profiles-others.xml");
            // todo-bundle - should this be checked?
//      int ec = 0;
//      for (Resource e : valueSetsFeed.getItem()) {
//        ValueSet vs = (ValueSet) e;
//        if (!vs.getUrl().equals(e.getId())) {
//          ec++;
//          page.log("Valueset id mismatch: atom entry has '"+e.getId()+"', but value set is '"+vs.getUrl()+"'", LogMessageType.Error);
//        }
//      }
//      if (ec > 0)
//        throw new Exception("Cannot continue due to value set mis-identification");

      checkBundleURLs(dataElements);
      s = new FileOutputStream(page.getFolders().dstDir + "dataelements.xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, dataElements);
      s.close();
      Utilities.copyFile(page.getFolders().dstDir + "dataelements.xml", page.getFolders().dstDir + "examples" + File.separator + "dataelements.xml");
      s = new FileOutputStream(page.getFolders().dstDir + "dataelements.json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, dataElements);
      s.close();

      checkBundleURLs(valueSetsFeed);
      s = new FileOutputStream(page.getFolders().dstDir + "valuesets.xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, valueSetsFeed);
      s.close();
      Utilities.copyFile(page.getFolders().dstDir + "valuesets.xml", page.getFolders().dstDir + "examples" + File.separator + "valuesets.xml");
      s = new FileOutputStream(page.getFolders().dstDir + "valuesets.json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, valueSetsFeed);
      s.close();

      checkBundleURLs(conceptMapsFeed);
      s = new FileOutputStream(page.getFolders().dstDir + "conceptmaps.xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, conceptMapsFeed);
      s.close();
      Utilities.copyFile(page.getFolders().dstDir + "conceptmaps.xml", page.getFolders().dstDir + "examples" + File.separator + "conceptmaps.xml");
      s = new FileOutputStream(page.getFolders().dstDir + "conceptmaps.json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, conceptMapsFeed);
      s.close();

      checkBundleURLs(externals);
      s = new FileOutputStream(page.getFolders().dstDir + "external-resources.xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, externals);
      s.close();
      Utilities.copyFile(page.getFolders().dstDir + "external-resources.xml", page.getFolders().dstDir + "examples" + File.separator + "external-resources.xml");
      s = new FileOutputStream(page.getFolders().dstDir + "external-resources.json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, externals);
      s.close();

      ImplementationGuide expIg = new ImplementationGuide();
      expIg.addFhirVersion(FHIRVersion._4_4_0);
      expIg.setPackageId("hl7.fhir.r5.expansions");
      expIg.setVersion(FHIRVersion._4_4_0.toCode());
      expIg.setLicense(ImplementationGuide.SPDXLicense.CC01_0);
      expIg.setTitle("FHIR R5 package : Expansions");
      expIg.setDescription("Expansions for the R5 version of the FHIR standard");
      NPMPackageGenerator npm = new NPMPackageGenerator(Utilities.path(page.getFolders().dstDir, "hl7.fhir.r5.expansions.tgz"), "http://hl7.org/fhir", "http://hl7.org/fhir", PackageType.CORE, expIg, page.getGenDate().getTime(), true);
      Bundle expansionFeed = new Bundle();
      Set<String> urlset = new HashSet<>();
      expansionFeed.setId("valueset-expansions");
      expansionFeed.setType(BundleType.COLLECTION);
      expansionFeed.setMeta(new Meta().setLastUpdated(page.getGenDate().getTime()));
      expansionFeed.getFormatCommentsPre().add(
          "This collection contains expansions for all the value sets that are used on an element of type \r\n"
          + "'code', to help with code generation (saves the code generator having to figure out how to \r\n"
          + "do the expansions or find a terminology server that supports the same version of the value sets");
      for (ValueSet vs : page.getValueSets().getList()) {
        if (!urlset.contains(vs.getUrl())) {
          urlset.add(vs.getUrl());
          if (vs.getUserData(ToolResourceUtilities.NAME_VS_USE_MARKER) != null) {
            ValueSet evs = null;
            if (vs.hasUserData("expansion"))
              evs = (ValueSet) vs.getUserData("expansion");
            else {  
              ValueSetExpansionOutcome vse = page.getWorkerContext().expandVS(vs, true, false);
              if (vse.getValueset() != null) {
                evs = vse.getValueset();
                vs.setUserData("expansion", evs);
              }
            }
            if (evs != null) {
              ValueSet vsc = vs.copy();
              vsc.setText(null);
              vsc.setExpansion(evs.getExpansion());
              expansionFeed.addEntry().setFullUrl("http://hl7.org/fhir/"+vsc.fhirType()+"/"+vsc.getId()).setResource(vsc);
              npm.addFile(Category.RESOURCE, "ValueSet-"+vsc.getId()+".json", new JsonParser().composeBytes(vsc));
            }
          }
        }
      }
      npm.finish();
      if (!isCIBuild) {
        new FilesystemPackageCacheManager(true, ToolsVersion.TOOLS_VERSION).addPackageToCache("hl7.fhir.r5.expansions", "current", new FileInputStream(Utilities.path(page.getFolders().dstDir, "hl7.fhir.r5.expansions.tgz")), Utilities.path(page.getFolders().dstDir, "hl7.fhir.r5.expansions.tgz"));
      }
      
      s = new FileOutputStream(page.getFolders().dstDir + "expansions.xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, expansionFeed);
      s.close();
      s = new FileOutputStream(page.getFolders().dstDir + "expansions.json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, expansionFeed);
      s.close();


      produceComparisons();
      produceSpecMap();
      processRDF();

      page.log("....version maps", LogMessageType.Process);
      ZipGenerator zip = new ZipGenerator(page.getFolders().dstDir + "r3r4maps.zip");
      zip.addFiles(Utilities.path(page.getFolders().rootDir, "implementations", "r3maps", "R3toR4", ""), "r3/", null, null);
      zip.addFiles(Utilities.path(page.getFolders().rootDir, "implementations", "r3maps", "R4toR3", ""), "r4/", null, null);
      zip.close();

      page.log("....definitions", LogMessageType.Process);
      zip = new ZipGenerator(page.getFolders().dstDir + "definitions.xml.zip");
      zip.addFileName("version.info", page.getFolders().dstDir + "version.info", false);
      zip.addFileName("profiles-types.xml", page.getFolders().dstDir + "profiles-types.xml", false);
      zip.addFileName("profiles-resources.xml", page.getFolders().dstDir + "profiles-resources.xml", false);
      zip.addFileName("profiles-others.xml", page.getFolders().dstDir + "profiles-others.xml", false);
      zip.addFileName("extension-definitions.xml", page.getFolders().dstDir + "extension-definitions.xml", false);
      zip.addFileName("search-parameters.xml", page.getFolders().dstDir + "search-parameters.xml", false);
      zip.addFileName("valuesets.xml", page.getFolders().dstDir + "valuesets.xml", false);
      zip.addFileName("conceptmaps.xml", page.getFolders().dstDir + "conceptmaps.xml", false);
      zip.addFileName("dataelements.xml", page.getFolders().dstDir + "dataelements.xml", false);
      zip.addFileName("fhir-all-xsd.zip", page.getFolders().dstDir + "fhir-all-xsd.zip", false);
      zip.close();

      zip = new ZipGenerator(page.getFolders().dstDir + "definitions.json.zip");
      zip.addFileName("version.info", page.getFolders().dstDir + "version.info", false);
      zip.addFileName("profiles-types.json", page.getFolders().dstDir + "profiles-types.json", false);
      zip.addFileName("profiles-resources.json", page.getFolders().dstDir + "profiles-resources.json", false);
      zip.addFileName("profiles-others.json", page.getFolders().dstDir + "profiles-others.json", false);
      zip.addFileName("extension-definitions.json", page.getFolders().dstDir + "extension-definitions.json", false);
      zip.addFileName("search-parameters.json", page.getFolders().dstDir + "search-parameters.json", false);
      zip.addFileName("valuesets.json", page.getFolders().dstDir + "valuesets.json", false);
      zip.addFileName("conceptmaps.json", page.getFolders().dstDir + "conceptmaps.json", false);
      zip.addFileName("dataelements.json", page.getFolders().dstDir + "dataelements.json", false);
      zip.addFileName("fhir.schema.json.zip", page.getFolders().dstDir + "fhir.schema.json.zip", false);
      zip.close();

      zip = new ZipGenerator(page.getFolders().dstDir + "definitions.xlsx.zip");
      for (String rn : page.getDefinitions().sortedResourceNames()) {
        zip.addFileName(rn.toLowerCase()+".xlsx", page.getFolders().dstDir + rn.toLowerCase()+".xlsx", false);
      }
      zip.close();

      // this is the actual package used by the validator. 
      zip = new ZipGenerator(page.getFolders().dstDir + "validator.pack");
      // conformance resources
      zip.addFileName("profiles-types.json", page.getFolders().dstDir + "profiles-types.json", false);
      zip.addFileName("profiles-resources.json", page.getFolders().dstDir + "profiles-resources.json", false);
      zip.addFileName("profiles-others.json", page.getFolders().dstDir + "profiles-others.json", false);
      zip.addFileName("extension-definitions.json", page.getFolders().dstDir + "extension-definitions.json", false);
      zip.addFileName("valuesets.json", page.getFolders().dstDir + "valuesets.json", false);
      zip.addFileName("conceptmaps.json", page.getFolders().dstDir + "conceptmaps.json", false);
      // native schema
      zip.addFileName("fhir-all-xsd.zip", page.getFolders().dstDir + "fhir-all-xsd.zip", false);
      zip.addFileName("fhir.schema.json.zip", page.getFolders().dstDir + "fhir.schema.json.zip", false);
      zip.addFileName("fhir.shex", page.getFolders().dstDir + "fhir.shex", false);
      zip.close();

      page.log("....dstu3 format (xml)", LogMessageType.Process);
      DSTU3ValidationConvertor dstu3 = new DSTU3ValidationConvertor();
      dstu3.convert(page.getFolders().dstDir + "profiles-types.xml", page.getFolders().tmpDir + "profiles-types-r3.xml");
      dstu3.convert(page.getFolders().dstDir + "profiles-resources.xml", page.getFolders().tmpDir + "profiles-resources-r3.xml");
      dstu3.convert(page.getFolders().dstDir + "profiles-others.xml", page.getFolders().tmpDir + "profiles-others-r3.xml");
      dstu3.convert(page.getFolders().dstDir + "extension-definitions.xml", page.getFolders().tmpDir + "extension-definitions-r3.xml");
      dstu3.convert(page.getFolders().dstDir + "search-parameters.xml", page.getFolders().tmpDir + "search-parameters-r3.xml");
      dstu3.convert(page.getFolders().dstDir + "valuesets.xml", page.getFolders().tmpDir + "valuesets-r3.xml");
      dstu3.convert(page.getFolders().dstDir + "conceptmaps.xml", page.getFolders().tmpDir + "conceptmaps-r3.xml");
      dstu3.convert(page.getFolders().dstDir + "dataelements.xml", page.getFolders().tmpDir + "dataelements-r3.xml");
      
      zip = new ZipGenerator(page.getFolders().dstDir + "definitions-r3.xml.zip");
      zip.addFileName("profiles-types.xml", page.getFolders().tmpDir + "profiles-types-r3.xml", false);
      zip.addFileName("profiles-resources.xml", page.getFolders().tmpDir + "profiles-resources-r3.xml", false);
      zip.addFileName("profiles-others.xml", page.getFolders().tmpDir + "profiles-others-r3.xml", false);
      zip.addFileName("extension-definitions.xml", page.getFolders().tmpDir + "extension-definitions-r3.xml", false);
      zip.addFileName("search-parameters.xml", page.getFolders().tmpDir + "search-parameters-r3.xml", false);
      zip.addFileName("valuesets.xml", page.getFolders().tmpDir + "valuesets-r3.xml", false);
      zip.addFileName("conceptmaps.xml", page.getFolders().tmpDir + "conceptmaps-r3.xml", false);
      zip.addFileName("dataelements.xml", page.getFolders().tmpDir + "dataelements-r3.xml", false);
      zip.close();

      page.log("....dstu3 format (json)", LogMessageType.Process);
      dstu3.convertJ(page.getFolders().dstDir + "profiles-types.xml", page.getFolders().tmpDir + "profiles-types-r3.json");
      dstu3.convertJ(page.getFolders().dstDir + "profiles-resources.xml", page.getFolders().tmpDir + "profiles-resources-r3.json");
      dstu3.convertJ(page.getFolders().dstDir + "profiles-others.xml", page.getFolders().tmpDir + "profiles-others-r3.json");
      dstu3.convertJ(page.getFolders().dstDir + "extension-definitions.xml", page.getFolders().tmpDir + "extension-definitions-r3.json");
      dstu3.convertJ(page.getFolders().dstDir + "search-parameters.xml", page.getFolders().tmpDir + "search-parameters-r3.json");
      dstu3.convertJ(page.getFolders().dstDir + "valuesets.xml", page.getFolders().tmpDir + "valuesets-r3.json");
      dstu3.convertJ(page.getFolders().dstDir + "conceptmaps.xml", page.getFolders().tmpDir + "conceptmaps-r3.json");
      dstu3.convertJ(page.getFolders().dstDir + "dataelements.xml", page.getFolders().tmpDir + "dataelements-r3.json");
      
      zip = new ZipGenerator(page.getFolders().dstDir + "definitions-r3.json.zip");
      zip.addFileName("profiles-types.json", page.getFolders().tmpDir + "profiles-types-r3.json", false);
      zip.addFileName("profiles-resources.json", page.getFolders().tmpDir + "profiles-resources-r3.json", false);
      zip.addFileName("profiles-others.json", page.getFolders().tmpDir + "profiles-others-r3.json", false);
      zip.addFileName("extension-definitions.json", page.getFolders().tmpDir + "extension-definitions-r3.json", false);
      zip.addFileName("search-parameters.json", page.getFolders().tmpDir + "search-parameters-r3.json", false);
      zip.addFileName("valuesets.json", page.getFolders().tmpDir + "valuesets-r3.json", false);
      zip.addFileName("conceptmaps.json", page.getFolders().tmpDir + "conceptmaps-r3.json", false);
      zip.addFileName("dataelements.json", page.getFolders().tmpDir + "dataelements-r3.json", false);
      zip.close();
      System.gc();

      page.log("....r4 in r5 format", LogMessageType.Process);
      zip = new ZipGenerator(page.getFolders().dstDir + "definitions-r2asr3.xml.zip");
      page.getDiffEngine().saveR4AsR5(zip, FhirFormat.XML);
      zip.close();
      zip = new ZipGenerator(page.getFolders().dstDir + "definitions-r2asr3.json.zip");
      page.getDiffEngine().saveR4AsR5(zip, FhirFormat.JSON);
      zip.close();
            
      zip = new ZipGenerator(page.getFolders().dstDir + "all-valuesets.zip");
      zip.addFileName("valuesets.xml", page.getFolders().dstDir + "valuesets.xml", false);
      zip.addFileName("valuesets.json", page.getFolders().dstDir + "valuesets.json", false);
      zip.addFileName("conceptmaps.xml", page.getFolders().dstDir + "conceptmaps.xml", false);
      zip.addFileName("conceptmaps.json", page.getFolders().dstDir + "conceptmaps.json", false);
      zip.close();
    
      page.log("....IG Builder Resources", LogMessageType.Process);
      zip = new ZipGenerator(page.getFolders().tmpDir + "ig-template.zip");
      zip.addFolder(Utilities.path(page.getFolders().rootDir, "tools", "ig"), "", false, null);
      zip.close();

      zip = new ZipGenerator(page.getFolders().dstDir + "igpack.zip");
      zip.addFileName("fhir.css", page.getFolders().dstDir + "fhir.css", false);
      zip.addFileName("spec.internals", page.getFolders().dstDir + "spec.internals", false);
      zip.addFileName("profiles-types.xml", page.getFolders().dstDir + "profiles-types.xml", false);
      zip.addFileName("profiles-resources.xml", page.getFolders().dstDir + "profiles-resources.xml", false);
      zip.addFileName("profiles-others.xml", page.getFolders().dstDir + "profiles-others.xml", false);
      zip.addFileName("extension-definitions.xml", page.getFolders().dstDir + "extension-definitions.xml", false);
      zip.addFileName("search-parameters.xml", page.getFolders().dstDir + "search-parameters.xml", false);
      zip.addFileName("valuesets.xml", page.getFolders().dstDir + "valuesets.xml", false);
      zip.addFileName("conceptmaps.xml", page.getFolders().dstDir + "conceptmaps.xml", false);
      zip.addFileName("dataelements.xml", page.getFolders().dstDir + "dataelements.xml", false);
      zip.addFileName("version.info", page.getFolders().dstDir + "version.info", false);
      zip.addFileName("mappingSpaces.details", page.getFolders().srcDir + "mappingSpaces.xml", false);
      zip.addFileName("redirect.asp.template", page.getFolders().srcDir + "redirect.asp", false);
      zip.addFileName("redirect.cgi.template", page.getFolders().srcDir + "redirect.cgi", false);
      zip.addFileName("redirect.php.template", page.getFolders().srcDir + "redirect.php", false);
      zip.addFileName("ig-template.zip", Utilities.path(page.getFolders().tmpDir, "ig-template.zip"), false);
      zip.addFiles(Utilities.path(page.getFolders().rootDir, "publish", ""), "", ".png", null);
      zip.addFiles(Utilities.path(page.getFolders().rootDir, "publish", ""), "", ".gif", null);
      zip.close();
      page.log("....IG Builder (2)", LogMessageType.Process);

      SpecNPMPackageGenerator self = new SpecNPMPackageGenerator();
      self.generate(page.getFolders().dstDir, page.getBaseURL(), false, page.getGenDate().getTime());
      if (!isCIBuild) {
        new FilesystemPackageCacheManager(true, ToolsVersion.TOOLS_VERSION).addPackageToCache("hl7.fhir.r5.core", "current", new FileInputStream(Utilities.path(page.getFolders().dstDir, "hl7.fhir.r5.core.tgz")), Utilities.path(page.getFolders().dstDir, "hl7.fhir.r5.core.tgz"));
      }

      page.log(" ...zips", LogMessageType.Process);
      zip = new ZipGenerator(page.getFolders().dstDir + "examples.zip");
      zip.addFiles(page.getFolders().dstDir + "examples" + File.separator, "", null, "expansions.xml");
      zip.close();

      zip = new ZipGenerator(page.getFolders().dstDir + "examples-json.zip");
      gson = new GsonBuilder().setPrettyPrinting().create();
      File f = new CSFile(page.getFolders().dstDir);
      File[] files = f.listFiles();
      String[] noExt = new String[] {".schema.json", ".canonical.json", ".diff.json", "expansions.json", "package.json", "choice-elements.json", "backbone-elements.json"};
      for (int fi = 0; fi < files.length; fi++) {
        if (files[fi].isFile() && (files[fi].getName().endsWith(".json"))) {
          boolean ok = true;
          for (String n : noExt) {
            ok = ok && !files[fi].getName().endsWith(n);
          }
          if (ok) {
            JsonObject jr = JSONUtil.parse(TextFile.fileToString(files[fi]));
            if (!jr.has("url")) {
              JsonObject meta = JSONUtil.forceObject(jr, "meta");
              JsonArray labels = JSONUtil.forceArray(meta, "tag");
              JsonObject label = JSONUtil.addObj(labels);
              label.addProperty("system", "http://terminology.hl7.org/CodeSystem/v3-ActReason");
              label.addProperty("code", "HTEST");
              label.addProperty("display", "test health data");
                
            }
            String jrs = gson.toJson(jr);
            zip.addBytes(files[fi].getName(), jrs.getBytes(Charsets.UTF_8), true);
          }
        }
      }
      zip.close();
      
      NDJsonWriter ndjson = new NDJsonWriter(page.getFolders().dstDir + "examples-ndjson.zip", page.getFolders().tmpDir);
      ndjson.addFilesFiltered(page.getFolders().dstDir, ".json", new String[] {".schema.json", ".canonical.json", ".diff.json", "expansions.json", "package.json"});
      ndjson.close();
      

      zip = new ZipGenerator(page.getFolders().dstDir + "examples-ttl.zip");
      zip.addFilesFiltered(page.getFolders().dstDir, "", ".ttl", new String[0]);
      zip.close();

      page.log("Check HTML Links", LogMessageType.Process);
      page.getHTMLChecker().produce();
      checkAllOk();
    } else
      page.log("Partial Build - terminating now", LogMessageType.Error);
  }

  private void produceUml() throws IOException {
    TextFile.stringToFile(UMLWriter.toJson(page.getUml()), page.getFolders().dstDir+"uml.json");
    TextFile.stringToFile(UMLWriter.toText(page.getUml()), page.getFolders().dstDir+"uml.text");   
  }

  private void produceConceptMap(ConceptMap cm, ResourceDefn rd, SectionTracker st) throws Exception {
    RenderingContext lrc = page.getRc().copy().setLocalPrefix("");
    RendererFactory.factory(cm, lrc).render(cm);
    String n = cm.getUserString("path");
    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(n, ".xml"));
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, cm);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(n, ".canonical.xml"));
    new XmlParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, cm);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(n, ".json"));
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, cm);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(n, ".canonical.json"));
    new JsonParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, cm);
    s.close();

    Utilities.copyFile(new CSFile(page.getFolders().dstDir + Utilities.changeFileExt(n, ".xml")), new CSFile(page.getFolders().dstDir + "examples" + File.separator + Utilities.changeFileExt(n, ".xml")));
//    saveAsPureHtml(cm, new FileOutputStream(Utilities.path(page.getFolders().dstDir, "html", n)), true);
    String src = TextFile.fileToString(page.getFolders().templateDir + "template-status-map.html");
    Map<String, String> others = new HashMap<String, String>();
    others.put("status-map", new XhtmlComposer(XhtmlComposer.HTML).compose(cm.getText().getDiv()));
    TextFile.stringToFile(insertSectionNumbers(page.processPageIncludes(n, src, "conceptmap-instance", others, null, null, "Profile", null, rd, rd.getWg()), st, n, 0, null), page.getFolders().dstDir + n);
    page.getHTMLChecker().registerFile(n, cm.getTitle(), HTMLLinkChecker.XHTML_TYPE, true);
    cloneToXhtml(Utilities.changeFileExt(n, ""), cm.getTitle(), true, "conceptmap-instance", "Profile", null, ((ResourceDefn) cm.getUserData("resource-definition")).getWg());
    jsonToXhtml(Utilities.changeFileExt(n, ""), cm.getTitle(), resource2Json(cm), "conceptmap-instance", "Profile", null, ((ResourceDefn) cm.getUserData("resource-definition")).getWg());
    ttlToXhtml(Utilities.changeFileExt(n, ""), cm.getTitle(), resource2Ttl(cm),  "conceptmap-instance", "Profile", null, ((ResourceDefn) cm.getUserData("resource-definition")).getWg());
  }

  public class ProfileBundleSorter implements Comparator<BundleEntryComponent> {

    @Override
    public int compare(BundleEntryComponent o1, BundleEntryComponent o2) {
      String s1 = typeScore(o1.getResource());
      String s2 = typeScore(o2.getResource());
      return s1.compareTo(s2);
    }

    private String typeScore(Resource r) {
      if (!(r instanceof StructureDefinition))
        return r.fhirType()+"."+r.getId();
      StructureDefinition sd = (StructureDefinition) r;
      String p = sd.getDerivation() == TypeDerivationRule.CONSTRAINT ? "1" : "0";
      if (sd.getId().equals("Element"))
        return "aaStructureDefinition.00."+p+".Element";
      if (sd.getId().equals("BackboneElement"))
        return "aaStructureDefinition.01."+p+".BackboneElement";
      if (sd.getId().equals("Resource"))
        return "aaStructureDefinition.03."+p+".Resource";
      if (sd.getId().equals("BackboneElement"))
        return "aaStructureDefinition.04."+p+".DomainResource";
      if (sd.getKind() == StructureDefinitionKind.PRIMITIVETYPE) 
        return "aaStructureDefinition.05."+p+"."+r.getId();
      if (sd.getKind() == StructureDefinitionKind.COMPLEXTYPE) 
        return "aaStructureDefinition.06."+p+"."+r.getId();
      if (sd.getKind() == StructureDefinitionKind.RESOURCE) 
        return "aaStructureDefinition.07."+p+"."+r.getId();
//    (r1.getKind() == StructureDefinitionKind.LOGICAL) 
      return "aaStructureDefinition.08."+p+"."+r.getId();
    }
  }

  private void produceMap(String name, SectionTracker st, ResourceDefn res) throws Exception {
    File f = new File(Utilities.path(page.getFolders().rootDir, "implementations", "r3maps", "R4toR3", name+".map"));
    if (!f.exists())
        return;
    String n = name.toLowerCase();
    Map<String, String> values = new HashMap<String, String>();
    values.put("conv-status", page.r3r4StatusForResource(name));
    String fwds = TextFile.fileToString(Utilities.path(page.getFolders().rootDir, "implementations", "r3maps", "R3toR4", page.r3nameForResource(name)+".map"));
    String bcks = TextFile.fileToString(Utilities.path(page.getFolders().rootDir, "implementations", "r3maps", "R4toR3", name+".map"));
    values.put("fwds", Utilities.escapeXml(fwds));
    values.put("bcks", Utilities.escapeXml(bcks));
    values.put("fwds-status", "");
    values.put("bcks-status", "");
    values.put("r3errs", Utilities.escapeXml(page.getR3R4ValidationErrors(name)));
    try {
      new StructureMapUtilities(page.getWorkerContext()).parse(fwds, page.r3nameForResource(name)+".map");
    } catch (FHIRException e) {
      values.put("fwds-status", "<p style=\"background-color: #ffb3b3; border:1px solid maroon; padding: 5px;\">This script does not compile: "+e.getMessage()+"</p>\r\n");
    }
    try {
      new StructureMapUtilities(page.getWorkerContext()).parse(bcks, name+".map");
    } catch (FHIRException e) {
      values.put("bcks-status", "<p style=\"background-color: #ffb3b3; border:1px solid maroon; padding: 5px;\">This script does not compile: "+e.getMessage()+"</p>\r\n");
    } catch (IllegalArgumentException e) {
      values.put("bcks-status", "<p style=\"background-color: #ffb3b3; border:1px solid maroon; padding: 5px;\">This script does not compile: "+e.getMessage()+"</p>\r\n");
    }
    if (page.getDefinitions().hasResource(name) || (page.getDefinitions().getBaseResources().containsKey(name) && !name.equals("Parameters"))) {
      String src = TextFile.fileToString(page.getFolders().templateDir + "template-version-maps.html");
      TextFile.stringToFile(
          insertSectionNumbers(page.processResourceIncludes(n, page.getDefinitions().getResourceByName(name), null, null, null, null, null, src, null, null, "res-R3/R4 Conversions", n + "-version-maps.html", null, values, res.getWg(), null), st, n
              + "-version-maps.html", 0, null), page.getFolders().dstDir + n + "-version-maps.html");
      page.getHTMLChecker().registerFile(n + "-version-maps.html", "Version Maps for " + name, HTMLLinkChecker.XHTML_TYPE, true);
    }    
  }


  private void produceSpecMap() throws IOException {
    SpecMapManager spm = new SpecMapManager("hl7.fhir.core", page.getVersion().toCode(), page.getVersion().toCode(), page.getBuildId(), page.getGenDate(), CANONICAL_BASE);
        
    for (StructureDefinition sd : page.getWorkerContext().allStructures()) {
      if (sd.hasUserData("path")) {
        spm.path(sd.getUrl(), sd.getUserString("path").replace("\\", "/"));
        spm.target(sd.getUserString("path").replace("\\", "/"));
      }
    }
    for (StructureDefinition sd : page.getWorkerContext().getExtensionDefinitions()) {
      if (sd.hasUserData("path")) {
        spm.path(sd.getUrl(), sd.getUserString("path").replace("\\", "/"));
        spm.target(sd.getUserString("path").replace("\\", "/"));
      }
    }
    for (String s : page.getCodeSystems().keys()) {
      CodeSystem cs = page.getCodeSystems().get(s);
      if (cs == null && !Utilities.existsInList(s, "http://unitsofmeasure.org", "http://hl7.org/fhir/sid/cvx", "http://loinc.org", "http://fdasis.nlm.nih.gov", "http://www.nlm.nih.gov/research/umls/rxnorm", "urn:oid:1.2.36.1.2001.1005.17"))
        System.out.println("No code system resource found for "+s);
    }
    for (CodeSystem cs : page.getCodeSystems().getList()) {
      if (cs != null && cs.hasUserData("path")) {
        spm.path(cs.getUrl(), cs.getUserString("path").replace("\\", "/"));
        spm.target(cs.getUserString("path").replace("\\", "/"));      
      }
    }
    for (ValueSet vs : page.getValueSets().getList()) {
      if (vs.hasUserData("path")) {
        spm.path(vs.getUrl(), vs.getUserString("path").replace("\\", "/"));
        spm.target(vs.getUserString("path").replace("\\", "/"));      
      }
    }
    for (ConceptMap cm : page.getConceptMaps().getList()) {
      if (cm.hasUserData("path")) {
        spm.path(cm.getUrl(), cm.getUserString("path").replace("\\", "/"));
        spm.target(cm.getUserString("path").replace("\\", "/"));      
      }
    }
    for (String s : page.getDefinitions().getPageTitles().keySet()) {
      spm.page(s, page.getDefinitions().getPageTitles().get(s));      
    }
    for (String n : page.getIni().getPropertyNames("pages")) {
      spm.target(n);      
    }
    for (ResourceDefn rd : page.getDefinitions().getResources().values()) {
      spm.target(rd.getName().toLowerCase()+".html");
      spm.target(rd.getName().toLowerCase()+"-definitions.html");
      spm.target(rd.getName().toLowerCase()+"-mappings.html");
      spm.target(rd.getName().toLowerCase()+"-examples.html");
      spm.target(rd.getName().toLowerCase()+"-profiles.html");
      if (!rd.getOperations().isEmpty())
        spm.target(rd.getName().toLowerCase()+"-operations.html");
      for (Example ex : rd.getExamples()) {
        ImplementationGuideDefn ig = ex.getIg() == null ? null : page.getDefinitions().getIgs().get(ex.getIg());
        String prefix = (ig == null || ig.isCore()) ? "" : ig.getCode() + "/";
        spm.target(prefix+ex.getTitle()+".html");
      }
    }

    for (Profile p : page.getDefinitions().getPackList()) {
      spm.target(p.getId()+".html");      
    }
//    for (String url : page.getDefinitions().getMapTypes().keySet()) {
//      spm.map(url, page.getDefinitions().getMapTypes().get(url).getPreamble());
//    }
    scanForImages(spm, page.getFolders().dstDir, page.getFolders().dstDir);
    scanForPages(spm, page.getFolders().dstDir, page.getFolders().dstDir);
    
    for (String url : page.getDefinitions().getRedirectList().keySet()) {
      spm.target(url.substring(20)); // http://hl7.org/fhir/ = 20 chars
    }

    spm.save(page.getFolders().dstDir + "spec.internals");
  }

  private void scanForPages(SpecMapManager spm, String base, String folder) {
    for (File f : new File(folder).listFiles()) {
      if (f.isDirectory()) {
        scanForPages(spm, base, f.getAbsolutePath());
      } else if (f.getName().equals("redirect.asp")) {
        String s = folder.substring(0, folder.length()-1);
        if (s.length() > base.length()) {
          s = s.substring(base.length()).replace(File.separator, "/");
          if (!Utilities.noString(s)) {
            spm.target(s);
            spm.target(s+"/");
          }
        }
      } else {
        String ext = f.getName().contains(".") ? f.getName().substring(f.getName().lastIndexOf(".")) : "";
        if (Utilities.existsInList(ext, ".html", ".zip", ".jar"))
          spm.target(f.getAbsolutePath().substring(base.length()).replace(File.separator, "/"));
      }
    }
    
  }

  private void scanForImages(SpecMapManager spm, String base, String folder) {
    for (File f : new File(folder).listFiles()) {
      if (f.isDirectory()) {
        scanForImages(spm, base, f.getAbsolutePath());
      } else {
        String ext = f.getName().contains(".") ? f.getName().substring(f.getName().lastIndexOf(".")) : "";
        if (Utilities.existsInList(ext, ".png", ".jpg"))
          spm.image(f.getAbsolutePath().substring(base.length()).replace(File.separator, "/"));
      }
    }    
  }

  private void checkStructureDefinitions(Bundle bnd) {
    for (BundleEntryComponent e : bnd.getEntry()) {
      if (e.getResource() instanceof StructureDefinition) {
        StructureDefinition sd = (StructureDefinition) e.getResource();
        checkMetaData(sd);
        for (ElementDefinition ed : sd.getDifferential().getElement())
          checkElement(sd, ed, true);
        for (ElementDefinition ed : sd.getSnapshot().getElement())
          checkElement(sd, ed, false);
      }
    }
    
  }

  private void checkElement(StructureDefinition sd, ElementDefinition ed, boolean inDiff) {
    check(ed.hasPath(), sd, "Element has no path");
    Set<String> codes = new HashSet<String>();
    for (TypeRefComponent tr : ed.getType()) {
      String tc = tr.getWorkingCode();
      if (codes.contains(tc))
        check(false, sd, ed.getPath()+": type '"+tc+"' is duplicated");
        
      if ((!inDiff || tr.hasCode()) && tc != null)
        if (ed.getPath().contains("."))
          check(page.getDefinitions().hasBaseType(tc) || tc.equals("Resource"), sd, ed.getPath()+": type '"+tc+"' is not valid (a)");
        else if (sd.hasBaseDefinition()) {
          if (sd.getDerivation() == TypeDerivationRule.CONSTRAINT)
            check(page.getDefinitions().hasConcreteResource(tc) || page.getDefinitions().hasBaseType(tc) , sd, ed.getPath()+": type '"+tc+"' is not valid (b)");
          else
            check(page.getDefinitions().hasAbstractResource(tc) || tc.equals("Element"), sd, ed.getPath()+": type '"+tc+"' is not valid (c)");
        }
      if (tr.hasProfile()) {
        check(tr.getProfile().size() == 1, sd, ed.getPath()+": multiple profiles found: "+tr.getProfile());

        String pt = tr.getProfile().get(0).getValue();
        if (pt.contains("#")) {
          String[] parts = pt.split("\\#");
          StructureDefinition exd = page.getWorkerContext().fetchResource(StructureDefinition.class, parts[0]);
          if (exd == null)
            check(false, sd, ed.getPath()+": profile '"+pt+"' is not valid (definition not found)");
          else {
            ElementDefinition ex = null;
            for (ElementDefinition et : exd.getSnapshot().getElement())
              if (et.hasFixed() && et.getFixed() instanceof UriType && ((UriType)et.getFixed()).asStringValue().equals(parts[1]))
                  ex = et;
              check(ex != null, sd, ed.getPath()+": profile '"+pt+"' is not valid (inner path not found)");
          }
        } else
          check((page.getWorkerContext().hasResource(StructureDefinition.class, pt))
          || isStringPattern(tail(pt)), sd, ed.getPath()+": profile '"+pt+"' is not valid (d)");
      }
      if (tr.hasTargetProfile()) {
        String pt = tr.getTargetProfile().get(0).getValue();
        if (pt.contains("#")) {
          String[] parts = pt.split("\\#");
          StructureDefinition exd = page.getWorkerContext().fetchResource(StructureDefinition.class, parts[0]);
          if (exd == null)
            check(false, sd, ed.getPath()+": target profile '"+pt+"' is not valid (definition not found)");
          else {
            ElementDefinition ex = null;
            for (ElementDefinition et : exd.getSnapshot().getElement())
              if (et.hasFixed() && et.getFixed() instanceof UriType && ((UriType)et.getFixed()).asStringValue().equals(parts[1]))
                  ex = et;
              check(ex != null, sd, ed.getPath()+": target profile '"+pt+"' is not valid (inner path not found)");
          }
        } else
          check((page.getWorkerContext().hasResource(StructureDefinition.class, pt))
          || isStringPattern(tail(pt)), sd, ed.getPath()+": target profile '"+pt+"' is not valid (d)");
      }
    }
  }

  private boolean isStringPattern(String name) {
    return !page.getDefinitions().getPrimitives().containsKey(name) || !(page.getDefinitions().getPrimitives().get(name) instanceof DefinedStringPattern);
  }

  private boolean checkMetaData(StructureDefinition sd) {
    check(tail(sd.getUrl()).equals(sd.getId()), sd, "id must equal tail of URL");
    check(page.getVersion().equals(sd.getFhirVersion()), sd, "FhirVersion is wrong (should be "+page.getVersion()+", is "+sd.getFhirVersion()+")");
    switch (sd.getKind()) {
    case COMPLEXTYPE: return checkDataType(sd);
    case PRIMITIVETYPE: return checkDataType(sd);
    case RESOURCE: return checkResource(sd);
    case LOGICAL: return checkLogical(sd);
    default:
      check(false, sd, "Unknown kind");
      return false;    
    }
  }

  private boolean checkLogical(StructureDefinition sd) {
    return false;
  }

  private boolean checkResource(StructureDefinition sd) {
    check(!sd.getAbstract() || sd.getName().equals("Resource") || sd.getName().equals("DomainResource"), sd, "Only Resource/DomainResource can be abstract");
    check(!sd.hasContext(), sd, "Only extensions can have context (not resources)");
    if (sd.getDerivation() == TypeDerivationRule.CONSTRAINT) {
      check(page.getDefinitions().hasConcreteResource(sd.getType()), sd, "Unknown constrained base resource "+sd.getType());
      check(!page.getDefinitions().hasResource(sd.getId()), sd, "Duplicate resource name "+sd.getType());
    } else {
      if (sd.hasBaseDefinition()) 
         check(page.getDefinitions().hasAbstractResource(sd.getBaseDefinition().substring(40)), sd, "Unknown specialised base resource "+sd.getType());
      else
        check(page.getDefinitions().hasAbstractResource(sd.getType()), sd, "Unknown specialised base resource "+sd.getType());
    }
    return false;
  }

  private boolean checkDataType(StructureDefinition sd) {
    check(!sd.getAbstract() || sd.getName().equals("Element") || sd.getName().equals("BackboneElement") , sd, "Only Element/BackboneElement can be abstract");
    check(!sd.hasContext() || "Extension".equals(sd.getType()), sd, "Only extensions can have context (base type = "+sd.getType()+")");
    if (sd.getDerivation() == TypeDerivationRule.CONSTRAINT) {
      check(page.getDefinitions().hasType(sd.getType()), sd, "Unknown constrained base type "+sd.getType());
      check(page.getDefinitions().hasPrimitiveType(sd.getId()) || !page.getDefinitions().hasBaseType(sd.getId()), sd, "Duplicate type name "+sd.getType());
    } else {
      if (sd.hasBaseDefinition())
        check(page.getDefinitions().hasBaseType(sd.getBaseDefinition().substring(40)), sd, "Unknown specialised base type "+sd.getType());
      else
        check(page.getDefinitions().hasBaseType(sd.getType()), sd, "Unknown specialised base type "+sd.getType());
    }
    return false;
  }

  private void check(boolean pass, StructureDefinition sd, String msg) {
    if (!pass)
      System.out.println("Error in StructureDefinition "+sd.getId()+": "+msg);    
  }

  private String tail(String url) {
    return url.substring(url.lastIndexOf("/")+1);
  }

  private void produceIgOperations(ImplementationGuideDefn ig, Profile p) throws Exception {
    throw new Error("not supported anymore");
//    String src = TextFile.fileToString(page.getFolders().templateDir + "template-ig-operations.html");
//    String n = p.getId();
//    WorkGroup wg = null;
//    TextFile.stringToFile(page.processPageIncludes(ig.getCode()+File.separator+n+"-operations.html", src, "?type", null, "??path", null, null, "Operations", p, ig, null, wg), page.getFolders().dstDir + ig.getCode()+File.separator+n + "-operations.html");
//    // insertSectionNumbers(, st, n+"-operations.html", 0, null)
//    page.getHTMLChecker().registerFile(ig.getCode()+File.separator+n + "-operations.html", "Operations defined by " + p.getTitle(), HTMLLinkChecker.XHTML_TYPE, true);
//
//    for (Operation t : p.getOperations()) {
//      produceOperation(ig, n+"-"+t.getName(), n+"-"+t.getName().toLowerCase(), null, t, null);
//    }
  }

  /**
   * This is not true of bundles generally, but it is true of all the
   * conformance bundles produced by the spec:
   *
   * all entries must have a fullUrl, and it must equal http://hl7.org/fhir/[type]/[id]
   *
   * @param bnd - the bundle to check
   */
  private void checkBundleURLs(Bundle bnd) {
    int i = 0;
    for (BundleEntryComponent e : bnd.getEntry()) {
      i++;
      if (!e.getResource().hasUserData("external.url")) {
        if (!e.hasFullUrl())
          page.getValidationErrors().add(new ValidationMessage(Source.Publisher, IssueType.INVALID, -1, -1, "Bundle "+bnd.getId(), "no Full URL on entry "+Integer.toString(i),IssueSeverity.ERROR));
        else if (!e.getFullUrl().endsWith("/"+e.getResource().getResourceType().toString()+"/"+e.getResource().getId()) && e.getResource().getResourceType() != ResourceType.CodeSystem)
          page.getValidationErrors().add(new ValidationMessage(Source.Publisher, IssueType.INVALID, -1, -1, "Bundle "+bnd.getId(), "URL doesn't match resource and id on entry "+Integer.toString(i)+" : "+e.getFullUrl()+" should end with /"+e.getResource().getResourceType().toString()+"/"+e.getResource().getId(),IssueSeverity.ERROR));
        else if (!e.getFullUrl().equals("http://hl7.org/fhir/"+e.getResource().getResourceType().toString()+"/"+e.getResource().getId()) && e.getResource().getResourceType() != ResourceType.CodeSystem)
          page.getValidationErrors().add(new ValidationMessage(Source.Publisher, IssueType.INVALID, -1, -1, "Bundle "+bnd.getId(), "URL is non-FHIR "+Integer.toString(i)+" : "+e.getFullUrl()+" should start with http://hl7.org/fhir/ for HL7-defined artifacts",IssueSeverity.WARNING));
        if (e.getResource() instanceof CanonicalResource) {
          CanonicalResource m = (CanonicalResource) e.getResource();
          String url = m.getUrl();
          if (url != null && url.startsWith("http://hl7.org/fhir") && !url.startsWith("http://hl7.org/fhir/sid")) {
            if (!Constants.VERSION.equals(m.getVersion())) 
              page.getValidationErrors().add(new ValidationMessage(Source.Publisher, IssueType.INVALID, -1, -1, "Bundle "+bnd.getId(), "definitions in FHIR space should have the correct version (url = "+url+", version = "+m.getVersion()+")", IssueSeverity.ERROR));              
          }
        }
      }
    }
  }

  private void produceComparisons() throws Exception {
//    for (String n : page.getIni().getPropertyNames("comparisons")) {
//      produceComparison(n);
//    }
  }

  private void minify(String srcFile, String dstFile) throws Exception {
    CloseProtectedZipInputStream source = new CloseProtectedZipInputStream(new FileInputStream(srcFile));
    ZipGenerator dest = new ZipGenerator(dstFile);
    ZipEntry entry = null;
    while ((entry = source.getNextEntry()) != null) {
      String name = entry.getName();

      if (name.endsWith(".xsd"))
        dest.addStream(entry.getName(), stripXsd(source), false);
      else if (name.endsWith(".json") && !name.endsWith(".schema.json"))
        dest.addStream(entry.getName(), stripJson(source), false);
      else if (name.endsWith(".xml"))
        dest.addStream(entry.getName(), stripXml(source), false);
      else
        dest.addStream(entry.getName(), source, false);
    }
    source.actualClose();
    dest.close();
  }

  private InputStream stripJson(InputStream source) throws Exception {
    JsonParser p = new JsonParser();
    Resource r = p.parse(source);
    minify(r);
    ByteArrayOutputStream bo = new ByteArrayOutputStream();
    p.compose(bo, r);
    bo.close();
    return new ByteArrayInputStream(bo.toByteArray());
  }

  private InputStream stripXml(InputStream source) throws Exception {
    XmlParser p = new XmlParser();
    Resource r = p.parse(source);
    minify(r);
    ByteArrayOutputStream bo = new ByteArrayOutputStream();
    p.compose(bo, r);
    bo.close();
    return new ByteArrayInputStream(bo.toByteArray());
  }

  private void minify(Resource r) {
    if (r == null)
      return;
    if (r instanceof DomainResource)
      dropNarrative((DomainResource) r);
    if (r instanceof StructureDefinition)
      minifyProfile((StructureDefinition) r);
    if (r instanceof ValueSet)
      minifyValueSet((ValueSet) r);
    if (r instanceof CodeSystem)
      minifyCodeSystem((CodeSystem) r);
    if (r instanceof Bundle)
      minifyBundle((Bundle) r);
  }

  private void dropNarrative(DomainResource r) {
    if (r.hasText() && r.getText().hasDiv()) {
      r.getText().getDiv().getChildNodes().clear();
      r.getText().getDiv().addText("Narrative removed to reduce size");
    }
  }

  private void minifyBundle(Bundle b) {
    for (BundleEntryComponent e : b.getEntry())
      minify(e.getResource());
  }

  private void minifyProfile(StructureDefinition p) {
    p.getContact().clear();
    p.setDescriptionElement(null);
    p.getKeyword().clear();
    p.setPurposeElement(null);
    p.getMapping().clear();
    p.setDifferential(null);
    for (ElementDefinition ed : p.getSnapshot().getElement()) {
      ed.setShortElement(null);
      ed.setDefinitionElement(null);
      ed.setCommentElement(null);
      ed.setRequirementsElement(null);
      ed.getAlias().clear();
      ed.setMeaningWhenMissingElement(null);
      ed.getMapping().clear();
    }
  }

  private void minifyValueSet(ValueSet vs) {
    vs.getContact().clear();
    vs.setDescriptionElement(null);
    vs.setCopyrightElement(null);
  }

  private void minifyCodeSystem(CodeSystem cs) {
    cs.getContact().clear();
    cs.setDescriptionElement(null);
    cs.setCopyrightElement(null);
    stripDefinition(cs.getConcept());
  }
  
  private void stripDefinition(List<ConceptDefinitionComponent> concept) {
    for (ConceptDefinitionComponent c : concept) {
      c.setDefinitionElement(null);
      if (c.hasConcept())
        stripDefinition(c.getConcept());
    }
  }

  private InputStream stripXsd(InputStream source) throws Exception {
    byte[] src = IOUtils.toByteArray(source);
    try {
      byte[] xslt = IOUtils.toByteArray( new FileInputStream(Utilities.path(page.getFolders().rootDir, "implementations", "xmltools", "AnnotationStripper.xslt")));
      String scrs = new String(src);
      String xslts = new String(xslt);
      return new ByteArrayInputStream(XsltUtilities.transform(new HashMap<String, byte[]>(), src, xslt));
    } catch (Exception e) {
      if (web) {
        e.printStackTrace();
        throw e;
      } else
        return new ByteArrayInputStream(src);
    }

//    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//    factory.setNamespaceAware(false);
//    DocumentBuilder builder = factory.newDocumentBuilder();
//    Document doc = builder.parse(source);
//    stripElement(doc.getDocumentElement(), "annotation");
//    TransformerFactory transformerFactory = TransformerFactory.newInstance();
//    Transformer transformer = transformerFactory.newTransformer();
//    ByteArrayOutputStream bo = new ByteArrayOutputStream();
//    DOMSource src = new DOMSource(doc);erent
//    StreamResult streamResult =  new StreamResult(bo);
//    transformer.transform(src, streamResult);
//    bo.close();
//    return new ByteArrayInputStream(bo.toByteArray());
  }

  private Document loadDom(InputStream src, boolean namespaces) throws Exception {
  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
  factory.setNamespaceAware(namespaces);
  DocumentBuilder builder = factory.newDocumentBuilder();
  Document doc = builder.parse(src);
  return doc;
  }

  private void stripElement(Element element, String name) {
    Node child = element.getFirstChild();
    while (child != null) {
      Node next = child.getNextSibling();
      if (child.getNodeName().endsWith(name))
        element.removeChild(child);
      else if (child.getNodeType() == Node.ELEMENT_NODE)
        stripElement((Element) child, name);
      child = next;
    }

  }

  private void addOtherProfiles(Bundle bundle, Profile cp) {
    for (ConstraintStructure p : cp.getProfiles())
      bundle.addEntry().setResource(p.getResource()).setFullUrl("http://hl7.org/fhir/"+p.getResource().fhirType()+"/"+p.getResource().getId());
  }

  private void addOtherProfiles(Bundle bundle, ResourceDefn rd) {
    for (Profile cp : rd.getConformancePackages())
      addOtherProfiles(bundle, cp);


  }

  private void addSearchParams(Set<String> uris, Bundle bundle, ResourceDefn rd) throws Exception {
    if (rd.getConformancePack() == null) {
      for (SearchParameterDefn spd : rd.getSearchParams().values()) {
        if (spd.getResource() == null) {
          buildSearchDefinition(rd, spd);
        }
        SearchParameter sp = spd.getResource();
        if (!uris.contains(sp.getUrl())) {
          bundle.addEntry().setResource(sp).setFullUrl("http://hl7.org/fhir/"+sp.fhirType()+"/"+sp.getId());
          uris.add(sp.getUrl());
        }
      }
    } else
      addSearchParams(uris, bundle, rd.getConformancePack());
  }

  private void buildSearchDefinition(ResourceDefn rd, SearchParameterDefn spd) throws Exception {
    StructureDefinition p = new StructureDefinition();
    p.setFhirVersion(page.getVersion());
    p.setKind(StructureDefinitionKind.RESOURCE);
    p.setAbstract(true);
    p.setPublisher("Health Level Seven International (" + rd.getWg() + ")");
    p.setName(rd.getName());
    p.setVersion(Constants.VERSION);
    p.setType(rd.getName());
    p.addContact().addTelecom().setSystem(ContactPointSystem.URL).setValue("http://hl7.org/fhir");
    SearchParameter sp = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), page.getVersion(), dataElements, fpUsages, page.getFolders().rootDir, page.getUml(), page.getRc()).makeSearchParam(p, rd.getName()+"-"+spd.getCode(), rd.getName(), spd, rd);
    spd.setResource(sp);
  }

  private void addSearchParams(Set<String> uris, Bundle bundle, Profile conformancePack) {
    for (SearchParameter sp : conformancePack.getSearchParameters()) {
      if (!uris.contains(sp.getUrl())) {
        bundle.addEntry().setResource(sp).setFullUrl("http://hl7.org/fhir/"+sp.fhirType()+"/"+sp.getId());
        uris.add(sp.getUrl());
      }
    }
  }

  Set<StructureDefinition> ped = new HashSet<StructureDefinition>();

  private void produceExtensionDefinition(StructureDefinition ed) throws FileNotFoundException, Exception {
    if (!ped.contains(ed)) {
      ped.add(ed);
      ImplementationGuideDefn ig = page.getDefinitions().getIgs().get(ed.getUserString(ToolResourceUtilities.NAME_RES_IG));
      String prefix = ig.isCore() ? "" : ig.getCode()+File.separator;
      String filename = ed.getUserString("filename");
      FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + prefix+filename+".xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, ed);
      s.close();
      s = new FileOutputStream(page.getFolders().dstDir + prefix+filename+".canonical.xml");
      new XmlParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, ed);
      s.close();
      s = new FileOutputStream(page.getFolders().dstDir + prefix+filename+".json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, ed);
      s.close();
      s = new FileOutputStream(page.getFolders().dstDir + prefix+filename+".canonical.json");
      new JsonParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, ed);
      s.close();
      cloneToXhtml(prefix+filename, ed.getName(), false, "summary-instance", ed.getUrl(), null, wg(ed));
      jsonToXhtml(prefix+filename, ed.getName(), resource2Json(ed), "extension", ed.getUrl(), null, wg(ed));
      ttlToXhtml(prefix+filename, ed.getName(), resource2Ttl(ed), "extension", ed.getUrl(), null, wg(ed));

      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      XmlSpecGenerator gen = new XmlSpecGenerator(bytes, filename+"-definitions.html", null /*"http://hl7.org/fhir/"*/, page, page.genlevel(ig.isCore() ? 0 : 1));
      gen.generateExtension(ed);
      gen.close();
      String xml = bytes.toString();

      bytes = new ByteArrayOutputStream();
      JsonSpecGenerator genj = new JsonSpecGenerator(bytes, filename+"-definitions.html", null /*"http://hl7.org/fhir/"*/, page, page.genlevel(ig.isCore() ? 0 : 1));
      genj.generateExtension(ed);
      genj.close();
      String json = bytes.toString();
      bytes = new ByteArrayOutputStream();
      TurtleSpecGenerator gent = new TurtleSpecGenerator(bytes, filename+"-definitions.html", null /*"http://hl7.org/fhir/"*/, page, page.genlevel(ig.isCore() ? 0 : 1));
      gent.generateExtension(ed);
      gent.close();
      String ttl = bytes.toString();

      bytes = new ByteArrayOutputStream();
      TerminologyNotesGenerator tgen = new TerminologyNotesGenerator(bytes, page);
      tgen.generateExtension("", ed);
      tgen.close();
      String tx = bytes.toString();

      String src = TextFile.fileToString(page.getFolders().templateDir + "template-extension-mappings.html");
      src = page.processExtensionIncludes(filename, ed, xml, json, ttl, tx, src, filename + ".html", ig);
      page.getHTMLChecker().registerFile(prefix+filename + "-mappings.html", "Mappings for Extension " + ed.getName(), HTMLLinkChecker.XHTML_TYPE, true);
      TextFile.stringToFile(src, page.getFolders().dstDir + prefix+filename + "-mappings.html");

      src = TextFile.fileToString(page.getFolders().templateDir + "template-extension-definitions.html");
      src = page.processExtensionIncludes(filename, ed, xml, json, ttl, tx, src, filename + ".html", ig);
      page.getHTMLChecker().registerFile(prefix+filename + "-definitions.html", "Definitions for Extension " + ed.getName(), HTMLLinkChecker.XHTML_TYPE, true);
      TextFile.stringToFile(src, page.getFolders().dstDir + prefix+filename + "-definitions.html");

      src = TextFile.fileToString(page.getFolders().templateDir + "template-extension.html");
      src = page.processExtensionIncludes(filename, ed, xml, json, ttl, tx, src, filename + ".html", ig);
      page.getHTMLChecker().registerFile(prefix+filename + ".html", "Extension " + ed.getName(), HTMLLinkChecker.XHTML_TYPE, true);
      TextFile.stringToFile(src, page.getFolders().dstDir + prefix+filename + ".html");
    }
  }

  private WorkGroup wg(StructureDefinition ed) {
    return page.getDefinitions().getWorkgroups().get(ToolingExtensions.readStringExtension(ed, ToolingExtensions.EXT_WORKGROUP));
  }

  private void copyStaticContent() throws IOException, Exception {
    if (page.getIni().getPropertyNames("support") != null)
      for (String n : page.getIni().getPropertyNames("support")) {
        Utilities.copyFile(new CSFile(page.getFolders().srcDir + n), new CSFile(page.getFolders().dstDir + n));
        page.getHTMLChecker().registerFile(n, "Support File", HTMLLinkChecker.determineType(n), true);
      }
    for (String n : page.getIni().getPropertyNames("images")) {
      copyImage(page.getFolders().imgDir, n);
    }
    for (ImplementationGuideDefn ig : page.getDefinitions().getSortedIgs()) {
      for (String n : ig.getImageList()) {
        copyIgImage(ig, n);
      }
    }
    for (String n : page.getIni().getPropertyNames("files")) {
      Utilities.copyFile(new CSFile(page.getFolders().rootDir + n), new CSFile(page.getFolders().dstDir + page.getIni().getStringProperty("files", n)));
      page.getHTMLChecker().registerFile(page.getIni().getStringProperty("files", n), "Support File",
          HTMLLinkChecker.determineType(page.getIni().getStringProperty("files", n)), true);
    }

    page.log("Copy HTML templates", LogMessageType.Process);
    Utilities.copyDirectory(page.getFolders().rootDir + page.getIni().getStringProperty("html", "source"), page.getFolders().dstDir, page.getHTMLChecker());
    TextFile.stringToFile("\r\n[FHIR]\r\nFhirVersion=" + page.getVersion() + "-" + page.getBuildId() + "\r\nversion=" + page.getVersion().toCode()
        + "\r\nbuildId=" + page.getBuildId() + "\r\ndate=" + new SimpleDateFormat("yyyyMMddHHmmss").format(page.getGenDate().getTime()),
        Utilities.path(page.getFolders().dstDir, "version.info"), false);

    for (String n : page.getDefinitions().getDiagrams().keySet()) {
      page.log(" ...diagram " + n, LogMessageType.Process);
      page.getSvgs().put(n, TextFile.fileToString(page.getFolders().srcDir + page.getDefinitions().getDiagrams().get(n)));
    }
  }

  private void copyImage(String folder, String n) throws IOException {
    if (n.contains("*")) {
      final String filter = n.replace("?", ".?").replace("*", ".*?");
      File[] files = new File(folder).listFiles(new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
          return name.matches(filter);
        }
      });
      for (File f : files) {
        Utilities.copyFile(f, new CSFile(page.getFolders().dstDir + f.getName()));
        page.getHTMLChecker().registerFile(f.getName(), "Support File", HTMLLinkChecker.determineType(n), true);
      }
    } else {
      Utilities.copyFile(new CSFile(Utilities.path(folder, n)), new CSFile(page.getFolders().dstDir + (n.contains("/") ? n.substring(n.lastIndexOf("/")+1): n)));
      page.getHTMLChecker().registerFile(n, "Support File", HTMLLinkChecker.determineType(n), true);
    }
  }

  private void copyIgImage(ImplementationGuideDefn ig, String path) throws IOException {
    File file = new File(Utilities.path(page.getFolders().rootDir, ig.getSource(), "..", path));
    String prefix = ig.isCore() ? "" : ig.getCode()+File.separator;

    if (path.contains("*")) {
      final String filter = file.getName().replace("?", ".?").replace("*", ".*?");
      File[] files = new File(file.getParent()).listFiles(new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
          return name.matches(filter);
        }
      });
      for (File f : files) {
        Utilities.copyFile(f, new CSFile(Utilities.path(page.getFolders().dstDir, prefix+f.getName())));
        page.getHTMLChecker().registerFile(prefix+f.getName(), "Support File", HTMLLinkChecker.determineType(f.getName()), true);
      }
    } else {
      Utilities.copyFile(file, new CSFile(Utilities.path(page.getFolders().dstDir, prefix+file.getName())));
      page.getHTMLChecker().registerFile(prefix+file.getName(), "Support File", HTMLLinkChecker.determineType(file.getName()), true);
    }
  }

  /** this is only used when generating xhtml of json **/
  private String resource2Json(Bundle profileFeed2) throws Exception {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
    json.setSuppressXhtml("Snipped for Brevity");
    json.compose(bytes, page.getResourceBundle());
    bytes.close();
    return new String(bytes.toByteArray());
  }

  private String resource2Json(Resource r) throws Exception {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
    json.setSuppressXhtml("Snipped for Brevity");
    json.compose(bytes, r);
    bytes.close();
    return new String(bytes.toByteArray());
  }

  private String resource2Ttl(Resource r) throws Exception {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    IParser rdf = new RdfParser().setOutputStyle(OutputStyle.PRETTY);
    rdf.setSuppressXhtml("Snipped for Brevity");
    rdf.compose(bytes, r);
    bytes.close();
    return new String(bytes.toByteArray());
  }

  private void produceQA() throws Exception {
    page.getQa().countDefinitions(page.getDefinitions());

    String src = TextFile.fileToString(page.getFolders().srcDir + "qa.html");
    TextFile.stringToFile(page.processPageIncludes("qa.html", src, "page", null, null, null, "QA Page", null, null, page.getDefinitions().getWorkgroups().get("fhir")), page.getFolders().dstDir + "qa.html");

    if (web) {
      page.getQa().commit(page.getFolders().rootDir);
    }
  }


  private void produceBaseProfile() throws Exception {

    for (DefinedCode pt : page.getDefinitions().getPrimitives().values())
      producePrimitiveTypeProfile(pt);
    produceXhtmlProfile();
    for (TypeDefn e : page.getDefinitions().getTypes().values())
      produceTypeProfile(e);
    for (TypeDefn e : page.getDefinitions().getInfrastructure().values())
      produceTypeProfile(e);
    for (ProfiledType c : page.getDefinitions().getConstraints().values())
      produceProfiledTypeProfile(c);
  }

  private void produceProfiledTypeProfile(ProfiledType pt) throws Exception {
    String fn = pt.getName().toLowerCase() + ".profile.xml";
    StructureDefinition rp = pt.getProfile();

    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + fn);
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, rp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(fn, ".canonical.xml"));
    new XmlParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, rp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(fn, ".json"));
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, rp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(fn, ".canonical.json"));
    new JsonParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, rp);
    s.close();

    Utilities.copyFile(new CSFile(page.getFolders().dstDir + fn), new CSFile(Utilities.path(page.getFolders().dstDir, "examples", fn)));
    addToResourceFeed(rp, page.getTypeBundle(), (fn));
    cloneToXhtml(pt.getName().toLowerCase() + ".profile", "StructureDefinition for " + pt.getName(), false, "profile-instance:type:" + pt.getName(), "Type", null, wg("mnm"));
    jsonToXhtml(pt.getName().toLowerCase() + ".profile", "StructureDefinition for " + pt.getName(), resource2Json(rp), "profile-instance:type:" + pt.getName(), "Type", null, wg("mnm"));
    ttlToXhtml(pt.getName().toLowerCase() + ".profile", "StructureDefinition for " + pt.getName(), resource2Ttl(rp), "profile-instance:type:" + pt.getName(), "Type", null, wg("mnm"));
    String shex = new ShExGenerator(page.getWorkerContext()).generate(HTMLLinkPolicy.NONE, rp);
    TextFile.stringToFile(shex, Utilities.changeFileExt(page.getFolders().dstDir + fn, ".shex"));
    shexToXhtml(pt.getName().toLowerCase(), "ShEx statement for " + pt.getName(), shex, "profile-instance:type:" + pt.getName(), "Type", null, wg("mnm"));
  }

  private void produceXhtmlProfile() throws Exception {

    String fn = "xhtml.profile.xml";
    StructureDefinition rp = page.getProfiles().get("xhtml");

    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + fn);
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, rp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(fn, ".canonical.xml"));
    new XmlParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, rp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(fn, ".json"));
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, rp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(fn, ".canonical.json"));
    new JsonParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, rp);
    s.close();

    String shex = new ShExGenerator(page.getWorkerContext()).generate(HTMLLinkPolicy.NONE, rp);
    TextFile.stringToFile(shex, Utilities.changeFileExt(page.getFolders().dstDir + fn, ".shex"));
    
    Utilities.copyFile(new CSFile(page.getFolders().dstDir + fn), new CSFile(Utilities.path(page.getFolders().dstDir, "examples", fn)));
    addToResourceFeed(rp, page.getTypeBundle(), (fn));
    // saveAsPureHtml(rp, new FileOutputStream(page.getFolders().dstDir+ "html"
    // + File.separator + "datatypes.html"));
    cloneToXhtml("xhtml.profile", "StructureDefinition for xhtml", false, "profile-instance:type:xhtml", "Type", null, wg("mnm"));
    jsonToXhtml("xhtml.profile", "StructureDefinition for xhtml", resource2Json(rp), "profile-instance:type:xhtml", "Type", null, wg("mnm"));
    ttlToXhtml("xhtml.profile", "StructureDefinition for xhtml", resource2Ttl(rp), "profile-instance:type:xhtml", "Type", null, wg("mnm"));
    shexToXhtml("xhtml", "ShEx statement for xhtml", shex, "profile-instance:type:xhtml", "Type", null, wg("mnm"));
  }


  private void producePrimitiveTypeProfile(DefinedCode type) throws Exception {

    String fn = type.getCode().toLowerCase() + ".profile.xml";
    StructureDefinition rp = type.getProfile();

    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + fn);
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, rp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(fn, ".canonical.xml"));
    new XmlParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, rp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(fn, ".json"));
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, rp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(fn, ".canonical.json"));
    new JsonParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, rp);
    s.close();

    String shex = new ShExGenerator(page.getWorkerContext()).generate(HTMLLinkPolicy.NONE, rp);
    TextFile.stringToFile(shex, Utilities.changeFileExt(page.getFolders().dstDir + fn, ".shex"));
    
    Utilities.copyFile(new CSFile(page.getFolders().dstDir + fn), new CSFile(Utilities.path(page.getFolders().dstDir, "examples", fn)));
    addToResourceFeed(rp, page.getTypeBundle(), (fn));
    // saveAsPureHtml(rp, new FileOutputStream(page.getFolders().dstDir+ "html"
    // + File.separator + "datatypes.html"));
    cloneToXhtml(type.getCode().toLowerCase() + ".profile", "StructureDefinition for " + type.getCode(), false, "profile-instance:type:" + type.getCode(), "Type", null, wg("mnm"));
    jsonToXhtml(type.getCode().toLowerCase() + ".profile", "StructureDefinition for " + type.getCode(), resource2Json(rp), "profile-instance:type:" + type.getCode(), "Type", null, wg("mnm"));
    ttlToXhtml(type.getCode().toLowerCase() + ".profile", "StructureDefinition for " + type.getCode(), resource2Ttl(rp), "profile-instance:type:" + type.getCode(), "Type", null, wg("mnm"));
    shexToXhtml(type.getCode().toLowerCase(), "ShEx statement for " + type.getCode(), shex, "profile-instance:type:" + type.getCode(), "Type", null, wg("mnm"));
  }

  private void produceTypeProfile(TypeDefn type) throws Exception {
    //    ProfileDefn p = new ProfileDefn();
    //    p.putMetadata("id", type.getName());
    //    p.putMetadata("name", "Basic StructureDefinition for " + type.getName());
    //    p.putMetadata("author.name", "FHIR Specification");
    //    p.putMetadata("author.ref", "http://hl7.org/fhir");
    //    p.putMetadata("description", "Basic StructureDefinition for " + type.getName() + " for validation support");
    //    p.putMetadata("status", "draft");
    //    p.putMetadata("date", new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "US")).format(new Date()));
    //    p.getElements().add(type);
    //    ProfileGenerator pgen = new ProfileGenerator(page.getDefinitions());
    //    String fn = "type-" + type.getName() + ".profile.xml";
    //    StructureDefinition rp = pgen.generate(p, "type-" + type.getName() + ".profile", "<div>Type definition for " + type.getName() + " from <a href=\"http://hl7.org/fhir/datatypes.html#" + type.getName()
    //        + "\">FHIR Specification</a></div>");

    String fn = type.getName().toLowerCase() + ".profile.xml";
    StructureDefinition rp = type.getProfile();

    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + fn);
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, rp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(fn, ".canonical.xml"));
    new XmlParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, rp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(fn, ".json"));
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, rp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(fn, ".canonical.json"));
    new JsonParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, rp);
    s.close();

    Utilities.copyFile(new CSFile(page.getFolders().dstDir + fn), new CSFile(Utilities.path(page.getFolders().dstDir, "examples", fn)));
    addToResourceFeed(rp, page.getTypeBundle(), fn);
    // saveAsPureHtml(rp, new FileOutputStream(page.getFolders().dstDir+ "html"
    // + File.separator + "datatypes.html"));
    cloneToXhtml(type.getName().toLowerCase() + ".profile", "StructureDefinition for " + type.getName(), false, "profile-instance:type:" + type.getName(), "Type", null, wg("mnm"));
    jsonToXhtml(type.getName().toLowerCase() + ".profile", "StructureDefinition for " + type.getName(), resource2Json(rp), "profile-instance:type:" + type.getName(), "Type", null, wg("mnm"));
    ttlToXhtml(type.getName().toLowerCase() + ".profile", "StructureDefinition for " + type.getName(), resource2Ttl(rp), "profile-instance:type:" + type.getName(), "Type", null, wg("mnm"));
    String shex = new ShExGenerator(page.getWorkerContext()).generate(HTMLLinkPolicy.NONE, rp);
    TextFile.stringToFile(shex, Utilities.changeFileExt(page.getFolders().dstDir + fn, ".shex"));
    shexToXhtml(type.getName().toLowerCase(), "ShEx statement for " + type.getName(), shex, "profile-instance:type:" + type.getName(), "Type", null, wg("mnm"));
  }

  protected XmlPullParser loadXml(InputStream stream) throws Exception {
    BufferedInputStream input = new BufferedInputStream(stream);
    XmlPullParserFactory factory = XmlPullParserFactory.newInstance(System.getProperty(XmlPullParserFactory.PROPERTY_NAME), null);
    factory.setNamespaceAware(true);
    XmlPullParser xpp = factory.newPullParser();
    xpp.setInput(input, "UTF-8");
    xpp.next();
    return xpp;
  }

  protected int nextNoWhitespace(XmlPullParser xpp) throws Exception {
    int eventType = xpp.getEventType();
    while (eventType == XmlPullParser.TEXT && xpp.isWhitespace())
      eventType = xpp.next();
    return eventType;
  }

  private void checkFragments() throws Exception {
    for (Fragment f : fragments) {
      try {
        // System.out.println("    "+f.page+"/"+f.id);
        String xml = f.getXml();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        Document doc = builder.parse(is);
        org.w3c.dom.Element base = doc.getDocumentElement();
        String type = base.getAttribute("fragment");
        if (!page.getDefinitions().hasPrimitiveType(type)) {
          if (f.isJson()) {
            org.hl7.fhir.r5.elementmodel.JsonParser p = new org.hl7.fhir.r5.elementmodel.JsonParser(page.getWorkerContext());
            p.setupValidation(ValidationPolicy.QUICK, null);
            p.parse(base.getTextContent(), type);
          } else {
            org.hl7.fhir.r5.elementmodel.XmlParser p = new org.hl7.fhir.r5.elementmodel.XmlParser(page.getWorkerContext());
            p.setupValidation(ValidationPolicy.QUICK, null);
            p.parse(XMLUtil.getFirstChild(base), type);
          }
        }
      } catch (Exception e) {
        page.getValidationErrors().add(new ValidationMessage(Source.Publisher, IssueType.STRUCTURE, f.getPage(), "Fragment Error in page " + f.getPage() +(f.id != null ? "#"+f.id : "") + ": " + e.getMessage(), IssueSeverity.ERROR));
      }
    }
  }

  private void produceSchemaZip() throws Exception {
    char sc = File.separatorChar;
    File f = new CSFile(page.getFolders().dstDir + "fhir-all-xsd.zip");
    if (f.exists())
      f.delete();
    ZipGenerator zip = new ZipGenerator(page.getFolders().tmpResDir + "fhir-all-xsd.zip");
    zip.addFiles(page.getFolders().dstDir, "", ".xsd", null);
    zip.addFiles(page.getFolders().dstDir, "", ".sch", null);
    zip.addFiles(page.getFolders().rootDir + "tools" + sc + "schematron" + sc, "", ".xsl", "");
    zip.close();
    Utilities.copyFile(new CSFile(page.getFolders().tmpResDir + "fhir-all-xsd.zip"), f);

    f = new CSFile(page.getFolders().dstDir + "fhir-codegen-xsd.zip");
    if (f.exists())
      f.delete();
    zip = new ZipGenerator(page.getFolders().tmpResDir + "fhir-codegen-xsd.zip");
    zip.addFiles(page.getFolders().xsdDir+"codegen"+File.separator, "", ".xsd", null);
    zip.close();
    Utilities.copyFile(new CSFile(page.getFolders().tmpResDir + "fhir-codegen-xsd.zip"), f);

    f = new CSFile(page.getFolders().dstDir + "fhir.schema.json.zip");
    if (f.exists())
      f.delete();
    zip = new ZipGenerator(page.getFolders().tmpResDir + "fhir.schema.json.zip");
    zip.addFiles(page.getFolders().dstDir, "", ".schema.json", null);
    zip.close();
    Utilities.copyFile(new CSFile(page.getFolders().tmpResDir + "fhir.schema.json.zip"), f);
    f = new CSFile(page.getFolders().dstDir + "fhir.schema.graphql.zip");
    if (f.exists())
      f.delete();
    zip = new ZipGenerator(page.getFolders().tmpResDir + "fhir.schema.graphql.zip");
    zip.addFiles(page.getFolders().dstDir, "", ".graphql", null);
    zip.close();
    Utilities.copyFile(new CSFile(page.getFolders().tmpResDir + "fhir.schema.graphql.zip"), f);
    zip = new ZipGenerator(page.getFolders().dstDir + "fhir.schema.shex.zip");
    zip.addFileName("fhir.shex", Utilities.path(page.getFolders().dstDir, "fhir.shex"), false);
    zip.close();
  }

  private void produceResource1(ResourceDefn resource, boolean isAbstract) throws Exception {
    String n = resource.getName().toLowerCase();
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    XmlSpecGenerator gen = new XmlSpecGenerator(bs, n + "-definitions.html", null, page, "");
    gen.generate(resource.getRoot(), isAbstract, true);
    gen.close();
    String xml = new String(bs.toByteArray());

    bs = new ByteArrayOutputStream();
    JsonSpecGenerator genJ = new JsonSpecGenerator(bs, n + "-definitions.html", null, page, "");
    genJ.generate(resource.getRoot(), true, isAbstract);
    genJ.close();
    String json = new String(bs.toByteArray());

    bs = new ByteArrayOutputStream();
    TurtleSpecGenerator gent = new TurtleSpecGenerator(bs, n + "-definitions.html", null, page, "");
    gent.generate(resource.getRoot(), isAbstract);
    gent.close();
    String ttl = new String(bs.toByteArray());

    xmls.put(n, xml);
    jsons.put(n, json);
    ttls.put(n, ttl);
    generateProfile(resource, n, xml, json, ttl, false);
  }

  private void produceResource2(ResourceDefn resource, boolean isAbstract, String extraTypeForDefn, boolean logicalOnly) throws Exception {
    File tmp = Utilities.createTempFile("tmp", ".tmp");
    String n = resource.getName().toLowerCase();
    String xml = xmls.get(n);
    String json = jsons.get(n);
    String ttl = ttls.get(n);

    TerminologyNotesGenerator tgen = new TerminologyNotesGenerator(new FileOutputStream(tmp), page);
    tgen.generate("", resource.getRoot());
    tgen.close();
    String tx = TextFile.fileToString(tmp.getAbsolutePath());

    DictHTMLGenerator dgen = new DictHTMLGenerator(new FileOutputStream(tmp), page, "");
    dgen.generate(resource.getRoot());
    dgen.close();
    String dict = TextFile.fileToString(tmp.getAbsolutePath());

    if (extraTypeForDefn != null) {
      dgen = new DictHTMLGenerator(new FileOutputStream(tmp), page, "");
      dgen.generate(page.getDefinitions().getElementDefn(extraTypeForDefn));
      dgen.close();
      dict = dict +"\r\n"+ TextFile.fileToString(tmp.getAbsolutePath());
    }
    Map<String, String> values = new HashMap<String, String>();

    MappingsGenerator mgen = new MappingsGenerator(page.getDefinitions());
    mgen.generate(resource);
    String mappings = mgen.getMappings();
    String mappingsList = mgen.getMappingsList();

    if (!logicalOnly) {
      SvgGenerator svg = new SvgGenerator(page, "", resource.getLayout(), true, false);
      svg.generate(resource, page.getFolders().dstDir + n + ".svg", "1");
      svg.generate(resource, Utilities.path(page.getFolders().srcDir, n, n + ".gen.svg"), "1");
  
      String prefix = page.getBreadCrumbManager().getIndexPrefixForReference(resource.getName());
      SectionTracker st = new SectionTracker(prefix, false);
      st.start("");
      page.getSectionTrackerCache().put(n, st);

      String template = isAbstract ? "template-abstract" : "template";
      String src = TextFile.fileToString(page.getFolders().templateDir + template+".html");
      src = insertSectionNumbers(page.processResourceIncludes(n, resource, xml, json, ttl, tx, dict, src, mappings, mappingsList, "resource", n + ".html", null, values, resource.getWg(), null), st, n + ".html", 0, null);
      TextFile.stringToFile(src, page.getFolders().dstDir + n + ".html");
      scanForFragments(n + ".html", new XhtmlParser().parseFragment(src));
      page.getHTMLChecker().registerFile(n + ".html", "Base Page for " + resource.getName(), HTMLLinkChecker.XHTML_TYPE, true);

      StructureDefinition profile = (StructureDefinition) ResourceUtilities.getById(page.getResourceBundle(), ResourceType.StructureDefinition, resource.getName());
      String pages = page.getIni().getStringProperty("resource-pages", n);
      if (!Utilities.noString(pages)) {
        for (String p : pages.split(",")) {
          producePage(p, n);
        }
      }
      try {
        if (!isAbstract)
          processQuestionnaire(resource, profile, st, true, "", null);
      } catch (Exception e) {
        //      e.printStackTrace();
        page.log("Questionnaire Generation Failed: "+e.getMessage(), LogMessageType.Error);
      }

      if (!isAbstract || !resource.getExamples().isEmpty()) {
        src = TextFile.fileToString(page.getFolders().templateDir + template+"-examples.html");
        TextFile.stringToFile(
            insertSectionNumbers(page.processResourceIncludes(n, resource, xml, json, ttl, tx, dict, src, mappings, mappingsList, "res-Examples", n + "-examples.html", null, values, resource.getWg(), null), st, n + "-examples.html", 0, null),
            page.getFolders().dstDir + n + "-examples.html");
        page.getHTMLChecker().registerFile(n + "-examples.html", "Examples for " + resource.getName(), HTMLLinkChecker.XHTML_TYPE, true);
        for (Example e : resource.getExamples()) {
          try {
            processExample(e, resource, profile, null, e.getIg() == null ? null : page.getDefinitions().getIgs().get(e.getIg()));
          } catch (Exception ex) {
            throw new Exception("processing " + e.getTitle(), ex);
            // throw new Exception(ex.getMessage()+" processing "+e.getFileTitle());
          }
        }
      }
      src = TextFile.fileToString(page.getFolders().templateDir + template+"-definitions.html");
      TextFile.stringToFile(
          insertSectionNumbers(page.processResourceIncludes(n, resource, xml, json, ttl, tx, dict, src, mappings, mappingsList, "res-Detailed Descriptions", n + "-definitions.html", null, values, resource.getWg(), null), st, n
              + "-definitions.html", 0, null), page.getFolders().dstDir + n + "-definitions.html");
      page.getHTMLChecker().registerFile(n + "-definitions.html", "Detailed Descriptions for " + resource.getName(), HTMLLinkChecker.XHTML_TYPE, true);

      if (!isAbstract) {
        src = TextFile.fileToString(page.getFolders().templateDir + "template-mappings.html");
        TextFile.stringToFile(
            insertSectionNumbers(page.processResourceIncludes(n, resource, xml, json, ttl, tx, dict, src, mappings, mappingsList, "res-Mappings", n + "-mappings.html", null, values, resource.getWg(), null), st, n + "-mappings.html", 0, null),
            page.getFolders().dstDir + n + "-mappings.html");
        page.getHTMLChecker().registerFile(n + "-mappings.html", "Formal Mappings for " + resource.getName(), HTMLLinkChecker.XHTML_TYPE, true);
        src = TextFile.fileToString(page.getFolders().templateDir + "template-profiles.html");
        TextFile.stringToFile(
            insertSectionNumbers(page.processResourceIncludes(n, resource, xml, json, ttl, tx, dict, src, mappings, mappingsList, "res-Profiles", n + "-profiles.html", null, values, resource.getWg(), null), st, n + "-profiles.html", 0, null),
            page.getFolders().dstDir + n + "-profiles.html");
        page.getHTMLChecker().registerFile(n + "-profiles.html", "Profiles for " + resource.getName(), HTMLLinkChecker.XHTML_TYPE, true);
      }
      if (!resource.getOperations().isEmpty()) {
        src = TextFile.fileToString(page.getFolders().templateDir + "template-operations.html");
        TextFile.stringToFile(
            insertSectionNumbers(page.processResourceIncludes(n, resource, xml, json, ttl, tx, dict, src, mappings, mappingsList, "res-Operations", n + "-operations.html", null, values, resource.getWg(), null), st, n + "-operations.html", 0, null), 
            page.getFolders().dstDir + n + "-operations.html");
        page.getHTMLChecker().registerFile(n + "-operations.html", "Operations for " + resource.getName(), HTMLLinkChecker.XHTML_TYPE, true);

        for (Operation t : resource.getOperations()) {
          produceOperation(null, resource.getName().toLowerCase()+"-"+t.getName(), resource.getName()+"-"+t.getName(), resource, t, st);
        }
      }
      produceMap(resource.getName(), st, resource);
      for (Profile ap : resource.getConformancePackages())
        produceConformancePackage(resource, ap, st);
      src = TextFile.fileToString(page.getFolders().templateDir + "template-json-schema.html");
      TextFile.stringToFile(
          insertSectionNumbers(page.processResourceIncludes(n, resource, xml, json, ttl, tx, dict, src, mappings, mappingsList, "res-schema", n + ".schema.json.html", null, values, resource.getWg(), null), st, n + ".schema.json.html", 0, null),
          page.getFolders().dstDir + n + ".schema.json.html");
      page.getHTMLChecker().registerFile(n + ".schema.json.html", "Json Schema for " + resource.getName(), HTMLLinkChecker.XHTML_TYPE, true);

      src = TextFile.fileToString(page.getFolders().templateDir + "template-dependencies.html");
      TextFile.stringToFile(
          insertSectionNumbers(page.processResourceIncludes(n, resource, xml, json, ttl, tx, dict, src, mappings, mappingsList, "res-Dependencies", n + "-dependencies.html", null, values, resource.getWg(), null), st, n
              + "-dependencies.html", 0, null), page.getFolders().dstDir + n + "-dependencies.html");
      page.getHTMLChecker().registerFile(n + "-dependencies.html", "Dependency graph for " + resource.getName(), HTMLLinkChecker.XHTML_TYPE, true);
      
      for (ConceptMap cm : statusCodeConceptMaps)
        if (cm.getUserData("resource-definition") == resource) 
          produceConceptMap(cm, resource, st);

      // xml to json
      // todo - fix this up
      // JsonGenerator jsongen = new JsonGenerator();
      // jsongen.generate(new CSFile(page.getFolders().dstDir+n+".xml"), new
      // File(page.getFolders().dstDir+n+".json"));
    }
    tmp.delete();

    new ProfileUtilities(page.getWorkerContext(), page.getValidationErrors(), page).generateXlsx(new FileOutputStream(Utilities.path(page.getFolders().dstDir, n + ".xlsx")), resource.getProfile(), false, false);
    
    // because we'll pick up a little more information as we process the
    // resource
    StructureDefinition p = generateProfile(resource, n, xml, json, ttl, !logicalOnly);
    com.google.gson.JsonObject diff = new com.google.gson.JsonObject();
    page.getDiffEngine().getDiffAsJson(diff, p);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    json = gson.toJson(diff);
    TextFile.stringToFile(json, Utilities.path(page.getFolders().dstDir, resource.getName().toLowerCase()+".diff.json"));

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = dbf.newDocumentBuilder();
    Document doc = builder.newDocument();
    Element element = doc.createElement("difference");
    doc.appendChild(element);
    page.getDiffEngine().getDiffAsXml(doc, element, p);
    prettyPrint(doc, Utilities.path(page.getFolders().dstDir, resource.getName().toLowerCase()+".diff.xml"));
}

  public void prettyPrint(Document xml, String filename) throws Exception {
    Transformer tf = TransformerFactory.newInstance().newTransformer();
    tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    tf.setOutputProperty(OutputKeys.INDENT, "yes");
    Writer out = new StringWriter();
    tf.transform(new DOMSource(xml), new StreamResult(out));
    TextFile.stringToFile(out.toString(), filename);
  }

  private void produceOperation(ImplementationGuideDefn ig, String name, String id, ResourceDefn resource, Operation op, SectionTracker st) throws Exception {
    OperationDefinition opd = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), page.getVersion(), dataElements, fpUsages, page.getFolders().rootDir, page.getUml(), page.getRc()).generate(name, id, resource.getName(), op, resource);
    
    String dir = ig == null ? "" : ig.getCode()+File.separator;

    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + dir+"operation-" + name + ".xml");
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, opd);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + dir+"operation-" + name + ".canonical.xml");
    new XmlParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, opd);
    s.close();
    cloneToXhtml(dir+"operation-" + name + "", "Operation Definition", true, "resource-instance:OperationDefinition", "Operation definition", resource, resource.getWg());
    s = new FileOutputStream(page.getFolders().dstDir + dir+"operation-" + name + ".json");
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, opd);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + dir+"operation-" + name + ".canonical.json");
    new JsonParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, opd);
    s.close();
    jsonToXhtml(dir+"operation-" + name, "Operation Definition", resource2Json(opd), "resource-instance:OperationDefinition", "Operation definition", resource, resource.getWg());

    s = new FileOutputStream(page.getFolders().dstDir + dir+"operation-" + name + ".ttl");
    new RdfParser().setOutputStyle(OutputStyle.PRETTY).compose(s, opd);
    s.close();
    ttlToXhtml(dir+"operation-" + name, "Operation Definition", resource2Ttl(opd), "resource-instance:OperationDefinition", "Operation definition", resource, resource.getWg());
    
    Utilities.copyFile(new CSFile(page.getFolders().dstDir + dir+"operation-" + name + ".xml"), new CSFile(page.getFolders().dstDir + "examples" + File.separator + "operation-" + name + ".xml"));
    if (buildFlags.get("all")) {
      addToResourceFeed(opd, page.getResourceBundle(), name);
      page.getWorkerContext().cacheResource(opd);
    }
    // now we create a page for the operation
    String fnp = resource.getName().toLowerCase()+"-operation-" + op.getName().toLowerCase()+".html";
    
    String src = TextFile.fileToString(page.getFolders().templateDir + "template-operation.html");
    src = page.processPageIncludes(fnp, src, "res-Operations", null, "operation-" + name + ".html", op.getResource(), null, "Operation Definition", op, ig, resource, resource.getWg());
    TextFile.stringToFile(insertSectionNumbers(src, st, fnp, 0, null), page.getFolders().dstDir + fnp);
    page.getHTMLChecker().registerFile(fnp, "Operation "+op.getName()+" for " + resource.getName(), HTMLLinkChecker.XHTML_TYPE, true);

    
    // now, we create an html page from the narrative
    String html = TextFile.fileToString(page.getFolders().templateDir + "template-example.html").replace("<%example%>", new XhtmlComposer(XhtmlComposer.HTML).compose(opd.getText().getDiv()));
    html = page.processPageIncludes(dir+"operation-" + name + ".html", html, "resource-instance:OperationDefinition", null, null, null, "Operation Definition", ig, resource, resource.getWg());
    TextFile.stringToFile(html, page.getFolders().dstDir + dir+"operation-" + name + ".html");
    page.getHTMLChecker().registerFile(dir+"operation-" + name + ".html", "Operation " + op.getName(), HTMLLinkChecker.XHTML_TYPE, true);
    // head =
    // "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">\r\n<head>\r\n <title>"+Utilities.escapeXml(e.getDescription())+"</title>\r\n <link rel=\"Stylesheet\" href=\"fhir.css\" type=\"text/css\" media=\"screen\"/>\r\n"+
    // "</head>\r\n<body>\r\n<p>&nbsp;</p>\r\n<p>"+Utilities.escapeXml(e.getDescription())+"</p>\r\n"+
    // "<p><a href=\""+n+".xml.html\">XML</a> <a href=\""+n+".json.html\">JSON</a></p>\r\n";
    // tail = "\r\n</body>\r\n</html>\r\n";
    // TextFile.stringToFile(head+narrative+tail, page.getFolders().dstDir + n +
    // ".html");
  }

  /*
  private void generateQuestionnaire(String n, StructureDefinition p) throws Exception {
    QuestionnaireBuilder b = new QuestionnaireBuilder(page.getWorkerContext());
    b.setProfile(p);
    b.build();
    Questionnaire q = b.getQuestionnaire();

    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(page.getFolders().dstDir + n + "-questionnaire.xml"), q);
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(page.getFolders().dstDir + n + "-questionnaire.json"), q);

  }

  */
  private void shexToXhtml(String n, String description, String shex, String pageType, String crumbTitle, ResourceDefn rd, WorkGroup wg) throws Exception {
    shexToXhtml(n, description, shex, pageType, crumbTitle, null, rd, wg);
  }
  
  private void shexToXhtml(String n, String description, String shex, String pageType, String crumbTitle, ImplementationGuideDefn igd, ResourceDefn rd, WorkGroup wg) throws Exception {
    shex = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml(description) + "</p>\r\n<pre class=\"shex\">\r\n" + Utilities.escapeXml(shex)+ "\r\n</pre>\r\n</div>\r\n";
    String html = TextFile.fileToString(page.getFolders().templateDir + "template-example-shex.html").replace("<%example%>", shex);
    html = page.processPageIncludes(n + ".shex.html", html, pageType, null, null, null, crumbTitle, igd, rd, wg);
    TextFile.stringToFile(html, page.getFolders().dstDir + n + ".shex.html");
    page.getHTMLChecker().registerExternal(n + ".shex.html");
  }

  private void ttlToXhtml(String n, String description, String ttl, String pageType, String crumbTitle, ResourceDefn rd, WorkGroup wg) throws Exception {
    ttlToXhtml(n, description, ttl, pageType, crumbTitle, null, rd, wg);
  }
  
  private void ttlToXhtml(String n, String description, String ttl, String pageType, String crumbTitle, ImplementationGuideDefn igd, ResourceDefn rd, WorkGroup wg) throws Exception {
    ttl = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml(description) + "</p>\r\n<pre class=\"turtle\">\r\n" + Utilities.escapeXml(ttl)+ "\r\n</pre>\r\n</div>\r\n";
    String html = TextFile.fileToString(page.getFolders().templateDir + "template-example-ttl.html").replace("<%example%>", ttl);
    html = page.processPageIncludes(n + ".ttl.html", html, pageType, null, null, null, crumbTitle, igd, rd, wg);
    TextFile.stringToFile(html, page.getFolders().dstDir + n + ".ttl.html");
    page.getHTMLChecker().registerExternal(n + ".ttl.html");
  }

  private void jsonToXhtml(String n, String description, String json, String pageType, String crumbTitle, ResourceDefn rd, WorkGroup wg) throws Exception {
    jsonToXhtml(n, description, json, pageType, crumbTitle, null, rd, wg);
  }
  
  private void jsonToXhtml(String n, String description, String json, String pageType, String crumbTitle, ImplementationGuideDefn igd, ResourceDefn rd, WorkGroup wg) throws Exception {
    json = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml(description) + "</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(json)+ "\r\n</pre>\r\n</div>\r\n";
    String html = TextFile.fileToString(page.getFolders().templateDir + "template-example-json.html").replace("<%example%>", json);
    html = page.processPageIncludes(n + ".json.html", html, pageType, null, null, null, crumbTitle, igd, rd, wg);
    TextFile.stringToFile(html, page.getFolders().dstDir + n + ".json.html");
    page.getHTMLChecker().registerExternal(n + ".json.html");
  }

  private void cloneToXhtml(String n, String description, boolean adorn, String pageType, String crumbTitle, ResourceDefn rd, WorkGroup wg) throws Exception {
    cloneToXhtml(n, description, adorn, pageType, crumbTitle, null, rd, wg);
  }
  private void cloneToXhtml(String n, String description, boolean adorn, String pageType, String crumbTitle, ImplementationGuideDefn igd, ResourceDefn rd, WorkGroup wg) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();

    Document xdoc = builder.parse(new CSFileInputStream(new CSFile(page.getFolders().dstDir + n + ".xml")));
    XhtmlGenerator xhtml = new XhtmlGenerator(new ExampleAdorner(page.getDefinitions(), page.genlevel(Utilities.charCount(n, File.separatorChar))));
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    xhtml.generate(xdoc, b, n.toUpperCase().substring(0, 1) + n.substring(1), description, 0, adorn, n + ".xml.html");
    String html = TextFile.fileToString(page.getFolders().templateDir + "template-example-xml.html").replace("<%example%>", b.toString());
    html = page.processPageIncludes(n + ".xml.html", html, pageType, null, n + ".xml.html", null, null, crumbTitle, (adorn && hasNarrative(xdoc)) ? new Boolean(true) : null, igd, rd, wg);
    TextFile.stringToFile(html, page.getFolders().dstDir + n + ".xml.html");

    //    page.getEpub().registerFile(n + ".xml.html", description, EPubManager.XHTML_TYPE);
    page.getHTMLChecker().registerExternal(n + ".xml.html");
  }

  private boolean hasNarrative(Document xdoc) {
    return XMLUtil.hasNamedChild(XMLUtil.getNamedChild(xdoc.getDocumentElement(), "text"), "div");
  }

  private void processQuestionnaire(ResourceDefn res, StructureDefinition profile, SectionTracker st, boolean isResource, String prefix, ImplementationGuideDefn ig) throws Exception {

    QuestionnaireBuilder qb = new QuestionnaireBuilder(page.getWorkerContext());
    qb.setProfile(profile);
    qb.build();
    Questionnaire q = qb.getQuestionnaire();

    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + prefix+ profile.getId().toLowerCase() + "-questionnaire.json");
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, q);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + prefix+ profile.getId().toLowerCase() + "-questionnaire.canonical.json");
    new JsonParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, q);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + prefix+ profile.getId().toLowerCase() + "-questionnaire.xml");
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, q);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + prefix+ profile.getId().toLowerCase() + "-questionnaire.canonical.xml");
    new XmlParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, q);
    s.close();

    String json = "<div class=\"example\">\r\n<p>Generated Questionnaire for "+profile.getId()+"</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(new JsonParser().setOutputStyle(OutputStyle.PRETTY).composeString(q)) + "\r\n</pre>\r\n</div>\r\n";
    String html = TextFile.fileToString(page.getFolders().templateDir + "template-example-json.html").replace("<%example%>", json);
    html = page.processPageIncludes(prefix+profile.getId().toLowerCase() + "-questionnaire.json.html", html, (isResource ? "resource-questionnaire:" : "profile-questionnaire:") + profile.getId(), null, null, null, "Questionnaire", ig, res, res == null ? wg("fhir") : res.getWg());
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix+ profile.getId().toLowerCase() + "-questionnaire.json.html");

    String xml = "<div class=\"example\">\r\n<p>Generated Questionnaire for "+profile.getId()+"</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(new XmlParser().setOutputStyle(OutputStyle.PRETTY).composeString(q)) + "\r\n</pre>\r\n</div>\r\n";
    html = TextFile.fileToString(page.getFolders().templateDir + "template-example-xml.html").replace("<%example%>", xml);
    html = page.processPageIncludes(prefix+profile.getId().toLowerCase() + "-questionnaire.xml.html", html, (isResource ? "resource-questionnaire:" : "profile-questionnaire:") + profile.getId(), null, null, null, "Questionnaire", ig, res, res == null ? wg("fhir") : res.getWg());
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix+ profile.getId().toLowerCase() + "-questionnaire.xml.html");

    if (false) {
      File tmpTransform = Utilities.createTempFile("tmp", ".html");
      //    if (web) {
      HashMap<String, String> params = new HashMap<String, String>();
      params.put("suppressWarnings", "true");
      XsltUtilities.saxonTransform(
          Utilities.path(page.getFolders().rootDir, "implementations", "xmltools"), // directory for xslt references
          page.getFolders().dstDir + prefix+ profile.getId().toLowerCase() + "-questionnaire.xml",  // source to run xslt on
          Utilities.path(page.getFolders().rootDir, "implementations", "xmltools", "QuestionnaireToHTML.xslt"), // xslt file to run
          tmpTransform.getAbsolutePath(), // file to produce
          this, // handle to self to implement URI resolver for terminology fetching
          params
          );
      //    } else
      //      TextFile.stringToFile("test", tmpTransform.getAbsolutePath());

      // now, generate the form
      html = TextFile.fileToString(page.getFolders().templateDir + (isResource ? "template-questionnaire.html" : "template-profile-questionnaire.html")).replace("<%questionnaire%>", loadHtmlForm(tmpTransform.getAbsolutePath()));
    } else
      html = "<html><p>Not generated in this build</p></html>";
    html = page.processPageIncludes(profile.getId().toLowerCase() + "-questionnaire.html", html, (isResource ? "resource-questionnaire:" : "profile-questionnaire:") + profile.getId(), null, profile, null, "Questionnaire", ig, res, res == null ? wg("fhir") : res.getWg());
    int level = (ig == null || ig.isCore()) ? 0 : 1;
    if (st != null)
      html = insertSectionNumbers(html, st, profile.getId().toLowerCase() + "-questionnaire.html", level, null);
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix+ profile.getId().toLowerCase() + "-questionnaire.html");

    page.getHTMLChecker().registerExternal(prefix+ profile.getId().toLowerCase() + "-questionnaire.html");
    page.getHTMLChecker().registerExternal(prefix+ profile.getId().toLowerCase() + "-questionnaire.json.html");
    page.getHTMLChecker().registerExternal(prefix+ profile.getId().toLowerCase() + "-questionnaire.xml.html");
  }

  private String loadHtmlForm(String path) throws Exception {
    String form = TextFile.fileToString(path);
    form = form.replace("h5>", "h6>").replace("h4>", "h6>").replace("h3>", "h5>").replace("h2>", "h4>").replace("h1>", "h3>");

    form = form.replace("<!--header insertion point-->", "\r\n");
    form = form.replace("<!--body top insertion point-->", "\r\n");
    form = form.replace("<!--body bottom insertion point-->", "\r\n");
    return form;
  }

  private Set<String> examplesProcessed = new HashSet<String>();

  private boolean validateBundles;

  
  private void processExample(Example e, ResourceDefn resn, StructureDefinition profile, Profile pack, ImplementationGuideDefn ig) throws Exception {
    if (e.getType() == ExampleType.Tool)
      return;
    long time = System.currentTimeMillis(); 
    int level = (ig == null || ig.isCore()) ? 0 : 1;
    String prefix = (ig == null || ig.isCore()) ? "" : ig.getCode() + File.separator;

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document xdoc;
    String narrative = null;
    String n = e.getTitle();

    if (examplesProcessed.contains(prefix+n))
      return;
    examplesProcessed.add(prefix+n);
    
    // strip the xsi: stuff. seems to need double processing in order to
    // delete namespace crap
    xdoc = e.getXml();
    XmlGenerator xmlgen = new XmlGenerator();
    CSFile file = new CSFile(page.getFolders().dstDir + prefix +n + ".xml");
    xmlgen.generate(xdoc.getDocumentElement(), file, "http://hl7.org/fhir", xdoc.getDocumentElement()
          .getLocalName());
    
    // check the narrative. We generate auto-narrative. If the resource didn't
    // have it's own original narrative, then we save it anyway
    // n
    String rt = null;
    try {
      RenderingContext lrc = page.getRc().copy().setLocalPrefix("").setTooCostlyNoteEmpty(PageProcessor.TOO_MANY_CODES_TEXT_EMPTY).setTooCostlyNoteNotEmpty(PageProcessor.TOO_MANY_CODES_TEXT_NOT_EMPTY);
      xdoc = loadDom(new FileInputStream(file), true);
      rt = xdoc.getDocumentElement().getNodeName();
      String id = XMLUtil.getNamedChildValue(xdoc.getDocumentElement(), "id");
      if (!page.getDefinitions().getBaseResources().containsKey(rt) && !id.equals(e.getId()))
        throw new Error("Resource in "+prefix +n + ".xml needs an id of value=\""+e.getId()+"\"");
      page.getDefinitions().addNs("http://hl7.org/fhir/"+rt+"/"+id, "Example", prefix +n + ".html");
      if (rt.equals("ValueSet") || rt.equals("CodeSystem") || rt.equals("ConceptMap") || rt.equals("CapabilityStatement") || rt.equals("Library")) {
        // for these, we use the reference implementation directly
        CanonicalResource res = (CanonicalResource) new XmlParser().parse(new FileInputStream(file));
        if (res.getUrl() != null && res.getUrl().startsWith("http://hl7.org/fhir"))
          res.setVersion(Constants.VERSION);
        boolean wantSave = false;
        if (res instanceof CapabilityStatement) {
          ((CapabilityStatement) res).setFhirVersion(page.getVersion());
          if (res.hasText() && res.getText().hasDiv())
            wantSave = updateVersion(((CapabilityStatement) res).getText().getDiv());
        }
        if (!res.hasText() || !res.getText().hasDiv()) {
          RendererFactory.factory(res, lrc).render(res);
          wantSave = true;
        }
        if (wantSave)
          new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(file), res);
        narrative = new XhtmlComposer(XhtmlComposer.HTML).compose(res.getText().getDiv());
      } else {
        if (rt.equals("Bundle")) {
          List<Element> entries = new ArrayList<Element>();
          XMLUtil.getNamedChildren(xdoc.getDocumentElement(), "entry", entries);
          boolean wantSave = false;
          for (Element entry : entries) {
            Element ers = XMLUtil.getFirstChild(XMLUtil.getNamedChild(entry, "resource"));
            id = XMLUtil.getNamedChildValue(ers, "id");
            if (id != null)
              page.getDefinitions().addNs("http://hl7.org/fhir/"+ers.getLocalName()+"/"+id, "Example", prefix +n + ".html", true);
            if (ers != null) {
              String ert = ers.getLocalName();
              String s = null;
              if (!page.getDefinitions().getBaseResources().containsKey(ert) && !ert.equals("Binary") && !ert.equals("Parameters") && !ert.equals("Bundle")) {
                ResourceRenderer r = RendererFactory.factory(ers.getLocalName(), lrc);
                ResourceWrapper rw = new DOMWrappers.ResourceWrapperElement(lrc, ers, page.getDefinitions().getResourceByName(ers.getLocalName()).getProfile());
                XhtmlNode div = rw.getNarrative();
                if (div == null || div.isEmpty()) {
                  wantSave = true;
                  r.render(rw);
                } else
                  s = new XhtmlComposer(true).compose(div);
                if (s != null)
                  narrative = narrative == null ? s : narrative +"<hr/>\r\n"+s;
              }
              if (ert.equals("NamingSystem")) {
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                new XmlGenerator().generate(ers, bs);
                bs.close();
                NamingSystem ns = (NamingSystem) new XmlParser().parse(new ByteArrayInputStream(bs.toByteArray()));
                if (!ns.hasUrl() || ns.getUrl().startsWith("http://hl7.org/fhir"))
                  ns.setVersion(Constants.VERSION);
                
                ns.setUserData("path", prefix +n+".html");
                page.getDefinitions().getNamingSystems().add(ns);
              }
            }
          }
          if (wantSave)
            new XmlGenerator().generate(xdoc.getDocumentElement(), file, "http://hl7.org/fhir", xdoc.getDocumentElement().getLocalName());
        } else {
          if (!page.getDefinitions().getBaseResources().containsKey(rt) && !rt.equals("Binary") && !rt.equals("Parameters")) {
            ResourceRenderer r = RendererFactory.factory(xdoc.getDocumentElement().getLocalName(), lrc);
            ResourceWrapper rw = new DOMWrappers.ResourceWrapperElement(lrc, xdoc.getDocumentElement(), page.getDefinitions().getResourceByName(xdoc.getDocumentElement().getLocalName()).getProfile());
            XhtmlNode div = rw.getNarrative();
            if (div == null || div.isEmpty()) {
              narrative = new XhtmlComposer(true).compose(r.render(rw));
              new XmlGenerator().generate(xdoc.getDocumentElement(), file, "http://hl7.org/fhir", xdoc.getDocumentElement().getLocalName());
            } else {
              narrative = new XhtmlComposer(true).compose(div);
            }
          }
        }
      }
    } catch (Exception ex) {
      StringWriter errors = new StringWriter();
      ex.printStackTrace();
      XhtmlNode xhtml = new XhtmlNode(NodeType.Element, "div");
      xhtml.addTag("p").setAttribute("style", "color: maroon").addText("Error processing narrative: " + ex.getMessage());
      xhtml.addTag("p").setAttribute("style", "color: maroon").addText(errors.toString());
      narrative = new XhtmlComposer(XhtmlComposer.HTML).compose(xhtml);
    }

    if (rt.equals("ValueSet")) {
      ValueSet vs = (ValueSet) new XmlParser().parse(new FileInputStream(file));
      vs.setUserData("filename", Utilities.changeFileExt(file.getName(), ""));
      vs.addExtension().setUrl(ToolingExtensions.EXT_WORKGROUP).setValue(new CodeType("fhir"));
      if (vs.getUrl().startsWith("http://hl7.org/fhir"))
        vs.setVersion(Constants.VERSION);

      page.getVsValidator().validate(page.getValidationErrors(), "Value set Example "+prefix +n, vs, false, false);
      if (vs.getUrl() == null)
        throw new Exception("Value set example " + e.getTitle() + " has no url");
      vs.setUserData("path", prefix +n + ".html");
      if (vs.getUrl().startsWith("http:"))
        page.getValueSets().see(vs, page.packageInfo());
      addToResourceFeed(vs, valueSetsFeed, file.getName());
      page.getDefinitions().getValuesets().see(vs, page.packageInfo());
    } else if (rt.equals("CodeSystem")) {
      CodeSystem cs = (CodeSystem) new XmlParser().parse(new FileInputStream(file));
      if (cs.getUrl().startsWith("http://hl7.org/fhir"))
        cs.setVersion(Constants.VERSION);
      cs.setUserData("example", "true");
      cs.setUserData("filename", Utilities.changeFileExt(file.getName(), ""));
      cs.addExtension().setUrl(ToolingExtensions.EXT_WORKGROUP).setValue(new CodeType("fhir"));
      cs.setUserData("path", prefix +n + ".html");
      addToResourceFeed(cs, valueSetsFeed, file.getName());
      page.getCodeSystems().see(cs, page.packageInfo());
    } else if (rt.equals("ConceptMap")) {
      ConceptMap cm = (ConceptMap) new XmlParser().parse(new FileInputStream(file));
      new ConceptMapValidator(page.getDefinitions(), e.getTitle()).validate(cm, false);
      if (cm.getUrl() == null)
        throw new Exception("Value set example " + e.getTitle() + " has no identifier");
      if (cm.getUrl().startsWith("http://hl7.org/fhir"))
        cm.setVersion(Constants.VERSION);
      addToResourceFeed(cm, conceptMapsFeed, file.getName());
      page.getDefinitions().getConceptMaps().see(cm, page.packageInfo());
      cm.setUserData("path", prefix +n + ".html");
      page.getConceptMaps().see(cm, page.packageInfo());
    } else if (rt.equals("Library")) {
      try {
        Library lib = (Library) new XmlParser().parse(new FileInputStream(file));
        if (lib.hasUrl() && lib.getUrl().startsWith("http://hl7.org/fhir"))
          lib.setVersion(Constants.VERSION);
        lib.setUserData("example", "true");
        lib.setUserData("filename", Utilities.changeFileExt(file.getName(), ""));
        lib.setUserData("path", prefix +n + ".html");
        page.getWorkerContext().cacheResource(lib);
      } catch (Exception ex) {
        System.out.println("Internal exception processing Library "+file.getName()+": "+ex.getMessage()+". Does the libary code need regenerating?");
        ex.printStackTrace();
      }
    }

    // queue for json and canonical XML generation processing
    e.setResourceName(resn.getName());
    String canonical = "http://hl7.org/fhir/";
    
    org.hl7.fhir.r5.elementmodel.Element ex = Manager.parse(page.getWorkerContext(), new CSFileInputStream(page.getFolders().dstDir + prefix+n + ".xml"), FhirFormat.XML);
    new DefinitionsUsageTracker(page.getDefinitions()).updateUsage(ex);
    Manager.compose(page.getWorkerContext(), ex, new FileOutputStream(page.getFolders().dstDir + prefix+n + ".json"), FhirFormat.JSON, OutputStyle.PRETTY, canonical); 
//    Manager.compose(page.getWorkerContext(), ex, new FileOutputStream(Utilities.changeFileExt(destName, ".canonical.json")), FhirFormat.JSON, OutputStyle.CANONICAL); 
//    Manager.compose(page.getWorkerContext(), ex, new FileOutputStream(Utilities.changeFileExt(destName, ".canonical.xml")), FhirFormat.XML, OutputStyle.CANONICAL); 
    Manager.compose(page.getWorkerContext(), ex, new FileOutputStream(page.getFolders().dstDir + prefix+n + ".ttl"), FhirFormat.TURTLE, OutputStyle.PRETTY, resn.getName().equals("Parameters") || resn.getName().equals("OperationOutcome")  ? null : canonical); 
    
    String json = TextFile.fileToString(page.getFolders().dstDir + prefix+n + ".json");
    //        String json2 = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml(e.getDescription()) + "</p>\r\n<p><a href=\""+ n + ".json\">Raw JSON</a> (<a href=\""+n + ".canonical.json\">Canonical</a>)</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(json)
    //            + "\r\n</pre>\r\n</div>\r\n";
    json = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml(e.getDescription()) + "</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(json)
    + "\r\n</pre>\r\n</div>\r\n";
    String html = TextFile.fileToString(page.getFolders().templateDir + "template-example-json.html").replace("<%example%>", json);
    html = page.processPageIncludes(n + ".json.html", html, e.getResourceName() == null ? "profile-instance:resource:" + e.getResourceName() : "resource-instance:" + e.getResourceName(), null, null, null, "Example", null, resn, resn.getWg());
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix+n + ".json.html");

    page.getHTMLChecker().registerExternal(prefix+n + ".json.html");

    String ttl = TextFile.fileToString(page.getFolders().dstDir + prefix+n + ".ttl");
    ttl = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml(e.getDescription()) + "</p>\r\n<pre class=\"rdf\">\r\n" + Utilities.escapeXml(ttl)
    + "\r\n</pre>\r\n</div>\r\n";
    html = TextFile.fileToString(page.getFolders().templateDir + "template-example-ttl.html").replace("<%example%>", ttl);
    html = page.processPageIncludes(n + ".ttl.html", html, e.getResourceName() == null ? "profile-instance:resource:" + e.getResourceName() : "resource-instance:" + e.getResourceName(), null, null, null, "Example", null, resn, resn.getWg());
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix+n + ".ttl.html");

    page.getHTMLChecker().registerExternal(prefix+n + ".ttl.html");

    // reload it now, xml to xhtml of xml
    builder = factory.newDocumentBuilder();
    xdoc = builder.parse(new CSFileInputStream(file));
    XhtmlGenerator xhtml = new XhtmlGenerator(new ExampleAdorner(page.getDefinitions(), page.genlevel(level)));
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    xhtml.generate(xdoc, b, n.toUpperCase().substring(0, 1) + n.substring(1), Utilities.noString(e.getId()) ? e.getDescription() : e.getDescription()
        + " (id = \"" + e.getId() + "\")", 0, true, n + ".xml.html");
    html = TextFile.fileToString(page.getFolders().templateDir + "template-example-xml.html").replace("<%example%>", b.toString());
    html = page.processPageIncludes(n + ".xml.html", html, resn == null ? "profile-instance:resource:" + rt : "resource-instance:" + resn.getName(), null, n + ".xml.html", profile, null, "Example", (hasNarrative(xdoc)) ? new Boolean(true) : null, ig, resn, resn.getWg());
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix +n + ".xml.html");
    XhtmlDocument d = new XhtmlParser().parse(new CSFileInputStream(page.getFolders().dstDir + prefix +n + ".xml.html"), "html");
    XhtmlNode pre = d.getElement("html").getElement("body").getElement("div");
    e.setXhtm(b.toString());
    
    Element root = xdoc.getDocumentElement();
    Element meta = XMLUtil.getNamedChild(root, "meta");
    if (meta == null) {
      Element id = XMLUtil.getNamedChild(root, "id");
      if (id == null)
        meta = XMLUtil.insertChild(xdoc, root, "meta", FormatUtilities.FHIR_NS, 2);
      else {
        Element pid = XMLUtil.getNextSibling(id);
        if (pid == null)
          throw new Exception("not handled - id is last child in "+n);
        else
          meta = XMLUtil.insertChild(xdoc, root, "meta", FormatUtilities.FHIR_NS, pid, 2);
      }
    }
    Element tag = XMLUtil.getNamedChild(meta, "tag");
    Element label = XMLUtil.insertChild(xdoc, meta, "security", FormatUtilities.FHIR_NS, tag, 4);
    XMLUtil.addTextTag(xdoc, label, "system", FormatUtilities.FHIR_NS, "http://terminology.hl7.org/CodeSystem/v3-ActReason", 6);
    XMLUtil.addTextTag(xdoc, label, "code", FormatUtilities.FHIR_NS, "HTEST", 6);
    XMLUtil.addTextTag(xdoc, label, "display", FormatUtilities.FHIR_NS, "test health data", 6); 
    XMLUtil.spacer(xdoc, label, 4); 
    XMLUtil.spacer(xdoc, meta, 2); 
    
    String destf = (!Utilities.noString(e.getId())) ?  page.getFolders().dstDir + "examples" + File.separator + n + "(" + e.getId() + ").xml" : page.getFolders().dstDir + "examples" + File.separator + n + ".xml";
    FileOutputStream fs = new FileOutputStream(destf);
    XMLUtil.saveToFile(root, fs); 
    fs.close();
    
    // now, we create an html page from the narrative
    narrative = fixExampleReferences(e.getTitle(), narrative);
    html = TextFile.fileToString(page.getFolders().templateDir + "template-example.html").replace("<%example%>", narrative == null ? "" : narrative).replace("<%example-usage%>", genExampleUsage(e, page.genlevel(level)));
    html = page.processPageIncludes(n + ".html", html, resn == null ? "profile-instance:resource:" + rt : "resource-instance:" + resn.getName(), null, profile, null, "Example", ig, resn, resn.getWg());
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix +n + ".html");
    // head =
    // "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">\r\n<head>\r\n <title>"+Utilities.escapeXml(e.getDescription())+"</title>\r\n <link rel=\"Stylesheet\" href=\"fhir.css\" type=\"text/css\" media=\"screen\"/>\r\n"+
    // "</head>\r\n<body>\r\n<p>&nbsp;</p>\r\n<p>"+Utilities.escapeXml(e.getDescription())+"</p>\r\n"+
    // "<p><a href=\""+n+".xml.html\">XML</a> <a href=\""+n+".json.html\">JSON</a></p>\r\n";
    // tail = "\r\n</body>\r\n</html>\r\n";
    // TextFile.stringToFile(head+narrative+tail, page.getFolders().dstDir + n +
    // ".html");
    page.getHTMLChecker().registerExternal(prefix +n + ".html");
    page.getHTMLChecker().registerExternal(prefix +n + ".xml.html");
  }

  private String fixExampleReferences(String path, String narrative) throws Exception {
    if (narrative == null)
      return "";
    XhtmlNode node = new XhtmlParser().parseFragment(narrative);
    checkExampleLinks(path, node);
    return new XhtmlComposer(XhtmlComposer.HTML).compose(node);
  }

  private void checkExampleLinks(String path, XhtmlNode node) throws Exception {
    if (node.getNodeType() == NodeType.Element) {
      if (node.getName().equals("a") && node.hasAttribute("href")) {
        String link = node.getAttribute("href");
        if (!link.startsWith("http:") && !link.startsWith("https:") && !link.startsWith("mailto:") && !link.startsWith("tel:") && !link.contains(".html") &&!link.startsWith("#")) {
          String[] parts = link.split("\\/");
          if ((parts.length == 2) || (parts.length == 4 && parts[2].equals("_history")) && page.getDefinitions().hasResource(parts[0])) {

            node.setAttribute("href", determineLink(path, parts[0], parts[1]));
          } else if (page.getDefinitions().hasType(link)) { 
            node.setAttribute("href", page.getDefinitions().getSrcFile(link)+".html#"+link);
         } else if (page.getDefinitions().hasResource(link)) 
          node.setAttribute("href", link.toLowerCase()+".html#"+link);
          else
            throw new Exception("Unknown example narrative href pattern: "+link);
        }
      } else
        for (XhtmlNode n : node.getChildNodes()) {
          checkExampleLinks(path, n);
      }
    }
  }

  private String determineLink(String path, String rn, String id) throws Exception {
    ResourceDefn r = page.getDefinitions().getResourceByName(rn);
    Example e = r.getExampleById(id);
    if (e == null)
      for (ImplementationGuideDefn ig : page.getDefinitions().getIgs().values()) {
        e = ig.getExample(rn, id);
        if (e != null)
          break;
      }
    if (e == null) {
      page.getValidationErrors().add(new ValidationMessage(Source.Publisher, IssueType.NOTFOUND, path, "The reference to "+rn+"/"+id+" could not be resolved", IssueSeverity.WARNING));
      return "#null";
    } else
      return e.getTitle()+".html";
  }

  private boolean updateVersion(XhtmlNode div) {
    if (div.getNodeType().equals(NodeType.Text)) {
      if (div.getContent().contains("$ver$")) {
        div.setContent(div.getContent().replace("$ver$", page.getVersion().toCode()));
        return true;
      } else
        return false;
    } else {
      boolean res = false;
      for (XhtmlNode child : div.getChildNodes())
        res = updateVersion(child) || res;
      return res;
    }
  }

  private String genExampleUsage(Example e, String prefix) {
    if (e.getInbounds().isEmpty())
      return "";
    else {
      StringBuilder b = new StringBuilder();
      b.append("<p>\r\nOther examples that reference this example:</p>\r\n");
      List<String> names = new ArrayList<String>();
      for (Example x : e.getInbounds())
        names.add(x.getResourceName()+":"+x.getId());
      Collections.sort(names);
      for (String n : names) {
        Example x  = null;
        for (Example y : e.getInbounds())
          if (n.equals(y.getResourceName()+":"+y.getId()))
            x = y;
        b.append("<li><a href=\"");
        b.append(prefix);
        if (x.getIg() != null) {
          ImplementationGuideDefn ig = page.getDefinitions().getIgs().get(x.getIg());
          if (ig != null && !ig.isCore()) {
             b.append(ig.getCode());
             b.append("/");
          }
        }
        b.append(x.getTitle()+".html");
        b.append("\">");
        b.append(x.getResourceName()+"/"+x.getName());
        b.append("</a></li>\r\n");
      }
      b.append("</ul>\r\n");
      return b.toString();
    }
  }


  private String buildLoincExample(String filename) throws FileNotFoundException, Exception {
    LoincToDEConvertor conv = new LoincToDEConvertor();
    conv.setDefinitions(Utilities.path(page.getFolders().srcDir, "loinc", "loincS.xml"));
    conv.process();
    IParser xml = new XmlParser().setOutputStyle(OutputStyle.PRETTY);
    FileOutputStream s = new FileOutputStream(Utilities.path(page.getFolders().dstDir, filename+".xml"));
    xml.compose(s, conv.getBundle());
    s.close();
    IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
    s = new FileOutputStream(Utilities.path(page.getFolders().dstDir, filename+".json"));
    json.compose(s, conv.getBundle());
    s.close();
    return "Loinc Narrative";
  }

  private StructureDefinition generateProfile(ResourceDefn root, String n, String xmlSpec, String jsonSpec, String ttlSpec, boolean gen) throws Exception, FileNotFoundException {
    StructureDefinition rp = root.getProfile();
    page.getProfiles().see(rp, page.packageInfo());
    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + n + ".profile.xml");
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, rp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + n + ".profile.canonical.xml");
    new XmlParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, rp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + n + ".profile.json");
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, rp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + n + ".profile.canonical.json");
    new JsonParser().setOutputStyle(OutputStyle.CANONICAL).compose(s, rp);
    s.close();

    Utilities.copyFile(new CSFile(page.getFolders().dstDir + n + ".profile.xml"), new CSFile(page.getFolders().dstDir + "examples" + File.separator + n
        + ".profile.xml"));
    if (buildFlags.get("all")) {
      addToResourceFeed(rp, page.getResourceBundle(), null);
    }
    if (gen) {
      saveAsPureHtml(rp, new FileOutputStream(page.getFolders().dstDir + "html" + File.separator + n + ".html"));
      cloneToXhtml(n + ".profile", "StructureDefinition for " + n, true, "profile-instance:resource:" + root.getName(), "Profile", root, root.getWg());
      jsonToXhtml(n + ".profile", "StructureDefinition for " + n, resource2Json(rp), "profile-instance:resource:" + root.getName(), "Profile", root, root.getWg());
      ttlToXhtml(n + ".profile", "StructureDefinition for " + n, resource2Ttl(rp), "profile-instance:resource:" + root.getName(), "Profile", root, root.getWg());
      String shex = new ShExGenerator(page.getWorkerContext()).generate(HTMLLinkPolicy.NONE, rp);
      TextFile.stringToFile(shex, page.getFolders().dstDir + n+".shex");
      shexToXhtml(n, "ShEx statement for " + n, shex, "profile-instance:type:" + root.getName(), "Type", root, root.getWg());
    }
    return rp;
  }

  private void deletefromFeed(ResourceType type, String id, Bundle feed) {
    int index = -1;
    for (BundleEntryComponent ae : feed.getEntry()) {
      if (ae.getResource().getId().equals(id) && ae.getResource().getResourceType() == type)
        index = feed.getEntry().indexOf(ae);
    }
    if (index > -1)
      feed.getEntry().remove(index);
  }

  private void saveAsPureHtml(DomainResource resource, FileOutputStream stream) throws Exception {
    saveAsPureHtml(resource, stream, false); 
  }
  private void saveAsPureHtml(DomainResource resource, FileOutputStream stream, boolean isPretty) throws Exception {
    XhtmlDocument html = new XhtmlDocument();
    html.setNodeType(NodeType.Document);
    html.addComment("Generated by automatically by FHIR Tooling");
    XhtmlNode doc = html.addTag("html");
    XhtmlNode head = doc.addTag("head");
    XhtmlNode work = head.addTag("title");
    work.addText("test title");
    work = head.addTag("link");
    work.setAttribute("rel", "Stylesheet");
    work.setAttribute("href", "/css/fhir.css");
    work.setAttribute("type", "text/css");
    work.setAttribute("media", "screen");
    work = doc.addTag("body");
    if ((resource.hasText()) && (resource.getText().hasDiv())) {
      work.getAttributes().putAll(resource.getText().getDiv().getAttributes());
      work.getChildNodes().addAll(resource.getText().getDiv().getChildNodes());
    }
    XhtmlComposer xml = new XhtmlComposer(XhtmlComposer.HTML, isPretty);
    xml.compose(stream, html);
    stream.close();
  }

  private void addToResourceFeed(DomainResource resource, Bundle dest, String filename) throws Exception {
    maybeFixResourceId(resource, filename);
    if (resource.getId() == null)
      throw new Exception("Resource has no id");
    BundleEntryComponent byId = ResourceUtilities.getEntryById(dest, resource.getResourceType(), resource.getId());
    if (byId != null)
      dest.getEntry().remove(byId);
    deletefromFeed(resource.getResourceType(), resource.getId(), dest);

    ResourceUtilities.meta(resource).setLastUpdated(page.getGenDate().getTime());
    if (resource.getText() == null || resource.getText().getDiv() == null)
      throw new Exception("Example Resource " + resource.getId() + " does not have any narrative");
    dest.getEntry().add(new BundleEntryComponent().setResource(resource).setFullUrl("http://hl7.org/fhir/"+resource.getResourceType().toString()+"/"+resource.getId()));
  }

  private void maybeFixResourceId(DomainResource theResource, String theFilename) {
    if (theResource.getId() == null && theFilename != null) {
      String candidateId = theFilename.replaceAll("\\..*", "");
      candidateId = FormatUtilities.makeId(candidateId);
      theResource.setId(candidateId);
    }
  }

  private void addToResourceFeed(ValueSet vs, Bundle dest, String filename) throws Exception {
    maybeFixResourceId(vs, filename);
    if (vs.getId() == null)
      throw new Exception("Resource has no id: "+vs.getName()+" ("+vs.getUrl()+")");
    if (ResourceUtilities.getById(dest, ResourceType.ValueSet, vs.getId()) != null)
      throw new Exception("Attempt to add duplicate value set " + vs.getId()+" ("+vs.getName()+")");
    if (!vs.hasText() || vs.getText().getDiv() == null)
      throw new Exception("Example Value Set " + vs.getId() + " does not have any narrative");

    ResourceUtilities.meta(vs).setLastUpdated(page.getGenDate().getTime());
    if (vs.getUrl().startsWith("http://hl7.org/fhir/") && !vs.getUrl().equals("http://hl7.org/fhir/"+vs.getResourceType().toString()+"/"+vs.getId()))
      throw new Exception("URL mismatch on value set: "+vs.getUrl()+" vs "+"http://hl7.org/fhir/"+vs.getResourceType().toString()+"/"+vs.getId());
    dest.getEntry().add(new BundleEntryComponent().setResource(vs).setFullUrl("http://hl7.org/fhir/"+vs.fhirType()+"/"+vs.getId()));
  }

  private void addToResourceFeed(ConceptMap cm, Bundle dest) throws Exception {
    if (cm.getId() == null)
      throw new Exception("Resource has no id");
    if (ResourceUtilities.getById(dest, ResourceType.ValueSet, cm.getId()) != null)
      throw new Exception("Attempt to add duplicate Concept Map " + cm.getId());
    if (cm.getText() == null || cm.getText().getDiv() == null)
      throw new Exception("Example Concept Map " + cm.getId() + " does not have any narrative");

    ResourceUtilities.meta(cm).setLastUpdated(page.getGenDate().getTime());
    if (!cm.getUrl().equals("http://hl7.org/fhir/"+cm.getResourceType().toString()+"/"+cm.getId()))
      throw new Exception("URL mismatch on concept map");
    dest.getEntry().add(new BundleEntryComponent().setResource(cm).setFullUrl("http://hl7.org/fhir/"+cm.fhirType()+"/"+cm.getId()));
  }

  private void addToResourceFeed(CompartmentDefinition cd, Bundle dest) throws Exception {
    if (cd.getId() == null)
      throw new Exception("Resource has no id");
    if (ResourceUtilities.getById(dest, ResourceType.CompartmentDefinition, cd.getId()) != null)
      throw new Exception("Attempt to add duplicate Compartment Definition " + cd.getId());
    if (cd.getText() == null || cd.getText().getDiv() == null)
      throw new Exception("Example Compartment Definition " + cd.getId() + " does not have any narrative");

    ResourceUtilities.meta(cd).setLastUpdated(page.getGenDate().getTime());
    if (!cd.getUrl().equals("http://hl7.org/fhir/"+cd.getResourceType().toString()+"/"+cd.getId()))
      throw new Exception("URL mismatch on concept map");
    dest.getEntry().add(new BundleEntryComponent().setResource(cd).setFullUrl("http://hl7.org/fhir/"+cd.fhirType()+"/"+cd.getId()));
  }

  private void addToResourceFeed(CapabilityStatement cs, Bundle dest) throws Exception {
    if (cs.getId() == null)
      throw new Exception("Resource has no id");
    if (ResourceUtilities.getById(dest, ResourceType.ValueSet, cs.getId()) != null)
      throw new Exception("Attempt to add duplicate Conformance " + cs.getId());
    if (!cs.hasText() || cs.getText().getDiv() == null)
      System.out.println("WARNING: Example CapabilityStatement " + cs.getId() + " does not have any narrative");
      // Changed this from an exception to a warning because generateConformanceStatement doesn't produce narrative if
      // "register" is 'false'

    ResourceUtilities.meta(cs).setLastUpdated(page.getGenDate().getTime());
    if (!cs.getUrl().equals("http://hl7.org/fhir/"+cs.getResourceType().toString()+"/"+cs.getId()))
      throw new Exception("URL mismatch on CapabilityStatement");
    dest.getEntry().add(new BundleEntryComponent().setResource(cs).setFullUrl("http://hl7.org/fhir/"+cs.fhirType()+"/"+cs.getId()));
  }

  private void produceConformancePackage(ResourceDefn res, Profile pack, SectionTracker st) throws Exception {
    String resourceName = res == null ? "" : res.getName();
    if (Utilities.noString(resourceName)) {
      if (pack.getProfiles().size() == 1)
        if (pack.getProfiles().get(0).getDefn() != null)
          resourceName = pack.getProfiles().get(0).getDefn().getName();
        else 
          resourceName = pack.getProfiles().get(0).getResource().getType();
      else if (pack.getProfiles().size() == 0) {
       // throw new Exception("Unable to determine resource name - no profiles"); no, we don't complain
      } else if (pack.getProfiles().get(0).getDefn() != null) {
        resourceName = pack.getProfiles().get(0).getDefn().getName();
        for (int i = 1; i < pack.getProfiles().size(); i++)
          if (!pack.getProfiles().get(i).getDefn().getName().equals(resourceName))
            throw new Exception("Unable to determine resource name - profile mismatch "+resourceName+"/"+pack.getProfiles().get(i).getDefn().getName());
      }
    }
    ImplementationGuideDefn ig = page.getDefinitions().getIgs().get(pack.getCategory());
    String prefix = (ig == null || ig.isCore()) ? "" : ig.getCode()+File.separator;

    String intro = pack.getIntroduction() != null ? page.loadXmlNotesFromFile(pack.getIntroduction(), false, null, null, null, null, res == null ? wg("fhir") : res.getWg()) : null;
    String notes = pack.getNotes() != null ? page.loadXmlNotesFromFile(pack.getNotes(), false, null, null, null, null, res == null ? wg("fhir") : res.getWg()) : null;

    if (!("profile".equals(pack.metadata("navigation")) && pack.getProfiles().size() == 1)) {
      String src = TextFile.fileToString(page.getFolders().templateDir + "template-conformance-pack.html");
      src = page.processConformancePackageIncludes(pack, src, intro, notes, resourceName, ig);
      if (st != null)
        src = insertSectionNumbers(src, st, pack.getId().toLowerCase() + ".html",  0, null);
      else if (ig != null && !ig.isCore())
        src = addSectionNumbers(pack.getId() + ".html", pack.getId(), src, null, 1, null, ig);

      page.getHTMLChecker().registerFile(prefix+pack.getId().toLowerCase() + ".html", "Profile " + pack.getId(), HTMLLinkChecker.XHTML_TYPE, true);
      TextFile.stringToFile(src, page.getFolders().dstDir + prefix+pack.getId() + ".html");
    }

    // now, we produce each profile
    for (ConstraintStructure profile : pack.getProfiles())
      produceProfile(res, pack, profile, st, intro, notes, prefix, ig);

    for (SearchParameter sp : pack.getSearchParameters())
      producePackSearchParameter(res, pack, sp, st, ig);

    for (Example ex : pack.getExamples()) {
      StructureDefinition sd  = null;
      boolean ambiguous = false;
      for (ConstraintStructure sdt : pack.getProfiles()) {
        if (sdt.getResource().getSnapshot().getElement().get(0).getPath().equals(resourceName))
          if (sd == null)
            sd = sdt.getResource();
          else
            ambiguous = true;
      }
      if (ambiguous)
        processExample(ex, res, null, null, ig);
      else
        processExample(ex, res, sd, pack, ig);
    }
    // create examples here
//    if (examples != null) {
//      for (String en : examples.keySet()) {
//        processExample(examples.get(en), null, profile.getSource());
  }

  private void producePackSearchParameter(ResourceDefn res, Profile pack, SearchParameter sp, SectionTracker st, ImplementationGuideDefn ig) throws Exception {
    String title = sp.getId();
    sp.setUserData("pack", pack.getId());

    String prefix = (ig == null || ig.isCore()) ? "" : ig.getCode()+File.separator;
    int level = (ig == null || ig.isCore()) ? 0 : 1;

    XmlParser comp = new XmlParser();
    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + prefix+title + ".xml");
    comp.setOutputStyle(OutputStyle.PRETTY).compose(s, sp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + prefix+title + ".canonical.xml");
    comp.setOutputStyle(OutputStyle.CANONICAL).compose(s, sp);
    s.close();
    JsonParser jcomp = new JsonParser();
    s = new FileOutputStream(page.getFolders().dstDir + prefix+title + ".json");
    jcomp.setOutputStyle(OutputStyle.PRETTY).compose(s, sp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + prefix+title + ".canonical.json");
    jcomp.setOutputStyle(OutputStyle.CANONICAL).compose(s, sp);
    s.close();

    String src = TextFile.fileToString(page.getFolders().templateDir + "template-search-parameter.html");
    src = page.processPageIncludes(sp.getId()+".html", src, "search-parameter:"+(res == null ? "na" : res.getName())+"/"+pack.getId()+"/"+sp.getId(), null, sp, null, "Search Parameter", ig, res, res == null ? wg("fhir"): res.getWg());
    if (st != null)
      src = insertSectionNumbers(src, st, title + ".html", level, null);
    page.getHTMLChecker().registerFile(prefix+title + ".html", "SearchParameter " + sp.getName(), HTMLLinkChecker.XHTML_TYPE, true);
    TextFile.stringToFile(src, page.getFolders().dstDir + prefix+title + ".html");
    cloneToXhtml(prefix+title, "Search Parameter "+sp.getName(), false, "searchparam-instance", "Search Parameter", res, res == null ? wg("fhir") : res.getWg());

    String json = resource2Json(sp);
    json = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml("SearchParameter " + sp.getName()) + "</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(json)+ "\r\n</pre>\r\n</div>\r\n";
    String html = TextFile.fileToString(page.getFolders().templateDir + "template-example-json.html").replace("<%example%>", json);
    html = page.processPageIncludes(title + ".json.html", html, "search-parameter:"+(res == null ? "wg" : res.getName())+"/"+pack.getId()+"/"+sp.getId(), null, sp, null, "Search Parameter", ig, res, res == null ? wg("fhir"): res.getWg());
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix+title + ".json.html");
    page.getHTMLChecker().registerExternal(prefix+title + ".json.html");
  }

  private void produceProfile(ResourceDefn resource, Profile pack, ConstraintStructure profile, SectionTracker st, String intro, String notes, String prefix, ImplementationGuideDefn ig) throws Exception {
    File tmp = Utilities.createTempFile("tmp", ".tmp");
    String title = profile.getId();
    int level = (ig == null || ig.isCore()) ? 0 : 1;

    // you have to validate a profile, because it has to be merged with it's
    // base resource to fill out all the missing bits
    //    validateProfile(profile);
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    XmlSpecGenerator gen = new XmlSpecGenerator(bs, title + "-definitions.html", "", page, ig.isCore() ? "" : "../");
    gen.generate(profile.getResource());
    gen.close();
    String xml = new String(bs.toByteArray());

    bs = new ByteArrayOutputStream();
    JsonSpecGenerator genJ = new JsonSpecGenerator(bs, title + "-definitions.html", "", page, ig.isCore() ? "" : "../");
    genJ.generate(profile.getResource());
    genJ.close();
    String json = new String(bs.toByteArray());

    XmlParser comp = new XmlParser();
    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + prefix +title + ".profile.xml");
    comp.setOutputStyle(OutputStyle.PRETTY).compose(s, profile.getResource());
    s.close();
    Utilities.copyFile(new CSFile(page.getFolders().dstDir + prefix +title + ".profile.xml"), new CSFile(page.getFolders().dstDir + "examples" + File.separator + title+ ".profile.xml"));
    JsonParser jcomp = new JsonParser();
    s = new FileOutputStream(page.getFolders().dstDir + prefix +title + ".profile.json");
    jcomp.setOutputStyle(OutputStyle.PRETTY).compose(s, profile.getResource());
    s.close();
//    String shex = new ShExGenerator(page.getWorkerContext()).generate(HTMLLinkPolicy.NONE, profile.getResource());
//    TextFile.stringToFile(shex, Utilities.changeFileExt(page.getFolders().dstDir + prefix +title + ".profile.shex", ".shex"));
//    shexToXhtml(prefix +title + ".profile", "ShEx statement for " + prefix +title, shex, "profile-instance:type:" + title, "Type");

    TerminologyNotesGenerator tgen = new TerminologyNotesGenerator(new FileOutputStream(tmp), page);
    tgen.generate(level == 0 ? "" : "../", profile);
    tgen.close();
    String tx = TextFile.fileToString(tmp.getAbsolutePath());

    String src = TextFile.fileToString(page.getFolders().templateDir + "template-profile.html");
    src = page.processProfileIncludes(profile.getId(), profile.getId(), pack, profile, xml, json, tx, src, title + ".html", (resource == null ? profile.getResource().getType() : resource.getName())+"/"+pack.getId()+"/"+profile.getId(), intro, notes, ig, false, false);
    if (st != null)
      src = insertSectionNumbers(src, st, title + ".html", level, null);
    else if (ig != null && !ig.isCore()) {
      src = addSectionNumbers(title + ".html", title, src, null, 1, null, ig);
      st = page.getSectionTrackerCache().get(ig.getCode()+"::"+title);
    }

    page.getHTMLChecker().registerFile(prefix +title + ".html", "StructureDefinition " + profile.getResource().getName(), HTMLLinkChecker.XHTML_TYPE, false);
    TextFile.stringToFile(src, page.getFolders().dstDir + prefix +title + ".html");
    new ProfileUtilities(page.getWorkerContext(), page.getValidationErrors(), page).generateSchematrons(new FileOutputStream(page.getFolders().dstDir + prefix +title + ".sch"), profile.getResource());

    if (pack.getExamples().size() > 0) {
      src = TextFile.fileToString(page.getFolders().templateDir + "template-profile-examples.html");
      src = page.processProfileIncludes(profile.getId(), profile.getId(), pack, profile, xml, json, tx, src, title + ".html", (resource == null ? profile.getResource().getType() : resource.getName())+"/"+pack.getId()+"/"+profile.getId(), intro, notes, ig, false, false);
      page.getHTMLChecker().registerFile(prefix+title + "-examples.html", "Examples for StructureDefinition " + profile.getResource().getName(), HTMLLinkChecker.XHTML_TYPE, true);
      TextFile.stringToFile(src, page.getFolders().dstDir + prefix +title + "-examples.html");
    }
    src = TextFile.fileToString(page.getFolders().templateDir + "template-profile-definitions.html");
    src = page.processProfileIncludes(profile.getId(), profile.getId(), pack, profile, xml, json, tx, src, title + ".html", (resource == null ? profile.getResource().getType() : resource.getName())+"/"+pack.getId()+"/"+profile.getId(), intro, notes, ig, false, false);
    if (st != null)
      src = insertSectionNumbers(src, st, title + "-definitions.html", level, null);
    page.getHTMLChecker().registerFile(prefix +title + "-definitions.html", "Definitions for StructureDefinition " + profile.getResource().getName(), HTMLLinkChecker.XHTML_TYPE, true);
    TextFile.stringToFile(src, page.getFolders().dstDir + prefix +title + "-definitions.html");

    src = TextFile.fileToString(page.getFolders().templateDir + "template-profile-mappings.html");
    src = page.processProfileIncludes(profile.getId(), profile.getId(), pack, profile, xml, json, tx, src, title + ".html", (resource == null ? profile.getResource().getType() : resource.getName())+"/"+pack.getId()+"/"+profile.getId(), intro, notes, ig, false, false);
    if (st != null)
      src = insertSectionNumbers(src, st, title + "-mappings.html", level, null);
    page.getHTMLChecker().registerFile(prefix +title + "-mappings.html", "Mappings for StructureDefinition " + profile.getResource().getName(), HTMLLinkChecker.XHTML_TYPE, true);
    TextFile.stringToFile(src, page.getFolders().dstDir + prefix +title + "-mappings.html");

    try {
      processQuestionnaire(resource, profile.getResource(), st, false, prefix, ig);
    } catch (Exception e) {
      e.printStackTrace();
      page.log("Questionnaire Generation Failed: "+e.getMessage(), LogMessageType.Error);
    }
    new ReviewSpreadsheetGenerator().generate(page.getFolders().dstDir +prefix+ Utilities.changeFileExt((String) profile.getResource().getUserData("filename"), "-review.xls"), "Health Level Seven International", page.getGenDate(), profile.getResource(), page);

    // xml to xhtml of xml
    // first pass is to strip the xsi: stuff. seems to need double
    // processing in order to delete namespace crap
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document xdoc = builder.parse(new CSFileInputStream(page.getFolders().dstDir + prefix +title + ".profile.xml"));
    XmlGenerator xmlgen = new XmlGenerator();
    xmlgen.generate(xdoc.getDocumentElement(), tmp, "http://hl7.org/fhir", xdoc.getDocumentElement().getLocalName());

    // reload it now
    builder = factory.newDocumentBuilder();
    xdoc = builder.parse(new CSFileInputStream(tmp.getAbsolutePath()));
    XhtmlGenerator xhtml = new XhtmlGenerator(new ExampleAdorner(page.getDefinitions(), page.genlevel(level)));
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    xhtml.generate(xdoc, b, "StructureDefinition", profile.getTitle(), 0, true, title + ".profile.xml.html");
    String html = TextFile.fileToString(page.getFolders().templateDir + "template-profile-example-xml.html").replace("<%example%>", b.toString());
    html = page.processProfileIncludes(title + ".profile.xml.html", profile.getId(), pack, profile, "", "", "", html, title + ".html", (resource == null ? profile.getResource().getType() : resource.getName())+"/"+pack.getId()+"/"+profile.getId(), intro, notes, ig, false, hasNarrative(xdoc));
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix +title + ".profile.xml.html");

    page.getHTMLChecker().registerFile(prefix +title + ".profile.xml.html", "StructureDefinition", HTMLLinkChecker.XHTML_TYPE, false);
    String n = prefix +title + ".profile";
    json = resource2Json(profile.getResource());


    json = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml("StructureDefinition for " + profile.getResource().getDescription()) + "</p>\r\n<p><a href=\""+title+".profile.json\">Raw JSON</a></p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(json)+ "\r\n</pre>\r\n</div>\r\n";
    html = TextFile.fileToString(page.getFolders().templateDir + "template-profile-example-json.html").replace("<%example%>", json);
    html = page.processProfileIncludes(title + ".profile.json.html", profile.getId(), pack, profile, "", "", "", html, title + ".html", (resource == null ? profile.getResource().getType() : resource.getName())+"/"+pack.getId()+"/"+profile.getId(), intro, notes, ig, false, false);
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix +title + ".profile.json.html");
    //    page.getEpub().registerFile(n + ".json.html", description, EPubManager.XHTML_TYPE);
    page.getHTMLChecker().registerExternal(n + ".json.html");
    tmp.delete();
  }

  //  private void validateProfile(ProfileDefn profile) throws FileNotFoundException, Exception {
  //    for (ResourceDefn c : profile.getResources()) {
  //      StructureDefinition resource = loadResourceProfile(c.getName());
  //      ProfileValidator v = new ProfileValidator();
  //      v.setCandidate(c);
  //      v.setProfile(resource);
  //      v.setTypes(typeFeed);
  //      List<String> errors = v.evaluate();
  //      if (errors.size() > 0)
  //        throw new Exception("Error validating " + profile.metadata("name") + ": " + errors.toString());
  //    }
  //  }

  // private void produceFutureReference(String n) throws Exception {
  // ElementDefn e = new ElementDefn();
  // e.setName(page.getIni().getStringProperty("future-resources", n));
  // }


  /*
  private StructureDefinition loadResourceProfile(String name) throws FileNotFoundException, Exception {
    XmlParser xml = new XmlParser();
    try {
      return (StructureDefinition) xml.parse(new CSFileInputStream(page.getFolders().dstDir + name.toLowerCase() + ".profile.xml"));
    } catch (Exception e) {
      throw new Exception("error parsing " + name, e);
    }
  }
  */

//  private void produceIgPage(String source, String file, String logicalName, ImplementationGuideDefn ig) throws Exception {
//    String src = TextFile.fileToString(source);
//    src = page.processPageIncludes(file, src, "page", null, null, null, logicalName, null);
//    // before we save this page out, we're going to figure out what it's index
//    // is, and number the headers if we can
//
//    if (Utilities.noString(logicalName))
//      logicalName = Utilities.fileTitle(file);
//
//    TextFile.stringToFile(src, page.getFolders().dstDir + file);
//    src = addSectionNumbers(file, logicalName, src, null, 0, null, ig);
//
//    TextFile.stringToFile(src, page.getFolders().dstDir + file);
//
//    src = TextFile.fileToString(source).replace("<body>", "<body style=\"margin: 10px\">");
//    src = page.processPageIncludesForBook(file, src, "page", null, null);
//    cachePage(file, src, logicalName);
//  }
//
  private void producePage(String file, String logicalName) throws Exception {
    String src = TextFile.fileToString(page.getFolders().srcDir + file);
    src = page.processPageIncludes(file, src, "page", null, null, null, logicalName, null, null, null);
    // before we save this page out, we're going to figure out what it's index
    // is, and number the headers if we can

    if (Utilities.noString(logicalName))
      logicalName = Utilities.fileTitle(file);

    TextFile.stringToFile(src, page.getFolders().dstDir + file);
    DocumentHolder doch = new DocumentHolder();
    src = addSectionNumbers(file, logicalName, src, null, 0, doch, null);

    if (!page.getDefinitions().getStructuralPages().contains(file)) {
      XhtmlNode fmm = findId(doch.doc, "fmm");
      XhtmlNode wg = findId(doch.doc, "wg");
      if (fmm == null)
        page.getValidationErrors().add(new   ValidationMessage(Source.Publisher, IssueType.BUSINESSRULE, -1, -1, file, "Page has no fmm level", IssueSeverity.ERROR));
      else
        page.getDefinitions().page(file).setFmm(get2ndPart(fmm.allText()));
      if (wg == null)
        page.getValidationErrors().add(new ValidationMessage(Source.Publisher, IssueType.BUSINESSRULE, -1, -1, file, "Page has no workgroup", IssueSeverity.ERROR));
      else
        page.getDefinitions().page(file).setWg(wg.getChildNodes().get(0).allText());
    }

    TextFile.stringToFile(src, page.getFolders().dstDir + file);

    src = TextFile.fileToString(page.getFolders().srcDir + file).replace("<body>", "<body style=\"margin: 10px\">");
    src = page.processPageIncludesForBook(file, src, "page", null, null, null);
    cachePage(file, src, logicalName, true);
  }

  private String get2ndPart(String t) {
    return t.substring(t.indexOf(":")+1).trim();
  }


  private void produceIgPage(ImplementationGuideDefn ig, ImplementationGuideDefinitionPageComponent p) throws Exception {
    String actualName = Utilities.path(page.getFolders().rootDir, Utilities.getDirectoryForFile(ig.getSource()), p.getNameUrlType().getValue());
    String logicalName = Utilities.fileTitle(actualName);
    String src;
    if (IgParser.getKind(p) == GuidePageKind.TOC)
      src = TextFile.fileToString(Utilities.path(page.getFolders().templateDir, "template-ig-toc.html"));
    else
      throw new Exception("Unsupported special page kind "+IgParser.getKind(p).toCode());

    String file = ig.getCode()+File.separator+logicalName +".html";

    src = page.processPageIncludes(file, src, "page", null, null, null, logicalName, ig, null, null);
    // before we save this page out, we're going to figure out what it's index
    // is, and number the headers if we can

    src = addSectionNumbers(file, logicalName, src, null, 1, null, ig);

    TextFile.stringToFile(src, Utilities.path(page.getFolders().dstDir, file));

    src = TextFile.fileToString(Utilities.path(page.getFolders().dstDir, file)).replace("<body>", "<body style=\"margin: 10px\">");
    src = page.processPageIncludesForBook(file, src, "page", null, ig, null);
    cachePage(file, src, logicalName, true);
  }

  private void produceIgPage(String file, ImplementationGuideDefn ig) throws Exception {
    String actualName = Utilities.path(page.getFolders().rootDir, Utilities.getDirectoryForFile(ig.getSource()), file);
    String logicalName = Utilities.fileTitle(actualName);
    String src = TextFile.fileToString(actualName);
    file = ig.getCode()+File.separator+logicalName +".html";

    src = page.processPageIncludes(file, src, "page", null, null, null, logicalName, ig, null, null);
    // before we save this page out, we're going to figure out what it's index
    // is, and number the headers if we can

    TextFile.stringToFile(src, page.getFolders().dstDir + file);
    src = addSectionNumbers(file, logicalName, src, null, 1, null, ig);

    TextFile.stringToFile(src, page.getFolders().dstDir + file);

    src = TextFile.fileToString(actualName).replace("<body>", "<body style=\"margin: 10px\">");
    src = page.processPageIncludesForBook(file, src, "page", null, ig, null);
    cachePage(file, src, logicalName, true);
  }

  private void produceIgPage(String file, ImplementationGuideDefn ig, String logicalName) throws Exception {
    String srcOrig = TextFile.fileToString(page.getFolders().srcDir + file);
    file = file.substring(3);
    String src = page.processPageIncludes(file, srcOrig, "page", null, null, null, logicalName, ig, null, null);
    // before we save this page out, we're going to figure out what it's index
    // is, and number the headers if we can

    if (Utilities.noString(logicalName))
      logicalName = Utilities.fileTitle(file);

    TextFile.stringToFile(src, Utilities.path(page.getFolders().dstDir, ig.getCode(), file));
    DocumentHolder doch = new DocumentHolder();
    src = addSectionNumbers(file, logicalName, src, null, 0, doch, ig);

//    if (!page.getDefinitions().getStructuralPages().contains(file)) {
//      XhtmlNode fmm = findId(doch.doc, "fmm");
//      XhtmlNode wg = findId(doch.doc, "wg");
//      if (fmm == null)
//        page.getValidationErrors().add(new   ValidationMessage(Source.Publisher, IssueType.BUSINESSRULE, -1, -1, file, "Page has no fmm level", IssueSeverity.ERROR));
//      else
//        page.getDefinitions().page(file).setFmm(get2ndPart(fmm.allText()));
//      if (wg == null)
//        page.getValidationErrors().add(new ValidationMessage(Source.Publisher, IssueType.BUSINESSRULE, -1, -1, file, "Page has no workgroup", IssueSeverity.ERROR));
//      else
//        page.getDefinitions().page(file).setWg(wg.getChildNodes().get(0).allText());
//    }
//
//    TextFile.stringToFile(src, page.getFolders().dstDir + file);

    src = srcOrig.replace("<body>", "<body style=\"margin: 10px\">");
    src = page.processPageIncludesForBook(file, src, "page", null, ig, null);
    cachePage(ig.getCode()+File.separator+file, src, logicalName, true);
  }

  private void produceLogicalModel(LogicalModel lm, ImplementationGuideDefn ig) throws Exception {
    String n = lm.getId();

    Map<String, String> examples = new HashMap<String, String>();
    
    File tmp = Utilities.createTempFile("tmp", ".tmp");

    TerminologyNotesGenerator tgen = new TerminologyNotesGenerator(new FileOutputStream(tmp), page);
    if (lm.hasResource())
      tgen.generate("", lm.getResource().getRoot());
    else
      tgen.generate("", lm.getDefinition());
    tgen.close();
    String tx = TextFile.fileToString(tmp.getAbsolutePath());

    DictHTMLGenerator dgen = new DictHTMLGenerator(new FileOutputStream(tmp), page, "");
    if (lm.hasResource())
      dgen.generate(lm.getResource().getRoot());
    else
      dgen.generate(lm.getDefinition());
    dgen.close();
    String dict = TextFile.fileToString(tmp.getAbsolutePath());

    MappingsGenerator mgen = new MappingsGenerator(page.getDefinitions());
    if (lm.hasResource())
      mgen.generate(lm.getResource());
    else
      mgen.generate(lm.getDefinition());
    String mappings = mgen.getMappings();
    String mappingsList = mgen.getMappingsList();

    SvgGenerator svg = new SvgGenerator(page, "", lm.getLayout(), true, false);
    String fn = ig.getPrefix()+n;
    if (lm.hasResource())
      svg.generate(lm.getResource(), page.getFolders().dstDir + fn+".svg", "2");
    else
      svg.generate(lm.getDefinition(), page.getFolders().dstDir + fn+".svg", "2");

    String prefix = page.getBreadCrumbManager().getIndexPrefixForReference(lm.getId()+".html");
    SectionTracker st = new SectionTracker(prefix, true);
    st.start("");
    page.getSectionTrackerCache().put(fn, st);

    if (lm.getDefinition() != null) {
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(page.getFolders().dstDir, ig.getPrefix(), n+".xml")), lm.getDefinition());
      cloneToXhtml(ig.getPrefix()+n, "Logical Model "+lm.getDefinition().getName(), true, "logical-model", lm.getDefinition().getName(), null, lm.getWg());
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(page.getFolders().dstDir, ig.getPrefix(), n+".json")), lm.getDefinition());
      jsonToXhtml(ig.getPrefix()+n, "Logical Model "+lm.getDefinition().getName(), new JsonParser().setOutputStyle(OutputStyle.PRETTY).composeString(lm.getDefinition()), "logical-model", lm.getDefinition().getName(), null, lm.getWg());
      ttlToXhtml(ig.getPrefix()+n, "Logical Model "+lm.getDefinition().getName(), new RdfParser().setOutputStyle(OutputStyle.PRETTY).composeString(lm.getDefinition()), "logical-model", lm.getDefinition().getName(), null, lm.getWg());
    }
    if (lm.getWg() != null && lm.getResource().getWg() == null)
      lm.getResource().setWg(lm.getWg());
    String template = "template-logical";
    String src = TextFile.fileToString(page.getFolders().templateDir + template+".html");
    Map<String, String> values = new HashMap<String, String>();
    if (lm.hasResource())
      src = insertSectionNumbers(page.processResourceIncludes(n, lm.getResource(), "", "", "", tx, dict, src, mappings, mappingsList, "resource", n + ".html", ig, values, lm.getWg(), examples), st, n + ".html", ig.getLevel(), null);
    else
      src = insertSectionNumbers(new LogicalModelProcessor(n, page, ig, lm.getDefinition().getId(), "logical-model", n+".html", lm.getDefinition(), tx, dict, examples, ig.getLogicalModels(), page.getDefinitions()).process(src), st, n + ".html", ig.getLevel(), null);
    TextFile.stringToFile(src, page.getFolders().dstDir + fn+".html");
    page.getHTMLChecker().registerFile(fn+".html", "Base Page for " + n, HTMLLinkChecker.XHTML_TYPE, true);

    src = TextFile.fileToString(page.getFolders().templateDir + "template-logical-definitions.html");
    if (lm.hasResource())
      TextFile.stringToFile(insertSectionNumbers(page.processResourceIncludes(n, lm.getResource(), "", "", "", tx, dict, src, mappings, mappingsList, "res-Detailed Descriptions", n + "-definitions.html", ig, values, lm.getWg(), examples), st, n
            + "-definitions.html", ig.getLevel(), null), page.getFolders().dstDir + fn+"-definitions.html");
    else
      TextFile.stringToFile(insertSectionNumbers(new LogicalModelProcessor(n, page, ig, lm.getDefinition().getId(), "logical-model", n+".html", lm.getDefinition(), tx, dict, examples, ig.getLogicalModels(), page.getDefinitions()).process(src), st, n
          + "-definitions.html", ig.getLevel(), null), page.getFolders().dstDir + fn+"-definitions.html");
    page.getHTMLChecker().registerFile(fn+"-definitions.html", "Detailed Descriptions for " + (lm.hasResource() ? lm.getResource().getName() : lm.getDefinition().getName()), HTMLLinkChecker.XHTML_TYPE, true);

    src = TextFile.fileToString(page.getFolders().templateDir + "template-logical-examples.html");
    if (lm.hasResource())
      TextFile.stringToFile(insertSectionNumbers(page.processResourceIncludes(n, lm.getResource(), "", "", "", tx, dict, src, mappings, mappingsList, "resource", n + ".html", ig, values, lm.getWg(), examples), st, n + ".html", ig.getLevel(), null), page.getFolders().dstDir + fn+"-implementations.html");
    else
      TextFile.stringToFile(insertSectionNumbers(new LogicalModelProcessor(n, page, ig, lm.getDefinition().getId(), "logical-model", n+".html", lm.getDefinition(), tx, dict, examples, ig.getLogicalModels(), page.getDefinitions()).process(src), st, n
          + "-implementations.html", ig.getLevel(), null), page.getFolders().dstDir + fn+"-implementations.html");
    page.getHTMLChecker().registerFile(fn+"-implementations.html", "Implementations for " + (lm.hasResource() ? lm.getResource().getName() : lm.getDefinition().getName()), HTMLLinkChecker.XHTML_TYPE, true);

    src = TextFile.fileToString(page.getFolders().templateDir + "template-logical-mappings.html");
    if (lm.hasResource())
      TextFile.stringToFile(
          insertSectionNumbers(page.processResourceIncludes(n, lm.getResource(), "", "", "", tx, dict, src, mappings, mappingsList, "res-Mappings", n + "-mappings.html", ig, values, lm.getWg(), examples), st, n + "-mappings.html", ig.getLevel(), null),
          page.getFolders().dstDir + fn + "-mappings.html");
    else
      TextFile.stringToFile(insertSectionNumbers(new LogicalModelProcessor(n, page, ig, lm.getDefinition().getId(), "logical-model", n+".html", lm.getDefinition(), tx, dict, examples, ig.getLogicalModels(), page.getDefinitions()).process(src), st, n + "-mappings.html", ig.getLevel(), null),
        page.getFolders().dstDir + fn + "-mappings.html");
    page.getHTMLChecker().registerFile(fn+"-mappings.html", "Formal Mappings for " + n, HTMLLinkChecker.XHTML_TYPE, true);

    src = TextFile.fileToString(page.getFolders().templateDir + "template-logical-analysis.html");
    if (lm.hasResource())
      TextFile.stringToFile(
          insertSectionNumbers(page.processResourceIncludes(n, lm.getResource(), "", "", "", tx, dict, src, mappings, mappingsList, "res-Analysis", n + "-analysis.html", ig, values, lm.getWg(), examples), st, n + "-analysis.html", ig.getLevel(), null),
          page.getFolders().dstDir + fn + "-analysis.html");
    else
      TextFile.stringToFile(insertSectionNumbers(new LogicalModelProcessor(n, page, ig, lm.getDefinition().getId(), "logical-model", n+".html", lm.getDefinition(), tx, dict, examples, ig.getLogicalModels(), page.getDefinitions()).process(src), st, n + "-analysis.html", ig.getLevel(), null),
        page.getFolders().dstDir + fn + "-analysis.html");
    page.getHTMLChecker().registerFile(fn+"-analysis.html", "Analysis for " + n, HTMLLinkChecker.XHTML_TYPE, true);

    tmp.delete();
  }


  private void produceDictionary(Dictionary d) throws Exception {
    if (web)
      return;

    String src = TextFile.fileToString(page.getFolders().templateDir + "template-dictionary.html");
    String file = d.getSource();
    String prefix = d.getIg() != null ? d.getIg().getCode()+File.separator : "";
    String filename = prefix+d.getId();
    XmlParser xml = new XmlParser();
    Bundle dict = (Bundle) xml.parse(new CSFileInputStream(file));

    src = page.processPageIncludes(filename+".html", src, "page", null, dict, null, "Dictionary", null, null, null);
    // before we save this page out, we're going to figure out what it's index
    // is, and number the headers if we can

    TextFile.stringToFile(src, page.getFolders().dstDir + filename+".html");
    src = addSectionNumbers(filename+".html", filename, src, null, d.getIg() != null ? 1 : 0, null, d.getIg());

    TextFile.stringToFile(src, page.getFolders().dstDir + filename+".html");

    src = TextFile.fileToString(page.getFolders().templateDir + "template-dictionary.html").replace("<body>", "<body style=\"margin: 10px\">");
    src = page.processPageIncludesForBook(filename+".html", src, "page", dict, null, null);
    cachePage(filename+".html", src, d.getId(), true);

    xml.setOutputStyle(OutputStyle.PRETTY);
    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + filename+".xml");
    xml.compose(s, dict);
    s.close();
    xml.setOutputStyle(OutputStyle.CANONICAL);
    s = new FileOutputStream(page.getFolders().dstDir + filename+".canonical.xml");
    xml.compose(s, dict);
    s.close();
    cloneToXhtml(filename, "Source for Dictionary" + d.getName(), false, "dict-instance", "Dictionary", null, null);
    IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
    s = new FileOutputStream(page.getFolders().dstDir+filename+ ".json");
    json.compose(s, dict);
    s.close();
    json = new JsonParser().setOutputStyle(OutputStyle.CANONICAL);
    s = new FileOutputStream(page.getFolders().dstDir+filename+ ".canonical.json");
    json.compose(s, dict);
    s.close();
    jsonToXhtml(filename, "Source for Dictionary" + d.getName(), resource2Json(dict), "dict-instance", "Dictionary", null, null);
    ttlToXhtml(filename, "Source for Dictionary" + d.getName(), resource2Ttl(dict), "dict-instance", "Dictionary", null, null);
    throw new Error("must be redone");
//    for (BundleEntryComponent e : dict.getEntry()) {
//      produceDictionaryProfile(d, file, filename, (DataElement) e.getResource(), d.getIg());
//    }
  }

//  private void produceDictionaryProfile(Dictionary d, String srcbase, String destbase, DataElement de, ImplementationGuideDefn ig) throws Exception {
//    // first, sort out identifiers
//    String template = TextFile.fileToString(Utilities.changeFileExt(srcbase, "-profile.xml"));
//    String file = Utilities.changeFileExt(destbase, "-"+de.getId());
//
//    // second, generate the profile.
//    Map<String, String> variables = new HashMap<String, String>();
//    variables.put("de_id", de.getId());
//    variables.put("de_name", de.getName());
//    variables.put("de_definition", Utilities.noString(de.getElement().get(0).getDefinition()) ? "??" : de.getElement().get(0).getDefinition());
//    variables.put("de_code0_code", de.getElement().get(0).getCode().get(0).getCode());
//    Type ucc = ToolingExtensions.getAllowedUnits(de.getElement().get(0));
//    if (ucc instanceof CodeableConcept)
//      variables.put("de_units_code0_code", ((CodeableConcept) ucc).getCoding().get(0).getCode());
//    else
//      variables.put("de_units_code0_code", "");
//    String profile = processTemplate(template, variables);
//    XmlParser xml = new XmlParser();
//    StructureDefinition p = (StructureDefinition) xml.parse(new ByteArrayInputStream(profile.getBytes()));
//    StructureDefinition base = page.getProfiles().get(p.getBaseDefinition());
//    if (base == null)
//      throw new Exception("Unable to find base profile for "+d.getId()+": "+p.getBaseDefinition()+" from "+page.getProfiles().keySet());
//    new ProfileUtilities(page.getWorkerContext(), page.getValidationErrors(), page).generateSnapshot(base, p, p.getBaseDefinition(), p.getId());
//    ConstraintStructure pd = new ConstraintStructure(p, page.getDefinitions().getUsageIG("hspc", "special HSPC generation"), null, "0", true); // todo
//    pd.setId(p.getId());
//    pd.setTitle(p.getName());
//    Profile pack = new Profile("hspc");
//    pack.forceMetadata("date", p.getDateElement().asStringValue());
//    p.setUserData("filename", file  );
//
//    ByteArrayOutputStream bs = new ByteArrayOutputStream();
//    XmlSpecGenerator gen = new XmlSpecGenerator(bs, null, "http://hl7.org/fhir/", page, "");
//    gen.generate(p);
//    gen.close();
//    String xmls = new String(bs.toByteArray());
//    bs = new ByteArrayOutputStream();
//    JsonSpecGenerator genJ = new JsonSpecGenerator(bs, null, "http://hl7.org/fhir/", page, "");
//    // genJ.generate(profile.getResource());
//    genJ.close();
//    String jsons = new String(bs.toByteArray());
//
//    String tx = ""; //todo
//
//    String src = TextFile.fileToString(page.getFolders().templateDir + "template-profile.html");
//    src = page.processProfileIncludes(p.getId(), p.getId(), pack, pd, xmls, jsons, tx, src, file + ".html", "??/??/??", "", "", ig, true, false); // resourceName+"/"+pack.getId()+"/"+profile.getId());
//    page.getHTMLChecker().registerFile(file + ".html", "StructureDefinition " + p.getName(), HTMLLinkChecker.XHTML_TYPE, true);
//    TextFile.stringToFile(src, page.getFolders().dstDir + file + ".html");
//
//    src = TextFile.fileToString(page.getFolders().templateDir + "template-profile-mappings.html");
//    src = page.processProfileIncludes(p.getId(), p.getId(), pack, pd, xmls, jsons, tx, src, file + ".html", "??/??/??", "", "", ig, true, false);
//    page.getHTMLChecker().registerFile(file + "-mappings.html", "Mappings for StructureDefinition " + p.getName(), HTMLLinkChecker.XHTML_TYPE, true);
//    TextFile.stringToFile(src, page.getFolders().dstDir + file + "-mappings.html");
//
//    src = TextFile.fileToString(page.getFolders().templateDir + "template-profile-definitions.html");
//    src = page.processProfileIncludes(p.getId(), p.getId(), pack, pd, xmls, jsons, tx, src, file + ".html", "??/??/??", "", "", ig, true, false);
//    page.getHTMLChecker().registerFile(file + "-definitions.html", "Definitions for StructureDefinition " + p.getName(), HTMLLinkChecker.XHTML_TYPE, true);
//    TextFile.stringToFile(src, page.getFolders().dstDir + file + "-definitions.html");
//
//    // now, save the profile and generate equivalents
//    xml.setOutputStyle(OutputStyle.PRETTY);
//    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + file+".profile.xml");
//    xml.compose(s, p);
//    s.close();
//    xml.setOutputStyle(OutputStyle.CANONICAL);
//    s = new FileOutputStream(page.getFolders().dstDir + file+".profile.canonical.xml");
//    xml.compose(s, p);
//    s.close();
//    cloneToXhtml(file+".profile", "Source for Dictionary" + page.getDefinitions().getDictionaries().get(file), false, "dict-instance", "Profile", null, null);
//    IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
//    s = new FileOutputStream(page.getFolders().dstDir+file+ ".profile.json");
//    json.compose(s, p);
//    s.close();
//    json = new JsonParser().setOutputStyle(OutputStyle.CANONICAL);
//    s = new FileOutputStream(page.getFolders().dstDir+file+ ".profile.canonical.json");
//    json.compose(s, p);
//    s.close();
//    jsonToXhtml(file+".profile", "Source for Dictionary based StructureDefinition" + page.getDefinitions().getDictionaries().get(file), resource2Json(p), "dict-instance", "Profile", null, null);
//    new ReviewSpreadsheetGenerator().generate(page.getFolders().dstDir + file+ "-review.xls", "Health Level Seven International", page.getGenDate(), p, page);
//  }

  private String processTemplate(String template, Map<String, String> variables) {
    ST st = new ST(template, '$', '$');
    for (String var : variables.keySet())
      st.add(var, variables.get(var));
    return st.render();
  }

  private void produceSid(int i, String logicalName, String file) throws Exception {
    String src = TextFile.fileToString(page.getFolders().srcDir + file);
    String dstName = Utilities.path(page.getFolders().dstDir, "sid", logicalName, "index.html");
    src = page.processPageIncludes(dstName, src, "sid:" + logicalName, null, null, null, "Sid", null, null, null);
    // before we save this page out, we're going to figure out what it's index
    // is, and number the headers if we can

    Utilities.createDirectory(Utilities.path(page.getFolders().dstDir, "sid", logicalName));
    TextFile.stringToFile(src, dstName);
    src = addSectionNumbers(Utilities.path("sid", logicalName, "index.html"), "sid:terminologies-systems", src, "3." + Integer.toString(i), 0, null, null);
    TextFile.stringToFile(src, dstName);
    page.getHTMLChecker().registerFile(Utilities.path("sid", logicalName, "index.html"), logicalName, HTMLLinkChecker.XHTML_TYPE, true);
  }

  @Override
  public String addSectionNumbers(String file, String logicalName, String src, String id, int level, DocumentHolder doch, ImplementationGuideDefn ig) throws Exception {
    if (ig != null)
      logicalName = ig.getCode()+"::"+logicalName;

    if (!page.getSectionTrackerCache().containsKey(logicalName)) {
      // String prefix =
      // page.getNavigation().getIndexPrefixForFile(logicalName+".html");
      String prefix;
      if (ig != null)
        prefix = ig.getIndexPrefixForFile(file, logicalName + ".html");
      else
        prefix = page.getBreadCrumbManager().getIndexPrefixForFile(logicalName + ".html");
      if (Utilities.noString(prefix))
        throw new Exception("No indexing home for logical place " + logicalName);
      page.getSectionTrackerCache().put(logicalName, new SectionTracker(prefix, ig != null));
    }
    SectionTracker st = page.getSectionTrackerCache().get(logicalName);
    st.start(id);
    src = insertSectionNumbers(src, st, file, level, doch);
    return src;
  }

  private void produceCompartment(Compartment c) throws Exception {

    String logicalName = "compartmentdefinition-" + c.getName().toLowerCase();
    String file = logicalName + ".html";
    String src = TextFile.fileToString(page.getFolders().templateDir + "template-compartment.html");
    src = page.processPageIncludes(file, src, "resource-instance:CompartmentDefinition", null, null, null, "Compartment", null, null, wg("fhir"));

    // String prefix = "";
    // if
    // (!page.getSectionTrackerCache().containsKey("compartmentdefinition-"+c.getName()))
    // {
    // prefix = page.getNavigation().getIndexPrefixForFile(logicalName+".html");
    // if (Utilities.noString(prefix))
    // throw new Exception("No indexing home for logical place "+logicalName);
    // }
    // page.getSectionTrackerCache().put(logicalName, new
    // SectionTracker(prefix));

    // TextFile.stringToFile(src, page.getFolders().dstDir + file);
    // src = insertSectionNumbers(src,
    // page.getSectionTrackerCache().get(logicalName), file);

    TextFile.stringToFile(src, page.getFolders().dstDir + file);

    src = TextFile.fileToString(page.getFolders().templateDir + "template-compartment.html").replace("<body>", "<body style=\"margin: 10px\">");
    src = page.processPageIncludesForBook(file, src, "compartment", null, null, null);
    cachePage(file, src, "Compartments", true);
  }

  private String insertSectionNumbers(String src, SectionTracker st, String link, int level, DocumentHolder doch) throws Exception {
    try {
      // TextFile.stringToFile(src, "c:\\temp\\text.html");
      XhtmlDocument doc = new XhtmlParser().parse(src, "html");
      insertSectionNumbersInNode(doc, st, link, level, new BooleanHolder(), null);
      if (doch != null)
        doch.doc = doc;
      return new XhtmlComposer(XhtmlComposer.HTML).compose(doc);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      //TextFile.stringToFile(src, "c:\\temp\\dump.html");
      TextFile.stringToFile(src, Utilities.appendSlash(System.getProperty("user.dir")) + "fhir-error-dump.html");

      throw new Exception("Exception inserting section numbers in " + link + ": " + e.getMessage(), e);
    }
  }

  private XhtmlNode findId(XhtmlNode node, String id) {
    if (id.equals(node.getAttribute("id")))
      return node;
    for (XhtmlNode n : node.getChildNodes()) {
      XhtmlNode xn = findId(n, id);
      if (xn != null)
        return xn;
    }
    return null;
  }

  private class BooleanHolder {
    private boolean value;
  }

  private void insertSectionNumbersInNode(XhtmlNode node, SectionTracker st, String link, int level, BooleanHolder registered, XhtmlNode parent) throws Exception {
    // while we're looking, mark external references explicitly
    if (node.getNodeType() == NodeType.Element && node.getName().equals("a") &&
        node.getAttribute("href") != null && node.getAttribute("no-external") == null && (node.getAttribute("href").startsWith("http:") || node.getAttribute("href").startsWith("https:"))) {
      node.addText(" ");
      XhtmlNode img = node.addTag("img");
      String s = "external.png";
      for (int i = 0; i < level; i++)
        s = "../"+s;
      img.attribute("src", s);
      img.attribute("style", "vertical-align: baseline");
    }

    if (node.getNodeType() == NodeType.Element
        && (node.getName().equals("h1") || node.getName().equals("h2") || node.getName().equals("h3") || node.getName().equals("h4")
            || node.getName().equals("h5") || node.getName().equals("h6"))) {
      String v = st.getIndex(Integer.parseInt(node.getName().substring(1)));
      String sv = v;
      if (!st.isIg() && !registered.value) {
        TocEntry t = new TocEntry(v, node.allText(), link, st.isIg());
        if (t.getText() == null)
          t.setText("(No Title?)");
        if (!page.getToc().containsKey(v)) {
//          throw new Exception("Duplicate TOC Entry "+v);
          page.getToc().put(v, t);
          registered.value = true;
        } // else
          // System.out.println("-- duplicate TOC --> "+v+" = "+t.getLink()+" ("+t.getText()+") in place of "+page.getToc().get(v).getLink()+" ("+page.getToc().get(v).getText()+")");
      } else if (parent != null)
        sv = findSemanticLink(parent, node, sv);
      node.addText(0, " ");
      XhtmlNode span = node.addTag(0, "span");
      span.setAttribute("class", "sectioncount");
      span.addText(v);
      if (sv.equals(v)) {
        XhtmlNode a = span.addTag("a");
        a.setAttribute("name", v);
        a.addText(" "); // bug in some browsers?
      }
      node.addText(" ");
      XhtmlNode a = node.addTag("a");
      if (node.hasAttribute("class"))
        throw new Error("test");
      else
        node.setAttribute("class", "self-link-parent");
      a.setAttribute("href", (link.contains(File.separator) ? link.substring(link.lastIndexOf(File.separator)+1) : link) +"#"+sv);
      a.setAttribute("title", "link to here");
      a.setAttribute("class", "self-link");
      XhtmlNode svg = a.addTag("svg");
      XhtmlNode path = svg.addTag("path");
      String pathData = "M1520 1216q0-40-28-68l-208-208q-28-28-68-28-42 0-72 32 3 3 19 18.5t21.5 21.5 15 19 13 25.5 3.5 27.5q0 40-28 68t-68 28q-15 0-27.5-3.5t-25.5-13-19-15-21.5-21.5-18.5-19q-33 31-33 73 0 40 28 68l206 207q27 27 68 27 40 0 68-26l147-146q28-28 28-67zm-703-705q0-40-28-68l-206-207q-28-28-68-28-39 0-68 27l-147 146q-28 28-28 67 0 40 28 68l208 208q27 27 68 27 42 0 72-31-3-3-19-18.5t-21.5-21.5-15-19-13-25.5-3.5-27.5q0-40 28-68t68-28q15 0 27.5 3.5t25.5 13 19 15 21.5 21.5 18.5 19q33-31 33-73zm895 705q0 120-85 203l-147 146q-83 83-203 83-121 0-204-85l-206-207q-83-83-83-203 0-123 88-209l-88-88q-86 88-208 88-120 0-204-84l-208-208q-84-84-84-204t85-203l147-146q83-83 203-83 121 0 204 85l206 207q83 83 83 203 0 123-88 209l88 88q86-88 208-88 120 0 204 84l208 208q84 84 84 204z";
      svg.attribute("height", "20").attribute("width", "20").attribute("viewBox", "0 0 1792 1792").attribute("class", "self-link");
      path.attribute("d", pathData).attribute("fill", "navy");
    }
    if (node.getNodeType() == NodeType.Document
        || (node.getNodeType() == NodeType.Element && !(node.getName().equals("div") && "sidebar".equals(node.getAttribute("class"))))) {
      for (XhtmlNode n : node.getChildNodes()) {
        insertSectionNumbersInNode(n, st, link, level, registered, node);
      }
    }
  }

  private String findSemanticLink(XhtmlNode parent, XhtmlNode child, String def) {
    int i = parent.getChildNodes().indexOf(child) - 1;
    while (i >= 0) {
      XhtmlNode f = parent.getChildNodes().get(i);
      if (f.getNodeType() == NodeType.Text) {
        if (!Utilities.isWhitespace(f.getContent()))
          break;
      } else if (f.getNodeType() == NodeType.Element) {
        if (f.getName().equals("a") && f.hasAttribute("name")) {
          return f.getAttribute("name");
        } else break;
      }
      i--;
    }
    return def;
  }

  private void cachePage(String filename, String source, String title, boolean includeInBook) throws Exception {
    try {
      // page.log("parse "+filename);
      XhtmlDocument src = new XhtmlParser().parse(source, "html");
      scanForFragments(filename, src);
      // book.getPages().put(filename, src);
      page.getHTMLChecker().registerFile(filename, title, HTMLLinkChecker.XHTML_TYPE, includeInBook);
    } catch (Exception e) {
      throw new Exception("error parsing page " + filename + ": " + e.getMessage() + " in source\r\n" + source, e);
    }
  }

  private void scanForFragments(String filename, XhtmlNode node) throws Exception {
    if (node != null && (node.getNodeType() == NodeType.Element || node.getNodeType() == NodeType.Document)) {
      if (node.getNodeType() == NodeType.Element && node.getName().equals("pre") && node.getAttribute("fragment") != null) {
        processFragment(filename, node, node.getAttribute("fragment"), node.getAttribute("class"), node.getAttribute("id"));
      }
      for (XhtmlNode child : node.getChildNodes())
        scanForFragments(filename, child);
    }
  }

  private void processFragment(String filename, XhtmlNode node, String type, String clss, String id) throws Exception {
    if ("xml".equals(clss)) {
      String xml = new XhtmlComposer(XhtmlComposer.XML).compose(node);
      Fragment f = new Fragment();
      f.setType(type);
      f.setXml(Utilities.unescapeXml(xml));
      f.setPage(filename);
      f.setJson(false);
      f.setId(id);
      fragments.add(f);
    }
    if ("json".equals(clss)) {
      String xml = new XhtmlComposer(XhtmlComposer.XML).compose(node);
      Fragment f = new Fragment();
      f.setType(type);
      f.setXml(xml);
      f.setPage(filename);
      f.setId(id);
      f.setJson(true);
      fragments.add(f);
    }
  }

  private void validationProcess() throws Exception {

    if (!isPostPR) {
      if (buildFlags.get("all"))
        runJUnitTestsInProcess();
      page.log("Validating Examples", LogMessageType.Process);
      ExampleInspector ei = new ExampleInspector(page.getWorkerContext(), page, page.getFolders().dstDir, Utilities.path(page.getFolders().rootDir, "tools", "schematron"), page.getValidationErrors(), page.getDefinitions());
      page.log(".. Loading", LogMessageType.Process);
      ei.prepare();

      for (String rname : page.getDefinitions().sortedResourceNames()) {
        ResourceDefn r = page.getDefinitions().getResources().get(rname);
        if (wantBuild(rname)) {
          for (Example e : r.getExamples()) {
            String n = e.getTitle();
            ImplementationGuideDefn ig = e.getIg() == null ? null : page.getDefinitions().getIgs().get(e.getIg());
            if (ig != null)
              n = ig.getCode()+File.separator+n;
            if (validateId == null || validateId.equals(n))
              ei.validate(n, rname);
          }

          for (Profile e : r.getConformancePackages()) {
            for (Example en : e.getExamples()) {
              ImplementationGuideDefn ig = en.getIg() == null ? null : page.getDefinitions().getIgs().get(en.getIg());
              String prefix = (ig == null || ig.isCore()) ? "" : ig.getCode()+File.separator;
              String n = prefix+Utilities.changeFileExt(en.getTitle(), "");
              if (validateId == null || validateId.equals(n))
                ei.validate(n, rname, e.getProfiles().get(0).getResource());
            }
          }
        }
      }

      for (ImplementationGuideDefn ig : page.getDefinitions().getSortedIgs()) {
        String prefix = (ig == null || ig.isCore()) ? "" : ig.getCode()+File.separator;
        for (Example ex : ig.getExamples()) {
          String n = ex.getTitle();
          ei.validate(prefix+n, ex.getResourceName());
        }
        for (Profile pck : ig.getProfiles()) {
          for (Example en : pck.getExamples()) {
            ei.validate(prefix+Utilities.changeFileExt(en.getTitle(), ""), en.getResourceName(), pck.getProfiles().get(0).getResource());
          }
        }
      }
      if (buildFlags.get("all") && validateBundles) {
        if (validateId == null || validateId.equals("valuesets"))
          ei.validate("valuesets", "Bundle");
        if (validateId == null || validateId.equals("conceptmaps"))
          ei.validate("conceptmaps", "Bundle");
        if (validateId == null || validateId.equals("profiles-types"))
          ei.validate("profiles-types", "Bundle");
        if (validateId == null || validateId.equals("profiles-resources"))
          ei.validate("profiles-resources", "Bundle");
        if (validateId == null || validateId.equals("profiles-others"))
          ei.validate("profiles-others", "Bundle");
        if (validateId == null || validateId.equals("search-parameters"))
          ei.validate("search-parameters", "Bundle");
        if (validateId == null || validateId.equals("extension-definitions"))
          ei.validate("extension-definitions", "Bundle");
      }
      ei.summarise();

      if (buildFlags.get("all"))
        runJUnitTestsEnd();

      if (buildFlags.get("all") && isGenerate)
        produceCoverageWarnings();
      if (buildFlags.get("all"))
        miscValidation();
    }    

  }

  private void runJUnitTestsInProcess() throws Exception {
    /*
	  TestingUtilities.context = page.getWorkerContext();
    TestingUtilities.silent = true;
    TestingUtilities.fixedpath = page.getFolders().rootDir;
    TestingUtilities.contentpath = page.getFolders().dstDir;
    
    runJUnitClass(ValidationTestSuite.class);
    runJUnitClass(FHIRPathTests.class);
    runJUnitClass(NarrativeGeneratorTests.class);
    runJUnitClass(SnomedExpressionsTests.class);
    runJUnitClass(ResourceRoundTripTests.class);
    runJUnitClass(SnapShotGenerationTests.class);
    runJUnitClass(GraphQLParserTests.class);
    runJUnitClass(GraphQLEngineTests.class);
    checkAllOk();
	 */
  }

  private void runJUnitTestsEnd() throws Exception {
	  /*
    ValidationEngineTests.inbuild = true;
    runJUnitClass(ValidationEngineTests.class);
    runJUnitClass(TransformationTests.class); 
    runJUnitClass(AllGuidesTests.class);
    checkAllOk();
	 */
  }

  private void runJUnitClass(Class<?> clzz) {
    page.log("Run JUnit: "+clzz.getName(), LogMessageType.Process);
    Result result = JUnitCore.runClasses(clzz);
    for (Failure failure : result.getFailures()) {
      page.getValidationErrors().add(new ValidationMessage(Source.Publisher, IssueType.EXCEPTION, -1, -1, clzz.getName(), failure.toString(), IssueSeverity.ERROR));
    }
  }

  private void miscValidation() throws Exception {
    page.log("Other Validation", LogMessageType.Process);
    page.clean2();

    for (String rn : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn r = page.getDefinitions().getResourceByName(rn);
      for (SearchParameterDefn sp : r.getSearchParams().values()) {
        if (!sp.isWorks() && !sp.getCode().equals("_id") && !Utilities.noString(sp.getExpression())) {
          page.getValidationErrors().add(new ValidationMessage(Source.Publisher, IssueType.INFORMATIONAL, -1, -1, rn + "." + sp.getCode(), 
              "Search Parameter '" + rn + "." + sp.getCode() + "' had no found values in any example. Consider reviewing the expression (" + sp.getExpression() + ")", IssueSeverity.WARNING));
        }
      }
    }
  }

  private void produceCoverageWarnings() throws Exception {
    for (ElementDefn e : page.getDefinitions().getTypes().values())
      produceCoverageWarning("", e);
    for (String s : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn e = page.getDefinitions().getResourceByName(s);
      produceCoverageWarning("", e.getRoot());
    }
  }

  private void produceCoverageWarning(String path, ElementDefn e) {

    if (!e.isCoveredByExample() && !Utilities.noString(path) && !e.typeCode().startsWith("@")) {
      // page.getValidationErrors().add(new ValidationMessage(Source.Publisher, IssueType.INFORMATIONAL, -1, -1, path+e.getName(), "Path had no found values in any example. Consider reviewing the path", IssueSeverity.INFORMATION));
    }
    for (ElementDefn c : e.getElements()) {
      produceCoverageWarning(path + e.getName() + "/", c);
    }
  }



  private void compareXml(String t, String n, String fn1, String fn2) throws Exception {
    char sc = File.separatorChar;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setNamespaceAware(true);
    dbf.setCoalescing(true);
    dbf.setIgnoringElementContentWhitespace(true);
    dbf.setIgnoringComments(true);
    DocumentBuilder db = dbf.newDocumentBuilder();

    Document doc1 = db.parse(new CSFile(fn1));
    doc1.normalizeDocument();
    stripWhitespaceAndComments(doc1);

    Document doc2 = db.parse(new CSFile(fn2));
    doc2.normalizeDocument();
    stripWhitespaceAndComments(doc2);

    XmlGenerator xmlgen = new XmlGenerator();
    File tmp1 = Utilities.createTempFile("xml", ".xml");
    xmlgen.generate(doc1.getDocumentElement(), tmp1, doc1.getDocumentElement().getNamespaceURI(), doc1.getDocumentElement().getLocalName());
    File tmp2 = Utilities.createTempFile("xml", ".xml");
    xmlgen.generate(doc2.getDocumentElement(), tmp2, doc2.getDocumentElement().getNamespaceURI(), doc2.getDocumentElement().getLocalName());

    boolean ok = Utilities.compareIgnoreWhitespace(tmp1, tmp2);

    if (!ok) {
      page.getValidationErrors().add(
              new ValidationMessage(Source.Publisher, IssueType.BUSINESSRULE, -1, -1, "Reference Implementation", "file " + t + " did not round trip perfectly in XML in platform " + n, IssueSeverity.WARNING));
      String diff = diffProgram != null ? diffProgram : System.getenv("ProgramFiles(X86)") + sc + "WinMerge" + sc + "WinMergeU.exe";
      if (new CSFile(diff).exists()) {
        List<String> command = new ArrayList<String>();
        command.add("\"" + diff + "\" \"" + tmp1.getAbsolutePath() + "\" \"" + tmp2.getAbsolutePath() + "\"");

        ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(new CSFile(page.getFolders().rootDir));
//        final Process process = builder.start();
        builder.start();
//        process.waitFor();
      } else {
        // no diff program
        page.log("Files for diff: '" + fn1 + "' and '" + fn2 + "'", LogMessageType.Warning);
      }
    }
  }


  private void stripWhitespaceAndComments(Node node) {
    if (node.getNodeType() == Node.ELEMENT_NODE) {
      Element e = (Element) node;
      Map<String, String> attrs = new HashMap<String, String>();
      for (int i = e.getAttributes().getLength() - 1; i >= 0; i--) {
        attrs.put(e.getAttributes().item(i).getNodeName(), e.getAttributes().item(i).getNodeValue());
        e.removeAttribute(e.getAttributes().item(i).getNodeName());
      }
      for (String n : attrs.keySet()) {
        e.setAttribute(n, attrs.get(n));
      }
    }
    for (int i = node.getChildNodes().getLength() - 1; i >= 0; i--) {
      Node c = node.getChildNodes().item(i);
      if (c.getNodeType() == Node.TEXT_NODE && c.getTextContent().trim().length() == 0)
        node.removeChild(c);
      else if (c.getNodeType() == Node.TEXT_NODE)
        c.setTextContent(c.getTextContent().trim());
      else if (c.getNodeType() == Node.COMMENT_NODE)
        node.removeChild(c);
      else if (c.getNodeType() == Node.ELEMENT_NODE)
        stripWhitespaceAndComments(c);
    }
    if (node.getNodeType() == Node.ELEMENT_NODE) {
      node.appendChild(node.getOwnerDocument().createTextNode("\r\n"));
    }

  }

  // public void logNoEoln(String content) {
  // page.logNoEoln(content);
  // }

  @SuppressWarnings("unchecked")
  private void generateIGValueSetsPart1() throws Exception {
    for (Resource ae : page.getIgResources().values()) {
      if (ae instanceof ValueSet) {
        ValueSet vs = (ValueSet) ae;
        page.getValueSets().see(vs, page.packageInfo());
      }
      if (ae instanceof CodeSystem) {
        CodeSystem cs = (CodeSystem) ae;
        page.getCodeSystems().see(cs, page.packageInfo());
      }
    }
  }

  private void generateIGValueSetsPart2(ImplementationGuideDefn ig) throws Exception {
    for (Resource ae : page.getIgResources().values()) {
      if (ae instanceof ValueSet) {
        ValueSet vs = (ValueSet) ae;
        String name = Utilities.fileTitle((String) ae.getUserData("path"));
        String title = vs.getName();

        if (vs.getText() == null || vs.getText().getDiv() == null || vs.getText().getDiv().allChildrenAreText()
            && (Utilities.noString(vs.getText().getDiv().allText()) || !vs.getText().getDiv().allText().matches(".*\\w.*"))) {
          RenderingContext lrc = page.getRc().copy().setLocalPrefix("").setTooCostlyNoteEmpty(PageProcessor.TOO_MANY_CODES_TEXT_EMPTY).setTooCostlyNoteNotEmpty(PageProcessor.TOO_MANY_CODES_TEXT_NOT_EMPTY);
          RendererFactory.factory(vs, lrc).render(vs);
        }
        page.getVsValidator().validate(page.getValidationErrors(), name, vs, true, false);

        addToResourceFeed(vs, valueSetsFeed, null); // todo - what should the Oids be

        String sf = page.processPageIncludes(title + ".html", TextFile.fileToString(page.getFolders().templateDir + "template-vs-ig.html"), "valueSet", null, name+".html", vs, null, "Value Set", null, null, wg("vocab"));
        sf = addSectionNumbers(title + ".html", "template-valueset", sf, "??", 0, null, ig);
        TextFile.stringToFile(sf, page.getFolders().dstDir + name + ".html");

        String src = page.processPageIncludesForBook(title + ".html", TextFile.fileToString(page.getFolders().templateDir + "template-vs-ig-book.html"), "valueSet", vs, null, null);
        cachePage(name + ".html", src, "Value Set " + title, false);
        page.setId(null);

        IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
        FileOutputStream s = new FileOutputStream(page.getFolders().dstDir+name + ".json");
        json.compose(s, vs);
        s.close();
        json = new JsonParser().setOutputStyle(OutputStyle.CANONICAL);
        s = new FileOutputStream(page.getFolders().dstDir+name + ".canonical.json");
        json.compose(s, vs);
        s.close();
        IParser xml = new XmlParser().setOutputStyle(OutputStyle.PRETTY);
        s = new FileOutputStream(page.getFolders().dstDir+name + ".xml");
        xml.compose(s, vs);
        s.close();
        xml = new XmlParser().setOutputStyle(OutputStyle.CANONICAL);
        s = new FileOutputStream(page.getFolders().dstDir+name + ".canonical.xml");
        xml.compose(s, vs);
        s.close();
        cloneToXhtml(name, "Definition for Value Set" + vs.getName(), false, "valueset-instance", "Value Set", null, wg("vocab"));
        jsonToXhtml(name, "Definition for Value Set" + vs.getName(), resource2Json(vs), "valueset-instance", "Value Set", null, wg("vocab"));
        ttlToXhtml(name, "Definition for Value Set" + vs.getName(), resource2Ttl(vs), "valueset-instance", "Value Set", null, wg("vocab"));
      }
    }
  }

  private void generateCodeSystemsPart2() throws Exception {

    Set<String> urls = new HashSet<String>();

    for (CodeSystem cs : page.getDefinitions().getCodeSystems().getList()) {
      if (cs != null && !cs.hasUserData("external.url")) {
        if (cs.getUserData("example") == null && !cs.getUrl().contains("/v2-") && !cs.getUrl().contains("/v3-"))
          if (!urls.contains(cs.getUrl())) {
            urls.add(cs.getUrl());
            generateCodeSystemPart2(cs);
          }
      }
    }
  }

  private void generateValueSetsPart2() throws Exception {

    for (ValueSet vs : page.getDefinitions().getBoundValueSets().values()) {
      generateValueSetPart2(vs);
    }
    for (String s : page.getDefinitions().getExtraValuesets().keySet()) {
      if (!s.startsWith("http:")) {
        ValueSet vs = page.getDefinitions().getExtraValuesets().get(s);
        generateValueSetPart2(vs);
      }
    }
  }


  private void generateValueSetPart2(ValueSet vs) throws Exception {
    String n = vs.getUserString("filename");
    if (n == null)
      n = "valueset-"+vs.getId();
    ImplementationGuideDefn ig = (ImplementationGuideDefn) vs.getUserData(ToolResourceUtilities.NAME_RES_IG);
    if (ig != null)
      n = ig.getCode()+File.separator+n;

    if (!vs.hasText() || (vs.getText().getDiv().allChildrenAreText()
        && (Utilities.noString(vs.getText().getDiv().allText()) || !vs.getText().getDiv().allText().matches(".*\\w.*")))) {
      RenderingContext lrc = page.getRc().copy().setLocalPrefix(ig != null ? "../" : "").setTooCostlyNoteEmpty(PageProcessor.TOO_MANY_CODES_TEXT_EMPTY).setTooCostlyNoteNotEmpty(PageProcessor.TOO_MANY_CODES_TEXT_NOT_EMPTY);
      RendererFactory.factory(vs, lrc).render(vs);
    }
    page.getVsValidator().validate(page.getValidationErrors(), n, vs, true, false);

    if (isGenerate) {
//      page.log(" ... "+n, LogMessageType.Process);

      addToResourceFeed(vs, valueSetsFeed, null);

      if (vs.getUserData("path") == null)
        vs.setUserData("path", n + ".html");
      page.setId(vs.getId());
      String sf;
      try {
        sf = page.processPageIncludes(n + ".html", TextFile.fileToString(page.getFolders().templateDir + "template-vs.html"), "valueSet", null, n+".html", vs, null, "Value Set", ig, null, wg(vs, "vocab"));
      } catch (Exception e) {
        throw new Exception("Error processing "+n+".html: "+e.getMessage(), e);
      }
      sf = addSectionNumbers(n + ".html", "template-valueset", sf, vsCounter(), ig == null ? 0 : 1, null, ig);

      TextFile.stringToFile(sf, page.getFolders().dstDir + n + ".html");
      try {
        String src = page.processPageIncludesForBook(n + ".html", TextFile.fileToString(page.getFolders().templateDir + "template-vs-book.html"), "valueSet", vs, ig, null);
        cachePage(n + ".html", src, "Value Set " + n, false);
        page.setId(null);
      } catch (Exception e) {
        throw new Exception("Error processing "+n+".html: "+e.getMessage(), e);
      }

      IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
      FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + n + ".json");
      json.compose(s, vs);
      s.close();
      json = new JsonParser().setOutputStyle(OutputStyle.CANONICAL);
      s = new FileOutputStream(page.getFolders().dstDir + n + ".canonical.json");
      json.compose(s, vs);
      s.close();
      IParser xml = new XmlParser().setOutputStyle(OutputStyle.PRETTY);
      s = new FileOutputStream(page.getFolders().dstDir + n + ".xml");
      xml.compose(s, vs);
      s.close();
      xml = new XmlParser().setOutputStyle(OutputStyle.CANONICAL);
      s = new FileOutputStream(page.getFolders().dstDir + n + ".canonical.xml");
      xml.compose(s, vs);
      s.close();
//      System.out.println(vs.getUrl());
      cloneToXhtml(n, "Definition for Value Set" + vs.present(), false, "valueset-instance", "Value Set", null, wg("vocab"));
      jsonToXhtml(n, "Definition for Value Set" + vs.present(), resource2Json(vs), "valueset-instance", "Value Set", null, wg("vocab"));
      ttlToXhtml(n, "Definition for Value Set" + vs.present(), resource2Ttl(vs), "valueset-instance", "Value Set", null, wg("vocab"));
    }

  }


  private String vsCounter() {
    vscounter++;
    return String.valueOf(vscounter);
  }

  private String cmCounter() {
    cmcounter++;
    return String.valueOf(cmcounter);
  }

  private WorkGroup wg(DomainResource dr, String wg) {
    String code = ToolingExtensions.readStringExtension(dr, ToolingExtensions.EXT_WORKGROUP);
    return page.getDefinitions().getWorkgroups().get(Utilities.noString(code) ? wg : code);
  }

  private void generateCodeSystemPart2(CodeSystem cs) throws Exception {
    String n = cs.getUserString("filename");
    if (n == null)
      n = "codesystem-"+cs.getId();
    ImplementationGuideDefn ig = (ImplementationGuideDefn) cs.getUserData(ToolResourceUtilities.NAME_RES_IG);
    if (ig != null)
      n = ig.getCode()+File.separator+n;

    if (cs.getText().getDiv().allChildrenAreText()
        && (Utilities.noString(cs.getText().getDiv().allText()) || !cs.getText().getDiv().allText().matches(".*\\w.*"))) {
      RenderingContext lrc = page.getRc().copy().setLocalPrefix(ig != null ? "../" : "").setTooCostlyNoteEmpty(PageProcessor.TOO_MANY_CODES_TEXT_EMPTY).setTooCostlyNoteNotEmpty(PageProcessor.TOO_MANY_CODES_TEXT_NOT_EMPTY);
      RendererFactory.factory(cs, lrc).render(cs);
    }
    page.getVsValidator().validate(page.getValidationErrors(), n, cs, true, false);

    if (isGenerate) {
//      page.log(" ... "+n, LogMessageType.Process);

      addToResourceFeed(cs, valueSetsFeed, null);

      if (cs.getUserData("path") == null)
        cs.setUserData("path", n + ".html");
      page.setId(cs.getId());
      String sf;
      WorkGroup wg = wg(cs, "vocab");
      try {
        sf = page.processPageIncludes(n + ".html", TextFile.fileToString(page.getFolders().templateDir + "template-cs.html"), "codeSystem", null, n+".html", cs, null, "Value Set", ig, null, wg);
      } catch (Exception e) {
        throw new Exception("Error processing "+n+".html: "+e.getMessage(), e);
      }
      sf = addSectionNumbers(n + ".html", "template-codesystem", sf, csCounter(), ig == null ? 0 : 1, null, ig);

      TextFile.stringToFile(sf, page.getFolders().dstDir + n + ".html");
      try {
        String src = page.processPageIncludesForBook(n + ".html", TextFile.fileToString(page.getFolders().templateDir + "template-cs-book.html"), "codeSystem", cs, ig, null);
        cachePage(n + ".html", src, "Code System " + n, false);
        page.setId(null);
      } catch (Exception e) {
        throw new Exception("Error processing "+n+".html: "+e.getMessage(), e);
      }

      IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
      FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + n + ".json");
      json.compose(s, cs);
      s.close();
      json = new JsonParser().setOutputStyle(OutputStyle.CANONICAL);
      s = new FileOutputStream(page.getFolders().dstDir + n + ".canonical.json");
      json.compose(s, cs);
      s.close();
      IParser xml = new XmlParser().setOutputStyle(OutputStyle.PRETTY);
      s = new FileOutputStream(page.getFolders().dstDir + n + ".xml");
      xml.compose(s, cs);
      s.close();
      xml = new XmlParser().setOutputStyle(OutputStyle.CANONICAL);
      s = new FileOutputStream(page.getFolders().dstDir + n + ".canonical.xml");
      xml.compose(s, cs);
      s.close();
//      System.out.println(vs.getUrl());
      cloneToXhtml(n, "Definition for Code System " + cs.getName(), false, "codesystem-instance", "Code System", null, wg);
      jsonToXhtml(n, "Definition for Code System " + cs.getName(), resource2Json(cs), "codesystem-instance", "Code System", null, wg);
      ttlToXhtml(n, "Definition for Code System " + cs.getName(), resource2Ttl(cs), "codesystem-instance", "Code System", null, wg);
    }
  }
private String csCounter() {
    cscounter ++;
    return String.valueOf(cscounter);
  }

//  if (vs.hasCodeSystem()) {
//    if (ToolingExtensions.getOID(vs.getCodeSystem()) == null && !Utilities.noString(vs.getUserString("csoid")))
//      ToolingExtensions.setOID(vs.getCodeSystem(), "urn:oid:"+vs.getUserString("csoid"));
//    if (ToolingExtensions.getOID(vs.getCodeSystem()) == null)
//      throw new Exception("No OID on value set define for "+vs.getUrl());
//  }
//  if (vs.hasCodeSystem()) {
//    page.getCodeSystems().put(vs.getCodeSystem().getSystem(), vs);
//    page.getDefinitions().getCodeSystems().put(vs.getCodeSystem().getSystem(), vs);
//  }

  private void generateValueSetsPart1() throws Exception {
    page.log(" ...value sets", LogMessageType.Process);
    for (ValueSet vs : page.getDefinitions().getBoundValueSets().values()) {
      if (!vs.hasText()) {
        vs.setText(new Narrative());
        vs.getText().setStatus(NarrativeStatus.EMPTY);
      }
      if (!vs.getText().hasDiv()) {
        vs.getText().setDiv(new XhtmlNode(NodeType.Element));
        vs.getText().getDiv().setName("div");
      }
      if (ValueSetUtilities.getOID(vs) == null)
        throw new Exception("No OID on value set "+vs.getUrl());

      page.getValueSets().see(vs, page.packageInfo());
      page.getDefinitions().getValuesets().see(vs, page.packageInfo());
    }
    for (ValueSet vs : page.getDefinitions().getBoundValueSets().values()) {
      page.getVsValidator().validate(page.getValidationErrors(), vs.getUserString("filename"), vs, true, false);
    }
  }

  private void generateCodeSystemsPart1() throws Exception {
    page.log(" ...code systems", LogMessageType.Process);
    for (CodeSystem cs : page.getDefinitions().getCodeSystems().getList()) {
      if (cs != null && !cs.hasUserData("external.url")) {
        if (!cs.hasText()) {
          cs.setText(new Narrative());
          cs.getText().setStatus(NarrativeStatus.EMPTY);
        }
        if (!cs.getText().hasDiv()) {
          cs.getText().setDiv(new XhtmlNode(NodeType.Element));
          cs.getText().getDiv().setName("div");
        }
        //      if (ToolingExtensions.getOID(cs) == null)
        //        throw new Exception("No OID on code system "+cs.getUrl());
      }
    }
  }

  private void generateConceptMaps() throws Exception {
    List<ConceptMap> list = new ArrayList<>();
    page.getConceptMaps().listAll(list);
    for (ConceptMap cm : list) {
      if (cm.hasUserData("generate")) {
        generateConceptMap(cm);
      }
    }
    
  }
  private void generateConceptMap(ConceptMap cm) throws Exception {
    String filename = cm.getUserString("path");
    RenderingContext lrc = page.getRc().copy().setLocalPrefix("").setTooCostlyNoteEmpty(PageProcessor.TOO_MANY_CODES_TEXT_EMPTY).setTooCostlyNoteNotEmpty(PageProcessor.TOO_MANY_CODES_TEXT_NOT_EMPTY);
    RendererFactory.factory(cm, lrc).render(cm);

    IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, ".json"));
    json.compose(s, cm);
    s.close();
    json = new JsonParser().setOutputStyle(OutputStyle.CANONICAL);
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, ".canonical.json"));
    json.compose(s, cm);
    s.close();
    String n = Utilities.changeFileExt(filename, "");
    jsonToXhtml(n, cm.getName(), resource2Json(cm), "conceptmap-instance", "Concept Map", null, wg("vocab"));
    ttlToXhtml(n, cm.getName(), resource2Ttl(cm), "conceptmap-instance", "Concept Map", null, wg("vocab"));
    IParser xml = new XmlParser().setOutputStyle(OutputStyle.PRETTY);
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, ".xml"));
    xml.compose(s, cm);
    s.close();
    xml = new XmlParser().setOutputStyle(OutputStyle.CANONICAL);
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, ".canonical.xml"));
    xml.compose(s, cm);
    s.close();
    cloneToXhtml(n, cm.getName(), false, "conceptmap-instance", "Concept Map", null, wg("vocab"));

    // now, we create an html page from the narrative
    String narrative = new XhtmlComposer(XhtmlComposer.HTML).compose(cm.getText().getDiv());
    String html = TextFile.fileToString(page.getFolders().templateDir + "template-example.html").replace("<%example%>", narrative);
    html = page.processPageIncludes(Utilities.changeFileExt(filename, ".html"), html, "conceptmap-instance", null, null, null, "Concept Map", null, null, wg("vocab"));
    TextFile.stringToFile(html, page.getFolders().dstDir + Utilities.changeFileExt(filename, ".html"));

    conceptMapsFeed.getEntry().add(new BundleEntryComponent().setResource(cm).setFullUrl("http://hl7.org/fhir/"+cm.fhirType()+"/"+cm.getId()));
    page.getConceptMaps().see(cm, page.packageInfo());
    page.getHTMLChecker().registerFile(n + ".html", cm.getName(), HTMLLinkChecker.XHTML_TYPE, false);
  }

  public static Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
    Map<String, String> query_pairs = new LinkedHashMap<String, String>();
    String query = url.getQuery();
    String[] pairs = query.split("&");
    for (String pair : pairs) {
      int idx = pair.indexOf("=");
      query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
    }
    return query_pairs;
  }

  @Override
  public javax.xml.transform.Source resolve(String href, String base) throws TransformerException {
    if (!href.startsWith("http://fhir.healthintersections.com.au/open/ValueSet/$expand"))
      return null;
    try {
      Map<String, String> params = splitQuery(new URL(href));
      ValueSet vs = page.getValueSets().get(params.get("identifier"));
      if (vs == null) {
        page.log("unable to resolve "+params.get("identifier"), LogMessageType.Process);
        return null;
      }
      vs = page.expandValueSet(vs, true);
      if (vs == null) {
        page.log("unable to expand "+params.get("identifier"), LogMessageType.Process);
        return null;
      }
      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      new XmlParser().compose(bytes, vs, false);
      bytes.close();
      return new StreamSource(new ByteArrayInputStream(bytes.toByteArray()));
    } catch (Exception e) {
      throw new TransformerException(e);
    }
  }

}

