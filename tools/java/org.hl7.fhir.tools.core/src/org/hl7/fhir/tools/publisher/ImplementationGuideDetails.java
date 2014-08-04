package org.hl7.fhir.tools.publisher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class ImplementationGuideDetails {

  private String name;
  private String contact;
  private String outputFolder;
  private String homePage;
  private String baseUri;
  private String filePrefix;
  private List<String> inputFolders = new ArrayList<String>();
  private List<String> imageSources = new ArrayList<String>();
  private List<String> pages = new ArrayList<String>();
  
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getContact() {
    return contact;
  }
  public void setContact(String contact) {
    this.contact = contact;
  }
  public String getOutputFolder() {
    return outputFolder;
  }
  public void setOutputFolder(String outputFolder) {
    this.outputFolder = outputFolder;
  }
  public List<String> getInputFolders() {
    return inputFolders;
  }
  
  public String getHomePage() {
    return homePage;
  }
  public void setHomePage(String homePage) {
    this.homePage = homePage;
  }
  
  public List<String> getImageSources() {
    return imageSources;
  }
  
  public List<String> getPages() {
    return pages;
  }
  public String getFilePrefix() {
    return filePrefix;
  }
  public void setFilePrefix(String filePrefix) {
    this.filePrefix = filePrefix;
  }
  public String getBaseUri() {
    return baseUri;
  }
  public void setBaseUri(String baseUri) {
    this.baseUri = baseUri;
  }
  
  public static ImplementationGuideDetails loadFromFile(String filename) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new CSFileInputStream(new CSFile(filename)));
    String folder = Utilities.getDirectoryForFile(filename);
    
    ImplementationGuideDetails res = new ImplementationGuideDetails();
    res.setName(XMLUtil.getNamedChild(doc.getDocumentElement(), "name").getTextContent());
    res.setContact(XMLUtil.getNamedChild(doc.getDocumentElement(), "contact").getTextContent());
    res.setOutputFolder(fixFolder(folder, XMLUtil.getNamedChild(doc.getDocumentElement(), "output").getTextContent()));
    res.setHomePage(fixFolder(folder, XMLUtil.getNamedChild(doc.getDocumentElement(), "homePage").getTextContent()));
    res.setBaseUri(XMLUtil.getNamedChild(doc.getDocumentElement(), "baseUri").getTextContent());
    res.setFilePrefix(XMLUtil.getNamedChild(doc.getDocumentElement(), "filePrefix").getTextContent());
    
    Element e = XMLUtil.getNamedChild(doc.getDocumentElement(), "folders");
    e = XMLUtil.getFirstChild(e);
    while (e != null) {
      res.getInputFolders().add(fixFolder(folder, e.getTextContent()));
      e = XMLUtil.getNextSibling(e);
    }

    e = XMLUtil.getNamedChild(doc.getDocumentElement(), "images");
    e = XMLUtil.getFirstChild(e);
    while (e != null) {
      res.getImageSources().add(fixFolder(folder, e.getTextContent()));
      e = XMLUtil.getNextSibling(e);
    }

    e = XMLUtil.getNamedChild(doc.getDocumentElement(), "pages");
    e = XMLUtil.getFirstChild(e);
    while (e != null) {
      res.getPages().add(fixFolder(folder, e.getTextContent()));
      e = XMLUtil.getNextSibling(e);
    }

    return res;
  }
  
  private static String fixFolder(String context, String path) {
    if (path.startsWith(".\\"))
      return context+path.substring(1);
    else if (path.startsWith("./"))
      return context+"\\"+path.substring(2);
    else
      return path;
  }
}
