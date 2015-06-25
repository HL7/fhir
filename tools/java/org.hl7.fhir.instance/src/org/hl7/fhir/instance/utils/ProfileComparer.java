package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.model.Base;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionMappingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.PropertyRepresentation;
import org.hl7.fhir.instance.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.instance.model.Enumeration;
import org.hl7.fhir.instance.model.Enumerations.BindingStrength;
import org.hl7.fhir.instance.model.Enumerations.ConformanceResourceStatus;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.StructureDefinition.StructureDefinitionType;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.instance.model.valuesets.IssueType;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;

public class ProfileComparer {

  /**
   * The outcome of comparing two profiles
   */
  public class ProfileComparerOutome {
    private List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
    private StructureDefinition commonContent;
    
    /**
     * warnings or error messages generated during the comparison. 
     * 
     * @return
     */
    public List<ValidationMessage> getMessages() {
      return messages;
    }
    /**
     * The common content that conforms to both structures
     * 
     * if this is null when the comparison completes, there will be 
     * at least one error message in the messages
     * @return
     */
    public StructureDefinition getCommonContent() {
      return commonContent;
    }
    public String addValueSet(ValueSet cvs) {
      // TODO Auto-generated method stub
      return null;
    }
  }

  private static final int BOTH_NULL = 0;
  private static final int EITHER_NULL = 1;
  
  private WorkerContext context;
  
  public ProfileComparer(WorkerContext context) {
    super();
    this.context = context;
  }

  /**
   * Compre two structure definitions to see whether they are consistent or not
   * 
   * Not that for the definitions of this routine, structure definitions are comparable if
   * there are any resources that conform to both structure definitions. 
   * 
   * Note that left and right are arbitrary choices. In one respect, left 
   * is 'preferred' - the left's example value and data sets will be selected 
   * over the right ones in the common structure definition
   *  
   * @throws Exception 
   */
  public ProfileComparerOutome compareProfiles(StructureDefinition left, StructureDefinition right) throws Exception {
    if (left == null)
      throw new Exception("No StructureDefinition provided (left)");
    if (right == null)
      throw new Exception("No StructureDefinition provided (right)");
    if (!left.hasSnapshot())
      throw new Exception("StructureDefinition has no snapshot (left: "+left.getName()+")");
    if (!right.hasSnapshot())
      throw new Exception("StructureDefinition has no snapshot (right: "+right.getName()+")");
    if (left.getSnapshot().getElement().isEmpty())
      throw new Exception("StructureDefinition snapshot is empty (left: "+left.getName()+")");
    if (right.getSnapshot().getElement().isEmpty())
      throw new Exception("StructureDefinition snapshot is empty (right: "+right.getName()+")");
    
    DefinitionNavigator ln = new DefinitionNavigator(context, left, 0);
    DefinitionNavigator rn = new DefinitionNavigator(context, right, 0);
    
    // from here on in, any issues go in outcome.messages
    ProfileComparerOutome outcome = new ProfileComparerOutome();
    outcome.commonContent = new StructureDefinition();
    if (ruleEqual(outcome, ln.path(), ln.path(), rn.path(), "Base Type is not compatible", false)) {
      if (compareElements(outcome, ln.path(), ln, rn, left, right)) {
        outcome.commonContent.setName("intersection of "+left.getName()+" and "+right.getName());
        outcome.commonContent.setStatus(ConformanceResourceStatus.DRAFT);
        outcome.commonContent.setType(StructureDefinitionType.CONSTRAINT);
        outcome.commonContent.setAbstract(false);
      } else
        outcome.commonContent = null;
    }
    return outcome;  
  }

