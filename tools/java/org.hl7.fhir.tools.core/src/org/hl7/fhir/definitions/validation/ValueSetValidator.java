package org.hl7.fhir.definitions.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.TerminologyServiceException;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.CodeSystem.CodeSystemContentMode;
import org.hl7.fhir.r5.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.r5.model.MetadataResource;
import org.hl7.fhir.r5.model.Resource;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.model.ValueSet.ConceptReferenceComponent;
import org.hl7.fhir.r5.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.r5.terminologies.CodeSystemUtilities;
import org.hl7.fhir.r5.terminologies.TerminologyServiceOptions;
import org.hl7.fhir.r5.utils.ToolingExtensions;
import org.hl7.fhir.r5.validation.BaseValidator;
import org.hl7.fhir.tools.publisher.BuildWorkerContext;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueType;

public class ValueSetValidator extends BaseValidator {

  public class VSDuplicateList {
    private ValueSet vs;
    private String id;
    private String url;
    private Set<String> name = new HashSet<String>();
    private Set<String> description = new HashSet<String>();
    
    public VSDuplicateList(ValueSet vs) {
      super();
      this.vs = vs;
      id = vs.getId();
      url = vs.getUrl();
      for (String w : stripPunctuation(splitByCamelCase(vs.getName()), true).split(" ")) {
        String wl = w.toLowerCase();
        if (!Utilities.noString(w) && !grammarWord(wl) && !nullVSWord(wl)) {
          String wp = Utilities.pluralizeMe(wl);
          if (!name.contains(wp))
            name.add(wp);
        }
      }
      for (String w : stripPunctuation(splitByCamelCase(vs.getDescription()), true).split(" ")) {
        String wl = w.toLowerCase();
        if (!Utilities.noString(w) && !grammarWord(wl) && !nullVSWord(wl)) {
          String wp = Utilities.pluralizeMe(wl);
          if (!description.contains(wp))
            description.add(wp);
        }
      }
    }
  }

  private BuildWorkerContext context;
  private List<String> fixups;
  private Set<String> handled = new HashSet<String>();
  private List<VSDuplicateList> duplicateList = new ArrayList<ValueSetValidator.VSDuplicateList>();
  private Map<String, MetadataResource> oids = new HashMap<String, MetadataResource>();
  private Set<String> styleExemptions;
  private Set<String> valueSets = new HashSet<String>();
  private Set<String> codeSystems = new HashSet<String>();

  public ValueSetValidator(BuildWorkerContext context, List<String> fixups, Set<String> styleExemptions) {
    this.context = context;
    this.fixups = fixups;
    this.styleExemptions = styleExemptions;
    codeSystems.add("http://snomed.info/sct");
    codeSystems.add("http://www.nlm.nih.gov/research/umls/rxnorm");
    codeSystems.add("http://loinc.org");
    codeSystems.add("http://unitsofmeasure.org");
    codeSystems.add("http://ncimeta.nci.nih.gov");
    codeSystems.add("http://www.ama-assn.org/go/cpt");
    codeSystems.add("http://hl7.org/fhir/ndfrt");
    codeSystems.add("http://fdasis.nlm.nih.gov");
    codeSystems.add("http://hl7.org/fhir/sid/ndc");
    codeSystems.add("http://www2a.cdc.gov/vaccines/");
    codeSystems.add("iis/iisstandards/vaccines.asp?rpt=cvx");
    codeSystems.add("urn:iso:std:iso:3166");
    codeSystems.add("http://www.nubc.org/patient-discharge");
    codeSystems.add("http://www.radlex.org");
    codeSystems.add("http://hl7.org/fhir/sid/icd-10");
    codeSystems.add("http://hl7.org/fhir/sid/icpc2");
    codeSystems.add("http://www.icd10data.com/icd10pcs");
    codeSystems.add("http://hl7.org/fhir/sid/icd-9");
    codeSystems.add("http://terminology.hl7.org/CodeSystem/v2-[X](/v)");
    codeSystems.add("http://terminology.hl7.org/CodeSystem/v3-[X]");
    codeSystems.add("http://www.whocc.no/atc");
    codeSystems.add("urn:ietf:bcp:47");
    codeSystems.add("urn:ietf:bcp:13");
    codeSystems.add("urn:ietf:rfc:3986");
    codeSystems.add("urn:iso:std:iso:11073:10101");
    codeSystems.add("http://www.genenames.org");
    codeSystems.add("http://www.ensembl.org");
    codeSystems.add("http://www.ncbi.nlm.nih.gov/nuccore");
    codeSystems.add("http://www.ncbi.nlm.nih.gov/clinvar");
    codeSystems.add("http://sequenceontology.org");
    codeSystems.add("http://varnomen.hgvs.org/");
    codeSystems.add("http://www.ncbi.nlm.nih.gov/projects/SNP");
    codeSystems.add("http://cancer.sanger.ac.uk/");
    codeSystems.add("cancergenome/projects/cosmic");
    codeSystems.add("http://www.lrg-sequence.org");
    codeSystems.add("http://www.omim.org");
    codeSystems.add("http://www.ncbi.nlm.nih.gov/pubmed");
    codeSystems.add("http://www.pharmgkb.org");
    codeSystems.add("http://clinicaltrials.gov");  }

