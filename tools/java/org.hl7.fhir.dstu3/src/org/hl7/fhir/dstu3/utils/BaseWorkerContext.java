package org.hl7.fhir.dstu3.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.dstu3.formats.JsonParser;
import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.model.BooleanType;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.ConceptMap;
import org.hl7.fhir.dstu3.model.Conformance;
import org.hl7.fhir.dstu3.model.ExpansionProfile;
import org.hl7.fhir.dstu3.model.Extension;
import org.hl7.fhir.dstu3.model.OperationOutcome;
import org.hl7.fhir.dstu3.model.Parameters;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.dstu3.model.StructureMap;
import org.hl7.fhir.dstu3.model.Type;
import org.hl7.fhir.dstu3.model.UriType;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.dstu3.model.Parameters.ParametersParameterComponent;
import org.hl7.fhir.dstu3.model.PrimitiveType;
import org.hl7.fhir.dstu3.model.CodeSystem.CodeSystemHierarchyMeaning;
import org.hl7.fhir.dstu3.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.dstu3.model.CodeSystem.ConceptDefinitionDesignationComponent;
import org.hl7.fhir.dstu3.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.dstu3.model.ValueSet.ConceptSetFilterComponent;
import org.hl7.fhir.dstu3.model.ValueSet.ValueSetComposeComponent;
import org.hl7.fhir.dstu3.model.ValueSet.ValueSetExpansionComponent;
import org.hl7.fhir.dstu3.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.dstu3.terminologies.ValueSetExpanderFactory;
import org.hl7.fhir.dstu3.terminologies.ValueSetExpansionCache;
import org.hl7.fhir.dstu3.terminologies.ValueSetExpander.ETooCostly;
import org.hl7.fhir.dstu3.terminologies.ValueSetExpander.ExpansionErrorClass;
import org.hl7.fhir.dstu3.terminologies.ValueSetExpander.ValueSetExpansionOutcome;
import org.hl7.fhir.dstu3.utils.IWorkerContext.ILoggingService;
import org.hl7.fhir.dstu3.utils.client.FHIRToolingClient;
import org.hl7.fhir.exceptions.NoTerminologyServiceException;
import org.hl7.fhir.exceptions.TerminologyServiceException;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

import com.google.gson.JsonSyntaxException;

public abstract class BaseWorkerContext implements IWorkerContext {

  // all maps are to the full URI
  protected Map<String, CodeSystem> codeSystems = new HashMap<String, CodeSystem>();
  protected Set<String> nonSupportedCodeSystems = new HashSet<String>();
  protected Map<String, ValueSet> valueSets = new HashMap<String, ValueSet>();
  protected Map<String, ConceptMap> maps = new HashMap<String, ConceptMap>();
  protected Map<String, StructureMap> transforms = new HashMap<String, StructureMap>();
  
  protected ValueSetExpanderFactory expansionCache = new ValueSetExpansionCache(this);
  protected boolean cacheValidation; // if true, do an expansion and cache the expansion
  private Set<String> failed = new HashSet<String>(); // value sets for which we don't try to do expansion, since the first attempt to get a comprehensive expansion was not successful
  protected Map<String, Map<String, ValidationResult>> validationCache = new HashMap<String, Map<String,ValidationResult>>();
  protected String tsServer;
  protected String validationCachePath;

  // private ValueSetExpansionCache expansionCache; //   

  protected FHIRToolingClient txServer;
  private Bundle bndCodeSystems;
  private boolean canRunWithoutTerminology;
  protected boolean noTerminologyServer;
  protected String cache;
  private int expandCodesLimit = 10000;
  private ILoggingService logger;
  private ExpansionProfile expProfile;

  @Override
  public CodeSystem fetchCodeSystem(String system) {
    return codeSystems.get(system);
  } 

