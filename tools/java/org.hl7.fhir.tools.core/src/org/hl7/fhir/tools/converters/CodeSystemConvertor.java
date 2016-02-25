package org.hl7.fhir.tools.converters;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.dstu3.model.BooleanType;
import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.CodeSystem.CodeSystemContentMode;
import org.hl7.fhir.dstu3.model.CodeSystem.CodeSystemPropertyComponent;
import org.hl7.fhir.dstu3.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.dstu3.model.CodeSystem.ConceptDefinitionDesignationComponent;
import org.hl7.fhir.dstu3.model.CodeSystem.ConceptDefinitionPropertyComponent;
import org.hl7.fhir.dstu3.model.CodeSystem.PropertyType;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

public class CodeSystemConvertor {

  public void convert(ValueSet vs, String name) throws FileNotFoundException, IOException {
    if (vs.hasCodeSystem()) {
      String nname = name.replace("valueset-", "codesystem-");
      if (nname.equals(name))
        name = Utilities.changeFileExt(name, "-cs.xml");
      else
        name = nname;
      CodeSystem cs = new CodeSystem();
      cs.setId(vs.getId());
      cs.setUrl(vs.getCodeSystem().getSystem());
      if (vs.hasVersion())
        cs.setVersion(vs.getVersion());
      cs.setName(vs.getName());
      cs.setStatus(vs.getStatus());
      cs.setExperimental(vs.getExperimental());
      if (vs.hasDate())
        cs.setDate(vs.getDate());
      cs.setDescription(vs.getDescription());
      cs.setRequirements(vs.getRequirements());
      cs.setCopyright(vs.getCopyright());
      cs.setCaseSensitive(vs.getCodeSystem().getCaseSensitive());
      cs.setValueSet(vs.getUrl());
      cs.setContent(CodeSystemContentMode.COMPLETE);
      processConcepts(cs, cs.getConcept(), vs.getCodeSystem().getConcept());
      if (!new File(name).exists()) {
        new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(name), cs);
      } else {
        String existing = TextFile.fileToString(name);
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(bs, cs);
        String current = new String(bs.toByteArray()); 
        if (!existing.equals(current)) 
          new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(name), cs);
      }
    }    
  }

  private void processConcepts(CodeSystem cs, List<ConceptDefinitionComponent> dest, List<org.hl7.fhir.dstu3.model.ValueSet.ConceptDefinitionComponent> source) {
    for (org.hl7.fhir.dstu3.model.ValueSet.ConceptDefinitionComponent ccs : source) {
      ConceptDefinitionComponent ct = new ConceptDefinitionComponent();
      ct.setCode(ccs.getCode());
      ct.setDisplay(ccs.getDisplay());
      ct.setDefinition(ccs.getDefinition());
      for (org.hl7.fhir.dstu3.model.ValueSet.ConceptDefinitionDesignationComponent ds : ccs.getDesignation()) {
        ConceptDefinitionDesignationComponent dt = new ConceptDefinitionDesignationComponent();
        dt.setLanguage(ds.getLanguage());
        dt.setUse(ds.getUse());
        dt.setValue(ds.getValue());
        ct.getDesignation().add(dt);
      }
      if (ccs.getAbstract()) {
        boolean defined = false;
        for (CodeSystemPropertyComponent pd : cs.getProperty()) {
          if (pd.getCode().equals("abstract")) 
            defined = true;
        }
        if (!defined) {
          CodeSystemPropertyComponent pd = new CodeSystemPropertyComponent();
          cs.getProperty().add(pd);
          pd.setCode("abstract");
          pd.setDescription("True if an element is considered 'abstract' - that is the code is not for use as a real concept");
          pd.setType(PropertyType.BOOLEAN);
        }
        ConceptDefinitionPropertyComponent p = new ConceptDefinitionPropertyComponent();
        p.setCode("abstract");
        p.setValue(new BooleanType(true));
        ct.getProperty().add(p );  
      }
      dest.add(ct);
      processConcepts(cs, ct.getConcept(), ccs.getConcept());
    }
  }

}
