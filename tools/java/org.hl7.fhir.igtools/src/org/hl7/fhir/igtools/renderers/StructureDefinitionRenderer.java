package org.hl7.fhir.igtools.renderers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.igtools.publisher.FetchedFile;
import org.hl7.fhir.igtools.publisher.FetchedResource;
import org.hl7.fhir.igtools.publisher.IGKnowledgeProvider;
import org.hl7.fhir.igtools.publisher.SpecMapManager;
import org.hl7.fhir.r5.conformance.ProfileUtilities;
import org.hl7.fhir.r5.conformance.ProfileUtilities.ProfileKnowledgeProvider.BindingResolution;
import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.formats.IParser.OutputStyle;
import org.hl7.fhir.r5.formats.XmlParser;
import org.hl7.fhir.r5.model.CanonicalType;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.CodeableConcept;
import org.hl7.fhir.r5.model.Coding;
import org.hl7.fhir.r5.model.ElementDefinition;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionExampleComponent;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionMappingComponent;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionSlicingComponent;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionSlicingDiscriminatorComponent;
import org.hl7.fhir.r5.model.ElementDefinition.SlicingRules;
import org.hl7.fhir.r5.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.r5.model.Enumerations.BindingStrength;
import org.hl7.fhir.r5.model.Extension;
import org.hl7.fhir.r5.model.IdType;
import org.hl7.fhir.r5.model.PrimitiveType;
import org.hl7.fhir.r5.model.Quantity;
import org.hl7.fhir.r5.model.StringType;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.r5.model.StructureDefinition.StructureDefinitionMappingComponent;
import org.hl7.fhir.r5.model.StructureDefinition.TypeDerivationRule;
import org.hl7.fhir.r5.model.Type;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.utils.ElementDefinitionUtilities;
import org.hl7.fhir.r5.utils.ToolingExtensions;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.MarkDownProcessor;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.cache.NpmPackage;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator.Piece;

public class StructureDefinitionRenderer extends BaseRenderer {
  public static final String RIM_MAPPING = "http://hl7.org/v3";
  public static final String v2_MAPPING = "http://hl7.org/v2";
  public static final String LOINC_MAPPING = "http://loinc.org";
  public static final String SNOMED_MAPPING = "http://snomed.info";

  ProfileUtilities utils;
  private StructureDefinition sd;
  private String destDir;

  public StructureDefinitionRenderer(IWorkerContext context, String prefix, StructureDefinition sd, String destDir, IGKnowledgeProvider igp, List<SpecMapManager> maps, MarkDownProcessor markdownEngine, NpmPackage packge) {
    super(context, prefix, igp, maps, markdownEngine, packge);
    this.sd = sd;
    this.destDir = destDir;
    utils = new ProfileUtilities(context, null, igp);
    utils.setIgmode(true);
  }

  @Override
  public void setTranslator(org.hl7.fhir.utilities.TranslationServices translator) {
    super.setTranslator(translator);
    utils.setTranslator(translator);
  }

  public String summary() {
    try {
      if (sd.getDifferential() == null)
        return "<p>"+translate("sd.summary", "No Summary, as this profile has no differential")+"</p>";

      // references
      List<String> refs = new ArrayList<String>(); // profile references
      // extensions (modifier extensions)
      List<String> ext = new ArrayList<String>(); // extensions
      // slices
      List<String> slices = new ArrayList<String>(); // Fixed Values 
      // numbers - must support, required, prohibited, fixed
      int supports = 0;
      int requiredOutrights = 0;
      int requiredNesteds = 0;
      int fixeds = 0;
      int prohibits = 0;

      for (ElementDefinition ed : sd.getDifferential().getElement()) {
        if (ed.getPath().contains(".")) {
          if (ed.getMin() == 1)
            if (parentChainHasOptional(ed, sd))
              requiredNesteds++;
            else
              requiredOutrights++;
          if ("0".equals(ed.getMax()))
            prohibits++;
          if (ed.getMustSupport())
            supports++;
          if (ed.hasFixed())
            fixeds++;

          for (TypeRefComponent t : ed.getType()) {
            if (t.hasProfile() && !igp.isDatatype(t.getProfile().get(0).getValue().substring(40))) {
              if (ed.getPath().endsWith(".extension"))
                tryAdd(ext, summariseExtension(t.getProfile(), false, prefix));
              else if (ed.getPath().endsWith(".modifierExtension"))
                tryAdd(ext, summariseExtension(t.getProfile(), true, prefix));
              else
                tryAdd(refs, describeProfile(t.getProfile().get(0).getValue(), prefix));
            } 
            if (t.hasTargetProfile()) {
              tryAdd(refs, describeProfile(t.getTargetProfile().get(0).getValue(), prefix));
            } 
          }

          if (ed.hasSlicing() && !ed.getPath().endsWith(".extension") && !ed.getPath().endsWith(".modifierExtension"))
            tryAdd(slices, describeSlice(ed.getPath(), ed.getSlicing()));
        }
      }
      StringBuilder res = new StringBuilder("<a name=\"summary\"> </a>\r\n<p><b>\r\n"+translate("sd.summary", "Summary")+"\r\n</b></p>\r\n");
      if (ToolingExtensions.hasExtension(sd, "http://hl7.org/fhir/StructureDefinition/structuredefinition-summary")) {
        Extension v = ToolingExtensions.getExtension(sd, "http://hl7.org/fhir/StructureDefinition/structuredefinition-summary");
        res.append(processMarkdown("Profile.summary", (PrimitiveType) v.getValue()));
      }
      if (supports + requiredOutrights + requiredNesteds + fixeds + prohibits > 0) {
        boolean started = false;
        res.append("<p>");
        if (requiredOutrights > 0 || requiredNesteds > 0) {
          started = true;
          res.append(translate("sd.summary", "Mandatory: %s %s", toStr(requiredOutrights), (requiredOutrights > 1 ? translate("sd.summary", Utilities.pluralizeMe("element")) : translate("sd.summary", "element"))));
          if (requiredNesteds > 0)
            res.append(translate("sd.summary", " (%s nested mandatory %s)", toStr(requiredNesteds), requiredNesteds > 1 ? translate("sd.summary", Utilities.pluralizeMe("element")) : translate("sd.summary", "element"))); 
        }
        if (supports > 0) {
          if (started)
            res.append("<br/> ");
          started = true;
          res.append(translate("sd.summary", "Must-Support: %s %s", toStr(supports), supports > 1 ? translate("sd.summary", Utilities.pluralizeMe("element")) : translate("sd.summary", "element"))); 
        }
        if (fixeds > 0) {
          if (started)
            res.append("<br/> ");
          started = true;
          res.append(translate("sd.summary", "Fixed Value: %s %s", toStr(fixeds), fixeds > 1 ? translate("sd.summary", Utilities.pluralizeMe("element")) : translate("sd.summary", "element"))); 
        }
        if (prohibits > 0) {
          if (started)
            res.append("<br/> ");
          started = true;
          res.append(translate("sd.summary", "Prohibited: %s %s", toStr(prohibits), prohibits > 1 ? translate("sd.summary", Utilities.pluralizeMe("element")) : translate("sd.summary", "element"))); 
        }
        res.append("</p>");        
      }
      if (!refs.isEmpty()) {
        res.append("<p><b>"+translate("sd.summary", "Structures")+"</b></p>\r\n<p>"+translate("sd.summary", "This structure refers to these other structures")+":</p>\r\n<ul>\r\n");
        for (String s : refs)
          res.append(s);
        res.append("\r\n</ul>\r\n\r\n");
      }
      if (!ext.isEmpty()) {
        res.append("<p><b>"+translate("sd.summary", "Extensions")+"</b></p>\r\n<p>"+translate("sd.summary", "This structure refers to these extensions")+":</p>\r\n<ul>\r\n");
        for (String s : ext)
          res.append(s);
        res.append("\r\n</ul>\r\n\r\n");
      }
      if (!slices.isEmpty()) {
        res.append("<p><b>"+translate("sd.summary", "Slices")+"</b></p>\r\n<p>"+translate("sd.summary", "This structure defines the following %sSlices%s", "<a href=\""+prefix+"profiling.html#slices\">", "</a>")+":</p>\r\n<ul>\r\n");
        for (String s : slices)
          res.append(s);
        res.append("\r\n</ul>\r\n\r\n");
      }
      if (ToolingExtensions.hasExtension(sd, ToolingExtensions.EXT_FMM_LEVEL))
        res.append("<p><b><a class=\"fmm\" href=\"versions.html#maturity\" title=\"Maturity Level\">"+translate("cs.summary", "Maturity")+"</a></b>: "+ToolingExtensions.readStringExtension(sd, ToolingExtensions.EXT_FMM_LEVEL)+"</p>\r\n");
      
      return res.toString();
    } catch (Exception e) {
      return "<p><i>"+Utilities.escapeXml(e.getMessage())+"</i></p>";
    }
  }

  private boolean parentChainHasOptional(ElementDefinition ed, StructureDefinition profile) {
    if (!ed.getPath().contains("."))
      return false;

    ElementDefinition match = (ElementDefinition) ed.getUserData(ProfileUtilities.DERIVATION_POINTER);
    if (match == null)
      return true; // really, we shouldn't get here, but this appears to be common in the existing profiles?  
    // throw new Error("no matches for "+ed.getPath()+"/"+ed.getName()+" in "+profile.getUrl());

    while (match.getPath().contains(".")) {
      if (match.getMin() == 0) {
        return true;
      }
      match = getElementParent(profile.getSnapshot().getElement(), match);
    }

    return false;
  }

  private ElementDefinition getElementParent(List<ElementDefinition> list, ElementDefinition element) {
    String targetPath = element.getPath().substring(0, element.getPath().lastIndexOf("."));
    int index = list.indexOf(element) - 1;
    while (index >= 0) {
      if (list.get(index).getPath().equals(targetPath))
        return list.get(index);
      index--;
    }
    return null;
  }

