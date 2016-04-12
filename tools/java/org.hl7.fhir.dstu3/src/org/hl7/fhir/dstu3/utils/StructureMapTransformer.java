package org.hl7.fhir.dstu3.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.language.MatchRatingApproachEncoder;
import org.hl7.fhir.dstu3.exceptions.PathEngineException;
import org.hl7.fhir.dstu3.model.Base;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.ExpressionNode;
import org.hl7.fhir.dstu3.model.StructureMap;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapGroupComponent;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapGroupRuleComponent;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapGroupRuleSourceComponent;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapGroupRuleTargetComponent;
import org.hl7.fhir.dstu3.utils.StructureMapTransformer.ITransformerServices;
import org.hl7.fhir.dstu3.utils.StructureMapTransformer.TransformContext;
import org.hl7.fhir.dstu3.utils.StructureMapTransformer.Variable;
import org.hl7.fhir.dstu3.utils.StructureMapTransformer.VariableMode;
import org.hl7.fhir.dstu3.utils.StructureMapTransformer.Variables;

public class StructureMapTransformer {

  public enum VariableMode {
    INPUT, OUTPUT
  }

  public class Variable {
    private VariableMode mode;
    private String name;
    private Base object;
    public Variable(VariableMode mode, String name, Base object) {
      super();
      this.mode = mode;
      this.name = name;
      this.object = object;
    }
    public VariableMode getMode() {
      return mode;
    }
    public String getName() {
      return name;
    }
    public Base getObject() {
      return object;
    }
    
  }

  public class Variables {
    private List<Variable> list = new ArrayList<Variable>();

    public void add(VariableMode mode, String name, Base object) {
      Variable vv = null;
      for (Variable v : list) 
        if ((v.mode == mode) && v.getName().equals(name))
          vv = v;
      if (vv != null)
        list.remove(vv);
      list.add(new Variable(mode, name, object));
    }

    public Variables copy() {
      Variables result = new Variables();
      result.list.addAll(list);
      return result;
    }

    public Base get(VariableMode mode, String name) {
      for (Variable v : list) 
        if ((v.mode == mode) && v.getName().equals(name))
          return v.getObject();
      return null;
    }
  }

  public class TransformContext {
    private Object appInfo;

    public TransformContext(Object appInfo) {
      super();
      this.appInfo = appInfo;
    }

    public Object getAppInfo() {
      return appInfo;
    }
    
  }

  public interface ITransformerServices {
    public boolean validateByValueSet(Coding code, String valuesetId);
//    public Coding translate(Coding code)
//    ValueSet validation operation
//    Translation operation
//    Lookup another tree of data
//    Create an instance tree
//    Return the correct string format to refer to a tree (input or output)

  }
  private Map<String, StructureMap> library;
  private ITransformerServices services;
  private IWorkerContext context;
  private FHIRPathEngine fpe;
  
  public StructureMapTransformer(IWorkerContext context, Map<String, StructureMap> library, ITransformerServices services) {
    super();
    this.context = context;
    this.library = library;
    this.services = services;
    fpe = new FHIRPathEngine(context);
  }

  /**
   * Given an item, return all the children that conform to the pattern described in name
   * 
   * Possible patterns:
   *  - a simple name (which may be the base of a name with [] e.g. value[x])
   *  - a name with a type replacement e.g. valueCodeableConcept
   *  - * which means all children
   *  - ** which means all descendents
   *  
   * @param item
   * @param name
   * @param result
   */
  protected void getChildrenByName(Base item, String name, List<Base> result) {
    for (Base v : item.listChildrenByName(name))
      if (v != null)
        result.add(v);
  }

  public Base transform(Base source, StructureMap map) {
    return null;
  }
  
  public void transform(Object appInfo, Base source, StructureMap map, Base target) throws Exception {
    TransformContext context = new TransformContext(appInfo);
    Variables vars = new Variables();
    vars.add(VariableMode.INPUT, "src", source);
    vars.add(VariableMode.OUTPUT, "tgt", target);
    
    executeGroup(context, vars, map.getGroup().get(0));
  }

  private void executeGroup(TransformContext context, Variables vars, StructureMapGroupComponent group) throws Exception {
    // todo: extends
    // todo: check inputs
    for (StructureMapGroupRuleComponent r : group.getRule()) {
      executeRule(context, vars, r);
    }
  }

  private void executeRule(TransformContext context, Variables vars, StructureMapGroupRuleComponent rule) throws Exception {
    Variables srcVars = vars.copy();
    if (rule.getSource().size() != 1)
      throw new Exception("not handled yet");
    List<Variables> source = analyseSource(context, srcVars, rule.getSource().get(0)); 
    for (Variables v : source) {
      for (StructureMapGroupRuleTargetComponent t : rule.getTarget()) {
        processTarget(context, v, t);
      }
      // process dependencies
    }
  }
  
  private List<Variables> analyseSource(TransformContext context, Variables vars, StructureMapGroupRuleSourceComponent src) throws Exception {
    List<Base> sources = new ArrayList<Base>();
    List<Base> items = new ArrayList<Base>();
    Base b = vars.get(VariableMode.INPUT, src.getContext());
    if (!src.hasElement()) 
      items.add(b);
    else 
      getChildrenByName(b, src.getElement(), items);
    if (src.hasCondition()) {
      ExpressionNode expr = (ExpressionNode) src.getUserData(StructureMapCompiler.MAP_WHERE_EXPRESSION);
      if (expr == null) {
        expr = fpe.parse(src.getCondition());
//        fpe.check(context.appInfo, ??, ??, expr)
        src.setUserData(StructureMapCompiler.MAP_WHERE_EXPRESSION, expr);
      }
      for (Base item : items)
        if (fpe.evaluateToBoolean(null, item, expr))
          sources.add(item);
    } else
      sources.addAll(items);
    if (src.hasCheck()) {
      ExpressionNode expr = (ExpressionNode) src.getUserData(StructureMapCompiler.MAP_WHERE_CHECK);
      if (expr == null) {
        expr = fpe.parse(src.getCondition());
//        fpe.check(context.appInfo, ??, ??, expr)
        src.setUserData(StructureMapCompiler.MAP_WHERE_CHECK, expr);
      }
      for (Base item : sources)
        if (!fpe.evaluateToBoolean(null, item, expr))
          throw new Exception("Check condition failed");
    } 
    List<Variables> result = new ArrayList<Variables>();
    for (Base r : sources) {
      Variables v = vars.copy();
      if (src.hasVariable())
        v.add(VariableMode.INPUT, src.getVariable(), r);
      result.add(v); 
    }
    return result;
  }
  
  
  private void processTarget(TransformContext context, Variables tgtVars, StructureMapGroupRuleTargetComponent t) {
    
    
  }

  
}
