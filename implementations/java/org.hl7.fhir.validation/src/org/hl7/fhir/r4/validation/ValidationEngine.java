package org.hl7.fhir.r4.validation;

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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.hl7.fhir.r4.conformance.ProfileUtilities;
import org.hl7.fhir.r4.context.SimpleWorkerContext;
import org.hl7.fhir.r4.context.SimpleWorkerContext.IContextResourceLoader;
import org.hl7.fhir.r4.elementmodel.Manager;
import org.hl7.fhir.r4.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.r4.formats.JsonParser;
import org.hl7.fhir.r4.formats.RdfParser;
import org.hl7.fhir.r4.formats.XmlParser;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.ExpansionProfile;
import org.hl7.fhir.r4.model.ImplementationGuide;
import org.hl7.fhir.r4.model.MetadataResource;
import org.hl7.fhir.r4.model.OperationOutcome;
import org.hl7.fhir.r4.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.r4.model.Questionnaire;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.hl7.fhir.r4.utils.FHIRPathEngine;
import org.hl7.fhir.r4.utils.NarrativeGenerator;
import org.hl7.fhir.r4.utils.OperationOutcomeUtilities;
import org.hl7.fhir.r4.utils.StructureMapUtilities;
import org.hl7.fhir.r4.utils.ToolingExtensions;
import org.hl7.fhir.r4.utils.ValidationProfileSet;
import org.hl7.fhir.convertors.R2ToR3Loader;
import org.hl7.fhir.convertors.R2ToR4Loader;
import org.hl7.fhir.convertors.R3ToR4Loader;
import org.hl7.fhir.exceptions.DefinitionException;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueType;
import org.hl7.fhir.utilities.validation.ValidationMessage.Source;
import org.xml.sax.SAXException;

/**
 * This is just a wrapper around the InstanceValidator class for convenient use 
 * 
 * The following resource formats are supported: XML, JSON, Turtle
 * The following versions are supported: 1.4.0, 1.6.0, and current
 * 
 * Note: the validation engine is intended to be threadsafe
 * To Use:
 *  
 * 1/ Initialize
 *    ValidationEngine validator = new ValidationEngine(src);
 *      - this must refer to the igpack.zip for the version of the spec against which you want to validate
 *       it can be a url or a file reference. It can nominate the igpack.zip directly, 
 *       or it can name the container alone (e.g. just the spec URL).
 *       The validation engine does not cache igpack.zip. the user must manage that if desired 
 *
 *    validator.connectToTSServer(txServer);
 *      - this is optional; in the absence of a terminology service, snomed, loinc etc will not be validated
 *      
 *    validator.loadIg(src);
 *      - call this any number of times for the Implementation Guide(s) of interest. This is a reference
 *        to the igpack.zip for the implementation guide - same rules as above
 *        the version of the IGPack must match that of the spec 
 *        Alternatively it can point to a local folder that contains conformance resources.
 *         
 *    validator.loadQuestionnaire(src)
 *      - url or filename of a questionnaire to load. Any loaded questionnaires will be used while validating
 *      
 *    validator.setNative(doNative);
 *      - whether to do xml/json/rdf schema validation as well
 *
 *   You only need to do this initialization once. You can validate as many times as you like
 *   
 * 2. validate
 *    validator.validate(src, profiles);
 *      - source (as stream, byte[]), or url or filename of a resource to validate. 
 *        Also validate against any profiles (as canonical URLS, equivalent to listing them in Resource.meta.profile)
 *        
 *        if the source is provided as byte[] or stream, you need to provide a format too, though you can 
 *        leave that as null, and the validator will guess
 * 
 * 3. Or, instead of validating, transform        
 * @author Grahame Grieve
 *
 */
public class ValidationEngine {

	private SimpleWorkerContext context;
  private FHIRPathEngine fpe;
  private Map<String, byte[]> binaries = new HashMap<String, byte[]>();
  private boolean doNative;
  private boolean noInvariantChecks;
  private String version;

  private class AsteriskFilter implements FilenameFilter {
    String dir;
    String prefix;
    String suffix;
    int minLength;
    
    public AsteriskFilter(String filter) throws IOException {
      if (!filter.matches("(.*(\\\\|\\/))*(.*)\\*(.*)"))
        throw new IOException("Filter names must have the following syntax: [directorypath][prefix]?*[suffix]?   I.e. The asterisk must be in the filename, not the directory path");
      dir = filter.replaceAll("(.*(\\\\|\\/))*(.*)\\*(.*)", "$1");
      prefix = filter.replaceAll("(.*(\\\\|\\/))*(.*)\\*(.*)", "$3");
      suffix = filter.replaceAll("(.*(\\\\|\\/))*(.*)\\*(.*)", "$4");
      File f = new File(dir);
      if (!f.exists()) {
        throw new IOException("Directory " + dir + " does not exist");
      }
      if (!f.isDirectory()) {
        throw new IOException("Directory " + dir + " is not a directory");
      }
    }
    
