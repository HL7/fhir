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
import org.hl7.fhir.instance.model.IntegerType;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.PrimitiveType;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.StructureDefinition.StructureDefinitionType;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptReferenceComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.instance.model.valuesets.IssueType;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;
import org.mozilla.javascript.NativeGenerator.GeneratorClosedException;

public class ProfileComparer {

  private WorkerContext context;
  
  public ProfileComparer(WorkerContext context) {
    super();
    this.context = context;
  }

  private static final int BOTH_NULL = 0;
  private static final int EITHER_NULL = 1;
  
  /**
   * messages generated during the comparison. There are 4 grades of messages:
   *   information - a list of differences between structures
   *   warnings - notifies that the comparer is unable to fully compare the structures (constraints differ, open value sets)
   *   errors - where the structures are incompatible
   *   fatal errors - some error that prevented full analysis 
   * 
   * @return
   */
  private List<ValidationMessage> messages = new ArrayList<ValidationMessage>();

  /**
   * The structure that describes all instances that will conform to both structures 
   */
  private StructureDefinition subset;

  /**
   * The structure that describes all instances that will conform to either structures 
   */
  private StructureDefinition superset;

  /**
   * Value sets used in the subset and superset
   */
  private List<ValueSet> valuesets = new ArrayList<ValueSet>();

  public List<ValidationMessage> getMessages() {
    return messages;
  }

  public StructureDefinition getSubset() {
    return subset;
  }

  public StructureDefinition getSuperset() {
    return superset;
  }

  private StructureDefinition leftStructure;
  private StructureDefinition rightStructure;
  
  
  public StructureDefinition getLeftStructure() {
    return leftStructure;
  }

  public void setLeftStructure(StructureDefinition leftStructure) {
    this.leftStructure = leftStructure;
  }

  public StructureDefinition getRightStructure() {
    return rightStructure;
  }

  public void setRightStructure(StructureDefinition rightStructure) {
    this.rightStructure = rightStructure;
  }

  /**
   * Compare left and right structure definitions to see whether they are consistent or not
   * 
   * Note that left and right are arbitrary choices. In one respect, left 
   * is 'preferred' - the left's example value and data sets will be selected 
   * over the right ones in the common structure definition
   *  
   * @throws Exception 
   */
  public void compareProfiles() throws Exception {
    if (leftStructure == null)
      throw new Exception("No StructureDefinition provided (left)");
    if (rightStructure == null)
      throw new Exception("No StructureDefinition provided (right)");
    if (!leftStructure.hasSnapshot())
      throw new Exception("StructureDefinition has no snapshot (left: "+leftName()+")");
    if (!rightStructure.hasSnapshot())
      throw new Exception("StructureDefinition has no snapshot (right: "+rightName()+")");
    if (leftStructure.getSnapshot().getElement().isEmpty())
      throw new Exception("StructureDefinition snapshot is empty (left: "+leftName()+")");
    if (rightStructure.getSnapshot().getElement().isEmpty())
      throw new Exception("StructureDefinition snapshot is empty (right: "+rightName()+")");
    
    DefinitionNavigator ln = new DefinitionNavigator(context, leftStructure, 0);
    DefinitionNavigator rn = new DefinitionNavigator(context, rightStructure, 0);
    
    // from here on in, any issues go in messages
    superset = new StructureDefinition();
    subset = new StructureDefinition();
    if (ruleEqual(ln.path(), ln.path(), rn.path(), "Base Type is not compatible", false)) {
      if (compareElements(ln.path(), ln, rn)) {
        subset.setName("intersection of "+leftName()+" and "+rightName());
        subset.setStatus(ConformanceResourceStatus.DRAFT);
        subset.setType(StructureDefinitionType.CONSTRAINT);
        subset.setAbstract(false);
        superset.setName("union of "+leftName()+" and "+rightName());
        superset.setStatus(ConformanceResourceStatus.DRAFT);
        superset.setType(StructureDefinitionType.CONSTRAINT);
        superset.setAbstract(false);
      } else {
        subset = null;
        superset = null;
      }
    }
  }

  private String leftName() {
    return leftStructure.getName();
  }
  private String rightName() {
    return rightStructure.getName();
  }

