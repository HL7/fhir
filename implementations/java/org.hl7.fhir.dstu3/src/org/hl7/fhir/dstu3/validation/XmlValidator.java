package org.hl7.fhir.dstu3.validation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueType;
import org.hl7.fhir.dstu3.validation.ValidationMessage;
import org.hl7.fhir.dstu3.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.SchemaInputSource;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.Logger.LogMessageType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XmlValidator {
  
  private String rootDir;
  private String xsltDir;
  private Schema schema;
  private List<ValidationMessage> errors;
  private Logger logger;

  public class MyErrorHandler implements ErrorHandler {

    private List<String> errors = new ArrayList<String>();
    private List<ValidationMessage> list;
    private String path;

    public MyErrorHandler(List<ValidationMessage> list, String path) {
      this.list = list;
      this.path = path;
    }

    @Override
    public void error(SAXParseException arg0) throws SAXException {
      if (list != null)
        list.add(new ValidationMessage(Source.InstanceValidator, IssueType.STRUCTURE, arg0.getLineNumber(), arg0.getColumnNumber(), path == null ? arg0.getSystemId() : path, arg0.getMessage(), IssueSeverity.ERROR));
      if (logger != null)
        logger.log("error: " + arg0.toString(), LogMessageType.Error);
      errors.add(arg0.toString());
    }

    @Override
    public void fatalError(SAXParseException arg0) throws SAXException {
      if (list != null)
        list.add(new ValidationMessage(Source.InstanceValidator, IssueType.STRUCTURE, arg0.getLineNumber(), arg0.getColumnNumber(), path == null ? arg0.getSystemId() : path, arg0.getMessage(), IssueSeverity.FATAL));
      if (logger != null)
        logger.log("fatal error: " + arg0.toString(), LogMessageType.Error);
    }

    @Override
    public void warning(SAXParseException arg0) throws SAXException {
      if (list != null)
        list.add(new ValidationMessage(Source.InstanceValidator, IssueType.STRUCTURE, arg0.getLineNumber(), arg0.getColumnNumber(), path == null ? arg0.getSystemId() : path, arg0.getMessage(), IssueSeverity.WARNING));
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
  
  public class MyResourceResolver implements LSResourceResolver {
    
    @Override
    public LSInput resolveResource(final String type, final String namespaceURI, final String publicId, String systemId, final String baseURI) {
      try {
        if (!new CSFile(Utilities.path(rootDir, systemId)).exists())
          return null;
        return new SchemaInputSource(new CSFileInputStream(new CSFile(Utilities.path(rootDir, systemId))), publicId, systemId, namespaceURI);
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }
  }

  public XmlValidator(List<ValidationMessage> errors, String rootDir, String xsltDir, String[] schemaSource) throws FileNotFoundException, IOException, SAXException {
    this.errors = errors;
    this.rootDir = rootDir;
    this.xsltDir = xsltDir;
    StreamSource[] sources = new StreamSource[schemaSource.length];
    int i = 0;
    for (String s : schemaSource) {
      sources[i] = new StreamSource(new CSFileInputStream(Utilities.path(rootDir, s)));
      i++;
    }
    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    schemaFactory.setErrorHandler(new MyErrorHandler(errors, null));
    schemaFactory.setResourceResolver(new MyResourceResolver());
    schema = schemaFactory.newSchema(sources);
  }

  public Element checkBySchema(String fileToCheck, boolean wantThrow) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, FHIRException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    factory.setValidating(false);
    factory.setSchema(schema);
    DocumentBuilder builder = factory.newDocumentBuilder();
    MyErrorHandler err = new MyErrorHandler(errors, fileToCheck);
    builder.setErrorHandler(err);
    CSFileInputStream f = new CSFileInputStream(new CSFile(fileToCheck));
    Document doc = builder.parse(f);
    if (wantThrow && err.getErrors().size() > 0)
      throw new FHIRException("File " + fileToCheck + " failed schema validation");
    return doc.getDocumentElement();
  }

  public void checkBySchematron(String n, String sch, boolean wantThrow) throws IOException, ParserConfigurationException, SAXException, FileNotFoundException, FHIRException {
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document doc;
    File tmpTransform = Utilities.createTempFile("tmp", ".xslt");
    File tmpOutput = Utilities.createTempFile("tmp", ".xml");
    try {
      Utilities.saxonTransform(xsltDir, Utilities.path(rootDir, sch), Utilities.path(xsltDir, "iso_svrl_for_xslt2.xsl"), tmpTransform.getAbsolutePath(), null);
      Utilities.saxonTransform(xsltDir, Utilities.path(rootDir, n + ".xml"), tmpTransform.getAbsolutePath(), tmpOutput.getAbsolutePath(), null);
    } catch (Throwable e) {
      errors.add(new ValidationMessage(Source.InstanceValidator, IssueType.STRUCTURE, -1, -1, n+":"+sch, e.getMessage(), IssueSeverity.ERROR));
      if (wantThrow)
        throw new FHIRException("Error validating " + rootDir + n + ".xml with schematrons", e);
    }

    factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    builder = factory.newDocumentBuilder();
    doc = builder.parse(new CSFileInputStream(tmpOutput.getAbsolutePath()));
    NodeList nl = doc.getDocumentElement().getElementsByTagNameNS("http://purl.oclc.org/dsdl/svrl", "failed-assert");
    if (nl.getLength() > 0) {
      logger.log("Schematron Validation Failed for " + n + ".xml:", LogMessageType.Error);
      for (int i = 0; i < nl.getLength(); i++) {
        Element e = (Element) nl.item(i);
        logger.log("  @" + e.getAttribute("location") + ": " + e.getTextContent(), LogMessageType.Error);
        errors.add(new ValidationMessage(Source.InstanceValidator, IssueType.STRUCTURE, -1, -1, n+":"+e.getAttribute("location"), e.getTextContent(), IssueSeverity.ERROR));
      }
      if (wantThrow)
        throw new FHIRException("Schematron Validation Failed for " + n + ".xml");
    }
  }

  
}