  @Override
  public boolean supportsSystem(String system) throws TerminologyServiceException {
    if (codeSystems.containsKey(system))
      return true;
    else if (nonSupportedCodeSystems.contains(system))
      return false;
    else if (system.startsWith("http://example.org") || system.startsWith("http://acme.com") || system.startsWith("http://hl7.org/fhir/valueset-") || system.startsWith("urn:oid:"))
      return false;
    else {
      if (noTerminologyServer)
        return false;
      if (bndCodeSystems == null) {
        try {
          log("Terminology server: Check for supported code systems for "+system);
          bndCodeSystems = txServer.fetchFeed(txServer.getAddress()+"/CodeSystem?content=not-present&_summary=true&_count=1000");
        } catch (Exception e) {
          if (canRunWithoutTerminology) {
            noTerminologyServer = true;
            System.out.println("==============!! Running without terminology server !!==============");
            return false;
          } else
            throw new TerminologyServiceException(e);
        }
      }
      if (bndCodeSystems != null) {
        for (BundleEntryComponent be : bndCodeSystems.getEntry()) {
          CodeSystem cs = (CodeSystem) be.getResource();
          if (!codeSystems.containsKey(cs.getUrl())) {
            codeSystems.put(cs.getUrl(), null);
          }
        }
    }
      if (codeSystems.containsKey(system))
        return true;
    }
    nonSupportedCodeSystems.add(system);
    return false;
  }

  private void log(String message) {
    if (logger != null)
      logger.logMessage(message);
  }

  @Override
  public ValueSetExpansionOutcome expandVS(ValueSet vs, boolean cacheOk, boolean heirarchical) {
    try {
      if (vs.hasExpansion()) {
        return new ValueSetExpansionOutcome(vs.copy());
      }
      String cacheFn = null;
      if (cache != null) {
        cacheFn = Utilities.path(cache, determineCacheId(vs, heirarchical)+".json");
        if (new File(cacheFn).exists())
          return loadFromCache(vs.copy(), cacheFn);
      }
      if (cacheOk && vs.hasUrl()) {
        if (expProfile == null)
          throw new Exception("No ExpansionProfile provided");
        ValueSetExpansionOutcome vse = expansionCache.getExpander().expand(vs, expProfile.setExcludeNested(!heirarchical));
        if (vse.getValueset() != null) {
          if (cache != null) {
            FileOutputStream s = new FileOutputStream(cacheFn);
            newJsonParser().compose(new FileOutputStream(cacheFn), vse.getValueset());
            s.close();
          }
        }
        return vse;
      } else {
        ValueSetExpansionOutcome res = expandOnServer(vs, cacheFn);
        if (cacheFn != null) {
          if (res.getValueset() != null) {
            saveToCache(vs, cacheFn);
          } else { 
            OperationOutcome oo = new OperationOutcome();
            oo.addIssue().getDetails().setText(res.getError());
            saveToCache(oo, cacheFn);
          }
        }
        return res;
      }
    } catch (NoTerminologyServiceException e) {
      return new ValueSetExpansionOutcome(e.getMessage() == null ? e.getClass().getName() : e.getMessage(), ExpansionErrorClass.NOSERVICE);
    } catch (Exception e) {
      return new ValueSetExpansionOutcome(e.getMessage() == null ? e.getClass().getName() : e.getMessage(), ExpansionErrorClass.UNKNOWN);
    }
  }

  private ValueSetExpansionOutcome loadFromCache(ValueSet vs, String cacheFn) throws FileNotFoundException, Exception {
    JsonParser parser = new JsonParser();
    Resource r = parser.parse(new FileInputStream(cacheFn));
    if (r instanceof OperationOutcome)
      return new ValueSetExpansionOutcome(((OperationOutcome) r).getIssue().get(0).getDetails().getText(), ExpansionErrorClass.NOSERVICE);
    else {
      vs.setExpansion(((ValueSet) r).getExpansion()); // because what is cached might be from a different value set
      return new ValueSetExpansionOutcome(vs);
    }
  }