  public boolean nullVSWord(String wp) {
    return 
        wp.equals("vs") ||
        wp.equals("valueset") ||
        wp.equals("value") ||
        wp.equals("set") ||
        wp.equals("includes") ||
        wp.equals("code") ||
        wp.equals("system") ||
        wp.equals("contents") ||
        wp.equals("definition") ||
        wp.equals("hl7") ||
        wp.equals("v2") ||
        wp.equals("v3") ||
        wp.equals("table") ||
        wp.equals("codes");
  }
 
  
  public void validate(List<ValidationMessage> errors, String nameForErrors, CodeSystem cs, boolean internal, boolean exemptFromCopyrightRule) throws FHIRException {
    if (Utilities.noString(cs.getCopyright()) && !exemptFromCopyrightRule) {
      String s = cs.getUrl();
      warning(errors, IssueType.BUSINESSRULE, getWg(cs)+":CodeSystem["+cs.getId()+"].copyright", s.startsWith("http://hl7.org") || s.startsWith("urn:iso") || s.startsWith("urn:ietf") || s.startsWith("http://need.a.uri.org")
            || s.contains("cdc.gov") || s.startsWith("urn:oid:"),
           "Value set "+nameForErrors+" ("+cs.getName()+"): A copyright statement should be present for any value set that includes non-HL7 sourced codes ("+s+")",
           "<a href=\""+cs.getUserString("path")+"\">Value set "+nameForErrors+" ("+cs.getName()+")</a>: A copyright statement should be present for any code system that defines non-HL7 sourced codes ("+s+")");
    }
    if (fixups.contains(cs.getId()))
      fixup(cs);
    
    boolean isOld = codeSystems.contains(cs.getUrl());
    if (isOld)
      System.out.println("duplicate: " +cs.getUrl());
    if (rule(errors, IssueType.BUSINESSRULE, getWg(cs)+":CodeSystem["+cs.getId()+"].codeSystem", !isOld, "Duplicate Code System definition for "+cs.getUrl()))
      codeSystems.add(cs.getUrl());
    
    String oid = getOid(cs);
    if (oid != null) {
      if (!oids.containsKey(oid)) {
        oids.put(oid, cs);
      } else 
        rule(errors, IssueType.DUPLICATE, getWg(cs)+":CodeSystem["+cs.getId()+"]", oid.endsWith(".0")|| oids.get(oid).getUrl().equals(cs.getUrl()), "Duplicate OID for "+oid+" on "+oids.get(oid).getUrl()+" and "+cs.getUrl());  
    } 
    rule(errors, IssueType.BUSINESSRULE, getWg(cs)+":CodeSystem["+cs.getId()+"].codeSystem", cs.getUrl().startsWith("http://") || 
        cs.getUrl().startsWith("urn:") , "Unacceptable code system url "+cs.getUrl());
    
    Set<String> codes = new HashSet<String>();
    if (!cs.hasId())
      throw new Error("Code system without id: "+cs.getUrl());
      
    if (rule(errors, IssueType.BUSINESSRULE, getWg(cs)+":CodeSystem["+cs.getId()+"].codeSystem", cs.hasUrl(), "A cod esystem must have a url")) {
      if (!cs.getId().startsWith("v2-")) 
        rule(errors, IssueType.BUSINESSRULE, getWg(cs)+":CodeSystem["+cs.getId()+"].codeSystem", cs.hasCaseSensitiveElement() && cs.getCaseSensitive(), 
          "Value set "+nameForErrors+" ("+cs.getName()+"): All value sets that define codes must mark them as case sensitive",
          "<a href=\""+cs.getUserString("path")+"\">Value set "+nameForErrors+" ("+cs.getName()+")</a>: All value sets that define codes must mark them as case sensitive");
      checkCodeCaseDuplicates(errors, nameForErrors, cs, codes, cs.getConcept());
      if (!cs.getUrl().startsWith("http://terminology.hl7.org/CodeSystem/v2-") && 
          !cs.getUrl().startsWith("urn:uuid:") && 
          !cs.getUrl().startsWith("http://terminology.hl7.org/CodeSystem/v3-") && 
          !exemptFromCodeRules(cs.getUrl())) {
        checkCodesForDisplayAndDefinition(errors, getWg(cs)+":CodeSystem["+cs.getId()+"].define", cs.getConcept(), cs, nameForErrors);
        checkCodesForSpaces(errors, getWg(cs)+":CodeSystem["+cs.getId()+"].define", cs, cs.getConcept());
        if (!exemptFromStyleChecking(cs.getUrl())) {
          checkDisplayIsTitleCase(errors, getWg(cs)+":CodeSystem["+cs.getId()+"].define", cs, cs.getConcept());
          checkCodeIslowerCaseDash(errors, getWg(cs)+":CodeSystem["+cs.getId()+"].define", cs, cs.getConcept());
        }
      }
    }
  }
  