    public boolean accept(File dir, String s) {
      boolean match = s.startsWith(prefix) && s.endsWith(suffix) && s.length() >= minLength;
      return match;
    }
    
    public String getDir() {
      return dir;
    }
  }
  
  public ValidationEngine(String src, String txsrvr) throws Exception {
    loadDefinitions(src);
    connectToTSServer(txsrvr);
  }
  
  private void loadDefinitions(String src) throws Exception {
    Map<String, byte[]> source = loadSource(src, "igpack.zip");   
    if (version == null)
      version = getVersionFromPack(source);
    context = SimpleWorkerContext.fromDefinitions(source, loaderForVersion());
    context.setAllowLoadingDuplicates(true); // because of Forge
    context.setExpansionProfile(makeExpProfile());
    fpe = new FHIRPathEngine(context);
    grabNatives(source, "http://hl7.org/fhir");
  }

  private IContextResourceLoader loaderForVersion() {
    if (Utilities.noString(version))
      return null;
    if (version.equals("1.0.2"))
      return new R2ToR4Loader();
    if (version.equals("1.4.0"))
      return new R3ToR4Loader(); // special case
    if (version.equals("3.0.1"))
      return new R3ToR4Loader();    
    return null;
  }

  private String getVersionFromPack(Map<String, byte[]> source) {
    if (source.containsKey("version.info")) {
      IniFile vi = new IniFile(new ByteArrayInputStream(removeBom(source.get("version.info"))));
      return vi.getStringProperty("FHIR", "version");
    } else {
      throw new Error("Missing version.info?");
    }
  }

  private byte[] removeBom(byte[] bs) {
    if (bs.length > 3 && bs[0] == -17 && bs[1] == -69 && bs[2] == -65)
      return Arrays.copyOfRange(bs, 3, bs.length);
    else
      return bs;
  }

  private ExpansionProfile makeExpProfile() {
    ExpansionProfile ep  = new ExpansionProfile();
    ep.setId("dc8fd4bc-091a-424a-8a3b-6198ef146891"); // change this to blow the cache
    ep.setUrl("http://hl7.org/fhir/ExpansionProfile/"+ep.getId());
    // all defaults....
    return ep;
  }

  private Map<String, byte[]> loadSource(String src, String defname) throws Exception {
    if (Utilities.noString(src)) {
      throw new FHIRException("Definitions Source '" + src + "' could not be processed");
    } else if (src.startsWith("https:") || src.startsWith("http:")) {
      return loadFromUrl(src, defname);
    } else if (new File(src).exists()) {
      return loadFromFile(src, defname);      
    } else {
      throw new FHIRException("Definitions Source '"+src+"' could not be processed");
  }
  }
  
  private Map<String, byte[]> loadFromUrl(String src, String defname) throws Exception {
    if (Utilities.noString(defname))
      defname = "validator.pack";
    if (!src.endsWith(defname))
      src = Utilities.pathReverse(src, defname);

		try {
      URL url = new URL(src);
      URLConnection c = url.openConnection();
      byte[] cnt = IOUtils.toByteArray(c.getInputStream());
      return readZip(new ByteArrayInputStream(cnt));
		} catch (Exception e) {
      throw new Exception("Unable to find definitions at URL '"+src+"': "+e.getMessage(), e);
	}
    }

  private Map<String, byte[]> loadFromFile(String src, String defname) throws FileNotFoundException, IOException {
    File f = new File(src);
    if (f.isDirectory()) {
      if (defname == null)
        throw new IOException("You must provide a file name, not a directory name");
      if (new File(Utilities.path(src, defname)).exists())
        return readZip(new FileInputStream(Utilities.path(src, defname)));
      else {
        Map<String, byte[]> res = new HashMap<String, byte[]>();
        for (File ff : f.listFiles()) {
          FhirFormat fmt = checkIsResource(ff.getAbsolutePath());
          if (fmt != null) {
            res.put(Utilities.changeFileExt(ff.getName(), "."+fmt.getExtension()), TextFile.fileToBytes(ff.getAbsolutePath()));
        }
    }
        return res;
    }
      } else {
        if (src.endsWith(".zip") || src.endsWith(".pack") || (defname != null && src.endsWith(defname)))
          return readZip(new FileInputStream(src));
        else {
          Map<String, byte[]> res = new HashMap<String, byte[]>();
          res.put(f.getName(), TextFile.fileToBytes(src));
          return res;
      }
    }
  }