  private void saveToCache(Resource res, String cacheFn) throws FileNotFoundException, Exception {
    JsonParser parser = new JsonParser();
    parser.compose(new FileOutputStream(cacheFn), res);
  }

  private String determineCacheId(ValueSet vs, boolean heirarchical) throws Exception {
    // just the content logical definition is hashed
    ValueSet vsid = new ValueSet();
    vsid.setCompose(vs.getCompose());
    vsid.setLockedDate(vs.getLockedDate());
    JsonParser parser = new JsonParser();
    parser.setOutputStyle(OutputStyle.NORMAL);
    ByteArrayOutputStream b = new  ByteArrayOutputStream();
    parser.compose(b, vsid);
    b.close();
    String s = new String(b.toByteArray());
    // any code systems we can find, we add these too. 
    for (ConceptSetComponent inc : vs.getCompose().getInclude()) {
      CodeSystem cs = fetchCodeSystem(inc.getSystem());
      if (cs != null) 
        s = s + cacheValue(cs);
    }
    s = s + "-"+Boolean.toString(heirarchical);
    String r = Integer.toString(s.hashCode());
//    TextFile.stringToFile(s, Utilities.path(cache, r+".id.json"));
    return r;
  }


  private String cacheValue(CodeSystem cs) throws IOException {
    CodeSystem csid = new CodeSystem();
    csid.setId(cs.getId());
    csid.setVersion(cs.getVersion());
    csid.setContent(cs.getContent());
    csid.setHierarchyMeaning(CodeSystemHierarchyMeaning.GROUPEDBY);
    for (ConceptDefinitionComponent cc : cs.getConcept()) 
      csid.getConcept().add(processCSConcept(cc));
    JsonParser parser = new JsonParser();
    parser.setOutputStyle(OutputStyle.NORMAL);
    ByteArrayOutputStream b = new  ByteArrayOutputStream();
    parser.compose(b, csid);
    b.close();
    return new String(b.toByteArray());
  }


  private ConceptDefinitionComponent processCSConcept(ConceptDefinitionComponent cc) {
    ConceptDefinitionComponent ccid = new ConceptDefinitionComponent();
    ccid.setCode(cc.getCode());
    ccid.setDisplay(cc.getDisplay());
    for (ConceptDefinitionComponent cci : cc.getConcept()) 
      ccid.getConcept().add(processCSConcept(cci));
    return ccid;
  }

  public ValueSetExpansionOutcome expandOnServer(ValueSet vs, String fn) throws Exception {
    if (noTerminologyServer)
      return new ValueSetExpansionOutcome("Error expanding ValueSet: running without terminology services", ExpansionErrorClass.NOSERVICE);
      
    try {
      Map<String, String> params = new HashMap<String, String>();
      params.put("_limit", Integer.toString(expandCodesLimit ));
      params.put("_incomplete", "true");
      params.put("profile", "http://www.healthintersections.com.au/fhir/expansion/no-details");
      log("Terminology Server: $expand on "+getVSSummary(vs));
      ValueSet result = txServer.expandValueset(vs, params);
      return new ValueSetExpansionOutcome(result);  
    } catch (Exception e) {
      return new ValueSetExpansionOutcome("Error expanding ValueSet \""+vs.getUrl()+": "+e.getMessage(), ExpansionErrorClass.UNKNOWN);
    }
  }

  private String getVSSummary(ValueSet vs) {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (UriType u : vs.getCompose().getImport())
      b.append("Import "+u.asStringValue());
    for (ConceptSetComponent cc : vs.getCompose().getInclude())
      b.append("Include "+getIncSummary(cc));
    for (ConceptSetComponent cc : vs.getCompose().getExclude())
      b.append("Exclude "+getIncSummary(cc));
    return b.toString();
  }