  /**
   * left and right refer to the same element. Are they compatible?   
   * @param outcome
   * @param path
   * @param left
   * @param right
   * @throws Exception - if there's a problem that needs fixing in this code
   */
  private boolean compareElements(String path, DefinitionNavigator left, DefinitionNavigator right) throws Exception {
//    preconditions:
    assert(path != null);
    assert(left != null);
    assert(right != null);
    assert(left.path().equals(right.path()));
    
    if (left.current().hasSlicing() || right.current().hasSlicing()) {
      if (isExtension(left.path()))
        return compareExtensions(path, left, right);
      return true;
//      throw new Exception("Slicing is not handled yet");
    // todo: name 
    }
    
    // simple stuff
    ElementDefinition subset = new ElementDefinition();
    subset.setPath(left.path());
    
    // not allowed to be different: 
    subset.getRepresentation().addAll(left.current().getRepresentation()); // can't be bothered even testing this one
    if (!ruleCompares(left.current().getDefaultValue(), right.current().getDefaultValue(), path+".defaultValue[x]", BOTH_NULL))
      return false;
    subset.setDefaultValue(left.current().getDefaultValue());
    if (!ruleEqual(path, left.current().getMeaningWhenMissing(), right.current().getMeaningWhenMissing(), "meaningWhenMissing Must be the same", true))
      return false;
    subset.setMeaningWhenMissing(left.current().getMeaningWhenMissing());
    if (!ruleEqual(left.current().getIsModifier(), right.current().getIsModifier(), path, "isModifier"))
      return false;
    subset.setIsModifier(left.current().getIsModifier());
    if (!ruleEqual(left.current().getIsSummary(), right.current().getIsSummary(), path, "isSummary"))
      return false;
    subset.setIsSummary(left.current().getIsSummary());
    
    // descriptive properties from ElementDefinition - merge them:
    subset.setLabel(mergeText(path, "label", left.current().getLabel(), right.current().getLabel()));
    subset.setShort(mergeText(path, "short", left.current().getShort(), right.current().getShort()));
    subset.setDefinition(mergeText(path, "definition", left.current().getDefinition(), right.current().getDefinition()));
    subset.setComments(mergeText(path, "comments", left.current().getComments(), right.current().getComments()));
    subset.setRequirements(mergeText(path, "requirements", left.current().getRequirements(), right.current().getRequirements()));
    subset.getCode().addAll(mergeCodings(left.current().getCode(), right.current().getCode()));
    subset.getAlias().addAll(mergeStrings(left.current().getAlias(), right.current().getAlias()));
    subset.getMapping().addAll(mergeMappings(left.current().getMapping(), right.current().getMapping()));
    // left will win for example
    subset.setExample(left.current().hasExample() ? left.current().getExample() : right.current().getExample());

    subset.setMustSupport(left.current().getMustSupport() || right.current().getMustSupport());
    ElementDefinition superset = subset.copy();


    // compare and intersect
    superset.setMin(unionMin(left.current().getMin(), right.current().getMin()));
    superset.setMax(unionMax(left.current().getMax(), right.current().getMax()));
    subset.setMin(intersectMin(left.current().getMin(), right.current().getMin()));
    subset.setMax(intersectMax(left.current().getMax(), right.current().getMax()));
    rule(subset.getMax().equals("*") || Integer.parseInt(subset.getMax()) >= subset.getMin(), path, "Cardinality Mismatch: "+card(left)+"/"+card(right));
    
    superset.getType().addAll(unionTypes(path, left.current().getType(), right.current().getType()));
    subset.getType().addAll(intersectTypes(path, left.current().getType(), right.current().getType()));
    rule(!subset.getType().isEmpty(), path, "Type Mismatch: "+typeCode(left)+"/"+typeCode(right));
//    <fixed[x]><!-- ?? 0..1 * Value must be exactly this --></fixed[x]>
//    <pattern[x]><!-- ?? 0..1 * Value must have at least these property values --></pattern[x]>
    superset.setMaxLengthElement(unionMaxLength(left.current().getMaxLength(), right.current().getMaxLength()));
    subset.setMaxLengthElement(intersectMaxLength(left.current().getMaxLength(), right.current().getMaxLength()));
    if (left.current().hasBinding() || right.current().hasBinding()) {
      compareBindings(subset, superset, path, left.current(), right.current());
    }

    // note these are backwards
    superset.getConstraint().addAll(intersectConstraints(path, left.current().getConstraint(), right.current().getConstraint()));
    subset.getConstraint().addAll(unionConstraints(path, left.current().getConstraint(), right.current().getConstraint()));
    
    // add the children
    this.subset.getSnapshot().getElement().add(subset);
    this.superset.getSnapshot().getElement().add(superset);
    return compareChildren(path, left, right);
  }