  public SimpleWorkerContext getContext() {
    return context;
  }
  
  public FHIRPathEngine getFpe() {
    return fpe;
  }
  
  public boolean isNoInvariantChecks() {
    return noInvariantChecks;
  }

  public void setNoInvariantChecks(boolean value) {
    this.noInvariantChecks = value;
  }

  private FhirFormat checkIsResource(String path) {
    String ext = Utilities.getFileExtension(path);
    if (Utilities.existsInList(ext, "xml")) 
      return FhirFormat.XML;
    if (Utilities.existsInList(ext, "json")) 
      return FhirFormat.JSON;
    if (Utilities.existsInList(ext, "ttl")) 
      return FhirFormat.TURTLE;
    if (Utilities.existsInList(ext, "map")) 
      return FhirFormat.TEXT;

    try {
      Manager.parse(context, new FileInputStream(path), FhirFormat.XML);
      return FhirFormat.XML;
    } catch (Exception e) {
  }
    try {
      Manager.parse(context, new FileInputStream(path), FhirFormat.JSON);
      return FhirFormat.JSON;
    } catch (Exception e) {
  }
    try {
      Manager.parse(context, new FileInputStream(path), FhirFormat.TURTLE);
      return FhirFormat.TURTLE;
    } catch (Exception e) {
  }
      try {
      new StructureMapUtilities(context, null, null, null).parse(TextFile.fileToString(path));
        return FhirFormat.TEXT;
      } catch (Exception e) {
      }
    return null;
	}

  private Map<String, byte[]> readZip(InputStream stream) throws IOException {
    Map<String, byte[]> res = new HashMap<String, byte[]>();
    ZipInputStream zip = new ZipInputStream(stream);
		ZipEntry ze;
		while ((ze = zip.getNextEntry()) != null) {
				String name = ze.getName();
				InputStream in = zip;
				ByteArrayOutputStream b = new ByteArrayOutputStream();
				int n;
				byte[] buf = new byte[1024];
				while ((n = in.read(buf, 0, 1024)) > -1) {
					b.write(buf, 0, n);
				}        
      res.put(name, b.toByteArray());
			zip.closeEntry();
		}
		zip.close();    
    return res;
	}

  public void connectToTSServer(String url) throws URISyntaxException {
    if (url == null) {
      context.setCanRunWithoutTerminology(true);
    } else
    context.connectToTSServer(url);
	}

  public void loadIg(String src) throws IOException, FHIRException, Exception {
    String canonical = null;
    Map<String, byte[]> source = loadSource(src, "validator.pack");
    for (Entry<String, byte[]> t : source.entrySet()) {
      String fn = t.getKey();
      Resource res = null;
      try { 
      if (fn.endsWith(".xml") && !fn.endsWith("template.xml"))
        res = new XmlParser().parse(t.getValue());
      else if (fn.endsWith(".json") && !fn.endsWith("template.json"))
        res = new JsonParser().parse(t.getValue());
//      else if (fn.endsWith(".ttl"))
//        res = new RdfParser().parse(t.getValue());
      else if (fn.endsWith(".txt"))
        res = new StructureMapUtilities(context, null, null).parse(TextFile.bytesToString(t.getValue()));
      } catch (Exception e) {
        throw new Exception("Error parsing "+fn+": "+e.getMessage(), e);
      }

      if (res != null && res instanceof MetadataResource) {
        context.seeResource(((MetadataResource) res).getUrl(), res);
      } else if (res != null && res instanceof Questionnaire) {
        context.seeResource(((Questionnaire) res).getUrl(), res);
	}
      if (res instanceof ImplementationGuide)
        canonical = ((ImplementationGuide) res).getUrl();
		}
    if (canonical != null)
      grabNatives(source, canonical);
	}

  private void grabNatives(Map<String, byte[]> source, String prefix) {
    for (Entry<String, byte[]> e : source.entrySet()) {
      if (e.getKey().endsWith(".zip"))
        binaries.put(prefix+"#"+e.getKey(), e.getValue());
    }
	}

  public void setQuestionnaires(List<String> questionnaires) {
	}

  public void setNative(boolean doNative) {
    this.doNative = doNative;
  }

  private class Content {
    byte[] focus = null;
    FhirFormat cntType = null;
  }
  