  public void validate(List<ValidationMessage> errors, String nameForErrors, ValueSet vs, boolean internal, boolean exemptFromCopyrightRule) throws FHIRException {
    int o_warnings = 0;
    for (ValidationMessage em : errors) {
      if (em.getLevel() == IssueSeverity.WARNING)
        o_warnings++;
    }
    if (!handled.contains(vs.getId())) {
      handled.add(vs.getId());
      duplicateList.add(new VSDuplicateList(vs));
    }
    String oid = getOid(vs);
    if (oid != null) {
      if (!oids.containsKey(oid)) {
        oids.put(oid, vs);
      } else 
        rule(errors, IssueType.DUPLICATE, getWg(vs)+":ValueSet["+vs.getId()+"]", oid.endsWith(".0")|| oids.get(oid).getUrl().equals(vs.getUrl()), "Duplicate OID for "+oid+" on "+oids.get(oid).getUrl()+" and "+vs.getUrl());  
    }
    
    rule(errors, IssueType.BUSINESSRULE, getWg(vs)+":ValueSet["+vs.getId()+"]", vs.hasDescription(), "Value Sets in the build must have a description");
    
    if (Utilities.noString(vs.getCopyright()) && !exemptFromCopyrightRule) {
      Set<String> sources = getListOfSources(vs);
      for (String s : sources) {
        rule(errors, IssueType.BUSINESSRULE, getWg(vs)+":ValueSet["+vs.getId()+"].copyright", !s.equals("http://snomed.info/sct") && !s.equals("http://loinc.org"), 
           "Value set "+nameForErrors+" ("+vs.getName()+"): A copyright statement is required for any value set that includes Snomed or Loinc codes",
           "<a href=\""+vs.getUserString("path")+"\">Value set "+nameForErrors+" ("+vs.getName()+")</a>: A copyright statement is required for any value set that includes Snomed or Loinc codes");
        warning(errors, IssueType.BUSINESSRULE, getWg(vs)+":ValueSet["+vs.getId()+"].copyright", s.startsWith("http://hl7.org") || s.startsWith("urn:iso") || s.startsWith("urn:ietf") || s.startsWith("http://need.a.uri.org")
            || s.contains("cdc.gov") || s.startsWith("urn:oid:"),
           "Value set "+nameForErrors+" ("+vs.getName()+"): A copyright statement should be present for any value set that includes non-HL7 sourced codes ("+s+")",
           "<a href=\""+vs.getUserString("path")+"\">Value set "+nameForErrors+" ("+vs.getName()+")</a>: A copyright statement should be present for any value set that includes non-HL7 sourced codes ("+s+")");
      }
    }
    
    if (vs.hasCompose()) {
      if (!context.hasResource(CodeSystem.class, "http://terminology.hl7.org/CodeSystem/data-absent-reason") && !vs.getUrl().contains("v3")&& !vs.getUrl().contains("v2"))
        throw new Error("d-a-r not found");
      
      int i = 0;
      for (ConceptSetComponent inc : vs.getCompose().getInclude()) {
        i++;
        if (inc.hasSystem() && !context.hasResource(CodeSystem.class, inc.getSystem()) && !isContainedSystem(vs, inc.getSystem()))
          rule(errors, IssueType.BUSINESSRULE, getWg(vs)+":ValueSet["+vs.getId()+"].compose.include["+Integer.toString(i)+"]", isKnownCodeSystem(inc.getSystem()), "The system '"+inc.getSystem()+"' is not valid");
        
        if (inc.hasSystem() && canValidate(inc.getSystem())) {
          for (ConceptReferenceComponent cc : inc.getConcept()) {
            if (inc.getSystem().equals("http://dicom.nema.org/resources/ontology/DCM"))
              warning(errors, IssueType.BUSINESSRULE, getWg(vs)+":ValueSet["+vs.getId()+"].compose.include["+Integer.toString(i)+"]", isValidCode(cc.getCode(), inc.getSystem()), 
                  "The code '"+cc.getCode()+"' is not valid in the system "+inc.getSystem()+" (1)",
                  "<a href=\""+vs.getUserString("path")+"\">Value set "+nameForErrors+" ("+vs.getName()+")</a>: The code '"+cc.getCode()+"' is not valid in the system "+inc.getSystem()+" (1a)");             
            else if (!isValidCode(cc.getCode(), inc.getSystem()))
              rule(errors, IssueType.BUSINESSRULE, getWg(vs)+":ValueSet["+vs.getId()+"].compose.include["+Integer.toString(i)+"]", false, 
                "The code '"+cc.getCode()+"' is not valid in the system "+inc.getSystem()+" (2)");
            
          }
        }
      }
    }
    int warnings = 0;
    for (ValidationMessage em : errors) {
      if (em.getLevel() == IssueSeverity.WARNING)
        warnings++;
    }
    vs.setUserData("warnings", o_warnings - warnings);
  }

