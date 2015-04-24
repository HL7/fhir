package org.hl7.fhir.instance.validation;

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
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.io.IOUtils;
import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.utils.NarrativeGenerator;
import org.hl7.fhir.instance.utils.ValueSetExpansionCache;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.SchemaInputSource;
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

public class ValidationEngine {
	static final String MASTER_SOURCE = "http://hl7.org/documentcenter/public/standards/FHIR-Develop/validator.zip"; // fix after DSTU!!

	//  static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	//  static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
	//  static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

	private byte[] source;
	private Map<String, byte[]> definitions = new HashMap<String, byte[]>();
	private List<ValidationMessage> outputs;  
	private OperationOutcome outcome;
	private boolean noSchematron;
	private StructureDefinition profile;
	private String profileURI;
	private WorkerContext context;
	private Schema schema;
	private byte[] schCache = null;
	private ValueSetExpansionCache cache;
	private List<String> extensionDomains = new ArrayList<String>();
	private boolean anyExtensionsAllowed;


	public String getProfileURI() {
		return profileURI;
	}

	public void setProfileURI(String profileURI) {
		this.profileURI = profileURI;
	}

	public void process() throws Exception {
		if (isXml())
			processXml();
		else
			processJson();
	}
	
  private boolean isXml() {
	  int x = position(source, '<'); 
	  int j = position(source, '{');
	  return (x < j);
  }

	private int position(byte[] bytes, char target) {
		byte t = (byte) target;
		for (int i = 0; i < bytes.length; i++)
			if (bytes[i] == t)
				return i;
		return Integer.MAX_VALUE;
	  
  }

	public void processXml() throws Exception {
		outputs = new ArrayList<ValidationMessage>();

		// ok all loaded
    System.out.println("  .. validate (xml)");

		// 1. schema validation 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(false);
		factory.setSchema(schema);
		DocumentBuilder builder = factory.newDocumentBuilder();
		builder.setErrorHandler(new ValidationErrorHandler(outputs));
		Document doc = builder.parse(new ByteArrayInputStream(source));

		if (!noSchematron) {
			// 2. schematron validation
			if (schCache == null) {
			  String sch = "fhir-invariants.sch";
			  schCache = Utilities.saxonTransform(definitions, definitions.get(sch), definitions.get("iso_svrl_for_xslt2.xsl"));
			}
			byte[] out = Utilities.saxonTransform(definitions, source, schCache);
			processSchematronOutput(out);
		}

		// 3. internal validation. reparse without schema to "help", and use a special parser that keeps location data for us
		factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(false);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer nullTransformer = transformerFactory.newTransformer();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		doc = docBuilder.newDocument();
		DOMResult domResult = new DOMResult(doc);
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		saxParserFactory.setNamespaceAware(true);
		saxParserFactory.setValidating(false);
		SAXParser saxParser = saxParserFactory.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
    XmlLocationAnnotator locationAnnotator = new XmlLocationAnnotator(xmlReader, doc);
    InputSource inputSource = new InputSource(new ByteArrayInputStream(source));
    SAXSource saxSource = new SAXSource(locationAnnotator, inputSource);
    nullTransformer.transform(saxSource, domResult);

		if (cache == null)
		  cache = new ValueSetExpansionCache(context, null);
		InstanceValidator validator = new InstanceValidator(context, cache);
		validator.setAnyExtensionsAllowed(anyExtensionsAllowed);
		validator.getExtensionDomains().addAll(extensionDomains);

		if (profile != null)
			outputs.addAll(validator.validate(doc, profile));
		else if (profileURI != null)
			outputs.addAll(validator.validate(doc, profileURI));
		else
			outputs.addAll(validator.validate(doc));

		try {
		  new XmlParser().parse(new ByteArrayInputStream(source));
		} catch (Exception e) {
			outputs.add(new ValidationMessage(Source.InstanceValidator, "structure", -1, -1, "??", e.getMessage(), IssueSeverity.ERROR));
		}

		OperationOutcome op = new OperationOutcome();
		for (ValidationMessage vm : outputs) {
			op.getIssue().add(vm.asIssue(op));
		}
		new NarrativeGenerator("", context).generate(op);
		outcome = op;
	}

  public void processJson() throws Exception {
		outputs = new ArrayList<ValidationMessage>();

		// ok all loaded
    System.out.println("  .. validate (json)");

    com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
    JsonObject obj = parser.parse(new String(source)).getAsJsonObject();

		if (cache == null)
		  cache = new ValueSetExpansionCache(context, null);
		InstanceValidator validator = new InstanceValidator(context, cache);
		validator.setAnyExtensionsAllowed(anyExtensionsAllowed);
		validator.getExtensionDomains().addAll(extensionDomains);

		if (profile != null)
			outputs.addAll(validator.validate(obj, profile));
		else if (profileURI != null)
			outputs.addAll(validator.validate(obj, profileURI));
		else
			outputs.addAll(validator.validate(obj));

		try {
		  new JsonParser().parse(new ByteArrayInputStream(source));
		} catch (Exception e) {
			outputs.add(new ValidationMessage(Source.InstanceValidator, "structure", -1, -1, "??", e.getMessage(), IssueSeverity.ERROR));
		}

		OperationOutcome op = new OperationOutcome();
		for (ValidationMessage vm : outputs) {
			op.getIssue().add(vm.asIssue(op));
		}
		new NarrativeGenerator("", context).generate(op);
		outcome = op;
	}

	public class ValidatorResourceResolver implements LSResourceResolver {

		private Map<String, byte[]> files;

		public ValidatorResourceResolver(Map<String, byte[]> files) {
			this.files = files;
		}