  public Content loadContent(String source, String opName) throws Exception {
    Map<String, byte[]> s = loadSource(source, null);
    Content res = new Content();
    if (s.size() != 1)
      throw new Exception("Unable to find resource " + source + " to "+opName);
    for (Entry<String, byte[]> t: s.entrySet()) {
      res.focus = t.getValue();
      if (t.getKey().endsWith(".json"))
        res.cntType = FhirFormat.JSON; 
      else if (t.getKey().endsWith(".xml"))
        res.cntType = FhirFormat.XML; 
      else if (t.getKey().endsWith(".ttl"))
        res.cntType = FhirFormat.TURTLE; 
      else
        throw new Exception("Todo: Determining resource type is not yet done");
    }
    return res;
  }

  public OperationOutcome validate(String source, List<String> profiles) throws Exception {
    List<String> l = new ArrayList<String>();
    l.add(source);
    return (OperationOutcome)validate(l, profiles);
  }
    
  public Resource validate(List<String> sources, List<String> profiles) throws Exception {
    List<String> refs = new ArrayList<String>();
    boolean asBundle = handleSources(sources, refs);
    Bundle results = new Bundle();
    results.setType(Bundle.BundleType.COLLECTION);
    for (String ref : refs) {
      Content cnt = loadContent(ref, "validate");
      OperationOutcome outcome = validate(cnt.focus, cnt.cntType, profiles);
      ToolingExtensions.addStringExtension(outcome, ToolingExtensions.EXT_OO_FILE, ref);
      results.addEntry().setResource(outcome);
    }
    if (asBundle)
      return results;
    else
      return results.getEntryFirstRep().getResource();
  }

  public OperationOutcome validateString(String source, FhirFormat format, List<String> profiles) throws Exception {
    return validate(source.getBytes(), format, profiles);
  }

  private boolean handleSources(List<String> sources, List<String> refs) throws IOException {
    boolean asBundle = sources.size() > 1;
    for (String source : sources) {
      if (handleSource(source, refs)) {
        asBundle = true;  // Code needs to be written this way to ensure handleSource gets called
      }
    }
    
    return asBundle;
  }
  
  private boolean handleSource(String name, List<String> refs) throws IOException {
    boolean isBundle = false;
    if (name.startsWith("https:") || name.startsWith("http:")) {
      refs.add(name);

    } else if (name.contains("*")) {
      isBundle = true;
      AsteriskFilter filter = new AsteriskFilter(name);
      File[] files = new File(filter.getDir()).listFiles(filter);
      for (int i=0; i < files.length; i++) {
        refs.add(files[i].getPath());
      }
    
    } else {
      File file = new File(name);

      if (!file.exists())
        throw new IOException("File " + name + " does not exist");
    
      if (file.isFile()) {
        refs.add(name);
        
      } else {
        isBundle = true;
        for (int i=0; i < file.listFiles().length; i++) {
          File[] fileList = file.listFiles();
          if (fileList[i].isFile())
            refs.add(fileList[i].getPath());
        }
      }
    }
    
    return isBundle;
  }

  public OperationOutcome validate(byte[] source, FhirFormat cntType, List<String> profiles) throws Exception {
    List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
    if (doNative) {
      if (cntType == FhirFormat.JSON)
        validateJsonSchema(messages);
      if (cntType == FhirFormat.XML)
        validateXmlSchema(messages);
      if (cntType == FhirFormat.TURTLE)
        validateSHEX(messages);
    }
    InstanceValidator validator = new InstanceValidator(this);
    validator.setNoInvariantChecks(isNoInvariantChecks());
    validator.validate(null, messages, new ByteArrayInputStream(source), cntType, new ValidationProfileSet(profiles, true));
    return messagesToOutcome(messages);
  }

  private void validateSHEX(List<ValidationMessage> messages) {
    messages.add(new ValidationMessage(Source.InstanceValidator, IssueType.INFORMATIONAL, "SHEX Validation is not done yet", IssueSeverity.INFORMATION));
	}

  private void validateXmlSchema(List<ValidationMessage> messages) throws FileNotFoundException, IOException, SAXException {
    XmlValidator xml = new XmlValidator(messages, loadSchemas(), loadTransforms());
    messages.add(new ValidationMessage(Source.InstanceValidator, IssueType.INFORMATIONAL, "XML Schema Validation is not done yet", IssueSeverity.INFORMATION));
	}

