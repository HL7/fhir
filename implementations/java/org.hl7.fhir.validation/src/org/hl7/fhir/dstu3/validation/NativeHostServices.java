package org.hl7.fhir.dstu3.validation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hl7.fhir.convertors.VersionConvertorAdvisor30;
import org.hl7.fhir.convertors.VersionConvertorAdvisor40;
import org.hl7.fhir.convertors.VersionConvertor_10_30;
import org.hl7.fhir.convertors.VersionConvertor_14_30;
import org.hl7.fhir.convertors.VersionConvertor_30_40;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.dstu3.validation.ValidationEngine;

/**
 * This class wraps up the validation and conversion infrastructure
 * so it can be hosted inside a native server
 * 
 * workflow is pretty simple:
 *  - create a DelphiLibraryHost, provide with path to library and tx server to use
 *    (tx server is usually the host server)
 *  - any structure definitions, value sets, code systems changes on the server get sent to tp seeResource or dropResource
 *  - server wants to validate a resource, it calls validateResource and gets an operation outcome back
 *  - server wants to convert from R4 to something else, it calls convertResource  
 *  - server wants to convert to R4 from something else, it calls unConvertResource  
 *  
 * threading: todo: this class should be thread safe
 *  
 * note: this is a solution that uses lots of RAM...  
 */

