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
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
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
import org.hl7.fhir.definitions.generators.specification.XPathQueryGenerator;
import org.hl7.fhir.definitions.generators.specification.XmlSpecGenerator;
import org.hl7.fhir.definitions.generators.xsd.SchemaGenerator;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.Compartment;
import org.hl7.fhir.definitions.model.ConstraintStructure;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.DefinedStringPattern;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.Definitions.PageInformation;
import org.hl7.fhir.definitions.model.Dictionary;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.Example.ExampleType;
import org.hl7.fhir.definitions.model.ImplementationGuideDefn;
import org.hl7.fhir.definitions.model.LogicalModel;
import org.hl7.fhir.definitions.model.Operation;
import org.hl7.fhir.definitions.model.OperationParameter;
import org.hl7.fhir.definitions.model.PrimitiveType;
import org.hl7.fhir.definitions.model.Profile;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameterDefn;
import org.hl7.fhir.definitions.model.SearchParameterDefn.SearchType;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.WorkGroup;
import org.hl7.fhir.definitions.parsers.SourceParser;
import org.hl7.fhir.definitions.validation.ConceptMapValidator;
import org.hl7.fhir.definitions.validation.ResourceValidator;
import org.hl7.fhir.definitions.validation.ValueSetValidator;
import org.hl7.fhir.instance.formats.FormatUtilities;
import org.hl7.fhir.instance.formats.IParser;
import org.hl7.fhir.instance.formats.IParser.OutputStyle;
import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.instance.model.Bundle.BundleType;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.ConceptMap;
import org.hl7.fhir.instance.model.ConceptMap.ConceptMapContactComponent;
import org.hl7.fhir.instance.model.ConceptMap.SourceElementComponent;
import org.hl7.fhir.instance.model.ConceptMap.TargetElementComponent;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.Conformance.ConformanceRestComponent;
import org.hl7.fhir.instance.model.Conformance.ConformanceRestResourceComponent;
import org.hl7.fhir.instance.model.Conformance.ConformanceRestResourceSearchParamComponent;
import org.hl7.fhir.instance.model.Conformance.ConformanceStatementKind;
import org.hl7.fhir.instance.model.Conformance.ResourceInteractionComponent;
import org.hl7.fhir.instance.model.Conformance.RestfulConformanceMode;
import org.hl7.fhir.instance.model.Conformance.SystemInteractionComponent;
import org.hl7.fhir.instance.model.Conformance.SystemRestfulInteraction;
import org.hl7.fhir.instance.model.Conformance.TypeRestfulInteraction;
import org.hl7.fhir.instance.model.Conformance.UnknownContentCode;
import org.hl7.fhir.instance.model.ContactPoint;
import org.hl7.fhir.instance.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.instance.model.DataElement;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.Enumerations.ConceptMapEquivalence;
import org.hl7.fhir.instance.model.Enumerations.ConformanceResourceStatus;
import org.hl7.fhir.instance.model.Enumerations.SearchParamType;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.InstantType;
import org.hl7.fhir.instance.model.Meta;
import org.hl7.fhir.instance.model.NamingSystem;
import org.hl7.fhir.instance.model.Narrative;
import org.hl7.fhir.instance.model.Narrative.NarrativeStatus;
import org.hl7.fhir.instance.model.OperationDefinition;
import org.hl7.fhir.instance.model.OperationDefinition.OperationDefinitionParameterComponent;
import org.hl7.fhir.instance.model.OperationDefinition.OperationKind;
import org.hl7.fhir.instance.model.OperationDefinition.OperationParameterUse;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Questionnaire;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ResourceType;
import org.hl7.fhir.instance.model.SearchParameter;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionDesignationComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetFilterComponent;
import org.hl7.fhir.instance.model.ValueSet.FilterOperator;
import org.hl7.fhir.instance.model.ValueSet.ValueSetComposeComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetContactComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetCodeSystemComponent;
import org.hl7.fhir.instance.model.OperationOutcome.IssueType;
import org.hl7.fhir.instance.terminologies.LoincToDEConvertor;
import org.hl7.fhir.instance.terminologies.ValueSetUtilities;
import org.hl7.fhir.instance.utils.NarrativeGenerator;
import org.hl7.fhir.instance.utils.ProfileComparer;
import org.hl7.fhir.instance.utils.ProfileComparer.ProfileComparison;
import org.hl7.fhir.instance.utils.ProfileUtilities;
import org.hl7.fhir.instance.utils.QuestionnaireBuilder;
import org.hl7.fhir.instance.utils.ResourceUtilities;
import org.hl7.fhir.instance.utils.ToolingExtensions;
import org.hl7.fhir.instance.validation.IResourceValidator.BestPracticeWarningLevel;
import org.hl7.fhir.instance.validation.InstanceValidator;
import org.hl7.fhir.instance.validation.ProfileValidator;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.rdf.RDFValidator;
import org.hl7.fhir.tools.implementations.XMLToolsGenerator;
import org.hl7.fhir.tools.implementations.csharp.CSharpGenerator;
import org.hl7.fhir.tools.implementations.delphi.DelphiGenerator;
import org.hl7.fhir.tools.implementations.emf.EMFGenerator;
import org.hl7.fhir.tools.implementations.java.JavaGenerator;
import org.hl7.fhir.tools.implementations.javascript.JavaScriptGenerator;
import org.hl7.fhir.tools.publisher.Publisher.DocumentHolder;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.CloseProtectedZipInputStream;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Logger.LogMessageType;
import org.hl7.fhir.utilities.SchemaInputSource;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlDocument;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.hl7.fhir.utilities.xml.NamespaceContextMap;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.hl7.fhir.utilities.xml.XhtmlGenerator;
import org.hl7.fhir.utilities.xml.XmlGenerator;
import org.stringtemplate.v4.ST;
import org.tigris.subversion.javahl.ClientException;
import org.tigris.subversion.javahl.SVNClient;
import org.tigris.subversion.javahl.Status;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

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
public class Publisher implements URIResolver {

  public class DocumentHolder {

    public XhtmlDocument doc;

  }

  public static class Fragment {
    private String type;
    private String xml;
    private String page;

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

  }

  public static class ExampleReference {
    private final String type;
    private final String id;
    private final String path;

    public ExampleReference(String type, String id, String path) {
      super();
      this.type = type;
      this.id = id;
      this.path = path;
    }

    public String describe() {
      return type + "|" + id;
    }

    public String getType() {
      return type;
    }

    public String getId() {
      return id;
    }

    public String getPath() {
      return path;
    }

  }

  private static final String HTTP_separator = "/";

  private SourceParser prsr;
  private PageProcessor page;
  // private BookMaker book;

  private JavaGenerator javaReferencePlatform;
  private DelphiGenerator delphiReferencePlatform;

  private boolean isGenerate;
  private boolean noArchive;
  private boolean web;
  private String diffProgram;
  private Bundle profileFeed;
  private Bundle profileOthersFeed;
  private Bundle typeFeed;
  private Bundle valueSetsFeed;
  private Bundle conceptMapsFeed;
  private Bundle v2Valuesets;
  private Bundle v3Valuesets;
  private Bundle dataElements;
  private boolean noPartialBuild;
  private List<Fragment> fragments = new ArrayList<Publisher.Fragment>();
  private Map<String, String> xmls = new HashMap<String, String>();
  private Map<String, String> jsons = new HashMap<String, String>();
  private Map<String, Long> dates = new HashMap<String, Long>();
  private Map<String, Boolean> buildFlags = new HashMap<String, Boolean>();
  private IniFile cache;
  private WebMaker wm;
  private String svnStated;
  private String singleResource;
  private String singlePage;

  private Map<String, Example> processingList = new HashMap<String, Example>();
  
  private boolean genRDF;
  int errorCount = 0;
  int warningCount = 0;
  int informationCount = 0;


  public static void main(String[] args) throws Exception {
    //

    Publisher pub = new Publisher();
    pub.page = new PageProcessor(PageProcessor.DEV_TS_SERVER); 
    pub.isGenerate = !(args.length > 1 && hasParam(args, "-nogen"));
    if (hasParam(args, "-rdf")) {
      pub.isGenerate = false;
      pub.genRDF = true; 
    }
    pub.noArchive = (args.length > 1 && hasParam(args, "-noarchive"));
    pub.web = (args.length > 1 && hasParam(args, "-web"));
    pub.diffProgram = getNamedParam(args, "-diff");
    pub.noPartialBuild = (args.length > 1 && hasParam(args, "-nopartial"));
    if (hasParam(args, "-resource"))
      pub.singleResource = getNamedParam(args, "-resource");
    if (hasParam(args, "-page"))
      pub.singlePage = getNamedParam(args, "-page");
    if (hasParam(args, "-name"))
      pub.page.setPublicationType(getNamedParam(args, "-name"));
    if (hasParam(args, "-url"))
      pub.page.setBaseURL(getNamedParam(args, "-url"));
    if (hasParam(args, "-svn"))
      pub.page.setSvnRevision(getNamedParam(args, "-svn"));
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
    String dir = hasParam(args, "-folder") ? getNamedParam(args, "-folder") : System.getProperty("user.dir");
    pub.execute(dir);
  }

  /**
   * Invokes the SVN API to find out the current revision number for SVN,
   * returns ????
   *
   * @param folder
   * @return the revision number, or "????" if SVN was not available
   */
  private static String checkSubversion(String folder) {
    SVNClient svnClient = new SVNClient();
    Status[] status;
    try {
      status = svnClient.status(folder, true, false, true);
      long revNumber = 0;
      for (Status stat : status)
        revNumber = (revNumber < stat.getRevisionNumber()) ? stat.getRevisionNumber() : revNumber;
        return Long.toString(revNumber);
    } catch (ClientException e) {
      System.out.println("Warning @ Unable to read the SVN version number: " + e.getMessage() );
      return "????";
    }

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
   * @throws Exception
   */
  public void execute(String folder) {
    page.log("Publish FHIR in folder " + folder + " @ " + Config.DATE_FORMAT().format(page.getGenDate().getTime()), LogMessageType.Process);
    if (web)
      page.log("Build final copy for HL7 web site", LogMessageType.Process);
    else
      page.log("Build local copy", LogMessageType.Process);
    try { 
      page.setFolders(new FolderManager(folder));

      if (isGenerate && page.getSvnRevision() == null)
        page.setSvnRevision(checkSubversion(folder));
      registerReferencePlatforms();

      if (!initialize(folder))
        throw new Exception("Unable to publish as preconditions aren't met");

      page.log("Version " + page.getVersion() + "-" + page.getSvnRevision(), LogMessageType.Hint);

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
        Utilities.tone(1000, 10);
        Utilities.tone(1400, 10);
        Utilities.tone(1800, 10);
        Utilities.tone(1000, 10);
        Utilities.tone(1400, 10);
        Utilities.tone(1800, 10);
        page.log("Partial Build (if you want a full build, just run the build again)", LogMessageType.Process);
        CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
        for (String n : buildFlags.keySet())
          if (buildFlags.get(n))
            b.append(n);
        page.log("  Build: "+b.toString(), LogMessageType.Process);
      } else {
        Utilities.tone(1200, 30);
        page.log("Full Build", LogMessageType.Process);
      }
      Utilities.createDirectory(page.getFolders().dstDir);
      Utilities.deleteTempFiles();

      page.getBreadCrumbManager().parse(page.getFolders().srcDir + "heirarchy.xml");
      page.loadSnomed();
      page.loadLoinc();

      prsr.parse(page.getGenDate(), page.getValidationErrors());

      if (buildFlags.get("all")) {
        copyStaticContent();
      }
      loadValueSets1();
      prsr.getRegistry().commit();


      validate();
      processProfiles();
      checkAllOk();

      if (isGenerate) {
        if (web) {
          page.log("Clear Directory", LogMessageType.Process);
          Utilities.clearDirectory(page.getFolders().dstDir);
        }
        Utilities.createDirectory(page.getFolders().dstDir + "html");
        Utilities.createDirectory(page.getFolders().dstDir + "examples");
        for (ImplementationGuideDefn ig : page.getDefinitions().getSortedIgs())
          if (!ig.isCore())
            Utilities.createDirectory(page.getFolders().dstDir + ig.getCode());
          

        String eCorePath = page.getFolders().dstDir + "ECoreDefinitions.xml";
        generateECore(prsr.getECoreParseResults(), eCorePath);
        produceSpecification(eCorePath);
        checkAllOk();
      } else if (genRDF) 
        processRDF();

      validationProcess();
      processWarnings();
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
      Utilities.tone(800, 10);
      Utilities.tone(1000, 10);
      Utilities.tone(1200, 10);
      Utilities.tone(1000, 10);
      Utilities.tone(800, 10);
      page.log("Finished publishing FHIR @ " + Config.DATE_FORMAT().format(Calendar.getInstance().getTime()), LogMessageType.Process);
    } catch (Exception e) {

      if (!(e instanceof NullPointerException)) { // because this is unexpected...
        try {
          processWarnings();
        } catch (Exception e2) {
          page.log("  ERROR: Unable to process warnings: " + e.getMessage(), LogMessageType.Error);
          e.printStackTrace();
        }
      }
      if (!buildFlags.get("all")) {
        page.log("This was a Partial Build", LogMessageType.Process);
        CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
        for (String n : buildFlags.keySet())
          if (buildFlags.get(n))
            b.append(n);
        page.log("  Build: "+b.toString(), LogMessageType.Process);
      } else
        page.log("This was a Full Build", LogMessageType.Process);
      Utilities.tone(800, 20);
      Utilities.tone(1000, 20);
      Utilities.tone(1200, 20);
      try {
        Thread.sleep(50);
      } catch (InterruptedException e1) {
      }
      Utilities.tone(800, 20);
      Utilities.tone(1000, 20);
      Utilities.tone(1200, 20);
      try {
        Thread.sleep(50);
      } catch (InterruptedException e1) {
      }
      Utilities.tone(800, 20);
      Utilities.tone(1000, 20);
      Utilities.tone(1200, 20);
      page.log("FHIR build failure @ " + Config.DATE_FORMAT().format(Calendar.getInstance().getTime()), LogMessageType.Process);
      System.out.println("Error: " + e.getMessage());
      e.printStackTrace();
      System.exit(1);
    }
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
      if (vs.hasCodeSystem())
        page.getCodeSystems().put(vs.getCodeSystem().getSystem(), vs);
      page.getValueSets().put(ae.getId(), vs);
    }
    if (ae instanceof ConceptMap)
      page.getConceptMaps().put(ae.getId(), (ConceptMap) ae);

