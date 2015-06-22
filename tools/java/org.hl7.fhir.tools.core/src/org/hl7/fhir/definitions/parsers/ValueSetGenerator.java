package org.hl7.fhir.definitions.parsers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.EventDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.instance.model.Enumerations.ConformanceResourceStatus;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetComposeComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineComponent;
import org.hl7.fhir.instance.terminologies.ValueSetUtilities;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Utilities;

public class ValueSetGenerator {

  private Definitions definitions;
  private String version;
  private Calendar genDate; 

  public ValueSetGenerator(Definitions definitions, String version, Calendar genDate) {
    super();
    this.definitions = definitions;
    this.version = version;
    this.genDate = genDate;
  }

  public void check(ValueSet vs) throws Exception {
    if (!vs.hasUrl())
      throw new Exception("Value set with no URL!");

    if (vs.getId().equals("data-types"))
      genDataTypes(vs);
    else if (vs.getId().equals("defined-types"))
      genDefinedTypes(vs);
    else if (vs.getId().equals("message-events"))
      genMessageEvents(vs);
    else if (vs.getId().equals("resource-types"))
      genResourceTypes(vs);
  }

  private void genDataTypes(ValueSet vs) throws Exception {
    vs.setDefine(new ValueSetDefineComponent());
    vs.getDefine().setSystem("http://hl7.org/fhir/data-types");
    vs.getDefine().setVersion(version);
    vs.getDefine().setCaseSensitive(true);    

    List<String> codes = new ArrayList<String>();
    for (TypeRef t : definitions.getKnownTypes())
      codes.add(t.getName());
    Collections.sort(codes);
    for (String s : codes) {
      if (!definitions.dataTypeIsSharedInfo(s)) {
        ConceptDefinitionComponent c = vs.getDefine().addConcept();
        c.setCode(s);
        c.setDisplay(s);
        if (definitions.getPrimitives().containsKey(s))
          c.setDefinition(definitions.getPrimitives().get(s).getDefinition());
        else if (definitions.getConstraints().containsKey(s))
          c.setDefinition(definitions.getConstraints().get(s).getDefinition());
        else if (definitions.hasElementDefn(s))
          c.setDefinition(definitions.getElementDefn(s).getDefinition());
        else 
          c.setDefinition("...to do...");
      }
    }
  }

  private void genResourceTypes(ValueSet vs) {
    vs.setDefine(new ValueSetDefineComponent());
    vs.getDefine().setSystem("http://hl7.org/fhir/resource-types");
    vs.getDefine().setVersion(version);
    vs.getDefine().setCaseSensitive(true);    
    List<String> codes = new ArrayList<String>();
    codes.addAll(definitions.getKnownResources().keySet());
    Collections.sort(codes);
    for (String s : codes) {
      DefinedCode rd = definitions.getKnownResources().get(s);
      ConceptDefinitionComponent c = vs.getDefine().addConcept();
      c.setCode(rd.getCode());
      c.setDisplay(rd.getCode());
      c.setDefinition(rd.getDefinition());
    }

  }

  private void genDefinedTypes(ValueSet vs) throws Exception {
    ValueSetComposeComponent compose = new ValueSetComposeComponent(); 
    vs.setCompose(compose);
    compose.addInclude().setSystem("http://hl7.org/fhir/data-types");
    compose.addInclude().setSystem("http://hl7.org/fhir/resource-types");
  }

  private void genMessageEvents(ValueSet vs) {
    vs.setDefine(new ValueSetDefineComponent());
    vs.getDefine().setSystem("http://hl7.org/fhir/message-events");
    vs.getDefine().setVersion(version);
    vs.getDefine().setCaseSensitive(true);    
    List<String> codes = new ArrayList<String>();
    codes.addAll(definitions.getEvents().keySet());
    Collections.sort(codes);
    for (String s : codes) {
      ConceptDefinitionComponent c = vs.getDefine().addConcept();
      EventDefn e = definitions.getEvents().get(s);
      c.setCode(s);
      c.setDisplay(e.getCode());
      c.setDefinition(e.getDefinition());
    }
  }

  public void updateHeader(BindingSpecification bs, ValueSet vs) throws Exception {
    ValueSetUtilities.checkShareable(vs);
    if (!vs.hasId())
      throw new Exception("no id");
    if (!vs.hasUrl())
      throw new Exception("no url");
    if (!vs.hasVersion())
      vs.setVersion(version);
    if (!vs.hasExperimental())
      vs.setExperimental(false);
    if (!vs.hasName())
      vs.setName(bs.getName());
    if (!vs.hasPublisher())
      vs.setPublisher("HL7 (FHIR Project)");
    if (!vs.hasContact()) {
      vs.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, Utilities.noString(bs.getWebSite()) ? "http://hl7.org/fhir" : bs.getWebSite()));
      vs.getContact().get(0).getTelecom().add(Factory.newContactPoint(ContactPointSystem.EMAIL, Utilities.noString(bs.getEmail()) ? "fhir@lists.hl7.org" : bs.getEmail()));
    }
    if (!vs.hasDescription())
      vs.setDescription(Utilities.noString(bs.getDescription()) ? bs.getDefinition() : bs.getDefinition() + "\r\n\r\n" + bs.getDescription());
    if (!vs.hasCopyright())
      vs.setCopyright(bs.getCopyright());

    if (!vs.hasStatus())
      vs.setStatus(bs.getStatus() != null ? bs.getStatus() : ConformanceResourceStatus.DRAFT); // until we publish DSTU, then .review
    if (!vs.hasDate())
      vs.setDate(genDate.getTime());
    if (!Utilities.noString(bs.getV2Map()))
      vs.setUserData("v2map", bs.getV2Map());
    if (!Utilities.noString(bs.getV3Map()))
      vs.setUserData("v3map", checkV3Mapping(bs.getV3Map()));
  }
  
  private String checkV3Mapping(String value) {
    if (value.startsWith("http://hl7.org/fhir/v3/vs/"))
      return value.substring("http://hl7.org/fhir/v3/vs/".length());
    else
      return value;
  }

  
}