  private boolean isContainedSystem(ValueSet vs, String system) {
    if (system.startsWith("#"))
      system = system.substring(1);
    else
      return false;
    
    for (Resource r : vs.getContained()) {
      if (r instanceof CodeSystem) {
        CodeSystem cs = (CodeSystem) r;
        if (system.equals(cs.getId()))
          return true;
      }
    }
    return false;
  }

  private String getOid(ValueSet vs) {
    for (org.hl7.fhir.r5.model.Identifier id : vs.getIdentifier()) {
      if (id.getSystem().equals("urn:ietf:rfc:3986") && id.getValue().startsWith("urn:oid:")) {
        return id.getValue().substring(8);
      }
    }
    return null;
  }

  private String getOid(CodeSystem cs) {
    if (cs.hasIdentifier() && cs.getIdentifierFirstRep().getSystem().equals("urn:ietf:rfc:3986") && cs.getIdentifierFirstRep().getValue().startsWith("urn:oid:")) {
      return cs.getIdentifierFirstRep().getValue().substring(8);
    }
    return null;
  }

  private boolean exemptFromStyleChecking(String system) {
    return styleExemptions.contains(system);
  }

  private boolean exemptFromCodeRules(String system) {
    if (system.equals("http://www.abs.gov.au/ausstats/abs@.nsf/mf/1220.0"))
      return true;
    if (system.equals("http://dicom.nema.org/resources/ontology/DCM"))
      return true;
    return false;
    
  }

  private void checkCodeIslowerCaseDash(List<ValidationMessage> errors, String nameForErrors, CodeSystem cs, List<ConceptDefinitionComponent> concept) {
    for (ConceptDefinitionComponent cc : concept) {
      if (!suppressedwarning(errors, IssueType.BUSINESSRULE, getWg(cs)+":CodeSystem["+cs.getId()+"].define", !cc.hasCode() || isLowerCaseDash(cc.getCode()), 
         "Code System "+nameForErrors+" ("+cs.getName()+"/"+cs.getUrl()+"): Defined codes must be lowercase-dash: "+cc.getCode()))
        return;
      checkDisplayIsTitleCase(errors, nameForErrors, cs, cc.getConcept());  
    }
  }

  private boolean isLowerCaseDash(String code) {
    for (char c : code.toCharArray()) {
      if (Character.isAlphabetic(c) && Character.isUpperCase(c))
        return false;
      if (c != '-' && c != '.' && !Character.isAlphabetic(c) && !Character.isDigit(c))
        return false;
    }
    return true;
  }