  /**
   * left and right refer to the same element. Are they compatible?   
   * @param outcome
   * @param path
   * @param left
   * @param right
   * @throws Exception - if there's a problem that needs fixing in this code
   */
  private boolean compareElements(ProfileComparerOutome outcome, String path, DefinitionNavigator left, DefinitionNavigator right, StructureDefinition ctxtLeft, StructureDefinition ctxtRight) throws Exception {
//    preconditions:
    assert(path != null);
    assert(left != null);
    assert(right != null);
    assert(left.path().equals(right.path()));
    
    if (left.current().hasSlicing() || right.current().hasSlicing()) {
      return true;
//      throw new Exception("Slicing is not handled yet");
    // todo: name 
    }
    
    // simple stuff
    ElementDefinition common = new ElementDefinition(left.current().getPathElement());
    // not allowed to be different: 
    common.getRepresentation().addAll(left.current().getRepresentation()); // can't be bothered even testing this one
    if (!ruleCompares(outcome, left.current().getDefaultValue(), right.current().getDefaultValue(), path+".defaultValue[x]", BOTH_NULL))
      return false;
    common.setDefaultValue(left.current().getDefaultValue());
    if (!ruleEqual(outcome, path, left.current().getMeaningWhenMissing(), right.current().getMeaningWhenMissing(), "meaningWhenMissing Must be the same", true))
      return false;
    common.setMeaningWhenMissing(left.current().getMeaningWhenMissing());
    if (!ruleEqual(outcome, left.current().getIsModifier(), right.current().getIsModifier(), path, "isModifier"))
      return false;
    common.setIsModifier(left.current().getIsModifier());
    if (!ruleEqual(outcome, left.current().getIsSummary(), right.current().getIsSummary(), path, "isSummary"))
      return false;
    common.setIsSummary(left.current().getIsSummary());
    
    
    // descriptive properties from ElementDefinition - merge them:
    common.setLabel(mergeText(left.current().getLabel(), right.current().getLabel()));
    common.setShort(mergeText(left.current().getShort(), right.current().getShort()));
    common.setDefinition(mergeText(left.current().getDefinition(), right.current().getDefinition()));
    common.setComments(mergeText(left.current().getComments(), right.current().getComments()));
    common.setRequirements(mergeText(left.current().getRequirements(), right.current().getRequirements()));
    common.getCode().addAll(mergeCodings(left.current().getCode(), right.current().getCode()));
    common.getAlias().addAll(mergeStrings(left.current().getAlias(), right.current().getAlias()));
    common.getMapping().addAll(mergeMappings(left.current().getMapping(), right.current().getMapping()));
    // left will win for example
    common.setExample(left.current().hasExample() ? left.current().getExample() : right.current().getExample());


    // compare and intersect
    common.setMin(mergeMin(left.current().getMin(), right.current().getMin()));
    common.setMax(mergeMax(left.current().getMax(), right.current().getMax()));
    if (!rule(outcome, common.getMax().equals("*") || Integer.parseInt(common.getMax()) >= common.getMin(), path, "Cardinality Mismatch: "+card(left)+"/"+card(right)))
      return false;
    common.getType().addAll(intersectTypes(left.current().getType(), right.current().getType()));
    if (!rule(outcome, !common.getType().isEmpty(), path, "Type Mismatch: "+typeCode(left)+"/"+typeCode(right)))
      return false;
//    <fixed[x]><!-- ?? 0..1 * Value must be exactly this --></fixed[x]>
//    <pattern[x]><!-- ?? 0..1 * Value must have at least these property values --></pattern[x]>
    common.setMaxLength(mergeMaxLength(left.current().getMaxLength(), right.current().getMaxLength()));
    if (left.current().hasBinding() || right.current().hasBinding()) {
      if (!compareBindings(outcome, common, path, left.current().getBinding(), right.current().getBinding(), ctxtLeft, ctxtRight))
        return false;
    }

    common.setMustSupport(left.current().getMustSupport() || right.current().getMustSupport());
    
    common.getConstraint().addAll(mergeConstraints(outcome, path, left.current().getConstraint(), right.current().getConstraint(), ctxtLeft, ctxtRight));
    
    // now, children
    outcome.commonContent.getSnapshot().getElement().add(common);
    return compareChildren(outcome, path, left, right, ctxtLeft, ctxtRight);
  }