		@Override
		public LSInput resolveResource(final String type, final String namespaceURI, final String publicId, String systemId, final String baseURI) {
			//      if (!(namespaceURI.equals("http://hl7.org/fhir"))) //|| namespaceURI.equals("http://www.w3.org/1999/xhtml")))
			if (!files.containsKey(systemId))
				return null;
			return new SchemaInputSource(new ByteArrayInputStream(files.get(systemId)), publicId, systemId, namespaceURI);
		}
	}

	private Schema readSchema() throws SAXException {
		StreamSource[] sources = new StreamSource[1];
		sources[0] = new StreamSource(new ByteArrayInputStream(definitions.get("fhir-all.xsd")));

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		schemaFactory.setErrorHandler(new ValidationErrorHandler(outputs));
		schemaFactory.setResourceResolver(new ValidatorResourceResolver(definitions));
		Schema schema = schemaFactory.newSchema(sources);
		return schema;
	}

	private void processSchematronOutput(byte[] out)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory;
		DocumentBuilder builder;
		Document doc;
		factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		builder = factory.newDocumentBuilder();
		doc = builder.parse(new ByteArrayInputStream(out));
		NodeList nl = doc.getDocumentElement().getElementsByTagNameNS("http://purl.oclc.org/dsdl/svrl", "failed-assert");
		if (nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				Element e = (Element) nl.item(i);
				ValidationMessage o = new ValidationMessage();
				o.setSource(Source.Schematron);
				o.setType("invariant");
				o.setLevel(IssueSeverity.ERROR);
				o.setLocation(e.getAttribute("location"));
				o.setMessage(e.getTextContent());
				outputs.add(o);
			}
		}
	}

	public List<ValidationMessage> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<ValidationMessage> outputs) {
		this.outputs = outputs;
	}

	public byte[] getSource() {
		return source;
	}

	public Map<String, byte[]> getDefinitions() {
		return definitions;
	}

	public OperationOutcome getOutcome() {
		return outcome;
	}

	public void setSource(byte[] source) {
		this.source = source;
	}

	public boolean isNoSchematron() {
		return noSchematron;
	}

	public void setNoSchematron(boolean noSchematron) {
		this.noSchematron = noSchematron;
	}

	public StructureDefinition getProfile() {
		return profile;
	}

	public void setProfile(StructureDefinition profile) {
		this.profile = profile;
	}

	public void init() throws Exception {
		context = WorkerContext.fromDefinitions(definitions);    
		schema = readSchema();
	}

	public WorkerContext getContext() {
		return context;
	}

	public void readDefinitions(byte[] defn) throws Exception {
		ZipInputStream zip = new ZipInputStream(new ByteArrayInputStream(defn));
		ZipEntry ze;
		while ((ze = zip.getNextEntry()) != null) {
			if (!ze.getName().endsWith(".zip") && !ze.getName().endsWith(".jar") ) { // skip saxon .zip
				String name = ze.getName();
				InputStream in = zip;
				ByteArrayOutputStream b = new ByteArrayOutputStream();
				int n;
				byte[] buf = new byte[1024];
				while ((n = in.read(buf, 0, 1024)) > -1) {
					b.write(buf, 0, n);
				}        
				getDefinitions().put(name, b.toByteArray());
			}
			zip.closeEntry();
		}
		zip.close();    
		init();
	}

	public void readDefinitions(String definitions) throws Exception {
    System.out.println("  .. load definitions from "+definitions);
		byte[] defn;
		if (Utilities.noString(definitions)) {
			defn = loadFromUrl(MASTER_SOURCE);
		} else if (definitions.startsWith("https:") || definitions.startsWith("http:")) {
			defn = loadFromUrl(definitions);
		} else if (new File(definitions).exists()) {
			defn = loadFromFile(definitions);      
		} else
			throw new Exception("Unable to find FHIR validation Pack (source = "+definitions+")");
		readDefinitions(defn);
	}

	public byte[] loadFromUrl(String src) throws Exception {
		URL url = new URL(src);
		byte[] str = IOUtils.toByteArray(url.openStream());
		return str;
	}

	public byte[] loadFromFile(String src) throws Exception {
		FileInputStream in = new FileInputStream(src);
		byte[] b = new byte[in.available()];
		in.read(b);
		in.close();
		return b;
	}

	public void loadProfile(String profile) throws Exception {
		if (!Utilities.noString(profile)) { 
	    System.out.println("  .. load profile "+profile);
			if (getContext().getProfiles().containsKey(profile))
				setProfile(getContext().getProfiles().get(profile));
			else
				setProfile(readProfile(loadProfileCnt(profile)));
		}
	}

	private StructureDefinition readProfile(byte[] content) throws Exception {
		XmlParser xml = new XmlParser(true);
		return (StructureDefinition) xml.parse(new ByteArrayInputStream(content));
	}

	private byte[] loadProfileCnt(String profile) throws Exception {
		if (Utilities.noString(profile)) {
			return null;
		} else if (profile.startsWith("https:") || profile.startsWith("http:")) {
			return loadFromUrl(profile);
		} else if (new File(profile).exists()) {
			return loadFromFile(profile);      
		} else
			throw new Exception("Unable to find named profile (source = "+profile+")");
	}

	public void reset() {
		source = null;
		outputs = null;  
		outcome = null;
		profile = null;
		profileURI = null;
  }

	public List<String> getExtensionDomains() {
		return extensionDomains;
	}

	public void setExtensionDomains(List<String> extensionDomains) {
		this.extensionDomains = extensionDomains;
	}

	public boolean isAnyExtensionsAllowed() {
		return anyExtensionsAllowed;
	}

	public void setAnyExtensionsAllowed(boolean anyExtensionsAllowed) {
		this.anyExtensionsAllowed = anyExtensionsAllowed;
	}


}