  private String describeSlice(String path, ElementDefinitionSlicingComponent slicing) {
    if (!slicing.hasDiscriminator())
      return "<li>"+translate("sd.summary", "There is a slice with no discriminator at %s", path)+"</li>\r\n";
    String s = "";
    if (slicing.getOrdered())
      s = "ordered";
    if (slicing.getRules() != SlicingRules.OPEN)
      s = Utilities.noString(s) ? slicing.getRules().getDisplay() : s+", "+ slicing.getRules().getDisplay();
      if (!Utilities.noString(s))
        s = " ("+s+")";
      CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
      for (ElementDefinitionSlicingDiscriminatorComponent d : slicing.getDiscriminator()) 
        b.append(d.getType().toCode()+":"+d.getPath());
      if (slicing.getDiscriminator().size() == 1)
        return "<li>"+translate("sd.summary", "The element %s is sliced based on the value of %s", path, b.toString())+s+"</li>\r\n";
      else
        return "<li>"+translate("sd.summary", "The element %s is sliced based on the values of %s", path, b.toString())+s+"</li>\r\n";
  }

  private void tryAdd(List<String> ext, String s) {
    if (!Utilities.noString(s) && !ext.contains(s))
      ext.add(s);
  }

  private String summariseExtension(List<CanonicalType> profiles, boolean modifier, String prefix) throws Exception {
    if (profiles.size() != 1)
      throw new Exception("Multiple profiles are not supported at this time (#1)");
    String url = profiles.get(0).getValue();
    StructureDefinition ed = context.fetchResource(StructureDefinition.class, url);
    if (ed == null)
      return "<li>"+translate("sd.summary", "Unable to summarise extension %s (no extension found)", url)+"</li>";
    if (ed.getUserData("path") == null)
      return "<li><a href=\""+"extension-"+ed.getId().toLowerCase()+".html\">"+url+"</a>"+(modifier ? " (<b>"+translate("sd.summary", "Modifier")+"</b>) " : "")+"</li>\r\n";    
    else
      return "<li><a href=\""+ed.getUserString("path")+"\">"+url+"</a>"+(modifier ? " (<b>"+translate("sd.summary", "Modifier")+"</b>) " : "")+"</li>\r\n";    
  }

  private String describeProfile(String url, String prefix) throws Exception {
    if (url.startsWith("http://hl7.org/fhir/StructureDefinition/") && (igp.isDatatype(url.substring(40)) || igp.isResource(url.substring(40)) || "Resource".equals(url.substring(40))))
      return null;

    StructureDefinition ed = context.fetchResource(StructureDefinition.class, url);
    if (ed == null)
      return "<li>"+translate("sd.summary", "unable to summarise profile %s (no profile found)",url)+"</li>";
    return "<li><a href=\""+ed.getUserString("path")+"\">"+url+"</a></li>\r\n";    
  }


  private String summariseValue(Type fixed) throws FHIRException {
    if (fixed instanceof org.hl7.fhir.r5.model.PrimitiveType)
      return ((org.hl7.fhir.r5.model.PrimitiveType) fixed).asStringValue();
    if (fixed instanceof CodeableConcept) 
      return summarise((CodeableConcept) fixed);
    if (fixed instanceof Coding) 
      return summarise((Coding) fixed);
    if (fixed instanceof Quantity) 
      return summarise((Quantity) fixed);
    throw new FHIRException("Generating text summary of fixed value not yet done for type "+fixed.getClass().getName());
  }


  private String summarise(Quantity quantity) {
    String cu = "";
    if ("http://unitsofmeasure.org/".equals(quantity.getSystem()))
      cu = " ("+translate("sd.summary", "UCUM")+": "+quantity.getCode()+")";
    if ("http://snomed.info/sct".equals(quantity.getSystem()))
      cu = " ("+translate("sd.summary", "SNOMED CT")+": "+quantity.getCode()+")";
    return quantity.getValue().toString()+quantity.getUnit()+cu;
  }

  private String summarise(CodeableConcept cc) throws FHIRException {
    if (cc.getCoding().size() == 1 && cc.getText() == null) {
      return summarise(cc.getCoding().get(0));
    } else if (cc.getCoding().size() == 0 && cc.hasText()) {
      return "\"" + cc.getText()+"\"";
    } else 
      throw new FHIRException("too complex to describe");
  }

  private String summarise(Coding coding) throws FHIRException {
    if ("http://snomed.info/sct".equals(coding.getSystem()))
      return ""+translate("sd.summary", "SNOMED CT code")+" "+coding.getCode()+ (!coding.hasDisplay() ? "" : "(\""+gt(coding.getDisplayElement())+"\")");
    if ("http://loinc.org".equals(coding.getSystem()))
      return ""+translate("sd.summary", "LOINC code")+" "+coding.getCode()+ (!coding.hasDisplay() ? "" : "(\""+gt(coding.getDisplayElement())+"\")");
    CodeSystem cs = context.fetchCodeSystem(coding.getSystem());
    if (cs == null) 
      return "<span title=\""+coding.getSystem()+"\">"+coding.getCode()+"</a>"+(!coding.hasDisplay() ? "" : "(\""+gt(coding.getDisplayElement())+"\")");
    else
      return "<a title=\""+cs.present()+"\" href=\""+cs.getUserData("path")+"#"+coding.getCode()+"\">"+coding.getCode()+"</a>"+(!coding.hasDisplay() ? "" : "(\""+gt(coding.getDisplayElement())+"\")");
  }

  public String diff(String defnFile, Set<String> outputTracker) throws IOException, FHIRException, org.hl7.fhir.exceptions.FHIRException {
    if (sd.getDifferential().getElement().isEmpty())
      return "";
    else
      return new XhtmlComposer(XhtmlComposer.HTML).compose(utils.generateTable(defnFile, sd, true, destDir, false, sd.getId(), false, prefix, "", false, false, outputTracker));
  }

  public String snapshot(String defnFile, Set<String> outputTracker) throws IOException, FHIRException, org.hl7.fhir.exceptions.FHIRException {
    if (sd.getSnapshot().getElement().isEmpty())
      return "";
    else
      return new XhtmlComposer(XhtmlComposer.HTML).compose(utils.generateTable(defnFile, sd, false, destDir, false, sd.getId(), true, prefix, "", false, false, outputTracker));
  }

  public String grid(String defnFile, Set<String> outputTracker) throws IOException, FHIRException, org.hl7.fhir.exceptions.FHIRException {
    if (sd.getSnapshot().getElement().isEmpty())
      return "";
    else
      return new XhtmlComposer(XhtmlComposer.HTML).compose(utils.generateGrid(defnFile, sd, destDir, false, sd.getId(), prefix, "", outputTracker));
  }

  public String txDiff(boolean withHeadings, boolean mustSupportOnly) throws FHIRException {
    List<String> txlist = new ArrayList<String>();
    boolean hasFixed  = false;
    Map<String, ElementDefinitionBindingComponent> txmap = new HashMap<String, ElementDefinitionBindingComponent>();
    for (ElementDefinition ed : sd.getDifferential().getElement()) {
      if (ed.hasBinding() && !"0".equals(ed.getMax()) && (!mustSupportOnly || ed.getMustSupport())) {
        String id = ed.getId();
        if (ed.hasFixed()) { 
          hasFixed = true;
          ed.getBinding().setUserData("tx.value", ed.getFixed());
        } else if (ed.hasPattern()) { 
          hasFixed = true;
          ed.getBinding().setUserData("tx.pattern", ed.getPattern());
        } else {
          // tricky : scan the children for a fixed coding value
          Type t = findFixedValue(ed, true);
          if (t != null)
            ed.getBinding().setUserData("tx.value", t);
        }
        if (ed.getType().size() == 1 && ed.getType().get(0).getCode().equals("Extension"))
          id = id + "<br/>"+ed.getType().get(0).getProfile();
        txlist.add(id);
        txmap.put(id, ed.getBinding());
      }
    }
    if (txlist.isEmpty())
      return "";
    else {
      StringBuilder b = new StringBuilder();
      if (withHeadings)
        b.append("<h4>"+translate("sd.tx", "Terminology Bindings (Differential)")+"</h4>\r\n");       
      b.append("<table class=\"list\">\r\n");
      b.append("<tr><td><b>"+translate("sd.tx", "Path")+"</b></td><td><b>"+translate("sd.tx", "Conformance")+"</b></td><td><b>"+translate("sd.tx", hasFixed ? "ValueSet / Code" : "ValueSet")+"</b></td></tr>\r\n");
      for (String path : txlist)  {
        txItem(txmap, b, path);
      }
      b.append("</table>\r\n");
      return b.toString();
    }
  }
  
  public String tx(boolean withHeadings, boolean mustSupportOnly) throws FHIRException {
    List<String> txlist = new ArrayList<String>();
    boolean hasFixed  = false;
    Map<String, ElementDefinitionBindingComponent> txmap = new HashMap<String, ElementDefinitionBindingComponent>();
    for (ElementDefinition ed : sd.getSnapshot().getElement()) {
      if (ed.hasBinding() && !"0".equals(ed.getMax()) && (!mustSupportOnly || ed.getMustSupport())) {
        String id = ed.getId();
        if (ed.hasFixed()) { 
          hasFixed = true;
          ed.getBinding().setUserData("tx.value", ed.getFixed());
        } else if (ed.hasPattern()) { 
          hasFixed = true;
          ed.getBinding().setUserData("tx.pattern", ed.getPattern());
        } else {
          // tricky : scan the children for a fixed coding value
          Type t = findFixedValue(ed, false);
          if (t != null)
            ed.getBinding().setUserData("tx.value", t);
        }
        if (ed.getType().size() == 1 && ed.getType().get(0).getCode().equals("Extension"))
          id = id + "<br/>"+ed.getType().get(0).getProfile();
        txlist.add(id);
        txmap.put(id, ed.getBinding());
      }
    }
    if (txlist.isEmpty())
      return "";
    else {
      StringBuilder b = new StringBuilder();
      if (withHeadings)
        b.append("<h4>"+translate("sd.tx", "Terminology Bindings")+"</h4>\r\n");       
      b.append("<table class=\"list\">\r\n");
      b.append("<tr><td><b>"+translate("sd.tx", "Path")+"</b></td><td><b>"+translate("sd.tx", "Conformance")+"</b></td><td><b>"+translate("sd.tx", hasFixed ? "ValueSet / Code" : "ValueSet")+"</b></td></tr>\r\n");
      for (String path : txlist)  {
        txItem(txmap, b, path);
      }
      b.append("</table>\r\n");
      return b.toString();

    }
  }