  private String getIncSummary(ConceptSetComponent cc) {
    String system = cc.getSystem();
    if (cc.hasConcept())
      return Integer.toString(cc.getConcept().size())+" codes from "+system;;
    if (cc.hasFilter()) {
      String s = "";
      for (ConceptSetFilterComponent f : cc.getFilter()) {
        if (!Utilities.noString(s))
          s = s + " & ";
        s = s + f.getProperty()+" "+f.getOp().toCode()+" "+f.getValue();
      }
      return "from "+system+" where "+s;
    }
    return "All codes from "+system;
  }

  private ValidationResult handleByCache(ValueSet vs, Coding coding, boolean tryCache) {
    String cacheId = cacheId(coding);
    Map<String, ValidationResult> cache = validationCache.get(vs.getUrl());
    if (cache == null) {
      cache = new HashMap<String, IWorkerContext.ValidationResult>();
      validationCache.put(vs.getUrl(), cache);
    }
    if (cache.containsKey(cacheId))
      return cache.get(cacheId);
    if (!tryCache)
      return null;
    if (!cacheValidation)
      return null;
    if (failed.contains(vs.getUrl()))
      return null;
    ValueSetExpansionOutcome vse = expandVS(vs, true, false);
    if (vse.getValueset() == null || notcomplete(vse.getValueset())) {
      failed.add(vs.getUrl());
      return null;
    }
    
    ValidationResult res = validateCode(coding, vse.getValueset());
    cache.put(cacheId, res);
    return res;
  }

  private boolean notcomplete(ValueSet vs) {
    if (!vs.hasExpansion())
      return true;
    if (!vs.getExpansion().getExtensionsByUrl("http://hl7.org/fhir/StructureDefinition/valueset-unclosed").isEmpty())
      return true;
    if (!vs.getExpansion().getExtensionsByUrl("http://hl7.org/fhir/StructureDefinition/valueset-toocostly").isEmpty())
      return true;
    return false;
  }

  private ValidationResult handleByCache(ValueSet vs, CodeableConcept concept, boolean tryCache) {
    String cacheId = cacheId(concept);
    Map<String, ValidationResult> cache = validationCache.get(vs.getUrl());
    if (cache == null) {
      cache = new HashMap<String, IWorkerContext.ValidationResult>();
      validationCache.put(vs.getUrl(), cache);
    }
    if (cache.containsKey(cacheId))
      return cache.get(cacheId);
    
    if (validationCache.containsKey(vs.getUrl()) && validationCache.get(vs.getUrl()).containsKey(cacheId))
      return validationCache.get(vs.getUrl()).get(cacheId);
    if (!tryCache)
      return null;
    if (!cacheValidation)
      return null;
    if (failed.contains(vs.getUrl()))
      return null;
    ValueSetExpansionOutcome vse = expandVS(vs, true, false);
    if (vse.getValueset() == null || notcomplete(vse.getValueset())) {
      failed.add(vs.getUrl());
      return null;
    }
    ValidationResult res = validateCode(concept, vse.getValueset());
    cache.put(cacheId, res);
    return res;
  }

  private String cacheId(Coding coding) {
    return "|"+coding.getSystem()+"|"+coding.getVersion()+"|"+coding.getCode()+"|"+coding.getDisplay();
  }
  
  private String cacheId(CodeableConcept cc) {
    StringBuilder b = new StringBuilder();
    for (Coding c : cc.getCoding()) {
      b.append("#");
      b.append(cacheId(c));
    }    
    return b.toString();
  }
  
  private ValidationResult verifyCodeExternal(ValueSet vs, Coding coding, boolean tryCache) throws IOException {
    ValidationResult res = vs == null ? null : handleByCache(vs, coding, tryCache);
    if (res != null)
      return res;
    Parameters pin = new Parameters();
    pin.addParameter().setName("coding").setValue(coding);
    if (vs != null)
    pin.addParameter().setName("valueSet").setResource(vs);
    res = serverValidateCode(pin, vs == null);
    if (vs != null) {
    Map<String, ValidationResult> cache = validationCache.get(vs.getUrl());
    cache.put(cacheId(coding), res);
    }
    return res;
  }
  