    if (ae instanceof StructureDefinition)  {
      StructureDefinition sd = (StructureDefinition) ae;
      if (page.getProfiles().containsKey(sd.getUrl()))
        throw new Exception("Duplicate Profile URL "+sd.getUrl());
      page.getProfiles().put(sd.getUrl(), sd);
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
    for (TypeDefn t : page.getDefinitions().getTypes().values())
      genTypeProfile(t);
    for (TypeDefn t : page.getDefinitions().getStructures().values())
      genTypeProfile(t);
    for (TypeDefn t : page.getDefinitions().getInfrastructure().values())
      genTypeProfile(t);

    page.log(" ...process profiles (resources)", LogMessageType.Process);

    for (ResourceDefn r : page.getDefinitions().getBaseResources().values()) {
        r.setConformancePack(makeConformancePack(r));
        r.setProfile(new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), dataElements).generate(r.getConformancePack(), r, "core"));
        if (page.getProfiles().containsKey(r.getProfile().getUrl()))
          throw new Exception("Duplicate Profile URL "+r.getProfile().getUrl());
        page.getProfiles().put(r.getProfile().getUrl(), r.getProfile());
        ResourceTableGenerator rtg = new ResourceTableGenerator(page.getFolders().dstDir, page, null, true);
        r.getProfile().getText().setDiv(new XhtmlNode(NodeType.Element, "div"));
        r.getProfile().getText().getDiv().getChildNodes().add(rtg.generate(r.getRoot(), ""));
    }

    for (ResourceDefn r : page.getDefinitions().getResources().values()) {
      r.setConformancePack(makeConformancePack(r));
      r.setProfile(new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), dataElements).generate(r.getConformancePack(), r, "core"));
      if (page.getProfiles().containsKey(r.getProfile().getUrl()))
        throw new Exception("Duplicate Profile URL "+r.getProfile().getUrl());
      page.getProfiles().put(r.getProfile().getUrl(), r.getProfile());
      ResourceTableGenerator rtg = new ResourceTableGenerator(page.getFolders().dstDir, page, null, true);
      r.getProfile().getText().setDiv(new XhtmlNode(NodeType.Element, "div"));
      r.getProfile().getText().getDiv().getChildNodes().add(rtg.generate(r.getRoot(), ""));
    }

    for (ProfiledType pt : page.getDefinitions().getConstraints().values()) {
      genProfiledTypeProfile(pt);
    }

    page.log(" ...process profiles (extensions)", LogMessageType.Process);
    for (StructureDefinition ex : page.getWorkerContext().getExtensionDefinitions().values())
      processExtension(ex);

    for (ResourceDefn r : page.getDefinitions().getResources().values()) {
//      boolean logged = false;
      for (Profile ap : r.getConformancePackages()) {
//        if (!logged)
//          page.log(" ...  resource "+r.getName(), LogMessageType.Process);
//        logged = true;
        for (ConstraintStructure p : ap.getProfiles())
          processProfile(ap, p, ap.getId());
      }
    }
    
    page.log(" ...process profiles (packs)", LogMessageType.Process);
    // we have profiles scoped by resources, and stand alone profiles
    for (Profile ap : page.getDefinitions().getPackList()) { 
//      page.log(" ...  pack "+ap.getId(), LogMessageType.Process);
      for (ConstraintStructure p : ap.getProfiles())
        processProfile(ap, p, ap.getId());
    }
    

    // now, validate the profiles
    for (Profile ap : page.getDefinitions().getPackList())
      for (ConstraintStructure p : ap.getProfiles())
        validateProfile(p);
    for (ResourceDefn r : page.getDefinitions().getResources().values())
      for (Profile ap : r.getConformancePackages())
        for (ConstraintStructure p : ap.getProfiles())
          validateProfile(p);
  }

  private void processExtension(StructureDefinition ex) throws Exception {
    StructureDefinition bd = page.getDefinitions().getSnapShotForBase(ex.getBase());
    new ProfileUtilities(page.getWorkerContext()).generateSnapshot(bd, ex, ex.getUrl(), ex.getName(), page, page.getValidationErrors());
  }

  private Profile makeConformancePack(ResourceDefn r) {
    Profile result = new Profile("core");
    result.setTitle("Base Profile for "+r.getName());
    return result;
  }

  private void validateProfile(StructureDefinition rd) throws Exception {
    ProfileValidator pv = new ProfileValidator();
    pv.setContext(page.getWorkerContext());
    List<String> errors = pv.validate(rd);
    if (errors.size() > 0) {
      for (String e : errors)
        page.log(e, LogMessageType.Error);
      throw new Exception("Error validating " + rd.getName());
    }
  }

  private void validateProfile(ConstraintStructure p) throws Exception {
    ProfileValidator pv = new ProfileValidator();
    pv.setContext(page.getWorkerContext());
    List<String> errors = pv.validate(p.getResource());
    if (errors.size() > 0) {
      for (String e : errors)
        page.log(e, LogMessageType.Error);
      throw new Exception("Error validating " + p.getId());
    }
  }

  private void genProfiledTypeProfile(ProfiledType pt) throws Exception {
    StructureDefinition profile = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), dataElements).generate(pt, page.getValidationErrors());
    if (page.getProfiles().containsKey(profile.getUrl()))
      throw new Exception("Duplicate Profile URL "+profile.getUrl());
    page.getProfiles().put(profile.getUrl(), profile);
    pt.setProfile(profile);
    page.getProfiles().put(profile.getUrl(), profile);
    page.getProfiles().put(profile.getName(), profile);
    // todo: what to do in the narrative?
  }

  private void genPrimitiveTypeProfile(PrimitiveType t) throws Exception {
    StructureDefinition profile = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), dataElements).generate(t);
    if (page.getProfiles().containsKey(profile.getUrl()))
      throw new Exception("Duplicate Profile URL "+profile.getUrl());
    page.getProfiles().put(profile.getUrl(), profile);
    page.getProfiles().put(profile.getName(), profile);
    t.setProfile(profile);

    //    DataTypeTableGenerator dtg = new DataTypeTableGenerator(page.getFolders().dstDir, page, t.getCode(), true);
    //    t.setProfile(profile);
    //    t.getProfile().getText().setDiv(new XhtmlNode(NodeType.Element, "div"));
    //    t.getProfile().getText().getDiv().getChildNodes().add(dtg.generate(t));
  }


  private void genPrimitiveTypeProfile(DefinedStringPattern t) throws Exception {
    StructureDefinition profile = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), dataElements).generate(t);
    if (page.getProfiles().containsKey(profile.getUrl()))
      throw new Exception("Duplicate Profile URL "+profile.getUrl());
    page.getProfiles().put(profile.getUrl(), profile);
    page.getProfiles().put(profile.getName(), profile);
    t.setProfile(profile);
    //    DataTypeTableGenerator dtg = new DataTypeTableGenerator(page.getFolders().dstDir, page, t.getCode(), true);
    //    t.setProfile(profile);
    //    t.getProfile().getText().setDiv(new XhtmlNode(NodeType.Element, "div"));
    //    t.getProfile().getText().getDiv().getChildNodes().add(dtg.generate(t));
  }


  private void genTypeProfile(TypeDefn t) throws Exception {
    StructureDefinition profile;
    try {
      profile = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), dataElements).generate(t);
      page.getProfiles().put(profile.getUrl(), profile);
      t.setProfile(profile);
      DataTypeTableGenerator dtg = new DataTypeTableGenerator(page.getFolders().dstDir, page, t.getName(), true);
      t.getProfile().getText().setDiv(new XhtmlNode(NodeType.Element, "div"));
      t.getProfile().getText().getDiv().getChildNodes().add(dtg.generate(t));
    } catch (Exception e) {
      throw new Exception("Error generating profile for '"+t.getName()+"': "+e.getMessage(), e);
    }
  }

  private void processProfile(Profile ap, ConstraintStructure profile, String filename) throws Exception {
//    page.log(" ...   profile "+profile.getId(), LogMessageType.Process);
    
    // they've either been loaded from spreadsheets, or from profile declarations
    // what we're going to do:
    //  create StructureDefinition structures if needed (create differential definitions from spreadsheets)
    if (profile.getResource() == null) {
      StructureDefinition p = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), dataElements).generate(ap, profile, profile.getDefn(), profile.getId(), profile.getUsage(), page.getValidationErrors());
      p.setUserData("pack", ap);
      profile.setResource(p);
      if (page.getProfiles().containsKey(p.getUrl()))
        throw new Exception("Duplicate Profile URL "+p.getUrl());
      page.getProfiles().put(p.getUrl(), p);
    } else {
      profile.getResource().setUserData("pack", ap);
      // special case: if the profile itself doesn't claim a date, it's date is the date of this publication
      if (!profile.getResource().hasDate())
        profile.getResource().setDate(page.getGenDate().getTime());
        if (profile.getResource().hasBase() && !profile.getResource().hasSnapshot()) {
          // cause it probably doesn't, coming from the profile directly
          StructureDefinition base = getSnapShotForProfile(profile.getResource().getBase());
          new ProfileUtilities(page.getWorkerContext()).generateSnapshot(base, profile.getResource(), profile.getResource().getBase().split("#")[0], profile.getResource().getName(), page, page.getValidationErrors());
        }
        if (page.getProfiles().containsKey(profile.getResource().getUrl()))
          throw new Exception("Duplicate Profile URL "+profile.getResource().getUrl());
        page.getProfiles().put(profile.getResource().getUrl(), profile.getResource());
      }
    if (!Utilities.noString(filename))
      profile.getResource().setUserData("filename", filename+".html");
    if (Utilities.noString(profile.getResource().getUserString("path")))
      profile.getResource().setUserData("path", filename+".html");
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
      } else
        throw new Exception("unable to find base definition for "+name);
    }
    StructureDefinition p = new ProfileUtilities(page.getWorkerContext()).getProfile(null, parts[0]);
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
        StructureDefinition ps = getSnapShotForProfile(pc.getBase());
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
    if (ae.hasBase() && ae.hasSnapshot()) {
      // cause it probably doesn't, coming from the profile directly
      StructureDefinition base = getIgProfile(ae.getBase());
      if (base == null)
        base = new ProfileUtilities(page.getWorkerContext()).getProfile(null, ae.getBase());
      new ProfileUtilities(page.getWorkerContext()).generateSnapshot(base, ae, ae.getBase().split("#")[0], ae.getName(), page, page.getValidationErrors());
      if (page.getProfiles().containsKey(ae.getUrl()))
        throw new Exception("Duplicate Profile URL "+ae.getUrl());
      page.getProfiles().put(ae.getUrl(), ae);
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
        StructureDefinition ps = getSnapShotForProfile(pc.getBase());
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
    r.close();
  }

  private void loadValueSets1() throws Exception {
    buildFeedsAndMaps();

    page.log(" ...vocab #1", LogMessageType.Process);
    new ValueSetImporterV2(page, page.getValidationErrors()).execute();
    analyseV3();
    generateValueSetsPart1();
    for (BindingSpecification cd : page.getDefinitions().getUnresolvedBindings()) {
      String ref = cd.getReference();
      if (ref.startsWith("http://hl7.org/fhir")) {
        // we expect to be able to resolve this
        ValueSet vs = page.getDefinitions().getValuesets().get(ref);
        if (vs == null)
          vs = page.getWorkerContext().getValueSets().get(ref);            
        if (vs == null) {
          if (page.getDefinitions().getBoundValueSets().containsKey(ref))
            throw new Exception("Unable to resolve the value set reference "+ref+" but found it in load list");
          throw new Exception("Unable to resolve the value set reference "+ref);
        }
        cd.setValueSet(vs);
      }
    }
    for (ImplementationGuideDefn ig : page.getDefinitions().getSortedIgs()) {
      for (BindingSpecification cd : ig.getUnresolvedBindings()) {
        ValueSet vs = page.getDefinitions().getValuesets().get(cd.getReference());
        if (vs == null)
          vs = ig.getValueSet(cd.getReference());
        if (vs == null)
          vs = page.getWorkerContext().getValueSets().get(cd.getReference());            
        if (vs == null)
          throw new Exception("unable to resolve value set "+cd.getReference());
        cd.setValueSet(vs);
      }
    }
  }

  private void loadValueSets2() throws Exception {
    page.log(" ...default conformance statements", LogMessageType.Process);
   
    if (isGenerate) {
      generateConformanceStatement(true, "base");
      generateConformanceStatement(false, "base2");
    }
    page.log(" ...resource ValueSet", LogMessageType.Process);
    ResourceDefn r = page.getDefinitions().getResources().get("ValueSet");
    if (isGenerate && wantBuild("ValueSet")) {
      produceResource1(r, false);
      produceResource2(r, false, null);
    }
    page.log(" ...value sets", LogMessageType.Process);
    generateValueSetsPart2();
    page.saveSnomed();
    if (isGenerate) {
      /// regenerate. TODO: this is silly - need to generate before so that xpaths are populated. but need to generate now to fill them properly
      generateConformanceStatement(true, "base");
      generateConformanceStatement(false, "base2");
    }

  }
  private void buildFeedsAndMaps() {
    profileFeed = new Bundle();
    profileFeed.setId("resources");
    profileFeed.setType(BundleType.COLLECTION);
    profileFeed.setMeta(new Meta().setLastUpdated(page.getGenDate().getTime()));

    profileOthersFeed = new Bundle();
    profileOthersFeed.setId("profiles-others");
    profileOthersFeed.setType(BundleType.COLLECTION);
    profileOthersFeed.setMeta(new Meta().setLastUpdated(page.getGenDate().getTime()));

    typeFeed = new Bundle();
    typeFeed.setId("types");
    typeFeed.setType(BundleType.COLLECTION);
    typeFeed.setMeta(new Meta().setLastUpdated(page.getGenDate().getTime()));

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

    v2Valuesets = new Bundle();
    v2Valuesets.setType(BundleType.COLLECTION);
    v2Valuesets.setId("v2-valuesets");
    v2Valuesets.setMeta(new Meta().setLastUpdated(page.getGenDate().getTime()));
    page.setV2Valuesets(v2Valuesets);

    v3Valuesets = new Bundle();
    v3Valuesets.setType(BundleType.COLLECTION);
    v3Valuesets.setId("v3-valuesets");
    v3Valuesets.setMeta(new Meta().setLastUpdated(page.getGenDate().getTime()));
    page.setv3Valuesets(v3Valuesets);
  }

  private void generateConformanceStatement(boolean full, String name) throws Exception {
    Conformance conf = new Conformance();
    conf.setId(FormatUtilities.makeId(name));
    conf.setUrl("http://hl7.org/fhir/Conformance/" + name);
    conf.setVersion(page.getVersion() + "-" + page.getSvnRevision());
    conf.setName("Base FHIR Conformance Statement " + (full ? "(Full)" : "(Empty)"));
    conf.setPublisher("FHIR Project Team");
    conf.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.OTHER, "http://hl7.org/fhir"));
    conf.setStatus(ConformanceResourceStatus.DRAFT);
    conf.setDate(page.getGenDate().getTime());
    conf.setFhirVersion(page.getVersion());
    conf.setAcceptUnknown(full ? UnknownContentCode.BOTH : UnknownContentCode.NO);
    conf.getFormat().add(Factory.newCode("xml"));
    conf.getFormat().add(Factory.newCode("json"));
    conf.setKind(ConformanceStatementKind.CAPABILITY);
    conf.getSoftware().setName("Insert your softwware name here...");
    ConformanceRestComponent rest = new Conformance.ConformanceRestComponent();
    conf.getRest().add(rest);
    rest.setMode(RestfulConformanceMode.SERVER);
    if (full) {
      rest.setDocumentation("All the functionality defined in FHIR");
      conf.setDescription("This is the base conformance statement for FHIR. It represents a server that provides the full set of functionality defined by FHIR. It is provided to use as a template for system designers to build their own conformance statements from");
    } else {
      rest.setDocumentation("An empty conformance statement");
      conf.setDescription("This is the base conformance statement for FHIR. It represents a server that provides the none of the functionality defined by FHIR. It is provided to use as a template for system designers to build their own conformance statements from. A conformance profile has to contain something, so this contains a read of a Conformance Statement");
    }
    if (full) {
      genConfOp(conf, rest, SystemRestfulInteraction.TRANSACTION);
      genConfOp(conf, rest, SystemRestfulInteraction.HISTORYSYSTEM);
      genConfOp(conf, rest, SystemRestfulInteraction.SEARCHSYSTEM);

      for (String rn : page.getDefinitions().sortedResourceNames()) {
        ResourceDefn rd = page.getDefinitions().getResourceByName(rn);
        ConformanceRestResourceComponent res = new Conformance.ConformanceRestResourceComponent();
        rest.getResource().add(res);
        res.setType(rn);
        res.setProfile(Factory.makeReference("http://hl7.org/fhir/StructureDefinition/" + rn));
        genConfOp(conf, res, TypeRestfulInteraction.READ);
        genConfOp(conf, res, TypeRestfulInteraction.VREAD);
        genConfOp(conf, res, TypeRestfulInteraction.UPDATE);
        genConfOp(conf, res, TypeRestfulInteraction.DELETE);
        genConfOp(conf, res, TypeRestfulInteraction.HISTORYINSTANCE);
        genConfOp(conf, res, TypeRestfulInteraction.VALIDATE);
        genConfOp(conf, res, TypeRestfulInteraction.HISTORYTYPE);
        genConfOp(conf, res, TypeRestfulInteraction.CREATE);
        genConfOp(conf, res, TypeRestfulInteraction.SEARCHTYPE);

        for (SearchParameterDefn i : rd.getSearchParams().values()) {
          res.getSearchParam().add(makeSearchParam(conf, rn, i));
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
    } else {
      ConformanceRestResourceComponent res = new Conformance.ConformanceRestResourceComponent();
      rest.getResource().add(res);
      res.setType("Conformance");
      genConfOp(conf, res, TypeRestfulInteraction.READ);
    }

    NarrativeGenerator gen = new NarrativeGenerator("", "", page.getWorkerContext());
    gen.generate(conf);
    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + "conformance-" + name + ".xml");
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, conf);
    s.close();
    cloneToXhtml("conformance-" + name + "", "Basic Conformance Statement", true, "resource-instance:Conformance", "Conformance Statement");
    s = new FileOutputStream(page.getFolders().dstDir + "conformance-" + name + ".json");
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, conf);
    s.close();

    jsonToXhtml("conformance-" + name, "Base Conformance Statement", resource2Json(conf), "resource-instance:Conformance", "Conformance Statement");

    Utilities.copyFile(new CSFile(page.getFolders().dstDir + "conformance-" + name + ".xml"), new CSFile(page.getFolders().dstDir + "examples" + File.separator
        + "conformance-" + name + ".xml"));
    if (buildFlags.get("all")) {
      deletefromFeed(ResourceType.Conformance, name, profileFeed);
      addToResourceFeed(conf, profileFeed);
    }
  }

  private ConformanceRestResourceSearchParamComponent makeSearchParam(Conformance p, String rn, SearchParameterDefn i) throws Exception {
    ConformanceRestResourceSearchParamComponent result = new Conformance.ConformanceRestResourceSearchParamComponent();
    result.setName(i.getCode());
    result.setDefinition("http://hl7.org/fhir/SearchParameter/"+rn.toLowerCase()+"-"+i.getCode().replace("-[x]", ""));
    result.setType(getSearchParamType(i.getType()));
    result.setDocumentation(i.getDescription());
    i.setXPath(new XPathQueryGenerator(page.getDefinitions(), page, page.getQa()).generateXpath(i.getPaths())); // used elsewhere later
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
    }
    return null;
  }

  private void genConfOp(Conformance conf, ConformanceRestResourceComponent res, TypeRestfulInteraction op) {
    ResourceInteractionComponent t = new ResourceInteractionComponent();
    t.setCode(op);
    res.getInteraction().add(t);
  }

  private void genConfOp(Conformance conf, ConformanceRestComponent res, SystemRestfulInteraction op) {
    SystemInteractionComponent t = new SystemInteractionComponent();
    t.setCode(op);
    res.getInteraction().add(t);
  }

  private void generateECore(org.hl7.fhir.definitions.ecore.fhir.Definitions eCoreDefinitions, String filename) throws IOException {
    org.eclipse.emf.ecore.xmi.XMLResource resource = new XMLResourceImpl();
    Map<String, String> options = new HashMap<String, String>();
    options.put(org.eclipse.emf.ecore.xmi.XMLResource.OPTION_ENCODING, "UTF-8");
    options.put(org.eclipse.emf.ecore.xmi.XMLResource.OPTION_XML_VERSION, "1.0");

    resource.getContents().add(eCoreDefinitions);
    FileOutputStream s = new FileOutputStream(filename);
    resource.save(s, options);
    s.close();
  }

  private void registerReferencePlatforms() {
    javaReferencePlatform = new JavaGenerator(page.getFolders());
    delphiReferencePlatform = new DelphiGenerator(page.getFolders());
    page.getReferenceImplementations().add(javaReferencePlatform);
    page.getReferenceImplementations().add(delphiReferencePlatform);
    page.getReferenceImplementations().add(new CSharpGenerator());
    page.getReferenceImplementations().add(new XMLToolsGenerator());
    page.getReferenceImplementations().add(new JavaScriptGenerator());
    page.getReferenceImplementations().add(new EMFGenerator());

    // page.getReferenceImplementations().add(new ECoreOclGenerator());
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
      if (!dates.containsKey(category) || d > dates.get(category))
        dates.put(category, d);
      return true;
    } else
      return true;
  }

  private boolean initialize(String folder) throws Exception {
    page.setDefinitions(new Definitions());

    page.log("Checking Source for " + folder, LogMessageType.Process);

    List<String> errors = new ArrayList<String>();

    Utilities.checkFolder(page.getFolders().rootDir, errors);
    if (checkFile("required", page.getFolders().rootDir, "publish.ini", errors, "all")) {
      checkFile("required", page.getFolders().srcDir, "navigation.xml", errors, "all");
      page.setIni(new IniFile(page.getFolders().rootDir + "publish.ini"));
      page.setVersion(page.getIni().getStringProperty("FHIR", "version"));

      prsr = new SourceParser(page, folder, page.getDefinitions(), web, page.getVersion(), page.getWorkerContext(), page.getGenDate(), page.getWorkerContext().getExtensionDefinitions(), page);
      prsr.checkConditions(errors, dates);
      page.setRegistry(prsr.getRegistry());

      for (String s : page.getIni().getPropertyNames("special-pages"))
        page.getDefinitions().getStructuralPages().add(s);
        
      Utilities.checkFolder(page.getFolders().xsdDir, errors);
      for (PlatformGenerator gen : page.getReferenceImplementations())
        Utilities.checkFolder(page.getFolders().implDir(gen.getName()), errors);
      checkFile("required", page.getFolders().srcDir, "heirarchy.xml", errors, "all");
      checkFile("required", page.getFolders().srcDir, "fhir-all.xsd", errors, "all");
      checkFile("required", page.getFolders().srcDir, "header.html", errors, "all");
      checkFile("required", page.getFolders().srcDir, "footer.html", errors, "all");
      checkFile("required", page.getFolders().srcDir, "template.html", errors, "all");
      checkFile("required", page.getFolders().srcDir, "template-book.html", errors, "all");
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
      checkBySchema(page.getFolders().rootDir + "implementations" + File.separator + "translations.xml", new String[] {page.getFolders().rootDir + "implementations" + File.separator + "translations.xsd"});
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
    ResourceValidator val = new ResourceValidator(page.getDefinitions(), page.getTranslations(), page.getCodeSystems(), page.getFolders().srcDir);

    for (String n : page.getDefinitions().getTypes().keySet())
      page.getValidationErrors().addAll(val.checkStucture(n, page.getDefinitions().getTypes().get(n)));
    for (String n : page.getDefinitions().getStructures().keySet())
      page.getValidationErrors().addAll(val.checkStucture(n, page.getDefinitions().getStructures().get(n)));
    for (String n : page.getDefinitions().sortedResourceNames())
      if (hasBuildFlag("page-" + n.toLowerCase()))
        page.getValidationErrors().addAll(val.check(n, page.getDefinitions().getResources().get(n)));

    for (String rname : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn r = page.getDefinitions().getResources().get(rname);
      checkExampleLinks(page.getValidationErrors(), r);
    }
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

  private void processWarnings() throws Exception {
    String xslt = Utilities.path(page.getFolders().rootDir, "implementations", "xmltools", "OwnerResources.xslt");
    OutputStreamWriter s = new OutputStreamWriter(new FileOutputStream(page.getFolders().dstDir + "warnings.xml"), "UTF-8");
    s.write("<warnings>");
    for (WorkGroup wg : page.getDefinitions().getWorkgroups().values()) {
      s.write("<wg code=\""+wg.getCode()+"\" name=\""+wg.getName()+"\" url=\""+wg.getUrl()+"\"/>\r\n");
    }
    for (PageInformation pn : page.getDefinitions().getPageInfo().values()) {
      s.write("<page name=\""+pn.getName()+"\" wg=\""+pn.getWgCode()+"\" fmm=\""+pn.getFmm()+"\"/>\r\n");
    }
    s.write(new String(Utilities.saxonTransform(page.getFolders().dstDir + "profiles-resources.xml", xslt)));
    s.write(new String(Utilities.saxonTransform(page.getFolders().dstDir + "profiles-types.xml", xslt)));
    s.write(new String(Utilities.saxonTransform(page.getFolders().dstDir + "profiles-others.xml", xslt)));

    for (ValidationMessage e : page.getValidationErrors()) {
    	s.write(e.toXML());
    }

    s.write("</warnings>");
    s.flush();
    s.close();

    String xslt2 = Utilities.path(page.getFolders().rootDir, "implementations", "xmltools", "CategorizeWarnings.xslt");
    FileOutputStream s2 = new FileOutputStream(page.getFolders().dstDir + "work-group-warnings.xml");
    s2.write(Utilities.saxonTransform(page.getFolders().dstDir + "warnings.xml", xslt2).getBytes("UTF8"));
    s2.flush();
    s2.close();

    String xslt3 = Utilities.path(page.getFolders().rootDir, "implementations", "xmltools", "RenderWarnings.xslt");
    page.log(Utilities.saxonTransform(page.getFolders().dstDir + "work-group-warnings.xml", xslt3), LogMessageType.Process);

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
            if (!ref.getId().startsWith("cid:") && !ref.getId().startsWith("urn:") && !ref.getId().startsWith("http:") && !resolveLink(ref, e)) {
              String path = ref.getPath().replace("/f:", ".").substring(1)+" (example "+e.getTitle()+")";
              errors.add(new ValidationMessage(Source.ExampleValidator, IssueType.BUSINESSRULE, -1, -1, path, 
                  "Unable to resolve example reference to " + ref.describe() + " in " + e.getTitle() + " (Possible Ids: " + listTargetIds(ref.getType())+")", 
                  "Unable to resolve example reference to " + ref.describe() + " in <a href=\""+e.getTitle() + ".html"+"\">" + e.getTitle() + "</a> (Possible Ids: " + listTargetIds(ref.getType())+")", 
                  IssueSeverity.WARNING));
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
    if (ref.getId().startsWith("#"))
      return true;
    if (!page.getDefinitions().hasResource(ref.getType()))
      return false;
    ResourceDefn r = page.getDefinitions().getResourceByName(ref.getType());
    for (Example e : r.getExamples()) {
      if (!ref.getId().startsWith("#")) {
        String id = ref.getId(); // extractId(ref.getId(), ref.getType());
        if (id.equals(e.getId())) {
          e.getInbounds().add(src);
          return true;
        }
        if (e.getXml() != null) {
          if (e.getXml().getDocumentElement().getLocalName().equals("feed")) {
            List<Element> entries = new ArrayList<Element>();
            XMLUtil.getNamedChildren(e.getXml().getDocumentElement(), "entry", entries);
            for (Element c : entries) {
              String _id = XMLUtil.getNamedChild(c, "id").getTextContent();
              if (id.equals(_id) || _id.equals("http://hl7.org/fhir/" + ref.getType() + "/" + id)) {
                e.getInbounds().add(src);
                return true;
              }
            }
          }
        }
      }
    }
    return false;
  }

  private String extractId(String id, String type) throws Exception {
    String[] parts = id.split("/");
    if (parts.length < 2)
      throw new Exception("The example reference '" + id + "' is not valid (not enough path parts");
    if (!parts[0].equals(type))
      throw new Exception("The example reference '" + id + "' is not valid (the type portion doesn't match the specified type '" + type + "')");
    if (parts[1].startsWith("@"))
      throw new Exception("The example reference '" + id + "' is not valid (the id shouldn't start with @)");
    if (parts[1].length() < 1 || parts[1].length() > 36)
      throw new Exception("The example reference '" + id + "' is not valid (id length 1 - 36)");
    if (!parts[1].matches(FormatUtilities.ID_REGEX))
      throw new Exception("The example reference '" + id + "' is not valid (id doesn't match regular expression for id)");
    if (parts.length > 2) {
      if (!parts[2].equals("history"))
        throw new Exception("The example reference '" + id + "' is not valid");
      if (parts.length != 4 || parts[3].startsWith("@"))
        throw new Exception("The example reference '" + id + "' is not valid");
      if (parts[3].length() < 1 || parts[3].length() > 36)
        throw new Exception("The example reference '" + id + "' is not valid (version id length 1 - 36)");
      if (!parts[3].matches(FormatUtilities.ID_REGEX))
        throw new Exception("The example reference '" + id + "' is not valid (version id doesn't match regular expression for id)");
    }
    return parts[1];
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
          String[] parts = XMLUtil.getNamedChildValue(m, "reference").split("\\/");
          if (parts.length > 0 && page.getDefinitions().hasResource(parts[0])) {
            refs.add(new ExampleReference(parts[0], parts[1], path));
          }
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

  private void produceSpecification(String eCorePath) throws Exception {
    page.setNavigation(new Navigation());
    page.getNavigation().parse(page.getFolders().srcDir + "navigation.xml");
    if (buildFlags.get("all")) {
      copyStaticContent();
    }
    for (ValueSet vs : page.getWorkerContext().getCodeSystems().values())
      if (!vs.hasCodeSystem())
        throw new Error("ValueSet "+vs.getName()+"/"+vs.getUrl()+" has no code system!");
        
    XMIResource resource = new XMIResourceImpl();
    resource.load(new CSFileInputStream(eCorePath), null);
    org.hl7.fhir.definitions.ecore.fhir.Definitions eCoreDefs = (org.hl7.fhir.definitions.ecore.fhir.Definitions) resource.getContents().get(0);

    processRDF();

    page.log("Produce Schemas", LogMessageType.Process);
    new SchemaGenerator().generate(page.getDefinitions(), page.getIni(), page.getFolders().tmpResDir, page.getFolders().xsdDir+"codegen"+File.separator, page.getFolders().dstDir,
        page.getFolders().srcDir, page.getVersion(), Config.DATE_FORMAT().format(page.getGenDate().getTime()), true);
    new SchemaGenerator().generate(page.getDefinitions(), page.getIni(), page.getFolders().tmpResDir, page.getFolders().xsdDir, page.getFolders().dstDir,
        page.getFolders().srcDir, page.getVersion(), Config.DATE_FORMAT().format(page.getGenDate().getTime()), false);

    if (buildFlags.get("all")) {
      for (PlatformGenerator gen : page.getReferenceImplementations()) {
        page.log("Produce " + gen.getName() + " Reference Implementation", LogMessageType.Process);

        String destDir = page.getFolders().dstDir;
        String implDir = page.getFolders().implDir(gen.getName());

        if (!gen.isECoreGenerator())
          gen.generate(page.getDefinitions(), destDir, implDir, page.getVersion(), page.getGenDate().getTime(), page, page.getSvnRevision());
        else
          gen.generate(eCoreDefs, destDir, implDir, page.getVersion(), page.getGenDate().getTime(), page, page.getSvnRevision());
      }
      for (PlatformGenerator gen : page.getReferenceImplementations()) {
        if (gen.doesCompile()) {
          page.log("Compile " + gen.getName() + " Reference Implementation", LogMessageType.Process);
          if (!gen.compile(page.getFolders().rootDir, new ArrayList<String>(), page, page.getValidationErrors())) {
            // Must always be able to compile Java to go on. Also, if we're
            // building
            // the web build, all generators that can compile, must compile
            // without error.
            if (gen.getName().equals("java") || web)
              throw new Exception("Compile " + gen.getName() + " failed");
            else
              page.log("Compile " + gen.getName() + " failed, still going on.", LogMessageType.Error);
          }
        }
      }
    }

    page.log("Produce Schematrons", LogMessageType.Process);
    for (String rname : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn r = page.getDefinitions().getResources().get(rname);
      String n = r.getName().toLowerCase();
      SchematronGenerator sch = new SchematronGenerator(new FileOutputStream(page.getFolders().dstDir + n + ".sch"), page);
      sch.generate(r, page.getDefinitions());
      sch.close();
    }

    SchematronGenerator sg = new SchematronGenerator(new FileOutputStream(page.getFolders().dstDir + "fhir-invariants.sch"), page);
    sg.generate(page.getDefinitions());
    sg.close();

    produceSchemaZip();
    for (ValueSet vs : page.getWorkerContext().getCodeSystems().values())
      if (!vs.hasCodeSystem())
        throw new Error("ValueSet "+vs.getName()+"/"+vs.getUrl()+" has no code system!");
    page.log("Produce Content", LogMessageType.Process);
    produceSpec();

    if (buildFlags.get("all")) {
      if (web) {
        if (!new File(page.getFolders().archiveDir).exists())
          throw new Exception("Unable to build HL7 copy with no archive directory (sync svn at one level up the tree)");

        page.log("Produce HL7 copy", LogMessageType.Process);
        wm = new WebMaker(page.getFolders(), page.getVersion(), page.getIni(), page.getDefinitions());
        wm.produceHL7Copy();
      }
      if (new File(page.getFolders().archiveDir).exists() && !noArchive) {
        page.log("Produce Archive copy", LogMessageType.Process);
        produceArchive();
      }
    }
  }

  private void processRDF() throws Exception, FileNotFoundException {
    // first, process the RIM file
    String rim = TextFile.fileToString(Utilities.path(page.getFolders().srcDir, "v3", "rim.ttl"));
    ByteArrayOutputStream tmp = new ByteArrayOutputStream();
    FhirTurtleGenerator ttl = new FhirTurtleGenerator(tmp, page.getDefinitions(), page.getWorkerContext(), page.getValidationErrors());
    ttl.executeV3(page.getV3Valuesets());
    rim = rim + tmp.toString();
    TextFile.stringToFile(rim, Utilities.path(page.getFolders().dstDir, "rim.ttl"));
    ttl = new FhirTurtleGenerator(new FileOutputStream(Utilities.path(page.getFolders().dstDir, "fhir.ttl")), page.getDefinitions(), page.getWorkerContext(), page.getValidationErrors());
    ttl.executeMain();
    RDFValidator val = new RDFValidator();
    val.validate(Utilities.path(page.getFolders().dstDir, "fhir.ttl"));
    val.validate(Utilities.path(page.getFolders().dstDir, "rim.ttl"));
    ZipGenerator zip = new ZipGenerator(Utilities.path(page.getFolders().dstDir, "fhir.rdf.zip"));
    zip.addFileName("fhir.ttl", Utilities.path(page.getFolders().dstDir, "fhir.ttl"), false);
    zip.addFileName("fhir.rdf.xml", Utilities.path(page.getFolders().dstDir, "fhir.rdf.xml"), false);
    zip.addFileName("rim.ttl", Utilities.path(page.getFolders().dstDir, "rim.ttl"), false);
    zip.addFileName("rim.rdf.xml", Utilities.path(page.getFolders().dstDir, "rim.rdf.xml"), false);
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
    for (StructureDefinition ed : page.getWorkerContext().getExtensionDefinitions().values()) {
      String filename = "extension-"+ed.getUrl().substring(40).toLowerCase();
      ed.setUserData("filename", filename);
      ImplementationGuideDefn ig = page.getDefinitions().getIgs().get(ed.getUserString(ToolResourceUtilities.NAME_RES_IG));
      ed.setUserData("path", (ig.isCore() ? "" : ig.getCode()+File.separator) + filename+".html");  
    }
    for (ValueSet vs : page.getWorkerContext().getCodeSystems().values())
      if (!vs.hasCodeSystem())
        throw new Error("ValueSet "+vs.getName()+"/"+vs.getUrl()+" has no code system!");

    loadValueSets2();
    page.log(" ...extensions", LogMessageType.Process);

    for (StructureDefinition ae : page.getWorkerContext().getExtensionDefinitions().values()) 
      produceExtensionDefinition(ae);
    checkAllOk();

    page.log(" ...resource identities", LogMessageType.Process);
    for (String rname : page.getDefinitions().getBaseResources().keySet()) {
      ResourceDefn r = page.getDefinitions().getBaseResources().get(rname);
      produceResource1(r, r.isAbstract());
    }
    for (String rname : page.getDefinitions().sortedResourceNames()) {
      if (!rname.equals("ValueSet") && wantBuild(rname)) {
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
      produceResource2(r, true, rname.equals("Resource") ? "Meta" : null);
    }
    for (String rname : page.getDefinitions().sortedResourceNames()) {
      if (!rname.equals("ValueSet") && wantBuild(rname)) {
        ResourceDefn r = page.getDefinitions().getResources().get(rname);
        page.log(" ...resource " + r.getName(), LogMessageType.Process);
        produceResource2(r, false, null);
      }
    }

    for (Compartment c : page.getDefinitions().getCompartments()) {
      if (buildFlags.get("all")) {
        page.log(" ...compartment " + c.getName(), LogMessageType.Process);
        produceCompartment(c);
      }
    }
    processExamplesByBatch();
    
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
      for (Profile p : ig.getProfiles()) {
        if (!p.getOperations().isEmpty()) {
          produceIgOperations(ig, p);
        }
      }
    }
    for (String n : page.getIni().getPropertyNames("ig-pages")) {
      page.log(" ...page " + n, LogMessageType.Process);
      for (ImplementationGuideDefn ig : page.getDefinitions().getSortedIgs()) {
        if (!ig.isCore())
        produceIgPage(n, ig, page.getIni().getStringProperty("ig-pages", n));
      }
    }
    for (String n : page.getDefinitions().getDictionaries().keySet()) {
      if (buildFlags.get("all")) { // || buildFlags.get("dict-" + n.toLowerCase())) {
        page.log(" ...dictionary " + n, LogMessageType.Process);
        produceDictionary(page.getDefinitions().getDictionaries().get(n));
      }
    }
    for (ImplementationGuideDefn ig : page.getDefinitions().getSortedIgs()) {
      for (LogicalModel lm : ig.getLogicalModels()) {
        page.log(" ...ig logical model " + lm.getId(), LogMessageType.Process);
        produceLogicalModel(lm, ig);        
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
          produceConformancePackage("", p, null);
        //}
      }
      processExamplesByBatch();

      produceV2();
      produceV3();
      page.getVsValidator().checkDuplicates(page.getValidationErrors());

      if (buildFlags.get("all")) {
        page.getToc().put("1.1", new TocEntry("1.1", "Table Of Contents", "toc.html"));
        page.log(" ...page toc.html", LogMessageType.Process);
        producePage("toc.html", null);
      }

      checkAllOk();

      page.log(" ...collections ", LogMessageType.Process);

      checkBundleURLs(profileFeed);
      FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + "profiles-resources.xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, profileFeed);
      s.close();
      s = new FileOutputStream(page.getFolders().dstDir + "profiles-resources.json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, profileFeed);
      s.close();
      checkBundleURLs(typeFeed);
      s = new FileOutputStream(page.getFolders().dstDir + "profiles-types.xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, typeFeed);
      s.close();
      s = new FileOutputStream(page.getFolders().dstDir + "profiles-types.json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, typeFeed);
      s.close();

      Bundle extensionsFeed = new Bundle();
      extensionsFeed.setId("extensions");
      extensionsFeed.setType(BundleType.COLLECTION);
      extensionsFeed.setMeta(new Meta().setLastUpdated(profileFeed.getMeta().getLastUpdated()));
      Set<String> urls = new HashSet<String>();
      for (StructureDefinition ed : page.getWorkerContext().getExtensionDefinitions().values()) {
        if (!urls.contains(ed.getUrl())) {
          urls.add(ed.getUrl());
          extensionsFeed.getEntry().add(new BundleEntryComponent().setResource(ed).setFullUrl(ed.getUrl()));
        }
      }
      checkBundleURLs(extensionsFeed);
      s = new FileOutputStream(page.getFolders().dstDir + "extension-definitions.xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, extensionsFeed);
      s.close();
      s = new FileOutputStream(page.getFolders().dstDir + "extension-definitions.json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, extensionsFeed);
      s.close();
      Utilities.copyFile(page.getFolders().dstDir + "extension-definitions.xml", page.getFolders().dstDir + "examples" + File.separator + "extension-definitions.xml");

      Bundle searchParamsFeed = new Bundle();
      searchParamsFeed.setId("searchParams");
      searchParamsFeed.setType(BundleType.COLLECTION);
      searchParamsFeed.setMeta(new Meta().setLastUpdated(profileFeed.getMeta().getLastUpdated()));
      for (ResourceDefn rd : page.getDefinitions().getBaseResources().values())
        addSearchParams(searchParamsFeed, rd);
      for (ResourceDefn rd : page.getDefinitions().getResources().values())
        addSearchParams(searchParamsFeed, rd);
      for (Profile cp : page.getDefinitions().getPackList()) {
        addSearchParams(searchParamsFeed, cp);
      }
      checkBundleURLs(searchParamsFeed);
      s = new FileOutputStream(page.getFolders().dstDir + "search-parameters.xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, searchParamsFeed);
      s.close();
      s = new FileOutputStream(page.getFolders().dstDir + "search-parameters.json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, searchParamsFeed);
      s.close();
      Utilities.copyFile(page.getFolders().dstDir + "search-parameters.xml", page.getFolders().dstDir + "examples" + File.separator + "search-parameters.xml");

      for (ResourceDefn rd : page.getDefinitions().getResources().values())
        addOtherProfiles(profileOthersFeed, rd);
      for (Profile cp : page.getDefinitions().getPackList()) {
        addOtherProfiles(profileOthersFeed, cp);
      }
      checkBundleURLs(profileOthersFeed);
      s = new FileOutputStream(page.getFolders().dstDir + "profiles-others.xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, profileOthersFeed);
      s.close();
      s = new FileOutputStream(page.getFolders().dstDir + "profiles-others.json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, profileOthersFeed);
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
//      if (delphiReferencePlatform.canSign())
//        delphiReferencePlatform.sign(page.getFolders().dstDir + "conceptmaps.xml", true, "dsa");

      checkBundleURLs(v2Valuesets);
      s = new FileOutputStream(page.getFolders().dstDir + "v2-tables.xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, v2Valuesets);
      s.close();
      Utilities.copyFile(page.getFolders().dstDir + "v2-tables.xml", page.getFolders().dstDir + "examples" + File.separator + "v2-tables.xml");
      s = new FileOutputStream(page.getFolders().dstDir + "v2-tables.json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, v2Valuesets);
      s.close();
      checkBundleURLs(v3Valuesets);
      s = new FileOutputStream(page.getFolders().dstDir + "v3-codesystems.xml");
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, v3Valuesets);
      s.close();
      Utilities.copyFile(page.getFolders().dstDir + "v3-codesystems.xml", page.getFolders().dstDir + "examples" + File.separator + "v3-codesystems.xml");
      s = new FileOutputStream(page.getFolders().dstDir + "v3-codesystems.json");
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, v3Valuesets);
      s.close();

      produceComparisons();
      
      page.log("....validator", LogMessageType.Process);
      ZipGenerator zip = new ZipGenerator(page.getFolders().dstDir + "validation.xml.zip");
      zip.addFileName("profiles-types.xml", page.getFolders().dstDir + "profiles-types.xml", false);
      zip.addFileName("profiles-resources.xml", page.getFolders().dstDir + "profiles-resources.xml", false);
      zip.addFileName("profiles-others.xml", page.getFolders().dstDir + "profiles-others.xml", false);
      zip.addFileName("extension-definitions.xml", page.getFolders().dstDir + "extension-definitions.xml", false);
      zip.addFileName("search-parameters.xml", page.getFolders().dstDir + "search-parameters.xml", false);
      zip.addFileName("valuesets.xml", page.getFolders().dstDir + "valuesets.xml", false);
      zip.addFileName("v2-tables.xml", page.getFolders().dstDir + "v2-tables.xml", false);
      zip.addFileName("v3-codesystems.xml", page.getFolders().dstDir + "v3-codesystems.xml", false);
      zip.addFileName("conceptmaps.xml", page.getFolders().dstDir + "conceptmaps.xml", false);
      zip.addFileName("dataelements.xml", page.getFolders().dstDir + "dataelements.xml", false);
      zip.addFiles(page.getFolders().dstDir, "", ".xsd", null);
      zip.addFiles(page.getFolders().dstDir, "", ".sch", null);
      zip.addFiles(Utilities.path(page.getFolders().rootDir, "tools", "schematron", ""), "", ".xsl", null);
      zip.addFiles(Utilities.path(page.getFolders().rootDir, "tools", "schematron", ""), "", ".xslt", null);
      zip.close();

      zip = new ZipGenerator(page.getFolders().dstDir + "validation.json.zip");
      zip.addFileName("profiles-types.json", page.getFolders().dstDir + "profiles-types.json", false);
      zip.addFileName("profiles-resources.json", page.getFolders().dstDir + "profiles-resources.json", false);
      zip.addFileName("profiles-others.json", page.getFolders().dstDir + "profiles-others.json", false);
      zip.addFileName("extension-definitions.json", page.getFolders().dstDir + "extension-definitions.json", false);
      zip.addFileName("search-parameters.json", page.getFolders().dstDir + "search-parameters.json", false);
      zip.addFileName("valuesets.json", page.getFolders().dstDir + "valuesets.json", false);
      zip.addFileName("v2-tables.json", page.getFolders().dstDir + "v2-tables.json", false);
      zip.addFileName("v3-codesystems.json", page.getFolders().dstDir + "v3-codesystems.json", false);
      zip.addFileName("conceptmaps.json", page.getFolders().dstDir + "conceptmaps.json", false);
      zip.addFileName("dataelements.json", page.getFolders().dstDir + "dataelements.json", false);
      zip.addFiles(page.getFolders().dstDir, "", ".xsd", null);
      zip.addFiles(page.getFolders().dstDir, "", ".sch", null);
      zip.addFiles(Utilities.path(page.getFolders().rootDir, "tools", "schematron", ""), "", ".xsl", null);
      zip.addFiles(Utilities.path(page.getFolders().rootDir, "tools", "schematron", ""), "", ".xslt", null);
      zip.close();

      page.log("....validator-min", LogMessageType.Process);
      minify(page.getFolders().dstDir + "validation.xml.zip", page.getFolders().dstDir + "validation-min.xml.zip");
      minify(page.getFolders().dstDir + "validation.json.zip", page.getFolders().dstDir + "validation-min.json.zip");

      zip = new ZipGenerator(page.getFolders().dstDir + "validator.zip");
      zip.addFileName("readme.txt", Utilities.path(page.getFolders().srcDir, "tools", "readme.txt"), false);
      zip.addFileName("org.hl7.fhir.validator.jar", Utilities.path(page.getFolders().dstDir, "org.hl7.fhir.validator.jar"), false);
//      zip.addFiles(Utilities.path(page.getFolders().rootDir, "tools", "schematron", ""), "", ".zip", null); // saxon
      // always make this last
      zip.close();

      zip = new ZipGenerator(page.getFolders().dstDir + "all-valuesets.zip");
      zip.addFileName("valuesets.xml", page.getFolders().dstDir + "valuesets.xml", false);
      zip.addFileName("valuesets.json", page.getFolders().dstDir + "valuesets.json", false);
      zip.addFileName("conceptmaps.xml", page.getFolders().dstDir + "conceptmaps.xml", false);
      zip.addFileName("conceptmaps.json", page.getFolders().dstDir + "conceptmaps.json", false);
      zip.addFileName("v2-tables.xml", page.getFolders().dstDir + "v2-tables.xml", false);
      zip.addFileName("v2-tables.json", page.getFolders().dstDir + "v2-tables.json", false);
      zip.addFileName("v3-codesystems.xml", page.getFolders().dstDir + "v3-codesystems.xml", false);
      zip.addFileName("v3-codesystems.json", page.getFolders().dstDir + "v3-codesystems.json", false);
      zip.close();

      page.log(" ...zips", LogMessageType.Process);
      zip = new ZipGenerator(page.getFolders().dstDir + "examples.zip");
      zip.addFiles(page.getFolders().dstDir + "examples" + File.separator, "", null, null);
      zip.close();

      zip = new ZipGenerator(page.getFolders().dstDir + "examples-json.zip");
      zip.addFiles(page.getFolders().dstDir, "", ".json", null);
      zip.close();

      if (web) {
        page.log(" ...final zip", LogMessageType.Process);
        produceZip();
      }

      page.log("Produce .epub Form", LogMessageType.Process);
      page.getEpub().produce();
      checkAllOk();
    } else
      page.log("Partial Build - terminating now", LogMessageType.Error);
  }

  private void produceIgOperations(ImplementationGuideDefn ig, Profile p) throws Exception {
    String src = TextFile.fileToString(page.getFolders().srcDir + "template-ig-operations.html");
    String n = p.getId();
    TextFile.stringToFile(page.processPageIncludes(ig.getCode()+File.separator+n+"-operations.html", src, "?type", null, "??path", null, null, "Operations", p, ig), page.getFolders().dstDir + ig.getCode()+File.separator+n + "-operations.html");
    // insertSectionNumbers(, st, n+"-operations.html", 0, null)
    page.getEpub().registerFile(ig.getCode()+File.separator+n + "-operations.html", "Operations defined by " + p.getTitle(), EPubManager.XHTML_TYPE);

    for (Operation t : p.getOperations()) {
      produceOperation(ig, n+"-"+t.getName(), n+"-"+t.getName().toLowerCase(), null, t);
    }
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
      if (!e.hasFullUrl())
        page.getValidationErrors().add(new ValidationMessage(Source.Publisher, IssueType.INVALID, -1, -1, "Bundle "+bnd.getId(), "no Full URL on entry "+Integer.toString(i),IssueSeverity.ERROR));
      else if (!e.getFullUrl().equals("http://hl7.org/fhir/"+e.getResource().getResourceType().toString()+"/"+e.getResource().getId()))
        page.getValidationErrors().add(new ValidationMessage(Source.Publisher, IssueType.INVALID, -1, -1, "Bundle "+bnd.getId(), "URL mismatch on entry "+Integer.toString(i)+" : "+e.getFullUrl()+" vs "+"http://hl7.org/fhir/"+e.getResource().getResourceType().toString()+"/"+e.getResource().getId(),IssueSeverity.ERROR));
    }
  }

  private void produceComparisons() throws Exception {
    for (String n : page.getIni().getPropertyNames("comparisons")) {
      produceComparison(n);
    }
  }

  private void produceComparison(String n) throws Exception {
    int t = page.getIni().getIntegerProperty(n, "pairs");
    ProfileComparer pc = new ProfileComparer(page.getWorkerContext());
    pc.setId(n);
    pc.setTitle(page.getIni().getStringProperty("comparisons", n));
    page.log("...Comparison: "+pc.getTitle(), LogMessageType.Process);
    pc.setLeftLink(page.getIni().getStringProperty(n, "left-link"));
    pc.setLeftName(page.getIni().getStringProperty(n, "left-name"));
    pc.setRightLink(page.getIni().getStringProperty(n, "right-link"));
    pc.setRightName(page.getIni().getStringProperty(n, "right-name"));
    for (int i = 1; i <= t; i++) {
      String[] pair = page.getIni().getStringProperty(n, "pair"+Integer.toString(i)).split(",");
      if (pair.length != 2)
        throw new Exception("Didn't find a pair for "+n+".pair"+Integer.toString(i));
      StructureDefinition sdl = page.getWorkerContext().getProfiles().get("http://hl7.org/fhir/StructureDefinition/"+pair[0]);
      if (sdl == null)
        throw new Exception("Unable to find structure "+pair[0]);
      StructureDefinition sdr = page.getWorkerContext().getProfiles().get("http://hl7.org/fhir/StructureDefinition/"+pair[1]);
      if (sdr == null)
        throw new Exception("Unable to find structure "+pair[1]);
      pc.compareProfiles(sdl, sdr);
    }  
    
    // assign file namea and paths to all the structures
    int i = 0;
    for (ProfileComparison cmp : pc.getComparisons()) {
      i++;
      if (cmp.getSubset() != null) {
        cmp.getSubset().setUserData("filename", n+".intersection."+Integer.toString(i)+".xml");
        cmp.getSubset().setUserData("filename", n+".intersection."+Integer.toString(i)+".html");
      }
      if (cmp.getSuperset() != null) {
        cmp.getSuperset().setUserData("filename", n+".intersection."+Integer.toString(i)+".xml");
        cmp.getSuperset().setUserData("filename", n+".intersection."+Integer.toString(i)+".html");
      }      
    }
    
    // ok, all compared; now produce the output 
    // first page we produce is simply the index
    page.log("   ... generate", LogMessageType.Process);
    String src = TextFile.fileToString(page.getFolders().srcDir + "template-comparison-set.html");
    src = page.processPageIncludes(n+".html", src, "?type", null, "??path", null, null, "Comparison", pc, null);
    TextFile.stringToFile(src, Utilities.path(page.getFolders().dstDir, n+".html"));
    cachePage(n + ".html", src, "Comparison "+pc.getTitle());
    
    // then we produce a comparison page for each pair
    for (ProfileComparison cmp : pc.getComparisons()) {
      src = TextFile.fileToString(page.getFolders().srcDir + "template-comparison.html");
      src = page.processPageIncludes(n+"."+cmp.getId()+".html", src, "?type", null, "??path", null, null, "Comparison", cmp, null);
      TextFile.stringToFile(src, Utilities.path(page.getFolders().dstDir, n+"."+cmp.getId()+".html"));
      cachePage(n +"."+cmp.getId()+".html", src, "Comparison "+pc.getTitle());      
    }
      //   and also individual pages for each pair outcome
    // then we produce value set pages for each value set
    
  }

  private void minify(String srcFile, String dstFile) throws Exception {
    CloseProtectedZipInputStream source = new CloseProtectedZipInputStream(new FileInputStream(srcFile));
    ZipGenerator dest = new ZipGenerator(dstFile);
    ZipEntry entry = null;
    while ((entry = source.getNextEntry()) != null) {
      String name = entry.getName();

      if (name.endsWith(".xsd"))
        dest.addStream(entry.getName(), stripXsd(source), false);
      else if (name.endsWith(".json"))
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
    p.getCode().clear();
    p.setRequirementsElement(null);
    p.getMapping().clear();
    p.setDifferential(null);
    for (ElementDefinition ed : p.getSnapshot().getElement()) {
      ed.setShortElement(null);
      ed.setDefinitionElement(null);
      ed.setCommentsElement(null);
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
    if (vs.hasCodeSystem())
      stripDefinition(vs.getCodeSystem().getConcept());
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
      return new ByteArrayInputStream(Utilities.transform(new HashMap<String, byte[]>(), src, xslt));
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
      bundle.addEntry().setResource(p.getResource()).setFullUrl(p.getResource().getUrl());
  }

  private void addOtherProfiles(Bundle bundle, ResourceDefn rd) {
    for (Profile cp : rd.getConformancePackages())
      addOtherProfiles(bundle, cp);


  }

  private void addSearchParams(Bundle bundle, ResourceDefn rd) throws Exception {
    if (rd.getConformancePack() == null) {
      for (SearchParameterDefn spd : rd.getSearchParams().values()) {
        StructureDefinition p = new StructureDefinition();
        p.setKind(StructureDefinitionKind.RESOURCE);
        p.setAbstract(true);
        p.setPublisher("Health Level Seven International (" + rd.getWg() + ")");
        p.setName(rd.getName());
        p.addContact().addTelecom().setSystem(ContactPointSystem.OTHER).setValue("http://hl7.org/fhir");
        SearchParameter sp = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), dataElements).makeSearchParam(p, rd.getName()+"-"+spd.getCode(), rd.getName(), spd);
        bundle.addEntry().setResource(sp).setFullUrl(sp.getUrl());
      }
    } else
      addSearchParams(bundle, rd.getConformancePack());
  }

  private void addSearchParams(Bundle bundle, Profile conformancePack) {
    for (SearchParameter sp : conformancePack.getSearchParameters()) {
     bundle.addEntry().setResource(sp).setFullUrl(sp.getUrl());
    }
  }

  private void produceExtensionDefinition(StructureDefinition ed) throws FileNotFoundException, Exception {
    ImplementationGuideDefn ig = page.getDefinitions().getIgs().get(ed.getUserString(ToolResourceUtilities.NAME_RES_IG));
    String prefix = ig.isCore() ? "" : ig.getCode()+File.separator;
    String filename = ed.getUserString("filename");
    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + prefix+filename+".xml");
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, ed);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + prefix+filename+".json");
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, ed);
    s.close();
    cloneToXhtml(prefix+filename, ed.getName(), false, "summary-instance", "Extension");
    jsonToXhtml(prefix+filename, ed.getName(), resource2Json(ed), "extension", "Extension");

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
    TerminologyNotesGenerator tgen = new TerminologyNotesGenerator(bytes, page);
    tgen.generateExtension("", ed);
    tgen.close();
    String tx = bytes.toString();

    String src = TextFile.fileToString(page.getFolders().srcDir + "template-extension-mappings.html");
    src = page.processExtensionIncludes(filename, ed, xml, json, tx, src, filename + ".html", ig);
    page.getEpub().registerFile(prefix+filename + "-mappings.html", "Mappings for Extension " + ed.getName(), EPubManager.XHTML_TYPE);
    TextFile.stringToFile(src, page.getFolders().dstDir + prefix+filename + "-mappings.html");

    src = TextFile.fileToString(page.getFolders().srcDir + "template-extension-definitions.html");
    src = page.processExtensionIncludes(filename, ed, xml, json, tx, src, filename + ".html", ig);
    page.getEpub().registerFile(prefix+filename + "-definitions.html", "Definitions for Extension " + ed.getName(), EPubManager.XHTML_TYPE);
    TextFile.stringToFile(src, page.getFolders().dstDir + prefix+filename + "-definitions.html");

    src = TextFile.fileToString(page.getFolders().srcDir + "template-extension.html");
    src = page.processExtensionIncludes(filename, ed, xml, json, tx, src, filename + ".html", ig);
    page.getEpub().registerFile(prefix+filename + ".html", "Extension " + ed.getName(), EPubManager.XHTML_TYPE);
    TextFile.stringToFile(src, page.getFolders().dstDir + prefix+filename + ".html");
  }

  private void copyStaticContent() throws IOException, Exception {
    if (page.getIni().getPropertyNames("support") != null)
      for (String n : page.getIni().getPropertyNames("support")) {
        Utilities.copyFile(new CSFile(page.getFolders().srcDir + n), new CSFile(page.getFolders().dstDir + n));
        page.getEpub().registerFile(n, "Support File", EPubManager.determineType(n));
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
      page.getEpub().registerFile(page.getIni().getStringProperty("files", n), "Support File",
          EPubManager.determineType(page.getIni().getStringProperty("files", n)));
    }

    page.log("Copy HTML templates", LogMessageType.Process);
    Utilities.copyDirectory(page.getFolders().rootDir + page.getIni().getStringProperty("html", "source"), page.getFolders().dstDir, page.getEpub());
    TextFile.stringToFile("\r\n[FHIR]\r\nFhirVersion=" + page.getVersion() + "." + page.getSvnRevision() + "\r\nversion=" + page.getVersion()
        + "\r\nrevision=" + page.getSvnRevision() + "\r\ndate=" + new SimpleDateFormat("yyyyMMddHHmmss").format(page.getGenDate().getTime()),
        Utilities.path(page.getFolders().dstDir, "version.info"));

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
        page.getEpub().registerFile(f.getName(), "Support File", EPubManager.determineType(n));
      }
    } else {
      Utilities.copyFile(new CSFile(folder + n), new CSFile(page.getFolders().dstDir + n));
      page.getEpub().registerFile(n, "Support File", EPubManager.determineType(n));
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
        page.getEpub().registerFile(prefix+f.getName(), "Support File", EPubManager.determineType(f.getName()));
      }
    } else {
      Utilities.copyFile(file, new CSFile(Utilities.path(page.getFolders().dstDir, prefix+file.getName())));
      page.getEpub().registerFile(prefix+file.getName(), "Support File", EPubManager.determineType(file.getName()));
    }
  }

  /** this is only used when generating xhtml of json **/
  private String resource2Json(Bundle profileFeed2) throws Exception {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
    json.setSuppressXhtml("Snipped for Brevity");
    json.compose(bytes, profileFeed);
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

  private void produceQA() throws Exception {
    page.getQa().countDefinitions(page.getDefinitions());

    String src = TextFile.fileToString(page.getFolders().srcDir + "qa.html");
    TextFile.stringToFile(page.processPageIncludes("qa.html", src, "page", null, null, null, "QA Page", null), page.getFolders().dstDir + "qa.html");

    if (web) {
      page.getQa().commit(page.getFolders().rootDir);
      wm.addPage("qa.html");
    }
  }

  private static String nodeToString(Element node) throws Exception {
    StringBuilder b = new StringBuilder();
    Node n = node.getFirstChild();
    while (n != null) {
      if (n.getNodeType() == Node.ELEMENT_NODE) {
        b.append(nodeToString((Element) n));
      } else if (n.getNodeType() == Node.TEXT_NODE) {
        b.append(Utilities.escapeXml(n.getTextContent()));
      }
      n = n.getNextSibling();
    }
    if (node.getNodeName().equals("p"))
      b.append("<br/>\r\n");
    return b.toString();
  }

  private static String nodeToText(Element node) throws Exception {
    StringBuilder b = new StringBuilder();
    Node n = node.getFirstChild();
    while (n != null) {
      if (n.getNodeType() == Node.ELEMENT_NODE) {
        b.append(nodeToText((Element) n));
      } else if (n.getNodeType() == Node.TEXT_NODE) {
        b.append(n.getTextContent());
      }
      n = n.getNextSibling();
    }
    if (node.getNodeName().equals("p"))
      b.append("\r\n");
    return b.toString();
  }

  private static class CodeInfo {
    boolean select;
    String code;
    String display;
    String definition;
    String textDefinition;
    boolean deprecated;

    List<String> parents = new ArrayList<String>();
    List<CodeInfo> children = new ArrayList<CodeInfo>();

    public void write(int lvl, StringBuilder s, ValueSet vs, List<ConceptDefinitionComponent> list, ConceptDefinitionComponent owner,
        Map<String, ConceptDefinitionComponent> handled) throws Exception {
      if (!select && children.size() == 0)
        return;

      if (handled.containsKey(code)) {
        if (owner == null)
          throw new Exception("Error handling poly-heirarchy - subsequent mention is on the root");
        // ToolingExtensions.addParentCode(handled.get(code),
        // owner.getCode());
        ToolingExtensions.addSubsumes(owner, code);
        s.append(" <tr><td>").append(Integer.toString(lvl)).append("</td><td>");
        for (int i = 1; i < lvl; i++)
          s.append("&nbsp;&nbsp;");
        s.append("<a href=\"#").append(Utilities.escapeXml(Utilities.nmtokenize(code))).append("\">")
				.append(Utilities.escapeXml(code)).append("</a></td><td></td><td></td></tr>\r\n");
      } else {
        ConceptDefinitionComponent concept = new ValueSet.ConceptDefinitionComponent();
        handled.put(code, concept);
        concept.setCode(code);
        concept.setDisplay(display);
        concept.setDefinition(textDefinition);
        if (!concept.hasDefinition())
          concept.setDefinition(concept.getDisplay());
        concept.setAbstract(!select);
        String d = "";
        if (deprecated) {
          ToolingExtensions.markDeprecated(concept);
          d = " <b><i>Deprecated</i></b>";
        }

        list.add(concept);

        s.append(" <tr" + (deprecated ? " style=\"background: #EFEFEF\"" : "") + "><td>" + Integer.toString(lvl) + "</td><td>");
        for (int i = 1; i < lvl; i++)
          s.append("&nbsp;&nbsp;");
        if (select) {
          s.append(Utilities.escapeXml(code) + "<a name=\"" + Utilities.escapeXml(Utilities.nmtokenize(code)) + "\"> </a>" + d + "</td><td>"
              + Utilities.escapeXml(display) + "</td><td>");
        } else
          s.append("<span style=\"color: grey\"><i>(" + Utilities.escapeXml(code) + ")</i></span>" + d + "</td><td><a name=\""
              + Utilities.escapeXml(Utilities.nmtokenize(code)) + "\">&nbsp;</a></td><td>");
        if (definition != null)
          s.append(definition);
        s.append("</td></tr>\r\n");
        for (CodeInfo child : children) {
          child.write(lvl + 1, s, vs, concept.getConcept(), concept, handled);
        }
      }
    }
  }

  private ValueSet buildV3CodeSystem(String id, String date, Element e, String csOid, String vsOid) throws Exception {
    StringBuilder s = new StringBuilder();
    ValueSet vs = new ValueSet();
    ValueSetUtilities.makeShareable(vs);
    vs.setUserData("filename", Utilities.path("v3", id, "index.html"));
    vs.setId("v3-"+FormatUtilities.makeId(id));
    vs.setUrl("http://hl7.org/fhir/ValueSet/" + vs.getId());
    vs.setName("v3 Code System " + id);
    vs.setPublisher("HL7, Inc");
    vs.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.OTHER, "http://hl7.org"));
    vs.setStatus(ConformanceResourceStatus.ACTIVE);
    ValueSetCodeSystemComponent def = new ValueSet.ValueSetCodeSystemComponent();
    vs.setCodeSystem(def);
    vs.setExperimental(false);
    def.setCaseSensitive(true);
    def.setSystem("http://hl7.org/fhir/v3/" + id);

    Element r = XMLUtil.getNamedChild(e, "releasedVersion");
    if (r != null) {
      s.append("<p>Release Date: " + r.getAttribute("releaseDate") + "</p>\r\n");
      vs.setDateElement(new DateTimeType(r.getAttribute("releaseDate")));
      vs.setVersion(r.getAttribute("releaseDate"));
    }
    if (csOid != null)
      s.append("<p>OID for code system: " + csOid + "</p>\r\n");
    if (vsOid != null) {
      s.append("<p>OID for value set: " + vsOid + " (this is the value set that includes the entire code system)</p>\r\n");
      ToolingExtensions.setOID(vs, "urn:oid:"+vsOid);
    }
    r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "annotations"), "documentation"), "description"), "text");
    if (r == null)
      r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "annotations"), "documentation"), "definition"), "text");
    if (r != null) {
      s.append("<h2>Description</h2>\r\n");
      s.append("<p>").append(nodeToString(r)).append("</p>\r\n");
      s.append("<hr/>\r\n");
      vs.setDescription(XMLUtil.htmlToXmlEscapedPlainText(r));
    } else
      vs.setDescription("**** MISSING DEFINITIONS ****");

    List<CodeInfo> codes = new ArrayList<CodeInfo>();
    // first, collate all the codes
    Element c = XMLUtil.getFirstChild(XMLUtil.getNamedChild(e, "releasedVersion"));
    while (c != null) {
      if (c.getNodeName().equals("concept")) {
        CodeInfo ci = new CodeInfo();
        ci.select = !"false".equals(c.getAttribute("isSelectable"));
        r = XMLUtil.getNamedChild(c, "code");
        ci.code = r == null ? null : r.getAttribute("code");
        r = XMLUtil.getNamedChild(c, "printName");
        ci.display = r == null ? null : r.getAttribute("text");
        r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(c, "annotations"), "documentation"), "definition"), "text");
        ci.definition = r == null ? null : nodeToString(r);
        ci.textDefinition = r == null ? null : nodeToText(r).trim();
        ci.deprecated = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(c, "annotations"), "appInfo"), "deprecationInfo") != null;
        List<Element> pl = new ArrayList<Element>();
        XMLUtil.getNamedChildren(c, "conceptRelationship", pl);
        for (Element p : pl) {
          if (p.getAttribute("relationshipName").equals("Specializes"))
            ci.parents.add(XMLUtil.getFirstChild(p).getAttribute("code"));
        }
        if (!"retired".equals(XMLUtil.getNamedChild(c, "code").getAttribute("status")))
          codes.add(ci);
      }
      c = XMLUtil.getNextSibling(c);
    }

    // now, organise the heirarchy
    for (CodeInfo ci : codes) {
      for (String p : ci.parents) {
        CodeInfo pi = null;
        for (CodeInfo cip : codes) {
          if (cip.code != null && cip.code.equals(p))
            pi = cip;
        }
        if (pi != null)
          pi.children.add(ci);
      }
    }

    s.append("<table class=\"grid\">\r\n");
    s.append(" <tr><td><b>Level</b></td><td><b>Code</b></td><td><b>Display</b></td><td><b>Definition</b></td></tr>\r\n");
    Map<String, ConceptDefinitionComponent> handled = new HashMap<String, ValueSet.ConceptDefinitionComponent>();
    for (CodeInfo ci : codes) {
      if (ci.parents.size() == 0) {
        ci.write(1, s, vs, def.getConcept(), null, handled);
      }
    }
    s.append("</table>\r\n");

    vs.setText(new Narrative());
    vs.getText().setStatus(NarrativeStatus.GENERATED);
    vs.getText().setDiv(new XhtmlParser().parse("<div>" + s.toString() + "</div>", "div").getElement("div"));
    page.getVsValidator().validate(page.getValidationErrors(), "v3 valueset "+id, vs, false, true);

    return vs;
  }

  private void analyseV3() throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    page.setV3src(builder.parse(new CSFileInputStream(new CSFile(page.getFolders().srcDir + "v3" + File.separator + "source.xml"))));
    String dt = null;
    Map<String, ValueSet> codesystems = new HashMap<String, ValueSet>();
    Set<String> cslist = new HashSet<String>();

    IniFile ini = new IniFile(page.getFolders().srcDir + "v3" + File.separator + "valuesets.ini");

    Element e = XMLUtil.getFirstChild(page.getV3src().getDocumentElement());
    while (e != null) {

      if (e.getNodeName().equals("header")) {
        Element d = XMLUtil.getNamedChild(e, "renderingInformation");
        if (d != null)
          dt = d.getAttribute("renderingTime");
      }

      if (e.getNodeName().equals("codeSystem")) {
        Element r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "header"), "responsibleGroup");
        if (!ini.getBooleanProperty("Exclude", e.getAttribute("name")) && !deprecated(e)) {
          String id = e.getAttribute("name");
          if (cslist.contains(id))
            throw new Exception("Duplicate v3 name: "+id);
          cslist.add(id);
          if (r != null && "Health Level 7".equals(r.getAttribute("organizationName")) || ini.getBooleanProperty("CodeSystems", id)) {
            String vsOid = getVSForCodeSystem(page.getV3src().getDocumentElement(), e.getAttribute("codeSystemId"));
            ValueSet vs = buildV3CodeSystem(id, dt, e, e.getAttribute("codeSystemId"), vsOid);
            vs.setId("v3-" + FormatUtilities.makeId(id));
            vs.setUrl("http://hl7.org/fhir/ValueSet/"+vs.getId());
            vs.setUserData("path", "v3" + HTTP_separator + id + HTTP_separator + "index.html");
            ToolingExtensions.setOID(vs.getCodeSystem(), "urn:oid:"+e.getAttribute("codeSystemId"));
            if (vs.hasDate())
              vs.getMeta().setLastUpdatedElement(new InstantType(vs.getDate()));
            else
              vs.getMeta().setLastUpdated(page.getGenDate().getTime());
            page.getV3Valuesets().getEntry().add(new BundleEntryComponent().setResource(vs).setFullUrl(vs.getUrl()));
            page.getDefinitions().getValuesets().put(vs.getUrl(), vs);
            page.getDefinitions().getCodeSystems().put(vs.getCodeSystem().getSystem(), vs);
            page.getValueSets().put(vs.getUrl(), vs);
            page.getCodeSystems().put(vs.getCodeSystem().getSystem().toString(), vs);
            codesystems.put(e.getAttribute("codeSystemId"), vs);
          } // else if (r == null)
          // page.log("unowned code system: "+id);
        }
      }

      if (e.getNodeName().equals("valueSet")) {
        String iniV = ini.getStringProperty("ValueSets", e.getAttribute("name"));
        if (iniV != null) {
          String id = e.getAttribute("name");
          if (cslist.contains(id))
            throw new Exception("Duplicate v3 name: "+id);
          cslist.add(id);
          ValueSet vs;
          if (iniV.equals("1"))
            vs = buildV3ValueSet(id, dt, e, codesystems, ini);
          else if (iniV.startsWith("->")) {
            vs = buildV3ValueSetAsCodeSystem(id, e, iniV.substring(2));
          } else
            throw new Exception("unhandled value set specifier in ini file");

          vs.setUserData("path", "v3" + HTTP_separator + id + HTTP_separator + "index.html");
          ToolingExtensions.setOID(vs, "urn:oid:"+e.getAttribute("id"));
          if (vs.hasDate())
            vs.getMeta().setLastUpdatedElement(new InstantType(vs.getDate()));
          else
            vs.getMeta().setLastUpdated(page.getGenDate().getTime());
          page.getV3Valuesets().getEntry().add(new BundleEntryComponent().setResource(vs).setFullUrl(vs.getUrl()));
          page.getValueSets().put(vs.getUrl(), vs);
          page.getDefinitions().getValuesets().put(vs.getUrl(), vs);
        }
      }
      e = XMLUtil.getNextSibling(e);
    }
  }

  private boolean deprecated(Element cs) {
    Element e = XMLUtil.getNamedChild(cs, "annotations"); 
    e = XMLUtil.getNamedChild(e, "appInfo"); 
    e = XMLUtil.getNamedChild(e, "deprecationInfo"); 
    return e != null;
  }

  private String getVSForCodeSystem(Element documentElement, String oid) {
    // we need to find a value set that has the content from the supported code system, and nothing ese
    Element element = XMLUtil.getFirstChild(documentElement);
    while (element != null) {
      Element version = XMLUtil.getNamedChild(element, "version");
      if (version != null) {
        Element content = XMLUtil.getNamedChild(version, "content");
        if (oid.equals(content.getAttribute("codeSystem")) && content.getFirstChild() == null)
          return element.getAttribute("id");
      }
      element = XMLUtil.getNextSibling(element);
    }

    return null;
  }

  private ValueSet buildV3ValueSetAsCodeSystem(String id, Element e, String csname) throws DOMException, Exception {
    ValueSet vs = new ValueSet();
    ValueSetUtilities.makeShareable(vs);
    vs.setUserData("filename", Utilities.path("v3", id, "index.html"));
    vs.setId("v3-"+FormatUtilities.makeId(id));
    vs.setUrl("http://hl7.org/fhir/ValueSet/" + vs.getId());
    vs.setName(id);
    Element r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "annotations"), "documentation"), "description"),
        "text");
    if (r != null) {
      vs.setDescription(XMLUtil.htmlToXmlEscapedPlainText(r) + " (OID = " + e.getAttribute("id") + ")");
    } else {
      vs.setDescription("?? (OID = " + e.getAttribute("id") + ")");
    }
    vs.setPublisher("HL7 v3");
    vs.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.OTHER, "http://www.hl7.org"));
    vs.setStatus(ConformanceResourceStatus.ACTIVE);
    vs.setExperimental(false);

    r = XMLUtil.getNamedChild(e, "version");
    if (r != null)
      vs.setVersion(r.getAttribute("versionDate"));
    String[] parts = csname.split("\\&");
    ValueSetComposeComponent compose = new ValueSet.ValueSetComposeComponent();
    vs.setCompose(compose);
    for (String cs : parts) {
      if (cs.contains(":")) {
        compose.addInclude().setSystem(cs);
      } else if (cs.contains(".")) {
        String[] csp = cs.split("\\.");
        if (csp.length != 2)
          throw new Exception("unhandled value set specifier "+cs+" in ini file");
        ConceptSetComponent inc = compose.addInclude();
        inc.setSystem("http://hl7.org/fhir/v3/"+csp[0]);
        inc.addFilter().setProperty("concept").setOp(FilterOperator.ISA).setValue(csp[1]);
      } else {
        compose.addInclude().setSystem("http://hl7.org/fhir/v3/"+cs);
      }
    }

    NarrativeGenerator gen = new NarrativeGenerator("../../", "v3/"+id, page.getWorkerContext());
    gen.generate(vs);
    page.getVsValidator().validate(page.getValidationErrors(), "v3 value set as code system "+id, vs, false, true);
    return vs;
  }

  private ValueSet buildV3ValueSet(String id, String dt, Element e, Map<String, ValueSet> codesystems, IniFile vsini) throws DOMException, Exception {
    ValueSet vs = new ValueSet();
    ValueSetUtilities.makeShareable(vs);
    vs.setUserData("filename", Utilities.path("v3", id, "index.html"));
    vs.setId("v3-"+FormatUtilities.makeId(id));
    vs.setUrl("http://hl7.org/fhir/ValueSet/" + vs.getId());
    vs.setName(id);
    Element r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "annotations"), "documentation"), "description"),
        "text");
    if (r != null) {
      vs.setDescription(XMLUtil.htmlToXmlEscapedPlainText(r) + " (OID = " + e.getAttribute("id") + ")");
    } else {
      vs.setDescription("?? (OID = " + e.getAttribute("id") + ")");
    }
    vs.setPublisher("HL7 v3");
    vs.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.OTHER, "http://www.hl7.org"));
    vs.setStatus(ConformanceResourceStatus.ACTIVE);
    vs.setExperimental(false);

    r = XMLUtil.getNamedChild(e, "version");
    if (r != null) {
      vs.setVersion(r.getAttribute("versionDate"));

      // ok, now the content
      ValueSetComposeComponent compose = new ValueSet.ValueSetComposeComponent();
      vs.setCompose(compose);
      Element content = XMLUtil.getNamedChild(r, "content");
      if (content == null)
        throw new Exception("Unable to find content for ValueSet " + id);
      ValueSet cs = codesystems.get(content.getAttribute("codeSystem"));
      if (cs == null)
        throw new Exception("Error Processing ValueSet " + id + ", unable to resolve code system '"
            + XMLUtil.getNamedChild(e, "supportedCodeSystem").getTextContent() + "'");
      ConceptSetComponent imp = new ValueSet.ConceptSetComponent();

      if (XMLUtil.hasNamedChild(content, "combinedContent")) {
        if (!id.equals("SecurityControlObservationValue") && !id.equals("ProvenanceEventCurrentState"))
          throw new Exception("check logic; this is fragile code, and each value set needs manual review- id is "+id);
        Element part = XMLUtil.getFirstChild(XMLUtil.getNamedChild(content, "combinedContent"));
        while (part != null) {
          if (part.getNodeName().equals("unionWithContent"))
            compose.addImport("http://hl7.org/fhir/ValueSet/v3-" + XMLUtil.getNamedChild(part, "valueSetRef").getAttribute("name"));
          else
            throw new Exception("unknown value set construction method");
          part = XMLUtil.getNextSibling(part);
        }
      } else {
        // simple value set
        compose.getInclude().add(imp);
        imp.setSystem(cs.getCodeSystem().getSystem());

        if (!XMLUtil.getNamedChild(r, "supportedCodeSystem").getTextContent().equals(content.getAttribute("codeSystem")))
          throw new Exception("Unexpected codeSystem oid on content for ValueSet " + id + ": expected '"
              + XMLUtil.getNamedChild(r, "supportedCodeSystem").getTextContent() + "', found '" + content.getAttribute("codeSystem") + "'");

        List<String> codes = new ArrayList<String>();

        Element cnt = XMLUtil.getFirstChild(content);
        while (cnt != null) {
          if (cnt.getNodeName().equals("codeBasedContent") && (XMLUtil.getNamedChild(cnt, "includeRelatedCodes") != null)) {
            // common case: include a child and all or some of it's descendants
            ConceptSetFilterComponent f = new ValueSet.ConceptSetFilterComponent();
            f.setOp(FilterOperator.ISA);
            f.setProperty("concept");
            f.setValue(cnt.getAttribute("code"));
            imp.getFilter().add(f);
            if ("false".equals(cnt.getAttribute("includeHeadCode"))) {
              compose.addExclude().setSystem(imp.getSystem()).addConcept().setCode(cnt.getAttribute("code"));
            }
          } else if (cnt.getNodeName().equals("codeBasedContent") && cnt.hasAttribute("code")) {
            codes.add(cnt.getAttribute("code"));
          }
          cnt = XMLUtil.getNextSibling(cnt);
        }
        if (vsini.getStringProperty("Order", id) != null) {
          List<String> order = new ArrayList<String>();
          for (String s : vsini.getStringProperty("Order", id).split("\\,")) {
            order.add(s);
          }
          for (String c : order) {
            if (codes.contains(c))
              imp.addConcept().setCode(c);
          }
          for (String c : codes) {
            if (!order.contains(c))
              imp.addConcept().setCode(c);
          }
        } else
          for (String c : codes) {
            imp.addConcept().setCode(c);
          }
      }
    }
    NarrativeGenerator gen = new NarrativeGenerator("../../", "v3/"+id, page.getWorkerContext());
    gen.generate(vs);
    page.getVsValidator().validate(page.getValidationErrors(), "v3 valueset "+id, vs, false, true);
    return vs;

  }

  private void produceV3() throws Exception {
    page.log(" ...v3 Code Systems", LogMessageType.Process);

    Utilities.createDirectory(page.getFolders().dstDir + "v3");
    Utilities.clearDirectory(page.getFolders().dstDir + "v3");
    String src = TextFile.fileToString(page.getFolders().srcDir + "v3" + File.separator + "template.html");
    TextFile.stringToFile(
        addSectionNumbers("terminologies-v3.html", "terminologies-v3", page.processPageIncludes("terminologies-v3.html", src, "page", null, null, null, "V3 Terminologies", null), null, 0, null),
        page.getFolders().dstDir + "terminologies-v3.html");
    src = TextFile.fileToString(page.getFolders().srcDir + "v3" + File.separator + "template.html");
    cachePage("terminologies-v3.html", page.processPageIncludesForBook("terminologies-v3.html", src, "page", null, null), "V3 Terminologes");
    IniFile ini = new IniFile(page.getFolders().srcDir + "v3" + File.separator + "valuesets.ini");

    Element e = XMLUtil.getFirstChild(page.getV3src().getDocumentElement());
    while (e != null) {
      if (e.getNodeName().equals("codeSystem")) {
        if (!ini.getBooleanProperty("Exclude", e.getAttribute("name")) && !deprecated(e)) {
          Element r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "header"), "responsibleGroup");
          if (r != null && "Health Level 7".equals(r.getAttribute("organizationName")) || ini.getBooleanProperty("CodeSystems", e.getAttribute("name"))) {
            String id = e.getAttribute("name");
            Utilities.createDirectory(page.getFolders().dstDir + "v3" + File.separator + id);
            Utilities.clearDirectory(page.getFolders().dstDir + "v3" + File.separator + id);
            src = TextFile.fileToString(page.getFolders().srcDir + "v3" + File.separator + "template-cs.html");

            String sf = page.processPageIncludes(id + ".html", src, "v3Vocab", null, "v3" + File.separator + id + File.separator + "index.html", null, null, null, "V3 CodeSystem", null);
            sf = addSectionNumbers("v3" + id + ".html", "template-v3", sf, Utilities.oidTail(e.getAttribute("codeSystemId")), 0, null);
            TextFile.stringToFile(sf, page.getFolders().dstDir + "v3" + File.separator + id + File.separator + "index.html");
            page.getEpub().registerExternal("v3" + File.separator + id + File.separator + "index.html");
          }
        }
      }
      if (e.getNodeName().equals("valueSet")) {
        if (ini.getStringProperty("ValueSets", e.getAttribute("name")) != null) {
          String id = e.getAttribute("name");
          Utilities.createDirectory(page.getFolders().dstDir + "v3" + File.separator + id);
          Utilities.clearDirectory(page.getFolders().dstDir + "v3" + File.separator + id);
          src = TextFile.fileToString(page.getFolders().srcDir + "v3" + File.separator + "template-vs.html");
          String sf = page.processPageIncludes(id + ".html", src, "v3Vocab", null, "v3" + File.separator + id + File.separator + "index.html", null, null, "V3 ValueSet", null);
          sf = addSectionNumbers("v3" + id + ".html", "template-v3", sf, Utilities.oidTail(e.getAttribute("id")), 0, null);
          TextFile.stringToFile(sf, page.getFolders().dstDir + "v3" + File.separator + id + File.separator + "index.html");
          page.getEpub().registerExternal("v3" + File.separator + id + File.separator + "index.html");
        }
      }
      e = XMLUtil.getNextSibling(e);
    }
  }

  private void produceV2() throws Exception {
    page.log(" ...v2 Tables", LogMessageType.Process);

    Utilities.createDirectory(page.getFolders().dstDir + "v2");
    Utilities.clearDirectory(page.getFolders().dstDir + "v2");
    String src = TextFile.fileToString(page.getFolders().srcDir + "v2" + File.separator + "template.html");
    TextFile.stringToFile(
        addSectionNumbers("terminologies-v2.html", "terminologies-v2", page.processPageIncludes("terminologies-v2.html", src, "v2Vocab", null, null, null, "V2 Tables", null), null, 0, null),
        page.getFolders().dstDir + "terminologies-v2.html");
    src = TextFile.fileToString(page.getFolders().srcDir + "v2" + File.separator + "template.html");
    cachePage("terminologies-v2.html", page.processPageIncludesForBook("v2/template.html", src, "v2Vocab", null, null), "V2 Terminologies");

    Element e = XMLUtil.getFirstChild(page.getV2src().getDocumentElement());
    while (e != null) {
      String st = e.getAttribute("state");
      if ("include".equals(st)) {
        String id = Utilities.padLeft(e.getAttribute("id"), '0', 4);
        String iid = id;
        while (iid.startsWith("0"))
          iid = iid.substring(1);
        Utilities.createDirectory(page.getFolders().dstDir + "v2" + File.separator + id);
        Utilities.clearDirectory(page.getFolders().dstDir + "v2" + File.separator + id);
        src = TextFile.fileToString(page.getFolders().srcDir + "v2" + File.separator + "template-tbl.html");
        String sf = page.processPageIncludes(id + ".html", src, "v2Vocab", null, "v2" + File.separator + id + File.separator + "index.html", null, null, "V2 Table", null);
        sf = addSectionNumbers("v2" + id + ".html", "template-v2", sf, iid, 0, null);
        TextFile.stringToFile(sf, page.getFolders().dstDir + "v2" + File.separator + id + File.separator + "index.html");
        page.getEpub().registerExternal("v2" + File.separator + id + File.separator + "index.html");
      } else if ("versioned".equals(st)) {
        String id = Utilities.padLeft(e.getAttribute("id"), '0', 4);
        String iid = id;
        while (iid.startsWith("0"))
          iid = iid.substring(1);
        Utilities.createDirectory(page.getFolders().dstDir + "v2" + File.separator + id);
        Utilities.clearDirectory(page.getFolders().dstDir + "v2" + File.separator + id);
        List<String> versions = new ArrayList<String>();
        Element c = XMLUtil.getFirstChild(e);
        while (c != null) {
          if (XMLUtil.getFirstChild(c) != null && !versions.contains(c.getAttribute("namespace"))) {
            versions.add(c.getAttribute("namespace"));
          }
          c = XMLUtil.getNextSibling(c);
        }
        int i = 0;
        for (String ver : versions) {
          if (!Utilities.noString(ver)) {
            i++;
            Utilities.createDirectory(page.getFolders().dstDir + "v2" + File.separator + id + File.separator + ver);
            Utilities.clearDirectory(page.getFolders().dstDir + "v2" + File.separator + id + File.separator + ver);
            src = TextFile.fileToString(page.getFolders().srcDir + "v2" + File.separator + "template-tbl-ver.html");
            String sf = page.processPageIncludes(id + "|" + ver + ".html", src, "v2Vocab", null, "v2" + File.separator + id + File.separator + ver + File.separator + "index.html", null, null, "V2 Table", null);
            sf = addSectionNumbers("v2" + id + "." + ver + ".html", "template-v2", sf, iid + "." + Integer.toString(i), 0, null);
            TextFile.stringToFile(sf, page.getFolders().dstDir + "v2" + File.separator + id + File.separator + ver + File.separator + "index.html");
            page.getEpub().registerExternal("v2" + File.separator + id + File.separator + ver + File.separator + "index.html");
          }
        }
      }
      e = XMLUtil.getNextSibling(e);
    }

  }

  private void produceBaseProfile() throws Exception {

    for (DefinedCode pt : page.getDefinitions().getPrimitives().values())
      producePrimitiveTypeProfile(pt);
    for (TypeDefn e : page.getDefinitions().getTypes().values())
      produceTypeProfile(e);
    for (TypeDefn e : page.getDefinitions().getInfrastructure().values())
      produceTypeProfile(e);
    for (TypeDefn e : page.getDefinitions().getStructures().values())
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
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(fn, ".json"));
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, rp);
    s.close();

    Utilities.copyFile(new CSFile(page.getFolders().dstDir + fn), new CSFile(Utilities.path(page.getFolders().dstDir, "examples", fn)));
    addToResourceFeed(rp, typeFeed, (fn));
    cloneToXhtml(pt.getName().toLowerCase() + ".profile", "StructureDefinition for " + pt.getName(), false, "profile-instance:type:" + pt.getName(), "Type");
    jsonToXhtml(pt.getName().toLowerCase() + ".profile", "StructureDefinition for " + pt.getName(), resource2Json(rp), "profile-instance:type:" + pt.getName(), "Type");
  }

  private void producePrimitiveTypeProfile(DefinedCode type) throws Exception {

    String fn = type.getCode().toLowerCase() + ".profile.xml";
    StructureDefinition rp = type.getProfile();

    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + fn);
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, rp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(fn, ".json"));
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, rp);
    s.close();

    Utilities.copyFile(new CSFile(page.getFolders().dstDir + fn), new CSFile(Utilities.path(page.getFolders().dstDir, "examples", fn)));
    addToResourceFeed(rp, typeFeed, (fn));
    // saveAsPureHtml(rp, new FileOutputStream(page.getFolders().dstDir+ "html"
    // + File.separator + "datatypes.html"));
    cloneToXhtml(type.getCode().toLowerCase() + ".profile", "StructureDefinition for " + type.getCode(), false, "profile-instance:type:" + type.getCode(), "Type");
    jsonToXhtml(type.getCode().toLowerCase() + ".profile", "StructureDefinition for " + type.getCode(), resource2Json(rp), "profile-instance:type:" + type.getCode(), "Type");
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
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(fn, ".json"));
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, rp);
    s.close();

    Utilities.copyFile(new CSFile(page.getFolders().dstDir + fn), new CSFile(Utilities.path(page.getFolders().dstDir, "examples", fn)));
    addToResourceFeed(rp, typeFeed, fn);
    // saveAsPureHtml(rp, new FileOutputStream(page.getFolders().dstDir+ "html"
    // + File.separator + "datatypes.html"));
    cloneToXhtml(type.getName().toLowerCase() + ".profile", "StructureDefinition for " + type.getName(), false, "profile-instance:type:" + type.getName(), "Type");
    page.getEpub().registerExternal(type.getName() + ".profile.xml.html");
    jsonToXhtml(type.getName().toLowerCase() + ".profile", "StructureDefinition for " + type.getName(), resource2Json(rp), "profile-instance:type:" + type.getName(), "Type");
    page.getEpub().registerExternal(type.getName() + ".profile.json.html");
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
    List<String> errors = new ArrayList<String>();
    StringBuilder s = new StringBuilder();
    s.append("<tests>\r\n");
    int i = 0;
    for (Fragment f : fragments) {
      s.append("<test id=\"").append(Integer.toString(i)).append("\" page=\"")
			  .append(f.getPage()).append("\" type=\"").append(f.getType()).append("\">\r\n");
      s.append(f.getXml());
      s.append("</test>\r\n");
      i++;
    }
    s.append("</tests>\r\n");
    String err = javaReferencePlatform.checkFragments(page.getFolders(), s.toString());
    if (err == null)
      throw new Exception("Unable to process outcome of checking fragments");
    if (!err.startsWith("<results"))
      throw new Exception(err);

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document errDoc = builder.parse(new ByteArrayInputStream(err.getBytes()));

    Element result = XMLUtil.getFirstChild(errDoc.getDocumentElement());

    while (result != null) {
      String id = result.getAttribute("id");
      String outcome = result.getAttribute("outcome");
      if (!"ok".equals(outcome)) {
        Fragment f = fragments.get(Integer.parseInt(id));
        String msg = "Fragment Error in page " + f.getPage() + ": " + result.getAttribute("msg") + " for\r\n" + f.getXml();
        page.log(msg, LogMessageType.Error);
        page.log("", LogMessageType.Error);
        errors.add(msg);
      }
      result = XMLUtil.getNextSibling(result);
    }
    if (errors.size() > 0)
      throw new Exception("Fragment Errors prevent publication from continuing");
  }

  private void produceZip() throws Exception {
    File f = new CSFile(page.getFolders().dstDir + "fhir-spec.zip");
    if (f.exists())
      f.delete();
    ZipGenerator zip = new ZipGenerator(page.getFolders().tmpResDir + "fhir-spec.zip");
    zip.addFolder(page.getFolders().dstDir, "site/", false, ".zip");
    zip.addFolder(Utilities.path(page.getFolders().rootDir, "tools", "html", ""), "site/", true);
    zip.addFileName("index.html", page.getFolders().srcDir + "redirect.html", false);
    zip.close();
    Utilities.copyFile(new CSFile(page.getFolders().tmpResDir + "fhir-spec.zip"), f);
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
  }

  private void produceResource1(ResourceDefn resource, boolean isAbstract) throws Exception {
    String n = resource.getName().toLowerCase();
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    XmlSpecGenerator gen = new XmlSpecGenerator(bs, n + "-definitions.html", null, page, "");
    gen.generate(resource.getRoot(), isAbstract);
    gen.close();
    String xml = new String(bs.toByteArray());

    bs = new ByteArrayOutputStream();
    JsonSpecGenerator genJ = new JsonSpecGenerator(bs, n + "-definitions.html", null, page, "");
    genJ.generate(resource.getRoot(), true, isAbstract);
    genJ.close();
    String json = new String(bs.toByteArray());

    xmls.put(n, xml);
    jsons.put(n, json);
    generateProfile(resource, n, xml, json);
  }

  private void produceResource2(ResourceDefn resource, boolean isAbstract, String extraTypeForDefn) throws Exception {
    File tmp = Utilities.createTempFile("tmp", ".tmp");
    String n = resource.getName().toLowerCase();
    String xml = xmls.get(n);
    String json = jsons.get(n);

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

    MappingsGenerator mgen = new MappingsGenerator(page.getDefinitions());
    mgen.generate(resource);
    String mappings = mgen.getMappings();
    String mappingsList = mgen.getMappingsList();

    SvgGenerator svg = new SvgGenerator(page, "");
    svg.generate(resource, page.getFolders().dstDir + n + ".svg", "1");

    String prefix = page.getBreadCrumbManager().getIndexPrefixForReference(resource.getName());
    SectionTracker st = new SectionTracker(prefix);
    st.start("");
    page.getSectionTrackerCache().put(n, st);

    String template = isAbstract ? "template-abstract" : "template";
    String src = TextFile.fileToString(page.getFolders().srcDir + template+".html");
    src = insertSectionNumbers(page.processResourceIncludes(n, resource, xml, json, tx, dict, src, mappings, mappingsList, "resource", n + ".html", null), st, n + ".html", 0, null);
    TextFile.stringToFile(src, page.getFolders().dstDir + n + ".html");
    page.getEpub().registerFile(n + ".html", "Base Page for " + resource.getName(), EPubManager.XHTML_TYPE);

    StructureDefinition profile = (StructureDefinition) ResourceUtilities.getById(profileFeed, ResourceType.StructureDefinition, resource.getName());
    String pages = page.getIni().getStringProperty("resource-pages", n);
    if (!Utilities.noString(pages)) {
      for (String p : pages.split(",")) {
        producePage(p, n);
      }
    }
    try {
      if (!isAbstract)
        processQuestionnaire(profile, st, true, "", null);
    } catch (Exception e) {
      e.printStackTrace();
      page.log("Questionnaire Generation Failed: "+e.getMessage(), LogMessageType.Error);
    }

    if (!isAbstract || !resource.getExamples().isEmpty()) {
      src = TextFile.fileToString(page.getFolders().srcDir + template+"-examples.html");
      TextFile.stringToFile(
          insertSectionNumbers(page.processResourceIncludes(n, resource, xml, json, tx, dict, src, mappings, mappingsList, "res-Examples", n + "-examples.html", null), st, n + "-examples.html", 0, null),
          page.getFolders().dstDir + n + "-examples.html");
      page.getEpub().registerFile(n + "-examples.html", "Examples for " + resource.getName(), EPubManager.XHTML_TYPE);
      for (Example e : resource.getExamples()) {
        try {
          processExample(e, resource.getName(), profile, null, e.getIg() == null ? null : page.getDefinitions().getIgs().get(e.getIg()));
        } catch (Exception ex) {
          throw new Exception("processing " + e.getTitle(), ex);
          // throw new Exception(ex.getMessage()+" processing "+e.getFileTitle());
        }
      }
    }
    src = TextFile.fileToString(page.getFolders().srcDir + template+"-definitions.html");
    TextFile.stringToFile(
        insertSectionNumbers(page.processResourceIncludes(n, resource, xml, json, tx, dict, src, mappings, mappingsList, "res-Detailed Descriptions", n + "-definitions.html", null), st, n
            + "-definitions.html", 0, null), page.getFolders().dstDir + n + "-definitions.html");
    page.getEpub().registerFile(n + "-definitions.html", "Detailed Descriptions for " + resource.getName(), EPubManager.XHTML_TYPE);

    if (!isAbstract) {
      src = TextFile.fileToString(page.getFolders().srcDir + "template-mappings.html");
      TextFile.stringToFile(
          insertSectionNumbers(page.processResourceIncludes(n, resource, xml, json, tx, dict, src, mappings, mappingsList, "res-Mappings", n + "-mappings.html", null), st, n + "-mappings.html", 0, null),
          page.getFolders().dstDir + n + "-mappings.html");
      page.getEpub().registerFile(n + "-mappings.html", "Formal Mappings for " + resource.getName(), EPubManager.XHTML_TYPE);
      src = TextFile.fileToString(page.getFolders().srcDir + "template-explanations.html");
      TextFile.stringToFile(
          insertSectionNumbers(page.processResourceIncludes(n, resource, xml, json, tx, dict, src, mappings, mappingsList, "res-Design Notes", n + "-explanations.html", null), st, n
              + "-explanations.html", 0, null), page.getFolders().dstDir + n + "-explanations.html");
      page.getEpub().registerFile(n + "-explanations.html", "Design Notes for " + resource.getName(), EPubManager.XHTML_TYPE);
      src = TextFile.fileToString(page.getFolders().srcDir + "template-profiles.html");
      TextFile.stringToFile(
          insertSectionNumbers(page.processResourceIncludes(n, resource, xml, json, tx, dict, src, mappings, mappingsList, "res-Profiles", n + "-profiles.html", null), st, n + "-profiles.html", 0, null),
          page.getFolders().dstDir + n + "-profiles.html");
      page.getEpub().registerFile(n + "-profiles.html", "Profiles for " + resource.getName(), EPubManager.XHTML_TYPE);
    }
    for (Profile ap : resource.getConformancePackages())
      produceConformancePackage(resource.getName(), ap, st);

    if (!resource.getOperations().isEmpty()) {
      src = TextFile.fileToString(page.getFolders().srcDir + "template-operations.html");
      TextFile.stringToFile(
          insertSectionNumbers(page.processResourceIncludes(n, resource, xml, json, tx, dict, src, mappings, mappingsList, "res-Operations", n + "-operations.html", null), st, n
              + "-operations.html", 0, null), page.getFolders().dstDir + n + "-operations.html");
      page.getEpub().registerFile(n + "-operations.html", "Operations for " + resource.getName(), EPubManager.XHTML_TYPE);

      for (Operation t : resource.getOperations()) {
        produceOperation(null, resource.getName().toLowerCase()+"-"+t.getName(), resource.getName()+"-"+t.getName(), resource.getName(), t);
      }
      
      // todo: get rid of these...
      src = TextFile.fileToString(page.getFolders().srcDir + "template-book.html").replace("<body>", "<body style=\"margin: 10px\">");
      src = page.processResourceIncludes(n, resource, xml, json, tx, dict, src, mappings, mappingsList, "resource", n + ".html", null);
      cachePage(n + ".html", src, "Resource " + resource.getName());
      //    src = TextFile.fileToString(page.getFolders().srcDir + "template-book-ex.html").replace("<body>", "<body style=\"margin: 10px\">");
      //    src = page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList, "res-Examples");
      // cachePage(n + "Ex.html", src,
      // "Resource Examples for "+resource.getName());
      src = TextFile.fileToString(page.getFolders().srcDir + "template-book-defn.html").replace("<body>", "<body style=\"margin: 10px\">");
      src = page.processResourceIncludes(n, resource, xml, json, tx, dict, src, mappings, mappingsList, "res-Detailed Descriptions", n + "-definitions.html", null);
      cachePage(n + "-definitions.html", src, "Resource Definitions for " + resource.getName());
    }

    // xml to json
    // todo - fix this up
    // JsonGenerator jsongen = new JsonGenerator();
    // jsongen.generate(new CSFile(page.getFolders().dstDir+n+".xml"), new
    // File(page.getFolders().dstDir+n+".json"));

    tmp.delete();
    // because we'll pick up a little more information as we process the
    // resource
    StructureDefinition p = generateProfile(resource, n, xml, json);
//    if (!isAbstract && !n.equals("Bundle") && web)
//      generateQuestionnaire(n, p);
  }

  private void produceOperation(ImplementationGuideDefn ig, String name, String id, String resourceName, Operation op) throws Exception {
    OperationDefinition opd = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext(), page, page.getGenDate(), dataElements).generate(name, id, resourceName, op);

    String dir = ig == null ? "" : ig.getCode()+File.separator;
    
    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + dir+"operation-" + name + ".xml");
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, opd);
    s.close();
    cloneToXhtml(dir+"operation-" + name + "", "Operation Definition", true, "resource-instance:OperationDefinition", "Operation definition");
    s = new FileOutputStream(page.getFolders().dstDir + dir+"operation-" + name + ".json");
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, opd);
    s.close();
    jsonToXhtml(dir+"operation-" + name, "Operation Definition", resource2Json(opd), "resource-instance:OperationDefinition", "Operation definition");
    Utilities.copyFile(new CSFile(page.getFolders().dstDir + dir+"operation-" + name + ".xml"), new CSFile(page.getFolders().dstDir + "examples" + File.separator + "operation-" + name + ".xml"));
    if (buildFlags.get("all"))
      addToResourceFeed(opd, profileFeed, name);

    // now, we create an html page from the narrative
    String html = TextFile.fileToString(page.getFolders().srcDir + "template-example.html").replace("<%example%>", new XhtmlComposer().compose(opd.getText().getDiv()));
    html = page.processPageIncludes(dir+"operation-" + name + ".html", html, "resource-instance:OperationDefinition", null, null, null, "Operation Definition", ig);
    TextFile.stringToFile(html, page.getFolders().dstDir + dir+"operation-" + name + ".html");
    page.getEpub().registerFile(dir+"operation-" + name + ".html", "Operation " + op.getName(), EPubManager.XHTML_TYPE);
    // head =
    // "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">\r\n<head>\r\n <title>"+Utilities.escapeXml(e.getDescription())+"</title>\r\n <link rel=\"Stylesheet\" href=\"fhir.css\" type=\"text/css\" media=\"screen\"/>\r\n"+
    // "</head>\r\n<body>\r\n<p>&nbsp;</p>\r\n<p>"+Utilities.escapeXml(e.getDescription())+"</p>\r\n"+
    // "<p><a href=\""+n+".xml.html\">XML</a> <a href=\""+n+".json.html\">JSON</a></p>\r\n";
    // tail = "\r\n</body>\r\n</html>\r\n";
    // TextFile.stringToFile(head+narrative+tail, page.getFolders().dstDir + n +
    // ".html");