  private Type findFixedValue(ElementDefinition ed, boolean diff) {
    if (ElementDefinitionUtilities.hasType(ed, "Coding")) {
      List<ElementDefinition> children = ProfileUtilities.getChildList(sd, ed, diff);
      String sys = null;
      String code = null;
      for (ElementDefinition cd : children) {
        if (cd.getPath().endsWith(".system") && cd.hasFixed()) 
          sys = cd.getFixed().primitiveValue();
        if (cd.getPath().endsWith(".code") && cd.hasFixed()) 
          code = cd.getFixed().primitiveValue();
      }
      if (sys != null && code != null)
        return new Coding().setSystem(sys).setCode(code);
    }
    return null;
  }

  public void txItem(Map<String, ElementDefinitionBindingComponent> txmap, StringBuilder b, String path) throws FHIRException {
    ElementDefinitionBindingComponent tx = txmap.get(path);
    String vss = "";
    String vsn = "?ext";
    if (tx.hasValueSet()) {
      String uri = tx.getValueSet();
      String name = getSpecialValueSetName(uri);
      if (name != null) {
        vss = "<a href=\""+uri+"\">"+Utilities.escapeXml(name)+"</a>";
        vsn = name;
      } else {
      ValueSet vs = context.fetchResource(ValueSet.class, canonicalise(uri));
      if (vs == null) {
        BindingResolution br = igp.resolveActualUrl(uri);
        if (br.url == null)
          vss = "<code>"+Utilities.escapeXml(br.display)+"</code>";
        else if (Utilities.isAbsoluteUrl(br.url))
          vss = "<a href=\""+br.url+"\">"+Utilities.escapeXml(br.display)+"</a>";
        else
          vss = "<a href=\""+prefix+br.url+"\">"+Utilities.escapeXml(br.display)+"</a>";
      } else { 
        String p = vs.getUserString("path");
        if (p == null)
          vss = "<a href=\"??\">"+Utilities.escapeXml(gt(vs.getNameElement()))+" ("+translate("sd.tx", "missing link")+")</a>";
        else if (p.startsWith("http:"))
          vss = "<a href=\""+p+"\">"+Utilities.escapeXml(gt(vs.getNameElement()))+"</a>";
        else
          vss = "<a href=\""+p+"\">"+Utilities.escapeXml(gt(vs.getNameElement()))+"</a>";
        StringType title = vs.hasTitleElement() ? vs.getTitleElement() : vs.getNameElement();
        if (title != null)
          vsn = gt(title);
      }
      }
    }
    if (vsn.equals("?ext"))
      if (tx.getValueSet() != null)
        System.out.println("No value set at "+path+" (url = '"+tx.getValueSet()+"')");
      else
        System.out.println("No value set at "+path+" (no url)");
    if (tx.hasUserData("tx.value"))
      vss = "Fixed Value: "+summariseValue((Type) tx.getUserData("tx.value"));
    else if (tx.hasUserData("tx.pattern"))
      vss = "Pattern: "+summariseValue((Type) tx.getUserData("tx.pattern"));
    
    b.append("<tr><td>").append(path).append("</td><td><a href=\"").append(prefix).append("terminologies.html#").append(tx.getStrength() == null ? "" : egt(tx.getStrengthElement()));
    if (tx.hasDescription())
      b.append("\">").append(tx.getStrength() == null ? "" : egt(tx.getStrengthElement())).append("</a></td><td title=\"").append(Utilities.escapeXml(tx.getDescription())).append("\">").append(vss);
    else
      b.append("\">").append(tx.getStrength() == null ? "" : egt(tx.getStrengthElement())).append("</a></td><td>").append(vss);
    if (tx.hasExtension(ToolingExtensions.EXT_MAX_VALUESET)) {
      BindingResolution br = igp.resolveBinding(sd, ToolingExtensions.readStringExtension(tx, ToolingExtensions.EXT_MAX_VALUESET), path);
      b.append("<br>");
      b.append("<a style=\"font-weight:bold\" title=\"Max Value Set Extension\" href=\""+prefix+"extension-elementdefinition-maxvalueset.html\">Max Binding</a>: ");             
      b.append((br.url == null ? Utilities.escapeXml(br.display) : "<a href=\""+ (Utilities.isAbsoluteUrl(br.url) || !igp.prependLinks() ? br.url : prefix+br.url)+"\">"+Utilities.escapeXml(br.display)+"</a>"));
    }
    if (tx.hasExtension(ToolingExtensions.EXT_MIN_VALUESET)) {
      BindingResolution br = igp.resolveBinding(sd, ToolingExtensions.readStringExtension(tx, ToolingExtensions.EXT_MIN_VALUESET), path);
      b.append("<br>");
      b.append("<a style=\"font-weight:bold\" title=\"Min Value Set Extension\" href=\""+prefix+"extension-elementdefinition-minvalueset.html\">Min Binding</a>: ");             
      b.append((br.url == null ? Utilities.escapeXml(br.display) : "<a href=\""+ (Utilities.isAbsoluteUrl(br.url) || !igp.prependLinks() ? br.url : prefix+br.url)+"\">"+Utilities.escapeXml(br.display)+"</a>"));
    }      
    b.append("</td></tr>\r\n");
  }

  private String getSpecialValueSetName(String uri) {
    if (uri.startsWith("http://loinc.org/vs/"))
      return "LOINC "+uri.substring(20);
    return null;
  }

  public String inv(boolean withHeadings) {
    List<String> txlist = new ArrayList<String>();
    Map<String, List<ElementDefinitionConstraintComponent>> txmap = new HashMap<String, List<ElementDefinitionConstraintComponent>>();
    for (ElementDefinition ed : sd.getSnapshot().getElement()) {
      if (!"0".equals(ed.getMax())) {
        txlist.add(ed.getPath());
        txmap.put(ed.getPath(), ed.getConstraint());
      }
    }
    if (txlist.isEmpty())
      return "";
    else {
      StringBuilder b = new StringBuilder();
      if (withHeadings)
        b.append("<h4>"+translate("sd.inv", "Constraints")+"</h4>\r\n");       
      b.append("<table class=\"list\">\r\n");
      b.append("<tr><td width=\"60\"><b>"+translate("sd.inv", "Id")+"</b></td><td><b>"+translate("sd.inv", "Path")+"</b></td><td><b>"+translate("sd.inv", "Details")+"</b></td><td><b>"+translate("sd.inv", "Requirements")+"</b></td></tr>\r\n");
      for (String path : txlist)  {
        List<ElementDefinitionConstraintComponent> invs = txmap.get(path);
        for (ElementDefinitionConstraintComponent inv : invs) {
          b.append("<tr><td>").append(inv.getKey()).append("</td><td>").append(path).append("</td><td>").append(Utilities.escapeXml(gt(inv.getHumanElement())))
          .append("<br/>: ").append(Utilities.escapeXml(inv.getExpression())).append("</td><td>").append(Utilities.escapeXml(gt(inv.getRequirementsElement()))).append("</td></tr>\r\n");
        }
      }
      b.append("</table>\r\n");
      return b.toString();      
    }
  }

  public class StringPair {

    private String match;
    private String replace;

    public StringPair(String match, String replace) {
      this.match = match;
      this.replace = replace;
    }

  }

  public String dict(boolean incProfiledOut) throws Exception {
    int i = 1;
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"dict\">\r\n");