  private ValidationResult verifyCodeExternal(ValueSet vs, CodeableConcept cc, boolean tryCache) throws IOException {
    ValidationResult res = handleByCache(vs, cc, tryCache);
    if (res != null)
      return res;
    Parameters pin = new Parameters();
    pin.addParameter().setName("codeableConcept").setValue(cc);
    pin.addParameter().setName("valueSet").setResource(vs);
    res = serverValidateCode(pin, false);
    Map<String, ValidationResult> cache = validationCache.get(vs.getUrl());
    cache.put(cacheId(cc), res);
    return res;
  }

  private ValidationResult serverValidateCode(Parameters pin, boolean doCache) throws IOException {
    if (noTerminologyServer)
      return new ValidationResult(null, null, ExpansionErrorClass.NOSERVICE);
    String cacheName = doCache ? generateCacheName(pin) : null;
    ValidationResult res = loadFromCache(cacheName);
    if (res != null)
      return res;
    log("Terminology Server: $validate-code "+describeValidationParameters(pin));
    Parameters pout = txServer.operateType(ValueSet.class, "validate-code", pin);
    boolean ok = false;
    String message = "No Message returned";
    String display = null;
    for (ParametersParameterComponent p : pout.getParameter()) {
      if (p.getName().equals("result"))
        ok = ((BooleanType) p.getValue()).getValue().booleanValue();
      else if (p.getName().equals("message"))
        message = ((StringType) p.getValue()).getValue();
      else if (p.getName().equals("display"))
        display = ((StringType) p.getValue()).getValue();
    }
    if (!ok)
      res = new ValidationResult(IssueSeverity.ERROR, message);
    else if (display != null)
      res = new ValidationResult(new ConceptDefinitionComponent().setDisplay(display));
    else
      res = new ValidationResult(null);
    saveToCache(res, cacheName);
    return res;
  }

  
  @SuppressWarnings("rawtypes")
  private String describeValidationParameters(Parameters pin) {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (ParametersParameterComponent p : pin.getParameter()) {
      if (p.hasValue() && p.getValue() instanceof PrimitiveType) {
        b.append(p.getName()+"="+((PrimitiveType) p.getValue()).asStringValue());
      } else if (p.hasValue() && p.getValue() instanceof Coding) {
        b.append("system="+((Coding) p.getValue()).getSystem());
        b.append("code="+((Coding) p.getValue()).getCode());
        b.append("display="+((Coding) p.getValue()).getDisplay());
      } else if (p.hasValue() && p.getValue() instanceof CodeableConcept) {
        if (((CodeableConcept) p.getValue()).hasCoding()) {
          Coding c = ((CodeableConcept) p.getValue()).getCodingFirstRep();
          b.append("system="+c.getSystem());
          b.append("code="+c.getCode());
          b.append("display="+c.getDisplay());
        } else if (((CodeableConcept) p.getValue()).hasText()) {
          b.append("text="+((CodeableConcept) p.getValue()).getText());
        }
      } else if (p.hasResource() && (p.getResource() instanceof ValueSet)) {
        b.append("valueset="+getVSSummary((ValueSet) p.getResource()));
      } 
    }
    return b.toString();
  }

  private ValidationResult loadFromCache(String fn) throws FileNotFoundException, IOException {
    if (fn == null)
      return null;
    if (!(new File(fn).exists()))
      return null;
    String cnt = TextFile.fileToString(fn);
    if (cnt.startsWith("!error: "))
      return new ValidationResult(IssueSeverity.ERROR, cnt.substring(8));
    else if (cnt.startsWith("!warning: "))
      return new ValidationResult(IssueSeverity.ERROR, cnt.substring(10));
    else
      return new ValidationResult(new ConceptDefinitionComponent().setDisplay(cnt));
  }