//    page.getEpub().registerExternal(dir+"operation-" + name + ".html");
    page.getEpub().registerExternal(dir+"operation-" + name + ".json.html");
    page.getEpub().registerExternal(dir+"operation-" + name + ".xml.html");
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
  private void jsonToXhtml(String n, String description, String json, String pageType, String crumbTitle) throws Exception {
    json = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml(description) + "</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(json)+ "\r\n</pre>\r\n</div>\r\n";
    String html = TextFile.fileToString(page.getFolders().srcDir + "template-example-json.html").replace("<%example%>", json);
    html = page.processPageIncludes(n + ".json.html", html, pageType, null, null, null, crumbTitle, null);
    TextFile.stringToFile(html, page.getFolders().dstDir + n + ".json.html");
    //    page.getEpub().registerFile(n + ".json.html", description, EPubManager.XHTML_TYPE);
    page.getEpub().registerExternal(n + ".json.html");
  }

  private void cloneToXhtml(String n, String description, boolean adorn, String pageType, String crumbTitle) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();

    Document xdoc = builder.parse(new CSFileInputStream(new CSFile(page.getFolders().dstDir + n + ".xml")));
    XhtmlGenerator xhtml = new XhtmlGenerator(new ExampleAdorner(page.getDefinitions(), page.genlevel(Utilities.charCount(n, File.separatorChar))));
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    xhtml.generate(xdoc, b, n.toUpperCase().substring(0, 1) + n.substring(1), description, 0, adorn, n + ".xml.html");
    String html = TextFile.fileToString(page.getFolders().srcDir + "template-example-xml.html").replace("<%example%>", b.toString());
    html = page.processPageIncludes(n + ".xml.html", html, pageType, null, null, null, crumbTitle, null);
    TextFile.stringToFile(html, page.getFolders().dstDir + n + ".xml.html");

    //    page.getEpub().registerFile(n + ".xml.html", description, EPubManager.XHTML_TYPE);
    page.getEpub().registerExternal(n + ".xml.html");
  }

  private void processQuestionnaire(StructureDefinition profile, SectionTracker st, boolean isResource, String prefix, ImplementationGuideDefn ig) throws Exception {
    
    QuestionnaireBuilder qb = new QuestionnaireBuilder(page.getWorkerContext());
    qb.setProfile(profile);
    qb.build();
    Questionnaire q = qb.getQuestionnaire();

    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + prefix+ profile.getId().toLowerCase() + "-questionnaire.json");
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, q);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + prefix+ profile.getId().toLowerCase() + "-questionnaire.xml");
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, q);
    s.close();

    String json = "<div class=\"example\">\r\n<p>Generated Questionnaire for "+profile.getId()+"</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(new JsonParser().setOutputStyle(OutputStyle.PRETTY).composeString(q)) + "\r\n</pre>\r\n</div>\r\n";
    String html = TextFile.fileToString(page.getFolders().srcDir + "template-example-json.html").replace("<%example%>", json);
    html = page.processPageIncludes(prefix+profile.getId().toLowerCase() + "-questionnaire.json.html", html, (isResource ? "resource-questionnaire:" : "profile-questionnaire:") + profile.getId(), null, null, null, "Questionnaire", ig);
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix+ profile.getId().toLowerCase() + "-questionnaire.json.html");

    String xml = "<div class=\"example\">\r\n<p>Generated Questionnaire for "+profile.getId()+"</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(new XmlParser().setOutputStyle(OutputStyle.PRETTY).composeString(q)) + "\r\n</pre>\r\n</div>\r\n";
    html = TextFile.fileToString(page.getFolders().srcDir + "template-example-xml.html").replace("<%example%>", xml);
    html = page.processPageIncludes(prefix+profile.getId().toLowerCase() + "-questionnaire.xml.html", html, (isResource ? "resource-questionnaire:" : "profile-questionnaire:") + profile.getId(), null, null, null, "Questionnaire", ig);
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix+ profile.getId().toLowerCase() + "-questionnaire.xml.html");

    if (web) {
      File tmpTransform = Utilities.createTempFile("tmp", ".html");
      //    if (web) {
      HashMap<String, String> params = new HashMap<String, String>();
      params.put("suppressWarnings", "true");
      Utilities.saxonTransform(
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
      html = TextFile.fileToString(page.getFolders().srcDir + (isResource ? "template-questionnaire.html" : "template-profile-questionnaire.html")).replace("<%questionnaire%>", loadHtmlForm(tmpTransform.getAbsolutePath()));
    } else
      html = "<html><p>Not generated in this build</p></html>";
    html = page.processPageIncludes(profile.getId().toLowerCase() + "-questionnaire.html", html, (isResource ? "resource-questionnaire:" : "profile-questionnaire:") + profile.getId(), null, profile, null, "Questionnaire", ig);
    int level = (ig == null || ig.isCore()) ? 0 : 1;
    if (st != null)
      html = insertSectionNumbers(html, st, profile.getId().toLowerCase() + "-questionnaire.html", level, null);
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix+ profile.getId().toLowerCase() + "-questionnaire.html");

    page.getEpub().registerExternal(prefix+ profile.getId().toLowerCase() + "-questionnaire.html");
    page.getEpub().registerExternal(prefix+ profile.getId().toLowerCase() + "-questionnaire.json.html");
    page.getEpub().registerExternal(prefix+ profile.getId().toLowerCase() + "-questionnaire.xml.html");
  }

  private String loadHtmlForm(String path) throws Exception {
    String form = TextFile.fileToString(path);
    form = form.replace("h5>", "h6>").replace("h4>", "h6>").replace("h3>", "h5>").replace("h2>", "h4>").replace("h1>", "h3>");
        
    form = form.replace("<!--header insertion point-->", "\r\n");
    form = form.replace("<!--body top insertion point-->", "\r\n");
    form = form.replace("<!--body bottom insertion point-->", "\r\n");
    return form;
  }

  private void processExample(Example e, String resourceName, StructureDefinition profile, Profile pack, ImplementationGuideDefn ig) throws Exception {
    if (e.getType() == ExampleType.Tool)
      return;
    
    int level = (ig == null || ig.isCore()) ? 0 : 1;
    String prefix = (ig == null || ig.isCore()) ? "" : ig.getCode() + File.separator;
    

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document xdoc;
    String narrative = null;
    String n = e.getTitle();

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
      NarrativeGenerator gen = new NarrativeGenerator("", "", page.getWorkerContext().clone(new SpecificationInternalClient(page, null)));
      xdoc = loadDom(new FileInputStream(file), true);
      rt = xdoc.getDocumentElement().getNodeName();
      String id = XMLUtil.getNamedChildValue(xdoc.getDocumentElement(), "id");
      if (!page.getDefinitions().getBaseResources().containsKey(rt) && !id.equals(e.getId()))
        throw new Error("Resource in "+prefix +n + ".xml needs an id of value=\""+e.getId()+"\"");
      if (rt.equals("ValueSet") || rt.equals("ConceptMap") || rt.equals("Conformance")) {
        // for these, we use the reference implementation directly
        DomainResource res = (DomainResource) new XmlParser().parse(new FileInputStream(file));
        boolean wantSave = false;
        if (res instanceof Conformance) {
          ((Conformance) res).setFhirVersion(page.getVersion());
          if (res.hasText() && res.getText().hasDiv()) 
            wantSave = updateVersion(((Conformance) res).getText().getDiv());
        }
        if (!res.hasText() || !res.getText().hasDiv()) {
          gen.generate(res);
          wantSave = true;
        } 
        if (wantSave)
          new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(file), res);
        narrative = new XhtmlComposer().compose(res.getText().getDiv());                  
      } else {
        if (rt.equals("Bundle")) {
          List<Element> entries = new ArrayList<Element>();
          XMLUtil.getNamedChildren(xdoc.getDocumentElement(), "entry", entries);
          boolean wantSave = false;
          for (Element entry : entries) {
            Element ers = XMLUtil.getFirstChild(XMLUtil.getNamedChild(entry, "resource"));
            if (ers != null) {
              String ert = ers.getLocalName();
              String s = null;
              if (!page.getDefinitions().getBaseResources().containsKey(ert) && !ert.equals("Binary") && !ert.equals("Parameters") && !ert.equals("Bundle")) {
                Element div = gen.getNarrative(ers); 
                if (div == null || !div.hasChildNodes()) {
                  wantSave = true;
                  s = gen.generate(ers);
                } else
                  s = new XmlGenerator().generate(div);
                if (s != null)
                  narrative = narrative == null ? s : narrative +"<hr/>\r\n"+s;
              }
              if (ert.equals("NamingSystem")) {
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                new XmlGenerator().generate(ers, bs);
                bs.close();
                NamingSystem ns = (NamingSystem) new XmlParser().parse(new ByteArrayInputStream(bs.toByteArray()));
                page.getDefinitions().getNamingSystems().add(ns);
              }
            }
          }
          if (wantSave)
            new XmlGenerator().generate(xdoc.getDocumentElement(), file, "http://hl7.org/fhir", xdoc.getDocumentElement().getLocalName());
        } else {
          if (!page.getDefinitions().getBaseResources().containsKey(rt) && !rt.equals("Binary") && !rt.equals("Parameters")) {
            Element div = gen.getNarrative(xdoc.getDocumentElement()); 
            if (div == null || !div.hasChildNodes()) {
              narrative = gen.generate(xdoc.getDocumentElement());
              new XmlGenerator().generate(xdoc.getDocumentElement(), file, "http://hl7.org/fhir", xdoc.getDocumentElement().getLocalName());
            } else {
              narrative = new XmlGenerator().generate(div);          
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
      narrative = new XhtmlComposer().compose(xhtml);
    }

    if (rt.equals("ValueSet")) {
      ValueSet vs = (ValueSet) new XmlParser().parse(new FileInputStream(file));
      vs.setUserData("filename", Utilities.changeFileExt(file.getName(), ""));
      vs.setUserData("committee", "fhir");

      page.getVsValidator().validate(page.getValidationErrors(), "Value set Example "+prefix +n, vs, false, false);
      if (vs.getUrl() == null)
        throw new Exception("Value set example " + e.getTitle() + " has no identifier");
      vs.setUserData("path", prefix +n + ".html");
      if (vs.getUrl().startsWith("http:"))
        page.getValueSets().put(vs.getUrl(), vs);
      if (vs.hasCodeSystem()) {
        page.getCodeSystems().put(vs.getCodeSystem().getSystem().toString(), vs);
      }
      addToResourceFeed(vs, valueSetsFeed, file.getName());
      page.getDefinitions().getValuesets().put(vs.getUrl(), vs);
      if (vs.hasCodeSystem()) {
        page.getDefinitions().getCodeSystems().put(vs.getCodeSystem().getSystem(), vs);
      }
    } else if (rt.equals("ConceptMap")) {
      ConceptMap cm = (ConceptMap) new XmlParser().parse(new FileInputStream(file));
      new ConceptMapValidator(page.getDefinitions(), e.getTitle()).validate(cm, false);
      if (cm.getUrl() == null)
        throw new Exception("Value set example " + e.getTitle() + " has no identifier");
      addToResourceFeed(cm, conceptMapsFeed, file.getName());
      page.getDefinitions().getConceptMaps().put(cm.getUrl(), cm);
      cm.setUserData("path", prefix +n + ".html");
      page.getConceptMaps().put(cm.getUrl(), cm);
    }

    // queue for json and canonical XML generation processing
    e.setResourceName(resourceName);
    processingList.put(prefix +n, e);

    // reload it now, xml to xhtml of xml
    builder = factory.newDocumentBuilder();
    xdoc = builder.parse(new CSFileInputStream(file));
    XhtmlGenerator xhtml = new XhtmlGenerator(new ExampleAdorner(page.getDefinitions(), page.genlevel(level)));
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    xhtml.generate(xdoc, b, n.toUpperCase().substring(0, 1) + n.substring(1), Utilities.noString(e.getId()) ? e.getDescription() : e.getDescription()
        + " (id = \"" + e.getId() + "\")", 0, true, n + ".xml.html");
    String html = TextFile.fileToString(page.getFolders().srcDir + "template-example-xml.html").replace("<%example%>", b.toString());
    html = page.processPageIncludes(n + ".xml.html", html, resourceName == null ? "profile-instance:resource:" + rt : "resource-instance:" + resourceName, null, profile, null, "Example", ig);
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix +n + ".xml.html");
    XhtmlDocument d = new XhtmlParser().parse(new CSFileInputStream(page.getFolders().dstDir + prefix +n + ".xml.html"), "html");
    XhtmlNode pre = d.getElement("html").getElement("body").getElement("div");
    e.setXhtm(b.toString());
    if (!Utilities.noString(e.getId()))
      Utilities.copyFile(file, new CSFile(page.getFolders().dstDir + "examples" + File.separator + n + "(" + e.getId() + ").xml"));
    else
      Utilities.copyFile(file, new CSFile(page.getFolders().dstDir + "examples" + File.separator + n + ".xml"));

    // now, we create an html page from the narrative
    html = TextFile.fileToString(page.getFolders().srcDir + "template-example.html").replace("<%example%>", narrative == null ? "" : narrative).replace("<%example-usage%>", genExampleUsage(e));
    html = page.processPageIncludes(n + ".html", html, resourceName == null ? "profile-instance:resource:" + rt : "resource-instance:" + resourceName, null, profile, null, "Example", ig);
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix +n + ".html");
    // head =
    // "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">\r\n<head>\r\n <title>"+Utilities.escapeXml(e.getDescription())+"</title>\r\n <link rel=\"Stylesheet\" href=\"fhir.css\" type=\"text/css\" media=\"screen\"/>\r\n"+
    // "</head>\r\n<body>\r\n<p>&nbsp;</p>\r\n<p>"+Utilities.escapeXml(e.getDescription())+"</p>\r\n"+
    // "<p><a href=\""+n+".xml.html\">XML</a> <a href=\""+n+".json.html\">JSON</a></p>\r\n";
    // tail = "\r\n</body>\r\n</html>\r\n";
    // TextFile.stringToFile(head+narrative+tail, page.getFolders().dstDir + n +
    // ".html");
    page.getEpub().registerExternal(prefix +n + ".html");
    page.getEpub().registerExternal(prefix +n + ".xml.html");
  }

  private boolean updateVersion(XhtmlNode div) {
    if (div.getNodeType().equals(NodeType.Text)) {
      if (div.getContent().contains("$ver$")) {
        div.setContent(div.getContent().replace("$ver$", page.getVersion()));
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

  private String genExampleUsage(Example e) {
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
        b.append(x.getTitle()+".html");
        b.append("\">");
        b.append(x.getResourceName()+"/"+x.getName());
        b.append("</a></li>\r\n");
      }
      b.append("</ul>\r\n");
      return b.toString();
    }
  }

  private void processExamplesByBatch() throws Exception {
    page.log(" ...process examples", LogMessageType.Process);
    
    try {
      javaReferencePlatform.processExamples(page.getFolders(), page.getFolders().tmpDir, processingList.keySet());
    } catch (Throwable t) {
      System.out.println("Error processing examples");
      t.printStackTrace(System.err);
    }
    for (String n : processingList.keySet()) {
      Example e = processingList.get(n);
      String json = TextFile.fileToString(page.getFolders().dstDir + n + ".json");
      String json2 = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml(e.getDescription()) + "</p>\r\n<p><a href=\""+ n + ".json\">Raw JSON</a> (<a href=\""+n + ".canonical.json\">Canonical</a>)</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(json)
          + "\r\n</pre>\r\n</div>\r\n";
      json = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml(e.getDescription()) + "</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(json)
          + "\r\n</pre>\r\n</div>\r\n";
      String html = TextFile.fileToString(page.getFolders().srcDir + "template-example-json.html").replace("<%example%>", json);
      html = page.processPageIncludes(n + ".json.html", html, e.getResourceName() == null ? "profile-instance:resource:" + e.getResourceName() : "resource-instance:" + e.getResourceName(), null, null, null, "Example", null);
      TextFile.stringToFile(html, page.getFolders().dstDir + n + ".json.html");

      page.getEpub().registerExternal(n + ".json.html");
    }
    processingList.clear();
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

  private StructureDefinition generateProfile(ResourceDefn root, String n, String xmlSpec, String jsonSpec) throws Exception, FileNotFoundException {
    StructureDefinition rp = root.getProfile();
    page.getProfiles().put("http://hl7.org/fhir/StructureDefinition/"+root.getName(), rp);
    page.getProfiles().put(root.getName(), rp);
    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + n + ".profile.xml");
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(s, rp);
    s.close();
    s = new FileOutputStream(page.getFolders().dstDir + n + ".profile.json");
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(s, rp);
    s.close();

    Utilities.copyFile(new CSFile(page.getFolders().dstDir + n + ".profile.xml"), new CSFile(page.getFolders().dstDir + "examples" + File.separator + n
        + ".profile.xml"));
    if (buildFlags.get("all")) {
      addToResourceFeed(rp, profileFeed, null);
    }
    saveAsPureHtml(rp, new FileOutputStream(page.getFolders().dstDir + "html" + File.separator + n + ".html"));
    cloneToXhtml(n + ".profile", "StructureDefinition for " + n, true, "profile-instance:resource:" + root.getName(), "Profile");
    jsonToXhtml(n + ".profile", "StructureDefinition for " + n, resource2Json(rp), "profile-instance:resource:" + root.getName(), "Profile");
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

  private void saveAsPureHtml(StructureDefinition resource, FileOutputStream stream) throws Exception {
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
    XhtmlComposer xml = new XhtmlComposer();
    xml.setPretty(false);
    xml.compose(stream, html);
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
    if (vs.getText() == null || vs.getText().getDiv() == null)
      throw new Exception("Example Value Set " + vs.getId() + " does not have any narrative");

    ResourceUtilities.meta(vs).setLastUpdated(page.getGenDate().getTime());
    if (vs.getUrl().startsWith("http://hl7.org/fhir/") && !vs.getUrl().equals("http://hl7.org/fhir/"+vs.getResourceType().toString()+"/"+vs.getId()))
      throw new Exception("URL mismatch on value set: "+vs.getUrl()+" vs "+"http://hl7.org/fhir/"+vs.getResourceType().toString()+"/"+vs.getId());
    dest.getEntry().add(new BundleEntryComponent().setResource(vs).setFullUrl(vs.getUrl()));
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
    dest.getEntry().add(new BundleEntryComponent().setResource(cm).setFullUrl(cm.getUrl()));
  }

  private void addToResourceFeed(Conformance conf, Bundle dest) throws Exception {
    if (conf.getId() == null)
      throw new Exception("Resource has no id");
    if (ResourceUtilities.getById(dest, ResourceType.ValueSet, conf.getId()) != null)
      throw new Exception("Attempt to add duplicate Conformance " + conf.getId());
    if (conf.getText() == null || conf.getText().getDiv() == null)
      throw new Exception("Example Conformance " + conf.getId() + " does not have any narrative");

    ResourceUtilities.meta(conf).setLastUpdated(page.getGenDate().getTime());
    if (!conf.getUrl().equals("http://hl7.org/fhir/"+conf.getResourceType().toString()+"/"+conf.getId()))
      throw new Exception("URL mismatch on conformance stmt");
    dest.getEntry().add(new BundleEntryComponent().setResource(conf).setFullUrl(conf.getUrl()));
  }

  private void produceConformancePackage(String resourceName, Profile pack, SectionTracker st) throws Exception {
    if (Utilities.noString(resourceName)) {
      if (pack.getProfiles().size() == 1)
        resourceName = pack.getProfiles().get(0).getDefn().getName();
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
    
    String intro = pack.getIntroduction() != null ? page.loadXmlNotesFromFile(pack.getIntroduction(), false, null, null, null, null) : null;
    String notes = pack.getNotes() != null ? page.loadXmlNotesFromFile(pack.getNotes(), false, null, null, null, null) : null;

    if (!("profile".equals(pack.metadata("navigation")) && pack.getProfiles().size() == 1)) {
      String src = TextFile.fileToString(page.getFolders().srcDir + "template-conformance-pack.html");
      src = page.processConformancePackageIncludes(pack, src, intro, notes, resourceName, ig);
      if (st != null)
        src = insertSectionNumbers(src, st, pack.getId().toLowerCase() + ".html",  1, null);
      page.getEpub().registerFile(prefix+pack.getId().toLowerCase() + ".html", "Profile " + pack.getId(), EPubManager.XHTML_TYPE);
      TextFile.stringToFile(src, page.getFolders().dstDir + prefix+pack.getId() + ".html");
    }

    // now, we produce each profile
    for (ConstraintStructure profile : pack.getProfiles())
      produceProfile(resourceName, pack, profile, st, intro, notes, prefix, ig);

    for (SearchParameter sp : pack.getSearchParameters())
      producePackSearchParameter(resourceName, pack, sp, st, ig);
    
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
        processExample(ex, resourceName, null, null, ig);
      else
        processExample(ex, resourceName, sd, pack, ig);
    }
    // create examples here
//    if (examples != null) {
//      for (String en : examples.keySet()) {
//        processExample(examples.get(en), null, profile.getSource());
  }

  private void producePackSearchParameter(String resourceName, Profile pack, SearchParameter sp, SectionTracker st, ImplementationGuideDefn ig) throws Exception {
    String title = sp.getId();
    sp.setUserData("pack", pack.getId());

    String prefix = (ig == null || ig.isCore()) ? "" : ig.getCode()+File.separator;
    int level = (ig == null || ig.isCore()) ? 0 : 1;
    
    XmlParser comp = new XmlParser();
    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + prefix+title + ".xml");
    comp.setOutputStyle(OutputStyle.PRETTY).compose(s, sp);
    s.close();
    JsonParser jcomp = new JsonParser();
    s = new FileOutputStream(page.getFolders().dstDir + prefix+title + ".json");
    jcomp.setOutputStyle(OutputStyle.PRETTY).compose(s, sp);
    s.close();

    String src = TextFile.fileToString(page.getFolders().srcDir + "template-search-parameter.html");
    src = page.processPageIncludes(sp.getId()+".html", src, "search-parameter:"+resourceName+"/"+pack.getId()+"/"+sp.getId(), null, sp, null, "Search Parameter", ig);
    if (st != null)
      src = insertSectionNumbers(src, st, title + ".html", level, null);
    page.getEpub().registerFile(prefix+title + ".html", "SearchParameter " + sp.getName(), EPubManager.XHTML_TYPE);
    TextFile.stringToFile(src, page.getFolders().dstDir + prefix+title + ".html");
    cloneToXhtml(prefix+title, "Search Parameter "+sp.getName(), false, "searchparam-instance", "Search Parameter");
    
    String json = resource2Json(sp);
    json = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml("SearchParameter " + sp.getName()) + "</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(json)+ "\r\n</pre>\r\n</div>\r\n";
    String html = TextFile.fileToString(page.getFolders().srcDir + "template-example-json.html").replace("<%example%>", json);
    html = page.processPageIncludes(title + ".json.html", html, resourceName+"/"+pack.getId()+"/"+sp.getId(), null, sp, null, "Search Parameter", ig);
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix+title + ".json.html");
    page.getEpub().registerExternal(prefix+title + ".json.html");
  }

  private void produceProfile(String resourceName, Profile pack, ConstraintStructure profile, SectionTracker st, String intro, String notes, String prefix, ImplementationGuideDefn ig) throws Exception {
    File tmp = Utilities.createTempFile("tmp", ".tmp");
    String title = profile.getId();
    int level = (ig == null || ig.isCore()) ? 0 : 1;

    // you have to validate a profile, because it has to be merged with it's
    // base resource to fill out all the missing bits
    //    validateProfile(profile);
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    XmlSpecGenerator gen = new XmlSpecGenerator(bs, null, "http://hl7.org/fhir/", page, "");
    gen.generate(profile.getResource());
    gen.close();
    String xml = new String(bs.toByteArray());

    bs = new ByteArrayOutputStream();
    JsonSpecGenerator genJ = new JsonSpecGenerator(bs, null, "http://hl7.org/fhir/", page, "");
    // genJ.generate(profile.getResource());
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

    TerminologyNotesGenerator tgen = new TerminologyNotesGenerator(new FileOutputStream(tmp), page);
    tgen.generate(level == 0 ? "" : "../", profile);
    tgen.close();
    String tx = TextFile.fileToString(tmp.getAbsolutePath());

    String src = TextFile.fileToString(page.getFolders().srcDir + "template-profile.html");
    src = page.processProfileIncludes(profile.getId(), profile.getId(), pack, profile, xml, json, tx, src, title + ".html", resourceName+"/"+pack.getId()+"/"+profile.getId(), intro, notes, ig);
    if (st != null)
      src = insertSectionNumbers(src, st, title + ".html", level, null);
    page.getEpub().registerFile(prefix +title + ".html", "StructureDefinition " + profile.getResource().getName(), EPubManager.XHTML_TYPE);
    TextFile.stringToFile(src, page.getFolders().dstDir + prefix +title + ".html");
    new ProfileUtilities(page.getWorkerContext()).generateSchematrons(new FileOutputStream(page.getFolders().dstDir + prefix +title + ".sch"), profile.getResource());

//    src = TextFile.fileToString(page.getFolders().srcDir + "template-profile-examples.html");
//    src = page.processProfileIncludes(profile.getId(), pack, profile, xml, tx, src, intro, notes, title + ".html");
//    page.getEpub().registerFile(title + "-examples.html", "Examples for StructureDefinition " + profile.getResource().getName(), EPubManager.XHTML_TYPE);
//    TextFile.stringToFile(src, page.getFolders().dstDir + title + "-examples.html");

    src = TextFile.fileToString(page.getFolders().srcDir + "template-profile-definitions.html");
    src = page.processProfileIncludes(profile.getId(), profile.getId(), pack, profile, xml, json, tx, src, title + ".html", resourceName+"/"+pack.getId()+"/"+profile.getId(), intro, notes, ig);
    if (st != null)
      src = insertSectionNumbers(src, st, title + "-definitions.html", level, null);
    page.getEpub().registerFile(prefix +title + "-definitions.html", "Definitions for StructureDefinition " + profile.getResource().getName(), EPubManager.XHTML_TYPE);
    TextFile.stringToFile(src, page.getFolders().dstDir + prefix +title + "-definitions.html");

    src = TextFile.fileToString(page.getFolders().srcDir + "template-profile-mappings.html");
    src = page.processProfileIncludes(profile.getId(), profile.getId(), pack, profile, xml, json, tx, src, title + ".html", resourceName+"/"+pack.getId()+"/"+profile.getId(), intro, notes, ig);
    if (st != null)
      src = insertSectionNumbers(src, st, title + "-mappings.html", level, null);
    page.getEpub().registerFile(prefix +title + "-mappings.html", "Mappings for StructureDefinition " + profile.getResource().getName(), EPubManager.XHTML_TYPE);
    TextFile.stringToFile(src, page.getFolders().dstDir + prefix +title + "-mappings.html");

    try {
      processQuestionnaire(profile.getResource(), st, false, prefix, ig);
    } catch (Exception e) {
      e.printStackTrace();
      page.log("Questionnaire Generation Failed: "+e.getMessage(), LogMessageType.Error);
    }
    new ReviewSpreadsheetGenerator().generate(page.getFolders().dstDir +prefix+ Utilities.changeFileExt((String) profile.getResource().getUserData("filename"), "-review.xls"), "Health Level Seven International", page.getGenDate(), profile.getResource(), page);

    //
    // src = Utilities.fileToString(page.getFolders().srcDir +
    // "template-print.html").replace("<body>",
    // "<body style=\"margin: 20px\">");
    // src = processResourceIncludes(n, root, xml, tx, dict, src);
    // Utilities.stringToFile(src, page.getFolders().dstDir +
    // "print-"+n+".html");
    // Utilities.copyFile(umlf, new
    // File(page.getFolders().dstDir+n+".png"));
    // src = Utilities.fileToString(page.getFolders().srcDir +
    // "template-book.html").replace("<body>",
    // "<body style=\"margin: 10px\">");
    // src = processResourceIncludes(n, root, xml, tx, dict, src);
    // cachePage(n+".html", src);
    //
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
    String html = TextFile.fileToString(page.getFolders().srcDir + "template-profile-example-xml.html").replace("<%example%>", b.toString());
    html = page.processProfileIncludes(title + ".profile.xml.html", profile.getId(), pack, profile, "", "", "", html, title + ".html", resourceName+"/"+pack.getId()+"/"+profile.getId(), intro, notes, ig);
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix +title + ".profile.xml.html");

    page.getEpub().registerFile(prefix +title + ".profile.xml.html", "StructureDefinition", EPubManager.XHTML_TYPE);
    String n = prefix +title + ".profile";
    json = resource2Json(profile.getResource());
    json = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml("StructureDefinition for " + profile.getResource().getDescription()) + "</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(json)+ "\r\n</pre>\r\n</div>\r\n";
    html = TextFile.fileToString(page.getFolders().srcDir + "template-profile-example-json.html").replace("<%example%>", json);
    html = page.processProfileIncludes(title + ".profile.json.html", profile.getId(), pack, profile, "", "", "", html, title + ".html", resourceName+"/"+pack.getId()+"/"+profile.getId(), intro, notes, ig);
    TextFile.stringToFile(html, page.getFolders().dstDir + prefix +title + ".profile.json.html");
    //    page.getEpub().registerFile(n + ".json.html", description, EPubManager.XHTML_TYPE);
    page.getEpub().registerExternal(n + ".json.html");
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

  private void produceIgPage(String source, String file, String logicalName) throws Exception {
    String src = TextFile.fileToString(source);
    src = page.processPageIncludes(file, src, "page", null, null, null, logicalName, null);
    // before we save this page out, we're going to figure out what it's index
    // is, and number the headers if we can

    if (Utilities.noString(logicalName))
      logicalName = Utilities.fileTitle(file);

    TextFile.stringToFile(src, page.getFolders().dstDir + file);
    src = addSectionNumbers(file, logicalName, src, null, 0, null);

    TextFile.stringToFile(src, page.getFolders().dstDir + file);

    src = TextFile.fileToString(source).replace("<body>", "<body style=\"margin: 10px\">");
    src = page.processPageIncludesForBook(file, src, "page", null, null);
    cachePage(file, src, logicalName);
  }

  private void producePage(String file, String logicalName) throws Exception {
    String src = TextFile.fileToString(page.getFolders().srcDir + file);
    src = page.processPageIncludes(file, src, "page", null, null, null, logicalName, null);
    // before we save this page out, we're going to figure out what it's index
    // is, and number the headers if we can

    if (Utilities.noString(logicalName))
      logicalName = Utilities.fileTitle(file);

    TextFile.stringToFile(src, page.getFolders().dstDir + file);
    DocumentHolder doch = new DocumentHolder();
    src = addSectionNumbers(file, logicalName, src, null, 0, doch);

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
    src = page.processPageIncludesForBook(file, src, "page", null, null);
    cachePage(file, src, logicalName);
  }

  private String get2ndPart(String t) {
    return t.substring(t.indexOf(":")+1).trim();
  }

  private void produceIgPage(String file, ImplementationGuideDefn ig) throws Exception {
    String actualName = Utilities.path(page.getFolders().rootDir, Utilities.getDirectoryForFile(ig.getSource()), file);
    String logicalName = Utilities.fileTitle(actualName);
    String src = TextFile.fileToString(actualName);
    file = ig.getCode()+File.separator+logicalName +".html";
    
    src = page.processPageIncludes(file, src, "page", null, null, null, logicalName, ig);
    // before we save this page out, we're going to figure out what it's index
    // is, and number the headers if we can

    TextFile.stringToFile(src, page.getFolders().dstDir + file);
    src = addSectionNumbers(file, logicalName, src, null, 1, null);

    TextFile.stringToFile(src, page.getFolders().dstDir + file);

    src = TextFile.fileToString(actualName).replace("<body>", "<body style=\"margin: 10px\">");
    src = page.processPageIncludesForBook(file, src, "page", null, ig);
    cachePage(file, src, logicalName);
  }

  private void produceIgPage(String file, ImplementationGuideDefn ig, String logicalName) throws Exception {
    String srcOrig = TextFile.fileToString(page.getFolders().srcDir + file);
    file = file.substring(3);
    String src = page.processPageIncludes(file, srcOrig, "page", null, null, null, logicalName, ig);
    // before we save this page out, we're going to figure out what it's index
    // is, and number the headers if we can

    if (Utilities.noString(logicalName))
      logicalName = Utilities.fileTitle(file);

    TextFile.stringToFile(src, Utilities.path(page.getFolders().dstDir, ig.getCode(), file));
    DocumentHolder doch = new DocumentHolder();
    src = addSectionNumbers(file, logicalName, src, null, 0, doch);

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
    src = page.processPageIncludesForBook(file, src, "page", null, ig);
    cachePage(ig.getCode()+File.separator+file, src, logicalName);
  }

  private void produceLogicalModel(LogicalModel lm, ImplementationGuideDefn ig) throws Exception {
    String n = lm.getId();
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    XmlSpecGenerator gen = new XmlSpecGenerator(bs, n + "-definitions.html", null, page, "../");
    gen.generate(lm.getRoot(), true);
    gen.close();
    String xml = new String(bs.toByteArray());

    bs = new ByteArrayOutputStream();
    JsonSpecGenerator genJ = new JsonSpecGenerator(bs, n + "-definitions.html", null, page, "../");
    genJ.generate(lm.getRoot(), true, true);
    genJ.close();
    String json = new String(bs.toByteArray());

//    xmls.put(n, xml);
//    jsons.put(n, json);

    File tmp = Utilities.createTempFile("tmp", ".tmp");

    TerminologyNotesGenerator tgen = new TerminologyNotesGenerator(new FileOutputStream(tmp), page);
    tgen.generate("../", lm.getRoot());
    tgen.close();
    String tx = TextFile.fileToString(tmp.getAbsolutePath());

    DictHTMLGenerator dgen = new DictHTMLGenerator(new FileOutputStream(tmp), page, "../");
    dgen.generate(lm.getRoot());
    dgen.close();
    String dict = TextFile.fileToString(tmp.getAbsolutePath());

    MappingsGenerator mgen = new MappingsGenerator(page.getDefinitions());
    mgen.generate(lm.getResource());
    String mappings = mgen.getMappings();
    String mappingsList = mgen.getMappingsList();

    SvgGenerator svg = new SvgGenerator(page, "../");
    String fn = ig.getCode()+File.separator+n;
    svg.generate(lm.getResource(), page.getFolders().dstDir + fn+".svg", "2");

    String prefix = page.getBreadCrumbManager().getIndexPrefixForReference(lm.getId());
    SectionTracker st = new SectionTracker(prefix);
    st.start("");
    page.getSectionTrackerCache().put(fn, st);

    String template = "template-logical";
    String src = TextFile.fileToString(page.getFolders().srcDir + template+".html");
    src = insertSectionNumbers(page.processResourceIncludes(n, lm.getResource(), xml, json, tx, dict, src, mappings, mappingsList, "resource", n + ".html", ig), st, n + ".html", 1, null);
    TextFile.stringToFile(src, page.getFolders().dstDir + fn+".html");
    page.getEpub().registerFile(fn+".html", "Base Page for " + n, EPubManager.XHTML_TYPE);

    src = TextFile.fileToString(page.getFolders().srcDir + "template-logical-definitions.html");
    TextFile.stringToFile(
        insertSectionNumbers(page.processResourceIncludes(n, lm.getResource(), xml, json, tx, dict, src, mappings, mappingsList, "res-Detailed Descriptions", n + "-definitions.html", ig), st, n
            + "-definitions.html", 0, null), page.getFolders().dstDir + fn+"-definitions.html");
    page.getEpub().registerFile(fn+"-definitions.html", "Detailed Descriptions for " + lm.getResource().getName(), EPubManager.XHTML_TYPE);

    src = TextFile.fileToString(page.getFolders().srcDir + "template-logical-mappings.html");
    TextFile.stringToFile(
        insertSectionNumbers(page.processResourceIncludes(n, lm.getResource(), xml, json, tx, dict, src, mappings, mappingsList, "res-Mappings", n + "-mappings.html", ig), st, n + "-mappings.html", 0, null),
        page.getFolders().dstDir + fn + "-mappings.html");
    page.getEpub().registerFile(fn+"-mappings.html", "Formal Mappings for " + n, EPubManager.XHTML_TYPE);

    tmp.delete();
  }


  private void produceDictionary(Dictionary d) throws Exception {
    if (web)
      return; 
    
    String src = TextFile.fileToString(page.getFolders().srcDir + "template-dictionary.html");
    String file = d.getSource();
    String prefix = d.getIg() != null ? d.getIg().getCode()+File.separator : "";
    String filename = prefix+d.getId();
    XmlParser xml = new XmlParser();
    Bundle dict = (Bundle) xml.parse(new CSFileInputStream(file));

    src = page.processPageIncludes(filename+".html", src, "page", null, dict, null, "Dictionary", null);
    // before we save this page out, we're going to figure out what it's index
    // is, and number the headers if we can

    TextFile.stringToFile(src, page.getFolders().dstDir + filename+".html");
    src = addSectionNumbers(filename, filename, src, null, d.getIg() != null ? 1 : 0, null);

    TextFile.stringToFile(src, page.getFolders().dstDir + d.getId()+".html");

    src = TextFile.fileToString(page.getFolders().srcDir + "template-dictionary.html").replace("<body>", "<body style=\"margin: 10px\">");
    src = page.processPageIncludesForBook(filename+".html", src, "page", dict, null);
    cachePage(filename+".html", src, d.getId());

    xml.setOutputStyle(OutputStyle.PRETTY);
    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + filename+".xml");
    xml.compose(s, dict);
    s.close();
    cloneToXhtml(filename, "Source for Dictionary" + d.getName(), false, "dict-instance", "Dictionary");
    IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
    s = new FileOutputStream(page.getFolders().dstDir+filename+ ".json");
    json.compose(s, dict);
    s.close();
    jsonToXhtml(filename, "Source for Dictionary" + d.getName(), resource2Json(dict), "dict-instance", "Dictionary");
    for (BundleEntryComponent e : dict.getEntry()) {
      produceDictionaryProfile(d, file, filename, (DataElement) e.getResource(), d.getIg());
    }
  }

  private void produceDictionaryProfile(Dictionary d, String srcbase, String destbase, DataElement de, ImplementationGuideDefn ig) throws Exception {
    // first, sort out identifiers
    String template = TextFile.fileToString(Utilities.changeFileExt(srcbase, "-profile.xml"));
    String file = Utilities.changeFileExt(destbase, "-"+de.getId());    

    // second, generate the profile.
    Map<String, String> variables = new HashMap<String, String>();
    variables.put("de_id", de.getId());
    variables.put("de_name", de.getName());
    variables.put("de_definition", Utilities.noString(de.getElement().get(0).getDefinition()) ? "??" : de.getElement().get(0).getDefinition());
    variables.put("de_code0_code", de.getElement().get(0).getCode().get(0).getCode());
    Type ucc = ToolingExtensions.getAllowedUnits(de.getElement().get(0));
    if (ucc instanceof CodeableConcept)
      variables.put("de_units_code0_code", ((CodeableConcept) ucc).getCoding().get(0).getCode());
    else
      variables.put("de_units_code0_code", "");
    String profile = processTemplate(template, variables);
    XmlParser xml = new XmlParser();
    StructureDefinition p = (StructureDefinition) xml.parse(new ByteArrayInputStream(profile.getBytes()));
    StructureDefinition base = page.getProfiles().get(p.getBase());
    if (base == null)
      throw new Exception("Unable to find base profile for "+d.getId()+": "+p.getBase()+" from "+page.getProfiles().keySet());
    new ProfileUtilities(page.getWorkerContext()).generateSnapshot(base, p, p.getBase(), p.getId(), page, page.getValidationErrors());
    ConstraintStructure pd = new ConstraintStructure(p, page.getDefinitions().getUsageIG("hspc", "special HSPC generation")); // todo
    pd.setId(p.getId());
    pd.setTitle(p.getName());
    Profile pack = new Profile("hspc");
    pack.forceMetadata("date", p.getDateElement().asStringValue());
    p.setUserData("filename", file  );

    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    XmlSpecGenerator gen = new XmlSpecGenerator(bs, null, "http://hl7.org/fhir/", page, "");
    gen.generate(p);
    gen.close();
    String xmls = new String(bs.toByteArray());
    bs = new ByteArrayOutputStream();
    JsonSpecGenerator genJ = new JsonSpecGenerator(bs, null, "http://hl7.org/fhir/", page, "");
    // genJ.generate(profile.getResource());
    genJ.close();
    String jsons = new String(bs.toByteArray());

    String tx = ""; //todo

    String src = TextFile.fileToString(page.getFolders().srcDir + "template-profile.html");
    src = page.processProfileIncludes(p.getId(), p.getId(), pack, pd, xmls, jsons, tx, src, file + ".html", "??/??/??", "", "", ig); // resourceName+"/"+pack.getId()+"/"+profile.getId());
    page.getEpub().registerFile(file + ".html", "StructureDefinition " + p.getName(), EPubManager.XHTML_TYPE);
    TextFile.stringToFile(src, page.getFolders().dstDir + file + ".html");

    src = TextFile.fileToString(page.getFolders().srcDir + "template-profile-mappings.html");
    src = page.processProfileIncludes(p.getId(), p.getId(), pack, pd, xmls, jsons, tx, src, file + ".html", "??/??/??", "", "", ig);
    page.getEpub().registerFile(file + "-mappings.html", "Mappings for StructureDefinition " + p.getName(), EPubManager.XHTML_TYPE);
    TextFile.stringToFile(src, page.getFolders().dstDir + file + "-mappings.html");

//    src = TextFile.fileToString(page.getFolders().srcDir + "template-profile-examples.html");
//    src = page.processProfileIncludes(profile.getId(), pack, profile, xml, tx, src, intro, notes, title + ".html");
//    page.getEpub().registerFile(title + "-examples.html", "Examples for StructureDefinition " + profile.getResource().getName(), EPubManager.XHTML_TYPE);
//    TextFile.stringToFile(src, page.getFolders().dstDir + title + "-examples.html");

    src = TextFile.fileToString(page.getFolders().srcDir + "template-profile-definitions.html");
    src = page.processProfileIncludes(p.getId(), p.getId(), pack, pd, xmls, jsons, tx, src, file + ".html", "??/??/??", "", "", ig);
    page.getEpub().registerFile(file + "-definitions.html", "Definitions for StructureDefinition " + p.getName(), EPubManager.XHTML_TYPE);
    TextFile.stringToFile(src, page.getFolders().dstDir + file + "-definitions.html");

    // now, save the profile and generate equivalents
    xml.setOutputStyle(OutputStyle.PRETTY);
    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + file+".profile.xml");
    xml.compose(s, p);
    s.close();
    cloneToXhtml(file+".profile", "Source for Dictionary" + page.getDefinitions().getDictionaries().get(file), false, "dict-instance", "Profile");
    IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
    s = new FileOutputStream(page.getFolders().dstDir+file+ ".profile.json");
    json.compose(s, p);
    s.close();
    jsonToXhtml(file+".profile", "Source for Dictionary based StructureDefinition" + page.getDefinitions().getDictionaries().get(file), resource2Json(p), "dict-instance", "Profile");
    new ReviewSpreadsheetGenerator().generate(page.getFolders().dstDir + file+ "-review.xls", "Health Level Seven International", page.getGenDate(), p, page);
  }

  private String processTemplate(String template, Map<String, String> variables) {
    ST st = new ST(template, '$', '$');
    for (String var : variables.keySet())
      st.add(var, variables.get(var));
    return st.render();
  }

  private void produceSid(int i, String logicalName, String file) throws Exception {
    String src = TextFile.fileToString(page.getFolders().srcDir + file);
    String dstName = Utilities.path(page.getFolders().dstDir, "sid", logicalName, "index.html");
    src = page.processPageIncludes(dstName, src, "sid:" + logicalName, null, null, null, "Sid", null);
    // before we save this page out, we're going to figure out what it's index
    // is, and number the headers if we can

    Utilities.createDirectory(Utilities.path(page.getFolders().dstDir, "sid", logicalName));
    TextFile.stringToFile(src, dstName);
    src = addSectionNumbers(file, "sid:terminologies-systems", src, "3." + Integer.toString(i), 0, null);
    TextFile.stringToFile(src, dstName);
  }

  private String addSectionNumbers(String file, String logicalName, String src, String id, int level, DocumentHolder doch) throws Exception {
    if (!page.getSectionTrackerCache().containsKey(logicalName)) {
      // String prefix =
      // page.getNavigation().getIndexPrefixForFile(logicalName+".html");
      String prefix = page.getBreadCrumbManager().getIndexPrefixForFile(logicalName + ".html");
      if (Utilities.noString(prefix))
        throw new Exception("No indexing home for logical place " + logicalName);
      page.getSectionTrackerCache().put(logicalName, new SectionTracker(prefix));
    }
    SectionTracker st = page.getSectionTrackerCache().get(logicalName);
    st.start(id);
    src = insertSectionNumbers(src, st, file, level, doch);
    return src;
  }

  private void produceCompartment(Compartment c) throws Exception {

    String logicalName = "compartment-" + c.getName();
    String file = logicalName + ".html";
    String src = TextFile.fileToString(page.getFolders().srcDir + "template-compartment.html");
    src = page.processPageIncludes(file, src, "compartment", null, null, null, "Compartment", null);

    // String prefix = "";
    // if
    // (!page.getSectionTrackerCache().containsKey("compartment-"+c.getName()))
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

    src = TextFile.fileToString(page.getFolders().srcDir + "template-compartment.html").replace("<body>", "<body style=\"margin: 10px\">");
    src = page.processPageIncludesForBook(file, src, "compartment", null, null);
    cachePage(file, src, "Compartments");
  }

  private String insertSectionNumbers(String src, SectionTracker st, String link, int level, DocumentHolder doch) throws Exception {
    try {
      // TextFile.stringToFile(src, "c:\\temp\\text.html");
      XhtmlDocument doc = new XhtmlParser().parse(src, "html");
      insertSectionNumbersInNode(doc, st, link, level);
      if (doch != null)
        doch.doc = doc;
      return new XhtmlComposer().compose(doc);
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

  private void insertSectionNumbersInNode(XhtmlNode node, SectionTracker st, String link, int level) throws Exception {
    if (node.getNodeType() == NodeType.Element
        && (node.getName().equals("h1") || node.getName().equals("h2") || node.getName().equals("h3") || node.getName().equals("h4")
            || node.getName().equals("h5") || node.getName().equals("h6"))) {
      String v = st.getIndex(Integer.parseInt(node.getName().substring(1)));
      TocEntry t = new TocEntry(v, node.allText(), link);
      page.getToc().put(v, t);
      node.addText(0, " ");
      XhtmlNode span = node.addTag(0, "span");
      span.setAttribute("class", "sectioncount");
      span.addText(v);
      XhtmlNode a = span.addTag("a");
      a.setAttribute("name", v);
        a.addText(" "); // bug in some browsers?
        node.addText(" ");
        a = node.addTag("a");
        a.setAttribute("href", link+"#"+v);
        a.setAttribute("title", "link to here");
        a.setAttribute("class", "self-link");
        XhtmlNode img = a.addTag("img");
        String s = "target.png";
        for (int i = 0; i < level; i++)
          s = "../"+s;
        img.attribute("src", s);

    }
    if (node.getNodeType() == NodeType.Document
        || (node.getNodeType() == NodeType.Element && !(node.getName().equals("div") && "sidebar".equals(node.getAttribute("class"))))) {
      for (XhtmlNode n : node.getChildNodes()) {
        insertSectionNumbersInNode(n, st, link, level);
      }
    }
  }

  private void cachePage(String filename, String source, String title) throws Exception {
    try {
      // page.log("parse "+filename);
      XhtmlDocument src = new XhtmlParser().parse(source, "html");
      scanForFragments(filename, src);
      // book.getPages().put(filename, src);
      page.getEpub().registerFile(filename, title, EPubManager.XHTML_TYPE);
    } catch (Exception e) {
      throw new Exception("error parsing page " + filename + ": " + e.getMessage() + " in source\r\n" + source);
    }
  }

  private void scanForFragments(String filename, XhtmlNode node) throws Exception {
    if (node != null && (node.getNodeType() == NodeType.Element || node.getNodeType() == NodeType.Document)) {
      if (node.getNodeType() == NodeType.Element && node.getName().equals("pre") && node.getAttribute("fragment") != null) {
        processFragment(filename, node, node.getAttribute("fragment"), node.getAttribute("class"));
      }
      for (XhtmlNode child : node.getChildNodes())
        scanForFragments(filename, child);
    }
  }

  private void processFragment(String filename, XhtmlNode node, String type, String clss) throws Exception {
    if (clss.equals("xml")) {
      String xml = new XhtmlComposer().setXmlOnly(true).compose(node);
      Fragment f = new Fragment();
      f.setType(type);
      f.setXml(Utilities.unescapeXml(xml));
      f.setPage(filename);
      fragments.add(f);
    }
  }

  public static class MyErrorHandler implements ErrorHandler {

    private boolean trackErrors;
    private List<String> errors = new ArrayList<String>();
    private Publisher pub;
    private List<ValidationMessage> list;
    private String path;

    public MyErrorHandler(boolean trackErrors, Publisher pub, List<ValidationMessage> list, String path) {
      this.trackErrors = trackErrors;
      this.pub = pub;
      this.list = list; 
      this.path = path;
    }

    @Override
    public void error(SAXParseException arg0) throws SAXException {
      if (trackErrors) {
        list.add(new ValidationMessage(Source.InstanceValidator, IssueType.STRUCTURE, arg0.getLineNumber(), arg0.getColumnNumber(), path == null ? arg0.getSystemId() : path, arg0.getMessage(), IssueSeverity.ERROR));
        pub.logError("error: " + arg0.toString(), LogMessageType.Error);
        errors.add(arg0.toString());
      }

    }

    @Override
    public void fatalError(SAXParseException arg0) throws SAXException {
      list.add(new ValidationMessage(Source.InstanceValidator, IssueType.STRUCTURE, arg0.getLineNumber(), arg0.getColumnNumber(), path == null ? arg0.getSystemId() : path, arg0.getMessage(), IssueSeverity.FATAL));
      pub.logError("fatal error: " + arg0.toString(), LogMessageType.Error);
    }

    @Override
    public void warning(SAXParseException arg0) throws SAXException {
      list.add(new ValidationMessage(Source.InstanceValidator, IssueType.STRUCTURE, arg0.getLineNumber(), arg0.getColumnNumber(), path == null ? arg0.getSystemId() : path, arg0.getMessage(), IssueSeverity.WARNING));
      // System.out.println("warning: " + arg0.toString());

    }

    public List<String> getErrors() {
      return errors;
    }

    public String getPath() {
      return path;
    }

    public void setPath(String path) {
      this.path = path;
    }

    
  }

  public static class MyResourceResolver implements LSResourceResolver {

    private String dir;

    public MyResourceResolver(String dir) {
      this.dir = dir;
    }

    @Override
    public LSInput resolveResource(final String type, final String namespaceURI, final String publicId, String systemId, final String baseURI) {
      // System.out.println(type+", "+namespaceURI+", "+publicId+", "+systemId+", "+baseURI);
      try {
        if (!new CSFile(dir + systemId).exists())
          return null;
        return new SchemaInputSource(new CSFileInputStream(new CSFile(dir + systemId)), publicId, systemId, namespaceURI);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return null;
      }
    }
  }

  static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
  static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
  static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

  private void checkBySchema(String fileToCheck, String[] schemaSource) throws Exception {
    StreamSource[] sources = new StreamSource[schemaSource.length];
    int i = 0;
    for (String s : schemaSource) {
      sources[i] = new StreamSource(new CSFileInputStream(s));
      i++;
    }
    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    schemaFactory.setErrorHandler(new MyErrorHandler(false, this, page.getValidationErrors(), null));
    schemaFactory.setResourceResolver(new MyResourceResolver(page.getFolders().dstDir));
    Schema schema = schemaFactory.newSchema(sources);

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    factory.setValidating(false);
    factory.setSchema(schema);
    DocumentBuilder builder = factory.newDocumentBuilder();
    MyErrorHandler err = new MyErrorHandler(true, this, page.getValidationErrors(), fileToCheck);
    builder.setErrorHandler(err);
    builder.parse(new CSFileInputStream(new CSFile(fileToCheck)));
    if (err.getErrors().size() > 0)
      throw new Exception("File " + fileToCheck + " failed schema validation");
  }


  private void validationProcess() throws Exception {
    validateXml();
    if (web)
      roundTrip();
  }
  
  private void validateXml() throws Exception {
    if (buildFlags.get("all") && isGenerate)
      produceCoverageWarnings();
    page.clean();
    page.log("Validating XML", LogMessageType.Process);
    page.log(".. Loading schemas", LogMessageType.Process);
    StreamSource[] sources = new StreamSource[1];
    sources[0] = new StreamSource(new CSFileInputStream(page.getFolders().dstDir + "fhir-all.xsd"));
    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    schemaFactory.setErrorHandler(new MyErrorHandler(false, this, page.getValidationErrors(), null));
    schemaFactory.setResourceResolver(new MyResourceResolver(page.getFolders().dstDir));
    Schema schema = schemaFactory.newSchema(sources);
    InstanceValidator validator = new InstanceValidator(page.getWorkerContext());
    validator.setSuppressLoincSnomedMessages(true);
    validator.setRequireResourceId(true);
    validator.setBestPracticeWarningLevel(BestPracticeWarningLevel.Warning);
    page.log(".... done", LogMessageType.Process);

    for (String rname : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn r = page.getDefinitions().getResources().get(rname);
      if (wantBuild(rname)) {
        for (Example e : r.getExamples()) {
          String n = e.getTitle();
          ImplementationGuideDefn ig = e.getIg() == null ? null : page.getDefinitions().getIgs().get(e.getIg());
          if (ig != null)
            n = ig.getCode()+File.separator+n;
          logError(" ...validate " + n, LogMessageType.Process);
          validateXmlFile(schema, n, validator, null);
          validateJsonFile(schema, n, validator, null);          
        }
        // todo-profile: how this works has to change (to use profile tag)
        for (Profile e : r.getConformancePackages()) {
          for (Example en : e.getExamples()) {
            ImplementationGuideDefn ig = en.getIg() == null ? null : page.getDefinitions().getIgs().get(en.getIg());
            String prefix = (ig == null || ig.isCore()) ? "" : ig.getCode()+File.separator;
            String n = prefix+Utilities.changeFileExt(en.getTitle(), "");
            page.log(" ...validate " + n+" ("+e.getTitle()+")", LogMessageType.Process);
            validateXmlFile(schema, n, validator, e.getProfiles().get(0).getResource()); // validates the example against it's base definitions
          }
        }
      }
    }

    for (ImplementationGuideDefn ig : page.getDefinitions().getSortedIgs()) {
      String prefix = (ig == null || ig.isCore()) ? "" : ig.getCode()+File.separator;
      for (Example ex : ig.getExamples()) {
        String n = ex.getTitle();
        logError(" ...validate " + prefix+n, LogMessageType.Process);
        validateXmlFile(schema, prefix+n, validator, null);
      }
      for (Profile pck : ig.getProfiles()) {
        for (Example en : pck.getExamples()) {
          page.log(" ...validate " + prefix+en.getTitle()+" ("+pck.getTitle()+")", LogMessageType.Process);
          validateXmlFile(schema, prefix+Utilities.changeFileExt(en.getTitle(), ""), validator, pck.getProfiles().get(0).getResource()); // validates the example against it's base definitions
        }
      }
    }

    if (buildFlags.get("all")) {
      // todo-profile: how this works has to change (to use profile tag)
//      for (String n : page.getDefinitions().getProfiles().keySet()) {
//        page.log(" ...profile " + n, LogMessageType.Process);
//        validateXmlFile(schema, n + ".profile", validator, null);
//      }

      logError(" ...validate " + "profiles-resources", LogMessageType.Process);
      validateXmlFile(schema, "profiles-resources", validator, null);

      logError(" ...validate " + "profiles-types", LogMessageType.Process);
      validateXmlFile(schema, "profiles-types", validator, null);

      logError(" ...validate " + "profiles-others", LogMessageType.Process);
      validateXmlFile(schema, "profiles-others", validator, null);

      logError(" ...validate " + "search-parameters", LogMessageType.Process);
      validateXmlFile(schema, "search-parameters", validator, null);

      logError(" ...validate " + "extension-definitions", LogMessageType.Process);
      validateXmlFile(schema, "extension-definitions", validator, null);

      logError(" ...validate " + "valuesets", LogMessageType.Process);
      validateXmlFile(schema, "valuesets", validator, null);

      logError(" ...validate " + "conceptmaps", LogMessageType.Process);
      validateXmlFile(schema, "conceptmaps", validator, null);

      logError(" ...validate " + "v2-tables", LogMessageType.Process);
      validateXmlFile(schema, "v2-tables", validator, null);
      logError(" ...validate " + "v3-codesystems", LogMessageType.Process);
      validateXmlFile(schema, "v3-codesystems", validator, null);
    }
    page.saveSnomed();

    logError("Summary: Errors="+Integer.toString(errorCount)+", Warnings="+Integer.toString(warningCount)+", Hints="+Integer.toString(informationCount), LogMessageType.Error);
    if (errorCount > 0)
      throw new Exception("Resource Examples failed instance validation");
  }
  
  private void roundTrip() throws Exception {
    page.log("Reference Platform Validation", LogMessageType.Process);
    page.clean2();

    page.log("Round Trip #1", LogMessageType.Process);
    List<String> list = new ArrayList<String>();
    
    listExamples(list);
    Collections.sort(list);
    for (PlatformGenerator gen : page.getReferenceImplementations()) {
      if (gen.doesTest()) {
        page.log(" ...round trip " + gen.getTitle(), LogMessageType.Process);
        gen.test(page.getFolders(), list);
      }
    }
    
    page.log("Round Trip #2", LogMessageType.Process);
    if (buildFlags.get("all")) {
      list = new ArrayList<String>();
      listCollections1(list);
    }
   
    for (PlatformGenerator gen : page.getReferenceImplementations()) {
      if (gen.doesTest()) {
        page.log(" ...round trip " + gen.getTitle(), LogMessageType.Process);
        gen.test(page.getFolders(), list);
      }
    }

    page.log("Round Trip #3", LogMessageType.Process);
    if (buildFlags.get("all")) {
      list = new ArrayList<String>();
      listCollections2(list);
    }
   
    for (PlatformGenerator gen : page.getReferenceImplementations()) {
      if (gen.doesTest()) {
        page.log(" ...round trip " + gen.getTitle(), LogMessageType.Process);
        gen.test(page.getFolders(), list);
      }
    }

    page.log("Round Trip #4", LogMessageType.Process);
    if (buildFlags.get("all")) {
      list = new ArrayList<String>();
      listCollections3(list);
    }
   
    for (PlatformGenerator gen : page.getReferenceImplementations()) {
      if (gen.doesTest()) {
        page.log(" ...round trip " + gen.getTitle(), LogMessageType.Process);
        gen.test(page.getFolders(), list);
      }
    }

    list = new ArrayList<String>();
    
    listExamples(list);
    listCollections1(list);
    listCollections2(list);
    listCollections3(list);
    Collections.sort(list);
    
    for (String n : list) {
      page.log(" ...test " + n, LogMessageType.Process);
      validateRoundTrip(n);
    }

    for (String rn : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn r = page.getDefinitions().getResourceByName(rn);
      for (SearchParameterDefn sp : r.getSearchParams().values()) {
        if (!sp.isWorks() && !sp.getCode().equals("_id") && !Utilities.noString(sp.getXPath())) {
          //          page.log(
          //              "Search Parameter '" + rn + "." + sp.getCode() + "' had no found values in any example. Consider reviewing the path (" + sp.getXPath() + ")",
          //              LogMessageType.Warning);
          page.getValidationErrors().add(
              new ValidationMessage(Source.Publisher, IssueType.INFORMATIONAL, -1, -1, rn + "." + sp.getCode(), "Search Parameter '" + rn + "." + sp.getCode() + "' had no found values in any example. Consider reviewing the path (" + sp.getXPath() + ")", IssueSeverity.WARNING));
        }
      }
    }
  }

  private void listCollections1(List<String> list) {
    list.add("profiles-types");
    list.add("profiles-resources");
    list.add("profiles-others");
    list.add("extension-definitions");
  }
  private void listCollections2(List<String> list) {
    list.add("search-parameters");
    list.add("v2-tables");
    list.add("v3-codesystems");
    list.add("conceptmaps");
  }

  private void listCollections3(List<String> list) {
    list.add("valuesets");
  }

  private void listExamples(List<String> list) {
    for (String rname : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn r = page.getDefinitions().getResources().get(rname);
      if (wantBuild(rname)) {
        for (Example e : r.getExamples()) {
          String n = e.getTitle();
          ImplementationGuideDefn ig = e.getIg() == null ? null : page.getDefinitions().getIgs().get(e.getIg());
          if (ig != null)
            n = ig.getCode()+File.separator+n;
          list.add(n);
        }
        // todo-profile: how this works has to change (to use profile tag)
        for (Profile e : r.getConformancePackages()) {
          for (Example en : e.getExamples()) {
            ImplementationGuideDefn ig = en.getIg() == null ? null : page.getDefinitions().getIgs().get(en.getIg());
            String prefix = (ig == null || ig.isCore()) ? "" : ig.getCode()+File.separator;
            String n = prefix+Utilities.changeFileExt(en.getTitle(), "");
            list.add(n);
          }
        }
      }
    }

    for (ImplementationGuideDefn ig : page.getDefinitions().getSortedIgs()) {
      String prefix = (ig == null || ig.isCore()) ? "" : ig.getCode()+File.separator;
      for (Example ex : ig.getExamples()) {
        String n = ex.getTitle();
        logError(" ...validate " + prefix+n, LogMessageType.Process);
        list.add(prefix+n);
      }
      for (Profile pck : ig.getProfiles()) {
        for (Example en : pck.getExamples()) {
          page.log(" ...validate " + prefix+en.getTitle()+" ("+pck.getTitle()+")", LogMessageType.Process);
          list.add(prefix+Utilities.changeFileExt(en.getTitle(), ""));
        }
      }
    }
  }

  private void produceCoverageWarnings() throws Exception {
    for (ElementDefn e : page.getDefinitions().getStructures().values())
      produceCoverageWarning("", e);
    for (ElementDefn e : page.getDefinitions().getTypes().values())
      produceCoverageWarning("", e);
    for (String s : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn e = page.getDefinitions().getResourceByName(s);
      produceCoverageWarning("", e.getRoot());
    }
  }

  private void produceCoverageWarning(String path, ElementDefn e) {

    if (!e.isCoveredByExample() && !Utilities.noString(path) && !e.typeCode().startsWith("@")) {
      page.getValidationErrors().add(new ValidationMessage(Source.Publisher, IssueType.INFORMATIONAL, -1, -1, path+e.getName(), "Path had no found values in any example. Consider reviewing the path", IssueSeverity.WARNING));
    }
    for (ElementDefn c : e.getElements()) {
      produceCoverageWarning(path + e.getName() + "/", c);
    }
  }

  private void validateXmlFile(Schema schema, String n, InstanceValidator validator, StructureDefinition profile) throws Exception {
    char sc = File.separatorChar;
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    factory.setValidating(false);
    factory.setSchema(schema);
    DocumentBuilder builder = factory.newDocumentBuilder();
    MyErrorHandler err = new MyErrorHandler(true, this, page.getValidationErrors(), n);
    builder.setErrorHandler(err);
    Document doc = builder.parse(new CSFileInputStream(new CSFile(page.getFolders().dstDir + n + ".xml")));
    Element root = doc.getDocumentElement();
    errorCount = errorCount + err.getErrors().size();

    String sch = "fhir-invariants.sch";

    validateBySchematron(n, sch);
    if (profile != null && new File(Utilities.path(page.getFolders().dstDir, profile.getId()+".sch")).exists()) {
      validateBySchematron(n, profile.getId()+".sch");
    }
    
    // now, finally, we validate the resource ourselves.
    // the build tool validation focuses on codes and identifiers
    List<ValidationMessage> issues = new ArrayList<ValidationMessage>();
    validator.validate(issues, root);
    // if (profile != null)
    // validator.validateInstanceByProfile(issues, root, profile);
    for (ValidationMessage m : issues) {
      if (!m.getLevel().equals(IssueSeverity.INFORMATION) && !m.getLevel().equals(IssueSeverity.WARNING)) {
        page.getValidationErrors().add(m);
      }
      if (m.getLevel() == IssueSeverity.WARNING)
        warningCount++;
      else if (m.getLevel() == IssueSeverity.INFORMATION)
        informationCount++;
      else
        errorCount++;
    }
  }

  private void validateJsonFile(Schema schema, String n, InstanceValidator validator, StructureDefinition profile) throws Exception {
    com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
    JsonObject obj = parser.parse(TextFile.fileToString(Utilities.path(page.getFolders().dstDir, n + ".json"))).getAsJsonObject();
    
    // the build tool validation focuses on codes and identifiers
    List<ValidationMessage> issues = new ArrayList<ValidationMessage>();
    validator.validate(issues, obj);
    // if (profile != null)
    // validator.validateInstanceByProfile(issues, root, profile);
    for (ValidationMessage m : issues) {
      if (!m.getLevel().equals(IssueSeverity.INFORMATION) && !m.getLevel().equals(IssueSeverity.WARNING))
        logError("  " + m.summary(), typeforSeverity(m.getLevel()));

      if (m.getLevel() == IssueSeverity.WARNING)
        warningCount++;
      else if (m.getLevel() == IssueSeverity.INFORMATION)
        informationCount++;
      else
        errorCount++;
    }
  }

  private void validateBySchematron(String n, String sch) throws IOException, ParserConfigurationException, SAXException, FileNotFoundException {
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document doc;
    File tmpTransform = Utilities.createTempFile("tmp", ".xslt");
    File tmpOutput = Utilities.createTempFile("tmp", ".xml");
    try {
      Utilities.saxonTransform(Utilities.path(page.getFolders().rootDir, "tools", "schematron"), Utilities.path(page.getFolders().dstDir, sch),
          Utilities.path(page.getFolders().rootDir, "tools", "schematron", "iso_svrl_for_xslt2.xsl"), tmpTransform.getAbsolutePath(), null);
      Utilities.saxonTransform(Utilities.path(page.getFolders().rootDir, "tools", "schematron"), Utilities.path(page.getFolders().dstDir, n + ".xml"),
          tmpTransform.getAbsolutePath(), tmpOutput.getAbsolutePath(), null);
    } catch (Throwable t) {
      //      throw new Exception("Error validating " + page.getFolders().dstDir + n + ".xml with schematrons", t);
    }

    factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    builder = factory.newDocumentBuilder();
    doc = builder.parse(new CSFileInputStream(tmpOutput.getAbsolutePath()));
    NodeList nl = doc.getDocumentElement().getElementsByTagNameNS("http://purl.oclc.org/dsdl/svrl", "failed-assert");
    if (nl.getLength() > 0) {
      logError("Schematron Validation Failed for " + n + ".xml:", LogMessageType.Error);
      for (int i = 0; i < nl.getLength(); i++) {
        Element e = (Element) nl.item(i);
        logError("  @" + e.getAttribute("location") + ": " + e.getTextContent(), LogMessageType.Error);
        page.getValidationErrors().add(new ValidationMessage(Source.InstanceValidator, IssueType.STRUCTURE, -1, -1, n+":"+e.getAttribute("location"), e.getTextContent(), IssueSeverity.ERROR));
        errorCount++;
      }
    }
  }

  StringBuilder vallog = new StringBuilder();
  
  private void logError(String string, LogMessageType typeforSeverity) {
    page.log(string, typeforSeverity);
    vallog.append(string+"\r\n");
    try {
      TextFile.stringToFileNoPrefix(vallog.toString(), "validation.log");
    } catch (Exception e) {
    }
  }

  private LogMessageType typeforSeverity(IssueSeverity level) {
    switch (level) {
    case ERROR:
      return LogMessageType.Error;
    case FATAL:
      return LogMessageType.Error;
    case INFORMATION:
      return LogMessageType.Hint;
    case WARNING:
      return LogMessageType.Warning;
    default:
      return LogMessageType.Error;
    }
  }

  private void validateRoundTrip(String n) throws Exception {
    testSearchParameters(page.getFolders().dstDir + n + ".xml");
    for (PlatformGenerator gen : page.getReferenceImplementations()) {
      if (gen.doesTest()) {
        compareXml(n, gen.getName(), page.getFolders().dstDir + n + ".xml", page.getFolders().tmpDir + n.replace(File.separator, "-") + "."+gen.getName()+".xml");
      }
    }
  }

  private void testSearchParameters(String filename) throws Exception {
    // load the xml
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document xml = builder.parse(new CSFileInputStream(new CSFile(filename)));

    if (xml.getDocumentElement().getNodeName().equals("Bundle")) {
      // iterating the entries running xpaths takes too long. What we're going to do 
      // is list all the resources, and then evaluate all the paths...
      Set<String> names = new HashSet<String>();
      Element child = XMLUtil.getFirstChild(xml.getDocumentElement());
      while (child != null) {
        if (child.getNodeName().equals("entry")) {
          Element grandchild = XMLUtil.getFirstChild(child);
          while (grandchild != null) {
            if (grandchild.getNodeName().equals("resource"))
              names.add(XMLUtil.getFirstChild(grandchild).getNodeName());
            grandchild = XMLUtil.getNextSibling(grandchild);
          }
        }
        child = XMLUtil.getNextSibling(child);
      }
      for (String name : names) 
        testSearchParameters(xml.getDocumentElement(), name, true);
    } else {
      testSearchParameters(xml.getDocumentElement(), xml.getDocumentElement().getNodeName(), false);
    }
  }

  private void testSearchParameters(Element e, String name, boolean inBundle) throws Exception {
    ResourceDefn r = page.getDefinitions().getResourceByName(name);
    for (SearchParameterDefn sp : r.getSearchParams().values()) {
      if (sp.getXPath() != null) {
        try {
          NamespaceContext context = new NamespaceContextMap("f", "http://hl7.org/fhir", "h", "http://www.w3.org/1999/xhtml");
          XPathFactory factory = XPathFactory.newInstance();
          XPath xpath = factory.newXPath();
          xpath.setNamespaceContext(context);
          XPathExpression expression;
          expression = inBundle ? xpath.compile("/f:Bundle/f:entry/f:resource/"+sp.getXPath()) : xpath.compile("/"+sp.getXPath());
          NodeList resultNodes = (NodeList) expression.evaluate(e, XPathConstants.NODESET);
          if (resultNodes.getLength() > 0)
            sp.setWorks(true);
        } catch (Exception e1) {
          page.log("Xpath \"" + sp.getXPath() + "\" execution failed: " + e1.getMessage(), LogMessageType.Error);
        }
      }
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
        page.getValueSets().put(vs.getUrl(), (ValueSet) ae);
        page.getDefinitions().getValuesets().put(vs.getUrl(), vs);
        if (vs.hasCodeSystem()) {
          page.getCodeSystems().put(vs.getCodeSystem().getSystem(), (ValueSet) ae);
          page.getDefinitions().getCodeSystems().put(vs.getCodeSystem().getSystem(), vs);
        }

      }
    }
  }

  private void generateIGValueSetsPart2() throws Exception {
    for (Resource ae : page.getIgResources().values()) {
      if (ae instanceof ValueSet) {
        ValueSet vs = (ValueSet) ae;
        String name = Utilities.fileTitle((String) ae.getUserData("path"));
        String title = vs.getName();

        if (vs.getText() == null || vs.getText().getDiv() == null || vs.getText().getDiv().allChildrenAreText()
            && (Utilities.noString(vs.getText().getDiv().allText()) || !vs.getText().getDiv().allText().matches(".*\\w.*")))
          new NarrativeGenerator("", "", page.getWorkerContext()).generate(vs);

        page.getVsValidator().validate(page.getValidationErrors(), name, vs, true, false);

        addToResourceFeed(vs, valueSetsFeed, null); // todo - what should the Oids be

        String sf = page.processPageIncludes(title + ".html", TextFile.fileToString(page.getFolders().srcDir + "template-vs-ig.html"), "valueSet", null, name+".html", vs, null, "Value Set", null);
        sf = addSectionNumbers(title + ".html", "template-valueset", sf, "??", 0, null);
        TextFile.stringToFile(sf, page.getFolders().dstDir + name + ".html");

        String src = page.processPageIncludesForBook(title + ".html", TextFile.fileToString(page.getFolders().srcDir + "template-vs-ig-book.html"), "valueSet", vs, null);
        cachePage(name + ".html", src, "Value Set " + title);
        page.setId(null);

        IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
        FileOutputStream s = new FileOutputStream(page.getFolders().dstDir+name + ".json");
        json.compose(s, vs);
        s.close();
        IParser xml = new XmlParser().setOutputStyle(OutputStyle.PRETTY);
        s = new FileOutputStream(page.getFolders().dstDir+name + ".xml");
        xml.compose(s, vs);
        s.close();
        cloneToXhtml(name, "Definition for Value Set" + vs.getName(), false, "valueset-instance", "Value Set");
        jsonToXhtml(name, "Definition for Value Set" + vs.getName(), resource2Json(vs), "valueset-instance", "Value Set");
      }
    }
  }

  private void generateValueSetsPart2() throws Exception {

    for (ValueSet vs : page.getDefinitions().getBoundValueSets().values()) {
      generateValueSetPart2(vs);
    }
    for (ValueSet vs : page.getDefinitions().getExtraValuesets().values())
      generateValueSetPart2(vs);
  }

  
  private void generateValueSetPart2(ValueSet vs) throws Exception { 
    String n = vs.getUserString("filename");
    if (n == null)
      n = "valueset-"+vs.getId();
    ImplementationGuideDefn ig = (ImplementationGuideDefn) vs.getUserData(ToolResourceUtilities.NAME_RES_IG);
    if (ig != null)
      n = ig.getCode()+File.separator+n;

    if (vs.getText().getDiv().allChildrenAreText()
        && (Utilities.noString(vs.getText().getDiv().allText()) || !vs.getText().getDiv().allText().matches(".*\\w.*"))) {
      if (ig != null) 
        new NarrativeGenerator("../", ig.getCode()+"/", page.getWorkerContext()).generate(vs);
      else
        new NarrativeGenerator("", "", page.getWorkerContext()).generate(vs);
    }
    page.getVsValidator().validate(page.getValidationErrors(), n, vs, true, false);

    if (isGenerate) {
//       page.log(" ... "+n, LogMessageType.Process);
      if (vs.hasCodeSystem())
        generateCodeSystemPart2(vs);
      
      addToResourceFeed(vs, valueSetsFeed, null);

      if (vs.getUserData("path") == null)
        vs.setUserData("path", n + ".html");
      page.setId(vs.getId());
      String sf = page.processPageIncludes(n + ".html", TextFile.fileToString(page.getFolders().srcDir + "template-vs.html"), "valueSet", null, n+".html", vs, null, "Value Set", ig);
      sf = addSectionNumbers(n + ".html", "template-valueset", sf, Utilities.oidTail(ToolingExtensions.getOID(vs)), ig == null ? 0 : 1, null);

      TextFile.stringToFile(sf, page.getFolders().dstDir + n + ".html");
      String src = page.processPageIncludesForBook(n + ".html", TextFile.fileToString(page.getFolders().srcDir + "template-vs-book.html"), "valueSet", vs, ig);
      cachePage(n + ".html", src, "Value Set " + n);
      page.setId(null);

      IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
      FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + n + ".json");
      json.compose(s, vs);
      s.close();
      IParser xml = new XmlParser().setOutputStyle(OutputStyle.PRETTY);
      s = new FileOutputStream(page.getFolders().dstDir + n + ".xml");
      xml.compose(s, vs);
      s.close();
//      System.out.println(vs.getUrl());
      cloneToXhtml(n, "Definition for Value Set" + vs.getName(), false, "valueset-instance", "Value Set");
      jsonToXhtml(n, "Definition for Value Set" + vs.getName(), resource2Json(vs), "valueset-instance", "Value Set");
    }

  }

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
      if (ToolingExtensions.getOID(vs) == null)
        throw new Exception("No OID on value set "+vs.getUrl());
      if (vs.hasCodeSystem()) {
        if (ToolingExtensions.getOID(vs.getCodeSystem()) == null && !Utilities.noString(vs.getUserString("csoid")))
          ToolingExtensions.setOID(vs.getCodeSystem(), "urn:oid:"+vs.getUserString("csoid"));          
        if (ToolingExtensions.getOID(vs.getCodeSystem()) == null)
          throw new Exception("No OID on value set define for "+vs.getUrl());
      }

      page.getValueSets().put(vs.getUrl(), vs);
      page.getDefinitions().getValuesets().put(vs.getUrl(), vs);
      if (vs.hasCodeSystem()) {
        page.getCodeSystems().put(vs.getCodeSystem().getSystem(), vs);
        page.getDefinitions().getCodeSystems().put(vs.getCodeSystem().getSystem(), vs);
      }
      
    }
    for (ValueSet vs : page.getDefinitions().getBoundValueSets().values()) {
      page.getVsValidator().validate(page.getValidationErrors(), vs.getUserString("filename"), vs, true, false);
    }
  }
  
  private void generateConceptMapV2(ValueSet vs, String filename, String src, String srcCS) throws Exception {
    ConceptMap cm = new ConceptMap();
    cm.setId("v2-"+FormatUtilities.makeId(Utilities.fileTitle(filename)));
    cm.setUrl("http://hl7.org/fhir/ConceptMap/v2-" + Utilities.fileTitle(filename));
    // no version?? vs.setVersion(...
    cm.setName("v2 map for " + vs.getName());
    cm.setPublisher("HL7 (FHIR Project)");
    for (ValueSetContactComponent cs : vs.getContact()) {
      ConceptMapContactComponent cd = cm.addContact();
      cd.setName(cs.getName());
      for (ContactPoint ccs : cs.getTelecom())
        cd.addTelecom(ccs.copy());
    }
    cm.setCopyright(vs.getCopyright());

    Set<String> tbls = new HashSet<String>();
    cm.setStatus(ConformanceResourceStatus.DRAFT); // until we publish
    // DSTU, then .review
    cm.setDate(page.getGenDate().getTime());
    cm.setSource(Factory.makeReference(src));
    cm.setTarget(Factory.makeReference(vs.getUserString("v2map")));
    for (ConceptDefinitionComponent c : vs.getCodeSystem().getConcept()) {
      genV2MapItems(vs, srcCS, cm, tbls, c);
    }
    StringBuilder b = new StringBuilder();
    boolean first = false;
    for (String s : tbls) {
      if (first)
        b.append(", ");
      first = true;
      b.append(s);
    }
    cm.setDescription("v2 Map (" + b.toString() + ")");
    NarrativeGenerator gen = new NarrativeGenerator("", "", page.getWorkerContext());
    gen.generate(cm);

    IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, "-map-v2.json"));
    json.compose(s, cm);
    s.close();
    json = new JsonParser().setOutputStyle(OutputStyle.CANONICAL);
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, "-map-v2.canonical.json"));
    json.compose(s, cm);
    s.close();
    String n = Utilities.changeFileExt(filename, "-map-v2");
    jsonToXhtml(n, cm.getName(), resource2Json(cm), "conceptmap-instance", "Concept Map");
    IParser xml = new XmlParser().setOutputStyle(OutputStyle.PRETTY);
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, "-map-v2.xml"));
    xml.compose(s, cm);
    s.close();
    xml = new XmlParser().setOutputStyle(OutputStyle.CANONICAL);
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, "-map-v2.canonical.xml"));
    xml.compose(s, cm);
    s.close();
    cloneToXhtml(n, cm.getName(), false, "conceptmap-instance", "Concept Map");

    // now, we create an html page from the narrative
    String narrative = new XhtmlComposer().setXmlOnly(true).compose(cm.getText().getDiv());
    String html = TextFile.fileToString(page.getFolders().srcDir + "template-example.html").replace("<%example%>", narrative);
    html = page.processPageIncludes(Utilities.changeFileExt(filename, "-map-v2.html"), html, "conceptmap-instance", null, null, null, "Concept Map", null);
    TextFile.stringToFile(html, page.getFolders().dstDir + Utilities.changeFileExt(filename, "-map-v2.html"));

    cm.setUserData("path", Utilities.changeFileExt(filename, "-map-v2.html"));
    conceptMapsFeed.getEntry().add(new BundleEntryComponent().setResource(cm).setFullUrl(cm.getUrl()));
    page.getConceptMaps().put(cm.getUrl(), cm);
    page.getEpub().registerFile(n + ".html", cm.getName(), EPubManager.XHTML_TYPE);
    page.getEpub().registerFile(n + ".json.html", cm.getName(), EPubManager.XHTML_TYPE);
    page.getEpub().registerFile(n + ".xml.html", cm.getName(), EPubManager.XHTML_TYPE);
  }

  private void genV2MapItems(ValueSet vs, String srcCS, ConceptMap cm, Set<String> tbls, ConceptDefinitionComponent c) throws Exception {
    if (!Utilities.noString(c.getUserString("v2"))) {
      for (String m : c.getUserString("v2").split(",")) {
        SourceElementComponent cc = new ConceptMap.SourceElementComponent();
        cc.setCodeSystem(srcCS);
        cc.setCode(c.getCode());
        TargetElementComponent map = new ConceptMap.TargetElementComponent();
        cc.getTarget().add(map);
        cm.getElement().add(cc);
        String[] n = m.split("\\(");
        if (n.length > 1)
          map.setComments(n[1].substring(0, n[1].length() - 1));
        n = n[0].split("\\.");
        tbls.add(n[0].substring(1));
        map.setCodeSystem("http://hl7.org/fhir/v2/" + n[0].substring(1));
        map.setCode(n[1].trim());
        if (n[0].charAt(0) == '=')
          map.setEquivalence(ConceptMapEquivalence.EQUAL);
        if (n[0].charAt(0) == '~')
          map.setEquivalence(ConceptMapEquivalence.EQUIVALENT);
        if (n[0].charAt(0) == '>')
          map.setEquivalence(ConceptMapEquivalence.WIDER);
        if (n[0].charAt(0) == '<') {
          map.setEquivalence(ConceptMapEquivalence.NARROWER);
          if (!map.hasComments())
            throw new Exception("Missing comments for narrower match on "+vs.getName()+"/"+c.getCode());
        }
      }
    }
    for (ConceptDefinitionComponent cc : c.getConcept()) {
      genV2MapItems(vs, srcCS, cm, tbls, cc);
    }
  }

  private void generateConceptMapV3(ValueSet vs, String filename, String src, String srcCS) throws Exception {
    ConceptMap cm = new ConceptMap();
    cm.setId("v3-"+FormatUtilities.makeId(Utilities.fileTitle(filename)));
    cm.setUrl("http://hl7.org/fhir/ConceptMap/v3-" + Utilities.fileTitle(filename));
    // no version?? vs.setVersion(...
    cm.setName("v3 map for " + vs.getName());
    cm.setPublisher("HL7 (FHIR Project)");
    for (ValueSetContactComponent cs : vs.getContact()) {
      ConceptMapContactComponent cd = cm.addContact();
      cd.setName(cs.getName());
      for (ContactPoint ccs : cs.getTelecom())
        cd.addTelecom(ccs.copy());
    }
    cm.setCopyright(vs.getCopyright());

    Set<String> tbls = new HashSet<String>();
    cm.setStatus(ConformanceResourceStatus.DRAFT); // until we publish
    // DSTU, then .review
    cm.setDate(page.getGenDate().getTime());
    cm.setSource(Factory.makeReference(src));
    cm.setTarget(Factory.makeReference("http://hl7.org/fhir/ValueSet/v3-"+vs.getUserString("v3map")));
    for (ConceptDefinitionComponent c : vs.getCodeSystem().getConcept()) {
      genV3MapItems(vs, srcCS, cm, tbls, c);
    }
    StringBuilder b = new StringBuilder();
    boolean first = false;
    for (String s : tbls) {
      if (first)
        b.append(", ");
      first = true;
      b.append(s);
    }
    cm.setDescription("v3 Map (" + b.toString() + ")");
    NarrativeGenerator gen = new NarrativeGenerator("", "", page.getWorkerContext());
    gen.generate(cm);
    IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
    FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, "-map-v3.json"));
    json.compose(s, cm);
    s.close();
    json = new JsonParser().setOutputStyle(OutputStyle.CANONICAL);
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, "-map-v3.canonical.json"));
    json.compose(s, cm);
    s.close();
    String n = Utilities.changeFileExt(filename, "-map-v3");
    jsonToXhtml(n, cm.getName(), resource2Json(cm), "conceptmap-instance", "Concept Map");
    IParser xml = new XmlParser().setOutputStyle(OutputStyle.PRETTY);
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, "-map-v3.xml"));
    xml.compose(s, cm);
    s.close();
    xml = new XmlParser().setOutputStyle(OutputStyle.CANONICAL);
    s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, "-map-v3.canonical.xml"));
    xml.compose(s, cm);
    s.close();
    cloneToXhtml(n, cm.getName(), false, "conceptmap-instance", "Concept Map");

    // now, we create an html page from the narrative
    String narrative = new XhtmlComposer().setXmlOnly(true).compose(cm.getText().getDiv());
    String html = TextFile.fileToString(page.getFolders().srcDir + "template-example.html").replace("<%example%>", narrative);
    html = page.processPageIncludes(Utilities.changeFileExt(filename, "-map-v3.html"), html, "conceptmap-instance", null, null, null, "Concept Map", null);
    TextFile.stringToFile(html, page.getFolders().dstDir + Utilities.changeFileExt(filename, "-map-v3.html"));

    cm.setUserData("path", Utilities.changeFileExt(filename, "-map-v3.html"));
    conceptMapsFeed.getEntry().add(new BundleEntryComponent().setResource(cm).setFullUrl(cm.getUrl()));
    page.getConceptMaps().put(cm.getUrl(), cm);
    page.getEpub().registerFile(n + ".html", cm.getName(), EPubManager.XHTML_TYPE);
    page.getEpub().registerFile(n + ".json.html", cm.getName(), EPubManager.XHTML_TYPE);
    page.getEpub().registerFile(n + ".xml.html", cm.getName(), EPubManager.XHTML_TYPE);
  }

  private void genV3MapItems(ValueSet vs, String srcCS, ConceptMap cm, Set<String> tbls, ConceptDefinitionComponent c) throws Exception {
    if (!Utilities.noString(c.getUserString("v3"))) {
      for (String m : c.getUserString("v3").split(",")) {
        SourceElementComponent cc = new SourceElementComponent();
        cc.setCodeSystem(srcCS);
        cc.setCode(c.getCode());
        TargetElementComponent map = new TargetElementComponent();
        cc.getTarget().add(map);
        cm.getElement().add(cc);
        String[] n = m.split("\\(");
        if (n.length > 1)
          map.setComments(n[1].substring(0, n[1].length() - 1));
        n = n[0].split("\\.");
        if (n.length != 2)
          throw new Exception("Error processing v3 map value for "+vs.getName()+"."+c.getCode()+" '"+m+"' - format should be CodeSystem.code (comment) - the comment bit is optional");
        String codesystem = n[0].substring(1);
        if (n[0].charAt(0) == '=')
          map.setEquivalence(ConceptMapEquivalence.EQUAL);
        else if (n[0].charAt(0) == '~')
          map.setEquivalence(ConceptMapEquivalence.EQUIVALENT);
        else if (n[0].charAt(0) == '>') {
          map.setEquivalence(ConceptMapEquivalence.NARROWER);
          if (!map.hasComments())
            throw new Exception("Missing comments for narrower match on "+vs.getName()+"/"+c.getCode());

        } else if (n[0].charAt(0) == '<')
          map.setEquivalence(ConceptMapEquivalence.WIDER);
        else {
          map.setEquivalence(ConceptMapEquivalence.EQUAL);
          codesystem = n[0];
        }
        tbls.add(codesystem);
        map.setCodeSystem("http://hl7.org/fhir/v3/" + codesystem);
        map.setCode(n[1]);
      }
    }
    for (ConceptDefinitionComponent cc : c.getConcept()) {
      genV3MapItems(vs, srcCS, cm, tbls, cc);
    }
  }

  private void generateCodeSystemPart2(ValueSet vs) throws Exception {

    if (!Utilities.noString(vs.getUserString("v2map")))
      generateConceptMapV2(vs, vs.getId(), vs.getUrl(), "http://hl7.org/fhir/" + vs.getId());
    if (!Utilities.noString(vs.getUserString("v3map")))
      generateConceptMapV3(vs, vs.getId(), vs.getUrl(), "http://hl7.org/fhir/" + vs.getId());

//    new NarrativeGenerator("", page.getWorkerContext()).generate(vs);
//
//      addToResourceFeed(vs, valueSetsFeed, vs.getId());
//
//      String sf;
//      if (cd.hasInternalCodes() && cd.getReferredValueSet() != null)
//        sf = page.processPageIncludes(filename, TextFile.fileToString(page.getFolders().srcDir + "template-tx.html"), "codeSystem", null, null, null, "Value Set");
//      else {
//        cd.getReferredValueSet().setUserData("filename", filename);
//        sf = page.processPageIncludes(filename, TextFile.fileToString(page.getFolders().srcDir + "template-vs.html"), "codeSystem", null, cd.getReferredValueSet(), null, "Value Set");
//      }
//      if (cd.getVsOid() == null)
//        throw new Error("no oid for value set "+vs.getName()+" ("+vs.getUrl()+")");
//      sf = addSectionNumbers(filename + ".html", "template-valueset", sf, Utilities.oidTail(cd.getVsOid()));
//      TextFile.stringToFile(sf, page.getFolders().dstDir + filename);
//      String src = page.processPageIncludesForBook(filename, TextFile.fileToString(page.getFolders().srcDir + "template-tx-book.html"), "codeSystem", null);
//      cachePage(filename, src, "Code System " + vs.getName());
//
//      IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
//      FileOutputStream s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, ".json"));
//      json.compose(s, vs);
//      s.close();
//      IParser xml = new XmlParser().setOutputStyle(OutputStyle.PRETTY);
//      s = new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, ".xml"));
//      xml.compose(s, vs);
//      s.close();
//      cloneToXhtml(Utilities.fileTitle(filename), "Definition for Value Set" + vs.getName(), false, "valueset-instance", "Value Set");
//      jsonToXhtml(Utilities.fileTitle(filename), "Definition for Value Set" + vs.getName(), resource2Json(vs), "valueset-instance", "Value Set");
  }

  private void addCode(ValueSet vs, List<ConceptDefinitionComponent> list, DefinedCode c) {
    ConceptDefinitionComponent d = new ValueSet.ConceptDefinitionComponent();
    list.add(d);
    d.setCode(c.getCode());
    if (!Utilities.noString(c.getDisplay()))
      d.setDisplay(c.getDisplay());
    if (!Utilities.noString(c.getDefinition()))
      d.setDefinition(c.getDefinition());
    for (DefinedCode g : c.getChildCodes()) {
      addCode(vs, d.getConcept(), g);
    }
    for (String n : c.getLangs().keySet()) {
      ConceptDefinitionDesignationComponent designation = d.addDesignation();
      designation.setLanguage(n);
      designation.setValue(c.getLangs().get(n));
    }
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
      vs = page.expandValueSet(vs);
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