  private void checkDisplayIsTitleCase(List<ValidationMessage> errors, String nameForErrors, CodeSystem cs, List<ConceptDefinitionComponent> concept) {
    for (ConceptDefinitionComponent cc : concept) {
      if (!suppressedwarning(errors, IssueType.BUSINESSRULE, getWg(cs)+":CodeSystem["+cs.getId()+"].define", !cc.hasDisplay() || isTitleCase(cc.getDisplay()), 
         "Value set "+nameForErrors+" ("+cs.getName()+"/"+cs.getUrl()+"): Display Names must be TitleCase: "+cc.getDisplay()))
        return;
      checkDisplayIsTitleCase(errors, nameForErrors, cs, cc.getConcept());  
    }
  }

  private boolean isTitleCase(String code) {
    char c = code.charAt(0);
    return !Character.isAlphabetic(c) || Character.isUpperCase(c);
  }

  private boolean isKnownCodeSystem(String system) {
    if (Utilities.existsInList(system, "http://cancer.sanger.ac.uk/cancergenome/projects/cosmic",
        "http://clinicaltrials.gov",  "http://fdasis.nlm.nih.gov",  "http://hl7.org/fhir/ndfrt", 
        "http://hl7.org/fhir/sid/icd-9",  "http://hl7.org/fhir/sid/icd-10",  "http://hl7.org/fhir/sid/icpc2", 
        "http://hl7.org/fhir/sid/ndc",  "http://loinc.org",  "https://precision.fda.gov/apps/", 
        "http://www.lrg-sequence.org",  "http://ncimeta.nci.nih.gov",  "http://www.sequenceontology.org", 
        "http://snomed.info/sct",  "http://unitsofmeasure.org",  "http://www.ama-assn.org/go/cpt", 
        "http://www.ensembl.org",  "http://www.genenames.org",  "http://varnomen.hgvs.org/", 
        "http://www.icd10data.com/icd10pcs",  "http://www.ncbi.nlm.nih.gov/nuccore",  "http://www.ncbi.nlm.nih.gov/projects/SNP", 
        "http://www.ncbi.nlm.nih.gov/pubmed",  "http://www.ncbi.nlm.nih.gov/clinvar",  "http://www.nlm.nih.gov/research/umls/rxnorm", 
        "http://www.nubc.org/patient-discharge",  "http://www.omim.org",  "http://www.pharmgkb.org", 
        "http://www.radlex.org",  "http://www.whocc.no/atc",  "http://hl7.org/fhir/sid/cvx", 
        "urn:ietf:bcp:47",  "urn:ietf:bcp:13",  "urn:ietf:rfc:3986", 
        "urn:iso:std:iso:4217",  "urn:iso:std:iso:11073:10101",  "urn:iso-astm:E1762-95:2013", 
        "urn:iso:std:iso:3166",  "urn:iso:std:iso:3166:-2",  "urn:iso:std:iso:3166:-3",  "http://nucc.org/provider-taxonomy", 
        "http://example.com",  "http://example.org", "https://precision.fda.gov/files/", "http://www.ebi.ac.uk/ipd/imgt/hla", 
        "https://www.iana.org/time-zones", "https://precision.fda.gov/jobs/"))
      return true;
    
    // todo: why do these need to be listed here?
    if (system.equals("http://hl7.org/fhir/data-types") ||
        system.equals("http://hl7.org/fhir/restful-interaction") ||
        system.equals("http://dicom.nema.org/resources/ontology/DCM") ||
        system.equals("http://unstats.un.org/unsd/methods/m49/m49.htm") ||
        system.equals("http://www.cms.gov/Medicare/Coding/ICD9ProviderDiagnosticCodes/codes.html") ||
        system.equals("http://www.cms.gov/Medicare/Coding/ICD10/index.html") ||
        system.equals("http://www.iana.org/assignments/language-subtag-registry") ||
        system.equals("http://www.nucc.org") ||
        system.equals("https://www.census.gov/geo/reference/") ||
        system.equals("https://www.usps.com/") ||
        system.startsWith("http://cimi.org") ||
        system.startsWith("http://terminology.hl7.org/ValueSet/v3") ||
        system.startsWith("http://ihc.org") ||
        system.startsWith("urn:oid:")
       )
      return true;
    
    return false; // todo: change this back to false
  }