  private void saveToCache(ValidationResult res, String cacheName) throws IOException {
    if (cacheName == null)
      return;
    if (res.getDisplay() != null)
      TextFile.stringToFile(res.getDisplay(), cacheName);
    else if (res.getSeverity() == IssueSeverity.WARNING)
      TextFile.stringToFile("!warning: "+res.getMessage(), cacheName);
    else 
      TextFile.stringToFile("!error: "+res.getMessage(), cacheName);
  }

  private String generateCacheName(Parameters pin) throws IOException {
    if (cache == null)
      return null;
    String json = new JsonParser().composeString(pin);
    return Utilities.path(cache, "vc"+Integer.toString(json.hashCode())+".json");
  }

  @Override
  public ValueSetExpansionComponent expandVS(ConceptSetComponent inc, boolean heirachical) throws TerminologyServiceException {
    ValueSet vs = new ValueSet();
    vs.setCompose(new ValueSetComposeComponent());
    vs.getCompose().getInclude().add(inc);
    ValueSetExpansionOutcome vse = expandVS(vs, true, heirachical);
    ValueSet valueset = vse.getValueset();
    if (valueset == null)
      throw new TerminologyServiceException("Error Expanding ValueSet: "+vse.getError());
    return valueset.getExpansion();
  }

  @Override
  public ValidationResult validateCode(String system, String code, String display) {
    try {
      if (codeSystems.containsKey(system) && codeSystems.get(system) != null)
        return verifyCodeInCodeSystem(codeSystems.get(system), system, code, display);
      else 
        return verifyCodeExternal(null, new Coding().setSystem(system).setCode(code).setDisplay(display), false);
    } catch (Exception e) {
      return new ValidationResult(IssueSeverity.FATAL, "Error validating code \""+code+"\" in system \""+system+"\": "+e.getMessage());
    }
  }

  
  @Override
  public ValidationResult validateCode(Coding code, ValueSet vs) {
    if (codeSystems.containsKey(code.getSystem()) && codeSystems.get(code.getSystem()) != null) 
      try {
        return verifyCodeInCodeSystem(codeSystems.get(code.getSystem()), code.getSystem(), code.getCode(), code.getDisplay());
      } catch (Exception e) {
        return new ValidationResult(IssueSeverity.FATAL, "Error validating code \""+code+"\" in system \""+code.getSystem()+"\": "+e.getMessage());
      }
    else if (vs.hasExpansion()) 
      try {
        return verifyCodeInternal(vs, code.getSystem(), code.getCode(), code.getDisplay());
      } catch (Exception e) {
        return new ValidationResult(IssueSeverity.FATAL, "Error validating code \""+code+"\" in system \""+code.getSystem()+"\": "+e.getMessage());
      }
    else 
      try {
        return verifyCodeExternal(vs, code, true);
      } catch (Exception e) {
        return new ValidationResult(IssueSeverity.WARNING, "Error validating code \""+code+"\" in system \""+code.getSystem()+"\": "+e.getMessage());
      }
  }

  @Override
  public ValidationResult validateCode(CodeableConcept code, ValueSet vs) {
    try {
      if (vs.hasExpansion()) 
        return verifyCodeInternal(vs, code);
      else 
        return verifyCodeExternal(vs, code, true);
    } catch (Exception e) {
      return new ValidationResult(IssueSeverity.FATAL, "Error validating code \""+code.toString()+"\": "+e.getMessage(), ExpansionErrorClass.UNKNOWN);
    }
  }


  @Override
  public ValidationResult validateCode(String system, String code, String display, ValueSet vs) {
    try {
      if (system == null && display == null)
        return verifyCodeInternal(vs, code);
      if ((codeSystems.containsKey(system)  && codeSystems.get(system) != null) || vs.hasExpansion()) 
        return verifyCodeInternal(vs, system, code, display);
      else 
        return verifyCodeExternal(vs, new Coding().setSystem(system).setCode(code).setDisplay(display), true);
    } catch (Exception e) {
      return new ValidationResult(IssueSeverity.FATAL, "Error validating code \""+code+"\" in system \""+system+"\": "+e.getMessage());
    }
  }