  private boolean compareExtensions(String path, DefinitionNavigator left, DefinitionNavigator right) {
    //
    return false;
  }

  private boolean isExtension(String path) {
    return path.endsWith("Extension");
  }

  private boolean compareChildren(String path, DefinitionNavigator left, DefinitionNavigator right) throws Exception {
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
      messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "Different number of children at "+path+" ("+Integer.toString(lc.size())+"/"+Integer.toString(rc.size())+")", IssueSeverity.ERROR));
      return false;      
    } else {
      for (int i = 0; i < lc.size(); i++) {
        DefinitionNavigator l = lc.get(i);
        DefinitionNavigator r = rc.get(i);
        if (l.path().equals(r.path())) {
          if (!compareElements(path+"."+l.nameTail(), l, r))
            return false;
        } else {
          messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "Different path at "+path+"["+Integer.toString(i)+"] ("+l.path()+"/"+r.path()+")", IssueSeverity.ERROR));
          return false;
        }
      }
    }
    return true;
  }

  private boolean compareBindings(ElementDefinition subset, ElementDefinition superset, String path, ElementDefinition lDef, ElementDefinition rDef) throws Exception {
    assert(lDef.hasBinding() || rDef.hasBinding());
    if (!lDef.hasBinding()) {
      subset.setBinding(rDef.getBinding());
      // technically, the super set is unbound, but that's not very useful - so we use the provided on as an example
      superset.setBinding(rDef.getBinding().copy());
      superset.getBinding().setStrength(BindingStrength.EXAMPLE);
      return true;
    }
    if (!rDef.hasBinding()) {
      subset.setBinding(lDef.getBinding());
      superset.setBinding(lDef.getBinding().copy());
      superset.getBinding().setStrength(BindingStrength.EXAMPLE);
      return true;
    }
    ElementDefinitionBindingComponent left = lDef.getBinding();
    ElementDefinitionBindingComponent right = rDef.getBinding();
    
    // if they're both examples/preferred then:
    // subset: left wins if they're both the same
    // superset: 
    if (isPreferredOrExample(left) && isPreferredOrExample(right)) {
      if (right.getStrength() == BindingStrength.PREFERRED && left.getStrength() == BindingStrength.EXAMPLE) { 
        messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "Example/preferred bindings differ at "+path+" using binding from "+rightName(), IssueSeverity.INFORMATION));
        subset.setBinding(right);
        superset.setBinding(unionBindings(path, left, right));
      } else {
        messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "Example/preferred bindings differ at "+path+" using binding from "+leftName(), IssueSeverity.INFORMATION));
        subset.setBinding(left);
        superset.setBinding(unionBindings(path, left, right));
      }
      return true;
    }
    // if either of them are extensible/required, then it wins
    if (isPreferredOrExample(left)) {
      subset.setBinding(right);
      superset.setBinding(unionBindings(path, left, right));
      return true;
    }
    if (isPreferredOrExample(right)) {
      subset.setBinding(left);
      superset.setBinding(unionBindings(path, left, right));
      return true;
    }
    
    // ok, both are extensible or required.
    ElementDefinitionBindingComponent subBinding = new ElementDefinitionBindingComponent();
    subset.setBinding(subBinding);
    ElementDefinitionBindingComponent superBinding = new ElementDefinitionBindingComponent();
    superset.setBinding(superBinding);
    subBinding.setName(mergeText(path, "name", left.getName(), right.getName()));
    superBinding.setName(mergeText(null, "name", left.getName(), right.getName()));
    subBinding.setDescription(mergeText(path, "description", left.getDescription(), right.getDescription()));
    superBinding.setDescription(mergeText(null, "description", left.getDescription(), right.getDescription()));
    if (left.getStrength() == BindingStrength.REQUIRED || right.getStrength() == BindingStrength.REQUIRED)
      subBinding.setStrength(BindingStrength.REQUIRED);
    else
      subBinding.setStrength(BindingStrength.EXTENSIBLE);
    if (left.getStrength() == BindingStrength.EXTENSIBLE || right.getStrength() == BindingStrength.EXTENSIBLE)
      superBinding.setStrength(BindingStrength.EXTENSIBLE);
    else
      superBinding.setStrength(BindingStrength.REQUIRED);
    
    if (Base.compareDeep(left.getValueSet(), right.getValueSet(), false)) {
      subBinding.setValueSet(left.getValueSet());
      superBinding.setValueSet(left.getValueSet());
      return true;
    } else {
      // ok, now we compare the value sets. This may be unresolvable. 
      ValueSet lvs = resolveVS(leftStructure, left.getValueSet());
      ValueSet rvs = resolveVS(rightStructure, right.getValueSet());
      
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
          messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "The value sets "+lvs.getUrl()+" and "+rvs.getUrl()+" do not intersect", IssueSeverity.ERROR));
          return false;
        }
      }
      subBinding.setValueSet(new Reference().setReference("#"+addValueSet(cvs)));
      superBinding.setValueSet(new Reference().setReference("#"+addValueSet(unite(path, lvs, rvs))));
    }
    return false;
  }

  private ElementDefinitionBindingComponent unionBindings(String path, ElementDefinitionBindingComponent left, ElementDefinitionBindingComponent right) {
    ElementDefinitionBindingComponent union = new ElementDefinitionBindingComponent();
    if (left.getStrength().compareTo(right.getStrength()) < 0)
      union.setStrength(left.getStrength());
    else
      union.setStrength(right.getStrength());
    union.setDescription(mergeText(path, "binding.description", left.getDescription(), right.getDescription()));
    union.setName(mergeText(null, "binding.description", left.getDescription(), right.getDescription()));
    if (Base.compareDeep(left.getValueSet(), right.getValueSet(), false))
      union.setValueSet(left.getValueSet());
    else {
      ValueSet lvs = resolveVS(leftStructure, left.getValueSet());
      ValueSet rvs = resolveVS(leftStructure, right.getValueSet());
      if (lvs != null && rvs != null)
        union.setValueSet(new Reference().setReference("#"+addValueSet(unite(path, lvs, rvs))));
      else if (lvs != null)
        union.setValueSet(new Reference().setReference("#"+addValueSet(lvs)));
      else if (rvs != null)
        union.setValueSet(new Reference().setReference("#"+addValueSet(rvs)));
    }
    return union;
  }

  
  private ValueSet unite(String path, ValueSet lvs, ValueSet rvs) {
    ValueSet vs = new ValueSet();
    if (lvs.hasDefine())
      vs.getCompose().addInclude().setSystem(lvs.getDefine().getSystem());
    if (rvs.hasDefine())
      vs.getCompose().addInclude().setSystem(rvs.getDefine().getSystem());
    if (lvs.hasCompose()) {
      for (UriType imp : lvs.getCompose().getImport()) 
        vs.getCompose().getImport().add(imp);
      for (ConceptSetComponent inc : lvs.getCompose().getInclude()) 
        vs.getCompose().getInclude().add(inc);
      if (lvs.getCompose().hasExclude())
        messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "The value sets "+lvs.getUrl()+" has exclude statements, and no union involving it can be correctly determined", IssueSeverity.ERROR));
    }
    if (rvs.hasCompose()) {
      for (UriType imp : rvs.getCompose().getImport())
        if (!vs.getCompose().hasImport(imp.getValue()))
          vs.getCompose().getImport().add(imp);
      for (ConceptSetComponent inc : rvs.getCompose().getInclude())
        if (mergeIntoExisting(vs.getCompose().getInclude(), inc))
          vs.getCompose().getInclude().add(inc);
      if (rvs.getCompose().hasExclude())
        messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "The value sets "+lvs.getUrl()+" has exclude statements, and no union involving it can be correctly determined", IssueSeverity.ERROR));
    }    
    return vs;
  }

  private boolean mergeIntoExisting(List<ConceptSetComponent> include, ConceptSetComponent inc) {
    for (ConceptSetComponent dst : include) {
      if (Base.compareDeep(dst,  inc, false))
        return true; // they're actually the same
      if (dst.getSystem().equals(inc.getSystem())) {
        if (!inc.hasFilter() || dst.hasFilter()) {
          throw new Error("not done yet");
        } else if (inc.hasConcept() && dst.hasConcept()) {
          for (ConceptReferenceComponent cc : inc.getConcept()) {
            boolean found = false;
            for (ConceptReferenceComponent dd : dst.getConcept()) {
              if (dd.getCode().equals(cc.getCode()))
                found = true;
              if (found) {
                if (cc.hasDisplay() && !dd.hasDisplay())
                  dd.setDisplay(cc.getDisplay());
                break;
              }
            }
            if (!found)
              dst.getConcept().add(cc.copy());
          }
        } else
          dst.getConcept().clear(); // one of them includes the entire code system 
      }
    }
    return false;
  }

  private ValueSet resolveVS(StructureDefinition ctxtLeft, Type vsRef) {
    if (vsRef == null)
      return null;
    if (vsRef instanceof UriType)
      throw new Error("not done yet");
    else {
      Reference ref = (Reference) vsRef;
      if (!ref.hasReference())
        return null;
      return context.getValueSets().get(ref.getReference());
    }
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

  private Collection<? extends TypeRefComponent> intersectTypes(String path, List<TypeRefComponent> left, List<TypeRefComponent> right) {
    List<TypeRefComponent> result = new ArrayList<TypeRefComponent>();
    for (TypeRefComponent l : left) {
      boolean found = false;
      TypeRefComponent c = l.copy();
      for (TypeRefComponent r : right)
        if (l.getCode().equals("Reference") && r.getCode().equals("Reference")) {
          if (Base.compareDeep(l.getProfile(), r.getProfile(), false)) {
            found = true;
          }
        } else if (Utilities.equals(l.getCode(), r.getCode())) {
          found = true;
          // todo: compare profiles
          // todo: compare aggregation values
        }
      if (found)
        result.add(c);
    }
    return result;
  }

  private Collection<? extends TypeRefComponent> unionTypes(String path, List<TypeRefComponent> left, List<TypeRefComponent> right) {
    List<TypeRefComponent> result = new ArrayList<TypeRefComponent>();
    result.addAll(left);
    for (TypeRefComponent r : right) {
      boolean found = false;
      TypeRefComponent c = r.copy();
      for (TypeRefComponent l : left)
        if (l.getCode().equals("Reference") && r.getCode().equals("Reference")) {
          if (Base.compareDeep(l.getProfile(), r.getProfile(), false)) {
            found = true;
          }
        } else if (Utilities.equals(l.getCode(), r.getCode())) {
          found = true;
          // todo: compare profiles
          // todo: compare aggregation values
        }
      if (!found)
        result.add(c);
    }
    return result;
  }

  private String mergeText(String path, String name, String left, String right) {
    if (left == null && right == null)
      return null;
    if (left == null)
      return right;
    if (right == null)
      return left;
    if (left.equalsIgnoreCase(right))
      return left;
    if (path != null)
      messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.INFORMATIONAL, path, "Elements differ in definition for "+name+": \""+left+"\" vs \""+right+"\")", IssueSeverity.INFORMATION));
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
  private List<ElementDefinitionConstraintComponent> unionConstraints(String path, List<ElementDefinitionConstraintComponent> left, List<ElementDefinitionConstraintComponent> right) {
    List<ElementDefinitionConstraintComponent> result = new ArrayList<ElementDefinitionConstraintComponent>();
    for (ElementDefinitionConstraintComponent l : left) {
      boolean found = false;
      for (ElementDefinitionConstraintComponent r : right)
        if (Utilities.equals(r.getId(), l.getId()) || (Utilities.equals(r.getXpath(), l.getXpath()) && r.getSeverity() == l.getSeverity()))
          found = true;
      if (!found)
        messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "SturctureDefinition "+leftName()+" has a constraint that is not found in "+rightName()+" and it is uncertain whether they are compatible ("+l.getXpath()+")", IssueSeverity.WARNING));
      result.add(l);
    }
    for (ElementDefinitionConstraintComponent r : right) {
      boolean found = false;
      for (ElementDefinitionConstraintComponent l : left)
        if (Utilities.equals(r.getId(), l.getId()) || (Utilities.equals(r.getXpath(), l.getXpath()) && r.getSeverity() == l.getSeverity()))
          found = true;
      if (!found) {
        messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "SturctureDefinition "+rightName()+" has a constraint that is not found in "+leftName()+" and it is uncertain whether they are compatible ("+r.getXpath()+")", IssueSeverity.WARNING));
        result.add(r);
      }
    }
    return result;
  }


  private List<ElementDefinitionConstraintComponent> intersectConstraints(String path, List<ElementDefinitionConstraintComponent> left, List<ElementDefinitionConstraintComponent> right) {
  List<ElementDefinitionConstraintComponent> result = new ArrayList<ElementDefinitionConstraintComponent>();
  for (ElementDefinitionConstraintComponent l : left) {
    boolean found = false;
    for (ElementDefinitionConstraintComponent r : right)
      if (Utilities.equals(r.getId(), l.getId()) || (Utilities.equals(r.getXpath(), l.getXpath()) && r.getSeverity() == l.getSeverity()))
        found = true;
    if (found)
      result.add(l);
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

  private int intersectMin(int left, int right) {
    if (left > right)
      return left;
    else
      return right;
  }

  private int unionMin(int left, int right) {
    if (left > right)
      return right;
    else
      return left;
  }

  private String intersectMax(String left, String right) {
    int l = "*".equals(left) ? Integer.MAX_VALUE : Integer.parseInt(left);
    int r = "*".equals(right) ? Integer.MAX_VALUE : Integer.parseInt(right);
    if (l < r)
      return left;
    else
      return right;
  }

  private String unionMax(String left, String right) {
    int l = "*".equals(left) ? Integer.MAX_VALUE : Integer.parseInt(left);
    int r = "*".equals(right) ? Integer.MAX_VALUE : Integer.parseInt(right);
    if (l < r)
      return right;
    else
      return left;
  }

  private IntegerType intersectMaxLength(int left, int right) {
    if (left == 0) 
      left = Integer.MAX_VALUE;
    if (right == 0) 
      right = Integer.MAX_VALUE;
    if (left < right)
      return left == Integer.MAX_VALUE ? null : new IntegerType(left);
    else
      return right == Integer.MAX_VALUE ? null : new IntegerType(right);
  }

  private IntegerType unionMaxLength(int left, int right) {
    if (left == 0) 
      left = Integer.MAX_VALUE;
    if (right == 0) 
      right = Integer.MAX_VALUE;
    if (left < right)
      return right == Integer.MAX_VALUE ? null : new IntegerType(right);
    else
      return left == Integer.MAX_VALUE ? null : new IntegerType(left);
  }

  
  private boolean rule(boolean test, String path, String message) {
    if (!test) 
      messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, message, IssueSeverity.ERROR));
    return test;
  }

  private boolean ruleEqual(String path, String vLeft, String vRight, String description, boolean nullOK) {
    if (vLeft == null && vRight == null && nullOK)
      return true;
    if (vLeft == null && vRight == null) {
      messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, description+" and not null (null/null)", IssueSeverity.ERROR));
    }
    if (vLeft == null || !vLeft.equals(vRight)) {
      messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, description+" ("+vLeft+"/"+vRight+")", IssueSeverity.ERROR));
    }
    return true;
  }
  
  private boolean ruleEqual(boolean vLeft, boolean vRight, String path, String elementName) {
    if (vLeft != vRight) {
      messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, elementName+" must be the same ("+vLeft+"/"+vRight+")", IssueSeverity.ERROR));
    }
    return true;
  }

  private boolean ruleCompares(Type vLeft, Type vRight, String path, int nullStatus) throws Exception {
    if (vLeft == null && vRight == null && nullStatus == BOTH_NULL)
      return true;
    if (vLeft == null && vRight == null) {
      messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "Must be the same and not null (null/null)", IssueSeverity.ERROR));
    }
    if (vLeft == null && nullStatus == EITHER_NULL)
      return true;
    if (vRight == null && nullStatus == EITHER_NULL)
      return true;
    if (vLeft == null || vRight == null || !Base.compareDeep(vLeft, vRight, false)) {
      messages.add(new ValidationMessage(Source.ProfileComparer, IssueType.STRUCTURE, path, "Must be the same ("+toString(vLeft)+"/"+toString(vRight)+")", IssueSeverity.ERROR));
    }
    return true;
  }

  private String toString(Type val) throws Exception {
    if (val instanceof PrimitiveType) 
      return "\"" + ((PrimitiveType) val).getValueAsString()+"\"";
    
    JsonParser jp = new JsonParser();
    return jp.composeString(val, "value");
  }

  private int vsIndex = 0;
  
  public String addValueSet(ValueSet cvs) {
    vsIndex++;
    String id = Integer.toString(vsIndex);
    cvs.setId(id);
    valuesets.add(cvs);
    return id;
  }

  
}
