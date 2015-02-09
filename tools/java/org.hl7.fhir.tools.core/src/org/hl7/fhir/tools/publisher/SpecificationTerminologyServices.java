package org.hl7.fhir.tools.publisher;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.hl7.fhir.instance.client.EFhirClientException;
import org.hl7.fhir.instance.client.FHIRSimpleClient;
import org.hl7.fhir.instance.client.IFHIRClient;
import org.hl7.fhir.instance.formats.IParser.OutputStyle;
import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetComposeComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.instance.utils.ITerminologyServices;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.hl7.fhir.utilities.xml.XMLWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SpecificationTerminologyServices  implements ITerminologyServices {

  public class Concept {
    private String display; // preferred
    private List<String> displays = new ArrayList<String>();
    public boolean has(String d) {
      if (display.equalsIgnoreCase(d))
        return true;
      for (String s : displays)
        if (s.equalsIgnoreCase(d))
          return true;
      return false;
    }
    public String summary() {
      CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
      b.append(display);
      for (String s : displays)
        if (!s.equalsIgnoreCase(display))
          b.append(s);
      return b.toString();
    }
    
  }
  private Map<String, Concept> snomedCodes = new HashMap<String, Concept>();
  private Map<String, Concept> loincCodes = new HashMap<String, Concept>();
  private boolean triedServer = false;
  private boolean serverOk = false;
  private String cache;
  private String tsServer;
  
  public SpecificationTerminologyServices(String cache, String tsServer) {
    super();
    this.cache = cache;
    this.tsServer = tsServer;
  }

  @Override
  public ConceptDefinitionComponent getCodeDefinition(String system, String code) {
    if (system == null)
      return null;
    if (system.equals("http://snomed.info/sct"))
      try {
        return locateSnomed(code);
      } catch (Exception e) {
      }        
    if (system.equals("http://loinc.org"))
      try {
        return locateLoinc(code);
      } catch (Exception e) {
      }        
    return null;
  }

  private ConceptDefinitionComponent locateSnomed(String code) throws Exception {
    if (!snomedCodes.containsKey(code))
      queryForTerm(code);
    if (!snomedCodes.containsKey(code))
      return null;
    ConceptDefinitionComponent cc = new ConceptDefinitionComponent();
    cc.setCode(code);
    cc.setDisplay(snomedCodes.get(code).display);
    return cc;
  }

  private ValidationResult verifySnomed(String code, String display) throws Exception {
    SnomedServerResponse response = null;
    if (!snomedCodes.containsKey(code))
      response = queryForTerm(code);
    if (snomedCodes.containsKey(code))
      if (display == null || snomedCodes.get(code).has(display))
        return null;
      else 
        return new ValidationResult(IssueSeverity.WARNING, "Snomed Display Name for "+code+" must be one of '"+snomedCodes.get(code).summary()+"'");
    
    if (response != null) // this is a wrong expression 
      return new ValidationResult(IssueSeverity.ERROR, "The Snomed Expression "+code+" must use the form "+response.correctExpression);
    else  if (serverOk)
      return new ValidationResult(IssueSeverity.ERROR, "Unknown Snomed Code "+code);
    else
      return new ValidationResult(IssueSeverity.WARNING, "Unknown Snomed Code "+code);
  }

  private class SnomedServerResponse  {
    String correctExpression;
    String display;
  }

  private SnomedServerResponse queryForTerm(String code) throws Exception {
    if (!triedServer || serverOk) {
      triedServer = true;
      serverOk = false;
      HttpClient httpclient = new DefaultHttpClient();
      // HttpGet httpget = new HttpGet("http://fhir.healthintersections.com.au/snomed/tool/"+URLEncoder.encode(code, "UTF-8").replace("+", "%20"));
      HttpGet httpget = new HttpGet("http://localhost:960/snomed/tool/"+URLEncoder.encode(code, "UTF-8").replace("+", "%20")); // don't like the url encoded this way
      HttpResponse response = httpclient.execute(httpget);
      HttpEntity entity = response.getEntity();
      InputStream instream = entity.getContent();
      try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xdoc = builder.parse(instream);
        serverOk = true;
        // we always get back a version, and a type. What we do depends on the type 
        String t = xdoc.getDocumentElement().getAttribute("type");
        if (t.equals("error")) 
          throw new Exception(xdoc.getDocumentElement().getAttribute("message"));
        if (t.equals("description"))
          throw new Exception("The Snomed code (\""+code+"\") is a description id not a concept id which is not valid");
        if (t.equals("concept")) {
          Concept c = new Concept();
          c.display = xdoc.getDocumentElement().getAttribute("display");
          Element child = XMLUtil.getFirstChild(xdoc.getDocumentElement());
          while (child != null) {
            c.displays.add(child.getAttribute("value"));
            child = XMLUtil.getNextSibling(child);
          }
          snomedCodes.put(code, c);
          return null;
        }
        if (t.equals("expression")) {
          SnomedServerResponse resp = new SnomedServerResponse();
          resp.correctExpression = xdoc.getDocumentElement().getAttribute("expressionMinimal");
          resp.display = xdoc.getDocumentElement().getAttribute("display");
          if (!snomedCodes.containsKey(resp.correctExpression)) {
            Concept c = new Concept();
            c.display = resp.display;
            snomedCodes.put(resp.correctExpression, c);
          }
          return resp;
        }
        throw new Exception("Unrecognised response from server");
      } finally {
        instream.close();
      }
    } else
      return null;
  }

  private ConceptDefinitionComponent locateLoinc(String code) throws Exception {
    if (!loincCodes.containsKey(code))
      return null;
    ConceptDefinitionComponent cc = new ConceptDefinitionComponent();
    cc.setCode(code);
    String s = loincCodes.get(code).display;
    cc.setDisplay(s);
    return cc;
  }

  private ValidationResult verifyLoinc(String code, String display) throws Exception {
    if (!loincCodes.containsKey(code))
      return new ValidationResult(IssueSeverity.ERROR, "Unknown Loinc Code "+code);
    if (display == null)
      return null;
    if (!loincCodes.get(code).has(display))
      return new ValidationResult(IssueSeverity.WARNING, "Loinc Display Name for "+code+" must be one of '"+loincCodes.get(code).summary()+"'");
    return null;
  }

  @Override
  public ValidationResult validateCode(String system, String code, String display) {
    try {
      if (system.equals("http://snomed.info/sct"))
        return verifySnomed(code, display);
      if (system.equals("http://loinc.org"))
        return verifyLoinc(code, display);
      if (system.startsWith("http://example.org"))
        return null;
    } catch (Exception e) {
      return new ValidationResult(IssueSeverity.ERROR, "Error validating code \""+code+"\" in system \""+system+"\": "+e.getMessage());
    }
    return new ValidationResult(IssueSeverity.WARNING, "Unknown code system "+system);
  }

  @Override
  public boolean supportsSystem(String system) {
    return "http://snomed.info/sct".equals(system) || "http://loinc.org".equals(system) ;
  }

  public void loadSnomed(String filename) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document xdoc = builder.parse(new CSFileInputStream(filename));
    Element code = XMLUtil.getFirstChild(xdoc.getDocumentElement());
    while (code != null) {
      Concept c = new Concept();
      c.display = code.getAttribute("display");
      Element child = XMLUtil.getFirstChild(code);
      while (child != null) {
        c.displays.add(child.getAttribute("value"));
        child = XMLUtil.getNextSibling(child);
      }
      snomedCodes.put(code.getAttribute("id"), c);
      code = XMLUtil.getNextSibling(code);
    }
  }

  public void saveSnomed(String filename) throws Exception {
    FileOutputStream file = new FileOutputStream(filename);
    XMLWriter xml = new XMLWriter(file, "UTF-8");
    xml.setPretty(true);
    xml.start();
    xml.comment("the build tool builds these from the designated snomed server, when it can", true);
    xml.open("snomed");
    
    List<String> ids = new ArrayList<String>();
    ids.addAll(snomedCodes.keySet());
    Collections.sort(ids);
    for (String s : ids) {
      xml.attribute("id", s);
      Concept c = snomedCodes.get(s);
      xml.attribute("display", c.display);
      if (c.displays.size() == 0)
        xml.element("concept", null);
      else {
        xml.open("concept");
        for (String d : c.displays) {
          xml.attribute("value", d);
          xml.element("display", null);
        }
        xml.close("concept");
      }
    }
    xml.close("snomed");
    xml.close();
    
  }
  
  public void loadLoinc(String filename) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document xdoc = builder.parse(new CSFileInputStream(filename));
    Element code = XMLUtil.getFirstChild(xdoc.getDocumentElement());
    while (code != null) {
      Concept c = new Concept();
      c.display = code.getAttribute("long");
      if (!code.getAttribute("long").equalsIgnoreCase(code.getAttribute("short")))
        c.displays.add(code.getAttribute("short"));
      loincCodes.put(code.getAttribute("id"), c);
      code = XMLUtil.getNextSibling(code);
    }
  }

  @Override
  public List<ValueSetExpansionContainsComponent> expandVS(ConceptSetComponent inc) throws Exception {
    ValueSet vs = new ValueSet();
    vs.setCompose(new ValueSetComposeComponent());
    vs.getCompose().getInclude().add(inc);
    ByteArrayOutputStream b = new  ByteArrayOutputStream();
    JsonParser parser = new JsonParser();
    parser.setOutputStyle(OutputStyle.NORMAL);
    parser.compose(b, vs);
    String hash = Integer.toString(new String(b.toByteArray()).hashCode());
    String fn = Utilities.path(cache, hash+".json");
    if (new File(fn).exists()) {
      Resource r = parser.parse(new FileInputStream(fn));
      if (r instanceof OperationOutcome)
        throw new Exception(((OperationOutcome) r).getIssue().get(0).getDetails());
      else
        return ((ValueSet) ((Bundle)r).getEntry().get(0).getResource()).getExpansion().getContains();
    }
    vs.setIdentifier("urn:uuid:"+UUID.randomUUID().toString().toLowerCase()); // that's all we're going to set
    
        
    if (!triedServer || serverOk) {
      try {
        triedServer = true;
        serverOk = false;
        // for this, we use the FHIR client
        IFHIRClient client = new FHIRSimpleClient();
        // client.initialize("http://fhir.healthintersections.com.au/open");
        client.initialize(tsServer+"/open");
        Map<String, String> params = new HashMap<String, String>();
        params.put("_query", "expand");
        params.put("limit", "500");
        ValueSet result = client.expandValueset(vs);
        serverOk = true;
        parser.compose(new FileOutputStream(fn), result);
        return result.getExpansion().getContains();
      } catch (EFhirClientException e) {
        serverOk = true;
        parser.compose(new FileOutputStream(fn), e.getServerErrors().get(0));
        throw new Exception(e.getServerErrors().get(0).getIssue().get(0).getDetails());
      } catch (Exception e) {
        serverOk = false;
        throw e;
      }
    } else
      throw new Exception("Server is not available");
  }

  @Override
  public boolean checkVS(ConceptSetComponent inc, String system, String code) {
    try {
      OperationOutcome op = checkVSOperation(inc, system, code);
      boolean result = true;
      for (OperationOutcomeIssueComponent issue : op.getIssue())
        if (issue.getSeverity() == IssueSeverity.FATAL || issue.getSeverity() == IssueSeverity.ERROR)
          result = false;
      return result;
    } catch (Exception e) {
      return false;
    }
  }
  
  public OperationOutcome checkVSOperation(ConceptSetComponent inc, String system, String code) throws Exception {
    ValueSet vs = new ValueSet();
    vs.setCompose(new ValueSetComposeComponent());
    vs.getCompose().getInclude().add(inc);
    ByteArrayOutputStream b = new  ByteArrayOutputStream();
    JsonParser parser = new JsonParser();
    parser.setOutputStyle(OutputStyle.NORMAL);
    parser.compose(b, vs);
    String hash = Integer.toString(new String(b.toByteArray()).hashCode())+"-vs-check";
    String fn = Utilities.path(cache, hash+".json");
    if (new File(fn).exists()) {
      Resource r = parser.parse(new FileInputStream(fn));
      if (r instanceof OperationOutcome)
        throw new Exception(((OperationOutcome) r).getIssue().get(0).getDetails());
      else
        return ((OperationOutcome) ((Bundle) r).getEntry().get(0).getResource());
    }
    vs.setIdentifier("urn:uuid:"+UUID.randomUUID().toString().toLowerCase()); // that's all we're going to set
        
    if (!triedServer || serverOk) {
      try {
        triedServer = true;
        serverOk = false;
        // for this, we use the FHIR client
        IFHIRClient client = new FHIRSimpleClient();
        client.initialize("http://fhir.healthintersections.com.au/open");
        //client.initialize("http://localhost:961/open");
        Map<String, String> params = new HashMap<String, String>();
        params.put("_query", "validate");
        params.put("system", system);
        params.put("code", code);
        Bundle result = client.searchPost(ValueSet.class, vs, params);
        serverOk = true;
        parser.compose(new FileOutputStream(fn), result);
        return ((OperationOutcome) result.getEntry().get(0).getResource());
      } catch (EFhirClientException e) {
        serverOk = true;
        parser.compose(new FileOutputStream(fn), e.getServerErrors().get(0));
        throw new Exception(e.getServerErrors().get(0).getIssue().get(0).getDetails());
      } catch (Exception e) {
        serverOk = false;
        throw e;
      }
    } else
      throw new Exception("Server is not available");
  }

  @Override
  public boolean verifiesSystem(String system) {
    return true;
  }
}