  private Map<String, byte[]> loadSchemas() throws IOException {
    Map<String, byte[]> res = new HashMap<String, byte[]>();
    for (Entry<String, byte[]> e : readZip(new ByteArrayInputStream(binaries.get("http://hl7.org/fhir#fhir-all-xsd.zip"))).entrySet()) {
      if (e.getKey().equals("fhir-single.xsd"))
        res.put(e.getKey(), e.getValue());
      if (e.getKey().equals("fhir-invariants.sch"))
        res.put(e.getKey(), e.getValue());
    }
    return res;
  }
  
  private Map<String, byte[]> loadTransforms() throws IOException {
    Map<String, byte[]> res = new HashMap<String, byte[]>();
    for (Entry<String, byte[]> e : readZip(new ByteArrayInputStream(binaries.get("http://hl7.org/fhir#fhir-all-xsd.zip"))).entrySet()) {
      if (e.getKey().endsWith(".xsl"))
        res.put(e.getKey(), e.getValue());
    }
    return res;
  }

  private void validateJsonSchema(List<ValidationMessage> messages) {
    messages.add(new ValidationMessage(Source.InstanceValidator, IssueType.INFORMATIONAL, "JSON Schema Validation is not done yet", IssueSeverity.INFORMATION));   
	}

  private List<ValidationMessage> filterMessages(List<ValidationMessage> messages) {
    List<ValidationMessage> filteredValidation = new ArrayList<ValidationMessage>();
    for (ValidationMessage e : messages) {
      if (!filteredValidation.contains(e))
        filteredValidation.add(e);
    }
    filteredValidation.sort(null);
    return filteredValidation;
  }
  
  private OperationOutcome messagesToOutcome(List<ValidationMessage> messages) throws DefinitionException {
    OperationOutcome op = new OperationOutcome();
    for (ValidationMessage vm : filterMessages(messages)) {
      op.getIssue().add(OperationOutcomeUtilities.convertToIssue(vm, op));
    }
    new NarrativeGenerator("", "", context).generate(null, op);
    return op;
	}
  
  public static String issueSummary (OperationOutcomeIssueComponent issue) {
    String source = ToolingExtensions.readStringExtension(issue, ToolingExtensions.EXT_ISSUE_SOURCE);
    return issue.getSeverity().toString()+" @ "+issue.getLocation() + " " +issue.getDetails().getText() +(source != null ? " (src = "+source+")" : "");    
  }

  public Resource transform(String source, String map) throws Exception {
    Content cnt = loadContent(source, "validate");
    return transform(cnt.focus, cnt.cntType, map);
  }
  
  public Resource transform(byte[] source, FhirFormat cntType, String mapUri) throws Exception {
    StructureMapUtilities scu = new StructureMapUtilities(context);

//    org.hl7.fhir.r4.elementmodel.Element src = Manager.parse(context, new ByteArrayInputStream(source), cntType); 
//    StructureMap map = scu.getLibrary().get(mapUri);
//    if (map == null)
//      throw new Error("Unable to find map "+mapUri);
//    
//    Resource dst = ResourceFactory.createResource("Bundle");
//    scu.transform(null, src, map, dst);
//    return dst;
    return null;
  }

  public DomainResource generate(String source) throws Exception {
    Content cnt = loadContent(source, "validate");
    Resource res;
    if (cnt.cntType == FhirFormat.XML)
      res = new XmlParser().parse(cnt.focus);
    else if (cnt.cntType == FhirFormat.JSON)
      res = new JsonParser().parse(cnt.focus);
    else if (cnt.cntType == FhirFormat.TURTLE)
      res = new RdfParser().parse(cnt.focus);
    else
      throw new Error("Not supported yet");
  
    new NarrativeGenerator("",  "", context).generate((DomainResource) res);
    return (DomainResource) res;
  }
  
  public StructureDefinition snapshot(String source) throws Exception {
    Content cnt = loadContent(source, "validate");
    Resource res;
    if (cnt.cntType == FhirFormat.XML)
      res = new XmlParser().parse(cnt.focus);
    else if (cnt.cntType == FhirFormat.JSON)
      res = new JsonParser().parse(cnt.focus);
    else if (cnt.cntType == FhirFormat.TURTLE)
      res = new RdfParser().parse(cnt.focus);
    else
      throw new Error("Not supported yet");
  
    if (!(res instanceof StructureDefinition))
      throw new Exception("Require a StructureDefinition for generating a snapshot");
    StructureDefinition sd = (StructureDefinition) res;
    StructureDefinition base = context.fetchResource(StructureDefinition.class, sd.getBaseDefinition());
    
    new ProfileUtilities(context, null, null).generateSnapshot(base, sd, sd.getUrl(), sd.getName());
    return sd;
  }
  
}