    List<StringPair> replacements = new ArrayList<StringPair>();
    for (ElementDefinition ec : sd.getSnapshot().getElement()) {
      if (incProfiledOut || !"0".equals(ec.getMax())) {
        if (isProfiledExtension(ec)) {
          StructureDefinition extDefn = context.fetchResource(StructureDefinition.class, ec.getType().get(0).getProfile().get(0).getValue());
          if (extDefn == null) {
            String title = ec.getId();
            b.append("  <tr><td colspan=\"2\" class=\"structure\"><span class=\"self-link-parent\"><a name=\""+ec.getId()+"\"> </a><span style=\"color: grey\">"+Integer.toString(i++)+".</span> <b>"+title+"</b>"+link(ec.getId())+"</span></td></tr>\r\n");
            generateElementInner(b, sd,  ec, 1, null);
          } else {
            String title = ec.getId();
            b.append("  <tr><td colspan=\"2\" class=\"structure\"><span class=\"self-link-parent\"><a name=\""+ec.getId()+"\"> </a>");
            if (ec.getId().endsWith("[x]")) {
              Set<String> tl = new HashSet<String>();
              for (TypeRefComponent tr : ec.getType()) {
                String tc = tr.getCode();
                if (!tl.contains(tc)) {
                  tl.add(tc);
                  String s = ec.getId().replace("[x]", Utilities.capitalize(tc));
                  b.append("<a name=\""+s+"\"> </a>");
                  replacements.add(new StringPair(ec.getId(), s));
                }
              }
            }
            b.append("<span style=\"color: grey\">"+Integer.toString(i++)+".</span> <b>"+title+"</b>"+link(ec.getId())+"</span></td></tr>\r\n");
            ElementDefinition valueDefn = getExtensionValueDefinition(extDefn);
            generateElementInner(b, sd, ec, valueDefn == null ? 2 : 3, valueDefn);
//            generateElementInner(b, extDefn, extDefn.getSnapshot().getElement().get(0), valueDefn == null ? 2 : 3, valueDefn);
          }
        } else {
          String title = ec.getId();
          b.append("  <tr><td colspan=\"2\" class=\"structure\"><span class=\"self-link-parent\"><a name=\""+ec.getId()+"\"> </a>");
          if (ec.getId().endsWith("[x]")) {
            Set<String> tl = new HashSet<String>();
            for (TypeRefComponent tr : ec.getType()) {
              String tc = tr.getCode();
              if (!tl.contains(tc)) {
                tl.add(tc);
                String s = ec.getId().replace("[x]", Utilities.capitalize(tc));
                b.append("<a name=\""+s+"\"> </a>");
                replacements.add(new StringPair(ec.getId(), s));
              }
            }
          } else if (ec.hasBase() && ec.getBase().getPath().endsWith("[x]")) {
            String s = nottail(ec.getId())+"."+tail(ec.getBase().getPath());
            replacements.add(new StringPair(ec.getId(), s));
            b.append("<a name=\""+s+"\"> </a>");
          } else if (!ec.getId().equals(ec.getPath())) {
            b.append("<a name=\""+ec.getPath()+"\"> </a>");
          }
          for (StringPair s : replacements)
            if (ec.getId().startsWith(s.match))
              b.append("<a name=\""+s.replace+ec.getId().substring(s.match.length())+"\"> </a>");
          b.append("<span style=\"color: grey\">"+Integer.toString(i++)+".</span> <b>"+title+"</b>"+link(ec.getId())+"</span></td></tr>\r\n");
          generateElementInner(b, sd, ec, 1, null);
          if (ec.hasSlicing())
            generateSlicing(sd, ec.getSlicing());
        }
      }
    }
    b.append("</table>\r\n");
    i++;
    return b.toString();
  }

  private String link(String id) {
    return "<a href=\"#"+id+"\" title=\"link to here\" class=\"self-link\"><svg viewBox=\"0 0 1792 1792\" width=\"16\" class=\"self-link\" height=\"16\"><path d=\"M1520 1216q0-40-28-68l-208-208q-28-28-68-28-42 0-72 32 3 3 19 18.5t21.5 21.5 15 19 13 25.5 3.5 27.5q0 40-28 68t-68 28q-15 0-27.5-3.5t-25.5-13-19-15-21.5-21.5-18.5-19q-33 31-33 73 0 40 28 68l206 207q27 27 68 27 40 0 68-26l147-146q28-28 28-67zm-703-705q0-40-28-68l-206-207q-28-28-68-28-39 0-68 27l-147 146q-28 28-28 67 0 40 28 68l208 208q27 27 68 27 42 0 72-31-3-3-19-18.5t-21.5-21.5-15-19-13-25.5-3.5-27.5q0-40 28-68t68-28q15 0 27.5 3.5t25.5 13 19 15 21.5 21.5 18.5 19q33-31 33-73zm895 705q0 120-85 203l-147 146q-83 83-203 83-121 0-204-85l-206-207q-83-83-83-203 0-123 88-209l-88-88q-86 88-208 88-120 0-204-84l-208-208q-84-84-84-204t85-203l147-146q83-83 203-83 121 0 204 85l206 207q83 83 83 203 0 123-88 209l88 88q86-88 208-88 120 0 204 84l208 208q84 84 84 204z\" fill=\"navy\"></path></svg></a>";
  }

  private boolean isProfiledExtension(ElementDefinition ec) {
    return ec.getType().size() == 1 && "Extension".equals(ec.getType().get(0).getCode()) && ec.getType().get(0).hasProfile();
  }


  private String makePathLink(ElementDefinition element) {
    return element.getId();
  }

  private ElementDefinition getExtensionValueDefinition(StructureDefinition extDefn) {
    for (ElementDefinition ed : extDefn.getSnapshot().getElement()) {
      if (ed.getPath().startsWith("Extension.value"))
        return ed;
    }
    return null;
  }

  private void generateElementInner(StringBuilder b, StructureDefinition profile, ElementDefinition d, int mode, ElementDefinition value) throws Exception {
    tableRowNE(b, translate("sd.dict", "Definition"), null, processMarkdown(profile.getName(), d.getDefinitionElement()));
    tableRowNE(b, translate("sd.dict", "Note"), null, businessIdWarning(profile.getName(), tail(d.getPath())));
    tableRow(b, translate("sd.dict", "Control"), "conformance-rules.html#conformance", describeCardinality(d) + summariseConditions(d.getCondition()));
    tableRowNE(b, translate("sd.dict", "Binding"), "terminologies.html", describeBinding(profile, d, d.getPath()));
    if (d.hasContentReference())
      tableRow(b, translate("sd.dict", "Type"), null, "See "+d.getContentReference().substring(1));
    else
      tableRowNE(b, translate("sd.dict", "Type"), "datatypes.html", describeTypes(d.getType()) + processSecondary(mode, value));
    if (d.getPath().endsWith("[x]"))
      tableRowNE(b, translate("sd.dict", "[x] Note"), null, translate("sd.dict", "See %sChoice of Data Types%s for further information about how to use [x]", "<a href=\""+prefix+"formats.html#choice\">", "</a>"));
    tableRow(b, translate("sd.dict", "Is Modifier"), "conformance-rules.html#ismodifier", displayBoolean(d.getIsModifier()));
    tableRow(b, translate("sd.dict", "Must Support"), "conformance-rules.html#mustSupport", displayBoolean(d.getMustSupport()));
    tableRowNE(b, translate("sd.dict", "Requirements"),  null, processMarkdown(profile.getName(), d.getRequirementsElement()));
    tableRowHint(b, translate("sd.dict", "Alternate Names"), translate("sd.dict", "Other names by which this resource/element may be known"), null, describeAliases(d.getAlias()));
    tableRowNE(b, translate("sd.dict", "Comments"),  null, processMarkdown(profile.getName(), d.getCommentElement()));
    tableRow(b, translate("sd.dict", "Max Length"), null, !d.hasMaxLengthElement() ? null : toStr(d.getMaxLength()));
    tableRowNE(b, translate("sd.dict", "Default Value"), null, encodeValue(d.getDefaultValue()));
    tableRowNE(b, translate("sd.dict", "Meaning if Missing"), null, d.getMeaningWhenMissing());
    tableRowNE(b, translate("sd.dict", "Fixed Value"), null, encodeValue(d.getFixed()));
    tableRowNE(b, translate("sd.dict", "Pattern Value"), null, encodeValue(d.getPattern()));
    tableRowNE(b, translate("sd.dict", "Example"), null, encodeValues(d.getExample()));
    tableRowNE(b, translate("sd.dict", "Invariants"), null, invariants(d.getConstraint()));
    tableRow(b, translate("sd.dict", "LOINC Code"), null, getMapping(profile, d, LOINC_MAPPING));
    tableRow(b, translate("sd.dict", "SNOMED-CT Code"), null, getMapping(profile, d, SNOMED_MAPPING));
  }

  private void generateSlicing(StructureDefinition profile, ElementDefinitionSlicingComponent slicing) throws IOException {
    StringBuilder b = new StringBuilder();
    if (slicing.getOrdered())
      b.append("<li>"+translate("sd.dict", "ordered")+"</li>");
    else
      b.append("<li>"+translate("sd.dict", "unordered")+"</li>");
    if (slicing.hasRules())
      b.append("<li>"+slicing.getRules().getDisplay()+"</li>");
    if (!slicing.getDiscriminator().isEmpty()) {
      b.append("<li>"+translate("sd.dict", "discriminators")+": ");
      boolean first = true;
      for (ElementDefinitionSlicingDiscriminatorComponent s : slicing.getDiscriminator()) {
        if (first)
          first = false;
        else
          b.append(", ");
        b.append(s.getType().toCode()+":"+s.getPath());
      }
      b.append("</li>");
    }
    tableRowNE(b, ""+translate("sd.dict", "Slicing"), "profiling.html#slicing", translate("sd.dict", "This element introduces a set of slices. The slicing rules are")+": <ul> "+b.toString()+"</ul>");
  }

  private void tableRow(StringBuilder b, String name, String defRef, String value) throws IOException {
    if (value != null && !"".equals(value)) {
      if (defRef != null) 
        b.append("  <tr><td><a href=\""+prefix+defRef+"\">"+name+"</a></td><td>"+Utilities.escapeXml(value)+"</td></tr>\r\n");
      else
        b.append("  <tr><td>"+name+"</td><td>"+Utilities.escapeXml(value)+"</td></tr>\r\n");
    }
  }


  private void tableRowHint(StringBuilder b, String name, String hint, String defRef, String value) throws IOException {
    if (value != null && !"".equals(value)) {
      if (defRef != null) 
        b.append("  <tr><td><a href=\""+prefix+defRef+"\" title=\""+Utilities.escapeXml(hint)+"\">"+name+"</a></td><td>"+Utilities.escapeXml(value)+"</td></tr>\r\n");
      else
        b.append("  <tr><td title=\""+Utilities.escapeXml(hint)+"\">"+name+"</td><td>"+Utilities.escapeXml(value)+"</td></tr>\r\n");
    }
  }


  private void tableRowNE(StringBuilder b, String name, String defRef, String value) throws IOException {
    if (value != null && !"".equals(value))
      if (defRef != null) 
        b.append("  <tr><td><a href=\""+prefix+defRef+"\">"+name+"</a></td><td>"+value+"</td></tr>\r\n");
      else
        b.append("  <tr><td>"+name+"</td><td>"+value+"</td></tr>\r\n");
  }

  private String head(String path) {
    if (path.contains("."))
      return path.substring(0, path.indexOf("."));
    else
      return path;
  }

  private String tail(String path) {
    if (path.contains("."))
      return path.substring(path.lastIndexOf(".")+1);
    else
      return path;
  }

  private String nottail(String path) {
    if (path.contains("."))
      return path.substring(0, path.lastIndexOf("."));
    else
      return path;
  }

  private String businessIdWarning(String resource, String name) {
    if (name.equals("identifier"))
      return ""+translate("sd.dict", "This is a business identifier, not a resource identifier (see %sdiscussion%s)", "<a href=\""+prefix+"resource.html#identifiers\">", "</a>");
    if (name.equals("version")) // && !resource.equals("Device"))
      return ""+translate("sd.dict", "This is a business versionId, not a resource version id (see %sdiscussion%s)", "<a href=\""+prefix+"resource.html#versions\">", "</a>");
    return null;
  }

  private String describeCardinality(ElementDefinition d) {
    if (!d.hasMax() && d.getMinElement() == null)
      return "";
    else if (d.getMax() == null)
      return toStr(d.getMin()) + "..?";
    else
      return toStr(d.getMin()) + ".." + d.getMax();
  }

  private String summariseConditions(List<IdType> conditions) {
    if (conditions.isEmpty())
      return "";
    else {
      CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
      for (IdType t : conditions)
        b.append(t.getValue());
      return " "+translate("sd.dict", "This element is affected by the following invariants")+": "+b.toString();
    }
  }

  private String describeTypes(List<TypeRefComponent> types) throws Exception {
    if (types.isEmpty())
      return "";

    StringBuilder b = new StringBuilder();
    if (types.size() == 1)
      describeType(b, types.get(0));
    else {
      boolean first = true;
      b.append(translate("sd.dict", "Choice of")+": ");
      for (TypeRefComponent t : types) {
        if (first)
          first = false;
        else
          b.append(", ");
        describeType(b, t);
      }
    }
    return b.toString();
  }

  private void describeType(StringBuilder b, TypeRefComponent t) throws Exception {
    if (t.getCode() == null)
      return;
    if (t.getCode().startsWith("="))
      return;

    if (t.getCode().startsWith("xs:")) {
      b.append(t.getCode());
    } else {
      String s = igp.getLinkFor(sd.getUserString("path"), t.getCode());
      if (s != null) {
        b.append("<a href=\"");
        //    GG 13/12/2016 - I think that this is always wrong now. 
        //      if (!s.startsWith("http:") && !s.startsWith("https:") && !s.startsWith(".."))
        //        b.append(prefix);         
        b.append(s);
        if (!s.contains(".html")) {
          //     b.append(".html#");
          //     String type = t.getCode();
          //     if (type.equals("*"))
          //       b.append("open");
          //     else 
          //       b.append(t.getCode());
        }
        b.append("\">");
        b.append(t.getCode());
        b.append("</a>");
      } else 
        b.append(t.getCode());
    }
    if (t.hasProfile()) {
      b.append("(");
      StructureDefinition p = context.fetchResource(StructureDefinition.class, t.getProfile().get(0).getValue());
      if (p == null)
        b.append(t.getProfile());
      else {
        String pth = p.getUserString("path");
        b.append("<a href=\""+pth+"\" title=\""+t.getProfile()+"\">");
        b.append(p.getName());
        b.append("</a>");
      }
      b.append(")");
    }
    if (t.hasTargetProfile()) {
      b.append("(");
      boolean first = true;
      for (CanonicalType tp : t.getTargetProfile()) {
        if (first)
          first = false;
        else
          b.append(" | ");
        StructureDefinition p = context.fetchResource(StructureDefinition.class, tp.getValue());
        if (p == null)
          b.append(tp.getValue());
        else {
          String pth = p.getUserString("path");
          b.append("<a href=\""+pth+"\" title=\""+tp.getValue()+"\">");
          b.append(p.getName());
          b.append("</a>");
        }
      }
      b.append(")");
    }
  }

  private String processSecondary(int mode, ElementDefinition value) throws Exception {
    switch (mode) {
    case 1 : return "";
    case 2 : return "  ("+translate("sd.dict", "Complex Extension")+")";
    case 3 : return "  ("+translate("sd.dict", "Extension Type")+": "+describeTypes(value.getType())+")";
    default: return "";
    }
  }

  private String displayBoolean(boolean mustUnderstand) {
    if (mustUnderstand)
      return "true";
    else
      return null;
  }

  private String invariants(List<ElementDefinitionConstraintComponent> constraints) {
    if (constraints.isEmpty())
      return null;
    StringBuilder s = new StringBuilder();
    if (constraints.size() > 0) {
      s.append("<b>"+translate("sd.dict", "Defined on this element")+"</b><br/>\r\n");
      List<String> ids = new ArrayList<String>();
      for (ElementDefinitionConstraintComponent id : constraints)
        ids.add(id.hasKey() ? id.getKey() : id.toString());
      Collections.sort(ids);
      boolean b = false;
      for (String id : ids) {
        ElementDefinitionConstraintComponent inv = getConstraint(constraints, id);
        if (b)
          s.append("<br/>");
        else
          b = true;
        s.append("<b title=\""+translate("sd.dict", "Formal Invariant Identifier")+"\">"+id+"</b>: "+Utilities.escapeXml(gt(inv.getHumanElement()))+" (: "+Utilities.escapeXml(inv.getExpression())+")");
      }
    }

    return s.toString();
  }

  private ElementDefinitionConstraintComponent getConstraint(List<ElementDefinitionConstraintComponent> constraints, String id) {
    for (ElementDefinitionConstraintComponent c : constraints) {
      if (c.hasKey() && c.getKey().equals(id))
        return c;
      if (!c.hasKey() && c.toString().equals(id))
        return c;
    } 
    return null;
  }

  private String describeBinding(StructureDefinition sd, ElementDefinition d, String path) throws Exception {
    if (!d.hasBinding())
      return null;
    else {
      // return TerminologyNotesGenerator.describeBinding(prefix, d.getBinding(), page);
      ElementDefinitionBindingComponent def = d.getBinding();
      if (!def.hasValueSet()) 
        return def.getDescription();
      BindingResolution br = igp.resolveBinding(sd, def, path);
      String defDesc = def.getDescription()==null ? "" : Utilities.escapeXml(def.getDescription()) + "<br/>";
      String s = defDesc+conf(def)+ "<a href=\""+br.url+"\">"+Utilities.escapeXml(br.display)+"</a>"+confTail(def);
      if (def.hasExtension(ToolingExtensions.EXT_MAX_VALUESET)) {
        br = igp.resolveBinding(sd, ToolingExtensions.readStringExtension(def, ToolingExtensions.EXT_MAX_VALUESET), path);
        s = s + "<br>";
        s = s + "<a style=\"font-weight:bold\" title=\"Max Value Set Extension\" href=\""+prefix+"extension-elementdefinition-maxvalueset.html\">Max Binding</a>: ";             
        s = s + (br.url == null ? Utilities.escapeXml(br.display) : "<a href=\""+ (Utilities.isAbsoluteUrl(br.url) || !igp.prependLinks() ? br.url : prefix+br.url)+"\">"+Utilities.escapeXml(br.display)+"</a>");
      }
      if (def.hasExtension(ToolingExtensions.EXT_MIN_VALUESET)) {
        br = igp.resolveBinding(sd, ToolingExtensions.readStringExtension(def, ToolingExtensions.EXT_MIN_VALUESET), path);
        s = s + "<br>";
        s = s + "<a style=\"font-weight:bold\" title=\"Min Value Set Extension\" href=\""+prefix+"extension-elementdefinition-minvalueset.html\">Min Binding</a>: ";             
        s = s + (br.url == null ? Utilities.escapeXml(br.display) : "<a href=\""+ (Utilities.isAbsoluteUrl(br.url) || !igp.prependLinks() ? br.url : prefix+br.url)+"\">"+Utilities.escapeXml(br.display)+"</a>");
      }      
      return s;
    }
  }

  private String confTail(ElementDefinitionBindingComponent def) {
    if (def.getStrength() == BindingStrength.EXTENSIBLE)
      return "; "+translate("sd.dict", "other codes may be used where these codes are not suitable");
    else
      return "";
  }

  private String conf(ElementDefinitionBindingComponent def) {
    switch (def.getStrength()) {
    case EXAMPLE:
      return ""+translate("sd.dict", "For example codes, see ");
    case PREFERRED:
      return ""+translate("sd.dict", "The codes SHOULD be taken from ");
    case EXTENSIBLE:
      return ""+translate("sd.dict", "The codes SHALL be taken from ");
    case REQUIRED:
      return ""+translate("sd.dict", "The codes SHALL be taken from ");
    default:
      return ""+"??";
    }
  }

  private String encodeValues(List<ElementDefinitionExampleComponent> examples) throws Exception {
    StringBuilder b = new StringBuilder();
    boolean first = false;
    for (ElementDefinitionExampleComponent ex : examples) {
      if (first)
        first = false;
      else
        b.append("<br/>");
      b.append("<b>"+Utilities.escapeXml(ex.getLabel())+"</b>:"+encodeValue(ex.getValue())+"\r\n");
    }
    return b.toString();
    
  }
  private String encodeValue(Type value) throws Exception {
    if (value == null || value.isEmpty())
      return null;
    if (value instanceof PrimitiveType)
      return Utilities.escapeXml(((PrimitiveType) value).asStringValue());

    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    XmlParser parser = new XmlParser();
    parser.setOutputStyle(OutputStyle.PRETTY);
    parser.compose(bs, null, value);
    String[] lines = bs.toString().split("\\r?\\n");
    StringBuilder b = new StringBuilder();
    for (String s : lines) {
      if (!Utilities.noString(s) && !s.startsWith("<?")) { // eliminate the xml header 
        b.append(Utilities.escapeXml(s).replace(" ", "&nbsp;")+"<br/>");
      }
    }
    return b.toString();  

  }

  private String getMapping(StructureDefinition profile, ElementDefinition d, String uri) {
    String id = null;
    for (StructureDefinitionMappingComponent m : profile.getMapping()) {
      if (m.hasUri() && m.getUri().equals(uri))
        id = m.getIdentity();
    }
    if (id == null)
      return null;
    for (ElementDefinitionMappingComponent m : d.getMapping()) {
      if (m.getIdentity().equals(id))
        return m.getMap();
    }
    return null;
  }


  private String describeAliases(List<StringType> synonym) {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (StringType s : synonym) 
      b.append(gt(s));
    return b.toString();
  }

  public String mappings(boolean complete) {
    if (sd.getMapping().isEmpty())
      return "<p>"+translate("sd.maps", "No Mappings")+"</p>";
    else {
      StringBuilder s = new StringBuilder();
      for (StructureDefinitionMappingComponent map : sd.getMapping()) {

        String url = getUrlForUri(map.getUri());
        if (url == null)
          s.append("<a name=\""+map.getIdentity() +"\"> </a><h3>"+translate("sd.maps", "Mappings for %s (%s)", Utilities.escapeXml(gt(map.getNameElement())), map.getUri())+"</h3>");
        else
          s.append("<a name=\""+map.getIdentity() +"\"> </a><h3>"+translate("sd.maps", "Mappings for %s (<a href=\""+url+"\">%s</a>)", Utilities.escapeXml(gt(map.getNameElement())), map.getUri())+"</h3>");
        if (map.hasComment())
          s.append("<p>"+Utilities.escapeXml(gt(map.getCommentElement()))+"</p>");
//        else if (specmaps != null && preambles.has(map.getUri()))   
//          s.append(preambles.get(map.getUri()).getAsString());

        s.append("<table class=\"grid\">\r\n");

        s.append(" <tr><td colspan=\"3\"><b>"+Utilities.escapeXml(gt(sd.getNameElement()))+"</b></td></tr>\r\n");
        String path = null;
        for (ElementDefinition e : sd.getSnapshot().getElement()) {
          if (path == null || !e.getPath().startsWith(path)) {
            path = null;
            if (e.hasMax() && e.getMax().equals("0") || !(complete || hasMappings(e, map))) {
              path = e.getPath()+".";
            } else
              genElement(s, e, map.getIdentity());
          }
        }
        s.append("</table>\r\n");
      }
      return s.toString();
    }
  }

  private String getUrlForUri(String uri) {
    if (Utilities.existsInList(uri,"http://clinicaltrials.gov",
        "http://github.com/MDMI/ReferentIndexContent",
        "http://loinc.org",
        "http://metadata-standards.org/11179/",
        "http://openehr.org",
        "http://snomed.info/sct",
        "http://wiki.ihe.net/index.php?title=Data_Element_Exchange",
        "http://www.cda-adc.ca/en/services/cdanet/",
        "http://www.cdisc.org/define-xml",
        "http://www.fda.gov/MedicalDevices/DeviceRegulationandGuidance/UniqueDeviceIdentification/default.htm",
        "http://www.hl7.org/implement/standards/product_brief.cfm?product_id=378",
        "http://www.ietf.org/rfc/rfc2445.txt",
        "http://www.omg.org/spec/ServD/1.0/",
        "http://www.pharmacists.ca/",
        "http://www.w3.org/ns/prov"))
      return uri;
    if ("http://hl7.org/fhir/auditevent".equals(uri)) return "http://hl7.org/fhir/auditevent.html";
    if ("http://hl7.org/fhir/provenance".equals(uri)) return "http://hl7.org/fhir/provenance.html";
    if ("http://hl7.org/fhir/w5".equals(uri)) return "http://hl7.org/fhir/w5.html";
    if ("http://hl7.org/fhir/workflow".equals(uri)) return "http://hl7.org/fhir/workflow.html";
    if ("http://hl7.org/v2".equals(uri)) return "http://hl7.org/comparison-v2.html";
    if ("http://hl7.org/v3".equals(uri)) return "http://hl7.org/comparison-v3.html";
    if ("http://hl7.org/v3/cda".equals(uri)) return "http://hl7.org/comparison-cda.html";
    return null;
  }

  private boolean hasMappings(ElementDefinition e, StructureDefinitionMappingComponent map) {
    ElementDefinitionMappingComponent m = getMap(e, map.getIdentity());
    if (m != null)
      return true;
    int i = sd.getSnapshot().getElement().indexOf(e)+1;
    while (i < sd.getSnapshot().getElement().size()) {
      ElementDefinition t =  sd.getSnapshot().getElement().get(i);
      if (t.getPath().startsWith(e.getPath()+".")) {
        m = getMap(t, map.getIdentity());
        if (m != null)
          return true;
      }
      i++;
    }
    return false;
  }

  private void genElement(StringBuilder s, ElementDefinition e, String id) {
    s.append(" <tr><td>");
    boolean root = true;
    for (char c : e.getPath().toCharArray()) 
      if (c == '.') {
        s.append("&nbsp;");
        s.append("&nbsp;");
        s.append("&nbsp;");
        root = false;
      }
    if (root)
      s.append(e.getPath());
    else
      s.append(tail(e.getPath()));
    if (e.hasSliceName()) {
      s.append(" (");
      s.append(Utilities.escapeXml(e.getSliceName()));
      s.append(")");
    }
    s.append("</td>");
    ElementDefinitionMappingComponent m = getMap(e, id);
    if (m == null)
      s.append("<td></td>");
    else
      s.append("<td>"+Utilities.escapeXml(m.getMap())+"</td>");
    s.append(" </tr>\r\n");
  }


  private ElementDefinitionMappingComponent getMap(ElementDefinition e, String id) {
    for (ElementDefinitionMappingComponent m : e.getMapping()) {
      if (m.getIdentity().equals(id))
        return m;
    }
    return null;
  }

  public String header() throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<p>\r\n");
    b.append(translate("sd.header", "The official URL for this profile is:")+"\r\n");
    b.append("</p>\r\n");
    b.append("<pre>"+sd.getUrl()+"</pre>\r\n");
    b.append("<p>\r\n");
    b.append(processMarkdown("description", sd.getDescriptionElement()));
    b.append("</p>\r\n");
    if (sd.getDerivation() == TypeDerivationRule.CONSTRAINT) {
      b.append("<p>\r\n");
      StructureDefinition sdb = context.fetchResource(StructureDefinition.class, sd.getBaseDefinition());
      if (sdb != null)
        b.append(translate("sd.header", "This profile builds on")+" <a href=\""+sdb.getUserString("path")+"\">"+gt(sdb.getNameElement())+"</a>.");
      else
        b.append(translate("sd.header", "This profile builds on")+" "+sd.getBaseDefinition()+".");
      b.append("</p>\r\n");
    }
    b.append("<p>\r\n");
    b.append(translate("sd.header", "This profile was published on %s as a %s by %s.\r\n", toStr(sd.getDate()), egt(sd.getStatusElement()), gt(sd.getPublisherElement())));
    b.append("</p>\r\n");
    return b.toString();
  }

  public String exampleList(List<FetchedFile> fileList) {
    StringBuilder b = new StringBuilder();
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        for (String p : r.getProfiles()) {
          if (sd.getUrl().equals(p)) {
            String name = r.getTitle();
            if (Utilities.noString(name))
              name = "example";
            String ref = igp.getLinkFor(r);
            b.append(" <li><a href=\""+ref+"\">"+Utilities.escapeXml(name)+"</a></li>\r\n");
          }
        }
      }
    }
    return b.toString();
  }

  public String span(boolean onlyConstraints, String canonical, Set<String> outputTracker) throws IOException, FHIRException {
    return new XhtmlComposer(XhtmlComposer.HTML).compose(utils.generateSpanningTable(sd, destDir, onlyConstraints, canonical, outputTracker));
  }

  public String pseudoJson() throws Exception {
    StringBuilder b = new StringBuilder();
    ElementDefinition root = sd.getSnapshot().getElement().get(0);
    String rn = sd.getSnapshot().getElement().get(0).getPath();
    b.append(" // <span style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(sd.getTitle()) + "</span>\r\n {\r\n");
    if (sd.getKind() == StructureDefinitionKind.RESOURCE)
      b.append("   \"resourceType\" : \""+sd.getType()+"\",\r\n");
    
    List<ElementDefinition> children = getChildren(sd.getSnapshot().getElement(), sd.getSnapshot().getElement().get(0));
    boolean complex = isComplex(children);
    if (!complex && !hasExtensionChild(children))
      b.append("  // from Element: <a href=\""+prefix+"extensibility.html\">extension</a>\r\n");
    
    int c = 0;
    int l = lastChild(children);
    boolean extDone = false; // todo: investigate why getChildren is returning slices...
    for (ElementDefinition child : children)
      if (isExtension(child)) {
        if (!extDone)
          generateCoreElemExtension(b, sd.getSnapshot().getElement(), child, children, 2, rn, false, child.getType().get(0), ++c == l, complex);
        extDone = true;
      } else if (child.hasSlicing())
        generateCoreElemSliced(b, sd.getSnapshot().getElement(), child, children, 2, rn, false, child.getType().get(0), ++c == l, complex);
      else if (wasSliced(child, children))
        ; // nothing
      else if (child.getType().size() == 1 || allTypesAreReference(child))
        generateCoreElem(b, sd.getSnapshot().getElement(), child, 2, rn, false, child.getType().get(0), ++c == l, complex);
      else {
        if (!"0".equals(child.getMax())) {
          b.append("<span style=\"color: Gray\">// "+tail(child.getPath())+": <span style=\"color: navy; opacity: 0.8\">" +Utilities.escapeXml(child.getShort()) + "</span>. One of these "+Integer.toString(child.getType().size())+":</span>\r\n");
          for (TypeRefComponent t : child.getType())
            generateCoreElem(b, sd.getSnapshot().getElement(), child, 2, rn, false, t, ++c == l, false);
        }
      }
    b.append("  }\r\n");
    return b.toString();
  }

  private boolean allTypesAreReference(ElementDefinition child) {
    for (TypeRefComponent tr : child.getType()) {
      if (!"Reference".equals(tr.getCode()))
          return false;
    }
    return !child.getType().isEmpty();
  }

  private boolean isExtension(ElementDefinition child) {
    return child.getPath().endsWith(".extension") || child.getPath().endsWith(".modifierExtension");
  }

  private boolean hasExtensionChild(List<ElementDefinition> children) {
    for (ElementDefinition ed : children)
      if (ed.getPath().endsWith(".extension"))
        return true;
    return false;
  }

  private List<ElementDefinition> getChildren(List<ElementDefinition> elements, ElementDefinition elem) {
    int i = elements.indexOf(elem)+1;
    List<ElementDefinition> res = new ArrayList<ElementDefinition>();
    while (i < elements.size()) {
      if (elements.get(i).getPath().startsWith(elem.getPath()+".")) {
        String tgt = elements.get(i).getPath();
        String src = elem.getPath();
        if (tgt.startsWith(src+".")) {
          if (!tgt.substring(src.length()+1).contains(".")) 
            res.add(elements.get(i));
        }
      } else
        return res;
      i++;
    }
    return res;
  }

  private boolean isComplex(List<ElementDefinition> children) {
    int c = 0;
    for (ElementDefinition child : children) {
      if (child.getPath().equals("Extension.extension"))
        c++;
    }
    return c > 1;
  }

  private int lastChild(List<ElementDefinition> extchildren) {
    int l = extchildren.size();
    while (l > 0 && "0".equals(extchildren.get(l-1).getMax()))
      l--;
    return l;
  }

  @SuppressWarnings("rawtypes")
  private void generateCoreElem(StringBuilder b, List<ElementDefinition> elements, ElementDefinition elem, int indent, String pathName, boolean asValue, TypeRefComponent type, boolean last, boolean complex) throws Exception {
    if (elem.getPath().endsWith(".id") && elem.getPath().lastIndexOf('.') > elem.getPath().indexOf('.'))
      return;
    if (!complex && elem.getPath().endsWith(".extension"))
      return;
    
    if (elem.getMax().equals("0"))
      return;
    
    String indentS = "";
    for (int i = 0; i < indent; i++) {
      indentS += "  ";
    }
    b.append(indentS);

    
    
    List<ElementDefinition> children = getChildren(elements, elem);
    String name =  tail(elem.getPath());
    String en = asValue ? "value[x]" : name;
    if (en.contains("[x]"))
      en = en.replace("[x]", upFirst(type.getCode()));
    boolean unbounded = elem.hasBase() && elem.getBase().hasMax() ? elem.getBase().getMax().equals("*") : "*".equals(elem.getMax());
    String defPage = igp.getLinkForProfile(sd, sd.getUrl());
    // 1. name
    if (defPage.contains("|"))
      defPage = defPage.substring(0, defPage.indexOf("|"));
    b.append("\"<a href=\"" + (defPage + "#" + pathName + "." + en)+ "\" title=\"" + Utilities .escapeXml(getEnhancedDefinition(elem)) 
        + "\" class=\"dict\"><span style=\"text-decoration: underline\">"+en+"</span></a>\" : ");
    
    // 2. value
    boolean delayedClose = false;
    if (unbounded) 
      b.append("[");

    if (type == null || children.size() > 0) {
      // inline definition
      assert(children.size() > 0);
      b.append("{");
      delayedClose = true;
    } else if (type.getCode() == null) {
      // For the 'value' element of simple types
      b.append("&lt;<span style=\"color: darkgreen\">n/a</span>&gt;");
    } else if (isPrimitive(type.getCode())) {
      if (!(type.getCode().equals("integer") || type.getCode().equals("boolean") || type.getCode().equals("decimal")))
        b.append("\"");
      if (elem.hasFixed()) 
        b.append(Utilities.escapeJson(((PrimitiveType) elem.getFixed()).asStringValue()));
      else {
        String l = getSrcFile(type.getCode());
        if (l == null)
            b.append("&lt;<span style=\"color: darkgreen\">" + type.getCode()+ "</span>&gt;");
        else
          b.append("&lt;<span style=\"color: darkgreen\"><a href=\"" +suffix(l, type.getCode()) + "\">" + type.getCode()+ "</a></span>&gt;");
      }
      if (!(type.getCode().equals("integer") || type.getCode().equals("boolean") || type.getCode().equals("decimal")))
        b.append("\"");
    } else {
      b.append("{");
      b.append("<span style=\"color: darkgreen\"><a href=\"" +suffix(getSrcFile(type.getCode()), type.getCode()) + "\">" + type.getCode()+ "</a></span>");
      if (type.hasProfile()) {
        StructureDefinition tsd = context.fetchResource(StructureDefinition.class, type.getProfile().get(0).getValue());
        if (tsd != null)
          b.append(" (as <span style=\"color: darkgreen\"><a href=\"" + tsd.getUserString("path")+ "#"+tsd.getType() + "\">" + tsd.getName()+ "</a></span>)");
        else 
          b.append(" (as <span style=\"color: darkgreen\">"+type.getProfile()+ "</span>)");
      }
      if (type.hasTargetProfile()) {
        if (type.getTargetProfile().get(0).getValue().startsWith("http://hl7.org/fhir/StructureDefinition/")) {
          String t = type.getTargetProfile().get(0).getValue().substring(40);
          if (hasType(t))
            b.append("(<span style=\"color: darkgreen\"><a href=\"" +suffix(getSrcFile(t), t) + "\">" + t+ "</a></span>)");
          else if (hasResource(t))
            b.append("(<span style=\"color: darkgreen\"><a href=\"" + prefix+ t.toLowerCase()+ ".html\">" + t+ "</a></span>)");
          else
            b.append("("+t+")");
        } else
          b.append("("+type.getTargetProfile()+")");
      }
      b.append("}");
    } 

    if (!delayedClose) {
      if (unbounded) {
        b.append("]");
      }
      if (!last)
        b.append(",");
    }
    
    b.append(" <span style=\"color: Gray\">//</span>");

    // 3. optionality
    writeCardinality(unbounded, b, elem);

    // 4. doco
    if (!elem.hasFixed()) {
      if (elem.hasBinding() && elem.getBinding().hasValueSet()) {
        ValueSet vs = context.fetchResource(ValueSet.class, elem.getBinding().getValueSet());
        if (vs != null)
          b.append(" <span style=\"color: navy; opacity: 0.8\"><a href=\""+prefix+vs.getUserData("filename")+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShort()) + "</a></span>");
        else
          b.append(" <span style=\"color: navy; opacity: 0.8\"><a href=\""+elem.getBinding().getValueSet()+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShort()) + "</a></span>");          
      } else
        b.append(" <span style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(elem.getShort()) + "</span>");
    }

    b.append("\r\n");

    if (delayedClose) {
      int c = 0;
      int l = lastChild(children);
      boolean extDone = false;
      for (ElementDefinition child : children) {
        if (isExtension(child)) {
          if (!extDone)
            generateCoreElemExtension(b, sd.getSnapshot().getElement(), child, children, indent + 1, pathName + "." + name, false, child.getType().get(0), ++c == l, complex);
          extDone = true;
        } else if (child.hasSlicing())
          generateCoreElemSliced(b, sd.getSnapshot().getElement(), child, children, indent + 1, pathName + "." + name, false,  child.hasType() ? child.getType().get(0) : null, ++c == l, complex);
        else if (wasSliced(child, children))
          ; // nothing
        else if (child.getType().size() == 1 || allTypesAreReference(child))
          generateCoreElem(b, elements, child, indent + 1, pathName + "." + name, false, child.getType().get(0), ++c == l, false);
        else {
          if (!"0".equals(child.getMax())) {
            b.append("<span style=\"color: Gray\">// value[x]: <span style=\"color: navy; opacity: 0.8\">" +Utilities.escapeXml(child.getShort()) + "</span>. One of these "+Integer.toString(child.getType().size())+":</span>\r\n");
            for (TypeRefComponent t : child.getType())
              generateCoreElem(b, elements, child, indent + 1, pathName + "." + name, false, t, ++c == l, false);
          }
        }
      }
      b.append(indentS);
      b.append("}");
      if (unbounded)
        b.append("]");
      if (!last)
        b.append(",");
      b.append("\r\n");
    }
  }

  private String suffix(String link, String suffix) {
    if (link.contains("|"))
      link = link.substring(0, link.indexOf("|"));
    if (link.contains("#"))
      return link;
    else
      return link+"#"+suffix;
  }

  private void generateCoreElemSliced(StringBuilder b, List<ElementDefinition> elements, ElementDefinition elem, List<ElementDefinition> children, int indent, String pathName, boolean asValue, TypeRefComponent type, boolean last, boolean complex) throws Exception {
    if (elem.getMax().equals("0"))
      return;

    String name =  tail(elem.getPath());
    String en = asValue ? "value[x]" : name;
    if (en.contains("[x]"))
      en = en.replace("[x]", upFirst(type.getCode()));
    boolean unbounded = elem.hasMax() && elem.getMax().equals("*");

    String indentS = "";
    for (int i = 0; i < indent; i++) {
      indentS += "  ";
    }
    String defPage = igp.getLinkForProfile(sd, sd.getUrl());
    if (defPage.contains("|"))
      defPage = defPage.substring(0, defPage.indexOf("|"));
    b.append(indentS);
    b.append("\"<a href=\"" + (defPage + "#" + pathName + "." + en)+ "\" title=\"" + Utilities .escapeXml(getEnhancedDefinition(elem)) 
    + "\" class=\"dict\"><span style=\"text-decoration: underline\">"+en+"</span></a>\" : ");
    b.append("[ <span style=\"color: navy\">"+describeSlicing(elem.getSlicing())+"</span>");
//    b.append(" <span style=\"color: Gray\">//</span>");
//    writeCardinality(elem);
    b.append("\r\n");
    
    List<ElementDefinition> slices = getSlices(elem, children);
    int c = 0;
    for (ElementDefinition slice : slices) {
      b.append(indentS+"  ");
      b.append("{ // <span style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(slice.getShort()) + "</span>");
      writeCardinality(unbounded, b, slice);
      b.append("\r\n");
      
      List<ElementDefinition> extchildren = getChildren(elements, slice);
      boolean extcomplex = isComplex(extchildren) && complex;
      if (!extcomplex && !hasExtensionChild(extchildren)) {
        b.append(indentS+"  ");
        b.append("  // from Element: <a href=\""+prefix+"extensibility.html\">extension</a>\r\n");
      }
      
      int cc = 0;
      int l = lastChild(extchildren);
      for (ElementDefinition child : extchildren)
        if (child.hasSlicing())
          generateCoreElemSliced(b, elements, child, children, indent+2, pathName+"."+en, false, child.getType().isEmpty() ? null : child.getType().get(0), ++cc == l, extcomplex);
        else if (wasSliced(child, children))
          ; // nothing
        else if (child.getType().size() == 1)
          generateCoreElem(b, elements, child, indent+2, pathName+"."+en, false, child.getType().get(0), ++cc == l, extcomplex);
        else {
          b.append("<span style=\"color: Gray\">// value[x]: <span style=\"color: navy; opacity: 0.8\">" +Utilities.escapeXml(child.getShort()) + "</span>. One of these "+Integer.toString(child.getType().size())+":</span>\r\n");
          for (TypeRefComponent t : child.getType())
            generateCoreElem(b, elements, child, indent+2, pathName+"."+en, false, t, ++cc == l, false);
        }
      c++;
      b.append(indentS);
      if (c == slices.size())
        b.append("  }\r\n");
      else
        b.append("  },\r\n");

    }
    b.append(indentS);
    if (last)
      b.append("]\r\n");
    else
      b.append("],\r\n");
  }

  private void generateCoreElemExtension(StringBuilder b, List<ElementDefinition> elements, ElementDefinition elem, List<ElementDefinition> children, int indent, String pathName, boolean asValue, TypeRefComponent type, boolean last, boolean complex) throws Exception {
    if (elem.getMax().equals("0"))
      return;

    String name =  tail(elem.getPath());
    String en = asValue ? "value[x]" : name;
    if (en.contains("[x]"))
      en = en.replace("[x]", upFirst(type.getCode()));
    boolean unbounded = elem.hasMax() && elem.getMax().equals("*");

    String indentS = "";
    for (int i = 0; i < indent; i++) {
      indentS += "  ";
    }
//    String defPage = igp.getLinkForProfile(sd, sd.getUrl());
//    b.append(indentS);
//    b.append("\"<a href=\"" + (defPage + "#" + pathName + "." + en)+ "\" title=\"" + Utilities .escapeXml(getEnhancedDefinition(elem)) 
//    + "\" class=\"dict\"><span style=\"text-decoration: underline\">"+en+"</span></a>\" : ");
//    b.append("[ <span style=\"color: navy\">"+describeSlicing(elem.getSlicing())+"</span>");
////    b.append(" <span style=\"color: Gray\">//</span>");
////    writeCardinality(elem);
//    b.append("\r\n");
//    
    b.append(indentS+"\"extension\": [\r\n");
    List<ElementDefinition> slices = getSlices(elem, children);
    int c = 0;
    for (ElementDefinition slice : slices) {
      List<CanonicalType> profiles = slice.getTypeFirstRep().getProfile();
      // Won't have a profile if this slice is part of a complex extension
      String url = profiles.isEmpty() ? null : profiles.get(0).getValue();
      StructureDefinition sdExt = url == null ? null : context.fetchResource(StructureDefinition.class, url);
      b.append(indentS+"  ");
      b.append("{ // <span style=\"color: navy; opacity: 0.8\">");
      writeCardinality(unbounded, b, slice);
      b.append(Utilities.escapeXml(slice.getShort()) + "</span>");
      b.append("\r\n");
      b.append(indentS+"    ");
      if (sdExt == null)
        b.append("\"url\": \""+url+"\",\r\n");
      else
        b.append("\"url\": \"<a href=\""+sdExt.getUserString("path")+"\">"+url+"</a>\",\r\n");
      
      List<ElementDefinition> extchildren = getChildren(elements, slice);
      if (extchildren.isEmpty()) {
        if (sdExt == null)
          b.append("Not handled yet: unknown extension "+url+"\r\n");
        else
          extchildren = getChildren(sdExt.getSnapshot().getElement(), sdExt.getSnapshot().getElementFirstRep());
      }

      ElementDefinition value = getValue(extchildren);
      if (value != null) {
        if (value.getType().size() == 1)
          generateCoreElem(b, elements, value, indent+2, pathName+"."+en, false, value.getType().get(0), true, false);
        else {
          b.append("<span style=\"color: Gray\">// value[x]: <span style=\"color: navy; opacity: 0.8\">" +Utilities.escapeXml(value.getShort()) + "</span>. One of these "+Integer.toString(value.getType().size())+":</span>\r\n");
          for (TypeRefComponent t : value.getType())
            generateCoreElem(b, elements, value, indent+2, pathName+"."+en, false, t, t==value.getType().get(value.getType().size()-1), false);
        }      
      } else {
        b.append("Not handled yet: complex extension "+url+"\r\n");
      }
      
      c++;
      b.append(indentS);
      if (c == slices.size())
        b.append("  }\r\n");
      else
        b.append("  },\r\n");

    }
    b.append(indentS);
    if (last)
      b.append("]\r\n");
    else
      b.append("],\r\n");
  }

  
  
  private ElementDefinition getValue(List<ElementDefinition> extchildren) {
    for (ElementDefinition ed : extchildren) 
      if (ed.getPath().contains(".value") && !"0".equals(ed.getMax()))
        return ed;
    return null;
  }

  private boolean hasResource(String code) {
    StructureDefinition sd = context.fetchTypeDefinition(code);
    return sd != null && sd.getKind() == StructureDefinitionKind.RESOURCE;
  }

  private boolean hasType(String code) {
    StructureDefinition sd = context.fetchResource(StructureDefinition.class, ProfileUtilities.sdNs(code, null));
    return sd != null && (sd.getKind() == StructureDefinitionKind.PRIMITIVETYPE || sd.getKind() == StructureDefinitionKind.COMPLEXTYPE);
  }

  private String getSrcFile(String code) {
    StructureDefinition sd = context.fetchResource(StructureDefinition.class, ProfileUtilities.sdNs(code, null));
    if (sd == null)
      return "??";
    else {
      String l = igp.getLinkForProfile(this.sd, sd.getUrl());
      if (l == null)
        return null;
      if (l.contains("|"))
        l = l.substring(0, l.indexOf("|"));
      return l;
    }
  }

  private boolean isPrimitive(String code) {
    StructureDefinition sd = context.fetchTypeDefinition(code);
    return sd != null && sd.getKind() == StructureDefinitionKind.PRIMITIVETYPE;
  }


  private String upFirst(String s) {
    return s.substring(0, 1).toUpperCase() + s.substring(1);
  }

  private void writeCardinality(boolean unbounded, StringBuilder b, ElementDefinition elem) throws IOException {
    if (elem.getConstraint().size() > 0)
      b.append(" <span style=\"color: brown\" title=\""
          + Utilities.escapeXml(getInvariants(elem)) + "\"><b>C?</b></span>");
    if (elem.getMin() > 0)
      b.append(" <span style=\"color: brown\" title=\"This element is required\"><b>R!</b></span>");
    if (unbounded && "1".equals(elem.getMax()))
      b.append(" <span style=\"color: brown\" title=\"This element is an array in the base standard, but the profile only allows on element\"><b>Only One!</b></span> ");
  }

  private String getInvariants(ElementDefinition elem) {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (ElementDefinitionConstraintComponent i : elem.getConstraint()) {
      if (!first)
        b.append("; ");
      first = false;
      b.append(i.getKey()+": "+i.getHuman());
    }

    return b.toString();
  }


  private String getEnhancedDefinition(ElementDefinition elem) {
    if (elem.getIsModifier() && elem.getMustSupport())
      return Utilities.removePeriod(elem.getDefinition()) + " (this element modifies the meaning of other elements, and must be supported)";
    else if (elem.getIsModifier())
      return Utilities.removePeriod(elem.getDefinition()) + " (this element modifies the meaning of other elements)";
    else if (elem.getMustSupport())
      return Utilities.removePeriod(elem.getDefinition()) + " (this element must be supported)";
    else
      return Utilities.removePeriod(elem.getDefinition());
  }

  private boolean wasSliced(ElementDefinition child, List<ElementDefinition> children) {
    String path = child.getPath();
    for (ElementDefinition c : children) {
      if (c == child)
        break;
      if (c.getPath().equals(path) && c.hasSlicing())
        return true;
    }
    return false;
  }


  private List<ElementDefinition> getSlices(ElementDefinition elem, List<ElementDefinition> children) {
    List<ElementDefinition> slices = new ArrayList<ElementDefinition>();
    for (ElementDefinition child : children) {
      if (child != elem && child.getPath().equals(elem.getPath()))
        slices.add(child);
    }
    return slices;
  }

  private String describeSlicing(ElementDefinitionSlicingComponent slicing) {
    if (slicing.getRules() == SlicingRules.CLOSED)
      return "";
    CommaSeparatedStringBuilder csv = new CommaSeparatedStringBuilder();
    for (ElementDefinitionSlicingDiscriminatorComponent d : slicing.getDiscriminator()) {
      csv.append(d.getType().toCode()+":"+d.getPath());
    }
    String s = slicing.getOrdered() ? " in any order" : " in the specified order " + (slicing.hasRules() ? slicing.getRules().getDisplay() : "");
    return "// sliced by "+csv.toString()+" "+s;
  }

}