  private boolean isValidCode(String code, String system) {
    CodeSystem cs = context.fetchResource(CodeSystem.class, system);
    if (cs == null || cs.getContent() != CodeSystemContentMode.COMPLETE) 
      return context.validateCode(new TerminologyServiceOptions("en-US"), system, code, null).isOk();
    else {
      if (hasCode(code, cs.getConcept()))
        return true;
      return false;
    }
  }

  private boolean hasCode(String code, List<ConceptDefinitionComponent> list) {
    for (ConceptDefinitionComponent cc : list) {
      if (cc.getCode().equals(code))
        return true;
      if (hasCode(code, cc.getConcept()))
        return true;
    }
    return false;
  }

  private boolean canValidate(String system) {
    try {
      return context.hasResource(CodeSystem.class, system) || context.supportsSystem(system);
    } catch (TerminologyServiceException e) {
      //If there are problems accessing the terminology server, fail gracefully
      return false;
    }
  }

  private void fixup(CodeSystem cs) {
    for (ConceptDefinitionComponent cc: cs.getConcept())
      fixup(cc);
  }

  private void fixup(ConceptDefinitionComponent cc) {
    if (cc.hasDisplay() && !cc.hasDefinition())
      cc.setDefinition(cc.getDisplay());
    if (!cc.hasDisplay() && cc.hasDefinition())
      cc.setDisplay(cc.getDefinition());
    for (ConceptDefinitionComponent gc: cc.getConcept())
      fixup(gc);
  }

  private void checkCodesForSpaces(List<ValidationMessage> errors, String nameForErrors, CodeSystem cs, List<ConceptDefinitionComponent> concept) {
    for (ConceptDefinitionComponent cc : concept) {
      if (!rule(errors, IssueType.BUSINESSRULE, getWg(cs)+":CodeSystem["+cs.getId()+"].define", !cc.hasCode() || !cc.getCode().contains(" "), 
         "Value set "+nameForErrors+" ("+cs.getName()+"/"+cs.getUrl()+"): Defined codes cannot include spaces ("+cc.getCode()+")"))
        return;
      checkCodesForSpaces(errors, nameForErrors, cs, cc.getConcept());  
    }
  }

  private void checkCodesForDisplayAndDefinition(List<ValidationMessage> errors, String path, List<ConceptDefinitionComponent> concept, CodeSystem cs, String nameForErrors) {
    int i = 0;
    for (ConceptDefinitionComponent cc : concept) {
      String p = path +"["+Integer.toString(i)+"]";
      if (!suppressedwarning(errors, IssueType.BUSINESSRULE, p, !CodeSystemUtilities.isNotSelectable(cs, cc) || !cc.hasCode() || cc.hasDisplay(), "Code System '"+cs.getUrl()+"' has a code without a display ('"+cc.getCode()+"')",
        "<a href=\""+cs.getUserString("path")+"\">Value set "+nameForErrors+" ("+cs.getName()+")</a>: Code System '"+cs.getUrl()+"' has a code without a display ('"+cc.getCode()+"')"))
        return;
      if (!suppressedwarning(errors, IssueType.BUSINESSRULE, p, cc.hasDefinition() && (!cc.getDefinition().toLowerCase().equals("todo") || cc.getDefinition().toLowerCase().equals("to do")), "Code System '"+cs.getUrl()+"' has a code without a definition ('"+cc.getCode()+"')",
        "<a href=\""+cs.getUserString("path")+"\">Value set "+nameForErrors+" ("+cs.getName()+")</a>: Code System '"+cs.getUrl()+"' has a code without a definition ('"+cc.getCode()+"')"))
        return;
      checkCodesForDisplayAndDefinition(errors, p+".concept", cc.getConcept(), cs, nameForErrors);
      i++;
    }
  }

  private void checkCodeCaseDuplicates(List<ValidationMessage> errors, String nameForErrors, CodeSystem cs, Set<String> codes, List<ConceptDefinitionComponent> concepts) {
    for (ConceptDefinitionComponent c : concepts) {
      if (c.hasCode()) {
        String cc = c.getCode().toLowerCase();
        rule(errors, IssueType.BUSINESSRULE, getWg(cs)+":CodeSystem["+cs.getId()+"].define", !codes.contains(cc), 
            "Value set "+nameForErrors+" ("+cs.getName()+"): Code '"+cc+"' is defined twice, different by case - this is not allowed in a FHIR definition");
        if (c.hasConcept())
          checkCodeCaseDuplicates(errors, nameForErrors, cs, codes, c.getConcept());
      }
    }
  }

