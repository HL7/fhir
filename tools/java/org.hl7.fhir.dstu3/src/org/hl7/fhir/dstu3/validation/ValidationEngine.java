package org.hl7.fhir.dstu3.validation;

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
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.io.IOUtils;
import org.hl7.fhir.dstu3.elementmodel.Manager;
import org.hl7.fhir.dstu3.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.dstu3.exceptions.DefinitionException;
import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.exceptions.FHIRFormatError;
import org.hl7.fhir.dstu3.formats.IParser;
import org.hl7.fhir.dstu3.formats.JsonParser;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.dstu3.formats.FormatUtilities;
import org.hl7.fhir.dstu3.model.BaseConformance;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.ImplementationGuide;
import org.hl7.fhir.dstu3.model.OperationOutcome;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueType;
import org.hl7.fhir.dstu3.model.Questionnaire;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.ResourceType;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.terminologies.ValueSetExpansionCache;
import org.hl7.fhir.dstu3.utils.NarrativeGenerator;
import org.hl7.fhir.dstu3.utils.SimpleWorkerContext;
import org.hl7.fhir.dstu3.utils.XmlLocationAnnotator;
import org.hl7.fhir.dstu3.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.SchemaInputSource;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.google.gson.JsonObject;

/**
 * This is just a wrapper around the InstanceValidator class for convenient use 
 * 
 * The following resource formats are supported: XML, JSON, Turtle
 * The following verions are supported: 1.4.0, 1.6.0, and current
 * 
 * Note: the validation engine is not threadsafe
 * To Use:
 *  
 * 1/ Initialise
 *    ValidationEngine validator = new ValidationEngine();
 *    validator.loadDefinitions(src);
 *      - this must refer to the igpack.zip for the version of the spec against which you wnat to validate
 *       it can be a url or a file reference. It can nominate the igpack.zip directly, 
 *       or it can name the container alone (e.g. just the spec URL).
 *       The validation engine does not cache igpack.zip. the user must manage that if desired 
 *
 *    validator.connectToTSServer(txServer);
 *      - this is optional; in the absence of a terminology service, snomed, loinc etc will not be validated
 *      
 *    validator.loadIg(src);
 *      - call this any number of times for the Implementation Guide of interest. This is also a reference
 *        to the igpack.zip for the implementation guide - same rules as above
 *        the version of the IGPack must match that of the spec (todo: enforce this?)
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
 * @author Grahame Grieve
 *
 */
public class ValidationEngine {

  private SimpleWorkerContext context;
  private InstanceValidator validator;
  private List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
  private boolean doNative;
  private Map<String, byte[]> binaries = new HashMap<String, byte[]>();

  public void loadDefinitions(String src) throws Exception {
    Map<String, byte[]> source = loadSource(src, "igpack.zip");   
    context = SimpleWorkerContext.fromDefinitions(source);
    validator  = new InstanceValidator(context);    
    grabNatives(source, "http://hl7.org/fhir");
  }

  Map<String, byte[]> loadSource(String src, String defname) throws Exception {
    if (Utilities.noString(src)) {
      throw new FHIRException("Definitions Source '' could not be processed");
    } else if (src.startsWith("https:") || src.startsWith("http:")) {
      return loadFromUrl(src);
    } else if (new File(src).exists()) {
      return loadFromFile(src, defname);      
    } else {
      throw new FHIRException("Definitions Source '"+src+"' could not be processed");
    }
  }

  private Map<String, byte[]> loadFromUrl(String src) throws Exception {
    if (!src.endsWith("validator.pack"))
      src = Utilities.pathReverse(src, "validator.pack");

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
        throw new IOException("You must provide a fie name, not a directory name");
      if (new File(Utilities.path(src, defname)).exists())
        return readZip(new FileInputStream(Utilities.path(src, defname)));
      else {
        Map<String, byte[]> res = new HashMap<String, byte[]>();
        for (File ff : f.listFiles()) {
          FhirFormat fmt = checkIsResource(ff.getAbsolutePath());
          if (ff != null) {
            res.put(Utilities.changeFileExt(ff.getName(), fmt.getExtension()), TextFile.fileToBytes(ff.getAbsolutePath()));
          }
        }
        return res;
      }
    } else {
      if (src.endsWith(".zip") || (defname != null && src.endsWith(defname)))
        return readZip(new FileInputStream(src));
      else {
        Map<String, byte[]> res = new HashMap<String, byte[]>();
        res.put(f.getName(), TextFile.fileToBytes(src));
        return res;
      }
    }
  }

  private FhirFormat checkIsResource(String path) {
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
      if (fn.endsWith(".xml"))
        res = new XmlParser().parse(t.getValue());
      else if (fn.endsWith(".json"))
        res = new JsonParser().parse(t.getValue());
//      else if (fn.endsWith(".ttl"))
//        res = new RdfParser().parse(t.getValue());

      if (res != null && res instanceof BaseConformance) {
        context.seeResource(((BaseConformance) res).getUrl(), res);
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
//    validator.set
  }

  public void setNative(boolean doNative) {
    this.doNative = doNative;
  }


  public OperationOutcome validate(String source, List<String> profiles) throws Exception {
    Map<String, byte[]> s = loadSource(source, null);
    if (s.size() != 1)
      throw new Exception("Unable to find a single resource to validate");
    byte[] focus = null;
    FhirFormat cntType = null;
    for (Entry<String, byte[]> t: s.entrySet()) {
      focus = t.getValue();
      if (t.getKey().endsWith(".json"))
        cntType = FhirFormat.JSON; 
      else if (t.getKey().endsWith(".xml"))
        cntType = FhirFormat.XML; 
      else if (t.getKey().endsWith(".ttl"))
        cntType = FhirFormat.TURTLE; 
      else
        throw new Exception("Todo: Determining resource type is not yet done");
    }
    return validate(focus, cntType, profiles);
  }
  
  public OperationOutcome validate(byte[] source, FhirFormat cntType, List<String> profiles) throws Exception {
    if (doNative) {
      if (cntType == FhirFormat.JSON)
        validateJsonSchema();
      if (cntType == FhirFormat.XML)
        validateXmlSchema();
      if (cntType == FhirFormat.TURTLE)
        validateSHEX();
    }
    messages.clear();
    validator.validate(messages, new ByteArrayInputStream(source), cntType, new ValidationProfileSet(profiles));
    return getOutcome();
  }

  private void validateSHEX() {
    messages.add(new ValidationMessage(Source.InstanceValidator, IssueType.INFORMATIONAL, "SHEX Validation is not done yet", IssueSeverity.INFORMATION));
  }

  private void validateXmlSchema() throws FileNotFoundException, IOException, SAXException {
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

  private void validateJsonSchema() {
    messages.add(new ValidationMessage(Source.InstanceValidator, IssueType.INFORMATIONAL, "JSON Schema Validation is not done yet", IssueSeverity.INFORMATION));   
  }

  public List<ValidationMessage> getMessages() {
    return messages;
  }

  private OperationOutcome getOutcome() throws DefinitionException {
    OperationOutcome op = new OperationOutcome();
    for (ValidationMessage vm : messages) {
      op.getIssue().add(vm.asIssue(op));
    }
    new NarrativeGenerator("", "", context).generate(op);
    return op;
  }

  public InstanceValidator getValidator() {
    return validator;
  }
}