import org.hl7.fhir.dstu3.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.dstu3.formats.JsonParser;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.OperationOutcome;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class NativeHostServices {
  public class NH_10_30_Advisor implements VersionConvertorAdvisor30 {

    @Override
    public boolean ignoreEntry(BundleEntryComponent src) {
      return false;
    }

    @Override
    public org.hl7.fhir.dstu2.model.Resource convert(Resource resource) throws FHIRException {
      return null;
    }

    @Override
    public void handleCodeSystem(CodeSystem tgtcs, ValueSet source) throws FHIRException {
      throw new FHIRException("Not handled yet");      
    }

    @Override
    public CodeSystem getCodeSystem(ValueSet src) throws FHIRException {
      throw new FHIRException("Not handled yet");      
    }

  }

  ValidationEngine validator;
  int validationCount = 0;
  int resourceCount = 0;
  int convertCount = 0;
  int unConvertCount = 0;
  int exceptionCount = 0;
  String lastException = null;
  private VersionConvertorAdvisor30 conv_10_30_advisor = new NH_10_30_Advisor();

  public NativeHostServices()  {
    super();
  } 

  public void init(String pack) throws Exception {
    validator = new ValidationEngine(pack);
  }

  public void connectToTxSvc(String txServer) throws Exception {
    validator.connectToTSServer(txServer);
  }
  
  public String status() {
    JsonObject json = new JsonObject();
    json.addProperty("custom-resource-count", resourceCount);
    validator.getContext().reportStatus(json);
    json.addProperty("validation-count", validationCount);
    json.addProperty("convert-count", convertCount);
    json.addProperty("unconvert-count", unConvertCount);
    json.addProperty("exception-count", exceptionCount);
    json.addProperty("last-exception", lastException);

    Gson gson = new GsonBuilder().create();
    return gson.toJson(json);
  }

  public void seeResource(byte[] source) throws Exception {
    try {
      Resource r = new XmlParser().parse(source);
      validator.seeResource(r);
      resourceCount++;
    } catch (Exception e) {
      exceptionCount++;
      lastException = e.getMessage();
      throw e;
    }
  }

  public void dropResource(String type, String id, String url, String bver) throws Exception  {
    try {
      validator.dropResource(type, id, url, bver);
      resourceCount--;
    } catch (Exception e) {
      exceptionCount++;
      lastException = e.getMessage();
      throw e;
    }
  }

  public byte[] validateResource(String location, byte[] source, String cntType) throws Exception {
    OperationOutcome oo = validator.validate(location, source, FhirFormat.valueOf(cntType), null);
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    new XmlParser().compose(bs, oo);
    validationCount++;
    return bs.toByteArray();
  }

  public byte[] convertResource(byte[] r, String fmt, String version) throws FHIRException, IOException  {
    if ("3.2".equals(version) || "3.2.0".equals(version) || "r4".equals(version)) {
      org.hl7.fhir.r4.formats.ParserBase p4 = org.hl7.fhir.r4.formats.FormatUtilities.makeParser(fmt);
      org.hl7.fhir.r4.model.Resource res4 = p4.parse(r);
      Resource res3 = VersionConvertor_30_40.convertResource(res4);
      org.hl7.fhir.dstu3.formats.ParserBase p3 = org.hl7.fhir.dstu3.formats.FormatUtilities.makeParser(fmt);
      convertCount++;
      return p3.composeBytes(res3);
    } else if ("1.0".equals(version) || "1.0.2".equals(version) || "r2".equals(version)) {
      org.hl7.fhir.dstu2.formats.ParserBase p2 = org.hl7.fhir.dstu2.formats.FormatUtilities.makeParser(fmt);
      org.hl7.fhir.dstu2.model.Resource res2 = p2.parse(r);
      VersionConvertor_10_30 conv = new VersionConvertor_10_30(conv_10_30_advisor );
      Resource res3 = conv.convertResource(res2);
      org.hl7.fhir.dstu3.formats.ParserBase p3 = org.hl7.fhir.dstu3.formats.FormatUtilities.makeParser(fmt);
      convertCount++;
      return p3.composeBytes(res3);
    } else if ("1.4".equals(version) || "1.4.0".equals(version)) {
      org.hl7.fhir.dstu2016may.formats.ParserBase p2 = org.hl7.fhir.dstu2016may.formats.FormatUtilities.makeParser(fmt);
      org.hl7.fhir.dstu2016may.model.Resource res2 = p2.parse(r);
      Resource res3 = VersionConvertor_14_30.convertResource(res2);
      org.hl7.fhir.dstu3.formats.ParserBase p3 = org.hl7.fhir.dstu3.formats.FormatUtilities.makeParser(fmt);
      convertCount++;
      return p3.composeBytes(res3);
    } else
      throw new FHIRException("Unsupported version "+version);
  }

  public byte[] unConvertResource(byte[] r, String fmt, String version) throws FHIRException, IOException  {
    if ("3.2".equals(version) || "3.2.0".equals(version) || "r4".equals(version)) {
      org.hl7.fhir.dstu3.formats.ParserBase p3 = org.hl7.fhir.dstu3.formats.FormatUtilities.makeParser(fmt);
      org.hl7.fhir.dstu3.model.Resource res3 = p3.parse(r);
      org.hl7.fhir.r4.model.Resource res4 = VersionConvertor_30_40.convertResource(res3);
      org.hl7.fhir.r4.formats.ParserBase p4 = org.hl7.fhir.r4.formats.FormatUtilities.makeParser(fmt);
      unConvertCount++;
      return p4.composeBytes(res4);
    } else if ("1.0".equals(version) || "1.0.2".equals(version) || "r2".equals(version)) {
      org.hl7.fhir.dstu3.formats.ParserBase p3 = org.hl7.fhir.dstu3.formats.FormatUtilities.makeParser(fmt);
      org.hl7.fhir.dstu3.model.Resource res3 = p3.parse(r);
      VersionConvertor_10_30 conv = new VersionConvertor_10_30(conv_10_30_advisor );
      org.hl7.fhir.dstu2.model.Resource res2 = conv.convertResource(res3);
      org.hl7.fhir.dstu2.formats.ParserBase p2 = org.hl7.fhir.dstu2.formats.FormatUtilities.makeParser(fmt);
      unConvertCount++;
      return p2.composeBytes(res2);
    } else if ("1.4".equals(version) || "1.4.0".equals(version)) {
      org.hl7.fhir.dstu3.formats.ParserBase p3 = org.hl7.fhir.dstu3.formats.FormatUtilities.makeParser(fmt);
      org.hl7.fhir.dstu3.model.Resource res3 = p3.parse(r);
      org.hl7.fhir.dstu2016may.model.Resource res2 = VersionConvertor_14_30.convertResource(res3);
      org.hl7.fhir.dstu2016may.formats.ParserBase p2 = org.hl7.fhir.dstu2016may.formats.FormatUtilities.makeParser(fmt);
      unConvertCount++;
      return p2.composeBytes(res2);
    } else
      throw new FHIRException("Unsupported version "+version);
  }

  
}   