  private Set<String> getListOfSources(ValueSet vs) {
    Set<String> sources = new HashSet<String>();
    if (vs.hasCompose()) {
      for (ConceptSetComponent imp : vs.getCompose().getInclude()) 
        if (imp.hasSystem())
          sources.add(imp.getSystem());
      for (ConceptSetComponent imp : vs.getCompose().getExclude()) 
        if (imp.hasSystem())
          sources.add(imp.getSystem());
    }
    return sources;
  }

  public void checkDuplicates(List<ValidationMessage> errors) {
    for (int i = 0; i < duplicateList.size()-1; i++) {
      for (int j = i+1; j < duplicateList.size(); j++) {
        VSDuplicateList vd1 = duplicateList.get(i);
        VSDuplicateList vd2 = duplicateList.get(j);
        String committee = pickCommittee(vd1, vd2);
        if (committee != null) {
          if (rule(errors, IssueType.BUSINESSRULE, committee+":ValueSetComparison", !vd1.id.equals(vd2.id), "Duplicate Value Set ids : "+vd1.id+"("+vd1.vs.getName()+") & "+vd2.id+"("+vd2.vs.getName()+") (id)") &&
              rule(errors, IssueType.BUSINESSRULE, committee+":ValueSetComparison", !vd1.url.equals(vd2.url), "Duplicate Value Set URLs: "+vd1.id+"("+vd1.vs.getName()+") & "+vd2.id+"("+vd2.vs.getName()+") (url)")) {
            if (isInternal(vd1.url) || isInternal(vd2.url)) {
              warning(errors, IssueType.BUSINESSRULE, committee+":ValueSetComparison", areDisjoint(vd1.name, vd2.name), "Duplicate Valueset Names: "+vd1.vs.getUserString("path")+" ("+vd1.vs.getName()+") & "+vd2.vs.getUserString("path")+" ("+vd2.vs.getName()+") (name: "+vd1.name.toString()+" / "+vd2.name.toString()+"))", 
                  "Duplicate Valueset Names: <a href=\""+vd1.vs.getUserString("path")+"\">"+vd1.id+"</a> ("+vd1.vs.getName()+") &amp; <a href=\""+vd2.vs.getUserString("path")+"\">"+vd2.id+"</a> ("+vd2.vs.getName()+") (name: "+vd1.name.toString()+" / "+vd2.name.toString()+"))");
              warning(errors, IssueType.BUSINESSRULE, committee+":ValueSetComparison", areDisjoint(vd1.description, vd2.description), "Duplicate Valueset Definitions: "+vd1.vs.getUserString("path")+" ("+vd1.vs.getName()+") & "+vd2.vs.getUserString("path")+" ("+vd2.vs.getName()+") (description: "+vd1.description.toString()+" / "+vd2.description.toString()+")",
                  "Duplicate Valueset descriptions: <a href=\""+vd1.vs.getUserString("path")+"\">"+vd1.id+"</a> ("+vd1.vs.getName()+") &amp; <a href=\""+vd2.vs.getUserString("path")+"\">"+vd2.id+"</a> ("+vd2.vs.getName()+") (description: "+vd1.description.toString()+" / "+vd2.description.toString()+"))");
            }
          }
        }
      }
    }
  }

  private String pickCommittee(VSDuplicateList vd1, VSDuplicateList vd2) {
    String c1 = getWg(vd1.vs);
    String c2 = getWg(vd2.vs);
    if (c1 == null)
      return c2;
    else if (c2 == null)
      return c1;
    else if (c1.equals("fhir"))
      return c2;
    else if (c2.equals("fhir"))
      return c1;
    else
      return c1;
  }

  private String getWg(MetadataResource mr) {
   return ToolingExtensions.readStringExtension(mr, ToolingExtensions.EXT_WORKGROUP);
  }

  private boolean isInternal(String url) {
    return url.startsWith("http://hl7.org/fhir") && !url.startsWith("http://terminology.hl7.org/ValueSet/v2-") && !url.startsWith("http://terminology.hl7.org/ValueSet/v3-");
  }

  private boolean areDisjoint(Set<String> set1, Set<String> set2) {
    if (set1.isEmpty() || set2.isEmpty())
      return true;
    
    Set<String> set = new HashSet<String>();
    for (String s : set1) 
      if (!set2.contains(s))
        set.add(s);
    for (String s : set2) 
      if (!set1.contains(s))
        set.add(s);
    return !set.isEmpty();
  }

}
