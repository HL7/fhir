package org.hl7.fhir.instance.validation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.utils.NarrativeGenerator;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.SchemaInputSource;
import org.hl7.fhir.utilities.Utilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.SAXException;

public class ValidationEngine {

//  static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
//  static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
//  static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

  private byte[] source;
  private Map<String, byte[]> definitions = new HashMap<String, byte[]>();
  private List<ValidationMessage> outputs;  
  private OperationOutcome outcome;
	private boolean noSchematron;


  public void process() throws Exception {
    outputs = new ArrayList<ValidationMessage>();
    Schema schema = readSchema();

    // ok all loaded

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
    	String sch = doc.getDocumentElement().getNodeName().toLowerCase();
    	if (sch.equals("feed"))
    		sch = "fhir-atom";
    	byte[] tmp = Utilities.transform(definitions, definitions.get(sch+".sch"), definitions.get("iso_svrl_for_xslt1.xsl"));
    	byte[] out = Utilities.transform(definitions, source, tmp);
    	processSchematronOutput(out);
    }

    // 3. internal validation. reparse without schema to "help"
    factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    factory.setValidating(false);
    builder = factory.newDocumentBuilder();
    builder.setErrorHandler(new ValidationErrorHandler(outputs));
    doc = builder.parse(new ByteArrayInputStream(source));

    outputs.addAll(new InstanceValidator(definitions, null).validateInstance(doc.getDocumentElement()));

    Resource r = new XmlParser().parse(new ByteArrayInputStream(source));
        
    OperationOutcome op = new OperationOutcome();
    for (ValidationMessage vm : outputs) {
      op.getIssue().add(vm.asIssue(op));
    }
    new NarrativeGenerator("", null, null, null, null, null, null).generate(op);
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
    StreamSource[] sources = new StreamSource[2];
    sources[0] = new StreamSource(new ByteArrayInputStream(definitions.get("fhir-all.xsd")));
    sources[1] = new StreamSource(new ByteArrayInputStream(definitions.get("fhir-atom.xsd")));

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
        o.setLevel(IssueSeverity.error);
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


}