  @Override
  public ValidationResult validateCode(String system, String code, String display, ConceptSetComponent vsi) {
    try {
      ValueSet vs = new ValueSet();
      vs.setUrl(Utilities.makeUuidUrn());
      vs.getCompose().addInclude(vsi);
      return verifyCodeExternal(vs, new Coding().setSystem(system).setCode(code).setDisplay(display), true);
    } catch (Exception e) {
      return new ValidationResult(IssueSeverity.FATAL, "Error validating code \""+code+"\" in system \""+system+"\": "+e.getMessage());
    }
  }

  public void initTS(String cachePath, String tsServer) throws Exception {
    cache = cachePath;
    this.tsServer = tsServer;
    expansionCache = new ValueSetExpansionCache(this, null);
    validationCachePath = Utilities.path(cachePath, "validation.cache");
    try {
      loadValidationCache();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void loadValidationCache() throws JsonSyntaxException, Exception {
  }
  
  @Override
  public List<ConceptMap> findMapsForSource(String url) {
    List<ConceptMap> res = new ArrayList<ConceptMap>();
    for (ConceptMap map : maps.values())
      if (((Reference) map.getSource()).getReference().equals(url)) 
        res.add(map);
    return res;
  }

  private ValidationResult verifyCodeInternal(ValueSet vs, CodeableConcept code) throws FileNotFoundException, ETooCostly, IOException {
    for (Coding c : code.getCoding()) {
      ValidationResult res = verifyCodeInternal(vs, c.getSystem(), c.getCode(), c.getDisplay());
      if (res.isOk())
        return res;
    }
    if (code.getCoding().isEmpty())
      return new ValidationResult(IssueSeverity.ERROR, "None code provided");
    else
      return new ValidationResult(IssueSeverity.ERROR, "None of the codes are in the specified value set");
  }

  private ValidationResult verifyCodeInternal(ValueSet vs, String system, String code, String display) throws FileNotFoundException, ETooCostly, IOException {
    if (vs.hasExpansion())
      return verifyCodeInExpansion(vs, system, code, display);
    else {
      ValueSetExpansionOutcome vse = expansionCache.getExpander().expand(vs, null);
      if (vse.getValueset() != null) 
        return verifyCodeExternal(vs, new Coding().setSystem(system).setCode(code).setDisplay(display), false);
      else
        return verifyCodeInExpansion(vse.getValueset(), system, code, display);
    }
  }

  private ValidationResult verifyCodeInternal(ValueSet vs, String code) throws FileNotFoundException, ETooCostly, IOException {
    if (vs.hasExpansion())
      return verifyCodeInExpansion(vs, code);
    else {
      ValueSetExpansionOutcome vse = expansionCache.getExpander().expand(vs, null);
      if (vse.getValueset() == null)
        return new ValidationResult(IssueSeverity.ERROR, vse.getError(), vse.getErrorClass());
      else
        return verifyCodeInExpansion(vse.getValueset(), code);
    }
  }

  private ValidationResult verifyCodeInCodeSystem(CodeSystem cs, String system, String code, String display) {
    ConceptDefinitionComponent cc = findCodeInConcept(cs.getConcept(), code);
    if (cc == null)
	  if (cs.getContent().equals(CodeSystem.CodeSystemContentMode.COMPLETE))
	    return new ValidationResult(IssueSeverity.ERROR, "Unknown Code "+code+" in "+cs.getUrl());
	  else if (!cs.getContent().equals(CodeSystem.CodeSystemContentMode.NOTPRESENT))
	    return new ValidationResult(IssueSeverity.WARNING, "Unknown Code "+code+" in partial code list of "+cs.getUrl());
	  else
	    return new ValidationResult(IssueSeverity.WARNING, "Codes are not available for validation of content from system "+cs.getUrl());
//      return new ValidationResult(IssueSeverity.ERROR, "Unknown Code "+code+" in "+cs.getUrl());
    if (display == null)
      return new ValidationResult(cc);
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    if (cc.hasDisplay()) {
      b.append(cc.getDisplay());
      if (display.equalsIgnoreCase(cc.getDisplay()))
        return new ValidationResult(cc);
    }
    for (ConceptDefinitionDesignationComponent ds : cc.getDesignation()) {
      b.append(ds.getValue());
      if (display.equalsIgnoreCase(ds.getValue()))
        return new ValidationResult(cc);
    }
    return new ValidationResult(IssueSeverity.WARNING, "Display Name for "+code+" must be one of '"+b.toString()+"'", cc);
  }


  private ValidationResult verifyCodeInExpansion(ValueSet vs, String system,String code, String display) {
    ValueSetExpansionContainsComponent cc = findCode(vs.getExpansion().getContains(), code);
    if (cc == null)
      return new ValidationResult(IssueSeverity.ERROR, "Unknown Code "+code+" in "+vs.getUrl());
    if (display == null)
      return new ValidationResult(new ConceptDefinitionComponent().setCode(code).setDisplay(cc.getDisplay()));
    if (cc.hasDisplay()) {
      if (display.equalsIgnoreCase(cc.getDisplay()))
        return new ValidationResult(new ConceptDefinitionComponent().setCode(code).setDisplay(cc.getDisplay()));
      return new ValidationResult(IssueSeverity.WARNING, "Display Name for "+code+" must be '"+cc.getDisplay()+"'", new ConceptDefinitionComponent().setCode(code).setDisplay(cc.getDisplay()));
    }
    return null;
  }

  private ValidationResult verifyCodeInExpansion(ValueSet vs, String code) {
    ValueSetExpansionContainsComponent cc = findCode(vs.getExpansion().getContains(), code);
    if (cc == null)
      return new ValidationResult(IssueSeverity.ERROR, "Unknown Code "+code+" in "+vs.getUrl());
    return null;
  }

  private ValueSetExpansionContainsComponent findCode(List<ValueSetExpansionContainsComponent> contains, String code) {
    for (ValueSetExpansionContainsComponent cc : contains) {
      if (code.equals(cc.getCode()))
        return cc;
      ValueSetExpansionContainsComponent c = findCode(cc.getContains(), code);
      if (c != null)
        return c;
    }
    return null;
  }

  private ConceptDefinitionComponent findCodeInConcept(List<ConceptDefinitionComponent> concept, String code) {
    for (ConceptDefinitionComponent cc : concept) {
      if (code.equals(cc.getCode()))
        return cc;
      ConceptDefinitionComponent c = findCodeInConcept(cc.getConcept(), code);
      if (c != null)
        return c;
    }
    return null;
  }

  public Set<String> getNonSupportedCodeSystems() {
    return nonSupportedCodeSystems;
  }

  public boolean isCanRunWithoutTerminology() {
    return canRunWithoutTerminology;
  }

  public void setCanRunWithoutTerminology(boolean canRunWithoutTerminology) {
    this.canRunWithoutTerminology = canRunWithoutTerminology;
  }

  public int getExpandCodesLimit() {
    return expandCodesLimit;
  }

  public void setExpandCodesLimit(int expandCodesLimit) {
    this.expandCodesLimit = expandCodesLimit;
  }

  public void setLogger(ILoggingService logger) {
    this.logger = logger;
  }

  public ExpansionProfile getExpansionProfile() {
    return expProfile;
  }

  public void setExpansionProfile(ExpansionProfile expProfile) {
    this.expProfile = expProfile;
  }

  @Override
  public boolean isNoTerminologyServer() {
    return noTerminologyServer;
  }

}