  private boolean compareChildren(ProfileComparerOutome outcome, String path, DefinitionNavigator left, DefinitionNavigator right, StructureDefinition ctxtLeft, StructureDefinition ctxtRight) throws Exception {
    List<DefinitionNavigator> lc = left.children();
    List<DefinitionNavigator> rc = right.children();
    // it's possible that one of these profiles walks into a data type and the other doesn't
    // if it does, we have to load the children for that data into the profile that doesn't 
    // walk into it
    if (lc.isEmpty() && !rc.isEmpty() && right.current().getType().size() == 1 && left.hasTypeChildren(right.current().getType().get(0)))
      lc = left.childrenFromType(right.current().getType().get(0));
    if (rc.isEmpty() && !lc.isEmpty() && left.current().getType().size() == 1 && right.hasTypeChildren(left.current().getType().get(0)))
      rc = right.childrenFromType(left.current().getType().get(0));
    if (lc.size() != rc.size()) {
      outcome.messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "Different number of children at "+path+" ("+Integer.toString(lc.size())+"/"+Integer.toString(rc.size())+")", IssueSeverity.ERROR));
      return false;      
    } else {
      for (int i = 0; i < lc.size(); i++) {
        DefinitionNavigator l = lc.get(i);
        DefinitionNavigator r = rc.get(i);
        if (l.path().equals(r.path())) {
          if (!compareElements(outcome, path+"."+l.nameTail(), l, r, ctxtLeft, ctxtRight))
            return false;
        } else {
          outcome.messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "Different path at "+path+"["+Integer.toString(i)+"] ("+l.path()+"/"+r.path()+")", IssueSeverity.ERROR));
          return false;
        }
      }
    }
    return true;
  }

  private boolean compareBindings(ProfileComparerOutome outcome, ElementDefinition common, String path, ElementDefinitionBindingComponent left, ElementDefinitionBindingComponent right, StructureDefinition ctxtLeft, StructureDefinition ctxtRight) throws Exception {
    assert(left != null || right != null);
    if (left == null) {
      common.setBinding(right);
      return true;
    }
    if (right == null) {
      common.setBinding(left);
      return true;
    }
    // if they're both examples/preferred, left wins if they're both the same 
    if (isPreferredOrExample(left) && isPreferredOrExample(right)) {
      if (right.getStrength() == BindingStrength.PREFERRED && left.getStrength() == BindingStrength.EXAMPLE) 
        common.setBinding(right);
      else
        common.setBinding(left);
      return true;
    }
    // if either of them are extensible/required, then it wins
    if (isPreferredOrExample(left)) {
      common.setBinding(right);
      return true;
    }
    if (isPreferredOrExample(right)) {
      common.setBinding(left);
      return true;
    }
    // ok, both are extensible or required.
    ElementDefinitionBindingComponent binding = new ElementDefinitionBindingComponent();
    common.setBinding(binding);
    binding.setName(mergeText(left.getName(), right.getName()));
    binding.setDescription(mergeText(left.getDescription(), right.getDescription()));
    if (left.getStrength() == BindingStrength.REQUIRED || right.getStrength() == BindingStrength.REQUIRED)
      binding.setStrength(BindingStrength.REQUIRED);
    else
      binding.setStrength(BindingStrength.EXTENSIBLE);
    if (Base.compareDeep(left.getValueSet(), right.getValueSet(), false)) {
      binding.setValueSet(left.getValueSet());
      return true;
    } else {
      // ok, now we compare the value sets. This may be unresolvable. 
      ValueSet lvs = resolveVS(ctxtLeft, left.getValueSet());
      ValueSet rvs = resolveVS(ctxtRight, right.getValueSet());
      
      // first, we'll try to do it by definition
      ValueSet cvs = intersectByDefinition(lvs, rvs);
      if(cvs == null) {
        // if that didn't work, we'll do it by expansion
        ValueSet le = context.getTerminologyServices().expandVS(lvs);
        ValueSet re = context.getTerminologyServices().expandVS(rvs);
        if (!closed(le) || !closed(re)) 
          throw new Exception("not handled yet");
        cvs = intersectByExpansion(lvs, rvs);
        if (!cvs.getCompose().hasInclude()) {
          outcome.messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "The value sets "+lvs.getUrl()+" and "+rvs.getUrl()+" do not intersect", IssueSeverity.ERROR));
          return false;
        }
      }
      binding.setValueSet(new Reference().setReference("#"+outcome.addValueSet(cvs)));
    }
    return false;
  }

  private ValueSet resolveVS(StructureDefinition ctxtLeft, Type vsRef) throws Exception {
    if (vsRef instanceof UriType)
      throw new Exception("not done yet");
    else
      throw new Exception("not done yet");
  }

  private ValueSet intersectByDefinition(ValueSet lvs, ValueSet rvs) {
    // this is just a stub. The idea is that we try to avoid expanding big open value sets from SCT, RxNorm, LOINC.
    // there's a bit of long hand logic coming here, but that's ok.
    return null;
  }

  private ValueSet intersectByExpansion(ValueSet lvs, ValueSet rvs) {
    // this is pretty straight forward - we intersect the lists, and build a compose out of the intersection
    ValueSet vs = new ValueSet();
    vs.setStatus(ConformanceResourceStatus.DRAFT);
    
    Map<String, ValueSetExpansionContainsComponent> left = new HashMap<String, ValueSetExpansionContainsComponent>();
    scan(lvs.getExpansion().getContains(), left);
    Map<String, ValueSetExpansionContainsComponent> right = new HashMap<String, ValueSetExpansionContainsComponent>();
    scan(rvs.getExpansion().getContains(), right);
    Map<String, ConceptSetComponent> inc = new HashMap<String, ConceptSetComponent>();
    
    for (String s : left.keySet()) {
      if (right.containsKey(s)) {
        ValueSetExpansionContainsComponent cc = left.get(s);
        ConceptSetComponent c = inc.get(cc.getSystem());
        if (c == null) {
          c = vs.getCompose().addInclude().setSystem(cc.getSystem());
          inc.put(cc.getSystem(), c);
        }
        c.addConcept().setCode(cc.getCode()).setDisplay(cc.getDisplay());
      }
    }
    return vs;
  }

  private void scan(List<ValueSetExpansionContainsComponent> list, Map<String, ValueSetExpansionContainsComponent> map) {
    for (ValueSetExpansionContainsComponent cc : list) {
      if (cc.hasSystem() && cc.hasCode()) {
        String s = cc.getSystem()+"::"+cc.getCode();
        if (!map.containsKey(s))
          map.put(s,  cc);
      }
      if (cc.hasContains())
        scan(cc.getContains(), map);
    }
  }

  private boolean closed(ValueSet vs) {
    return !ToolingExtensions.findBooleanExtension(vs.getExpansion(), ToolingExtensions.EXT_UNCLOSED);
  }

  private boolean isPreferredOrExample(ElementDefinitionBindingComponent binding) {
    return binding.getStrength() == BindingStrength.EXAMPLE || binding.getStrength() == BindingStrength.PREFERRED;
  }

  private Collection<? extends TypeRefComponent> intersectTypes(List<TypeRefComponent> left, List<TypeRefComponent> right) {
    List<TypeRefComponent> result = new ArrayList<TypeRefComponent>();
    for (TypeRefComponent l : left) {
      boolean found = false;
      TypeRefComponent c = new TypeRefComponent(l.getCodeElement());
      for (TypeRefComponent r : right)
        if (Utilities.equals(l.getCode(), r.getCode())) {
          found = true;
          // todo: compare profiles
          // todo: compare aggregation values
        }
      if (found)
        result.add(c);
    }
    return result;
  }

  private String mergeText(String left, String right) {
    if (left == null && right == null)
      return null;
    if (left == null)
      return right;
    if (right == null)
      return left;
    if (left.equalsIgnoreCase(right))
      return left;
    return "left: "+left+"; right: "+right;
  }

  private List<Coding> mergeCodings(List<Coding> left, List<Coding> right) {
    List<Coding> result = new ArrayList<Coding>();
    result.addAll(left);
    for (Coding c : right) {
      boolean found = false;
      for (Coding ct : left)
        if (Utilities.equals(c.getSystem(), ct.getSystem()) && Utilities.equals(c.getCode(), ct.getCode()))
          found = true;
      if (!found)
        result.add(c);
    }
    return result;
  }

  private List<StringType> mergeStrings(List<StringType> left, List<StringType> right) {
    List<StringType> result = new ArrayList<StringType>();
    result.addAll(left);
    for (StringType c : right) {
      boolean found = false;
      for (StringType ct : left)
        if (Utilities.equals(c.getValue(), ct.getValue()))
          found = true;
      if (!found)
        result.add(c);
    }
    return result;
  }

  private List<ElementDefinitionMappingComponent> mergeMappings(List<ElementDefinitionMappingComponent> left, List<ElementDefinitionMappingComponent> right) {
    List<ElementDefinitionMappingComponent> result = new ArrayList<ElementDefinitionMappingComponent>();
    result.addAll(left);
    for (ElementDefinitionMappingComponent c : right) {
      boolean found = false;
      for (ElementDefinitionMappingComponent ct : left)
        if (Utilities.equals(c.getIdentity(), ct.getIdentity()) && Utilities.equals(c.getLanguage(), ct.getLanguage()) && Utilities.equals(c.getMap(), ct.getMap()))
          found = true;
      if (!found)
        result.add(c);
    }
    return result;
  }

  // we can't really know about constraints. We create warnings, and collate them 
  private List<ElementDefinitionConstraintComponent> mergeConstraints(ProfileComparerOutome outcome, String path, List<ElementDefinitionConstraintComponent> left, 
        List<ElementDefinitionConstraintComponent> right, StructureDefinition ctxtLeft, StructureDefinition ctxtRight) {
    List<ElementDefinitionConstraintComponent> result = new ArrayList<ElementDefinitionConstraintComponent>();
    for (ElementDefinitionConstraintComponent l : left) {
      boolean found = false;
      for (ElementDefinitionConstraintComponent r : right)
        if (Utilities.equals(r.getId(), l.getId()) || (Utilities.equals(r.getXpath(), l.getXpath()) && r.getSeverity() == l.getSeverity()))
          found = true;
      if (!found)
        outcome.messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "SturctureDefinition "+ctxtLeft.getName()+" has a constraint that is not found in "+ctxtRight.getName()+" and it is uncertain whether they are compatible ("+l.getXpath()+")", IssueSeverity.WARNING));
      result.add(l);
    }
    for (ElementDefinitionConstraintComponent r : right) {
      boolean found = false;
      for (ElementDefinitionConstraintComponent l : left)
        if (Utilities.equals(r.getId(), l.getId()) || (Utilities.equals(r.getXpath(), l.getXpath()) && r.getSeverity() == l.getSeverity()))
          found = true;
      if (!found) {
        outcome.messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "SturctureDefinition "+ctxtRight.getName()+" has a constraint that is not found in "+ctxtLeft.getName()+" and it is uncertain whether they are compatible ("+r.getXpath()+")", IssueSeverity.WARNING));
        result.add(r);
      }
    }
    return result;
  }
  
  private String card(DefinitionNavigator defn) {
    return Integer.toString(defn.current().getMin())+".."+defn.current().getMax();
  }
  
  private String typeCode(DefinitionNavigator defn) {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (TypeRefComponent t : defn.current().getType())
      b.append(t.getCode()); // todo: other properties
    return b.toString();
  }

  private int mergeMin(int left, int right) {
    if (left > right)
      return left;
    else
      return right;
  }

  private String mergeMax(String left, String right) {
    int l = "*".equals(left) ? Integer.MAX_VALUE : Integer.parseInt(left);
    int r = "*".equals(right) ? Integer.MAX_VALUE : Integer.parseInt(right);
    if (l < r)
      return left;
    else
      return right;
  }

  private int mergeMaxLength(int left, int right) {
    if (left == 0) 
      left = Integer.MAX_VALUE;
    if (right == 0) 
      right = Integer.MAX_VALUE;
    if (left < right)
      return left;
    else
      return right == Integer.MAX_VALUE ? 0 : right;
  }

  
  private boolean rule(ProfileComparerOutome outcome, boolean test, String path, String message) {
    if (!test) 
      outcome.messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, message, IssueSeverity.ERROR));
    return test;
  }

  private boolean ruleEqual(ProfileComparerOutome outcome, String path, String vLeft, String vRight, String description, boolean nullOK) {
    if (vLeft == null && vRight == null && nullOK)
      return true;
    if (vLeft == null && vRight == null) {
      outcome.messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, description+" and not null (null/null)", IssueSeverity.ERROR));
    }
    if (vLeft == null || !vLeft.equals(vRight)) {
      outcome.messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, description+" ("+vLeft+"/"+vRight+")", IssueSeverity.ERROR));
    }
    return true;
  }
  
  private boolean ruleEqual(ProfileComparerOutome outcome, boolean vLeft, boolean vRight, String path, String elementName) {
    if (vLeft != vRight) {
      outcome.messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, elementName+" must be the same ("+vLeft+"/"+vRight+")", IssueSeverity.ERROR));
    }
    return true;
  }

  private boolean ruleCompares(ProfileComparerOutome outcome, Type vLeft, Type vRight, String path, int nullStatus) throws Exception {
    if (vLeft == null && vRight == null && nullStatus == BOTH_NULL)
      return true;
    if (vLeft == null && vRight == null) {
      outcome.messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "Must be the same and not null (null/null)", IssueSeverity.ERROR));
    }
    if (vLeft == null && nullStatus == EITHER_NULL)
      return true;
    if (vRight == null && nullStatus == EITHER_NULL)
      return true;
    if (vLeft == null || vRight == null || Base.compareDeep(vLeft, vRight, false)) {
      outcome.messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "Must be the same ("+toString(vLeft)+"/"+toString(vRight)+")", IssueSeverity.ERROR));
    }
    return true;
  }

  private String toString(Type val) throws Exception {
    JsonParser jp = new JsonParser();
    return jp.composeString(val, "value");
  }


  
}
